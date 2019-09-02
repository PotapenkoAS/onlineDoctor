var symptoms = [];
var id = Number(0);

function updateDiseases() {
    // создание ajax запроса
    $.ajax({
        type: "GET",//метод
        url: "/rest/update_diseases?symptoms=" + symptoms,//url, по хорошему надо переделать передачу параметров через параметр data
        dataType: "json",// тип, который ждем в ответ от сервера
        success: function (data) {//выполняется при получении ответа HTTP 2xx
            var j = -1;
            var arr = [];
            //обнуление списка болезней если данных не вернулось
            if (data == null) {
                $('#diseasesList').html("");
                return;
            }
            //генерация нового контента по пришедшим данным
            for (var i = 0; i < data.length; i++) {
                arr[++j] = '<div id="d' + id + '" onclick="swapVisibility(this.id)">';
                arr[++j] = '<div><p>Название: ' + data[i].diseaseName + '</p><p>Описание: ' + data[i].diseaseInfo + '</p></div>';
                arr[++j] = '<div><p>Вероятность по обязательным симптомам: ' + data[i].mandatoryRate + '</p>';
                if (!!data[i].optionalRate) {
                    arr[++j] = '<p>Вероятность по дополнительным симптомам: ' + data[i].optionalRate + '</p>';
                }
                arr[++j] = '</div><br>';

                arr[++j] = '<div id="m' + id + '" style="display: none">';
                for (var k = 0; k < data[i].meds.length; k++) {
                    arr[++j] = '<p>Лекарство: ' + data[i].meds[k].name + '</p><p>Описание: ' + data[i].meds[k].info + '</p><p>Рейтинг: ' + data[i].meds[k].rate + '</p>';
                }
                arr[++j] = '</div>';
                arr[++j] = '</div><hr><br>';
                id++;
            }
            //добавление сгенерированного контента на страницу
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

// изменение списка симпотомов и обновление списка болезней
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

function swapVisibility(elId) {
    var trueElId = "m" + elId.substr(1, elId.length - 1);
    if (document.getElementById(trueElId).style.display === "none") {
        document.getElementById(trueElId).style.display = "block";
    } else {
        document.getElementById(trueElId).style.display = "none";
    }
}