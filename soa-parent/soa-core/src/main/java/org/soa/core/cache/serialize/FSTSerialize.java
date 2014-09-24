package org.soa.core.cache.serialize;

import org.nustaq.serialization.FSTConfiguration;

public class FSTSerialize implements ISerialize {
	FSTConfiguration configuration = FSTConfiguration
			.createStructConfiguration();

	@Override
	public byte[] serialize(Object object) {
		return configuration.asByteArray(object);
	}

	@Override
	public Object unserialize(byte[] bytes) {
		return configuration.asObject(bytes);
	}

}
