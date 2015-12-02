<?php

$servername = "localhost";
$username = "root";
$pass = "eko";
$DB = "RG";

// Create connection
$conn = new mysqli($servername, $username, $pass, $DB);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$user = $_POST['user'];
$userPass = $_POST['userPass'];

$hash = md5($user."Capsicum annuum");
$pHash = md5($userPass);
$insert = "('$user','$pHash', '$hash')";

$sql = "INSERT INTO USERS(user, pass, photo)
VALUES $insert";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
exit();  // exit without auto_append_file
?>