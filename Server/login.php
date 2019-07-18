<?php
header("Content-Type : application/json; charset=UTF-8");

// Get data from client
$json = file_get_contents("php://input");
$data = json_decode($json);
$username = $data->username;
$password = $data->password;

// Search in Database
$connection = new mysqli("localhost", "root", "", "NorNote");
if ($connection->connect_error) {
    die("Connection Error : " . $connection->connect_error);
}
$query = "select username, password from Users where username = '$username'";
$result = $connection->query($query);
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if ($row["username"] == $username && $row["password"] == $password) {
        $response = array("isOk" => "true");
        echo json_encode($response);
    } else {
        $response = array("isOk" => "false");
        echo json_encode($response);
    }
} else {
    $response = array("isOk" => "false");
    echo json_encode($response);
}

$connection->close();
?>