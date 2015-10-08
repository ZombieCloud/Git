<?php // query.php
    
require_once 'login.php';

$db_server = mysql_connect($db_hostname, $db_username, $db_password);
if (!$db_server) die("Невозможно подключиться к MySQL: " . mysql_error());

mysql_select_db($db_database, $db_server) or die("Невозможно выбрать базу данных: " . mysql_error());



//Удаление
if (isset($_POST['delete']) && isset($_POST['num']))
{
    $num = get_post('num');

    $query = "DELETE FROM tab_words1 WHERE num='$num'";
    
    if (!mysql_query($query, $db_server))  echo "Сбой при удалении данных: $query<br>" . mysql_error() . "<br><br>";
}


//Вставка
if (isset($_POST['en']) && isset($_POST['ru']) && isset($_POST['level']) && isset($_POST['hit']) && isset($_POST['num']))
{
    $en = get_post('en');
    $ru = get_post('ru');
    $level = get_post('level');
    $hit = get_post('hit');
    $num = get_post('num');
    $query = "INSERT INTO tab_words1 (en, ru, level, hit, num) VALUES" . "('$en', '$ru', '$level', '$hit', '$num')";

    if (!mysql_query($query, $db_server))  echo "Сбой при вставке данных: $query<br>" . mysql_error() . "<br><br>";
}


//Вывод формы для добавления записи
echo <<<_END
<form action="index.php" method="post">
    <pre>
        en <input type="text" name="en">
        ru <input type="text" name="ru">
        level <input type="text" name="level">
        hit <input type="text" name="hit">
        num <input type="text" name="num">
        <input type="submit" value="ADD RECORD"> 
    </pre>
</form>
_END;




//Вывод таблицы
$query = "SELECT * FROM tab_words1";
$result = mysql_query($query);
if (!$result) die ("Сбой при доступе к базе данных: " . mysql_error());


$rows = mysql_num_rows($result);
for ($j = 0 ; $j < $rows ; ++$j)
{
    $row = mysql_fetch_row($result);

    echo <<<_END
    <pre>
        en $row[0]
        ru $row[1]
        level $row[2]
        hit $row[3]
        num $row[4]
    </pre>
    
    <form action="index.php" method="post">
        <input type="hidden" name="delete" value="yes">
        <input type="hidden" name="num" value="$row[4]">
        <input type="submit" value="DELETE RECORD">
    </form>
_END;
}

mysql_close($db_server);






function get_post($var)
{
    return mysql_real_escape_string($_POST[$var]);
}

?>