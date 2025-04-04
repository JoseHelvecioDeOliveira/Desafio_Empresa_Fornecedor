-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: empresa_fornecedor
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cep` varchar(255) DEFAULT NULL,
  `cnpjcpf` varchar(255) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKh0xttlw43w2dn7dok7kt9d773` (`cnpjcpf`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'29175-674','13193630675','1980-01-01','bancodobrasil@bb.com','Banco do Brasil','987654321'),(2,'58430-695','68085641089','1980-01-01','bancodobrasil@bb.com','Banco do Itau bh','987654321'),(4,'15503-410','414829830007','1980-01-01','bancodobrasil@bb.com','Banco marcantil brasil 2','987654321'),(5,'15503-410','08063086000103',NULL,'bancodobrasil@bb.com','Banco sicoob creditos',NULL),(11,'31140-250','90.053.207/0001-17',NULL,'josehelvecio18@gmail.com','Fornecedor Front',''),(14,'31140-250','71.118.796/0001-20',NULL,'josehelvecio18@gmail.com','José Helvécio de Oliveira Filho','20173601'),(15,'31140-250','97.373.875/0001-70',NULL,'josehelvecio18@gmail.com','sdafasdfasdfas','20173608'),(17,'31140-250','32.690.968/0001-83','2001-08-11','josehelvecio18@gmail.com','AFSDFASD','20173601'),(18,'31140-250','17679521000110','2001-08-11','josehelvecio18@gmail.com','José Helvécio de Oliveira Filho teste final','20173601'),(19,'83503-280','71239439024','2025-04-02','josehelvecio18@gmail.com2','PR22','987654322'),(20,'31140-250','95788746000','2000-02-24','josehelvecio18@gmail.com','José Helvécio de Oliveira Filho','987654321');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-03 20:53:28
