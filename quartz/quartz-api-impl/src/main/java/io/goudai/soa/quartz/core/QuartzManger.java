package io.goudai.soa.quartz.core;

import io.goudai.soa.common.context.SoaContext;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;

public interface QuartzManger {

    void addJob(SoaContext context);

    /**
     * @Description: 构建触发器
     */
    CronTrigger buildTrigger(SoaContext context);

    /**
     * @Description: 构建任务
     */
    JobDetail buildJob(SoaContext context);

    /**
     * @Description: 更新任务
     */
    void modifyJob(SoaContext context);

    /**
     * @Description: 删除任务
     */
    void deleteJob(SoaContext context);

    /**
     * @Description: 暂停任务
     */
    void pauseJob(SoaContext context);

    /**
     * @Description: 重新激活任务
     */
    void resumeJob(SoaContext context);

    /**
     * @Description: 获取触发器
     */
    Trigger getTrigger(SoaContext context);

    /**
     * @Description: 获取调度任务
     */
    JobDetail getJobDetail(SoaContext context);

    /**
     * TODO (重置触发器)
     *
     * @param context
     */
    void rescheduleJob(SoaContext context);

    void pauseAll();

    void resumeAll();

    public void start();

}