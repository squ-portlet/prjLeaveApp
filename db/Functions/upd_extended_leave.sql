create or replace FUNCTION        user_college(ecode varchar2) RETURN varchar2 IS
x varchar2(40);
begin
  begin
     select vhm_branch_name into x from vhm_branch,vhm_employee
     where  vhm_emp_code =ecode
       and  vhm_emp_bran_code = vhm_branch_code; 
     if x is null then
           x := 'N/A';
     end if;
  exception
           when no_data_found then   x := 'N/A';
   end;
return (x);
end;