package org.soa.core.cache.redis;

import org.soa.core.cache.serialize.ISerialize;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.JedisPool;

public class RedisCacheFactoryBean implements FactoryBean<RedisCache>,
		BeanNameAware, InitializingBean {

	private RedisCache redisCache;

	private JedisPool jedisPool;
	private ISerialize serialize;
	private String name;

	@Override
	public void afterPropertiesSet() {
		this.redisCache = new RedisCache();
		redisCache.setJedisPool(jedisPool);
		redisCache.setSerialize(serialize);
	}

	@Override
	public void setBeanName(String name) {
		this.name = name;
	}

	@Override
	public RedisCache getObject() throws Exception {
		return this.redisCache;
	}

	@Override
	public Class<?> getObjectType() {
		return RedisCache.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public ISerialize getSerialize() {
		return serialize;
	}

	public void setSerialize(ISerialize serialize) {
		this.serialize = serialize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
