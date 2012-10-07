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
 * File Name			:	LeaveType.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Aug 25, 2012  12:16:37 PM
 * Date of modification :	
 * 
 * Summary				:	Leave Type
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
public class LeaveType
{
	private String 	typeNo;
	private	String	typeDesc;
	/**
	 * Getter Method	: getTypeNo
	 * @return the typeNo
	 * 
	 * Date				: Aug 25, 2012
	 */
	public String getTypeNo()
	{
		return this.typeNo;
	}
	/**
	 * Setter method : setTypeNo
	 * @param typeNo the typeNo to set
	 * 
	 * Date          : Aug 25, 2012 12:18:11 PM
	 */
	public void setTypeNo(String typeNo)
	{
		this.typeNo = typeNo;
	}
	/**
	 * Getter Method	: getTypeDesc
	 * @return the typeDesc
	 * 
	 * Date				: Aug 25, 2012
	 */
	public String getTypeDesc()
	{
		return this.typeDesc;
	}
	/**
	 * Setter method : setTypeDesc
	 * @param typeDesc the typeDesc to set
	 * 
	 * Date          : Aug 25, 2012 12:18:11 PM
	 */
	public void setTypeDesc(String typeDesc)
	{
		this.typeDesc = typeDesc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LeaveType ["
				+ (this.typeNo != null ? "typeNo=" + this.typeNo + ", " : "")
				+ (this.typeDesc != null ? "typeDesc=" + this.typeDesc : "")
				+ "]";
	}
	
	
	
}
