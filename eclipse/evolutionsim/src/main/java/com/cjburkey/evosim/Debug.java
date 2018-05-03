package com.cjburkey.evosim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Debug {
	
	private static final Logger logger = LogManager.getLogger("evosim");
	
	public static void log(Object msg) {
		logger.info(sanitize(msg));
	}
	
	public static void log(Object msg, Object... param) {
		logger.info(sanitize(msg), param);
	}
	
	public static void warn(Object msg) {
		logger.warn(sanitize(msg));
	}
	
	public static void warn(Object msg, Object... param) {
		logger.warn(sanitize(msg), param);
	}
	
	public static void error(Object msg) {
		logger.error(sanitize(msg));
	}
	
	public static void error(Object msg, Object... param) {
		logger.error(sanitize(msg), param);
	}
	
	public static void exception(Throwable t) {
		error("-- BEGERR --");
		error("  Error: " + t.getMessage());
		for (StackTraceElement e : t.getStackTrace()) {
			error("    " + e.toString());
		}
		error("-- ENDERR --");
	}
	
	public static void setDefaultErrorHandler() {
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> exception(e));
	}
	
	public static Logger getLogger() {
		return logger;
	}
	
	private static String sanitize(Object in) {
		String str = (in == null) ? "null" : in.toString();
		return (str == null) ? "null" : str;
	}
	
}