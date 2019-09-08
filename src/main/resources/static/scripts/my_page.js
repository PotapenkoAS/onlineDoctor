var edit = false;

function editor() {
    var nameH = document.getElementById("nameH");
    var nameI = document.getElementById("nameI");
    var surnameI = document.getElementById("surnameI");
    var heightP = document.getElementById("heightP");
    var heightI = document.getElementById("heightI");
    var weightP = document.getElementById("weightP");
    var weightI = document.getElementById("weightI");
    if (!edit) {
        nameH.style.display = "none";
        nameI.style.display = "block";
        surnameI.style.display = "block";
        nameI.value = nameH.innerText.split(" ")[1];
        surnameI.value = nameH.innerText.split(" ")[0];
        heightP.style.display = "none";
        heightI.style.display = "block";
        heightI.value = heightP.innerText;
        weightP.style.display = "none";
        weightI.style.display = "block";
        weightI.value = weightP.innerText;
        edit = true;
    } else {
        $.ajax({
            type: "post",
            url: "/client/update",
            data: {
                name: nameI.value,
                surname: surnameI.value,
                height: heightI.value,
                weight: weightI.value
            }
        });
        updateBodyMass(heightI.value, weightI.value);
        nameH.innerText = surnameI.value + ' ' + nameI.value;
        nameH.style.display = "block";
        nameI.style.display = "none";
        surnameI.style.display = "none";
        heightP.innerText = heightI.value;
        heightP.style.display = "block";
        heightI.style.display = "none";
        weightP.innerText = weightI.value;
        weightP.style.display = "block";
        weightI.style.display = "none";
        edit = false;
    }
}

function updateBodyMass(height, weight) {
    var bodyMass = document.getElementById("bodyMass");
    var indexValue = (weight / Math.pow(height / 100, 2)).toFixed(2);
    var indexText;
    var color;
    if(indexValue<=16){
        indexText = 'Выраженный дефицит массы тела';
        color = 'red';
    }else if(indexValue>16 && indexValue<=18.5){
        indexText = 'Недостаточная масса тела';
        color = 'yellow';
    }else if(indexValue>18.5 && indexValue<=24.99){
        indexText = 'Норма';
        color = 'green'
    }else if(indexValue>24.99 && indexValue<=30){
        indexText = 'Избыточная масса тела(предожирение)';
        color = 'yellow';
    }else if(indexValue>30 && indexValue<=35){
        indexText = 'Ожирение';
        color = 'pink';
    }else if(indexValue>35 && indexValue<=40){
        indexText = 'Ожирение резкое';
        color = 'red';
    }else if(indexValue>40 ){
        indexText = 'Очень резкое ожирение';
        color = 'darkred';
    }
    bodyMass.innerText = indexValue + ' - ' + indexText;
    bodyMass.style.color = color;
}