<?php
header("Content-Type : application/json; charset=UTF-8");

// Get data from client
$request = file_get_contents("php://input");
$json = json_decode($request);
$id = $json->id;
$title = $json->title;
$content = $json->content;

// Work in Database
$connection = new mysqli("localhost", "root", "", "NorNote");
if ($connection->connect_error) {
    die("Connect Error : " . $connection->connect_error);
}
$query = "update Notes set title = '$title', content = '$content' where id = $id";
$result = $connection->query($query);
if ($request == true) {
    $response = array("edited" => true);
    echo json_encode($response);
} else {
    $response = array("edited" => false);
    echo json_encode($response);
}

$connection->close();
?>