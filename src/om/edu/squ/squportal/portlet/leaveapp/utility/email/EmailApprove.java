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
 * File Name			:	EmailApprove.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 19, 2013  10:55:25 AM
 * Date of modification :	
 * 
 * Summary				:	Email to requester and approver for approved leave request
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;

/**
 * @author Bhabesh
 *
 */

public class EmailApprove extends EmailDataAbstract implements EmailLeave
{
	private final 	Logger 		logger 		= LoggerFactory.getLogger(this.getClass());
	private			EmailData	emailData	= 	null;
	private			Locale 		locale		=	null;

	/**
	 * 
	 * Constructor
	 * @param leaveRequest
	 * @param leaveApprove
	 * @param delegatedEmps
	 * @param locale
	 *
	 */
	public EmailApprove
					(
							LeaveRequest leaveRequest, LeaveApprove leaveApprove,
							DelegatedEmp[] delegatedEmps, EmailService emailService,  
							Locale locale
					)
	{
		this.emailData	=	setGeneralEmailData(leaveRequest,leaveApprove,delegatedEmps,emailService,locale);
		this.locale		=	locale;
	}
	

	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendRequesterEmail()
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
		if(this.emailData.getLeaveIndicator().equals(Constants.CONST_LEAVE_RETURN_INDICATOR_RETURN))
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.leave.return.approve.requester",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.leave.return.approve.requester",null, ARABIC));
		}
		else
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.approve.requester",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.approve.requester",null, ARABIC));
		}

		return sendLeaveEmail();
	}

	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendApproverEmail()
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
		if(this.emailData.getLeaveIndicator().equals(Constants.CONST_LEAVE_RETURN_INDICATOR_RETURN))
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.leave.return.approve.approver",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.leave.return.approve.approver",null,ARABIC));
		}
		else
		{
			this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.approve.approver",null));
			this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.approve.approver",null,ARABIC));
		}
		
		
		return sendLeaveEmail();
	}

	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendDelegateEmail(java.lang.String, java.lang.String)
	 */
	public boolean sendDelegateEmail(String mailTo, String delegateName)
	{
		// TODO Auto-generated method stub
		return false;
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
