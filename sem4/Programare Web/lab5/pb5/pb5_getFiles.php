<?php
header("Access-Control-Allow-Origin: *");
$path = $_GET['path'];
$myfile = fopen($path, "r") or die("Unable to open file!");
$filesize = filesize($path);
if ($filesize > 0) {
    echo fread($myfile, $filesize);
} else {
    echo "File is empty.";
}
fclose($myfile);