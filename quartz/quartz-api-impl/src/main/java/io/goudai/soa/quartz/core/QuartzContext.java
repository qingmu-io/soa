package io.goudai.soa.quartz.core;

import org.quartz.Scheduler;

import java.io.Serializable;

/**
 * 
 * @ClassName: QuartzTask
 * @Description: Quartz任务调度信息封装类
 * @author freeman
 */
public class QuartzContext  implements Serializable {
        private static final long serialVersionUID = 1L;
        private String id;
        /**
         * job任务名称
         */
        private String jobName;
        /**
         * job任务分组
         */
        private String jobGroup;
        /**
         * 执行的任务类模板
         */
        private String clazz;
        /**
         * 执行的时间表达式
         */
        private String cronExpression;
        /**
         * 触发器名称
         */
        private String triggerName;
        /**
         * 触发器分组
         */
        private String triggerGroup;

        /**
         * 任务的状态 1 表示运行 0 表示暂停
         */
        private String status;
        public QuartzContext(String id, String jobGroup, String jobName, String clazz,
                        String cronExpression, String triggerName, String triggerGroup) {
                super();
                this.id = id;
                this.jobGroup = jobGroup;
                this.jobName = jobName;
                this.clazz = clazz;
                this.cronExpression = cronExpression;
                this.triggerName = triggerName;
                this.triggerGroup = triggerGroup;
        }

        public QuartzContext() {
        }

        public QuartzContext(String jobName, String clazz, String cronExpression, String triggerName) {
                super();
                this.jobName = jobName;
                this.jobGroup = Scheduler.DEFAULT_GROUP;
                this.clazz = clazz;
                this.cronExpression = cronExpression;
                this.triggerName = triggerName;
                this.triggerGroup = Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_GROUP;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getJobGroup() {
                return jobGroup;
        }

        public void setJobGroup(String jobGroup) {
                this.jobGroup = jobGroup;
        }

        public String getJobName() {
                return jobName;
        }

        public void setJobName(String jobName) {
                this.jobName = jobName;
        }

        public String getClazz() {
                return clazz;
        }
        public void setClazz(String clazz) {
                this.clazz = clazz;
        }


        public String getCronExpression() {
                return cronExpression;
        }

        public void setCronExpression(String cronExpression) {
                this.cronExpression = cronExpression;
        }

        public String getTriggerName() {
                return triggerName;
        }

        public void setTriggerName(String triggerName) {
                this.triggerName = triggerName;
        }

        public String getTriggerGroup() {
                return triggerGroup;
        }

        public void setTriggerGroup(String triggerGroup) {
                this.triggerGroup = triggerGroup;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
        
        
}
