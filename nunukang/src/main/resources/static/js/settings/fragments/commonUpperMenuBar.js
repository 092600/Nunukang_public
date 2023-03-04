function showAlertDiv() {
    $("#commonAlertOutterDiv").css("display","block");
    $(".commonAlertOutterDiv").animate({'width':'toggle'}, 250);
}


function hideAlertDiv() {
    
    $(".commonAlertOutterDiv").animate({'width':'toggle'}, 250);
    $("#commonAlertOutterDiv").css("display","none");
}

// const slideAlertDiv = document.querySelector(".slideAlertDiv");
// header = slideAlertDiv.querySelector(".slideAlertDiv");

// function onDrag() {
//     let getStyle = window.getComputedStyle(slideAlertDiv);
//     let left = parseInt(getStyle.left);
//     let top = parseInt(getStyle.top);

//     console.log(left, top);
//     console.log(typeof left, typeof top);
// }

// header.addEventListener('mousedown', ()=>{
//     header.addEventListener("mousemove", onDrag);
// });
    

