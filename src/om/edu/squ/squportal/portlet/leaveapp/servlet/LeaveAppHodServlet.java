package om.edu.squ.squportal.portlet.leaveapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.HoD;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDaoImpl;
import om.edu.squ.squportal.portlet.leaveapp.utility.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveAppHodServlet
 */
public class LeaveAppHodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private	JdbcTemplate		jdbcTemplate;
	private	DataSource			datasource;
	
	public void setDataSource(DataSource dataSource) 
	{ 
		this.jdbcTemplate		=	new JdbcTemplate(dataSource);
		this.datasource			=	dataSource;
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveAppHodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		Object obj = config.getServletContext().getAttribute("srvDataSource"); 
		setDataSource((DataSource)obj); 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Locale			locale				=	request.getLocale();	
		String			branchCode			=	request.getParameter("branchCode");
		String			deptCode			=	request.getParameter("deptCode");
		String			hierarchyLevelCode	=	request.getParameter("hierarchyLevelCode");
		String			sectCode			=	null;
		HoD				deptHead			=	null;
		List<HoD>		sectHead			=	null;
		HoD				hoD					=	new HoD("na","na");
		List<HoD>		hoDList				=	null;
		String			strJson				=	null;
				
		
		LeaveDbDao		leaveDbDao	=	new LeaveDbDaoImpl(datasource);

		if(null != request.getParameter("sectionCode") && ! request.getParameter("sectionCode").trim().equals("") )
		{
			sectCode		=	request.getParameter("sectionCode");
			try
			{
				sectHead		=	leaveDbDao.getSectionHead(branchCode, deptCode, sectCode,hierarchyLevelCode, locale);
				hoDList			=	sectHead;		//leaveDbDao.getDepartmentHead(branchCode, deptCode, locale);

				if(hoDList.size()==0)
				{
					logger.warn("hod not available at section level, " +
							"for branch : "+branchCode + " department : "+deptCode+ " section : " +sectCode);

					hoDList	=	getDepartmentHeads(branchCode, deptCode, hierarchyLevelCode, locale);
				}
			}
			catch(Exception ex)
			{
				logger.error("hod not available at section level, " +
						"for branch : "+branchCode + " department : "+deptCode+ " section : " +sectCode +
								" Error : "+ex.getMessage());
			}
			
		}
		else
		{
			try
			{
				hoDList	=	leaveDbDao.getDepartmentHead(branchCode, deptCode, hierarchyLevelCode, locale);
				
				if(hoDList.size() == 0)
				{
					for (int i = 1; i<Constants.CONST_LEVEL_COUNT; i++)
					{
						try
							{
								List<HoD> hoDNextLevelList	=	getBranchLevelHeads(branchCode, i, hierarchyLevelCode, locale);
								if(hoDNextLevelList.size() != 0)
								{
									hoDList	= hoDNextLevelList;
									break;
								}
								
							}
						catch(Exception ex4)
						{
							logger.error("hod not available at next level increment("+i+"), for branch : "+branchCode + "Error : "+ex4.getMessage());
						}
						
					}
				}
				
			}
			catch(Exception ex3)
			{
				logger.error("hod not available at department level, for branch : "+branchCode + "Error : "+ex3.getMessage());

			}
		}
		
		
		
		Gson gson = new Gson();
		if(null == hoDList)
		{
			strJson	=	gson.toJson(hoD);
		}
		else
		{
			strJson	=	gson.toJson(hoDList);
		}

		response.getWriter().print(strJson);
	}
	
	/**
	 * 
	 * method name  : getDepartmentHeads
	 * @param branchCode
	 * @param deptCode
	 * @param locale
	 * @return
	 * LeaveAppHodServlet
	 * return type  : List<HoD>
	 * 
	 * purpose		: get department level heads
	 *
	 * Date    		:	Dec 5, 2012 9:51:01 AM
	 */
	private List<HoD>	getDepartmentHeads(String branchCode, String deptCode, String hierarchyLevelCode, Locale locale)
	{
		LeaveDbDao		leaveDbDao	=	new LeaveDbDaoImpl(datasource);
		List<HoD>		lstHods		=	null;
		try
		{
			lstHods	=	leaveDbDao.getDepartmentHead(branchCode, deptCode, hierarchyLevelCode, locale);
		}
		catch(Exception ex)
		{
			logger.error("hod not available at department level, for branch : "+branchCode + "Error : "+ex.getMessage());
		}
		return lstHods;
		
	}
	
	/**
	 * 
	 * method name  : getBranchLevelHeads
	 * @param branchCode
	 * @param paramLevelAdd
	 * @param locale
	 * @return
	 * LeaveAppHodServlet
	 * return type  : List<HoD>
	 * 
	 * purpose		: get next level head at branch level
	 *
	 * Date    		:	Dec 5, 2012 10:02:21 AM
	 */
	private List<HoD>	getBranchLevelHeads(String branchCode, int paramLevelAdd, String hierarchyLevelCode, Locale locale)
	{
		LeaveDbDao		leaveDbDao	=	new LeaveDbDaoImpl(datasource);
		List<HoD>		lstHods		=	null;
		
		try
		{
			lstHods	=	leaveDbDao.getNextHeadBranch(branchCode, paramLevelAdd,hierarchyLevelCode, locale);
		}
		catch(Exception ex)
		{
			logger.error("hod not available at next level, for branch : "+branchCode + "Error : "+ex.getMessage());
		}
		return lstHods;
		
		
	}
	
}
