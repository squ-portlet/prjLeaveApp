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

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
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
	 * @param locale
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Aug 25, 2012 1:05:51 PM
	 */
	public List<LeaveType>	getLeaveTypes(Locale locale)
	{
		return leaveDbDao.getLeaveTypes(locale);
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
	 * method name  : setNewLeaveRequest
	 * @param leaveAppModel
	 * @param employee
	 * @return
	 * LeaveAppServiceDaoImpl
	 * return type  : int
	 * 
	 * purpose		: service layer for adding new leave request
	 *
	 * Date    		:	Sep 9, 2012 8:59:53 AM
	 */
	public int setNewLeaveRequest(LeaveAppModel 	leaveAppModel, Employee	employee)
	{
		LeaveRequest	leaveRequest	=	new LeaveRequest();
		LeaveType		leaveType		=	new	LeaveType();		

		
		leaveRequest.setLeaveStartDate(leaveAppModel.getLeaveStartDate());
		leaveRequest.setLeaveEndDate(leaveAppModel.getLeaveEndDate());
		leaveRequest.setLeaveStatus(Constants.CONST_LEAVE_STATUS_WAITING_APPV);
		employee.setDesignationAddlCode(leaveAppModel.getPositionAdditional());
		employee.setAdmin(leaveAppModel.isAdminSqu());
		leaveRequest.setEmployee(employee);
		leaveType.setTypeNo(leaveAppModel.getLeaveType());
		leaveRequest.setLeaveType(leaveType);
		leaveRequest.setLeaveRequestRemarks(leaveAppModel.getLeaveRemarks());
		
		return leaveDbDao.setNewLeaveRequest(leaveRequest,leaveAppModel.getDelegatedEmps());
		
	}
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param empNumber
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
	public List<LeaveRequest>	getLeaveRequests(String empNumber,Employee employee, Locale locale)
	{
		return leaveDbDao.getLeaveRequests(empNumber, employee, locale);
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
}
