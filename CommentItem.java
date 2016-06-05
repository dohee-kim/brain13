package web;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

public class CommentItem{
	private int seqNo;
	private int num;
	private String writer;
	private String content;
	private Date date;
	private Time time;

public CommentItem(){

}
public void setSeqNo(int seqNo){
	this.seqNo=seqNo;
}
public void setNum(int seqNo){
	this.num=seqNo;
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
public int getNum(){
	return this.num;
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

public void updateDB() throws ServletException {
	Connection conn = null;
	Statement stmt=null;
	try{
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
		if(conn == null)
			throw new Exception("연결불가!");
		stmt = conn.createStatement();



		stmt.executeUpdate("update comment set content:='"+this.content+"' where seqno="+this.seqNo+";");

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
			throw new Exception("연결불가!");
		stmt = conn.createStatement();



		stmt.executeUpdate("delete from comment where seqno="+this.seqNo+";");

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
