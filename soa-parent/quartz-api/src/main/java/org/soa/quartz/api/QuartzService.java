package org.soa.quartz.api;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.soa.common.context.SoaContext;

/**
 * 
 * @ClassName: IQuartzService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYi
 * @date 2014年7月9日 下午6:17:22
 * 
 */
public interface QuartzService {
	
	
	
		public SoaContext addJob(SoaContext context);
	
        /**
         * 
         * @Description: TODO(更新任务)
         * @author LiuYi
         * @date 2014年7月9日 下午3:03:02
         * @param scheduler
         * @param task
         * @
         *                 SoaContext
         */
        public abstract SoaContext modifyJob(SoaContext context) ;

        /**
         * 
         * @Description: TODO(删除任务)
         * @author LiuYi
         * @date 2014年7月9日 下午3:03:20
         * @param scheduler
         * @param task
         * @
         *                 SoaContext
         */
        public abstract SoaContext deleteJob(SoaContext context) ;

        /**
         * 
         * @Description: TODO(暂停任务)
         * @author LiuYi
         * @date 2014年7月9日 下午3:03:33
         * @param scheduler
         * @param task
         * @
         *                 SoaContext
         */
        public abstract SoaContext pauseJob(SoaContext context) ;

        /**
         * 
         * @Description: TODO(重新激活任务)
         * @author LiuYi
         * @date 2014年7月9日 下午3:03:47
         * @param scheduler
         * @param task
         * @
         *                 SoaContext
         */
        public abstract SoaContext resumeJob(SoaContext context) ;

        /**
         * 
         * @Description: TODO(获取触发器)
         * @author LiuYi
         * @date 2014年7月9日 下午3:08:35
         * @param scheduler
         * @param task
         * @return Trigger
         * @
         */
        public abstract Trigger getTrigger(SoaContext context) ;
        public abstract Trigger getTriggerById(SoaContext context) ;

        /**
         * 
         * @Description: TODO(获取调度任务)
         * @author LiuYi
         * @date 2014年7月9日 下午3:12:19
         * @param scheduler
         * @param task
         * @return
         * @
         *                 JobDetail
         */
        public abstract JobDetail getJobDetail(SoaContext context) ;

        public abstract JobDetail getJobDetailById(SoaContext context) ;

        /**
         * 
         * @Description: TODO(通过ID查询任务)
         * @author LiuYi
         * @date 2014年7月9日 下午6:19:34
         * @param id
         * @return
         * @
         *                 QuartzTask<?>
         */
        public abstract SoaContext get(SoaContext context) ;

        public abstract SoaContext page(SoaContext context) ;
        /**
         * 
        * @Description: TODO(暂停所有任务)
        * @author LiuYi
        * @date 2014年7月10日 上午9:45:30
        *  @  SoaContext
         */
        public abstract SoaContext pauseAll(SoaContext context);
        /**
         * 
        * @Description: TODO(激活所有任务)
        * @author LiuYi
        * @date 2014年7月10日 上午9:46:01 
        *  @  SoaContext
         */
        public abstract SoaContext resumeAll(SoaContext context);

		public SoaContext query(SoaContext context);
		
		public SoaContext start(SoaContext context);

}