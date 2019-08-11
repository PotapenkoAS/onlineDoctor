var symptoms = [];

function updateDiseases() {
    $.ajax({
        type: "GET",
        url: "/rest/update_diseases?symptoms=" + symptoms,
        dataType: "json",
        success: function (data) {
            var j = -1;
            var arr = [];
            if(data==null){
                $('#diseasesList').html("");
                return;
            }
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
                    arr[++j] = '<p>Лекарство: ' + data[i].meds[k].name + '</p><p>Описание: ' + data[i].meds[k].info + '</p><p>Рейтинг: ' + data[i].meds[k].rate + '</p>';
                }
                arr[++j] = '</div>';
                arr[++j] = '</div><br>';
            }
            $('#diseasesList').html(arr.join(''));
        }
    })
}

function saveTest() {
    if (symptoms != null) {
        $.ajax({
            type: "POST",
            url: "/rest/save_test",
            data: {'symptoms': JSON.stringify(symptoms)},
            success: function (data) {
                var checkMark = document.getElementById("check_mark");
                if (data === "success") {
                    checkMark.style.color = "#12fc10";
                    checkMark.innerText = "✓";
                } else if (data === "empty") {
                    checkMark.style.color = "#fcdb37";
                    checkMark.innerText = "Пустой список не сохранен";
                } else {
                    checkMark.style.color = "#fc403d";
                    checkMark.innerText = "🗙";
                }
            }
        })
    }
}

function updateSymptoms(checkbox) {
    document.getElementById("check_mark").innerText = "";

    if (checkbox.checked) {
        symptoms.push(Number(checkbox.id));
    } else {
        symptoms = symptoms.filter(function (value) {
            return value !== Number(checkbox.id);
        })
    }
    updateDiseases();
}