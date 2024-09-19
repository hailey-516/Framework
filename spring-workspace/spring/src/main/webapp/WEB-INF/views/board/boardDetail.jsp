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
                    <tr>
                        <th colspan="2">
                            <textarea name="" id="content" cols="55" rows="2" class="form-control" style="resize: none;"></textarea>
                        </th>
                        <th style="vertical-align:middle;">
                            <button class="btn btn-secondary">등록</button>
                        </th>
                    </tr>
                    <tr>
                        <td colspan="3">댓글 (<span id="rcount">3</span>)</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>user02</th>
                        <td>댓글-----내용</td>
                        <td>2024-04-15</td>
                    </tr>
                    <tr>
                        <th>user01</th>
                        <td>ㅋㅋㅋㅋㅋㅋㅋ</td>
                        <td>2024-04-13</td>
                    </tr>
                    <tr>
                        <th>admin</th>
                        <td>댓글테스트ㅋㅋ</td>
                        <td>2024-04-07</td>
                    </tr>                         
                </tbody>
            </table>     
            <br><br>
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
    			},
    			error: function(err) {
    				console.log("댓글 조회 실패!");
    				console.log(err);
    			}
    		});
    	}
    </script>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />    
</body>
</html>