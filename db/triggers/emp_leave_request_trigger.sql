CREATE OR REPLACE TRIGGER "SHMAIN".TRGR_EMP_LEAVE_REQUEST BEFORE  INSERT  OR UPDATE ON  VHM_EMP_LEAVE_REQUEST
	REFERENCING OLD AS OLD NEW AS NEW
        FOR EACH ROW
	WHEN (NEW.VHM_LEAVE_REQ_NO  IS NOT NULL OR OLD.VHM_LEAVE_REQ_NO  IS NOT NULL )
DECLARE 
 
    CURSOR chk_leave_crsr IS
	       SELECT 'x'
		   FROM EMP_LEAVE_AVAIL
 		   WHERE  ELA_EMP_CODE = :OLD.VHM_EMP_CODE
		   AND ELA_START_DATE = :OLD.VHM_LEAVE_START_DATE 
           AND ELA_END_DATE  = :OLD.VHM_LEAVE_END_DATE;
		   
	
	CURSOR chk_prv_leave_crsr IS
 	     SELECT portal_vhm_leave_req_no
  		 FROM EMP_LEAVE_AVAIL 
         WHERE  ela_end_date = :NEW.VHM_LEAVE_START_DATE -1
 		 AND ela_leave_type = '0010';
		 
	app_emp_old             VHM_EMPLOYEE.VHM_EMP_CODE%TYPE := :OLD.VHM_SUGGESTED_APP_EMP_CODE;	
	app_emp_new           VHM_EMPLOYEE.VHM_EMP_CODE%TYPE := :OLD.VHM_RETURN_APP_EMP_CODE;	
	return_dt                    DATE;  
	flag                            VARCHAR2(1) := NULL;
	leave_ind                    VARCHAR2(1);
	leave_exists_yn          VARCHAR2(1);
	v_process_sal_yn       VARCHAR2(1); 
	l_ind                           VARCHAR2(1) := 'L';
	emp_leave_avail_ins    VARCHAR2(1) := 'N';
	req_no                        VARCHAR2(10) := NULL;
	leave_extension          VARCHAR2(1) := 'L';
	
BEGIN

IF INSERTING AND  :NEW.VHM_STATUS_CODE = '0000000001' THEN   /*1*/  
     Leave_Req_Trg_Ins_Proc(:NEW.VHM_SUGGESTED_APP_EMP_CODE, :NEW.VHM_STATUS_CODE, 
                                          :NEW.VHM_EMP_CODE, :NEW.VHM_BRANCH_CODE, :NEW.VHM_LEAVE_REQ_NO, :NEW.VHM_LEAVE_TYPE_FLAG);
										  
								 
	leave_ind := 'L';
ELSIF UPDATING THEN /*1*/ 
           DBMS_OUTPUT.PUT_LINE('Enters in UPDATING');
           return_dt  := NVL(:NEW.VHM_RETURN_DATE, :OLD.VHM_RETURN_DATE);
		   IF return_dt IS NOT NULL THEN /*2*/
		          leave_ind := 'R';
				  app_emp_old    := :OLD.VHM_RETURN_APP_EMP_CODE;
				  app_emp_new   := :NEW.VHM_RETURN_APP_EMP_CODE;
		          IF  :NEW.VHM_STATUS_CODE = '0000000001' THEN /*3*/  
				           IF :OLD.VHM_STATUS_CODE = '0000000001' THEN /*4*/
									     FLAG := 'U';
						   ELSIF  :OLD.VHM_STATUS_CODE = '0000000002' THEN /*4*/
						                 FLAG := 'I';
						   ELSIF  :OLD.VHM_STATUS_CODE = '0000000004' THEN /*4*/
						                 IF NVL(app_emp_new,'xXx') <> app_emp_old THEN
		                                         FLAG := 'I';
										 ELSE
										         FLAG := 'U';
		                                 END IF;
						   END IF; /*4*/
				  ELSIF  :NEW.VHM_STATUS_CODE = '0000000002' THEN /*3*/ 
				           UPDATE EMP_LEAVE_AVAIL
 			               SET ELA_RETURN_DATE = return_dt,
                                  ELA_RETURN_POSTED_YN 	='Y',
                                  ELA_RETURN_POSTED_USER 	='WEB',
                                  ELA_RETURN_POSTED_DATE 	=TRUNC(SYSDATE)
                            WHERE PORTAL_VHM_LEAVE_REQ_NO = :OLD.VHM_LEAVE_REQ_NO;
				  END IF; /*3*/
		   ELSE /*2*/
		        DBMS_OUTPUT.PUT_LINE('Enters in UPDATING of LEAVE');
		        leave_ind := 'L';
				app_emp_old    := :OLD.VHM_SUGGESTED_APP_EMP_CODE;
				app_emp_new   := :NEW.VHM_SUGGESTED_APP_EMP_CODE;
				IF  :NEW.VHM_STATUS_CODE = '0000000001' THEN /*3*/  
				           IF :OLD.VHM_STATUS_CODE = '0000000001' THEN /*4*/
						                 DBMS_OUTPUT.PUT_LINE('NEW AND OLD LEAVE STATUS IS WAITING FOR APPROVAL');
									     FLAG := 'U';
						   ELSIF  :OLD.VHM_STATUS_CODE = '0000000004' THEN /*4*/
						                  IF NVL(app_emp_new,'xXx') <> app_emp_old THEN
		                                         FLAG := 'I';
										 ELSE
										         FLAG := 'U';
		                                 END IF;
						   END IF; /*4*/
				  ELSIF  :NEW.VHM_STATUS_CODE = '0000000002' THEN /*3*/ 
				          UPDATE  VHM_EMP_LEAVE_REQ_DELEGATION
	                      SET VHM_DELEGATED_STATUS = 'A' --- A: Approved
		                   WHERE VHM_LEAVE_REQ_NO = NVL(:NEW.VHM_LEAVE_REQ_NO,'XxX') ;
			             IF (  ( :NEW.VHM_LEAVE_END_DATE  -   :NEW.VHM_LEAVE_START_DATE) + 1  ) <14 THEN
			                     v_PROCESS_SAL_YN := 'N' ;
			             ELSIF :NEW.VHM_LEAVE_TYPE NOT IN ('0010', '0040' , '0050' , '0060', '0080') THEN
			                    v_PROCESS_SAL_YN := 'N' ;			
		                 ELSE
			                    v_PROCESS_SAL_YN := :NEW.VHM_PROCESS_SAL_YN  ;			
			             END IF;
						 OPEN chk_prv_leave_crsr;
		                 FETCH chk_prv_leave_crsr INTO req_no;
		                 IF chk_prv_leave_crsr%FOUND THEN
 		                      :NEW.VHM_EXTENSION_OF_LEAVE := req_no;
							  leave_extension := 'T';
		                 END IF;
		                 CLOSE chk_prv_leave_crsr;	
			              OPEN chk_leave_crsr;
			              FETCH chk_leave_crsr INTO leave_exists_yn;
			              IF chk_leave_crsr%NOTFOUND THEN
						           INSERT INTO EMP_LEAVE_AVAIL ( ELA_EMP_CODE, ELA_LEAVE_TYPE, ELA_REQ_DATE 
                                                                              , ELA_START_DATE, ELA_END_DATE, ELA_NO_OF_DAYS   
                                                                              , ELA_CRE_USER_INIT, ELA_CRE_DT, ELA_USER_APP_YN, ELA_USER_APP_USER  
                                                                              , ELA_USER_APP_DATE, ELA_PROCESS_SAL_YN, ELA_PER_APP_YN, ELA_LEAVE_SAL_PAID_YN  
                                                                              , ELA_RETURN_POSTED_YN, ELA_PER_RETURN_POST_YN, ELA_PAY_RETURN_APP_YN   
                                                                              , ELA_LEAVE_IND, ELA_DOC_NO, PORTAL_VHM_LEAVE_REQ_NO,PORTAL_LEAVE_REQ_NO_REF  )
                                                            	VALUES(:NEW.VHM_EMP_CODE, :NEW.VHM_LEAVE_TYPE, :NEW.VHM_LEAVE_REQ_DATE 
                                                                             ,:NEW.VHM_LEAVE_START_DATE, :NEW.VHM_LEAVE_END_DATE, ( :NEW.VHM_LEAVE_END_DATE -   :NEW.VHM_LEAVE_START_DATE) + 1
                                                                             , 'WEB', SYSDATE, 'Y', 'WEB',  SYSDATE, v_PROCESS_SAL_YN, 'N' , 'N', 'N', 'N', 'N' ,leave_extension, LPAD(EMP_LEAVE_DOC.NEXTVAL,9,'0'), :NEW.VHM_LEAVE_REQ_NO,
																			  req_no);
                                      DBMS_OUTPUT.PUT_LINE('INSERTING INTO EMP_LEAVE_AVAIL');
			              END IF;
			             CLOSE chk_leave_crsr;
				  ELSIF   :NEW.VHM_STATUS_CODE = '0000000005'   THEN  /*3*/ 
                            UPDATE  VHM_EMP_LEAVE_REQUEST_APPROVAL
		                    SET VHM_ACTION_CODE = '0000000007'
                            WHERE VHM_LEAVE_REQ_NO = NVL( :OLD.VHM_LEAVE_REQ_NO,'XxX')
                            AND VHM_ACTION_CODE IS NULL 
 		                    AND NVL(VHM_LEAVE_REQ_RTN_IND,'L') = leave_ind; 
				  END IF; /*3*/
		   END IF; /*2*/
		  
		IF flag IS NOT NULL THEN
            Leave_Req_Trg_Upd_Proc( :OLD.VHM_EMP_CODE,  :OLD.VHM_LEAVE_REQ_NO, 
													return_dt,  app_emp_old , app_emp_new ,
													:NEW.VHM_LEAVE_START_DATE, :NEW.VHM_LEAVE_END_DATE,  
													:OLD.VHM_LEAVE_REQ_DATE, v_PROCESS_SAL_YN, flag, leave_ind) ;
		END IF;
							
END IF;  /*1*/

IF NVL(:NEW.VHM_STATUS_CODE,'XxX') <> :OLD.VHM_STATUS_CODE THEN
      INSERT INTO VHM_WORKFLOW_STATUS_LOG
                  (  VHM_STATUS_CODE,   VHM_STATUS_DATE,   VHM_SERVICE_TYPE ,
                      VHM_LEAVE_REQ_NO   ,   VHM_STATUS_REMARKS  ,
                       VHM_CRE_USER_INIT   ,   VHM_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
		  VALUES (  :NEW.VHM_STATUS_CODE , SYSDATE, 'LEAVE' ,
		                 :NEW.VHM_LEAVE_REQ_NO,:NEW.VHM_LEAVE_REQUEST_REMARKS   ,   -- instead of "Auto Inserted by Trigger"
						 'WEB', SYSDATE,leave_ind);
END IF;
DBMS_OUTPUT.PUT_LINE('RETURNS TO  MAIN TRIGGER');
  
END;
