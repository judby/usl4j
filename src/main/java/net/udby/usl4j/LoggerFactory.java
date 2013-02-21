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

/**
 * The simple LogFactory providing appropriate Logger implementations
 * 
 * @author Jesper Udby
 */
public class LoggerFactory {
	private LoggerFactory() {
	}
	/**
	 * KISS factory method that determines logger name from the calling class using stacktrace
	 * 
	 * @return Log implementation for the calling class
	 */
	public static Logger getLogger() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace.length > 2) {
			return getLogger(stackTrace[2].getClassName());
		} else {
			return getLogger(Logger.class);
		}
	}

	/**
	 * Factory method for creating Log implementation for the class given
	 * 
	 * @param clazz
	 *            class to get Log implementation for
	 * @return Logger implementation for the class argument given
	 */
	public static Logger getLogger(Class<?> clazz) {
		return getLoggerImpl(clazz.getName());
	}

	/**
	 * Factory method for creating Log implementation for the name given
	 * 
	 * @param className
	 *            class-name to get Log implementation for
	 * @return Logger implementation for the className argument given
	 */
	public static Logger getLogger(String className) {
		return getLoggerImpl(className);
	}

	/**
	 * Internal factory method - this is where you change if you do not like the default jdk-logging implementation
	 * 
	 * @param name
	 *            Name of logger
	 * @return appropriate Logger implementation for the name given
	 */
	private static Logger getLoggerImpl(String name) {
		return LoggerJdkImpl.create(name);
	}
}
