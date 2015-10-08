<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        //echo 'Привет !!!';
            $paper = array("Copier", "Inkjet", "Laser", "Photo");

            $j = 0;
            foreach ($paper as $item)
            {
                echo "$j: $item<br>";
                ++$j;
            }

        ?>
    </body>
</html>
