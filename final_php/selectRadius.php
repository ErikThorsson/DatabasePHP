<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = 'eko';
$DB = "RG";

$conn = mysql_connect($dbhost, $dbuser, $dbpass, $DB);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$dis=doubleval($_POST['dis']);
$myLat=doubleval($_POST['myLat']);
$myLongi=doubleval($_POST['myLongi']);


$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE 69 * haversine(lat,longi,'".$myLat."', '".$myLongi."') < '".$dis."' 
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