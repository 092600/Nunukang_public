$(document).ready(function(){
    $(".commonUpperHeaderFunctionalDiv").click(function() {
        history.back();
    });

    

});

function showUserPosts() {
    alert("hi");
    $(".userPostsContentDiv").css("display", "block");
    // $(".userPostsContentDiv").css("border-color", "gray");

    // $(".userTagedPostsContentDiv").css("border-color", "gray");
    $(".userTagedPostsContentDiv").css("display", "none");
    
};

function showUserTagedPosts() {
    alert("hi");
    // $(".userTagedPostsContentDiv").css("border-color", "gray");
    $(".userTagedPostsContentDiv").css("display", "block");

    // $(".userPostsContentDiv").css("border-color", "gray");
    $(".userPostsContentDiv").css("display", "none");
};
