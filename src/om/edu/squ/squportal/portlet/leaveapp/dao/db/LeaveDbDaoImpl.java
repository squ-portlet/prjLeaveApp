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
 * File Name			:	LeaveDbDaoImpl.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.dao.db
 * Date of creation		:	Aug 25, 2012  10:55:56 AM
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
package om.edu.squ.squportal.portlet.leaveapp.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.kerberos.DelegationPermission;
import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveStatus;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.dao.ldap.LdapDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bhabesh
 *
 */
public class LeaveDbDaoImpl implements LeaveDbDao
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	JdbcTemplate					jdbcTemplate;	
	private	NamedParameterJdbcTemplate 		namedParameterJdbcTemplate;
	private SimpleJdbcCall 					simpleJdbcCall;
	
	/**
	 * 
	 * Constructor
	 *
	 */
	public LeaveDbDaoImpl(){}
	
	/**
	 * 
	 * Constructor
	 * @param dataSource
	 *
	 */
	public LeaveDbDaoImpl(DataSource dataSource)
	{
		this.namedParameterJdbcTemplate	=	new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * 
	 * method name  : setDataSource
	 * @param dataSource
	 * LeaveDbDaoImpl
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 25, 2012 10:59:43 AM
	 */
	public void setDataSource(DataSource dataSource) 
	{
		this.jdbcTemplate				=	new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = 	new NamedParameterJdbcTemplate(dataSource); 
		this.simpleJdbcCall				=	new SimpleJdbcCall(dataSource);
	}

	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Aug 25, 2012 12:58:17 PM
	 */
	public List<LeaveType>	getLeaveTypes(Locale locale)
	{
		RowMapper<LeaveType> mapper	=	new RowMapper<LeaveType>()
		{

			public LeaveType mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				LeaveType	leaveType	=	new LeaveType();
				leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
				leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
				return leaveType;
			}
			
		};
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		return this.namedParameterJdbcTemplate.query(Constants.SQL_LEAVE_TYPE, namedParameters, mapper);
	}
	
	/**
	 * 
	 * method name  : getAdminActions
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<AdminAction>
	 * 
	 * purpose		: Admin actions
	 *
	 * Date    		:	Aug 26, 2012 7:58:03 AM
	 */
	public List<AdminAction>	getAdminActions(Locale locale)
	{
		RowMapper<AdminAction> mapper	=	new RowMapper<AdminAction>()
		{

			public AdminAction mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				AdminAction	action	=	new AdminAction();
				action.setActionCode(rs.getString(Constants.CONST_ACTION_CODE));
				action.setActionDesc(rs.getString(Constants.CONST_ACTION_DESC));
				return action;
			}
			
		};
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		return this.namedParameterJdbcTemplate.query(Constants.SQL_ADMIN_ACTION, namedParameters, mapper);
	}
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: get employee record
	 *
	 * Date    		:	Aug 27, 2012 9:13:07 AM
	 */
	public Employee	getEmployee(String empNumber,Locale locale)
	{
		RowMapper<Employee> mapper	=	new RowMapper<Employee>()
		{

			public Employee mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				Employee	employee	=	new Employee();
				employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				employee.setJobTypeCode(rs.getString(Constants.CONST_EMP_JOB_TYPE_CODE));
				employee.setDesignationCode(rs.getString(Constants.CONST_EMP_DESIGNATION_CODE));
				employee.setDesignation(rs.getString(Constants.CONST_EMP_DESIGNATION));
				employee.setDesignationAddlCode(rs.getString(Constants.CONST_EMP_ADDL_POSITION_CODE));
				employee.setGradeCode(rs.getString(Constants.CONST_EMP_GRADE_CODE));
				employee.setGrade(rs.getString(Constants.CONST_EMP_GRADE));
				employee.setGradeShort(rs.getString(Constants.CONST_EMP_GRADE_SHORT));
				employee.setBranchCode(rs.getString(Constants.CONST_EMP_BRANCH_CODE));
				employee.setBranch(rs.getString(Constants.CONST_EMP_BRANCH));
				employee.setDepartmentCode(rs.getString(Constants.CONST_EMP_DEPARTMENT_CODE));
				employee.setDepartment(rs.getString(Constants.CONST_EMP_DEPARTMENT));
				employee.setDepartmentShort(rs.getString(Constants.CONST_EMP_DEPARTMENT_SHORT));
				employee.setSectionCode(rs.getString(Constants.CONST_EMP_SECTION_CODE));
				employee.setSection(rs.getString(Constants.CONST_EMP_SECTION));
				employee.setJoinDt(rs.getString(Constants.CONST_EMP_JOIN_DATE));
				employee.setHierarchyCode(rs.getString(Constants.CONST_EMP_HIERARCHY_CODE));
				if(rs.getString(Constants.CONST_EMP_ADMIN.toUpperCase()).equals("Y"))
				{
					employee.setAdmin(true);
				}
				else
				{
					employee.setAdmin(false);
				}
				return employee;
			}
		};
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", empNumber);
		
		return this.namedParameterJdbcTemplate.queryForObject(Constants.SQL_EMPLOYEE, namedParameters, mapper);
		
	}
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param branchCode
	 * @param deptCode
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: Get List of Employees with limited information
	 *
	 * Date    		:	Sep 16, 2012 9:05:32 AM
	 */
	public List<Employee>	getEmployee(String branchCode, String deptCode,Locale locale)
	{
		RowMapper<Employee> mapper	=	new RowMapper<Employee>()
		{

			public Employee mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				
				Employee	employee	=	new Employee();
				employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				
				return employee;
			}
		};
		
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramBranchCode", branchCode);
		namedParameters.put("paramDept", deptCode);
		
		return	this.namedParameterJdbcTemplate.query(Constants.SQL_EMP_BRANCH_DEPT_SHORT, namedParameters, mapper);
		
	}
	
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: Get Employee with default locale (i.e. english)
	 *
	 * Date    		:	Sep 10, 2012 2:27:16 PM
	 */
	private Employee	getEmployee(String empNumber)
	{
		Locale	localeEN	=	new Locale(Constants.CONST_LANG_DEFAULT_EN);	
		return getEmployee(empNumber, localeEN);
		
	}
	
	
	/**
	 * 
	 * method name  : getAdditionalDesignation
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Designation>
	 * 
	 * purpose		:  Additional designations (might be holding more than one designation at a time)
	 *
	 * Date    		:	Sep 17, 2012 11:55:30 AM
	 */
	public List<Designation>	getAdditionalDesignation(String empNumber, Locale locale)
	{
		List<Designation>	designations	=	null;
		RowMapper<Designation> mapper = new RowMapper<Designation>()
		{
			public Designation	mapRow(ResultSet rs, int rowNum)
			throws SQLException
			{
				Designation	designation	=	new Designation();
				designation.setDesigCode(rs.getString(Constants.CONST_EMP_DESIGNATION_CODE));
				designation.setDesigDescription(rs.getString(Constants.CONST_EMP_DESIGNATION));
				return designation;
			}
		};
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", empNumber);
		
		try
		{
			designations	=	this.namedParameterJdbcTemplate.query(Constants.SQL_ADDITIONAL_POSITION, namedParameters,mapper);
		}
		catch(NullPointerException nullEx)
		{
			designations	=	null;						// No additional designation available
		}
		 return designations;
	}
	
	/**
	 * 
	 * method name  : setNewLeaveRequest
	 * @param leaveRequest
	 * @param delegatedEmps
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Insert new leave request
	 *
	 * Date    		:	Sep 10, 2012 2:47:24 PM
	 */
	@Transactional("trLeaveReq")
	public synchronized int setNewLeaveRequest(LeaveRequest leaveRequest, DelegatedEmp[] delegatedEmps)
	{
		int			result					=	0;
		String		leaveRequestNo			=	String.valueOf(getLeaveRequestCounter());
		Employee 	emp						=	leaveRequest.getEmployee();
		LeaveType	leaveType				=	leaveRequest.getLeaveType();
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		
		namedParameters.put("paramReqNo",leaveRequestNo);
		//namedParameters.put("paramReqDate",);
		namedParameters.put("paramLeaveStatus",leaveRequest.getLeaveStatus());
		namedParameters.put("paramEmpCode",emp.getEmpNumber());
		namedParameters.put("paramInternetId",emp.getEmpInternetId());
		namedParameters.put("paramStartDate",leaveRequest.getLeaveStartDate());
		namedParameters.put("paramEndDate",leaveRequest.getLeaveEndDate());
		//namedParameters.put("paramReturnDate",leaveRequest.getLeaveReturnDate());
		//namedParameters.put("paramLastReturnDate",leaveRequest.getLeaveLastReturnDate());
		namedParameters.put("paramBranchCode",emp.getBranchCode());
		namedParameters.put("paramDepartmentCode",emp.getDepartmentCode());
		namedParameters.put("paramSectionCode",emp.getSectionCode());
		namedParameters.put("paramGradeCode",emp.getGradeCode());
		namedParameters.put("paramDesignationCode",emp.getDesignationCode());
		namedParameters.put("paramJobTypeCode",emp.getJobTypeCode());
		namedParameters.put("paramLeaveType",leaveRequest.getLeaveType().getTypeNo());
		//namedParameters.put("paramLeavePurpose",leaveRequest.getLeavePurpose());
		if(emp.isAdmin())
		{
			namedParameters.put("paramIsAdmin",Constants.CONST_ADMIN_TRUE);
		}
		else
		{
			namedParameters.put("paramIsAdmin",Constants.CONST_ADMIN_FALSE);
		}
		namedParameters.put("paramPositionCode",emp.getDesignationAddlCode());							//TODO Position is not cleared
		namedParameters.put("paramHierarchyCode",emp.getHierarchyCode());
		namedParameters.put("paramIsReqActive",Constants.CONST_LEAVE_REQ_ACTIVE_TRUE);
		namedParameters.put("paramReqRemarks",leaveRequest.getLeaveRequestRemarks());
		namedParameters.put("paramReqCreUserInit",Constants.USER_WEB);
		//namedParameters.put("paramReqCreDate",);
		
		result =  this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQUEST, namedParameters);
		
		setNewLeaveDelegationRequest(leaveRequestNo,delegatedEmps);
		
		return result;
		
	}

	/**
	 * 
	 * method name  : getDelegations
	 * @param leaveRequestNo
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<DelegatedEmp>
	 * 
	 * purpose		: get delegated employees for a particular leave request
	 *
	 * Date    		:	Sep 19, 2012 11:17:00 AM
	 */
	public List<DelegatedEmp>	getDelegations(String leaveRequestNo, Locale locale)
	{
		RowMapper<DelegatedEmp> mapper	=	new RowMapper<DelegatedEmp>()
		{

			public DelegatedEmp mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				DelegatedEmp	delegatedEmp	=	new DelegatedEmp();
				delegatedEmp.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				delegatedEmp.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
				delegatedEmp.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				delegatedEmp.setFromDate(rs.getString(Constants.CONST_DELEGATE_START_DATE));
				delegatedEmp.setToDate(rs.getString(Constants.CONST_DELEGATE_END_DATE));
				
				return delegatedEmp;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLeaveReqNo",leaveRequestNo);
		namedParameters.put("paramLocale",locale.getLanguage());
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_LEAVE_REQ_DELEGATION, namedParameters, mapper);
		
	}
	
	
	
	/**
	 * 
	 * method name  : setNewLeaveDelegationRequest
	 * @param requestNo
	 * @param delegatedEmps
	 * LeaveDbDaoImpl
	 * return type  : void
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Sep 10, 2012 2:42:53 PM
	 */
	private	void	setNewLeaveDelegationRequest(String requestNo, DelegatedEmp[] delegatedEmps)
	{
		logger.info("delegated employees length: "+delegatedEmps.length);
		try
		{
			for(DelegatedEmp delEmp: delegatedEmps)
				{
					logger.info("delegated employee Summary : "+delEmp.toString());
					
					String 		empNumber	=	delEmp.getEmpNumber();
					Employee	employee	=	getEmployee(empNumber);
					logger.info("delegated emplyee details : "+employee.toString());
					Map<String,String> namedParameters 	= 	new HashMap<String,String>();
					namedParameters.put("paramLeaveReqNo", requestNo);
					namedParameters.put("paramDelEmpCode",empNumber);
					namedParameters.put("paramDelEmpBranchCode",employee.getBranchCode());
					namedParameters.put("paramDelEmpDeptCode",employee.getDepartmentCode());
					namedParameters.put("paramDelEmpSectionCode",employee.getSectionCode());
					namedParameters.put("paramDelEmpDesig",employee.getDesignationCode());
					namedParameters.put("paramDelEmpGradeCode",employee.getGradeCode());
					namedParameters.put("paramDelFromDate",delEmp.getFromDate());
					namedParameters.put("paramDelToDate",delEmp.getToDate());
					namedParameters.put("paramDelCreUser",Constants.USER_WEB);
					
					this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQ_DELEGATION, namedParameters);
				}
		}
		catch(Exception ex)
		{
			logger.warn("Error in Delegated employees. error description : "+ex.getMessage());
		}
	}
	
	/**
	 * 
	 * method name  : getLeaveRequests
	 * @param empNumber
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveRequest>
	 * 
	 * purpose		: Get list of leave requests
	 *
	 * Date    		:	Sep 12, 2012 12:38:08 PM
	 */
	public List<LeaveRequest>	getLeaveRequests(String empNumber,Employee employee, Locale locale)
	{
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{
			
			public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveRequest	leaveRequest	=	new LeaveRequest();
				LeaveType		leaveType		=	new LeaveType();
				
				leaveRequest.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
				leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_REQ_DATE));
				leaveRequest.setLeaveStartDate(rs.getString(Constants.CONST_LEAVE_START_DATE));
				leaveRequest.setLeaveEndDate(rs.getString(Constants.CONST_LEAVE_END_DATE));
					leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
					leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
				leaveRequest.setLeaveType(leaveType);
				leaveRequest.setLeaveStatus(rs.getString(Constants.CONST_LEAVE_STATUS));
				return leaveRequest;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", empNumber);
		namedParameters.put("paramHierarchy", employee.getHierarchyCode());
		namedParameters.put("paramBranchCode", employee.getBranchCode());
		namedParameters.put("paramDeptCode", employee.getDepartmentCode());
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_LEAVE_REQUEST, namedParameters, mapper);
	}
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param reqNo
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Sep 18, 2012 2:14:04 PM
	 */
	public LeaveRequest	getLeaveRequest(String reqNo, Locale locale)
	{
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{

			public LeaveRequest mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				LeaveRequest	leaveRequest	=	new LeaveRequest();
				LeaveStatus		leaveStatus		=	new LeaveStatus();
				LeaveType		leaveType		=	new LeaveType();
				Employee		employee		=	new	Employee();
					leaveRequest.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
					leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_REQ_DATE));
						leaveStatus.setStatusCode(rs.getString(Constants.CONST_LEAVE_STATUS_CODE));
						leaveStatus.setStatusDesc(rs.getString(Constants.CONST_LEAVE_STATUS));
					leaveRequest.setStatus(leaveStatus);
					leaveRequest.setLeaveStartDate(rs.getString(Constants.CONST_LEAVE_START_DATE));
					leaveRequest.setLeaveEndDate(rs.getString(Constants.CONST_LEAVE_END_DATE));
						leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
						leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
					leaveRequest.setLeaveType(leaveType);
					leaveRequest.setLeaveRequestRemarks(rs.getString(Constants.CONST_LEAVE_REQ_REMARK));
						employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
							if(rs.getString(Constants.CONST_EMP_ADMIN).equalsIgnoreCase(Constants.CONST_LEAVE_REQ_ACTIVE_TRUE))
							{
								employee.setAdmin2(true);
							}
							else
							{
								employee.setAdmin2(false);
							}
						employee.setDepartment2code(rs.getString(Constants.CONST_EMP_DEPARTMENT_CODE));
						employee.setDepartment2(rs.getString(Constants.CONST_EMP_DEPARTMENT));
						employee.setDesignationAddlCode(rs.getString(Constants.CONST_EMP_ADDL_POSITION_CODE));
						employee.setDesignationAddl(rs.getString(Constants.CONST_EMP_ADDL_POSITION_DESC));
					leaveRequest.setEmployee(employee);
					
				return leaveRequest;
			
		}
	};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", reqNo);
		
		return this.namedParameterJdbcTemplate.queryForObject(Constants.SQL_VIEW_LEAVE_REQUEST_SPECIFIC, namedParameters, mapper);
		
	}
	
	
	/**
	 * 
	 * method name  : getDepartments
	 * @param branchCode
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<Department>
	 * 
	 * purpose		: Get list of departments
	 *
	 * Date    		:	Sep 15, 2012 10:56:21 AM
	 */
	public List<Department>	getDepartments(String branchCode,Locale locale)
	{
		RowMapper<Department> mapper	=	new RowMapper<Department>()
		{
			
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Department	department		=	new Department();
				department.setDeptCode(rs.getString(Constants.CONST_EMP_DEPARTMENT_CODE));
				department.setDeptDesc(rs.getString(Constants.CONST_EMP_DEPARTMENT));
				return department;
			}
		};
		
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramBranchCode", branchCode);
		return this.namedParameterJdbcTemplate.query(Constants.SQL_DEPARTMENT, namedParameters, mapper);
	}
	
//	public	int setLeaveApproval(LeaveApprove approve)
//	{
//		
//	}
	
	
	/**
	 * 
	 * method name  : getLeaveRequestCounter
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : long
	 * 
	 * purpose		: 
	 *
	 * Date    		:	Sep 8, 2012 12:53:38 PM
	 */
	private long getLeaveRequestCounter()
	{
		return jdbcTemplate.queryForLong(Constants.SQL_COUNTER_LEAVE_REQUEST);
	}
	
	
	
	
}
