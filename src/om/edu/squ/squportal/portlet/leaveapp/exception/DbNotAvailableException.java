/**
 * Project				:	prjLeaveApp
 * Organization			:	Sultan Qaboos University | Muscat | Oman
 * Centre				:	Centre for Information System
 * Department			:	Planning & Development
 * 
 * Author				:	Bhabesh
 *
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	DbNotAvailableException.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.exception
 * Date of creation		:	Oct 7, 2012  1:16:36 PM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2012 the original author or authors and Organization.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * 
 */
package om.edu.squ.squportal.portlet.leaveapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

/**
 * @author Bhabesh
 *
 */
public class DbNotAvailableException extends CannotGetJdbcConnectionException
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("deprecation")
	public DbNotAvailableException(String msg, ClassNotFoundException ex)
	{
		super(msg, ex);
		logger.error("Database is not responding");
		// TODO Auto-generated constructor stub
	}

	private static final long	serialVersionUID	= 1L;
	
}
