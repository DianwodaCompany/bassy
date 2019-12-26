package com.dianwoda.test.bassy.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * transform bean to another bean or transform list to another list
 */
public class BassyUtil {
    private final static Logger logger = LoggerFactory.getLogger(BassyUtil.class);

    public static <T> T tranformToBean(Object source, Class<T> tclaz) {
        return tranformToBean(source, tclaz, null);
    }

    public static <T> T tranformToBean(Object source, Class<T> tclaz, String... ignoreProperties) {
        try {
            T obj = tclaz.newInstance();
            BeanUtils.copyProperties(source, obj, ignoreProperties);
            return obj;
        } catch (Throwable e) {
            logger.error("【BassyUtil-tranformToBean-error】error:", e);
        }
        return null;
    }

    public static <T> List<T> tranformToList(List source, Class<T> tclaz) {
        return tranformToList(source, tclaz, null);
    }

    public static <T> List<T> tranformToList(List source, Class<T> tclaz, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        List<T> res = new ArrayList<>();
        source.forEach(sor -> {
            try {
                T obj = tclaz.newInstance();
                BeanUtils.copyProperties(sor, obj, ignoreProperties);
                res.add(obj);
            } catch (Throwable e) {
                logger.error("【BassyUtil-tranformToList-error】error:", e);
            }
        });
        return res;
    }

    public static int matchCount(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher match = p.matcher(str);
        int count = 0;
        while(match.find()){
            count ++;
        }
        return count;
    }
}
