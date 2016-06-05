<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="bbsItem" class="web.BBSItem" />
<jsp:setProperty name="bbsItem" property="seqNo" value="${param.ITEM_NUM}" />
<% bbsItem.readDB(); %>

<H4>Edit Page</H4>
<FORM ACTION="bbs-edit?SEQ_NO=${param.ITEM_NUM}" METHOD=POST>
	Title : <INPUT TYPE=TEXT NAME=TITLE value=${bbsItem.title}><BR>
	<TEXTAREA COLS=50 ROWS=5 NAME=CONTENT >${bbsItem.content}</TEXTAREA><BR>
	<INPUT TYPE=SUBMIT VALUE='Edit'>
</FORM>