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

$insert=$_POST['insert'];
$id=$_POST['id'];
$id = "_".$id;

 $sql = "INSERT INTO $id(user, txt)
 VALUES $insert";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

exit();  
?>