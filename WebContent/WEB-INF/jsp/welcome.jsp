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

<table id="list3" class="listT"></table>
<div id="pager3" class="pagerT"></div>

<table id="list2" class="listT"></table>
<div id="pager2" class="pagerT"></div>


<c:if test="${not empty leaveRequests}">
	<script type="text/javascript">
	//contribution :  http://trirand.com/blog/jqgrid/jqgrid.html

	
	var countRequester = 0;
	var countApprover = 0;
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
											(req.employee.hierarchyCode >empHierarchyAddl) ||
												(!req.employee.senior && (req.employee.empNumber != empNumber))}">
								reqNo:'<a href="${varLeaveApprove}"><font color="red"><c:out value="${req.requestNo}"/></font></a>',
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
						<c:choose>
						<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
										(req.employee.hierarchyCode >empHierarchyAddl) ||
											(!req.employee.senior && (req.employee.empNumber != empNumber))}">
							employee:'<c:out value="${req.employee.empName}"/> &nbsp; (<c:out value="${req.employee.empNumber}"/>)',
						</c:when>
						<c:otherwise>
							employee:'<c:out value="${req.approve.employee.empName}"/> &nbsp; (<c:out value="${req.approve.employee.empNumber}"/>)',
						</c:otherwise>
					</c:choose>
					 
					<c:choose>
						<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
											(req.employee.hierarchyCode >empHierarchyAddl) ||
											(!req.employee.senior && (req.employee.empNumber != empNumber))}">
							<c:choose>
								<c:when test="${(req.status.statusCode == leaveStatusApproved)}">
									actions: '-',
								</c:when>
								<c:otherwise>
									actions: '<c:forEach items="${adminActions}" var="admActions">'+
									'<portlet:actionURL var="varLeaveAdminAction">'+
									'   <portlet:param name="action" value="leaveAutoAdminAction"/>'+
									'   <portlet:param name="reqNum" value="${req.requestNo}"/>'+
									'   <portlet:param name="appActionNum" value="${admActions.actionCode}"/>'+
									'</portlet:actionURL>'+
									'<a href="${varLeaveAdminAction}"><c:out value="${admActions.actionDesc}"/></a> &nbsp;'+
									'</c:forEach>',		
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(req.status.statusCode == furtherClarification)}">
							<portlet:renderURL var="varLeaveClarification">
								<portlet:param name="action" value="updateLeaveApply"/>
								<portlet:param name="reqNum" value="${req.requestNo}"/>
							</portlet:renderURL>
							actions:'<a href="${varLeaveClarification}"><font color="red"><spring:message code="prop.leave.app.apply.action.update"/></font></a>',
						</c:when>
						<c:otherwise>
							actions:'-',
						</c:otherwise>
					</c:choose>
					
					<c:choose>
					<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
									(req.employee.hierarchyCode >empHierarchyAddl) ||
										(!req.employee.senior && (req.employee.empNumber != empNumber))}">
					isApprover:'y'
					</c:when>
					<c:otherwise>
					isApprover:'n'
					</c:otherwise>
				</c:choose>
					
					},
				</c:forEach>

	      		];
	
	
	var list3Data = mydata;
	
	
	$(function(){
		for(var i=0;i<mydata.length;i++)
			{
			if(mydata[i].isApprover=="y")
				{
					countApprover	=	countApprover + 1;
				}
			if(mydata[i].isApprover=="n")
				{
					countRequester	=	countRequester + 1;
				}

			
			}
		
	});
	
	
	$(function(){
		if(countApprover != 0)
			{
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
		   	       '<spring:message code="prop.leave.app.title.request.requester"/>',
		   	          '<spring:message code="prop.leave.app.title.request.action"/>'
		   	       	  //'approver'
		   	          ],
		   	colModel:[
		   		{name:'reqNo',width:90,index:'endDate'},
		   		{name:'reqDate',index:'reqDate', align:"right"},
		   		{name:'startDate',index:'startDate',  align:"right"},
		   		{name:'endDate',index:'endDate', align:"right"},
		   		{name:'type'},		
		   		{name:'status'},
		   		{name:'employee'},
		   		{name:'actions',width:200}
		   		//{name:'approver',width:200}
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#pager2',
		   	sortname: 'reqNo',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:'<spring:message code="prop.leave.app.title.request.approver.header"/>'
		});
	
		}
		
	});

	$(function(){
		if(countRequester != 0)
			{
		$('#list3').jqGrid({
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
			   	       '<spring:message code="prop.leave.app.title.request.approver"/>',
		   	          '<spring:message code="prop.leave.app.title.request.action"/>'
		   	       	  //'approver'
		   	          ],
		   	colModel:[
		   		{name:'reqNo',width:90,index:'endDate'},
		   		{name:'reqDate',index:'reqDate', align:"right"},
		   		{name:'startDate',index:'startDate',  align:"right"},
		   		{name:'endDate',index:'endDate', align:"right"},
		   		{name:'type'},		
		   		{name:'status'},
		   		{name:'employee'},
		   		{name:'actions',width:200}
		   		//{name:'approver',width:200}
		   	],
		   	rowNum:10,
		   	rowList:[10,20,30],
		   	pager: '#pager3',
		   	sortname: 'reqNo',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:'<spring:message code="prop.leave.app.title.request.requester.header"/>'
		});
		
		}
	});

	
	$(function(){
		if(countApprover != 0)
			{
				for(var i=0;i<mydata.length;i++)
				{
				if(mydata[i].isApprover=="y")
					{
					$('#list2').jqGrid('addRowData',i+1,mydata[i]);	
					}
				}
			}
		$('#backupDiv').remove();
	});


	$(function(){  
		if(countRequester != 0)
			{
				for(var i=0;i<list3Data.length;i++)
				{
				if(list3Data[i].isApprover=="n")
					{
					$('#list3').jqGrid('addRowData',i+1,list3Data[i]);	
					}
				}
			}
		});
		
	</script>

<div id="backupDiv">
<c:set value="0" var="cnt"/>
<fieldset > 
		<legend><spring:message code="javax.portlet.title"/></legend>

 <table border="1" style="border:1px solid; margin: 1em; border-collapse: collapse;">
 	<tr>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.no"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.date"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.start.date"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.end.date"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.type"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.status"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.requester"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.approver"/></th>
	 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.action"/></th>
 	</tr>
 	<c:forEach items="${leaveRequests}" var="req" >
		<portlet:renderURL var="varLeaveApprove">
			<portlet:param name="action" value="leaveApprove"/>
			<portlet:param name="reqNo" >
			<jsp:attribute name="value">
				<c:out value="${req.requestNo}"/>
			</jsp:attribute>
			</portlet:param>
		</portlet:renderURL>

		<c:choose> 
			<c:when test='${(req.employee.hierarchyCode > empHierarchy) || 
										(!req.employee.senior && (req.employee.empNumber != empNumber))}'>
			<c:set value="yellow" var="colYell"/>
			<c:set value="${cnt+1}" var="cnt"/>
			</c:when>
			<c:otherwise>
			<c:set value="white" var="colYell"/>
			</c:otherwise>
										
		</c:choose>
		
		<tr bgcolor="${colYell}">
			<td>
				<c:choose>
					<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
									(req.employee.hierarchyCode >empHierarchyAddl) ||
										(!req.employee.senior && (req.employee.empNumber != empNumber))}">
						<a href="${varLeaveApprove}"><c:out value="${req.requestNo}"/></a>
					</c:when>
					<c:otherwise>
						<c:out value="${req.requestNo}"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:out value="${req.requestDate}"/>
			</td>
			<td>
				<c:out value="${req.leaveStartDate}"/>
			</td>
			<td>
				<c:out value="${req.leaveEndDate}"/>
			</td>
			<td>
				<c:out value="${req.leaveType.typeDesc}"/>
			</td>
			<td>
				<c:out value="${req.leaveStatus}"/>
			</td>
			<td>
				<c:out value="${req.employee.empName}"/> &nbsp; (<c:out value="${req.employee.empNumber}"/>)
			</td>
			<td>
				<c:out value="${req.approve.employee.empName}"/> &nbsp; (<c:out value="${req.approve.employee.empNumber}"/>)
			</td>
			<td>
				<c:choose>
					<c:when test="${(req.employee.hierarchyCode > empHierarchy) || 
					(req.employee.hierarchyCode >empHierarchyAddl) ||
						(!req.employee.senior && (req.employee.empNumber != empNumber))}">
						<c:choose>
								<c:when test="${(req.status.statusCode == leaveStatusApproved)}">
									-
								</c:when>
							<c:otherwise>
								<c:forEach items="${adminActions}" var="admActions">
									<portlet:renderURL var="varLeaveAdminAction">
									   <portlet:param name="action" value="leaveAutoAdminAction"/>
									   <portlet:param name="reqNum" value="${req.requestNo}"/>
									   <portlet:param name="appActionNum" value="${admActions.actionCode}"/>
									</portlet:renderURL>
										<a href="${varLeaveAdminAction}"><c:out value="${admActions.actionDesc}"/></a>
								</c:forEach>
							</c:otherwise>
						</c:choose>		
					</c:when>
					<c:when test="${(req.status.statusCode == furtherClarification)}">
						<portlet:renderURL var="varLeaveClarification">
							<portlet:param name="action" value="updateLeaveApply"/>
							<portlet:param name="reqNum" value="${req.requestNo}"/>
						</portlet:renderURL>
							<a href="${varLeaveClarification}"><spring:message code="prop.leave.app.apply.action.update"/></a>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
 	</c:forEach>
 </table>
 </fieldset>
	<c:if test="${cnt!=0}">
		 <spring:message code="prop.leave.app.title.request.note"/>
 	</c:if>
 <table>
 	<tr>
 		<td></td><td></td>
 	</tr>
 </table>
</div>
</c:if>


<portlet:renderURL var="newApply">
	<portlet:param name="action" value="newApply"/>
</portlet:renderURL>

<a href="${newApply}">
	<spring:message code="prop.leave.app.apply.new"/>
</a>
