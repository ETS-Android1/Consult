<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$name=$_REQUEST['ID'];
$Password=$_REQUEST['Password'];





$result=  mysql_result(mysql_query("select count(stu_no) from student where stu_no='$name' and stu_password='$Password';"),0);
$result2=  mysql_result(mysql_query("select stu_name from student where stu_no='$name' and stu_password='$Password';"),0);
$result3=  mysql_result(mysql_query("select stu_major from student where stu_no='$name' and stu_password='$Password';"),0);
$result4=  mysql_result(mysql_query("select stu_phone from student where stu_no='$name' and stu_password='$Password';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>\n";
$xmlcode .= "<result2>$result2</result2>\n";
$xmlcode .= "<result3>$result3</result3>\n";
$xmlcode .= "<result4>$result4</result4>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/student_login.xml";

file_put_contents($filename, $xmlcode); 
?>

