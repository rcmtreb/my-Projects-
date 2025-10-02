<?php
$server = "localhost";
$database = "cs3a";
$user = "root";
$pass = "";

$conn = new mysqli($server, $user, $pass, $database);

if ($conn->connect_error) {
    die('Connection Failed: ' . $conn->connect_error);
}
?>
