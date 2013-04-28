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
 * File Name			:	CheckLeaveResearch.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Mar 19, 2013  11:43:09 AM
 * Date of modification :	
 * 
 * Summary				:	business object for stored procedure check_eleave_Research
 *
 *
 * Copyright 2013 the original author or authors and Organization.
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
public class CheckLeaveResearch
{
	private	String	researchId;
	private	String	empCode;
	private	boolean	accept;
	private String	message;
	/**
	 * Getter Method	: getResearchId
	 * @return the researchId
	 * 
	 * Date				: Mar 19, 2013
	 */
	public String getResearchId()
	{
		return this.researchId;
	}
	/**
	 * Setter method : setResearchId
	 * @param researchId the researchId to set
	 * 
	 * Date          : Mar 19, 2013 11:47:07 AM
	 */
	public void setResearchId(String researchId)
	{
		this.researchId = researchId;
	}
	/**
	 * Getter Method	: getEmpCode
	 * @return the empCode
	 * 
	 * Date				: Mar 19, 2013
	 */
	public String getEmpCode()
	{
		return this.empCode;
	}
	/**
	 * Setter method : setEmpCode
	 * @param empCode the empCode to set
	 * 
	 * Date          : Mar 19, 2013 11:47:07 AM
	 */
	public void setEmpCode(String empCode)
	{
		this.empCode = empCode;
	}
	/**
	 * Getter Method	: isAccept
	 * @return the accept
	 * 
	 * Date				: Mar 19, 2013
	 */
	public boolean isAccept()
	{
		return this.accept;
	}
	/**
	 * Setter method : setAccept
	 * @param accept the accept to set
	 * 
	 * Date          : Mar 19, 2013 11:47:07 AM
	 */
	public void setAccept(boolean accept)
	{
		this.accept = accept;
	}
	/**
	 * Getter Method	: getMessage
	 * @return the message
	 * 
	 * Date				: Mar 19, 2013
	 */
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * Setter method : setMessage
	 * @param message the message to set
	 * 
	 * Date          : Mar 19, 2013 11:47:07 AM
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
	
}

