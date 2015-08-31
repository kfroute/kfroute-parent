package cn.melinkr.kfrouteWeb.core.ibatis;

import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.SelectStatement;

public class CountStatementUtil
{
  public static MappedStatement createCountStatement(MappedStatement selectStatement)
  {
    return new CountStatement((SelectStatement)selectStatement);
  }

  public static String getCountStatementId(String selectStatementId) {
    return "__" + selectStatementId + "Count__";
  }
}