<?php // query.php
    
$tmp1 = 'i_am_tmp1';
$tmp2 = 'i_am_tmp2';
$tmp3 = 'i_am_tmp3';

session_start();
$_SESSION['tmp__1'] = $tmp1;
$_SESSION['tmp__2'] = $tmp2;
$_SESSION['tmp__3'] = $tmp3;

echo 'Saved variables $tmp1, $tmp2, $tmp3<br>';
echo "With  ".$tmp1.",   ",   "".$tmp2.",   ",   "".$tmp3;
echo "<p><a href=continue.php>Click here to continue</a></p>";

?>