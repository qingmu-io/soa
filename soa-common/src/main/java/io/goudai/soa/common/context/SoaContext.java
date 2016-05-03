package io.goudai.soa.common.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统环境类
 * 
 * @author freeman
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
	/**总页数*/
	private int totalPage;
	/**最多显示页码*/
	private final Integer SHOWPAGE = 6;
	/**
	 * 页面中的起始页
	 */
  	private Integer startpage;
  	/**
  	 * 页面中的结束页
  	 */
  private Integer endpage;
	
	  public SoaContext paginationTool() {
          /* 计算总页数 */
          this.totalPage = this.total % this.limit == 0 ? this.total / limit : this.total
                          / limit + 1;
          /* 计算startpage与endpage的值 */
          // 如果当前页数小于最大显示数，则全部显示，当前页从0开始
          if (this.totalPage < this.SHOWPAGE) {
                  this.startpage = 1;
                  this.endpage = this.totalPage;
          } else {
                  /** else中是总页数大于SHOWPAGES的情况 */
                  if (this.totalPage <= SHOWPAGE / 2 + 1) {
                          this.startpage = 1;
                          this.endpage = this.SHOWPAGE;
                  } else {
                          this.startpage = this.page - (SHOWPAGE / 2);
                          this.endpage = this.page + (SHOWPAGE / 2 - 1);
                          if (this.endpage >= this.totalPage) {
                                  this.endpage = this.totalPage;
                                  this.startpage = this.totalPage - SHOWPAGE + 1;
                          }
                  }
          }
          return this;
  }
	

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
		return (String) this.attr.get(key);
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public Integer getStartpage() {
		return startpage;
	}


	public void setStartpage(Integer startpage) {
		this.startpage = startpage;
	}


	public Integer getEndpage() {
		return endpage;
	}


	public void setEndpage(Integer endpage) {
		this.endpage = endpage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getSHOWPAGE() {
		return SHOWPAGE;
	}
	
	public static SoaContext newSoaContext(String service,String method){
		SoaContext context = new SoaContext();
		context.setService(service);
		context.setMethod(method);
		return context;
	}

}
