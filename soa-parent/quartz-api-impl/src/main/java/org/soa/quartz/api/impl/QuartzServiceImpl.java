package org.soa.quartz.api.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.soa.common.context.SoaContext;
import org.soa.common.exception.QuartzException;
import org.soa.core.service.BaseService;
import org.soa.quartz.api.QuartzService;
import org.soa.quartz.core.QuartzManger;
import org.soa.quartz.core.Status;
import org.springframework.stereotype.Service;



@Service("quartzService")
public class QuartzServiceImpl extends BaseService implements QuartzService {
	private final static String QUARTZ = "QUARTZ";
	private static final String LOAD = "load";
	//通过作业名称统计作业个数
	private static final String COUNTQRTJOBDETAILS = "countQrtzJobDetails";
	//通过作业触发器名称统计
	private static final String COUNTQRTZCRONTRIGGERS = "countQrtzCronTriggers";
	//通过作业类统计
	private static final String COUNTJOBCLASS = "countJobClass";
	private static final String LISTALL = "listAll";
	public volatile boolean isInit = Boolean.FALSE;
	@Resource
	private QuartzManger quartzManger;
	
	
	@Override
	public SoaContext modifyJob(SoaContext context) {
		super.update(context);
		this.quartzManger.rescheduleJob(context);
		return context;
	}

	@Override
	public SoaContext deleteJob(SoaContext context) {
		super.delete(context);
		this.quartzManger.deleteJob(context);
		return context;
	}

	@Override
	public SoaContext pauseJob(SoaContext context) {
		context.addAttr("status", Status.PAUSE);
		super.update(context);
		this.quartzManger.pauseJob(context);
		return context;
	}

	@Override
	public SoaContext resumeJob(SoaContext context) {
		context.addAttr("status", Status.RUN);
		super.update(context);
		this.quartzManger.resumeJob(context);
		return context;
	}

	@Override
	public Trigger getTrigger(SoaContext context) {
		return this.quartzManger.getTrigger(context);
	}

	@Override
	public Trigger getTriggerById(SoaContext context) {
		return this.getTrigger(super.queryStatement(context, LOAD));
	}

	@Override
	public JobDetail getJobDetail(SoaContext context) {
		return this.quartzManger.getJobDetail(context);
	}

	@Override
	public JobDetail getJobDetailById(SoaContext context) {
		return this.getJobDetail(super.queryStatement(context, LOAD));
	}

	@Override
	public SoaContext get(SoaContext context) {
		return super.queryStatement(context, LOAD);
	}

	@Override
	public SoaContext page(SoaContext context) {
		return super.queryByPage(context);
	}

	@Override
	public SoaContext pauseAll(SoaContext context) {
		this.quartzManger.pauseAll();
		context.addAttr("STATUS", -1);
		this.update(context);
		return context;
	}

	@Override
	public SoaContext resumeAll(SoaContext context) {
		this.quartzManger.resumeAll();
		context.addAttr("STATUS", 1);
		this.update(context);
		return context;
	}


	@Override
	public String getNameSpace() {
		return QUARTZ;
	}
	
	public void setQuartzManger(QuartzManger quartzManger) {
		this.quartzManger = quartzManger;
	}

	@Override
	public SoaContext addJob(SoaContext context) {
		
		
		final String code = context.getStringAttr("src");
		final String clazz = context.getStringAttr("clazz");
		if(StringUtils.isNotBlank(code)){
			JdkCompiler.INSTA.compile(code, clazz);
			JdkCompiler.INSTA.loadClass();
		}
		
		 try {
			int count = super.count(context, COUNTQRTJOBDETAILS);
			if(count>0){
				throw new QuartzException("作业名称已经存在");
			}
			count = super.count(context, COUNTQRTZCRONTRIGGERS);
			if(count>0){
				throw new QuartzException("触发器名称已经存在");
			}
			count = super.count(context, COUNTJOBCLASS);
			if(count>0) {
				throw new QuartzException("该作业类已经在存在");
			}
			this.quartzManger.addJob(context);
			this.insert(context);
			context.getAttr().clear();
			return context;
		} catch (QuartzException e) {
			throw new QuartzException(e.getMessage(),e);
		}
	}


	
	

	@Override
	public SoaContext start(SoaContext context) {
		SoaContext result = super.queryStatement(context, LISTALL);
		List<java.util.Map<String, Object>> rows = result.getRows();
		if(rows.isEmpty())return context;
		JdkCompiler insta = JdkCompiler.INSTA;
		for (Map<String, Object> map : rows) {
			final String src = (String)map.get("src");
			final String clazz = (String)map.get("clazz");
			if(StringUtils.isNoneBlank(src)
				&& StringUtils.isNoneBlank(clazz)){
				insta.compile(src, clazz);
			}
		}
		insta.loadClass();
		this.quartzManger.start();
		for (Map<String, Object> map : rows) {
			System.out.println(map.get("status").getClass());
			final long status = (long)map.get("status");
			if(status == 1){
				context.setAttr(map);
				this.quartzManger.pauseJob(context);
			}
		}
		return context;
	}

}
