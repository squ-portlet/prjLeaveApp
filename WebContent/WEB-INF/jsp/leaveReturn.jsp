<!--  
 * Project 				:	prjLeaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	leaveReturn.jsp
 * 
 * Date of Creation		:	27-July-2016
 *  
 * Summary				:	Leave Application Form
 *
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

-->

<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/css/images/backIcon.png" var="urlImgBack"/>


<%@include file="ui/cssWelcome.jsp" %>


<head>

<spring:url value="/css/images/backIcon.png" var="urlImgBack"/>
<c:url value="/css/images/calendar.png" var="urlImgCalendar"/>
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

<c:url value="/css/images/ui-anim_basic_16x16.gif" var="urlImgAnimAuto"/>
<%-- <spring:url value="/css/images/dk_arrows.png" var="urlImgDkArrow"/> --%>





<c:url value="/LeaveAppEmpServlet" var="servletLeave"/>
<c:url value="/LeaveAppBranchServlet" var="servletLeaveBranch"/>
<c:url value="/LeaveAppHodServlet" var="servletLeaveHOD"/>
<c:url value="/LeaveAppResearch" var="servletResearch" />
<c:url value="/LeaveAppBalanceLeave" var="servletLeaveBal" />

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
	<c:set var="direction" value="right"/>
	<c:set var="directionOpp" value="left"/>
</c:if>
<c:if test="${rc.locale.language=='en'}">
	<c:set var="direction" value="left"/>
	<c:set var="directionOpp" value="right"/>
</c:if>

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
/*
	.ui-widget-content { border: 1px solid #eeeeee; background: #ffffff url("${urlImgBgFlat75}") 50% 50% repeat-x; color: #333333; }
*/
	.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight  {border: 1px solid #fcd3a1; background: #fbf8ee url("${urlImgBgGlass55}") 50% 50% repeat-x; color: #444444; }
	.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #d8dcdf; background: #eeeeee url("${urlImgBgHighLightHard1}") 50% 50% repeat-x; font-weight: bold; color: #004276; }
	.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus { border: 1px solid #cdd5da; background: #f6f6f6 url("${urlImgBgHighLightHard2}") 50% 50% repeat-x; font-weight: bold; color: #111111; }
	
	.ui-widget-header { border: 1px solid #e3a1a1; background: #cc0000 url("${urlImgBgHighLightSoft1}") 50% 50% repeat-x; color: #ffffff; font-weight: bold; }
/* 	.ui-widget-header { border: 1px solid #e3a1a1; background: #cc0000 url("${urlImgBgHighLightSoft1}") 50% 50% repeat-x; color: #ffffff; font-weight: bold; } */
	.ui-state-highlight .ui-icon {background-image: url("${urlImgIcons1}"); }
	
	
	
	
	.ui-icon { width: 16px; height: 16px; background-image: url("${urlImgIcons2}"); }
	/*
	.ui-widget-content .ui-icon {background-image: url("${urlImgIcons2}"); }
	*/
	
	.ui-widget-header .ui-icon {background-image: url("${urlImgIcons3}"); }
	
	.ui-state-default .ui-icon { background-image: url("${urlImgIcons2}"); }
	.ui-state-hover .ui-icon, .ui-state-focus .ui-icon {background-image: url("${urlImgIcons2}"); }
	.ui-state-active .ui-icon {background-image: url("${urlImgIcons2}"); }
	.ui-state-error .ui-icon, .ui-state-error-text .ui-icon {background-image: url("${urlImgIcons2}"); }
	/*
	.ui-widget-content { border: 1px solid #aaaaaa/*{borderColorContent}*/; background: #ffffff/*{bgColorContent}*/ url("${urlImgBgFlat75}")/*{bgImgUrlContent}*/ 50%/*{bgContentXPos}*/ 50%/*{bgContentYPos}*/ repeat-x/*{bgContentRepeat}*/; color: #222222/*{fcContent}*/; }
	*/
	/*.dk_toggle {background-image: url("${urlImgDkArrow}");}*/
	/*Ajax autocomplete list*/
	.ui-menu-item {font-size: small;}
	.ui-menu .ui-menu-item {font-size: small;}
	.ui-autocomplete-loading { background: white url("${urlImgAnimAuto}") ${directionOpp} center no-repeat; }
	/*.ui-autocomplete {width: 70%; font-size: small;}*/
	/*autocomplete alternet color*/
	.ui-menu-item-alternate { background: rgb(231, 217, 217); }
	.ui-corner-all {font-size: small;}
	.ui-datepicker { width: 16em; padding: .2em .2em 0; display: none; }
	

	
</style>
<portlet:resourceURL id="getDepartmentsByAjax" var="urlDepartmentsByAjax">
</portlet:resourceURL>


<script>
var resDtIssueOK	=	true;
var varLeaveBal		=	null;




$(function(){
	// Datepicker
	$('.calendarReturn').datepicker({
		dateFormat:"dd/mm/yy",
		defaultDate: new Date(),
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		onClose: function( selectedDate ) {
			validateReturnDate();
		}
		
	});
	
	validateReturnDate();
});


	function validateReturnDate()
	{
		
		var addDay		=1;
		var	fromDtStr	= "${leaveRequest.leaveStartDate}";
		var	toDtStr		= "${leaveRequest.leaveEndDate}";
		
		
		var fromDtArr	= fromDtStr.split("/");
		var toDtArr		= toDtStr.split("/");
		
		var fromDate	= new Date(fromDtArr[2], fromDtArr[1] - 1, fromDtArr[0]);
		var toDate		= new Date(toDtArr[2], toDtArr[1] - 1, toDtArr[0]);
		
		var returnDate = $(".calendarReturn").datepicker("getDate");
		
		if((returnDate <= fromDate) || (returnDate <= toDate))
		{
			$("#divLeaveReturnMsg").html('&nbsp;<font color="red"><spring:message code="error.prop.leave.return.invalid.date"/></font> &nbsp;');
			$(".calendarReturn").datepicker( "setDate", null );
		}
		else
		{
			$("#divLeaveReturnMsg").html("");
		}
		
		if(toDate.getDay() == 4)
		{
			addDay = 3;
		}
		else
		{
			addDay = 1;
		}
		

		if(returnDate > toDate.setDate((toDate.getDate()+addDay)))
		{
			  $("#browserCompAdv").html('&nbsp;<spring:message code="warn.prop.leave.return.delay"/> &nbsp;');
			  $("#browserCompAdv").show();
		}
		else
		{
				$("#browserCompAdv").html('');
			  	$("#browserCompAdv").hide();
		}
		
	}

//reference of the code from url mentioned below :
//http://stackoverflow.com/questions/2412311/multiple-drop-down-lists-with-the-same-behaviour-with-jquery
//http://codeassembly.com/Simple-chained-combobox-plugin-for-jQuery/
$(function() {
    var populateDropDowns = function() {
        var currentDropdown = $(this);
        var branchCode=$(this).val();
        var empNumber = "${empNumber}";
        $('txtEmpNum').val='';
		var varEmpId=currentDropdown.closest('tr').find("input.tEmpId");
        
        currentDropdown.closest('tr').find("select.selEmpNum").empty().append('<option value=""><spring:message code="prop.leave.app.dropdown.employee.text"/></option>');
        $.ajax({
            type: "GET",
            //contentType: "application/json; charset=utf-8",
            //url:  '${servletLeave}&branchCode='+branchCode+'&deptCode='+deptCode,
            url:  "${servletLeave}",
            data: 'branchCode='+branchCode+'&empNumber='+empNumber+'&localeSrv='+"${rc.locale.language}",
            dataType: "json",
            success: function(data) {
				//tEmpId
				var selct = '';
            	currentDropdown.closest('tr').find("select.selEmpNum").empty().append('<option value=""><spring:message code="prop.leave.app.dropdown.employee.text"/></option>');
            	
            	if(data.length==1)
            	{
            		var empData=data[0];
            		
            		$(empData).each(function(i,item){
            			var opt=$('<option></option>');
            			$(opt).val(item.empNumber);
            			$(opt).html(item.empName);
            			currentDropdown.closest('tr').find("select.selEmpNum").append(opt);
            		});
            		
            		currentDropdown.closest('tr').find("select.selEmpNum").val(varEmpId.val());
            	}
            	else if(data.length>1) 
            	{
            		var sameDept=data[0];
            		var otherDept=data[1];
            		
            		var optGroup=$('<optgroup></optgroup>');

        			$(optGroup).attr('label','<spring:message code="prop.leave.app.dropdown.delg.emp.group1"/>');
            		$(sameDept).each(function(i,item){          			
            			var opt=$('<option></option>');
            			$(opt).val(item.empNumber);
            			$(opt).html(item.empName);
            			$(optGroup).append(opt);
            		});            		
            		currentDropdown.closest('tr').find("select.selEmpNum").append(optGroup);
            		
            		var optGroup=$('<optgroup></optgroup>');
        			$(optGroup).attr('label','<spring:message code="prop.leave.app.dropdown.delg.emp.group2"/>');
        			
            		$(otherDept).each(function(i,item){          			
            			var opt=$('<option></option>');
            			$(opt).val(item.empNumber);
            			$(opt).html(item.empName);
            			$(optGroup).append(opt);
            		});            		
            		//$('#selEmpNum').append(optGroup);
            		currentDropdown.closest('tr').find("select.selEmpNum").append(optGroup);
            	}
            	else
            	{
            		
            	}
            

            }
        }
        );
    };

    $('select.selBranchCode').each(populateDropDowns);
    $('select.selBranchCode').change(populateDropDowns);
});

/* get departments */
$(function() {
    var populateDropDowns = function() {
        var currentDropdown = $(this);
        var branchCode=$(this).val();
        var deptCode = "${employee.departmentCode}";
        <c:if test="${opMode == 'u'}">
        	var deptCode = "${approver.departmentCode}";
        </c:if>
        
        $('#selSectionCode2').empty().append("<option value=''><spring:message code="prop.leave.app.dropdown.department.text"/></option>");
        $(".divHod").html("HOD : ");
        $.ajax({
            type: "GET",
//             contentType: "application/json; charset=utf-8",
            url:  "${servletLeaveBranch}",
            data: 'branchCode='+branchCode+'&localeSrv='+"${rc.locale.language}",
            dataType: "json",
            async:false,
            success: function(data) {
                var options = "<option value=' '><spring:message code="prop.leave.app.dropdown.department.text"/></option>"; 
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
            data: 'deptCode='+deptCode+'&localeSrv='+"${rc.locale.language}",
            dataType: "json",
            async:false,
            success: function(data) {
                var options = "<option value=''><spring:message code="prop.leave.app.dropdown.section.text"/></option>"; 
                for (index in data) {
                	var section = data[index];
                	var selct = '';
                	//if (deptCode==dept.deptCode) selct=' selected';
                	options += "<option value='" + section.sectionCode +"'" + selct + ">" + section.sectionDesc + "</option>";
                }
                
				currentDropdown.closest('tr').find("select.selSectionCode2").html(options);
				
				if($('#selSectionCode2').find('option').length <= 1)
					{
						$('#sectionDiv').hide();
					}
				else
					{
					$('#sectionDiv').show();
					}
				
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
        var branchCode=$('select.selBranchCode').val();
        var deptCode = $('select.selDeptCode2').val();
        var sectionCode=$('select.selSectionCode2').val();
        var hierarchyLevelCode="${employee.hierarchyLevelCode}";
        var hodId="${leaveAppModel.hod}";
        $.ajax({
            type: "GET",
//             contentType: "application/json; charset=utf-8", hierarchyLevelCode
            url:  "${servletLeaveHOD}",
            data: 'branchCode='+branchCode+'&deptCode='+deptCode+'&sectionCode='+sectionCode+'&hierarchyLevelCode='+hierarchyLevelCode+'&localeSrv='+"${rc.locale.language}",
            dataType: "json",
            success: function(data) {
			var  dataHtml = '';
			
		    for (index in data) {
		    	 var hod = data[index];
		    	 var selct = '';
		    	 
		    	 if (hod.hodId==hodId) selct=' checked ';
			    	 dataHtml += "<tr><td><input type='radio' name='hod' id='radio"+hod.hodId+"' value='"+hod.hodId+"'"+ selct + "/> <a href='#' class='rdhlink' rdid='"+hod.hodId+"'>" +hod.hodName+ "</a></td></tr>"; 
		    }
		    
		    if(data != null || data != '')
		    	{
		    		$(".divHod").html(dataHtml);
		    		rdLink();
		    		$("#approverDiv").show();
		    		
		    	}
		    else
		    	{
		    		$("#approverDiv").hide();
		    	}
            }
        }
        );
    };

		    $('select.selSectionCode2').each(populateDropDowns);
		    $('select.selSectionCode2').change(populateDropDowns);

		    $('select.selDeptCode2').each(populateDropDowns);
		    $('select.selDeptCode2').change(populateDropDowns);

		    $('select.selBranchCode').each(populateDropDowns);
		    $('select.selBranchCode').change(populateDropDowns);

});



/**
 * Make the HOD hyperlink clickable to select the radio button
 */
function rdLink(){
    $(".rdhlink").click(function(event) {
    				var rdId	=	this.getAttribute("rdid");
    					$("#radio"+rdId).prop("checked", true)
                         return false; 
                         });
	}


$(function() {
	$( "#leaveAppModel" ).validate({
		
		rules: {
			"leaveReturnDate":{
	 			required: true
	 		}
		},
		errorPlacement: function(error, element) {
			  error.insertAfter(element);
	  	},
	  	 messages: {
	  		leaveReturnDate : '<spring:message code="error.prop.leave.return.empty.date"/>'
	  	 }
	});
});


	/*
	$(function(){
		$("#department2")change(function(){
			$.ajax({
				url: "${urlDepartmentsByAjax}",
				type: 'GET',
				data : {
					branchCode : $("#branch2").val
				},
				success: function(data){
					 for (index in data) {
						 var dept = data[index];
						 
					 }
				}
				
			});
		});
		
	});
	*/
	
</script>

</head>




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
	<c:set var="direction" value="right"/>
	<c:set var="directionOpp" value="left"/>
</c:if>
<c:if test="${rc.locale.language=='en'}">
	<c:set var="direction" value="left"/>
	<c:set var="directionOpp" value="right"/>
</c:if>


<portlet:renderURL var="backToMain">
	<portlet:param name="action" value="backToMain"/>
</portlet:renderURL>

<div >
	<a class="button btn btn-primary" style="width: 100 px; " href="${backToMain}">
		<img class="imgBck"  src="${urlImgBack}" style="border-style: none; position: relative; top: 3px;"/>&nbsp;
		<fmt:message key="prop.leave.app.url.back"/>
	</a>
</div>
<!-- *************************************************** -->

 
<portlet:actionURL var="submitRequest">
	<portlet:param name="action" value="leaveReturn"/>
</portlet:actionURL>

<c:choose>
	<c:when test="${request.returnDelay}">
		<c:set value="" var="varHtmlDisplayReturnWarn"></c:set>
	</c:when>
	<c:otherwise>
		<c:set value="style='display:none;'" var="varHtmlDisplayReturnWarn"></c:set>
	</c:otherwise>
</c:choose>


<div id="dialogDate" class="dialogApproveClass" title='<spring:message code="error.prop.leave.app.warning.dialogue.title"/>' style="display:none;"></div>

<form:form modelAttribute="leaveAppModel"  action="${submitRequest}" method="post" htmlEscape="false" >
		<center>
				<div id="browserCompAdv" class="alert alert-warning" role="alert"  ${varHtmlDisplayReturnWarn}>
					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<c:if test="${request.returnDelay}">
							<spring:message code="warn.prop.leave.return.delay"/>
						</c:if>
						<center>
							<div class="alert alert-warning" role="alert">
								<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
								<form:errors path="acceptLeave" cssClass="error" />
							</div>
						</center>
				</div>
		</center>
<form:hidden path="approverEmpNumber" />
<form:hidden path="requestNo" />
	<center><form:errors path="*"  cssClass="alert alert-danger" role="alert"/></center>
	<fieldset>
		<legend>
			<h3>
				<spring:message code="prop.leave.app.return.form.legend.title"/>
			</h3>
		</legend>
			<table width=100%>
				<tr>
					<td><spring:message code="prop.leave.app.apply.form.request.no"/>:</td>
					<td><c:out value="${leaveRequest.requestNo}"/></td>
				</tr>
				<tr>
					<td>
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.type"/>:
						</span>
					</td>
					<td>
						<c:out value="${leaveRequest.leaveType.typeDesc}"/>
					</td>
				</tr>

				<tr>
					<td>
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.start.date"/>:
						</span>
					</td>
					<td>
						<c:out value="${leaveRequest.leaveStartDate}"/>
					</td>
	
				</tr>
				<tr>
					<td>
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.end.date"/>:
						</span>
					</td>
					<td>
						<c:out value="${leaveRequest.leaveEndDate}"/>
					</td>
				</tr>
				<tr>
					<td>
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.remarks"/>:
					</span>
					</td>
					<td colspan="4">
							<c:choose>
								<c:when test="${not empty leaveRequest.leaveRequestRemarks}">
									<c:out value="${leaveRequest.leaveRequestRemarks}"/>
								</c:when>
								<c:otherwise>
									<spring:message code="prop.leave.app.apply.leave.remarks.not.available"/>
								</c:otherwise>
							</c:choose>
						<br>
						<form:textarea path="leaveRemarks"/>
					</td>
				</tr>
				<tr>
					<td><spring:message code="prop.leave.app.return.date"/></td>
					<td>
						<div style="float: ${direction};"><form:input path="leaveReturnDate" id="leaveReturnDate" cssClass="calendarReturn" /></div><div id="divLeaveReturnMsg"></div>
						<br><form:errors path="leaveReturnDate" cssClass="error" />
					</td>
				</tr>
				
				<tr>

					<td colspan="2">
				
							<div  >
										<c:choose>
											<c:when test="${not empty delegatedEmployee}">
												<h5>
													<spring:message code="prop.leave.app.apply.form.leave.manager.custom"/>
													<font color="red" size="small"><i><c:out value="${manager.empName}"/></i> &nbsp; <spring:message code="prop.leave.app.apply.employee.on.leave"/></font>
												</h5>
												<h4 >
													<spring:message code="prop.leave.app.apply.form.delegated.employees"/>
													<font color="red" size="small"><i><c:out value="${delegatedEmployee.empName}"/></i></font>
												</h4>	
											</c:when>
											<c:otherwise>
												<h4 >
													<spring:message code="prop.leave.app.apply.form.leave.manager.custom"/>
													<font color="red" size="small"><i><c:out value="${manager.empName}"/></i></font>
												</h4>											
											</c:otherwise>												
										
										</c:choose>
							<div>
										<fieldset style="padding: 5; border: 1;">
										<legend><spring:message code="prop.leave.app.apply.form.leave.manager.select"/> </legend>
											<table width="100%">     
												<tr>
													<td>
														<div style="float: none;">
															<div style="width: 20%; float: ${direction}">
																<spring:message code="prop.leave.app.apply.form.requester.branch"/>
															</div>
															<div>
																	<form:select path="branch2"  cssClass="selBranchCode leaveOptions" id="selBranchCode" >
																		<option><spring:message code="prop.leave.app.dropdown.branch.text"/></option>
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
															 </div>
														</div>
														<br>
														<div style="float: none;">
															<div style="width: 20%; float:${direction}">
																<spring:message code="prop.leave.app.apply.form.requester.department"/>
															</div>
															<div>
																<form:select path="department2" cssClass="selDeptCode2 leaveOptions" id="selDeptCode2">
																	<option><spring:message code="prop.leave.app.dropdown.department.text"/></option>
																</form:select>
															</div>
														</div>
														<br>
														<div id="sectionDiv" style="float: none;">
															<div style="width: 20%; float: ${direction}"><spring:message code="prop.leave.app.apply.form.requester.section"/></div>
															<div>
																<form:select path="section2" cssClass="selSectionCode2 leaveOptions" id="selSectionCode2">
																	<option><spring:message code="prop.leave.app.dropdown.section.text"/></option>
																</form:select>
															</div>
															<br>
														</div>
														<div id="approverDiv" style="float:none;">
															<div > <spring:message code="prop.leave.app.apply.form.approvar.name.list"/> </div> 
															<table id="divHod" class="divHod" style="margin-${direction}:20% " width="100%"></table><form:errors path="hod"  cssClass="error"/>
														</div>
											</td>
										</tr>
									</table>
									</fieldset>
								</div>
	
						</div>	

					</td>
				</tr>

			</table>

		<center>
			<input id="bttnSubmit" class='btn btn-primary' type="submit" value='<spring:message code="prop.leave.app.return.form.requester.submit"/>' style="border-style: solid; border-color: black; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"/>
		</center>

		
	</fieldset>
</form:form>

<script type="text/javascript">
	function getEmpId(obj,i)
	{
		var currentDropdown = $(obj);
		var varEmpId=currentDropdown.closest('tr').find("input.tEmpId");
		varEmpId.val(obj.value);

	}
	
</script>