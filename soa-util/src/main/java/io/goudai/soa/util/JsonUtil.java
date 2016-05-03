package io.goudai.soa.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @author liuyi
 * 
 */
public class JsonUtil implements Serializable {
	private static final long serialVersionUID = -8872078079583695100L;
	private volatile static JsonUtil jsonUtil = new JsonUtil();

	private JsonUtil() {
	}

	public static JsonUtil getInstance() {
		return jsonUtil;
	}

	public String object2JSON(Object obj, SerializerFeature serializerFeature) {
		if (obj == null) {
			return "{}";
		}
		return JSON.toJSONString(obj, serializerFeature);
	}

	public String object2JSON(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
	}

	public <T> T json2Object(String json, Class<T> clazz) {
		if (json == null || json.isEmpty()) {
			return null;
		}
		return JSON.parseObject(json, clazz);
	}

	public <T> T json2Reference(String json, TypeReference<T> reference) {
		if (json == null || json.isEmpty()) {
			return null;
		}
		return JSON.parseObject(json, reference);
	}
}
