<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="bbsItem" class="web.BBSItem" />
<jsp:setProperty name="bbsItem" property="seqNo" value="${param.SEQ_NO}" />
<% bbsItem.readDB(); %>



<B>[Title]</B> ${bbsItem.title} <BR>
<B>[Writer]</B> ${bbsItem.writer}
<B>[Date/Time]</B> ${bbsItem.date} ${bbsItem.time} <BR>
<c:choose>
	<c:when test="${bbsItem.writer.equals(sessionScope.LOGIN_ID)}">
	<TABLE>
		<TR>
			<TD>
				<FORM ACTION="WebTemplate.jsp?BODY_PATH=BBSItemEdit.jsp?ITEM_NUM=${param.SEQ_NO}" METHOD=POST>
					<INPUT TYPE=SUBMIT VALUE="Edit"/>
				</FORM>
			</TD>
			<TD>
				<FORM ACTION="delete-bbs" METHOD=POST>
					<INPUT TYPE=SUBMIT VALUE="Delete" onclick="return confirm(&quot;삭제하시겠습니까?&quot;)"/>
					<INPUT TYPE=hidden name="SEQ_NO" value="${param.SEQ_NO}" >
				</FORM>
			</TD>
		</TR>
	</TABLE>


	</c:when>
	<c:otherwise>
		<BR>
		<BR>
	</c:otherwise>
</c:choose>
<B>--------------------------------------------------------</B><BR><BR>
<pre>
${bbsItem.content}<BR><BR>
</pre>
<B>--------------------------------------------------------</B><BR><BR><BR>
<B>Comment List</B>
<BR><BR>
<jsp:include page="CommentListView.jsp" />

