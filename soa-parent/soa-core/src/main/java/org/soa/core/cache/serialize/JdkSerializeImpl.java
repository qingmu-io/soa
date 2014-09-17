package org.soa.core.cache.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Service;


@Service("serialize")
public class JdkSerializeImpl implements ISerialize {

	public byte[] serialize(Object object) {
		if(object == null )return null;
		// 序列化
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(object);
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object unserialize(byte[] bytes) {
		if (bytes == null)
			return null;
		// 反序列化
		try (
			 ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			 ObjectInputStream ois = new ObjectInputStream(bais)){
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JdkSerializeImpl() {
	}
	
	
}

