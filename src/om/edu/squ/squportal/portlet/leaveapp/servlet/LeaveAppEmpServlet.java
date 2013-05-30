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

import om.edu.squ.squportal.portlet.leaveapp.bo.Employee;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveAppEmpServlet
 */
public class LeaveAppEmpServlet extends HttpServlet {
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
     * Default constructor. 
     */
    public LeaveAppEmpServlet() {
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
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String			empNumber		=	request.getParameter("empNumber");
		String			branchCode		=	request.getParameter("branchCode");
		String			localeSrv		=	request.getParameter("localeSrv");
		//String			deptCode	=	request.getParameter("deptCode");
		response.setContentType("text/html; charset=utf-8");
		
		if(null == request.getParameter("localeSrv"))
		{
			localeSrv	=	"en";
		}
		Locale				locale		=	new Locale(localeSrv);
		
		LeaveDbDao		leaveDbDao	=	new LeaveDbDaoImpl(datasource);
		List<Employee>	employees	=	leaveDbDao.getEmployee(empNumber,branchCode,locale);
		Gson gson = new Gson();
		
		String	strJson	=	gson.toJson(employees);
		response.getWriter().print(strJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
