day<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$stu_no=$_REQUEST['stu_no'];
$stu_password=$_REQUEST['stu_password'];
$year=$_REQUEST['year'];
$month=$_REQUEST['month'];
$day=$_REQUEST['day'];
$time=$_REQUEST['time'];






$problem=  mysql_result(mysql_query("select problem from reservation where stu_no='$stu_no' and year='$year' and month='$month' and day='$day' and time='$time';"),0);






 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<problem>$problem</problem>";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/stu_problem/reservation_check_student_$stu_no.xml";

file_put_contents($filename, $xmlcode); 
?>

