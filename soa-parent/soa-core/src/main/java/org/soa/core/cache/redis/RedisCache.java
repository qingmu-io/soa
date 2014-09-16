package org.soa.core.cache.redis;

import org.soa.core.cache.serialize.ISerialize;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCache  implements Cache{

	private ISerialize serialize =  null;
	
	private JedisPool jedisPool = null;
	
	public ISerialize getSerialize() {
		return serialize;
	}

	public void setSerialize(ISerialize serialize) {
		this.serialize = serialize;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String getName() {
		return "redisCache";
	}

	@Override
	public JedisPool getNativeCache() {
		return this.jedisPool;
	}

	@Override
	public ValueWrapper get(Object key) {
		Jedis jedis = null;
		Object value = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = this.jedisPool.getResource();
			value = serialize.unserialize(jedis.get(serialize.serialize(key
					.hashCode())));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		return value!=null?new SimpleValueWrapper(value):null;
	}

	@Override
	public void put(Object key, Object value) {
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = this.jedisPool.getResource();
			jedis.set(serialize.serialize(key.hashCode()),
					serialize.serialize(value));
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		
	}

	@Override
	public void evict(Object key) {
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = this.jedisPool.getResource();
			jedis.expire(serialize.serialize(key.hashCode()), 0);
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		boolean borrowOrOprSuccess = true;
		try {
			jedis = this.jedisPool.getResource();
			jedis.flushDB();
			jedis.flushAll();
		} catch (JedisConnectionException e) {
			borrowOrOprSuccess = false;
			if (jedis != null)
				jedisPool.returnBrokenResource(jedis);
		} finally {
			if (borrowOrOprSuccess)
				jedisPool.returnResource(jedis);
		}
		
	}

}
