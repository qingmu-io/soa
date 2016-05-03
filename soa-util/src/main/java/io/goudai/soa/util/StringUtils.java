package io.goudai.soa.util;

import java.util.UUID;

public class StringUtils {

	public final static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public final static boolean isBank(String string){
			return org.apache.commons.lang3.StringUtils.isBlank(string);
	}
	public final static boolean isNotBank(String string){
		return org.apache.commons.lang3.StringUtils.isNotBlank(string);
	}
	
	
}
