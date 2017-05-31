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
package om.edu.squ.squportal.portlet.leaveapp.utility.email.process;

/**
 * @author Bhabesh
 *
 */

import java.util.Map;
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
import om.edu.squ.squportal.portlet.leaveapp.utility.UtilProperty;
import om.edu.squ.squportal.portlet.leaveapp.utility.email.EmailService;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;



	public class MailProcess implements EmailService
	{
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		   	private static  String 	SMTP_HOST_NAME;
		    private static  int 	SMTP_HOST_PORT;
		    private static  String 	SMTP_AUTH_USER; 
		    private	static	String	SMTP_AUTH_USER_NAME;

	
			private StringTemplateGroup stringTemplateGroup;
			private Map<String,String> stringTemplateMap;
			
			public void setStringTemplateMap(Map<String,String> stringTemplateMap) {
				this.stringTemplateMap = stringTemplateMap;
			}
			public void setStringTemplateGroup(StringTemplateGroup stringTemplateGroup) {
				this.stringTemplateGroup = stringTemplateGroup;
			}

		    
		    
		    
		    
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
			public boolean setLeaveEmail(EmailData emailData)
			{
				boolean result	=	false; 

				StringTemplate template = stringTemplateGroup
			  								.getInstanceOf(stringTemplateMap.get(emailData.getEmailTemplateName())); 
			  template.setAttribute("emailData", emailData);
			  
				try
				{
					if(Constants.IS_MAIL_SEND_ON)
					{
						sendMail(
								Constants.MAIL_FROM, 
								Constants.MAIL_FROM_USER, 
								new String[]{emailData.getMailTo()}, 
								null, 
								Constants.MAIL_SUBJECT+Constants.MAIL_REQUEST_NO+emailData.getRequestNo(), 
								template.toString(), null);
						result	=	true;
					}
				}
				catch (Exception ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
					result	=	false;
				}
				
				return result;
			}

		    
		    
		public  boolean  sendMail(String fromAddress, String fromAddressUser, String[] toAddress, String[] ccAddress,String txtMailSubject, String txtMailBody, MultipartFile multipartFile ) throws Exception{
			boolean	mailSuccess = false;
			Properties props = new Properties();
			
			SMTP_HOST_NAME	=	UtilProperty.getMessage
								(
										Constants.MAIL_SMTP_HOST, 
										new Object[]{} 
								);
	        SMTP_AUTH_USER 	=   fromAddress;
	        SMTP_AUTH_USER_NAME	=   fromAddressUser;
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
		        
		        message.setFrom(new InternetAddress(SMTP_AUTH_USER,SMTP_AUTH_USER_NAME));
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
	        	logger.error("Mail sending failure, Details : "+ex);
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
