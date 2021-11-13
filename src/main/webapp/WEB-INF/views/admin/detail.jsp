<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<%
	String strReferer = request.getHeader("referer");
	if(strReferer == null){
		%><script type="text/javascript">
		alert("정상적인 경로를 통해 다시 접근해 주십시오.");
		document.location.href="/user/loginForm";
		</script><%
		return;
	}
%>

<div class="container">
 		<div class="text-center" >
 			<h1>관리자 페이지</h1>
 		</div>
 		<div>
 			<p>관리자 페이지 입니다.</p>
 		</div>
</div>
 


<%@ include file="../layout/footer.jsp"%>