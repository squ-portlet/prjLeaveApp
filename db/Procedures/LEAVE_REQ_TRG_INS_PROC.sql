create or replace PROCEDURE Leave_Req_Trg_Ins_Proc(suggested_app_emp_new  IN VARCHAR2, status_code_new IN VARCHAR2, 
                                                                                            emp_code_new IN VARCHAR2, branch_code IN VARCHAR2, leave_req IN VARCHAR2, leave_type_flag IN VARCHAR2) AS
																							
       CURSOR emp_crsr(p_emp VARCHAR2) IS
            SELECT   VHM_EMP_CODE, VHM_HIERARCHY_CODE,
                            VHM_EMP_BRAN_CODE, VHM_EMP_DEPT_CODE,
                            VHM_EMP_SECTION_CODE, VHM_EMP_DESG_CODE
             FROM  VHM_EMPLOYEE e,   VHM_DESIGNATION d
             WHERE e.VHM_EMP_DESG_CODE = d.VHM_DESG_CODE(+)
             AND  VHM_EMP_CODE  = NVL(p_emp,'XxX');
  			
		CURSOR approvals_crsr   IS  
		    SELECT VHM_EMP_CODE    EMP_ID, VHM_HIERARCHY_CODE,
                          VHM_EMP_BRAN_CODE, VHM_EMP_DEPT_CODE,
                          VHM_EMP_SECTION_CODE, VHM_EMP_DESG_CODE,
			              '2' seq, '* This the second approval for Sabatical Leave ' app_narr, 1 ord
            FROM EMP_HIERARCHY_VW
            WHERE   VHM_EMP_BRAN_CODE  = branch_code
            AND  VHM_HIERARCHY_CODE = '012'   -- dean
	      UNION 
	         SELECT VEAT_EMP_CODE  EMP_ID, VHM_HIERARCHY_CODE,
                          VEAT_EMP_BRANCH_CODE VHM_EMP_BRAN_CODE,  VEAT_EMP_DEPT_CODE VHM_EMP_DEPT_CODE,
                          'NA' VHM_EMP_SECTION_CODE, VEAT_EMP_DESG_CODE VHM_EMP_DESG_CODE,
						   '2' seq, '* This the second approval for Sabatical Leave ' app_narr, 2 ord
             FROM EMP_ADDED_TASK_HIERARCHY_VW
             WHERE    VEAT_EMP_BRANCH_CODE = branch_code
             AND  VHM_HIERARCHY_CODE = '012'    -- dean
             AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE)   
		  UNION
	         SELECT VHM_EMP_CODE       EMP_ID, VHM_HIERARCHY_CODE,
                          VHM_EMP_BRAN_CODE, VHM_EMP_DEPT_CODE,
                          VHM_EMP_SECTION_CODE, VHM_EMP_DESG_CODE,
						  '2' seq, '* This the second approval for Sabatical Leave ' app_narr , 3 ord
             FROM EMP_HIERARCHY_VW
             WHERE   VHM_EMP_BRAN_CODE      = branch_code
             AND  VHM_HIERARCHY_CODE =  '013'    -- assitance dean
	      UNION 
            SELECT VEAT_EMP_CODE     EMP_ID, VHM_HIERARCHY_CODE,
                          VEAT_EMP_BRANCH_CODE VHM_EMP_BRAN_CODE,  VEAT_EMP_DEPT_CODE VHM_EMP_DEPT_CODE,
                          'NA' VHM_EMP_SECTION_CODE, VEAT_EMP_DESG_CODE VHM_EMP_DESG_CODE,
						  '2' seq, '* This the second approval for Sabatical Leave ' app_narr , 4  ord
            FROM EMP_ADDED_TASK_HIERARCHY_VW
            WHERE    VEAT_EMP_BRANCH_CODE      = branch_code
            AND  VHM_HIERARCHY_CODE  = '013'    -- assitance dean
            AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE)              
		 UNION
	        SELECT VHM_EMP_CODE    EMP_ID, VHM_HIERARCHY_CODE,
                          VHM_EMP_BRAN_CODE, VHM_EMP_DEPT_CODE,
                          VHM_EMP_SECTION_CODE, VHM_EMP_DESG_CODE,
						   '3' seq, '* This the third approval for Sabatical Leave - Deputy VC' app_narr , 5 ord
            FROM VHM_EMPLOYEE e, vhm_designation d
            WHERE e.VHM_EMP_DESG_CODE = d.VHM_DESG_CODE(+)   
			AND VHM_EMP_DESG_CODE = '6360'
            AND NVL(vhm_emp_active,'N')  = 'Y'
	     UNION 
            SELECT VEAT_EMP_CODE     EMP_ID, VHM_HIERARCHY_CODE,
                          VEAT_EMP_BRANCH_CODE VHM_EMP_BRAN_CODE,  VEAT_EMP_DEPT_CODE VHM_EMP_DEPT_CODE,
                          'NA' VHM_EMP_SECTION_CODE, VEAT_EMP_DESG_CODE VHM_EMP_DESG_CODE,
						  '3' seq, '* This the third approval for Sabatical Leave - Deputy VC' app_narr , 6 ord
            FROM EMP_ADDED_TASK_HIERARCHY_VW
            WHERE   VEAT_EMP_DESG_CODE    = '6360' 
            AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE)
		 UNION
	        SELECT VHM_EMP_CODE    EMP_ID, VHM_HIERARCHY_CODE,
                          VHM_EMP_BRAN_CODE, VHM_EMP_DEPT_CODE,
                          VHM_EMP_SECTION_CODE, VHM_EMP_DESG_CODE,
						  '4', '* This the last approval for Sabatical Leave - SQU  VC' app_narr , 7 ord
            FROM VHM_EMPLOYEE e, vhm_designation d
            WHERE e.VHM_EMP_DESG_CODE = d.VHM_DESG_CODE(+)   
			AND    VHM_EMP_DESG_CODE = '1052'
            AND NVL(vhm_emp_active,'N')  = 'Y'
	    UNION 
           SELECT VEAT_EMP_CODE     EMP_ID, VHM_HIERARCHY_CODE,
                         VEAT_EMP_BRANCH_CODE VHM_EMP_BRAN_CODE,  VEAT_EMP_DEPT_CODE VHM_EMP_DEPT_CODE,
                          'NA' VHM_EMP_SECTION_CODE, VEAT_EMP_DESG_CODE VHM_EMP_DESG_CODE,
						  '4', '* This the last approval for Sabatical Leave - SQU  VC' app_narr , 8 ord
           FROM EMP_ADDED_TASK_HIERARCHY_VW
           WHERE   VEAT_EMP_DESG_CODE    = '1052' 
           AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE)
		   ORDER BY 9;
																				
		v_mgr_emp 	             VHM_EMPLOYEE.VHM_EMP_CODE%TYPE;
	    v_hint                         VARCHAR2 (20);
		remarks                      VARCHAR2(200);
		v_emp_rec                  emp_crsr%ROWTYPE;
		cnt2                           NUMBER := 0;
		cnt3                           NUMBER := 0;
		cnt4                           NUMBER := 0;
		
BEGIN

   IF suggested_app_emp_new IS NULL THEN  /*1*/ 
            get_higher_mgr_process (emp_code_new,  v_mgr_emp , v_hint  );
		    remarks := '* This Approver has been Auto Selected by the Leave Process using: ' || v_hint;
   ELSE /*1*/ 
      	    v_mgr_emp := suggested_app_emp_new;
		    remarks := '* This Approver has been manually selected by the Leave Requester *';
   END IF;	/*1*/ 	
   
   IF   v_mgr_emp IS NOT NULL THEN /*2*/
            OPEN emp_crsr (v_mgr_emp);
			FETCH emp_crsr INTO v_emp_rec;
           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                          ( VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE, VHM_APP_HIERARCHY_CODE,
                             VHM_APP_BRANCH_CODE, VHM_APP_DEPT_CODE, VHM_APP_SECTION_CODE,
                             VHM_APP_DESG_CODE, VHM_APP_RECEIVED_DATE, VHM_APP_REMARKS,
                             VHM_APP_SEQ_NO, VHM_APP_CRE_USER_INIT, VHM_APP_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
	         VALUES ( leave_req , v_emp_rec.vhm_emp_code, v_emp_rec.vhm_hierarchy_code,
                             v_emp_rec.vhm_emp_bran_code, v_emp_rec.vhm_emp_dept_code, v_emp_rec.vhm_emp_section_code,
                             v_emp_rec.vhm_emp_desg_code, SYSDATE, remarks, 
                		      1,  'WEB',  SYSDATE,  'L' );
		     CLOSE  emp_crsr ;
   END IF; /*2*/
   
    IF leave_type_flag  = 'B' THEN  /*3*/  --- sabatical leave only
	      FOR i IN approvals_crsr LOOP
                IF i.seq = '2' THEN 
				     IF cnt2 = 0 THEN
					     cnt2 := 1;
						 INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                             ( VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE, VHM_APP_HIERARCHY_CODE,
                                               VHM_APP_BRANCH_CODE, VHM_APP_DEPT_CODE, VHM_APP_SECTION_CODE,
                                               VHM_APP_DESG_CODE, VHM_APP_RECEIVED_DATE, VHM_APP_REMARKS,
                                               VHM_APP_SEQ_NO, VHM_APP_CRE_USER_INIT, VHM_APP_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
	                            VALUES ( leave_req , i.emp_id, i.vhm_hierarchy_code,
                                               i.vhm_emp_bran_code, i.vhm_emp_dept_code, i.vhm_emp_section_code,
                                               i.vhm_emp_desg_code, SYSDATE, i.app_narr, 
                		                       i.seq,   'WEB',  SYSDATE,  'L' );
					 END IF;
				ELSIF i.seq = '3' THEN
				     IF cnt3 = 0 THEN
					     cnt3 := 1;
						 INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                             ( VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE, VHM_APP_HIERARCHY_CODE,
                                               VHM_APP_BRANCH_CODE, VHM_APP_DEPT_CODE, VHM_APP_SECTION_CODE,
                                               VHM_APP_DESG_CODE, VHM_APP_RECEIVED_DATE, VHM_APP_REMARKS,
                                               VHM_APP_SEQ_NO, VHM_APP_CRE_USER_INIT, VHM_APP_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
	                            VALUES ( leave_req , i.emp_id, i.vhm_hierarchy_code,
                                               i.vhm_emp_bran_code, i.vhm_emp_dept_code, i.vhm_emp_section_code,
                                               i.vhm_emp_desg_code, SYSDATE, i.app_narr, 
                		                       i.seq,   'WEB',  SYSDATE,  'L' );
					 END IF;
				ELSIF i.seq = '4' THEN
				     IF cnt4 = 0 THEN
					     cnt4 := 1;
						 INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                             ( VHM_LEAVE_REQ_NO, VHM_APP_EMP_CODE, VHM_APP_HIERARCHY_CODE,
                                               VHM_APP_BRANCH_CODE, VHM_APP_DEPT_CODE, VHM_APP_SECTION_CODE,
                                               VHM_APP_DESG_CODE, VHM_APP_RECEIVED_DATE, VHM_APP_REMARKS,
                                               VHM_APP_SEQ_NO, VHM_APP_CRE_USER_INIT, VHM_APP_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
	                            VALUES ( leave_req , i.emp_id, i.vhm_hierarchy_code,
                                               i.vhm_emp_bran_code, i.vhm_emp_dept_code, i.vhm_emp_section_code,
                                               i.vhm_emp_desg_code, SYSDATE, i.app_narr, 
                		                       i.seq,   'WEB',  SYSDATE,  'L' );
					 END IF;
				END IF;		  
		  END LOOP;
	END IF;  /*3*/
	 
END;
