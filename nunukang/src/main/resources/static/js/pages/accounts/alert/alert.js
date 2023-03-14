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
    console.log(user_id);
    window.location.href="/accounts/user/"+user_id;
}