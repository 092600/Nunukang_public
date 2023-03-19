$(document).ready(function(){
    var changable = false;
});

function changable() {
    changable = true;
}



function getProfileImg() {
    $("#profileImg").click();
}

function changeProfileImg(input) {
    if (input.files && input.files[0]) {
        
        var reader = new FileReader();
        reader.onload = function(e) {
          document.getElementById('profileImgDiv').src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
      } else {
        document.getElementById('profileImgDiv').src = "";
      }

}

function getBackgroundImg() {
    $("#backgroundImg").click();
}

function changeBackgroundImg(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
          document.getElementById('backgroundImgDiv').src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
      } else {
        document.getElementById('backgroundImgDiv').src = "";
      }
}


function changeProfile() {
    const formData = new FormData();

    const profileImg = document.getElementById("profileImg").files[0];
    const backgroundImg = document.getElementById("backgroundImg").files[0];

    const user = {
        id : $("#user_id").val(),
        profile : {
            intro : $("#userIntroTextarea").val(),
        },
    }

    formData.append("user", new Blob([JSON.stringify(user)], {type : "application/json"}));

    if (profileImg != undefined) {
        formData.append("profileImage", profileImg);
    }

    if (backgroundImg != undefined) {
        formData.append("backgroundImage", backgroundImg);
    }

    if (confirm("프로필을 변경하시겠습니까?")) {
        $.ajax({
            url : '/api/v4/accounts/mypage/profile',
            type : 'PUT',
            enctype: 'multipart/form-data',
            async: true, 
            processData: false,
            contentType: false, 
            data: formData,
            success:function(result){
                window.location.href = "/accounts/mypage/profile";
            },
            error:function(err){
                alert("다시 한번 시도해주세요.");
            }
    
        });
    }
    

}

function goBackPage() {
    history.go(-1);
}