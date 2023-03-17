function activateCreateButton(){
    if ($("#commentCreateButton").text().length > 0){
        $("#commentCreateButton").attr("disabled", false)
    } else {
        $("#commentCreateButton").attr("disabled", true)
    }
}

function activateAmendButton(){
    if ($("#commentAmendButton").text().length > 0){
        $("#commentAmendButton").attr("disabled", false)
    } else {
        $("#commentAmendButton").attr("disabled", true)
    }
}


function deletePost(postId) {

    $.ajax({
        url : '/api/v4/community/post/'+postId,
        type:'DELETE',
        success:function(result){
            if (result){
                alert("글이 삭제되었습니다.");
                window.location.href = "/community";
            } else {
                alert("다시 한번 시도해주세요.");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}


function saveComment(post_id, user_id){
    console.log(post_id, user_id);

    const content = $('#commentContentTextarea').val();
    console.log(content)
    const data = {
        post : {
            id : post_id,
        },
        user : {
            id : user_id,
        },
        content : content,
    }
    $.ajax({
        url : '/api/v4/community/post/'+post_id+"/comment",
        type:'POST',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(result){
            if (result){
                alert("댓글이 등록되었습니다.");
                location.reload();
            } else {
                alert("다시 한번 시도해주세요.");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}


function imgZoomDown(){
    $("#imgZoomUpDiv").css("display", "");
    $("#zoomUpImg").attr("src", "");
}


function deleteComment(comment_id){
    alert(comment_id);
}
