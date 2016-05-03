drop table t_sys_user;
create table t_sys_user(
	GUID char(32) not null,
	LGOGIN_ID char(32) not null,
	USERNAME varchar(200),
	PASSWORD char(32),
	SEX bigint default '1', -- 1 男 -1 女
	EMAIL varchar(200),
	CREATE_TIME DATETIME,
	IS_ENABLE bigint default '1', --1启用 -1 禁用
);

create table t_sys_role(
	GUID char(32) not null,
	NAME varchar(200),
	CREATE_TIME DATETIME,
	CRATE_USER varchar(50)
);
