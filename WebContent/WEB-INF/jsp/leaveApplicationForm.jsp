<!--  * Project 				:	prjLeaveApp
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
<link rel="stylesheet" type="text/css" href="${urlCssSquPortletStyle}" />


<script type="text/javascript" src="${urlJsJqueryMin}"></script>
<script type="text/javascript" src="${urlJsJqueryCustom}"></script>

<c:url value="/LeaveAppEmpServlet" var="servletLeave"/>
<c:url value="/LeaveAppBranchServlet" var="servletLeaveBranch"/>
<c:url value="/LeaveAppHodServlet" var="servletLeaveHOD"/>


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
		minDate: -30,
		firstDay:6
		//inline: true
	});
});

//reference of the code from url mentioned below :
//http://stackoverflow.com/questions/2412311/multiple-drop-down-lists-with-the-same-behaviour-with-jquery
//http://codeassembly.com/Simple-chained-combobox-plugin-for-jQuery/
$(function() {
    var populateDropDowns = function() {
        var currentDropdown = $(this);
        var branchCode="${employee.branchCode}";
        var deptCode = $(this).val();
        $.ajax({
            type: "GET",
            //contentType: "application/json; charset=utf-8",
            //url:  '${servletLeave}&branchCode='+branchCode+'&deptCode='+deptCode,
            url:  "${servletLeave}",
            data: 'branchCode='+branchCode+'&deptCode='+deptCode,
            dataType: "json",
            success: function(data) {
                var options = "<option value=' '><spring:message code="prop.leave.app.dropdown.text"/></option>"; 
                for (index in data) {
                	var emp = data[index];
                	options += "<option value='" + emp.empNumber + "'>" + emp.empName + "</option>";
                }

                currentDropdown.closest('tr').find("select.selEmpNum").html(options);
            }
        }
        );
    };

    $('select.selDeptCode').each(populateDropDowns);
    $('select.selDeptCode').change(populateDropDowns);
});

/* get departments */
$(function() {
    var populateDropDowns = function() {
        var currentDropdown = $(this);
        var branchCode=$(this).val();
        var deptCode = "${employee.departmentCode}";
        $('#selSectionCode2').empty().append("<option value=''><spring:message code="prop.leave.app.dropdown.text"/></option>");
        $(".divHod").html("HOD : ");
        $.ajax({
            type: "GET",
//             contentType: "application/json; charset=utf-8",
            url:  "${servletLeaveBranch}",
            data: 'branchCode='+branchCode,
            dataType: "json",
            async:false,
            success: function(data) {
                var options = "<option value=' '><spring:message code="prop.leave.app.dropdown.text"/></option>"; 
                for (index in data) {
                	var dept = data[index];
                	var selct = '';
                	if (deptCode==dept.deptCode) selct=' selected';
                	options += "<option value='" + dept.deptCode +"'" + selct + ">" + dept.deptDesc + "</option>";
                }

                currentDropdown.closest('tr').find("select.selDeptCode2").html(options);
            }
        }
        );
    };

    $('select.selBranchCode').each(populateDropDowns);
    $('select.selBranchCode').change(populateDropDowns);
});

/* get sections */
$(function() {
    var populateDropDowns = function() {
        var currentDropdown = $(this);
        var deptCode = $(this).val();
        $.ajax({
            type: "GET",
//             contentType: "application/json; charset=utf-8",
            url:  "${servletLeaveBranch}",
            data: 'deptCode='+deptCode,
            dataType: "json",
            async:false,
            success: function(data) {
                var options = "<option value=''><spring:message code="prop.leave.app.dropdown.text"/></option>"; 
                for (index in data) {
                	var section = data[index];
                	var selct = '';
                	//if (deptCode==dept.deptCode) selct=' selected';
                	options += "<option value='" + section.sectionCode +"'" + selct + ">" + section.sectionDesc + "</option>";
                }

                currentDropdown.closest('tr').find("select.selSectionCode2").html(options);
            }
        }
        );
    };

    $('select.selDeptCode2').each(populateDropDowns);
    $('select.selDeptCode2').change(populateDropDowns);
});

// Get HOD
$(function() {
    var populateDropDowns = function() {
        //var currentDropdown = $(this);
        var branchCode=$('select.selBranchCode').val();
        var deptCode = $('select.selDeptCode2').val();
        var sectionCode=$('select.selSectionCode2').val();
        
        $.ajax({
            type: "GET",
//             contentType: "application/json; charset=utf-8",
            url:  "${servletLeaveHOD}",
            data: 'branchCode='+branchCode+'&deptCode='+deptCode+'&sectionCode='+sectionCode,
            dataType: "json",
            success: function(data) {
			var  dataHtml = '';
		    for (index in data) {
		    	 var hod = data[index];
		    	 dataHtml += "<input type='radio' name='hod'  value='"+hod.hodId+"'/> " +hod.hodName+ "<br>"; 
		    }
		    $(".divHod").html(dataHtml);
            }
        }
        );
    };
//     alert ("section code : "+$('select.selSectionCode2').val());
// 	if($('select.selSectionCode2').val()  != '')
// 		{
// 			alert ("section code2 : "+$('select.selSectionCode2').val());
		    $('select.selSectionCode2').each(populateDropDowns);
		    $('select.selSectionCode2').change(populateDropDowns);
// 		}
// 	else
// 		{
// 		alert ("dept code : "+$('select.selSectionCode2').val());
		    $('select.selDeptCode2').each(populateDropDowns);
		    $('select.selDeptCode2').change(populateDropDowns);
// 		}

		    $('select.selBranchCode').each(populateDropDowns);
		    $('select.selBranchCode').change(populateDropDowns);

});



</script>



</head>

<portlet:actionURL var="submitRequest">
	<portlet:param name="action" value="submitRequest"/>
		<portlet:param name="operation">
		<jsp:attribute name="value">
			<c:out value="${operation}"/>
		</jsp:attribute>
	</portlet:param>
</portlet:actionURL>

<portlet:renderURL var="backToMain">
	<portlet:param name="action" value="backToMain"/>
</portlet:renderURL>


<div >
	<a class="button" style="width: 100 px; " href="${backToMain}">
		<img class="imgBck"  src="${urlImgBack}" style="border-style: none; position: relative; top: 3px;"/>&nbsp;
		<fmt:message key="prop.leave.app.url.back"/>
	</a>
</div>



<form:form modelAttribute="leaveAppModel"  action="${submitRequest}" method="post" htmlEscape="false" >
	<fieldset>
		<legend>
			<h3>
				<spring:message code="prop.leave.app.apply.form.legend.title"/>
			</h3>
		</legend>
		
		<table cellspacing="0" cellpadding="0" border="1" width="100%">
			<caption><spring:message code="prop.leave.app.apply.form.required.details"/></caption>
<!-- 			<tr> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.request.no"/>: --%>
<!-- 					</span> -->
<!-- 				</th>  -->
<%-- 				<td><form:input path="requestNo"/></td> --%>
<!-- 				<td>&nbsp;</td> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.request.date"/>: --%>
<!-- 					</span> -->
<!-- 				</th> -->
<%-- 				<td><form:input path="requestDate" id="requestDate" cssClass="calendar"/></td> --%>
<!-- 			</tr> -->
			<tr>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.type"/>:
					</span>
				</th>
				<td >
					<form:select path="leaveTypeFlag">
						<form:option value="">Select</form:option>
						<form:options items="${leaveTypeFlag}" itemLabel="typeDesc" itemValue="typeNo"/>
					</form:select>
				</td>
				<td>&nbsp;</td>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.requester.office"/>
					</span>
				</th>
				<td >
					<form:select path="branch2"  cssClass="selBranchCode" id="selBranchCode" >
						<option><spring:message code="prop.leave.app.dropdown.text"/></option>
						<c:forEach items="${branches}" var="branch">
							<c:set value="" var="selct"/>
							<c:if test="${employee.branchCode==branch.branchCode}">
								<c:set value="selected" var="selct"/> 
							</c:if>
							<option value="${branch.branchCode}" ${selct}>
								<c:out value="${branch.branchDesc}"/>
							</option>
						</c:forEach>
					</form:select>
				<br>
					<form:select path="department2" cssClass="selDeptCode2" id="selDeptCode2">
						<option><spring:message code="prop.leave.app.dropdown.text"/></option>
					</form:select>
				<br>
					<form:select path="section2" cssClass="selSectionCode2" id="selSectionCode2">
						<option><spring:message code="prop.leave.app.dropdown.text"/></option>
					</form:select>
				<br>
					<div id="divHod" class="divHod"></div>
					
				</td>
			</tr>
			<tr>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.start.date"/>:
					</span>
				</th>
				<td><form:input path="leaveStartDate" id="leaveStartDate" cssClass="calendar"/></td>
				<td>&nbsp;</td>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.end.date"/>:
					</span>
				</th>
				<td><form:input path="leaveEndDate" id="leaveEndDate" cssClass="calendar"/></td>
			</tr>
			<tr>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.remarks"/>:
					</span>
				</th>
				<td colspan="4"><form:textarea cssStyle="width:100%" path="leaveRemarks"/></td>
			</tr>
		</table>
		<p/>
		<c:if test="${employee.hierarchyCode != baseHierarchyEmp}" >		
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
					<form:checkbox path="adminSqu" />
				</td>
				<td></td>
				<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.position"/>
					</span>
				</th>
				<td>
					<form:select path="positionAdditional">
						<form:option value="NA"><spring:message code="prop.leave.app.dropdown.text"/></form:option>
						<form:options items="${addlPosition}" itemLabel="desigDescription" itemValue="desigCode"/>
					
					</form:select>
				</td>			
			</tr>
		
		</table>
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
					<th colspan="2"><i><spring:message code="prop.leave.app.apply.form.delegated.msg"/></i></th>
					
					
				</tr>
				<c:forEach var="i" begin="0" end="2" step="1">
					<tr>
						<td><form:input path="delegatedEmps[${i}].fromDate" cssClass="calendar"/></td>
						<td><form:input path="delegatedEmps[${i}].toDate" cssClass="calendar"/></td>
						<td><form:input path="delegatedEmps[${i}].empNumber" /></td>
						<td>
							<form:select path="delegatedEmps[${i}].empNumber" cssClass="selEmpNum" id="selEmpNum" onchange="getEmpId(this,${i});">
								<option><spring:message code="prop.leave.app.dropdown.text"/></option>
							</form:select>
						</td>
						<td>
							<form:select path="delegatedEmps[${i}].departmentCode" cssClass="selDeptCode" id="selDeptCode">
								<option><spring:message code="prop.leave.app.dropdown.text"/></option>
								<c:forEach items="${departments}" var="dept">
									<c:set value="" var="selct"/>
									<c:if test="${employee.departmentCode==dept.deptCode}">
										<c:set value="selected" var="selct"/> 
									</c:if>
									<option value="${dept.deptCode}" ${selct}>
										<c:out value="${dept.deptDesc}"/>
									</option>
								</c:forEach>
	
							</form:select>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		
		<table cellspacing="0" cellpadding="0" border="1" width="100%">
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
		
<!-- 		<table cellspacing="0" cellpadding="0" border="1" width="100%"> -->
<%-- 			<caption> --%>
<%-- 				<spring:message code="prop.leave.app.apply.form.approvar.history.details"/> --%>
<%-- 			</caption> --%>
			
<!-- 			<tr> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.name"/> --%>
<!-- 					</span> -->
<!-- 				</th> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.date"/> --%>
<!-- 					</span> -->
<!-- 				</th> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.action"/> --%>
<!-- 					</span> -->
<!-- 				</th> -->
<!-- 				<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.approver"/> --%>
<!-- 					</span> -->
<!-- 				</th>	 -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 				<td></td> -->
<!-- 				<td></td> -->
<!-- 				<td></td>	 -->
<!-- 			</tr>			 -->
<!-- 		</table> -->
		<center>
			<input type="submit" value='<spring:message code="prop.leave.app.apply.form.requester.submit"/>' style="border-style: solid; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"/>
		</center>
<!-- 		<fieldset> -->
<!-- 			<legend> -->
<%-- 				<spring:message code="prop.leave.app.apply.form.approvar.action.administrative"/> --%>
<!-- 			</legend> -->
<!-- 			<table cellspacing="0" cellpadding="0" border="1" width="100%"> -->
<!-- 				<tr> -->
<!-- 					<th class="PortletHeaderColor"> -->
<!-- 					<span class="PortletHeaderText"> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.action"/> --%>
<!-- 					</span> -->
<!-- 					</th> -->
						
<!-- 					<td> -->
<%-- 						<spring:message code="prop.leave.app.apply.form.approvar.actions" var="appActions"/> --%>
						
<%-- 						<form:select path="approverAction"> --%>
<%-- 							<form:option value="0">Select</form:option> --%>
<%-- 							<form:options items="${adminActions}" itemLabel="actionDesc" itemValue="actionCode"/> --%>
							
<%-- 						</form:select> --%>
					
<!-- 					</td> -->
<!-- 					<td>&nbsp;</td> -->
<!-- 					<th class="PortletHeaderColor"> -->
<!-- 						<span class="PortletHeaderText"> -->
<%-- 							<spring:message code="prop.leave.app.apply.form.approvar.remarks"/> --%>
<!-- 						</span> -->
<!-- 					</th> -->
<!-- 					<td> -->
<%-- 						<form:textarea path="approverRemark" cssStyle="width:100%"/> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<%-- 			<center> --%>
<%-- 				<input type="submit" value='<spring:message code="prop.leave.app.apply.form.approver.submit"/>' style="border-style: solid; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"/> --%>
<%-- 			</center> --%>
			
			
<!-- 		</fieldset> -->
		
	</fieldset>
</form:form>

<script type="text/javascript">
	function getEmpId(obj,i)
	{
		document.getElementById('delegatedEmps'+i+'.empNumber').value=obj.value;
	}
	
</script>