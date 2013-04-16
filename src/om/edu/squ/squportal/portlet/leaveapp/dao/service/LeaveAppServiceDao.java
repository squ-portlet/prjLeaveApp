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

import java.text.ParseException;
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
import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Sabbatical;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
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
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveAppServiceDao
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Sep 25, 2012 1:12:19 PM
	 */
	public List<LeaveType>	getLeaveTypes(Employee employee, Locale locale);
	
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
	 * method name  : getAllowEleaveRequest
	 * @param requestNo
	 * @param leaveTypeNo
	 * @param leaveAppModel
	 * @param locale
	 * @param employeeNo
	 * @return
	 * LeaveAppServiceDao
	 * return type  : AllowEleaveRequestProc
	 * 
	 * purpose		: Check before insert leave request
	 *
	 * Date    		:	Sep 29, 2012 11:05:23 AM
	 * @throws ParseException 
	 */
	public AllowEleaveRequestProc	getAllowEleaveRequest(
			String requestNo,
			String leaveTypeNo,
			LeaveAppModel 	leaveAppModel, Employee	employee, Locale locale) 
	throws ParseException;
	
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
	public Employee getManager(String empNumber, Locale locale);
	
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
	//public int setNewLeaveRequest(LeaveAppModel 	leaveAppModel, Employee	employee);
	
	/**
	 * 
	 * method name  : getLeaveRequests
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
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param empNumber 
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
	public LeaveRequest	getLeaveRequest(String empNumber, String reqNo, Locale locale);
	
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
	 * LeaveAppServiceDaoImpl
	 * return type  : List<LeaveApprove>
	 * 
	 * purpose		: Get approver's history
	 *
	 * Date    		:	Jan 13, 2013 1:01:30 PM
	 */
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, Locale locale);
	
	/**
	 * 
	 * method name  : getSabaSabbatical
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Map<String,Sabbatical>
	 * 
	 * purpose		: Get limited list of subbatical objects (sequence number just below) 
	 *
	 * Date    		:	Mar 25, 2013 2:01:51 PM
	 */
	public	Map<String, Sabbatical>	getSabbatical(String empNumber);
	
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
	public List<Branch>	getBranches(String branchId, Locale locale);
	
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
	public List<Branch> getEmpBranches (String empNumber, Locale locale);
	
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
	public List<Budget> getBudget(String budgetId, Locale locale);
	
	/**
	 * 
	 * method name  : setLeaveApprove
	 * @param leaveAppModel
	 * @param employee
	 * @param locale TODO
	 * @return
	 * LeaveAppServiceDao
	 * return type  : int
	 * 
	 * purpose		: Set leave approve
	 *
	 * Date    		:	Oct 3, 2012 2:10:02 PM
	 */
	public int setLeaveApprove(LeaveAppModel 	leaveAppModel, Employee employee, Locale locale);
	
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
	public int setLeaveApprove(String requestNo, String actionNo, Locale locale,Employee employee);

	/**
	 * 
	 * method name  : cancelLeaveRequest
	 * @param approverEmpNumber TODO
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Cancel the leave request
	 *
	 * Date    		:	Feb 3, 2013 1:54:23 PM
	 */
	public String cancelLeaveRequest(String approverEmpNumber, String requestNo, Locale locale);
	
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
	public String removeLeaveRequest(String requestNo,Locale locale);
	
}
