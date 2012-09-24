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
 * File Name			:	LeaveDbDao.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.dao.db
 * Date of creation		:	Aug 25, 2012  11:12:55 AM
 * Date of modification :	
 * 
 * Summary				:	database dao interface for leave request
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
package om.edu.squ.squportal.portlet.leaveapp.dao.db;

import java.util.List;
import java.util.Locale;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;

/**
 * @author Bhabesh
 *
 */
public interface LeaveDbDao
{
	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Aug 25, 2012 1:00:22 PM
	 */
	public List<LeaveType>	getLeaveTypes(Locale locale);
	
	/**
	 * 
	 * method name  : getAdminActions
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<AdminAction>
	 * 
	 * purpose		: Admin actions
	 *
	 * Date    		:	Aug 26, 2012 8:20:54 AM
	 */
	public List<AdminAction>	getAdminActions(Locale locale);
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : Employee
	 * 
	 * purpose		: get employee record
	 *
	 * Date    		:	Aug 27, 2012 9:14:07 AM
	 */
	public Employee	getEmployee(String empNumber, Locale locale);
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param branchCode
	 * @param deptCode
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<Employee>
	 * 
	 * purpose		: Get List of Employees with limited information
	 *
	 * Date    		:	Sep 16, 2012 9:20:42 AM
	 */
	public List<Employee>	getEmployee(String branchCode, String deptCode,Locale locale);
	
	/**
	 * 
	 * method name  : getAdditionalDesignation
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<Designation>
	 * 
	 * purpose		: Additional designations (might be holding more than one designation at a time)
	 *
	 * Date    		:	Sep 17, 2012 12:03:16 PM
	 */
	public List<Designation>	getAdditionalDesignation(String empNumber, Locale locale);
	
	/**
	 * 
	 * method name  : setNewLeaveRequest
	 * @param leaveRequest
	 * @return
	 * LeaveDbDao
	 * return type  : int
	 * 
	 * purpose		: Insert new leave request
	 *
	 * Date    		:	Sep 8, 2012 1:46:27 PM
	 */
	int setNewLeaveRequest(LeaveRequest leaveRequest,DelegatedEmp[] delegatedEmps);
	
	/**
	 * 
	 * method name  : getDelegations
	 * @param leaveRequestNo
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<DelegatedEmp>
	 * 
	 * purpose		: get delegated employees for a particular leave request
	 *
	 * Date    		:	Sep 19, 2012 11:19:56 AM
	 */
	public List<DelegatedEmp>	getDelegations(String leaveRequestNo, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param empNumber
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Get list of leave requests
	 *
	 * Date    		:	Sep 12, 2012 12:39:13 PM
	 */
	public List<LeaveRequest>	getLeaveRequests(String empNumber,Employee employee, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param reqNo
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Sep 18, 2012 2:15:21 PM
	 */
	public LeaveRequest	getLeaveRequest(String reqNo, Locale locale);
	
	/**
	 * 
	 * method name  : getDepartments
	 * @param branchCode
	 * @return
	 * LeaveDbDao
	 * return type  : List<Department>
	 * 
	 * purpose		: Get list of departments
	 *
	 * Date    		:	Sep 15, 2012 11:02:44 AM
	 */
	public List<Department>	getDepartments(String branchCode, Locale locale);
}