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
	private		Employee	employee;
	private		String		branchCode;
	private		String		departmentCode;
	private		String		sectionCode;
	private		String		approverAction;
	private		String		approverRemark;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveApprove ["
				+ (this.requestNo != null ? "requestNo=" + this.requestNo
						+ ", " : "")
				+ (this.employee != null ? "employee=" + this.employee + ", "
						: "")
				+ (this.approverAction != null ? "approverAction="
						+ this.approverAction + ", " : "")
				+ (this.approverRemark != null ? "approverRemark="
						+ this.approverRemark : "") + "]";
	}
	
	
	
}
