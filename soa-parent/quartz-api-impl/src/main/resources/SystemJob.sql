create database job;
use job;
CREATE TABLE t_sys_Job(
	 ID int auto_increment primary key not null 
	,JOB_NAME VARCHAR(50)
	,JOB_GROUP VARCHAR(50)
	,CLAZZ VARCHAR(50)
	,CRON_EXPRESSION VARCHAR(50)
	,TRIGGER_NAME VARCHAR(50)
	,TRIGGER_GROUP VARCHAR(50)
	,STATUS BIGINT
	,job_src mediumText
	,create_time DATETIME
	,create_author VARCHAR(50)
)CHARSET=utf8;
