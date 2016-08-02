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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.leaveapp.bo.AdminAction;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.Budget;
import om.edu.squ.squportal.portlet.leaveapp.bo.CheckLeaveDelegation;
import om.edu.squ.squportal.portlet.leaveapp.bo.CheckLeaveResearch;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Designation;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.HoD;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveApprove;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveStatus;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.bo.Sabbatical;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
import om.edu.squ.squportal.portlet.leaveapp.exception.DbNotAvailableException;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.DateChange;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailGeneral;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailLeave;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailService;

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
	private	EmailService					emailService;
	private	Properties						queryPropsReturn;
	
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
		this.jdbcTemplate				=	new JdbcTemplate(dataSource);
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
		;
	}
	
	/**
	 * 
	 * method name  : setEmailService
	 * @param emailService
	 * LeaveDbDaoImpl
	 * return type  : void
	 * 
	 * purpose		: set Email service bean
	 *
	 * Date    		:	Mar 5, 2013 11:57:21 AM
	 */
	public void setEmailService(EmailService emailService)
	{
		this.emailService	=	emailService;
	}

	
	
	/**
	 * Setter method : setQueryPropsReturn
	 * @param queryPropsReturn the queryPropsReturn to set
	 * 
	 * Date          : May 31, 2016 1:30:55 PM
	 */
	public void setQueryPropsReturn(Properties queryPropsReturn)
	{
		this.queryPropsReturn = queryPropsReturn;
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
				logger.warn(" Warning : Religion is null ");
				namedParameters.put("paramMuslim", Constants.CONST_MUSLIM);
			}
			else
			{
				namedParameters.put("paramMuslim", Constants.CONST_NON_MUSLIM);
			}
			//TODO as per bug id : 1268 null value at employee religion code is considered as MUSLIM
			// this will be changed as soon as correct data enterted at VHM_EMPLOYEE at EMP_RELIGION_CODE
			// and below code will be uncommented and within the if statement above the code will be removed / commented
			//namedParameters.put("paramMuslim", Constants.CONST_NON_MUSLIM);
		}
		if(null != employee.getGender() && employee.getGender().equals(Constants.CONST_GENDER_FEMALE_NUM))
		{
			namedParameters.put("paramFemale", Constants.CONST_GENDER_FEMALE);
		}
		else
		{
			if(null == employee.getGender())
			{
				logger.warn(" Warning : Gender is null ");
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
				employee.setEmpSquEmail(rs.getString(Constants.CONST_EMP_EMAIL_ID));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				employee.setEmpNameEn(rs.getString(Constants.CONST_EMP_NAME_EN));
				employee.setEmpNameAr(rs.getString(Constants.CONST_EMP_NAME_AR));
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
		String			deptCode			=	null;
		String			empLevelCode		=	null;
		int 			intEmpLevelCode		=	0;		
		List<Employee>	lstEmployees		=	null;
		List<Employee>	lstLevelEmployees	=	new ArrayList<Employee>();
		List<Employee>	lstDeptEmployees	=	new ArrayList<Employee>();
		List			lstResult				=	new ArrayList();
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
				deptCode		=	employee.getDepartmentAddlCode();
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
			
			/**
			 * Fetch employee list based on level
			 */
			int delegationLevelCounter	=	0;
			for (int i=intEmpLevelCode+1; i<Integer.parseInt(Constants.CONST_EMPLOYEE_LEVEL);i++)
			{
				Map<String,String> namedParameters = new HashMap<String,String>();
				namedParameters.put("paramLocale", locale.getLanguage());
				namedParameters.put("paramBranchCode", branchCode);
				namedParameters.put("paramLevel", String.valueOf(i));

				try
				{
					lstEmployees	=	this.namedParameterJdbcTemplate.query(Constants.SQL_EMP_BRANCH_SHORT, namedParameters, mapper);
					if(lstEmployees.size() != 0)
					{
						List<Employee>	lstTemp	=	new ArrayList<Employee>();
										lstTemp.addAll(lstEmployees);
						lstLevelEmployees.addAll(lstTemp);
						lstTemp	=	null;
						delegationLevelCounter++;
						if(delegationLevelCounter == Constants.CONST_DELEGATION_LEVEL_COUNT)
						{
							break;
						}
						
					}
				}
				catch(Exception ex)
				{
					logger.warn("no delegated emp data found for level : '"+i+"'; error : "+ex);
				}

			}
					lstEmployees	=	null;
			
			/**
			 * Fetch employee list from own department
			 */
			
			try
			{
				for (int i=intEmpLevelCode+1; i<=Integer.parseInt(Constants.CONST_EMPLOYEE_LEVEL);i++)
				{
					Map<String,String> namedParameters = new HashMap<String,String>();
					namedParameters.put("paramLocale", locale.getLanguage());
					namedParameters.put("paramBranchCode", branchCode);
					namedParameters.put("paramDept", deptCode);
					namedParameters.put("paramEmpNumber", empNumber);
					namedParameters.put("paramLevel", String.valueOf(i));
					
					lstEmployees	=	this.namedParameterJdbcTemplate.query(Constants.SQL_EMP_BRANCH_DEPT_SHORT, namedParameters, mapper);
					lstDeptEmployees.addAll(lstEmployees);
					if(lstEmployees.size() != 0)
					{
						break;
					}
				}
			}
			catch(Exception ex)
			{
				logger.warn("no delegated emp data found for department : '"+deptCode+"' -- error : "+ex);
				
			}
			
		}
		catch(Exception ex)
		{
			logger.error("Error generated: can not fetch employee list");
		}
		

			/**
			 * Remove the similar employee object from lstLevelEmployeesList
			 */
		try{
			for(int i=0; i<lstDeptEmployees.size(); i++)
			{
				Employee	deptEmp = lstDeptEmployees.get(i);
				for(int j=0; j<lstLevelEmployees.size(); j++)
				{
					Employee levelEmp	=	lstLevelEmployees.get(j);
					if(deptEmp.equals(levelEmp))
					{
						lstLevelEmployees.remove(j);
						break;
					}
					
				}
				
			}
		}
		catch(Exception ex)
		{
			logger.warn("issues with comparing two employee object list, for preparing final delegation list" );
		}
		
		if(lstDeptEmployees.size() != 0)
		{
			lstResult.add(lstDeptEmployees);
		}
		if(lstLevelEmployees.size() != 0)
		{
			lstResult.add(lstLevelEmployees);
		}

		
		return	lstResult;
		
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
		CheckLeaveResearch	leaveResearch		=	null;

		AllowEleaveRequestProc	requestProc		=	new AllowEleaveRequestProc();
		
		if(null != leaveRequest.getResearchId() && ! leaveRequest.getResearchId().trim().equals(""))
		{
			try
			{
				leaveResearch	=	 getCheckLeaveResearch(leaveRequest.getEmployee().getEmpNumber(), leaveRequest.getResearchId(),locale);
			}
			catch(Exception ex)
			{
				logger.error("error to validate researchid for employee : "+leaveRequest.getEmployee().getEmpNumber() +" for research id : "+leaveRequest.getResearchId()+ " -- ERROR : "+ex);
				ex.printStackTrace();
			}
			
		}
		if((null != leaveResearch))
		
		{
			if(! leaveResearch.isAccept())
			{
				requestProc.setAcceptLeave(leaveResearch.isAccept());
				requestProc.setLeaveMessage(leaveResearch.getMessage());
			}
			
		}

		
		if(((leaveResearch == null || (leaveResearch ==null) && (null == leaveRequest.getResearchId()) || leaveRequest.getResearchId().trim().equals(""))) ||
				((leaveResearch !=null )&& (leaveResearch.isAccept())))
		{
		
			this.simpleJdbcCall						=	new SimpleJdbcCall(this.jdbcTemplate);
			LeaveType	leaveType	=	new LeaveType();
			Map			resultProc		=	null;	
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			Date				startDate			=	new Date (df.parse(leaveRequest.getLeaveStartDate()).getTime());
			Date				endDate				=	new Date (df.parse(leaveRequest.getLeaveEndDate()).getTime());

			simpleJdbcCall.withProcedureName(Constants.CONST_PROC_ALLOW_eLEAVE_REQUEST);
			simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
			simpleJdbcCall.useInParameterNames(
												Constants.CONST_PROC_COL_IN_P_EMP_CODE,
												Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG,
												Constants.CONST_PROC_COL_IN_P_LEAVE_START,
												Constants.CONST_PROC_COL_IN_P_LEAVE_END,
												Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO,
												Constants.CONST_PROC_COL_IN_P_SUGGESTED_APP_EMP_CODE
												);
			simpleJdbcCall.declareParameters(
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_EMP_CODE, Types.VARCHAR),
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG, Types.VARCHAR),
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_START, Types.DATE),
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_END, Types.DATE),
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO, Types.VARCHAR),
												new SqlParameter(Constants.CONST_PROC_COL_IN_P_SUGGESTED_APP_EMP_CODE, Types.VARCHAR),
												new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN, Types.VARCHAR),
												new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_LEAVE_CODE, Types.VARCHAR),
												new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ENGLISH, Types.VARCHAR),
												new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ARABIC, Types.VARCHAR),
												new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_CHECKED_APP_EMP_CODE, Types.VARCHAR)
											);

			Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
						paramIn.put(Constants.CONST_PROC_COL_IN_P_EMP_CODE, leaveRequest.getEmployee().getEmpNumber());
						paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_FLAG, leaveRequest.getLeaveTypeFlag().getTypeNo());
						paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_START, startDate);
						paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_END, endDate);
						paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO, leaveRequest.getRequestNo());
						paramIn.put(Constants.CONST_PROC_COL_IN_P_SUGGESTED_APP_EMP_CODE, leaveRequest.getEmployee().getMyHodId());

			
			resultProc			=	simpleJdbcCall.execute(paramIn);
			
			if( (null != resultProc.get(Constants.CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN) ) && 
					((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN))
						.equalsIgnoreCase(Constants.CONST_YES_CAPITAL)
			  )
			{
				requestProc.setAcceptLeave(true);
			}
			else
			{
				requestProc.setAcceptLeave(false);
			}
	
			requestProc.setLeaveCode((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_LEAVE_CODE));
			if(locale.getLanguage().equals(Constants.CONST_LANG_DEFAULT_EN))
			{
				requestProc.setLeaveMessage((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_MSG_ENGLISH));
			}
			else
			{
				requestProc.setLeaveMessage((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_MSG_ARABIC));
			}
			requestProc.setCheckedAprroverEmpCode((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_CHECKED_APP_EMP_CODE));
			
			resultProc	=	null;
		}
		
		return requestProc;
	}

	
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
	@Transactional("trLeaveReq")
	public CheckLeaveResearch getCheckLeaveResearch(String empNumber, String researchId, Locale locale)
	{
		this.simpleJdbcCall				=	new SimpleJdbcCall(this.jdbcTemplate);
		Map			resultProc			=	null;
		simpleJdbcCall.withProcedureName(Constants.CONST_PROC_CHECK_ELEAVE_RESEARCH);
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
		simpleJdbcCall.useInParameterNames(
				Constants.CONST_PROC_COL_IN_P_RESEARCH_CODE,
				Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE_RESEARCH
				);
		simpleJdbcCall.declareParameters(
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_RESEARCH_CODE, Types.VARCHAR),
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE_RESEARCH, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_ACCEPT_YN_RESEARCH, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ENG_RESEARCH, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ARB_RESEARCH, Types.VARCHAR)
			);
		
		Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
		paramIn.put(Constants.CONST_PROC_COL_IN_P_RESEARCH_CODE, researchId);
		paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE_RESEARCH, empNumber);

							resultProc			=	simpleJdbcCall.execute(paramIn);
		CheckLeaveResearch	leaveResearch		=	new CheckLeaveResearch();
		leaveResearch.setResearchId(researchId);
		leaveResearch.setEmpCode(empNumber);
		if(
				((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_ACCEPT_YN_RESEARCH))
					.equalsIgnoreCase(Constants.CONST_YES_CAPITAL)
		  )
		{
			leaveResearch.setAccept(true);
		}
		else
		{
			leaveResearch.setAccept(false);
		}
		if(locale.getLanguage().equals(Constants.CONST_LANG_DEFAULT_EN))
		{
			leaveResearch.setMessage((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_MSG_ENG_RESEARCH));
		}
		else
		{
			leaveResearch.setMessage((String)resultProc.get(Constants.CONST_PROC_COL_OUT_P_MSG_ARB_RESEARCH));
		}
		
		return leaveResearch;
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
	public  Employee getManager(String empNumber, Locale locale)
	{
		this.simpleJdbcCall				=	new SimpleJdbcCall(this.jdbcTemplate);
		
		Map						result			=	null;
		String					mgrEmpNumber	=	null;
		Employee				mgrEmployee		=	null;
		
		
		simpleJdbcCall.withProcedureName(Constants.CONST_PROC_GET_HIGHER_MGR_PROCESS);
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
		simpleJdbcCall.useInParameterNames(Constants.CONST_PROC_COL_IN_P_EMP_CODE);
		simpleJdbcCall.declareParameters(
					new SqlParameter(Constants.CONST_PROC_COL_IN_P_EMP_CODE,Types.VARCHAR),
					new SqlOutParameter(Constants.CONST_PROC_COL_OUT_V_MGR_EMP, Types.VARCHAR),
					new SqlOutParameter(Constants.CONST_PROC_COL_OUT_V_HINT, Types.VARCHAR)
		);
		
		Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
		paramIn.put(Constants.CONST_PROC_COL_IN_P_EMP_CODE, empNumber);
		
		result			=	simpleJdbcCall.execute(paramIn);
		
		mgrEmpNumber	=	(String)result.get(Constants.CONST_PROC_COL_OUT_V_MGR_EMP);
		mgrEmployee		=	getEmployee(mgrEmpNumber, locale);
		
		result			=	null;
		return mgrEmployee;
	}
	
	/**
	 * 
	 * method name  : getCheckEleaveDelegations
	 * @param requestNo
	 * @param delegatedEmps
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : CheckLeaveDelegation
	 * 
	 * purpose		: Check delegation status
	 *
	 * Date    		:	Mar 13, 2013 2:07:33 PM
	 */
	@Transactional("trLeaveReq")
	public synchronized CheckLeaveDelegation	getCheckEleaveDelegations
																		(
																			String requestNo, DelegatedEmp[] delegatedEmps,
																			String empNumber,Locale locale
																		)
	{
		CheckLeaveDelegation	checkLeaveDelegation	=	null;
		for(DelegatedEmp delEmp: delegatedEmps)
		{
			if(!(null == delEmp.getEmpNumber() || delEmp.getEmpNumber().trim().equals("")) && 
					!(null == delEmp.getFromDate() || delEmp.getFromDate().trim().equals("")) &&
					!(null == delEmp.getToDate() || delEmp.getToDate().trim().equals(""))
			)
				{
					checkLeaveDelegation	=	checkEleaveDelegation(requestNo,delEmp,empNumber,locale);
					
					if((null == checkLeaveDelegation ) || (! checkLeaveDelegation.isAcceptDelegation()))
					{
						break;
					}
				}
		}
		return checkLeaveDelegation;
	}
	
	
	/**
	 * 
	 * method name  : checkEleaveDelegation
	 * @param requestNo
	 * @param delEmp
	 * @param empNumber
	 * @param locale
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : CheckLeaveDelegation
	 * 
	 * purpose		: Check Delegation leave status
	 *
	 * Date    		:	Mar 13, 2013 1:54:35 PM
	 */
	private synchronized CheckLeaveDelegation	checkEleaveDelegation
	(
		String requestNo, DelegatedEmp  delEmp,
		String empNumber,Locale locale
	)
	{
		
		this.simpleJdbcCall								=	new SimpleJdbcCall(this.jdbcTemplate);
		CheckLeaveDelegation	checkLeaveDelegation	=	new CheckLeaveDelegation();
		Map						result					=	null;
		simpleJdbcCall.withProcedureName(Constants.CONST_PROC_CHECK_ELEAVE_DELEGATION);
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
		simpleJdbcCall.useInParameterNames(
				Constants.CONST_PROC_COL_IN_P_DLG_EMP_CODE,
				Constants.CONST_PROC_COL_IN_P_DLG_START,
				Constants.CONST_PROC_COL_IN_P_DLG_END,
				Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO_2,
				Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE
				);
		
		simpleJdbcCall.declareParameters(
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_DLG_EMP_CODE, Types.VARCHAR),
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_DLG_START, Types.DATE),
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_DLG_END, Types.DATE),
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO_2, Types.VARCHAR),
				new SqlParameter(Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_ACCEPT_DLG_YN, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ENG, Types.VARCHAR),
				new SqlOutParameter(Constants.CONST_PROC_COL_OUT_P_MSG_ARB, Types.VARCHAR)
			);
		
		Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
			paramIn.put(Constants.CONST_PROC_COL_IN_P_DLG_EMP_CODE,delEmp.getEmpNumber());
			paramIn.put(Constants.CONST_PROC_COL_IN_P_DLG_START, delEmp.getFromDate());
			paramIn.put(Constants.CONST_PROC_COL_IN_P_DLG_END, delEmp.getToDate());
			paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_REQ_NO_2,requestNo );
			paramIn.put(Constants.CONST_PROC_COL_IN_P_LEAVE_EMP_CODE,empNumber );
			
			try
			{
				result			=	simpleJdbcCall.execute(paramIn);	
		
				if(
						((String)result.get(Constants.CONST_PROC_COL_OUT_P_ACCEPT_DLG_YN))
							.equalsIgnoreCase(Constants.CONST_YES_CAPITAL)
				  )
				{
					checkLeaveDelegation.setAcceptDelegation(true);
				}
				else
				{
					checkLeaveDelegation.setAcceptDelegation(false);
				}
				
				if(locale.getLanguage().equals(Constants.CONST_LANG_DEFAULT_EN))
				{
					checkLeaveDelegation.setMessage((String)result.get(Constants.CONST_PROC_COL_OUT_P_MSG_ENG));
				}
				else
				{
					checkLeaveDelegation.setMessage((String)result.get(Constants.CONST_PROC_COL_OUT_P_MSG_ARB));
				}
				checkLeaveDelegation.setEmpCodeDlg(delEmp.getEmpNumber());
				checkLeaveDelegation.setEmpCodeLeave(empNumber);
			}
			catch(Exception ex)
			{
				checkLeaveDelegation = null;
				logger.warn("delegation of : "+delEmp.getEmpNumber()+" not possible for delegator : "+empNumber);
			}
		
		return checkLeaveDelegation;
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
//						+"<br>"+UtilProperty.getMessage("prop.leave.app.department.head", null)
//						+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
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
//								+"<br> "+UtilProperty.getMessage("prop.leave.app.section.head", null)
//								+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
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
//						+ "<br> " +UtilProperty.getMessage("prop.leave.app.higher.head", null)
//						+" "+UtilProperty.getMessage("prop.leave.app.level.no", new String[]{rs.getString(Constants.CONST_EMP_LEVEL)})
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
		EmailLeave	emailLeave				=	null;
		EmailGeneral	emailGeneral				=	null;
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
		namedParameters.put("paramResearchId",leaveRequest.getResearchId());
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
		namedParameters.put("paramHodId", emp.getMyHodId());
		
		namedParameters.put("paramProcessSalaray",leaveRequest.getProcessSalaray());
		if(
			null == leaveRequest.getRequestNo() || 
		   leaveRequest.getRequestNo().trim().equals("") || 
		   leaveRequest.getRequestNo().trim().equals(Constants.CONST_NOT_AVAILABLE))
		{
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQUEST, namedParameters);

			/***************** SENDING E-MAIL TO REQUESTER & APPROVER  FOR NEW LEAVE REQUEST*****************/

					emailGeneral	=	new	EmailGeneral
									(
											leaveRequestNo,
											leaveRequest, 
											getLeaveApproveHistory(leaveRequestNo, locale).get(0), 
											delegatedEmps, emailService, locale
									);
					emailLeave	=	emailGeneral;
		
					emailLeave.sendRequesterEmail();
					emailLeave.sendApproverEmail();
			/**************************************************************/			
			
			if(null != delegatedEmps)
			{
				setNewLeaveDelegationRequest(leaveRequestNo,delegatedEmps,emp.getEmpNumber(),locale,Constants.CONST_OPERATION_ADD,emailLeave);
			}
			
		}
		else
		{
			LeaveRequest	leaveRequestCompare	=	getLeaveRequest(leaveRequest.getApproverId(),leaveRequestNo);
			namedParameters.put("paramCompLeaveStatus",leaveRequestCompare.getStatus().getStatusCode());

			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQUEST, namedParameters);
			
			if(result == 0)
			{
				logger.error("update into request status not successful. This might happens for avoiding accidental update of requester/approver");
			}
			

			/***************** SENDING E-MAIL TO REQUESTER & APPROVER FOR UPDATED LEAVE REQUEST*****************/
			
			emailGeneral	=	new	EmailGeneral
			(
					leaveRequest, 
					getLeaveApproveHistory(leaveRequestNo, locale).get(0), 
					delegatedEmps, emailService, locale
			);
			emailLeave	=	emailGeneral;
			
			emailLeave.sendRequesterEmail();
			emailLeave.sendApproverEmail();
																  	  
			/**************************************************************************************************************/
			
			LeaveApprove	approve		=	new LeaveApprove();
			Employee		employee	=	new Employee();
			employee.setEmpNumber(emp.getMyHodId());
			approve.setEmployee(employee);
			approve.setRequestNo(leaveRequestNo);
			approve.setApproverRemark(UtilProperty.getMessage("prop.leave.app.apply.form.approvar.auto.remarks2", null, locale));
			if(null != delegatedEmps)
			{
				setNewLeaveDelegationRequest(leaveRequestNo,delegatedEmps,emp.getEmpNumber(),locale,Constants.CONST_OPERATION_UPDATE,emailLeave);
			}

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
	 * @param operation
	 * LeaveDbDaoImpl
	 * return type  : void
	 * 
	 * purpose		: set delegation
	 *
	 * Date    		:	Dec 8, 2012 12:44:31 PM
	 */
	@Transactional("trLeaveDelegate")
	private	void	setNewLeaveDelegationRequest
											(
												String requestNo, DelegatedEmp[] delegatedEmps,
												String orginEmpNumber,Locale locale,
												String operation,EmailLeave emailLeave
											)
	{
		try
		{
			if(operation.equals(Constants.CONST_OPERATION_UPDATE))
			{	Map<String,String> namedParameters2 	= 	new HashMap<String,String>();
				namedParameters2.put("paramLeaveReqNo", requestNo);
				this.namedParameterJdbcTemplate.update(Constants.SQL_DELETE_LEAVE_REQ_DELEGATION, namedParameters2);
			}
			
			for(DelegatedEmp delEmp: delegatedEmps)
				{
					Employee	employee	=	null;
					String 		empNumber	=	delEmp.getEmpNumber();
					if(null != empNumber && !empNumber.trim().equals(""))
					{
						employee	=	getEmployee(empNumber);
					}
					else
					{
						break;
					}
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
					
					if(!(null == delEmp.getEmpNumber() || delEmp.getEmpNumber().trim().equals("")) && 
							!(null == delEmp.getFromDate() || delEmp.getFromDate().trim().equals("")) &&
							!(null == delEmp.getToDate() || delEmp.getToDate().trim().equals(""))
					)
						{
							this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQ_DELEGATION, namedParameters);
							if(null != emailLeave)
							{
								Employee delEmployee	=	getEmployee(delEmp.getEmpNumber(), locale);
								/*E-mail to delegates*/
								emailLeave.sendDelegateEmail(delEmployee.getEmpSquEmail(), delEmployee.getEmpName());
							}
						}

				}
		}
		catch(Exception ex)
		{
			logger.error("Error in Delegated employees. error description : "+ex.getMessage());
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
	
	//(intResult==1)?true:false;
	public List<LeaveRequest>	getLeaveRequests(Employee employee, Locale locale)
	{
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{
			
			public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveRequest	leaveRequest		=	new LeaveRequest();
				LeaveType		leaveType			=	new LeaveType();
				LeaveStatus		leaveStatus			=	new LeaveStatus();
				LeaveApprove	leaveApprove		=	new LeaveApprove();
				LeaveApprove	leaveReturnApprove	=	new LeaveApprove();
				Employee		employeeReq			=	new	Employee();											//	Employee as requester
				Employee		employeeApp			=	new Employee();											//	Employee as approver
				Employee		employeeRetApp		=	new Employee();											//	Employee as approver for Leave Return	

				
				int 			returnEligible	=	0;
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
					employeeReq.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
					employeeReq.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
					employeeReq.setEmpName(rs.getString(Constants.CONST_EMP_FIRST_NAME) + " " +rs.getString(Constants.CONST_EMP_LAST_NAME ));
					employeeReq.setHierarchyCode(rs.getString(Constants.CONST_EMP_HIERARCHY_CODE));
					if(rs.getString(Constants.CONST_DELEGATE_STATUS).equals(Constants.CONST_EMP_SENIOR))
					{
						employeeReq.setSenior(true);
					}
					else
					{
						employeeReq.setSenior(false);
					}
				leaveRequest.setEmployee(employeeReq);
						employeeApp.setEmpNumber(rs.getString(Constants.CONST_EMP_APP_CODE));
						employeeApp.setEmpName(rs.getString(Constants.CONST_EMP_APP_FIRST_NAME) + " " +rs.getString(Constants.CONST_EMP_APP_LAST_NAME) );
					leaveApprove.setEmployee(employeeApp);
				leaveRequest.setApprove(leaveApprove);
				leaveRequest.setApproverSequenceNo(rs.getInt(Constants.CONST_APPROVER_SEQUENCE_NO));
						employeeRetApp.setEmpNumber(rs.getString(Constants.CONST_RETURN_EMP_APP_CODE));
						employeeRetApp.setEmpName(rs.getString(Constants.CONST_RETURN_EMP_APP_FIRST_NAME) + " " +rs.getString(Constants.CONST_RETURN_EMP_APP_LAST_NAME));
				leaveReturnApprove.setEmployee(employeeRetApp);
				leaveRequest.setReturnApprove(leaveReturnApprove);

				if((null != rs.getString(Constants.CONST_APPROVER_BEFORE_ACTION)) && rs.getString(Constants.CONST_APPROVER_BEFORE_ACTION).equalsIgnoreCase(Constants.CONST_YES_CAPITAL))
				{
					leaveRequest.setSabbaticalLowerApproverAction(true);
				}
				else
				{
					leaveRequest.setSabbaticalLowerApproverAction(false);
				}
				if(rs.getInt(Constants.CONST_LEAVE_RETURN_ELIGIBLE) == 1 )
				{
					leaveRequest.setLeaveReturn(true);
				}
				else
				{
					leaveRequest.setLeaveReturn(false);
				}
				

				
				return leaveRequest;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramEmpNumber", employee.getEmpNumber());
		namedParameters.put("paramLevel", employee.getHierarchyLevelCode());
		
		
		
		List<LeaveRequest>	leaveRequests	=	this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_LEAVE_REQUEST, namedParameters, mapper);
				
		return leaveRequests;
	}
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param reqNo
	 * @param locale
	 * @param approverEmpNumber
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Sep 18, 2012 2:14:04 PM
	 */
	public LeaveRequest	getLeaveRequest(String approverEmpNumber, String reqNo, Locale locale)
	{
		LeaveRequest	leaveRequestResult	=	null;
		Employee		empApprover			=	null;
		LeaveApprove	leaveApprove		=	null;
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{

			public LeaveRequest mapRow(ResultSet rs, int rowNum)
					throws SQLException
			{
				LeaveRequest	leaveRequest		=	new LeaveRequest();
				LeaveStatus		leaveStatus			=	new LeaveStatus();
				LeaveType		leaveType			=	new LeaveType();
				LeaveType		leaveTypeFlag		=	new LeaveType();
				Employee		employee			=	new	Employee();
				Employee		empAppLeaveReturn	=	new Employee();
				LeaveApprove	approve				=	new LeaveApprove();
				LeaveApprove	returnApprove		=	new LeaveApprove();

					leaveRequest.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
					leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_REQ_DATE));
						leaveStatus.setStatusCode(rs.getString(Constants.CONST_LEAVE_STATUS_CODE));
						leaveStatus.setStatusDesc(rs.getString(Constants.CONST_LEAVE_STATUS));
					leaveRequest.setStatus(leaveStatus);
					leaveRequest.setLeaveStartDate(rs.getString(Constants.CONST_LEAVE_START_DATE));
					leaveRequest.setLeaveEndDate(rs.getString(Constants.CONST_LEAVE_END_DATE));
					if(null != rs.getString(Constants.CONST_LEAVE_RETURN_DATE ))
							{
								leaveRequest.setLeaveReturnDate(rs.getString(Constants.CONST_LEAVE_RETURN_DATE ));
							}
					leaveRequest.setLeaveReturnDate(rs.getString(Constants.CONST_LEAVE_RETURN_DATE));
						leaveType.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE));
						leaveType.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
						leaveTypeFlag.setTypeNo(rs.getString(Constants.CONST_LEAVE_TYPE_FLAG));
						leaveTypeFlag.setTypeDesc(rs.getString(Constants.CONST_LEAVE_DESC));
					leaveRequest.setLeaveType(leaveType);
					leaveRequest.setLeaveTypeFlag(leaveTypeFlag);
					leaveRequest.setResearchId(rs.getString(Constants.CONST_LEAVE_RESEARCH_ID));
					leaveRequest.setLeaveRequestRemarks(rs.getString(Constants.CONST_LEAVE_REQ_REMARK));
						employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
						employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
						employee.setEmpNameEn(rs.getString(Constants.CONST_EMP_NAME_EN));
						employee.setEmpNameAr(rs.getString(Constants.CONST_EMP_NAME_AR));
						employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
						employee.setEmpSquEmail(rs.getString(Constants.CONST_EMP_EMAIL_ID));
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
					leaveRequest.setApproverId(rs.getString(Constants.CONST_EMP_APP_CODE));
					leaveRequest.setApproverSequenceNo(rs.getInt(Constants.CONST_APPROVER_SEQUENCE_NO));
					leaveRequest.setProcessSalaray(rs.getString(Constants.CONST_LEAVE_REQUEST_PROCESS_SALARY));

					if(null != rs.getString(Constants.CONST_RETURN_EMP_APP_CODE ))
					{
						empAppLeaveReturn.setEmpNumber(rs.getString(Constants.CONST_RETURN_EMP_APP_CODE ));
						returnApprove.setEmployee(empAppLeaveReturn);
						leaveRequest.setReturnApprove(returnApprove);
					}

				return leaveRequest;
			
		}
	};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramEmpNumber", approverEmpNumber);
		namedParameters.put("paramReqNo", reqNo);
		namedParameters.put("paramLocale", locale.getLanguage());
		leaveRequestResult	=	this.namedParameterJdbcTemplate.queryForObject(Constants.SQL_VIEW_LEAVE_REQUEST_SPECIFIC_EMPLOYEE, namedParameters, mapper);
			empApprover			=	getEmployee(leaveRequestResult.getApproverId(), locale);	
				leaveApprove		=	leaveRequestResult.getApprove();
					leaveApprove.setEmployee(empApprover);
		
		leaveRequestResult.setApprove(leaveApprove);
					
		return leaveRequestResult;
		
	}
	
	/**
	 * 
	 * method name  : getLeaveRequest
	 * @param approverEmpNumber
	 * @param reqNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : LeaveRequest
	 * 
	 * purpose		: get leave request details for a leave request number
	 *
	 * Date    		:	Jan 30, 2013 12:03:42 PM
	 */
	private	LeaveRequest	getLeaveRequest(String approverEmpNumber,String reqNo)
	{
		return getLeaveRequest(approverEmpNumber, reqNo, new Locale(Constants.CONST_LANG_DEFAULT_EN));
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
		RowMapper<LeaveRequest> mapper	=	new RowMapper<LeaveRequest>()
		{
			
			public LeaveRequest mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveRequest	leaveRequest	=	new LeaveRequest();
				LeaveStatus		leaveStatus		=	new LeaveStatus();
				
				leaveStatus.setStatusCode(rs.getString(Constants.CONST_LEAVE_STATUS_CODE));
				leaveStatus.setStatusDesc(rs.getString(Constants.CONST_LEAVE_STATUS));
				
				leaveRequest.setStatus(leaveStatus);
				leaveRequest.setRequestDate(rs.getString(Constants.CONST_LEAVE_STATUS_DATE));
				leaveRequest.setLeaveRequestRemarks(rs.getString(Constants.CONST_LEAVE_REQUEST_REMARKS));
				return leaveRequest;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", requestNo);
		
		
		return this.namedParameterJdbcTemplate.query(Constants.SQL_LEAVE_STATUS_HISTORY, namedParameters, mapper);
	}
	
	
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
	public List<LeaveApprove>	getLeaveApproveHistory(String requestNo, Locale locale)
	{
		RowMapper<LeaveApprove> mapper	=	new RowMapper<LeaveApprove>()
		{
			
			public LeaveApprove mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveApprove	approve		=	new LeaveApprove();
				AdminAction		action		=	new AdminAction();
				Employee		employee	=	new Employee();
				
				action.setActionCode(rs.getString(Constants.CONST_ACTION_CODE));
				action.setActionDesc(rs.getString(Constants.CONST_ACTION_DESC));
				action.setActionDate(rs.getString(Constants.CONST_ACTION_DATE));
				action.setAdminActionRemark(rs.getString(Constants.CONST_APPROVER_REMARK));
				
				employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				employee.setEmpNameEn(rs.getString(Constants.CONST_EMP_NAME_EN));
				employee.setEmpNameAr(rs.getString(Constants.CONST_EMP_NAME_AR));
				employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
				
				approve.setAction(action);
				approve.setEmployee(employee);
				
				return approve;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", requestNo);

		return this.namedParameterJdbcTemplate.query(Constants.SQL_LEAVE_APPROVE_HISTORY, namedParameters, mapper);
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
		RowMapper<LeaveApprove> mapper	=	new RowMapper<LeaveApprove>()
		{
			
			public LeaveApprove mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveApprove	approve		=	new LeaveApprove();
				AdminAction		action		=	new AdminAction();
				Employee		employee	=	new Employee();
				
				action.setActionCode(rs.getString(Constants.CONST_ACTION_CODE));
				action.setActionDesc(rs.getString(Constants.CONST_ACTION_DESC));
				action.setActionDate(rs.getString(Constants.CONST_ACTION_DATE));
				action.setAdminActionRemark(rs.getString(Constants.CONST_APPROVER_REMARK));
				
				employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				employee.setEmpNameEn(rs.getString(Constants.CONST_EMP_NAME_EN));
				employee.setEmpNameAr(rs.getString(Constants.CONST_EMP_NAME_AR));
				employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
				
				approve.setAction(action);
				approve.setEmployee(employee);
				
				return approve;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", requestNo);
		namedParameters.put("paramEmpCode", appEmpNumber);

		return this.namedParameterJdbcTemplate.query(Constants.SQL_LEAVE_APPROVE_HISTORY_EMPLOYEE_NUM, namedParameters, mapper);
	}
	
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
	public List<LeaveApprove>	getLeaveApproveHistorySequence(String requestNo, String sequenceNo, Locale locale)
	{
		RowMapper<LeaveApprove> mapper	=	new RowMapper<LeaveApprove>()
		{
			
			public LeaveApprove mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				LeaveApprove	approve		=	new LeaveApprove();
				AdminAction		action		=	new AdminAction();
				Employee		employee	=	new Employee();
				
				action.setActionCode(rs.getString(Constants.CONST_ACTION_CODE));
				action.setActionDesc(rs.getString(Constants.CONST_ACTION_DESC));
				action.setActionDate(rs.getString(Constants.CONST_ACTION_DATE));
				action.setAdminActionRemark(rs.getString(Constants.CONST_APPROVER_REMARK));
				
				employee.setEmpNumber(rs.getString(Constants.CONST_EMP_CODE));
				employee.setEmpName(rs.getString(Constants.CONST_EMP_NAME));
				employee.setEmpNameEn(rs.getString(Constants.CONST_EMP_NAME_EN));
				employee.setEmpNameAr(rs.getString(Constants.CONST_EMP_NAME_AR));
				employee.setEmpInternetId(rs.getString(Constants.CONST_EMP_INTERNET_ID));
				
				approve.setAction(action);
				approve.setEmployee(employee);
				
				return approve;
			}
		};
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramLocale", locale.getLanguage());
		namedParameters.put("paramReqNo", requestNo);
		namedParameters.put("paramAppSeqNo", sequenceNo);

		return this.namedParameterJdbcTemplate.query(Constants.SQL_LEAVE_APPROVE_HISTORY_APP_SEQUENCE_NUM, namedParameters, mapper);
	}

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
	public int getMaxLeaveApproverSequence(String requestNo)
	{
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo", requestNo);
		
		return this.namedParameterJdbcTemplate.queryForInt(Constants.SQL_LEAVE_APPROVE_MAX_SEQUENCE_NUM, namedParameters);
	}
	
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
	public	List<Sabbatical>	getSabbatical(String empNumber)
	{
		RowMapper<Sabbatical>	mapper	=	new RowMapper<Sabbatical>()
		{
			public Sabbatical mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Sabbatical		sabbatical		=	new Sabbatical();
				sabbatical.setRequestNo(rs.getString(Constants.CONST_LEAVE_REQUEST_NO));
				sabbatical.setApproverAction(rs.getString(Constants.CONST_ACTION_CODE));
				sabbatical.setSequence(rs.getInt(Constants.CONST_APPROVER_NEXT_SEQUENCE_NO));
				return sabbatical;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramEmpNumber", empNumber);
		return  this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_SABBATICAL_LIMITED, namedParameters, mapper);
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
		namedParameters.put("paramBranchId", branchId);
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
	public List<Branch> getEmpBranches (String empNumber, Locale locale)
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
		RowMapper<Budget> mapper = new RowMapper<Budget>()
		{
			
			public Budget mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Budget	budget		=	new Budget();
				budget.setBudgetId(rs.getString(Constants.CONST_LEAVE_RESEARCH_ID));
				budget.setBudgetYearFrom(rs.getString(Constants.CONST_BUDGET_YEAR_FROM));
				budget.setBudgetYearTo(rs.getString(Constants.CONST_BUDGET_YEAR_TO));
				budget.setBudgetDescription(rs.getString(Constants.CONST_BUDGET_DESCRIPTION));
				budget.setBudgetDescriptionShort(rs.getString(Constants.CONST_BUDGET_DESCRIPTION_SHORT));
				budget.setBudgetRemarks(rs.getString(Constants.CONST_BUDGET_REMARKS));
				return budget;
			}
		};
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramResearchId", budgetId+"%");
		return this.namedParameterJdbcTemplate.query(Constants.SQL_VIEW_BUDGET, namedParameters, mapper);
		
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
		int result2	=	0;
		
		Employee		employee		=	approve.getEmployee();
		LeaveRequest	leaveRequest	=	getLeaveRequest(employee.getEmpNumber(),approve.getRequestNo());
		String			leaveStatusCode	=	leaveRequest.getStatus().getStatusCode();
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
			namedParameters.put("paramReqNo", approve.getRequestNo());
			namedParameters.put("paramEmpNo", employee.getEmpNumber());
			namedParameters.put("paramActionCode",approve.getApproverAction());
			namedParameters.put("paramApprvRemark", approve.getApproverRemark());

			if(isApprovalRecordExist(approve.getRequestNo(), employee.getEmpNumber()))
		{
				
			namedParameters.put("paramUpdateUsr", Constants.USER_WEB);
			namedParameters.put("paramActionCodeApprove", Constants.CONST_LEAVE_ACTION_APPROVE);
				if	(
					! leaveStatusCode.equals(Constants.CONST_LEAVE_STATUS_APPROVED) &&
					! leaveStatusCode.equals(Constants.CONST_LEAVE_STATUS_REJECTED) &&
					! leaveStatusCode.equals(Constants.CONST_LEAVE_STATUS_CANCEL) 
					)
				{
					result =  this.namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQ_APPROVE,namedParameters );		
				}
			
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
			
			result =  this.namedParameterJdbcTemplate.update(Constants.SQL_INSERT_LEAVE_REQ_APPROVE,namedParameters );

		}
		
		if(result != 0)
		{
				result2 = setLeaveRequestStatusUpdate(employee.getEmpNumber(),approve.getRequestNo(), approve.getApproverAction());
		}
		else
		{
			logger.error("update not successful for approval");
		}
		if(result2 == 0)
		{
			logger.error("update into request status not successful. This might happens for avoiding accidental update of requester/approver");
		}

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
	public long getLeaveRequestCounter()
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
	private int setLeaveRequestStatusUpdate(String empNumber,String requestNo, String approveAction)
	{
		String 			leaveStatus		=	null;
		LeaveRequest	leaveRequest	=	null;
		
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
		
		leaveRequest	=	getLeaveRequest(empNumber,requestNo);
		
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo", requestNo);
		namedParameters.put("paramStatusCode", leaveStatus);
		
		namedParameters.put("paramCompLeaveTypeFlag",leaveRequest.getLeaveTypeFlag().getTypeNo());
		namedParameters.put("paramCompStartDate",leaveRequest.getLeaveStartDate());
		namedParameters.put("paramCompEndDate",leaveRequest.getLeaveEndDate());
		if(null == leaveRequest.getLeaveReturnDate())
		{
			namedParameters.put("paramCompSuggestedHod",leaveRequest.getSuggestedHod());
			return namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_REQ_STATUS, namedParameters);
		}
		else
		{
			namedParameters.put("paramLeaveReturnDate",leaveRequest.getLeaveReturnDate());
			namedParameters.put("paramReturnApproverEmpNumber",leaveRequest.getReturnApprove().getEmployee().getEmpNumber());
			return namedParameterJdbcTemplate.update(Constants.SQL_UPDATE_LEAVE_RETURN_STATUS, namedParameters);
		}
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
	
	/**
	 * 
	 * method name  : cancelLeaveRequest
	 * @param approverEmpNumber
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: Cancel the leave request
	 *
	 * Date    		:	Feb 3, 2013 1:54:23 PM
	 */
	public int cancelLeaveRequest(String approverEmpNumber, String requestNo)
	{
		LeaveRequest	leaveRequestCompare	=	getLeaveRequest(approverEmpNumber,requestNo);
		Map<String,String> namedParameters 	= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo",requestNo);
		namedParameters.put("paramCompLeaveStatus",leaveRequestCompare.getStatus().getStatusCode());
		return namedParameterJdbcTemplate.update(Constants.SQL_CANCEL_LEAVE_REQUEST, namedParameters);
	}
	
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
	@Transactional("trLeaveDeleteSpecific")
	public int removeLeaveRequest(String requestNo)
	{
		int resultLvReq		=	0;
		int resultLvApprv	=	0;
		int resultLvDelegt	=	0;
		int resultLvReqHis	=	0;
		int result			=	0;
		
		resultLvReq	=	jdbcTemplate.update(Constants.SQL_DELETE_LEAVE_REQ_SPECIFIC, requestNo);
		resultLvApprv	=	removeLeaveApproval(requestNo);
		resultLvDelegt	=	removeLeaveDelegation(requestNo);
		resultLvReqHis	=	removeLeaveReqHistory(requestNo);
		
		if (resultLvReq !=0 && resultLvApprv != 0 && resultLvDelegt != 0 && resultLvReqHis != 0)
		{
			result	=	1;
		}
		return result;
	}
	
	/**
	 * 
	 * method name  : removeLeaveApproval
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: delete leave approval records
	 *
	 * Date    		:	Feb 3, 2013 9:48:22 AM
	 */
	@Transactional("trLeaveDeleteSpecific")
	private	int removeLeaveApproval(String requestNo)
	{
		return jdbcTemplate.update(Constants.SQL_DELETE_LEAVE_APPROVAL_SPECIFIC, requestNo);
	}
	
	/**
	 * 
	 * method name  : removeLeaveDelegation
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: delete delegation records for a particular leave request
	 *
	 * Date    		:	Feb 3, 2013 10:14:21 AM
	 */
	@Transactional("trLeaveDeleteSpecific")
	private int removeLeaveDelegation(String requestNo)
	{
		return jdbcTemplate.update(Constants.SQL_DELETE_LEAVE_DELEGATION_SPECIFIC, requestNo);
	}

	/**
	 * 
	 * method name  : removeLeaveReqHistory
	 * @param requestNo
	 * @return
	 * LeaveDbDaoImpl
	 * return type  : int
	 * 
	 * purpose		: remove history records for a particular leave request
	 *
	 * Date    		:	Feb 3, 2013 10:14:59 AM
	 */
	@Transactional("trLeaveDeleteSpecific")
	private int removeLeaveReqHistory(String requestNo)
	{
		return jdbcTemplate.update(Constants.SQL_DELETE_LEAVE_WORKFLOW_LOG_SPECIFIC, requestNo);
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
		this.simpleJdbcCall				=	new SimpleJdbcCall(this.jdbcTemplate);
		
		java.sql.Date	dateSQL			=	null;
		try
		{
			dateSQL	=	new DateChange(strStartDate).getStringToSqlDate();
		}
		catch(ParseException pex)
		{
			logger.error("Parse exception in date : "+pex);
		}
		
		
		simpleJdbcCall.withFunctionName(Constants.CONST_FUNC_CHECK_LEAVE_BALANCE);
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
		simpleJdbcCall.useInParameterNames(
				Constants.CONST_FUNC_COL_IN_E_CODE,
				Constants.CONST_FUNC_COL_IN_SDATE
				);
		simpleJdbcCall.declareParameters(
				new SqlOutParameter(Constants.CONST_FUNC_COL_OUT_LEAVE_BAL, Types.NUMERIC),
				new SqlParameter(Constants.CONST_FUNC_COL_IN_E_CODE, Types.VARCHAR),
				new SqlParameter(Constants.CONST_FUNC_COL_IN_SDATE, Types.DATE)
				
				);

		Map<String,Object> 	paramIn				=	new HashMap<String, Object>();
		paramIn.put(Constants.CONST_FUNC_COL_IN_E_CODE, empNumber);
		paramIn.put(Constants.CONST_FUNC_COL_IN_SDATE, dateSQL);
		
		
		
		return String.valueOf(simpleJdbcCall.executeFunction(Object.class, paramIn));
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
		boolean booResult;
		int		intResult;
		String 				CONST_SELECT_RETURN_ELIGIBLE	=	queryPropsReturn.getProperty(Constants.CONST_SELECT_RETURN_ELIGIBLE);
		
		Map<String,String> 	namedParameters 				= 	new HashMap<String,String>();
							namedParameters.put("paramReqNo", requestNo);
		
							intResult						=	namedParameterJdbcTemplate.queryForInt(CONST_SELECT_RETURN_ELIGIBLE, namedParameters);
		
		return (intResult==1)?true:false;
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
	public int newLeaveReturn(LeaveRequest	leaveRequest)
	{
		int		intResult;
		String 				CONST_SQL_UPDATE_LEAVE_RETURN_NEW	=	queryPropsReturn.getProperty(Constants.CONST_LEAVE_RETURN_NEW);
		
		Map<String,String> 	namedParameters 				= 	new HashMap<String,String>();
		namedParameters.put("paramReqNo", leaveRequest.getRequestNo());
		namedParameters.put("paramLeaveReturnDate", leaveRequest.getLeaveReturnDate());
		namedParameters.put("paramEmpReturnApprover", leaveRequest.getApproverId());
		
		intResult	=	namedParameterJdbcTemplate.update(CONST_SQL_UPDATE_LEAVE_RETURN_NEW, namedParameters);
		
		return intResult;
		
	}
	
	
}
