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
 * The simplest logging API - kiss
 * 
 * @author Jesper Udby
 */
public interface Logger {
	void debug(String msg, Object... args);

	void debug(String msg, Throwable exc);

	boolean isDebugEnabled();

	void info(String msg, Object... args);

	void info(String msg, Throwable exc);

	boolean isInfoEnabled();

	void config(String msg, Object... args);

	void config(String msg, Throwable exc);

	boolean isConfigEnabled();

	void warn(String msg, Object... args);

	void warn(String msg, Throwable exc);

	boolean isWarnEnabled();

	void error(String msg, Object... args);

	void error(String msg, Throwable exc);

	boolean isErrorEnabled();
}
