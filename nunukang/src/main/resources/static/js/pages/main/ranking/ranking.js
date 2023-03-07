$(document).ready(function(){
    var rankCount = $("#rankCount").val();
    var data = {
        fishSpeicesCategoryNumber : parseInt($("#rankingSelectTag").val()),
    }
    $.ajax({
        url: "/api/v4/fish/ranking?rankCount="+rankCount,
        type: "POST",
        cache: false,
        async: false,
        dataType: 'text',
        contentType: "application/json",
        data: JSON.stringify(data),
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
    var rankCount = $("#rankCount").val();
    $("#rankCount").val(parseInt(rankCount)+5);

    var data = {
        fishSpeicesCategoryNumber : parseInt($("#rankingSelectTag").val()),
    }

    $.ajax({
        url: "/api/v4/fish/ranking?rankCount="+rankCount,
        type: "POST",
        cache: false,
        async: false,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(data),
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
                result.forEach(appendDiv);
            }
            $("#rankingContentInnerDiv").append('<button class="rankingContentInnerButton" onclick="rankCountP5()">랭킹 더 보기</button>')
        },
        error: function (err) {
            console.error(err);
        }
    })
}

function test(){
    $("#rankCount").val("5");
    var rankCount = $("#rankCount").val();

    var data = {
        fishSpeicesCategoryNumber : parseInt($("#rankingSelectTag").val()),
    }

    $.ajax({
        url: "/api/v4/fish/ranking?rankCount="+rankCount,
        type: "POST",
        cache: false,
        async: false,
        dataType: 'JSON',
        contentType: "application/json",
        data: JSON.stringify(data),
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
    index += 1;
    $("#rankingContentInnerDiv").append(
        '<div class="rankingDiv">'+
            '<div class="rankNumDiv">' +
                '<p>'+index+'등</p>'+
            '</div>'+
            '<div class="userNameDiv">' +
                '<p>'+item.email+'</p>'+
            '</div>'+
            '<div class="fishSizeDiv">' +
                '<p>'+item.fishSize +'cm</p>'+
            '</div>'+
            '<div class="fishScoreDiv">' +
                '<p>'+item.score+'점</p>'+
            '</div>'+
        '</div>'
    );
}
