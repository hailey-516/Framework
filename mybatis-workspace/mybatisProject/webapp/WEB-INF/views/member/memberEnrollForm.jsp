<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.outer {
		background-color: rgb(74, 210, 255);
		color: white;
		width: 1000px;
		margin: auto;
		margin-top: 50px;
	}

	#enroll-form table { margin: auto; }
	#enroll-form input { margin: 5px; }
</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2  align="center">회원가입</h2>

		<form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">
			<!-- table>(tr>td*2)*8 -->
			<table>
				<tr>
					<td>* 아이디</td>
					<td>
						<input type="text" name="userId" maxlength="12" required>
					</td>
					<td>
						<input type="button" value="중복체크" onclick="idCheck();">
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td>
						<input type="password" name="userPwd" maxlength="15" required>
					</td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td>
						<input type="password" name="userPwdCheck" maxlength="15" required>
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>
						<input type="text" name="userName" maxlength="6" required>
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="tel" name="phone" placeholder="- 포함해서 입력">
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" name="email">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<input type="text" name="address">
					</td>
				</tr>
				<tr>
					<td>취미</td>
					<td>
						<!-- (input:checkbox[name=interest]+label)*5 -->
						<input type="checkbox" name="interest" id="baseball" value="야구">
						<label for="baseball">야구</label>

						<input type="checkbox" name="interest" id="cartoon" value="만화">
						<label for="cartoon">만화</label>

						<input type="checkbox" name="interest" id="soccer" value="축구">
						<label for="soccer">축구</label>

						<br>

						<input type="checkbox" name="interest" id="study" value="공부">
						<label for="study">공부</label>

						<input type="checkbox" name="interest" id="game" value="게임">
						<label for="game">게임</label>
					</td>
				</tr>
			</table>

		<br><br>

		<div align="center">
		<button type="submit" onclick="return pwdCheck();" disabled>회원가입</button>
		<button type="reset">초기화</button>
		</div>

		<br><br>
	</form>
	</div>

	<script>
		function pwdCheck() {
			const pwd = document.querySelector("#enroll-form input[name=userPwd]").value;
			const pwdCheck = document.querySelector("#enroll-form input[name=userPwdCheck]").value;

			if (pwd != pwdCheck) {
				alert("비밀번호와 비밀번호 확인 입력 값이 다릅니다.");
				return false;
			}
		};

		function idCheck() {
			// 중복체크 버튼 클릭 시 사용자가 입력한 아이디 값을 서버로 보내서
			// 중복되는 데이터가 있는지 조회한 후에 결과를 받을 것임
			
			// (1) 사용 가능 => 사용 가능합니다. 메시지 출력, 회원가입 버튼을 활성화
			// (2) 사용 불가능 => 사용할 수 없는 아이디입니다. 메시지 출력, 다시 입력할 수 있도록 유도

			// const userId = $("#enroll-form input[name=userId]").val();
			const idEle = $("#enroll-form input[name=userId]");

			// console.log("userId: " + userId);
			console.log("userId: " + idEle.val());

			console.log("*** ajax 요청 전 ***");
			$.ajax({
				url: 'idCheck.me',
				type: 'get',
				// data: { userId: userId },
				data: {userId: idEle.val() },
				success: function(result) {
					// result ==> 중복된 아이디가 있을 경우 ("NNN"), 없을 경우 ("NNY")
					console.log(result);
					if(result == 'NNY') {
						alert("사용 가능합니다.");
						$("#enroll-form button[type=submit]").removeAttr("disabled");

					} else {
						alert("사용할 수 없는 아이디입니다. 다시 입력해주세요.");

						// $("#enroll-form input[name=userId]").focus(); -> jQuery 방식으로 접근했을 때 사용 가능...
						idEle.focus();
					}
				},
				error: function(err) {
					console.log(err);
				}
			});
		}
	</script>
</body>
</html>