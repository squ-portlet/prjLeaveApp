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


<c:url value="/css/jquery-ui-1.8.18.custom.css" var="urlJQueryCSS"/>
<c:url value="/css/ui.jqgrid.css" var="urlJqGridCSS"/>


<c:url value="/js/jquery-1.7.2.min.js" var="urlJsJqueryMin"/>
<c:url value="/js/jquery-ui-1.8.18.custom.min.js" var="urlJsJqueryCustom"/>
<c:url value="/js/jquery.layout.js" var="urlJsJqueryLayout"/>
<c:url value="/js/grid.locale-en.js"  var="urlJsJqueryGridLocaleEN"/>
<c:url value="/js/grid.locale-ar.js" var="urlJsJqueryGridLocaleAR"/>
<c:url value="/js/jquery.jqGrid.min.js"  var="urlJsJqueryGridMin"/>
<c:url value="/js/jquery.tablednd.js"  var="urlJsJqueryTableDND"/>
<c:url value="/js/jquery.contextmenu.js"  var="urlJsJqueryContextMenu"/>

<link type="text/css" href="${urlJQueryCSS}" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${urlJqGridCSS}" />

<script type="text/javascript" src="${urlJsJqueryMin}"></script>
<script type="text/javascript" src="${urlJsJqueryCustom}"></script>
<script type="text/javascript" src="${urlJsJqueryLayout}"></script>
<c:if test="${rc.locale.language=='en'}">
	<script type="text/javascript" src="${urlJsJqueryGridLocaleEN}"></script>
</c:if>
<c:if test="${rc.locale.language=='ar'}">
	<script type="text/javascript" src="${urlJsJqueryGridLocaleAR}"></script>
</c:if>
<script type="text/javascript" src="${urlJsJqueryGridMin}"></script>
<script type="text/javascript" src="${urlJsJqueryTableDND}"></script>
<script type="text/javascript" src="${urlJsJqueryContextMenu}"></script>

<style>
.error
{
	color:Red;
}

.error {
    border:1px solid red;
  }

	.ui-state-error, .ui-widget-content .ui-state-error, .ui-widget-header .ui-state-error {border: 1px solid #cc0000; background: #f3d8d8 url("${urlImgBgDiagonals}") 50% 50% repeat; color: #2e2e2e; }
	.ui-widget-overlay { background: #a6a6a6 url("${urlImgBgDots}") 50% 50% repeat; opacity: .40;filter:Alpha(Opacity=40); }
	.ui-widget-shadow { margin: -8px 0 0 -8px; padding: 8px; background: #333333 url("${urlImgBgFlat}") 50% 50% repeat-x; opacity: .10;filter:Alpha(Opacity=10); -moz-border-radius: 8px; -khtml-border-radius: 8px; -webkit-border-radius: 8px; border-radius: 8px; }
	
	.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active { border: 1px solid #eeeeee; background: #ffffff url("${urlImgBgFlat65}") 50% 50% repeat-x; font-weight: bold; color: #cc0000; }
	.ui-widget-content { border: 1px solid #eeeeee; background: #ffffff url("${urlImgBgFlat75}") 50% 50% repeat-x; color: #333333; }
	.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: 1px solid #fcd3a1; background: #fbf8ee url("${urlImgBgGlass55}") 50% 50% repeat-x; color: #444444; }
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #d8dcdf; background: #eeeeee url("${urlImgBgHighLightHard1}") 50% 50% repeat-x; font-weight: bold; color: #004276; }
	.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border: 1px solid #cdd5da; background: #f6f6f6 url("${urlImgBgHighLightHard2}") 50% 50% repeat-x; font-weight: bold; color: #111111; }
	
	.ui-widget-header { border: 1px solid #e3a1a1; background: #cc0000 url("${urlImgBgHighLightSoft1}") 50% 50% repeat-x; color: #ffffff; font-weight: bold; }
/* 	.ui-widget-header { border: 1px solid #e3a1a1; background: #cc0000 url("${urlImgBgHighLightSoft1}") 50% 50% repeat-x; color: #ffffff; font-weight: bold; } */
	.ui-state-highlight .ui-icon {background-image: url("${urlImgIcons1}"); }
	
	
	
	
	.ui-icon { width: 16px; height: 16px; background-image: url("${urlImgIcons2}"); }
	.ui-widget-content .ui-icon {background-image: url("${urlImgIcons2}"); }
	
	.ui-widget-header .ui-icon {background-image: url("${urlImgIcons3}"); }
	
	.ui-state-default .ui-icon { background-image: url("${urlImgIcons2}"); }
	.ui-state-hover .ui-icon, .ui-state-focus .ui-icon {background-image: url("${urlImgIcons2}"); }
	.ui-state-active .ui-icon {background-image: url("${urlImgIcons2}"); }
	.ui-state-error .ui-icon, .ui-state-error-text .ui-icon {background-image: url("${urlImgIcons2}"); }
	


</style>




<c:if test="${not empty allowELeaveRequestMsg}">
	<h2><font color="red"><c:out value="${allowELeaveRequestMsg}"/></font></h2>
</c:if>

<table id="list2"></table>
<div id="pager2"></div>



<c:if test="${not empty leaveRequests}">
	<script type="text/javascript">
	//contribution :  http://trirand.com/blog/jqgrid/jqgrid.html
	$(function(){
		$('#list2').jqGrid({
		   	//url:'server.php?q=2',
			//datatype: "json",
			datatype: "local",
		   	colNames:[
		   	          '<spring:message code="prop.leave.app.title.request.no"/>',
		   	          '<spring:message code="prop.leave.app.title.request.date"/>', 
		   	          '<spring:message code="prop.leave.app.title.request.leave.start.date"/>',
		   	          '<spring:message code="prop.leave.app.title.request.leave.end.date"/>',
		   	          '<spring:message code="prop.leave.app.title.request.leave.type"/>',
		   	          '<spring:message code="prop.leave.app.title.request.status"/>',
		   	          'employee'
		   	          ],
		   	colModel:[
		   		{name:'reqNo',index:'endDate'},
		   		{name:'reqDate',index:'reqDate', align:"right"},
		   		{name:'startDate',index:'startDate',  align:"right"},
		   		{name:'endDate',index:'endDate', align:"right"},
		   		{name:'type'},		
		   		{name:'status'},
		   		{name:'employee'}
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#pager2',
		   	sortname: 'reqNo',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:'<spring:message code="javax.portlet.title"/>'
		});
	});
	
	var mydata = [
				<c:forEach items="${leaveRequests}" var="req" >
					{
						
						<portlet:renderURL var="varLeaveApprove">
							<portlet:param name="action" value="leaveApprove"/>
							<portlet:param name="reqNo" >
							<jsp:attribute name="value">
								<c:out value="${req.requestNo}"/>
							</jsp:attribute>
							</portlet:param>
						</portlet:renderURL>
						<c:choose>
							<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
												(!req.employee.senior && (req.employee.empNumber != empNumber))}">
								reqNo:'<a href="${varLeaveApprove}"><c:out value="${req.requestNo}"/></a>',
							</c:when>
							<c:otherwise>
								reqNo:'<c:out value="${req.requestNo}"/>',
							</c:otherwise>
						</c:choose>
					 reqDate:'<c:out value="${req.requestDate}"/>',
					 startDate:'<c:out value="${req.leaveStartDate}"/>',
					 endDate:'<c:out value="${req.leaveEndDate}"/>',
					 type:'<c:out value="${req.leaveType.typeDesc}"/>',
					 status:'<c:out value="${req.leaveStatus}"/>',
					 employee:'<c:out value="${req.employee.empNumber}"/> / <c:out value="${req.employee.empInternetId}"/>'
					},
				</c:forEach>
	 
	      		];
	$(function(){      		
	for(var i=0;i<=mydata.length;i++)
		$('#list2').jqGrid('addRowData',i+1,mydata[i]);
	});
	
	</script>
</c:if>


<portlet:renderURL var="newApply">
	<portlet:param name="action" value="newApply"/>
</portlet:renderURL>

<a href="${newApply}">
	<spring:message code="prop.leave.app.apply.new"/>
</a>
