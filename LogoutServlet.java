package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class LogoutServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String exUrl = request.getParameter("exUrl");
		HttpSession session = request.getSession();
		session.setAttribute("LOGIN_ID", null);
		response.sendRedirect(exUrl);
	}
}