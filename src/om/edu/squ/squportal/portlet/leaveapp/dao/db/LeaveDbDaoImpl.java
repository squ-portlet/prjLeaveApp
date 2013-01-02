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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

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
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveStatus;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
import om.edu.squ.squportal.portlet.leaveapp.exception.DbNotAvailableException;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
		this.simpleJdbcCall				=	new SimpleJdbcCall(this.jdbcTemplate);
											
		
		
		;
	}

	/**
	 * 
	 * method name  : getLeaveTypes
	 * @param employee
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : List<LeaveType>
	 * 
	 * purpose		: List of available leave types
	 *
	 * Date    		:	Sep 25, 2012 1:04:27 PM
	 */
	public List<LeaveType>	getLeaveTypes(Employee employee, Locale locale)
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
		
		if(null != employee.getReligionCode() && employee.getReligionCode().equals(Constants.CONST_MUSLIM_NUM))
		{
			namedParameters.put("paramMuslim", Constants.CONST_MUSLIM);
		}
		else
		{
			if(null == employee.getReligionCode())
			{
				logger.error(" Error : Religion is null ");
			}
			
			namedParameters.put("paramMuslim", Constants.CONST_NON_MUSLIM);
		}
		if(null != employee.getGender() && employee.getGender().equals(Constants.CONST_GENDER_FEMALE_NUM))
		{
			namedParameters.put("paramFemale", Constants.CONST_GENDER_FEMALE);
		}
		else
		{
			if(null == employee.getGender())
			{
				logger.error(" Error : Gender is null ");
			}
			namedParameters.put("paramFemale", Constants.CONST_GENDER_NON_FEMALE);
		}
		if(employee.isOmani())
		{
			namedParameters.put("paramOmani", Constants.CONST_OMANI);
		}
		else
		{
			namedParameters.put("paramOmani",Constants.CONST_NON_OMANI);
		}
		
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
	public Employee	getEmployee(String empNumber,Locale locale) throws DbNotAvailableException
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
				employee.setBranchAddlCode(rs.getString(Constants.CONST_EMP_BRANCH_ADDL_CODE));
				employee.setDepartmentCode(rs.getString(Constants.CONST_EMP_DEPARTMENT_CODE));
				employee.setDepartment(rs.getString(Constants.CONST_EMP_DEPARTMENT));
				employee.setDepartmentAddlCode(rs.getString(Constants.CONST_EMP_DEPARTMENT_ADDL_CODE));
				employee.setDepartmentShort(rs.getString(Constants.CONST_EMP_DEPARTMENT_SHORT));
				employee.setSectionCode(rs.getString(Constants.CONST_EMP_SECTION_CODE));
				employee.setSection(rs.getString(Constants.CONST_EMP_SECTION));
				employee.setJoinDt(rs.getString(Constants.CONST_EMP_JOIN_DATE));
				employee.setHierarchyCode(rs.getString(Constants.CONST_EMP_HIERARCHY_CODE));
				employee.setHierarchyLevelCode(rs.getString(Constants.CONST_EMP_LEVEL));
				employee.setHierarchyAddlCode(rs.getString(Constants.CONST_EMP_ADDL_HIERARCHY_CODE));
				employee.setHierarchyAddlLevelCode(rs.getString(Constants.CONST_EMP_ADDL_LEVEL));
				if(rs.getString(Constants.CONST_EMP_ADMIN.toUpperCase()).equals("Y"))
				{
					employee.setAdmin(true);
				}
				else
				{
					employee.setAdmin(false);
				}
				employee.setReligionCode(rs.getString(Constants.CONST_EMP_RELIGION_CODE));
				if(rs.getString(Constants.CONST_EMP_OMANI).equals(Constants.CONST_OMANI_NUM))
				{
					employee.setOmani(true);
				}
				else
				{
					employee.setOmani(false);
				}
				employee.setGender(rs.getString(Constants.CONST_EMP_SEX));
				return employee;
			}
		};
		Map<String,String> namedParameters = new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", empNumber);
	
		logger.info("employee param : "+namedParameters);
		logger.info("employee sql : "+Constants.SQL_EMPLOYEE);
		
		return this.namedParameterJdbcTemplate.queryForObject(Constants.SQL_EMPLOYEE, namedParameters, mapper);
		
	}
	
	/**
	 * 
	 * method name  : getEmployee
	 * @param empNumber
	 * @param branchCode
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : Employee
	 * 
	 * purpose		: Get List of Employees with limited information
	 *
	 * Date    		:	Sep 16, 2012 9:05:32 AM
	 */
	public List<Employee>	getEmployee(String empNumber, String branchCode,  Locale locale )
	{
		String			deptCode		=	null;
		String			empLevelCode	=	null;
		int 			intEmpLevelCode	=	0;		
		List<Employee>	lstEmployees	=	null;
		try
		{
			Employee	employee	=	getEmployee(empNumber);
			if(employee.getBranchCode().equals(branchCode))
			{
				deptCode		=	employee.getDepartmentCode();
				empLevelCode	=	employee.getHierarchyLevelCode();
			}
			else if(employee.getBranchAddlCode().equals(branchCode))
			{
				empLevelCode	=	employee.getHierarchyAddlLevelCode();
			}
			
			intEmpLevelCode	=	Integer.parseInt(empLevelCode);
			
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
			
			for (int i=intEmpLevelCode+1; i<=Integer.parseInt(Constants.CONST_EMPLOYEE_LEVEL);i++)
			{
				Map<String,String> namedParameters = new HashMap<String,String>();
				namedParameters.put("paramLocale", locale.getLanguage());
				namedParameters.put("paramBranchCode", branchCode);
				namedParameters.put("paramDept", deptCode);
				namedParameters.put("paramLevel", String.valueOf(i));
				try
				{
					lstEmployees	=	this.namedParameterJdbcTemplate.query(Constants.SQL_EMP_BRANCH_DEPT_SHORT, namedParameters, mapper);
					if(lstEmployees.size() != 0)
					{
						break;
					}
				}
				catch(Exception ex)
				{
					logger.info("no emp data found for level : '"+i+"'; error : "+ex);
				}

			}
		}
		catch(Exception ex)
		{
			logger.info("Error generated: can not fetch employee list");
		}
		
		
		return	lstEmployees;
		
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
	 * method name  : getAllowEleaveRequest
	 * @param leaveRequest
	 * @param locale
	 * @return
	 * @throws ParseException
	 * LeaveDbDaoImpl
	 * return type  : AllowEleaveRequestProc
	 * 
	 * purpose		: Check before insert leave request
	 *
	 * Date    		:	Sep 26, 2012 2:20:20 PM
	 */
	@Transactional("trLeaveReq")
	public synchronized AllowEleaveRequestProc	getAllowEleaveRequest(LeaveRequest leaveRequest, Locale locale) 
																				throws ParseException
	{
		LeaveType	leaveType	=	new LeaveType();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Date				startDate			=	new Date (df.parse(leaveRequest.getLeaveStartDate()).getTime());
		Date				endDate				=	new Date (df.parse(leaveRequest.getLeaveEndDate()).getTime());

		simpleJdbcCall.withProcedureName(Constants.CONST_PROC_ALLOW_eLEAVE_REQUEST);
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
		simpleJdbcCall.useInParameterNames(
											Constants.CONST_PROC_COL_IN_P_EMP_CODE,
											Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG,
											Constants.CONST_PROC_COL_IN_P_LEAVE_START,
											Constants.CONST_PROC_COL_IN_P_LEAVE_END
											);
		simpleJdbcCall.declareParameters(
											new SqlParameter(Constants.CONST_PROC_COL_IN_P_EMP_CODE, Types.VARCHAR),
											new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG, Types.VARCHAR),
											new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_START, Types.DATE),
											new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_END, Types.DATE),
											new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN, Types.VARCHAR),
											new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_LEAVE_CODE, Types.VARCHAR),
											new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ENGLISH, Types.VARCHAR),
											new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ARABIC, Types.VARCHAR)
											
										);

		Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
					paramIn.put(Constants.CONST_PROC_COL_IN_P_EMP_CODE, leaveRequest.getEmployee().getEmpNumber());
					paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG, leaveRequest.getLeaveTypeFlag().getTypeNo());
					paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_START, startDate);
					paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_END, endDate);

		Map						result			=	simpleJdbcCall.execute(paramIn);
		
		AllowEleaveRequestProc	requestProc		=	new AllowEleaveRequestProc();
		if(
				((String)result.get(Constants.CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN))
					.equalsIgnoreCase("Y")
		  )
		{
			requestProc.setAcceptLeave(true);
		}
		else
		{
			requestProc.setAcceptLeave(false);
		}

		requestProc.setLeaveCode((String)result.get(Constants.CONST_PROC_COL_OUT_P_LEAVE_CODE));
		if(locale.getLanguage().equals(Constants.CONST_LANG_DEFAULT_EN))
		{
			requestProc.setLeaveMessage((String)result.get(Constants.CONST_PROC_COL_OUT_P_MSG_ENGLISH));
		}
		else
		{
			requestProc.setLeaveMessage((String)result.get(Constants.CONST_PROC_COL_OUT_P_MSG_ARABIC));
		}
		


		
		
		
		return requestProc;
	}
	
	/**
	 * 
	 * method name  : getDepartmentHead
	 * @param branchCode
	 * @param deptCode
	 * @param 
	 * @param locale
	 * @return
	 * LeaveDbDao
	 * return type  : List<HoD>
	 * 
	 * purpose		: get department head id and name
	 *
	 * Date    		:	Dec 5, 2012 9:34:39 AM
	 */
	public List<HoD> getDepartmentHead(String branchCode, String deptCode,String empLevel, Locale locale)
	{
		RowMapper<HoD> mapper	=	new RowMapper<HoD>()
		{
			
			public HoD mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				HoD	hod		=	new HoD();
				hod.setHodId(rs.getString(Constants.CONST_EMP_CODE));
				hod.setHodName(
						rs.getString(Constants.CONST_EMP_NAME)
						+"<br>"+UtilProperty.getMessage("prop.leave.app.department.head", null)
						+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
							);
				return hod;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramBranchCode", branchCode);
		namedParameters.put("paramDept", deptCode);
		if(null == empLevel || empLevel.trim().equals(""))
		{
			empLevel = Constants.CONST_NOT_AVAILABLE;
		}
		namedParameters.put("paramEmpLevel", empLevel);
		namedParameters.put("paramLocale", locale.getLanguage());
		
		logger.info("param HOD branch/Dept id : "+namedParameters);
		logger.info("HOD SQL"+Constants.SQL_VIEW_DEPT_HEAD_ID);
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_DEPT_HEAD_ID, namedParameters, mapper);
	}
	
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
	public List<HoD>	getSectionHead(String branchCode, String deptCode, String sectCode, String empLevel, Locale locale)
	{
		RowMapper<HoD> mapper = new RowMapper<HoD>()
		{

			public HoD mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				HoD		hod		=	new HoD();
						hod.setHodId(rs.getString(Constants.CONST_EMP_CODE));
						hod.setHodName(rs.getString(Constants.CONST_EMP_NAME)
								+"<br> "+UtilProperty.getMessage("prop.leave.app.section.head", null)
								+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
								);
				return hod;		
				
			}
			
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramBranchCode", branchCode);
		namedParameters.put("paramDept", deptCode);
		namedParameters.put("paramSectCode", sectCode);
		if(null == empLevel || empLevel.trim().equals(""))
		{
			empLevel = Constants.CONST_NOT_AVAILABLE;
		}
		namedParameters.put("paramEmpLevel", empLevel);

		namedParameters.put("paramLocale", locale.getLanguage());
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_SECTION_HEAD_ID, namedParameters, mapper);
		
	}
	
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
	public List<HoD> getNextHeadBranch(String branchCode, int paramLevelAdd, String empLevel, Locale locale)
	{
		RowMapper<HoD> 	mapper	=	new RowMapper<HoD>()
		{
			
			public HoD mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				HoD		hod		=	new HoD();
				hod.setHodId(rs.getString(Constants.CONST_EMP_CODE));
				hod.setHodName(rs.getString(Constants.CONST_EMP_NAME)
						+ "<br> " +UtilProperty.getMessage("prop.leave.app.higher.head", null)
						+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
				);

				return hod;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramBranchCode", branchCode);
		namedParameters.put("paramLevelAdd", String.valueOf(paramLevelAdd));
		if(null == empLevel || empLevel.trim().equals(""))
		{
			empLevel = Constants.CONST_NOT_AVAILABLE;
		}
		namedParameters.put("paramEmpLevel", empLevel);
		namedParameters.put("paramLocale", locale.getLanguage());
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_BRANCH_HEAD_NEXT_HIERARCHY, namedParameters, mapper);
	}
	
	/**
	 * 
	 * method name  : setNewLeaveRequest
	 * @param leaveRequest
	 * @param delegatedEmps
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Insert new leave request
	 *
	 * Date    		:	Sep 10, 2012 2:47:24 PM
	 */
	@Transactional("trLeaveReq")
	public synchronized int setNewLeaveRequest(LeaveRequest leaveRequest, DelegatedEmp[] delegatedEmps, Locale locale )
	{
		int			result					=	0;
		String		leaveRequestNo			=	null;
		if (
				null == leaveRequest.getRequestNo() ||
				leaveRequest.getRequestNo().trim().equals("") ||
				leaveRequest.getRequestNo().trim().equals(Constants.CONST_NOT_AVAILABLE)
				)
		{
			leaveRequestNo			=	String.valueOf(getLeaveRequestCounter());
		}
		else
		{
			leaveRequestNo			=	leaveRequest.getRequestNo();
		}
		
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
		namedParameters.put("paramReqUserInit",Constants.USER_WEB);
		namedParameters.put("paramLeaveTypeFlag",leaveRequest.getLeaveTypeFlag().getTypeNo());
		//namedParameters.put("paramReqCreDate",);
		
		namedParameters.put("paramHodId", emp.getMyHodId());
		
		
		logger.info("request insert statement :" +Constants.SQL_INSERT_LEAVE_REQUEST);
		logger.info("request insert param :" +namedParameters);
		
		if(
			null == leaveRequest.getRequestNo() || 
		   leaveRequest.getRequestNo().trim().equals("") || 
		   leaveRequest.getRequestNo().trim().equals(Constants.CONST_NOT_AVAILABLE))
		{
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQUEST, namedParameters);
		}
		else
		{
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQUEST, namedParameters);
			LeaveApprove	approve		=	new LeaveApprove();
			Employee		employee	=	new Employee();
			employee.setEmpNumber(emp.getMyHodId());
			approve.setEmployee(employee);
			approve.setRequestNo(leaveRequestNo);
			approve.setApproverRemark(UtilProperty.getMessage("prop.leave.app.apply.form.approvar.auto.remarks2", null, locale));
			
			//setLeaveApprove(approve);
		}

		
		if(null != delegatedEmps)
		{
			setNewLeaveDelegationRequest(leaveRequestNo,delegatedEmps,emp.getEmpNumber());
		}
		
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
	 * method name  : setNewLeaveDelegationRequest
	 * @param requestNo
	 * @param delegatedEmps
	 * @param orginEmpNumber
	 * LeaveDbDaoImpl
	 * return type  : void
	 * 
	 * purpose		: set delegation
	 *
	 * Date    		:	Dec 8, 2012 12:44:31 PM
	 */
	private	void	setNewLeaveDelegationRequest(String requestNo, DelegatedEmp[] delegatedEmps,String orginEmpNumber )
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
					namedParameters.put("paramOriginEmpCode",orginEmpNumber);
					logger.info("delegated param : "+namedParameters);
					
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
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale)
	{
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{
			
			public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveRequest	leaveRequest	=	new LeaveRequest();
				LeaveType		leaveType		=	new LeaveType();
				LeaveStatus		leaveStatus		=	new LeaveStatus();
				Employee		employee		=	new	Employee();
				
				leaveRequest.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
				leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_REQ_DATE));
				leaveRequest.setLeaveStartDate(rs.getString(Constants.CONST_LEAVE_START_DATE));
				leaveRequest.setLeaveEndDate(rs.getString(Constants.CONST_LEAVE_END_DATE));
					leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
					leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
				leaveRequest.setLeaveType(leaveType);
				leaveRequest.setLeaveStatus(rs.getString(Constants.CONST_LEAVE_STATUS));
					leaveStatus.setStatusCode(rs.getString(Constants.CONST_LEAVE_STATUS_CODE));
					leaveStatus.setStatusDesc(rs.getString(Constants.CONST_LEAVE_STATUS));
				leaveRequest.setStatus(leaveStatus);
					employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
					employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
					employee.setHierarchyCode(rs.getString(Constants.CONST_EMP_HIERARCHY_CODE));
					if(rs.getString(Constants.CONST_DELEGATE_STATUS).equals(Constants.CONST_EMP_SENIOR))
					{
						employee.setSenior(true);
					}
					else
					{
						employee.setSenior(false);
					}
				leaveRequest.setEmployee(employee);
				return leaveRequest;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", employee.getEmpNumber());
		namedParameters.put("paramLevel", employee.getHierarchyLevelCode());

		logger.info("param : "+namedParameters);
		
		List<LeaveRequest>	leaveRequests	=	this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_LEAVE_REQUEST, namedParameters, mapper);
		
		logger.info("leave request : "+leaveRequests);
		
		
		return leaveRequests;
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
				LeaveType		leaveTypeFlag	=	new LeaveType();
				Employee		employee		=	new	Employee();
				LeaveApprove	approve			=	new LeaveApprove();
					leaveRequest.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
					leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_REQ_DATE));
						leaveStatus.setStatusCode(rs.getString(Constants.CONST_LEAVE_STATUS_CODE));
						leaveStatus.setStatusDesc(rs.getString(Constants.CONST_LEAVE_STATUS));
					leaveRequest.setStatus(leaveStatus);
					leaveRequest.setLeaveStartDate(rs.getString(Constants.CONST_LEAVE_START_DATE));
					leaveRequest.setLeaveEndDate(rs.getString(Constants.CONST_LEAVE_END_DATE));
						leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
						leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
						leaveTypeFlag.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE_FLAG));
						leaveTypeFlag.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
					leaveRequest.setLeaveType(leaveType);
					leaveRequest.setLeaveTypeFlag(leaveTypeFlag);
					leaveRequest.setLeaveRequestRemarks(rs.getString(Constants.CONST_LEAVE_REQ_REMARK));
						employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
							if(null!= rs.getString(Constants.CONST_EMP_ADMIN) &&  
									rs.getString(Constants.CONST_EMP_ADMIN).equalsIgnoreCase
									(Constants.CONST_LEAVE_REQ_ACTIVE_TRUE)
								)
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
					approve.setBranchCode(rs.getString(Constants.CONST_EMP_APP_BRANCH_CODE));
					approve.setDepartmentCode(rs.getString(Constants.CONST_EMP_APP_DEPARTMENT_CODE));
					approve.setSectionCode(rs.getString(Constants.CONST_EMP_APP_SECTION_CODE));
					approve.setApproverAction(rs.getString(Constants.CONST_ACTION_CODE));
					approve.setApproverRemark(rs.getString(Constants.CONST_APPROVER_REMARK));
					leaveRequest.setSuggestedHod(rs.getString(Constants.CONST_SUGGESTED_APPROVER_CODE));
					leaveRequest.setApprove(approve);
					
				return leaveRequest;
			
		}
	};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", reqNo);
		
		
		logger.info("sepecific leave request param : "+namedParameters);
		logger.info("sepecific leave request SQL : "+Constants.SQL_VIEW_LEAVE_REQUEST_SPECIFIC);
		
		return this.namedParameterJdbcTemplate.queryForObject(Constants.SQL_VIEW_LEAVE_REQUEST_SPECIFIC, namedParameters, mapper);
		
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
		RowMapper<Branch> mapper	=	new RowMapper<Branch>()
		{
			
			public Branch mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Branch	branch		=	new Branch();
				branch.setBranchCode(rs.getString(Constants.CONST_EMP_BRANCH_CODE));
				branch.setBranchDesc(rs.getString(Constants.CONST_EMP_BRANCH));
				return branch;
			}
		};
		
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		return this.namedParameterJdbcTemplate.query(Constants.SQL_BRANCH, namedParameters, mapper);
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
	public List<Branch> getBranches (String empNumber, Locale locale)
	{
		RowMapper<Branch> mapper	=	new RowMapper<Branch>()
		{
			
			public Branch mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Branch	branch		=	new Branch();
				branch.setBranchCode(rs.getString(Constants.CONST_EMP_BRANCH_CODE));
				branch.setBranchDesc(rs.getString(Constants.CONST_EMP_BRANCH));
				return branch;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNo", empNumber);
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_BRANCH_USING_EMPNO, namedParameters, mapper);
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
		RowMapper<Section> mapper	=	new RowMapper<Section>()
		{
			
			public Section mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Section	section		=	new Section();
				section.setSectionCode(rs.getString(Constants.CONST_EMP_SECTION_CODE));
				section.setSectionDesc(rs.getString(Constants.CONST_EMP_SECTION));
				return section;
			}
		};
		
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramDept", departmentCode);
		return this.namedParameterJdbcTemplate.query(Constants.SQL_SECTION, namedParameters, mapper);
	}
	
	
	/**
	 * 
	 * method name  : setLeaveApprove
	 * @param approve
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		:  Set leave approve
	 *
	 * Date    		:	Oct 3, 2012 1:51:11 PM
	 */
	@Transactional("trLeaveAprv")
	public synchronized int setLeaveApprove(LeaveApprove approve)
	{
		int result	=	0;
		
		Employee	employee	=	approve.getEmployee();

		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
			namedParameters.put("paramReqNo", approve.getRequestNo());
			namedParameters.put("paramEmpNo", employee.getEmpNumber());
			namedParameters.put("paramActionCode",approve.getApproverAction());
			namedParameters.put("paramApprvRemark", approve.getApproverRemark());

			if(isApprovalRecordExist(approve.getRequestNo(), employee.getEmpNumber()))
		{
				
			namedParameters.put("paramUpdateUsr", Constants.USER_WEB);
			namedParameters.put("paramActionCodeApprove", Constants.CONST_LEAVE_ACTION_APPROVE);
			logger.info("update leave approve sql param : "+namedParameters);
			logger.info("update leave approve sql : " +Constants.SQL_UPDATE_LEAVE_REQ_APPROVE);
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQ_APPROVE,namedParameters );
			logger.info("update transaction status info : "+result);
			
		}
		else
		{
			namedParameters.put("paramEmpInternetId", employee.getEmpInternetId());
			namedParameters.put("paramHierarchyCode", employee.getHierarchyCode());
			namedParameters.put("paramBranchCode", employee.getBranchCode());
			namedParameters.put("paramDeptCode", employee.getDepartmentCode());
			namedParameters.put("paramSectionCode", employee.getSectionCode());
			namedParameters.put("paramDesgCode", employee.getDesignationCode());
			namedParameters.put("paramCreateUsr", Constants.USER_WEB);
			namedParameters.put("paramSeqNo", String.valueOf(getLeaveApproveCounter()));
			
			logger.info("insert leave approve sql param : "+namedParameters);
			logger.info("insert leave approve sql : " +Constants.SQL_INSERT_LEAVE_REQ_APPROVE);
			
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQ_APPROVE,namedParameters );
			logger.info("insert transaction status info : "+result);
		}

		logger.info("params for approval : "+namedParameters);
		
		
		int result2 = setLeaveRequestStatusUpdate(approve.getRequestNo(), approve.getApproverAction());
		logger.info("transaction status info : "+result2);
		return result;
		
	}
	
	
	
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
	private long getLeaveRequestCounter()
	{
		
		return jdbcTemplate.queryForLong(Constants.SQL_COUNTER_LEAVE_REQUEST);
	}
	/**
	 * 
	 * method name  : isApprovalRecordExist
	 * @param reqNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : boolean
	 * 
	 * purpose		:
	 *
	 * Date    		:	Oct 9, 2012 10:03:31 AM
	 */
	private	boolean isApprovalRecordExist(String reqNo, String empNumber)
	{
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo", reqNo);
		namedParameters.put("paramEmpNo", empNumber);
		
		int recNum	=namedParameterJdbcTemplate.queryForInt(Constants.SQL_REC_COUNTER_LEAVE_APPROVE, namedParameters);
		return (recNum>0)?true:false;
	}
	
	/**
	 * 
	 * method name  : getLeaveApproveCounter
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : long
	 * 
	 * purpose		: Get the next higher number for leave approve
	 *
	 * Date    		:	Oct 3, 2012 1:46:46 PM
	 */
	private long getLeaveApproveCounter()
	{
		return jdbcTemplate.queryForLong(Constants.SQL_COUNTER_LEAVE_APPROVE);
	}
	
	/**
	 * 
	 * method name  : setLeaveRequestStatusUpdate
	 * @param requestNo
	 * @param approveAction
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: update the leave request status
	 *
	 * Date    		:	Oct 8, 2012 12:19:59 PM
	 */
	@Transactional("trLeaveAprv")
	private int setLeaveRequestStatusUpdate(String requestNo, String approveAction)
	{
		String leaveStatus	=	null;
		if((null== approveAction) || (approveAction.trim().equals("")))
		{
			leaveStatus	=	Constants.CONST_LEAVE_STATUS_WAITING_APPV;
		}
		else
		{
			if(approveAction.equals(Constants.CONST_LEAVE_ACTION_APPROVE))
			{
				leaveStatus	=	Constants.CONST_LEAVE_STATUS_APPROVED;
			}
			if(approveAction.equals(Constants.CONST_LEAVE_ACTION_RETURN))
			{
				leaveStatus	=	Constants.CONST_LEAVE_STATUS_FURTHER_CLARIFICATION;
			}
			if(approveAction.equals(Constants.CONST_LEAVE_ACTION_REJECT))
			{
				leaveStatus	=	Constants.CONST_LEAVE_STATUS_REJECTED;
			}
		}
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo", requestNo);
		namedParameters.put("paramStatusCode", leaveStatus);
		
		logger.info("leave request status update; param: "+namedParameters);
		logger.info("leave request status update; SQL: "+Constants.SQL_UPDATE_LEAVE_REQ_STATUS);
		
		return namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQ_STATUS, namedParameters);
	}
	
	/**
	 * 
	 * method name  : getMapingReqStatusAction
	 * @param reqStatus
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : String
	 * 
	 * purpose		: get Request Action code against request status code
	 *
	 * Date    		:	Oct 8, 2012 1:54:20 PM
	 */
	private String	getMapingReqStatusAction(String reqStatus)
	{
		Map<String,String>	mapStatusAction		=	new HashMap<String, String>();
		mapStatusAction.put(Constants.CONST_LEAVE_STATUS_APPROVED, Constants.CONST_LEAVE_ACTION_APPROVE);
		mapStatusAction.put(Constants.CONST_LEAVE_STATUS_WAITING_APPV, Constants.CONST_LEAVE_ACTION_RETURN);
		mapStatusAction.put(Constants.CONST_LEAVE_STATUS_REJECTED, Constants.CONST_LEAVE_ACTION_REJECT);
		return mapStatusAction.get(reqStatus);
	}
	
}
