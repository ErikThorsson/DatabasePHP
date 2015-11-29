<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = 'eko';
$DB = "RG";

$conn = new mysqli($dbhost, $dbuser, $dbpass, $DB);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$user = $_POST['user'];
$userPass = $_POST['userPass'];
$userPass = substr(md5($userPass), 0, 16);

$sql = "SELECT user, pass, photo
        FROM USERS
        WHERE '".$user."' like user AND '".$userPass."' like pass
        ";

 $result = $conn->query($sql);
if ($result->num_rows > 0) 
{
echo true;
}
exit();

?> 