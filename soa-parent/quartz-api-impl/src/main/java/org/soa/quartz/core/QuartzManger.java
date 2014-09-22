package org.soa.quartz.core;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.soa.common.context.SoaContext;

public interface QuartzManger {

	public abstract void addJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(构建触发器)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:05:05
	 * @param context
	 * @return CronTrigger
	 */
	public abstract CronTrigger buildTrigger(SoaContext context);

	/**
	 * 
	 * @Description: TODO(构建任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:04:51
	 * @param context
	 * @return JobDetail
	 */
	public abstract JobDetail buildJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(更新任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:03:02
	 * @param scheduler
	 * @param context
	 *            @ void
	 */
	public abstract void modifyJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(删除任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:03:20
	 * @param scheduler
	 * @param context
	 *            @ void
	 */
	public abstract void deleteJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(暂停任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:03:33
	 * @param scheduler
	 * @param context
	 *            @ void
	 */
	public abstract void pauseJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(重新激活任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:03:47
	 * @param scheduler
	 * @param context
	 *            @ void
	 */
	public abstract void resumeJob(SoaContext context);

	/**
	 * 
	 * @Description: TODO(获取触发器)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:08:35
	 * @param scheduler
	 * @param context
	 * @return Trigger @
	 */
	public abstract Trigger getTrigger(SoaContext context);

	/**
	 * 
	 * @Description: TODO(获取调度任务)
	 * @author LiuYi
	 * @date 2014年7月9日 下午3:12:19
	 * @param scheduler
	 * @param context
	 * @return @ JobDetail
	 */
	public abstract JobDetail getJobDetail(SoaContext context);

	/**
	 * TODO (重置触发器)
	 * 
	 * @param context
	 */
	public abstract void rescheduleJob(SoaContext context);
	
	public abstract void pauseAll();

	public abstract void resumeAll();
	public void start();

}