<?php
// Conectarea la baza de date
$servername = "localhost";
$username = "utilizator";
$password = "parola";
$dbname = "nume_baza_date";

$conn = new mysqli($servername, $username, $password, $dbname);

// Verificarea conexiunii
if ($conn->connect_error) {
    die("Conexiunea la baza de date a eșuat: " . $conn->connect_error);
}

// Obținerea datelor introduse de utilizator din formularul HTML
$localitate_plecare = $_POST['localitate_plecare'];
$localitate_sosire = $_POST['localitate_sosire'];
$optiune_legatura = isset($_POST['legatura']) ? $_POST['legatura'] : false;

// Construirea interogării bazate pe opțiunile selectate de utilizator
if ($optiune_legatura) {
    // Căutare trenuri cu legătură
    $sql = "SELECT t1.nr_tren, t1.tip_tren, t1.localitate_plecare, t1.localitate_sosire, t1.ora_plecare, t1.ora_sosire
            FROM trenuri t1
            INNER JOIN trenuri t2 ON t1.localitate_sosire = t2.localitate_plecare
            WHERE t1.localitate_plecare = '$localitate_plecare' AND t2.localitate_sosire = '$localitate_sosire'";
} else {
    // Căutare doar trenuri directe
    $sql = "SELECT * FROM trenuri WHERE localitate_plecare = '$localitate_plecare' AND localitate_sosire = '$localitate_sosire'";
}

// Executarea interogării și obținerea rezultatelor
$result = $conn->query($sql);

// Afișarea rezultatelor
if ($result->num_rows > 0) {
    echo "<table>";
    echo "<tr><th>Nr. Tren</th><th>Tip Tren</th><th>Localitate Plecare</th><th>Localitate Sosire</th><th>Ora Plecare</th><th>Ora Sosire</th></tr>";
    
    while ($row = $result->fetch_assoc()) {
        echo "<tr><td>".$row["nr_tren"]."</td><td>".$row["tip_tren"]."</td><td>".$row["localitate_plecare"]."</td><td>".$row["localitate_sosire"]."</td><td>".$row["ora_plecare"]."</td><td>".$row["ora_sosire"]."</td></tr>";
    }
    
    echo "</table>";
} else {
    echo "Nu s-au găsit trenuri disponibile.";
}

// Închiderea conexiunii la baza de date
$conn->close();
?>
