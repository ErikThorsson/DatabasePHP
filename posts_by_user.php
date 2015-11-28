<?php
$dbhost = 'localhost';
$dbuser = 'root';
//$dbpass = $_POST['pass'];
$dbpass = 'eko';
$DB = "RG";

$conn = mysql_connect($dbhost, $dbuser, $dbpass, $DB);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$userN=$_POST['user'];
$userN="Dan";

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE '".$userN."' = user
        ";

mysql_select_db('RG');
$retval = mysql_query( $sql, $conn );

if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}

while($e=mysql_fetch_assoc($retval))
	$output[] = $e;

print(json_encode($output));
?> 