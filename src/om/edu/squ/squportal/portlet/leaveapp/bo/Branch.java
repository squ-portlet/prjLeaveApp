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
 * File Name			:	Branch.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Nov 24, 2012  10:00:42 AM
 * Date of modification :	
 * 
 * Summary				:	Branch of SQU
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

/**
 * @author Bhabesh
 *
 */
public class Branch
{
	private String 	branchCode;
	private	String	branchDesc;
	/**
	 * Getter Method	: getBranchCode
	 * @return the branchCode
	 * 
	 * Date				: Nov 24, 2012
	 */
	public String getBranchCode()
	{
		return this.branchCode;
	}
	/**
	 * Setter method : setBranchCode
	 * @param branchCode the branchCode to set
	 * 
	 * Date          : Nov 24, 2012 10:02:23 AM
	 */
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	/**
	 * Getter Method	: getBranchDesc
	 * @return the branchDesc
	 * 
	 * Date				: Nov 24, 2012
	 */
	public String getBranchDesc()
	{
		return this.branchDesc;
	}
	/**
	 * Setter method : setBranchDesc
	 * @param branchDesc the branchDesc to set
	 * 
	 * Date          : Nov 24, 2012 10:02:23 AM
	 */
	public void setBranchDesc(String branchDesc)
	{
		this.branchDesc = branchDesc;
	}
	
	
}
