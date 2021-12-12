<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");

$p_no=$_REQUEST['name'];
$major=$_REQUEST['Cname'];
$name=$_REQUEST['ID'];
$Password=$_REQUEST['Password'];
$phone=$_REQUEST['phone'];



$result=  mysql_result(mysql_query("select count(stu_no) from student where stu_no='$p_no' and stu_password='$Password' and stu_name='$name' and stu_major='$major' and stu_phone='$phone' ;"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/student_insert_check.xml";

file_put_contents($filename, $xmlcode); 
?>

