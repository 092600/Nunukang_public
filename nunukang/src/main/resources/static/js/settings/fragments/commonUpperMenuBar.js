function showAlertDiv() {
    $("#commonAlertOutterDiv").css("display","block");
    $(".commonAlertOutterDiv").animate({'width':'toggle'}, 250);
}


function hideAlertDiv() {
    
    $(".commonAlertOutterDiv").animate({'width':'toggle'}, 250);
    $("#commonAlertOutterDiv").css("display","none");
}

