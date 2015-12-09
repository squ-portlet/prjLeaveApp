<!--  
 * Project 				:	prjLeaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Planning & Development
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.0 (Annotation) Portlet
 * 
 * File Name			:	error.jsp
 * 
 * Date of Modification	:	20-april-2013
 *  
 * Summary				:	Error Page
 *
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

-->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/css/images/ui-bg_diagonals-thick_18_b81900_40x40.png" var="urlImgUIBGAlert"/>

<table width="100%" class="RegionBorder">
	<tr>
		<td class="PortletHeaderColor portletheadertext" background="${urlImgUIBGAlert}">
				
		<div  style="padding: 0 .7em; "> 
				<p> 
				<h3><strong>Alert:</strong> <spring:message code="prop.error.service.not.available"/></h3></p>
			</div>
		</td>
	</tr>
</table>