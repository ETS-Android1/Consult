<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");

$stu_no=$_REQUEST['stu_no'];
$stu_name=$_REQUEST['stu_name'];
$stu_major=$_REQUEST['stu_major'];
$Password=$_REQUEST['Password'];



$result=  mysql_result(mysql_query("select count(stu_no) from student where stu_no='$stu_no' and stu_name='$stu_name';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/insert_student_check.xml";

file_put_contents($filename, $xmlcode); 
?>

