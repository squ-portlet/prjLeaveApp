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
 * File Name			:	EmailData.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Jan 21, 2013  2:22:50 PM
 * Date of modification :	
 * 
 * Summary				:	Mail Data Object
 *
 *
 * Copyright 2013 the original author or authors and Organization.
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
public class EmailData
{
	private	String	mailTo;
	private	String	mailFrom;
	private	String	emailReceiverName;
	private	String	emailReceiverNameEn;
	private	String	emailReceiverNameAr;
	private	String	emailTemplateName;
	private String 	requestNo;
	private	String	requesterName;
	private	String	requesterNameAr;
	private	String	requesterEmail;
	private	String	requestDate;
	private	String	requestStartDate;
	private	String	requestEndDate;
	private	String	requesterRemark;
	private	String	delegationAvilable;
	private	String	delegationDetails;
	private	String	delegateName;
	private	String	delegationStartDate;
	private	String	delegationEndDate;
	private	String	approverName;
	private	String	approverNameAr;
	private	String	approverEmail;
	private	String	approveDate;
	private	String	approverRemark;
	private	String	leaveUrl;
	private	String	emailMessage;
	private	String	emailMessageAr;
	private	String	leaveIndicator;
	
	
	/**
	 * Getter Method	: getMailTo
	 * @return the mailTo
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getMailTo()
	{
		return this.mailTo;
	}
	/**
	 * Setter method : setMailTo
	 * @param mailTo the mailTo to set
	 * 
	 * Date          : Jan 21, 2013 2:42:13 PM
	 */
	public void setMailTo(String mailTo)
	{
		this.mailTo = mailTo;
	}
	/**
	 * Getter Method	: getMailFrom
	 * @return the mailFrom
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getMailFrom()
	{
		return this.mailFrom;
	}
	/**
	 * Setter method : setMailFrom
	 * @param mailFrom the mailFrom to set
	 * 
	 * Date          : Jan 21, 2013 2:42:13 PM
	 */
	public void setMailFrom(String mailFrom)
	{
		this.mailFrom = mailFrom;
	}
	/**
	 * Getter Method	: getEmailReceiverName
	 * @return the emailReceiverName
	 * 
	 * Date				: Feb 5, 2013
	 */
	public String getEmailReceiverName()
	{
		return this.emailReceiverName;
	}
	/**
	 * Setter method : setEmailReceiverName
	 * @param emailReceiverName the emailReceiverName to set
	 * 
	 * Date          : Feb 5, 2013 1:10:13 PM
	 */
	public void setEmailReceiverName(String emailReceiverName)
	{
		this.emailReceiverName = emailReceiverName;
	}
	/**
	 * Getter Method	: getEmailReceiverNameEn
	 * @return the emailReceiverNameEn
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getEmailReceiverNameEn()
	{
		return this.emailReceiverNameEn;
	}
	/**
	 * Setter method : setEmailReceiverNameEn
	 * @param emailReceiverNameEn the emailReceiverNameEn to set
	 * 
	 * Date          : Feb 27, 2013 4:00:26 PM
	 */
	public void setEmailReceiverNameEn(String emailReceiverNameEn)
	{
		this.emailReceiverNameEn = emailReceiverNameEn;
	}
	/**
	 * Getter Method	: getEmailReceiverNameAr
	 * @return the emailReceiverNameAr
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getEmailReceiverNameAr()
	{
		return this.emailReceiverNameAr;
	}
	/**
	 * Setter method : setEmailReceiverNameAr
	 * @param emailReceiverNameAr the emailReceiverNameAr to set
	 * 
	 * Date          : Feb 27, 2013 4:00:26 PM
	 */
	public void setEmailReceiverNameAr(String emailReceiverNameAr)
	{
		this.emailReceiverNameAr = emailReceiverNameAr;
	}
	/**
	 * Getter Method	: getEmailTemplateName
	 * @return the emailTemplateName
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getEmailTemplateName()
	{
		return this.emailTemplateName;
	}
	/**
	 * Setter method : setEmailTemplateName
	 * @param emailTemplateName the emailTemplateName to set
	 * 
	 * Date          : Jan 21, 2013 2:53:59 PM
	 */
	public void setEmailTemplateName(String emailTemplateName)
	{
		this.emailTemplateName = emailTemplateName;
	}
	/**
	 * Getter Method	: getRequestNo
	 * @return the requestNo
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequestNo()
	{
		return this.requestNo;
	}
	/**
	 * Setter method : setRequestNo
	 * @param requestNo the requestNo to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequestNo(String requestNo)
	{
		this.requestNo = requestNo;
	}
	/**
	 * Getter Method	: getRequesterName
	 * @return the requesterName
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequesterName()
	{
		return this.requesterName;
	}
	/**
	 * Setter method : setRequesterName
	 * @param requesterName the requesterName to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequesterName(String requesterName)
	{
		this.requesterName = requesterName;
	}
	/**
	 * Getter Method	: getRequesterNameEn
	 * @return the requesterNameAr
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getRequesterNameAr()
	{
		return this.requesterNameAr;
	}
	/**
	 * Setter method : setRequesterNameEn
	 * @param requesterNameAr the requesterNameAr to set
	 * 
	 * Date          : Feb 27, 2013 4:12:49 PM
	 */
	public void setRequesterNameAr(String requesterNameAr)
	{
		this.requesterNameAr = requesterNameAr;
	}
	/**
	 * Getter Method	: getRequesterEmail
	 * @return the requesterEmail
	 * 
	 * Date				: Jan 23, 2013
	 */
	public String getRequesterEmail()
	{
		return this.requesterEmail;
	}
	/**
	 * Setter method : setRequesterEmail
	 * @param requesterEmail the requesterEmail to set
	 * 
	 * Date          : Jan 23, 2013 10:14:22 AM
	 */
	public void setRequesterEmail(String requesterEmail)
	{
		this.requesterEmail = requesterEmail;
	}
	/**
	 * Getter Method	: getRequestDate
	 * @return the requestDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequestDate()
	{
		return this.requestDate;
	}
	/**
	 * Setter method : setRequestDate
	 * @param requestDate the requestDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequestDate(String requestDate)
	{
		this.requestDate = requestDate;
	}
	/**
	 * Getter Method	: getRequestStartDate
	 * @return the requestStartDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequestStartDate()
	{
		return this.requestStartDate;
	}
	/**
	 * Setter method : setRequestStartDate
	 * @param requestStartDate the requestStartDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequestStartDate(String requestStartDate)
	{
		this.requestStartDate = requestStartDate;
	}
	/**
	 * Getter Method	: getRequestEndDate
	 * @return the requestEndDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequestEndDate()
	{
		return this.requestEndDate;
	}
	/**
	 * Setter method : setRequestEndDate
	 * @param requestEndDate the requestEndDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequestEndDate(String requestEndDate)
	{
		this.requestEndDate = requestEndDate;
	}
	/**
	 * Getter Method	: getRequesterRemark
	 * @return the requesterRemark
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getRequesterRemark()
	{
		return this.requesterRemark;
	}
	/**
	 * Getter Method	: getDelegationAvilable
	 * @return the delegationAvilable
	 * 
	 * Date				: Jan 23, 2013
	 */
	public String getDelegationAvilable()
	{
		return this.delegationAvilable;
	}
	/**
	 * Setter method : setDelegationAvilable
	 * @param delegationAvilable the delegationAvilable to set
	 * 
	 * Date          : Jan 23, 2013 1:54:40 PM
	 */
	public void setDelegationAvilable(String delegationAvilable)
	{
		this.delegationAvilable = delegationAvilable;
	}
	/**
	 * Getter Method	: getDelegationDetails
	 * @return the delegationDetails
	 * 
	 * Date				: Jan 23, 2013
	 */
	public String getDelegationDetails()
	{
		return this.delegationDetails;
	}
	/**
	 * Setter method : setDelegationDetails
	 * @param delegationDetails the delegationDetails to set
	 * 
	 * Date          : Jan 23, 2013 1:54:40 PM
	 */
	public void setDelegationDetails(String delegationDetails)
	{
		this.delegationDetails = delegationDetails;
	}
	/**
	 * Getter Method	: getDelegateName
	 * @return the delegateName
	 * 
	 * Date				: Jan 22, 2013
	 */
	public String getDelegateName()
	{
		return this.delegateName;
	}
	/**
	 * Setter method : setDelegateName
	 * @param delegateName the delegateName to set
	 * 
	 * Date          : Jan 22, 2013 12:07:46 PM
	 */
	public void setDelegateName(String delegateName)
	{
		this.delegateName = delegateName;
	}
	/**
	 * Setter method : setRequesterRemark
	 * @param requesterRemark the requesterRemark to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setRequesterRemark(String requesterRemark)
	{
		this.requesterRemark = requesterRemark;
	}
	/**
	 * Getter Method	: getDelegationStartDate
	 * @return the delegationStartDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getDelegationStartDate()
	{
		return this.delegationStartDate;
	}
	/**
	 * Setter method : setDelegationStartDate
	 * @param delegationStartDate the delegationStartDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setDelegationStartDate(String delegationStartDate)
	{
		this.delegationStartDate = delegationStartDate;
	}
	/**
	 * Getter Method	: getDelegationEndDate
	 * @return the delegationEndDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getDelegationEndDate()
	{
		return this.delegationEndDate;
	}
	/**
	 * Setter method : setDelegationEndDate
	 * @param delegationEndDate the delegationEndDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setDelegationEndDate(String delegationEndDate)
	{
		this.delegationEndDate = delegationEndDate;
	}
	/**
	 * Getter Method	: getApproverName
	 * @return the approverName
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getApproverName()
	{
		return this.approverName;
	}
	/**
	 * Setter method : setApproverName
	 * @param approverName the approverName to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setApproverName(String approverName)
	{
		this.approverName = approverName;
	}
	/**
	 * Getter Method	: getApproverNameAr
	 * @return the approverNameAr
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getApproverNameAr()
	{
		return this.approverNameAr;
	}
	/**
	 * Setter method : setApproverNameAr
	 * @param approverNameAr the approverNameAr to set
	 * 
	 * Date          : Feb 27, 2013 4:24:11 PM
	 */
	public void setApproverNameAr(String approverNameAr)
	{
		this.approverNameAr = approverNameAr;
	}
	/**
	 * Getter Method	: getApproverEmail
	 * @return the approverEmail
	 * 
	 * Date				: Jan 23, 2013
	 */
	public String getApproverEmail()
	{
		return this.approverEmail;
	}
	/**
	 * Setter method : setApproverEmail
	 * @param approverEmail the approverEmail to set
	 * 
	 * Date          : Jan 23, 2013 10:15:17 AM
	 */
	public void setApproverEmail(String approverEmail)
	{
		this.approverEmail = approverEmail;
	}
	/**
	 * Getter Method	: getApproveDate
	 * @return the approveDate
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getApproveDate()
	{
		return this.approveDate;
	}
	/**
	 * Setter method : setApproveDate
	 * @param approveDate the approveDate to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setApproveDate(String approveDate)
	{
		this.approveDate = approveDate;
	}
	/**
	 * Getter Method	: getApproverRemark
	 * @return the approverRemark
	 * 
	 * Date				: Jan 21, 2013
	 */
	public String getApproverRemark()
	{
		return this.approverRemark;
	}
	/**
	 * Setter method : setApproverRemark
	 * @param approverRemark the approverRemark to set
	 * 
	 * Date          : Jan 21, 2013 2:28:20 PM
	 */
	public void setApproverRemark(String approverRemark)
	{
		this.approverRemark = approverRemark;
	}
	/**
	 * Getter Method	: getLeaveUrl
	 * @return the leaveUrl
	 * 
	 * Date				: Jan 22, 2013
	 */
	public String getLeaveUrl()
	{
		return this.leaveUrl;
	}
	/**
	 * Setter method : setLeaveUrl
	 * @param leaveUrl the leaveUrl to set
	 * 
	 * Date          : Jan 22, 2013 11:24:52 AM
	 */
	public void setLeaveUrl(String leaveUrl)
	{
		this.leaveUrl = leaveUrl;
	}
	/**
	 * Getter Method	: getEmailMessage
	 * @return the emailMessage
	 * 
	 * Date				: Feb 5, 2013
	 */
	public String getEmailMessage()
	{
		return this.emailMessage;
	}
	/**
	 * Setter method : setEmailMessage
	 * @param emailMessage the emailMessage to set
	 * 
	 * Date          : Feb 5, 2013 1:22:51 PM
	 */
	public void setEmailMessage(String emailMessage)
	{
		this.emailMessage = emailMessage;
	}
	/**
	 * Getter Method	: getEmailMessageAr
	 * @return the emailMessageAr
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getEmailMessageAr()
	{
		return this.emailMessageAr;
	}
	/**
	 * Setter method : setEmailMessageAr
	 * @param emailMessageAr the emailMessageAr to set
	 * 
	 * Date          : Feb 27, 2013 1:13:18 PM
	 */
	public void setEmailMessageAr(String emailMessageAr)
	{
		this.emailMessageAr = emailMessageAr;
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
	 * Date          : Jan 1, 2017 12:06:39 PM
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
		return "EmailData [mailTo=" + this.mailTo + ", mailFrom="
				+ this.mailFrom + ", emailReceiverName="
				+ this.emailReceiverName + ", emailReceiverNameEn="
				+ this.emailReceiverNameEn + ", emailReceiverNameAr="
				+ this.emailReceiverNameAr + ", emailTemplateName="
				+ this.emailTemplateName + ", requestNo=" + this.requestNo
				+ ", requesterName=" + this.requesterName
				+ ", requesterNameAr=" + this.requesterNameAr
				+ ", requesterEmail=" + this.requesterEmail + ", requestDate="
				+ this.requestDate + ", requestStartDate="
				+ this.requestStartDate + ", requestEndDate="
				+ this.requestEndDate + ", requesterRemark="
				+ this.requesterRemark + ", delegationAvilable="
				+ this.delegationAvilable + ", delegationDetails="
				+ this.delegationDetails + ", delegateName="
				+ this.delegateName + ", delegationStartDate="
				+ this.delegationStartDate + ", delegationEndDate="
				+ this.delegationEndDate + ", approverName="
				+ this.approverName + ", approverNameAr=" + this.approverNameAr
				+ ", approverEmail=" + this.approverEmail + ", approveDate="
				+ this.approveDate + ", approverRemark=" + this.approverRemark
				+ ", leaveUrl=" + this.leaveUrl + ", emailMessage="
				+ this.emailMessage + ", emailMessageAr=" + this.emailMessageAr
				+ ", leaveIndicator=" + this.leaveIndicator + "]";
	}
	
	

	
	
}
