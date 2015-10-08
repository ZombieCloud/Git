<?php // query.php
    require_once 'login.php';
    


    
    
    $db_server = mysql_connect($db_hostname, $db_username, $db_password);
    if (!$db_server) die("Невозможно подключиться к MySQL: " . mysql_error());
    
    mysql_select_db($db_database) or die("Невозможно выбрать базу данных: " . mysql_error());
    
    $query = "SELECT * FROM tab_words1";
    $result = mysql_query($query);
    if (!$result) die ("Сбой при доступе к базе данных: " . mysql_error());    
    $rows = mysql_num_rows($result); 
    
    
    
    
    // Извлечь строку
    for ($j = 0 ; $j < $rows ; ++$j)
    {
        $row = mysql_fetch_row($result);
        echo 'en: ' . $row[0] . '<br>';
        echo 'ru: ' . $row[1] . '<br>';
        echo 'level: ' . $row[2] . '<br>';
        echo 'hit: ' . $row[3] . '<br>';
        echo 'num: ' . $row[4] . '<br><br>';
    }    
    
    

/*   
    // Извлечь ячейку     
    for ($j = 0 ; $j < $rows ; ++$j)
    {
        echo 'en: ' . mysql_result($result,$j,'en') . '<br>';
        echo 'ru: ' . mysql_result($result,$j,'ru') . '<br>';
        echo 'level: ' . mysql_result($result,$j,'level') . '<br>';        
        echo 'hit: ' . mysql_result($result,$j,'hit') . '<br>';
        echo 'num: ' . mysql_result($result,$j,'num') . '<br><br>';
    }      
*/
    
    mysql_close($db_server);
?>