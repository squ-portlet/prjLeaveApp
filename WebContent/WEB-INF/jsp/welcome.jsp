<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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


<%@include file="ui/cssWelcome.jsp" %>

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
	.ui-widget-content { border: 1px solid rgb(236, 193, 193); background: #ffffff url("${urlImgBgFlat75}") 50% 50% repeat-x; color: #333333; }
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
	
	 /* .myDialogClass .ui-widget-header {background: purple;} */
	
/* 	.ui-state-hover .ui-icon, .ui-state-focus .ui-icon {background-image: url("${urlImgBgFlat}");} */

/* 	.ui-widget-header { */
/*     background: url("${urlImgBgDots}") repeat-x scroll 50% 50% #CC0000; */
/*     border: 1px solid #E3A1A1; */
/*     color: #FFFFFF; */
/*     font-weight: bold; */
}

		.dialog-title{
		
		background-color: #ffe7cf;
		color:#f48000;
		}

</style>

<portlet:renderURL var="newApply">
	<portlet:param name="action" value="newApply"/>
</portlet:renderURL>

<c:if test="${rc.locale.language=='en'}">
	<c:set var="direction" value="left"/>
</c:if>
<c:if test="${rc.locale.language=='ar'}">
	<c:set var="direction" value="right"/>
</c:if>

	<div id="browserCompAdv" style="display:none;">
		<center>
				<div class="alert alert-warning" role="alert">
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					<spring:message code="prop.leave.app.title.browser.compatibility.text"/>
				</div>
		</center>
	</div>
<%-- <c:catch var="e"> --%>


		<c:if test="${not empty allowELeaveRequestMsg}">
		<div class="alert alert-warning" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<c:out value="${allowELeaveRequestMsg}"/></div>
		</c:if>
		
		
			<script type="text/javascript">
			
			/*Dialog for approve request*/
			$(function() { 
			    $(".refApproveClass").click(function(event) {
			    	 								var linkRef     	= this.getAttribute("linkRef");
			    	 								var varReqNo 		= this.getAttribute("reqNo");
			    	 								var	varAppActionNum = this.getAttribute("appActionNum");
			    	 								var msg	     		= '<spring:message code="prop.leave.app.title.request.dialog.approve.msg" arguments="varReqNo"/>';
			    	 								var msgReplace		=	msg.replace("varReqNo",varReqNo);
			    	 								$("#dialogApprove").html(msgReplace);
			    	 								event.preventDefault();
			    	 								 $("#dialogApprove").closest('.ui-dialog').children('.ui-dialog-titlebar').addClass("dialog-title");
			    	 								$("#dialogApprove").dialog(
			                                        {
			                                        	resizable: false,
			                                        	width:400,
			                                        	height:200,
			                                        	modal: true,
			                            				close: function(event, ui) {
			                            					$("#dialogApprove").hide();
			                            					return false;
			                            					},
			                          					buttons: {
			                          						"<spring:message code='prop.leave.app.title.request.dialog.approve.button.yes'/>": function() {
			                          							$("#requestNo").val(varReqNo);
			                          							$("#approverAction").val(varAppActionNum);
							    	 							$("#leaveAppModel").attr("action", linkRef);
							    	 							$("#leaveAppModel").submit();
			                          						$( this ).dialog( "close" );
			                          						},
			                          						"<spring:message code='prop.leave.app.title.request.dialog.approve.button.exit'/>": function() {
			                          							$("#requestNo").val("");
			                          							$("#approverAction").val("");
			                          							$("#leaveAppModel").attr("action", "");
			                          							$( this ).dialog( "close" );
			                          						}
			                          						}
			                                        }		
			                                        ); 
			    	                              
			                                        return false; 
			                                        
			                                        });
			}); 

			
			/*Dialog for cancel request*/
			$(function() { 
			    $(".mydialogCls").click(function(event) {
		    	
			    	 								var i     		= this.getAttribute("indexRef");
			    	 								var varReqNo 	= this.getAttribute("reqNo");
			    	 								var	varAppEmpNo = this.getAttribute("appEmpNo");
			    	 								var msg	     = '<spring:message code="prop.leave.app.title.request.cancel.msg" arguments="varReqNo"/>';
			    	 								var msgReplace	=	msg.replace("varReqNo",varReqNo);
			    	 								$("#myDialog").html(msgReplace);
			    	 								event.preventDefault();
			    	 								 $("#myDialog").closest('.ui-dialog').children('.ui-dialog-titlebar').addClass("dialog-title");
			    	 								$("#myDialog").dialog(
			                                        {
			                                        	resizable: false,
			                                        	width:400,
			                                        	height:200,
			                                        	modal: true,
			                            				close: function(event, ui) {
			                            					$("#myDialog").hide();
			                            					return false;
			                            					},
			                          					buttons: {
			                          						"<spring:message code='prop.leave.app.title.request.cancel.button.remove'/>": function() {
			                          						$("#requestNo").val(varReqNo);
			                          						$("#approverEmpNumber").val(varAppEmpNo);
						    	 							$("#leaveAppModel").attr("action", i);
						    	 							$("#leaveAppModel").submit();
			                          						$( this ).dialog( "close" );
			                          						},
			                          						"<spring:message code='prop.leave.app.title.request.cancel.button.exit'/>": function() {
			                          							$("#requestNo").val("");
			                          							$("#approverEmpNumber").val("");
			                          							$("#leaveAppModel").attr("action", "");
			                          							$( this ).dialog( "close" );
			                          						}
			                          						}
			                                        }		
			                                        ); 
			    	                               
			                                        return false; 
			    	                                    
			                                        });
			    
			}); 
			
			</script>
		
		
			<div id="myDialog" class="myDialogClass" title='<spring:message code="prop.leave.app.title.request.cancel.title"/>' style="display:none;"></div>  
			<div id="dialogApprove" class="dialogApproveClass" title='<spring:message code="prop.leave.app.title.request.dialog.approve"/>' style="display:none;"></div>	
			<div id="dialogQuirk" class="dialogApproveClass" title='<spring:message code="error.prop.leave.app.quirk.mode.title"/>' style="display:none;"></div>

			<c:set var="appCount" value="0"/>
			<c:set var="reqCount" value="0"/>
			
			
				<c:if test="${not empty leaveRequestsApprover }">
						<c:set var="isApprover" value="y"/>
						<c:set var="appCount" value="${(appCount) + 1}"/>
				</c:if>

				
				<c:if test="${not empty  leaveRequests}">
						<c:set var="isApprover" value="n"/>
						<c:set var="reqCount" value="${reqCount+1}"/>
				</c:if>

				
			

<p>
		<br></br>		
		<a class='btn btn-primary' href="${newApply}">
			<spring:message code="prop.leave.app.apply.new"/>
		</a>
</p>
			<form:form modelAttribute="leaveAppModel" method="post" htmlEscape="false">
				<form:hidden 	path="requestNo" />
				<form:hidden	path="approverEmpNumber"/>
				<form:hidden	path="approverAction"/>
			</form:form>

<!-- 
		Requester table
 -->
	<c:if test="${reqCount != 0}">
		<fieldset >
			<legend><spring:message code="prop.leave.app.title.request.requester.header"/> (<c:out value="${empName}"/>)</legend>
			<center>
			<table border="1" style="border:1px solid;  border-collapse: collapse; " width="100%">
				<tr>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.no"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.start.date"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.end.date"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.type"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.status"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.approver"/></th>
						 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.action"/></th>
				</tr>
				<c:forEach items="${leaveRequests}" var="req" >
				
					<c:choose>
						<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber))}">
							<c:set var="isApprover" value="y"/>
						</c:when>
						<c:otherwise>
							<c:set var="isApprover" value="n"/>
						</c:otherwise>
					</c:choose>
				
					<c:if test='${isApprover == "n"}'>
					<portlet:renderURL var="varLeaveApprove">
						<portlet:param name="action" value="leaveApprove"/>
						<portlet:param name="reqNo" >
						<jsp:attribute name="value">
							<c:out value="${req.requestNo}"/>
						</jsp:attribute>
						</portlet:param>
					</portlet:renderURL>
	
					<portlet:renderURL var="varLeaveClarification">
						<portlet:param name="action" value="updateLeaveApply"/>
						<portlet:param name="reqNum" value="${req.requestNo}"/>
						<portlet:param name="appEmpNo" value="${req.approve.employee.empNumber}"/>
					</portlet:renderURL>
					<portlet:actionURL var="varLeaveCancel">
						<portlet:param name="action" value="leaveCancel"/>
					</portlet:actionURL>
					<portlet:renderURL var="urlLeaveReturn">
						<portlet:param name="action" value="leaveReturn" />
						<portlet:param name="requestNo" value="${req.requestNo}"/>
						<portlet:param name="approverEmpNo" value="${req.approve.employee.empNumber}"/>
					</portlet:renderURL>
				<tr>
						<td class="clsNum">
							<c:choose>
								<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber) && (req.sabbaticalLowerApproverAction && ! ((req.status.statusCode == leaveStatusApproved) || (req.status.statusCode == leaveStatusRejected))))}">
									<a href="${varLeaveApprove}"><font color="red"><c:out value="${req.requestNo}"/></font></a>
								</c:when>
								<c:otherwise>
									<c:out value="${req.requestNo}"/> - <b><c:out value="${req.leaveReturnIndicator}"/></b> &nbsp;
								</c:otherwise>
							</c:choose>
						</td>
						<td class="clsDate">
							<c:out value="${req.leaveStartDate}"/>
						</td>
						<td class="clsDate">
							<c:out value="${req.leaveEndDate}"/>
						</td>
						<td>
							<c:out value="${req.leaveType.typeDesc}"/>
						</td>
						<td>
							<c:choose>
								<c:when test="${(req.status.statusCode eq leaveStatusRejected) && (not empty req.returnApprove.employee.empNumber) }">
									<font color="red"><b>${req.leaveStatus}</b></font>
								</c:when>
								<c:otherwise>
									<c:out value="${req.leaveStatus}"/> 
								</c:otherwise>
								
							</c:choose>
							
						</td>
						<td>
							<c:out value="${req.approve.employee.empName}"/>
						</td>
						<td>
						
						
								<portlet:renderURL var="varLeaveView">
									<portlet:param name="action" value="leaveView"/>
									<portlet:param name="reqNo" value="${req.requestNo}"/>
									<portlet:param name="appEmpNo" value="${req.approve.employee.empNumber}"/>
								</portlet:renderURL>
								<c:choose>
									<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber))}">
										<c:choose>
											<c:when test="${(req.status.statusCode == leaveStatusApproved || 
													         req.status.statusCode == leaveStatusRejected) ||
													         (req.status.statusCode == furtherClarification)}">
												<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
											</c:when>
											<c:when test="${!(req.sabbaticalLowerApproverAction)}">
												<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
											</c:when>
											<c:otherwise>
													 	<c:forEach items="${adminActions}" var="admActions">
														<portlet:actionURL var="varLeaveAdminAction">
															<portlet:param name="action" value="leaveAutoAdminAction"/>
														</portlet:actionURL>
	
														<portlet:renderURL var="varLeaveApprove2">
														<portlet:param name="action" value="leaveApprove2"/>
														<portlet:param name="reqNo" value="${req.requestNo}"/>
														<portlet:param name="_approverAction" value="${admActions.actionCode}"/>
														</portlet:renderURL>
	
														<c:if test="${(admActions.actionCode == leaveActionApprove)}">
																|&nbsp;&nbsp;<a class="refApproveClass" reqNo="${req.requestNo}" appActionNum="${admActions.actionCode}" linkRef="${varLeaveAdminAction}"  href="#"><font color="red"><c:out value="${admActions.actionDesc}"/></font></a>&nbsp;&nbsp;|		
														</c:if>
															<c:if test="${(admActions.actionCode == leaveActionReturn) || (admActions.actionCode == leaveActionReject)}">
																&nbsp;&nbsp;<a href="${varLeaveApprove2}"><font color="red"><c:out value="${admActions.actionDesc}"/></font></a>&nbsp;&nbsp;|		
															</c:if>
														</c:forEach>
														&nbsp;&nbsp;<a href="${varLeaveApprove}"><font color="red"><spring:message code="prop.leave.app.title.request.actions" /></font></a>&nbsp;&nbsp;|
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${(req.status.statusCode == furtherClarification)}">
										<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a> | <a href="${varLeaveClarification}"><spring:message code="prop.leave.app.apply.action.update"/></a>
									</c:when>
									<c:when test="${(req.status.statusCode == leaveStatusApproved) || (req.status.statusCode == leaveStatusRejected)}">
										<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
										<c:if test="${(req.status.statusCode eq leaveStatusRejected) && (not empty req.returnApprove.employee.empNumber) && (req.finalStatusCode != leaveStatusApproved) }">
											| <a href="${urlLeaveReturn}"><spring:message code="prop.leave.app.return.link.text.color.red"/></a> 
										</c:if> 
									</c:when>
									<c:when test="${(req.approverSequenceNo != 1)}">
										<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
									</c:when>
									<c:when test="${ (not empty req.returnApprove.employee.empNumber) && (req.status.statusCode == waitingApproval)}">
										<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a> | (<spring:message code="prop.leave.app.return.link.text"/>)
									</c:when>
									<c:otherwise>
										<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a> | <a href="${varLeaveClarification}"><spring:message code="prop.leave.app.title.request.update"/></a> | <a href="#" class="mydialogCls" reqNo="${req.requestNo}" appEmpNo="${req.approve.employee.empNumber}" indexRef="${varLeaveCancel}"><spring:message code="prop.leave.app.title.request.cancel"/></a>
									</c:otherwise>
								</c:choose>
								<c:if test="${req.leaveReturn && (empty req.returnApprove.employee.empNumber)}">
									 | <a href="${urlLeaveReturn}"><spring:message code="prop.leave.app.return.link.text"/></a>
								</c:if>
								
								
								
						</td>
				</tr>
				</c:if>
				</c:forEach>
				
			</table>
		</center>
		</fieldset>
	</c:if>

<!-- 
		Approver table
 -->	
<c:if test="${appCount != 0}">
	<fieldset >
		<legend><spring:message code="prop.leave.app.title.request.approver.header"/></legend>
		<table border="1" style="border:1px solid;  border-collapse: collapse;" width="100%">
			<tr>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.no"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.start.date"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.end.date"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.leave.type"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.status"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.requester"/></th>
					 	<th  class="PortletHeaderColor PortletHeaderText"><spring:message code="prop.leave.app.title.request.action"/></th>
			</tr>
			<c:forEach items="${leaveRequestsApprover}" var="req" >
			
				<c:choose>
					<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber))}">
						<c:set var="isApprover" value="y"/>
					</c:when>
					<c:otherwise>
						<c:set var="isApprover" value="n"/>
					</c:otherwise>
				</c:choose>
			
				<c:if test='${isApprover == "y"}'>
				<portlet:renderURL var="varLeaveApprove">
					<portlet:param name="action" value="leaveApprove"/>
					<portlet:param name="reqNo" >
					<jsp:attribute name="value">
						<c:out value="${req.requestNo}"/>
					</jsp:attribute>
					</portlet:param>
				</portlet:renderURL>

				<portlet:renderURL var="varLeaveClarification">
					<portlet:param name="action" value="updateLeaveApply"/>
					<portlet:param name="reqNum" value="${req.requestNo}"/>
					<portlet:param name="appEmpNo" value="${req.approve.employee.empNumber}"/>
				</portlet:renderURL>
				<portlet:actionURL var="varLeaveCancel">
					<portlet:param name="action" value="leaveCancel"/>
				</portlet:actionURL>
			<tr>
					<td class="clsNum">
						<c:choose>
							<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber) && (req.sabbaticalLowerApproverAction && ! ((req.status.statusCode == leaveStatusApproved) || (req.status.statusCode == leaveStatusRejected))))}">
								<a href="${varLeaveApprove}"><font color="red"><c:out value="${req.requestNo}"/></font></a>
							</c:when>
							<c:otherwise>
								<c:out value="${req.requestNo}"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="clsDate">
						<c:out value="${req.leaveStartDate}"/>
					</td>
					<td class="clsDate">
						<c:out value="${req.leaveEndDate}"/>
					</td>
					<td>
						<c:out value="${req.leaveType.typeDesc}"/>
					</td>
					<td>
						<c:out value="${req.leaveStatus}"/>
						<c:if test="${empty  req.leaveStatus}">
							<spring:message code="prop.leave.app.apply"/>
						</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber))}">
								<c:out value="${req.employee.empName}"/>
							</c:when>
							<c:otherwise>
								<c:out value="${req.approve.employee.empName}"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
							<portlet:renderURL var="varLeaveView">
								<portlet:param name="action" value="leaveView"/>
								<portlet:param name="reqNo" value="${req.requestNo}"/>
								<portlet:param name="appEmpNo" value="${req.approve.employee.empNumber}"/>
							</portlet:renderURL>
							<c:choose>
								<c:when test="${(req.employee.senior && (req.employee.empNumber != empNumber))}">
									<c:choose>
										<c:when test="${(req.status.statusCode == leaveStatusApproved || 
												         req.status.statusCode == leaveStatusRejected) ||
												         (req.status.statusCode == furtherClarification)}">
											<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
										</c:when>
										<c:when test="${!(req.sabbaticalLowerApproverAction)}">
											<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
										</c:when>
										<c:otherwise>
												 	<c:forEach items="${adminActions}" var="admActions">
													<portlet:actionURL var="varLeaveAdminAction">
													<portlet:param name="action" value="leaveAutoAdminAction"/>
<%-- 													<portlet:param name="reqNum" value="${req.requestNo}"/> --%>
<%-- 													<portlet:param name="appActionNum" value="${admActions.actionCode}"/> --%>
													</portlet:actionURL>

													<portlet:renderURL var="varLeaveApprove2">
													<portlet:param name="action" value="leaveApprove2"/>
													<portlet:param name="reqNo" value="${req.requestNo}"/>
													<portlet:param name="_approverAction" value="${admActions.actionCode}"/>
													</portlet:renderURL>

													<c:if test="${(admActions.actionCode == leaveActionApprove)}">
															|&nbsp;&nbsp;<a class="refApproveClass" reqNo="${req.requestNo}" linkRef="${varLeaveAdminAction}" appActionNum="${admActions.actionCode}"  href="#"><font color="red"><c:out value="${admActions.actionDesc}"/></font></a>&nbsp;&nbsp;|		
														</c:if>
														<c:if test="${(admActions.actionCode == leaveActionReturn) || (admActions.actionCode == leaveActionReject)}">
															&nbsp;&nbsp;<a href="${varLeaveApprove2}"><font color="red"><c:out value="${admActions.actionDesc}"/></font></a>&nbsp;&nbsp;|
														</c:if>
													</c:forEach>
													&nbsp;&nbsp;<a href="${varLeaveApprove}"><font color="red"><spring:message code="prop.leave.app.title.request.actions" /></font></a>&nbsp;&nbsp;|
													
												<c:if test="${not empty req.returnApprove.employee.empNumber}">
													(<spring:message code="prop.leave.app.return.link.text"/>)
												</c:if>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${(req.status.statusCode == furtherClarification)}">
									<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a> | <a href="${varLeaveClarification}"><spring:message code="prop.leave.app.apply.action.update"/></a>
								</c:when>
								<c:when test="${(req.status.statusCode == leaveStatusApproved) || (req.status.statusCode == leaveStatusRejected)}">
									<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
								</c:when>
								<c:when test="${(req.approverSequenceNo != 1)}">
									<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a>
								</c:when>
								<c:otherwise>
									<a href="${varLeaveView}"><spring:message code="prop.leave.app.title.request.view"/></a> | <a href="${varLeaveClarification}"><spring:message code="prop.leave.app.title.request.update"/></a> | <a href="#" class="mydialogCls" reqNo="${req.requestNo}" appEmpNo="${req.approve.employee.empNumber}" indexRef="${varLeaveCancel}"><spring:message code="prop.leave.app.title.request.cancel"/></a>
								</c:otherwise>
							</c:choose>					
					</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
	</fieldset>
</c:if>



<p>
		<br></br>		
		<a class='btn btn-primary' href="${newApply}">
			<spring:message code="prop.leave.app.apply.new"/>
		</a>
</p>

	 
