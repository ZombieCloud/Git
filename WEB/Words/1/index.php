<?php // query.php
    
require_once 'login.php';



$connection  =  new  mysqli($db_hostname,  $db_username,  $db_password,  $db_database);
if ($connection->connect_error) die($connection->connect_error);



/*
//Удаление
if (isset($_POST['delete']) && isset($_POST['num']))
{
    $num = get_post($connection, 'num');

    $query = "DELETE FROM tab_words1 WHERE num='$num'";

    $result = $connection->query($query);
    if (!$result) die ($connection->error);
}


//Вставка
if (isset($_POST['en']) && isset($_POST['ru']) && isset($_POST['level']) && isset($_POST['hit']) && isset($_POST['num']))
{
    $en = get_post($connection, 'en');
    $ru = get_post($connection, 'ru');
    $level = get_post($connection, 'level');
    $hit = get_post($connection, 'hit');
    $num = get_post($connection, 'num');
    $query = "INSERT INTO tab_words1 (en, ru, level, hit, num) VALUES" . "('$en', '$ru', '$level', '$hit', '$num')";

    $result = $connection->query($query);
    if (!$result) die ($connection->error);
}
*/





?>

<form action="index.php" method="GET">
    <pre>
        First word <input type="text" name="W_FIRST_WORD">
        Last word <input type="text" name="W_LAST_WORD">
        Level <input type="text" name="W_LEVEL">     
        
        <textarea name="W_TEXT" rows="5" cols="100">
            <?php echo '' ?>
        </textarea>
              
        <button type='submit' name='W_NEXT'>Next</button>
        <button type='submit' name='W_TRANSLATE'>Translate</button>
        <button type='submit' name='W_UP'>Up</button>
        <button type='submit' name='W_DOWN'>Down</button>        
    </pre>
</form>

<?php



if (isset($_GET['W_FIRST_WORD']))
{
    echo 'Privet1: <br>';
    $num = get_post($connection, 'W_FIRST_WORD');
    $query = "SELECT * FROM tab_words1 WHERE num='$num'";
//    $query = "SELECT * FROM tab_words1 WHERE num=1";
    $result = $connection->query($query);
    if (!$result) die ($connection->error);
    
//    echo 'Privet2: <br>';
    $result->data_seek(0);
    $row = $result->fetch_array(MYSQLI_ASSOC);
//    $row = $result->fetch_array(MYSQLI_NUM);
    
    echo 'en: ' . $row['en'] . '<br>';
//    echo 'en: ' . $row[1] . '<br>';
} else {
    echo 'Not set <br>';
}



/*
        <input type="submit" name="W_NEXT" value="Next">     
        <input type="submit" name="W_TRANSLATE" value="Translate">        
        <input type="submit" name="W_UP" value="Up">
        <input type="submit" name="W_DOWN" value="Down">
 */
 


/*
//Вывод таблицы
$query = "SELECT * FROM tab_words1";
$result = $connection->query($query);
if (!$result) die ($connection->error);
*/


/*
$rows = $result->num_rows;
for ($j = 0 ; $j < $rows ; ++$j)
{
    $result->data_seek($j);
    $row = $result->fetch_array(MYSQLI_ASSOC);
//    $row = $result->fetch_array(MYSQLI_NUM);    
    echo 'en: ' . $row['en'] . '<br>';
    echo 'ru: ' . $row['ru'] . '<br>';
    echo 'level: ' . $row['level'] . '<br>';
    echo 'hit: ' . $row['hit'] . '<br>';
    echo 'num: ' . $row['num'] . '<br><br>';
    
    
    echo '<form action="index.php" method="post">';
    echo '<input type="hidden" name="delete" value="yes">';
    echo '<input type="hidden" name="num" value="'. $row['num'] .'">';
    echo '<input type="submit" value="DELETE RECORD">';
    echo '</form>';    
/*    
    echo <<<_END
    <form action="index.php" method="post">   
        <input type="hidden" name="delete" value="yes">
        <input type="hidden" name="num" value="$row['num']">
        <input type="submit" value="DELETE RECORD">            
    </form>
*/            
//_END;
//}



$result->close();
$connection->close();




function get_post($connection, $var)
{
    return $connection->real_escape_string($_GET[$var]);
}

?>