package com.dianwoda.test.bassy.web.config.aop;

import com.dianwoda.test.bassy.common.exception.error.BassyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ControllerAspect
 */
@Component
@Aspect
@Order(200)
@Slf4j
public class ControllerAspect {

    private static Gson gson = null;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        gson = gsonBuilder.create();
    }

    /**
     * Do around method object.
     *
     * @param pig the pig
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("execution(* com.dianwoda.test.bassy.web.controller.*.*(..))")
    public Object doAroundMethod(ProceedingJoinPoint pig) throws Throwable {
        Object obj = null;
        long startTime = System.currentTimeMillis();
        Method method = ((MethodSignature) pig.getSignature()).getMethod();
        try {
            log.info("方法：{}，参数：{}", method.getName(), formatParam(pig, method));
            obj = pig.proceed();
        } catch (DataAccessException ex) {
            obj = resultExceptionObjectBuilding(pig, BassyError.DATABSE_ERROR.getCode(),
                    BassyError.DATABSE_ERROR.getMessage(),
                    ex);
        } catch (Throwable ex) {
            obj = resultExceptionObjectBuilding(pig, BassyError.SYS_ERROR.getCode(),
                    ex.getMessage(), ex);
        } finally {
            log.info("方法：{}，参数：{}，结果：{}，耗时：{}ms", method.getName(), formatParam(pig, method), gson.toJson(obj), System.currentTimeMillis() - startTime);
        }
        return obj;
    }

    private String formatParam(ProceedingJoinPoint pig, Method method) {
        Object[] args = pig.getArgs();
        Class<?>[] paramNames = method.getParameterTypes();
        StringBuilder argstr = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (!(args[i] instanceof HttpServletRequest) && !(args[i] instanceof HttpServletResponse)) {
                argstr.append(paramNames[i]).append(":").append(gson.toJson(args[i]))
                        .append("\n");
            }
        }
        return argstr.toString();
    }


    private Object resultExceptionObjectBuilding(ProceedingJoinPoint pig, String errorCode, String errorMessage,
                                                 Throwable ex) {
        MethodSignature methodSign = (MethodSignature) pig.getSignature();
        Method method = methodSign.getMethod();
        String argstr = formatParam(pig, method);
        log.error(ex.getMessage() + "\n method:" + method.toString() + "\n arguments:{\n" + argstr + "}",
                ex);
        throw new RuntimeException(ex);
    }
}
