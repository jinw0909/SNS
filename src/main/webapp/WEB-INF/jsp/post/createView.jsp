<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5">
				<!-- <h1 class="text-center">메모입력</h1> -->
				<!-- 제목, 내용, 파일 업로드 -->
				<div class="post-box">
					<div class="image-box">					
						<textarea class="form-control mt-3" rows="5" id="contentInput" placeholder="내용을 입력해주세요"></textarea>
						<!-- MIME -->
						<div class="d-flex justify-content-between">
							<input type="file" id="fileInput" accept="image/*" multiple>
							<button type="btn" id="postBtn" class="btn btn-primary">업로드</button>
						</div> 
					</div>
				</div>
				<c:forEach var="post" items="${postList }" varStatus="status">
					<div class="list">
						<div class="list-heading d-flex justify-content-between align-items-center mt-3 bg-secondary">
							<span id="userId">${post.userName }</span>
							<i class="bi bi-three-dots"></i>
						</div>
						<div class="list-image mt-3">
							<img src="${post.imagePath }">
						</div>
						<div class="list-like">
							<i class="bi bi-heart"></i>
							<b>좋아요 11개</b>
						</div>
						<div class="list-content">
							${post.content }
						</div>
						<div class="comment">
							<div class="comment-heading bg-secondary mt-3">댓글</div>
						</div>
						<div class="d-flex justify-content-between mt-3">
							<input id="commentInput" type="text" class="form-control" placeholder="댓글을 남겨볼까요?">
							<button type="button" class="btn btn-info" id="commentBtn">게시</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
		<script>
			$(document).ready(function() {
				$('#saveBtn').on('click', function() {
					var content = $('#contentInput').val().trim();
					if (content == null || content == "") {
						alert("내용을 입력하세요");
						return;
					}
					var formData = new FormData();
					formData.append("content", content);
					formData.append("file", $('#fileInput')[0].files[0]);
					
					$.ajax({
						enctype: "multipart/form-data",
						method: "post",
						url: "/post/create",
						processData: false,
						contentType: false,
						data: formData,
						success: function(data) {
							if (data.result == "success") {
								location.reload();
								alert("글 쓰기 성공");
								
							} else {
								alert("글 쓰기에 실패했습니다.");
							}
						},
						error: function(error) {
							alert("에러발생" + error);
						}
					});
				});
			});
		</script>
	</div>
</body>
</html>