package io.goudai.soa.quartz.core;

import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.exception.AppException;
import io.goudai.soa.common.exception.QuartzException;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.goudai.soa.quartz.core.util.ClazzUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author freeman
 * @ClassName: SimpleQuzrtzManger
 * @Description: TODO(作业调度管理者)
 */
@Service("quartzManger")
public class SimpleQuartzManger implements QuartzManger {

    Logger logger = LoggerFactory.getLogger(SimpleQuartzManger.class);

    @Resource
    private Scheduler scheduler;


    /* (non-Javadoc)
     * @see org.soa.quartz.core.QuartzManger#addJob(org.soa.common.context.SoaContext)
     */
    @Override
    public void addJob(SoaContext context) {
        try {
            final JobKey jobKey = JobKey.jobKey(context.getStringAttr("jobName"));
            final TriggerKey triggerKey = TriggerKey.triggerKey(context.getStringAttr("triggerName"));
            if (
                    this.scheduler.checkExists(jobKey)
                            || this.scheduler.checkExists(triggerKey)) {
                //表示该任务已经被添加进了quartz维护的数据表中
            } else
                scheduler.scheduleJob(buildJob(context), buildTrigger(context));
        } catch (SchedulerException e) {
            throw new AppException(e.getMessage(), e);
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
            throw new QuartzException("暂停作业" + context.getStringAttr("triggerName") + "失败", e);
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
            throw new QuartzException("移除作业" + context.getStringAttr("triggerName") + "失败", e);
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
            throw new QuartzException("暂停作业" + context.getStringAttr("triggerName") + "失败", e);
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
            throw new QuartzException("获取任作业名：{" + context.getStringAttr("jobName") + "} 分组名：{" + context.getStringAttr("jobGroup") + "}的任务失败", e);
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
            logger.info("Quartz job srart success");
        } catch (SchedulerException e) {
            throw new QuartzException("Quartz job srart error{}", e);
        }
    }
}
