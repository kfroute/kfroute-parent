package cn.melinkr.kfrouteWeb.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import cn.melinkr.kfrouteWeb.core.ibatis.CountStatementUtil;
import cn.melinkr.kfrouteWeb.exception.AppException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;


@Repository("baseDao")
public class BaseDao extends SqlMapClientDaoSupport {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "dataSource")
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	public Object insert(String statementName) {
		return insert(statementName, null);
	}

	public Object insert(String statementName, Object parameterObject) {
		Throwable c;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			return getSqlMapClientTemplate().insert(statementName,
					parameterObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public int update(String statementName) {
		return update(statementName, null);
	}

	public int update(String statementName, Object parameterObject) {
		Throwable c;
		int affectRows = 0;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			affectRows = getSqlMapClientTemplate().update(statementName,
					parameterObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
		return affectRows;
	}

	public int delete(String statementName) {
		return delete(statementName, null);
	}

	public int delete(String statementName, Object parameterObject) {
		Throwable c;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			return getSqlMapClientTemplate().delete(statementName,
					parameterObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public Object queryForObject(String statementName) {
		Throwable c;
		try {
			return getSqlMapClientTemplate()
					.queryForObject(statementName, null);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public Object queryForObject(String statementName, Object parameterObject) {
		Throwable c;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			return getSqlMapClientTemplate().queryForObject(statementName,
					parameterObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public Object queryForObject(String statementName, Object parameterObject,
			Object resultObject) {
		Throwable c;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			return getSqlMapClientTemplate().queryForObject(statementName,
					parameterObject, resultObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public List queryForList(String statementName) {
		return queryForList(statementName, null);
	}

	public List queryForList(String statementName, Object parameterObject) {
		Throwable c = null;
		try {
			if ((log.isDebugEnabled()) && (parameterObject != null)) {
				log.debug(parameterObject.toString());
			}

			return getSqlMapClientTemplate().queryForList(statementName,
					parameterObject);
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public Map queryForPagingList(String _statementName, int _offsize,
			int _limit) {
		return queryForPagingList(_statementName, null, _offsize, _limit);
	}

	public Map queryForPagingList(String _statementName,
			Object _parameterObject, int _offsize, int _limit) {
		Throwable c;
		try {
			if ((log.isDebugEnabled()) && (_parameterObject != null)) {
				log.debug(_parameterObject.toString());
			}

			int sum = (int) getObjectTotal(_statementName, _parameterObject);

			List result = getSqlMapClientTemplate().queryForList(
					_statementName, _parameterObject, _offsize, _limit);
			Map map = new HashMap();
			map.put("total", Integer.valueOf(sum));
			map.put("rows", result);
			return map;
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public Object batchUpdate(final String statementName, final List list) {
		Throwable c = null;
		Object result = null;
		try {
			result = getSqlMapClientTemplate().execute(
					new SqlMapClientCallback() {
						public Object doInSqlMapClient(SqlMapExecutor executor)
								throws SQLException {
							if (list == null)
								throw new SQLException(
										"batchUpdate  parameter list is null !");
							int count = 0;
							executor.startBatch();
							int size = list.size();
							for (int i = 0; i < size; ++i)
								count += executor.update(statementName,
										list.get(i));
							executor.executeBatch();
							return new Integer(count);
						}
					});
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}

		return result;
	}

	public Object batchInsert(final String statementName, final List list) {
		Throwable c;
		try {
			return getSqlMapClientTemplate().execute(
					new SqlMapClientCallback() {
						public Object doInSqlMapClient(SqlMapExecutor executor)
								throws SQLException {
							if (list == null)
								throw new SQLException(
										"batchInsert  parameter list is null !");
							executor.startBatch();
							int size = list.size();
							for (int i = 0; i < size; ++i)
								executor.insert(statementName, list.get(i));
							executor.executeBatch();
							return new Integer(size);
						}
					});
		} catch (DataAccessException dae) {
			c = dae.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(c.getMessage(), c);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", dae);
		} catch (Throwable e) {
			if (e instanceof SQLException) {
				throw new AppException(e.getMessage(), e);
			}
			c = e.getCause();
			while (c != null) {
				if (c instanceof SQLException) {
					throw new AppException(e.getMessage(), e);
				}
				c = c.getCause();
			}
			throw new AppException("未知错误!", e);
		}
	}

	public long getObjectTotal(String selectQuery, Object parameterObject) {
		prepareCountQuery(selectQuery);
		return ((Long) getSqlMapClientTemplate().queryForObject(
				CountStatementUtil.getCountStatementId(selectQuery),
				parameterObject)).longValue();
	}

	public long getObjectTotal(String selectQuery) {
		prepareCountQuery(selectQuery);
		return ((Long) getSqlMapClientTemplate().queryForObject(
				CountStatementUtil.getCountStatementId(selectQuery)))
				.longValue();
	}

	protected void prepareCountQuery(String selectQuery) {
		String countQuery = CountStatementUtil.getCountStatementId(selectQuery);
		if (this.log.isDebugEnabled()) {
			this.log.debug("Convert " + selectQuery + " to " + countQuery);
		}
		SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		if (sqlMapClient instanceof ExtendedSqlMapClient) {
			SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient) sqlMapClient)
					.getDelegate();
			try {
				delegate.getMappedStatement(countQuery);
			} catch (SqlMapException e) {
				delegate.addMappedStatement(CountStatementUtil
						.createCountStatement(delegate
								.getMappedStatement(selectQuery)));
			}
		}
	}

}
