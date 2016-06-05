package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String exUrl = request.getParameter("url");

		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");
		String result;
		if(checkLoginInfo(id, password)){
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_ID", id);
			response.sendRedirect(exUrl);
		}
		else {
			PrintWriter out = response.getWriter();

			out.println("<script language='javascript'>");
			out.println("alert('Check your ID and PASSWORD!'); history.go(-1);");
			out.println("</script>");
			out.close();


		}


	}

	private boolean checkLoginInfo(String id, String password) throws ServletException {
		Connection conn = null;
		Statement stmt=null;

		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
			if(conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select password from userinfo where id = '"+id+"';");
			if(!rs.next())
				return false;

			String correctPassword = rs.getString("password");
			if(password.equals(correctPassword))
				return true;
			else
				return false;
		}
		catch(Exception e){
			throw new ServletException(e);
		}
		finally{
			try{
				stmt.close();
			}
			catch(Exception ignored){

			}
			try{
				conn.close();
			}
			catch(Exception ignored){

			}
		}
	}
}