-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: empresamsql
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `IDProducto` int NOT NULL AUTO_INCREMENT,
  `IDCategoria` int DEFAULT NULL,
  `Nombre` varchar(40) NOT NULL,
  `CanUnidad` varchar(40) DEFAULT NULL,
  `Precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`IDProducto`),
  KEY `fk_categoria` (`IDCategoria`),
  CONSTRAINT `fk_categoria` FOREIGN KEY (`IDCategoria`) REFERENCES `categorias` (`IDCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,'Té Dharamsala','10 cajas x 20 bolsas',34.00),(2,1,'Cerveza tibetana','24 - bot. 12 l',23.00),(3,2,'Sirope de regaliz','12 - bot. 550 ml',12.00),(4,2,'Especias Cajun del chef Anton New','48 - frascos 6 l',23.00),(5,2,'Mezcla Gumbo del chef Anton','Condimentos 36 cajas',34.00),(6,2,'Mermelada de grosellas de la abuela','12 - frascos 8 l',45.00),(7,7,'Peras secas orgánicas del tío Bob','12 - paq. 1 kg',23.00),(9,6,'Buey Mishi Kobe Tokyo Traders','18 - paq. 500 g',23.00),(10,8,'Pez espada Tokyo Traders','12 - frascos 200 ml',56.00),(11,4,'Queso Cabrales Cooperativa de Quesos','paq. 1 kg',12.00),(12,4,'Queso Manchego La Pastora Cooperativa','10 - paq. 500 g',10.00),(13,8,'Algas Konbu Mayumi\'s','caja 2 kg',45.00),(14,7,'Cuajada de judías Mayumi\'s','40 - paq. 100 g',60.00),(16,3,'Postre de merengue Pavlova Pavlova.','32 - cajas 500 g',25.00),(17,6,'Cordero Alice Springs Pavlova, Ltd.','20 - latas 1 kg',10.00),(18,8,'Langostinos tigre Carnarvon Pavlova.','paq. 16 kg',35.00),(19,3,'Pastas de té de chocolate Specialty','10 cajas x 12 piezas',45.00),(20,3,'Mermelada de Sir Rodney\'s Special','regalo',15.00);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-19 23:45:37
