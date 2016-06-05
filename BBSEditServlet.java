package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BBSEditServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int targetSeqNo = Integer.parseInt(request.getParameter("SEQ_NO"));

		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		BBSItem item = new BBSItem();
		item.setSeqNo(targetSeqNo);

		item.readDB();

		item.setTitle(title);
		item.setContent(content);

		item.updateDB();

		response.sendRedirect("WebTemplate.jsp?BODY_PATH="+"BBSItemView.jsp?SEQ_NO="+targetSeqNo);

	}


}