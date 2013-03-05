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
 * File Name			:	EmailService.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Mar 4, 2013  3:21:21 PM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2013 the original author or authors and Organization.
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
package om.edu.squ.squportal.portlet.leaveapp.utility.email;

import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;

/**
 * @author Bhabesh
 *
 */
public interface EmailService 
{
	/**
	 * 
	 * method name  : setLeaveEmail
	 * @param emailData
	 * @return
	 * EmailService
	 * return type  : boolean
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Mar 5, 2013 12:34:25 PM
	 */
	public boolean setLeaveEmail(EmailData emailData);
}
