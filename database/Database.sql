-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: 2023it6020_btl
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_username` varchar(50) NOT NULL,
  `teacher_password` varchar(255) NOT NULL,
  `teacher_fullname` varchar(100) NOT NULL,
  `teacher_email` varchar(100) NOT NULL,
  `teacher_phone` varchar(20) NOT NULL,
  `teacher_address` varchar(255) NOT NULL,
  `teacher_gender` enum('Male','Female','Other') NOT NULL,
  `teacher_age` int NOT NULL,
  `teacher_major` varchar(100) NOT NULL,
  `teacher_birthday` date NOT NULL,
  `teacher_degree` varchar(50) NOT NULL,
  `teacher_class` varchar(50) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'jdoe','482c811da5d5b4bc6d497ffa98491e38','John Doe','jdoe@example.com','1234567890','123 Main St','Male',45,'Computer Science','1979-04-12','PhD','CS101'),(2,'asmith','bb77d0d3b3f239fa5db73bdf27b8d29a','Alice Smith','asmith@example.com','0987654321','456 Oak St','Female',38,'Mathematics','1986-07-19','PhD','MATH201'),(3,'bwong','34819d7beeabb9260a5c854bc85b3e44','Bob Wong','bwong@example.com','1122334455','789 Pine St','Male',50,'Physics','1974-03-22','PhD','PHY301'),(4,'chung','7d347cf0ee68174a3588f6cba31b8a67','Cathy Hung','chung@example.com','9988776655','101 Maple St','Female',42,'Chemistry','1982-09-30','PhD','CHEM401'),(5,'dlee','0192023a7bbd73250516f069df18b500','David Lee','dlee@example.com','5566778899','202 Birch St','Male',47,'Biology','1977-12-15','PhD','BIO501'),(8,'hung','e10adc3949ba59abbe56e057f20f883e','Kieu Hung Pham','hung@example.com','1234567890','1 Hacker Way','Male',20,'Information Technology','2003-12-09','BA','IT6020'),(9,'mjohnson','b4af804009cb036a4ccdc33431ef9ac9','Michael Johnson','mjohnson@example.com','1112223333','234 Elm St','Male',40,'History','1984-05-14','PhD','HIS101'),(10,'lwilliams','bdb85fcec4296d6e1e1e6528f17fd8f4','Laura Williams','lwilliams@example.com','4445556666','567 Cedar St','Female',36,'English','1988-08-23','PhD','ENG202'),(11,'ktaylor','8cd8a233715286c5a48a0e4d33ce5081','Kevin Taylor','ktaylor@example.com','7778889999','890 Spruce St','Male',39,'Philosophy','1985-11-05','PhD','PHIL303'),(12,'ajones','13de5f51d79a9505db157b4e8c28f517','Amy Jones','ajones@example.com','1010101010','123 Maple St','Female',42,'Sociology','1982-02-19','PhD','SOC404'),(13,'grobinson','f4df4fe8e5ebe68f82c299a0b4cf4f70','George Robinson','grobinson@example.com','2020202020','456 Birch St','Male',50,'Anthropology','1974-07-16','PhD','ANTH505'),(14,'rclark','8117d1f5cb79fdab5ee9a293578245bd','Rachel Clark','rclark@example.com','3030303030','789 Ash St','Female',35,'Political Science','1989-04-27','PhD','PSCI606'),(15,'pharris','e874e4120261efedee12e23f60779bc0','Paul Harris','pharris@example.com','4040404040','321 Pine St','Male',48,'Economics','1976-09-12','PhD','ECON707'),(16,'jsanchez','0d28e4080dc8f64fc9603639bb7aa1b9','Julia Sanchez','jsanchez@example.com','5050505050','654 Oak St','Female',44,'Psychology','1980-06-01','PhD','PSY808'),(17,'tlewis','826f4b5c7b0dae454944a1ecdb08b598','Thomas Lewis','tlewis@example.com','6060606060','987 Willow St','Male',37,'Art History','1987-03-09','PhD','ARTH909'),(18,'gmartinez','8223fe8dc0533c6ebbb717e7fda2833c','Grace Martinez','gmartinez@example.com','7070707070','246 Cedar St','Female',41,'Music','1983-01-14','PhD','MUS101'),(19,'wbrown','d9096e580dc3527096f37ffb685430cd','William Brown','wbrown@example.com','8080808080','135 Spruce St','Male',39,'Theater','1985-10-22','PhD','THR202'),(20,'mdavis','3384a03103383bb58cb4416a4b8b6eed','Megan Davis','mdavis@example.com','9090909090','357 Elm St','Female',36,'Literature','1988-05-05','PhD','LIT303'),(21,'kwhite','6a4aa369d46cbfc19d285eaa7ec84299','Kyle White','kwhite@example.com','1010101212','468 Maple St','Male',38,'Journalism','1986-07-17','PhD','JOUR404'),(22,'pjackson','bb77d0d3b3f239fa5db73bdf27b8d29a','Patricia Jackson','pjackson@example.com','1111111313','579 Birch St','Female',43,'Linguistics','1981-08-29','PhD','LING505'),(23,'cwilson','9c87baa223f464954940f859bcf2e233','Charles Wilson','cwilson@example.com','1212121414','680 Ash St','Male',46,'Education','1978-12-08','PhD','EDU606'),(24,'hlee','c7753557b59d251571c55a79ef78ee4a','Hannah Lee','hlee@example.com','1313131515','791 Pine St','Female',34,'Geography','1990-09-04','PhD','GEO707'),(25,'erodriguez','ee8ef2f5441316e0305ae007372de337','Edward Rodriguez','erodriguez@example.com','1414141616','902 Oak St','Male',45,'Geology','1979-11-27','PhD','GEO808'),(26,'kjones','65714bebf2b8a0a152d067c556229156','Kelly Jones','kjones@example.com','1515151717','103 Willow St','Female',37,'Astronomy','1987-06-16','PhD','ASTR909'),(27,'dking','7b30dceb697b109ec398e147f533867f','David King','dking@example.com','1616161818','214 Cedar St','Male',40,'Engineering','1984-08-30','PhD','ENG101'),(28,'tsmith','7488e331b8b64e5794da3fa4eb10ad5d','Tina Smith','tsmith@example.com','1717171919','325 Spruce St','Female',39,'Architecture','1985-04-12','PhD','ARCH202'),(29,'jwright','2f768f01222ef02b19468b72a66bca98','James Wright','jwright@example.com','1818182020','436 Elm St','Male',41,'Urban Planning','1983-07-23','PhD','URP303'),(30,'lbrown','1ef87ba5d4f1be925b88ebca371e15f7','Linda Brown','lbrown@example.com','1919192121','547 Maple St','Female',42,'Graphic Design','1982-05-11','PhD','GD404'),(31,'hnguyen','4e8bccd38485a35836610ffd127838ed','Henry Nguyen','hnguyen@example.com','2020202323','658 Birch St','Male',44,'Photography','1980-09-19','PhD','PHOTO505'),(32,'kmiller','a2787c0adcaefe209513b85067302a42','Karen Miller','kmiller@example.com','2121212424','769 Ash St','Female',45,'Culinary Arts','1979-12-01','PhD','CUL606'),(33,'mscott','2a12d2c94f9809ed2e2e9147a4cf26f6','Michael Scott','mscott@example.com','2222222525','870 Pine St','Male',47,'Hospitality Management','1977-03-22','PhD','HM707');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 19:59:17
