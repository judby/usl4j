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
import java.util.Date;
import net.udby.usl4j.Logger;
import net.udby.usl4j.LoggerFactory;


public class SampleCode {
	private static final Logger logger = LoggerFactory.getLogger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.config("user={0}", System.getProperty("user.name"));
		logger.info("started={0}", new Date());
		logger.error("error", new Exception());
	}
}
