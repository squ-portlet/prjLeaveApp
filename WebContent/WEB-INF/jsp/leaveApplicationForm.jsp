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

<c:url value="/css/images/ui-icons_454545_256x240.png" var="urlImgIcons4"/>
<c:url value="/css/images/ui-icons_888888_256x240.png" var="urlImgIcons5"/>
<c:url value="/css/images/ui-bg_glass_65_ffffff_1x400.png" var="urlImgBgFlat65x1"/>
<c:url value="/css/images/ui-bg_glass_75_e6e6e6_1x400.png" var="urlImgBgFlat75e6"/>
<c:url value="/css/images/ui-bg_glass_75_dadada_1x400.png" var="urlImgBgFlat75d1"/>

<c:url value="/css/images/ui-anim_basic_16x16.gif" var="urlImgAnimAuto"/>
<%-- <spring:url value="/css/images/dk_arrows.png" var="urlImgDkArrow"/> --%>

<c:url value="/css/squPortletStyles.css" var="urlCssSquPortletStyle"/>
<c:url value="/css/jquery-ui-1.8.18.custom.css" var="urlJQueryCSS"/>
<c:url value="/css/jquery.ui.accordion.css" var="urlJQueryAccordionCSS"/>
<c:url value="/css/jquery.ui.theme.css" var="urlJQueryThemeCSS"/>
<c:url value="/css/jquery.ui.autocomplete.css" var="urlJQueryAutoCompCSS"/>

<%-- <c:url value="/css/dropkick.css" var="urlDropkickCSS"/> --%>

<%-- <c:url value="/js/jquery-1.7.1.min.js" var="urlJsJqueryMin"/> --%>
<c:url value="/js/jquery-1.7.1.js" var="urlJsJqueryMin"/>
<c:url value="/js/jquery-ui-1.8.18.custom.min.js" var="urlJsJqueryCustom"/>
<c:url value="/js/jquery.ui.accordion.js" var="urlJsJqueryAccordion"/>
<c:url value="/js/jquery-ui-i18n.js" var="urlJsJqueryLocaleI18n"/>

<c:url value="/js/jquery.ui.core.js" var="urlJsJqueryCore"/>
<c:url value="/js/jquery.ui.widget.js" var="urlJsJqueryWidget"/>
<c:url value="/js/jquery.ui.mouse.js" var="urlJsJqueryMouse"/>
<c:url value="/js/jquery.ui.button.js" var="urlJsJqueryButton"/>
<c:url value="/js/jquery.ui.draggable.js" var="urlJsJqueryDraggable"/>
<c:url value="/js/jquery.ui.position.js" var="urlJsJqueryPosition"/>
<c:url value="/js/jquery.ui.dialog.js" var="urlJsJqueryDialog"/>
<c:url value="/js/jquery.ui.autocomplete.js" var="urlJsJqueryAutocomplete"/>

<link type="text/css" href="${urlJQueryCSS}" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${urlCssSquPortletStyle}" />
<link type="text/css" href="${urlJsJqueryAccordion}" rel="stylesheet" />
<link type="text/css" href="${urlJQueryThemeCSS}" rel="stylesheet" />
<link type="text/css" href="${urlJQueryAutoCompCSS}" rel="stylesheet" />

<%-- <link type="text/css" href="${urlDropkickCSS}" rel="stylesheet" /> --%>

<script type="text/javascript" src="${urlJsJqueryMin}" charset="utf-8"></script>
<script type="text/javascript" src="${urlJsJqueryCustom}"></script>
<script type="text/javascript" src="${urlJsJqueryAccordion}"></script>
<%-- <script type="text/javascript" src="${urlJsJqueryLocaleI18n}"></script> --%>

<script type="text/javascript" src="${urlJsJqueryCore}"></script>
<script type="text/javascript" src="${urlJsJqueryWidget}"></script>
<script type="text/javascript" src="${urlJsJqueryMouse}"></script>
<script type="text/javascript" src="${urlJsJqueryButton}"></script>
<script type="text/javascript" src="${urlJsJqueryDraggable}"></script>
<script type="text/javascript" src="${urlJsJqueryPosition}"></script>
<script type="text/javascript" src="${urlJsJqueryDialog}"></script>

<script type="text/javascript" src="${urlJsJqueryAutocomplete}"></script>



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
	//.ui-autocomplete {width: 70%; font-size: small;}
	/*autocomplete alternet color*/
	.ui-menu-item-alternate { background: rgb(231, 217, 217); }
	.ui-corner-all {font-size: small;}
</style>



<script>
var resDtIssueOK	=	true;
$(function(){
	// Datepicker
	$('.calendar').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		minDate: -30,
		firstDay:7
		//inline: true
	});
});

$(function(){
	// Datepicker
	$('.calendarStart').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		minDate: -<c:out value="${daysAllowed}"/>,
		firstDay:7,
		onClose: function( selectedDate ) {
			var	endDt	=	$( ".calendarEnd" ).datepicker( "getDate" );
			resDtIssueOK = diffLeaveDate();	
			if(null == endDt || resDtIssueOK)
					{
						$( ".calendarEnd" ).datepicker( "option", "minDate", selectedDate ); 
						$( ".calendarDelgStart" ).datepicker( "option", "minDate", selectedDate );
						$( ".calendarDelgEnd" ).datepicker( "option", "minDate", selectedDate );
					}
			showHideProcessSalaray();

			var startLeaveDt=$(this).val();
			var empNumber = "${empNumber}";
		       $.ajax({
		            type: "GET",
//		             contentType: "application/json; charset=utf-8", hierarchyLevelCode
		            url:  "${servletLeaveBal}",
		            data: 'startLeaveDt='+startLeaveDt+"&empNumber="+empNumber,
		            dataType: "json",
		            success: function(resp) {
		            			if(null != resp && resp.length > 0 && $("#leaveTypeFlag option:selected").val() == 'A')
		            			{
									$("#divLeaveBal").html('<font color="red">'+resp+'</font>' + '&nbsp; <spring:message code="prop.leave.app.apply.form.leave.balance.days"/>');
									$("#divLeaveBalTxt").html('&nbsp;&nbsp; <spring:message code="prop.leave.app.apply.form.leave.balance"/> &nbsp; : &nbsp;&nbsp;');
		            			}
		            			else
		            				{
		            				$("#divLeaveBal").html("");
		            				$("#divLeaveBalTxt").html("");
		            				}
		            }
		        }
		        );
			
				}
		//inline: true
	});
});


$(function(){
	// Datepicker
	$('.calendarEnd').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		minDate: -<c:out value="${daysAllowed}"/>,
		firstDay:7,
	    onClose: function( selectedDate ) {
	    	var startDt	=	$( ".calendarStart" ).datepicker( "getDate" );
	    	resDtIssueOK = diffLeaveDate();
	    	if(null == startDt || resDtIssueOK)
			{
		    	$( ".calendarStart" ).datepicker( "option", "maxDate", selectedDate );  
	    		$( ".calendarDelgEnd" ).datepicker( "option", "maxDate", selectedDate );
	    		$( ".calendarDelgStart" ).datepicker( "option", "maxDate", selectedDate );
			}
	    	
	    	var durationDay	=	(($( ".calendarEnd" ).datepicker( "getDate" ) -  $( ".calendarStart" ).datepicker( "getDate" ))/(1000*60*60*24))+1;
	    	if(null != $( ".calendarStart" ).datepicker( "getDate" ) && $( ".calendarStart" ).datepicker( "getDate" ) != "")
	    		{
	    			$("#divLeaveDurTxt").html('&nbsp;&nbsp <spring:message code="prop.leave.app.apply.form.leave.duration"/>&nbsp; : &nbsp;&nbsp;');
	    			$("#divLeaveDur").html('<font color="red">'+durationDay+'</font> &nbsp;<spring:message code="prop.leave.app.apply.form.leave.balance.days"/>');
	    		}
	    	else
	    		{
	    			$("#divLeaveDurTxt").html("");
	    			$("#divLeaveDur").html("");
	    		}
	    	if(null == $( ".calendarEnd" ).datepicker( "getDate" ) || $( ".calendarEnd" ).datepicker( "getDate" ) == "")
	    		{
	    			$("#divLeaveDurTxt").html("");
    				$("#divLeaveDur").html("");
	    		}
	    	showHideProcessSalaray();
	    }
	});

});



$(function(){
	// Datepicker
	$('.calendarDelgStart').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		firstDay:6
		//inline: true
	});
});

$(function(){
	// Datepicker
	$('.calendarDelgEnd').datepicker({
		dateFormat:"dd/mm/yy",
		showOn: "both",
		buttonImage: "${urlImgCalendar}",
		buttonImageOnly: true,
		firstDay:6
		//inline: true
	});
});

/**
 * If not Annual leave then leave balance data will  
 */
$(function() {
	var varLeaveTypeF = function() {
		if ($("#leaveTypeFlag option:selected").val() != 'A')
	{
		$("#divLeaveBal").html("");
		$("#divLeaveBalTxt").html("");
	}
	};
    $('#leaveTypeFlag').each(varLeaveTypeF);
    $('#leaveTypeFlag').change(varLeaveTypeF);
});





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


/**
 * Accordion
 */
$(function() {
	$( "#accordion" ).accordion(
			{
			autoHeight: false

			}
			
		);
	});

/**
 * Dialogue for differ leave date 
 */
	function diffLeaveDate()
		{
			
// 			var startDt	=	Date.parse(document.getElementById("leaveStartDate").value);
// 			var	endDt	=	Date.parse(document.getElementById("leaveEndDate").value);

 			var startDt	=	$( ".calendarStart" ).datepicker( "getDate" );
 			var	endDt	=	$( ".calendarEnd" ).datepicker( "getDate" );
 			
			var result	=	null;
			if(null != startDt && null != endDt)
				{
						if(startDt > endDt)
						{
						$(function() { 
							$("#dialogDate").html('<spring:message code="error.prop.leave.app.date.incorrect.range"/>'); 
								$("#dialogDate").dialog(
				               {
				               	resizable: false,
				               	width:400,
				               	height:200,
				               	modal: true,
				               	closeOnEscape: false, 
				   				close: function(event, ui) {
				   					$("#dialogDate").hide();
				   					return false;
				   					},
				 					buttons:
				 						{
				 						"<spring:message code='prop.leave.app.title.request.button.exit'/>": function() {
				 						$( this ).dialog( "close" );
				 						}
				 						}
				               }		
				               ); 
							return false;                         
						});
							
							result = false;
						}
					else 
						{
						result = true;
						}
				}
			return result;
			
		}

	/**
	* 
	*/
	function showHideProcessSalaray()
	{
			if($("#leaveTypeFlag").val() == 'A')
				{
				var start = $( ".calendarStart" ).datepicker( "getDate" );
				var end = $( ".calendarEnd" ).datepicker( "getDate" );
				var diff = new Date(end - start);

				// get days
				var days = diff/1000/60/60/24;
				if (days >= 14)
 				{
 					$("#divProcessSal").show();
 				}
				else
 				{
 					$("#divProcessSal").hide();
 				}
				}
			else
 				{
 					$("#divProcessSal").hide();
 				}
	}
	
	/**
	* Leave type changes to sabbatical shows researchId
	*/
	$(function() {

	 		$("#leaveTypeFlag").on("change", function (){
	 			
	 			if(this.value == 'B')
	 				{
	 					$("#divResearchId").show();
	 				}
	 			else
	 				{
	 					$("#divResearchId").hide();
	 				}
	 				
	 		});
	});	

	/**
	* Leave type changes to annual shows advance salary options
	*/
	$(function() {

	 		$("#leaveTypeFlag").on("change", function (){
	 			
	 			showHideProcessSalaray();
	 				
	 		});
	});		
	
	/*AutoComplete feature at researchId for sabbatical leave*/
	$(function() {
		$( "#researchId" ).autocomplete({
			source: function( request, response ) {
				var researchId = $("#researchId").val();
				$.ajax({
					contentType: "application/json; charset=utf-8",
					url: "${servletResearch}",
					dataType: "json",
					data: 'researchId='+researchId,
					success: function( data ) {
						response( $.map( data, function( item ) {
							return {
								label: item.budgetId +  ", " + item.budgetYearFrom + " - " + item.budgetYearTo + ", " + item.budgetDescription + ", " + item.budgetDescriptionShort +", " +item.budgetRemarks,
								value: item.budgetId
							}

						}));

					}

				});
			},
		minLength: 2,
		
			open: function( event, ui ) {
						 $( this ).autocomplete( "widget" )
							 .find( "ui-menu-item-alternate" )
								 .removeClass( "ui-menu-item-alternate" )
							 .end()
							 .find( "li.ui-menu-item:odd a" )
								 .addClass( "ui-menu-item-alternate" );
						 
						 $('.ui-autocomplete').css('width', '75%');
						 $('.ui-autocomplete').css('font-size', 'smaller');
			
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		}
		});
		
	});
	
</script>

</head>
 
<portlet:actionURL var="submitRequest">
	<portlet:param name="action" value="newApply"/>
		<portlet:param name="operation">
		<jsp:attribute name="value">
			<c:out value="${operation}"/>
		</jsp:attribute>
	</portlet:param>
	<portlet:param name="reqNum" value="${reqNum}"/>
	<portlet:param name="leaveTypeNo" value="${leaveTypeNo}"/>
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

<div id="dialogDate" class="dialogApproveClass" title='<spring:message code="error.prop.leave.app.warning.dialogue.title"/>' style="display:none;"></div>

<form:form modelAttribute="leaveAppModel"  action="${submitRequest}" method="post" htmlEscape="false" >
<form:hidden path="approverEmpNumber" />
<h2><font color="red"><form:errors path="*" /></font></h2>
	<fieldset>
		<legend>
			<h3>
				<spring:message code="prop.leave.app.apply.form.legend.title"/>
			</h3>
		</legend>
			<table width=100%>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.type"/>:
						</span>
					</th>
					<td>
						<form:select path="leaveTypeFlag" cssClass="leaveOptions" >
							<form:option value=""><spring:message code="prop.leave.app.dropdown.leave.type.text"/></form:option>
							<form:options items="${leaveTypeFlag}" itemLabel="typeDesc" itemValue="typeNo"/>
						</form:select>
						<div><form:errors path="leaveTypeFlag" cssClass="error" /></div>
					</td>
				</tr>
				<tr id="divResearchId" style="display: none;">
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.sabbatical.research.id"/>:
						</span>						
					</th>
					<td>
						<div class="ui-widget" style="font-size: small;">
							<form:input path="researchId"/>
						</div>
					</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.start.date"/>:
						</span>
					</th>
					<td><div style="float: ${direction};"><form:input path="leaveStartDate" id="leaveStartDate" cssClass="calendarStart" /></div><div id="divLeaveBalTxt" style="float: ${direction};"></div><div id="divLeaveBal"></div> 
						<br><form:errors path="leaveStartDate" cssClass="error" />
					</td>
	
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.leave.end.date"/>:
						</span>
					</th>
					<td>
						<div style="float: ${direction};"><form:input path="leaveEndDate" id="leaveEndDate" cssClass="calendarEnd" /></div><div id="divLeaveDurTxt" style="float: ${direction};"></div><div id="divLeaveDur"></div>
						<br><form:errors path="leaveEndDate" cssClass="error"/>
					</td>
				</tr>
				<tr id="divProcessSal" style="display:none;">
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.salary.advance"/>
						</span>
					</th>
					<td>
						<form:radiobutton path="processSalaray" value="Y"/> <spring:message code="prop.leave.app.apply.form.salary.radio.bttn.yes"/>
						<c:if test="${employee.omani}">
						<br><form:radiobutton path="processSalaray" value="N"/> <spring:message code="prop.leave.app.apply.form.salary.radio.bttn.no"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
					<span class="PortletHeaderText">
						<spring:message code="prop.leave.app.apply.form.leave.remarks"/>:
					</span>
					</th>
					<td colspan="4"><form:textarea cssStyle="width:100%" path="leaveRemarks"/></td>
				</tr>
				<tr>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.requester.approver.selection"/>
						</span>
					</th>
					<td>
<!-- Accordion Start -->					
							<div id="accordion" class="ui-accordion ui-widget ui-helper-reset">
								<c:choose>
									<c:when test='${opMode=="u"}'>
										<h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top">
<!-- 												<a href="#"> -->
														<spring:message code="prop.leave.app.apply.form.leave.manager"/>
														<font color="red" size="small"><i><c:out value="${approver.employee.empName}"/></i></font>
<!-- 												</a> -->
										</h3>
									</c:when>
									<c:otherwise>
										<div>
<!-- 												<a href="#"> -->
														<spring:message code="prop.leave.app.apply.form.leave.manager.default"/>
														<font color="red" size="small"><i><c:out value="${mgrName}"/></i></font>
<!-- 												</a> -->
										</div>
										<div>
											<br>
											<center><spring:message code="prop.leave.app.apply.form.leave.manager.change.text"/></center>
										</div>
										
									<h3 class="ui-accordion-header ui-helper-reset ui-state-default ui-accordion-icons ui-accordion-header-active ui-state-active ui-corner-top">
										<a href="#"><spring:message code="prop.leave.app.apply.form.leave.manager.custom"/></a>
									</h3>				
									<div>
										<fieldset>
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
																			<c:choose>
																				<c:when test='${opMode=="u"}'>
																				<c:if test="${approver.branchCode==branch.branchCode}">
												<%-- 									<c:if test="${employee.branchCode==branch.branchCode}"> --%>
																					<c:set value="selected" var="selct"/> 
																				</c:if>
																				</c:when>
																				<c:otherwise>
																					<c:if test="${employee.branchCode==branch.branchCode}">
																						<c:set value="selected" var="selct"/> 
																					</c:if>
																				</c:otherwise>
																			</c:choose>
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
									</c:otherwise>
								</c:choose>
						</div>	
<!-- Accordion end -->
					</td>
				</tr>

			</table>


		<c:if test="${(employee.hierarchyLevelCode != baseLevelEmp) || (not empty (employee.hierarchyAddlLevelCode) != baseLevelEmp)}" >		
		<br>
			<table cellspacing="0" cellpadding="0" border="1" width="100%" style="margin: 1em;">
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
						<spring:message code="prop.leave.app.apply.form.delegated.branch"/>
					</span>
					</th>
					<th class="PortletHeaderColor">
						<span class="PortletHeaderText">
							<spring:message code="prop.leave.app.apply.form.delegated.emp.name"/>
						</span>
					</th>
				</tr>
				<c:forEach var="i" begin="0" end="2" step="1">
					<tr>
						<td>
							<center>
							<form:input path="delegatedEmps[${i}].fromDate" cssClass="calendarDelgStart" />
							</center>
							<form:errors path="delegatedEmps[${i}].fromDate" cssClass="error"/>
						</td>
						<td>
						<center>
							<form:input path="delegatedEmps[${i}].toDate" cssClass="calendarDelgEnd"/>
						</center>
							<form:errors path="delegatedEmps[${i}].toDate" cssClass="error"/>
						</td>
						<td>
							<center>
								<form:select path="delegatedEmps[${i}].branchCode" cssClass="selBranchCode" id="selBranchCode">
									<option><spring:message code="prop.leave.app.dropdown.branch.text"/></option>
									<c:forEach items="${branchesEmpno}" var="brn">
										<c:set value="" var="selct"/>
										<c:if test="${employee.branchCode==brn.branchCode}">
											<c:set value="selected" var="selct"/> 
										</c:if>
										<option value="${brn.branchCode}" ${selct}>
											<c:out value="${brn.branchDesc}"/>
										</option>
									</c:forEach>
								</form:select>
							</center>
						</td>
						<td>
							<center>
								<form:hidden path="delegatedEmps[${i}].empNumber" cssClass="tEmpId" id="tEmpId" />
								<form:select path="delegatedEmps[${i}].empName" cssClass="selEmpNum" id="selEmpNum" onchange="getEmpId(this,${i});">
									<option><spring:message code="prop.leave.app.dropdown.employee.text"/></option>
								</form:select>
							</center>
						</td>

					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">
						<i><font color="red"><spring:message code="prop.leave.app.apply.form.delegated.note"/></font></i>
					</td>
				</tr>
			</table>
		</c:if>
		
		<table cellspacing="0" cellpadding="0" border="1" width="100%">
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
		

		<center>
			<input type="submit" value='<spring:message code="prop.leave.app.apply.form.requester.submit"/>' style="border-style: solid; border-color: black; border-width: 1px; padding-left: 4px; padding-right: 4px; padding-top: 1px; padding-bottom: 1px"/>
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