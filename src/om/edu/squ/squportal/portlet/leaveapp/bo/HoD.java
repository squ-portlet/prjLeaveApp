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
 * File Name			:	HoD.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Dec 2, 2012  8:59:19 AM
 * Date of modification :	
 * 
 * Summary				:	Head of the department object
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
public class HoD
{
	private	String	hodId;
	private	String	hodName;
	private	boolean	delegated;
	
	public  HoD(){}
	public  HoD(String hodId, String hodName)
	{
		this.hodId	 =	hodId;
		this.hodName =  hodName; 
	}
	
	/**
	 * Getter Method	: getHodId
	 * @return the hodId
	 * 
	 * Date				: Dec 2, 2012
	 */
	public String getHodId()
	{
		return this.hodId;
	}
	/**
	 * Setter method : setHodId
	 * @param hodId the hodId to set
	 * 
	 * Date          : Dec 2, 2012 9:00:04 AM
	 */
	public void setHodId(String hodId)
	{
		this.hodId = hodId;
	}
	/**
	 * Getter Method	: getHodName
	 * @return the hodName
	 * 
	 * Date				: Dec 2, 2012
	 */
	public String getHodName()
	{
		return this.hodName;
	}
	/**
	 * Setter method : setHodName
	 * @param hodName the hodName to set
	 * 
	 * Date          : Dec 2, 2012 9:00:04 AM
	 */
	public void setHodName(String hodName)
	{
		this.hodName = hodName;
	}
	/**
	 * Getter Method	: isDelegated
	 * @return the delegated
	 * 
	 * Date				: Jul 6, 2017
	 */
	public boolean isDelegated() {
		return this.delegated;
	}
	/**
	 * Setter method : setDelegated
	 * @param delegated the delegated to set
	 * 
	 * Date          : Jul 6, 2017 1:35:07 PM
	 */
	public void setDelegated(boolean delegated) {
		this.delegated = delegated;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HoD [hodId=" + this.hodId + ", hodName=" + this.hodName
				+ ", delegated=" + this.delegated + "]";
	}
	
	
}
