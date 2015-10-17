<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = 'eko';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$sql = 'SELECT title, post, 
               location, reg_date
        FROM Posts';

mysql_select_db('test');
$retval = mysql_query( $sql, $conn );

if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}

while($e=mysql_fetch_assoc($retval))
	$output[] = $e;

print(json_encode($output));
?> 