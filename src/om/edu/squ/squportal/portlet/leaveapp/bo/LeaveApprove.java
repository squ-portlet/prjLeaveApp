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
 * File Name			:	LeaveApprove.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 22, 2012  1:57:01 PM
 * Date of modification :	
 * 
 * Summary				:	Leave Approve
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
public class LeaveApprove implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private		String		requestNo;
	private		AdminAction	action;
	private		Employee	employee;
	private		String		branchCode;
	private		String		departmentCode;
	private		String		sectionCode;
	private		String		approverAction;
	private		String		approverRemark;
	private		String		leaveIndicator;					// R = Leave Return & L = Leave Request
	/**
	 * Getter Method	: getRequestNo
	 * @return the requestNo
	 * 
	 * Date				: Sep 22, 2012
	 */
	public String getRequestNo()
	{
		return this.requestNo;
	}
	/**
	 * Setter method : setRequestNo
	 * @param requestNo the requestNo to set
	 * 
	 * Date          : Sep 22, 2012 2:01:14 PM
	 */
	public void setRequestNo(String requestNo)
	{
		this.requestNo = requestNo;
	}
	/**
	 * Getter Method	: getAction
	 * @return the action
	 * 
	 * Date				: Jan 13, 2013
	 */
	public AdminAction getAction()
	{
		return this.action;
	}
	/**
	 * Setter method : setAction
	 * @param action the action to set
	 * 
	 * Date          : Jan 13, 2013 12:29:06 PM
	 */
	public void setAction(AdminAction action)
	{
		this.action = action;
	}
	/**
	 * Getter Method	: getEmployee
	 * @return the employee
	 * 
	 * Date				: Sep 22, 2012
	 */
	public Employee getEmployee()
	{
		return this.employee;
	}
	/**
	 * Setter method : setEmployee
	 * @param employee the employee to set
	 * 
	 * Date          : Sep 22, 2012 2:01:14 PM
	 */
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	/**
	 * Getter Method	: getBranchCode
	 * @return the branchCode
	 * 
	 * Date				: Dec 15, 2012
	 */
	public String getBranchCode()
	{
		return this.branchCode;
	}
	/**
	 * Setter method : setBranchCode
	 * @param branchCode the branchCode to set
	 * 
	 * Date          : Dec 15, 2012 12:26:38 PM
	 */
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Dec 15, 2012
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Dec 15, 2012 12:26:38 PM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getSectionCode
	 * @return the sectionCode
	 * 
	 * Date				: Dec 15, 2012
	 */
	public String getSectionCode()
	{
		return this.sectionCode;
	}
	/**
	 * Setter method : setSectionCode
	 * @param sectionCode the sectionCode to set
	 * 
	 * Date          : Dec 15, 2012 12:26:38 PM
	 */
	public void setSectionCode(String sectionCode)
	{
		this.sectionCode = sectionCode;
	}
	/**
	 * Getter Method	: getApproverAction
	 * @return the approverAction
	 * 
	 * Date				: Sep 22, 2012
	 */
	public String getApproverAction()
	{
		return this.approverAction;
	}
	/**
	 * Setter method : setApproverAction
	 * @param approverAction the approverAction to set
	 * 
	 * Date          : Sep 22, 2012 2:01:14 PM
	 */
	public void setApproverAction(String approverAction)
	{
		this.approverAction = approverAction;
	}
	/**
	 * Getter Method	: getApproverRemark
	 * @return the approverRemark
	 * 
	 * Date				: Sep 22, 2012
	 */
	public String getApproverRemark()
	{
		return this.approverRemark;
	}
	/**
	 * Setter method : setApproverRemark
	 * @param approverRemark the approverRemark to set
	 * 
	 * Date          : Sep 22, 2012 2:01:14 PM
	 */
	public void setApproverRemark(String approverRemark)
	{
		this.approverRemark = approverRemark;
	}
	/**
	 * Getter Method	: getLeaveIndicator
	 * @return the leaveIndicator
	 * 
	 * Date				: Jan 1, 2017
	 */
	public String getLeaveIndicator()
	{
		return this.leaveIndicator;
	}
	/**
	 * Setter method : setLeaveIndicator
	 * @param leaveIndicator the leaveIndicator to set
	 * 
	 * Date          : Jan 1, 2017 11:31:44 AM
	 */
	public void setLeaveIndicator(String leaveIndicator)
	{
		this.leaveIndicator = leaveIndicator;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveApprove [requestNo=" + this.requestNo + ", action="
				+ this.action + ", employee=" + this.employee + ", branchCode="
				+ this.branchCode + ", departmentCode=" + this.departmentCode
				+ ", sectionCode=" + this.sectionCode + ", approverAction="
				+ this.approverAction + ", approverRemark="
				+ this.approverRemark + ", leaveIndicator="
				+ this.leaveIndicator + "]";
	}
	
	
	
}
