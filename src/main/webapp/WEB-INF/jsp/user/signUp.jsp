<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 - 회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>
		<section class="content d-flex justify-content-center">
			<div class="signup-box h-100 d-flex align-items-center justify-content-center">
				<div class="w-100">
					<h1 class="text-center">회원가입</h1>
					<form id="signupForm">
						<input type="text" id="loginIdInput" name="loginId" class="form-control mt-3" placeholder="아이디">
						<input type="password" id="passwordInput" name="password" class="form-control mt-3" placeholder="패스워드">
						<input type="password" id="passwordConfirmInput" class="form-control mt-3" placeholder="패스워드 확인">
						<small id="errorPassword" class="text-danger d-none">비밀번호가 일치하지 않습니다.</small>
						<input type="text" id="nameInput" name="name" class="form-control mt-3" placeholder="이름">
						<input type="text" id="emailInput" name="email" class="form-control mt-3" placeholder="이메일">
						<button id="signUpBtn" type="submit" class="btn btn-primary btn-block mt-3 w-100">회원가입</button>
					</form>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
	</div>
	<script>
		$(document).ready(function() {
			$('#signupForm').on('submit', function(e) {
				
				e.preventDefault();
				
				var loginId = $('#loginIdInput').val();
				var password = $('#passwordInput').val();
				var passwordConfirm = $('#passwordConfirmInput').val();
				var name = $('#nameInput').val().trim();
				var email = $('#emailInput').val().trim();
				
				if (loginId == null || loginId == "") {
					alert("아이디를 입력하세요");
					return false;
				}
				if (password == null || password == "") {
					alert("비밀번호를 입력하세요");
					return false;
				}
				if(password != passwordConfirm) {
					$("#errorPassword").removeClass("d-none");
					return false;
				} else {
					$("#errorPassword").addClass("d-none");
				}
				if (name == null || name == "") {
					alert("이름을 입력하세요");
					return false;
				}
				if (email == null || email == "") {
					alert("이메일을 입력하세요");
					return false;
				}
				
				$.ajax({
					type: "post",
					url: "/user/sign_up",
					data:{"loginId": loginId, "password": password, "name": name, "email": email},
					success:function(data) {
						if(data.result == "success") {
							location.href="/user/signin_view";
						} else {
							alert("회원 가입 실패");
						}
					}, 
					error:function(error) {
						alert("회원 가입 실패", error);
					}
					
					
				});
			});
		});
	</script>
</body>
</html>