<!--  
 * Project 				:	prjLeaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Planning & Development
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	welcome.jsp
 * 
 * Date of Creation		:	06-August-2012
 *  
 * Summary				:	Welcome Page
 *
 * Copyright 2012 the original author or authors.
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
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<portlet:renderURL var="newApply">
	<portlet:param name="action" value="newApply"/>
</portlet:renderURL>

<c:if test="${not empty leaveRequests}">
	
	<table border="1" width="100%" id="table1" cellspacing="0" cellpadding="0">
		<tr>
			<td class="PortletHeaderColor">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.no"/>
				</span>
			</td>
			<td class="PortletHeaderColor" align="center">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.date"/>
				</span>
			</td>
			<td class="PortletHeaderColor" align="center">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.leave.start.date"/>
				</span>
			</td>
			<td class="PortletHeaderColor" align="center">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.leave.end.date"/>
				</span>
			</td>
			<td class="PortletHeaderColor" align="center">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.leave.type"/>
				</span>
			</td>
			<td class="PortletHeaderColor" align="center">
				<span class="PortletHeaderText">
					<spring:message code="prop.leave.app.title.request.status"/>
				</span>
				</td>
		</tr>
		<c:forEach items="${leaveRequests}" var="req" >
			<tr>
				<portlet:renderURL var="varLeaveApprove">
					<portlet:param name="action" value="leaveApprove"/>
					<portlet:param name="reqNo" >
						<jsp:attribute name="value">
							<c:out value="${req.requestNo}"/>
						</jsp:attribute>
					</portlet:param>
					
				</portlet:renderURL>
			
				<td>&nbsp;<a href="${varLeaveApprove}"><c:out value="${req.requestNo}"/></a></td>
				<td align="center">&nbsp;<c:out value="${req.requestDate}"/></td>
				<td align="center">&nbsp;<c:out value="${req.leaveStartDate}"/></td>
				<td align="center">&nbsp;<c:out value="${req.leaveEndDate}"/></td>
				<td align="center">&nbsp;<c:out value="${req.leaveType.typeDesc}"/></td>
				<td align="center">&nbsp;<c:out value="${req.leaveStatus}"/></td>
			</tr>
		</c:forEach>
	</table>
	
</c:if>



<a href="${newApply}">
	<spring:message code="prop.leave.app.apply.new"/>
</a>
