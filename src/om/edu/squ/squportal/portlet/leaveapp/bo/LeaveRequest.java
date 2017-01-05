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

import java.io.Serializable;

/**
 * @author Bhabesh
 *
 */
public class LeaveRequest implements Serializable
{
	
	private static final long	serialVersionUID	= 1L;
	
	private	String			requestNo;
	private	String			requestDate;
	private	String			leaveStartDate;
	private	String			leaveEndDate;
	private	long			leaveDateDuration;
	private	String			leaveReturnDate;
	private	String			leaveLastReturnDate;
	private	String			leaveStatus;
	private	String			finalStatusCode;										//	-- Irrespective of different intermediate leave status code, it shows the last leave status code
	private	Employee		employee;
	private	LeaveType		leaveType;
	private	LeaveType		leaveTypeFlag;
	private	LeaveStatus		status;
	private	LeaveApprove	approve;
	private	LeaveApprove	returnApprove;
	private	String			leavePurpose;
	private	boolean			leaveRequestActive;
	private	String			leaveRequestRemarks;
	private	String			suggestedHod;
	private	String			approverId;
	private	String			researchId;
	private	boolean			sabbaticalLowerApproverAction;
	private	int				approverSequenceNo;
	private	String			processSalaray;
	private	boolean			leaveReturn;
	private String			leaveReturnIndicator;
	private	boolean			allowLeaveExtension;
	private	String			leaveExtnStartDate;
	private	int				leaveBalanceStart;
	private	int				leaveBalanceEnd;
	
	
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
	 * Getter Method	: getLeaveDateDuration
	 * @return the leaveDateDuration
	 * 
	 * Date				: Jul 18, 2013
	 */
	public long getLeaveDateDuration()
	{
		return this.leaveDateDuration;
	}
	/**
	 * Setter method : setLeaveDateDuration
	 * @param leaveDateDuration the leaveDateDuration to set
	 * 
	 * Date          : Jul 18, 2013 1:48:41 PM
	 */
	public void setLeaveDateDuration(long leaveDateDuration)
	{
		this.leaveDateDuration = leaveDateDuration;
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
	 * Getter Method	: getFinalStatusCode
	 * @return the finalStatusCode
	 * 
	 * Date				: Aug 4, 2016
	 */
	public String getFinalStatusCode()
	{
		return this.finalStatusCode;
	}
	/**
	 * Setter method : setFinalStatusCode
	 * @param finalStatusCode the finalStatusCode to set
	 * 
	 * Date          : Aug 4, 2016 3:08:44 PM
	 */
	public void setFinalStatusCode(String finalStatusCode)
	{
		this.finalStatusCode = finalStatusCode;
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
	 * Getter Method	: getLeaveTypeFlag
	 * @return the leaveTypeFlag
	 * 
	 * Date				: Sep 26, 2012
	 */
	public LeaveType getLeaveTypeFlag()
	{
		return this.leaveTypeFlag;
	}
	/**
	 * Setter method : setLeaveTypeFlag
	 * @param leaveTypeFlag the leaveTypeFlag to set
	 * 
	 * Date          : Sep 26, 2012 12:31:29 PM
	 */
	public void setLeaveTypeFlag(LeaveType leaveTypeFlag)
	{
		this.leaveTypeFlag = leaveTypeFlag;
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
	 * Getter Method	: getApprove
	 * @return the approve
	 * 
	 * Date				: Oct 8, 2012
	 */
	public LeaveApprove getApprove()
	{
		return this.approve;
	}
	/**
	 * Setter method : setApprove
	 * @param approve the approve to set
	 * 
	 * Date          : Oct 8, 2012 2:02:58 PM
	 */
	public void setApprove(LeaveApprove approve)
	{
		this.approve = approve;
	}
	/**
	 * Getter Method	: getReturnApprove
	 * @return the returnApprove
	 * 
	 * Date				: Aug 1, 2016
	 */
	public LeaveApprove getReturnApprove()
	{
		return this.returnApprove;
	}
	/**
	 * Setter method : setReturnApprove
	 * @param returnApprove the returnApprove to set
	 * 
	 * Date          : Aug 1, 2016 1:53:43 PM
	 */
	public void setReturnApprove(LeaveApprove returnApprove)
	{
		this.returnApprove = returnApprove;
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
	/**
	 * Getter Method	: getSuggestedHod
	 * @return the suggestedHod
	 * 
	 * Date				: Dec 15, 2012
	 */
	public String getSuggestedHod()
	{
		return this.suggestedHod;
	}
	/**
	 * Setter method : setSuggestedHod
	 * @param suggestedHod the suggestedHod to set
	 * 
	 * Date          : Dec 15, 2012 10:03:27 AM
	 */
	public void setSuggestedHod(String suggestedHod)
	{
		this.suggestedHod = suggestedHod;
	}
	/**
	 * Getter Method	: getApproverId
	 * @return the approverId
	 * 
	 * Date				: Feb 2, 2013
	 */
	public String getApproverId()
	{
		return this.approverId;
	}
	/**
	 * Setter method : setApproverId
	 * @param approverId the approverId to set
	 * 
	 * Date          : Feb 2, 2013 10:15:14 AM
	 */
	public void setApproverId(String approverId)
	{
		this.approverId = approverId;
	}
	/**
	 * Getter Method	: getResearchId
	 * @return the researchId
	 * 
	 * Date				: Mar 19, 2013
	 */
	public String getResearchId()
	{
		return this.researchId;
	}
	/**
	 * Setter method : setResearchId
	 * @param researchId the researchId to set
	 * 
	 * Date          : Mar 19, 2013 12:18:37 PM
	 */
	public void setResearchId(String researchId)
	{
		this.researchId = researchId;
	}
	/**
	 * Getter Method	: isSabbaticalLowerApproverAction
	 * @return the sabbaticalLowerApproverAction
	 * 
	 * Date				: Mar 26, 2013
	 */
	public boolean isSabbaticalLowerApproverAction()
	{
		return this.sabbaticalLowerApproverAction;
	}
	/**
	 * Setter method : setSabbaticalLowerApproverAction
	 * @param sabbaticalLowerApproverAction the sabbaticalLowerApproverAction to set
	 * 
	 * Date          : Mar 26, 2013 12:45:47 PM
	 */
	public void setSabbaticalLowerApproverAction(
			boolean sabbaticalLowerApproverAction)
	{
		this.sabbaticalLowerApproverAction = sabbaticalLowerApproverAction;
	}
	
	/**
	 * Getter Method	: getApproverSequenceNo
	 * @return the approverSequenceNo
	 * 
	 * Date				: Mar 27, 2013
	 */
	public int getApproverSequenceNo()
	{
		return this.approverSequenceNo;
	}
	/**
	 * Setter method : setApproverSequenceNo
	 * @param approverSequenceNo the approverSequenceNo to set
	 * 
	 * Date          : Mar 27, 2013 10:37:15 AM
	 */
	public void setApproverSequenceNo(int approverSequenceNo)
	{
		this.approverSequenceNo = approverSequenceNo;
	}
	/**
	 * Getter Method	: getProcessSalaray
	 * @return the processSalaray
	 * 
	 * Date				: May 13, 2013
	 */
	public String getProcessSalaray()
	{
		return this.processSalaray;
	}
	/**
	 * Setter method : setProcessSalaray
	 * @param processSalaray the processSalaray to set
	 * 
	 * Date          : May 13, 2013 11:04:09 AM
	 */
	public void setProcessSalaray(String processSalaray)
	{
		this.processSalaray = processSalaray;
	}
	
	/**
	 * Getter Method	: isLeaveReturn
	 * @return the leaveReturn
	 * 
	 * Date				: Jun 2, 2016
	 */
	public boolean isLeaveReturn()
	{
		return this.leaveReturn;
	}
	/**
	 * Setter method : setLeaveReturn
	 * @param leaveReturn the leaveReturn to set
	 * 
	 * Date          : Jun 2, 2016 10:59:51 AM
	 */
	public void setLeaveReturn(boolean leaveReturn)
	{
		this.leaveReturn = leaveReturn;
	}
	
	/**
	 * Getter Method	: getLeaveReturnIndicator
	 * @return the leaveReturnIndicator
	 * 
	 * Date				: Aug 4, 2016
	 */
	public String getLeaveReturnIndicator()
	{
		return this.leaveReturnIndicator;
	}
	/**
	 * Setter method : setLeaveReturnIndicator
	 * @param leaveReturnIndicator the leaveReturnIndicator to set
	 * 
	 * Date          : Aug 4, 2016 12:30:25 PM
	 */
	public void setLeaveReturnIndicator(String leaveReturnIndicator)
	{
		this.leaveReturnIndicator = leaveReturnIndicator;
	}
	/**
	 * Getter Method	: isAllowLeaveExtension
	 * @return the allowLeaveExtension
	 * 
	 * Date				: Nov 10, 2016
	 */
	public boolean isAllowLeaveExtension()
	{
		return this.allowLeaveExtension;
	}
	/**
	 * Setter method : setAllowLeaveExtension
	 * @param allowLeaveExtension the allowLeaveExtension to set
	 * 
	 * Date          : Nov 10, 2016 1:19:57 PM
	 */
	public void setAllowLeaveExtension(boolean allowLeaveExtension)
	{
		this.allowLeaveExtension = allowLeaveExtension;
	}
	/**
	 * Getter Method	: getLeaveExtnStartDate
	 * @return the leaveExtnStartDate
	 * 
	 * Date				: Nov 14, 2016
	 */
	public String getLeaveExtnStartDate()
	{
		return this.leaveExtnStartDate;
	}
	/**
	 * Setter method : setLeaveExtnStartDate
	 * @param leaveExtnStartDate the leaveExtnStartDate to set
	 * 
	 * Date          : Nov 14, 2016 8:09:40 AM
	 */
	public void setLeaveExtnStartDate(String leaveExtnStartDate)
	{
		this.leaveExtnStartDate = leaveExtnStartDate;
	}
	/**
	 * Getter Method	: getLeaveBalanceStart
	 * @return the leaveBalanceStart
	 * 
	 * Date				: Jan 5, 2017
	 */
	public int getLeaveBalanceStart()
	{
		return this.leaveBalanceStart;
	}
	/**
	 * Setter method : setLeaveBalanceStart
	 * @param leaveBalanceStart the leaveBalanceStart to set
	 * 
	 * Date          : Jan 5, 2017 12:11:50 PM
	 */
	public void setLeaveBalanceStart(int leaveBalanceStart)
	{
		this.leaveBalanceStart = leaveBalanceStart;
	}
	/**
	 * Getter Method	: getLeaveBalanceEnd
	 * @return the leaveBalanceEnd
	 * 
	 * Date				: Jan 5, 2017
	 */
	public int getLeaveBalanceEnd()
	{
		return this.leaveBalanceEnd;
	}
	/**
	 * Setter method : setLeaveBalanceEnd
	 * @param leaveBalanceEnd the leaveBalanceEnd to set
	 * 
	 * Date          : Jan 5, 2017 12:11:50 PM
	 */
	public void setLeaveBalanceEnd(int leaveBalanceEnd)
	{
		this.leaveBalanceEnd = leaveBalanceEnd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveRequest [requestNo=" + this.requestNo + ", requestDate="
				+ this.requestDate + ", leaveStartDate=" + this.leaveStartDate
				+ ", leaveEndDate=" + this.leaveEndDate
				+ ", leaveDateDuration=" + this.leaveDateDuration
				+ ", leaveReturnDate=" + this.leaveReturnDate
				+ ", leaveLastReturnDate=" + this.leaveLastReturnDate
				+ ", leaveStatus=" + this.leaveStatus + ", finalStatusCode="
				+ this.finalStatusCode + ", employee=" + this.employee
				+ ", leaveType=" + this.leaveType + ", leaveTypeFlag="
				+ this.leaveTypeFlag + ", status=" + this.status + ", approve="
				+ this.approve + ", returnApprove=" + this.returnApprove
				+ ", leavePurpose=" + this.leavePurpose
				+ ", leaveRequestActive=" + this.leaveRequestActive
				+ ", leaveRequestRemarks=" + this.leaveRequestRemarks
				+ ", suggestedHod=" + this.suggestedHod + ", approverId="
				+ this.approverId + ", researchId=" + this.researchId
				+ ", sabbaticalLowerApproverAction="
				+ this.sabbaticalLowerApproverAction + ", approverSequenceNo="
				+ this.approverSequenceNo + ", processSalaray="
				+ this.processSalaray + ", leaveReturn=" + this.leaveReturn
				+ ", leaveReturnIndicator=" + this.leaveReturnIndicator
				+ ", allowLeaveExtension=" + this.allowLeaveExtension
				+ ", leaveExtnStartDate=" + this.leaveExtnStartDate
				+ ", leaveBalanceStart=" + this.leaveBalanceStart
				+ ", leaveBalanceEnd=" + this.leaveBalanceEnd + "]";
	}
	
	
	
}
