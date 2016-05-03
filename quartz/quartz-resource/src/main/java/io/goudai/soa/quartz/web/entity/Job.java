package io.goudai.soa.quartz.web.entity;

import java.sql.Timestamp;

/**
 * @author liuyi
 *
 */

public class Job {
	
	private int id;
	private String jobName;
	private String jobGroup;
	private String clazz;
	private String cronExpression;
	private String triggerName;
	private String triggerGroup;
	/**
	 * 1 表示运行中 -1暂停中
	 */
	private int status;
	private Timestamp createTime;
	private String crateAuthor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCrateAuthor() {
		return crateAuthor;
	}
	public void setCrateAuthor(String crateAuthor) {
		this.crateAuthor = crateAuthor;
	}
	
	
	
	
}
