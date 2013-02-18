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
 * File Name			:	LeaveAppValidatorApprove.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.validator
 * Date of creation		:	Feb 12, 2013  2:30:36 PM
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
package om.edu.squ.squportal.portlet.leaveapp.validator;

import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppValidatorApprove implements Validator
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean supports(Class clazz)
	{
		return LeaveAppModel.class.equals(clazz);
	}

	public void validate(Object obj, Errors err)
	{
		LeaveAppModel	leaveAppModel	=	(LeaveAppModel) obj;
		if(null == leaveAppModel.getApproverRemark() || leaveAppModel.getApproverRemark().trim().equals(""))
		{
			err.rejectValue("approverRemark", "error.prop.leave.app.approverRemark.na");
		}

		if(null == leaveAppModel.getApproverAction() || leaveAppModel.getApproverAction().trim().equals(""))
		{
			err.rejectValue("approverAction", "error.prop.leave.app.approverAction.na");
		}

		
	}
	
}
