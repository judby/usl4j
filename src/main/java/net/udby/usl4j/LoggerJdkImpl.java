/*
 *  Copyright (c) 2013 Jesper Udby
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.udby.usl4j;

import java.util.logging.Level;

/**
 * A jdk-1.4 logging implementation - more or less a delegate
 * 
 * @author Jesper Udby
 */
class LoggerJdkImpl implements Logger {
	/** Level-mapping for debug logging */
	static final Level DEBUG = Level.FINE;
	/** Level-mapping for info logging */
	static final Level INFO = Level.INFO;
	/** Level-mapping for config logging */
	static final Level CONFIG = Level.CONFIG;
	/** Level-mapping for warn logging */
	static final Level WARN = Level.WARNING;
	/** Level-mapping for error logging */
	static final Level ERROR = Level.SEVERE;
	
	final java.util.logging.Logger logger;
	
	private LoggerJdkImpl(String name) {
		this.logger = java.util.logging.Logger.getLogger(name);
	}

	static LoggerJdkImpl create(String name) {
		return new LoggerJdkImpl(name);
	}

	static java.util.logging.Logger unwrap(Object o) {
		if (o instanceof java.util.logging.Logger) {
			return (java.util.logging.Logger)o;
		} else if (o instanceof LoggerJdkImpl) {
			return ((LoggerJdkImpl)o).logger;
		} else {
			return null;
		}
	}
	
	@Override
	public void debug(String msg, Object... args) {
		log(DEBUG, msg, args);
	}

	@Override
	public void debug(String msg, Throwable exc) {
		log(DEBUG, msg, exc);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isLoggable(DEBUG);
	}

	@Override
	public void info(String msg, Object... args) {
		log(INFO, msg, args);
	}

	@Override
	public void info(String msg, Throwable exc) {
		log(INFO, msg, exc);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isLoggable(INFO);
	}

	@Override
	public void config(String msg, Object... args) {
		log(CONFIG, msg, args);
	}

	@Override
	public void config(String msg, Throwable exc) {
		log(CONFIG, msg, exc);
	}

	@Override
	public boolean isConfigEnabled() {
		return logger.isLoggable(CONFIG);
	}

	@Override
	public void warn(String msg, Object... args) {
		log(WARN, msg, args);
	}

	@Override
	public void warn(String msg, Throwable exc) {
		log(WARN, msg, exc);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isLoggable(WARN);
	}

	@Override
	public void error(String msg, Object... args) {
		log(ERROR, msg, args);
	}

	@Override
	public void error(String msg, Throwable exc) {
		log(ERROR, msg, exc);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isLoggable(ERROR);
	}

	private void log(Level level, String msg, Object... args) {
		if (logger.isLoggable(level)) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
			logger.logp(level, ste.getClassName(), ste.getMethodName(), msg, args);
		}
	}

	private void log(Level level, String msg, Throwable exc) {
		if (logger.isLoggable(level)) {
			StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
			logger.logp(level, ste.getClassName(), ste.getMethodName(), msg, exc);
		}
	}
}
