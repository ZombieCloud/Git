<?php

require_once 'login.php';

$connection  =  new  mysqli($db_hostname,  $db_username,  $db_password,  $db_database);
if ($connection->connect_error) die($connection->connect_error);







if (isset($_GET['WORD_NUM']))
{    
    $num = get_post($connection, 'WORD_NUM');
    $query = "SELECT en FROM tab_words1 WHERE num='$num'";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);    
    echo $row['en'];
}






$result->close();
$connection->close();


function get_post($connection, $var)
{
    return $connection->real_escape_string($_GET[$var]);
}

?>