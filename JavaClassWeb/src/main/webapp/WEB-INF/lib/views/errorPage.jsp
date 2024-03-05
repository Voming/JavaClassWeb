<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ctxPath = request.getContextPath();
pageContext.setAttribute("ctxPath", request.getContextPath()); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Msg</title>
</head>
<body>
	매개변수(인자) 없음. 내장객체 미리 지정히줌. 
	request 
	response 
	${ctxPath }
<%
pageContext.setAttribute("pageScope", "pageScope Value");  //아래에서 가져와 쓸 수 있음

// response.getWriter("aaa"); ==> out 이름의 객체
// 이렇게 사용하면 outputStream에 aaa만 저장되어 출력됨(받은거 없어지고)
out.write("aaa");
// request.getSession(); ==> session 이름의 객체
session.setAttribute("loginId", "abc");

//page === Object 현재 JSP를 class의미로 봤을때 해당 JSP객체
//pageContext === 뢰주 환경/설정 정보를 제공하는 객체(현재 페이지를 기준)
//page, pageContext 같이 움직임

String psvalue = (String) pageContext.getAttribute("pageScope");
%>
jsp 제공 내장 객체의 scope를 분리하여 말하시오
예시) setAttirbute - getAttirbute
page(pageContext) - request(response 전까지 유지)request.getRequestDispachet("aaa.jsp");
- session(브라우저 창 닫기 전까지, removeAttribute())
- response.sendRedirect(url);과 함께사용됨, 로그인(로그아웃) 정보 - application

	<c:choose>
		<%--
		<c:when test="S?=6">화면에 바로 뿌려지는 부분</c:when>
		<c:when test=""></c:when>
		<c:when test=""></c:when>
		 --%>
	</c:choose>
	<h1>${msg}  <%-- page context getAttirbute("msg")
	-> request -> 범위 넓혀가면서 msg 찾음 --%></h1>
</body>
</html>