<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");





$cnt=count($result);




 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n";

$result= mysql_query("select * from student");
$result2=array();

while($result2 = mysql_fetch_array($result, MYSQL_BOTH))
{
	$stu_no = $result2[stu_no];
	$stu_name = $result2[stu_name];
	$stu_password = $result2[stu_password];
	$stu_major = $result2[stu_major];
	$stu_phone = $result2[stu_phone];
	$xmlcode .= "<student>\n";
	$xmlcode .= "<stu_no>$stu_no</stu_no>\n";	
	$xmlcode .= "<stu_name>$stu_name</stu_name>\n";
	$xmlcode .= "<stu_password>$stu_password</stu_password>\n";
	$xmlcode .= "<stu_major>$stu_major</stu_major>\n";
	$xmlcode .= "<stu_phone>$stu_phone</stu_phone>\n";
	$xmlcode .= "</student>\n";
}

echo $xmlcode;



$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/test.xml";
 
file_put_contents($filename, $xmlcode); 




?>




