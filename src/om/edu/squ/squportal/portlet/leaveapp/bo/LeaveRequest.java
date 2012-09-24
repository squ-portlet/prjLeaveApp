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
 * File Name			:	LeaveRequest.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 2, 2012  1:01:38 PM
 * Date of modification :	
 * 
 * Summary				:	Leave Request Object
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
public class LeaveRequest
{
	
	private	String			requestNo;
	private	String			requestDate;
	private	String			leaveStartDate;
	private	String			leaveEndDate;
	private	String			leaveReturnDate;
	private	String			leaveLastReturnDate;
	private	String			leaveStatus;
	private	Employee		employee;
	private	LeaveType		leaveType;
	private	LeaveStatus		status;
	private	String			leavePurpose;
	private	boolean			leaveRequestActive;
	private	String			leaveRequestRemarks;
	/**
	 * Getter Method	: getRequestNo
	 * @return the requestNo
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getRequestNo()
	{
		return this.requestNo;
	}
	/**
	 * Setter method : setRequestNo
	 * @param requestNo the requestNo to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setRequestNo(String requestNo)
	{
		this.requestNo = requestNo;
	}
	/**
	 * Getter Method	: getRequestDate
	 * @return the requestDate
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getRequestDate()
	{
		return this.requestDate;
	}
	/**
	 * Setter method : setRequestDate
	 * @param requestDate the requestDate to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}
	/**
	 * Getter Method	: getLeaveStartDate
	 * @return the leaveStartDate
	 * 
	 * Date				: Sep 3, 2012
	 */
	public String getLeaveStartDate()
	{
		return this.leaveStartDate;
	}
	/**
	 * Setter method : setLeaveStartDate
	 * @param leaveStartDate the leaveStartDate to set
	 * 
	 * Date          : Sep 3, 2012 2:23:37 PM
	 */
	public void setLeaveStartDate(String leaveStartDate)
	{
		this.leaveStartDate = leaveStartDate;
	}
	/**
	 * Getter Method	: getLeaveEndDate
	 * @return the leaveEndDate
	 * 
	 * Date				: Sep 3, 2012
	 */
	public String getLeaveEndDate()
	{
		return this.leaveEndDate;
	}
	/**
	 * Setter method : setLeaveEndDate
	 * @param leaveEndDate the leaveEndDate to set
	 * 
	 * Date          : Sep 3, 2012 2:23:37 PM
	 */
	public void setLeaveEndDate(String leaveEndDate)
	{
		this.leaveEndDate = leaveEndDate;
	}
	/**
	 * Getter Method	: getLeaveReturnDate
	 * @return the leaveReturnDate
	 * 
	 * Date				: Sep 3, 2012
	 */
	public String getLeaveReturnDate()
	{
		return this.leaveReturnDate;
	}
	/**
	 * Setter method : setLeaveReturnDate
	 * @param leaveReturnDate the leaveReturnDate to set
	 * 
	 * Date          : Sep 3, 2012 2:26:13 PM
	 */
	public void setLeaveReturnDate(String leaveReturnDate)
	{
		this.leaveReturnDate = leaveReturnDate;
	}
	/**
	 * Getter Method	: getLeaveLastReturnDate
	 * @return the leaveLastReturnDate
	 * 
	 * Date				: Sep 8, 2012
	 */
	public String getLeaveLastReturnDate()
	{
		return this.leaveLastReturnDate;
	}
	/**
	 * Setter method : setLeaveLastReturnDate
	 * @param leaveLastReturnDate the leaveLastReturnDate to set
	 * 
	 * Date          : Sep 8, 2012 8:57:51 AM
	 */
	public void setLeaveLastReturnDate(String leaveLastReturnDate)
	{
		this.leaveLastReturnDate = leaveLastReturnDate;
	}
	/**
	 * Getter Method	: getLeaveStatus
	 * @return the leaveStatus
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getLeaveStatus()
	{
		return this.leaveStatus;
	}
	/**
	 * Setter method : setLeaveStatus
	 * @param leaveStatus the leaveStatus to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setLeaveStatus(String leaveStatus)
	{
		this.leaveStatus = leaveStatus;
	}
	/**
	 * Getter Method	: getEmployee
	 * @return the employee
	 * 
	 * Date				: Sep 2, 2012
	 */
	public Employee getEmployee()
	{
		return this.employee;
	}
	/**
	 * Setter method : setEmployee
	 * @param employee the employee to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	/**
	 * Getter Method	: getLeaveType
	 * @return the leaveType
	 * 
	 * Date				: Sep 2, 2012
	 */
	public LeaveType getLeaveType()
	{
		return this.leaveType;
	}
	/**
	 * Setter method : setLeaveType
	 * @param leaveType the leaveType to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setLeaveType(LeaveType leaveType)
	{
		this.leaveType = leaveType;
	}
	/**
	 * Getter Method	: getStatus
	 * @return the status
	 * 
	 * Date				: Sep 18, 2012
	 */
	public LeaveStatus getStatus()
	{
		return this.status;
	}
	/**
	 * Setter method : setStatus
	 * @param status the status to set
	 * 
	 * Date          : Sep 18, 2012 9:33:06 AM
	 */
	public void setStatus(LeaveStatus status)
	{
		this.status = status;
	}
	/**
	 * Getter Method	: getLeavePurpose
	 * @return the leavePurpose
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getLeavePurpose()
	{
		return this.leavePurpose;
	}
	/**
	 * Setter method : setLeavePurpose
	 * @param leavePurpose the leavePurpose to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setLeavePurpose(String leavePurpose)
	{
		this.leavePurpose = leavePurpose;
	}
	/**
	 * Getter Method	: isLeaveRequestActive
	 * @return the leaveRequestActive
	 * 
	 * Date				: Sep 2, 2012
	 */
	public boolean isLeaveRequestActive()
	{
		return this.leaveRequestActive;
	}
	/**
	 * Setter method : setLeaveRequestActive
	 * @param leaveRequestActive the leaveRequestActive to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setLeaveRequestActive(boolean leaveRequestActive)
	{
		this.leaveRequestActive = leaveRequestActive;
	}
	/**
	 * Getter Method	: getLeaveRequestRemarks
	 * @return the leaveRequestRemarks
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getLeaveRequestRemarks()
	{
		return this.leaveRequestRemarks;
	}
	/**
	 * Setter method : setLeaveRequestRemarks
	 * @param leaveRequestRemarks the leaveRequestRemarks to set
	 * 
	 * Date          : Sep 2, 2012 1:57:10 PM
	 */
	public void setLeaveRequestRemarks(String leaveRequestRemarks)
	{
		this.leaveRequestRemarks = leaveRequestRemarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveRequest ["
				+ (this.requestNo != null ? "requestNo=" + this.requestNo
						+ ", " : "")
				+ (this.requestDate != null ? "requestDate=" + this.requestDate
						+ ", " : "")
				+ (this.leaveStartDate != null ? "leaveStartDate="
						+ this.leaveStartDate + ", " : "")
				+ (this.leaveEndDate != null ? "leaveEndDate="
						+ this.leaveEndDate + ", " : "")
				+ (this.leaveReturnDate != null ? "leaveReturnDate="
						+ this.leaveReturnDate + ", " : "")
				+ (this.leaveLastReturnDate != null ? "leaveLastReturnDate="
						+ this.leaveLastReturnDate + ", " : "")
				+ (this.leaveStatus != null ? "leaveStatus=" + this.leaveStatus
						+ ", " : "")
				+ (this.employee != null ? "employee=" + this.employee + ", "
						: "")
				+ (this.leaveType != null ? "leaveType=" + this.leaveType
						+ ", " : "")
				+ (this.leavePurpose != null ? "leavePurpose="
						+ this.leavePurpose + ", " : "")
				+ "leaveRequestActive="
				+ this.leaveRequestActive
				+ ", "
				+ (this.leaveRequestRemarks != null ? "leaveRequestRemarks="
						+ this.leaveRequestRemarks : "") + "]";
	}
	
	
	
}
