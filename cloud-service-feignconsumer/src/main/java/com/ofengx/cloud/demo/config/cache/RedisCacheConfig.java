package com.yangst.cloud.demo.config.cache;

import com.yangst.cloud.demo.config.properties.RedisConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;


/**
 * redis配置
 */
@Configuration
@EnableAutoConfiguration
public class RedisCacheConfig {
	Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);

	@Autowired
	private JdkSerializationRedisSerializer valueSerializer;

	@Autowired
	private StringRedisSerializer keySerializer;
	
	@Autowired
	private RedisConfig redisConfig;

	@Bean
	// @ConfigurationProperties(prefix="spring.redis")
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(redisConfig.getMaxTotal());
		config.setMaxIdle(redisConfig.getMaxIdle());
		config.setMinIdle(redisConfig.getMinIdle());
		config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
		logger.info("JedisPoolConfig bean init success.");
		return config;
	}

	@Bean
	// @ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		factory.setHostName(redisConfig.getHost());
		factory.setPort(redisConfig.getPort());
		if (StringUtils.isNotBlank(redisConfig.getPassword())) {
			factory.setPassword(redisConfig.getPassword().trim());
		}
		logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}

	// @Bean
	// public JedisConnectionFactory redisConnectionFactory() {
	// JedisConnectionFactory factory = new JedisConnectionFactory();
	// factory.setUsePool(true);
	// factory.setHostName(host);
	// factory.setPort(port);
	// if (StringUtils.isNotBlank(password)) {
	// factory.setPassword(password.trim());
	// }
	// return factory;
	// }

	// @Bean
	// public RedisTemplate<?, ?> getRedisTemplate(){
	// RedisTemplate<?,?> template = new
	// StringRedisTemplate(getConnectionFactory());
	// return template;
	// }

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setKeySerializer(keySerializer);
		template.setValueSerializer(valueSerializer);
		template.setDefaultSerializer(valueSerializer);
		template.setHashValueSerializer(valueSerializer);
		template.setConnectionFactory(factory);
		return template;
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean
	public JdkSerializationRedisSerializer jdkRedisSerializer() {
		JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
		return jdkRedisSerializer;
	}

	@Bean
	public CacheClient redisClientFactory() {
		CacheClient cc = new BaseRedisCacheImpl();
		return cc;
	}

}
