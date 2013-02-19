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
 * File Name			:	EmailReturn.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 19, 2013  11:19:47 AM
 * Date of modification :	
 * 
 * Summary				:	Email to requester and approver for returned leave request
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
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bhabesh
 *
 */
public class EmailReturn extends EmailDataAbstract implements EmailLeave
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	EmailData	emailData			= 	null;
	private	Locale		locale				=	null;

	/**
	 * 
	 * Constructor
	 * @param leaveRequest
	 * @param leaveApprove
	 * @param delegatedEmps
	 * @param locale
	 *
	 */
	public EmailReturn(LeaveRequest leaveRequest, LeaveApprove leaveApprove,DelegatedEmp[] delegatedEmps,Locale locale)
	{
		this.emailData	=	setGeneralEmailData(leaveRequest,leaveApprove,delegatedEmps);
		this.locale		=	locale;
	}
	
	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendRequesterEmail()
	 */
	public boolean sendRequesterEmail()
	{
		//TODO replace with requester email id
		//this.emailData.setMailTo(getEmpRequester().getEmpInternetId()+"@squ.edu.om");
		this.emailData.setMailTo("bhabesh@squ.edu.om");
		this.emailData.setEmailReceiverName(getEmpRequester().getEmpName());
		this.emailData.setEmailTemplateName(emailTemplateRequesterPath);	
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.new.requester",null, locale));
		return sendLeaveEmail();
	}


	/* (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave#sendApproverEmail()
	 */
	public boolean sendApproverEmail()
	{
		//TODO replace with approver email id
		//emailData.setMailTo(getEmpApprover().getEmpInternetId()+"@squ.edu.om");
		this.emailData.setMailTo("bhabesh@squ.edu.om");	
		this.emailData.setEmailReceiverName(getEmpApprover().getEmpName());
		this.emailData.setEmailTemplateName(emailTemplateApproverPath);	
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.return.new.approver",null, locale));
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
