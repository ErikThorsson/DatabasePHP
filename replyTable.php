<?php
$servername = "localhost";
$username = "root";
$password = "eko";
$DB = "RG";

$id=$_POST['id'];
$id = "_".$id;

// Create connection
$conn = new mysqli($servername, $username, $password, $DB);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "CREATE TABLE $id (
id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
user VARCHAR(16) NOT NULL,
txt VARCHAR(500) NOT NULL,
ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)";

if ($conn->query($sql) === TRUE) {
    echo "Reply table $id created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
?> 