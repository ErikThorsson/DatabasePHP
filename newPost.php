<?php

$servername = "localhost";
$username = "root";
$pass = $_POST['pass'];
$DB = "RG";

// Create connection
$conn = new mysqli($servername, $username, $password, $DB);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$insert=$_POST['insert'];
$sql = "INSERT INTO Posts(user, title, txt, lat, longi)
VALUES $insert";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
exit();  // exit without auto_append_file
?>