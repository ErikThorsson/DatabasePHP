<?php
$servername = "localhost";
$username = "root";
$password = "eko";
$DB = "test";

// Create connection
$conn = new mysqli($servername, $username, $password, $DB);
//mysql_select_db('test'); 

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "CREATE TABLE Posts (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(30) NOT NULL,
post VARCHAR(30) NOT NULL,
location VARCHAR(10),
reg_date TIMESTAMP
)";

if ($conn->query($sql) === TRUE) {
    echo "Table Posts created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
?> 