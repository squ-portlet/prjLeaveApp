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
 * File Name			:	LeaveAppValidator.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.validator
 * Date of creation		:	Dec 8, 2012  9:41:28 AM
 * Date of modification :	
 * 
 * Summary				:	
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
package om.edu.squ.squportal.portlet.leaveapp.validator;

import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppValidator implements Validator
{

	public boolean supports(Class clazz)
	{
		return LeaveAppModel.class.equals(clazz);
	}

	public void validate(Object obj, Errors err)
	{
		LeaveAppModel	leaveAppModel	=	(LeaveAppModel) obj;
		
		if(null == leaveAppModel.getHod()|| leaveAppModel.getHod().trim().equals(""))
		{
			err.rejectValue("hod", "error.prop.leave.app.approver.na");
		}
		
	}
	
}
