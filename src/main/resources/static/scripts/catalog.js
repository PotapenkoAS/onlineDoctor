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

function DiseaseMed(diseaseId, medId) {
    this.diseaseId = diseaseId;
    this.medId = medId;
}

function Medicament(medicamentId, rate) {
    this.medicamentId = medicamentId;
    this.diseaseId = diseaseId;
    this.rate = rate;
}

function editor() {
    let symMinus = $(".sym_minus");
    let medMinus = $(".med_minus");
    if (isEdit) {
        symMinus.style.display = "none";
        medMinus.style.display = "none";

        let symSelect = $("#sym_select");
        symSelect.value = null;
        symSelect.style.display = "none";
        $("#sym_span").style.display = "none";
        let symRate = $("#sym_rate");
        symRate.value = null;
        symRate.style.display = "none";
        $("#sym_save_button").style.display = "none";
        $("#sym_man").style.display = "none";

        let medSelect = $("#med_select");
        medSelect.value = null;
        medSelect.style.display = "none";
        $("#med_span").style.display = "none";
        let medRate = $("#med_rate");
        medRate.value = null;
        medRate.style.display = "none";
        $("#med_save_button").style.display = "none";

        isEdit = false;
        return
    }
    isEdit = true;
    symMinus.style.display = "block";
    medMinus.style.display = "block";
    $.ajax({
            type: "GET",
            url: "/rest/get_symptoms",
            dataType: "JSON",
            success: function (data) {
                $("#sym_select").html(collectOptions(data).join(''));
                $("#sym_plus").style.display = "block";
            }
        }
    );
    $.ajax({
            type: "GET",
            url: "/rest/get_meds",
            dataType: "JSON",
            success: function (data) {
                $("#med_select").html(collectOptions(data).join(''));
                $("#med_plus").style.display = "block";
            }
        }
    )
}

function showNewSym() {
    $("#sym_select").style.display = "block";
    $("#sym_span").style.display = "block";
    $("#sym_rate").style.display = "block";
    $("#sym_save_button").style.display = "block";
    $("#sym_man").style.display = "block";
}

function showNewMed() {
    $("#med_select").style.display = "block";
    $("#med_span").style.display = "block";
    $("#med_rate").style.display = "block";
    $("#med_save_button").style.display = "block";
}

function hideSymError() {
    $("#sym_error").style.display = "none";
}

function hideMedError() {
    $("#med_error").style.display = "none";
}

function deleteSym(id) {
    let diseaseSymptom = new DiseaseSymptom(diseaseId, id);
    $.ajax({
        type: "DELETE",
        url: "/rest/symptom",
        data: JSON.stringify(diseaseSymptom),
        success: function () {
            $("s" + id).html = "";
        }
    })
}

function deleteMed(id) {
    let diseaseMed = new DiseaseMed(diseaseId, id);
    $.ajax({
        type: "DELETE",
        url: "/rest/symptom",
        data: JSON.stringify(diseaseMed),
        success: function () {
            $("m" + id).html = "";
        }
    })
}

function collectOptions(data) {
    let j = -1;
    let arr = [];
    for (let i = 0; i < data.length; i++) {
        arr[++j] = '<option value="' + data.symptomId + '">' + data.name + '</option>';
    }
    return arr;
}

function saveSymptom() {
    let symSelect = $("#sym_select");
    let symRate = $("#sym_rate");
    let mandatory = 1;
    if (symSelect.value == null || symSelect.value === "" || symRate.value == null || symRate <= 0) {
        $("#sym_error").style.display = "block";
        return
    }
    if ($("#sym_man").checked) {
        mandatory = 0;
    }
    let symptom = new Symptom(symSelect.id, symRate.value, mandatory);
    $.ajax({
        type: "POST",
        url: "/rest/symptom",
        dataType:"JSON",
        data: JSON.stringify(symptom),
        success: function (data) {
            let j = -1;
            let arr = [];
            arr[++j] = '<div id="\'s\'+' + data.symptomId + '" onclick="swapVisibility(\'s\'+this.id)">' +
                '                    <img src="https://image.flaticon.com/icons/png/512/54/54601.png" alt="Удалить"' +
                '                         class="round_extra_small sym_minus" style="display:none"' +
                '                         onclick="deleteSym(' + data.symptomId + ')">' +
                '                    <span href="/medicament/' + data.symptomId + '"' +
                '                          style="text-decoration: none; color: #4443fc">' + data.name + '</span>' +
                '                    <span> - ' + data.rate + '</span>' +
                '                    <span id="\'ss\'+' + data.symptomId + '" style="display: none">' + data.info + '</span>' +
                '                </div>';
            let symDiv = $("#sym_div");
            symDiv.html(symDiv.html, arr.join(''));
            symSelect.value = null;
            symRate.value = null;
        }
    })
}

function saveMed() {
    let medSelect = $("#med_select");
    let medRate = $("#med_rate");
    if (medSelect.value == null || medSelect.value === "" || medRate.value == null || medRate <= 0) {
        $("#med_error").style.display = "block";
        return
    }
    let medicament = new Medicament(medSelect.id, medRate.value);
    $.ajax({
        type: "POST",
        url: "/rest/medicament",
        data: JSON.stringify(medicament),
        success: function (data) {
            let j = -1;
            let arr = [];
            arr[++j] = '<div id="\'m\'+' + data.medicamentId + '" onclick="swapVisibility(\'s\'+this.id)">' +
                '                    <img src="https://image.flaticon.com/icons/png/512/54/54601.png" alt="Удалить"' +
                '                         class="round_extra_small med_minus" style="display:none"' +
                '                         onclick="deleteMed(' + data.medicamentId + ')">' +
                '                    <span href="/medicament/' + data.medicamentId + '"' +
                '                          style="text-decoration: none; color: #4443fc">' + data.name + '</span>' +
                '                    <span> - ' + data.rate + '</span>' +
                '                    <span id="\'sm\'+' + data.medicamentId + '" style="display: none">' + data.info + '</span>' +
                '                </div>';
            let medDiv = $("#med_div");
            medDiv.html(medDiv.html, arr.join(''));
            medSelect.value = null;
            medRate.value = null;
        }
    })
}