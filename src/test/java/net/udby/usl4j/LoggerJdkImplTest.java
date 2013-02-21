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

import static org.junit.Assert.*;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jesper Udby
 */
public class LoggerJdkImplTest {
	private static final String LOGGER_NAME = LoggerJdkImplTest.class.getName();
	
	private Logger logger;
	private java.util.logging.Logger jdklogger;
	private boolean logCalled;
	private LogRecord record;
	private Filter filter = new Filter() {
		@Override
		public boolean isLoggable(LogRecord lr) {
			logCalled = true;
			record = lr;
			return true;
		}
	};
	
	@Before
	public void setUp() throws Exception {
		logger = LoggerFactory.getLogger();
		jdklogger = LoggerJdkImpl.unwrap(logger);
		logCalled = false;
		record = null;
		jdklogger.setFilter(filter);
		jdklogger.setLevel(Level.INFO);
	}

	@Test
	public void testUnwrap() {
		// pass in Logger and get jdk-Logger
		java.util.logging.Logger l = LoggerJdkImpl.unwrap(logger);
		assertEquals(((LoggerJdkImpl)logger).logger, l);
		// pass in jdk-Logger and get it back
		java.util.logging.Logger x = LoggerJdkImpl.unwrap(l);
		assertEquals(l, x);
		// pass in something else and get null
		java.util.logging.Logger n = LoggerJdkImpl.unwrap(this);
		assertNull(n);
		// pass in null and get null
		java.util.logging.Logger nil = LoggerJdkImpl.unwrap(null);
		assertNull(nil);
	}

	@Test
	public void testDebugStringObjectArray() {
		// debug logging is not enabled (INFO)
		logger.debug("Testing");
		assertFalse(logCalled);
		assertNull(record);
		// change level (uses FINE for debug)
		jdklogger.setLevel(Level.FINE);
		logger.debug("Testing");
		assertTrue(logCalled);
		assertNotNull(record);
		assertEquals(LOGGER_NAME, record.getLoggerName());
		assertEquals("Testing", record.getMessage());
		assertEquals(LOGGER_NAME, record.getSourceClassName());
		assertEquals("testDebugStringObjectArray", record.getSourceMethodName());
	}

	@Test
	public void testInfoStringObjectArray() {
		// create object of another class using this class' logger...
		Object o = new Object() {
			@Override
			public String toString() {
				// Do some logging from other class...
				logger.info("Testing");
				return super.toString();
			}
			
		};
		// do the logging...
		o.toString();
		
		assertTrue(logCalled);
		assertNotNull(record);
		assertEquals(LOGGER_NAME, record.getLoggerName());
		assertEquals("Testing", record.getMessage());
		assertEquals(o.getClass().getName(), record.getSourceClassName());
		assertEquals("toString", record.getSourceMethodName());
	}

	@Test
	public void testDebugStringThrowable() {
		final Exception e = new Exception("Aargh");
		// debug logging is not enabled (INFO)
		logger.debug("Error", e);
		assertFalse(logCalled);
		assertNull(record);
		// change level (uses FINE for debug)
		jdklogger.setLevel(Level.FINE);
		logger.debug("Error", e);
		assertTrue(logCalled);
		assertNotNull(record);
		assertEquals(LOGGER_NAME, record.getLoggerName());
		assertEquals("Error", record.getMessage());
		assertEquals(e, record.getThrown());
		assertEquals(LOGGER_NAME, record.getSourceClassName());
		assertEquals("testDebugStringThrowable", record.getSourceMethodName());
	}

	@Test
	public void testIsDebugEnabled() {
		// debug logging is not enabled (INFO)
		assertFalse(logger.isDebugEnabled());
		// change level (uses FINE for debug)
		jdklogger.setLevel(Level.FINE);
		assertTrue(logger.isDebugEnabled());
	}

	@Test
	public void testIsInfoEnabled() {
		// info logging is enabled (INFO)
		assertTrue(logger.isInfoEnabled());
		// change level
		jdklogger.setLevel(Level.WARNING);
		assertFalse(logger.isInfoEnabled());
	}

	@Test
	public void testIsConfigEnabled() {
		// config logging is not enabled (INFO)
		assertFalse(logger.isConfigEnabled());
		// change level
		jdklogger.setLevel(Level.CONFIG);
		assertTrue(logger.isConfigEnabled());
	}

	@Test
	public void testIsWarnEnabled() {
		// warn logging is enabled (INFO)
		assertTrue(logger.isWarnEnabled());
		// change level
		jdklogger.setLevel(Level.SEVERE);
		assertFalse(logger.isWarnEnabled());
	}

	@Test
	public void testIsErrorEnabled() {
		// error logging is enabled (INFO)
		assertTrue(logger.isErrorEnabled());
		// change level
		jdklogger.setLevel(Level.OFF);
		assertFalse(logger.isErrorEnabled());
	}

}
