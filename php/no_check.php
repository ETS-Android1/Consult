<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup"); 
mysql_selectdb("consult"); 
mysql_query("set names UTF8");


$p_no=$_REQUEST['p_no'];
$name=$_REQUEST['name'];
$t=$_REQUEST['t'];


	 
$result=  mysql_result(mysql_query("select count(stu_name) from reservation where yesno=2 and ready=2 and stu_name='$name' and p_no='$p_no' and t='$t';"),0);
	
 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n";
$xmlcode .= "<result>$result</result>\n";
 
$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/no_check.xml";
 
file_put_contents($filename, $xmlcode); 
?>

