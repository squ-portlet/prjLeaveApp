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
 * File Name			:	CheckLeaveDelegation.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Mar 12, 2013  9:11:10 AM
 * Date of modification :	
 * 
 * Summary				:	Check e-leave delegation data object
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
public class CheckLeaveDelegation
{
	private	String	empCodeDlg;
	private	String	empCodeLeave;
	private	boolean	acceptDelegation;
	private	String	message;
	/**
	 * Getter Method	: getEmpCodeDlg
	 * @return the empCodeDlg
	 * 
	 * Date				: Mar 12, 2013
	 */
	public String getEmpCodeDlg()
	{
		return this.empCodeDlg;
	}
	/**
	 * Setter method : setEmpCodeDlg
	 * @param empCodeDlg the empCodeDlg to set
	 * 
	 * Date          : Mar 12, 2013 9:16:44 AM
	 */
	public void setEmpCodeDlg(String empCodeDlg)
	{
		this.empCodeDlg = empCodeDlg;
	}
	/**
	 * Getter Method	: getEmpCodeLeave
	 * @return the empCodeLeave
	 * 
	 * Date				: Mar 12, 2013
	 */
	public String getEmpCodeLeave()
	{
		return this.empCodeLeave;
	}
	/**
	 * Setter method : setEmpCodeLeave
	 * @param empCodeLeave the empCodeLeave to set
	 * 
	 * Date          : Mar 12, 2013 9:16:44 AM
	 */
	public void setEmpCodeLeave(String empCodeLeave)
	{
		this.empCodeLeave = empCodeLeave;
	}
	/**
	 * Getter Method	: isAcceptDelegation
	 * @return the acceptDelegation
	 * 
	 * Date				: Mar 12, 2013
	 */
	public boolean isAcceptDelegation()
	{
		return this.acceptDelegation;
	}
	/**
	 * Setter method : setAcceptDelegation
	 * @param acceptDelegation the acceptDelegation to set
	 * 
	 * Date          : Mar 12, 2013 9:16:44 AM
	 */
	public void setAcceptDelegation(boolean acceptDelegation)
	{
		this.acceptDelegation = acceptDelegation;
	}
	/**
	 * Getter Method	: getMessage
	 * @return the message
	 * 
	 * Date				: Mar 12, 2013
	 */
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * Setter method : setMessage
	 * @param message the message to set
	 * 
	 * Date          : Mar 12, 2013 9:16:44 AM
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CheckLeaveDelegation ["
				+ (this.empCodeDlg != null ? "empCodeDlg=" + this.empCodeDlg
						+ ", " : "")
				+ (this.empCodeLeave != null ? "empCodeLeave="
						+ this.empCodeLeave + ", " : "") + "acceptDelegation="
				+ this.acceptDelegation + ", "
				+ (this.message != null ? "message=" + this.message : "") + "]";
	}
	
	
}
