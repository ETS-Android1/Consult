<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");

$p_no=$_REQUEST['p_no'];
$year=$_REQUEST['year'];
$month=$_REQUEST['month'];
$day=$_REQUEST['day'];
$time=$_REQUEST['time'];
$stu_no=$_REQUEST['stu_no'];




$result=  mysql_result(mysql_query("select contents from after_conversation where p_no='$p_no' and year='$year' and month='$month' and day='$day' and time='$time' and stu_no='$stu_no';"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/after_consult/check_content_$p_no.xml";

file_put_contents($filename, $xmlcode); 
?>

