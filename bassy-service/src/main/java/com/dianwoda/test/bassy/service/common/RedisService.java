package com.dianwoda.test.bassy.service.common;

import org.springframework.data.redis.core.RedisTemplate;

public interface RedisService {
    RedisTemplate<Object, Object> getRedisTemplate();

    void set(String key, Object value);

    void set(String key, Object value, Long timeout);

    Object get(String key);

    void delete(String key);
}
