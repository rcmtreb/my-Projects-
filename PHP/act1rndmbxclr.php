<?php
function getRandomColor() {
    $rand = rand(1, 5);
    switch ($rand) {
        case 1:
            return '#FF0000';
        case 2:
            return '#FFA500';
        case 3:
            return '#FFFF00';
        case 4:
            return '#008000';
        case 5:
            return '#0000FF';
        default:
            return '#FFFFFF';
    }
}

echo "<table style='border-collapse: collapse;'>";

for ($i = 0; $i < 10; $i++) {
    echo "<tr>";
    for ($j = 0; $j < 10; $j++) {
        $color = getRandomColor();
        echo "<td style='width: 40px; height: 40px; background-color: $color; border: 1px solid #000;'></td>";
    }
    echo "</tr>";
}

echo "</table>";
?>
