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

import java.util.Arrays;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppModel
{
	private	String			requestNo;
	private	String			requestDate;
	private	String			leaveType;
	private	String			leaveStartDate;
	private	String			leaveEndDate;
	private	String			leaveRemarks;
	private	String			leavePurpose;
	private	String			leaveLastReturnDate;
	private	String			researchId;
	private	boolean			adminSqu;
	private	String			positionAdditional;
	private	DelegatedEmp[]	delegatedEmps;
	private	String			approverAction;										//	Action for approve, reject, return
	private	String			approverRemark;
	
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveAppModel ["
				+ (this.requestNo != null ? "requestNo=" + this.requestNo
						+ ", " : "")
				+ (this.requestDate != null ? "requestDate=" + this.requestDate
						+ ", " : "")
				+ (this.leaveType != null ? "leaveType=" + this.leaveType
						+ ", " : "")
				+ (this.leaveStartDate != null ? "leaveStartDate="
						+ this.leaveStartDate + ", " : "")
				+ (this.leaveEndDate != null ? "leaveEndDate="
						+ this.leaveEndDate + ", " : "")
				+ (this.leaveRemarks != null ? "leaveRemarks="
						+ this.leaveRemarks + ", " : "")
				+ (this.leavePurpose != null ? "leavePurpose="
						+ this.leavePurpose + ", " : "")
				+ (this.leaveLastReturnDate != null ? "leaveLastReturnDate="
						+ this.leaveLastReturnDate + ", " : "")
				+ (this.researchId != null ? "researchId=" + this.researchId
						+ ", " : "")
				+ "adminSqu="
				+ this.adminSqu
				+ ", "
				+ (this.delegatedEmps != null ? "delegatedEmps="
						+ Arrays.toString(this.delegatedEmps) + ", " : "")
				+ "approverAction="
				+ this.approverAction
				+ ", "
				+ (this.approverRemark != null ? "approverRemark="
						+ this.approverRemark : "") + "]";
	}
	
	
	
	
}
