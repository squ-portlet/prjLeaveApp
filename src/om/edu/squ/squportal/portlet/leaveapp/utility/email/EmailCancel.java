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
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.process.MailProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bhabesh
 *
 */
public class EmailCancel extends EmailDataAbstract implements EmailLeave
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	EmailData	emailData			= 	null;
	private	Locale		locale				=	null;
	
	public EmailCancel (LeaveRequest leaveRequest, LeaveApprove leaveApprove,Locale locale)
	{
		this.locale	=	locale;
		this.emailData	=	setGeneralEmailData(leaveRequest, leaveApprove, null);
		this.emailData.setRequestNo(leaveRequest.getRequestNo());
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
		//TODO replace with requester email id
		//this.emailData.setMailTo(getEmpRequester().getEmpInternetId()+"@squ.edu.om");
		this.emailData.setMailTo("bhabesh@squ.edu.om");
		this.emailData.setEmailReceiverName(getEmpRequester().getEmpName());

		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.requester",null, locale));
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
		//TODO replace with approver email id
		//emailData.setMailTo(getEmpApprover().getEmpInternetId()+"@squ.edu.om");
		this.emailData.setMailTo("bhabesh@squ.edu.om");	
		this.emailData.setEmailReceiverName(getEmpApprover().getEmpName());
		this.emailData.setEmailMessage(UtilProperty.getMessage("prop.leave.app.email.template.msg.cancel.approver",null, locale));
		return sendLeaveEmail();
	}
	
	/**
	 * 
	 */
	public boolean sendDelegateEmail(String mailTo, String delegateName)
	{
		return false;
	}
	
}
