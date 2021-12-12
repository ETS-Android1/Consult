<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$p_no=$_REQUEST['p_name'];
$year=$_REQUEST['year'];
$month=$_REQUEST['month'];
$day=$_REQUEST['day'];


$result9=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='09:00~10:00' and yesno=0;"),0);
$result10=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='10:00~11:00' and yesno=0 ;"),0);
$result11=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='11:00~12:00' and yesno=0  ;"),0);
$result12=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='12:00~13:00' and yesno=0  ;"),0);
$result13=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='13:00~14:00' and yesno=0  ;"),0);
$result14=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='14:00~15:00' and yesno=0  ;"),0);
$result15=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='15:00~16:00' and yesno=0  ;"),0);
$result16=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='16:00~17:00' and yesno=0  ;"),0);
$result17=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='17:00~18:00' and yesno=0  ;"),0);
$result18=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='18:00~19:00' and yesno=0  ;"),0);
$result19=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='19:00~20:00' and yesno=0  ;"),0);
$result20=  mysql_result(mysql_query("select count(p_no) from reservation where month='$month' and day='$day' and year='$year' and p_no='$p_no' and time='20:00~21:00' and yesno=0  ;"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result9>$result9</result9>\n";
$xmlcode .= "<result10>$result10</result10>\n";
$xmlcode .= "<result11>$result11</result11>\n";
$xmlcode .= "<result12>$result12</result12>\n";
$xmlcode .= "<result13>$result13</result13>\n";
$xmlcode .= "<result14>$result14</result14>\n";
$xmlcode .= "<result15>$result15</result15>\n";
$xmlcode .= "<result16>$result16</result16>\n";
$xmlcode .= "<result17>$result17</result17>\n";
$xmlcode .= "<result18>$result18</result18>\n";
$xmlcode .= "<result19>$result19</result19>\n";
$xmlcode .= "<result20>$result20</result20>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/time_pick_check.xml";

file_put_contents($filename, $xmlcode); 
?>

