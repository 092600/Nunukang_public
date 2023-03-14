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