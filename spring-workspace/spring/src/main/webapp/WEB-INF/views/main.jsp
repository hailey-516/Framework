<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp" />
	
	<div class="outer">
		<div class="inner-area">
			<table id="topList" class="table table-hover" align="center">
	                <thead>
	                    <th>글번호</th>
	                    <th>제목</th>
	                    <th>작성자</th>
	                    <th>조회수</th>
	                    <th>작성일</th>
	                    <th>첨부파일</th>
	                </thead>
					
	                <tbody></tbody>
	            </table>
		</div>
		
		<script>
			$(function() {
				boardTopList();	
			});
			
			function boardTopList() {
				$.ajax({
					url: 'board/top5',
					success: function(result) {
						// console.log(result);
						let value = '';
						for(let b of result) {
							value += '<tr>' 
										+ '<td>' + b.boardNo + '</td>'
										+ '<td>' + b.boardTitle + '</td>'
										+ '<td>' + b.boardWriter + '</td>'
										+ '<td>' + b.count + '</td>'
										+ '<td>' + b.createDate + '</td>'
										+ '<td>';
										
									if (b.originName != null) {
										value += '■';	
									}
									value += '</td></tr>';
								}
							$("#topList tbody").html(value);
					},
					error: function(err) {
						console.log("인기 게시글 조회 실패");
						console.log(err);
					}
				});
			}
		</script>
	</div>
	
	<jsp:include page="common/footer.jsp" />
</body>
</html>