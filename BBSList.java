package web;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;

public class BBSList{
	private ArrayList<Integer> seqNoList = new ArrayList<Integer>();
	private ArrayList<String> titleList = new ArrayList<String>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private ArrayList<Date> dateList = new ArrayList<Date>();
	private ArrayList<Time> timeList = new ArrayList<Time>();

	private int dbSize;
	private int dbCount;
	private boolean lastPage = false;
	private boolean firstPage = false;

	public BBSList(){

	}
	public void setSeqNo(int index, int seqNo){
		this.seqNoList.add(index, seqNo);
	}
	public void setTitle(int index, String title){
		this.titleList.add(index, title);
	}
	public void setWriter(int index, String writer){
		this.writerList.add(index, writer);
	}
	public void setDate(int index, Date date){
		this.dateList.add(index, date);
	}
	public void setTime(int index, Time time){
		this.timeList.add(index, time);
	}
	public void setLastPage(boolean lastPage){
		this.lastPage=lastPage;
	}
	public void setFirstPage(boolean firstPage){
		this.firstPage=firstPage;
	}
	public void setDbSize(int size){
		this.dbSize = size;
	}
	public void setDbCount(int count){
		this.dbCount = count;
	}


	public int getDbSize(){
		return this.dbSize;
	}
	public int getDbCount(){
		return this.dbCount;
	}

	public int[] getSeqNo(){
		int[] ret = new int[seqNoList.size()];
		Integer[] a = seqNoList.toArray(new Integer[seqNoList.size()]);
		for(int i=0;i<seqNoList.size();i++){
			ret[i] = a[i];
		}
		return ret;

	}
	public String[] getTitle(){
		return titleList.toArray(new String[titleList.size()]);
	}
	public String[] getWriter(){
		return writerList.toArray(new String[writerList.size()]);
	}
	public Date[] getDate(){
		return dateList.toArray(new Date[dateList.size()]);
	}
	public Time[] getTime(){
		return timeList.toArray(new Time[timeList.size()]);
	}
	public boolean isLastPage(){
		return lastPage;
	}
	public boolean isFirstPage(){
		return firstPage;
	}
	public int getListSize(){
		return seqNoList.size();
	}



}
