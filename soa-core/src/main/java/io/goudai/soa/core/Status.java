package io.goudai.soa.core;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public final class Status {

	public DefaultTransactionDefinition defaultTransactionDefinition;
	public TransactionStatus tx;
	public boolean isCanTX;
	public PlatformTransactionManager txManager;
	public boolean needRollback = false;
	
}
