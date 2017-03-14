TRIGGER "SHMAIN".TRGR_EMP_LEAVE_REQUEST AFTER  INSERT  OR UPDATE ON  VHM_EMP_LEAVE_REQUEST
	REFERENCING OLD AS OLD NEW AS NEW
        FOR EACH ROW
	WHEN (NEW.VHM_LEAVE_REQ_NO  IS NOT NULL OR OLD.VHM_LEAVE_REQ_NO  IS NOT NULL )
DECLARE 
 
    CURSOR emp_crsr   (p_emp VARCHAR2) IS
       SELECT   VHM_EMP_CODE  VHM_APP_EMP_CODE, VHM_HIERARCHY_CODE  VHM_APP_HIERARCHY_CODE,
              VHM_EMP_BRAN_CODE VHM_APP_BRANCH_CODE,
              VHM_EMP_DEPT_CODE VHM_APP_DEPT_CODE,
             VHM_EMP_SECTION_CODE  VHM_APP_SECTION_CODE,
              VHM_EMP_DESG_CODE VHM_APP_DESG_CODE
       FROM  VHM_EMPLOYEE e,   VHM_DESIGNATION d
      WHERE e.VHM_EMP_DESG_CODE = d.VHM_DESG_CODE(+)
         AND  VHM_EMP_CODE  = NVL(p_emp,'XxX');
  			 
		 	 
	v_mgr_emp 	VHM_EMPLOYEE.VHM_EMP_CODE%TYPE;
	 
	v_hint VARCHAR2 (20);
 	
	-------------------------------- the followings declarations for the when updating trigger -----------------------------------
	CURSOR renew_approval_crsr IS
	    SELECT   VHM_LEAVE_REQ_NO  ,   VHM_APP_EMP_CODE ,   VHM_APP_INTERNET_ID ,   VHM_APP_HIERARCHY_CODE  ,
                       VHM_APP_BRANCH_CODE   ,   VHM_APP_DEPT_CODE   ,   VHM_APP_SECTION_CODE   ,   VHM_APP_DESG_CODE   ,
                       VHM_APP_SEQ_NO  ,VHM_APP_CRE_USER_INIT
           FROM VHM_EMP_LEAVE_REQUEST_APPROVAL
          WHERE VHM_LEAVE_REQ_NO = NVL( :OLD.VHM_LEAVE_REQ_NO,'XxX')
         AND NVL(VHM_ACTION_CODE,'xxX') = '0000000002' 
         ORDER BY  VHM_APP_ACTION_DATE  DESC;
   
	nw_aprvl_rec renew_approval_crsr%ROWTYPE;
	
	CURSOR emp_dean_crsr (p_branch VARCHAR2  )  IS  
		  SELECT VHM_EMP_CODE    EMP_ID
          FROM EMP_HIERARCHY_VW
         WHERE   VHM_EMP_BRAN_CODE      = p_branch
         AND  VHM_HIERARCHY_CODE = '012'   -- dean
	  UNION 
	         SELECT VEAT_EMP_CODE  EMP_ID
       FROM EMP_ADDED_TASK_HIERARCHY_VW
       WHERE    VEAT_EMP_BRANCH_CODE      = p_branch
       AND  VHM_HIERARCHY_CODE = '012'    -- dean
       AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE)  ;
	    
		CURSOR emp_astnt_dean_crsr (p_branch VARCHAR2   )  IS  
	  SELECT VHM_EMP_CODE       EMP_ID
         FROM EMP_HIERARCHY_VW
       WHERE   VHM_EMP_BRAN_CODE      = p_branch
       AND  VHM_HIERARCHY_CODE =  '013'    -- assitance dean
	  UNION 
         SELECT VEAT_EMP_CODE     EMP_ID
       FROM EMP_ADDED_TASK_HIERARCHY_VW
       WHERE    VEAT_EMP_BRANCH_CODE      = p_branch
       AND  VHM_HIERARCHY_CODE  = '013'    -- assitance dean
       AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE);
	   
	   CURSOR emp_dupty_VC_crsr IS 
	     SELECT VHM_EMP_CODE    EMP_ID
         FROM VHM_EMPLOYEE
         WHERE    VHM_EMP_DESG_CODE = '6360'
          AND vhm_emp_active  = 'Y'
	  UNION 
         SELECT VEAT_EMP_CODE     EMP_ID
       FROM EMP_ADDED_TASK_HIERARCHY_VW
       WHERE   VEAT_EMP_DESG_CODE    = '6360' 
       AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE);

	  CURSOR emp_VC_crsr IS 
	     SELECT VHM_EMP_CODE    EMP_ID
         FROM VHM_EMPLOYEE
         WHERE    VHM_EMP_DESG_CODE = '1052'
          AND vhm_emp_active  = 'Y'
	  UNION 
         SELECT VEAT_EMP_CODE     EMP_ID
       FROM EMP_ADDED_TASK_HIERARCHY_VW
       WHERE   VEAT_EMP_DESG_CODE    = '1052' 
       AND SYSDATE BETWEEN  NVL(VEAT_FROM_DATE,SYSDATE) AND NVL( VEAT_TO_DATE,SYSDATE);
	   
	   CURSOR chk_leave_crsr IS
	       SELECT 'x'
		   FROM EMP_LEAVE_AVAIL
 		   WHERE  ELA_EMP_CODE = :OLD.VHM_EMP_CODE
		   AND ELA_START_DATE = :OLD.VHM_LEAVE_START_DATE 
           AND ELA_END_DATE  = :OLD.VHM_LEAVE_END_DATE;
		   
		   LEAVE_EXISTS_YN    VARCHAR2(1);
		     v_app_emp_id           vhm_employee.vhm_emp_code%TYPE; 
	v_emp_rec                         emp_crsr%ROWTYPE;
    v_PROCESS_SAL_YN       VARCHAR2(1); 
	
	rmk      VARCHAR2(2000) ; -- for testing purpose
BEGIN

IF INSERTING AND  :NEW.VHM_STATUS_CODE = '0000000001' THEN   /*1*/  
   --- inserting the first direct HOD into   VHM_EMP_LEAVE_REQUEST_APPROVAL table
  IF :NEW.VHM_SUGGESTED_APP_EMP_CODE IS NULL THEN  /*2*/ 
          get_higher_mgr_process ( :NEW.VHM_EMP_CODE,  v_mgr_emp , v_hint  );
	      IF v_mgr_emp IS NOT NULL THEN  /*3*/
			OPEN emp_crsr (v_mgr_emp);
			FETCH emp_crsr INTO v_emp_rec;
            INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
            ( VHM_LEAVE_REQ_NO,
              VHM_APP_EMP_CODE,
              VHM_APP_HIERARCHY_CODE,
              VHM_APP_BRANCH_CODE,
              VHM_APP_DEPT_CODE,
              VHM_APP_SECTION_CODE,
              VHM_APP_DESG_CODE,
              VHM_APP_RECEIVED_DATE,
              VHM_APP_REMARKS,
              VHM_APP_SEQ_NO,
              VHM_APP_CRE_USER_INIT,
              VHM_APP_CRE_DATE ,
              VHM_LEAVE_REQ_RTN_IND)
	      VALUES
		   ( :NEW.VHM_LEAVE_REQ_NO,
              v_emp_rec.VHM_APP_EMP_CODE,
              v_emp_rec.VHM_APP_HIERARCHY_CODE,
             v_emp_rec.VHM_APP_BRANCH_CODE,
             v_emp_rec. VHM_APP_DEPT_CODE,
              v_emp_rec.VHM_APP_SECTION_CODE,
             v_emp_rec.VHM_APP_DESG_CODE,
		     SYSDATE,
              '* This Approver has been Auto Selected by the Leave Process using: ' || v_hint ,
              1,
              'WEB',
              SYSDATE,
			  'L' 		 
	        );
		CLOSE  emp_crsr ;
	 ELSE  /*3*/
	       NULL; ---- in this case, the data are not ready to tell the direct MGR. Personel Affairs should fill the data.
	  END IF; /*3*/
	  
  ELSE  /*2*/
			OPEN emp_crsr (:NEW.VHM_SUGGESTED_APP_EMP_CODE);
			FETCH emp_crsr INTO v_emp_rec;
           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                          ( VHM_LEAVE_REQ_NO  ,
                             VHM_APP_EMP_CODE,
                             VHM_APP_HIERARCHY_CODE,
                             VHM_APP_BRANCH_CODE,
                             VHM_APP_DEPT_CODE,
                             VHM_APP_SECTION_CODE,
                             VHM_APP_DESG_CODE,
                             VHM_APP_RECEIVED_DATE,
                             VHM_APP_REMARKS,
                             VHM_APP_SEQ_NO,
                             VHM_APP_CRE_USER_INIT,
                             VHM_APP_CRE_DATE,
			                 VHM_LEAVE_REQ_RTN_IND )
	                VALUES
		                  ( :NEW.VHM_LEAVE_REQ_NO ,
                             v_emp_rec.VHM_APP_EMP_CODE,
                             v_emp_rec.VHM_APP_HIERARCHY_CODE,
                             v_emp_rec.VHM_APP_BRANCH_CODE,
                             v_emp_rec. VHM_APP_DEPT_CODE,
                             v_emp_rec.VHM_APP_SECTION_CODE,
                             v_emp_rec.VHM_APP_DESG_CODE,
                		     SYSDATE,
                            '* This Approver has been manually selected by the Leave Requester *',
                             1,
                             'WEB',
                             SYSDATE,
			                  'L' 		 
	                         );
		     CLOSE  emp_crsr ;
   END IF; /*2*/
 
 ---- the following part is added for sabatical leave. 3 other approvers will be added.
 
    IF :NEW.VHM_LEAVE_TYPE_FLAG  = 'B' THEN --- sabatical leave only
                -- 1:  get the the dean or the assitance of the college

			OPEN emp_crsr (:NEW.VHM_EMP_CODE);
			FETCH emp_crsr INTO v_emp_rec;
	  
 			     v_app_emp_id := NULL; -- look for the dean
			     OPEN  emp_dean_crsr (  v_emp_rec.VHM_APP_BRANCH_CODE) ;
				 FETCH  emp_dean_crsr  INTO v_app_emp_id;
				 CLOSE  emp_dean_crsr ;
				 
				 IF v_app_emp_id IS NULL THEN -- look for assitant dean
 			          OPEN  emp_astnt_dean_crsr (  v_emp_rec.VHM_APP_BRANCH_CODE ) ;
	 			      FETCH  emp_astnt_dean_crsr INTO v_app_emp_id;
				      CLOSE  emp_astnt_dean_crsr ;		 
				 END IF;
		 
     		CLOSE  emp_crsr ;
			 
			 ----------------------------------------------------------------------  part 010101
			 IF v_app_emp_id IS NOT NULL THEN
			 		OPEN emp_crsr (v_app_emp_id);
			        FETCH emp_crsr INTO v_emp_rec;
			
        	            INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
            	           ( VHM_LEAVE_REQ_NO  ,
                 	        VHM_APP_EMP_CODE,
                 	        VHM_APP_HIERARCHY_CODE,
               	          VHM_APP_BRANCH_CODE,
                 	        VHM_APP_DEPT_CODE,
               	          VHM_APP_SECTION_CODE,
                 	        VHM_APP_DESG_CODE,
                	         VHM_APP_RECEIVED_DATE,
               	          VHM_APP_REMARKS,
               	          VHM_APP_SEQ_NO,
                	         VHM_APP_CRE_USER_INIT,
                	         VHM_APP_CRE_DATE,
							 VHM_LEAVE_REQ_RTN_IND)
	        	         VALUES
		    	          ( :NEW.VHM_LEAVE_REQ_NO,
               	          v_emp_rec.VHM_APP_EMP_CODE,
            	             v_emp_rec.VHM_APP_HIERARCHY_CODE,
           	              v_emp_rec.VHM_APP_BRANCH_CODE,
             	            v_emp_rec. VHM_APP_DEPT_CODE,
              	          v_emp_rec.VHM_APP_SECTION_CODE,
             	           v_emp_rec.VHM_APP_DESG_CODE,
		       	         SYSDATE,
               	          '* This the second approval for Sabatical Leave '  ,
                	         2,
                  	       'WEB',
                 	        SYSDATE,
							'L'
                  	        );			  
       		       CLOSE  emp_crsr ;
			 END IF;  			 
			 ----------------------------------------------------------------------  part 010101
			 
			 
			 				
                -- 2:  get the the dupty VC of SQU	
				 v_app_emp_id := NULL;			
 			     OPEN emp_dupty_VC_crsr ;
				 FETCH  emp_dupty_VC_crsr INTO v_app_emp_id;
				 CLOSE   emp_dupty_VC_crsr  ;
 
 		 ----------------------------------------------------------------------  part 020202
			 IF v_app_emp_id IS NOT NULL THEN

 		 		   OPEN emp_crsr (v_app_emp_id);
			        FETCH emp_crsr INTO v_emp_rec;		
						  
        	            INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
            	           ( VHM_LEAVE_REQ_NO  ,
                 	        VHM_APP_EMP_CODE,
                 	        VHM_APP_HIERARCHY_CODE,
               	          VHM_APP_BRANCH_CODE,
                 	        VHM_APP_DEPT_CODE,
               	          VHM_APP_SECTION_CODE,
                 	        VHM_APP_DESG_CODE,
                	         VHM_APP_RECEIVED_DATE,
               	          VHM_APP_REMARKS,
               	          VHM_APP_SEQ_NO,
                	         VHM_APP_CRE_USER_INIT,
                	         VHM_APP_CRE_DATE,
							 VHM_LEAVE_REQ_RTN_IND )
	        	         VALUES
		    	          ( :NEW.VHM_LEAVE_REQ_NO,
               	          v_emp_rec.VHM_APP_EMP_CODE,
            	             v_emp_rec.VHM_APP_HIERARCHY_CODE,
           	              v_emp_rec.VHM_APP_BRANCH_CODE,
             	            v_emp_rec. VHM_APP_DEPT_CODE,
              	          v_emp_rec.VHM_APP_SECTION_CODE,
             	            v_emp_rec.VHM_APP_DESG_CODE,
		       	         SYSDATE,
               	          '* This the third approval for Sabatical Leave - Dupty VC'  ,
                	         3,
                  	       'WEB',
                 	        SYSDATE,
							'L'
                  	        );			  
      		       CLOSE  emp_crsr ;
			 END IF;  			 
			 ----------------------------------------------------------------------  part 020202
                 -- 3:  get the the VC of SQU

				 v_app_emp_id := NULL;			
 			     OPEN emp_VC_crsr ;
				 FETCH  emp_VC_crsr INTO v_app_emp_id;
				 CLOSE   emp_VC_crsr  ;
 
 		 ----------------------------------------------------------------------  part 030303
			 IF v_app_emp_id IS NOT NULL THEN
 		 		   OPEN emp_crsr (v_app_emp_id);
			        FETCH emp_crsr INTO v_emp_rec;		
								  
        	            INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
            	           ( VHM_LEAVE_REQ_NO  ,
                 	        VHM_APP_EMP_CODE,
                 	        VHM_APP_HIERARCHY_CODE,
               	          VHM_APP_BRANCH_CODE,
                 	        VHM_APP_DEPT_CODE,
               	          VHM_APP_SECTION_CODE,
                 	        VHM_APP_DESG_CODE,
                	         VHM_APP_RECEIVED_DATE,
               	          VHM_APP_REMARKS,
               	          VHM_APP_SEQ_NO,
                	         VHM_APP_CRE_USER_INIT,
                	         VHM_APP_CRE_DATE,
							 VHM_LEAVE_REQ_RTN_IND )
	        	         VALUES
		    	          ( :NEW.VHM_LEAVE_REQ_NO,
               	          v_emp_rec.VHM_APP_EMP_CODE,
            	             v_emp_rec.VHM_APP_HIERARCHY_CODE,
           	              v_emp_rec.VHM_APP_BRANCH_CODE,
             	            v_emp_rec. VHM_APP_DEPT_CODE,
              	          v_emp_rec.VHM_APP_SECTION_CODE,
             	           v_emp_rec.VHM_APP_DESG_CODE,
		       	         SYSDATE,
               	          '* This the third approval for Sabatical Leave - SQU  VC'  ,
                	         4,
                  	       'WEB',
                 	        SYSDATE,
							'L'
                  	        );			  
      		       CLOSE  emp_crsr ;
			 END IF;  			 
			 ----------------------------------------------------------------------  part 030303
    END IF;
 
ELSIF UPDATING THEN   /*1*/

  IF :NEW.VHM_RETURN_DATE IS NOT NULL OR :OLD.VHM_RETURN_DATE IS NOT NULL THEN /*5*/--&&&&&
        IF :NEW.VHM_STATUS_CODE = '0000000001'   THEN   /*6*/
                 IF :OLD.VHM_STATUS_CODE  IN ('0000000004')  THEN  /*7*/ 
                              IF :NEW.VHM_RETURN_APP_EMP_CODE  <> :OLD.VHM_RETURN_APP_EMP_CODE THEN
                        	              OPEN emp_crsr (:NEW.VHM_RETURN_APP_EMP_CODE);
                			               FETCH emp_crsr INTO v_emp_rec;
                                           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                                          ( VHM_LEAVE_REQ_NO  ,
                                                            VHM_APP_EMP_CODE,
                                                            VHM_APP_HIERARCHY_CODE,
                                                            VHM_APP_BRANCH_CODE,
                                                            VHM_APP_DEPT_CODE,
                                                            VHM_APP_SECTION_CODE,
                                                            VHM_APP_DESG_CODE,
                                                            VHM_APP_RECEIVED_DATE,
                                                            VHM_APP_REMARKS,
                                                            VHM_APP_SEQ_NO,
                                                            VHM_APP_CRE_USER_INIT,
                                                            VHM_APP_CRE_DATE ,
                                                            VHM_LEAVE_REQ_RTN_IND 			  )
                	                       VALUES
                		                                  ( :OLD.VHM_LEAVE_REQ_NO,
                                                             v_emp_rec.VHM_APP_EMP_CODE,
                                                             v_emp_rec.VHM_APP_HIERARCHY_CODE,
                                                             v_emp_rec.VHM_APP_BRANCH_CODE,
                                                             v_emp_rec. VHM_APP_DEPT_CODE,
                                                             v_emp_rec.VHM_APP_SECTION_CODE,
                                                             v_emp_rec.VHM_APP_DESG_CODE,
                		                                      SYSDATE,
                                                           '* This Approver has been Auto Selected by the Leave Process using: ' || v_hint ,
                                                               1,
                                                             'WEB',
                                                              SYSDATE,
                			                                  'R' 		 
                	                                          );
                		                     CLOSE  emp_crsr ;
							 ELSE
							       OPEN emp_crsr (:OLD.VHM_RETURN_APP_EMP_CODE);
                			               FETCH emp_crsr INTO v_emp_rec;
                                           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                                          ( VHM_LEAVE_REQ_NO  ,
                                                            VHM_APP_EMP_CODE,
                                                            VHM_APP_HIERARCHY_CODE,
                                                            VHM_APP_BRANCH_CODE,
                                                            VHM_APP_DEPT_CODE,
                                                            VHM_APP_SECTION_CODE,
                                                            VHM_APP_DESG_CODE,
                                                            VHM_APP_RECEIVED_DATE,
                                                            VHM_APP_REMARKS,
                                                            VHM_APP_SEQ_NO,
                                                            VHM_APP_CRE_USER_INIT,
                                                            VHM_APP_CRE_DATE ,
                                                            VHM_LEAVE_REQ_RTN_IND 			  )
                	                       VALUES
                		                                  ( :OLD.VHM_LEAVE_REQ_NO,
                                                             v_emp_rec.VHM_APP_EMP_CODE,
                                                             v_emp_rec.VHM_APP_HIERARCHY_CODE,
                                                             v_emp_rec.VHM_APP_BRANCH_CODE,
                                                             v_emp_rec. VHM_APP_DEPT_CODE,
                                                             v_emp_rec.VHM_APP_SECTION_CODE,
                                                             v_emp_rec.VHM_APP_DESG_CODE,
                		                                      SYSDATE,
                                                           '* This Approver has been Auto Selected by the Leave Process using: ' || v_hint ,
                                                               1,
                                                             'WEB',
                                                              SYSDATE,
                			                                  'R' 		 
                	                                          );
                		                     CLOSE  emp_crsr ;
                        	 END IF;    
        		 ELSE /*7*/
		           IF :NEW.VHM_RETURN_APP_EMP_CODE  IS NULL THEN  /*8*/
                                     get_higher_mgr_process ( :OLD.VHM_EMP_CODE,  v_mgr_emp , v_hint  ) ;
                	                 IF v_mgr_emp IS NOT NULL THEN  /*9*/
                			               OPEN emp_crsr (v_mgr_emp);
                			               FETCH emp_crsr INTO v_emp_rec;
                                           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                                                          ( VHM_LEAVE_REQ_NO  ,
                                                            VHM_APP_EMP_CODE,
                                                            VHM_APP_HIERARCHY_CODE,
                                                            VHM_APP_BRANCH_CODE,
                                                            VHM_APP_DEPT_CODE,
                                                            VHM_APP_SECTION_CODE,
                                                            VHM_APP_DESG_CODE,
                                                            VHM_APP_RECEIVED_DATE,
                                                            VHM_APP_REMARKS,
                                                            VHM_APP_SEQ_NO,
                                                            VHM_APP_CRE_USER_INIT,
                                                            VHM_APP_CRE_DATE ,
                                                            VHM_LEAVE_REQ_RTN_IND 			  )
                	                       VALUES
                		                                  ( :OLD.VHM_LEAVE_REQ_NO,
                                                             v_emp_rec.VHM_APP_EMP_CODE,
                                                             v_emp_rec.VHM_APP_HIERARCHY_CODE,
                                                             v_emp_rec.VHM_APP_BRANCH_CODE,
                                                             v_emp_rec. VHM_APP_DEPT_CODE,
                                                             v_emp_rec.VHM_APP_SECTION_CODE,
                                                             v_emp_rec.VHM_APP_DESG_CODE,
                		                                      SYSDATE,
                                                           '* This Approver has been Auto Selected by the Leave Process using: ' || v_hint ,
                                                               1,
                                                             'WEB',
                                                              SYSDATE,
                			                                  'R' 		 
                	                                          );
                		                     CLOSE  emp_crsr ;
                	                ELSE  /*9*/
                	                    NULL; ---- in this case, the data are not ready to tell the direct MGR. Personel Affairs should fill the data.
                	                END IF; /*9*/
            ELSE  /*8*/
                  			OPEN emp_crsr (:NEW.VHM_RETURN_APP_EMP_CODE );
                  			FETCH emp_crsr INTO v_emp_rec;
                  	  
                           INSERT INTO VHM_EMP_LEAVE_REQUEST_APPROVAL
                              ( VHM_LEAVE_REQ_NO  ,
                                VHM_APP_EMP_CODE,
                                VHM_APP_HIERARCHY_CODE,
                                VHM_APP_BRANCH_CODE,
                                VHM_APP_DEPT_CODE,
                                VHM_APP_SECTION_CODE,
                                VHM_APP_DESG_CODE,
                                VHM_APP_RECEIVED_DATE,
                                VHM_APP_REMARKS,
                                VHM_APP_SEQ_NO,
                                VHM_APP_CRE_USER_INIT,
                                VHM_APP_CRE_DATE,
                  			  VHM_LEAVE_REQ_RTN_IND )
                  	      VALUES
                  		   ( :NEW.VHM_LEAVE_REQ_NO ,
                                v_emp_rec.VHM_APP_EMP_CODE,
                                v_emp_rec.VHM_APP_HIERARCHY_CODE,
                                v_emp_rec.VHM_APP_BRANCH_CODE,
                                v_emp_rec. VHM_APP_DEPT_CODE,
                                v_emp_rec.VHM_APP_SECTION_CODE,
                                v_emp_rec.VHM_APP_DESG_CODE,
                  		     SYSDATE,
                                '* This Approver has been manually selected by the Leave Requester *',
                                1,
                                'WEB',
                                SYSDATE,
                  			  'R' 		 
                  	        );
                  
                  		CLOSE  emp_crsr ;
                   END IF;  /*8*/
		 END IF; /*7*/
		 
	ELSIF :NEW.VHM_STATUS_CODE = '0000000002'   THEN   /*6*/
		   UPDATE EMP_LEAVE_AVAIL
 			SET ELA_RETURN_DATE = :OLD.VHM_RETURN_DATE,
                    ELA_RETURN_POSTED_YN 	='Y',
                    ELA_RETURN_POSTED_USER 	='WEB',
                    ELA_RETURN_POSTED_DATE 	=TRUNC(SYSDATE)
           WHERE PORTAL_VHM_LEAVE_REQ_NO = :OLD.VHM_LEAVE_REQ_NO;
	END IF; /*6*/
		
  ELSE  /*5*/
     IF :NEW.VHM_STATUS_CODE = '0000000001'   THEN   /*9*/ -----@@@@@@@@@@@@@
	     rmk := rmk||'***1***'||' - ';
         IF :OLD.VHM_STATUS_CODE IN ('0000000001' ,'0000000004') THEN /*10*/
		 rmk := rmk||'***2***'||' - ';
		     IF :NEW.VHM_SUGGESTED_APP_EMP_CODE <> :OLD.VHM_SUGGESTED_APP_EMP_CODE THEN
			      rmk := rmk||'***3***'||' - ';
			       UPDATE VHM_EMP_LEAVE_REQUEST_APPROVAL 
				   SET VHM_APP_EMP_CODE = :NEW.VHM_SUGGESTED_APP_EMP_CODE
				   WHERE VHM_LEAVE_REQ_NO = NVL( :OLD.VHM_LEAVE_REQ_NO,'XxX')
				   AND NVL(VHM_LEAVE_REQ_RTN_IND,'L') = 'L';
			ELSE
     			rmk := rmk||'***4***'||' - ';
			 END IF; 
			
		 ELSE /*10*/
		      rmk := rmk||'***5***'||' - ';
              OPEN  renew_approval_crsr ;
              FETCH  renew_approval_crsr  INTO  nw_aprvl_rec;
               INSERT INTO 	VHM_EMP_LEAVE_REQUEST_APPROVAL (   VHM_LEAVE_REQ_NO  ,   VHM_APP_EMP_CODE ,   VHM_APP_INTERNET_ID ,   VHM_APP_HIERARCHY_CODE  ,
                                                                  VHM_APP_BRANCH_CODE   ,   VHM_APP_DEPT_CODE   ,   VHM_APP_SECTION_CODE   ,   VHM_APP_DESG_CODE   ,
                                                                        VHM_APP_SEQ_NO ,  VHM_APP_CRE_USER_INIT  ,   VHM_APP_CRE_DATE  ,  VHM_APP_RECEIVED_DATE, VHM_LEAVE_REQ_RTN_IND )
												VALUES (  nw_aprvl_rec.VHM_LEAVE_REQ_NO  ,   nw_aprvl_rec.VHM_APP_EMP_CODE ,   nw_aprvl_rec.VHM_APP_INTERNET_ID ,   nw_aprvl_rec.VHM_APP_HIERARCHY_CODE  ,
                                                                  nw_aprvl_rec.VHM_APP_BRANCH_CODE   ,   nw_aprvl_rec.VHM_APP_DEPT_CODE   ,   nw_aprvl_rec.VHM_APP_SECTION_CODE   ,  nw_aprvl_rec.VHM_APP_DESG_CODE   
                                                                    ,   nw_aprvl_rec.VHM_APP_SEQ_NO,    nw_aprvl_rec.VHM_APP_CRE_USER_INIT  ,    SYSDATE , SYSDATE,'L');
	         CLOSE  renew_approval_crsr ;
		END IF; /*10*/
  ELSIF   :NEW.VHM_STATUS_CODE = '0000000002'   THEN  /*9*/-----@@@@@@@@@@@@@
         rmk := rmk||'***6***'||' - ';
       UPDATE  VHM_EMP_LEAVE_REQ_DELEGATION
	        SET VHM_DELEGATED_STATUS = 'A' --- A: Approved
		    WHERE VHM_LEAVE_REQ_NO = NVL(:NEW.VHM_LEAVE_REQ_NO,'XxX') ;
			
			-- the follwoing code is to insert the leave in the live table 
			---^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
			IF (  ( :NEW.VHM_LEAVE_END_DATE -   :NEW.VHM_LEAVE_START_DATE) + 1  ) <14 THEN
			     v_PROCESS_SAL_YN := 'N' ;
			ELSIF :NEW.VHM_LEAVE_TYPE NOT IN ('0010', '0040' , '0050' , '0060', '0080') THEN
			     v_PROCESS_SAL_YN := 'N' ;			
		    ELSE
			     v_PROCESS_SAL_YN := :NEW.VHM_PROCESS_SAL_YN  ;			
			END IF;
			
			OPEN chk_leave_crsr;
			FETCH chk_leave_crsr INTO leave_exists_yn;
			IF chk_leave_crsr%NOTFOUND THEN
			    INSERT INTO EMP_LEAVE_AVAIL
                   ( 
				        ELA_EMP_CODE 
                      , ELA_LEAVE_TYPE  
                      , ELA_REQ_DATE 
                      , ELA_START_DATE   
                      , ELA_END_DATE  
                      , ELA_NO_OF_DAYS   
                      , ELA_CRE_USER_INIT  
                      , ELA_CRE_DT   
                      , ELA_USER_APP_YN 
                      , ELA_USER_APP_USER  
                      , ELA_USER_APP_DATE 
                      , ELA_PROCESS_SAL_YN 
                      , ELA_PER_APP_YN  
                      , ELA_LEAVE_SAL_PAID_YN  
                      , ELA_RETURN_POSTED_YN  
                      , ELA_PER_RETURN_POST_YN  
                      , ELA_PAY_RETURN_APP_YN   
                      , ELA_LEAVE_IND    
                      , ELA_DOC_NO
					  , PORTAL_VHM_LEAVE_REQ_NO
                  )
				  
			VALUES
			   (
			   			:NEW.VHM_EMP_CODE 
                      , :NEW.VHM_LEAVE_TYPE  
                      , :NEW.VHM_LEAVE_REQ_DATE 
                      , :NEW.VHM_LEAVE_START_DATE   
                      , :NEW.VHM_LEAVE_END_DATE  
                      , ( :NEW.VHM_LEAVE_END_DATE -   :NEW.VHM_LEAVE_START_DATE) + 1  
                      , 'WEB' 
                      , SYSDATE  
                      , 'Y'
                      , 'WEB'  
                      ,  SYSDATE   
                      , v_PROCESS_SAL_YN 
                      , 'N'  
                      , 'N'   
                      , 'N'  
                      , 'N'  
                      , 'N'     
                      , 'L'    
                      , LPAD(EMP_LEAVE_DOC.NEXTVAL,9,'0')
                      , :NEW.VHM_LEAVE_REQ_NO 
					  ) ;			
			END IF;
			CLOSE chk_leave_crsr;
			
			
			 
   		   ---^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  ELSIF   :NEW.VHM_STATUS_CODE = '0000000005'   THEN  /*9*/-----@@@@@@@@@@@@@
       rmk := rmk||'***7***'||' - ';
           -- if the requester cancel his/her leave the approver will be filled with the proper acction
           UPDATE  VHM_EMP_LEAVE_REQUEST_APPROVAL
		   SET VHM_ACTION_CODE = '0000000007'
          WHERE VHM_LEAVE_REQ_NO = NVL( :OLD.VHM_LEAVE_REQ_NO,'XxX')
         AND VHM_ACTION_CODE IS NULL 
 		 AND NVL(VHM_LEAVE_REQ_RTN_IND,'L')='L'; 
   
  END IF;  /*9*/ -----@@@@@@@@@@@@@

  END IF;  /*5*/ --&&&&&
  
   --- the following codes is to log the status changes
  IF  :NEW.VHM_STATUS_CODE <>  :OLD.VHM_STATUS_CODE THEN  /*11*/---------#######################
       IF :OLD.vhm_return_date IS NOT NULL OR :NEW.vhm_return_date IS NOT NULL THEN /*12*/
          INSERT INTO VHM_WORKFLOW_STATUS_LOG
                  (  VHM_STATUS_CODE,   VHM_STATUS_DATE,   VHM_SERVICE_TYPE ,
                      VHM_LEAVE_REQ_NO   ,   VHM_STATUS_REMARKS  ,
                       VHM_CRE_USER_INIT   ,   VHM_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
		  VALUES (  :NEW.VHM_STATUS_CODE , SYSDATE, 'LEAVE' ,
		                 :NEW.VHM_LEAVE_REQ_NO,:NEW.VHM_LEAVE_REQUEST_REMARKS   ,   -- instead of "Auto Inserted by Trigger"
						 'WEB', SYSDATE,'R'
     		  );
	 ELSE /*12*/
	       INSERT INTO VHM_WORKFLOW_STATUS_LOG
                  (  VHM_STATUS_CODE,   VHM_STATUS_DATE,   VHM_SERVICE_TYPE ,
                      VHM_LEAVE_REQ_NO   ,   VHM_STATUS_REMARKS  ,
                       VHM_CRE_USER_INIT   ,   VHM_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
		  VALUES (  :NEW.VHM_STATUS_CODE , SYSDATE, 'LEAVE' ,
		                 :NEW.VHM_LEAVE_REQ_NO,:NEW.VHM_LEAVE_REQUEST_REMARKS   ,   -- instead of "Auto Inserted by Trigger"
						 'WEB', SYSDATE,'L'
     		  );
	 END IF; /*12*/
	 
  /*   IF :old.vhm_return_date is not null then
          insert into VHM_WORKFLOW_STATUS_LOG
                  (  VHM_STATUS_CODE,   VHM_STATUS_DATE,   VHM_SERVICE_TYPE ,
                      VHM_LEAVE_REQ_NO   ,   VHM_STATUS_REMARKS  ,
                       VHM_CRE_USER_INIT   ,   VHM_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
		  values (  :OLD.VHM_STATUS_CODE , :OLD.VHM_RETURN_STATUS_DT, 'LEAVE' ,
		                 :OLD.VHM_LEAVE_REQ_NO,:OLD.VHM_LEAVE_REQUEST_REMARKS   ,   -- instead of "Auto Inserted by Trigger"
						 'WEB', sysdate,'R'
     		  );
	 ELSE
	       insert into VHM_WORKFLOW_STATUS_LOG
                  (  VHM_STATUS_CODE,   VHM_STATUS_DATE,   VHM_SERVICE_TYPE ,
                      VHM_LEAVE_REQ_NO   ,   VHM_STATUS_REMARKS  ,
                       VHM_CRE_USER_INIT   ,   VHM_CRE_DATE, VHM_LEAVE_REQ_RTN_IND )
		  values (  :OLD.VHM_STATUS_CODE ,  :OLD.VHM_LEAVE_STATUS_DT, 'LEAVE' ,
		                 :OLD.VHM_LEAVE_REQ_NO,:OLD.VHM_LEAVE_REQUEST_REMARKS   ,   -- instead of "Auto Inserted by Trigger"
						 'WEB', sysdate,'L'
     		  );
	 END IF;*/
	 
  END IF;  /*11*/---------#######################
INSERT INTO TMP_LEAVE_REQUEST_TRG(REQUEST_NO ,DATE_ENTERED ,OLD_STATUS ,
NEW_STATUS , OLD_APP_EMP ,NEW_APP_EMP ,OLD_RETURN_DT ,NEW_RETURN_DT, remarks )
VALUES(:OLD.VHM_LEAVE_REQ_NO, SYSDATE, :OLD.VHM_STATUS_CODE,
:NEW.VHM_STATUS_CODE, :OLD.VHM_SUGGESTED_APP_EMP_CODE, :NEW.VHM_SUGGESTED_APP_EMP_CODE,
:OLD.VHM_RETURN_DATE, :NEW.VHM_RETURN_DATE,rmk );
   
END IF;  /*1*/
END;
