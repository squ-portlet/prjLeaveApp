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
 * File Name			:	DateChange.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility
 * Date of creation		:	Jul 10, 2013  9:17:29 AM
 * Date of modification :	
 * 
 * Summary				:	
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
package om.edu.squ.squportal.portlet.leaveapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 * @author Bhabesh
 *
 */
public class DateChange
{
	private	String	strUtilDate;
	public DateChange(String strDate)
	{
		this.strUtilDate	=	strDate;
	}
	
	/**
	 * 
	 * method name  : getStringToSqlDate
	 * @param strDate
	 * @return
	 * @throws ParseException
	 * DateChange
	 * return type  : java.sql.Date
	 * 
	 * purpose		:	Change date in String format (dd/mm/yyyy) to SQL Date
	 *
	 * Date    		:	Jul 10, 2013 9:41:50 AM
	 */
	public java.sql.Date  getStringToSqlDate() throws ParseException
	{
		java.util.Date		dateUtil		=	null;
		java.sql.Date		dateSQL			=	null;
		
		SimpleDateFormat 	sdfUtilDate 		= 	new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat 	sdfSqlDate 			= 	new SimpleDateFormat("yyyy-MM-dd");
		
		sdfUtilDate.setLenient(false); 
							dateUtil		=	sdfUtilDate.parse(strUtilDate);
							dateSQL			=	java.sql.Date.valueOf(new String(sdfSqlDate.format(dateUtil)));				
		
		return dateSQL;
	}
}
