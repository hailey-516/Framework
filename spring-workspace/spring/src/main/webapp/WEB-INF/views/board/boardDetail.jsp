<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	    table {width:100%;}
        table * {margin:5px;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%-- header --%>
    <jsp:include page="../common/header.jsp" />

    <div class="outer">
        <br><br>
        <div class="inner-area">
            <h2>게시글 상세보기</h2>
            <br>
            <a href="list" class="btn btn-secondary" style="float:right;">목록보기</a>
            <br><br>

            <table align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">
                        ${ b.boardTitle }
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        ${ b.boardWriter }
                    </td>
                    <th>작성일</th>
                    <td>
                        ${ b.createDate }
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                        <c:choose>
                        	<c:when test="${not empty b.originName}">
                        		
                        		<a href="<%= request.getContextPath() %>/${ b.changeName }" download="${ b.originName }">${ b.originName }</a>
                        		<%-- 첨부파일: 정적인 내용. 변경되는 것 없음 --%>
                        		<%-- download 속성에 원본 파일명을 작성하면 원본 파일명으로 다운로드됨 --%>
                        		
                        		<%-- 첨부파일이 있는 경우 수정 요청 시 전달 --%>
                        		<input type="hidden" name="originName" value="${ b.originName }" />
                        		<input type="hidden" name="changeName" value="${ b.changeName }" />
                        	</c:when>
                        	<c:otherwise>
                        		첨부파일 없음
                        	</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <p style="height:150px;">
                            ${ b.boardContent }
                        </p>
                    </td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 작성자와 로그인한 계정이 동일한 경우만 표시 -->
                <c:if test="${ b.boardWriter eq loginUser.userId }">
	                <%-- <a href="updateForm?bno=${ b.boardNo }" class="btn btn-primary">수정</a> 
	                	위 방식으로 하면 get 방식으로 uri 요청하는 구조 노출, post로 보내야 함
	                --%>
	                <a class="btn btn-primary" onclick="postSubmit('update');">수정</a>
	                <a class="btn btn-danger" onclick="postSubmit('delete');">삭제</a>
                </c:if>
            </div>
            <br><br>
            
            <form action="" method="post" id="postForm">
            	<input type="hidden" name="bno" value="${ b.boardNo }" />
            </form>
            
            <script>
            	function postSubmit(type) {
            		const postForm = document.getElementById("postForm");
            		// document.querySelector("#postForm")
            		
            		if (type == 'update') {
            			// 게시글 수정 페이지로 요청
						postForm.action = 'updateForm';		
            		} else if (type == 'delete') {
            			// 게시글 삭제 요청
            			postForm.action = 'delete';
            		}
            		
            		postForm.submit();
            	}
            </script>

			<table id="replyArea" class="table" align="center">
				<thead>
					<%--
                		* 로그인한 사용자만 댓글을 작성할 수 있도록
                		=> 만약, 로그인을 하지 않았다면
                								입력창 부분에 '로그인 후 이용 가능합니다.' 메시지를 표시하고 입력하지 못하도록
                								[등록] 버튼을 비활성화
                	--%>
					<c:choose>
						<c:when test="${ not empty loginUser }">
							<%-- 로그인 했을 경우 --%>
							<tr>
								<th colspan="2"><textarea name="" id="content" cols="55"
										rows="2" class="form-control" style="resize: none;"></textarea>
								</th>
								<th style="vertical-align: middle;">
									<button class="btn btn-secondary" onclick="addReply();">등록</button>
								</th>
							</tr>
						</c:when>

						<c:otherwise>
							<!-- 로그인하지 않았을 경우 -->
							<tr>
								<th colspan="2"><textarea name="" id="content" cols="55"
										rows="2" class="form-control" style="resize: none;"
										placeholder="로그인 후 이용 가능합니다." readonly></textarea></th>
								<th style="vertical-align: middle;">
									<button class="btn btn-secondary" disabled>등록</button>
								</th>
							</tr>

						</c:otherwise>
					</c:choose>

					<tr>
						<td colspan="3">댓글 (<span id="rcount">0</span>)
						</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<br> <br>

		</div>


    </div>
    <script>
    	// onload
    	$(function(){
    		// 해당 게시글의 댓글 목록 (ajax)
    		selectReplyList();
    	});
    	
    	function selectReplyList() {
    		$.ajax({
    			url: "rlist",
    			data: { bno: ${b.boardNo} },
    			success: function(result) {
    				console.log(result);
    				
    				// 댓글 목록이 있을 경우 화면에 표시
    				// => 댓글 목록 없을 경우 : 빈 배열
    				// => 댓글 목록 있는 경우 : 배열에 데이터 담겨져 있음
    				if( result != null && result.length > 0) {
    					// length로 간단하게 확인할 수 있지만 응답 데이터가 잘못 올 수도 있기 때문에 널 체크를 하는 편
    					let replyValue = "";
    					for (let r of result) {
    						replyValue += "<tr>"
    										+ "<th>" + r.replyWriter + "</th>"
    										+ "<td>" + r.replyContent + "</td>"
    										+ "<td>" + r.createDate + "</td>"
    										+ "</tr>";
    						
    					/*
    					<tr>
                        	<th>user02</th>
                        		<td>댓글-----내용</td>
                        		<td>2024-04-15</td>
                    	</tr>
    					*/
    					}
    					
    					$("#replyArea tbody").html(replyValue);
    					$("#rcount").text(result.length);
    				}
    				
    			},
    			error: function(err) {
    				console.log("댓글 조회 실패!");
    				console.log(err);
    			}
    		});
    	}
    	
    	
    	function addReply() {
			<!-- 등록 버튼 클릭 시 aleart 실행 * 입력된 내용을 출력 -->
    		// alert( $("#replyArea #content").val() );
    		
    		// 입력된 내용이 있을 경우 추가 요청하도록
    		// value 값에 trim() 공백일 시 댓글 못 달게 하도록
    		if($("#replyArea #content").val().trim().length > 0) {
    		
	    		// 댓글 추가 요청(ajax)
	    		// => /spring/board/rinsert?replyContent=입력된내용&refBno=게시글번호&replyWriter=작성자
	    		$.ajax({
	    			url: "rinsert",		// 요청 주소
	    			data: {				// 전달 데이터
	    					replyContent: $("#replyArea #content").val(),
	    					refBno: '${ b.boardNo }',
	    					replyWriter: '${ loginUser.userId }'
	    					},
	    			success: function(result) {		// 요청 성공 시 (통신 성공)
	    				console.log(result);
	    				// 댓글 추가 성공 시, 입력창 부분을 초기화 댓글 목록 다시 조회
	    				if(result === 'success') {
	    					$("#replyArea #content").val("");
	    					selectReplyList();
	    				} else {
	    				// 댓글 추가 실패 시, '댓글 추가에 실패했습니다.' 메시지를 출력(alert)
	    				alert("댓글 추가에 실패했습니다.");
	    				}
	    			},
	    			error: function(err) {			// 요청 실패 시 (통신 실패)
	    				console.log("댓글 추가 요청 실패");
	    				console.log(err);
	    			}
	    		});
    		} else {
    			alert("내용 입력 후 추가 가능합니다.");
    		}
    	}
    </script>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />    
</body>
</html>