<?php
header("Content-Type : application/json; charset=UTF-8");

// Get data from client
$request = file_get_contents("php://input");
$json = json_decode($request);
$username = $json[0]->username;

// Work in Database
$connection = new mysqli("localhost", "root", "", "NorNote");
if ($connection->connect_error) {
    die("Connection Error : " . $connection->connect_error);
}
$query = "select * from Notes where user = '$username'";
$result = $connection->query($query);
if ($result->num_rows > 0) {
    $notes = array();
    for ($i = 0;$i < $result->num_rows;$i++) {
        $row = $result->fetch_assoc();
        $note = array("id" => $row["id"], "username" => $row["user"],
            "title" => $row["title"], "content" => $row["content"]);
        $notes[$i] = $note;
    }
    echo json_encode($notes);
} else {
    $notes = array();
    echo json_encode($notes);
}

$connection->close();
?>