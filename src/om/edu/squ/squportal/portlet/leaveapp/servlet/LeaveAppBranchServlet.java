package om.edu.squ.squportal.portlet.leaveapp.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import om.edu.squ.squportal.portlet.leaveapp.bo.Branch;
import om.edu.squ.squportal.portlet.leaveapp.bo.Department;
import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.bo.Section;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveAppBranchServlet
 */
public class LeaveAppBranchServlet extends HttpServlet {
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
    public LeaveAppBranchServlet() {
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
		logger.info("logger inside branch servlet");
		System.out.println("logger inside branch servlet");

		Locale				locale		=	request.getLocale();			
		String				strJson		=	null;
		String				deptCode	=	null;
		String				branchCode	=	request.getParameter("branchCode");
		Gson 				gson 		= 	new Gson();
		
		LeaveDbDao			leaveDbDao	=	new LeaveDbDaoImpl(datasource);
		
		if(null == request.getParameter("deptCode"))
		{
			List<Department>	departments	=	leaveDbDao.getDepartments(branchCode, locale);
			strJson	=	gson.toJson(departments);													//json objects for departments
		}
		else
		{
			deptCode						=	request.getParameter("deptCode");
			List<Section>		sections	=	leaveDbDao.getSections(deptCode, locale);
			strJson	=	gson.toJson(sections);														//json objects for sections
		}
		
		
		logger.info("json from branch servlet : "+strJson);
		response.getWriter().print(strJson);
	}

}
