day<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$p_no=$_REQUEST['p_no'];
$t=$_REQUEST['t'];






$day=  mysql_result(mysql_query("select day from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$yesno=  mysql_result(mysql_query("select yesno from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$major=  mysql_result(mysql_query("select stu_major from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$month=  mysql_result(mysql_query("select month from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$stu_name=  mysql_result(mysql_query("select stu_name from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$problem=  mysql_result(mysql_query("select problem from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$stu_no=  mysql_result(mysql_query("select stu_no from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$time=  mysql_result(mysql_query("select time from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$year=  mysql_result(mysql_query("select year from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);
$stu_phone=  mysql_result(mysql_query("select stu_phone from reservation where p_no='$p_no' and ready=1 and t='$t';"),0);






 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<day>$day</day>\n<yesno>$yesno</yesno>\n<major>$major</major>\n<month>$month</month>\n<stu_name>$stu_name</stu_name>\n<problem>$problem</problem>\n<stu_no>$stu_no</stu_no>\n<stu_phone>$stu_phone</stu_phone>\n<time>$time</time>\n<year>$year</year>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/reservation_check_first.xml";

file_put_contents($filename, $xmlcode); 
?>

