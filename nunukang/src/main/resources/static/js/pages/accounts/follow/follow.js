function goBackPage() {
    history.go(-1);
}
var fold = false;

function test() {
    console.log(fold);

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

function showUserProfile(user_id) {
    window.location.href="/accounts/user/"+user_id;
}


function follow(user_id, my_id) {
    console.log(user_id, my_id);

    var data = {
    
        followUser : {
            id : user_id,
        },

        myUser : {
            id : my_id,
        }
        
    }
    $.ajax({
        url : '/api/v4/accounts/user/follow',
        type : 'POST',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(bool){
            if (bool) {
                alert("팔로우되었습니다.");
                window.location.href = "/accounts/user/"+my_id+"/followers";
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

function unfollow(user_id, my_id) {
    console.log(user_id, my_id);

    var data = {
    
        followUser : {
            id : user_id,
        },

        myUser : {
            id : my_id,
        }
        
    }
    $.ajax({
        url : '/api/v4/accounts/user/unfollow',
        type : 'DELETE',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(bool){
            if (bool) {
                window.location.href = "/accounts/user/"+my_id+"/following";
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

