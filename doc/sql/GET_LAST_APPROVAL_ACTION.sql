create or replace
FUNCTION get_last_approval_action( param_emp_code VARCHAR2, param_req_no   varchar2)  RETURN VARCHAR2
AS
    p_action_code VARCHAR2(50);
     BEGIN
             BEGIN
             
             select VHM_ACTION_CODE 
             into    p_action_code
             from   VHM_EMP_LEAVE_REQUEST_APPROVAL 
             where  VHM_LEAVE_REQ_NO=param_req_no
                and vhm_app_emp_code=param_emp_code
                and vhm_app_cre_date =( select max(vhm_app_cre_date) from VHM_EMP_LEAVE_REQUEST_APPROVAL  where VHM_LEAVE_REQ_NO=param_req_no
                                           and vhm_app_emp_code=param_emp_code) ;

        
              EXCEPTION
 WHEN NO_DATA_FOUND  THEN
  p_action_code :=null;
              END;
              RETURN(p_action_code );
    END;