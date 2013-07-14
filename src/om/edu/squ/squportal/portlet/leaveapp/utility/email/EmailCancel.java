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
 * File Name			:	EmailCancel.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 18, 2013  12:36:34 PM
 * Date of modification :	
 * 
 * Summary				:	Sending Email for cancel leave request
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
public class EmailCancel extends EmailDataAbstract implements EmailLeave
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	EmailData	emailData			= 	null;
	private	Locale		locale				=	null;
	
	public EmailCancel 
						(
								LeaveRequest leaveRequest, LeaveApprove leaveApprove, 
								EmailService	emailService,Locale locale)
	{
		this.locale	=	locale;
		this.emailData	=	setGeneralEmailData(leaveRequest, leaveApprove, null,emailService,locale);
		//this.emailData.setRequestNo(leaveRequest.getRequestNo());
	}
	
	/**
	 * 
	 * method name  : sendRequesterEmail
	 * @return
	 * EmailCancel
	 * return type  : boolean
	 * 
	 * purpose		: email  for requester
	 *
	 * Date    		:	Feb 18, 2013 1:09:27 PM
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
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.requester",null));
		this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.requester",null,ARABIC));
		return sendLeaveEmail();
	}
	
	/**
	 * 
	 * method name  : sendApproverEmail
	 * @return
	 * EmailCancel
	 * return type  : boolean
	 * 
	 * purpose		: email for approver
	 *
	 * Date    		:	Feb 18, 2013 1:10:33 PM
	 */
	public boolean sendApproverEmail()
	{	
		if(null != Constants.CONST_TEMP_EMAIL_TO)
		{
			this.emailData.setMailTo(Constants.CONST_TEMP_EMAIL_TO);
		}
		else
		{
			this.emailData.setMailTo(getEmpApprover().getEmpInternetId()+"@squ.edu.om");
		}
		this.emailData.setEmailReceiverNameEn(getEmpApprover().getEmpNameEn());
		this.emailData.setEmailReceiverNameAr(getEmpApprover().getEmpNameAr());
		this.emailData.setEmailTemplateName(emailTemplateApproverPath);	
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.approver",null));
		this.emailData.setEmailMessageAr(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.approver",null, ARABIC));
		return sendLeaveEmail();
	}
	
	/**
	 * 
	 */
	public boolean sendDelegateEmail(String mailTo, String delegateName)
	{
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
