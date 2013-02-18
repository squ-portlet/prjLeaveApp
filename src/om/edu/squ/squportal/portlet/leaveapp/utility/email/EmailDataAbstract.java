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
 * File Name			:	EmailDataAbstract.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 18, 2013  12:41:08 PM
 * Date of modification :	
 * 
 * Summary				:	Super class for Email Data
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

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.process.MailProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bhabesh
 *
 */
public class EmailDataAbstract
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	EmailData		emailData			= 	new EmailData();
	private String			emailTemplatePath	=	Constants.TEMPL_EMAIL_DIR_LEAVE+Constants.TEMPL_LEAVE_APP;
	private	Employee 		empRequester		=	null;
	private	Employee		empApprover			=	null;
	private	DelegatedEmp[] 	delegatedEmps		=	null;
	/**
	 * 
	 * method name  : setGenaralEmailData
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Feb 18, 2013 8:54:44 AM
	 */
	protected EmailData setGeneralEmailData(LeaveRequest leaveRequest, LeaveApprove leaveApprove,DelegatedEmp[] delegatedEmps)
	{
		String tmpDelgStr	=	"----------------------------------------------------------------------------<br>";
		
		this.empRequester				=	leaveRequest.getEmployee();
		this.empApprover				=	leaveApprove.getEmployee();
		this.delegatedEmps				=	delegatedEmps;
		
		
		this.emailData.setRequesterName(empRequester.getEmpName());
		this.emailData.setRequesterEmail(empRequester.getEmpInternetId()+"@squ.edu.om");	
		this.emailData.setRequestStartDate(leaveRequest.getLeaveStartDate());
		this.emailData.setRequestEndDate(leaveRequest.getLeaveEndDate());
		if(null != leaveRequest.getLeaveRequestRemarks())
		{
			this.emailData.setRequesterRemark(leaveRequest.getLeaveRequestRemarks());
		}
		else
		{
			this.emailData.setRequesterRemark("");
		}
		this.emailData.setApproverName(empApprover.getEmpName());
		this.emailData.setApproverEmail(empApprover.getEmpInternetId()+"@squ.edu.om");	
		this.emailData.setEmailTemplateName(emailTemplatePath);	
		this.emailData.setLeaveUrl(Constants.LEAVE_URL);
		
		if(null != delegatedEmps)
		{
			this.emailData.setDelegationAvilable(Constants.CONST_DELEGATION_AVL);
			for(DelegatedEmp delEmp: delegatedEmps)
			{
				if(!(null == delEmp.getEmpNumber() || delEmp.getEmpNumber().trim().equals("")) && 
						!(null == delEmp.getFromDate() || delEmp.getFromDate().trim().equals("")) &&
						!(null == delEmp.getToDate() || delEmp.getToDate().trim().equals(""))
				)
					{
					tmpDelgStr = tmpDelgStr + 
					delEmp.getEmpName() + " - " + 
		 			" ("+Constants.CONST_DELEGATION_START_DATE+ delEmp.getFromDate() + 
		 			" -- "+ Constants.CONST_DELEGATION_END_DATE+ delEmp.getToDate() + " ) " + "<br>";
					}
				else
				{
					this.emailData.setDelegationAvilable(" ");
					this.emailData.setDelegationDetails(" ");
				}
			}
			
			this.emailData.setDelegationDetails(tmpDelgStr);
		}
		else
		{
			this.emailData.setDelegationAvilable(" ");
			this.emailData.setDelegationDetails(" ");
		}
		
		return emailData;
	}
	/**
	 * Getter Method	: getEmpRequester
	 * @return the empRequester
	 * 
	 * Date				: Feb 18, 2013
	 */
	public Employee getEmpRequester()
	{
		return this.empRequester;
	}
	/**
	 * Getter Method	: getEmpApprover
	 * @return the empApprover
	 * 
	 * Date				: Feb 18, 2013
	 */
	public Employee getEmpApprover()
	{
		return this.empApprover;
	}
	
	/**
	 * 
	 * method name  : sendLeaveEmail
	 * @return
	 * EmailDataAbstract
	 * return type  : boolean
	 * 
	 * purpose		: sending mail for new leave request
	 *
	 * Date    		:	Feb 18, 2013 1:17:18 PM
	 */
	@Transactional("trLeaveReq")
	protected boolean sendLeaveEmail()
	{
		boolean	result	=	false;
		try
		{
			result = new MailProcess().setLeaveEmail(emailData);
		}
		catch(Exception ex)
		{
			logger.error("Error in sending e-mail. Detail error : "+ex);
		}
		return result;
	}
	
	
}
