package io.goudai.soa.quartz.api.impl;

import com.alibaba.dubbo.common.compiler.support.JavassistCompiler;
import io.goudai.soa.common.context.SoaContext;
import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionContext;
import io.goudai.soa.quartz.api.manger.QuartzSoaManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

//@Service
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
	 * @throws IOException 
	 */
	@PostConstruct
	public void test() throws IOException{
		String src2 = "package org.soa.quartz.api.job;"+
				"public class Job6 implements org.quartz.Job {"+
				"public void execute(org.quartz.JobExecutionContext context)"+
				"throws org.quartz.JobExecutionException {"+
				"System.out.println(\"我是动态添加的jobss\");"+
				"}"+
				"}";

		
		
		SoaContext context = new SoaContext();
		context.addAttr("guid", 1);
		context.addAttr("jobName", "jobname");
		context.addAttr("jobGroup", "jobgroup");
		context.addAttr("status", 1);
		context.addAttr("triggerName", "triggerName2");
		context.addAttr("triggerGroup", "triggerGroup3");
		context.addAttr("clazz", "org.soa.quartz.api.job.Job6");
		context.addAttr("cronExpression", "0/5 * * * * ?");
		context.addAttr("src", src2);
		context.setService("quartzService");
//		context.setMethod("pauseAll");
		context.setMethod("start");
		File file = new File("C:/img.jpg");
		FileInputStream in = new FileInputStream(file);
//		quartzSoaManager.invoke(context);
		context.addAttr("file", FileUtils.readFileToByteArray(file));
		context.setMethod("upload");
//		quartzSoaManager.invoke(context);
	}
	
	public static void main2()  {
		
		try {
			String src = "package org.soa.quartz.api.job;"+

"public class Job3 implements org.quartz.Job {"+
"	public void execute(org.quartz.JobExecutionContext context)"+
"			throws org.quartz.JobExecutionException {"+
"			System.out.println(\"我是动态添加的job\");"+
"	}"+
"}";
			JavassistCompiler compiler2 = new JavassistCompiler();
			final Class<?> clazz = compiler2.compile(src,ClassLoader.getSystemClassLoader());
			final Object newInstance = Class.forName("org.soa.quartz.api.job.Job3").newInstance();
			System.out.println(newInstance);
			final Method method = clazz.getMethod("execute", JobExecutionContext.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
