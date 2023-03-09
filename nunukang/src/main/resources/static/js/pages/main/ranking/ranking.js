var fishRankList = [];
var cnt = 0;

$(document).ready(function(){
    var rankCount = $("#rankCount").val();
    var species = parseInt($("#rankingSelectTag").val());
    
    $.ajax({
        url: "/api/v4/fish/ranking?rankCount="+rankCount+"&species="+species,
        type: "GET",
        cache: false,
        async: false,
        success: function (result) {

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
                fishRankList.push(result);
                console.log(fishRankList);
                result.forEach(appendDiv);
            }
            $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')

        },
        error: function (err) {
            console.error(err);
        }
    })

})

function rankCountP5(){

    // for (let i = cnt;i<=cnt+5; i++) {

    // }
    // if (result != "[]"){
    //     result.forEach(appendDiv, parseInt(result.indexOf(this)));
    // }
    // $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')

}

function test(){
    $("#rankCount").val("5");
    var rankCount = $("#rankCount").val();
    var species = parseInt($("#rankingSelectTag").val())
    

    $.ajax({
        url: "/api/v4/fish/ranking?rankCount="+rankCount+"&species="+species,
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
                result.forEach(appendDiv, parseInt(result.indexOf(this)));
            }
            $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')
        },
        error: function (err) {
            console.error(err);
        }
    })
}

function appendDiv(item, index){
    index += 1; cnt += 1;
    console.log(cnt)
    $("#rankingContentInnerDiv").append(
        '<div class="rankingDiv">'+
            '<div class="rankNumDiv">' +
                '<p>'+index+'등</p>'+
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
