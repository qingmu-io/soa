package org.soa.common.restful.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.PropertyUtilsBean;

import com.google.common.collect.Maps;

public enum MapUtil {
		mapUtil;
		PropertyUtilsBean propertyUtils = BeanUtilsBean2.getInstance().getPropertyUtils();
		
		public Map<String, Object> objToMap(Object bean){
			try {
				Field[] declaredFields = bean.getClass().getDeclaredFields();
				HashMap<String, Object> result = Maps.newHashMap();
				for(Field field : declaredFields){
					result.put(field.getName(), propertyUtils.getProperty(bean, field.getName()));
				}
				return result;
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("转换错误");
			
		}
		
}
