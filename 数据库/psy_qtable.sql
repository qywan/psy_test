-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: psy
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `qtable`
--

DROP TABLE IF EXISTS `qtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `count` int(3) DEFAULT NULL COMMENT '题目数量',
  `status` int(1) DEFAULT NULL COMMENT '题目完成状态(0:未开始，1：完成部分，2：全部完成)',
  `countdown` time DEFAULT NULL COMMENT '倒计时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qtable`
--

LOCK TABLES `qtable` WRITE;
/*!40000 ALTER TABLE `qtable` DISABLE KEYS */;
INSERT INTO `qtable` VALUES (11111,'貌美如花回眸倾城','C:\\Users\\lyz\\Desktop\\test.obj','2017-09-11',2,0,'00:12:00'),(11112,'test2','C:\\Users\\lyz\\Desktop\\test.obj','2017-09-11',2,1,'00:12:00'),(11113,'test3','C:\\Users\\lyz\\Desktop\\test.obj','2017-09-11',2,1,'00:12:00'),(11114,'test4','C:\\Users\\lyz\\Desktop\\test.obj','2017-09-11',2,2,'00:12:00');
/*!40000 ALTER TABLE `qtable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-19 22:19:47
