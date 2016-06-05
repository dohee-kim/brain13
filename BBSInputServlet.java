package web;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Calendar;

public class BBSInputServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		content.replaceAll("\r\n", "<BR>");
		content.replaceAll("\n", "<BR>");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("LOGIN_ID");
		if(id == null)
		{
			PrintWriter out = response.getWriter();

			out.println("<script language='javascript'>");
			out.println("alert('You need to sign-in for writing!'); history.go(-1);");
			out.println("</script>");
			out.close();
			return;
		}

		BBSItem item = new BBSItem();
		item.setTitle(title);
		item.setContent(content);
		item.setWriter(id);
		writeDB(item);

		response.sendRedirect("WebTemplate.jsp?BODY_PATH="+"BBSInputComplete.html");

	}

	private void writeDB(BBSItem item) throws ServletException {
		Connection conn = null;
		Statement stmt=null;

		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
			if(conn == null)
				throw new Exception("연결불가능!");
			stmt = conn.createStatement();


			ResultSet rs = stmt.executeQuery("select * from bbs order by seqno desc limit 1;");
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


			stmt.executeUpdate("insert into bbs (seqno, title, content, writer, wdate, wtime) values ("+item.getSeqNo()+",'"+item.getTitle()+"','"+item.getContent()+"','"+item.getWriter()+"','"+dateS+"','"+timeS+"');");

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