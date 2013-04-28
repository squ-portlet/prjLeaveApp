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
 * File Name			:	Section.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Nov 25, 2012  2:21:06 PM
 * Date of modification :	
 * 
 * Summary				:	Section of Departments
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
public class Section implements Serializable
{
	private	String	sectionCode;
	private	String	sectionDesc;
	/**
	 * Getter Method	: getSectionCode
	 * @return the sectionCode
	 * 
	 * Date				: Nov 25, 2012
	 */
	public String getSectionCode()
	{
		return this.sectionCode;
	}
	/**
	 * Setter method : setSectionCode
	 * @param sectionCode the sectionCode to set
	 * 
	 * Date          : Nov 25, 2012 2:22:04 PM
	 */
	public void setSectionCode(String sectionCode)
	{
		this.sectionCode = sectionCode;
	}
	/**
	 * Getter Method	: getSectionDesc
	 * @return the sectionDesc
	 * 
	 * Date				: Nov 25, 2012
	 */
	public String getSectionDesc()
	{
		return this.sectionDesc;
	}
	/**
	 * Setter method : setSectionDesc
	 * @param sectionDesc the sectionDesc to set
	 * 
	 * Date          : Nov 25, 2012 2:22:04 PM
	 */
	public void setSectionDesc(String sectionDesc)
	{
		this.sectionDesc = sectionDesc;
	}
	
	
}
