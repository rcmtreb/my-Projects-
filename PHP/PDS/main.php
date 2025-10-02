<?php
require_once 'config.php';

$msg = '';
$pds_id = 0;
$fname = $mname = $lname = $age = $sex = $civil_status = '';
$height = $weight = $blood_type = $gsis_no = $pagibig_no = $philhealth_no = '';
$sss_no = $tin_no = $agency_employee_no = $citizenship = $res_address = '';
$res_zip = $perm_address = $perm_zip = $tel_no = $mobile_no = $email = '';

if (isset($_GET['msg'])) {
    if ($_GET['msg'] === 'saved') {
        $msg = '<div class="alert alert-success">Record Successfully Saved!</div>';
    } elseif ($_GET['msg'] === 'updated') {
        $msg = '<div class="alert alert-success">Record Successfully Updated!</div>';
    } elseif ($_GET['msg'] === 'deleted') {
        $msg = '<div class="alert alert-success">Record Successfully Deleted!</div>';
    }
}

if (isset($_GET['token'])) {
    $pds_id = intval($_GET['token']);
    $stmt = $conn->prepare("SELECT * FROM pageone WHERE pds_id = ?");
    $stmt->bind_param("i", $pds_id);
    $stmt->execute();
    $result = $stmt->get_result();
    if ($result && $result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $fname = $row['pds_fname'];
        $mname = $row['pds_mname'];
        $lname = $row['pds_lname'];
        $age = $row['pds_age'];
        $sex = $row['pds_sex'];
        $civil_status = $row['pds_civil_status'];
        $height = $row['pds_height'];
        $weight = $row['pds_weight'];
        $blood_type = $row['pds_blood_type'];
        $gsis_no = $row['pds_gsis_no'];
        $pagibig_no = $row['pds_pagibig_no'];
        $philhealth_no = $row['pds_philhealth_no'];
        $sss_no = $row['pds_sss_no'];
        $tin_no = $row['pds_tin_no'];
        $agency_employee_no = $row['pds_agency_employee_no'];
        $citizenship = $row['pds_citizenship'];
        $res_address = $row['pds_res_address'];
        $res_zip = $row['pds_res_zip'];
        $perm_address = $row['pds_perm_address'];
        $perm_zip = $row['pds_perm_zip'];
        $tel_no = $row['pds_tel_no'];
        $mobile_no = $row['pds_mobile_no'];
        $email = $row['pds_email'];
    }
    $stmt->close();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $fname = $_POST['txtFname'] ?? '';
    $mname = $_POST['txtMname'] ?? '';
    $lname = $_POST['txtLname'] ?? '';
    $age = $_POST['txtAge'] ?? '';
    $sex = $_POST['txtSex'] ?? '';
    $civil_status = $_POST['txtCivilStatus'] ?? '';
    $height = $_POST['txtHeight'] ?? '';
    $weight = $_POST['txtWeight'] ?? '';
    $blood_type = $_POST['txtBloodType'] ?? '';
    $gsis_no = $_POST['txtGsis'] ?? '';
    $pagibig_no = $_POST['txtPagibig'] ?? '';
    $philhealth_no = $_POST['txtPhilhealth'] ?? '';
    $sss_no = $_POST['txtSss'] ?? '';
    $tin_no = $_POST['txtTin'] ?? '';
    $agency_employee_no = $_POST['txtAgency'] ?? '';
    $citizenship = $_POST['txtCitizenship'] ?? '';
    $res_address = $_POST['txtResAddress'] ?? '';
    $res_zip = $_POST['txtResZip'] ?? '';
    $perm_address = $_POST['txtPermAddress'] ?? '';
    $perm_zip = $_POST['txtPermZip'] ?? '';
    $tel_no = $_POST['txtTelNo'] ?? '';
    $mobile_no = $_POST['txtMobileNo'] ?? '';
    $email = $_POST['txtEmail'] ?? '';
    $pds_id = isset($_POST['pds_id']) ? intval($_POST['pds_id']) : 0;

    if ($pds_id > 0) {
        $stmt = $conn->prepare("UPDATE pageone SET pds_fname=?, pds_mname=?, pds_lname=?, pds_age=?, pds_sex=?, pds_civil_status=?, pds_height=?, pds_weight=?, pds_blood_type=?, pds_gsis_no=?, pds_pagibig_no=?, pds_philhealth_no=?, pds_sss_no=?, pds_tin_no=?, pds_agency_employee_no=?, pds_citizenship=?, pds_res_address=?, pds_res_zip=?, pds_perm_address=?, pds_perm_zip=?, pds_tel_no=?, pds_mobile_no=?, pds_email=? WHERE pds_id=?");
        $stmt->bind_param("ssssssddsssssssssssssssi", $fname, $mname, $lname, $age, $sex, $civil_status, $height, $weight, $blood_type, $gsis_no, $pagibig_no, $philhealth_no, $sss_no, $tin_no, $agency_employee_no, $citizenship, $res_address, $res_zip, $perm_address, $perm_zip, $tel_no, $mobile_no, $email, $pds_id);
        if ($stmt->execute()) {
            header("Location: main.php?msg=updated");
            exit;
        } else {
            $msg = '<div class="alert alert-danger">Update Failed: ' . htmlspecialchars($conn->error) . '</div>';
        }
        $stmt->close();
    } else {
        $check_stmt = $conn->prepare("SELECT COUNT(*) FROM pageone WHERE pds_email = ?");
        $check_stmt->bind_param("s", $email);
        $check_stmt->execute();
        $check_result = $check_stmt->get_result()->fetch_row();
        $count = $check_result[0];
        $check_stmt->close();

        if ($count > 0) {
            $msg = '<div class="alert alert-danger">Error: A record with this Email Address is already registered.</div>';
        } else {
            $stmt = $conn->prepare("INSERT INTO pageone (pds_fname, pds_mname, pds_lname, pds_age, pds_sex, pds_civil_status, pds_height, pds_weight, pds_blood_type, pds_gsis_no, pds_pagibig_no, pds_philhealth_no, pds_sss_no, pds_tin_no, pds_agency_employee_no, pds_citizenship, pds_res_address, pds_res_zip, pds_perm_address, pds_perm_zip, pds_tel_no, pds_mobile_no, pds_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->bind_param("ssssssddsssssssssssssss", $fname, $mname, $lname, $age, $sex, $civil_status, $height, $weight, $blood_type, $gsis_no, $pagibig_no, $philhealth_no, $sss_no, $tin_no, $agency_employee_no, $citizenship, $res_address, $res_zip, $perm_address, $perm_zip, $tel_no, $mobile_no, $email);
            if ($stmt->execute()) {
                header("Location: main.php?msg=saved");
                exit;
            } else {
                $msg = '<div class="alert alert-danger">Save Failed: ' . htmlspecialchars($conn->error) . '</div>';
            }
            $stmt->close();
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Personal Data Sheet</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f8f9fa; }
        .container { max-width: 1000px; }
        .pds-form { background-color: #ffffff; border: 1px solid #dee2e6; border-radius: .25rem; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,.06); }
        legend { font-size: 1.4rem; font-weight: bold; color: #333; border-bottom: 2px solid #007bff; padding-bottom: 8px; margin-bottom: 18px; text-align:center; }
        .form-label { font-weight: 500; }
        .form-control, .form-select { border-radius: 0; }
        .btn-primary { border-radius: 0; background-color: #007bff; border-color: #007bff; }
        .btn-primary:hover { background-color: #0056b3; border-color: #0056b3; }
        .table th, .table td { vertical-align: middle; }
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="pds-form">
        <fieldset>
            <legend>Personal Data Sheet</legend>
            <?php echo $msg; ?>
            <form method="post" id="pdsForm" autocomplete="off">
                <input type="hidden" name="pds_id" value="<?php echo htmlspecialchars($pds_id); ?>">
                <div class="row g-3">
                    <div class="col-md-4">
                        <label class="form-label">First Name</label>
                        <input type="text" name="txtFname" class="form-control" required value="<?php echo htmlspecialchars($fname); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Middle Name</label>
                        <input type="text" name="txtMname" class="form-control" required value="<?php echo htmlspecialchars($mname); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Last Name</label>
                        <input type="text" name="txtLname" class="form-control" required value="<?php echo htmlspecialchars($lname); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-2">
                        <label class="form-label">Age</label>
                        <input type="number" name="txtAge" class="form-control" required value="<?php echo htmlspecialchars($age); ?>">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Sex</label>
                        <input type="text" name="txtSex" class="form-control" required value="<?php echo htmlspecialchars($sex); ?>">
                    </div>
                    <div class="col-md-7">
                        <label class="form-label">Civil Status</label>
                        <input type="text" name="txtCivilStatus" class="form-control" required value="<?php echo htmlspecialchars($civil_status); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-4">
                        <label class="form-label">Height (cm)</label>
                        <input type="number" name="txtHeight" class="form-control" required value="<?php echo htmlspecialchars($height); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Weight (kg)</label>
                        <input type="number" name="txtWeight" class="form-control" required value="<?php echo htmlspecialchars($weight); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Blood Type</label>
                        <input type="text" name="txtBloodType" class="form-control" required value="<?php echo htmlspecialchars($blood_type); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-4">
                        <label class="form-label">GSIS ID No.</label>
                        <input type="text" name="txtGsis" class="form-control" value="<?php echo htmlspecialchars($gsis_no); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Pag-IBIG ID No.</label>
                        <input type="text" name="txtPagibig" class="form-control" value="<?php echo htmlspecialchars($pagibig_no); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">PhilHealth ID No.</label>
                        <input type="text" name="txtPhilhealth" class="form-control" value="<?php echo htmlspecialchars($philhealth_no); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-4">
                        <label class="form-label">SSS No.</label>
                        <input type="text" name="txtSss" class="form-control" required value="<?php echo htmlspecialchars($sss_no); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">TIN No.</label>
                        <input type="text" name="txtTin" class="form-control" required value="<?php echo htmlspecialchars($tin_no); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Agency Employee ID</label>
                        <input type="text" name="txtAgency" class="form-control" value="<?php echo htmlspecialchars($agency_employee_no); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-6">
                        <label class="form-label">Citizenship</label>
                        <input type="text" name="txtCitizenship" class="form-control" value="<?php echo htmlspecialchars($citizenship); ?>">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Email Address</label>
                        <input type="email" name="txtEmail" class="form-control" value="<?php echo htmlspecialchars($email); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-6">
                        <label class="form-label">Residential Address</label>
                        <input type="text" name="txtResAddress" class="form-control" value="<?php echo htmlspecialchars($res_address); ?>">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Residential Zip Code</label>
                        <input type="text" name="txtResZip" class="form-control" value="<?php echo htmlspecialchars($res_zip); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-6">
                        <label class="form-label">Permanent Address</label>
                        <input type="text" name="txtPermAddress" class="form-control" value="<?php echo htmlspecialchars($perm_address); ?>">
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Permanent Zip Code</label>
                        <input type="text" name="txtPermZip" class="form-control" value="<?php echo htmlspecialchars($perm_zip); ?>">
                    </div>
                </div>

                <div class="row g-3 mt-3">
                    <div class="col-md-4">
                        <label class="form-label">Telephone No.</label>
                        <input type="text" name="txtTelNo" class="form-control" value="<?php echo htmlspecialchars($tel_no); ?>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Mobile No.</label>
                        <input type="text" name="txtMobileNo" class="form-control" value="<?php echo htmlspecialchars($mobile_no); ?>">
                    </div>
                    <div class="col-md-4 d-flex align-items-end justify-content-end">
                        <button type="button" class="btn btn-secondary me-2" onclick="clearForm()"><i class="fa fa-broom"></i> Clear Form</button>
                        <input type="submit" class="btn btn-primary" value="<?php echo ($pds_id > 0) ? 'UPDATE INFO' : 'SAVE INFO'; ?>">
                    </div>
                </div>
            </form>

            <hr class="my-4">


            <div class="table-responsive">
      <table class="table table-bordered table-hover mt-3">
          <thead>
              <tr>
                  <th>No</th>
                  <th>Full Name</th>
                  <th>Action</th>
              </tr>
          </thead>
          <tbody>
          <?php
          $result = $conn->query("SELECT * FROM pageone ORDER BY pds_id DESC");
          if ($result && $result->num_rows > 0) {
              $cnt = 1;
              while ($row = $result->fetch_assoc()) {
                  echo '<tr>';
                  echo '<td>' . $cnt++ . '</td>';
                  echo '<td>' . htmlspecialchars($row['pds_fname']) . ' ' . htmlspecialchars($row['pds_mname']) . ' ' . htmlspecialchars($row['pds_lname']) . '</td>';
                  echo '<td class="text-center"><a class="btn btn-info btn-sm" href="view.php?token=' . htmlspecialchars($row['pds_id']) . '"><i class="fa fa-eye"></i> View </a>
                  <a class="btn btn-warning btn-sm" href="main.php?token=' . htmlspecialchars($row['pds_id']) . '"><i class="fa fa-edit"></i> Edit </a>
                  <a class="btn btn-danger btn-sm" href="del_item.php?delete=' . htmlspecialchars($row['pds_id']) . '" onclick="return confirm(\'Are you sure you want to delete this record?\')">
            <i class="fa fa-trash"></i> Delete
        </a>
      </td>';

                  echo '</tr>';
              }
          } else {
              echo '<tr><td colspan="3" class="text-center">No Records Found</td></tr>';
          }
          ?>
          </tbody>
      </table>
  </div>

<script>

function clearForm() {
    var form = document.getElementById('pdsForm');
    if (!form) return;

    var inputs = form.querySelectorAll('input');
    inputs.forEach(function(inp) {
        if (inp.type === 'hidden') return;
        if (['button','submit','reset'].includes(inp.type)) return;
        inp.value = '';
    });

    var textareas = form.querySelectorAll('textarea');
    textareas.forEach(function(t){ t.value = ''; });
    var selects = form.querySelectorAll('select');
    selects.forEach(function(s){ s.selectedIndex = -1; });

    var pdsId = form.querySelector('input[name="pds_id"]');
    if (pdsId) pdsId.value = '0';

    var first = form.querySelector('input[name="txtFname"]');
    if (first) first.focus();
}
</script>
</body>
</html>
<?php $conn->close(); ?>
