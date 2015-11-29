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

$delete=$_POST['delete'];

$sql = "DELETE FROM USERS
WHERE '".$delete."' like user
";

if ($conn->query($sql) === TRUE) {
    echo "Delete successful";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
exit();  