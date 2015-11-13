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

// $dis=($_POST['dis']);
// $myLat=$_POST['lat']);
// $mylongi=($_POST['longi']);

// $dis=doubleval("90");
// $myLat=doubleval("65");
// $myLongi=doubleval("65");

$sql = "SELECT id, user, title, txt, 
               lat, longi, ts
        FROM Posts
        WHERE ABS(lat - '".$myLat."') < '".$dis."' AND ABS(longi - '".$myLongi."') < '".$dis."'
        ";

               // WHERE ABS(65 - '".$myLat."') < '".$dis."' AND ABS(65 - '".$myLongi."') < '".$dis."'



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