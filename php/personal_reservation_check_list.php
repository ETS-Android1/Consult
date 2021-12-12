<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$stu_phone=$_REQUEST['stu_phone'];
$stu_no=$_REQUEST['stu_no'];
$P_num=$_REQUEST['ID'];
$Password=$_REQUEST['Password'];


$cnt=count($result);




 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n";

$result= mysql_query("select * from reservation where stu_no='$stu_no' and stu_phone='$stu_phone'");
$result2=array();

while($result2 = mysql_fetch_array($result, MYSQL_BOTH))
{
	$year = $result2[year];
	$month = $result2[month];
	$day = $result2[day];
	$time = $result2[time];
	$p_no = $result2[p_no];
	$yesno = $result2[yesno];

	$xmlcode .= "<reservation>\n";
	$xmlcode .= "<year>$year</year>\n";
	$xmlcode .= "<month>$month</month>\n";	
	$xmlcode .= "<day>$day</day>\n";
	$xmlcode .= "<time>$time</time>\n";	
	$xmlcode .= "<p_no>$p_no</p_no>\n";
	$xmlcode .= "<yesno>$yesno</yesno>\n";	
	$xmlcode .= "</reservation>\n";
}

echo $xmlcode;



$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/personal_reservation_check_list/check_list_$stu_phone.xml";
 
file_put_contents($filename, $xmlcode); 




?>




