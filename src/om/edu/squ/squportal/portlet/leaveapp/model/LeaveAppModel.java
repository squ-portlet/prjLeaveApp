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
 * File Name			:	LeaveAppModel.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.model
 * Date of creation		:	Aug 8, 2012  9:32:26 AM
 * Date of modification :	
 * 
 * Summary				:	Leave application model
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
package om.edu.squ.squportal.portlet.leaveapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppModel implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	private	String			requestNo;
	private	String			requestDate;
	private	String			leaveType;
	private	String			leaveTypeFlag;
	private	String			leaveStartDate;
	private	String			leaveEndDate;
	private	String			leaveReturnDate;
	private	String			leaveRemarks;
	private	String			leavePurpose;
	private	String			leaveLastReturnDate;
	private	long			leaveBalance;
	private	String			employeeNumber;
	private	String			empLevel;
	private	String			researchId;
	private	boolean			adminSqu;
	private	String			branch;
	private	String			branch2;
	private	String			department2;										////	Department changed by employee
	private	String			section2;											//		Section changed by employee
	private	String			hod;												//		head of department/section/branch
	private	String			positionAdditional;
	private	DelegatedEmp[]	delegatedEmps;
	private	String			approverAction;										//	Action for approve, reject, return
	private	String			approverRemark;
	private	String			opMode;												//  mode for update/insert etc.
	private	String			approverEmpNumber;
	private	String			processSalaray;
	private	boolean			acceptLeave;
	private	String			msgLeaveRequest;									// Message from eleave request - mostly at the time of rejection
	private	String			renderUrlText;
	private	String			userChoosenApproverEmpNumber;						// This approver number will be overrulled by direct manager by system
	
	
	/**
	 * Getter Method	: getRequestNo
	 * @return the requestNo
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getRequestNo()
	{
		return this.requestNo;
	}
	/**
	 * Setter method : setRequestNo
	 * @param requestNo the requestNo to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setRequestNo(String requestNo)
	{
		this.requestNo = requestNo;
	}
	/**
	 * Getter Method	: getRequestDate
	 * @return the requestDate
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getRequestDate()
	{
		return this.requestDate;
	}
	/**
	 * Setter method : setRequestDate
	 * @param requestDate the requestDate to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}
	/**
	 * Getter Method	: getLeaveType
	 * @return the leaveType
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeaveType()
	{
		return this.leaveType;
	}
	/**
	 * Setter method : setLeaveType
	 * @param leaveType the leaveType to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setLeaveType(String leaveType)
	{
		this.leaveType = leaveType;
	}
	/**
	 * Getter Method	: getLeaveTypeFlag
	 * @return the leaveTypeFlag
	 * 
	 * Date				: Sep 26, 2012
	 */
	public String getLeaveTypeFlag()
	{
		return this.leaveTypeFlag;
	}
	/**
	 * Setter method : setLeaveTypeFlag
	 * @param leaveTypeFlag the leaveTypeFlag to set
	 * 
	 * Date          : Sep 26, 2012 12:18:27 PM
	 */
	public void setLeaveTypeFlag(String leaveTypeFlag)
	{
		this.leaveTypeFlag = leaveTypeFlag;
	}
	/**
	 * Getter Method	: getLeaveStartDate
	 * @return the leaveStartDate
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeaveStartDate()
	{
		return this.leaveStartDate;
	}
	/**
	 * Setter method : setLeaveStartDate
	 * @param leaveStartDate the leaveStartDate to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setLeaveStartDate(String leaveStartDate)
	{
		this.leaveStartDate = leaveStartDate;
	}
	/**
	 * Getter Method	: getLeaveEndDate
	 * @return the leaveEndDate
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeaveEndDate()
	{
		return this.leaveEndDate;
	}
	/**
	 * Setter method : setLeaveEndDate
	 * @param leaveEndDate the leaveEndDate to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setLeaveEndDate(String leaveEndDate)
	{
		this.leaveEndDate = leaveEndDate;
	}
	/**
	 * Getter Method	: getLeaveReturnDate
	 * @return the leaveReturnDate
	 * 
	 * Date				: Jul 26, 2016
	 */
	public String getLeaveReturnDate()
	{
		return this.leaveReturnDate;
	}
	/**
	 * Setter method : setLeaveReturnDate
	 * @param leaveReturnDate the leaveReturnDate to set
	 * 
	 * Date          : Jul 26, 2016 12:12:13 PM
	 */
	public void setLeaveReturnDate(String leaveReturnDate)
	{
		this.leaveReturnDate = leaveReturnDate;
	}
	/**
	 * Getter Method	: getLeaveRemarks
	 * @return the leaveRemarks
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeaveRemarks()
	{
		return this.leaveRemarks;
	}
	/**
	 * Setter method : setLeaveRemarks
	 * @param leaveRemarks the leaveRemarks to set
	 * 
	 * Date          : Aug 8, 2012 9:36:39 AM
	 */
	public void setLeaveRemarks(String leaveRemarks)
	{
		this.leaveRemarks = leaveRemarks;
	}
	/**
	 * Getter Method	: getLeavePurpose
	 * @return the leavePurpose
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeavePurpose()
	{
		return this.leavePurpose;
	}
	/**
	 * Setter method : setLeavePurpose
	 * @param leavePurpose the leavePurpose to set
	 * 
	 * Date          : Aug 8, 2012 1:20:19 PM
	 */
	public void setLeavePurpose(String leavePurpose)
	{
		this.leavePurpose = leavePurpose;
	}
	/**
	 * Getter Method	: getLeaveLastReturnDate
	 * @return the leaveLastReturnDate
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getLeaveLastReturnDate()
	{
		return this.leaveLastReturnDate;
	}
	/**
	 * Setter method : setLeaveLastReturnDate
	 * @param leaveLastReturnDate the leaveLastReturnDate to set
	 * 
	 * Date          : Aug 8, 2012 1:20:19 PM
	 */
	public void setLeaveLastReturnDate(String leaveLastReturnDate)
	{
		this.leaveLastReturnDate = leaveLastReturnDate;
	}
	
	/**
	 * Getter Method	: getLeaveBalance
	 * @return the leaveBalance
	 * 
	 * Date				: Jul 14, 2013
	 */
	public long getLeaveBalance()
	{
		return this.leaveBalance;
	}
	/**
	 * Setter method : setLeaveBalance
	 * @param leaveBalance the leaveBalance to set
	 * 
	 * Date          : Jul 14, 2013 11:41:00 AM
	 */
	public void setLeaveBalance(long leaveBalance)
	{
		this.leaveBalance = leaveBalance;
	}
	/**
	 * Getter Method	: getEmployeeNumber
	 * @return the employeeNumber
	 * 
	 * Date				: Feb 19, 2013
	 */
	public String getEmployeeNumber()
	{
		return this.employeeNumber;
	}
	/**
	 * Setter method : setEmployeeNumber
	 * @param employeeNumber the employeeNumber to set
	 * 
	 * Date          : Feb 19, 2013 9:11:48 AM
	 */
	public void setEmployeeNumber(String employeeNumber)
	{
		this.employeeNumber = employeeNumber;
	}
	/**
	 * Getter Method	: getEmpLevel
	 * @return the empLevel
	 * 
	 * Date				: Feb 19, 2013
	 */
	public String getEmpLevel()
	{
		return this.empLevel;
	}
	/**
	 * Setter method : setEmpLevel
	 * @param empLevel the empLevel to set
	 * 
	 * Date          : Feb 19, 2013 9:14:19 AM
	 */
	public void setEmpLevel(String empLevel)
	{
		this.empLevel = empLevel;
	}
	/**
	 * Getter Method	: getResearchId
	 * @return the researchId
	 * 
	 * Date				: Aug 8, 2012
	 */
	public String getResearchId()
	{
		return this.researchId;
	}
	/**
	 * Setter method : setResearchId
	 * @param researchId the researchId to set
	 * 
	 * Date          : Aug 8, 2012 1:20:19 PM
	 */
	public void setResearchId(String researchId)
	{
		this.researchId = researchId;
	}
	/**
	 * Getter Method	: isAdminSqu
	 * @return the adminSqu
	 * 
	 * Date				: Aug 8, 2012
	 */
	public boolean isAdminSqu()
	{
		return this.adminSqu;
	}
	/**
	 * Setter method : setAdminSqu
	 * @param adminSqu the adminSqu to set
	 * 
	 * Date          : Aug 8, 2012 1:20:19 PM
	 */
	public void setAdminSqu(boolean adminSqu)
	{
		this.adminSqu = adminSqu;
	}
	/**
	 * Getter Method	: getBranch
	 * @return the branch
	 * 
	 * Date				: Nov 24, 2012
	 */
	public String getBranch()
	{
		return this.branch;
	}
	/**
	 * Setter method : setBranch
	 * @param branch the branch to set
	 * 
	 * Date          : Nov 24, 2012 2:20:52 PM
	 */
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	/**
	 * Getter Method	: getBranch2
	 * @return the branch2
	 * 
	 * Date				: Nov 25, 2012
	 */
	public String getBranch2()
	{
		return this.branch2;
	}
	/**
	 * Setter method : setBranch2
	 * @param branch2 the branch2 to set
	 * 
	 * Date          : Nov 25, 2012 1:30:10 PM
	 */
	public void setBranch2(String branch2)
	{
		this.branch2 = branch2;
	}
	/**
	 * Getter Method	: getDepartment2
	 * @return the department2
	 * 
	 * Date				: Sep 29, 2012
	 */
	public String getDepartment2()
	{
		return this.department2;
	}
	/**
	 * Setter method : setDepartment2
	 * @param department2 the department2 to set
	 * 
	 * Date          : Sep 29, 2012 2:17:34 PM
	 */
	public void setDepartment2(String department2)
	{
		this.department2 = department2;
	}
	/**
	 * Getter Method	: getSection2
	 * @return the section2
	 * 
	 * Date				: Nov 26, 2012
	 */
	public String getSection2()
	{
		return this.section2;
	}
	/**
	 * Setter method : setSection2
	 * @param section2 the section2 to set
	 * 
	 * Date          : Nov 26, 2012 12:59:30 PM
	 */
	public void setSection2(String section2)
	{
		this.section2 = section2;
	}
	/**
	 * Getter Method	: getHod
	 * @return the hod
	 * 
	 * Date				: Dec 5, 2012
	 */
	public String getHod()
	{
		return this.hod;
	}
	/**
	 * Setter method : setHod
	 * @param hod the hod to set
	 * 
	 * Date          : Dec 5, 2012 12:18:54 PM
	 */
	public void setHod(String hod)
	{
		this.hod = hod;
	}
	/**
	 * Getter Method	: getPositionAdditional
	 * @return the positionAdditional
	 * 
	 * Date				: Sep 9, 2012
	 */
	public String getPositionAdditional()
	{
		return this.positionAdditional;
	}
	/**
	 * Setter method : setPositionAdditional
	 * @param positionAdditional the positionAdditional to set
	 * 
	 * Date          : Sep 9, 2012 1:16:50 PM
	 */
	public void setPositionAdditional(String positionAdditional)
	{
		this.positionAdditional = positionAdditional;
	}
	/**
	 * Getter Method	: getDelegatedEmps
	 * @return the delegatedEmps
	 * 
	 * Date				: Aug 12, 2012
	 */
	public DelegatedEmp[] getDelegatedEmps()
	{
		return this.delegatedEmps;
	}
	/**
	 * Setter method : setDelegatedEmps
	 * @param delegatedEmps the delegatedEmps to set
	 * 
	 * Date          : Aug 12, 2012 9:03:35 AM
	 */
	public void setDelegatedEmps(DelegatedEmp[] delegatedEmps)
	{
		this.delegatedEmps = delegatedEmps;
	}
	
	/**
	 * 
	 * method name  : setDelegatedEmpsList
	 * @param delegatedEmps
	 * LeaveAppModel
	 * return type  : void
	 * 
	 * purpose		: convert list to array for delegated emps
	 *
	 * Date    		:	Jan 1, 2013 9:50:11 AM
	 */
	public void setDelegatedEmpsList(List<DelegatedEmp> delegatedEmpsLst)
	{
		DelegatedEmp[]	delEmpsArr	=	new DelegatedEmp[delegatedEmpsLst.size()];
		for(int i=0; i<delegatedEmpsLst.size(); i++)
		{
			delEmpsArr[i] = delegatedEmpsLst.get(i);
		}
		this.delegatedEmps	=	delEmpsArr;
	}
	
	/**
	 * Getter Method	: getApproverAction
	 * @return the approverAction
	 * 
	 * Date				: Aug 12, 2012
	 */
	public String getApproverAction()
	{
		return this.approverAction;
	}
	/**
	 * Setter method : setApproverAction
	 * @param approverAction the approverAction to set
	 * 
	 * Date          : Aug 12, 2012 1:38:08 PM
	 */
	public void setApproverAction(String approverAction)
	{
		this.approverAction = approverAction;
	}
	/**
	 * Getter Method	: getApproverRemark
	 * @return the approverRemark
	 * 
	 * Date				: Aug 13, 2012
	 */
	public String getApproverRemark()
	{
		return this.approverRemark;
	}
	/**
	 * Setter method : setApproverRemark
	 * @param approverRemark the approverRemark to set
	 * 
	 * Date          : Aug 13, 2012 9:11:26 AM
	 */
	public void setApproverRemark(String approverRemark)
	{
		this.approverRemark = approverRemark;
	}
	
	/**
	 * Getter Method	: getOpMode
	 * @return the opMode
	 * 
	 * Date				: Dec 15, 2012
	 */
	public String getOpMode()
	{
		return this.opMode;
	}
	/**
	 * Setter method : setOpMode
	 * @param opMode the opMode to set
	 * 
	 * Date          : Dec 15, 2012 12:41:29 PM
	 */
	public void setOpMode(String opMode)
	{
		this.opMode = opMode;
	}
	/**
	 * Getter Method	: getApproverEmpNumber
	 * @return the approverEmpNumber
	 * 
	 * Date				: Mar 30, 2013
	 */
	public String getApproverEmpNumber()
	{
		return this.approverEmpNumber;
	}
	/**
	 * Setter method : setApproverEmpNumber
	 * @param approverEmpNumber the approverEmpNumber to set
	 * 
	 * Date          : Mar 30, 2013 10:41:49 AM
	 */
	public void setApproverEmpNumber(String approverEmpNumber)
	{
		this.approverEmpNumber = approverEmpNumber;
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
	 * Date          : May 13, 2013 11:12:41 AM
	 */
	public void setProcessSalaray(String processSalaray)
	{
		this.processSalaray = processSalaray;
	}
	/**
	 * Getter Method	: isAcceptLeave
	 * @return the acceptLeave
	 * 
	 * Date				: Nov 9, 2016
	 */
	public boolean isAcceptLeave()
	{
		return this.acceptLeave;
	}
	/**
	 * Setter method : setAcceptLeave
	 * @param acceptLeave the acceptLeave to set
	 * 
	 * Date          : Nov 9, 2016 11:57:24 AM
	 */
	public void setAcceptLeave(boolean acceptLeave)
	{
		this.acceptLeave = acceptLeave;
	}
	
	/**
	 * Getter Method	: getMsgLeaveRequest
	 * @return the msgLeaveRequest
	 * 
	 * Date				: Nov 9, 2016
	 */
	public String getMsgLeaveRequest()
	{
		return this.msgLeaveRequest;
	}
	/**
	 * Setter method : setMsgLeaveRequest
	 * @param msgLeaveRequest the msgLeaveRequest to set
	 * 
	 * Date          : Nov 9, 2016 12:39:57 PM
	 */
	public void setMsgLeaveRequest(String msgLeaveRequest)
	{
		this.msgLeaveRequest = msgLeaveRequest;
	}
	
	/**
	 * Getter Method	: getRenderUrlText
	 * @return the renderUrlText
	 * 
	 * Date				: Mar 23, 2017
	 */
	public String getRenderUrlText() {
		return this.renderUrlText;
	}
	/**
	 * Setter method : setRenderUrlText
	 * @param renderUrlText the renderUrlText to set
	 * 
	 * Date          : Mar 23, 2017 2:09:23 PM
	 */
	public void setRenderUrlText(String renderUrlText) {
		this.renderUrlText = renderUrlText;
	}
	
	/**
	 * Getter Method	: getUserChoosenApproverEmpNumber
	 * @return the userChoosenApproverEmpNumber
	 * 
	 * Date				: Jul 28, 2019
	 */
	public String getUserChoosenApproverEmpNumber() {
		return this.userChoosenApproverEmpNumber;
	}
	/**
	 * Setter method : setUserChoosenApproverEmpNumber
	 * @param userChoosenApproverEmpNumber the userChoosenApproverEmpNumber to set
	 * 
	 * Date          : Jul 28, 2019 2:27:11 PM
	 */
	public void setUserChoosenApproverEmpNumber(String userChoosenApproverEmpNumber) {
		this.userChoosenApproverEmpNumber = userChoosenApproverEmpNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LeaveAppModel [requestNo=" + this.requestNo + ", requestDate="
				+ this.requestDate + ", leaveType=" + this.leaveType
				+ ", leaveTypeFlag=" + this.leaveTypeFlag + ", leaveStartDate="
				+ this.leaveStartDate + ", leaveEndDate=" + this.leaveEndDate
				+ ", leaveReturnDate=" + this.leaveReturnDate
				+ ", leaveRemarks=" + this.leaveRemarks + ", leavePurpose="
				+ this.leavePurpose + ", leaveLastReturnDate="
				+ this.leaveLastReturnDate + ", leaveBalance="
				+ this.leaveBalance + ", employeeNumber=" + this.employeeNumber
				+ ", empLevel=" + this.empLevel + ", researchId="
				+ this.researchId + ", adminSqu=" + this.adminSqu + ", branch="
				+ this.branch + ", branch2=" + this.branch2 + ", department2="
				+ this.department2 + ", section2=" + this.section2 + ", hod="
				+ this.hod + ", positionAdditional=" + this.positionAdditional
				+ ", delegatedEmps=" + Arrays.toString(this.delegatedEmps)
				+ ", approverAction=" + this.approverAction
				+ ", approverRemark=" + this.approverRemark + ", opMode="
				+ this.opMode + ", approverEmpNumber=" + this.approverEmpNumber
				+ ", processSalaray=" + this.processSalaray + ", acceptLeave="
				+ this.acceptLeave + ", msgLeaveRequest="
				+ this.msgLeaveRequest + ", renderUrlText="
				+ this.renderUrlText + ", userChoosenApproverEmpNumber="
				+ this.userChoosenApproverEmpNumber + "]";
	}
	
	
	
	
}
