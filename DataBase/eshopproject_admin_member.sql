-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: eshopproject
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_member`
--

DROP TABLE IF EXISTS `admin_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_member` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(45) NOT NULL,
  `admin_account` varchar(45) NOT NULL,
  `admin_password` varchar(45) NOT NULL,
  `admin_active` tinyint NOT NULL DEFAULT '0',
  `admin_phone` varchar(45) NOT NULL,
  `admin_restrict` tinyint NOT NULL DEFAULT '0',
  `admin_restricttime` timestamp(6) NULL DEFAULT NULL,
  `admin_wronglogintime` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_account_UNIQUE` (`admin_account`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_member`
--

LOCK TABLES `admin_member` WRITE;
/*!40000 ALTER TABLE `admin_member` DISABLE KEYS */;
INSERT INTO `admin_member` VALUES (1,'leo','leo0805tw','123456',1,'0922458961',0,NULL,0),(2,'tom','tomacc01','123456',0,'0928542689',0,NULL,0),(3,'sam','sam520','123456',0,'0965415777',0,NULL,0),(4,'des','desgamifa','123456',1,'0922915422',0,NULL,0),(5,'durrr','xpoker661','123456',0,'0911438925',0,NULL,0),(6,'momo','momo940713','123456',0,'0912321582',0,NULL,0),(7,'admin','admin','123456',1,'0922242312',0,NULL,0),(8,'kiki','kiki','123456',0,'06156145',0,NULL,0);
/*!40000 ALTER TABLE `admin_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-12 17:19:58
