<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "lab5");
if ($mysqli->connect_error) {
    exit("Couldn't connect to DB");
}

$query = "SELECT * FROM persoane;";
$statement = $mysqli->prepare($query);
$statement->execute();
$statement->bind_result($Nume, $Prenume, $Telefon, $Email);
$json = array();

while($row = $statement->fetch())
{
    $json[]= array(
        'Nume' => $Nume,
        'Prenume' => $Prenume,
        'Telefon' => $Telefon,
        'Email' => $Email
    );
}

$jsonstring = json_encode($json);
echo $jsonstring;