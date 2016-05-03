package io.goudai.soa.core.serialize;

public interface Serialize {
	public  byte[] serialize(Object object);
	public  Object unserialize(byte[] bytes);
}