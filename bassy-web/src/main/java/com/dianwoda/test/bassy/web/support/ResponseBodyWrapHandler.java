package com.dianwoda.test.bassy.web.support;

import com.dianwoda.test.bassy.common.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/** wrap RequestResponseBodyMethodProcessor to return a common response
 * @author lichengkai
 * 2018 - 04 - 28 : 15:52
 * Copyright(c) for dianwoda
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public  ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){
        this.delegate=delegate;
    }
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {
        CommonResponse result = CommonResponse.success(o);
        delegate.handleReturnValue(result,methodParameter,modelAndViewContainer,nativeWebRequest);
    }
}
