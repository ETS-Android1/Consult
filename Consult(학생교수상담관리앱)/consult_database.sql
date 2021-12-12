-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: consult
-- ------------------------------------------------------
-- Server version	5.1.41-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `after_conversation`
--

DROP TABLE IF EXISTS `after_conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `after_conversation` (
  `year` varchar(5) NOT NULL,
  `month` varchar(5) NOT NULL,
  `day` varchar(5) NOT NULL,
  `time` varchar(30) NOT NULL,
  `p_no` varchar(20) NOT NULL,
  `stu_name` varchar(30) NOT NULL,
  `stu_no` varchar(20) NOT NULL,
  `stu_major` varchar(30) NOT NULL,
  `contents` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `after_conversation`
--

LOCK TABLES `after_conversation` WRITE;
/*!40000 ALTER TABLE `after_conversation` DISABLE KEYS */;
INSERT INTO `after_conversation` VALUES ('2016','11','18','13:00~14:00','123','Eunseo_OH','2013110996','business','11233111234444888'),('2016','11','20','13:00~14:00','123','jojun','123','math','gg'),('2016','11','17','12:00~13:00','123','jojun','123','math','good'),('2016','11','24','13:00~14:00','123','Eunseo_OH','2013110996','business','good'),('2016','11','30','09:00~10:00','0216','Serin Oh','20120526','Business','good\n'),('2016','11','30','11:00~12:00','0216','Serin Oh','20120526','Business','excellent '),('2016','12','21','10:00~11:00','0216','Serin Oh','20120526','Business','good'),('2016','12','21','13:00~14:00','0216','Serin Oh','20120526','Business','good'),('2016','12','21','15:00~16:00','0216','Serin Oh','20120526','Business','good');
/*!40000 ALTER TABLE `after_conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `p_teach_stu`
--

DROP TABLE IF EXISTS `p_teach_stu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_teach_stu` (
  `p_no` varchar(20) NOT NULL,
  `stu_name` varchar(30) NOT NULL,
  `stu_no` varchar(20) NOT NULL,
  `stu_major` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `p_teach_stu`
--

LOCK TABLES `p_teach_stu` WRITE;
/*!40000 ALTER TABLE `p_teach_stu` DISABLE KEYS */;
INSERT INTO `p_teach_stu` VALUES ('123','Eunseo_OH','2013110996','business'),('123','Jeongeun Park','20121176','mathmatics'),('0216','Serin Oh','20120526','business'),('0216','Eunseo_OH','2013110996','business '),('0216','Eunseo_OH','2013110996','business');
/*!40000 ALTER TABLE `p_teach_stu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `p_no` varchar(20) NOT NULL,
  `p_name` varchar(30) NOT NULL,
  `p_password` varchar(30) NOT NULL,
  `p_major` varchar(30) NOT NULL,
  `p_phone` varchar(30) NOT NULL,
  PRIMARY KEY (`p_no`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='교수정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES ('0216','박춘식','0216','정보보호학과','01090908787'),('1','허종호','1','경영학과','01019090909'),('123','이동일','123','수학과','010-1234-5678'),('2','John Francis Kinsler','2','영어영문학과','01090909090'),('9','a','a','math','a');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `year` varchar(5) NOT NULL,
  `month` varchar(5) NOT NULL,
  `day` varchar(5) NOT NULL,
  `time` varchar(30) NOT NULL,
  `p_no` varchar(20) NOT NULL,
  `stu_no` varchar(20) NOT NULL,
  `stu_name` varchar(30) NOT NULL,
  `stu_major` varchar(30) NOT NULL,
  `stu_phone` varchar(30) NOT NULL,
  `problem` varchar(100) NOT NULL,
  `ready` int(1) NOT NULL,
  `yesno` int(1) NOT NULL,
  `read_ok` varchar(10) NOT NULL,
  `t` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=euckr;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('2016','11','17','12:00~13:00','123','123','jojun','math','01021313213','hjk',2,0,'yes','2016-11-16 23:33:09'),('2016','11','18','13:00~14:00','123','2013110996','Eunseo_OH','business','01000000000','for learning',2,2,'yes','2016-11-17 20:46:39'),('2016','11','18','15:00~16:00','123','2013110996','Eunseo_OH','business','01000000000','For studying',2,2,'yes','2016-11-17 21:56:37'),('2016','11','25','13:00~14:00','123','123','jojun','math','01021313213','ㄱㅅㅂㄴ',2,2,'yes','2016-11-21 23:47:00'),('2016','11','20','13:00~14:00','123','123','jojun','math','01021313213','근ㅅ',2,0,'yes','2016-11-21 23:47:46'),('2016','11','20','19:00~20:00','123','123','jojun','math','01021313213','ㅡㄷㄴㄱ',2,2,'yes','2016-11-21 23:48:07'),('2016','11','24','13:00~14:00','123','2013110996','Eunseo_OH','business','01000000000','learning',2,0,'yes','2016-11-23 08:26:28'),('2016','11','24','11:00~12:00','0216','2013110996','Eunseo_OH','business','01000000000','studying',2,2,'yes','2016-11-23 22:18:30'),('2016','11','24','16:00~17:00','1','2013110996','Eunseo_OH','business','01000000000','score',2,0,'yes','2016-11-23 22:31:02'),('2016','11','24','13:00~14:00','123','123','jojun','math','01021313213','ㅁ',2,2,'yes','2016-11-24 19:03:49'),('2016','11','24','15:00~16:00','123','2013110996','Eunseo_OH','business','01000000000','studying',2,0,'yes','2016-11-25 19:30:09'),('2016','11','28','10:00~11:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-11-25 19:32:39'),('2016','11','30','12:00~13:00','123','2013110996','Eunseo_OH','business','01000000000','learning',1,1,'yes','2016-11-25 19:39:39'),('2016','11','24','14:00~15:00','0216','2013110996','Eunseo_OH','business','01000000000','studying',2,2,'yes','2016-11-25 20:53:50'),('2016','11','28','09:00~10:00','123','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-11-25 21:53:57'),('2016','11','28','10:00~11:00','123','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-11-25 21:55:42'),('2016','11','28','11:00~12:00','123','20120526','Serin Oh','Business','01033337777','studying',1,1,'yes','2016-11-27 11:25:45'),('2016','11','30','09:00~10:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-11-29 17:11:44'),('2016','11','30','09:00~10:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-11-29 17:12:59'),('2016','11','30','11:00~12:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-11-29 18:13:40'),('2016','12','2','09:00~10:00','9','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-01 08:37:53'),('2016','12','20','09:00~10:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 21:52:45'),('2016','12','20','10:00~11:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-12-19 22:00:00'),('2016','12','20','10:00~11:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:02:54'),('2016','12','20','11:00~12:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:04:57'),('2016','12','20','12:00~13:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-12-19 22:07:03'),('2016','12','20','13:00~14:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-12-19 22:11:53'),('2016','12','21','09:00~10:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:13:19'),('2016','12','21','10:00~11:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:14:39'),('2016','12','21','11:00~12:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:18:42'),('2016','12','21','11:00~12:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,2,'yes','2016-12-19 22:20:07'),('2016','12','21','13:00~14:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:23:41'),('2016','12','21','14:00~15:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:39:15'),('2016','12','21','15:00~16:00','0216','20120526','Serin Oh','Business','01033337777','studying',2,0,'yes','2016-12-19 22:50:20');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `stu_no` varchar(20) NOT NULL,
  `stu_name` varchar(30) NOT NULL,
  `stu_password` varchar(30) NOT NULL,
  `stu_major` varchar(30) NOT NULL,
  `stu_phone` varchar(30) NOT NULL,
  PRIMARY KEY (`stu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr COMMENT='학생정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('123','jojun','123','math','01021313213'),('20120526','Serin Oh','1234','Business','01033337777'),('20121176','Jeongeun Park','1234','mathmatics','01011112222'),('2013110996','Eunseo_OH','1234','business','01000000000'),('2013111111','oh','1111','math','010456789');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-13 16:15:34
