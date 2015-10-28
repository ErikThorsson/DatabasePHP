<?php

$servername = "localhost";
$username = "root";
$password = "eko";
$DB = "RG";

// Create connection
$conn = new mysqli($servername, $username, $password, $DB);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$insert=$_POST['insert'];
//$insert = "('edd', 'text', '2015-10-27 16:27:10')";
$id=$_POST['id'];
$id = "_".$id;

 $sql = "INSERT INTO $id(user, txt, ts)
 VALUES $insert";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

exit();  
?>