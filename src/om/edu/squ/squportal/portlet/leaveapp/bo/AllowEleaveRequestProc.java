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
 * File Name			:	AllowEleaveRequestProc.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 26, 2012  10:49:26 AM
 * Date of modification :	
 * 
 * Summary				:	Object for oracle stored procedure "allow_eleave_request" 
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
public class AllowEleaveRequestProc
{
	private	boolean acceptLeave;
	private	String	leaveCode;
	private	String	leaveMessage;
	private	String	checkedAprroverEmpCode;
	/**
	 * Getter Method	: isAcceptLeave
	 * @return the acceptLeave
	 * 
	 * Date				: Sep 26, 2012
	 */
	public boolean isAcceptLeave()
	{
		return this.acceptLeave;
	}
	/**
	 * Setter method : setAcceptLeave
	 * @param acceptLeave the acceptLeave to set
	 * 
	 * Date          : Sep 26, 2012 10:54:21 AM
	 */
	public void setAcceptLeave(boolean acceptLeave)
	{
		this.acceptLeave = acceptLeave;
	}
	/**
	 * Getter Method	: getLeaveCode
	 * @return the leaveCode
	 * 
	 * Date				: Sep 26, 2012
	 */
	public String getLeaveCode()
	{
		return this.leaveCode;
	}
	/**
	 * Setter method : setLeaveCode
	 * @param leaveCode the leaveCode to set
	 * 
	 * Date          : Sep 26, 2012 10:54:21 AM
	 */
	public void setLeaveCode(String leaveCode)
	{
		this.leaveCode = leaveCode;
	}
	/**
	 * Getter Method	: getLeaveMessage
	 * @return the leaveMessage
	 * 
	 * Date				: Sep 26, 2012
	 */
	public String getLeaveMessage()
	{
		return this.leaveMessage;
	}
	/**
	 * Setter method : setLeaveMessage
	 * @param leaveMessage the leaveMessage to set
	 * 
	 * Date          : Sep 26, 2012 10:54:21 AM
	 */
	public void setLeaveMessage(String leaveMessage)
	{
		this.leaveMessage = leaveMessage;
	}
	/**
	 * Getter Method	: getCheckedAprroverEmpCode
	 * @return the checkedAprroverEmpCode
	 * 
	 * Date				: Mar 10, 2013
	 */
	public String getCheckedAprroverEmpCode()
	{
		return this.checkedAprroverEmpCode;
	}
	/**
	 * Setter method : setCheckedAprroverEmpCode
	 * @param checkedAprroverEmpCode the checkedAprroverEmpCode to set
	 * 
	 * Date          : Mar 10, 2013 9:37:55 AM
	 */
	public void setCheckedAprroverEmpCode(String checkedAprroverEmpCode)
	{
		this.checkedAprroverEmpCode = checkedAprroverEmpCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AllowEleaveRequestProc [acceptLeave="
				+ this.acceptLeave
				+ ", "
				+ (this.leaveCode != null ? "leaveCode=" + this.leaveCode
						+ ", " : "")
				+ (this.leaveMessage != null ? "leaveMessage="
						+ this.leaveMessage + ", " : "")
				+ (this.checkedAprroverEmpCode != null ? "checkedAprroverEmpCode="
						+ this.checkedAprroverEmpCode
						: "") + "]";
	}
	
	
	
}
