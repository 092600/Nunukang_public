$(document).ready(function(){
    $(".commonUpperHeaderFunctionalDiv").click(function() {
        history.back();
    });

    

});

function showUserPosts() {
    $(".usersPostsHeaderDiv").css("border-color", "rgba(0, 0, 0, 0.337)");
    $(".userPostsContentDiv").css("display", "block");
    $(".userTagedPostsHeaderDiv").css("border-color", "rgb(128, 128, 128)");
    $(".userTagedPostsContentDiv").css("display", "none");
    
};

function showUserTagedPosts() {
    $(".usersPostsHeaderDiv").css("border-color", "rgb(128, 128, 128)");
    $(".userTagedPostsContentDiv").css("display", "block");

    $(".userTagedPostsHeaderDiv").css("border-color", "rgba(0, 0, 0, 0.337)");
    $(".userPostsContentDiv").css("display", "none");
};
