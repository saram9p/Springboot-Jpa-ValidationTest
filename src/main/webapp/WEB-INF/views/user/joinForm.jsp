<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h1 style="text-align: center;">회원가입</h1>
	<div class="d-flex justify-content-center">
	<form action="/user/join" method="post">
		<div class="form-group">
			<label for="uname">아이디</label> <input type="text"
				class="form-control" id="uname" placeholder="아이디를 입력하세요"
				style="width: 400px;" name="username" required>
		</div>
		<br>

		<div class="form-group">
			<label for="pwd">비밀번호</label> <input type="password"
				class="form-control" id="pwd" placeholder="패스워드를 입력하세요"
				style="width: 400px;" name="password" required>
		</div>
		<br>

		<div class="d-flex justify-content-center">
			<button type="submit" class="btn btn-dark">회원가입 완료</button>
		</div>

	</form>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>