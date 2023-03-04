function test() {
    document.getElementById("myStoryImgInput").click();
}

function test2() {
    if (confirm("스토리에 해당 사진을 올리시겠습니까 ?")) {
        alert("스토리가 추가되었습니다.");
    } else {
        document.getElementById("myStoryImgInput").value = "";
    }
}




function showPostInfoDiv() {
    // $("#postInfoDivInSlideMainDiv").css("display","block");
    $(".postInfoDivInSlideMainDiv").slideToggle(400);
}




$(document).ready(function(){
    

    $("#closeMessageDiv").click(function(){

        $(".slideMessageDiv").animate({
            width: "toggle"
        });
    });
      

    

    $("#appearMessageDivButton").click(function(){

        $(".slideMessageDiv").animate({
            width: "toggle"
        });
      });

      


    $("#appearAlertDivButton").click(function(){
        $(".commonAlertOutterDiv").animate({
            width: "toggle"
        });

    });

    $("#closeAlertDivButton").click(function(){
        $(".commonAlertOutterDiv").animate({
            width: "toggle"
        });
    });
    


});


