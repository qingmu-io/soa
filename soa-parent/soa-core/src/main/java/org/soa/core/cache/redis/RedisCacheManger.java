package org.soa.core.cache.redis;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

public class RedisCacheManger extends
AbstractTransactionSupportingCacheManager {
	
	private RedisCache cache;
	Collection<String> collection = new ArrayList<>();
	Collection<Cache> caches = new ArrayList<>();

	@Override
	public Cache getCache(String name) {
		this.collection.add(name);
		return this.cache;
	}

	@Override
	public Collection<String> getCacheNames() {
		return this.collection;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return this.caches;
	}


	public void setCache(RedisCache cache) {
		this.cache = cache;
	}


	
}
