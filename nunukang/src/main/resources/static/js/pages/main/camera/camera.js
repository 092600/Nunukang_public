function getPic(){
    const camera = document.getElementById("camera");
    window.picture = camera.files[0];

    $("#mainDiv").css("display", "none");
    $("#imgDiv").css("display", "block");
    $(".fishNameOutterDiv").css("display", "flex");

    var mainDiv = document.getElementById("mainDiv");
    var imgDiv = document.getElementById("imgDiv");

    const reader = new FileReader();

    reader.onload = e => {
        const image = document.getElementById("image");
        image.src = e.target.result;
    }

    reader.readAsDataURL(picture);
    $("#cameraDiv").css("display","none");
    $("#submitPictureDiv").css("display","flex");

}

function submitPictureDivClick(){
    document.getElementById("pictureSubmitButton").click();
}


function sendPicture(){
    const formData = new FormData();
    const fileName = $("#fileName").val();

    const email = $("#email").val();
    const path = "/Users/sim/Nunukang/Fish/Picture";
    const camera = document.getElementById("camera");

    const picture = camera.files[0];

    formData.append("email", email);
    formData.append("path", path);
    formData.append("fileName", fileName);
    formData.append("picture", picture);
    const imageTag = document.getElementById("image");
    


    if (fileName == "") {
        alert("사진의 이름을 입력해주세요");
    } else {
        if (confirm("입력하신 이름으로 사진을 저장하시겠습니까?")){
            $("#fileName").attr("readonly",true);
            $(".submitPictureDiv").prop("onclick", null).off("click");
            $(".loadingDiv").css("display","flex");
            $.ajax({
                url:'http://127.0.0.1:5000',
                type:'POST',
                cache : false,
                contentType: false,               // * 중요 *
                processData: false,               // * 중요 *
                enctype : 'multipart/form-data',  // * 중요 *
                // crossDomain: true,
                data : formData,
                success:function(result){
        
                    $.ajax({
                        url: '/api/v4/fish/picture',
                        type: 'POST',
                        cache: false,
                        contentType: false,               // * 중요 *
                        processData: false,               // * 중요 *
                        dataType: 'JSON',
                        contentType: "application/json",
                        data: JSON.stringify(result),
                        success: function (result) {
                            console.log(result);
                            if (!result) {
                                alert("문제가 발생하여 다시 한번 시도해주세요.");
                            } else {
                                $("#pictureSubmitButton").click(function(){
                                    window.location.href = "/ranking"
                                });
                                $("#pictureSubmitButton").text("랭킹 보러가기");
                            }
                        },
                        error: function (err) {
                            console.error(err);
                        }
                    })
                    $(".loadingDiv").css("display","none");
                    $(".fish_species_namePTag").text(result.fishSpeicesName);
                    $(".fish_sizePTag").text(result.fishSize+"cm");
                    $(".indexContentInnerDiv").css("height", "93%");
                    $("#image").css("height", "85%");
        
                    imageTag.src = "/fish/images/"+result.fishingUser.email+"/"+result.pictureName+"_model.jpg";
                    
                    
        
        
                },
                error:function(err){
                    console.error(err);
                }
        
            });
        }
    }
    
}

function goRanking() {
    window.location.href = "/ranking";
}