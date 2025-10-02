<?php
require_once 'config.php';

if (!isset($_GET['token']) || empty($_GET['token'])) {
    die("Invalid request.");
}

$token = intval($_GET['token']);

$stmt = $conn->prepare("SELECT * FROM pageone WHERE pds_id = ?");
$stmt->bind_param("i", $token);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 0) {
    die("Record not found.");
}

$row = $result->fetch_assoc();
$stmt->close();
$conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Record</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-light">

<div class="container mt-4">
    <div class="card">
        <div class="card-header bg-dark text-white">
            <h4>Record Details</h4>
        </div>
        <div class="card-body">
            <table class="table table-bordered">
                <tr><th>Full Name</th><td><?= htmlspecialchars($row['pds_fname'] . ' ' . $row['pds_mname'] . ' ' . $row['pds_lname']); ?></td></tr>
                <tr><th>Age</th><td><?= htmlspecialchars($row['pds_age']); ?></td></tr>
                <tr><th>Sex</th><td><?= htmlspecialchars($row['pds_sex']); ?></td></tr>
                <tr><th>Civil Status</th><td><?= htmlspecialchars($row['pds_civil_status']); ?></td></tr>
                <tr><th>Height</th><td><?= htmlspecialchars($row['pds_height']); ?></td></tr>
                <tr><th>Weight</th><td><?= htmlspecialchars($row['pds_weight']); ?></td></tr>
                <tr><th>Blood Type</th><td><?= htmlspecialchars($row['pds_blood_type']); ?></td></tr>
                <tr><th>GSIS ID</th><td><?= htmlspecialchars($row['pds_gsis_no']); ?></td></tr>
                <tr><th>PAGIBIG ID</th><td><?= htmlspecialchars($row['pds_pagibig_no']); ?></td></tr>
                <tr><th>PhilHealth ID</th><td><?= htmlspecialchars($row['pds_philhealth_no']); ?></td></tr>
                <tr><th>SSS No</th><td><?= htmlspecialchars($row['pds_sss_no']); ?></td></tr>
                <tr><th>Tin ID</th><td><?= htmlspecialchars($row['pds_tin_no']); ?></td></tr>
                <tr><th>Agency Employee ID</th><td><?= htmlspecialchars($row['pds_agency_employee_no']); ?></td></tr>
                <tr><th>Citizenship</th><td><?= htmlspecialchars($row['pds_citizenship']); ?></td></tr>
                <tr><th>Email Address</th><td><?= htmlspecialchars($row['pds_email']); ?></td></tr>
                <tr><th>Residential Address</th><td><?= htmlspecialchars($row['pds_res_address']); ?></td></tr>
                <tr><th>Residential Zip Code</th><td><?= htmlspecialchars($row['pds_res_zip']); ?></td></tr>
                <tr><th>Permanent Address</th><td><?= htmlspecialchars($row['pds_perm_address']); ?></td></tr>
                <tr><th>Permanent Zip Code</th><td><?= htmlspecialchars($row['pds_perm_zip']); ?></td></tr>
                <tr><th>Telephone No</th><td><?= htmlspecialchars($row['pds_mobile_no']); ?></td></tr>
                <tr><th>Mobile No</th><td><?= htmlspecialchars($row['pds_mobile_no']); ?></td></tr>
            </table>
            <a href="main.php" class="btn btn-secondary"><i class="fa fa-arrow-left"></i> Back</a>
        </div>
    </div>
</div>

</body>
</html>
