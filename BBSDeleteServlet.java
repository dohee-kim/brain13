package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BBSDeleteServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int targetSeqNo = Integer.parseInt(request.getParameter("SEQ_NO"));


		BBSItem item = new BBSItem();
		item.setSeqNo(targetSeqNo);

		item.deleteDB();

		response.sendRedirect("bbs-list");

	}


}