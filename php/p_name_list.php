<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");





$cnt=count($result);




 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n";

$result= mysql_query("select * from professor");
$result2=array();

while($result2 = mysql_fetch_array($result, MYSQL_BOTH))
{
	$p_name = $result2[p_name];
	$p_major = $result2[p_major];


	$xmlcode .= "<professor>\n";
	$xmlcode .= "<p_name>$p_name</p_name>\n";
	$xmlcode .= "<p_major>$p_major</p_major>\n";	
	$xmlcode .= "</professor>\n";
}

echo $xmlcode;



$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/p_name_list.xml";
 
file_put_contents($filename, $xmlcode); 




?>




