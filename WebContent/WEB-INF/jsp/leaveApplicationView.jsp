<!--  
 * Project 				:	prjLeaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Planning & Development
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	leaveApplicationView.jsp
 * 
 * Date of Creation		:	07-August-2012
 *  
 * Summary				:	Leave Application View
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

<c:url value="/css/images/ui-icons_454545_256x240.png" var="urlImgIcons4"/>
<c:url value="/css/images/ui-icons_888888_256x240.png" var="urlImgIcons5"/>
<c:url value="/css/images/ui-bg_glass_65_ffffff_1x400.png" var="urlImgBgFlat65x1"/>
<c:url value="/css/images/ui-bg_glass_75_e6e6e6_1x400.png" var="urlImgBgFlat75e6"/>
<c:url value="/css/images/ui-bg_glass_75_dadada_1x400.png" var="urlImgBgFlat75d1"/>

<c:url value="/css/squPortletStyles.css" var="urlCssSquPortletStyle"/>
<c:url value="/css/jquery-ui-1.8.18.custom.css" var="urlJQueryCSS"/>
<c:url value="/css/jquery.ui.accordion.css" var="urlJQueryAccordionCSS"/>
<c:url value="/css/jquery.ui.theme.css" var="urlJQueryThemeCSS"/>

<c:url value="/js/jquery-1.7.1.min.js" var="urlJsJqueryMin"/>
<c:url value="/js/jquery-ui-1.8.18.custom.min.js" var="urlJsJqueryCustom"/>
<c:url value="/js/jquery.ui.accordion.js" var="urlJsJqueryAccordion"/>

<link type="text/css" href="${urlJQueryCSS}" rel="stylesheet" />
<link rel="Stylesheet" type="text/css" href="${urlCssSquPortletStyle}" />
<link type="text/css" href="${urlJsJqueryAccordion}" rel="stylesheet" />
<link type="text/css" href="${urlJQueryThemeCSS}" rel="stylesheet" />

<script type="text/javascript" src="${urlJsJqueryMin}"></script>
<script type="text/javascript" src="${urlJsJqueryCustom}"></script>
<script type="text/javascript" src="${urlJsJqueryAccordion}"></script>

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

<style>
.ui-state-hover .ui-icon, .ui-state-focus .ui-icon {background-image: url("${urlImgIcons4}")/*{iconsHover}*/; }
.ui-state-active .ui-icon {background-image: url("${urlImgIcons4}")/*{iconsActive}*/; }
.ui-state-default .ui-icon { background-image: url("${urlImgIcons5}")/*{iconsDefault}*/; }
.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active { border: 1px solid #aaaaaa/*{borderColorActive}*/; background: #ffffff/*{bgColorActive}*/ url("${urlImgBgFlat65x1}")/*{bgImgUrlActive}*/ 50%/*{bgActiveXPos}*/ 50%/*{bgActiveYPos}*/ repeat-x/*{bgActiveRepeat}*/; font-weight: normal/*{fwDefault}*/; color: #212121/*{fcActive}*/; }
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #d3d3d3/*{borderColorDefault}*/; background: #e6e6e6/*{bgColorDefault}*/ url("${urlImgBgFlat75e6}")/*{bgImgUrlDefault}*/ 50%/*{bgDefaultXPos}*/ 50%/*{bgDefaultYPos}*/ repeat-x/*{bgDefaultRepeat}*/; font-weight: normal/*{fwDefault}*/; color: #555555/*{fcDefault}*/; }
.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border: 1px solid #999999/*{borderColorHover}*/; background: #dadada/*{bgColorHover}*/ url("${urlImgBgFlat75d1}")/*{bgImgUrlHover}*/ 50%/*{bgHoverXPos}*/ 50%/*{bgHoverYPos}*/ repeat-x/*{bgHoverRepeat}*/; font-weight: normal/*{fwDefault}*/; color: #212121/*{fcHover}*/; }
.ui-widget-content { border: 1px solid #aaaaaa/*{borderColorContent}*/; background: #ffffff/*{bgColorContent}*/ url("${urlImgBgFlat75}")/*{bgImgUrlContent}*/ 50%/*{bgContentXPos}*/ 50%/*{bgContentYPos}*/ repeat-x/*{bgContentRepeat}*/; color: #222222/*{fcContent}*/; }
</style>


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

$(function() {
	$( "#accordion" ).accordion(
	{
		collapsible: true
	}		
	);
	});


</script>



</head>


<portlet:renderURL var="backToMain">
	<portlet:param name="action" value="backToMain"/>
</portlet:renderURL>


<div >
	<a class="button btn btn-primary" style="width: 100 px; " href="${backToMain}">
		<img class="imgBck"  src="${urlImgBack}" style="border-style: none; position: relative; top: 3px;"/>&nbsp;
		<fmt:message key="prop.leave.app.url.back"/>
	</a>
</div>

<portlet:actionURL var="leaveApprove">
	<portlet:param name="action" value="leaveApprove"/>
</portlet:actionURL>

	<fieldset>
		<legend>
			<h3>
				<spring:message code="prop.leave.app.apply.form.legend.title"/>
			</h3>
		</legend>
			<fieldset style="padding-top=0;margin-top=0;">
				<legend><spring:message code="prop.leave.app.apply.form.required.details"/></legend>
				<table cellspacing="0" cellpadding="0"  width="100%">
					<tr>
						<td>
								<b><spring:message code="prop.leave.app.apply.form.request.no"/>:</b>
						</td> 
						<td><c:out value="${leaveRequest.requestNo}"/></td>

						<td>
							<b><spring:message code="prop.leave.app.apply.form.request.date"/>:</b>
						</td>
						<td><c:out value="${leaveRequest.requestDate}"/></td>
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.leave.type"/>:</b>
						</td>
						<td >
							<c:out value="${leaveRequest.leaveType.typeDesc}"/>
							<c:if test="${leaveRequest.leaveTypeFlag.typeNo == 'B'}">
								<br>(<spring:message code="prop.leave.app.apply.form.leave.sabbatical.research.id"/> : <c:out value="${leaveRequest.researchId}"/>)
							</c:if>
						</td>
					<c:if test="${leaveRequest.leaveTypeFlag.typeNo == 'A' && not empty leaveRequest.leaveDateDuration}">
						<c:if test="${leaveRequest.leaveDateDuration > 14}">
							<td >
									<b><spring:message code="prop.leave.app.apply.form.salary.advance"/>:</b>
							</td>
							<td>
								<c:choose>
									<c:when test="${leaveRequest.processSalaray == 'Y'}">
										<spring:message code="prop.leave.app.apply.form.salary.advance.yes"/>
									</c:when>
									<c:otherwise>
										<spring:message code="prop.leave.app.apply.form.salary.advance.no"/>
									</c:otherwise>
								</c:choose>
							</td>
						</c:if>
					</c:if>
					</tr>
					<tr>
						<td >
							<b><spring:message code="prop.leave.app.apply.form.leave.start.date"/>:</b>
						</td>
						<td><c:out value="${leaveRequest.leaveStartDate}"/></td>

						<td >
							<b><spring:message code="prop.leave.app.apply.form.leave.end.date"/>:</b>
						</td>
						<td><c:out value="${leaveRequest.leaveEndDate}"/></td>
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.leave.duration"/></b>
						</td>
						<td>
							<c:out value="${leaveRequest.leaveDateDuration}"/> <spring:message code="prop.leave.app.apply.form.leave.balance.days"/>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.leave.balance"/></b>
						</td>
						<td colspan="3">
							<spring:message code="prop.leave.app.before.leave"/> :  <b>${leaveRequest.leaveBalanceStart}</b> <spring:message code="prop.leave.app.apply.form.leave.balance.days"/>
						</td>
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.leave.remarks"/>:</b>
						</td>
						<td colspan="3">
						<c:choose>
							<c:when test="${not empty leaveRequest.leaveRequestRemarks}">
								<c:out value="${leaveRequest.leaveRequestRemarks}"/>
							</c:when>
							<c:otherwise>
								<spring:message code="prop.leave.app.apply.leave.remarks.not.available"/>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<c:if test="${not empty leaveRequest.leaveReturnDate}">
						<tr>
							<td><b><font color="red"><spring:message code="prop.leave.app.return.date"/></font></b></td>
							<td><c:out value="${leaveRequest.leaveReturnDate}"/></td>
						</tr>
					</c:if>
				</table>
			</fieldset>
			<p/>
			<fieldset>
				<legend>
					<spring:message code="prop.leave.app.apply.form.requester.details"/>
				</legend>
				<table cellspacing="0" cellpadding="0"  width="100%">
					<tr>
						<td >
							<b><spring:message code="prop.leave.app.apply.form.requester.name"/></b>
						</td>
						<td><c:out value="${leaveRequest.employee.empName}"/></td>
						<td></td>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.requester.squ.id"/></b>
						</td>
						<td><c:out value="${leaveRequest.employee.empInternetId}"/> / (<c:out value="${leaveRequest.employee.empNumber}"/>)</td>
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.requester.job.title"/></b>
						</td>
						<td><c:out value="${leaveRequest.employee.designation}"/></td>
						<td></td>

<!-- Temporary blocking display of Grade  --  Code Start -->						

						<td>
<!-- 							 -->
<%-- 								<spring:message code="prop.leave.app.apply.form.requester.grade"/>: --%>
<!-- 							 -->
						</td>
						<td>
<%-- 							<c:out value="${leaveRequest.employee.grade}"/> (<c:out value="${leaveRequest.employee.gradeShort}"/>) --%>
						</td>

<!-- Temporary blocking display of Grade  --  Code End -->
						
					</tr>
					<tr>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.requester.branch"/></b>
						</td>
						<td><c:out value="${leaveRequest.employee.branch}"/></td>
						<td></td>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.requester.department"/></b>
						</td>
						<td>
							<c:if test="not empty leaveRequest.employee.department2">
								(<font color="red"><c:out value="${leaveRequest.employee.department2}"/></font>)
								<br>
							</c:if>
							<c:out value="${leaveRequest.employee.department}"/>
						
						</td>
					</tr>
					<tr>
						<td><b><spring:message code="prop.leave.app.apply.form.requester.section"/>:</b>
						</td>
						<td><c:out value="${leaveRequest.employee.section}"/></td>
						<td></td>
						<td>
							<b><spring:message code="prop.leave.app.apply.form.requester.doa"/></b>
						</td>
						<td><c:out value="${leaveRequest.employee.joinDt}"/></td>
					</tr>						
				</table>
			</fieldset>
<div id="accordion">
			<c:if test="${not empty delegatedEmps}">
			<h3><a href="#"><spring:message code="prop.leave.app.apply.form.delegated.employees"/></a><spring:message code="prop.leave.form.hide.show"/></h3>
			<div>
			<fieldset>
				<legend>
					<spring:message code="prop.leave.app.apply.form.delegated.employees"/>
				</legend>
				<table class="table table-bordered" width="100%">
					<tr>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.delegated.date.from"/>
							
						</th>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.delegated.date.to"/>
							
						</th>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.delegated.emp.code"/>
							
						</th>
					</tr>
						<c:forEach items="${delegatedEmps}" var="delgEmp">
						<c:if test="${not empty delgEmp.empName}">
							<tr>
								<td>&nbsp;<c:out value ="${delgEmp.fromDate}" /></td>
								<td>&nbsp;<c:out value ="${delgEmp.toDate}" /></td>
								<td>&nbsp;<c:out value ="${delgEmp.empName}" /></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				</fieldset>
				</div>
			</c:if>

			<c:if test="${not empty leaveHistory}">
			<h3><a href="#"><spring:message code="prop.leave.app.form.requester.history.details"/></a><spring:message code="prop.leave.form.hide.show"/></h3>
			<div>
			<fieldset>
				<legend><spring:message code="prop.leave.app.form.requester.history.details"/></legend>
				<table class="table table-bordered" width="100%">
					<tr>
						<th  >
							
								<spring:message code="prop.leave.app.form.requester.history.date"/>
							
						</th>
						<th  >
							
								<spring:message code="prop.leave.app.form.requester.history.status"/>
							
						</th>
						<th  >
							
								<spring:message code="prop.leave.app.form.requester.history.remarks"/>
							
						</th>
					</tr>
					<c:forEach items="${leaveHistory}" var="lhis">
						<tr>
							<td>&nbsp; <c:out value="${lhis.requestDate}" /> </td>
							<td>&nbsp; <c:out value="${lhis.status.statusDesc}" /> </td>
							<td>&nbsp; 
							<c:choose>
								<c:when test="${not empty lhis.leaveRequestRemarks}">
									<c:out value="${lhis.leaveRequestRemarks}" />
								</c:when>
								<c:otherwise>
									<spring:message code="prop.leave.app.apply.leave.remarks.not.available"/>
								</c:otherwise> 
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
			</div>
			</c:if>	
					
			
			<c:if test="${not empty appHistory}" >
			<h3><a href="#"><spring:message code="prop.leave.app.apply.form.approvar.history.details"/></a><spring:message code="prop.leave.form.hide.show"/></h3>
			<div>
			<fieldset>
					<legend><spring:message code="prop.leave.app.apply.form.approvar.history.details"/></legend>
				<table class="table table-bordered"  width="100%">
					<tr>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.approvar.name"/>
							
						</th>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.approvar.date"/>
							
						</th>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.approvar.action"/>
							
						</th>
						<th >
							
								<spring:message code="prop.leave.app.apply.form.approvar.approver"/>
							
						</th>	
					</tr>
					<c:forEach items="${appHistory}" var="appHis">
						<tr>
							<td>&nbsp;<c:out value="${appHis.employee.empName}" /></td>
							<td>&nbsp;<c:out value="${appHis.action.actionDate}" /></td>
							<td>&nbsp;<c:out value="${appHis.action.actionDesc}" /></td>
							<td>&nbsp;
							<c:choose>
								<c:when test="${not empty appHis.action.adminActionRemark}">
									<c:out value="${appHis.action.adminActionRemark}" />
								</c:when>
								<c:otherwise>
									<spring:message code="prop.leave.app.apply.leave.remarks.not.available"/>
								</c:otherwise>
							</c:choose>
							</td>	
						</tr>
					</c:forEach>			
				</table>
			</fieldset>
			</div>
			</c:if>
</div>

	</fieldset>
