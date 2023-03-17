function goBackPage() {
    history.go(-1);
}

function deleteAlert(alert_id) {
    $.ajax({
        url : '/api/v4/accounts/alert/'+alert_id,
        type:'DELETE',
        success:function(result){
            if (result){
                alert("글이 삭제되었습니다.");
                window.location.href = "/accounts/alert";
            } else {
                alert("다시 한번 시도해주세요.");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    });

}