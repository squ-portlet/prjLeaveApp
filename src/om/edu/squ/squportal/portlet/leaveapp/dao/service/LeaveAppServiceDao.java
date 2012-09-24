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
 * File Name			:	LeaveAppServiceDao.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.dao.service
 * Date of creation		:	Aug 25, 2012  11:21:20 AM
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
package om.edu.squ.squportal.portlet.leaveapp.dao.service;

import java.util.List;
import java.util.Locale;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.dao.ldap.LdapDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;

/**
 * @author Bhabesh
 *
 */
public interface LeaveAppServiceDao
{
	
	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Aug 25, 2012 1:20:39 PM
	 */
	public List<LeaveType>	getLeaveTypes(Locale locale);
	
	/**
	 * 
	 * method name  : getAdminActions
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<AdminAction>
	 * 
	 * purpose		: Admin actions
	 *
	 * Date    		:	Aug 26, 2012 8:23:24 AM
	 */
	public List<AdminAction>	getAdminActions(Locale locale);
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : Employee
	 * 
	 * purpose		: get employee record
	 *
	 * Date    		:	Aug 27, 2012 9:16:59 AM
	 */
	public Employee	getEmployee(String empNumber, String empInternetId, Locale locale);
	
	/**
	 * 
	 * method name  : getAdditionalDesignation
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<Designation>
	 * 
	 * purpose		: Additional designations (might be holding more than one designation at a time)
	 *
	 * Date    		:	Sep 17, 2012 12:07:18 PM
	 */
	public List<Designation>	getAdditionalDesignation(String empNumber, Locale locale);
	
	/**
	 * 
	 * method name  : setNewLeaveRequest
	 * @param leaveAppModel
	 * @param employee
	 * @return
	 * LeaveAppServiceDao
	 * return type  : int
	 * 
	 * purpose		: service layer for adding new leave request
	 *
	 * Date    		:	Sep 9, 2012 9:00:49 AM
	 */
	public int setNewLeaveRequest(LeaveAppModel 	leaveAppModel, Employee	employee);
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param empNumber
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Get list of leave requests
	 *
	 * Date    		:	Sep 12, 2012 12:41:29 PM
	 */
	public List<LeaveRequest>	getLeaveRequests(String empNumber,Employee employee, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param reqNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Sep 18, 2012 2:31:17 PM
	 */
	public LeaveRequest	getLeaveRequest(String reqNo, Locale locale);
	
	/**
	 * 
	 * method name  : getDelegations
	 * @param leaveRequestNo
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<DelegatedEmp>
	 * 
	 * purpose		: get delegated employees for a particular leave request
	 *
	 * Date    		:	Sep 19, 2012 11:53:08 AM
	 */
	public List<DelegatedEmp>	getDelegations(String leaveRequestNo, Locale locale);
	
	/**
	 * 
	 * method name  : getDepartments
	 * @param branchCode
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<Department>
	 * 
	 * purpose		: Get list of departments
	 *
	 * Date    		:	Sep 15, 2012 11:06:22 AM
	 */
	public List<Department>	getDepartments(String branchCode,Locale locale);
}
