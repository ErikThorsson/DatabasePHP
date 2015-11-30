<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = "eko";
$DB = "RG";

$conn = mysql_connect($dbhost, $dbuser, $dbpass, $DB);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$id=$_POST['id'];

$sql = "SELECT USER        
		FROM LIKES
		WHERE POST_ID = '".$id."'
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