function swapVisibility(elId) {
    if (document.getElementById(elId).style.display === "none") {
        document.getElementById(elId).style.display = "block";
    } else {
        document.getElementById(elId).style.display = "none";
    }
}