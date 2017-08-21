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
 * File Name			:	LeaveAppReturnValidator.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.validator
 * Date of creation		:	Aug 20, 2017  3:29:43 PM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2017 the original author or authors and Organization.
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
public class LeaveAppReturnValidator implements Validator
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean supports(Class<?> clazz) {
		return LeaveAppModel.class.equals(clazz);
	}

	public void validate(Object obj, Errors err) {
		
		String 			approverEmpNum	=	null;
		
		LeaveAppModel	leaveAppModel	=	(LeaveAppModel) obj;
		
		if((null== leaveAppModel.getHod()) && null != leaveAppModel.getApproverEmpNumber())
		{
			approverEmpNum = leaveAppModel.getApproverEmpNumber();
		}
		else if ((null != leaveAppModel.getHod()) && null != leaveAppModel.getApproverEmpNumber())
		{
			approverEmpNum = leaveAppModel.getHod();
		}
		
		logger.info("approverEmpNum : "+approverEmpNum);
		logger.info("LeaveAppModel : "+leaveAppModel.toString());
		
		if(approverEmpNum.trim().equals(leaveAppModel.getEmployeeNumber().trim()))
		{
			err.reject("warn.prop.leave.self.approve.not.allowed");
		}
		
		
	}

}
