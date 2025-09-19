<?php
session_start();

if (!isset($_SESSION['highlighted'])) {
    $_SESSION['highlighted'] = [];
    $_SESSION['total'] = 0;
}

if (isset($_POST['btnSave'])) {
    $newNumber = rand(0, 9);
    $_SESSION['highlighted'][] = $newNumber;
    $_SESSION['total'] += $newNumber;
}

$highlightedNumbers = $_SESSION['highlighted'];
$total = $_SESSION['total'];

$lastHighlighted = end($highlightedNumbers);

function Number($n, $highlightedNumbers) {
    $color = in_array($n, $highlightedNumbers) ? 'yellow' : 'white';
    return '<td><div class="box" style="background:' . $color . '">' . $n . '</div></td>';
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Practical</title>
    <style>
        .box {
            height: 50px;
            width: 50px;
            border: solid 1px black;
            text-align: center;
            line-height: 50px;
            font-weight: bold;
        }
    </style>
</head>
<body align='center'>
    
    <h3>Total of Highlighted Numbers: <?php echo $total; ?></h3>

    <form method="post">
        <table align='center'>
            <?php
            for ($y = 1; $y <= 10; $y++) {
                echo '<tr>';
                for ($x = 1; $x <= 10; $x++) {
                    echo Number(rand(0, 9), $highlightedNumbers);
                }
                echo '</tr>';
            }
            ?>
        </table>
        <br>
        <input type="submit" name="btnSave" value="Submit">
    </form>
</body>
</html>
