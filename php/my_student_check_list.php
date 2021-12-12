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

$result= mysql_query("select * from p_teach_stu where p_no='$p_no'");
$result2=array();

while($result2 = mysql_fetch_array($result, MYSQL_BOTH))
{
	$stu_name = $result2[stu_name];
	$stu_no = $result2[stu_no];
	$stu_major = $result2[stu_major];



	$xmlcode .= "<check>\n";
	$xmlcode .= "<stu_name>$stu_name</stu_name>\n";
	$xmlcode .= "<stu_no>$stu_no</stu_no>\n";	
	$xmlcode .= "<stu_major>$stu_major</stu_major>\n";	
	$xmlcode .= "</check>\n";
}

echo $xmlcode;



$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/my_student/my_student_check_list_$p_no.xml";
 
file_put_contents($filename, $xmlcode); 




?>




