package org.soa.logger;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoaLogger {

	static Map<String, Logger> loggerStorage = new HashMap<>();

	public static void debug(Class<?> clazz, String msg, Object... arg) {
		getLogger(clazz).debug(msg, arg);
	}

	public static void error(Class<?> clazz, String msg, Object... arg) {
		getLogger(clazz).error(msg, arg);
	}

	public static void warn(Class<?> clazz, String msg, Object... arg) {
		getLogger(clazz).error(msg, arg);

	}

	private static Logger getLogger(Class<?> clazz) {
		Logger logger = SoaLogger.loggerStorage.get(clazz.getName());
		if (logger == null) {
			logger = LoggerFactory.getLogger(clazz);
			SoaLogger.loggerStorage.put(clazz.getName(), logger);
		}
		return logger;
	}

}
