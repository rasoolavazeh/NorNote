<?php
header("Content-Type : application/json; charset=UTF-8");

// Get data from client
$json = file_get_contents("php://input");
$data = json_decode($json);
$username = $data->username;
$password = $data->password;

// Work in Database
$connection = new mysqli("localhost", "root", "", "NorNote");
if ($connection->connect_error) {
    die("Connection Error : " . $connection->connect_error);
}
$query = "select * from Users where username = '$username'";
$result = $connection->query($query);
if ($result->num_rows > 0) {
    $jsonObject = array("isExist" => true);
    echo json_encode($jsonObject);
} else {
    $insert = "insert into Users(username, password) values ('$username', '$password')";
    if ($connection->query($insert) == true) {
        $jsonObject = array("isExist" => false);
        echo json_encode($jsonObject);
    } else {
        echo "Insertion Error : " . $connection->error;
    }
}

$connection->close();
?>