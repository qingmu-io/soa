package org.soa.core.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.soa.core.dao.Dao;
import org.soa.logger.SoaLogger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service("daoMyBatis")
public class DaoMyBatisImpl implements Dao {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Inject
	@Named("sqlSessionTemplate")
	private SqlSessionTemplate dao;
	
	public List<Map<String, Object>> query(String namespace,String statement) {
		long bengin = System.currentTimeMillis();
		List<Map<String, Object>> dataList = dao.<Map<String,Object>>selectList(changeStatement(namespace,statement));
	SoaLogger.debug(DaoMyBatisImpl.class,"[{}.{}] execute, cost:{}ms",namespace,statement,(System.currentTimeMillis() - bengin));
		return dataList;
	}

	public List<Map<String, Object>> query(String namespace,String statement,
			Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		List<Map<String, Object>> dataList = dao.<Map<String,Object>>selectList(changeStatement(namespace,statement),paramData);
		SoaLogger.debug(DaoMyBatisImpl.class,"[{}.{}] execute, cost:{}ms",namespace,statement,(System.currentTimeMillis() - bengin));
		return dataList;
	}

	public List<Map<String, Object>> query(String namespace,String statement,
			Map<String, Object> paramData, int limit, int offset) {
		long bengin = System.currentTimeMillis();
		RowBounds rowBounds = new RowBounds((offset-1)*limit, limit);
		List<Map<String, Object>> dataList = dao.<Map<String,Object>>selectList(changeStatement(namespace,statement),paramData,rowBounds);
		SoaLogger.debug(DaoMyBatisImpl.class,"[{}.{}] execute, cost:{}ms",namespace,statement,(System.currentTimeMillis() - bengin));
		return dataList;
	}
	
	


	public int count(String namespace,String statement) {
		return dao.<Integer>selectOne(changeStatement(namespace,statement));
	}

	
	public int count(String namespace,String statement, Map<String, Object> paramData) {
		return dao.<Integer>selectOne(changeStatement(namespace,statement),paramData);
	}


	public Map<String, Object> get(String namespace,String statement,
			Map<String, Object> paramData) {
		Map<String, Object> dataMap =  dao.<Map<String, Object>>selectOne(changeStatement(namespace,statement),paramData);
		return dataMap;
	}


	public Map<String, Object> load(String namespace, String key,
			String value) {
		Map<String,String> param = new HashMap<String,String>();
		param.put(key, value);
		Map<String, Object> dataMap = dao.<Map<String, Object>>selectOne(changeStatement(namespace,"load"),param);
		return dataMap;
	}

	
	public void insert(String namespace,String statement, Map<String, Object> paramData) {
		dao.insert(changeStatement(namespace,statement), paramData);
	}

	
	public int update(String namespace,String statement, Map<String, Object> paramData) {
		return dao.update(changeStatement(namespace,statement), paramData);
	}

	
	public int delete(String namespace,String statement, Map<String, Object> paramData) {
		return dao.delete(changeStatement(namespace,statement), paramData);
	}
	
	private String changeStatement(String namespace,String statement){
		return namespace + "." + statement;
	}

	protected void changeDateType(List<Map<String,Object>> dataList){
		if(dataList == null || dataList.size() == 0){
			return;
		}
		
		for(Map<String,Object> map : dataList){
			for(Map.Entry<String, Object> entry : map.entrySet()){
				if(entry.getValue().getClass() == Timestamp.class){
					map.put(entry.getKey(), sdf.format((Timestamp)entry.getValue()));
				}
			}
		}
		
	}
	
	protected void changeDateType(Map<String,Object> dataMap){
		if(dataMap == null || dataMap.size() == 0){
			return;
		}
		for(Map.Entry<String, Object> entry : dataMap.entrySet()){
			if(entry.getValue().getClass() == Timestamp.class){
				dataMap.put(entry.getKey(),  sdf.format((Timestamp)entry.getValue()));
			}
		}
			
	}

	public List<Map<String, Object>> query(String namespace, String statement,
			int limit, int offset) {
		RowBounds rowBounds = new RowBounds((offset-1)*limit, limit);
		List<Map<String, Object>> dataList = dao.<Map<String,Object>>selectList(changeStatement(namespace,statement),null,rowBounds);
		return dataList;
	}


	public String getSql(String namespace, String statement, String ds,
			Map<String, Object> paramData) {
		MappedStatement ms = dao.getConfiguration().getMappedStatement(namespace+"."+statement);
		BoundSql boundSql = ms.getBoundSql(paramData);
		return boundSql.getSql();
	}
	@Override
	public String getSql(String namespace, String statement,
			Map<String, Object> paramData) {
		return null;
	}

}
