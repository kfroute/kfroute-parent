package cn.melinkr.kfrouteWeb.core.ibatis;


import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.result.AutoResultMap;
import com.ibatis.sqlmap.engine.mapping.result.ResultMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.ExecuteListener;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.mapping.statement.SelectStatement;
import com.ibatis.sqlmap.engine.scope.ErrorContext;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class CountStatement extends SelectStatement
{
  private static final Log logger = LogFactory.getLog(CountStatement.class);

  public CountStatement()
  {
  }

  public CountStatement(SelectStatement selectStatement) {
    setId(CountStatementUtil.getCountStatementId(selectStatement.getId()));
    setResultSetType(selectStatement.getResultSetType());
    setFetchSize(Integer.valueOf(1));
    setParameterMap(selectStatement.getParameterMap());
    setParameterClass(selectStatement.getParameterClass());
    setSql(selectStatement.getSql());
    setSqlMapClient(selectStatement.getSqlMapClient());
    setTimeout(selectStatement.getTimeout());
    setResource(selectStatement.getResource());

    List executeListeners = (List)getFieldValue(
      selectStatement, "executeListeners");
    if (executeListeners != null) {
      for (Iterator localIterator = executeListeners.iterator(); localIterator.hasNext(); ) { Object listener = localIterator.next();
        addExecuteListener((ExecuteListener)listener);
      }
    }

    ResultMap resultMap = new AutoResultMap(
      ((ExtendedSqlMapClient)getSqlMapClient()).getDelegate(), false);
    resultMap.setId(getId() + "-AutoResultMap");
    resultMap.setResultClass(Long.class);
    resultMap.setResource(getResource());
    setResultMap(resultMap);
  }

  protected void executeQueryWithCallback(StatementScope request, Connection conn, Object parameterObject, Object resultObject, RowHandler rowHandler, int skipResults, int maxResults)
    throws SQLException
  {
    ErrorContext errorContext = request.getErrorContext();
    errorContext
      .setActivity("preparing the mapped statement for execution");
    errorContext.setObjectId(getId());
    errorContext.setResource(getResource());
    try
    {
      parameterObject = validateParameter(parameterObject);

      Sql sql = getSql();

      errorContext.setMoreInfo("Check the parameter map.");
      ParameterMap parameterMap = sql.getParameterMap(request, 
        parameterObject);

      errorContext.setMoreInfo("Check the result map.");
      ResultMap resultMap = getResultMap(request, parameterObject, sql);

      request.setResultMap(resultMap);
      request.setParameterMap(parameterMap);

      errorContext.setMoreInfo("Check the parameter map.");
      Object[] parameters = parameterMap.getParameterObjectValues(
        request, parameterObject);

      errorContext.setMoreInfo("Check the SQL statement.");
      String sqlString = getSqlString(request, parameterObject, sql);

      errorContext.setActivity("executing mapped statement");
      errorContext
        .setMoreInfo("Check the SQL statement or the result map.");
      RowHandlerCallback callback = new RowHandlerCallback(resultMap, 
        resultObject, rowHandler);
      sqlExecuteQuery(request, conn, sqlString, parameters, skipResults, 
        maxResults, callback);

      errorContext.setMoreInfo("Check the output parameters.");
      if (parameterObject != null) {
        postProcessParameterObject(request, parameterObject, parameters);
      }

      errorContext.reset();
      sql.cleanup(request);
      notifyListeners();
    } catch (SQLException e) {
      errorContext.setCause(e);
      throw new NestedSQLException(errorContext.toString(), 
        e.getSQLState(), e.getErrorCode(), e);
    } catch (Exception e) {
      errorContext.setCause(e);
      throw new NestedSQLException(errorContext.toString(), e);
    }
  }

  private String getSqlString(StatementScope request, Object parameterObject, Sql sql)
  {
    String sqlString = sql.getSql(request, parameterObject);

    sqlString = getChangeCountSqlString(sqlString);

    if (logger.isDebugEnabled()) {
      logger.debug("dynamic SQL ：" + request.getDynamicSql());
      logger.debug("convert SQL ：" + sqlString);
    }

    return sqlString;
  }

  private ResultMap getResultMap(StatementScope request, Object parameterObject, Sql sql)
  {
    return getResultMap();
  }

  private String getChangeCountSqlString(String sql) {
    StringBuffer sb = new StringBuffer();

    sb.append("select count(*) from ( ")
      .append(sql)
      .append(" ) a");

    return sb.toString();
  }

  private Object getFieldValue(Object target, String fname)
  {
    Object reslut = null;
    if ((target == null) || (fname == null) || ("".equals(fname))) {
      return null;
    }
    Class clazz = target.getClass();
    try {
      Field field = clazz.getDeclaredField(fname);
      reslut = field.get(fname);
    }
    catch (Exception me) {
      if (logger.isDebugEnabled()) {
        logger.debug(me);
      }
    }
    return reslut;
  }
}