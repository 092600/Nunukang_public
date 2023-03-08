$(document).ready(function(){
    var email = $("#email").val();

    $.ajax({
        url: "/api/v4/fish/pictures?email="+email,
        type: "GET",
        cache: true,
        async: false,
        success: function (fsihs) {
            
            if (fsihs == ""){
                $("#myFishsContentOutterDiv").append(
                    '<div class="noneImagesDiv">' +
                        '<div>'+
                            '<img class="non" src="/images/myfishs/cry.png">'+
                            '<p>아직 사진이 한장도 없어요..</p>'+
                            '<p>앞으로 누누강과 함께 추억을 쌓아봐요 !</p>'+
                        '</div>'+
                    '</div>'
                )
            } else {
                console.log(fsihs);
                for (var i=0; i<=fsihs.length;i++){
                    $("#myFishsContentOutterDiv").append(
                        '<div class="myFishsContentInnerDiv">'+
                            '<div class="myFishsContentInnerHeaderDiv">'+
                                '<div class="pictureDeleteDiv" onClick="deletePicture('+fsihs.at(i).id+')"></div>'+
                                '<p>'+fsihs.at(i).pictureName+'</p>'+
                            '</div>'+
                            '<div class="myFishsContentInnerContentDiv">'+
                                '<a href="/myfishs/picture/'+fsihs.at(i).id+'"><img class="fishPicture" src="'+fsihs.at(i).picturePath+'.jpg"></a>'+
                            '</div>'+
                        '</div>'

                    )
                }
            }
        },
        error: function (err) {
            console.error(err);
        }
    });
        
    
    
})

function deletePicture(e){
    $.ajax({
        url: "/api/v4/fish/picture?fishId="+e,
        type: "DELETE",
        cache: true,
        async: false,
        success: function (result) {
            if (result){
                alert("이미지가 삭제되었습니다.");
                window.location.href = "/pictures";
            } else {
                alert("이미지 삭제하는 중 문제가 발생했습니다.\n다시 시도해주세요.");
            }
        },
        error: function (err) {
            console.error(err);
        }
    })
}
