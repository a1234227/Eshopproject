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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `pro_id` int NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(45) NOT NULL,
  `pro_desc` varchar(200) NOT NULL,
  `pro_price` decimal(10,3) NOT NULL,
  `pro_image` varchar(200) NOT NULL,
  `product_enum_id` int NOT NULL,
  `pro_stock` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`pro_id`),
  UNIQUE KEY `idproduct_UNIQUE` (`pro_id`),
  UNIQUE KEY `pro_name_UNIQUE` (`pro_name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'西瓜','西瓜香甜多汁，消暑解渴，被稱為「夏季瓜果之王」。水分含量佔西瓜整體約94%，不含脂肪和膽固醇，卻具備許多人體所需的營養素。傳統中醫認為西瓜味甘、性寒，助於解暑、止渴、開胃、利尿等。',450.000,'https://cloud-7cb1e.web.app/img/watermelon.jpg',1,30),(2,'李子','李子又名「嘉慶子」，對氣候的適應性強、對土壤要求也不嚴格，生長迅速產量高，經濟價值高。用來鮮食外也能做成罐頭、糖漬等加工食品。許多人會把李子加冰糖燉煮，用來潤喉開嗓，而東歐則會用李子釀成李子白蘭地。',200.000,'https://cloud-7cb1e.web.app/img/plums.jpg',1,20),(3,'芒果','「芒果」，中文稱呼來自於英文\"Mango\"的翻譯，漆樹科，原產於印度。早在明朝，李時珍便將芒果稱為「果中極品」，有止暈、行氣、消食等功效。另外，芒果富含大量的維生素C，也有助於抗氧化及美膚。',350.000,'https://cloud-7cb1e.web.app/img/mango.jpg',1,20),(4,'汽水','軟性飲料（英語：Soft drink）又名軟飲料、清涼飲料，是指酒精含量（體積比）低於0.5%的天然或人工調配的飲料。在歐美地區原本的定義是指由濃縮原料製成的碳酸或非碳酸飲料，但現在已是極低酒精之飲料的通稱。',20.000,'https://cloud-7cb1e.web.app/img/soda.jpg',2,0),(5,'舒跑','舒跑（英語：Super Supau）是1981年維他露食品推出的運動飲料，味道近似葡萄柚汁，與維他露P並列維他露食品兩大代表產品。其成分含電解質，能於運動後補充隨汗水流失的水份和電解質。',20.000,'https://cloud-7cb1e.web.app/img/super.jpeg',2,100),(6,'可樂果','「可樂果-豌豆酥」的誕生是個偶然的發現，卻帶給許多人童年的歡樂回憶。「可樂果」的前身是以蠶豆做為原料製成的, 當時叫做「可樂果-蠶豆酥」，由聯華食品董事長李開源的父親(李國衡先生)所發明。',15.000,'https://cloud-7cb1e.web.app/img/pea_crackers.webp',3,20),(7,'脆迪酥','可口小瓜呆脆笛酥',30.000,'https://cloud-7cb1e.web.app/img/roll.jpeg',3,20),(8,'衛生紙','舒潔拉拉柔軟秀',20.000,'https://cloud-7cb1e.web.app/img/tpaper.jpg',4,200),(9,'牙膏','灰人牙膏',40.000,'https://cloud-7cb1e.web.app/img/toothpaste.jpg',4,30),(10,'洗衣精','室內晾衣、密集晾衣、潮濕氣候晾衣再也不怕異味，享受衣物被陽光曬過般的自然安心。 兩匙靈熱銷系列現正優惠中',150.000,'https://cloud-7cb1e.web.app/img/Detergent.jpg',5,10),(11,'牛肉乾','特選上等豬後腿肉，純手工烘烤，不油也不膩，不含防腐劑，保有纖維口感和咬勁，是店內的超人氣商品。',460.000,'https://cloud-7cb1e.web.app/img/beef.webp',6,30),(12,'滋養霜','一般膚質適用',200.000,'https://cloud-7cb1e.web.app/img/cream.jpg',7,10);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
