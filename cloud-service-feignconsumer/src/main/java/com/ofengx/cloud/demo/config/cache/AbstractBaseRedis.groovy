package com.ofengx.cloud.demo.config.cache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate

abstract class AbstractBaseRedis {
    @SuppressWarnings("rawtypes")
    @Autowired
    protected RedisTemplate redisTemplate

    @SuppressWarnings("rawtypes")
    void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate
    }

}
