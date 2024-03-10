<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "lab5");
if ($mysqli->connect_error) {
    exit("Couldn't connect to DB");
}

$query = "SELECT sosiri FROM trenuri WHERE plecari=?;";
$statement = $mysqli->prepare($query);
$statement->bind_param('s', $_GET['plecare']);
$statement->execute();
$statement->bind_result($result);

echo "<table>";

while ($statement->fetch()) {
    echo "<tr><td>$result</td></tr>";
}
echo "</table>";