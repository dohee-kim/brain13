<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		<H4  >Board List</H4>
		<TABLE border=1 >
			<TR align="center">
				<TD width=40>No</TD>
				<TD width=300>Title</TD>
				<TD width=80>Writer</TD>
				<TD width=90>Date</TD>
				<TD width=70>Time</TD>
			</TR>
			<c:forEach var="cnt" begin="0" end="${BBS_LIST.listSize - 1}">
				<TR align="center">
					<TD>${BBS_LIST.seqNo[cnt]}</TD>
					<TD align="left">
						<A href='WebTemplate.jsp?BODY_PATH=BBSItemView.jsp?SEQ_NO=${BBS_LIST.seqNo[cnt]}'>
						${BBS_LIST.title[cnt]}
						</A>
					</TD>
					<TD>${BBS_LIST.writer[cnt]}</TD>
					<TD>${BBS_LIST.date[cnt]}</TD>
					<TD>${BBS_LIST.time[cnt]}</TD>
				</TR>
			</c:forEach>
		</TABLE>


		<c:choose>
			<c:when test="${sessionScope.LOGIN_ID==null}">
				<B>WRITE<FONT size="2">(need sign-in)</FONT></B> <BR>
			</c:when>
			<c:otherwise>
				<A href="WebTemplate.jsp?BODY_PATH=BBSInput.html"><B>WRITE</B></A><BR>
			</c:otherwise>
		</c:choose>


		<BR>


		<c:if test="${!BBS_LIST.firstPage}">
			<A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[0]+6}'>Prev</A>
		</c:if>

		<c:choose>
			<c:when test="${BBS_LIST.dbSize%5==0}">
				<c:set var="allPageNum" value="${BBS_LIST.dbSize/5 - (BBS_LIST.dbSize/5)%1}" />
				<c:set var="nowPageIndex" value="${(BBS_LIST.dbSize-BBS_LIST.seqNo[0])/5- (BBS_LIST.dbSize-BBS_LIST.seqNo[0])/5%1+1}" />
			</c:when>
			<c:otherwise>
				<c:set var="allPageNum" value="${BBS_LIST.dbSize/5 - (BBS_LIST.dbSize/5)%1 +1}" />
				<c:set var="nowPageIndex" value="${(BBS_LIST.dbSize-BBS_LIST.seqNo[0])/5- ((BBS_LIST.dbSize-BBS_LIST.seqNo[0])/5)%1 +1}" />
			</c:otherwise>
		</c:choose>



		<c:forEach var="cnt" begin="1" end="${allPageNum}">
			<c:choose>
				<c:when test="${cnt==nowPageIndex}">
					${cnt}
				</c:when>
				<c:otherwise>
					<A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.dbSize +1 - (cnt-1)*5}'>${cnt}</A>
				</c:otherwise>
			</c:choose>
		</c:forEach>



		<c:if test="${!BBS_LIST.lastPage}">
			<A href='bbs-list?LAST_SEQ_NO=${BBS_LIST.seqNo[BBS_LIST.listSize -1]}'>Next</A>
		</c:if>


