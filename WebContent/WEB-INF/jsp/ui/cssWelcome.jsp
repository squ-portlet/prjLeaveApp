<!--  
 * Project 				:	prjAudioVideoRequest
 * Organisation 		:	Sultan Qaboos University
 * Center				:	Center for Information System
 * Department 			:	Web & E-Services
 * Author				:	Bhabesh
 * 
 * FrameWork			:	Spring 3.2.3 (Annotation) Portlet
 * 
 * File Name			:	cssWelcome.jsp
 * 
 * Date of Creation		:	17-March-2016
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
<c:url value="/ui/images/calendar.gif" var="imgCalendar"/>


<!-- ******************** CSS Declaration************************ --> 
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/css/bootstrap.css" var="urlCssBootstrap"/>
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/css/bootstrap-theme.min.css" var="urlCssBootstrapThemeMin"/>
<c:url value="${urlCdn}/jqueryui/1.11.4.custom/jquery-ui.css" var="urlCssJQueryUiCustom"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/css/bootstrap-arabic.css" var="urlCssBootstrapArabic"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/css/bootstrap-arabic-theme.css" var="urlCssBootstrapArabicTheme"/>
	<!-- ------------ jqwidget css declaration -->
<c:url value="${urlCdn}/jqwidgets/3.8.2/css/jqx.base.css" var="urlCssJqxBase"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/css/jqx.bootstrap.css" var="urlCssJqxBootStrap"/>



<!-- ******************** JS Declaration************************ -->
<c:url value="${urlCdn}/bootstrap/3.3.6/dist/js/bootstrap.min.js" var="urlJsBootStrapMin"/>
<c:url value="${urlCdn}/bootstrap/3.3.6/js/button.js" var="urlJsBootStrapButton"/>
<c:url value="${urlCdn}/bootstrap-arabic/bootstrap-3-arabic-master/3.3.6/js/bootstrap-arabic.js" var="urlJsBootstrapArabic"/>
<c:url value="${urlCdn}/jquery/1.11.3/jquery-1.11.3.min.js" var="urlJsJqueryMin"/>
<c:url value="${urlCdn}/jqueryui/1.11.4/jquery-ui.min.js" var="urlJsJqueryUIMin"/>
<c:url value="${urlCdn}/jqueryvalidation/1.13.1/jquery.validate.min.js" var="urlJsValidatorJquery"/>
<c:url value="${urlCdn}/jqueryvalidation/1.13.1/additional-methods.min.js" var="urlJsValidatorJqueryAddl"/>
	<!-- ------------ jqwidget js declaration -->
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxcore.js" var="urlJsJqxCore"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxbuttons.js" var="urlJsJqxButtons"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdata.js" var="urlJsJqxData"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdatatable.js" var="urlJsJqxDataTable"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxscrollbar.js" var="urlJsJqxScrollbar"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtreegrid.js" var="urlJsJqxTreeGrid"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxlistbox.js" var="urlJsJqxlistbox"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdropdownlist.js" var="urlJsJqxdropdownlist"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdata.export.js" var="urlJsJqxDataExport"/>

<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.columnsreorder.js" var="urlJsJqxGridColumnsreorder"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.columnsresize.js" var="urlJsJqxGridColumnsresize"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.edit.js" var="urlJsJqxGridEdit"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.filter.js" var="urlJsJqxGridFilter"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.grouping.js" var="urlJsJqxGridGrouping"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.js" var="urlJsJqxGrid"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.pager.js" var="urlJsJqxGridPager"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.selection.js" var="urlJsJqxGridSelection"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxgrid.sort.js" var="urlJsJqxGridSort"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxmenu.js" var="urlJsJqxMenu"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdocking.js" var="urlJsJqxDocking"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxpanel.js" var="urlJsJqxPanel"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxsplitter.js" var="urlJsJqxSplitter"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtabs.js" var="urlJsJqxTabs"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxtooltip.js" var="urlJsJqxToolTip"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxwindow.js" var="urlJsJqxWindow"/>

<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxdatetimeinput.js" var="urlJsJqxDateTimeInput"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/jqxcalendar.js" var="urlJsJqxCalendar"/>


<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.js" var="urlJsJqxLocaleGlobal"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar.js" var="urlJsJqxLocaleAr"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar.js" var="urlJsJqxLocaleAr"/>
<c:url value="${urlCdn}/jqwidgets/3.8.2/js/locale/globalize.culture.ar-OM.js" var="urlJsJqxLocaleArOm"/>


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

	<!-- ------------ jqwidget css implementation --> 
 <link rel="stylesheet" type="text/css" href="${urlCssJqxBase}" />
 <link rel="stylesheet" type="text/css" href="${urlCssJqxBootStrap}" />
 
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

	<!-- ------------ jqwidget js implementation -->
<script type="text/javascript" src="${urlJsJqxCore}"></script>
<script type="text/javascript" src="${urlJsJqxButtons}"></script>
<script type="text/javascript" src="${urlJsJqxData}"></script>
<script type="text/javascript" src="${urlJsJqxDataTable}"></script>
<script type="text/javascript" src="${urlJsJqxScrollbar}"></script>
<script type="text/javascript" src="${urlJsJqxTreeGrid}"></script>
<script type="text/javascript" src="${urlJsJqxlistbox}"></script>
<script type="text/javascript" src="${urlJsJqxdropdownlist}"></script>
<script type="text/javascript" src="${urlJsJqxDataExport}"></script>

<script type="text/javascript" src="${urlJsJqxGridColumnsreorder}"></script>
<script type="text/javascript" src="${urlJsJqxGridColumnsresize}"></script>
<script type="text/javascript" src="${urlJsJqxGridEdit}"></script>
<script type="text/javascript" src="${urlJsJqxGridFilter}"></script>
<script type="text/javascript" src="${urlJsJqxGridGrouping}"></script>
<script type="text/javascript" src="${urlJsJqxGrid}"></script>
<script type="text/javascript" src="${urlJsJqxGridPager}"></script>
<script type="text/javascript" src="${urlJsJqxGridSelection}"></script>
<script type="text/javascript" src="${urlJsJqxGridSort}"></script>
<script type="text/javascript" src="${urlJsJqxMenu}"></script>

<script type="text/javascript" src="${urlJsJqxDocking}"></script>
<script type="text/javascript" src="${urlJsJqxPanel}"></script>
<script type="text/javascript" src="${urlJsJqxSplitter}"></script>
<script type="text/javascript" src="${urlJsJqxTabs}"></script>
<script type="text/javascript" src="${urlJsJqxToolTip}"></script>
<script type="text/javascript" src="${urlJsJqxWindow}"></script>

<script type="text/javascript" src="${urlJsJqxDateTimeInput}"></script>
<script type="text/javascript" src="${urlJsJqxCalendar}"></script>

<c:if test="${rc.locale.language == 'ar'}">
	<script type="text/javascript" src="${urlJsJqxLocaleGlobal}"></script>
	<script type="text/javascript" src="${urlJsJqxLocaleAr}"></script>
	<script type="text/javascript" src="${urlJsJqxLocaleArOm}"></script>
</c:if>



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



