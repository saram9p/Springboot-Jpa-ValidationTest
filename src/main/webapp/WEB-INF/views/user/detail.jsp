<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>


<div class="container">
 		<div class="text-center" >
 			<h1>로그인 완료 페이지</h1>
 		</div>
 		<div>
 			<p>로그인 완료 페이지 입니다.</p>
 			<button onclick="location.href='/admin/detailForm'">관리자 페이지로 이동</button>
 			
 			<button onclick="changeAdmin();">관리자 권한 얻기</button>
 			<button onclick="changeGuest();">게스트 권한 얻기</button>
 		</div>
</div>
 
 <script>
 	async function changeAdmin(){
 		let response = await fetch("http://localhost:8080/changeAdmin", {
 			method: "put",
 		});
 		
 		let parseResponse = await response.json();
 		
 		if(parseResponse.code == 1) {
 			alert("권한 얻기 성공!");
 		}else{
 			alert("권한 얻기 실패! " + parseResponse.body);
 		}
 	}
 </script>

 <script>
 	async function changeGuest(){
 		let response = await fetch("http://localhost:8080/changeGuest", {
 			method: "put",
 		});
 		
 		let parseResponse = await response.json();
 		
 		if(parseResponse.code == 1) {
 			alert("권한 얻기 성공!");
 		}else{
 			alert("권한 얻기 실패! " + parseResponse.body);
 		}
 	}
 </script>

<%@ include file="../layout/footer.jsp"%>