<?php
$rnumber = rand(0,9);
$count = 0;

function Number($n){
  global $rnumber, $count;
  $color = ($n == $rnumber) ? 'red' : 'white';

  if ($n == $rnumber) {
    $count++;
  }

  return '<td><div class="box" style="background:'. $color . '">' . $n . '</div></td>';
}
?>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
  <meta charset="utf-8">
  <title>Random Box Number</title>
  <style>
    .box {
      height: 50px;
      width: 50px;
      border: solid;
      text-align: center;
      line-height: 50px;
      font-weight: bold;
    }
  </style>
</head>
<body>
  <h3>Highlighted Random Number: <?php echo $rnumber; ?></h3>
  <table>
    <?php
    for($y=1; $y<=10; $y++){
      echo '<tr>';
      for($x=1; $x<=10; $x++){
        echo Number(rand(0,9));
      }
      echo '</tr>';
    }
    ?>
  </table>
  <h4>There are <?php echo $count; ?> boxes that have the value of <?php echo $rnumber; ?>.</h4>
</body>
</html>
