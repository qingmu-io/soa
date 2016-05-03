package io.goudai.soa.core.service;

import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.exception.AppException;
import io.goudai.soa.core.dao.Dao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseService {
	private static final String QUERY = "query";
	private static final String COUNT = "count";
	private static final String UPDATE = "update";
	private static final String DELETE = "delete";
	private static final String INSERT = "insert";

	@Inject
	@Named("daoMyBatis")
	private Dao dao;

	public SoaContext query(SoaContext context, String namespace) {
		try {
			if (context.getAttr().size() == 0) {
				context.setRows(dao.query(namespace, QUERY));
			} else {
				context.setRows(dao.query(namespace, QUERY, context.getAttr()));
			}
			context.setMsg("查询到" + context.getRows().size() + "条记录!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("查询失败,系统异常!");
		}
		return context;
	}

	public SoaContext query(SoaContext context) {
		return query(context, getNameSpace());
	}

	public SoaContext queryByPage(SoaContext context, String namespace) {
		try {
			if (context.getAttr().size() == 0) {
				context.setRows(dao.query(namespace, QUERY, context.getLimit(),context.getPage()));
				context.setTotal(dao.count(namespace, COUNT));
			} else {
				context.setRows(dao.query(namespace, QUERY, context.getAttr(),
						context.getLimit(), context.getPage()));
				context.setTotal(dao.count(namespace, COUNT, context.getAttr()));
			}
			context.setMsg("查询到" + context.getRows().size() + "条记录!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("查询失败,系统异常!");
		}
		return context;
	}

	public SoaContext queryByPage(SoaContext context) {
		return queryByPage(context, getNameSpace()).paginationTool();
	}

	public SoaContext query(SoaContext context, String namespace,
			String statement) {
		try {
			if (statement == null || statement.length() == 0) {
				statement = QUERY;
			}
			context.setRows(dao.query(namespace, statement, context.getAttr()));
			context.setMsg("查询到" + context.getRows().size() + "条记录!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("查询失败,系统异常!");
		}
		return context;
	}
	
	

	public SoaContext queryStatement(SoaContext context, String statement) {
		return query(context, getNameSpace(), statement);
	}

	public SoaContext queryByPage(SoaContext context, String namespace,
			String queryStatement, String countStatement) {
		try {
			if (queryStatement == null || queryStatement.length() == 0) {
				queryStatement = QUERY;
			}
			if (countStatement == null || countStatement.length() == 0) {
				countStatement = COUNT;
			}
			context.setRows(dao.query(namespace, queryStatement,
					context.getAttr(), context.getLimit(), context.getPage()));
			context.setTotal(dao.count(namespace, countStatement,
					context.getAttr()));
			context.setMsg("查询到" + context.getRows().size() + "条记录!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("查询失败,系统异常!");
		}
		return context;
	}

	public int count(SoaContext context, String countStatement) {
		return dao.count(getNameSpace(), countStatement, context.getAttr());
	}

	public SoaContext queryByPage(SoaContext context, String queryStatement,
			String countStatement) {
		return this.queryByPage(context, getNameSpace(), queryStatement,
				countStatement);
	}

	public SoaContext queryCombox(SoaContext context, String namespace,
			String queryStatement, String countStatement) {
		try {
			if (queryStatement == null || queryStatement.length() == 0) {
				queryStatement = QUERY;
			}
			if (countStatement == null || countStatement.length() == 0) {
				countStatement = COUNT;
			}
			String value = (String) context.getAttr("q");
			if (value != null && !value.trim().isEmpty()) {
				if (isNumeric(value)) {
					context.addAttr("id", value);
				} else {
					context.addAttr("name", value);
				}
			}
			context = queryByPage(context, namespace, queryStatement,
					countStatement);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("查询失败,系统异常!");
		}
		return context;
	}

	public SoaContext insert(SoaContext context, String namespace) {
		try {
			dao.insert(namespace, INSERT, context.getAttr());
			context.setMsg("新增成功!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			context.setSuccess(false);
			throw new AppException("新增失败,系统异常!");
		}
		return context;
	}

	public SoaContext insert(SoaContext context) {
		return this.insert(context, getNameSpace());
	}

	public SoaContext update(SoaContext context, String namespace) {
		try {
			int count = dao.update(namespace, UPDATE, context.getAttr());
			context.setMsg("成功修改" + count + "条记录!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			context.setSuccess(false);
			throw new AppException("修改失败,系统异常!");
		}
		return context;
	}
	

	
	public SoaContext update(SoaContext context) {
		return this.update(context, getNameSpace());
	}

	public SoaContext delete(SoaContext context, String namespace) {
		try {
			for (Map<String, Object> map : context.getRows()) {
				dao.delete(namespace, DELETE, map);
			}
			context.setMsg("成功删除" + context.getRows().size() + "条记录!");
			context.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			context.setSuccess(false);
			throw new AppException("删除失败,系统异常!");
		}
		return context;
	}

	public SoaContext delete(SoaContext context) {
		return delete(context, getNameSpace());
	}

	public abstract String getNameSpace();

	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

}
