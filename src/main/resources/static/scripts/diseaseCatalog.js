function swapVisibility(elId) {
    var trueElId = "i" + elId;
    if (document.getElementById(trueElId).style.display === "none") {
        document.getElementById(trueElId).style.display = "block";
    } else {
        document.getElementById(trueElId).style.display = "none";
    }
}