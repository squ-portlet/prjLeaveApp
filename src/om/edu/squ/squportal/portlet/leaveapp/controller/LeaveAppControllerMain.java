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
 * Summary				:	Controller for SQU Gate permit (Main)
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



import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import om.edu.squ.squportal.portlet.leaveapp.bo.AllowEleaveRequestProc;
import om.edu.squ.squportal.portlet.leaveapp.bo.DelegatedEmp;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.LeaveRequest;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.ldap.LdapDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.service.LeaveAppServiceDao;
import om.edu.squ.squportal.portlet.leaveapp.model.LeaveAppModel;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 */
	@RequestMapping
	private String welcome(PortletRequest request, Model model, Locale locale)
	{
		String			empNumber 	=	getEmpNumber(request);	
		PortletSession	session		=	request.getPortletSession();
		Employee	employee	=	leaveAppServiceDao.getEmployee(
				empNumber, 
				ldapdao.getCorrectUserName(request.getRemoteUser()) ,
				locale
			  );
		
		
		if(null != session.getAttribute("employee"))
		{
			session.removeAttribute("employee");
		}
		session.setAttribute("employee", employee);
		
		List<LeaveRequest>	leaveRequests	=	leaveAppServiceDao.getLeaveRequests(employee,locale);
		model.addAttribute("leaveRequests", leaveRequests);
		model.addAttribute("empHierarchy", employee.getHierarchyCode());
		model.addAttribute("empNumber", String.format("%07d", Integer.parseInt(empNumber)));
		return Constants.PAGE_WELCOME;
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
	private	String newApply(PortletRequest request, Model model,Locale locale)
	{
		PortletSession	session	=	request.getPortletSession();
		Employee	employee	=	(Employee)session.getAttribute("employee");	
		
		if(!model.containsAttribute("leaveAppModel"))
		{
			LeaveAppModel	leaveAppModel	=	new LeaveAppModel();
			leaveAppModel.setAdminSqu(employee.isAdmin());
			leaveAppModel.setPositionAdditional(employee.getDesignationAddlCode());
			model.addAttribute("leaveAppModel",leaveAppModel );
		}
		model.addAttribute("leaveTypeFlag",leaveAppServiceDao.getLeaveTypes(employee,locale) );
		model.addAttribute("adminActions", leaveAppServiceDao.getAdminActions(locale));
		model.addAttribute("employee",employee );
		model.addAttribute("addlPosition", leaveAppServiceDao.getAdditionalDesignation(employee.getEmpNumber(),locale));
		model.addAttribute(Constants.CONST_OPERATION,Constants.CONST_OPERATION_ADD);
		model.addAttribute("departments",leaveAppServiceDao.getDepartments(employee.getBranchCode(),locale));
		model.addAttribute("baseHierarchyEmp", Constants.CONST_EMPLOYEE_HIERARCHY_CODE);
		
		
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
	 */
	@RequestMapping (params="action=submitRequest")
	private void submitLeave(@RequestParam("operation") String operation, ActionRequest request,
			ActionResponse response, PortletRequest req, 
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
			BindingResult result,Locale locale,Model model)
	{
		PortletSession			session					=	request.getPortletSession();
		Employee				employee				=	(Employee)session.getAttribute("employee");	
		AllowEleaveRequestProc	allowEleaveRequestProc	=	null;

		if(operation.equals(Constants.CONST_OPERATION_ADD))
		{

			try
			{
				allowEleaveRequestProc	=	leaveAppServiceDao.getAllowEleaveRequest(leaveAppModel,employee,locale);
				logger.info("leave Request allow notification : "+ allowEleaveRequestProc.toString());
				response.setRenderParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, allowEleaveRequestProc.getLeaveMessage());
				response.setRenderParameter("action", "backToMain");
				
			}
			catch (Exception ex)
			{
				logger.error("exception at leave request allow notification : "+ex.getStackTrace());
			}
			
						logger.info("inside if ");
			//int dbResult	=	leaveAppServiceDao.setNewLeaveRequest(leaveAppModel,employee);
			//logger.info("db result : "+dbResult);
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
	 * purpose		:	leave application form
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
		LeaveRequest		leaveRequest	=	leaveAppServiceDao.getLeaveRequest(requestNo, locale);
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
		
		return Constants.PAGE_LEAVE_APPROVE_FORM;
	}
	
	@RequestMapping(params="action=leaveApprove")
	private void leaveApplicationApprove(
			ActionRequest request,
			ActionResponse response, PortletRequest req, 
			@ModelAttribute("leaveAppModel") LeaveAppModel leaveAppModel,
			BindingResult result,Locale locale,Model model
			)
	{
		Employee	employee	=	(Employee)request.getPortletSession().getAttribute("employee");
		leaveAppServiceDao.setLeaveApprove(leaveAppModel, employee);
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
	 */
	@RequestMapping(params="action=backToMain")
	private String	backToMain (
								PortletRequest request, Model model, Locale locale
							)
	{
		if(null != request.getParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG))
		{
			String allowELeaveRequestMsg	=	request.getParameter(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG);
			if (!model.containsAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG))
			{
				logger.info("inside if - attribute avialable :"+allowELeaveRequestMsg);
				model.addAttribute(Constants.CONST_ALLOW_ELEAVE_REQUEST_MSG, allowELeaveRequestMsg);
			}
			else
			{
				logger.info("inside else - attribute value :"+allowELeaveRequestMsg);
			}
		}
		return welcome(request,model,locale);
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
			try
			{
				empNumber			=	ldapdao.getEmpNumber(remoteUser);
			}
//			catch(CommunicationException	comEx)
//			{
//				logger.error("communication with ldap failed", comEx.getExplanation());
//				empNumber = "com/ex";
//			}
			catch(NullPointerException  nulEx)
			{
				empNumber = null;
			}

		}
		else
		{
			empNumber = "N/A";										// N/A = Not Available
			
			if (request.getRemoteUser() != null)
			{
				logger.error("Invalid user : {}",new Object[]{ldapdao.getCorrectUserName(request.getRemoteUser())});
			}
			else
			{
				logger.error("User not logged in");
			}
		}
		
		
		return empNumber;
	}
	
}
