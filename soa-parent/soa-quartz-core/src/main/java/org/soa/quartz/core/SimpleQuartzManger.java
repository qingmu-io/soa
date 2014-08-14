package org.soa.quartz.core;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.soa.common.context.SoaContext;
import org.soa.common.exception.QuartzException;
import org.soa.logger.SoaLogger;
import org.soa.quartz.core.util.ClazzUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: SimpleQuzrtzManger
 * @Description: TODO(作业调度管理者)
 * @author LiuYi
 * @date 2014年7月9日 下午3:05:27
 */
@Service("quartzManger")
public class SimpleQuartzManger implements QuartzManger {

	@Resource
	private Scheduler scheduler;
	
	@PostConstruct
	public void syso() {
		System.out.println("通过spring注入的schefuler + " + this.scheduler);
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#addJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void addJob(SoaContext context) {
		try {
			this.scheduler.start();
			final SchedulerContext context2 = this.scheduler.getContext();
			final Iterator<Entry<String, Object>> iterator = context2.entrySet().iterator();
			while(iterator.hasNext()){
				final Entry<String, Object> next = iterator.next();
				System.out.println(next.getKey() +" : " +next.getValue());
			}
			final JobKey jobKey = JobKey.jobKey(context.getStringAttr("jobName"));
			final TriggerKey triggerKey = TriggerKey.triggerKey(context.getStringAttr("triggerName"));
			if(
			this.scheduler.checkExists(jobKey)
			|| this.scheduler.checkExists(triggerKey)
			
					){
				//表示该任务已经被添加进了quartz维护的数据表中
			}else
				scheduler.scheduleJob(buildJob(context), buildTrigger(context));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#buildTrigger(org.soa.common.context.SoaContext)
	 */
	@Override
	public CronTrigger buildTrigger(SoaContext context) {
		return TriggerBuilder
				.newTrigger()
				.withIdentity(context.getStringAttr("triggerName"),
						context.getStringAttr("triggerGroup"))
				.withSchedule(
						CronScheduleBuilder.cronSchedule(context
								.getStringAttr("cronExpression"))).build();
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#buildJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public JobDetail buildJob(SoaContext context) {
		return JobBuilder
				.newJob(ClazzUtils.stringToClazz(context.getStringAttr("clazz")))
				.withIdentity(context.getStringAttr("jobName"),
						context.getStringAttr("jobGroup")).build();
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#modifyJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void modifyJob(SoaContext context) {
		try {
			scheduler.rescheduleJob(
					TriggerKey.triggerKey(
					context.getStringAttr("triggerName"),
					context.getStringAttr("triggerGroup")), 
					buildTrigger(context));
		} catch (SchedulerException e) {
			throw new QuartzException("暂停作业"+context.getStringAttr("triggerName")+"失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#deleteJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void deleteJob(SoaContext context) {
		try {
			scheduler.deleteJob(JobKey.jobKey(context.getStringAttr("jobName"),
					context.getStringAttr("jobGroup")));
		} catch (SchedulerException e) {
			throw new QuartzException("移除作业"+context.getStringAttr("triggerName")+"失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#pauseJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void pauseJob(SoaContext context) {
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(
					context.getStringAttr("triggerName"),
					context.getStringAttr("triggerGroup")));
		} catch (SchedulerException e) {
			throw new QuartzException("暂停作业"+context.getStringAttr("triggerName")+"失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#resumeJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void resumeJob(SoaContext context) {
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(
					context.getStringAttr("triggerName"),
					context.getStringAttr("triggerGroup")));
		} catch (SchedulerException e) {
			throw new QuartzException("重新激活失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#getTrigger(org.soa.common.context.SoaContext)
	 */
	@Override
	public Trigger getTrigger(SoaContext context) {
		try {
			return scheduler.getTrigger(TriggerKey.triggerKey(
					context.getStringAttr("triggerName"),
					context.getStringAttr("triggerGroup")));
		} catch (SchedulerException e) {
			throw new QuartzException("获取触发器失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#getJobDetail(org.soa.common.context.SoaContext)
	 */
	@Override
	public JobDetail getJobDetail(SoaContext context) {
		try {
			return scheduler.getJobDetail(JobKey.jobKey(
					context.getStringAttr("jobName"),
					context.getStringAttr("jobGroup")));
		} catch (SchedulerException e) {
			throw new QuartzException("获取任作业名：{"+context.getStringAttr("jobName")+"} 分组名：{"+context.getStringAttr("jobGroup")+"}的任务失败", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.soa.quartz.core.QuartzManger#rescheduleJob(org.soa.common.context.SoaContext)
	 */
	@Override
	public void rescheduleJob(SoaContext context) {
		try {
			scheduler.rescheduleJob(TriggerKey.triggerKey(
					context.getStringAttr("triggerName"),
					context.getStringAttr("triggerGroup")), this
					.buildTrigger(context));
		} catch (SchedulerException e) {
			throw new QuartzException("调用方法rescheduleJob 进行重置触发器失败");
		}
	}

	@Override
	public void pauseAll() {
		try {
			this.scheduler.pauseAll();
		} catch (SchedulerException e) {
			throw new QuartzException("调用方法pauseAll 进行所有任务的暂停失败");
		}
	}

	@Override
	public void resumeAll() {
		try {
			this.scheduler.resumeAll();
		} catch (SchedulerException e) {
			throw new QuartzException("调用方法resumeAll 进行所有任务的激活失败");
		}
	}

	@Override
	public void start() {
		try {
			this.scheduler.start();
			SoaLogger.debug(getClass(), "Quartz job srart success!~!");
		} catch (SchedulerException e) {
			SoaLogger.error(getClass(), "Quartz job srart error{}",e);
		}
	}
}
