package org.soa.quartz.api.impl;

import javax.annotation.Resource;

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
	private static final String RESUMEALL = "resumeAll";
	private static final String LOAD = "load";
	//通过作业名称统计作业个数
	private static final String COUNTQRTJOBDETAILS = "countQrtzJobDetails";
	//通过作业触发器名称统计
	private static final String COUNTQRTZCRONTRIGGERS = "countQrtzCronTriggers";
	//通过作业类统计
	private static final String COUNTJOBCLASS = "countJobClass";
	
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
			
		}
		return context;
	}
	

}
