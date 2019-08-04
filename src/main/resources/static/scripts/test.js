var symptoms = [1,2,3];
function updateDiseases() {
    $.ajax({
        type:"GET",
        url:"/rest/update_diseases?symptoms="+symptoms,
        dataType: "json",
        success: function (data) {
            var j = -1;
            var arr = [];
            arr[++j] = data
        }
    })
}