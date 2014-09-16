package org.soa.core.cache.serialize;

public interface ISerialize {
	public  byte[] serialize(Object object);
	public  Object unserialize(byte[] bytes);
}