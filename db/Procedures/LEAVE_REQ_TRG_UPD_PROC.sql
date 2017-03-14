create or replace PROCEDURE Leave_Req_Trg_Upd_Proc( emp_code_old IN VARCHAR2,  leave_req IN VARCHAR2, 
																								  return_dt IN DATE,  app_emp_old IN VARCHAR2, app_emp_new IN VARCHAR2,
																								  leave_start_dt IN DATE, leave_end_dt IN DATE,  leave_req_dt IN DATE, process_sal_yn IN VARCHAR2, flag IN VARCHAR2, leave_ind IN VARCHAR2) AS
		
		
	CURSOR emp_crsr   (p_emp VARCHAR2) IS
         SELECT    vhm_hierarchy_code, vhm_emp_bran_code,vhm_emp_dept_code,
                         vhm_emp_section_code,vhm_emp_desg_code
          FROM  vhm_employee e,   vhm_designation d
          WHERE e.vhm_emp_desg_code = d.vhm_desg_code(+)
          AND  vhm_emp_code  = NVL(p_emp,'XxX');
	
	
	
	 v_hint                                        VARCHAR2(50);
	 v_mgr_emp                               vhm_employee.vhm_emp_code%TYPE := app_emp_new; 
	 app_hierarchy_code                   vhm_designation.VHM_HIERARCHY_CODE%TYPE;
	 app_branch_code                       vhm_employee.vhm_emp_bran_code%TYPE;
	 app_dept_code	                        vhm_employee.vhm_emp_dept_code%TYPE;
	 app_section_code	                   vhm_employee.vhm_emp_section_code%TYPE;
	 app_desg_code                         vhm_employee.vhm_emp_desg_code%TYPE;
	 																			
BEGIN

 IF v_mgr_emp IS NULL THEN
     	get_higher_mgr_process ( emp_code_old, v_mgr_emp , v_hint  ); 
 END IF;
 
 IF v_mgr_emp IS NOT NULL THEN
     OPEN emp_crsr (v_mgr_emp);
	 FETCH emp_crsr INTO app_hierarchy_code, app_branch_code, app_dept_code, app_section_code, app_desg_code;
	 CLOSE emp_crsr;
     IF NVL(FLAG,'X') ='I' THEN
	        INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
            ( VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE,  VHM_APP_HIERARCHY_CODE,
              VHM_APP_BRANCH_CODE,  VHM_APP_DEPT_CODE,VHM_APP_SECTION_CODE,
              VHM_APP_DESG_CODE, VHM_APP_RECEIVED_DATE, VHM_APP_REMARKS,
              VHM_APP_SEQ_NO, VHM_APP_CRE_USER_INIT, VHM_APP_CRE_DATE ,VHM_LEAVE_REQ_RTN_IND)
	      VALUES
		   (leave_req,v_mgr_emp, app_hierarchy_code, 
		      app_branch_code, app_dept_code, app_section_code, app_desg_code,
		     SYSDATE,  '* This Approver has been Selected for the Leave Process' ,
              1,   'WEB',  SYSDATE, leave_ind);
	 ELSIF NVL(FLAG,'X') ='U' THEN
	       DBMS_OUTPUT.PUT_LINE('TRYING TO UPDATE LEAVE REQUEST APPROVAL'||V_MGR_EMP||'-'||leave_req||'-'||LEAVE_IND||'-'||app_emp_old);
	       UPDATE VHM_EMP_LEAVE_REQUEST_APPROVAL 
			SET VHM_APP_EMP_CODE = v_mgr_emp,
                   VHM_APP_HIERARCHY_CODE 	= app_hierarchy_code,
                    VHM_APP_BRANCH_CODE 	=  app_branch_code,
                    VHM_APP_DEPT_CODE 	       = app_dept_code,
                    VHM_APP_SECTION_CODE = app_section_code,
                    VHM_APP_DESG_CODE = app_desg_code,
					VHM_APP_RECEIVED_DATE = SYSDATE,
                    VHM_APP_ACTION_DATE = NULL,
                    VHM_ACTION_CODE 	= NULL		
				   WHERE VHM_LEAVE_REQ_NO = NVL( leave_req,'XxX')
				   AND NVL(VHM_LEAVE_REQ_RTN_IND,'L') = leave_ind
 				   AND VHM_APP_EMP_CODE = app_emp_old ;
	 END IF;
 END IF;

   
END;
