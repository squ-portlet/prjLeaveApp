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
 * File Name			:	DelegatedEmp.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Aug 12, 2012  8:56:37 AM
 * Date of modification :	
 * 
 * Summary				:	Delegated employee
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
public class DelegatedEmp
{
	private	String	fromDate;
	private	String	toDate;
	private	String	empNumber;
	private	String	empInternetId;
	private	String	empName;
	private String	branchCode;
	private	String	departmentCode;
	
	/**
	 * Getter Method	: getFromDate
	 * @return the fromDate
	 * 
	 * Date				: Aug 12, 2012
	 */
	public String getFromDate()
	{
		return this.fromDate;
	}
	/**
	 * Setter method : setFromDate
	 * @param fromDate the fromDate to set
	 * 
	 * Date          : Aug 12, 2012 8:58:54 AM
	 */
	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}
	/**
	 * Getter Method	: getToDate
	 * @return the toDate
	 * 
	 * Date				: Aug 12, 2012
	 */
	public String getToDate()
	{
		return this.toDate;
	}
	/**
	 * Setter method : setToDate
	 * @param toDate the toDate to set
	 * 
	 * Date          : Aug 12, 2012 8:58:54 AM
	 */
	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Aug 12, 2012
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Aug 12, 2012 8:58:54 AM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getEmpInternetId
	 * @return the empInternetId
	 * 
	 * Date				: Sep 19, 2012
	 */
	public String getEmpInternetId()
	{
		return this.empInternetId;
	}
	/**
	 * Setter method : setEmpInternetId
	 * @param empInternetId the empInternetId to set
	 * 
	 * Date          : Sep 19, 2012 11:07:35 AM
	 */
	public void setEmpInternetId(String empInternetId)
	{
		this.empInternetId = empInternetId;
	}
	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Sep 15, 2012
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Sep 15, 2012 11:54:47 AM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getBranchCode
	 * @return the branchCode
	 * 
	 * Date				: Dec 19, 2012
	 */
	public String getBranchCode()
	{
		return this.branchCode;
	}
	/**
	 * Setter method : setBranchCode
	 * @param branchCode the branchCode to set
	 * 
	 * Date          : Dec 19, 2012 1:04:11 PM
	 */
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Sep 15, 2012
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Sep 15, 2012 11:54:47 AM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DelegatedEmp ["
				+ (this.fromDate != null ? "fromDate=" + this.fromDate + ", "
						: "")
				+ (this.toDate != null ? "toDate=" + this.toDate + ", " : "")
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.empInternetId != null ? "empInternetId="
						+ this.empInternetId + ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ (this.branchCode != null ? "branchCode=" + this.branchCode
						+ ", " : "")
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode : "") + "]";
	}
}
