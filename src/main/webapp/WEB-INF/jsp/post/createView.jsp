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
			
				<div class="post-box rounded">
					<textarea class="form-control mt-3 border-0 resize-none" rows="5" id="contentInput" placeholder="내용을 입력해주세요"></textarea>
					<!-- MIME -->
					<div class="d-flex justify-content-between align-items-center p-2">
						<input type="file" id="fileInput" accept="image/*" style="display: none" multiple>
						<a href="#" id="imageUploadBtn"><i class="bi bi-image image-upload-icon"></i></a>
						<button type="btn" id="saveBtn" class="btn btn-sm btn-primary">업로드</button>
					</div> 
				</div>
				
				<c:forEach var="post" items="${postList }" varStatus="status">
					<div class="list">
						<div class="list-heading d-flex justify-content-between align-items-center mt-3 bg-secondary">
							<span id="userId">${post.post.userName }</span>
							<c:if test="${post.post.userId eq userId }">
								<a class="moreBtn" data-toggle="modal" data-target="#deleteModal" data-post-id="${post.post.id }"><i class="bi bi-three-dots"></i></a>
							</c:if>
						</div>
						<div class="list-image mt-3">
							<img src="${post.post.imagePath }">
						</div>
						<div class="list-like">
						<c:choose>
							<c:when test="${post.like}">
								<a href="#" class="deleteLike" data-post-id="${post.post.id }">
									<i class="bi bi-heart-fill"></i>
								</a>
							</c:when>
							<c:otherwise>
								<a href="#" class="likeBtn" data-post-id="${post.post.id }">
									<i class="bi bi-heart"></i>
								</a>
							</c:otherwise>
						</c:choose>
							<b>${post.likeCount }</b>
						</div>
						<div class="list-content">
							${post.post.content }
						</div>
						<hr/>
						<div class="comment">
							<div class="comment-heading mt-3">
							<c:forEach var="comment" items="${post.commentList }" >
								<div class="mt-1">
									<b>${comment.userName }</b> ${comment.content }
								</div>
							</c:forEach>
							</div>
						</div>
						<div class="input-group mt-2">
							<input id="commentInput${post.post.id }" type="text" class="form-control" placeholder="댓글을 남겨주세요">
							<div class="input-group-append">
								<button type="button" class="btn btn-info commentBtn" data-post-id="${post.post.id }" id="commentBtn">게시</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
		
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#deleteModal">
		  Launch demo modal
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      
		      <div class="modal-body text-center">
		       	<a href="#" id="deletePostBtn">삭제하기</a> 
		      </div>
		     
		    </div>
		  </div>
		</div>
		<script>
			$(document).ready(function() {
				$('#saveBtn').on('click', function() {
					var content = $('#contentInput').val().trim();
					if (content == null || content == "") {
						alert("내용을 입력하세요");
						return;
					}
					
					if ($('#fileInput')[0].files.length === 0) {
						
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
				
				$("#imageUploadBtn").on("click", function() {
					$("#fileInput").click();
				});
				
				$(".commentBtn").on("click", function() {
					var postId = $(this).data("post-id");
					var content = $('#commentInput' + postId).val().trim();
					console.log(content);
					
					if(content == null || content == "") {
						alert("내용을 입력하세요");
						return;
					}
					
					$.ajax({
						method: "post",
						url: "/post/comment/create",
						data: {"content": content, "postId": postId},
						success: function(data) {
							if(data.result == "success") {
								location.reload();
							} else {
								alert("댓글 작성 실패");
							}
						},
						error: function(e) {
							alert("error");
						}
					});
				});
				
				$('.likeBtn').on('click', function(e) {
					e.preventDefault();
					var postId = $(this).data("post-id");
					console.log(postId);
					
					$.ajax({
						type: "get",
						url: "/post/like",
						data: {"postId": postId},
						success: function(data) {
							if(data.result == "success") {
								alert("좋아요 성공");
								location.reload();
							} else {
								alert("좋아요 실패");
							}
						},
						error: function(error) {
							alert("error");
						}
						
					});
				});
				$('.deleteLike').on('click', function(e) {
					e.preventDefault();
					var postId = $(this).data("post-id");
					console.log(postId);
					
					$.ajax({
						type: "post",
						url: "/post/delete_like",
						data: {"postId": postId},
						success: function(data) {
							if(data.result == "success") {
								alert("좋아요 삭제 성공");
								location.reload();
							} else {
								alert("좋아요 삭제 실패");
							}
						},
						error: function(error) {
							alert("error");
						}
						
					});
				});
				
				$('.moreBtn').on('click', function() {
					//postId를 모달의 삭제 버튼에 주입한다.
					var postId = $(this).data("post-id");
					$('#deletePostBtn').data("post-id", postId);
					
				});
				$('#deletePostBtn').on('click', function(e) {
					e.preventDefault();
					var postId = $(this).data("post-id");
					console.log(postId);
					$.ajax({
						type: "post",
						url: "/post/delete",
						data: {"postId": postId},
						success: function(data) {
							if(data.result == "success") {
								alert("삭제 성공");
								location.reload();
							} else {
								alert("삭제 실패");
							}
						},
						error: function(error) {
							alert("ajax 에러 삭제 실패");
						}
					});
				});
			});
		</script>
	</div>
</body>
</html>