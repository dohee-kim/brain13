package web;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

public class BBSItem{
	private int seqNo;
	private String title;
	private String content;
	private String writer;
	private Date date;
	private Time time;

public BBSItem(){

}

public void setSeqNo(int seqNo){
	this.seqNo=seqNo;
}
public void setTitle(String title){
	this.title=title;
}
public void setContent(String content){
	this.content=content;
}
public void setWriter(String Writer){
	this.writer=Writer;
}
public int getSeqNo(){
	return this.seqNo;
}
public String getTitle(){
	return toUnicode(title);
}
public String getWriter(){
	return toUnicode(writer);
}
public String getContent(){
	return toUnicode(content);
}
public Date getDate(){
	return date;
}
public Time getTime(){
	return time;
}

public void readDB() throws ServletException {
	Connection conn = null;
	Statement stmt=null;

	try{
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");

		if(conn==null)
			throw new Exception("��񿬰�Ұ�!");

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from bbs where seqNo= '" + seqNo + "'; ");

		if(rs.next()){
			title = rs.getString("title");
			content = rs.getString("content");
			writer = rs.getString("writer");
			date = rs.getDate("wdate");
			time = rs.getTime("wtime");
		}
	}
	catch( Exception e ){
		throw new ServletException(e);
	}
	finally {
		try{
			stmt.close();
		}
		catch (Exception ignored){
		}
		try{
			conn.close();
		}
		catch(Exception ignored){
		}
	}
}

public void updateDB() throws ServletException {
	Connection conn = null;
	Statement stmt=null;
	try{
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
		if(conn == null)
			throw new Exception("����Ұ�!");
		stmt = conn.createStatement();

		stmt.executeUpdate("update bbs set title:='"+this.title+"', content:='"+this.content+"' where seqno="+this.seqNo+";");

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

public void deleteDB() throws ServletException {
	Connection conn = null;
	Statement stmt=null;
	try{
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
		if(conn == null)
			throw new Exception("����Ұ�!");
		stmt = conn.createStatement();




		stmt.executeUpdate("delete from bbs where seqno="+this.seqNo+";");


		//���� ����¡ �� ȥ���� ���ֱ� ���� ���⼭ �����ͺ��̽��� ������ seqNo ���ķ� ��� �͵��� ������ 1ĭ�� �����ִ� �۾��� �������
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


private String toUnicode(String str){
		if(str==null)
			return null;
		try{
			byte[] b = str.getBytes("ISO-8859-1");
			return new String(b);
		}
		catch(java.io.UnsupportedEncodingException uee){
			System.out.println(uee.getMessage());
			return null;
		}
	}

}
