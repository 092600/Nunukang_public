function goBackPage() {
    history.go(-1);
}

var fold = false;

function foldUserIntroDiv() {
    $(".userIntro2Div").toggle();

    $("#fold").remove();

    if (fold == true) {
        $(".usernameFunctionalDiv").append('<i class="fa-solid fa-chevron-down" id="fold"></i>');
        
        fold = false;
    } else {
        $(".usernameFunctionalDiv").append('<i class="fa-solid fa-chevron-up" id="fold"></i>');
        fold = true;
    }
    
}

function getSettingsDiv() {
    console.log($(".settingFunctionalDiv").css("display"));
    if ($(".settingFunctionalDiv").css("display") == "none") {
        $(".settingFunctionalDiv").css("display", "flex");
    } else {
        $(".settingFunctionalDiv").css("display", "none");
    }

}

function showUserWritePosts() {
    window.location.href = "/community/myposts";
}

function showFollowers(user_id) {
    window.location.href = "/accounts/user/"+user_id+"/followers";
}

function showFollowing(user_id) {
    window.location.href = "/accounts/user/"+user_id+"/following";
}


function deleteUser() {
    var password = prompt("회원탈퇴를 위해 현재 사용하고있는 비밀번호를 입력해주세요");

    const data = {
        id : $("#user_id").val(),
        password : password
    }

    $.ajax({
        url : '/api/v4/accounts/user',
        type : 'DELETE',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(bool){
            if (bool) {
                window.location.href="/logout";
            } else {
                alert("패스워드가 일치하지 않습니다.");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

