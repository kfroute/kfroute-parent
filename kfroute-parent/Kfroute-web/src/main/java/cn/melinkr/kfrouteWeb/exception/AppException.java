package cn.melinkr.kfrouteWeb.exception;

public class AppException extends BaseException {
	private static final long serialVersionUID = 5487627886134869465L;

	public AppException() {
	}

	public AppException(String paramString) {
		super(paramString);
	}

	public AppException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	public AppException(String paramString1, String paramString2) {
		super(paramString1, paramString2);
	}

	public AppException(String paramString1, String paramString2,
			String paramString3) {
		super(paramString1, paramString2, paramString3);
	}

	public AppException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}

	public AppException(String paramString1, String paramString2,
			Throwable paramThrowable) {
		super(paramString1, paramString2, paramThrowable);
	}
}