package cn.melinkr.kfrouteWeb.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseException extends RuntimeException
{
  private static final long serialVersionUID = 6660311097777385849L;
  protected StringBuffer backStacks = new StringBuffer();


  public BaseException()
  {
  }

  public BaseException(String paramString)
  {
    super(paramString);
    
  }

  public BaseException(Throwable paramThrowable)
  {
    super(paramThrowable);
    this.backStacks.append(getStackTrace(paramThrowable));
  }

  public BaseException(String paramString1, String paramString2)
  {
    super("errCode:" + paramString1 + ",errMsg:" + paramString2);
    
  }

  public BaseException(String paramString1, String paramString2, String paramString3)
  {
    super("errCode:" + paramString1 + ",errMsg:" + paramString2 + ",errDtlMsg:" + paramString3);
    
  }

  public BaseException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    
    this.backStacks.append(getStackTrace(paramThrowable));
  }

  public BaseException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super("errCode:" + paramString1 + ",errMsg:" + paramString2, paramThrowable);
    
    this.backStacks.append(getStackTrace(paramThrowable));
  }

  public final String getBackStacks()
  {
    return this.backStacks.toString();
  }

  public static final String getStackTrace(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    return localStringWriter.toString();
  }

  public static final String getStackTrace(BaseException paramBaseException)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    if (paramBaseException.backStacks != null)
      localPrintWriter.println(paramBaseException.backStacks);
    paramBaseException.printStackTrace(localPrintWriter);
    return localStringWriter.toString();
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    if (this.backStacks != null)
      paramPrintStream.println(this.backStacks);
    super.printStackTrace(paramPrintStream);
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    if (this.backStacks != null)
      paramPrintWriter.println(this.backStacks);
    super.printStackTrace(paramPrintWriter);
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }


}