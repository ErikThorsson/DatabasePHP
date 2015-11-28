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

$sql = "CREATE TABLE Posts (
id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
user VARCHAR(16) NOT NULL,
title VARCHAR(100) NOT NULL,
txt VARCHAR(500) NOT NULL,
lat FLOAT(8),
longi FLOAT(8),
ts TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)";

if ($conn->query($sql) === TRUE) {
    echo "Table Posts created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();

?> 