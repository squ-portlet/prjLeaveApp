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

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.HoD;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;

/**
 * @author Bhabesh
 *
 */
public interface LeaveDbDao
{

	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Sep 25, 2012 1:05:19 PM
	 */
	public List<LeaveType>	getLeaveTypes(Employee employee, Locale locale);
	
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
	 * @param empNumber
	 * @param branchCode
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<Employee>
	 * 
	 * purpose		: Get List of Employees with limited information
	 *
	 * Date    		:	Sep 16, 2012 9:20:42 AM
	 */
	public List<Employee>	getEmployee(String empNumber, String branchCode, Locale locale);
	
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
	 * method name  : getNextHeadBranch
	 * @param branchCode
	 * @param paramLevelAdd
	 * @param empLevel
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<HoD>
	 * 
	 * purpose		: Get the head of the system from next hierarchy level (branch wise)
	 *
	 * Date    		:	Dec 5, 2012 8:29:35 AM
	 */
	public List<HoD> getNextHeadBranch(String branchCode, int paramLevelAdd, String empLevel, Locale locale);
	
	/**
	 * 
	 * method name  : getDepartmentHead
	 * @param branchCode
	 * @param deptCode
	 * @param empLevel
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<HoD>
	 * 
	 * purpose		: get department head id and name
	 *
	 * Date    		:	Dec 5, 2012 9:34:39 AM
	 */
	public List<HoD> getDepartmentHead(String branchCode, String deptCode,String empLevel, Locale locale);
	
	/**
	 * 
	 * method name  : getSectionHead
	 * @param branchCode
	 * @param deptCode
	 * @param sectCode
	 * @param empLevel
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<HoD>
	 * 
	 * purpose		: get section head id and name 
	 *
	 * Date    		:	Dec 5, 2012 9:19:35 AM
	 */
	public List<HoD>	getSectionHead(String branchCode, String deptCode, String sectCode, String empLevel,Locale locale);
	
	/**
	 * 
	 * method name  : getAllowEleaveRequest
	 * @param leaveRequest
	 * @param locale
	 * @return
	 * @throws ParseException
	 * LeaveDbDao
	 * return type  : AllowEleaveRequestProc
	 * 
	 * purpose		: Check before insert leave request
	 *
	 * Date    		:	Sep 26, 2012 2:24:28 PM
	 */
	public AllowEleaveRequestProc	getAllowEleaveRequest(LeaveRequest leaveRequest, Locale locale) 
	throws ParseException;
	
	/**
	 * 
	 * method name  : setNewLeaveRequest
	 * @param leaveRequest
	 * @param locale TODO
	 * @return
	 * LeaveDbDao
	 * return type  : int
	 * 
	 * purpose		: Insert new leave request
	 *
	 * Date    		:	Sep 8, 2012 1:46:27 PM
	 */
	int setNewLeaveRequest(LeaveRequest leaveRequest,DelegatedEmp[] delegatedEmps, Locale locale);
	
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
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale);
	
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
	public List<LeaveRequest>	getLeaveRequestHistory(String requestNo, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveApproveHistory
	 * @param requestNo
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveApprove>
	 * 
	 * purpose		: Get approver's history
	 *
	 * Date    		:	Jan 13, 2013 12:53:38 PM
	 */
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, Locale locale);
	
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
	public List<Branch>	getBranches(Locale locale);
	
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
	public List<Branch> getBranches (String empNumber, Locale locale);
	
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
	public List<Section> getSections(String departmentCode, Locale locale);
	
	
	/**
	 * 
	 * method name  : setLeaveApprove
	 * @param approve
	 * @return
	 * LeaveDbDao
	 * return type  : int
	 * 
	 * purpose		: Set leave approve
	 *
	 * Date    		:	Oct 3, 2012 1:58:12 PM
	 */
	public int setLeaveApprove(LeaveApprove approve);
	
}
