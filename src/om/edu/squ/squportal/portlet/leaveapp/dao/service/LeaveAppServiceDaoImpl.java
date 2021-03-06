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
 * File Name			:	LeaveAppServiceDaoImpl.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.dao.service
 * Date of creation		:	Aug 14, 2012  9:29:57 AM
 * Date of modification :	
 * 
 * Summary				:	Leave application service implementations
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
package om.edu.squ.squportal.portlet.leaveapp.dao.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.Budget;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Sabbatical;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailApprove;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailCancel;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailGeneral;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailReject;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailReturn;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppServiceDaoImpl implements LeaveAppServiceDao
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private LeaveDbDao		leaveDbDao;
	private	EmailService	emailService;

	/**
	 * Setter method : setLeaveDbDao
	 * @param leaveDbDao the leaveDbDao to set
	 * 
	 * Date          : Aug 25, 2012 11:37:50 AM
	 */
	public void setLeaveDbDao(LeaveDbDao leaveDbDao)
	{
		this.leaveDbDao = leaveDbDao;
	}
	
	/**
	 * Setter method : setEmailService
	 * @param emailService the emailService to set
	 * 
	 * Date          : Mar 5, 2013 11:59:16 AM
	 */
	public void setEmailService(EmailService emailService)
	{
		this.emailService = emailService;
	}



	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Sep 25, 2012 1:09:18 PM
	 */
	public List<LeaveType>	getLeaveTypes(Employee employee, Locale locale)
	{
		return leaveDbDao.getLeaveTypes(employee, locale);
	}
	
	/**
	 * 
	 * method name  : getAdminActions
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<AdminAction>
	 * 
	 * purpose		: Admin actions
	 *
	 * Date    		:	Aug 26, 2012 8:22:38 AM
	 */
	public List<AdminAction>	getAdminActions(Locale locale)
	{
		return leaveDbDao.getAdminActions(locale);
	}
	
	/**
	 * 
	 * method name  : getAdminActionsReturned
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<AdminAction>
	 * 
	 * purpose		: Admin Actions for leave return
	 *
	 * Date    		:	Aug 6, 2017 2:12:45 PM
	 */
	public List<AdminAction>	getAdminActionsReturned(Locale locale)
	{
		return leaveDbDao.getAdminActionsReturned(locale);
	}
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: get employee record
	 *
	 * Date    		:	Aug 27, 2012 9:15:44 AM
	 */
	public Employee	getEmployee(String empNumber, String empInternetId, Locale locale)
	{
		Employee	employee	=	leaveDbDao.getEmployee(empNumber, locale);
		employee.setEmpNumber(empNumber);
		employee.setEmpInternetId(empInternetId);
		//leaveDbDao.getEmployee(empNumber, locale)
		return employee;
	}
	
	/**
	 * 
	 * method name  : getAdditionalDesignation
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<Designation>
	 * 
	 * purpose		: Additional designations (might be holding more than one designation at a time)
	 *
	 * Date    		:	Sep 17, 2012 12:04:29 PM
	 */
	public List<Designation>	getAdditionalDesignation(String empNumber, Locale locale)
	{
		return leaveDbDao.getAdditionalDesignation(empNumber, locale);
	}
	
	/**
	 * 
	 * method name  : getAllowEleaveRequest
	 * @param requestNo
	 * @param leaveAppModel
	 * @param employee
	 * @return
	 * @throws ParseException
	 * LeaveAppServiceDaoImpl
	 * return type  : AllowEleaveRequestProc
	 * 
	 * purpose		: Check before insert leave request
	 *
	 * Date    		:	Nov 9, 2016 11:49:06 AM
	 */
	public AllowEleaveRequestProc getAllowEleaveRequest( String requestNo, LeaveAppModel 	leaveAppModel, Employee	employee, Locale locale) throws ParseException
	{
		LeaveRequest 			leaveRequest 						=	new LeaveRequest();
		LeaveType				leaveTypeFlag						=	new LeaveType();
		AllowEleaveRequestProc	allowEleaveRequestProc				=	null;
		
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		employee.setAdmin(leaveAppModel.isAdminSqu());
		employee.setDesignationAddlCode(leaveAppModel.getPositionAdditional());

		employee.setBranch2Code(leaveAppModel.getBranch2());
		employee.setDepartment2code(leaveAppModel.getDepartment2());
		employee.setMyHodId(leaveAppModel.getHod());
		leaveTypeFlag.setTypeNo(leaveAppModel.getLeaveTypeFlag());
		
		leaveRequest.setRequestNo(requestNo);
		leaveRequest.setLeaveTypeFlag(leaveTypeFlag);
		leaveRequest.setEmployee(employee);
		leaveRequest.setLeaveStartDate(leaveAppModel.getLeaveStartDate());
		leaveRequest.setLeaveEndDate(leaveAppModel.getLeaveEndDate());
		leaveRequest.setLeaveRequestRemarks(leaveAppModel.getLeaveRemarks());
		leaveRequest.setLeaveStatus(Constants.CONST_LEAVE_STATUS_WAITING_APPV);
		leaveRequest.setResearchId(leaveAppModel.getResearchId());
		
		allowEleaveRequestProc	=	leaveDbDao.getAllowEleaveRequest(leaveRequest, locale);
		
		return allowEleaveRequestProc;
	}
	
	
	/**
	 * 
	 * method name  : getAllowEleaveRequest
	 * @param requestNo 
	 * @param leaveTypeNo
	 * @param leaveAppModel
	 * @param locale
	 * @param employeeNo
	 * @return
	 * @throws ParseException
	 * LeaveAppServiceDaoImpl
	 * return type  : AllowEleaveRequestProc
	 * 
	 * purpose		: Check before insert leave request
	 *
	 * Date    		:	Sep 29, 2012 11:04:01 AM
	 */
	public synchronized AllowEleaveRequestProc	getAllowEleaveRequest(
														String requestNo,
														String leaveTypeNo, 
														LeaveAppModel 	leaveAppModel, 
														Employee	employee, Locale locale
														) 
	throws ParseException
	{

		LeaveRequest 			leaveRequest 						=	new LeaveRequest();
		LeaveType				leaveType							=	new LeaveType();
		LeaveType				leaveTypeFlag						=	new LeaveType();
		AllowEleaveRequestProc	allowEleaveRequestProc				=	null;
		
		
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		employee.setAdmin(leaveAppModel.isAdminSqu());
		employee.setDesignationAddlCode(leaveAppModel.getPositionAdditional());

		employee.setBranch2Code(leaveAppModel.getBranch2());
		employee.setDepartment2code(leaveAppModel.getDepartment2());
		employee.setMyHodId(leaveAppModel.getHod());
		leaveTypeFlag.setTypeNo(leaveAppModel.getLeaveTypeFlag());
		
		leaveRequest.setRequestNo(requestNo);
		leaveRequest.setLeaveTypeFlag(leaveTypeFlag);
		leaveRequest.setEmployee(employee);
		leaveRequest.setLeaveStartDate(leaveAppModel.getLeaveStartDate());
		leaveRequest.setLeaveEndDate(leaveAppModel.getLeaveEndDate());
		leaveRequest.setLeaveRequestRemarks(leaveAppModel.getLeaveRemarks());
		leaveRequest.setLeaveStatus(Constants.CONST_LEAVE_STATUS_WAITING_APPV);
		leaveRequest.setResearchId(leaveAppModel.getResearchId());
		
			allowEleaveRequestProc	=	leaveDbDao.getAllowEleaveRequest(leaveRequest, locale);
		
		if(allowEleaveRequestProc.isAcceptLeave())
		{
			employee.setMyHodId(allowEleaveRequestProc.getCheckedAprroverEmpCode());
			leaveRequest.setEmployee(employee);
			leaveType.setTypeNo(allowEleaveRequestProc.getLeaveCode());
			leaveRequest.setLeaveType(leaveType);
			if(null != leaveAppModel.getDelegatedEmps())
			{
				DelegatedEmp[] delegatedEmps	=	leaveAppModel.getDelegatedEmps();
				for(DelegatedEmp delEmp: delegatedEmps)
				{
					if(!(null == delEmp.getEmpNumber() || delEmp.getEmpNumber().trim().equals("")) && 
							!(null == delEmp.getFromDate() || delEmp.getFromDate().trim().equals("")) &&
							!(null == delEmp.getToDate() || delEmp.getToDate().trim().equals(""))
					)
						{
							delEmp.setEmpName(leaveDbDao.getEmployee(delEmp.getEmpNumber(), locale).getEmpName());
						}
				}
				leaveAppModel.setDelegatedEmps(delegatedEmps);
			}
			if ((null != leaveAppModel.getApproverEmpNumber()) || (! leaveAppModel.getApproverEmpNumber().trim().equals("")))
			{
				leaveRequest.setApproverId(leaveAppModel.getApproverEmpNumber());
			}
			leaveRequest.setProcessSalaray(leaveAppModel.getProcessSalaray());
			
			int result = leaveDbDao.setNewLeaveRequest(leaveRequest,leaveAppModel.getDelegatedEmps(), locale);
				if (result == 0)
				{
					allowEleaveRequestProc.setLeaveMessage(UtilProperty.getMessage("error.prop.leave.app.update.blocked", null, locale));
				}
			
		}
		
		return allowEleaveRequestProc;
	}
	
	/**
	 * 
	 * method name  : getManager
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: Get Manager
	 *
	 * Date    		:	Jan 27, 2013 12:34:48 PM
	 */
	public synchronized Employee getManager(String empNumber, Locale locale)
	{
		return leaveDbDao.getManager(empNumber, locale);
	}
	
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Get list of leave requests
	 *
	 * Date    		:	Sep 12, 2012 12:40:42 PM
	 */
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale, String userType)
	{
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		
		return leaveDbDao.getLeaveRequests(employee, locale, userType);
	}
	

	
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param empNumber
	 * @param reqNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Sep 18, 2012 2:29:13 PM
	 */
	public synchronized LeaveRequest	getLeaveRequest(String empNumber, String reqNo, Locale locale)
	{
	    SimpleDateFormat sdfDate 		= 	new SimpleDateFormat("dd/MM/yyyy");
		Date			dtLvStartDate	=	null;										//	Leave start date
		Date			dtLvEndDate		=	null;	
		LeaveRequest	leaveRequest	=	leaveDbDao.getLeaveRequest(empNumber, reqNo, locale);
		Employee		empLeaveReq		=	leaveRequest.getEmployee();
		Employee		employee		=	leaveDbDao.getEmployee(empLeaveReq.getEmpNumber(), locale);
		sdfDate.setLenient(false); 
		
		try
		{
			dtLvStartDate			=	sdfDate.parse(leaveRequest.getLeaveStartDate());
			dtLvEndDate				=	sdfDate.parse(leaveRequest.getLeaveEndDate());
			long	msDiffLvTime 	=	dtLvEndDate.getTime() - dtLvStartDate.getTime();
			long	constTimeMilli	=	1000*60*60*24;	
			long	lvDateNos		=	(msDiffLvTime/constTimeMilli)+1;
			leaveRequest.setLeaveDateDuration(lvDateNos);
		}
		catch (ParseException ex)
		{
			logger.error("Date parsing error. Details : "+ex.getMessage());
		}
		
		employee.setAdmin2(empLeaveReq.isAdmin2());
		employee.setDepartment2code(empLeaveReq.getDepartment2code());
		employee.setDepartment2(empLeaveReq.getDepartment2());
		employee.setDesignationAddlCode(empLeaveReq.getDesignationAddlCode());
		employee.setDesignationAddl(empLeaveReq.getDesignationAddl());
		
		leaveRequest.setEmployee(employee);
		
		return leaveRequest;
		
	}
	
	/**
	 * 
	 * method name  : getLeaveRequestHistory
	 * @param requestNo
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Leave request history
	 *
	 * Date    		:	Jan 14, 2013 2:11:38 PM
	 */
	public List<LeaveRequest>	getLeaveRequestHistory(String requestNo, Locale locale)
	{
		return this.leaveDbDao.getLeaveRequestHistory(requestNo, locale);
	}
	
	/**
	 * 
	 * method name  : getLeaveApproveHistory
	 * @param requestNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<LeaveApprove>
	 * 
	 * purpose		: Get approver's history
	 *
	 * Date    		:	Jan 13, 2013 1:01:30 PM
	 */
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, Locale locale)
	{
		return leaveDbDao.getLeaveApproveHistory(requestNo, locale);
	}

	/**
	 * 
	 * method name  : getLeaveApproveHistory
	 * @param requestNo
	 * @param appEmpNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveApprove>
	 * 
	 * purpose		: Get Approver History of a particular approver for particular request number
	 *
	 * Date    		:	Apr 14, 2013 1:04:50 PM
	 */
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, String appEmpNumber, Locale locale)
	{
		return leaveDbDao.getLeaveApproveHistory(requestNo, appEmpNumber, locale);
	}
	
	/**
	 * 
	 * method name  : getSabbatical
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Sabbatical>
	 * 
	 * purpose		: Get limited list of subbatical objects (sequence number just below) 
	 *
	 * Date    		:	Mar 25, 2013 2:01:51 PM
	 */
	public	Map<String,Sabbatical>	getSabbatical(String empNumber)
	{
		Map<String,Sabbatical> 	mapSabbaticals	=	new HashMap<String, Sabbatical>();
		List<Sabbatical>		lstSabbaticals	=	leaveDbDao.getSabbatical(empNumber);
		
		
		
		for(Sabbatical sabbatical : lstSabbaticals )
		{
			mapSabbaticals.put(sabbatical.getRequestNo(), sabbatical);
		}
		return mapSabbaticals;
	}
	
	/**
	 * 
	 * method name  : getDelegations
	 * @param leaveRequestNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<DelegatedEmp>
	 * 
	 * purpose		: get delegated employees for a particular leave request
	 *
	 * Date    		:	Sep 19, 2012 11:51:25 AM
	 */
	public List<DelegatedEmp>	getDelegations(String leaveRequestNo, Locale locale)
	{
		List<DelegatedEmp>	delegatedEmps	=	leaveDbDao.getDelegations(leaveRequestNo, locale);
		return leaveDbDao.getDelegations(leaveRequestNo, locale);
	}

	/**
	 * 
	 * method name  : isLeaveApplicationForRequesterAllowed
	 * @param empNumber
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Nov 22, 2016 2:51:18 PM
	 */
	public boolean isLeaveApplicationForRequesterAllowed(String empNumber)
	{
		boolean result	= true;
		
			if(isUnderProbation(empNumber) || isEndOfService(empNumber))
			{
				result = false;
			}
				
		return result;
	}

	/**
	 * 
	 * method name  : isNewLeaveAfterReturn
	 * @param leaveRequests
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		:
	 *
	 * Date    		:	Nov 29, 2016 12:01:19 PM
	 */
	public boolean isNewLeaveAfterReturn(List<LeaveRequest> leaveRequests)
	{
		boolean	result = true;
		
		for (LeaveRequest leaveReq: leaveRequests )
		{
			if(leaveReq.isLeaveExtended())
			{
				result = true;
				break;
			}
			if(
					leaveReq.isLeaveReturn() || 
					(
							leaveReq.getLeaveReturnIndicator().equals(Constants.CONST_LEAVE_RETURN_INDICATOR_RETURN) && 
							! (
									( leaveReq.getStatus().getStatusCode().trim().equals(Constants.CONST_LEAVE_STATUS_APPROVED)) ||
									( leaveReq.getStatus().getStatusCode().trim().equals(Constants.CONST_LEAVE_STATUS_SUSPEND))
							  )
					) 
					
			 )
			{
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * method name  : isExtendedLeaves
	 * @param leaveRequests
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: Get status of extended leave (consider to make the leave apply button visible without leave return approve)
	 *
	 * Date    		:	Mar 2, 2017 10:37:36 AM
	 */
	public boolean isExtendedLeaves(List<LeaveRequest> leaveRequests)
	{
		boolean	result = false;
		
		for (LeaveRequest leaveReq: leaveRequests )
		{
			if(leaveReq.isLeaveExtended())
			{
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see om.edu.squ.squportal.portlet.leaveapp.dao.service.LeaveAppServiceDao#isUnderProbation(java.lang.String)
	 */
	public boolean isUnderProbation(String empNumber)
	{
		return leaveDbDao.getDaysAfterJoin(empNumber) <= Constants.CONST_EMP_DAYS_AFTER_JOIN_LIMIT ? true : false;
	}
	
	/**
	 * 
	 * method name  : isEndOfService
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: findout is end of service mentioned in DB
	 *
	 * Date    		:	Nov 27, 2016 12:11:36 PM
	 */
	public boolean isEndOfService(String empNumber)
	{
		return leaveDbDao.isEndOfService(empNumber);
	}
	
	/**
	 * 
	 * method name  : getBranches
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Branch>
	 * 
	 * purpose		: Get list of active branches
	 *
	 * Date    		:	Nov 24, 2012 2:07:46 PM
	 */
	public List<Branch>	getBranches(String branchId, Locale locale)
	{
		return leaveDbDao.getBranches(branchId,locale);
	}
	
	/**
	 * 
	 * method name  : getBranches
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Branch>
	 * 
	 * purpose		: get list of branch based on empno
	 *
	 * Date    		:	Dec 19, 2012 10:17:04 AM
	 */
	public List<Branch> getEmpBranches (String empNumber, Locale locale)
	{
		empNumber = String.format("%07d", Integer.valueOf(empNumber));
		return leaveDbDao.getEmpBranches(empNumber, locale);
	}
	
	/**
	 * 
	 * method name  : getDepartments
	 * @param branchCode
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<Department>
	 * 
	 * purpose		: Get list of departments
	 *
	 * Date    		:	Sep 15, 2012 11:04:02 AM
	 */
	public List<Department>	getDepartments(String branchCode,Locale locale)
	{
		return leaveDbDao.getDepartments(branchCode,locale);
	}

	/**
	 * 
	 * method name  : getSections
	 * @param departmentCode
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Section>
	 * 
	 * purpose		: get list of sections
	 *
	 * Date    		:	Nov 26, 2012 12:40:40 PM
	 */
	public List<Section> getSections(String departmentCode, Locale locale)
	{
		return leaveDbDao.getSections(departmentCode, locale);
	}

	/**
	 * 
	 * method name  : getBudget
	 * @param budgetId
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Budget>
	 * 
	 * purpose		: Get list of budget object. 
	 *
	 * (Note : the locale as parameter has kept only for future compatibility. 
	 *         at present all the columns in GLM_BUDG_ID_VIEW are in english 
	 *         even though they made for arabic) 
	 *
	 * Date    		:	Apr 15, 2013 10:32:19 AM
	 */
	public List<Budget> getBudget(String budgetId, Locale locale)
	{
		return leaveDbDao.getBudget(budgetId, locale);
	}
	
	/**
	 * 
	 * method name  : setLeaveApprove
	 * @param leaveAppModel
	 * @param employee
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Set leave approve
	 *
	 * Date    		:	Oct 3, 2012 2:08:24 PM
	 */
	public int setLeaveApprove(LeaveAppModel 	leaveAppModel, Employee employee, Locale locale)
	{
		int				result	=	0;
		LeaveApprove	approve	=	new LeaveApprove();
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		approve.setRequestNo(leaveAppModel.getRequestNo());
		approve.setApproverAction(leaveAppModel.getApproverAction());
		approve.setApproverRemark(leaveAppModel.getApproverRemark());
		approve.setEmployee(employee);
		
		result =  leaveDbDao.setLeaveApprove(approve);
		/*Sending e-mail*/
		if(result != 0)
		{
			sendApproveEmail(employee.getEmpNumber(),leaveAppModel.getRequestNo(), leaveAppModel.getApproverAction(), locale);
		}
		return result;
	}
	
	/**
	 * 
	 * method name  : setLeaveApprove
	 * @param requestNo
	 * @param actionNo
	 * @param locale
	 * @param employee
	 * @return
	 * LeaveAppServiceDao
	 * return type  : int
	 * 
	 * purpose		: Set leave approve
	 *
	 * Date    		:	Dec 10, 2012 10:27:07 AM
	 */
	public int setLeaveApprove(String requestNo, String actionNo, Locale locale,Employee employee)
	{
		int 			result	=	0;
		LeaveApprove	approve	=	new LeaveApprove();
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		approve.setRequestNo(requestNo);
		approve.setApproverAction(actionNo);
		approve.setApproverRemark(
				UtilProperty.getMessage(
											"prop.leave.app.apply.form.approvar.auto.remarks", 
											null, 
											locale
										)
								);
		approve.setEmployee(employee);
		result	=	 leaveDbDao.setLeaveApprove(approve);
		
		/*Sending e-mail*/
		if(result != 0)
		{
			sendApproveEmail(employee.getEmpNumber(),requestNo, actionNo, locale);
		}
		return result;
	}


	/**
	 * 
	 * method name  : removeLeaveRequest
	 * @param requestNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : String
	 * 
	 * purpose		: removal of a particular leave request
	 *
	 * Date    		:	Feb 3, 2013 10:32:29 AM
	 */
	public String removeLeaveRequest(String requestNo,Locale locale)
	{
		String message	=	null;
		int result	=	leaveDbDao.removeLeaveRequest(requestNo);
		if (result != 0)
		{
			message	=	UtilProperty.getMessage("prop.leave.app.remove.request.success", null, locale);
		}
		else
		{
			message	=	UtilProperty.getMessage("prop.leave.app.remove.request.failure", null, locale);
		}
		
		return message;
	}
	
	/**
	 * 
	 * method name  : cancelLeaveRequest
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Cancel the leave request
	 *
	 * Date    		:	Feb 3, 2013 1:54:23 PM
	 */
	public String cancelLeaveRequest(String approverEmpNumber, String requestNo, Locale locale)
	{
		String 			message			=	null;
		LeaveRequest	leaveRequest	=	null;
		LeaveApprove	leaveApprove	=	null;
		int result = leaveDbDao.cancelLeaveRequest(approverEmpNumber, requestNo);
		if(result != 0)
		{
						message			=	UtilProperty.getMessage("prop.leave.app.cancel.request.success", new String []{requestNo}, locale);
						leaveRequest 	= leaveDbDao.getLeaveRequest(approverEmpNumber, requestNo, locale);
						leaveApprove	=	leaveDbDao.getLeaveApproveHistory(requestNo, locale).get(0);
		
			/* Sending email */
						EmailLeave	emailLeave		=	new EmailCancel(leaveRequest, leaveApprove, emailService,locale);
						emailLeave.sendRequesterEmail();
						emailLeave.sendApproverEmail();
		}
		else
		{
			message = UtilProperty.getMessage("prop.leave.app.cancel.request.failure", null, locale);
			logger.error("Cancelation not possible for request no : "+requestNo);
		}
		
		return message;
	}

	/**
	 * 
	 * method name  : sendApproveEmail
	 * @param appEmpNumber
	 * @param requestNo
	 * @param ApproverAction
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: send email on approver's action (e.g. approve,return,reject)
	 *
	 * Date    		:	Feb 19, 2013 12:19:24 PM
	 */
	private boolean sendApproveEmail(String appEmpNumber,String requestNo, String ApproverAction, Locale locale)
	{
		boolean			result			=	false;
		EmailLeave		emailLeave		=	null;		
		LeaveRequest	leaveRequest	=	getLeaveRequest(appEmpNumber, requestNo, locale);
		LeaveApprove	leaveApprove	=	getLeaveApproveHistory(requestNo,appEmpNumber, locale).get(0);
		LeaveType		leaveTypeFlag	=	leaveRequest.getLeaveTypeFlag();
		
		if(ApproverAction.equals(Constants.CONST_LEAVE_ACTION_APPROVE))
		{
			emailLeave	=	new EmailApprove(leaveRequest, leaveApprove, null, emailService,locale);
			result		=	emailLeave.sendEmail(true, true);
			/**
			 * For Sabbatical leave
			 * send mail to next higher approver on successful leave approved by immediate approver
			 * 
			 */
			if(leaveTypeFlag.getTypeNo().equals(Constants.CONST_LEAVE_TYPE_FLAG_SABBATICAL))
			{
				int sequenceNo	=	leaveRequest.getApproverSequenceNo();
				if  (sequenceNo < leaveDbDao.getMaxLeaveApproverSequence(requestNo))
				{
					LeaveApprove	leaveApproveSeq	=	leaveDbDao.getLeaveApproveHistorySequence(requestNo, String.valueOf(sequenceNo+1), locale).get(0);
					emailLeave	=	new EmailGeneral(true, false, leaveRequest, leaveApproveSeq, null, emailService);
					result		=	emailLeave.sendEmail(true, true);
				}
			}
			
		}else
			if(ApproverAction.equals(Constants.CONST_LEAVE_ACTION_RETURN))
			{
				emailLeave	=	new EmailReturn(leaveRequest, leaveApprove, null, emailService, locale);
				result		=	emailLeave.sendEmail(true, true);
			} else
				if(ApproverAction.equals(Constants.CONST_LEAVE_ACTION_REJECT))
				{
					emailLeave	=	new EmailReject(leaveRequest, leaveApprove, null, emailService, locale);
					result		=	emailLeave.sendEmail(true, true);
				}
		return result;
	}

	/**
	 * 
	 * method name  : sendLeaveReturnEmail
	 * @param appEmpNumber
	 * @param requestNo
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Dec 7, 2016 1:40:07 PM
	 */
	private boolean sendLeaveReturnEmail(String appEmpNumber,String requestNo)
	{
		boolean			result			=	false;
		EmailLeave		emailLeave		=	null;		
		LeaveRequest	leaveRequest	=	getLeaveRequest(appEmpNumber, requestNo, new Locale("en"));
		LeaveApprove	leaveApprove	=	getLeaveApproveHistory(requestNo,appEmpNumber, new Locale("en")).get(0);

						emailLeave		=	new EmailGeneral(false, true, leaveRequest, leaveApprove, null, emailService);
						result			=	emailLeave.sendEmail(true, true);

		return result;
	}
	
	
	
	/**
	 * 
	 * method name  : getLeaveBalance
	 * @param empNumber
	 * @param strStartDate
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : String
	 * 
	 * purpose		: Get Leave balance
	 *
	 * Date    		:	Jul 8, 2013 1:21:42 PM
	 * @throws ParseException 
	 */
	public String	getLeaveBalance(String empNumber, String strStartDate)
	{
		return leaveDbDao.getLeaveBalance(empNumber, strStartDate);
	}
	
	
	/**
	 * 
	 * method name  : getDelegatedEmployeeCurrentDate
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: Get delegate employee (if any)  at present date 
	 *
	 * Date    		:	Aug 11, 2016 1:01:28 PM
	 */
	public Employee getDelegatedEmployeeCurrentDate(String empAppNumber, Locale locale)
	{
		return leaveDbDao.getDelegatedEmployeeCurrentDate(empAppNumber, locale);
	}
	/***********************************************************************************
	 *             LEAVE RETURN
	 ***********************************************************************************/
	
	/**
	 * 
	 * method name  : isReturnEligible
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		: if current date is higher than leave return date then return eligible function returns true
	 *
	 * Date    		:	May 31, 2016 2:19:54 PM
	 */
	public boolean isReturnEligible(String requestNo)
	{
		return leaveDbDao.isReturnEligible(requestNo);
	}
	
	/**
	 * 
	 * method name  : newLeaveReturn
	 * @param leaveRequest
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Request submitted for leave return
	 *
	 * Date    		:	Aug 1, 2016 11:37:06 AM
	 */
	public int newLeaveReturn(LeaveAppModel	leaveAppModel)
	{
		int 			result			=	0; 
		LeaveRequest	leaveRequest	=	new LeaveRequest();
		leaveRequest.setRequestNo(leaveAppModel.getRequestNo());
		if((null== leaveAppModel.getHod()) && null != leaveAppModel.getApproverEmpNumber())
		{
			leaveRequest.setApproverId(leaveAppModel.getApproverEmpNumber());
		}
		else if ((null != leaveAppModel.getHod()) && null != leaveAppModel.getApproverEmpNumber())
		{
			leaveRequest.setApproverId(leaveAppModel.getHod());
		}
		leaveRequest.setLeaveReturnDate(leaveAppModel.getLeaveReturnDate());
		leaveRequest.setLeaveRequestRemarks(leaveAppModel.getLeaveRemarks());
		
		result = leaveDbDao.newLeaveReturn(leaveRequest);
		
		if(result !=0)
		{
			sendLeaveReturnEmail(leaveRequest.getApproverId(), leaveAppModel.getRequestNo());
		}
		return result;
	}
}

