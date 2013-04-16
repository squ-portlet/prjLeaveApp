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
 * File Name			:	Budget.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.bo
 * Date of creation		:	Apr 15, 2013  9:55:12 AM
 * Date of modification :	
 * 
 * Summary				:	Budget Object
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
public class Budget
{
	private	String	budgetId;
	private	String	budgetYearFrom;
	private	String	budgetYearTo;
	private	String	budgetDescription;
	private	String	budgetDescriptionShort;
	private	String	budgetRemarks;
	/**
	 * Getter Method	: getBudgetId
	 * @return the budgetId
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetId()
	{
		return this.budgetId;
	}
	/**
	 * Setter method : setBudgetId
	 * @param budgetId the budgetId to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetId(String budgetId)
	{
		this.budgetId = budgetId;
	}
	/**
	 * Getter Method	: getBudgetYearFrom
	 * @return the budgetYearFrom
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetYearFrom()
	{
		return this.budgetYearFrom;
	}
	/**
	 * Setter method : setBudgetYearFrom
	 * @param budgetYearFrom the budgetYearFrom to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetYearFrom(String budgetYearFrom)
	{
		this.budgetYearFrom = budgetYearFrom;
	}
	/**
	 * Getter Method	: getBudgetYearTo
	 * @return the budgetYearTo
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetYearTo()
	{
		return this.budgetYearTo;
	}
	/**
	 * Setter method : setBudgetYearTo
	 * @param budgetYearTo the budgetYearTo to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetYearTo(String budgetYearTo)
	{
		this.budgetYearTo = budgetYearTo;
	}
	/**
	 * Getter Method	: getBudgetDescription
	 * @return the budgetDescription
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetDescription()
	{
		return this.budgetDescription;
	}
	/**
	 * Setter method : setBudgetDescription
	 * @param budgetDescription the budgetDescription to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetDescription(String budgetDescription)
	{
		this.budgetDescription = budgetDescription;
	}
	/**
	 * Getter Method	: getBudgetDescriptionShort
	 * @return the budgetDescriptionShort
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetDescriptionShort()
	{
		return this.budgetDescriptionShort;
	}
	/**
	 * Setter method : setBudgetDescriptionShort
	 * @param budgetDescriptionShort the budgetDescriptionShort to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetDescriptionShort(String budgetDescriptionShort)
	{
		this.budgetDescriptionShort = budgetDescriptionShort;
	}
	/**
	 * Getter Method	: getBudgetRemarks
	 * @return the budgetRemarks
	 * 
	 * Date				: Apr 15, 2013
	 */
	public String getBudgetRemarks()
	{
		return this.budgetRemarks;
	}
	/**
	 * Setter method : setBudgetRemarks
	 * @param budgetRemarks the budgetRemarks to set
	 * 
	 * Date          : Apr 15, 2013 9:58:27 AM
	 */
	public void setBudgetRemarks(String budgetRemarks)
	{
		this.budgetRemarks = budgetRemarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Budget ["
				+ (this.budgetId != null ? "budgetId=" + this.budgetId + ", "
						: "")
				+ (this.budgetYearFrom != null ? "budgetYearFrom="
						+ this.budgetYearFrom + ", " : "")
				+ (this.budgetYearTo != null ? "budgetYearTo="
						+ this.budgetYearTo + ", " : "")
				+ (this.budgetDescription != null ? "budgetDescription="
						+ this.budgetDescription + ", " : "")
				+ (this.budgetDescriptionShort != null ? "budgetDescriptionShort="
						+ this.budgetDescriptionShort + ", "
						: "")
				+ (this.budgetRemarks != null ? "budgetRemarks="
						+ this.budgetRemarks : "") + "]";
	}
	
	
	
	
}
