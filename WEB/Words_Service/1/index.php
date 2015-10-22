<?php

require_once 'login.php';

$connection  =  new  mysqli($db_hostname,  $db_username,  $db_password,  $db_database);
if ($connection->connect_error) die($connection->connect_error);
$connection->set_charset("utf8");


//Англ.слово
if (isset($_GET['NUM_EN']))
{    
    $num = get_post($connection, 'NUM_EN');
    $query = "SELECT en FROM tab_words1 WHERE num='$num'";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);    
    echo $row['en'];
}

//Рус.слово
if (isset($_GET['NUM_RU']))
{    
    $num = get_post($connection, 'NUM_RU');
    $query = "SELECT ru FROM tab_words1 WHERE num='$num'";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);    
    echo $row['ru'];
}

//Англ.звукозапись
if (isset($_GET['NUM_EN_SOUND']))
{    
    $num = get_post($connection, 'NUM_EN_SOUND');
    $query = "SELECT en_sound FROM tab_words1 WHERE num='$num'";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);    
    echo $row['en_sound'];
}

//Рус.звукозапись
if (isset($_GET['NUM_RU_SOUND']))
{    
    $num = get_post($connection, 'NUM_RU_SOUND');
    $query = "SELECT ru_sound FROM tab_words1 WHERE num='$num'";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);    
    echo $row['ru_sound'];
}

$result->close();
$connection->close();




function get_post($connection, $var)
{
    return $connection->real_escape_string($_GET[$var]);
}

?>