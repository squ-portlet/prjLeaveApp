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
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.controller
 * Date of creation		:	Oct 10, 2012  1:19:54 PM
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
package om.edu.squ.squportal.portlet.leaveapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppValidator implements Validator
{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean supports(Class clazz)
	{
		return LeaveAppModel.class.equals(clazz);
	}

	public void validate(Object obj, Errors err)
	{
	     Date currentDate = new Date();
	     SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	     
	     LeaveAppModel	leaveAppModel	=	(LeaveAppModel)obj;
	     DelegatedEmp[]	delegatedEmps	=	leaveAppModel.getDelegatedEmps();
	     
	     //for
		
	}
	
}
