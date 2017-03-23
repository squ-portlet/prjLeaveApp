create or replace function calc_actual_leave_bal_func(ecode varchar2, sdate date)  return number is
/*
This function calculates the leave balance
*/

   LEAVE_DAYS      NUMBER(9,2);
   EFFECTIVE_DT    DATE;
   NEW_GRADE       VARCHAR2(10);
   GRADE_CODE      VARCHAR2(10);
   L_DAYS                  NUMBER(9);
   L_LEAVE_DAYS    NUMBER(9,2);
   START_DATE        DATE;
   END_DATE        DATE;
   L_CNTLEAVE      NUMBER(9,2);
   L_ACCURED       NUMBER(9,2);

   CSTART_DATE     DATE;
   CEND_DATE       DATE;
   CLEAVE_DAYS     NUMBER(2);
 
   L_AVAIL NUMBER(7,2);

   AVAIL    Number(5) := 0;
   DEDUCTED Number(5) := 0;
   ADDED    Number(5) := 0;
   ENCASH   Number(5) := 0;
   ACCURED  Number(5) := 0;

   OUTSTANDING  Number(5) := 0;
   vlb                       Number(5) := 0;
    
   CURSOR emp_leave_crsr IS
  SELECT ELA_LEAVE_IND, ELA_NO_OF_DAYS
  FROM   EMP_LEAVE_AVAIL
  WHERE  ELA_EMP_CODE = ECODE
  AND    ELA_LEAVE_TYPE = '0010'
  AND nvl(ela_include_alb_calc,'Y') = 'Y';


  CURSOR cnt_days_crsr IS
  SELECT SUM(ELA_COUNT_DAYS)
  FROM   EMP_LEAVE_AVAIL
  WHERE  ELA_EMP_CODE = ECODE
  AND    ELA_START_DATE <= trunc(SDATE)
  AND nvl(ela_include_alb_calc,'Y') = 'Y';

 
   
   CURSOR emp_crsr IS
     SELECT VHM_EMP_JOIN_DATE,VHM_EMP_GRADE_CODE
       FROM VHM_EMPLOYEE
      WHERE VHM_EMP_CODE=ECODE;

   CURSOR promotion_crsr(JDATE DATE) IS
     SELECT VHEP_PROMOTION_DT,VHEP_GRADE_CODE
     FROM   VHM_EMP_PROMOTION
     WHERE  VHEP_EMP_CODE = ECODE
       AND  VHEP_EFFECTIVE_DT > JDATE
     ORDER BY VHEP_EFFECTIVE_DT;

   CURSOR annual_leave_crsr(GCODE CHAR) IS
     SELECT VHM_GRADE_ANNUAL_LEAVE_DAYS
     FROM   VHM_GRADE
     WHERE VHM_GRADE_CODE = GCODE;

   CURSOR CCHECK IS
     SELECT VLA_CHANGE_DT,
            VLA_GRADE_CODE ,
            NVL(VLA_END_DT,TRUNC(SDATE)) VLA_END_DT ,
            (NVL(VLA_NO_OF_NEW_DAYS,0) - NVL(VLA_NO_OF_DAYS,0)) VLA_NO_OF_DAYS
      FROM  VHM_LEAVE_ADJUST
      WHERE VLA_EMP_CODE = ECODE
	 AND nvl(vla_include_alb_calc,'Y') = 'Y'
     ORDER BY VLA_CHANGE_DT;

	 cursor acad_leave_adj_crsr is
	      SELECT VLA_CHANGE_DT,
            VLA_GRADE_CODE ,
            NVL(VLA_END_DT,TRUNC(SDATE)) VLA_END_DT ,
            (NVL(VLA_NO_OF_NEW_DAYS,0) - NVL(VLA_NO_OF_DAYS,0)) VLA_NO_OF_DAYS
      FROM  VHM_LEAVE_ADJUST
      WHERE VLA_EMP_CODE = ECODE
	 AND nvl(vla_include_alb_calc,'Y') = 'N'
     ORDER BY VLA_CHANGE_DT;
	 
BEGIN
      OPEN emp_crsr;
        FETCH emp_crsr INTO START_DATE,GRADE_CODE;
      CLOSE emp_crsr;

        FOR I IN promotion_crsr(START_DATE)
        LOOP
          NEW_GRADE    := I.VHEP_GRADE_CODE;
          END_DATE     := I.VHEP_PROMOTION_DT;

          OPEN annual_leave_crsr(NEW_GRADE);
             FETCH annual_leave_crsr INTO L_LEAVE_DAYS;
          CLOSE annual_leave_crsr;

		  L_DAYS                  := END_DATE - START_DATE;
          START_DATE       := END_DATE;
          END_DATE            := NULL;
          L_LEAVE_DAYS   := NVL(L_LEAVE_DAYS,0)/12;
          L_CNTLEAVE        := ROUND((L_DAYS * L_LEAVE_DAYS)/(365/12),0);
          L_ACCURED          := NVL(L_ACCURED,0) + NVL(L_CNTLEAVE,0);
        END LOOP;

          OPEN annual_leave_crsr(GRADE_CODE);
             FETCH annual_leave_crsr INTO L_LEAVE_DAYS;
          CLOSE annual_leave_crsr;

          IF END_DATE IS NULL THEN
             END_DATE := trunc(SDATE);
          END IF;

           L_DAYS       := END_DATE - START_DATE;
           L_LEAVE_DAYS := NVL(L_LEAVE_DAYS,0)/12;
           L_CNTLEAVE   := ROUND((L_DAYS * L_LEAVE_DAYS)/(365/12),0);
           L_ACCURED    := NVL(L_ACCURED,0) + NVL(L_CNTLEAVE,0);


        FOR J IN CCHECK LOOP
              CSTART_DATE     := J.VLA_CHANGE_DT;
              CEND_DATE       := J.VLA_END_DT;
			  if cend_date > trunc(sdate) then
			        cend_date :=  trunc(sdate);
			  end if;
              CLEAVE_DAYS     := J.VLA_NO_OF_DAYS;
           L_DAYS       := CEND_DATE - CSTART_DATE;
           L_LEAVE_DAYS := NVL(CLEAVE_DAYS,0)/12;
           L_CNTLEAVE   := ROUND((L_DAYS * L_LEAVE_DAYS)/(365/12),0);
           L_ACCURED    := NVL(L_ACCURED,0) + NVL(L_CNTLEAVE,0);
        END LOOP;
        
		
   
        FOR I IN emp_leave_crsr
  LOOP
    IF (I.ELA_LEAVE_IND = 'L' OR I.ELA_LEAVE_IND = 'T' /* 'T' is newly addedd */) THEN
      AVAIL  := NVL(AVAIL,0) + I.ELA_NO_OF_DAYS;
    ELSIF I.ELA_LEAVE_IND = 'A' THEN
      ADDED := NVL(ADDED,0) + I.ELA_NO_OF_DAYS;
    ELSIF I.ELA_LEAVE_IND = 'D' THEN
      DEDUCTED := NVL(DEDUCTED,0) + I.ELA_NO_OF_DAYS;
    ELSIF I.ELA_LEAVE_IND = 'E' THEN
      ENCASH := NVL(ENCASH,0) + I.ELA_NO_OF_DAYS;
    END IF;
  END LOOP;

   OPEN cnt_days_crsr;
    FETCH cnt_days_crsr INTO L_AVAIL;
   CLOSE cnt_days_crsr;


   OUTSTANDING := (NVL(L_ACCURED,0)+ NVL(ADDED,0)) - (NVL(ENCASH,0)
                    + NVL(AVAIL,0)+ NVL(DEDUCTED,0)) - NVL(L_AVAIL,0);
	
   select leave_avail(ecode , TRUNC(SDATE) )  INTO vlb from dual;
   	
   OUTSTANDING := 	OUTSTANDING - vlb;	
   
   RETURN(OUTSTANDING);

end;
