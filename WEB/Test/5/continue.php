<?php
echo "Hi !!!<br><br>";

session_start();
if (isset($_SESSION['tmp__1'])) $t1 = $_SESSION['tmp__1'];
if (isset($_SESSION['tmp__2'])) $t2 = $_SESSION['tmp__2'];
if (isset($_SESSION['tmp__3'])) $t3 = $_SESSION['tmp__3'];

echo "$t1<br>";
echo "$t2<br>";
echo "$t3<br>";

session_destroy();
?>

