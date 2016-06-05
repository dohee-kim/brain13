package web;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;

public class CommentList{
	private int num, size;
	private ArrayList<Integer> seqNoList = new ArrayList<Integer>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private ArrayList<String> contentList = new ArrayList<String>();
	private ArrayList<Date> dateList = new ArrayList<Date>();
	private ArrayList<Time> timeList = new ArrayList<Time>();


	public CommentList(){

	}
	public void setNum(int a){
		this.num=a;
	}


	public int[] getSeqNo(){
		int[] ret = new int[seqNoList.size()];
		Integer[] a = seqNoList.toArray(new Integer[seqNoList.size()]);
		for(int i=0;i<seqNoList.size();i++){
			ret[i] = a[i];
		}
		return ret;

	}
	public String[] getWriter(){
		return writerList.toArray(new String[writerList.size()]);
	}
	public String[] getContent(){
		return contentList.toArray(new String[contentList.size()]);
	}
	public Date[] getDate(){
		return dateList.toArray(new Date[dateList.size()]);
	}
	public Time[] getTime(){
		return timeList.toArray(new Time[timeList.size()]);
	}

	public int getSize(){
		return this.size;
	}


	public void readDB () throws ServletException{

		Connection conn = null;
		Statement stmt = null;

		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/webpool");
			if(conn == null)
			throw new Exception("DB에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from comment where num="+this.num+";");


			while(rs.next()){
				this.seqNoList.add(rs.getInt("seqno"));
				this.writerList.add(rs.getString("writer"));
				this.contentList.add(rs.getString("content"));
				this.dateList.add(rs.getDate("wdate"));
				this.timeList.add(rs.getTime("wtime"));
			}
			this.size = writerList.size();


		}
		catch (Exception e){
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
}
