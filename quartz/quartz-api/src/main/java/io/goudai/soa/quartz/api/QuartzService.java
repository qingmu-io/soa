package io.goudai.soa.quartz.api;

import io.goudai.soa.common.context.SoaContext;
import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @ClassName: QuartzService
 */
public interface QuartzService {


    SoaContext addJob(SoaContext context);

    /**
     * @Description: 更新任务
     * @ SoaContext
     */
    SoaContext modifyJob(SoaContext context);

    /**
     * @Description: 删除任务
     * @ SoaContext
     */
    SoaContext deleteJob(SoaContext context);

    /**
     * @Description: 暂停任务
     * @ SoaContext
     */
    SoaContext pauseJob(SoaContext context);

    /**
     * @Description: 重新激活任务
     * <p>
     * SoaContext
     */
    SoaContext resumeJob(SoaContext context);

    /**
     * @return Trigger
     * @Description: 获取触发器
     * @
     */
    Trigger getTrigger(SoaContext context);

    Trigger getTriggerById(SoaContext context);

    /**
     * @return
     * @Description: 获取调度任务
     * @ JobDetail
     */
    JobDetail getJobDetail(SoaContext context);

    JobDetail getJobDetailById(SoaContext context);

    /**
     * @return
     * @Description: 通过ID查询任务
     * @ QuartzTask<?>
     */
    SoaContext get(SoaContext context);

    SoaContext page(SoaContext context);

    /**
     * @Description: 暂停所有任务
     * @ SoaContext
     */
    SoaContext pauseAll(SoaContext context);

    /**
     * @Description: 激活所有任务
     * @ SoaContext
     */
    SoaContext resumeAll(SoaContext context);

    public SoaContext query(SoaContext context);

    public SoaContext start(SoaContext context);

}