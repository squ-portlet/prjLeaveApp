<!--  
 * Project 				:	prjLefaveApp
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	cssWelcome.jsp
 * 
 * Date of Creation		:	19-Aprili-2016
 *  
 * Summary				:	cssWelcome
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
<!-- UI side validation from http://bootstrapvalidator.com/ -->

<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<spring:message code="url.squ.cdn" var="urlCdn"/>
<c:url value="/ui/images/calendar.png" var="imgCalendar"/>


<!-- ******************** CSS Declaration************************ --> 
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/css/bootstrap.css" var="urlCssBootstrap"/>
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/css/bootstrap-theme.min.css" var="urlCssBootstrapThemeMin"/>
<c:url value="${urlCdn}/jqueryui/1.11.4.custom/jquery-ui.css" var="urlCssJQueryUiCustom"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/css/bootstrap-arabic.css" var="urlCssBootstrapArabic"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/css/bootstrap-arabic-theme.css" var="urlCssBootstrapArabicTheme"/>

	<!-- ------------ data table css declaration -->
<c:url value="${urlCdn}/DataTables/1.10.11/DataTables-1.10.11/css/dataTables.bootstrap.min.css" var="urlCssDataTableBSMin"/>
<c:url value="${urlCdn}/DataTables/1.10.11/Responsive-2.0.2/css/responsive.bootstrap.min.css" var="urlCssDataTableResponsiveBSMin"/>



<!-- ******************** JS Declaration************************ -->
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/js/bootstrap.min.js" var="urlJsBootStrapMin"/>
<c:url value="${urlCdn}/bootstrap/3.3.6/js/button.js" var="urlJsBootStrapButton"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/js/bootstrap-arabic.js" var="urlJsBootstrapArabic"/>
<c:url value="${urlCdn}/jquery/1.11.3/jquery-1.11.3.min.js" var="urlJsJqueryMin"/>
<c:url value="${urlCdn}/jqueryui/1.11.4/jquery-ui.min.js" var="urlJsJqueryUIMin"/>
<c:url value="${urlCdn}/jqueryvalidation/1.13.1/jquery.validate.min.js" var="urlJsValidatorJquery"/>
<c:url value="${urlCdn}/jqueryvalidation/1.13.1/additional-methods.min.js" var="urlJsValidatorJqueryAddl"/>

	<!-- ------------ Data Table js declaration -->
<c:url value="${urlCdn}/DataTables/1.10.11/DataTables-1.10.11/js/jquery.dataTables.min.js" var="urlJsDataTableJQueryMin"/>
<c:url value="${urlCdn}/DataTables/1.10.11/DataTables-1.10.11/js/dataTables.bootstrap.min.js" var="urlJsDataTableBSMin"/>
<c:url value="${urlCdn}/DataTables/1.10.11/Responsive-2.0.2/js/dataTables.responsive.min.js" var="urlJsDataTableResponsiveMin"/>
<c:url value="${urlCdn}/DataTables/1.10.11/Responsive-2.0.2/js/responsive.bootstrap.min.js" var="urlJsDataTableResponsiveBSMin"/>


<!-- ******************** CSS implementation************************ --> 
<c:if test="${rc.locale.language == 'en'}" > 
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrap}" />
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapThemeMin}" />
</c:if>
<c:if test="${rc.locale.language == 'ar'}" >
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapArabic}" />
 <link rel="stylesheet" type="text/css" href="${urlCssBootstrapArabicTheme}" />
</c:if>
 <!-- link rel="stylesheet" type="text/css" href="${urlCssBootstrapMin}" /-->

 <link rel="stylesheet" type="text/css" href="${urlCssJQueryUiCustom}" />

	<!-- ------------ datatable css implementation -->

 <link rel="stylesheet" type="text/css" href="${urlCssDataTableBSMin}" />
 <link rel="stylesheet" type="text/css" href="${urlCssDataTableResponsiveBSMin}" />
 
 
 <!-- ******************** JS implementation************************ --> 
  <script type="text/javascript" src="${urlJsJqueryMin}"></script>
 <c:if test="${rc.locale.language == 'en'}" > 
 	<script type="text/javascript" src="${urlJsBootStrapMin}"></script>
 </c:if>
 <c:if test="${rc.locale.language == 'ar'}" >
 	<script type="text/javascript" src="${urlJsBootstrapArabic}"></script> 
 </c:if>
 <script type="text/javascript" src="${urlJsBootStrapButton}"></script> 
 <script type="text/javascript" src="${urlJsJqueryUIMin}"></script>
 
 
 <script type="text/javascript" src="${urlJsValidatorJquery}"></script>
<script type="text/javascript" src="${urlJsValidatorJqueryAddl}"></script>

	<!-- ------------ datatable js implementation -->
<script type="text/javascript" src="${urlJsDataTableJQueryMin}"></script>
<script type="text/javascript" src="${urlJsDataTableBSMin}"></script>
<script type="text/javascript" src="${urlJsDataTableResponsiveMin}"></script>
<script type="text/javascript" src="${urlJsDataTableResponsiveBSMin}"></script>


	 <c:if test="${rc.locale.language == 'en'}" >
		 <c:set var="glphiconNext">
		 	<span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>
		 </c:set>
		 <c:set var="glphiconBack">
	 		<span class="glyphicon glyphicon-triangle-left" aria-hidden="true" ></span>
	 	</c:set>
		 
 	</c:if>
 	<c:if test="${rc.locale.language == 'ar'}" >
 		 <c:set var="glphiconNext">
	 		<span class="glyphicon glyphicon-triangle-left" aria-hidden="true" ></span>
	 	</c:set>
		 <c:set var="glphiconBack">
		 	<span class="glyphicon glyphicon-triangle-right" aria-hidden="true" ></span>
		 </c:set>	 	
 	</c:if>
<style>

.error {
    color:red !important;
}

/* Below code for changing background color of the pagination */
.pagination > .active > a, .pagination > .active > span, .pagination > .active > a:hover, .pagination > .active > span:hover, .pagination > .active > a:focus, .pagination > .active > span:focus {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #f0efef;
    border-color: #337ab7;
}


</style>

<script>

    var getLocalization = function () {
                var localizationobj = {};
                //localizationobj.pagerGoToPageString = "اذهب:";
                localizationobj.pagerGoToPageString='<spring:message code="localizationobj.pagerGoToPageString"/>';
                localizationobj.pagerShowRowsString = '<spring:message code="localizationobj.pagerShowRowsString"/>';
                localizationobj.pagerRangeString = ' <spring:message code="localizationobj.pagerRangeString"/> ';
                localizationobj.pagerNextButtonString = '<spring:message code="localizationobj.pagerNextButtonString"/>';
                localizationobj.pagerFirstButtonString = '<spring:message code="localizationobj.pagerFirstButtonString"/>';
                localizationobj.pagerLastButtonString = '<spring:message code="localizationobj.pagerLastButtonString"/>';
                localizationobj.pagerPreviousButtonString = '<spring:message code="localizationobj.pagerPreviousButtonString"/>';
                localizationobj.sortAscendingString = '<spring:message code="localizationobj.sortAscendingString"/>';
                localizationobj.sortDescendingString = '<spring:message code="localizationobj.sortDescendingString"/>';
                localizationobj.sortRemoveString = '<spring:message code="localizationobj.sortRemoveString"/>';
                localizationobj.emptydatastring = '<spring:message code="localizationobj.emptydatastring"/>';
                localizationobj.firstDay = 1;
                localizationobj.percentSymbol = "%";
                localizationobj.currencySymbol = "€";
                localizationobj.currencySymbolPosition = '<spring:message code="localizationobj.currencySymbolPosition"/>';
                localizationobj.decimalSeparator = ".";
                localizationobj.thousandsSeparator = ",";
                var days = {
                    // full day names
                    names: [
		                    "Sonntag", 
		                    "Montag", 
		                    "Dienstag", 
		                    "Mittwoch", 
		                    "Donnerstag", 
		                    "Freitag", 
		                    "Samstag"
		                    ],
                    // abbreviated day names
                    namesAbbr: [
	                    		"Sonn", 
	                    		"Mon", 
	                    		"Dien", 
	                    		"Mitt", 
	                    		"Donn", 
	                    		"Fre", 
	                    		"Sams"
                    		],
                    // shortest day names
                    namesShort: [
	                    		"So", 
	                    		"Mo", 
	                    		"Di", 
	                    		"Mi", 
	                    		"Do", 
	                    		"Fr", 
	                    		"Sa"
                    		]
                };
                localizationobj.days = days;
                var months = {
                    // full month names (13 months for lunar calendards -- 13th month should be "" if not lunar)
                    names: [
                    		"Januar", 
                    		"Februar", 
                    		"März", 
                    		"April", 
                    		"Mai", 
                    		"Juni", 
                    		"Juli", 
                    		"August", 
                    		"September", 
                    		"Oktober", 
                    		"November", 
                    		"Dezember", 
                    		""
                    		],
                    // abbreviated month names
                    namesAbbr: [
                    			"Jan", 
                    			"Feb", 
                    			"Mär", 
                    			"Apr", 
                    			"Mai", 
                    			"Jun", 
                    			"Jul", 
                    			"Aug", 
                    			"Sep", 
                    			"Oct", 
                    			"Nov", 
                    			"Dez", 
                    			""
                    		   ]
                };
                var patterns = {
                    d: "dd.MM.yyyy",
                    D: "dddd, d. MMMM yyyy",
                    t: "HH:mm",
                    T: "HH:mm:ss",
                    f: "dddd, d. MMMM yyyy HH:mm",
                    F: "dddd, d. MMMM yyyy HH:mm:ss",
                    M: "dd MMMM",
                    Y: "MMMM yyyy"
                }
                localizationobj.patterns = patterns;
                localizationobj.months = months;
                return localizationobj;
            };


</script>



