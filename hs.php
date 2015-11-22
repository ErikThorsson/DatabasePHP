<?php
function haversineGreatCircleDistance(
  $latitudeFrom, $longitudeFrom, $latitudeTo, $longitudeTo, $earthRadius = 6371000)
{
  // convert from degrees to radians
  $latFrom = deg2rad($latitudeFrom);
  $lonFrom = deg2rad($longitudeFrom);
  $latTo = deg2rad($latitudeTo);
  $lonTo = deg2rad($longitudeTo);

  $latDelta = $latTo - $latFrom;
  $lonDelta = $lonTo - $lonFrom;

  $angle = 2 * asin(sqrt(pow(sin($latDelta / 2), 2) +
    cos($latFrom) * cos($latTo) * pow(sin($lonDelta / 2), 2)));
  return $angle * $earthRadius;
}

function pythag($latFrom, $latTo, $lTo, $longTo)
{
  $d0 = deg2rad($latFrom - $latTo);
  $d1 = deg2rad($longFrom - $longTo);
  return (6371000 * sqrt(pow($d0, 2) + pow($d1, 2))) / 1609;
   
}
echo pythag(0,0,5,10);

//$d = deg2rad(10);
//echo pow($d,2);
echo "<br>";

echo haversineGreatCircleDistance(0,0,5,10)/1609;


?>