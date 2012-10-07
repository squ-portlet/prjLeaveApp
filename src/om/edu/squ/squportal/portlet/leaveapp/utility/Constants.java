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
 * File Name			:	Constants.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility
 * Date of creation		:	Aug 4, 2012  11:40:29 AM
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
package om.edu.squ.squportal.portlet.leaveapp.utility;

/**
 * @author Bhabesh
 *
 */
public interface Constants
{
	/**********SQL**********************************/

	public static final	String	SQL_EMPLOYEE					=			" SELECT DISTINCT EMP.VHM_EMP_CODE AS EMP_CODE, 									" +
																			"  SUBSTR(																			" +
																			"			VHM_EMP_SQU_EMAIL,1,													" +
																			"			INSTRB(VHM_EMP_SQU_EMAIL, '@', 1, 1)-1									" +
																			"		  ) AS EMP_INTERNET_ID,														" +
																			"  DECODE(:paramLocale,                          									" +		
																			"            'en',initCap(EMP.VHM_EMP_NAME),   										" +       	
																			"			 'ar',EMP.VHM_EMP_NAME_ARABIC) AS EMP_NAME,								" +
																			"	EMP.VHM_EMP_JOB_TYPE AS EMP_JOB_TYPE_CODE,										" +
																			"	EMP.VHM_EMP_DESG_CODE AS EMP_DESIGNATION_CODE,									" +
																			"  DECODE(:paramLocale,                          									" +
																			"            'en',initCap(DESG.VHM_DESG_DESC),          							" +
																			"			 'ar',DESG.VHM_DESG_DESC_ARABIC) AS EMP_DESIGNATION,					" +
																			"	ADESIG.VEAT_EMP_DESG_CODE AS EMP_ADDITIONAL_POSITION_CODE,						" +
																			" 	VHM_EMP_ADMIN_POSITION_YN AS EMP_ADMIN,											" +
																			"	EMP.VHM_EMP_GRADE_CODE AS EMP_GRADE_CODE,										" +		
																			"  DECODE(:paramLocale,                          									" +
																			"            'en',initCap(GRADE.VHM_GRADE_DESC),          							" +
																			"			 'ar',GRADE.VHM_GRADE_DESC_ARABIC) AS EMP_GRADE,						" +
																			"   GRADE.VHM_GRADE_SHORT_DESC AS EMP_GRADE_SHORT, 									" +
																			"	EMP.VHM_EMP_BRAN_CODE AS EMP_BRANCH_CODE,      									" +
																			"  DECODE(:paramLocale,                          									" +
																			"            'en',initCap(BRANCH.VHM_BRANCH_NAME),          						" +
																			"			 'ar',BRANCH.VHM_BRANCH_NAME_ARABIC) AS EMP_BRANCH,						" +
																			"  EMP.VHM_EMP_DEPT_CODE AS EMP_DEPARTMENT_CODE,									" +
																			"  DECODE(:paramLocale,                          									" +
																			"            'en',initCap(DEPARTMENT.VHM_DEPT_NAME),          						" +
																			"			 'ar',DEPARTMENT.VHM_DEPT_NAME_ARABIC) AS EMP_DEPARTMENT,				" +
																			"   DEPARTMENT.VHM_DEPT_SHORT_NAME AS EMP_DEPARTMENT_SHORT,							" +
																			"	EMP.VHM_EMP_SECTION_CODE AS EMP_SECTION_CODE,									" +
																			"  DECODE(:paramLocale,                          									" +
																			"            'en',initCap(SECTION.VHM_SECTION_NAME),          						" +
																			"			 'ar',SECTION.VHM_SECTION_NAME_ARABIC) AS EMP_SECTION,					" +
																			"    TO_CHAR(VHM_EMP_JOIN_DATE,'dd/mm/yyyy') AS EMP_JOIN_DATE,						" +
																			"	DESG.VHM_HIERARCHY_CODE AS 	EMP_HIERARCHY_CODE,									" +
																			"   EMP.VHM_REL_CODE AS EMP_RELIGION_CODE,											" +
																			"   EMP.VHM_EMP_LOCAL	AS EMP_OMANI,												" +
																			"   EMP.VHM_EMP_SEX  AS EMP_SEX														" +
																			" FROM VHM_EMPLOYEE EMP,															" +
																			"     VHM_DESIGNATION DESG,															" +
																			"     VHM_GRADE GRADE,																" +
																			"     VHM_BRANCH BRANCH,															" +
																			"     VHM_DEPARTMENT DEPARTMENT,													" +
																			"     VHM_SECTION SECTION,															" +
																			"	  VHM_EMPLOYEE_ADDITIONAL_TASK ADESIG											" +
																			" WHERE EMP.VHM_EMP_CODE=LPAD(:paramEmpNumber,7,0)									" +
																			"	AND EMP.VHM_EMP_DESG_CODE = DESG.VHM_DESG_CODE									" +
																			"	AND EMP.VHM_EMP_GRADE_CODE=GRADE.VHM_GRADE_CODE									" +
																			"	AND EMP.VHM_EMP_BRAN_CODE=BRANCH.VHM_BRANCH_CODE								" +
																			"	AND EMP.VHM_EMP_DEPT_CODE=DEPARTMENT.VHM_DEPT_CODE								" +
																			"	AND EMP.VHM_EMP_SECTION_CODE=SECTION.VHM_SECTION_CODE							" +
																			"   AND EMP.VHM_EMP_CODE = ADESIG.VEAT_EMP_CODE(+)									";
	
	public static final	String	SQL_EMP_BRANCH_DEPT_SHORT		=			" SELECT VHM_EMP_CODE AS EMP_CODE,							" +
																			"  	DECODE(													" +
																			"			:paramLocale,                          			" +		
																			"            'en',initCap(EMP.VHM_EMP_NAME),   				" +       	
																			"			 'ar',EMP.VHM_EMP_NAME_ARABIC					" +
																			"	) AS EMP_NAME											" +
																			"	FROM VHM_EMPLOYEE EMP									" +
																			" WHERE VHM_EMP_BRAN_CODE = :paramBranchCode				" +
																			" AND VHM_EMP_DEPT_CODE = :paramDept						";
	
	public static final	String	SQL_DEPARTMENT					=			" SELECT VHM_DEPT_CODE AS EMP_DEPARTMENT_CODE, 				" +
																			"  DECODE(:paramLocale,         							" +
																			"            'en',initCap(DEPARTMENT.VHM_DEPT_NAME), 		" +
																			"			 'ar',DEPARTMENT.VHM_DEPT_NAME_ARABIC) AS EMP_DEPARTMENT " +
																			" FROM VHM_DEPARTMENT	DEPARTMENT							" +
																			" WHERE VHM_BRANCH_CODE = :paramBranchCode					";
	
	public static final String	SQL_ADDITIONAL_POSITION			=			" SELECT 													" +
																			"	  DESIGNATION.VHM_DESG_CODE AS EMP_DESIGNATION_CODE,	" +
																			"	  DECODE(:paramLocale,											" + 
																			"	          'en',  initcap(DESIGNATION.VHM_DESG_DESC),	" +	
																			"	          'ar',  DESIGNATION.VHM_DESG_DESC_ARABIC		" +
																			"	          )	AS 	EMP_DESIGNATION												" +
																			"	FROM													" +
																			"	  VHM_DESIGNATION DESIGNATION,							" +
																			"	  VHM_EMPLOYEE_ADDITIONAL_TASK ADDL						" +
																			"	WHERE													" +
																			"	  DESIGNATION.VHM_DESG_CODE = ADDL.VEAT_EMP_DESG_CODE	" +
																			"	  AND ADDL.VEAT_EMP_CODE = LPAD(:paramEmpNumber,7,0)	";
	
//	public static final String	SQL_COUNTER_LEAVE_REQUEST		=			" SELECT  DECODE (											" +
//																			"					max(VHM_LEAVE_REQ_NO), 					" +
//																	        "    				NULL,1,									" +
//																	        "					MAX(VHM_LEAVE_REQ_NO) + 1				" +
//																	        "    			  ) AS COUNTER 								" +			
//																	        "    FROM VHM_EMP_LEAVE_REQUEST								";
	
	public static final String	SQL_COUNTER_LEAVE_REQUEST		=			"SELECT portal_LEAVE_REQ_NO_seq.nextval FROM DUAL";
	public static final String	SQL_COUNTER_LEAVE_APPROVE		=			" SELECT  DECODE (											" +
																			"					max(VHM_APP_SEQ_NO), 					" +
																		    "    				NULL,1,									" +
																		    "					MAX(VHM_APP_SEQ_NO) + 1				" +
																		    "    			  ) AS COUNTER 								" +			
																		    "    FROM VHM_EMP_LEAVE_REQUEST_APPROVAL								";
	
	public static final String	SQL_LEAVE_TYPE					=			" SELECT VHM_LEAVE_TYPE_FLAG AS LEAVE_TYPE, 				" +
                 															" DECODE(:paramLocale,                         				" +  			
                              			                      				"	'en',initCap(VHM_LEAVE_TYPE_DESC),      				" +    	
																		    "	'ar',VHM_LEAVE_TYPE_DESC_ARABIC) AS LEAVE_DESC 			" +   	
																		    "	FROM VHM_LEAVE_TYPE_FLAG 								" + 
																		    "	WHERE VHM_LEAVE_TYPE_ACTIVE = 'Y'						" +
																		    "	AND VHM_SHOW_LEAVE_PORTAL_YN = 'Y'						" +
																		    "   AND DECODE(:paramMuslim,'N',VHM_SHOW_MUSLIM_YN) IS NULL	" +
																		    "	AND DECODE(:paramFemale,'N',VHM_SHOW_FEMALE_YN) IS NULL	" +
																		    "   AND DECODE(:paramOmani,'N',VHM_SHOW_LOCAL_YN) IS NULL   ";
																		   
	
	public static final String	SQL_ADMIN_ACTION				=			" SELECT VHM_ACTION_CODE AS ACTION_CODE,					" +
																			" DECODE(:paramLocale, 												" +                          			
                              			                      				"	'en',initCap(VHM_ACTION_DESC),							" +          	
																		    "	'ar',VHM_ACTION_DESC_ARABIC) AS ACTION_DESC				" +
																		    " FROM VHM_WORKFLOW_ACTIONS    								" +
																		    " WHERE VHM_SERVICE_TYPE = 'LEAVE'							";
	
	public static final String	SQL_VIEW_LEAVE_REQUEST			=			" SELECT DISTINCT											" +
																			"	LVREQ.VHM_LEAVE_REQ_NO	AS LEAVE_REQUEST_NO,			" +
																			"	TO_CHAR(LVREQ.VHM_LEAVE_REQ_DATE,'DD/MM/YYYY') AS LEAVE_REQ_DATE,				" +
																			"	TO_CHAR(LVREQ.VHM_LEAVE_START_DATE,'DD/MM/YYYY') AS LEAVE_START_DATE,			" + 
																			"	TO_CHAR(LVREQ.VHM_LEAVE_END_DATE,'DD/MM/YYYY') AS LEAVE_END_DATE,				" +
																			" 	LVTYPE.VHM_LEAVE_TYPE_FLAG LEAVE_TYPE,					" +	
																			" DECODE													" +
																			"	(														" +
																			"		:paramLocale,										" +
																			"		'en',LVTYPE.VHM_LEAVE_TYPE_DESC,					" +
																			"		'ar',LVTYPE.VHM_LEAVE_TYPE_DESC_ARABIC				" +
																			"	) AS LEAVE_DESC,										" +
																			" DECODE													" +
																			"	(														" +
																			"		:paramLocale,										" +
																			"		'en',LVSTATUS.VHM_STATUS_DESC,						" +
																			"		'ar',LVSTATUS.VHM_STATUS_DESC_ARABIC				" +
																			"		) AS LEAVE_STATUS,									" +
																			"   LVREQ.VHM_EMP_CODE AS EMP_CODE,							" +
																			"	LVREQ.VHM_EMP_INTERNET_USR_ID AS EMP_INTERNET_ID,		" +
																			"	LVREQ.VHM_HIERARCHY_CODE AS EMP_HIERARCHY_CODE,			" +
																			"   DECODE( 												" +
									                                        "       NVL(												" +
									                                        "          (SELECT DELG.VHM_DELEGATED_STATUS					" +
									                                        "             FROM 											" +
									                                        "             VHM_EMP_LEAVE_REQUEST LVREQ2,					" +
									                                        "             VHM_EMP_LEAVE_REQ_DELEGATION DELG				" +
									                                        "             WHERE  LVREQ2.VHM_LEAVE_REQ_NO = DELG.VHM_LEAVE_REQ_NO (+) " +
									                                        "            AND LVREQ2.VHM_LEAVE_REQ_NO =LVREQ.VHM_LEAVE_REQ_NO),'N'	" +
									                                        "       ),																" +
									                                        "       'N','employee','senior'											" +
									                                        "	) AS DELEGATE_STATUS														" +	
																			" FROM 														" +
																			"		VHM_EMP_LEAVE_REQUEST LVREQ,						" +
																			"		VHM_LEAVE_TYPE_FLAG LVTYPE,							" +
																			"		VHM_WORKFLOW_STATUS LVSTATUS,						" +
																			"		VHM_EMP_LEAVE_REQ_DELEGATION DELG					" +
																			" WHERE 													" +
																			"		LVREQ.VHM_LEAVE_TYPE_FLAG=LVTYPE.VHM_LEAVE_TYPE_FLAG " +
																			"	AND LVREQ.VHM_STATUS_CODE = LVSTATUS.VHM_STATUS_CODE 	" +
																			"	AND	LVREQ.VHM_LEAVE_REQUEST_ACTIVE='Y'					" +
																			"	AND 													" +
																			"		(													" +
																			"				LVREQ.VHM_EMP_CODE = :paramEmpNumber		" +
																			"   	 OR 	LVREQ.VHM_HIERARCHY_CODE > :paramHierarchy	" +
																			"		 OR													" +
																			" (															" +
							                                                "     DELG.VHM_DELEGATED_EMP_CODE = :paramEmpNumber			" +
							                                                "    AND 													" +
							                                                "    (														" +
							                                                "                TO_DATE(LVREQ.VHM_LEAVE_START_DATE,'DD/MM/YYYY')	" + 
							                                                "        BETWEEN TO_DATE(DELG.VHM_DELEGATED_FROM_DATE,'DD/MM/YYYY')	" +
							                                                "                 AND TO_DATE(DELG.VHM_DELEGATED_TO_DATE,'DD/MM/YYYY')	" +
							                                                "    )																	" +	
							                                                "    AND 																" +
							                                                "    (																	" +
							                                                "                TO_DATE(SYSDATE,'DD/MM/YYYY') 							" +	
							                                                "        BETWEEN TO_DATE(DELG.VHM_DELEGATED_FROM_DATE,'DD/MM/YYYY')		" +
							                                                "                 AND TO_DATE(DELG.VHM_DELEGATED_TO_DATE,'DD/MM/YYYY')	" +
							                                                "    )																	" +
							                                                "  )																	" +
																			" ) 														" +
																			"	AND	LVREQ.VHM_BRANCH_CODE = :paramBranchCode			" +
																			"	AND LVREQ.VHM_DEPT_CODE = :paramDeptCode				" +
																			"	ORDER BY LVREQ.VHM_LEAVE_REQ_NO	DESC					";
	
	public static final String	SQL_VIEW_LEAVE_REQUEST_SPECIFIC	=			" SELECT 													" +
																			"	LVREQ.VHM_LEAVE_REQ_NO AS LEAVE_REQUEST_NO,				" +
																			"	TO_CHAR(LVREQ.VHM_LEAVE_REQ_DATE,'DD/MM/YYYY') 			" +
																			"									AS LEAVE_REQ_DATE,		" +
																			"	LVREQ.VHM_STATUS_CODE AS LEAVE_STATUS_CODE,		" +
																			"	DECODE													" +
																			"	  (:paramLocale,										" +
																			"	      'en',InitCap(LVSTAT.VHM_STATUS_DESC),		" +
																			"	      'ar',LVSTAT.VHM_STATUS_DESC_ARABIC			" +
																			"	  ) AS LEAVE_STATUS,									" +
																			"	TO_CHAR(LVREQ.VHM_LEAVE_START_DATE,'DD/MM/YYYY') 		" +
																			"									AS LEAVE_START_DATE,	" +
																			"	TO_CHAR(LVREQ.VHM_LEAVE_END_DATE,'DD/MM/YYYY') 			" +
																			"									AS LEAVE_END_DATE,		" +
																			"	LVREQ.VHM_LEAVE_TYPE_FLAG AS LEAVE_TYPE,				" +
																			"	DECODE													" +
																			"	  (:paramLocale,										" +
																			"	   'en',INITCAP(LVTYPE.VHM_LEAVE_TYPE_DESC),			" +
																			"	   'ar',LVTYPE.VHM_LEAVE_TYPE_DESC_ARABIC				" +
																			"	   ) AS LEAVE_DESC,										" +
																			"	LVREQ.VHM_LEAVE_REQUEST_REMARKS AS LEAVE_REQ_REMARK,	" +
																			"	LVREQ.VHM_EMP_CODE AS EMP_CODE,							" +
																			"	LVREQ.VHM_ADMIN_HOLDING_YN AS EMP_ADMIN,				" +
																			"	LVREQ.VHM_DEPT_CODE AS EMP_DEPARTMENT_CODE,				" +
																			"	DECODE 													" +
																			"	  (:paramLocale,										" +
																			"	   'en',INITCAP(DEPT.VHM_DEPT_NAME),					" +
																			"	   'ar',DEPT.VHM_DEPT_NAME_ARA							" +
																			"	  ) AS EMP_DEPARTMENT,									" +
																			"	LVREQ.VHM_POSITION_CODE AS EMP_ADDITIONAL_POSITION_CODE,								" +
																			"	DECODE													" +
																			"	  (:paramLocale,										" +
																			"	   'en',INITCAP(DESIG.VHM_DESG_DESC),					" +
																			"	   'ar',DESIG.VHM_DESG_DESC_ARABIC) 					" +
																			"							AS EMP_ADDITIONAL_POSITION_DESC	" +
																			"	FROM													" + 
																			"	  VHM_EMP_LEAVE_REQUEST LVREQ,							" +
																			"	  VHM_WORKFLOW_STATUS LVSTAT,							" +
																			"	  VHM_LEAVE_TYPE_FLAG LVTYPE,							" +
																			"	  VHM_DEPARTMENT DEPT,									" +
																			"	  VHM_DESIGNATION DESIG									" +
																			"	WHERE 													" +
																			"	  LVREQ.VHM_LEAVE_REQ_NO = :paramReqNo					" +
																			"	  AND LVREQ.VHM_STATUS_CODE								" +
																			"						=LVSTAT.VHM_STATUS_CODE				" +
																			"	  AND LVREQ.VHM_LEAVE_TYPE_FLAG = LVTYPE.VHM_LEAVE_TYPE_FLAG " +
																			"	  AND LVREQ.VHM_DEPT_CODE = DEPT.VHM_DEPT_CODE			" +
																			"	  AND LVREQ.VHM_POSITION_CODE = DESIG.VHM_DESG_CODE (+)	";


	
	public static final String	SQL_INSERT_LEAVE_REQUEST		=			" INSERT INTO VHM_EMP_LEAVE_REQUEST 						  " +
																			" (															  " +
																			" VHM_LEAVE_REQ_NO, VHM_LEAVE_REQ_DATE,VHM_STATUS_CODE, " +
																			" VHM_EMP_CODE, VHM_EMP_INTERNET_USR_ID,VHM_LEAVE_START_DATE, " +
																			" VHM_LEAVE_END_DATE, 										  " +
																			" VHM_BRANCH_CODE,VHM_DEPT_CODE, VHM_SECTION_CODE,			  " +
																			" VHM_GRADE_CODE, VHM_DESG_CODE,VHM_EMP_JOB_TYPE,			  " +
																			" VHM_LEAVE_TYPE, VHM_ADMIN_HOLDING_YN,	  " +
																			" VHM_POSITION_CODE,VHM_HIERARCHY_CODE,VHM_LEAVE_REQUEST_ACTIVE," +
																			" VHM_LEAVE_REQUEST_REMARKS,VHM_LEAVE_REQUEST_CRE_USR_INIT,VHM_LEAVE_REQUEST_CRE_DATE," +
																			" VHM_LEAVE_TYPE_FLAG		" +
																			" ) 															" +
																			" VALUES 														" +
																			"(																" +
																			" :paramReqNo,SYSDATE,:paramLeaveStatus,						" +
																			" :paramEmpCode,:paramInternetId,TO_DATE(:paramStartDate,'dd/MM/yyyy'),				" +
																			" TO_DATE(:paramEndDate,'dd/MM/yyyy'),												" +
																			" :paramBranchCode,:paramDepartmentCode,:paramSectionCode,		" +
																			" :paramGradeCode,:paramDesignationCode,:paramJobTypeCode,		" +
																			" :paramLeaveType,:paramIsAdmin,								" +
																			" :paramPositionCode,:paramHierarchyCode,:paramIsReqActive,		" +
																			" :paramReqRemarks,:paramReqCreUserInit,SYSDATE,				" +
																			" :paramLeaveTypeFlag											" +
																			" )																";

	public static final String	SQL_VIEW_LEAVE_REQ_DELEGATION	=			" SELECT														" + 
																			"  REQ_DELG.VHM_LEAVE_REQ_NO AS LEAVE_REQUEST_NO,				" +
																			"  REQ_DELG.VHM_DELEGATED_EMP_CODE AS EMP_CODE,					" +
																			"  SUBSTR(														" +
																			"    EMP.VHM_EMP_SQU_EMAIL,1,									" +
																			"    INSTRB(													" +
																			"          EMP.VHM_EMP_SQU_EMAIL, '@', 1, 1						" +
																			"          )-1													" +
																			"        ) AS EMP_INTERNET_ID,									" +
																			"  DECODE(:paramLocale,											" +
																			"          'en',initCap(EMP.VHM_EMP_NAME),						" +
																			"          'ar',EMP.VHM_EMP_NAME_ARABIC							" +
																			"        ) AS EMP_NAME,											" +  
																			"  TO_CHAR(														" +
																			"			REQ_DELG.VHM_DELEGATED_FROM_DATE,'DD/MM/YYYY'		" +
																			"         ) AS DELEGATE_START_DATE,								" +
																			"  TO_CHAR(														" +
																			"			REQ_DELG.VHM_DELEGATED_TO_DATE,'DD/MM/YYYY'			" +
																			"		   ) AS DELEGATE_END_DATE								" +
																			" FROM															" + 
																			"    VHM_EMP_LEAVE_REQ_DELEGATION REQ_DELG,						" +
																			"    VHM_EMPLOYEE EMP											" +
																			" WHERE															" +
																			"    REQ_DELG.VHM_DELEGATED_EMP_CODE = EMP.VHM_EMP_CODE			" +
																			"    AND REQ_DELG.VHM_LEAVE_REQ_NO = :paramLeaveReqNo			";

	public static final String	SQL_INSERT_LEAVE_REQ_DELEGATION	=			" INSERT INTO VHM_EMP_LEAVE_REQ_DELEGATION 						" +
																			" (  															" +
																			"	VHM_LEAVE_REQ_NO,VHM_DELEGATED_EMP_CODE,					" +
																			"	VHM_DELEGATED_EMP_BRANCH_CODE,VHM_DELEGATED_EMP_DEPT_CODE,	" +
																			"	VHM_DELEGATED_EMP_SECTION_CODE,VHM_DELEGATED_EMP_DESG_CODE,	" +
																			"	VHM_DELEGATED_EMP_GRADE_CODE,								" +
																			"	VHM_DELEGATED_FROM_DATE, VHM_DELEGATED_TO_DATE,				" +
																			"	VHM_DELEGATED_CRE_USER_INIT,VHM_DELEGATED_CRE_DATE,			" +
																			"	VHM_DELEGATED_STATUS										" +
																			" )																" +
																			" VALUES														" +
																			" ( 															" +
																			"	:paramLeaveReqNo,LPAD(:paramDelEmpCode,7,0),				" +
																			"	:paramDelEmpBranchCode,:paramDelEmpDeptCode,				" +
																			"	:paramDelEmpSectionCode,:paramDelEmpDesig,					" +
																			"	:paramDelEmpGradeCode,										" +
																			"	TO_DATE(:paramDelFromDate,'DD/MM/YYYY'), TO_DATE(:paramDelToDate,'DD/MM/YYYY')," +
																			"	:paramDelCreUser,SYSDATE,'A'									" +
																			" )																";

	public static final String	SQL_INSERT_LEAVE_REQ_APPROVE	=			"	INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL					" +
																		    "	(															" +
																		    "  		VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE,						" +
																		    "  		VHM_APP_INTERNET_ID, VHM_APP_HIERARCHY_CODE,			" +
																		    "  		VHM_APP_BRANCH_CODE,VHM_APP_DEPT_CODE,					" +
																		    "  		VHM_APP_SECTION_CODE,VHM_APP_DESG_CODE,					" +
																		    "  		VHM_APP_RECEIVED_DATE,VHM_APP_ACTION_DATE,				" +
																		    "  		VHM_ACTION_CODE,VHM_APP_REMARKS,						" +
																		    "  		VHM_APP_CRE_USER_INIT,VHM_APP_CRE_DATE,					" +
																		    "		VHM_APP_SEQ_NO											" +
																		    "	 )															" +
																		    "    VALUES 													" +
																		    "	(															" +
																		    "		:paramReqNo, :paramEmpNo,								" +
																		    "		:paramEmpInternetId, :paramHierarchyCode,				" +
																		    "		:paramBranchCode, :paramDeptCode,						" +
																		    "		:paramSectionCode, :paramDesgCode,						" +
																		    "		 SYSDATE,SYSDATE,										" +
																		    "		:paramActionCode,:paramRemarks,							" +
																		    "		:paramCreateUsr,SYSDATE,								" +
																		    "		:paramSeqNo												" +
																		    "	)															";
	
	/******************************************************/
	/**********TABLE COLUMN CONSTANTS**********************/
	
	public static final	String	CONST_EMP_CODE					=			"EMP_CODE";
	public static final	String	CONST_EMP_INTERNET_ID			=			"EMP_INTERNET_ID";
	public static final	String	CONST_EMP_NAME					=			"EMP_NAME";
	public static final	String	CONST_EMP_JOB_TYPE_CODE			=			"EMP_JOB_TYPE_CODE";
	public static final	String	CONST_EMP_DESIGNATION_CODE		=			"EMP_DESIGNATION_CODE";
	public static final	String	CONST_EMP_DESIGNATION			=			"EMP_DESIGNATION";
	public static final	String	CONST_EMP_ADDL_POSITION_CODE	=			"EMP_ADDITIONAL_POSITION_CODE";
	public static final	String	CONST_EMP_ADDL_POSITION_DESC	=			"EMP_ADDITIONAL_POSITION_DESC";
	public static final	String	CONST_EMP_ADMIN					=			"EMP_ADMIN";
	public static final	String	CONST_EMP_GRADE_CODE			=			"EMP_GRADE_CODE";
	public static final	String	CONST_EMP_GRADE					=			"EMP_GRADE";
	public static final	String	CONST_EMP_GRADE_SHORT			=			"EMP_GRADE_SHORT";
	public static final	String	CONST_EMP_BRANCH_CODE			=			"EMP_BRANCH_CODE";
	public static final	String	CONST_EMP_BRANCH				=			"EMP_BRANCH";
	public static final	String	CONST_EMP_DEPARTMENT_CODE		=			"EMP_DEPARTMENT_CODE";
	public static final	String	CONST_EMP_DEPARTMENT			=			"EMP_DEPARTMENT";
	public static final	String	CONST_EMP_DEPARTMENT_SHORT		=			"EMP_DEPARTMENT_SHORT";
	public static final	String	CONST_EMP_SECTION_CODE			=			"EMP_SECTION_CODE";
	public static final	String	CONST_EMP_SECTION				=			"EMP_SECTION";
	public static final	String	CONST_EMP_JOIN_DATE				=			"EMP_JOIN_DATE";
	public static final	String	CONST_EMP_HIERARCHY_CODE		=			"EMP_HIERARCHY_CODE";
	public static final	String	CONST_EMP_RELIGION_CODE			=			"EMP_RELIGION_CODE";
	public static final	String	CONST_EMP_OMANI					=			"EMP_OMANI";
	public static final	String	CONST_EMP_SEX					=			"EMP_SEX";
	
	public static final	String	CONST_LEAVE_REQUEST_NO			=			"LEAVE_REQUEST_NO";
	public static final	String	CONST_LEAVE_REQ_DATE			=			"LEAVE_REQ_DATE";
	public static final	String	CONST_LEAVE_START_DATE			=			"LEAVE_START_DATE";
	public static final	String	CONST_LEAVE_END_DATE			=			"LEAVE_END_DATE";
	public static final	String	CONST_LEAVE_STATUS				=			"LEAVE_STATUS";
	public static final	String	CONST_LEAVE_STATUS_CODE			=			"LEAVE_STATUS_CODE";
	public static final	String	CONST_LEAVE_TYPE				=			"LEAVE_TYPE";
	public static final	String	CONST_LEAVE_DESC				=			"LEAVE_DESC";
	public static final	String	CONST_LEAVE_REQ_REMARK			=			"LEAVE_REQ_REMARK";
	public static final	String	CONST_ACTION_CODE				=			"ACTION_CODE";
	public static final	String	CONST_ACTION_DESC				=			"ACTION_DESC";
	public static final	String	CONST_DELEGATE_START_DATE		=			"DELEGATE_START_DATE";
	public static final	String	CONST_DELEGATE_END_DATE			=			"DELEGATE_END_DATE";
	public static final	String	CONST_DELEGATE_STATUS			=			"DELEGATE_STATUS";
	
	public static final	String	CONST_COUNTER					=			"COUNTER";
	
	public static final	String	USER_WEB						=			"WEB";
	public static final	String	CONST_LEAVE_STATUS_WAITING_APPV	=			"0000000001";
	
	/******************************************************/
	
	/**********CONSTANTS***********************************/
	
	public static final	String	CONST_OPERATION					=			"operation";
	public static final	String	CONST_OPERATION_ADD				=			"addNew";
	public static final	String	CONST_OPERATION_UPDATE			=			"update";
	public static final	String	CONST_ADMIN_TRUE				=			"Y";
	public static final	String	CONST_ADMIN_FALSE				=			"N";
	public static final String	CONST_LEAVE_REQ_ACTIVE_TRUE		=			"Y";
	public static final	String	CONST_LANG_DEFAULT_EN			=			"en";
	
	public static final	String	CONST_MUSLIM_NUM				=			"1";
	public static final	String	CONST_MUSLIM					=			"Y";
	public static final	String	CONST_NON_MUSLIM				=			"N";
	
	public static final	String	CONST_GENDER_FEMALE_NUM			=			"1";
	public static final	String	CONST_GENDER_FEMALE				=			"Y";
	public static final	String	CONST_GENDER_NON_FEMALE			=			"N";
	
	public static final	String	CONST_OMANI_NUM					=			"1";
	public static final	String	CONST_OMANI						=			"Y";
	public static final	String	CONST_NON_OMANI					=			"N";
	public static final	String	CONST_EMP_SENIOR				=			"senior";
	
	public static final	String	CONST_ALLOW_ELEAVE_REQUEST_MSG	=			"allowELeaveRequestMsg";
	
	/******************************************************/
	
	
	/***********STORED PROCEDURE**************************/
	public static final	String	CONST_PROC_ALLOW_eLEAVE_REQUEST			=			"allow_eleave_request";
	public static final	String	CONST_PROC_COL_IN_P_EMP_CODE			=			"P_EMP_CODE";
	public static final	String	CONST_PROC_COL_IN_P_LEAVE_FLAG			=			"P_LEAVE_FLAG";
	public static final	String	CONST_PROC_COL_IN_P_LEAVE_START			=			"P_LEAVE_START";
	public static final	String	CONST_PROC_COL_IN_P_LEAVE_END			=			"P_LEAVE_END";
	public static final	String	CONST_PROC_COL_OUT_P_ACCEPT_LEAVE_YN	=			"P_ACCEPT_LEAVE_YN";
	public static final	String	CONST_PROC_COL_OUT_P_LEAVE_CODE			=			"P_LEAVE_CODE";
	public static final	String	CONST_PROC_COL_OUT_P_MSG_ENGLISH		=			"P_MSG_ENG";
	public static final	String	CONST_PROC_COL_OUT_P_MSG_ARABIC			=			"P_MSG_ARB";
	
	
	/******************************************************/
	
	/**********JSP PAGES**********************************/
	public static String	PAGE_WELCOME						=			"welcome";
	public static String	PAGE_LEAVE_APPLY_FORM				=			"leaveApplicationForm";
	public static String	PAGE_LEAVE_APPROVE_FORM				=			"leaveApplicationApproveForm";
	
	
	/******************************************************/
	
	/**********LDAP***************************************/
	public	static	String	LDAP_COMMON_NAME					=			"cn";
	public	static	String	LDAP_EMPLOYEE_NUMBER				=			"employeenumber"; 
	public	static	String	LDAP_EMPLOYEE_NUMBER2				=			"description"; 
	/******************************************************/
	
	/************* PROPERTY FILE ******************************/
	public	static	String	RESOURCE_PROPERTY_FILE_NAME			=			"messages";
	/******************************************************/
	
	
	
	
}
