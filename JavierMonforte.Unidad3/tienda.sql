-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cliente` (
  `IDCLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `DIRECCION` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `POBLACION` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TELEF` varchar(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  `NIF` varchar(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`IDCLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Hotel Astoria, S.A.','Plza. Rodrigo Botet, 5','Valencia','963326588','A28031650'),(2,'Hotel Ciudad de Valencia','Av. del Puerto, 214','Valencia','963674521','A46302524'),(3,'Francisco Lopez Serrano','C/Cuenca, 68','Valencia','963234217','24354274K'),(4,'Javier Monforte','Plaza Salamero 14','Zaragoza','669604539','b99276990'),(5,'Ruben notivol','Avda Goya','Zaragoza','625789625','b28964539');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `producto` (
  `IDPRODUCTO` int(11) NOT NULL,
  `DESCRIPCION` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `STOCKACTUAL` int(11) DEFAULT NULL,
  `STOCKMINIMO` int(11) DEFAULT NULL,
  `PVP` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDPRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Plato llano',800,200,22),(2,'Plato hondo grande',600,100,6),(3,'Plato llano pequeño',600,100,11),(4,'Copas de agua',200,50,12),(5,'Copas de vino',200,50,12),(6,'Mantel algodon Blanco',190,50,15),(7,'Mantel algodon Verde',150,50,5),(8,'Bol hondo 22cm',100,25,12);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `venta` (
  `IDVENTA` int(11) NOT NULL,
  `FECHAVENTA` date NOT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  `IDPRODUCTO` int(11) DEFAULT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDVENTA`),
  KEY `PRODUCTO_fk` (`IDPRODUCTO`),
  KEY `CLIENTE_fk` (`IDCLIENTE`),
  CONSTRAINT `CLIENTE_fk` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`idcliente`),
  CONSTRAINT `PRODUCTO_fk` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'2018-08-25',1,6,60),(2,'2018-08-25',1,7,50),(3,'2018-08-25',1,4,2),(4,'2018-08-25',2,6,100),(5,'2018-08-25',2,7,50),(6,'2018-09-01',3,6,40),(7,'2018-09-01',3,7,40),(8,'2018-09-01',3,4,4),(9,'2018-09-01',3,5,4),(10,'2018-09-01',3,1,5),(11,'2018-11-11',1,1,1),(12,'2018-12-25',1,2,25),(13,'2018-12-25',2,2,2),(14,'2018-11-11',2,5,5),(15,'2018-12-20',1,6,3),(16,'2018-12-25',2,5,18),(17,'2018-12-01',4,5,10);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 15:28:57
