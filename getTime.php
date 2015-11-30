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

$hr=$_POST['hr'];
$min=$_POST['min'];
$day=$_POST['day'];
$mode=$_POST['mode'];
$dis=doubleval($_POST['dis']);
$myLat=doubleval($_POST['myLat']);
$myLongi=doubleval($_POST['myLongi']);

if($dis == -1) {

if($mode === "hour") {
$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE HOUR(ts) >= '".$hr."' AND DAY(ts) >= '".$day."'
        ";

} else if($mode === "min") {

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE MINUTE(ts) >= '".$min."' AND HOUR(ts) >= '".$hr."'AND DAY(ts) >= '".$day."' 
        ";

} else {

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE DAY(ts) >= '".$day."'
        ";	
}

} else {

if($mode === "hour") {
$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE HOUR(ts) >= '".$hr."' AND DAY(ts) >= '".$day."' AND 69 * haversine(lat,longi,'".$myLat."', '".$myLongi."') < '".$dis."' 
        ";

} else if($mode === "min") {

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE MINUTE(ts) >= '".$min."' AND HOUR(ts) >= '".$hr."'AND DAY(ts) >= '".$day."' AND 69 * haversine(lat,longi,'".$myLat."', '".$myLongi."') < '".$dis."' 
        ";

} else {

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE DAY(ts) >= '".$day."'AND 69 * haversine(lat,longi,'".$myLat."', '".$myLongi."') < '".$dis."' 
        ";	
	}	
}

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