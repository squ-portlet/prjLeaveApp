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
 * File Name			:	Department.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 15, 2012  10:41:52 AM
 * Date of modification :	
 * 
 * Summary				:	
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
public class Department implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String 	deptCode;
	private	String	deptDesc;
	/**
	 * Getter Method	: getDeptCode
	 * @return the deptCode
	 * 
	 * Date				: Sep 15, 2012
	 */
	public String getDeptCode()
	{
		return this.deptCode;
	}
	/**
	 * Setter method : setDeptCode
	 * @param deptCode the deptCode to set
	 * 
	 * Date          : Sep 15, 2012 10:46:34 AM
	 */
	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}
	/**
	 * Getter Method	: getDeptDesc
	 * @return the deptDesc
	 * 
	 * Date				: Sep 15, 2012
	 */
	public String getDeptDesc()
	{
		return this.deptDesc;
	}
	/**
	 * Setter method : setDeptDesc
	 * @param deptDesc the deptDesc to set
	 * 
	 * Date          : Sep 15, 2012 10:46:34 AM
	 */
	public void setDeptDesc(String deptDesc)
	{
		this.deptDesc = deptDesc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Department ["
				+ (this.deptCode != null ? "deptCode=" + this.deptCode + ", "
						: "")
				+ (this.deptDesc != null ? "deptDesc=" + this.deptDesc : "")
				+ "]";
	}
	
	
}
