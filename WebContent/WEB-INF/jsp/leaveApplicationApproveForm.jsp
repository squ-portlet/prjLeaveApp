<!--  
 * Project 				:	prjLeaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Planning & Development
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	leaveApplicationForm.jsp
 * 
 * Date of Creation		:	07-August-2012
 *  
 * Summary				:	Leave Application Form
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

<head>

<spring:url value="/css/images/backIcon.png" var="urlImgBack"/>
<c:url value="/css/images/calendar.gif" var="urlImgCalendar"/>
<c:url value="/css/images/ui-bg_diagonals-thick_75_f3d8d8_40x40.png" var="urlImgBgDiagonals"/>
<c:url value="/css/images/ui-bg_dots-small_65_a6a6a6_2x2.png" var="urlImgBgDots"/>
<c:url value="/css/images/ui-bg_flat_0_333333_40x100.png" var="urlImgBgFlat"/>
<c:url value="/css/images/ui-bg_flat_65_ffffff_40x100.png" var="urlImgBgFlat65"/>
<c:url value="/css/images/ui-bg_flat_75_ffffff_40x100.png" var="urlImgBgFlat75"/>
<c:url value="/css/images/ui-bg_glass_55_fbf8ee_1x400.png" var="urlImgBgGlass55"/>
<c:url value="/css/images/ui-bg_highlight-hard_100_eeeeee_1x100.png" var="urlImgBgHighLightHard1"/>
<c:url value="/css/images/ui-bg_highlight-hard_100_f6f6f6_1x100.png" var="urlImgBgHighLightHard2"/>
<c:url value="/css/images/ui-bg_highlight-soft_15_cc0000_1x100.png" var="urlImgBgHighLightSoft1"/>
<c:url value="/css/images/ui-icons_004276_256x240.png" var="urlImgIcons1"/>
<c:url value="/css/images/ui-icons_cc0000_256x240.png" var="urlImgIcons2"/>
<c:url value="/css/images/ui-icons_ffffff_256x240.png" var="urlImgIcons3"/>

<c:url value="/css/squPortletStyles.css" var="urlCssSquPortletStyle"/>
<c:url value="/css/jquery-ui-1.8.18.custom.css" var="urlJQueryCSS"/>

<c:url value="/js/jquery-1.7.1.min.js" var="urlJsJqueryMin"/>
<c:url value="/js/jquery-ui-1.8.18.custom.min.js" var="urlJsJqueryCustom"/>

<link type="text/css" href="${urlJQueryCSS}" rel="stylesheet" />
<link rel="Stylesheet" type="text/css" href="${urlCssSquPortletStyle}" />

<script type="text/javascript" src="${urlJsJqueryMin}"></script>
<script type="text/javascript" src="${urlJsJqueryCustom}"></script>


<c:if test="${rc.locale.language=='ar'}">
<style>
	img.imgBck {
        -moz-transform: scaleX(-1);
        -o-transform: scaleX(-1);
        -webkit-transform: scaleX(-1);
        transform: scaleX(-1);
        filter: FlipH;
        -ms-filter: "FlipH";
	}
</style>
</c:if>

<script>
$(function(){
	// Datepicker
	$('.calendar').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		minDate: 1,
		firstDay:6
		//inline: true
	});
});

</script>



</head>


<portlet:renderURL var="backToMain">
	<portlet:param name="action" value="backToMain"/>
</portlet:renderURL>


<div >
	<a class="button" style="width: 100 px; " href="${backToMain}">
		<img class="imgBck"  src="${urlImgBack}" style="border-style: none; position: relative; top: 3px;"/>&nbsp;
		<fmt:message key="prop.leave.app.url.back"/>
	</a>
</div>

<portlet:actionURL var="leaveApprove">
	<portlet:param name="action" value="leaveApprove"/>
</portlet:actionURL>


<%-- <form:hidden path="requestDate" /> --%>

<%-- <form:hidden path="leaveStartDate" /> --%>
<%-- <form:hidden path="leaveEndDate"/> --%>
<%-- <form:hidden path="leaveRemarks"/> --%>

<%-- <form:checkbox path="adminSqu"/> --%>


	<fieldset>
		<legend>
			<h3>
				<spring:message code="prop.leave.app.apply.form.legend.title"/>
			</h3>
		</legend>
			<form:form modelAttribute="leaveAppModel" action="${leaveApprove}" method="post" htmlEscape="false" >	
			<form:hidden path="requestNo" />	
			<table cellspacing="0" cellpadding="0" border="1" width="100%">
				<caption><spring:message code="prop.leave.app.apply.form.required.details"/></caption>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.request.no"/>:
						</span>
					</th> 
					<td><c:out value="${leaveRequest.requestNo}"/></td>
					<td>&nbsp;</td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.request.date"/>:
						</span>
					</th>
					<td><c:out value="${leaveRequest.requestDate}"/></td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.type"/>:
						</span>
					</th>
					<td colspan="4">
						<c:out value="${leaveRequest.leaveType.typeDesc}"/>
					</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.start.date"/>:
						</span>
					</th>
					<td><c:out value="${leaveRequest.leaveStartDate}"/></td>
					<td>&nbsp;</td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.end.date"/>:
						</span>
					</th>
					<td><c:out value="${leaveRequest.leaveEndDate}"/></td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.remarks"/>:
						</span>
					</th>
					<td colspan="4"><c:out value="${leaveRequest.leaveRequestRemarks}"/></td>
				</tr>
			</table>
			
			<p/>
			<table cellspacing="0" cellpadding="0" border="1" width="100%">
				<caption><spring:message code="prop.leave.app.apply.form.required.details.other"/></caption>
	<!-- 			<tr> -->
	<!-- 				<th class="PortletHeaderColor"> -->
	<!-- 					<span class="PortletHeaderText"> -->
	<%-- 						<spring:message code="prop.leave.app.apply.form.purpose"/>: --%>
	<!-- 					</span> -->
	<!-- 				</th> -->
	<%-- 				<td colspan="4"><form:textarea path="leavePurpose" cssStyle="width:100%" /></td> --%>
	<!-- 			</tr> -->
	<!-- 			<tr> -->
	<!-- 				<th class="PortletHeaderColor"> -->
	<!-- 					<span class="PortletHeaderText"> -->
	<%-- 						<spring:message code="prop.leave.app.apply.form.last.leave.return.date"/>: --%>
	<!-- 					</span>		 -->
	<!-- 				</th> -->
	<%-- 				<td colspan="4"><form:input path="leaveLastReturnDate" cssClass="calendar"/></td> --%>
	<!-- 			</tr> -->
	<!-- 			<tr> -->
	<!-- 				<th class="PortletHeaderColor"> -->
	<!-- 					<span class="PortletHeaderText"> -->
	<%-- 						<spring:message code="prop.leave.app.apply.form.research.id"/>: --%>
	<!-- 					</span> -->
	<!-- 				</th> -->
	<%-- 				<td colspan="4"><form:input path="researchId"/></td> --%>
	<!-- 			</tr> -->
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.administrative"/>:
						</span>
					</th>
					<td>
						(<font color="red"><c:out value="${leaveRequest.employee.admin2}"/></font>) / <c:out value="${leaveRequest.employee.admin}"/>
					</td>
					<td></td>
					<c:if test="${not empty leaveRequest.employee.designationAddl}">
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.position"/>
							</span>
						</th>
						<td><c:out value="${leaveRequest.employee.designationAddl}"/></td>
					</c:if>			
				</tr>
			
			</table>
			<table cellspacing="0" cellpadding="0" border="1" width="100%">
				<caption><spring:message code="prop.leave.app.apply.form.requester.details"/></caption>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.name"/>
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.empName}"/></td>
					<td></td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.squ.id"/>
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.empInternetId}"/> / (<c:out value="${leaveRequest.employee.empNumber}"/>)</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.job.title"/>
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.designation}"/></td>
					<td></td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.grade"/>:
						</span>
					</th>
					<td>
						<c:out value="${leaveRequest.employee.grade}"/> (<c:out value="${leaveRequest.employee.gradeShort}"/>)
					</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.branch"/>
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.branch}"/></td>
					<td></td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.department"/>
						</span>
					</th>
					<td>
						<c:if test="not empty leaveRequest.employee.department2">
							(<font color="red"><c:out value="${leaveRequest.employee.department2}"/></font>)
							<br>
						</c:if>
						<c:out value="${leaveRequest.employee.department}"/>
					
					</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.section"/>:
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.section}"/></td>
					<td></td>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.doa"/>
						</span>
					</th>
					<td><c:out value="${leaveRequest.employee.joinDt}"/></td>
				</tr>						
			</table>
			<c:if test="${not empty delegatedEmps}">
				<table cellspacing="0" cellpadding="0" border="1" width="100%">
					<caption>
						<spring:message code="prop.leave.app.apply.form.delegated.employees"/>
					</caption>
					<tr>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.delegated.date.from"/>
							</span>
						</th>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.delegated.date.to"/>
							</span>
						</th>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.delegated.emp.code"/>
							</span>
						</th>
					</tr>
		<%-- 			<c:forEach var="i" begin="0" end="2" step="1"> --%>
						<c:forEach items="${delegatedEmps}" var="delgEmp">
						<tr>
							<td><c:out value ="${delgEmp.fromDate}" /></td>
							<td><c:out value ="${delgEmp.toDate}" /></td>
							<td>
							<c:out value ="${delgEmp.empInternetId}" /> / (<c:out value ="${delgEmp.empNumber}" />) - <c:out value ="${delgEmp.empName}" /> -
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			
	<!-- 		<table cellspacing="0" cellpadding="0" border="1" width="100%"> -->
	<!-- 			<tr> -->
	<!-- 				<td> -->
						
	<!-- 				</td> -->
	<!-- 			</tr> -->
	<!-- 		</table> -->
			<c:if test="${not empty appHistory}" >
			<fieldset>
					<legend><spring:message code="prop.leave.app.apply.form.approvar.history.details"/></legend>
				<table cellspacing="0" cellpadding="0" border="1" width="100%">
					<tr>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.approvar.name"/>
							</span>
						</th>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.approvar.date"/>
							</span>
						</th>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.approvar.action"/>
							</span>
						</th>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.approvar.approver"/>
							</span>
						</th>	
					</tr>
					<c:forEach items="${appHistory}" var="appHis">
						<tr>
							<td>&nbsp;<c:out value="${appHis.employee.empName}" /></td>
							<td>&nbsp;<c:out value="${appHis.action.actionDate}" /></td>
							<td>&nbsp;<c:out value="${appHis.action.actionDesc}" /></td>
							<td>&nbsp;<c:out value="${appHis.action.adminActionRemark}" /></td>	
						</tr>
					</c:forEach>			
				</table>
			</fieldset>
			</c:if>
	<%-- 		<center> --%>
	<%-- 			<input type="submit" value='<spring:message code="prop.leave.app.apply.form.requester.submit"/>' style="border-style: solid; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"/> --%>
	<%-- 		</center> --%>
			<c:set var="varDisable" value="false"/>
			<c:set var="bttnDisable" value="not disabled"/>
			<c:if test="${leaveRequest.approve.approverAction==constActionApprove}">
				<c:set var="varDisable" value="true"/>
				<c:set var="bttnDisable" value="disabled='disabled'"/>
			</c:if>
			<fieldset>
				<legend>
					<spring:message code="prop.leave.app.apply.form.approvar.action.administrative"/>
				</legend>
				<table cellspacing="0" cellpadding="0" border="1" width="100%">
					<tr>
						<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.approvar.action"/>
						</span>
						</th>
							
						<td>
							<spring:message code="prop.leave.app.apply.form.approvar.actions" var="appActions"/>
							
							<form:select path="approverAction" disabled="${varDisable}">
								<c:if test="${empty leaveRequest.approve.approverAction}">
									<form:option value=""><spring:message code="prop.leave.app.dropdown.text"/></form:option>
								</c:if>
								<form:options items="${adminActions}" itemLabel="actionDesc" itemValue="actionCode"/>
							</form:select>
						
						</td>
						<td>&nbsp;</td>
						<th class="PortletHeaderColor">
							<span class="PortletHeaderText">
								<spring:message code="prop.leave.app.apply.form.approvar.remarks"/>
							</span>
						</th>
						<td>
							<form:textarea path="approverRemark" cssStyle="width:100%" disabled="${varDisable}"/>
						</td>
					</tr>
				</table>
				<center>
				<c:if test="${!varDisable}">
					<input type="submit" value='<spring:message code="prop.leave.app.apply.form.approver.submit"/>' 
					style="border-style: solid; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px" 
					/>
				</c:if>
				</center>
				
				
			</fieldset>
		</form:form>
	</fieldset>
