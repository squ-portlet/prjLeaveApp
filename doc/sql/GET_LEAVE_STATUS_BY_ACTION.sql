create or replace
FUNCTION get_leave_status_by_action( param_action_code VARCHAR2, param_lang  varchar2 default 'en' )  RETURN VARCHAR2
AS
    p_status_desc VARCHAR2(50);
     BEGIN
             BEGIN
             
             select distinct DECODE (param_lang, 'en',vws.VHM_STATUS_DESC,
                                    'ar',vws.VHM_STATUS_DESC_ARABIC )
             into    p_status_desc
             from    vhm_workflow_status vws, 
                     leave_status_action  lsa
             where  vws.VHM_STATUS_CODE= lsa.lsa_status_code
               and  (lsa_action_code= param_action_code 
               or (param_action_code is null and lsa_action_code is null));
             
              EXCEPTION
 WHEN NO_DATA_FOUND  THEN
  p_status_desc :=null;
              END;
              RETURN(p_status_desc );
    END;