function login(){
    var idValue = $("#id").val();
    var passwordValue = $("#password").val();

    if ((idValue == "") || (passwordValue == "")){
        alert("아이디 혹은 비밀번호가 입력되지않았습니다.");
    } 

}