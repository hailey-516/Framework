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
        
        // 비동기 통신 => ajax

        $.ajax ({
            url: 'mail',    // 요청 주소(url)
            type: 'post',   // 요청 방식 (type|method)
            data: {         // 요청 시 전달 데이터(파라미터) (data)
                email: $("#email").val()
            },
            success: (result) => {
                alert(result);
            },
            error: (err) => {
                console.log(err);
            }
        });
    }
    
    // [확인] 버튼 클릭 이벤트 -> 알림창 표시 : "확인"
    $("#btn-check").click(()=>{
        alert("확인");
    })
    
}