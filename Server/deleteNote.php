<?php
header("Content-Type : application/json; charset = UTF-8");

// Get data from client
$request = file_get_contents("php://input");
$json = json_decode($request);
$id = $json->id;

// Work in Database
$connection = new mysqli("localhost", "root", "", "NorNote");
if ($connection->connect_error) {
    die("Connection Error : " . $connection->connect_error);
}
$query = "delete from Notes where id = $id";
$result = $connection->query($query);
if ($result == true) {
    $response = array("deleted" => true);
    echo json_encode($response);
} else {
    $response = array("deleted" => false);
    echo json_encode($response);
}

$connection->close();
;?>