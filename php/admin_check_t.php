<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("consult"); 
mysql_query("set names UTF8");

$p_no=$_REQUEST['p_no'];




$result=  mysql_result(mysql_query("select min(t) from reservation where p_no='$p_no' and ready=1;"),0);

 

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>\n";


$dir = "C:/APM_Setup/htdocs"; 
$filename = $dir."/consult/admin_check_t.xml";

file_put_contents($filename, $xmlcode); 
?>

