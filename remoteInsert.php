<?php
$insert=$_POST["insert"];
echo" Insert = ".$insert."\n";
$link=mysql_connect("localhost", "root","eko", "test");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//mysql_select_db("test");

$sql = "INSERT INTO Posts(title, post, location, reg_date)
VALUES ('test7','dvahjvsgsd','456', '2015-10-6 05:54:13')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
exit();  // exit without auto_append_file
?>