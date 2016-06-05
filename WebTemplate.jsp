<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%--로그아웃 서블릿으로 보내기 위해서 현재 URL저장!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
<%
	//서블릿의 경우 이렇게 처리해야 출력페이지인 jsp가 아닌 서블릿페이지로 정상 되돌아감
	String url = (String)request.getAttribute( "javax.servlet.forward.request_uri" );

	//위의 방법으로는 jsp페이지가 null이 리턴되므로 null일경우 jsp페이지를 대상으로 한 메소드를 호출로 해결
	if(url==null)
		url = request.getRequestURL().toString();

	//잘려나간 쿼리스트링을 붙여줌
	if ( request.getQueryString() != null )
	url = url + "?" + request.getQueryString();

	//세션에 저장함
	session.setAttribute("recentUrl", url);
%>

<HTML>
	<HEAD>
		<style>
		a {
			text-decoration: none;
			<--링크 밑줄 없애줌-->
		}
		</style>
		<TITLE>Web Board</TITLE>
	</HEAD>
	<BODY align="center">
		<A href="WebTemplate.jsp?BODY_PATH=Intro.html"><H1><B>HomePage</B></H1></A>
		<TABLE  border=1 cellpadding=10 align="center" width=1000>
			<TR >
				<TD width=150 valign=top align="center">

					<%--로그인 및 로그아웃 처리에 대한 부분--%>
					<TABLE width= 140 cellpadding=5 border=1 align="center"><TD align="center">
					<c:choose>
						<c:when test="${sessionScope.LOGIN_ID==null}">
							<jsp:include page='LoginForm.jsp' />
						</c:when>
						<c:otherwise>
							Welcome, <BR><B> ${sessionScope.LOGIN_ID}!</B><BR>
							<FORM ACTION="logout" METHOD=POST>
								<INPUT TYPE=SUBMIT VALUE='Sign-out' >
								<INPUT TYPE=hidden name="exUrl" value="${sessionScope.recentUrl}">
							</FORM>
						</c:otherwise>
					</c:choose>
					</TD></TABLE>
					<BR>

					<%--좌측 메인메뉴 출력--%>
					<B><FONT size="4">MENU</FONT></B><BR>------<BR>
					<A HREF="WebTemplate.jsp?BODY_PATH=Intro.html"><B><FONT size="3">INTRO</FONT></B></A><BR>
					<A href="bbs-list"><B><FONT size="3">BOARD</FONT></B></A><BR>
				</TD>

				<%--몸통부분 BODY PATH include해주는 부분--%>
				<TD valign=top width=650>
					<c:if test="${param.BODY_PATH==null}">
						<jsp:include page="Intro.html" />
					</c:if>
					<jsp:include page="${param.BODY_PATH}" />
				</TD>
			</TR>
		</TABLE>
		<H5>Copyright@ 1991-2015 Dohee</H5>
	</BODY>
</HTML>