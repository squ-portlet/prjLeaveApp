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
 * File Name			:	EmailGeneral.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 17, 2013  8:46:01 AM
 * Date of modification :	
 * 
 * Summary				:	Email to Requester & Approver for new and update leave request
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
package om.edu.squ.squportal.portlet.leaveapp.utility.email;

import java.util.Locale;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bhabesh
 *
 */
public class EmailGeneral extends EmailDataAbstract implements EmailLeave
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	EmailData		emailData			= 	null;
	private	Locale 			locale				=	null;
	private	boolean			isNewEmail			=	false;
	private	boolean			isSabbatical		=	false;
	

	/**
	 * 
	 * Constructor
	 * @param leaveRequestNo
	 * @param leaveRequest
	 * @param leaveApprove
	 * @param delegatedEmps
	 * @param locale
	 * 
	 * purpose : Constructor for new leave
	 *
	 */
	public EmailGeneral
						(
								String leaveRequestNo, LeaveRequest leaveRequest, 
								LeaveApprove leaveApprove,DelegatedEmp[] delegatedEmps, 
								EmailService emailService,Locale locale
						)
	{
		this.emailData	=	setGeneralEmailData(leaveRequest,leaveApprove,delegatedEmps,emailService,locale);
		this.isNewEmail	=	true;
		this.locale		=	locale;
		this.emailData.setRequestNo(leaveRequestNo);
		
	}
	
	/**
	 * 
	 * Constructor
	 * @param leaveRequest
	 * @param leaveApprove
	 * @param delegatedEmps
	 * @param locale
	 * 
	 * purpose : Constructor for updated leave
	 *
	 */
	public EmailGeneral
						(
								LeaveRequest leaveRequest, LeaveApprove leaveApprove,
								DelegatedEmp[] delegatedEmps, EmailService emailService, 
								Locale locale
						)
	{
		emailData	=	setGeneralEmailData(leaveRequest,leaveApprove,delegatedEmps,emailService,locale);
		this.isNewEmail	=	false;
		this.locale		=	locale;
		//this.emailData.setRequestNo(leaveRequest.getRequestNo());
		
	}
	
	/**
	 * 
	 * Constructor
	 * @param SabbaticalRequired
	 * @param leaveRequestNo
	 * @param leaveRequest
	 * @param leaveApprove
	 * @param delegatedEmps
	 * @param emailService
	 * @param locale
	 *
	 */
	public EmailGeneral
	(
			boolean SabbaticalRequired, LeaveRequest leaveRequest, 
			LeaveApprove leaveApprove,DelegatedEmp[] delegatedEmps, 
			EmailService emailService,Locale locale
	)
	{
		this.emailData	=	setGeneralEmailData(leaveRequest,leaveApprove,delegatedEmps,emailService,locale);
		this.isNewEmail	=	false;
		isSabbatical	=	SabbaticalRequired;
		this.locale		=	locale;
	}
	
	
	/**
	 * 
	 * method name  : setRequester
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: email  for requester
	 *
	 * Date    		:	Feb 17, 2013 1:00:19 PM
	 */
	public boolean sendRequesterEmail()
	{
		if(null != Constants.CONST_TEMP_EMAIL_TO)
		{
			this.emailData.setMailTo(Constants.CONST_TEMP_EMAIL_TO);
		}
		else
		{
			this.emailData.setMailTo(getEmpRequester().getEmpInternetId()+"@squ.edu.om");
		}
		this.emailData.setEmailReceiverNameEn(getEmpRequester().getEmpNameEn());
		this.emailData.setEmailReceiverNameAr(getEmpRequester().getEmpNameAr());
		this.emailData.setEmailTemplateName(emailTemplateRequesterPath);	
		if(isNewEmail)
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.requester",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.requester",null, ARABIC));
		}
		else
		{
			if(isSabbatical)
			{
				this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.requester.sabbatical",null));
				this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.requester.sabbatical",null, ARABIC));
			}
				
			else
			{
				this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.update.requester",null));
				this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.update.requester",null, ARABIC));
			}
		}
		return sendLeaveEmail();
	}
	
	/**
	 * 
	 * method name  : setApprover
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: email for approver
	 *
	 * Date    		:	Feb 17, 2013 1:05:25 PM
	 */
	public boolean sendApproverEmail()
	{	
		if(null != Constants.CONST_TEMP_EMAIL_TO)
		{
			this.emailData.setMailTo(Constants.CONST_TEMP_EMAIL_TO);
		}
		else
		{
			emailData.setMailTo(getEmpApprover().getEmpInternetId()+"@squ.edu.om");
		}
		this.emailData.setEmailReceiverNameEn(getEmpApprover().getEmpNameEn());
		this.emailData.setEmailReceiverNameAr(getEmpApprover().getEmpNameAr());
		this.emailData.setEmailTemplateName(emailTemplateApproverPath);	
		if(isNewEmail)
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.approver",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.approver",null, ARABIC));
		}
		else 
		{
			if(isSabbatical)
			{
				this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.approver.sabbatical",null));
				this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.approver.sabbatical",null, ARABIC));
			}
			else
			{
				this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.update.approver",null));
				this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.update.approver",null, ARABIC));
			}
		}
		return sendLeaveEmail();
	}

	/**
	 * 
	 * method name  : setDelegate
	 * @param mailTo
	 * @param delegateName
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: email for delegate
	 *
	 * Date    		:	Feb 17, 2013 3:04:06 PM
	 */
	public boolean sendDelegateEmail(String mailTo, String delegateName)
	{
		if(null != Constants.CONST_TEMP_EMAIL_TO)
		{
			this.emailData.setMailTo(Constants.CONST_TEMP_EMAIL_TO);
		}
		else
		{
			this.emailData.setMailTo(mailTo);
		}
		this.emailData.setEmailReceiverNameEn(delegateName);
		this.emailData.setEmailReceiverNameAr(delegateName);
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.delegation",null));
		this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.new.delegation",null, ARABIC));
		return sendLeaveEmail();
	}

	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendEmail(boolean, boolean, boolean)
	 */
	public boolean sendEmail
			(
					boolean requester, 
					boolean approver
			)
	{
		boolean result;
		result = (requester)? sendRequesterEmail():false;
		result = (approver)? sendApproverEmail():false;
		return result;
	}
}
