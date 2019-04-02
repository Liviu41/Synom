-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: vault
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `resources_brightfax`
--

DROP TABLE IF EXISTS `resources_brightfax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources_brightfax` (
  `idresources` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(45) DEFAULT NULL,
  `os_type` varchar(45) DEFAULT NULL,
  `cpu_usage` varchar(45) DEFAULT NULL,
  `ram_usage` varchar(45) DEFAULT NULL,
  `ram_total` varchar(45) DEFAULT NULL,
  `storage_total` varchar(45) DEFAULT NULL,
  `storage_free` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `processes` text CHARACTER SET ascii,
  PRIMARY KEY (`idresources`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources_brightfax`
--

LOCK TABLES `resources_brightfax` WRITE;
/*!40000 ALTER TABLE `resources_brightfax` DISABLE KEYS */;
INSERT INTO `resources_brightfax` VALUES (1,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline'),(2,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline');
/*!40000 ALTER TABLE `resources_brightfax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources_darkfax`
--

DROP TABLE IF EXISTS `resources_darkfax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources_darkfax` (
  `idresources_darkfax` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(45) DEFAULT NULL,
  `os_type` varchar(45) DEFAULT NULL,
  `cpu_usage` varchar(45) DEFAULT NULL,
  `ram_usage` varchar(45) DEFAULT NULL,
  `ram_total` varchar(45) DEFAULT NULL,
  `storage_total` varchar(45) DEFAULT NULL,
  `storage_free` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `processes` text,
  PRIMARY KEY (`idresources_darkfax`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources_darkfax`
--

LOCK TABLES `resources_darkfax` WRITE;
/*!40000 ALTER TABLE `resources_darkfax` DISABLE KEYS */;
INSERT INTO `resources_darkfax` VALUES (1,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline'),(2,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline');
/*!40000 ALTER TABLE `resources_darkfax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources_lightfax`
--

DROP TABLE IF EXISTS `resources_lightfax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources_lightfax` (
  `idresources` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(45) DEFAULT NULL,
  `os_type` varchar(45) DEFAULT NULL,
  `cpu_usage` varchar(45) DEFAULT NULL,
  `ram_usage` varchar(45) DEFAULT NULL,
  `ram_total` varchar(45) DEFAULT NULL,
  `storage_total` varchar(45) DEFAULT NULL,
  `storage_free` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `processes` text,
  PRIMARY KEY (`idresources`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources_lightfax`
--

LOCK TABLES `resources_lightfax` WRITE;
/*!40000 ALTER TABLE `resources_lightfax` DISABLE KEYS */;
INSERT INTO `resources_lightfax` VALUES (1,'Offline','Offine','Offline','Offline','Offline','Offline','Offline','Offline','Offline'),(2,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline');
/*!40000 ALTER TABLE `resources_lightfax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources_shadowfax`
--

DROP TABLE IF EXISTS `resources_shadowfax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources_shadowfax` (
  `idresources` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` varchar(45) DEFAULT NULL,
  `os_type` varchar(45) DEFAULT NULL,
  `cpu_usage` varchar(45) DEFAULT NULL,
  `ram_usage` varchar(45) DEFAULT NULL,
  `ram_total` varchar(45) DEFAULT NULL,
  `storage_total` varchar(45) DEFAULT NULL,
  `storage_free` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `processes` text,
  PRIMARY KEY (`idresources`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources_shadowfax`
--

LOCK TABLES `resources_shadowfax` WRITE;
/*!40000 ALTER TABLE `resources_shadowfax` DISABLE KEYS */;
INSERT INTO `resources_shadowfax` VALUES (1,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline'),(2,'Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline','Offline'),(4,'2019-04-02T10:00:59.465','Linux','7.023259703278639','5722.7890625','7868.4765625','937871.015625','736214.05078125','127.0.1.1',NULL),(5,'2019-04-02T10:01:01.282','Linux','20.27785401904259','5717.6640625','7868.4765625','937871.015625','736214.05078125','127.0.1.1',NULL),(6,'2019-04-02T10:01:02.706','Linux','11.698687409144485','5717.89453125','7868.4765625','937871.015625','736214.05078125','127.0.1.1',NULL),(7,'2019-04-02T10:01:04.183','Linux','16.37103161360334','5689.2421875','7868.4765625','937871.015625','736214.05078125','127.0.1.1',NULL),(8,'2019-04-02T10:01:05.582','Linux','23.40261503815971','5668.28125','7868.4765625','937871.015625','736214.01953125','127.0.1.1',NULL),(9,'2019-04-02T10:01:06.974','Linux','9.355147529132935','5688.1796875','7868.4765625','937871.015625','736214.01953125','127.0.1.1',NULL),(10,'2019-04-02T10:01:08.378','Linux','16.373181596790886','5687.57421875','7868.4765625','937871.015625','736214.01953125','127.0.1.1',NULL),(11,'2019-04-02T10:01:09.823','Linux','11.691575490304924','5707.5078125','7868.4765625','937871.015625','736214.01953125','127.0.1.1',NULL),(12,'2019-04-02T10:01:11.225','Linux','7.797568120030748','5707.6796875','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(13,'2019-04-02T10:01:12.670','Linux','19.483324674424292','5697.73828125','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(14,'2019-04-02T10:01:14.050','Linux','11.70288202568986','5696.55078125','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(15,'2019-04-02T10:01:15.463','Linux','18.727825844528887','5686.12109375','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(16,'2019-04-02T10:01:16.892','Linux','18.71243227771939','5661.5625','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(17,'2019-04-02T10:01:18.292','Linux','18.71954540557399','5659.2421875','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(18,'2019-04-02T10:01:19.675','Linux','15.604952366460283','5658.7734375','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(19,'2019-04-02T10:01:21.093','Linux','22.640894096590163','5678.4375','7868.4765625','937871.015625','736214.01171875','127.0.1.1',NULL),(20,'2019-04-02T12:05:15.163','Linux','10.918335761753179','5288.4140625','7868.4765625','937871.015625','737011.5703125','127.0.1.1',NULL),(21,'2019-04-02T12:05:16.905','Linux','13.24756116217419','5281.33984375','7868.4765625','937871.015625','737011.5703125','127.0.1.1',NULL),(22,'2019-04-02T12:05:18.304','Linux','17.16093920996571','5279.921875','7868.4765625','937871.015625','737011.5703125','127.0.1.1',NULL),(23,'2019-04-02T12:05:19.734','Linux','60.120081758813896','5236.6484375','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(24,'2019-04-02T12:05:21.133','Linux','97.58982731384809','5112.7421875','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(25,'2019-04-02T12:05:22.546','Linux','98.33766852083329','5135.3203125','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(26,'2019-04-02T12:05:23.989','Linux','42.938479713629384','5224.1640625','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(27,'2019-04-02T12:05:25.367','Linux','39.03563226225929','5224.0703125','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(28,'2019-04-02T12:05:26.747','Linux','39.03577635148338','5239.8671875','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(29,'2019-04-02T12:05:28.135','Linux','35.13318879445775','5239.609375','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(30,'2019-04-02T12:05:29.524','Linux','42.94038586230736','5231.15625','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(31,'2019-04-02T12:05:30.891','Linux','35.135852100662035','5238.24609375','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(32,'2019-04-02T12:05:32.270','Linux','41.35629844112005','5238.24609375','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(33,'2019-04-02T12:05:33.692','Linux','28.107628782721562','5237.48828125','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(34,'2019-04-02T12:05:35.083','Linux','29.66809187599702','5235.67578125','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(35,'2019-04-02T12:05:36.493','Linux','56.21179846176538','5148.3671875','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(36,'2019-04-02T12:05:37.875','Linux','60.885971441294586','5044.62109375','7868.4765625','937871.015625','737011.5546875','127.0.1.1',NULL),(37,'2019-04-02T12:05:39.261','Linux','56.212598596195285','5212.69140625','7868.4765625','937871.015625','737011.55078125','127.0.1.1',NULL),(38,'2019-04-02T12:05:40.683','Linux','31.22796720532568','5234.77734375','7868.4765625','937871.015625','737011.55078125','127.0.1.1',NULL),(39,'2019-04-02T12:05:42.049','Linux','30.4473686208301','5233.65625','7868.4765625','937871.015625','737011.55078125','127.0.1.1',NULL),(40,'2019-04-02T12:05:43.434','Linux','28.05839113309947','5270.76171875','7868.4765625','937871.015625','737011.55859375','127.0.1.1',NULL),(41,'2019-04-02T12:05:44.839','Linux','14.812621414964777','5268.10546875','7868.4765625','937871.015625','737011.55859375','127.0.1.1',NULL);
/*!40000 ALTER TABLE `resources_shadowfax` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-02 12:41:15
