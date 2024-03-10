function showRoutes(str) {
    let xhttp;
    if (str === '') {
        document.getElementById('sosiri').innerHTML = '';
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function(){
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById('sosiri').innerHTML = this.responseText;
        }
    };
    xhttp.open('GET', "http://localhost/pb1.php?plecare="+str, true);
    xhttp.send(null);
}