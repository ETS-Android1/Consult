<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> <!-- �좎럩裕꾢뜝占썹춯�묒삕占쏙옙DB�좎룞�셵tf-8_unicode_ci�β댙�숂춯�얠뒧獄�뼲�숋옙怨삵룖�좎럥梨뤄옙占썲뜝�숈삕�좎럡�들뇡占썲뜝�덉뒩占쏙옙-->
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup"); //DB�띠룊�쇿뜝�덈쐞占쏙옙�낅슣�섓옙占썲뜝�덈뼬�⊥띿삕�좎룞�숃キ占쏀맋�뺢퀗�т빳占썹춯�딅궚占쏙옙�좎럥理먲옙�숈삕占쎈∥裕��롪퍒�뷂옙議용Ь�좎룞�숋옙占쏙옙�쇿뜝�밸쇀占쏙옙�꾤뛾�됱삕�낅슣�섓옙�섎ご�좎룞�숋옙�살┣ �좎룞��
mysql_selectdb("zero"); //DB �좎럩伊싨틦占�mysql_query("set names UTF8"); //�좎럥��떋占썲뜝�덌옙�됵옙�좎럩裕꾢뜝占퐑tf8)�좎룞�숂춯�묒삕占쎌빢�숋옙�얄뵛 �좎럡�들뇡占썽뇦猿볦삕
mysql_query("set names UTF8"); 
mysql_selectdb("consult"); //DB �좎럩伊싨틦占�mysql_query("set names UTF8"); //�좎럥��떋占썲뜝�덌옙�됵옙�좎럩裕꾢뜝占퐑tf8)�좎룞�숂춯�묒삕占쎌빢�숋옙�얄뵛 �좎럡�들뇡占썽뇦猿볦삕
mysql_query("set names UTF8");

	$stu_name=$_REQUEST['stu_name'];
	$stu_no=$_REQUEST['stu_no'];
	$stu_major=$_REQUEST['stu_major'];
	$stu_phone=$_REQUEST['stu_phone'];
	$perpose=$_REQUEST['perpose'];
	$year=$_REQUEST['year'];
	$month=$_REQUEST['month'];
	$day=$_REQUEST['day'];
	$time=$_REQUEST['time'];
	$p_no=$_REQUEST['p_no'];
	 
$result=  mysql_result(mysql_query("select count(stu_phone) from reservation where year='$year' AND month='$month' AND day='$day' AND time='$time' AND stu_no='$stu_no'  AND stu_name='$stu_name' AND ready=2  ;"),0);
	
 
$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; //xml�좎럥�ｏ옙�륁삕�좎뜾鍮딉옙類ㅼ졑�좎룞�숋옙袁⑤�獄�옙

$xmlcode .= "<result>$result</result>\n";
$xmlcode .= "<stu_name>$stu_name</stu_name>\n";
$xmlcode .= "<stu_no>$stu_no</stu_no>\n";
$xmlcode .= "<stu_major>$stu_major</stu_major>\n";
$xmlcode .= "<stu_phone>$stu_phone</stu_phone>\n";
$xmlcode .= "<perpose>$perpose</perpose>\n";
$xmlcode .= "<year>$year</year>\n";
$xmlcode .= "<month>$month</month>\n";
$xmlcode .= "<day>$day</day>\n";
$xmlcode .= "<time>$time</time>\n";
 

$dir = "C:/APM_Setup/htdocs"; //insertresult.xml �좎럥�ｏ옙�륁삕�좎룞�쇿뜝�뚯궋�좎룞�숅뇦猿뗫윥餓ο옙

$filename = $dir."/consult/consult_customer/reservation_search_$stu_no.xml";
 
file_put_contents($filename, $xmlcode); //xmlcode�좎룞�쇿뜝�덈샍占쎌뮋�쇿뜝�봫l�좎럥�ｏ옙�놁뿉�좎뜾鍮딉옙類ㅼ졑
?>

