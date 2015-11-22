<?php

$servername = "localhost";
$username = "root";
$pass = $_POST['pass'];
$DB = "RG";

// Create connection
$conn = new mysqli($servername, $username, $pass, $DB);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$delete=$_POST['delete'];
$pid=$_POST['pid'];

$sql = "DELETE FROM Replies
WHERE $delete = id AND post_id = $pid
";

if ($conn->query($sql) === TRUE) {
    echo "Delete successful";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
exit();  