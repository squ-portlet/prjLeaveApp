/**
 * Project				:	prjLeaveApp
 * Organization			:	Sultan Qaboos University | Muscat | Oman
 * Centre				:	Centre for Information System
 * Department			:	Planning & Development
 * 
 * Author				:	Bhabesh
 *
 * FrameWork			:	Spring 3.1.0 (Annotation) Portlet
 * 
 * File Name			:	LeaveAppControllerMain.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.controller
 * Date of creation		:	Aug 4, 2012  12:02:53 PM
 * Date of modification :	
 * 
 * Summary				:	Main Controller for Leave Application
 *
 *
 * Copyright 2012 the original author or authors and Organization.
 *
 * Licensed under the SQU, CIS policy
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * 
 */
package om.edu.squ.squportal.portlet.leaveapp.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import om.edu.squ.portal.common.EmpCommon;
import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveType;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.ldap.LdapDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.service.LeaveAppServiceDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;
import om.edu.squ.squportal.portlet.leaveapp.validator.LeaveAppValidator;
import om.edu.squ.squportal.portlet.leaveapp.validator.LeaveAppValidatorApprove;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Bhabesh
 *
 */
@Controller
@RequestMapping("VIEW")
public class LeaveAppControllerMain
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LdapDao	ldapdao;
	@Autowired
	LeaveDbDao leaveDbDao;
	@Autowired 
	LeaveAppServiceDao leaveAppServiceDao;

	
	
	/**
	 * 
	 * method name  : welcome
	 * @param request
	 * @param model
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 7, 2012 1:05:54 PM
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping
	private String welcome(@RequestParam(defaultValue="") String msgDisplayAction, PortletRequest request, Model model, Locale locale) throws UnsupportedEncodingException
	{
		String			empNumber 				=	getEmpNumber(request);
		boolean			booLeveApplyAllowed		=	false;
		Employee		employee				=	leaveAppServiceDao.getEmployee(
													empNumber, 
													ldapdao.getCorrectUserName(request.getRemoteUser()) ,
													locale
												  );
		List<LeaveRequest>	leaveRequests			=	leaveAppServiceDao.getLeaveRequests(employee,locale, Constants.CONST_USERTYPE_REQUESTER);
		List<LeaveRequest>	leaveRequestsApprover	=	leaveAppServiceDao.getLeaveRequests(employee,locale, Constants.CONST_USERTYPE_APPROVER);

		if(!msgDisplayAction.equals(""))
		{
			model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG,msgDisplayAction);
		}
		
		if(null != leaveRequests )
		{
			if(leaveRequests.size() != 0)
			{
				LeaveRequest maxLeaveRequest	=	(LeaveRequest) leaveRequests.get(0);
				
				booLeveApplyAllowed = (leaveAppServiceDao.isNewLeaveAfterReturn(leaveRequests));
				
				if((maxLeaveRequest.getStatus().getStatusCode().equals(Constants.CONST_LEAVE_STATUS_REJECTED)) && (maxLeaveRequest.getLeaveReturnIndicator().equals(Constants.CONST_LEAVE_RETURN_INDICATOR_LEAVE)))
				{
					booLeveApplyAllowed	= true;
				}
			}
			else
			{
				booLeveApplyAllowed	= true;
			}
			
		}

		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			model.addAttribute("leaveAppModel",leaveAppModel );
		}

		
		model.addAttribute("isLeveApplyAllowed", booLeveApplyAllowed);
		
		model.addAttribute("leaveRequests", leaveRequests);
		model.addAttribute("leaveRequestsApprover", leaveRequestsApprover);
		
		
		
		model.addAttribute("empHierarchy", employee.getHierarchyCode());
		model.addAttribute("empHierarchyAddl", employee.getHierarchyAddlCode());
		model.addAttribute("empNumber", String.format("%07d", Integer.parseInt(empNumber)));
		model.addAttribute("empName", employee.getEmpName());
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
			
		model.addAttribute("waitingApproval", Constants.CONST_LEAVE_STATUS_WAITING_APPV);
		model.addAttribute("furtherClarification", Constants.CONST_LEAVE_STATUS_FURTHER_CLARIFICATION);
		model.addAttribute("leaveStatusApproved", Constants.CONST_LEAVE_STATUS_APPROVED);
		model.addAttribute("leaveStatusRejected", Constants.CONST_LEAVE_STATUS_REJECTED);
				
		model.addAttribute("leaveActionApprove", Constants.CONST_LEAVE_ACTION_APPROVE);
		model.addAttribute("leaveActionReturn", Constants.CONST_LEAVE_ACTION_RETURN);
		model.addAttribute("leaveActionReject", Constants.CONST_LEAVE_ACTION_REJECT);
		
		model.addAttribute("isLeaveApplicationForRequesterAllowed", leaveAppServiceDao.isLeaveApplicationForRequesterAllowed(empNumber));
		if(!leaveAppServiceDao.isLeaveApplicationForRequesterAllowed(empNumber))
		{
			if (!model.containsAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG))
			{
				if (!leaveAppServiceDao.isDaysAfterJoinAllowed(empNumber))
					{
						model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, UtilProperty.getMessage("error.prop.leave.apply.not.available.in.probation", null, locale));
					}
				if (!leaveAppServiceDao.isDaysAfterJoinAllowed(empNumber))
					{
						model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, UtilProperty.getMessage("error.prop.leave.apply.not.available.after.end.of.service", null, locale));
					}
				
			}
		}
		model.addAttribute("leaveApplicationSymbol", Constants.CONST_LEAVE_RETURN_INDICATOR_LEAVE);
		model.addAttribute("leaveReturnSymbol", Constants.CONST_LEAVE_RETURN_INDICATOR_RETURN);
		model.addAttribute("leaveExtensionSymbol", Constants.CONST_LEAVE_EXTENSION_SYMBOL);
		
		return Constants.PAGE_WELCOME;
		
	}
	
	/**
	 * 
	 * method name  : leaveApplicationView
	 * @param requestNo
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		: View the leave application
	 *
	 * Date    		:	Jan 16, 2013 8:49:25 AM
	 */
	@RequestMapping(params="action=leaveView")
	private String leaveApplicationView
		(
			@RequestParam("reqNo") String requestNo,
			@RequestParam("appEmpNo") String appEmpNo,
			PortletRequest request, 
			Model model,
			Locale locale
		)
	{
		LeaveRequest		leaveRequest	=	leaveAppServiceDao.getLeaveRequest(appEmpNo, requestNo, locale);
		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			
			leaveAppModel.setRequestNo(leaveRequest.getRequestNo());
			leaveAppModel.setApproverAction(leaveRequest.getApprove().getApproverAction());
			leaveAppModel.setApproverRemark(leaveRequest.getApprove().getApproverRemark());
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		List<DelegatedEmp>	delegatedEmps	=	leaveAppServiceDao.getDelegations(requestNo, locale);
		model.addAttribute("leaveRequest", leaveRequest);
		model.addAttribute("delegatedEmps", delegatedEmps);
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
		model.addAttribute("constActionApprove",Constants.CONST_LEAVE_ACTION_APPROVE);
		model.addAttribute("constActionReturn", Constants.CONST_LEAVE_ACTION_RETURN);
		model.addAttribute("constActionReject", Constants.CONST_LEAVE_ACTION_REJECT);	
		model.addAttribute("leaveHistory", leaveAppServiceDao.getLeaveRequestHistory(requestNo, locale));
		model.addAttribute("appHistory", leaveAppServiceDao.getLeaveApproveHistory(requestNo, locale));
		
		return Constants.PAGE_LEAVE_VIEW;
	}
	

	/**
	 * 
	 * method name  : newApply
	 * @param request
	 * @param model
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 7, 2012 1:06:00 PM
	 */
	@RequestMapping(params="action=newApply")
	private	String newApply(
			@RequestParam("paramEndDate") String paramEndDate,
			@RequestParam("paramLeaveExtension") String paramLeaveExtension,
			PortletRequest request, Model model,Locale locale)
	{
		String			empNumber 				=	getEmpNumber(request);
		Employee		employee				=	leaveAppServiceDao.getEmployee(
														empNumber, 
														ldapdao.getCorrectUserName(request.getRemoteUser()) ,
														locale
													  );
		
		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			if(!paramEndDate.equals("na"))
			{
				leaveAppModel.setLeaveStartDate(paramEndDate);
				
			}
			leaveAppModel.setAdminSqu(employee.isAdmin());
			leaveAppModel.setPositionAdditional(employee.getDesignationAddlCode());
			leaveAppModel.setOpMode(Constants.CONST_MODEL_MODE_INSERT);
			leaveAppModel.setProcessSalaray(Constants.CONST_YES_CAPITAL);
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		if(null == paramLeaveExtension || paramLeaveExtension.trim().equals(""))
		{
			paramLeaveExtension = "no";
		}
		
		empNumber	=	String.format("%07d", Integer.valueOf(employee.getEmpNumber()));
	
		model.addAttribute("empNumber", empNumber);
		model.addAttribute("leaveTypeFlag",leaveAppServiceDao.getLeaveTypes(employee,locale) );
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
		model.addAttribute("employee",employee );
		model.addAttribute("addlPosition", leaveAppServiceDao.getAdditionalDesignation(employee.getEmpNumber(),locale));
		model.addAttribute(Constants.CONST_OPERATION,Constants.CONST_OPERATION_ADD);
		model.addAttribute("branches",leaveAppServiceDao.getBranches(employee.getBranchCode(),locale));
		model.addAttribute("branchesEmpno",leaveAppServiceDao.getEmpBranches(employee.getEmpNumber(),locale));
		model.addAttribute("departments",leaveAppServiceDao.getDepartments(employee.getBranchCode(),locale));
		model.addAttribute("baseHierarchyEmp", Constants.CONST_EMPLOYEE_HIERARCHY_CODE);
		model.addAttribute("baseLevelEmp", Constants.CONST_EMPLOYEE_LEVEL);
		model.addAttribute("opMode", Constants.CONST_MODEL_MODE_INSERT);
		model.addAttribute("mgrName", leaveAppServiceDao.getManager(empNumber, locale).getEmpName());
		model.addAttribute("reqNum", Constants.CONST_NOT_AVAILABLE);
		model.addAttribute("leaveTypeNo", Constants.CONST_NOT_AVAILABLE);
		model.addAttribute("daysAllowed", Constants.CONST_NO_OF_DAYS_BEFORE_CURRENT_DATE);
		model.addAttribute("parmLeaveExtension", paramLeaveExtension);
		model.addAttribute("testActionMsg", Constants.CONST_TEST_TEXT_MSG_FROM_ACTION);
		return Constants.PAGE_LEAVE_APPLY_FORM;
	}

	
	/**
	 * 
	 * method name  : submitLeave
	 * @param request
	 * @param response
	 * @param req
	 * @param leaveAppModel
	 * @param result
	 * @param locale
	 * @param model
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		: Submission of leave request
	 *
	 * Date    		:	Sep 1, 2012 12:31:53 PM
	 * @throws IOException 
	 */
	@RequestMapping (params="action=newApply")
	private void submitLeave(
			@RequestParam("operation") String operation,
			@RequestParam("reqNum") String requestNo,
			@RequestParam("leaveTypeNo") String leaveTypeNo,
			@RequestParam("parmLeaveExtension") String parmLeaveExtension,
			ActionRequest request,
			ActionResponse response, PortletRequest req, PortletResponse res,
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
			BindingResult result,SessionStatus status,Locale locale,Model model
			) throws IOException
	{
		String					txtRenderUrl			=	leaveAppModel.getRenderUrlText();
		String					empNumber 				=	getEmpNumber(request);
		Employee				employee				=	leaveAppServiceDao.getEmployee(
																empNumber, 
																ldapdao.getCorrectUserName(request.getRemoteUser()) ,
																locale
															  );

		AllowEleaveRequestProc	allowEleaveRequestProc	=	null;
								empNumber				=	String.format("%07d", Integer.valueOf(employee.getEmpNumber()));
		long					empLeaveBal				=	Long.parseLong(leaveAppServiceDao.getLeaveBalance(empNumber, leaveAppModel.getLeaveStartDate()));
		
		leaveAppModel.setLeaveBalance(empLeaveBal);
		
		try
		{
			allowEleaveRequestProc	=	leaveAppServiceDao.getAllowEleaveRequest (requestNo,leaveAppModel,employee, locale);
			leaveAppModel.setAcceptLeave(allowEleaveRequestProc.isAcceptLeave());
			leaveAppModel.setMsgLeaveRequest(allowEleaveRequestProc.getLeaveMessage());
			
		}
		catch(ParseException ex)
		{
			logger.error("exception at leave request allow notification : "+ex.getMessage());
		}
		
		new LeaveAppValidator().validate(leaveAppModel, result);
		
		if(result.hasErrors())
		{

			if(null != requestNo && 
			! requestNo.trim().equals("") && 
			! requestNo.trim().equals(Constants.CONST_NOT_AVAILABLE) )
			{
				response.setRenderParameter("reqNum", requestNo);
				response.setRenderParameter("appEmpNo", leaveAppModel.getApproverEmpNumber());
				response.setRenderParameter("action", "updateLeaveApply");
			}
			else
			{
				response.setRenderParameter("paramEndDate", leaveAppModel.getLeaveStartDate());
				response.setRenderParameter("paramLeaveExtension", parmLeaveExtension);
				response.setRenderParameter("action", "newApply");
			}
		}
		else
		{
			
			try
			{
				allowEleaveRequestProc	=	leaveAppServiceDao.getAllowEleaveRequest
																	(
																		requestNo,leaveTypeNo,
																		leaveAppModel,employee, locale
																	 );
			}
			catch (ParseException ex)
			{
				logger.error("exception at leave request allow notification : "+ex.getMessage());
			}

			if(operation.equals(Constants.CONST_OPERATION_ADD))
			{
					try
					{
						model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, allowEleaveRequestProc.getLeaveMessage());
						txtRenderUrl = txtRenderUrl.replace(Constants.CONST_TEST_TEXT_MSG_FROM_ACTION, URLEncoder.encode(allowEleaveRequestProc.getLeaveMessage()));
					}
					catch(NullPointerException nEx)
					{
						logger.error("error generating message : "+nEx);
					}
			}
			else if(operation.equals(Constants.CONST_OPERATION_UPDATE))
			{
				model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, allowEleaveRequestProc.getLeaveMessage());
				txtRenderUrl = txtRenderUrl.replace(Constants.CONST_TEST_TEXT_MSG_FROM_ACTION, allowEleaveRequestProc.getLeaveMessage());

			}
			request.setAttribute("testAttr", "testMessage");
			request.setCharacterEncoding("utf-8");
			response.sendRedirect(txtRenderUrl);
			
		}
		
	}
	
	
	/**
	 * 
	 * method name  : leaveApplicationApprove
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		:	leave application approve form
	 *
	 * Date    		:	Sep 15, 2012 10:04:35 AM
	 */
	@RequestMapping(params="action=leaveApprove")
	private String leaveApplicationApprove
		(
			@RequestParam("reqNo") String requestNo,
			PortletRequest request, 
			Model model,
			Locale locale
		)
	{
		return leaveApplicationApprove(requestNo,null,request,model,locale);
	}

	/**
	 * 
	 * method name  : leaveApplicationApprove
	 * @param requestNo
	 * @param _approverAction
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		: leave application approve form mostly for return and reject
	 *
	 * Date    		:	Feb 12, 2013 2:23:25 PM
	 */
	@RequestMapping(params="action=leaveApprove2")
	private String leaveApplicationApprove
		(
			@RequestParam("reqNo") String requestNo,
			@RequestParam("_approverAction") String _approverAction,
			PortletRequest request, 
			Model model,
			Locale locale
		)
	{
		String					empNumber 				=	getEmpNumber(request);
		Employee				employee				=	leaveAppServiceDao.getEmployee(
															empNumber, 
															ldapdao.getCorrectUserName(request.getRemoteUser()) ,
															locale
														  );
		
		LeaveRequest			leaveRequest	=	leaveAppServiceDao.getLeaveRequest(String.format("%07d", Integer.valueOf(employee.getEmpNumber())), requestNo, locale);
		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			
			leaveAppModel.setRequestNo(leaveRequest.getRequestNo());
			if(null != _approverAction)
			{
				leaveAppModel.setApproverAction(_approverAction);
				
			}
			else
			{
				leaveAppModel.setApproverAction(leaveRequest.getApprove().getApproverAction());
				leaveAppModel.setApproverRemark(leaveRequest.getApprove().getApproverRemark());
			}
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		List<DelegatedEmp>	delegatedEmps	=	leaveAppServiceDao.getDelegations(requestNo, locale);
		model.addAttribute("leaveRequest", leaveRequest);
		model.addAttribute("delegatedEmps", delegatedEmps);
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
		model.addAttribute("constActionApprove",Constants.CONST_LEAVE_ACTION_APPROVE);
		model.addAttribute("constActionReturn", Constants.CONST_LEAVE_ACTION_RETURN);
		model.addAttribute("constActionReject", Constants.CONST_LEAVE_ACTION_REJECT);	
		model.addAttribute("leaveHistory", leaveAppServiceDao.getLeaveRequestHistory(requestNo, locale));
		model.addAttribute("appHistory", leaveAppServiceDao.getLeaveApproveHistory(requestNo, locale));
		return Constants.PAGE_LEAVE_APPROVE_FORM;
	}

	
	
	
	/**
	 * 
	 * method name  : leaveApplicationApprove
	 * @param request
	 * @param response
	 * @param req
	 * @param leaveAppModel
	 * @param result
	 * @param locale
	 * @param model
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		: leave approve 
	 *
	 * Date    		:	Dec 9, 2012 2:36:22 PM
	 * @throws IOException 
	 */
	@RequestMapping(params="action=leaveApprove")
	private void leaveApplicationApprove(
			ActionRequest request,
			ActionResponse response, PortletRequest req, 
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
			BindingResult result,Locale locale,Model model
			) throws IOException
	{
		String					empNumber 				=	getEmpNumber(request);
		Employee				employee				=	leaveAppServiceDao.getEmployee(
																empNumber, 
																ldapdao.getCorrectUserName(request.getRemoteUser()) ,
																locale
															  );
		
		new LeaveAppValidatorApprove().validate(leaveAppModel, result);
		if (result.hasErrors())
		{
			response.setRenderParameter("reqNo", leaveAppModel.getRequestNo());
			response.setRenderParameter("_approverAction", leaveAppModel.getApproverAction());
			response.setRenderParameter("action", "leaveApprove2");
		}
		else
		{
			int resultApprove = leaveAppServiceDao.setLeaveApprove(leaveAppModel, employee, locale);
			if(resultApprove == 0)
			{
				response.setRenderParameter(
											Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, 
											UtilProperty.getMessage("error.prop.leave.app.update.blocked", null, locale
								));
				response.setRenderParameter("action", "backToMain");
			}
			else
			{
				response.sendRedirect("/leave-application");
			}
			
			
		}
	}
	
	/**
	 * 
	 * method name  : leaveApplicationCancel
	 * @param requestNo
	 * @param request
	 * @param response
	 * @param req
	 * @param leaveAppModel
	 * @param result
	 * @param locale
	 * @param model
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		: Cancel the leave
	 *
	 * Date    		:	Feb 3, 2013 2:08:14 PM
	 */
	@RequestMapping(params="action=leaveCancel")
	private void leaveApplicationCancel
	(
			ActionRequest request,
			ActionResponse response, PortletRequest req, 
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
			BindingResult result,Locale locale,Model model		
	)
	{
		String	appEmpNo	=	leaveAppModel.getApproverEmpNumber();
		String	requestNo	=	leaveAppModel.getRequestNo();
		String message	=	leaveAppServiceDao.cancelLeaveRequest(appEmpNo, requestNo, locale);
		response.setRenderParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, message);
		response.setRenderParameter("action", "backToMain");
	}
	
	
	/**
	 * 
	 * method name  : leaveAppAutoAdminAction
	 * @param requestNo
	 * @param actionNo
	 * @param request
	 * @param response
	 * @param req
	 * @param leaveAppModel
	 * @param result
	 * @param locale
	 * @param model
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		: action taken from data grid itself
	 *
	 * Date    		:	Jan 9, 2013 2:00:01 PM
	 * @throws IOException 
	 */
	@RequestMapping(params="action=leaveAutoAdminAction")
	private void leaveAppAutoAdminAction 
		(
				ActionRequest request,
				ActionResponse response, PortletRequest req, 
				@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
				BindingResult result,Locale locale,Model model

		) throws IOException
	{
		String 		requestNo		=	leaveAppModel.getRequestNo();
		String		actionNo		=	leaveAppModel.getApproverAction();
		
		String		empNumber 		=	getEmpNumber(request);
		Employee	employee		=	leaveAppServiceDao.getEmployee(
														empNumber, 
														ldapdao.getCorrectUserName(request.getRemoteUser()) ,
														locale
													  );		

		int resultApprove = leaveAppServiceDao.setLeaveApprove(requestNo, actionNo, locale, employee);
		
		if(resultApprove == 0)
		{
			response.setRenderParameter(
										Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, 
										UtilProperty.getMessage("error.prop.leave.app.update.blocked", null, locale
							));
			response.setRenderParameter("action", "backToMain");
		}
		else
		{
			response.sendRedirect("/leave-application");	
		}
	}
	
	
	/**
	 * 
	 * method name  : backToMain
	 * @param request
	 * @param model
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		:
	 *
	 * Date    		:	Aug 7, 2012 1:06:05 PM
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params="action=backToMain")
	private String	backToMain (
								PortletRequest request, Model model, Locale locale
							) throws UnsupportedEncodingException
	{
		
		/*
		if(null != request.getParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG))
		{
			String allowELeaveRequestMsg	=	request.getParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG);
			if (!model.containsAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG))
			{
				model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, allowELeaveRequestMsg);
			}
		}
		*/
		return welcome("",request,model,locale);
	}

	
	/**
	 * 
	 * method name  : updateLeaveApply
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		: Update form of the leave application
	 *
	 * Date    		:	Dec 15, 2012 8:56:59 AM
	 */
	@RequestMapping(params="action=updateLeaveApply")
	private	String updateLeaveApply(
									@RequestParam("reqNum") String requestNo,
									@RequestParam("appEmpNo") String appEmpNo, 
									PortletRequest request, Model model,Locale locale
									)
	{
		String			empNumber 				=	getEmpNumber(request);
		Employee		employee				=	leaveAppServiceDao.getEmployee(
														empNumber, 
														ldapdao.getCorrectUserName(request.getRemoteUser()) ,
														locale
													  );		
		LeaveRequest		leaveRequest		=	leaveAppServiceDao.getLeaveRequest(appEmpNo, requestNo, locale);
		
		if(!model.containsAttribute("leaveAppModel"))
		{
			List<DelegatedEmp>	delegatedEmps	=	leaveAppServiceDao.getDelegations(requestNo, locale);
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			leaveAppModel.setAdminSqu(employee.isAdmin());
			leaveAppModel.setDelegatedEmpsList(delegatedEmps);
			leaveAppModel.setPositionAdditional(employee.getDesignationAddlCode());
			LeaveType		leaveTypeFlag	=	leaveRequest.getLeaveTypeFlag();
			leaveAppModel.setLeaveTypeFlag(leaveTypeFlag.getTypeNo());
			leaveAppModel.setResearchId(leaveRequest.getResearchId());
			leaveAppModel.setLeaveStartDate(leaveRequest.getLeaveStartDate());
			leaveAppModel.setLeaveEndDate(leaveRequest.getLeaveEndDate());
			leaveAppModel.setHod(leaveRequest.getSuggestedHod());
			leaveAppModel.setLeaveRemarks(leaveRequest.getLeaveRequestRemarks());
			leaveAppModel.setOpMode(Constants.CONST_MODEL_MODE_UPDATE);
			leaveAppModel.setApproverEmpNumber(appEmpNo);
			leaveAppModel.setProcessSalaray(leaveRequest.getProcessSalaray());
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		model.addAttribute("empNumber", String.format("%07d", Integer.valueOf(employee.getEmpNumber())));
		model.addAttribute("leaveTypeFlag",leaveAppServiceDao.getLeaveTypes(employee,locale) );
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
		model.addAttribute("employee",employee );
		model.addAttribute("addlPosition", leaveAppServiceDao.getAdditionalDesignation(employee.getEmpNumber(),locale));
		model.addAttribute(Constants.CONST_OPERATION,Constants.CONST_OPERATION_UPDATE);
		model.addAttribute("branches",leaveAppServiceDao.getBranches(employee.getBranchCode(),locale));
		model.addAttribute("branchesEmpno",leaveAppServiceDao.getEmpBranches(employee.getEmpNumber(),locale));
		model.addAttribute("departments",leaveAppServiceDao.getDepartments(employee.getBranchCode(),locale));
		model.addAttribute("baseHierarchyEmp", Constants.CONST_EMPLOYEE_HIERARCHY_CODE);
		model.addAttribute("baseLevelEmp", Constants.CONST_EMPLOYEE_LEVEL);
		model.addAttribute("opMode", Constants.CONST_MODEL_MODE_UPDATE); 
		model.addAttribute("mgrName", leaveAppServiceDao.getManager(String.format("%07d", Integer.valueOf(empNumber)), locale).getEmpName());
		model.addAttribute("approver", leaveRequest.getApprove());
		model.addAttribute("appEmpNo", appEmpNo);
		model.addAttribute("reqNum", requestNo);
		model.addAttribute("leaveTypeNo", leaveRequest.getLeaveType().getTypeNo());
		model.addAttribute("daysAllowed", Constants.CONST_NO_OF_DAYS_BEFORE_CURRENT_DATE);
		model.addAttribute("parmLeaveExtension", "no");
		model.addAttribute("testActionMsg", Constants.CONST_TEST_TEXT_MSG_FROM_ACTION);
		return Constants.PAGE_LEAVE_APPLY_FORM;
	}
	
	/**
	 * 
	 * method name  : leaveReturn
	 * @param request
	 * @param model
	 * @param locale
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		: Rendering of leave return
	 *
	 * Date    		:	Jun 2, 2016 12:18:14 PM
	 * @throws ParseException 
	 */
	@RequestMapping(params="action=leaveReturn")
	private String leaveReturnApply(
			@RequestParam("requestNo") String requestNum,
			@RequestParam("approverEmpNo") String approverEmpNo,
			PortletRequest request, Model model,Locale locale) throws ParseException
	{
		String			empNumber 				=	getEmpNumber(request);
		Employee		employee				=	leaveAppServiceDao.getEmployee(
														empNumber, 
														ldapdao.getCorrectUserName(request.getRemoteUser()) ,
														locale
													  );
						empNumber				=	String.format("%07d", Integer.parseInt(employee.getEmpNumber()));
		Employee		delegatedEmployee		=	leaveAppServiceDao.getDelegatedEmployeeCurrentDate(approverEmpNo, locale);
		
		LeaveRequest	leaveRequest			=	leaveAppServiceDao.getLeaveRequest(approverEmpNo, requestNum, locale);
		
		DateFormat 	df 					= 	new SimpleDateFormat("dd/MM/yyyy");
		String 		stringDate 			= 	df.format(new Date());
		Date 		leaveEndDate		=	df.parse(leaveRequest.getLeaveEndDate());
		Calendar	cal					=	Calendar.getInstance();
					cal.setTime(leaveEndDate);
					cal.add(Calendar.DATE,1);
		Date 		leaveEndDateAdd 	= cal.getTime();
		
		String 		expectedReturnDate 	= df.format(leaveEndDateAdd);
		
		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			leaveAppModel.setRequestNo(requestNum);
			if(null == delegatedEmployee)
			{
				leaveAppModel.setApproverEmpNumber(approverEmpNo);
			}
			else
			{
				leaveAppModel.setApproverEmpNumber(delegatedEmployee.getEmpNumber());
			}
			leaveAppModel.setLeaveReturnDate(expectedReturnDate);
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		
		
		model.addAttribute("employee",employee );
		model.addAttribute("delegatedEmployee",delegatedEmployee );
		model.addAttribute("leaveRequest", leaveRequest);
		model.addAttribute("approver", leaveRequest.getApprove());
		model.addAttribute("branches",leaveAppServiceDao.getBranches(employee.getBranchCode(),locale));
		
		//model.addAttribute("mgrName", leaveAppServiceDao.getManager(empNumber, locale).getEmpName());
		

		return Constants.PAGE_LEAVE_RETURN;
	}

	
	
	
	/**
	 * 
	 * method name  : submitLeaveReturn
	 * @param request
	 * @param response
	 * @param req
	 * @param leaveApplModel
	 * @param result
	 * @param locale
	 * @param model
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		: Submit Leave return
	 *
	 * Date    		:	Jul 25, 2016 11:05:23 AM
	 */
	@RequestMapping(params="action=leaveReturn")
	private void submitLeaveReturn(ActionRequest request,
			ActionResponse response, PortletRequest req, 
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveApplModel,
			BindingResult result,Locale locale,Model model)
	{
		int resultUpdate = leaveAppServiceDao.newLeaveReturn(leaveApplModel);
		if(resultUpdate == 0)
		{
			logger.error("Leave return submit not successful");
		}
	}
	

	/**
	 * 
	 * method name  : getDepartments
	 * @param branchCode
	 * @param request
	 * @param response
	 * @param locale
	 * @throws IOException
	 * LeaveAppControllerMain
	 * return type  : void
	 * 
	 * purpose		:
	 *
	 * Date    		:	Dec 7, 2016 12:54:13 PM
	 */
	@ResourceMapping(value="getDepartmentsByAjax")
	private void getDepartments(
				@RequestParam("branchCode") String branchCode,
				ResourceRequest request, ResourceResponse response,Locale locale) throws IOException
	{
		
	}
	
	
	
	/**
	 * 
	 * method name  : getEmpNumber
	 * @param request
	 * @return
	 * LeaveAppControllerMain
	 * return type  : String
	 * 
	 * purpose		:	get employee number
	 *
	 * Date    		:	Aug 27, 2012 9:23:48 AM
	 */
	private   String getEmpNumber(PortletRequest request)
	{
		String empNumber	=	null;
		if (request.getRemoteUser() != null)
		{
			String remoteUser	=	request.getRemoteUser();
			EmpCommon	empCommon	=	new EmpCommon();
			empNumber = empCommon.getEmployeeNumber(request.getRemoteUser());
		}
		else
		{
			empNumber = "N/A";										// N/A = Not Available
			logger.error("Invalid user");
		}
		
		
		return empNumber;
	}
	
	
	
}
