<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}

$sql = 'SELECT title, post, 
               location, reg_date
        FROM Posts';

mysql_select_db('testDB');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Title :{$row['title']}  <br> ".
         "Post: {$row['post']} <br> ".
         "location: {$row['location']} <br> ".
         "timestamp: {$row['reg_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";
$conn->close();
?> 