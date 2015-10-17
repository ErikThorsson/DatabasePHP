<?php
$servername = "localhost";
$username = "root";
$password = "eko";

// Create connection
$conn = new mysqli($servername, $username, $password, "test");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO Posts(title, post, location, reg_date)
VALUES ('test6','dvahjvsgsd','456', '2015-10-6 05:54:13')";


if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
?>