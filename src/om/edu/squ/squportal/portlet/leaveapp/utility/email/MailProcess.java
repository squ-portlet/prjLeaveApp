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
 * File Name			:	MailProcess.java
 * Package Name			:	om.edu.squ.portlets.courseEmail.utility
 * Date of creation		:	Jan 19, 2013  12:57:01 PM
 * Date of modification :	
 * 
 * Summary				:	process for sending mail
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
package om.edu.squ.squportal.portlet.leaveapp.utility.email;

/**
 * @author Bhabesh
 *
 */

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import om.edu.squ.squportal.portlet.leaveapp.bo.EmailData;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilFile;
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;

import org.springframework.web.multipart.MultipartFile;



	public class MailProcess 
	{
		   	private static  String 	SMTP_HOST_NAME;
		    private static  int 	SMTP_HOST_PORT;
		    private static  String 	SMTP_AUTH_USER;  

			/**
			 * 
			 * method name  : setLeaveEmail
			 * @param emailData
			 * LeaveAppServiceDaoImpl
			 * return type  : void
			 * 
			 * purpose		: process email template and send e-mail
			 *
			 * Date    		:	Jan 21, 2013 2:56:14 PM
			 */
			public void setLeaveEmail(EmailData emailData)
			{
				String emailBody	=	new UtilFile().readFile(emailData.getEmailTemplateName());
				
				/**
				 * Request No
				 */
				if(!(null == emailData.getRequestNo()) && emailBody.contains(Constants.TEMPL_PARAM_REQUEST_NO))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUEST_NO, emailData.getRequestNo());
				}
				/**
				 * Email Receiver Name
				 */
				if(!(null == emailData.getEmailReceiverName()) && emailBody.contains(Constants.TEMPL_PARAM_EMAIL_RECEIVER_NAME))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_EMAIL_RECEIVER_NAME, emailData.getEmailReceiverName());
				}
				/**
				 * Email Message
				 */
				if(!(null == emailData.getEmailMessage()) && emailBody.contains(Constants.TEMPL_PARAM_EMAIL_MESSAGE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_EMAIL_MESSAGE, emailData.getEmailMessage());
				}
				/**
				 * Requester Name
				 */
				if(!(null == emailData.getRequesterName()) && emailBody.contains(Constants.TEMPL_PARAM_REQUESTER_NAME))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUESTER_NAME, emailData.getRequesterName());
				}
				/**
				 * Requester Email
				 */
				if(!(null == emailData.getRequesterEmail()) && emailBody.contains(Constants.TEMPL_PARAM_REQUESTER_EMAIL))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUESTER_EMAIL, emailData.getRequesterEmail());
				}
				/**
				 * Request Date
				 */
				if(!(null== emailData.getRequestDate()) && emailBody.contains(Constants.TEMPL_PARAM_REQUEST_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUEST_DATE, emailData.getRequestDate());
				}
				/**
				 * Request Start Date
				 */
				if(!(null == emailData.getRequestStartDate()) && emailBody.contains(Constants.TEMPL_PARAM_REQUEST_START_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUEST_START_DATE, emailData.getRequestStartDate());
				}
				/**
				 * Request End Date
				 */
				if(!(null == emailData.getRequestEndDate()) && emailBody.contains(Constants.TEMPL_PARAM_REQUEST_END_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUEST_END_DATE, emailData.getRequestEndDate());
				}
				/**
				 * Requester Remark
				 */
				if(!(null == emailData.getRequesterRemark()) && emailBody.contains(Constants.TEMPL_PARAM_REQUESTER_REMARK))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_REQUESTER_REMARK, emailData.getRequesterRemark());
				}
				/**
				 *  Delegation available
				 */
				if(!(null == emailData.getDelegationAvilable()) && emailBody.contains(Constants.TEMPL_PARAM_DELEGATION_AVL))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_DELEGATION_AVL, emailData.getDelegationAvilable());
				}
				/**
				 *  Delegation details
				 */
				if(!(null == emailData.getDelegationDetails()) && emailBody.contains(Constants.TEMPL_PARAM_DELEGATION_DETAILS))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_DELEGATION_DETAILS, emailData.getDelegationDetails());
				}
				/**
				 *  Delegate Name
				 */
				if(!(null == emailData.getDelegateName()) && emailBody.contains(Constants.TEMPL_PARAM_DELEGATE_NAME))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_DELEGATE_NAME, emailData.getDelegateName());
				}
				/**
				 * Delegate Start Date
				 */
				if(!(null == emailData.getDelegationStartDate()) && emailBody.contains(Constants.TEMPL_PARAM_DELEGATE_START_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_DELEGATE_START_DATE, emailData.getDelegationStartDate());
				}
				/**
				 * Delegate End Date
				 */
				if(!(null == emailData.getDelegationEndDate()) && emailBody.contains(Constants.TEMPL_PARAM_DELEGATE_END_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_DELEGATE_END_DATE, emailData.getDelegationEndDate());
				}
				/**
				 * Approver Name 
				 */
				if(!(null == emailData.getApproverName()) && emailBody.contains(Constants.TEMPL_PARAM_APPROVER_NAME))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_APPROVER_NAME, emailData.getApproverName());
				}
				/**
				 * Approver Email
				 */
				if(!(null == emailData.getApproverEmail()) && emailBody.contains(Constants.TEMPL_PARAM_APPROVER_EMAIL))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_APPROVER_EMAIL, emailData.getApproverEmail());
				}
				/**
				 * Approve Date
				 */
				if(!(null == emailData.getApproveDate()) && emailBody.contains(Constants.TEMPL_PARAM_APPROVE_DATE))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_APPROVE_DATE, emailData.getApproveDate());
				}
				/**
				 * Approver Remark
				 */
				if(!(null == emailData.getApproverRemark()) && emailBody.contains(Constants.TEMPL_PARAM_APPROVER_REMARK))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_APPROVER_REMARK, emailData.getApproverRemark());
				}
				/**
				 * Leave URL
				 */
				if(!(null == emailData.getLeaveUrl()) && emailBody.contains(Constants.TEMPL_PARAM_LEAVE_URL))
				{
					emailBody	=	emailBody.replaceAll(Constants.TEMPL_PARAM_LEAVE_URL, emailData.getLeaveUrl());
				}
				
				try
				{
					if(Constants.IS_MAIL_SEND_ON)
					{
						sendMail(
								Constants.MAIL_FROM, 
								new String[]{emailData.getMailTo()}, 
								null, 
								Constants.MAIL_SUBJECT+Constants.MAIL_REQUEST_NO+emailData.getRequestNo(), 
								emailBody, 
								null);
					}
				}
				catch (Exception ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}

		    
		    
		public  boolean  sendMail(String fromAddress, String[] toAddress, String[] ccAddress, String txtMailSubject,String txtMailBody, MultipartFile multipartFile ) throws Exception{
			boolean	mailSuccess = false;
			Properties props = new Properties();
			
			SMTP_HOST_NAME	=	UtilProperty.getMessage
								(
										Constants.MAIL_SMTP_HOST, 
										new Object[]{} 
								);
	        SMTP_AUTH_USER 	=   fromAddress;
	        SMTP_HOST_PORT 	=   Integer.parseInt(
									        		UtilProperty.getMessage
													(
															Constants.MAIL_SMTP_PORT, 
															new Object[]{} 
													)
	        									);
	        
	        
	        String	content = txtMailBody;
	        
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.host", SMTP_HOST_NAME);
	        props.put("mail.smtp.debug", "true");
	        props.put("mail.smtp.auth", "false");
	        //props.put("mail.smtp.starttls.enable","false");								
	        //props.setProperty("mail.smtp.ssl.trust", "squmail.squ.edu.om");
	        props.put("mail.smtp.port", SMTP_HOST_PORT);
	        try
	        {
	        	SMTPAuthenticatorextends	auth	=	new SMTPAuthenticatorextends();
		        Session mailSession = Session.getInstance(props,auth);
		        
		        
		        //mailSession.setDebug(true);												// Debug output in console
		        Transport transport = mailSession.getTransport();
		
		        MimeMessage message = new MimeMessage(mailSession);
		        InternetAddress[] addressTo = new InternetAddress[toAddress.length];
		        InternetAddress[] addressCC	=	null;

		        

		        message.setSubject(txtMailSubject,"UTF-8");											//Arabic Encoding iso-8859-6/ UTF-8
		        
		        Multipart mp = new MimeMultipart();
		        
		        MimeBodyPart mbp1 = new MimeBodyPart();

		        	mbp1.setContent(content, "text/html; charset=UTF-8");								//Arabic Encoding
		        
		        mp.addBodyPart(mbp1);
		        
		        
		        if(null != multipartFile)
		        {
		        	if(multipartFile.getSize() > 0)
		        	{
		        		// create the second message part
				        MimeBodyPart mbp2 = new MimeBodyPart();
				        DataSource fds = new ByteArrayDataSource(multipartFile.getInputStream(),"application/x-any");
				        mbp2.setDataHandler(new DataHandler(fds));
				        mbp2.setFileName(multipartFile.getOriginalFilename());
				        mp.addBodyPart(mbp2);
		        	}
		        }
		        
		        message.setContent(mp);
		        
		        for(int i=0; i<toAddress.length; i++)
		        {
		        	addressTo[i] =  new InternetAddress(toAddress[i]);
		        }

		        
		        message.addRecipients(Message.RecipientType.TO, addressTo);
	
		        if(null != ccAddress)
		        {
		        	if(ccAddress.length !=0)
		        	{
		        		addressCC = new InternetAddress[ccAddress.length];
				        for(int j=0; j<ccAddress.length; j++)
				        {
				        	addressCC[j] =  new InternetAddress(ccAddress[j]);
				        }
				        message.addRecipients(Message.RecipientType.CC, addressCC);
				        
		        	}
		        }
		        
		        message.setFrom(new InternetAddress(SMTP_AUTH_USER));
		        transport.connect ();
		        transport.sendMessage(message,
		            message.getRecipients(Message.RecipientType.TO));
		        if(null != ccAddress)
		        {
		        	if(ccAddress.length !=0)
		        	{
		        		transport.sendMessage(message,
					            message.getRecipients(Message.RecipientType.CC));
		        	}
		        }
		        
		        
		        
		        transport.close();
		        mailSuccess	=	true;
	        }
	        catch(Exception ex)
	        {
	        	mailSuccess	= false;
	        	System.out.println("Mail sending failure, Details : "+ex);
	        	ex.printStackTrace();
	        }
	        
	        return mailSuccess;
	    }

		
		
		
		 class SMTPAuthenticatorextends extends Authenticator
		 {
		     public PasswordAuthentication getPasswordAuthentication()
		     {
		         return new PasswordAuthentication("userId", "*********");
		     }

		 	
		 }
		

		 
		 
		
	}
