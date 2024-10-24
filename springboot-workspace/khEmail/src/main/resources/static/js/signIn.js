/**
 * 
 */
// onload 시점 => 모든 요소들이 브라우저에 표시된 상태
onload = () => {
    // [인증 요청] 버튼 클릭 이벤트 -> 알림창 표시 : "인증 요청"
    // document.getElementById("btn-auth").onclick
    // $("#btn-auth").click
    document.querySelector("#btn-auth").onclick = () => {
        // alert("인증 요청");

        // (POST) /mail
        //        {email: {인증코드를 받을 이메일주소}}

        // 스피너 활성화 => 요청이 진행중임을 표시하기 위해
        $("#spinner").removeClass("d-none");
        // [인증요청] 버튼 활성화
        $("btn-auth").addClass("disabled");
        
        // 비동기 통신 => ajax

        $.ajax ({
            url: 'mail',    // 요청 주소(url)
            type: 'post',   // 요청 방식 (type|method)
            data: {         // 요청 시 전달 데이터(파라미터) (data)
                email: $("#email").val()
            },
            success: (result) => {

                // 요청 완료 후 스피너: 비활성화, 인증요청버튼: 활성화
                $("#spinner").addClass("d-none");
                $("#btn-auth").removeClass("disabled");

                alert(result);
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
    
    // [확인] 버튼 클릭 이벤트 -> 알림창 표시 : "확인"
    $("#btn-check").click(()=>{
        // alert("확인");

        // 입력된 코드 값을 /check 요청으로 검증
        $.ajax({
            url: '/check',
            method: 'post',
            data: {
                code: $("#authCode").val(),
                email: $("#email").val()
            },
            success: (data) => {
                alert(data);
            },
            error: (err) => {
                console.log(err);
            }
        });
    })
    
}