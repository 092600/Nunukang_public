var fishRankList = [];

var totalPage;
var rank = 0;
$(document).ready(function(){
    var species = $("#rankingSelectTag").val();
    
    $.ajax({
        url: "/api/v4/fish/ranking?species="+species+"&cnt="+0,
        type: "GET",
        cache: false,
        async: false,
        success: function (result) {

            totalPage = result.totalPages;
            
            $("#rankingContentInnerDiv").append(
                '<div class="rankingTextDiv">'+
                    '<div class="rankNumHeaderDiv">' +
                        '<p>순위</p>'+
                    '</div>'+
                    '<div class="userNameHeaderDiv">' +
                        '<p>이름</p>'+
                    '</div>'+
                    '<div class="fishSizeHeaderDiv">' +
                        '<p>물고기 크기</p>'+
                    '</div>'+
                    '<div class="fishScoreHeaderDiv">' +
                        '<p>점수</p>'+
                    '</div>'+
                '</div>'
            );

            if (result != "[]"){
                result.content.forEach(appendDiv);
            }
            $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')

        },
        error: function (err) {
            console.error(err);
        }
    })

})

function rankCountP5(){
    var species = $("#rankingSelectTag").val();

    const cnt = 1;

    if (cnt < totalPage) {
        $.ajax({
            url: "/api/v4/fish/ranking?species="+species+"&cnt="+cnt,
            type: "GET",
            cache: false,
            async: false,
            success: function (result) {
                console.log(result);
            
                
                if (result != "[]"){
                    $(".rankingContentInnerButton").remove();
                    result.content.forEach(appendDiv);
                    $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')
                }
                
            },
            error: function (err) {
                console.error(err);
            }
        })
    } 

}

function test(){
    var species = $("#rankingSelectTag").val();
    rank = 0;

    $.ajax({
        url: "/api/v4/fish/ranking?species="+species+"&cnt="+0,
        type: "GET",
        cache: false,
        async: false,
        success: function (result) {
            $("#rankingContentInnerDiv").empty();

            $("#rankingContentInnerDiv").append(
                '<div class="rankingTextDiv">'+
                    '<div class="rankNumHeaderDiv">' +
                        '<p>순위</p>'+
                    '</div>'+
                    '<div class="userNameHeaderDiv">' +
                        '<p>이름</p>'+
                    '</div>'+
                    '<div class="fishSizeHeaderDiv">' +
                        '<p>물고기 크기</p>'+
                    '</div>'+
                    '<div class="fishScoreHeaderDiv">' +
                        '<p>점수</p>'+
                    '</div>'+
                '</div>'
            );

            if (result != "[]"){
                result.content.forEach(appendDiv);
            }
            $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')
        },
        error: function (err) {
            console.error(err);
        }
    })
}

function appendDiv(item){
    rank += 1;
    
    // console.log(item.get("fishingUser"))
    $("#rankingContentInnerDiv").append(
        '<div class="rankingDiv">'+
            '<div class="rankNumDiv">' +
                '<p>'+rank+'등</p>'+
            '</div>'+
            '<div class="userNameDiv">' +
                '<p>'+item.fishingUser.email+'</p>'+
            '</div>'+
            '<div class="fishSizeDiv">' +
                '<p>'+item.fishSize +'cm</p>'+
            '</div>'+
            '<div class="fishScoreDiv">' +
                '<p>'+item.fishScore+'점</p>'+
            '</div>'+
        '</div>'
    );
}
