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