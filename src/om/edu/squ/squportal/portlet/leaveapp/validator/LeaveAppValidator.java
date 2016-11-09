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
		Date 			currentDate 	= 	new Date();
		Date			dtLvStartDate	=	null;										//	Leave start date
		Date			dtLvEndDate		=	null;										//	Leave end date

	     SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	     sdfDate.setLenient(false); 
	     
		LeaveAppModel	leaveAppModel	=	(LeaveAppModel) obj;
		DelegatedEmp[]	delegatedEmps	=	leaveAppModel.getDelegatedEmps();
		
		/*Forcing user to select their preferred approver -- Don't Remove these entries, although commented, might be in need at future*/
//		if(null == leaveAppModel.getHod()|| leaveAppModel.getHod().trim().equals(""))
//		{
//			err.rejectValue("hod", "error.prop.leave.app.approver.na");
//		}
		
		/* generte error if leave not accepted - checked by db store procedure*/
		if(!leaveAppModel.isAcceptLeave())
		{
			err.reject("acceptLeave", leaveAppModel.getMsgLeaveRequest());
			
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

		
		try
		{
			if((null != leaveAppModel.getLeaveStartDate() && ! leaveAppModel.getLeaveStartDate().trim().equals("")) 
					&& (null != leaveAppModel.getLeaveEndDate() && ! leaveAppModel.getLeaveEndDate().trim().equals("")) )
			{
				dtLvStartDate		=	sdfDate.parse(leaveAppModel.getLeaveStartDate());
				dtLvEndDate			=	sdfDate.parse(leaveAppModel.getLeaveEndDate());

				long	msDiffLvDate	=	dtLvEndDate.getTime() - dtLvStartDate.getTime();
				long	constTimeMilli	=	1000*60*60*24;	
				long	lvDateNos		=	(msDiffLvDate/constTimeMilli)+1;						// total number of leaves
				long	delgDateNos		=	0;														// total number of delegation dates 
				long	empLeaveBal		=	leaveAppModel.getLeaveBalance();						// employee leave balance
				
				if(leaveAppModel.getLeaveTypeFlag().equals("A") && lvDateNos>empLeaveBal)
				{
					err.rejectValue("leaveEndDate", "error.prop.leave.app.annual.leave.exceed");
				}
				
				if(msDiffLvDate <0)
				{
					err.rejectValue("leaveStartDate", "error.prop.leave.app.date.validation.incorrect.range");
				}
				
				if(null != delegatedEmps && delegatedEmps.length != 0)
				{
					for (int i=0;i<delegatedEmps.length; i++)
					{
						Date			dtDelgStartDate	=	null;
						Date			dtDelgEndDate	=	null;
						
						DelegatedEmp	delEmps			=	delegatedEmps[i];
						
						
						
						if(
								!(null == delEmps.getEmpNumber() || delEmps.getEmpNumber().trim().equals("")) ||  
								!(null == delEmps.getFromDate() || delEmps.getFromDate().trim().equals(""))	||
								!(null == delEmps.getToDate() || delEmps.getToDate().trim().equals(""))
						  )
						{
								if ((null == delEmps.getFromDate()) || delEmps.getFromDate().trim().equals("") )
								{
									err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.fromDate.na");
									break;
								}
								else
								{
									dtDelgStartDate	=	sdfDate.parse(delEmps.getFromDate());
								}
								if ((null == delEmps.getToDate()) || delEmps.getToDate().trim().equals("") )
								{
									err.rejectValue("delegatedEmps["+i+"].toDate", "error.prop.leave.app.delegate.fromDate.na");
									break;
								}
								else
								{
									dtDelgEndDate   =	sdfDate.parse(delEmps.getToDate());
								}
								
								long	msDiffDelgDate = dtDelgEndDate.getTime() - dtDelgStartDate.getTime();
								long	delgDateNo	=	(msDiffDelgDate/constTimeMilli) +1;
										delgDateNos	=	delgDateNos	+	delgDateNo;
										if(lvDateNos == delgDateNos)
										{
											break;
										}
								
								
								if(																		//empty emp number not accepted
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
										break;
									}
									if(dtDelgStartDate.compareTo(dtLvStartDate)<0)
									{
										err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.Date.not.higher.than.leave.Date");
										break;
									}
									if(dtLvEndDate.compareTo(dtDelgEndDate)<0)
									{
										err.rejectValue("delegatedEmps["+i+"].toDate", "error.prop.leave.app.delegate.Date.not.higher.than.leave.Date");
										break;
									}
									
									
								}
								else																			// empty delegation date not accepted
								{
										err.rejectValue("delegatedEmps["+i+"].toDate", "error.prop.leave.app.delegate.fromDate.na");
								}
								
								/**
								 * this following block of code ensures the validation on overlapping of delegation dates
								 */
								if( 

									(i+1) < delegatedEmps.length 
								  )
								{
									try
									{
										DelegatedEmp	delEmps1			=	delegatedEmps[i];
										DelegatedEmp	delEmps2			=	delegatedEmps[i+1];
										if((
												null != delEmps1.getToDate() || delEmps1.getToDate().trim().equals(""))  && 
												null != delEmps2.getFromDate() || delEmps2.getFromDate().trim().equals("") )
										{
										
											
												Date			dtDelgEndDate1		=	sdfDate.parse(delEmps1.getToDate());
												Date			dtDelgStartDate2 	=	sdfDate.parse(delEmps2.getFromDate());
												
												if(dtDelgStartDate2.compareTo(dtDelgEndDate1)<0)
												{
													err.rejectValue("delegatedEmps["+(i+1)+"].fromDate", "error.prop.leave.app.delegate.date.overlap");
												}
										}
									}
									catch(ArrayIndexOutOfBoundsException exArrEx)
									{
										//TODO for i+1
									}
									catch(ParseException exPars)
									{
										//TODO for i+1
									}
								}// end if
								
								
								
//								if(((msDiffLvDate /(constTimeMilli)+1)) > Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)								// delegation requirement
//										{																									// for more than 5 days
//										err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.limit.exceed.na", 
//														new Object[]{String.valueOf(Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)}, "");	
//											break;
//										}
								
								
						}
//						else
//						{
//							if(((msDiffLvDate /(constTimeMilli)+1)) > Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)								// delegation requirement
//									{																									// for more than 5 days
//									err.rejectValue("delegatedEmps["+i+"].fromDate", "error.prop.leave.app.delegate.limit.exceed.na", 
//													new Object[]{String.valueOf(Constants.CONST_DELEGATION_REQ_LEAVE_DAYS)}, "");	
//										break;
//									}
//						}
						

						
							
					} // End of for loop
					if((lvDateNos  >  Constants.CONST_DELEGATION_REQ_LEAVE_DAYS) && lvDateNos != delgDateNos)
					{
						err.rejectValue("leaveStartDate", "error.prop.leave.app.delegate.date.not.equal");
					}
					
				}
			
			}	// end if
		}
		catch (ParseException ex)
		{
			logger.warn("date parsing issue. Detail : "+ex.getMessage());
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
