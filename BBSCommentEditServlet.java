package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BBSCommentEditServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


		int nextPageSeqNo=Integer.parseInt(request.getParameter("SEQ_NO"));

		int targetSeqNo = Integer.parseInt(request.getParameter("C_SEQ_NO"));
		String content = request.getParameter("CONTENT");
		CommentItem item = new CommentItem();

		item.setSeqNo(targetSeqNo);
		item.setContent(content);

		item.updateDB();

		response.sendRedirect("WebTemplate.jsp?BODY_PATH="+"BBSItemView.jsp?SEQ_NO="+nextPageSeqNo);

	}


}