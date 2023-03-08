function isPassword(asValue) {
    var regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

    return regExp.test(asValue);
}

function isEmail(asValue) {
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return regExp.test(asValue);
}

function genderButtonClick(e){
    var genderButtonId = $(e).attr("id");

    $(".genderButton").css("background-color", "white");
    $("#"+genderButtonId).css("background-color", "cornflowerblue");

    $(".gender").val(genderButtonId);
}

var useName = false;
var useEmail = false;
var usePassword = false;

function signUp() {

    if ($("#name").val().length != 0) {
        useName = true;
    }

    var data = {
        name : $("#name").val(),
        email : $("#email").val(),
        password : $("#password").val(),
    }

    console.log(useName, useEmail, usePassword);
    if ((useName) && (useEmail) && (usePassword)) {
        $.ajax({
            url : '/api/v4/accounts/signup',
            type : 'POST',
            dataType : 'JSON',
            contentType : "application/json",
            data : JSON.stringify(data),
            success:function(result){
                if (result) {
                    alert("회원가입이 완료되었습니다.");
                } else {
                    alert("다시 한번 시도해주세요.");
                
                }
            },
            error:function(err){
                alert("다시 한번 시도해주세요.");
            }

        });
    } else {
        alert("이름, 이메일 중복확인, 비밀번호를 제대로 입력해주세요.");
    } 

}

function existsCheckByUserEmail() {
    const email = $('#email').val();

    if (isEmail(email)) {
        $(".emailInfoPTag").text("");

        $.ajax({
            url:'/api/v4/accounts/user/email/exist?email='+email,
            type:'GET',
            cache:true,
            async:false,

            success:function(result){
                if (!result){
                    alert("사용가능한 이메일입니다.");
                    $(".emailInfoPTag").text("");

                    useEmail = true;
                } else {
                    $(".emailInfoPTag").text("사용할 수 없는 이메일입니다.");
                    $(".emailInfoPTag").css("color", "red");

                    useEmail = false;
                }
            },
            error:function(err){
                console.error(err);
            }

        });
    } else {
        $(".emailInfoPTag").text("이메일 형식에 맞지 않습니다.");
        $(".emailInfoPTag").css("color", "red");
    }

}


document.addEventListener("DOMContentLoaded", function(){
    $('#email').change(function(){
        if (useName) {
            useName = false;
        }

        const email = $('#email').val();

        if (isEmail(email)) {
            $(".emailInfoPTag").text("");

        } else {
            $(".emailInfoPTag").text("이메일 형식에 맞지 않습니다.");
            $(".emailInfoPTag").css("color", "red");
        }
    });

    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    $('#password').change(function(){
        var passwordValue = password.value;
        var passwordValueLength = passwordValue.length;
        if (passwordValueLength>=8){
            if (isPassword(passwordValue)){
                $("#passwordInfoPTag1").text("");
            } else {
                $("#passwordInfoPTag1").text("숫자, 영문 대소문자, 특수문자 중 두가지 이상으로 조합해주세요.");
                $("#passwordInfoPTag1").css("color", "red");
            }
        } else {
            $("#passwordInfoPTag1").text("8~30자 이내로 입력해 주십시오.");
            $("#passwordInfoPTag1").css("color", "red");
        }
    });

    $('#password2').change(function(){
        var passwordValue = password.value;
        var password2Value = password2.value;
        
        if (passwordValue != password2Value){
            $("#passwordInfoPTag2").text("비밀번호가 일치하지 않습니다.");
            $("#passwordInfoPTag2").css("color", "red");

            usePassword = false;
        } else {
            $("#passwordInfoPTag2").text("비밀번호가 일치합니다.");
            $("#passwordInfoPTag2").css("color", "blue");

            usePassword = true;
        }
    });



})
