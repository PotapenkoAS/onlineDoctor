var symptoms = [1, 2, 3];

function updateDiseases() {
    $.ajax({
        type: "GET",
        url: "/rest/update_diseases?symptoms=" + symptoms,
        dataType: "json",
        success: function (data) {
            var j = -1;
            var arr = [];
            for (var i = 0; i < data.length; i++) {
                arr[++j] = '<div>';
                arr[++j] = '<div><p>Название: ' + data[i].diseaseName + '</p><p>Описание: ' + data[i].diseaseInfo + '</p></div>';
                arr[++j] = '<div><p>Вероятность по обязательным симптомам: ' + data[i].mandatoryRate + '</p>';
                if (!!data[i].optionalRate) {
                    arr[++j] = '<p>Вероятность по дополнительным симптомам: ' + data[i].optionalRate + '</p>';
                }
                arr[++j] = '</div><br>';

                arr[++j] = '<div>';
                for (var k = 0; k < data[i].meds.length; k++) {
                    arr[++j] = '<p>Лекарство: ' + data[i].meds[k].name + '</p><p>Описание: ' + data[i].meds[k].info + '</p><p>Рейтинг: '+ data[i].meds[k].rate +'</p>';
                }
                arr[++j] = '</div>';
                arr[++j] = '</div><br>';
            }
            $('#diseasesList').html(arr.join(''));
        }
    })
}