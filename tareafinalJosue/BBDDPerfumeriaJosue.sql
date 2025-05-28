-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: BDPerfumeria
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.24.04.1

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
-- Table structure for table `detallePerfume`
--

DROP TABLE IF EXISTS `detallePerfume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallePerfume` (
  `idDetalle` int NOT NULL AUTO_INCREMENT,
  `idPerfume` int DEFAULT NULL,
  `idNota` int DEFAULT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `fk_perfume` (`idPerfume`),
  KEY `fk_nota` (`idNota`),
  CONSTRAINT `fk_nota` FOREIGN KEY (`idNota`) REFERENCES `notasAromaticas` (`idNota`),
  CONSTRAINT `fk_perfume` FOREIGN KEY (`idPerfume`) REFERENCES `perfumes` (`idPerfume`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallePerfume`
--

LOCK TABLES `detallePerfume` WRITE;
/*!40000 ALTER TABLE `detallePerfume` DISABLE KEYS */;
INSERT INTO `detallePerfume` VALUES (1,1,2),(2,2,2),(3,3,5),(4,4,5),(5,7,9),(6,6,9),(7,2,3),(8,9,3),(9,4,6),(10,6,6),(11,8,6),(12,5,8),(13,1,11),(14,2,11),(15,3,7),(16,8,7);
/*!40000 ALTER TABLE `detallePerfume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disenadores`
--

DROP TABLE IF EXISTS `disenadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disenadores` (
  `idDisenador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `ape1` varchar(50) DEFAULT NULL,
  `ape2` varchar(50) DEFAULT NULL,
  `fecNacimiento` date DEFAULT NULL,
  PRIMARY KEY (`idDisenador`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disenadores`
--

LOCK TABLES `disenadores` WRITE;
/*!40000 ALTER TABLE `disenadores` DISABLE KEYS */;
INSERT INTO `disenadores` VALUES (1,'JeanPaul','Gaultier',NULL,'1952-04-24'),(2,'Thomas','Carlyle','Ford','1961-08-27'),(3,'Francisco','Rabaneda','Cuervo','1934-02-18'),(4,'Christian','Dior',NULL,'1905-01-21'),(5,'Giorgio','Armani','Raimondi','1934-07-11');
/*!40000 ALTER TABLE `disenadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notasAromaticas`
--

DROP TABLE IF EXISTS `notasAromaticas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notasAromaticas` (
  `idNota` int NOT NULL AUTO_INCREMENT,
  `tipoNota` enum('salida','corazon','base') DEFAULT NULL,
  `olor` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`idNota`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notasAromaticas`
--

LOCK TABLES `notasAromaticas` WRITE;
/*!40000 ALTER TABLE `notasAromaticas` DISABLE KEYS */;
INSERT INTO `notasAromaticas` VALUES (1,'base','Haba tonka'),(2,'corazon','Coco'),(3,'salida','Acuoso'),(4,'base','Vainilla'),(5,'salida','Lavanda'),(6,'base','Pachuli'),(7,'salida','Cardamomo'),(8,'corazon','Cacao'),(9,'corazon','Ron'),(10,'base','Ambar'),(11,'salida','Jengibre'),(12,'base','Vetiver');
/*!40000 ALTER TABLE `notasAromaticas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfumes`
--

DROP TABLE IF EXISTS `perfumes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfumes` (
  `idPerfume` int NOT NULL AUTO_INCREMENT,
  `idDisenador` int DEFAULT NULL,
  `nombrePerfume` varchar(50) DEFAULT NULL,
  `nombreLinea` varchar(50) DEFAULT NULL,
  `precio` decimal(6,2) DEFAULT NULL,
  `horasDuracion` int DEFAULT NULL,
  `cantidadML` int DEFAULT NULL,
  `tipoPerfume` enum('eau de toilette','eau de parfum','parfum','elixir') DEFAULT NULL,
  PRIMARY KEY (`idPerfume`),
  KEY `fk_disenador_perfume` (`idDisenador`),
  CONSTRAINT `fk_disenador_perfume` FOREIGN KEY (`idDisenador`) REFERENCES `disenadores` (`idDisenador`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfumes`
--

LOCK TABLES `perfumes` WRITE;
/*!40000 ALTER TABLE `perfumes` DISABLE KEYS */;
INSERT INTO `perfumes` VALUES (1,1,'Le Beau LeParfum','Le Beau',85.95,8,125,'eau de parfum'),(2,1,'Paradise Garden','Le Beau',84.95,6,125,'eau de parfum'),(3,3,'Victory Elixir','Invictus',118.95,10,200,'elixir'),(4,3,'Phantom','Phantom',82.95,5,150,'eau de toilette'),(5,2,'Tobacco Vanille','Private Blend',260.00,8,50,'eau de parfum'),(6,2,'Bitter Peach','Private Blend',350.00,7,50,'eau de parfum'),(7,4,'Fahrenheit','Fahrenheit',103.50,10,75,'parfum'),(8,4,'Sauvage Elixir','Sauvage',185.95,12,100,'elixir'),(9,5,'Acqua di Gio Profondo','Acqua di Gio',71.95,9,100,'parfum'),(10,5,'Stronger with you Intensly','Stronger With You',79.95,12,150,'eau de parfum');
/*!40000 ALTER TABLE `perfumes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'BDPerfumeria'
--

--
-- Dumping routines for database 'BDPerfumeria'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-28 14:00:19
