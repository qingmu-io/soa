package org.soa.common.util;

import java.util.UUID;

public class IdUtil {
	
		public static String salt(){
			return UUID.randomUUID().toString().substring(0, 10);
		}
		
}
