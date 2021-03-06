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
 * File Name			:	Employee.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Aug 26, 2012  1:21:59 PM
 * Date of modification :	
 * 
 * Summary				:	Employee object
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
package om.edu.squ.squportal.portlet.leaveapp.bo;

import java.io.Serializable;

/**
 * @author Bhabesh
 *
 */
public class Employee implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String	empNumber;
	private	String	empInternetId;
	private	String	empSquEmail;
	private	String	empName;
	private	String	empNameEn;														//	Employee Name in English
	private	String	empNameAr;														//	Employee Name in Arabic
	private	String	designationCode;
	private	String	designation;
	private	String	designationAddlCode;											// Additional designation ; if any
	private	String	designationAddl;
	private	String	gradeCode;
	private	String	grade;
	private	String	gradeShort;
	private	String	branchCode;
	private	String	branch;
	private	String	branchAddlCode;
	private	String	branch2Code;
	private	String	branch2;
	private	String	departmentCode;
	private	String	department;
	private	String	departmentAddlCode;
	private	String	department2code;												//	Department changed by employee
	private	String	department2;
	private	String	departmentShort;
	private	String	sectionCode;
	private	String	section;
	private	String	joinDt;
	private	String	jobTypeCode;
	private	String	hierarchyCode;
	private	String	hierarchyLevelCode;
	private	String	hierarchyAddlCode;
	private	String	hierarchyAddlLevelCode;
	private	boolean	admin;
	private	boolean	admin2;															//	Admin status changed by employee 
	private	String	religionCode;
	private	boolean	omani;
	private	String	gender;
	private	boolean	senior;															// employee or senior
	private	String	myHodId;														// Hod Id of the concerned employee
	
	public Employee(){}
	
	public Employee(String empNumber, String empName)
	{
		this.empNumber	=	empNumber;
		this.empName	=	empName;
	}
	
	/**
	 * Getter Method	: getEmpNumber
	 * @return the empNumber
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getEmpNumber()
	{
		return this.empNumber;
	}
	/**
	 * Setter method : setEmpNumber
	 * @param empNumber the empNumber to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setEmpNumber(String empNumber)
	{
		this.empNumber = empNumber;
	}
	/**
	 * Getter Method	: getEmpInternetId
	 * @return the empInternetId
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getEmpInternetId()
	{
		return this.empInternetId;
	}
	/**
	 * Setter method : setEmpInternetId
	 * @param empInternetId the empInternetId to set
	 * 
	 * Date          : Sep 2, 2012 1:10:18 PM
	 */
	public void setEmpInternetId(String empInternetId)
	{
		this.empInternetId = empInternetId;
	}
	/**
	 * Getter Method	: getEmpSquEmail
	 * @return the empSquEmail
	 * 
	 * Date				: Feb 17, 2013
	 */
	public String getEmpSquEmail()
	{
		return this.empSquEmail;
	}

	/**
	 * Setter method : setEmpSquEmail
	 * @param empSquEmail the empSquEmail to set
	 * 
	 * Date          : Feb 17, 2013 3:10:49 PM
	 */
	public void setEmpSquEmail(String empSquEmail)
	{
		this.empSquEmail = empSquEmail;
	}

	/**
	 * Getter Method	: getEmpName
	 * @return the empName
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getEmpName()
	{
		return this.empName;
	}
	/**
	 * Setter method : setEmpName
	 * @param empName the empName to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	/**
	 * Getter Method	: getEmpNameEn
	 * @return the empNameEn
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getEmpNameEn()
	{
		return this.empNameEn;
	}

	/**
	 * Setter method : setEmpNameEn
	 * @param empNameEn the empNameEn to set
	 * 
	 * Date          : Feb 27, 2013 3:38:23 PM
	 */
	public void setEmpNameEn(String empNameEn)
	{
		this.empNameEn = empNameEn;
	}

	/**
	 * Getter Method	: getEmpNameAr
	 * @return the empNameAr
	 * 
	 * Date				: Feb 27, 2013
	 */
	public String getEmpNameAr()
	{
		return this.empNameAr;
	}

	/**
	 * Setter method : setEmpNameAr
	 * @param empNameAr the empNameAr to set
	 * 
	 * Date          : Feb 27, 2013 3:38:23 PM
	 */
	public void setEmpNameAr(String empNameAr)
	{
		this.empNameAr = empNameAr;
	}

	/**
	 * Getter Method	: getDesignationCode
	 * @return the designationCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getDesignationCode()
	{
		return this.designationCode;
	}
	/**
	 * Setter method : setDesignationCode
	 * @param designationCode the designationCode to set
	 * 
	 * Date          : Sep 2, 2012 1:21:52 PM
	 */
	public void setDesignationCode(String designationCode)
	{
		this.designationCode = designationCode;
	}
	/**
	 * Getter Method	: getDesignation
	 * @return the designation
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getDesignation()
	{
		return this.designation;
	}
	/**
	 * Setter method : setDesignation
	 * @param designation the designation to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setDesignation(String designation)
	{
		this.designation = designation;
	}
	/**
	 * Getter Method	: getDesignationAddlCode
	 * @return the designationAddlCode
	 * 
	 * Date				: Sep 9, 2012
	 */
	public String getDesignationAddlCode()
	{
		return this.designationAddlCode;
	}
	/**
	 * Setter method : setDesignationAddlCode
	 * @param designationAddlCode the designationAddlCode to set
	 * 
	 * Date          : Sep 9, 2012 2:30:04 PM
	 */
	public void setDesignationAddlCode(String designationAddlCode)
	{
		this.designationAddlCode = designationAddlCode;
	}
	/**
	 * Getter Method	: getDesignationAddl
	 * @return the designationAddl
	 * 
	 * Date				: Sep 17, 2012
	 */
	public String getDesignationAddl()
	{
		return this.designationAddl;
	}

	/**
	 * Setter method : setDesignationAddl
	 * @param designationAddl the designationAddl to set
	 * 
	 * Date          : Sep 17, 2012 2:30:27 PM
	 */
	public void setDesignationAddl(String designationAddl)
	{
		this.designationAddl = designationAddl;
	}

	/**
	 * Getter Method	: getGradeCode
	 * @return the gradeCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getGradeCode()
	{
		return this.gradeCode;
	}
	/**
	 * Setter method : setGradeCode
	 * @param gradeCode the gradeCode to set
	 * 
	 * Date          : Sep 2, 2012 1:20:18 PM
	 */
	public void setGradeCode(String gradeCode)
	{
		this.gradeCode = gradeCode;
	}
	/**
	 * Getter Method	: getGrade
	 * @return the grade
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getGrade()
	{
		return this.grade;
	}
	/**
	 * Setter method : setGrade
	 * @param grade the grade to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	/**
	 * Getter Method	: getGradeShort
	 * @return the gradeShort
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getGradeShort()
	{
		return this.gradeShort;
	}
	/**
	 * Setter method : setGradeShort
	 * @param gradeShort the gradeShort to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setGradeShort(String gradeShort)
	{
		this.gradeShort = gradeShort;
	}
	/**
	 * Getter Method	: getBranchCode
	 * @return the branchCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getBranchCode()
	{
		return this.branchCode;
	}
	/**
	 * Setter method : setBranchCode
	 * @param branchCode the branchCode to set
	 * 
	 * Date          : Sep 2, 2012 1:15:29 PM
	 */
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	/**
	 * Getter Method	: getBranch
	 * @return the branch
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getBranch()
	{
		return this.branch;
	}
	/**
	 * Setter method : setBranch
	 * @param branch the branch to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	/**
	 * Getter Method	: getBranchAddlCode
	 * @return the branchAddlCode
	 * 
	 * Date				: Dec 17, 2012
	 */
	public String getBranchAddlCode()
	{
		return this.branchAddlCode;
	}

	/**
	 * Setter method : setBranchAddlCode
	 * @param branchAddlCode the branchAddlCode to set
	 * 
	 * Date          : Dec 17, 2012 2:18:20 PM
	 */
	public void setBranchAddlCode(String branchAddlCode)
	{
		this.branchAddlCode = branchAddlCode;
	}

	/**
	 * Getter Method	: getBranch2Code
	 * @return the branch2Code
	 * 
	 * Date				: Nov 25, 2012
	 */
	public String getBranch2Code()
	{
		return this.branch2Code;
	}

	/**
	 * Setter method : setBranch2Code
	 * @param branch2Code the branch2Code to set
	 * 
	 * Date          : Nov 25, 2012 1:28:49 PM
	 */
	public void setBranch2Code(String branch2Code)
	{
		this.branch2Code = branch2Code;
	}

	/**
	 * Getter Method	: getBranch2
	 * @return the branch2
	 * 
	 * Date				: Nov 25, 2012
	 */
	public String getBranch2()
	{
		return this.branch2;
	}

	/**
	 * Setter method : setBranch2
	 * @param branch2 the branch2 to set
	 * 
	 * Date          : Nov 25, 2012 1:28:49 PM
	 */
	public void setBranch2(String branch2)
	{
		this.branch2 = branch2;
	}

	/**
	 * Getter Method	: getDepartmentCode
	 * @return the departmentCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getDepartmentCode()
	{
		return this.departmentCode;
	}
	/**
	 * Setter method : setDepartmentCode
	 * @param departmentCode the departmentCode to set
	 * 
	 * Date          : Sep 2, 2012 1:17:40 PM
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	/**
	 * Getter Method	: getDepartment
	 * @return the department
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getDepartment()
	{
		return this.department;
	}
	/**
	 * Setter method : setDepartment
	 * @param department the department to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}
	/**
	 * Getter Method	: getDepartmentAddlCode
	 * @return the departmentAddlCode
	 * 
	 * Date				: Dec 17, 2012
	 */
	public String getDepartmentAddlCode()
	{
		return this.departmentAddlCode;
	}

	/**
	 * Setter method : setDepartmentAddlCode
	 * @param departmentAddlCode the departmentAddlCode to set
	 * 
	 * Date          : Dec 17, 2012 2:19:29 PM
	 */
	public void setDepartmentAddlCode(String departmentAddlCode)
	{
		this.departmentAddlCode = departmentAddlCode;
	}

	/**
	 * Getter Method	: getDepartment2code
	 * @return the department2code
	 * 
	 * Date				: Sep 18, 2012
	 */
	public String getDepartment2code()
	{
		return this.department2code;
	}

	/**
	 * Setter method : setDepartment2code
	 * @param department2code the department2code to set
	 * 
	 * Date          : Sep 18, 2012 12:51:43 PM
	 */
	public void setDepartment2code(String department2code)
	{
		this.department2code = department2code;
	}

	/**
	 * Getter Method	: getDepartment2
	 * @return the department2
	 * 
	 * Date				: Sep 18, 2012
	 */
	public String getDepartment2()
	{
		return this.department2;
	}

	/**
	 * Setter method : setDepartment2
	 * @param department2 the department2 to set
	 * 
	 * Date          : Sep 18, 2012 12:51:43 PM
	 */
	public void setDepartment2(String department2)
	{
		this.department2 = department2;
	}

	/**
	 * Getter Method	: getDepartmentShort
	 * @return the departmentShort
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getDepartmentShort()
	{
		return this.departmentShort;
	}
	/**
	 * Setter method : setDepartmentShort
	 * @param departmentShort the departmentShort to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setDepartmentShort(String departmentShort)
	{
		this.departmentShort = departmentShort;
	}
	/**
	 * Getter Method	: getSectionCode
	 * @return the sectionCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getSectionCode()
	{
		return this.sectionCode;
	}
	/**
	 * Setter method : setSectionCode
	 * @param sectionCode the sectionCode to set
	 * 
	 * Date          : Sep 2, 2012 1:18:57 PM
	 */
	public void setSectionCode(String sectionCode)
	{
		this.sectionCode = sectionCode;
	}
	/**
	 * Getter Method	: getSection
	 * @return the section
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getSection()
	{
		return this.section;
	}
	/**
	 * Setter method : setSection
	 * @param section the section to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setSection(String section)
	{
		this.section = section;
	}
	/**
	 * Getter Method	: getJoinDt
	 * @return the joinDt
	 * 
	 * Date				: Aug 26, 2012
	 */
	public String getJoinDt()
	{
		return this.joinDt;
	}
	/**
	 * Setter method : setJoinDt
	 * @param joinDt the joinDt to set
	 * 
	 * Date          : Aug 26, 2012 1:27:30 PM
	 */
	public void setJoinDt(String joinDt)
	{
		this.joinDt = joinDt;
	}
	/**
	 * Getter Method	: getJobTypeCode
	 * @return the jobTypeCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getJobTypeCode()
	{
		return this.jobTypeCode;
	}
	/**
	 * Setter method : setJobTypeCode
	 * @param jobTypeCode the jobTypeCode to set
	 * 
	 * Date          : Sep 2, 2012 1:34:18 PM
	 */
	public void setJobTypeCode(String jobTypeCode)
	{
		this.jobTypeCode = jobTypeCode;
	}
	/**
	 * Getter Method	: getHierarchyCode
	 * @return the hierarchyCode
	 * 
	 * Date				: Sep 2, 2012
	 */
	public String getHierarchyCode()
	{
		return this.hierarchyCode;
	}
	/**
	 * Setter method : setHierarchyCode
	 * @param hierarchyCode the hierarchyCode to set
	 * 
	 * Date          : Sep 2, 2012 1:42:40 PM
	 */
	public void setHierarchyCode(String hierarchyCode)
	{
		this.hierarchyCode = hierarchyCode;
	}
	/**
	 * Getter Method	: getHierarchyLevelCode
	 * @return the hierarchyLevelCode
	 * 
	 * Date				: Dec 9, 2012
	 */
	public String getHierarchyLevelCode()
	{
		return this.hierarchyLevelCode;
	}
	/**
	 * Setter method : setHierarchyLevelCode
	 * @param hierarchyLevelCode the hierarchyLevelCode to set
	 * 
	 * Date          : Dec 9, 2012 1:06:58 PM
	 */
	public void setHierarchyLevelCode(String hierarchyLevelCode)
	{
		this.hierarchyLevelCode = hierarchyLevelCode;
	}
	/**
	 * Getter Method	: getHierarchyAddlCode
	 * @return the hierarchyAddlCode
	 * 
	 * Date				: Dec 11, 2012
	 */
	public String getHierarchyAddlCode()
	{
		return this.hierarchyAddlCode;
	}
	/**
	 * Setter method : setHierarchyAddlCode
	 * @param hierarchyAddlCode the hierarchyAddlCode to set
	 * 
	 * Date          : Dec 11, 2012 1:48:16 PM
	 */
	public void setHierarchyAddlCode(String hierarchyAddlCode)
	{
		this.hierarchyAddlCode = hierarchyAddlCode;
	}

	/**
	 * Getter Method	: getHierarchyAddlLevelCode
	 * @return the hierarchyAddlLevelCode
	 * 
	 * Date				: Dec 11, 2012
	 */
	public String getHierarchyAddlLevelCode()
	{
		return this.hierarchyAddlLevelCode;
	}

	/**
	 * Setter method : setHierarchyAddlLevelCode
	 * @param hierarchyAddlLevelCode the hierarchyAddlLevelCode to set
	 * 
	 * Date          : Dec 11, 2012 1:48:16 PM
	 */
	public void setHierarchyAddlLevelCode(String hierarchyAddlLevelCode)
	{
		this.hierarchyAddlLevelCode = hierarchyAddlLevelCode;
	}

	/**
	 * Getter Method	: isAdmin
	 * @return the admin
	 * 
	 * Date				: Aug 29, 2012
	 */
	public boolean isAdmin()
	{
		return this.admin;
	}
	/**
	 * Setter method : setAdmin
	 * @param admin the admin to set
	 * 
	 * Date          : Aug 29, 2012 2:25:00 PM
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	/**
	 * Getter Method	: isAdmin2
	 * @return the admin2
	 * 
	 * Date				: Sep 18, 2012
	 */
	public boolean isAdmin2()
	{
		return this.admin2;
	}

	/**
	 * Setter method : setAdmin2
	 * @param admin2 the admin2 to set
	 * 
	 * Date          : Sep 18, 2012 12:59:06 PM
	 */
	public void setAdmin2(boolean admin2)
	{
		this.admin2 = admin2;
	}
	/**
	 * Getter Method	: getReligion
	 * @return the religionCode
	 * 
	 * Date				: Sep 25, 2012
	 */
	public String getReligionCode()
	{
		return this.religionCode;
	}

	/**
	 * Setter method : setReligion
	 * @param religionCode the religionCode to set
	 * 
	 * Date          : Sep 25, 2012 10:33:37 AM
	 */
	public void setReligionCode(String religionCode)
	{
		this.religionCode = religionCode;
	}
	/**
	 * Getter Method	: isOmani
	 * @return the omani
	 * 
	 * Date				: Sep 25, 2012
	 */
	public boolean isOmani()
	{
		return this.omani;
	}

	/**
	 * Setter method : setOmani
	 * @param omani the omani to set
	 * 
	 * Date          : Sep 25, 2012 12:52:37 PM
	 */
	public void setOmani(boolean omani)
	{
		this.omani = omani;
	}

	/**
	 * Getter Method	: getGender
	 * @return the gender
	 * 
	 * Date				: Sep 25, 2012
	 */
	public String getGender()
	{
		return this.gender;
	}

	/**
	 * Setter method : setGender
	 * @param gender the gender to set
	 * 
	 * Date          : Sep 25, 2012 12:52:37 PM
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	/**
	 * Getter Method	: isSenior
	 * @return the senior
	 * 
	 * Date				: Oct 7, 2012
	 */
	public boolean isSenior()
	{
		return this.senior;
	}

	/**
	 * Setter method : setSenior
	 * @param senior the senior to set
	 * 
	 * Date          : Oct 7, 2012 5:29:00 PM
	 */
	public void setSenior(boolean senior)
	{
		this.senior = senior;
	}
	/**
	 * Getter Method	: getMyHodId
	 * @return the myHodId
	 * 
	 * Date				: Dec 8, 2012
	 */
	public String getMyHodId()
	{
		return this.myHodId;
	}

	/**
	 * Setter method : setMyHodId
	 * @param myHodId the myHodId to set
	 * 
	 * Date          : Dec 8, 2012 8:53:02 AM
	 */
	public void setMyHodId(String myHodId)
	{
		this.myHodId = myHodId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Employee ["
				+ (this.empNumber != null ? "empNumber=" + this.empNumber
						+ ", " : "")
				+ (this.empInternetId != null ? "empInternetId="
						+ this.empInternetId + ", " : "")
				+ (this.empName != null ? "empName=" + this.empName + ", " : "")
				+ (this.designationCode != null ? "designationCode="
						+ this.designationCode + ", " : "")
				+ (this.designation != null ? "designation=" + this.designation
						+ ", " : "")
				+ (this.designationAddlCode != null ? "designationAddlCode="
						+ this.designationAddlCode + ", " : "")
				+ (this.designationAddl != null ? "designationAddl="
						+ this.designationAddl + ", " : "")
				+ (this.gradeCode != null ? "gradeCode=" + this.gradeCode
						+ ", " : "")
				+ (this.grade != null ? "grade=" + this.grade + ", " : "")
				+ (this.gradeShort != null ? "gradeShort=" + this.gradeShort
						+ ", " : "")
				+ (this.branchCode != null ? "branchCode=" + this.branchCode
						+ ", " : "")
				+ (this.branch != null ? "branch=" + this.branch + ", " : "")
				+ (this.branch2Code != null ? "branch2Code=" + this.branch2Code
						+ ", " : "")
				+ (this.branch2 != null ? "branch2=" + this.branch2 + ", " : "")
				+ (this.departmentCode != null ? "departmentCode="
						+ this.departmentCode + ", " : "")
				+ (this.department != null ? "department=" + this.department
						+ ", " : "")
				+ (this.department2code != null ? "department2code="
						+ this.department2code + ", " : "")
				+ (this.department2 != null ? "department2=" + this.department2
						+ ", " : "")
				+ (this.departmentShort != null ? "departmentShort="
						+ this.departmentShort + ", " : "")
				+ (this.sectionCode != null ? "sectionCode=" + this.sectionCode
						+ ", " : "")
				+ (this.section != null ? "section=" + this.section + ", " : "")
				+ (this.joinDt != null ? "joinDt=" + this.joinDt + ", " : "")
				+ (this.jobTypeCode != null ? "jobTypeCode=" + this.jobTypeCode
						+ ", " : "")
				+ (this.hierarchyCode != null ? "hierarchyCode="
						+ this.hierarchyCode + ", " : "")
				+ (this.hierarchyLevelCode != null ? "hierarchyLevelCode="
						+ this.hierarchyLevelCode + ", " : "")
				+ "admin="
				+ this.admin
				+ ", admin2="
				+ this.admin2
				+ ", "
				+ (this.religionCode != null ? "religionCode="
						+ this.religionCode + ", " : "") + "omani="
				+ this.omani + ", "
				+ (this.gender != null ? "gender=" + this.gender + ", " : "")
				+ "senior=" + this.senior + ", "
				+ (this.myHodId != null ? "myHodId=" + this.myHodId : "") + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		 return empNumber.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee emp = (Employee) obj;
        return this.empNumber.equals(emp.empNumber);
    }
		
	
	
	
	
}
