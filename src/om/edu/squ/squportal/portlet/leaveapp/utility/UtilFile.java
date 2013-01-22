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
 * File Name			:	UtilFile.java
 * Package Name			:	om.edu.squ.squportal.portlet.leaveapp.utility
 * Date of creation		:	Jan 22, 2013  9:28:50 AM
 * Date of modification :	
 * 
 * Summary				:	
 *
 *
 * Copyright 2013 the original author or authors and Organization.
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
package om.edu.squ.squportal.portlet.leaveapp.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Bhabesh
 *
 */
public class UtilFile
{
	
	 /**
	  * 
	  * method name  : readFile
	  * @param fileName
	  * @return
	  * UtilFile
	  * return type  : String
	  * 
	  * purpose		:	Read static Template file
	  *
	  * Date    		:	Jan 22, 2013 10:19:29 AM
	  */
	  public synchronized String readFile(String fileName) 
	    {
		  	URL 		url 	=	null;
		  	FileReader	fr		=	null;
		  	String 		strText = 	"";
	    	try
			{
				url = getClass().getResource("").toURI().toURL();
			}
			catch (MalformedURLException ex)
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			catch (URISyntaxException ex)
			{
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
				try {
					fr = new FileReader( url.getPath()+fileName );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    		//BufferedReader provides a convenient method to read lines from a file one
	    		//at a time. So wrap the filereader created into a BufferedReader.
	    		BufferedReader reader = new BufferedReader( fr ) ;
	    		String line = null ;
	    		
	    		//Now read the file one at a time until you get a null indicating the end
	    		//of file.
	    		try {
					while( ( line = reader.readLine() ) != null )
					{
					strText = strText+line; 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	return strText;
	    }
}
