function changeImg(e){
    $("#pictureImg").css("display", "block");
    $("#pictureImgInnerDiv").css("display", "block");
    // e.css("display", "block");
    e.style.display = "none";
}


function goToBeforePage(){
    window.location.href = "/pictures"
}
