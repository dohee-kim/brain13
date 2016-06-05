<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>


<H4>Edit Comment</H4>
<FORM ACTION="comment-edit" METHOD=POST>
	<TEXTAREA COLS=50 ROWS=5 NAME=CONTENT >${param.CONTENT}</TEXTAREA><BR>
	<INPUT TYPE=hidden name="SEQ_NO" value="${param.SEQ_NO}" >
	<INPUT TYPE=hidden name="C_SEQ_NO" value="${param.C_SEQ_NO}" >
	<INPUT TYPE=SUBMIT VALUE='Edit'>
</FORM>