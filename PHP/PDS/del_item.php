<?php
require_once 'config.php';

if (isset($_GET['delete'])) {
    $id = intval($_GET['delete']);

    $stmt = $conn->prepare("DELETE FROM pageone WHERE pds_id = ?");
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $stmt->close();
    $conn->close();

    header("Location: main.php");
    exit();
} else {
    header("Location: main.php");
    exit();
}
?>
