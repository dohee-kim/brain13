<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%--댓글출력을 위해 자바빈객체를 불러와서 디비에 읽음--%>
<jsp:useBean id="commentList" class="web.CommentList" />
<jsp:setProperty name="commentList" property="num" value="${param.SEQ_NO}" />
<% commentList.readDB(); %>

<%--댓글들을 출력하는부분--%>
<c:choose>
	<c:when test="${commentList.size>0}">
		<c:forEach var="cnt" begin="0" end="${commentList.size-1}" >
			<B>${commentList.writer[cnt]} </B><FONT size="2">${commentList.date[cnt]} / ${commentList.time[cnt]}</FONT><BR>
			<%--댓글수정 및 삭제 부분--%>
			<c:if test="${commentList.writer[cnt].equals(sessionScope.LOGIN_ID)}">
				<TABLE>
					<TR>
						<TD>
							<FORM ACTION="WebTemplate.jsp?BODY_PATH=BBSCommentEdit.jsp" METHOD=POST>
								<INPUT TYPE=hidden name="SEQ_NO" value="${param.SEQ_NO}" >
								<INPUT TYPE=hidden name="C_SEQ_NO" value="${commentList.seqNo[cnt]}" >
								<INPUT TYPE=hidden name="CONTENT" value="${commentList.content[cnt]}" >
								<INPUT TYPE=SUBMIT VALUE="Edit"/>
							</FORM>
						</TD>
						<TD>
							<FORM ACTION="comment-delete" METHOD=POST>
								<INPUT TYPE=SUBMIT VALUE="Delete" onclick="return confirm(&quot;삭제하시겠습니까?&quot;)"/>
								<INPUT TYPE=hidden name="SEQ_NO" value="${param.SEQ_NO}" >
								<INPUT TYPE=hidden name="C_SEQ_NO" value="${commentList.seqNo[cnt]}" >
								<INPUT TYPE=hidden name="CONTENT" value="${commentList.content[cnt]}" >
							</FORM>
						</TD>
					</TR>
				</TABLE>
			</c:if>
			<pre>${commentList.content[cnt]}</pre> <BR>----------------------------------------------------------<BR>
		</c:forEach>
	</c:when>
	<c:otherwise>
		No comments...<BR>
	</c:otherwise>
</c:choose>
<BR>

<%--댓글쓰는 부분--%>
<c:choose>
	<c:when test="${sessionScope.LOGIN_ID==null}">
		<B>You need sign-in for writing comment.</B>
	</c:when>
	<c:otherwise>
		<FORM ACTION="comment-input?SEQ_NO=${param.SEQ_NO}" METHOD=POST>
		<B>${sessionScope.LOGIN_ID} </B><BR>
		<TEXTAREA COLS=50 ROWS=2 NAME=COMMENT></TEXTAREA><BR>
		<INPUT TYPE=SUBMIT VALUE='Write Comment' >
		</FORM>
	</c:otherwise>
</c:choose>
<BR>