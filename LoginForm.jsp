<%@page import="javax.servlet.forward.*" %>

<%--로그인 및 로그아웃 처리 후에 전페이지로 다시 돌리기 위한 URL 저장 부분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--%>
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
<FORM ACTION=login METHOD=POST>
	<B><FONT size="2"> ID : <INPUT TYPE=TEXT NAME=ID SIZE=5> <BR>
	PW : <INPUT TYPE=PASSWORD NAME=PASSWORD SIZE=5> <BR>
	<%--히튼타입으로 URL을 전달해서 서블릿에서 처리하도록 함--%>
	<INPUT TYPE=hidden name="url" value="${sessionScope.recentUrl}">
	<INPUT TYPE=SUBMIT VALUE='Sign-In' ></FONT></B>
</FORM>