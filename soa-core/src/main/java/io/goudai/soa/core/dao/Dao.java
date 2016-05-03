package io.goudai.soa.core.dao;

import java.util.List;
import java.util.Map;

public interface Dao {
	
	  public static final String MYBATIS_DAO = "daoMybatis";
	  
	  public abstract List<Map<String,Object>> query(String namespace,String statement);
	  
	  public abstract List<Map<String,Object>> query(String namespace,String statement,int limit, int offset);

	  public abstract List<Map<String,Object>> query(String namespace,String statement, Map<String,Object> paramData);

	  public abstract List<Map<String,Object>> query(String namespace,String statement,Map<String,Object> paramData, int limit, int offset);

	  public abstract int count(String namespace,String statement);

	  public abstract int count(String namespace,String statement, Map<String,Object> paramData);

	  public abstract Map<String,Object> get(String namespace,String statement, Map<String,Object> paramData);

	  public abstract Map<String,Object> load(String namespace, String key, String value);

	  public abstract void insert(String namespace,String statement, Map<String,Object> paramData);

	  public abstract int update(String namespace,String statement, Map<String,Object> paramData);

	  public abstract int delete(String namespace,String statement, Map<String,Object> paramData);
	  
	  public abstract String getSql(String namespace,String statement, Map<String,Object> paramData);


}
