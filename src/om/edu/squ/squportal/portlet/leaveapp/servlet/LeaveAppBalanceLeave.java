package om.edu.squ.squportal.portlet.leaveapp.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveAppBalanceLeave
 */
public class LeaveAppBalanceLeave extends HttpServlet {
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
    public LeaveAppBalanceLeave() {
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
		String				strJson			=	null;
		String				startLeaveDt	=	request.getParameter("startLeaveDt");
		String				empNumber		=	request.getParameter("empNumber");

		LeaveDbDao			leaveDbDao		=	new LeaveDbDaoImpl(datasource);
		Gson 				gson 			= 	new Gson();
		try
		{
			if(null != startLeaveDt && ! startLeaveDt.equals(""))
			{
				strJson			=	gson.toJson(leaveDbDao.getLeaveBalance(empNumber, startLeaveDt));
			}
			else
			{
				strJson			=	"";
			}
		}
		catch(Exception ex)
		{
			strJson			=	"";
		}
				
		response.getWriter().print(strJson);
	}


}
