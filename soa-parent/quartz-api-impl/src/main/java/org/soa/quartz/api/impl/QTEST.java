package org.soa.quartz.api.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.soa.common.context.SoaContext;
import org.soa.quartz.api.QuartzService;
import org.soa.quartz.api.manger.QuartzSoaManager;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.extension.SPI;

@Service
public class QTEST {
	
	@Resource
	private QuartzSoaManager quartzSoaManager;
	
	/**
	 * 	private int guid;
	private String jobName;
	private String jobGroup;
	private String clazz;
	private String cronExpression;
	private String triggerName;
	private String triggerGroup;
	 */
	@PostConstruct
	public void test(){
		SoaContext context = new SoaContext();
		context.addAttr("guid", "ADFASFDKAKJasdflkjdfa");
		context.addAttr("jobName", "adfa");
		context.addAttr("jobGroup", "fghgfh");
		context.addAttr("status", 1);
		context.addAttr("triggerName", "triggerName");
		context.addAttr("triggerGroup", "triggerGroup");
		context.addAttr("clazz", "org.soa.quartz.api.job.TestJob");
		context.addAttr("cronExpression", "0/5 * * * * ?");
		context.setService("quartzService");
		context.setMethod("addJob");
		quartzSoaManager.invoke(context);
	}

}
