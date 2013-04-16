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

import om.edu.squ.squportal.portlet.leaveapp.bo.Budget;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDao;
import om.edu.squ.squportal.portlet.leaveapp.dao.db.LeaveDbDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * Servlet implementation class LeaveAppResearchServlet
 */
public class LeaveAppResearchServlet extends HttpServlet {
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
    public LeaveAppResearchServlet() {
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
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
		String			researchId			=	request.getParameter("researchId");
		String			strJson				=	null;
		List<Budget>	budgets				=	null;
		Gson 			gson 				= 	new Gson();
		
		response.setContentType("text/html; charset=utf-8");
		
		LeaveDbDao		leaveDbDao	=	new LeaveDbDaoImpl(datasource);
		
		if(null != request.getParameter("researchId") && ! request.getParameter("researchId").trim().equals("") )
		{
			budgets	=	leaveDbDao.getBudget(researchId, locale);
		}
		strJson	=	gson.toJson(budgets);
		response.getWriter().print(strJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
