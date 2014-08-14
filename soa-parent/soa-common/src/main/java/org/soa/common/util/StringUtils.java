package org.soa.common.util;

import java.util.UUID;

public class StringUtils {

	public final static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
