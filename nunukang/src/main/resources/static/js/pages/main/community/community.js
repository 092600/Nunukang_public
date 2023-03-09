function appearDiv(){
    if ($("#searchPostButton").css("display") == "none"){
        $("#searchPostButton").css("display", "flex");
        $("#createPostButton").css("display", "flex");
    } else {
        $("#searchPostButton").css("display", "none");
        $("#createPostButton").css("display", "none");
    }
}

function disappearButton(){
    if ($("#searchPostButton").css("display") != "none"){
        $("#searchPostButton").css("display", "none");
        $("#createPostButton").css("display", "none");
    }
}

function searchPost(){
    alert('search');
}




function postLike(obj, post_id, user_id) {
    obj.style.color = "red";

    console.log(post_id);

    const data = {
        id : user_id,
    }

    $.ajax({
        url : '/api/v4/community/post/'+post_id+"/like",
        type : 'PATCH',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(bool){
            if (bool) {
                history.go(0);
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

function postUnLike(obj, post_id, user_id) {
    obj.style.color = "gray"

    const data = {
        id : user_id,
    }

    $.ajax({
        url : '/api/v4/community/post/'+post_id+"/unlike",
        type : 'PATCH',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(bool){
            if (bool) {
                history.go(0);
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}


function follow(userEmail){

    var data = {
        "email" : $("#signedUserInput").val(),
    }

    $.ajax({
        url : '/api/v4/accounts/'+userEmail+'/follow/',
        type : 'PUT',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(result){
            if (result){
                history.go(0);
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

function unfollow(userEmail){

    var data = {
        "email" : $("#signedUserInput").val(),
    }

    $.ajax({
        url : '/api/v4/accounts/'+userEmail+'/unfollow/',
        type : 'PUT',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(result){
            if (result){
                history.go(0);
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

