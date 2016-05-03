package io.goudai.soa.core.serialize;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


@Service("serialize")
public class JdkSerialize implements Serialize {

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
	
	public JdkSerialize() {
	}
	
	
}

