/**
 * 
 */
const CLIENT_ID = "104780027769-jaqjvo2bi6qtn5gepordg1dqunnvqiae.apps.googleusercontent.com";
const REDIRECT_URI = "http://localhost:7777/login/oauth/google";

// onload 시점에 이벤트 핸들러 추가
window.onload = () => {

    // btn-google이라는 아이디 속성을 가진 요소의 클릭 이벤트
    document.querySelector("#btn-google").onclick = () => {
        // alert("구글 로그인!@");
        const url = "https://accounts.google.com/o/oauth2/v2/auth"
                    + "?client_id=" + CLIENT_ID
                    + "&redirect_uri=" + REDIRECT_URI
                    + "&response_type=code"
                    + "&scope=email profile";

                    location.href = url;    // get 요청이라 location 가능, post 요청이면 AJAX 사용
    }
    
}