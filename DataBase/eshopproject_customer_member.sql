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
-- Table structure for table `customer_member`
--

DROP TABLE IF EXISTS `customer_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_member` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(45) NOT NULL,
  `cust_account` varchar(45) NOT NULL,
  `cust_password` varchar(45) NOT NULL,
  `cust_address` varchar(45) NOT NULL,
  PRIMARY KEY (`cust_id`),
  UNIQUE KEY `cust_id_UNIQUE` (`cust_id`),
  UNIQUE KEY `cust_account_UNIQUE` (`cust_account`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_member`
--

LOCK TABLES `customer_member` WRITE;
/*!40000 ALTER TABLE `customer_member` DISABLE KEYS */;
INSERT INTO `customer_member` VALUES (1,'sandy','sandy888','123456','106台北市大安區信義路三段100號'),(2,'john','john999','123456','106台北市大安區復興南路二段52號'),(3,'leon','leon0314','123456','106台北市大安區敦化南路一段295巷16號'),(4,'tony','tonytw','123456','242新北市新莊區新樹路176號'),(5,'zed','ningzed','123456','324桃園市平鎮區復旦路四段116巷51號'),(6,'momo','momoling66','123456','303新竹縣湖口鄉光復路11號'),(7,'test','test','test','303新竹縣湖口鄉光復路12號');
/*!40000 ALTER TABLE `customer_member` ENABLE KEYS */;
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
