let isEdit = false;


function swapVisibility(elId) {
    if (document.getElementById(elId).style.display === "none") {
        document.getElementById(elId).style.display = "block";
    } else {
        document.getElementById(elId).style.display = "none";
    }
}

function Symptom(symptomId, rate, mandatory) {
    this.symptomId = symptomId;
    this.diseaseId = diseaseId;
    this.rate = rate;
    this.mandatory = mandatory;
}

function DiseaseSymptom(diseaseId, symptomId) {
    this.diseaseId = diseaseId;
    this.symptomId = symptomId;
}

function DiseaseMed(diseaseId, medicamentId) {
    this.diseaseId = diseaseId;
    this.medicamentId = medicamentId;
}

function Medicament(medicamentId, rate) {
    this.medicamentId = medicamentId;
    this.diseaseId = diseaseId;
    this.rate = rate;
}

function editor() {
    if (isEdit) {
        $(".sym_minus").hide();
        $(".med_minus").hide();
        $("#sym_plus").hide();
        $("#med_plus").hide();
        let symSelect = $("#sym_select")[0];
        symSelect.value = null;
        symSelect.style.display = "none";
        $("#sym_span")[0].style.display = "none";
        let symRate = $("#sym_rate")[0];
        symRate.value = null;
        symRate.style.display = "none";
        $("#sym_save_button")[0].style.display = "none";
        $("#sym_man")[0].style.display = "none";

        let medSelect = $("#med_select")[0];
        medSelect.value = null;
        medSelect.style.display = "none";
        $("#med_span")[0].style.display = "none";
        let medRate = $("#med_rate")[0];
        medRate.value = null;
        medRate.style.display = "none";
        $("#med_save_button")[0].style.display = "none";

        isEdit = false;
    } else {
        isEdit = true;
        $(".sym_minus").show();
        $(".med_minus").show();
        $.ajax({
                type: "GET",
                url: "/rest/symptoms",
                dataType: "JSON",
                success: function (data) {
                    let symSelect = $("#sym_select")[0];
                    for (let i = symSelect.options.length; i >= 0; i--) {
                        symSelect.options.remove(i);
                    }
                    for (let i = 0; i < data.length; i++) {
                        symSelect.options.add(new Option(data[i].name, data[i].symptomId));
                    }
                    $("#sym_plus")[0].style.display = "block";
                }
            }
        );
        $.ajax({
                type: "GET",
                url: "/rest/meds",
                dataType: "JSON",
                success: function (data) {
                    let medSelect = $("#med_select")[0];
                    for (let i = medSelect.options.length; i >= 0; i--) {
                        medSelect.options.remove(i);
                    }
                    for (let i = 0; i < data.length; i++) {
                        medSelect.options.add(new Option(data[i].name, data[i].medicamentId));
                    }
                    $("#med_plus")[0].style.display = "block";
                }
            }
        )
    }
}

function showNewSym() {
    $("#sym_select")[0].style.display = "block";
    $("#sym_span")[0].style.display = "block";
    $("#sym_rate")[0].style.display = "block";
    $("#sym_save_button")[0].style.display = "block";
    $("#sym_man")[0].style.display = "block";
}

function showNewMed() {
    $("#med_select")[0].style.display = "block";
    $("#med_span")[0].style.display = "block";
    $("#med_rate")[0].style.display = "block";
    $("#med_save_button")[0].style.display = "block";
}

function hideSymError() {
    $("#sym_error")[0].style.display = "none";
}

function hideMedError() {
    $("#med_error")[0].style.display = "none";
}

function deleteSym(id) {
    let diseaseSymptom = new DiseaseSymptom(diseaseId, id);
    $.ajax({
        type: "DELETE",
        url: "/rest/symptom",
        contentType: "application/json",
        data: JSON.stringify(diseaseSymptom),
        success: function () {
            $("#s" + id).html("");
        }
    })
}

function deleteMed(id) {
    let diseaseMed = new DiseaseMed(diseaseId, id);
    $.ajax({
        type: "DELETE",
        url: "/rest/med",
        contentType: "application/json",
        data: JSON.stringify(diseaseMed),
        success: function () {
            $("#m" + id).html("");
        }
    })
}

function saveSymptom() {
    let symSelect = $("#sym_select")[0];
    let symRate = $("#sym_rate")[0];
    let mandatory = 1;
    if (symSelect.value == null || symSelect.value === "" || symRate.value == null || symRate.value === "" || parseFloat(symRate.value) <= 0 || parseFloat(symRate.value) > 100) {
        $("#sym_error")[0].style.display = "block";
        return
    }
    if ($("#sym_man")[0].checked) {
        mandatory = 0;
    }
    let symptom = new Symptom(parseInt(symSelect.value), parseFloat(symRate.value), mandatory);
    $.ajax({
        type: "POST",
        url: "/rest/symptom",
        contentType: "application/json",
        dataType: "JSON",
        data: JSON.stringify(symptom),
        success: function (data) {
            let j = -1;
            let arr = [];
            arr[++j] = '<div id="s' + data.symptomId + '" onclick="swapVisibility(\'s\'+this.id)">' +
                '                    <img src="https://image.flaticon.com/icons/png/512/54/54601.png" alt="Удалить"' +
                '                         class="round_extra_small sym_minus" style="display:block; float: left"' +
                '                         onclick="deleteSym(' + data.symptomId + ')">' +
                '                    <span href="/medicament/' + data.symptomId + '"' +
                '                          style="text-decoration: none; color: #4443fc">' + data.name + '</span>' +
                '                    <span> - ' + data.rate + '</span>';
            if (data.mandatory === "0") {
                arr[++j] = '        <span><br>Обязательный</span>';
            }
            arr[++j] = '            <span id="ss' + data.symptomId + '" style="display: none">' + data.info + '</span>' +
                '               <hr></div>';
            let symDiv = $("#sym_div")[0];
            symDiv.innerHTML += arr.join('');
            symSelect.value = null;
            symRate.value = null;
        }
    })
}

function saveMed() {
    let medSelect = $("#med_select")[0];
    let medRate = $("#med_rate")[0];
    if (medSelect.value == null || medSelect.value === "" || medRate.value == null || medRate.value === "" || parseFloat(medRate.value) <= 0 || parseFloat(medRate.value) > 100) {
        $("#med_error")[0].style.display = "block";
        return
    }
    let medicament = new Medicament(parseInt(medSelect.value), parseFloat(medRate.value));
    $.ajax({
        type: "POST",
        url: "/rest/medicament",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(medicament),
        success: function (data) {
            let j = -1;
            let arr = [];
            arr[++j] = '<div id="m' + data.medicamentId + '" onclick="swapVisibility(\'s\'+this.id)">' +
                '                    <img src="https://image.flaticon.com/icons/png/512/54/54601.png" alt="Удалить"' +
                '                         class="round_extra_small med_minus" style="display:block; float: left"' +
                '                         onclick="deleteMed(' + data.medicamentId + ')">' +
                '                    <span href="/medicament/' + data.medicamentId + '"' +
                '                          style="text-decoration: none; color: #4443fc">' + data.name + '</span>' +
                '                    <span> - ' + data.rate + '</span>' +
                '                    <span id="sm' + data.medicamentId + '" style="display: none">' + data.info + '</span>' +
                '                <hr></div>';
            let medDiv = $("#med_div")[0];
            medDiv.innerHTML += arr.join('');
            medSelect.value = null;
            medRate.value = null;
        }
    })
}