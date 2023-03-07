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

function createPost(){
    alert('create');
}




function postLike(obj, postId) {
    obj.style.color = "red";

    console.log(postId);
    $.ajax({
        url : '/api/v4/post/'+postId+"/like",
        type:'PATCH',
        success:function(result){
            console.log(result);
            if (result){
                history.go(0);
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}

function postUnLike(obj, postId) {
    obj.style.color = "gray"

    $.ajax({
        url : '/api/v4/post/'+postId+"/unlike",
        type:'PATCH',
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

