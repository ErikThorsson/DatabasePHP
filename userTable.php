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

$tableName = "users";

$sql = "CREATE TABLE $tableName (
id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
user VARCHAR(16) NOT NULL,
pass VARCHAR(16) NOT NULL,
photo VARCHAR(32) NOT NULL
)";

if ($conn->query($sql) === TRUE) {
    echo "table created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
?> 