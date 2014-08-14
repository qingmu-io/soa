package org.soa.common.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统环境类
 * 
 * @author liuyi
 */
public class SoaContext implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 服务名称
	 */
	private String service;
	/**
	 * 方法名称
	 */
	private String method;
	/**
	 * 服务返回消息
	 */
	private String msg;
	/**
	 * 排序
	 */
	private String orderBy;
	/**
	 * 偏移量
	 */
	private int page = 1;
	/**
	 * 每页显示记录条数
	 */
	private int limit = 10;
	/**
	 * 总共多少条
	 */
	private int total = 0;

	/**
	 * 服务状态
	 */
	private boolean success = true;
	/**
	 * 属性
	 */
	private Map<String, Object> attr = new HashMap<String, Object>();
	/**
	 * 返回数据
	 */
	private List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public void addAttr(String key, 	Object value) {
		this.attr.put(key, value);
	}

	public Object getAttr(String key) {
		return this.attr.get(key);
	}

	public void removeAttr(String key) {
		this.attr.remove(key);
	}
	public String getStringAttr(String key){
		return (String) this.rows.get(0).get(key);
	}

}
