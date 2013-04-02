create or replace
FUNCTION IS_VALID_LEAVE_APP_APPROVER (param_sequence VARCHAR2, param_req_num VARCHAR2) RETURN VARCHAR2 AS 
p_count_check_lower_seq NUMBER;
p_status_sabbatical VARCHAR2(1);
p_status_temp_lower_seq VARCHAR2(1);
p_status_temp_self VARCHAR2(1);
BEGIN
  BEGIN
  /*Check for existence of record (it also applies for first record*/
    SELECT COUNT(APP.VHM_LEAVE_REQ_NO)
    into p_count_check_lower_seq
    FROM   VHM_EMP_LEAVE_REQUEST_APPROVAL APP
     WHERE APP.VHM_LEAVE_REQ_NO = param_req_num
    AND APP.VHM_APP_SEQ_NO = (param_sequence - 1);

 /* For get the status for self*/
           SELECT DECODE ( 
                    NVL(APP4.VHM_ACTION_CODE ,'Y'),
                    'Y','Y','N'
                    ) AS SAB_ACTION_LOWER
                    into p_status_temp_self
                    FROM  VHM_EMP_LEAVE_REQUEST_APPROVAL APP4
                    WHERE APP4.VHM_LEAVE_REQ_NO = param_req_num
                    AND APP4.VHM_APP_SEQ_NO = param_sequence
                   AND  APP4.VHM_APP_CRE_DATE = 
                   (SELECT MAX(APP5.VHM_APP_CRE_DATE)
                   FROM VHM_EMP_LEAVE_REQUEST_APPROVAL APP5
                   WHERE APP5.VHM_LEAVE_REQ_NO = param_req_num
                   AND APP5.VHM_APP_SEQ_NO = param_sequence);
 
/* For get the status for just one sequence lower*/
          SELECT DECODE ( 
                    NVL(APP2.VHM_ACTION_CODE ,'N'),
                    'N','N',
                    '0000000001','Y','N')  AS SAB_ACTION_LOWER
          into p_status_temp_lower_seq
          FROM  VHM_EMP_LEAVE_REQUEST_APPROVAL APP2
          WHERE APP2.VHM_LEAVE_REQ_NO = param_req_num
          AND APP2.VHM_APP_SEQ_NO = param_sequence - 1
          AND APP2.VHM_APP_CRE_DATE = 
              (
               SELECT MAX(APP3.VHM_APP_CRE_DATE)
               FROM VHM_EMP_LEAVE_REQUEST_APPROVAL APP3
               WHERE APP3.VHM_LEAVE_REQ_NO = param_req_num
               AND APP3.VHM_APP_SEQ_NO = param_sequence - 1
               );
       
        IF(p_count_check_lower_seq <> 0) THEN
            IF ((p_status_temp_lower_seq = 'Y') AND (p_status_temp_self = 'Y'))
            THEN
              p_status_sabbatical := 'Y';
            ELSE
              p_status_sabbatical := 'N';
            END IF;
        ELSE
            IF (p_status_temp_self = 'Y')
            THEN
              p_status_sabbatical := 'Y';
            ELSE
              p_status_sabbatical := 'N';
            END IF;
        END IF;
 EXCEPTION
 WHEN NO_DATA_FOUND  THEN
  p_status_sabbatical := 'X';
  IF(p_count_check_lower_seq = 0) THEN
            IF (p_status_temp_self = 'Y')
            THEN
              p_status_sabbatical := 'Y';
            ELSE
              p_status_sabbatical := 'N';
            END IF;
  END IF;
  END;
  RETURN p_status_sabbatical;
END IS_VALID_LEAVE_APP_APPROVER;