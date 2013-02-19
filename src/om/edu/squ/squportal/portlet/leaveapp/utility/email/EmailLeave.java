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
 * File Name			:	EmailLeave.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility.email
 * Date of creation		:	Feb 17, 2013  1:10:28 PM
 * Date of modification :	
 * 
 * Summary				:	
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

/**
 * @author Bhabesh
 *
 */
public interface EmailLeave
{
	/**
	 * 
	 * method name  : setRequester
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: set email data for requester
	 *
	 * Date    		:	Feb 17, 2013 1:00:19 PM
	 * @return TODO
	 */
	public boolean sendRequesterEmail();
	/**
	 * 
	 * method name  : setApprover
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: set email data for approver
	 *
	 * Date    		:	Feb 17, 2013 1:05:25 PM
	 * @return TODO
	 */
	public boolean sendApproverEmail();
	/**
	 * 
	 * method name  : setDelegate
	 * @param mailTo
	 * @param delegateName
	 * EmailGeneral
	 * return type  : void
	 * 
	 * purpose		: set email data for delegate
	 *
	 * Date    		:	Feb 17, 2013 3:04:06 PM
	 * @return TODO
	 */
	public boolean sendDelegateEmail(String mailTo, String delegateName);
	
	/**
	 * 
	 * method name  : sendEmail
	 * @param requester
	 * @param approver
	 * @param delegate
	 * @return
	 * EmailLeave
	 * return type  : boolean
	 * 
	 * purpose		: calling related methods to send emails
	 *
	 * Date    		:	Feb 19, 2013 12:35:06 PM
	 */
	public boolean sendEmail(boolean requester, boolean approver);

}
