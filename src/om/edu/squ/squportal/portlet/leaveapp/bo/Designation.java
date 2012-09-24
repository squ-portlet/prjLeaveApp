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
 * File Name			:	Designation.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Sep 9, 2012  12:57:41 PM
 * Date of modification :	
 * 
 * Summary				:	Designation
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
public class Designation
{
	private	String	desigCode;
	private	String	desigDescription;
	/**
	 * Getter Method	: getDesigCode
	 * @return the desigCode
	 * 
	 * Date				: Sep 9, 2012
	 */
	public String getDesigCode()
	{
		return this.desigCode;
	}
	/**
	 * Setter method : setDesigCode
	 * @param desigCode the desigCode to set
	 * 
	 * Date          : Sep 9, 2012 12:58:56 PM
	 */
	public void setDesigCode(String desigCode)
	{
		this.desigCode = desigCode;
	}
	/**
	 * Getter Method	: getDesigDescription
	 * @return the desigDescription
	 * 
	 * Date				: Sep 9, 2012
	 */
	public String getDesigDescription()
	{
		return this.desigDescription;
	}
	/**
	 * Setter method : setDesigDescription
	 * @param desigDescription the desigDescription to set
	 * 
	 * Date          : Sep 9, 2012 12:58:56 PM
	 */
	public void setDesigDescription(String desigDescription)
	{
		this.desigDescription = desigDescription;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Designation ["
				+ (this.desigCode != null ? "desigCode=" + this.desigCode
						+ ", " : "")
				+ (this.desigDescription != null ? "desigDescription="
						+ this.desigDescription : "") + "]";
	}
	
	
	
	
}
