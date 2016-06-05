package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class CommentInputServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int num = Integer.parseInt(request.getParameter("SEQ_NO"));
		String content = request.getParameter("COMMENT");
		content.replaceAll("\r\n", "<BR>");
		content.replaceAll("\n", "<BR>");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("LOGIN_ID");
		if(id==null){
			response.sendRedirect("WebTemplate.jsp?BODY_PATH=LoginForm.html");
		}
		else{
		CommentItem item = new CommentItem();

		item.setContent(content);
		item.setWriter(id);
		item.setNum(num);
		writeDB(item);

		response.sendRedirect("WebTemplate.jsp?BODY_PATH=BBSItemView.jsp?SEQ_NO="+num);
		}
	}

	private void writeDB(CommentItem item) throws ServletException {
		Connection conn = null;
		Statement stmt=null;

		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
			if(conn == null)
				throw new Exception("연결불가능!");
			stmt = conn.createStatement();



			ResultSet rs = stmt.executeQuery("select * from comment order by seqno desc limit 1;");
			if(!rs.next())
				item.setSeqNo(1);
			else{
				int currentSeqno = rs.getInt("seqno");
				item.setSeqNo(currentSeqno+1);
			}




			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) +1;
			int date = cal.get(cal.DATE);
			int hour = cal.get(cal.HOUR_OF_DAY);
			int min = cal.get(cal.MINUTE);
			int sec = cal.get(cal.SECOND);

			String dateS = new String(year+"/"+month+"/"+date);
			String timeS = new String(hour+":"+min+":"+sec);


			stmt.executeUpdate("insert into comment (seqno, num, writer, content, wdate, wtime) values ("+item.getSeqNo()+","+item.getNum()+",'"+item.getWriter()+"','"+item.getContent()+"','"+dateS+"','"+timeS+"');");

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