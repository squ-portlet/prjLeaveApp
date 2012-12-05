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
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.ldap.LdapDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;

/**
 * @author Bhabesh
 *
 */
public class LeaveAppServiceDaoImpl implements LeaveAppServiceDao
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private LeaveDbDao	leaveDbDao;

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
	 * @param leaveAppModel
	 * @param employeeNo
	 * @param locale
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
		
		leaveTypeFlag.setTypeNo(leaveAppModel.getLeaveTypeFlag());
		
		leaveRequest.setLeaveTypeFlag(leaveTypeFlag);
		leaveRequest.setEmployee(employee);
		leaveRequest.setLeaveStartDate(leaveAppModel.getLeaveStartDate());
		leaveRequest.setLeaveEndDate(leaveAppModel.getLeaveEndDate());
		leaveRequest.setLeaveRequestRemarks(leaveAppModel.getLeaveRemarks());
		leaveRequest.setLeaveStatus(Constants.CONST_LEAVE_STATUS_WAITING_APPV);
		
		logger.info("leaveRequest : "+leaveRequest);
		allowEleaveRequestProc	=	leaveDbDao.getAllowEleaveRequest(leaveRequest, locale);
		
		if(allowEleaveRequestProc.isAcceptLeave())
		{
			leaveType.setTypeNo(allowEleaveRequestProc.getLeaveCode());
			leaveRequest.setLeaveType(leaveType);
			leaveDbDao.setNewLeaveRequest(leaveRequest,leaveAppModel.getDelegatedEmps());
		}
		
		return allowEleaveRequestProc;
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
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale)
	{
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		
		return leaveDbDao.getLeaveRequests(employee, locale);
	}
	
	/**
	 * 
	 * method name  : getLeaveRequest
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
	public synchronized LeaveRequest	getLeaveRequest(String reqNo, Locale locale)
	{
		LeaveRequest	leaveRequest	=	leaveDbDao.getLeaveRequest(reqNo, locale);
		Employee		empLeaveReq		=	leaveRequest.getEmployee();
		Employee		employee		=	leaveDbDao.getEmployee(empLeaveReq.getEmpNumber(), locale);
					
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
		logger.info("delegated emps : " + delegatedEmps.toString());
		return leaveDbDao.getDelegations(leaveRequestNo, locale);
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
	public List<Branch>	getBranches(Locale locale)
	{
		return leaveDbDao.getBranches(locale);
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
	public int setLeaveApprove(LeaveAppModel 	leaveAppModel, Employee employee)
	{
		LeaveApprove	approve	=	new LeaveApprove();
		employee.setEmpNumber(String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		approve.setRequestNo(leaveAppModel.getRequestNo());
		approve.setApproverAction(leaveAppModel.getApproverAction());
		approve.setApproverRemark(leaveAppModel.getApproverRemark());
		approve.setEmployee(employee);
		
		return leaveDbDao.setLeaveApprove(approve);
	}
	
}
