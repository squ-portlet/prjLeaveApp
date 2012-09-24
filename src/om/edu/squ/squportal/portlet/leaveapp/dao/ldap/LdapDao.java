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
 * File Name			:	LdapDao.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.dao.ldap
 * Date of creation		:	Aug 4, 2012  11:37:04 AM
 * Date of modification :	
 * 
 * Summary				:	Interface for LDAP operation
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
package om.edu.squ.squportal.portlet.leaveapp.dao.ldap;

/**
 * @author Bhabesh
 *
 */
public interface LdapDao
{
	/**
	 * 
	 * method name  : getEmpNumber
	 * @param userName
	 * @return
	 * LdapDao
	 * return type  : String
	 * 
	 * purpose		: Get Employee Number from LDAP
	 *
	 * Date    		:	Aug 4, 2012 11:38:19 AM
	 */
	public String getEmpNumber(String userName);
	/**
	 * 
	 * method name  : getCorrectUserName
	 * @param userName
	 * @return
	 * LdapDao
	 * return type  : String
	 * 
	 * purpose		: correct User Name
	 *
	 * Date    		:	Aug 4, 2012 11:38:26 AM
	 */
	public String getCorrectUserName(String userName);
}
