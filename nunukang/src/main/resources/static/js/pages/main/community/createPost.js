var cnt=0;
var imageList = [];

var hashtagUserList = [];


// hashTag
function hashtagging(){
    $(".hashtagDiv").css("display", "block");
}

function hashtag(obj, id) {
    if (obj.getAttribute("checked") == "false"){
        hashtagUserList.push(id);
        obj.setAttribute("checked", "true");
        obj.style.color = "#6464f5";
    } else {
        hashtagUserList = hashtagUserList.filter((element)=> element != id);
        obj.setAttribute("checked", "false");
        obj.style.color = "black";
    }

}

function deleteHashtagDiv(){
    $(".hashtagDiv").css("display", "none");
}



// 
function postAppendImg(){
    $("#appendImgInput").click();
}

function getImages(event){
    const appendImages = event.files;
    const images = $("#appendImgInput")[0].files;
    // 아무 이미지도 추가되지 않았던 경우
    if (cnt == 0){
        if (images.length > 4){
            // 4개 이상의 이미지가 추가된 경우 imageList에 4개의 추가된 이미지만 저장
            for (i=0;i<4;i++){
                imageList.push(images[i]);
            }
            alert("글에 이미지는 최대 4개까지 첨부 할 수 있습니다.");
        } else {
            // 4개이하의 이미지가 추가된 경우 imageList에 추가된 이미지를 저장
            for (i=0;i<images.length;i++){
                imageList.push(images[i]);
            }
        }

        for (let image of imageList){
            // imageList에 추가된 이미지들을 미리보기 추가
            const reader = new FileReader();

            reader.onload = function (image) {
                $('.postContentImgInnerDiv:last-child').append("<div class=\"postImgDiv\" id=\"postImgDiv\">" +
                    "<div class=\"imgDeleteDiv\" onclick='deleteImg(" + cnt++ + ")'></div>" +
                    "<img name='postImg' id='postImg' src=" + image.target.result + " onclick=\"imgZoomUp(this)\">" +
                    "</div>");
            }

            reader.readAsDataURL(image);
        }
    }
    // 이전에 이미지가 추가되었었던 경우
    else {
        // 최대 개수를 넘지 않게 이미지가 추가되어있었던 경우
        if (cnt < 4){
            for (i=0;i<4-cnt;i++){
                console.log("appendImages : "+appendImages.length);
                if (appendImages[i] != undefined){
                    imageList.push(appendImages[i]);
                }
            }

            $('.postContentImgInnerDiv').children().remove();
            cnt = 0;
            for (const image of imageList){
                const reader = new FileReader();

                reader.onload = function (image) {
                    $('.postContentImgInnerDiv:last-child').append("<div class=\"postImgDiv\" id=\"postImgDiv\">" +
                        "<div class=\"imgDeleteDiv\" onclick='deleteImg(" + cnt++ + ")'></div>" +
                        "<img name='postImg' id='postImg' src=" + image.target.result + " onclick=\"imgZoomUp(this)\">" +
                        "</div>");
                }

                reader.readAsDataURL(image);
            }
        } else {
            alert("글에 이미지는 최대 4개까지 첨부 할 수 있습니다.");
        }
    }
}

function deleteImg(idx){
    imageList.splice(idx,1);
    cnt--;
    document.getElementById("postContentImgInnerDiv").childNodes[idx].remove();

    const imgCnt = document.getElementById("postContentImgInnerDiv").children.length;
    for (i=0;i<imgCnt;i++){
        $("#postContentImgInnerDiv div:eq("+i+")>div").remove();
    }
    for (i=0;i<imgCnt;i++){
        $("#postContentImgInnerDiv>div:eq("+i+")").append("<div class=\"imgDeleteDiv\" onclick='deleteImg("+ i +")'></div>");
    }
}

function imgZoomUp(result){
    $("#imgZoomUpDiv").css("display", "block");
    $("#zoomUpImg").attr("src", result.src);
}

function imgZoomDown(){
    $("#imgZoomUpDiv").css("display", "");
    $("#zoomUpImg").attr("src", "");
}


function postSave(){
    const formData = new FormData();
    const imageSize = imageList.length;
    
    
    // formData.append("postHashtagUserList", hashtagUserList);
    
    var post = {
        "title" : $("#postTitle").val(),
        "content" : $("#postContent").val(),
        "postWriter" : {
            id : $("#id").val(),
        },
    }

    for (let i=0; i<imageList.length; i++) {
        formData.append("images["+i+"]", imageList[i]);
    }

    formData.append("user", new Blob([JSON.stringify(post)], {type : "application/json"}));

    $.ajax({
        url: '/api/v4/community/post/create',
        type: 'POST',
        enctype: 'multipart/form-data',
        async: true, 
        processData: false,
        contentType: false, 
        data: formData,
        success:function(result){
            if (result != "0") {
                alert("글이 등록되었습니다.");
                window.location.href ="/community/post/"+result;
            } else {
                alert("다시 한번 시도해주세요.");
            }
            
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });
}


