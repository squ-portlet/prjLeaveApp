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
 * File Name			:	AdminAction.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Aug 25, 2012  1:52:10 PM
 * Date of modification :	
 * 
 * Summary				:	Admin Action
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

import java.io.Serializable;

/**
 * @author Bhabesh
 *
 */
public class AdminAction implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private	String 	actionCode;
	private	String	actionDesc;
	private	String	actionDate;
	private	String	adminActionRemark;
	
	/**
	 * Getter Method	: getActionCode
	 * @return the actionCode
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getActionCode()
	{
		return this.actionCode;
	}
	/**
	 * Setter method : setActionCode
	 * @param actionCode the actionCode to set
	 * 
	 * Date          : Aug 26, 2012 7:52:48 AM
	 */
	public void setActionCode(String actionCode)
	{
		this.actionCode = actionCode;
	}
	/**
	 * Getter Method	: getActionDesc
	 * @return the actionDesc
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getActionDesc()
	{
		return this.actionDesc;
	}
	/**
	 * Setter method : setActionDesc
	 * @param actionDesc the actionDesc to set
	 * 
	 * Date          : Aug 26, 2012 7:52:48 AM
	 */
	public void setActionDesc(String actionDesc)
	{
		this.actionDesc = actionDesc;
	}
	/**
	 * Getter Method	: getActionDate
	 * @return the actionDate
	 * 
	 * Date				: Jan 13, 2013
	 */
	public String getActionDate()
	{
		return this.actionDate;
	}
	/**
	 * Setter method : setActionDate
	 * @param actionDate the actionDate to set
	 * 
	 * Date          : Jan 13, 2013 12:27:38 PM
	 */
	public void setActionDate(String actionDate)
	{
		this.actionDate = actionDate;
	}
	/**
	 * Getter Method	: getAdminActionRemark
	 * @return the adminActionRemark
	 * 
	 * Date				: Jan 13, 2013
	 */
	public String getAdminActionRemark()
	{
		return this.adminActionRemark;
	}
	/**
	 * Setter method : setAdminActionRemark
	 * @param adminActionRemark the adminActionRemark to set
	 * 
	 * Date          : Jan 13, 2013 12:30:45 PM
	 */
	public void setAdminActionRemark(String adminActionRemark)
	{
		this.adminActionRemark = adminActionRemark;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AdminAction ["
				+ (this.actionCode != null ? "actionCode=" + this.actionCode
						+ ", " : "")
				+ (this.actionDesc != null ? "actionDesc=" + this.actionDesc
						+ ", " : "")
				+ (this.actionDate != null ? "actionDate=" + this.actionDate
						+ ", " : "")
				+ (this.adminActionRemark != null ? "adminActionRemark="
						+ this.adminActionRemark : "") + "]";
	}
	
	
	
}
