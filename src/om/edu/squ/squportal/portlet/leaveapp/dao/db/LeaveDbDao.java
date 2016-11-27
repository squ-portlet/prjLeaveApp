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

import org.springframework.transaction.annotation.Transactional;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.Budget;
import om.edu.squ.squportal.portlet.leaveapp.bo.CheckLeaveResearch;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.HoD;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Sabbatical;
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
	 * method name  : getCheckLeaveResearch
	 * @param empNumber
	 * @param researchId
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : CheckLeaveResearch
	 * 
	 * purpose		: Check leave for sabbatical research
	 *
	 * Date    		:	Mar 19, 2013 11:49:56 AM
	 */
	public CheckLeaveResearch getCheckLeaveResearch(String empNumber, String researchId, Locale locale);
	
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
	 * method name  : getDaysAfterJoin
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Number of days after joining
	 *
	 * Date    		:	Nov 22, 2016 2:22:51 PM
	 */
	public int getDaysAfterJoin(String empNumber);
	
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
	public boolean isEndOfService(String empNumber);
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param employee
	 * @param locale
	 * @param userType TODO
	 * @return
	 * LeaveDbDao
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Get list of leave requests
	 *
	 * Date    		:	Sep 12, 2012 12:39:13 PM
	 */
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale, String userType);
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param approverEmpNumber TODO
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
	public LeaveRequest	getLeaveRequest(String approverEmpNumber, String reqNo, Locale locale);

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
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, String appEmpNumber, Locale locale);
	
	/**
	 * 
	 * method name  : getLeaveApproveHistorySequence
	 * @param requestNo
	 * @param sequenceNo
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveApprove>
	 * 
	 * purpose		:	 Get Approver History of a particular sequence number for particular request number
	 *
	 * Date    		:	Apr 14, 2013 3:05:42 PM
	 */
	public List<LeaveApprove>	getLeaveApproveHistorySequence(String requestNo, String sequenceNo, Locale locale);
	
	/**
	 * 
	 * method name  : getMaxLeaveApproverSequence
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Maximum sequence number of leave approver of a particular leave request
	 *
	 * Date    		:	Apr 14, 2013 3:32:56 PM
	 */
	public int getMaxLeaveApproverSequence(String requestNo);
	
	
	/**
	 * 
	 * method name  : getSabaSabbatical
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Sabbatical>
	 * 
	 * purpose		: Get limited list of subbatical objects (sequence number just below)  
	 *
	 * Date    		:	Mar 25, 2013 2:01:51 PM
	 */
	public	List<Sabbatical>	getSabbatical(String empNumber);
	
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
	 * method name  : getLeaveRequestCounter
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : long
	 * 
	 * purpose		: Get the next higher number for leave request
	 *
	 * Date    		:	Sep 8, 2012 12:53:38 PM
	 */
	public long getLeaveRequestCounter();
	
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
	public int cancelLeaveRequest(String approverEmpNumber, String requestNo);
	
	/**
	 * 
	 * method name  : removeLeaveRequest
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: delete particular leave request
	 *
	 * Date    		:	Feb 3, 2013 9:48:59 AM
	 */
	public int removeLeaveRequest(String requestNo);
	

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
	 */
	public String	getLeaveBalance(String empNumber, String strStartDate);
	
	
	
	
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
	public Employee getDelegatedEmployeeCurrentDate(String empAppNumber, Locale locale);

	
	
	
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
	public boolean isReturnEligible(String requestNo);
	
	
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
	public int newLeaveReturn(LeaveRequest	leaveRequest);
}
