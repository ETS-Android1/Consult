<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$p_no=$_REQUEST['p_no'];
$p_name=$_REQUEST['p_name'];
$P_num=$_REQUEST['ID'];
$Password=$_REQUEST['Password'];


$cnt=count($result);




 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n";

$result= mysql_query("select * from reservation where p_no='$p_no' and yesno=0");
$result2=array();

while($result2 = mysql_fetch_array($result, MYSQL_BOTH))
{
	$year = $result2[year];
	$month = $result2[month];
	$day = $result2[day];
	$time = $result2[time];
	$stu_name = $result2[stu_name];
	$stu_no = $result2[stu_no];
	$problem = $result2[problem];
	$stu_major = $result2[stu_major];



	$xmlcode .= "<check>\n";
	$xmlcode .= "<year>$year</year>\n";
	$xmlcode .= "<month>$month</month>\n";	
	$xmlcode .= "<day>$day</day>\n";
	$xmlcode .= "<time>$time</time>\n";	
	$xmlcode .= "<stu_name>$stu_name</stu_name>\n";
	$xmlcode .= "<stu_no>$stu_no</stu_no>\n";	
	$xmlcode .= "<problem>$problem</problem>\n";
	$xmlcode .= "<stu_major>$stu_major</stu_major>\n";	
	$xmlcode .= "</check>\n";
}

echo $xmlcode;



$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/professor_reservation_check_list/check_list_$p_no.xml";
 
file_put_contents($filename, $xmlcode); 




?>




