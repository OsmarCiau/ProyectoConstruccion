-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectoconstruccion
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `delivery_truck`
--

DROP TABLE IF EXISTS `delivery_truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_truck` (
  `tracking_number` int NOT NULL,
  `capacity` double NOT NULL,
  `mileage` double NOT NULL,
  PRIMARY KEY (`tracking_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_truck`
--

LOCK TABLES `delivery_truck` WRITE;
/*!40000 ALTER TABLE `delivery_truck` DISABLE KEYS */;
INSERT INTO `delivery_truck` VALUES (123,1000,5000),(235,3000,600);
/*!40000 ALTER TABLE `delivery_truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck_assignment`
--

DROP TABLE IF EXISTS `truck_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truck_assignment` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `tracking_number` int DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `FK3rbe7bbw2n6dwtreruubkfmj4` (`tracking_number`),
  KEY `FKe2b0df9y2qt5mim1r46wwsx38` (`driver_id`),
  CONSTRAINT `FK3rbe7bbw2n6dwtreruubkfmj4` FOREIGN KEY (`tracking_number`) REFERENCES `delivery_truck` (`tracking_number`),
  CONSTRAINT `FKe2b0df9y2qt5mim1r46wwsx38` FOREIGN KEY (`driver_id`) REFERENCES `truck_driver` (`license_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_assignment`
--

LOCK TABLES `truck_assignment` WRITE;
/*!40000 ALTER TABLE `truck_assignment` DISABLE KEYS */;
INSERT INTO `truck_assignment` VALUES (1,123,123456);
/*!40000 ALTER TABLE `truck_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck_driver`
--

DROP TABLE IF EXISTS `truck_driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truck_driver` (
  `license_number` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`license_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_driver`
--

LOCK TABLES `truck_driver` WRITE;
/*!40000 ALTER TABLE `truck_driver` DISABLE KEYS */;
INSERT INTO `truck_driver` VALUES (123456,'John Doe');
/*!40000 ALTER TABLE `truck_driver` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-29  1:34:01
