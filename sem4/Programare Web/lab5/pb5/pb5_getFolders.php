<?php
header("Access-Control-Allow-Origin: *");
$path = $_GET['path'];
$dir = scandir($path);
printf(json_encode($dir));