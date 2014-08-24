package org.soa.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.ExecutorServiceUtil;

public class SoaLogger  {
	
	private static final ExecutorService executorService = ExecutorServiceUtil.newExecutorService();
	
	static Map<String, Logger> loggerStorage = new HashMap<>();

	public static void debug(final Class<?> clazz, final String msg, final Object... arg) {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				getLogger(clazz).debug(msg, arg);
			}
		});
	}

	public static void error(final Class<?> clazz, final String msg, final Object... arg) {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				getLogger(clazz).error(msg, arg);
			}
		});
		
	}

	public static void warn(final Class<?> clazz, final String msg, final Object... arg) {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				getLogger(clazz).error(msg, arg);
			}
		});

	}

	private static synchronized Logger getLogger(Class<?> clazz) {
		Logger logger = SoaLogger.loggerStorage.get(clazz.getName());
		if (logger == null) {
			logger = LoggerFactory.getLogger(clazz);
			SoaLogger.loggerStorage.put(clazz.getName(), logger);
		}
		return logger;
	}

}
