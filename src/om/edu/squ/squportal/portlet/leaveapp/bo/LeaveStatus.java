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
 * File Name			:	LeaveStatus.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 18, 2012  9:29:36 AM
 * Date of modification :	
 * 
 * Summary				:	Leave Status
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
package om.edu.squ.squportal.portlet.leaveapp.bo;

/**
 * @author Bhabesh
 *
 */
public class LeaveStatus
{
	private		String		statusCode;
	private		String		statusDesc;
	/**
	 * Getter Method	: getStatusCode
	 * @return the statusCode
	 * 
	 * Date				: Sep 18, 2012
	 */
	public String getStatusCode()
	{
		return this.statusCode;
	}
	/**
	 * Setter method : setStatusCode
	 * @param statusCode the statusCode to set
	 * 
	 * Date          : Sep 18, 2012 9:30:29 AM
	 */
	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}
	/**
	 * Getter Method	: getStatusDesc
	 * @return the statusDesc
	 * 
	 * Date				: Sep 18, 2012
	 */
	public String getStatusDesc()
	{
		return this.statusDesc;
	}
	/**
	 * Setter method : setStatusDesc
	 * @param statusDesc the statusDesc to set
	 * 
	 * Date          : Sep 18, 2012 9:30:29 AM
	 */
	public void setStatusDesc(String statusDesc)
	{
		this.statusDesc = statusDesc;
	}
	
	
}
