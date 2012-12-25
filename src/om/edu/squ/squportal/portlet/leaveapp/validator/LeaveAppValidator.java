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
 * Summary				:	spring level validation for employee level leave application
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;

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
	     Date currentDate = new Date();
	     SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	     sdfDate.setLenient(false); 
	     
		LeaveAppModel	leaveAppModel	=	(LeaveAppModel) obj;
		DelegatedEmp[]	delegatedEmps	=	leaveAppModel.getDelegatedEmps();
		
		if(null == leaveAppModel.getHod()|| leaveAppModel.getHod().trim().equals(""))
		{
			err.rejectValue("hod", "error.prop.leave.app.approver.na");
		}
		
		if(null == leaveAppModel.getLeaveTypeFlag() || leaveAppModel.getLeaveTypeFlag().trim().equals(""))
		{
			err.rejectValue("leaveTypeFlag", "error.prop.leave.app.leaveType.na");
		}

		if(null == leaveAppModel.getLeaveStartDate() || leaveAppModel.getLeaveStartDate().trim().equals(""))
		{
			err.rejectValue("leaveStartDate", "error.prop.leave.app.leaveStartDate.na");
		}

		if(null == leaveAppModel.getLeaveEndDate() || leaveAppModel.getLeaveEndDate().trim().equals(""))
		{
			err.rejectValue("leaveEndDate", "error.prop.leave.app.leaveEndDate.na");
		}

		
		
		if(null != delegatedEmps && delegatedEmps.length != 0)
		{
			for (int i=0;i<delegatedEmps.length; i++)
			{
				
				
				Date			dtLvStartDate	=	null;										//	Leave start date
				Date			dtLvEndDate		=	null;										//	Leave end date
				
				Date			dtDelgStartDate	=	null;
				Date			dtDelgEndDate	=	null;
				
				DelegatedEmp	delEmps			=	delegatedEmps[i];
				
				
				try
				{
					
					dtLvStartDate	=	sdfDate.parse(leaveAppModel.getLeaveStartDate());
					dtLvEndDate		=	sdfDate.parse(leaveAppModel.getLeaveEndDate());
					
				}
				catch (ParseException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				
				if(
						!(null == delEmps.getEmpNumber() || delEmps.getEmpNumber().trim().equals("")) ||  
						!(null == delEmps.getFromDate() || delEmps.getFromDate().trim().equals(""))	||
						!(null == delEmps.getToDate() || delEmps.getToDate().trim().equals(""))
				  )
				{
					try
					{
						
						dtDelgStartDate	=	sdfDate.parse(delEmps.getFromDate());
						dtDelgEndDate   =	sdfDate.parse(delEmps.getToDate());

						
					}
					catch (ParseException ex)
					{
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
																									//empty emp number not accepted
						if(																		
								null == delEmps.getEmpNumber() || 
								delEmps.getEmpNumber().trim().equals("")
							)
						{
							err.rejectValue("delegatedEmps["+i+"].empNumber", "error.prop.leave.app.delegate.empnumber.na");
							break;
						}
						if(null != dtDelgStartDate || null !=dtDelgEndDate )
						{
							if(dtDelgEndDate.compareTo(dtDelgStartDate) < 0)								// toDate should not be greater
							{
								err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.fromDate.na");
							}
							if(dtLvStartDate.compareTo(dtDelgStartDate)<0)
							{
								err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.Date.not.higher.than.leave.Date");
							}
							if(dtLvEndDate.compareTo(dtDelgEndDate)<0)
							{
								err.rejectValue("delegatedEmps["+i+"].toDate", "error.prop.leave.app.delegate.Date.not.higher.than.leave.Date");
							}
							
						}
						else																			// empty delegation date not accepted
						{
								err.rejectValue("delegatedEmps["+i+"].toDate", "error.prop.leave.app.delegate.fromDate.na");
						}
				}
				else
				{
					long msDiff	=	dtLvEndDate.getTime() - dtLvStartDate.getTime();
						
					if(((msDiff /(1000*60*60*24)+1)) > Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)								// toDate should not be greater
							{
							//error.prop.leave.app.delegate.limit.exceed.na
							err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.limit.exceed.na", 
											new Object[]{String.valueOf(Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)}, "");	
							//err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.fromDate.na");
								break;
							}
				}
					
			}
		}
		
		
		
	}
	
	/**
	 * 
	 * method name  : checkDate
	 * @param inDate
	 * @param dateFormat
	 * @return
	 * LeaveAppValidator
	 * return type  : boolean
	 * 
	 * purpose		: Check date format for validation
	 *
	 * Date    		:	Dec 22, 2012 2:27:20 PM
	 */
	private static boolean checkDate(String inDate, String dateFormat) { 
	    int dateFormatLength = dateFormat.length(); 
	    try { 
	              SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
	              format.setLenient(false); 
	              Date theDate = new Date(); 
	              theDate = format.parse(inDate); 
	              return true; 
	         } 
	    catch(Exception e)  
	      {
	       return false; 
	      }
	 
	  } 
}
