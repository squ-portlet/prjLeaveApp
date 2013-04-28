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
 * File Name			:	Sabbatical.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Mar 25, 2013  11:48:42 AM
 * Date of modification :	
 * 
 * Summary				:	Sabbatical object
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

import java.io.Serializable;

/**
 * @author Bhabesh
 *
 */
public class Sabbatical implements Serializable
{
	private	String	requestNo;
	private	String	approverAction;
	private	int		sequence;
	private	boolean	lowerApproverAction;
	/**
	 * Getter Method	: getRequestNo
	 * @return the requestNo
	 * 
	 * Date				: Mar 25, 2013
	 */
	public String getRequestNo()
	{
		return this.requestNo;
	}
	/**
	 * Setter method : setRequestNo
	 * @param requestNo the requestNo to set
	 * 
	 * Date          : Mar 25, 2013 11:53:05 AM
	 */
	public void setRequestNo(String requestNo)
	{
		this.requestNo = requestNo;
	}
	/**
	 * Getter Method	: getApproverAction
	 * @return the approverAction
	 * 
	 * Date				: Mar 25, 2013
	 */
	public String getApproverAction()
	{
		return this.approverAction;
	}
	/**
	 * Setter method : setApproverAction
	 * @param approverAction the approverAction to set
	 * 
	 * Date          : Mar 25, 2013 11:53:05 AM
	 */
	public void setApproverAction(String approverAction)
	{
		this.approverAction = approverAction;
	}
	/**
	 * Getter Method	: getSequence
	 * @return the sequence
	 * 
	 * Date				: Mar 25, 2013
	 */
	public int getSequence()
	{
		return this.sequence;
	}
	/**
	 * Setter method : setSequence
	 * @param sequence the sequence to set
	 * 
	 * Date          : Mar 25, 2013 11:53:05 AM
	 */
	public void setSequence(int sequence)
	{
		this.sequence = sequence;
	}
	/**
	 * Getter Method	: isLowerApproverAction
	 * @return the lowerApproverAction
	 * 
	 * Date				: Mar 26, 2013
	 */
	public boolean isLowerApproverAction()
	{
		return this.lowerApproverAction;
	}
	/**
	 * Setter method : setLowerApproverAction
	 * @param lowerApproverAction the lowerApproverAction to set
	 * 
	 * Date          : Mar 26, 2013 12:36:01 PM
	 */
	public void setLowerApproverAction(boolean lowerApproverAction)
	{
		this.lowerApproverAction = lowerApproverAction;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Sabbatical ["
				+ (this.requestNo != null ? "requestNo=" + this.requestNo
						+ ", " : "")
				+ (this.approverAction != null ? "approverAction="
						+ this.approverAction + ", " : "") + "sequence="
				+ this.sequence + ", lowerApproverAction="
				+ this.lowerApproverAction + "]";
	}
	
	
	
}
