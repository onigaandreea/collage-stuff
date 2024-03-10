let students = [];
let currentPage = 0;
let pages = 3;

function renderStudents() {
    let table = document.getElementById("students");
    table.innerHTML = "";
    let start = currentPage * pages;
    let end = start + pages;
    let studentsOnPage = students.slice(start, end);

    for (let i = 0; i < studentsOnPage.length; i++) {
        let row = table.insertRow(i);
        let firstName = row.insertCell(0);
        let lastName = row.insertCell(1);
        let phone = row.insertCell(2);
        let email = row.insertCell(3);

        firstName.innerHTML = studentsOnPage[i].Nume;
        lastName.innerHTML = studentsOnPage[i].Prenume;
        phone.innerHTML = studentsOnPage[i].Telefon;
        email.innerHTML = studentsOnPage[i].Email;

        const prevBtn = document.getElementById('prev');
        prevBtn.disabled = currentPage === 0;

        const lastPage = Math.floor((students.length - 1) / pages);
        const nextBtn = document.getElementById('next');
        nextBtn.disabled = currentPage >= lastPage;
    }
}

function previousPage(){
    if(currentPage > 0){
        currentPage--;
        renderStudents();
    }
}

function nextPage(){
    const lastPage = Math.floor((students.length - 1) / pages);
    if(currentPage < lastPage){
        currentPage++;
        renderStudents();
    }
}

function loadStudents() {
    let xhttp;
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            students = JSON.parse(this.responseText);
            console.log(students);
            renderStudents();
        }
    };
    xhttp.open('GET', "http://localhost/pb2.php", true);
    xhttp.send(null);
}

window.onload = function () {
    loadStudents();
}
