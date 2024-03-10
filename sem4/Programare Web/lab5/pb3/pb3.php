<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "lab5");
if ($mysqli->connect_error) {
    exit("Couldn't connect to DB");
}

$query = "SELECT * FROM studentsfr;";
$statement = $mysqli->prepare($query);
$statement->execute();
$statement->bind_result($id, $firstname, $lastname, $phone, $email);
$json = array();

while($row = $statement->fetch())
{
    $json[]= array(
        'studentid' => $id,
        'firstname' => $firstname,
        'lastname' => $lastname,
        'phone' => $phone,
        'email' => $email
    );
}

$jsonstring = json_encode($json);
echo $jsonstring;