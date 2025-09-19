<?php
$fname = $mname = $lname = $dob = $pob = $sex = $civilstatus = $height = $weight = $bt = "";
$gsis = $pagibig = $philhealth = $sss = $tin = $agency = $citizenship = "";
$resaddress = $reszip = $permaddress = $permzip = $tel = $mobile = $email = "";

if (isset($_POST['btnSave'])) {
  $fname = $_POST['txtFname'];
  $mname = $_POST['txtMname'];
  $lname = $_POST['txtLname'];
  $dob   = $_POST['txtDOB'];
  $pob   = $_POST['txtPOB'];
  $sex   = $_POST['txtSex'];
  $civilstatus = $_POST['txtCivilStatus'];
  $height = $_POST['txtHeight'];
  $weight = $_POST['txtWeight'];
  $bt = $_POST['txtbt'];

  $gsis = $_POST['txtGSIS'];
  $pagibig = $_POST['txtPagibig'];
  $philhealth = $_POST['txtPhilhealth'];
  $sss = $_POST['txtSSS'];
  $tin = $_POST['txtTIN'];
  $agency = $_POST['txtAgency'];
  $citizenship = $_POST['txtCitizenship'];
  $resaddress = $_POST['txtResAddress'];
  $reszip = $_POST['txtResZip'];
  $permaddress = $_POST['txtPermAddress'];
  $permzip = $_POST['txtPermZip'];
  $tel = $_POST['txtTel'];
  $mobile = $_POST['txtMobile'];
  $email = $_POST['txtEmail'];
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>PHP Form Method</title>
</head>
<body>
  <center>
    <form name="frmInfo" method="post">
      <table border="1" width="50%" cellpadding="8" cellspacing="0">
        <tr>
          <td colspan="4" align="center"><strong>PERSONAL DATA SHEET</strong></td>
        </tr>
        <tr>
          <td colspan="4"><strong>Personal Information</strong></td>
        </tr>

        <tr>
          <td>First Name</td>
          <td>Middle Name</td>
          <td>Last Name</td>
          <td rowspan="2" align="center">
            <input type="submit" name="btnSave" value="SAVE INFORMATION">
          </td>
        </tr>
        <tr>
          <td><input type="text" name="txtFname" placeholder="Enter First Name" required></td>
          <td><input type="text" name="txtMname" placeholder="Enter Middle Name" required></td>
          <td><input type="text" name="txtLname" placeholder="Enter Last Name" required></td>
        </tr>

        <tr>
          <td>Date of Birth</td>
          <td colspan="3"><input type="date" name="txtDOB" required></td>
        </tr>

        <tr>
          <td>Place of Birth</td>
          <td colspan="3"><input type="text" name="txtPOB" placeholder="Enter Place of Birth" required></td>
        </tr>

        <tr>
          <td>Sex</td>
          <td colspan="3">
            <select name="txtSex" required>
              <option value="">-- Select --</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="G lang">G lang</option>
            </select>
          </td>
        </tr>

        <tr>
          <td>Civil Status</td>
          <td colspan="3">
            <select name="txtCivilStatus" required>
              <option value="">-- Select --</option>
              <option value="Single">Single</option>
              <option value="Married">Married</option>
              <option value="Not Married">Not Married</option>
              <option value="Divorced">Divorced</option>
              <option value="Separated">Separated</option>
              <option value="Widowed">Widowed</option>
            </select>
          </td>
        </tr>

        <tr>
          <td>Height</td>
          <td colspan="3"><input type="text" name="txtHeight" placeholder="Enter Height (cm)" required></td>
        </tr>

        <tr>
          <td>Weight</td>
          <td colspan="3"><input type="text" name="txtWeight" placeholder="Enter Weight (kg)" required></td>
        </tr>

        <tr>
          <td>Blood Type</td>
          <td colspan="3">
            <select name="txtbt" required>
              <option value="">-- Select --</option>
              <option value="A">A</option>
              <option value="B">B</option>
              <option value="AB">AB</option>
              <option value="O">O</option>
            </select>
          </td>
        </tr>

        <tr><td>GSIS ID No.</td><td colspan="3"><input type="text" name="txtGSIS"></td></tr>
        <tr><td>Pag-IBIG ID No.</td><td colspan="3"><input type="text" name="txtPagibig"></td></tr>
        <tr><td>PhilHealth No.</td><td colspan="3"><input type="text" name="txtPhilhealth"></td></tr>
        <tr><td>SSS No.</td><td colspan="3"><input type="text" name="txtSSS"></td></tr>
        <tr><td>TIN No.</td><td colspan="3"><input type="text" name="txtTIN"></td></tr>
        <tr><td>Agency Employee No.</td><td colspan="3"><input type="text" name="txtAgency"></td></tr>
        <tr><td>Citizenship</td><td colspan="3"><input type="text" name="txtCitizenship"></td></tr>
        <tr><td>Residential Address</td><td colspan="3"><input type="text" name="txtResAddress"></td></tr>
        <tr><td>ZIP Code</td><td colspan="3"><input type="text" name="txtResZip"></td></tr>
        <tr><td>Permanent Address</td><td colspan="3"><input type="text" name="txtPermAddress"></td></tr>
        <tr><td>ZIP Code</td><td colspan="3"><input type="text" name="txtPermZip"></td></tr>
        <tr><td>Telephone No.</td><td colspan="3"><input type="text" name="txtTel"></td></tr>
        <tr><td>Mobile No.</td><td colspan="3"><input type="text" name="txtMobile"></td></tr>
        <tr><td>Email Address</td><td colspan="3"><input type="email" name="txtEmail"></td></tr>
      </table>
    </form>

    <?php if (isset($_POST['btnSave'])): ?>
      <h2>Saved Information</h2>
      <table border="1" width="60%" cellpadding="6" cellspacing="0">
        <tr><td><b>First Name</b></td><td><?= $fname ?></td></tr>
        <tr><td><b>Middle Name</b></td><td><?= $mname ?></td></tr>
        <tr><td><b>Last Name</b></td><td><?= $lname ?></td></tr>
        <tr><td><b>Date of Birth</b></td><td><?= $dob ?></td></tr>
        <tr><td><b>Place of Birth</b></td><td><?= $pob ?></td></tr>
        <tr><td><b>Sex</b></td><td><?= $sex ?></td></tr>
        <tr><td><b>Civil Status</b></td><td><?= $civilstatus ?></td></tr>
        <tr><td><b>Height</b></td><td><?= $height ?></td></tr>
        <tr><td><b>Weight</b></td><td><?= $weight ?></td></tr>
        <tr><td><b>Blood Type</b></td><td><?= $bt ?></td></tr>
        <tr><td><b>GSIS ID</b></td><td><?= $gsis ?></td></tr>
        <tr><td><b>Pag-IBIG ID</b></td><td><?= $pagibig ?></td></tr>
        <tr><td><b>PhilHealth No.</b></td><td><?= $philhealth ?></td></tr>
        <tr><td><b>SSS No.</b></td><td><?= $sss ?></td></tr>
        <tr><td><b>TIN</b></td><td><?= $tin ?></td></tr>
        <tr><td><b>Agency Employee No.</b></td><td><?= $agency ?></td></tr>
        <tr><td><b>Citizenship</b></td><td><?= $citizenship ?></td></tr>
        <tr><td><b>Residential Address</b></td><td><?= $resaddress ?></td></tr>
        <tr><td><b>Residential ZIP</b></td><td><?= $reszip ?></td></tr>
        <tr><td><b>Permanent Address</b></td><td><?= $permaddress ?></td></tr>
        <tr><td><b>Permanent ZIP</b></td><td><?= $permzip ?></td></tr>
        <tr><td><b>Telephone</b></td><td><?= $tel ?></td></tr>
        <tr><td><b>Mobile</b></td><td><?= $mobile ?></td></tr>
        <tr><td><b>Email</b></td><td><?= $email ?></td></tr>
      </table>
    <?php endif; ?>
  </center>
</body>
</html>
