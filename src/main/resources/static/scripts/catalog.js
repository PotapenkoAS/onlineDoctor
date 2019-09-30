var isEdit = false;


function swapVisibility(elId) {
    if (document.getElementById(elId).style.display === "none") {
        document.getElementById(elId).style.display = "block";
    } else {
        document.getElementById(elId).style.display = "none";
    }
}

function symptom(id, rate) {
    this.id = id;
    this.rate = rate;
}

function medicament(id, rate) {
    this.id = id;
    this.rate = rate;
}

function editor() {
    if (isEdit) {
        $(".sym_minus").style.display = "none";
        $(".med_minus").style.display = "none";
        isEdit = false;
        return
    }
    isEdit = true;
    $(".sym_minus").style.display = "block";
    $(".med_minus").style.display = "block";
    $.ajax({
            type: "GET",
            url: "/rest/get_symptoms",
            dataType: "JSON",
            success: function (data) {
                $("#sym_select").innerHTML = collectOptions(data);
                $("#sym_plus").style.display = "block";
            }
        }
    );
    $.ajax({
            type: "GET",
            url: "/rest/get_meds",
            dataType: "JSON",
            success: function (data) {
                $("#med_select").innerHTML = collectOptions(data);
                $("#med_plus").style.display = "block";
            }
        }
    )
}

function showNewSym() {
    $("#sym_select").style.display = "block";
    $("#sym_span").style.display = "block";
    $("#sym_rate").style.display = "block";
}

function showNewMed() {
    $("#med_select").style.display = "block";
    $("#med_span").style.display = "block";
    $("#med_rate").style.display = "block";
}

function deleteSym(id) {
    //TODO
}

function deleteMed(id) {
    //TODO
}

function collectOptions(data) {
    var j = -1;
    var arr = [];
    for (var i = 0; i < data.length; i++) {
        arr[++j] = '<option value="' + data.symptomId + '">' + data.name + '</option>';
    }
    return arr;
}