 SET NAMES utf8 ;
DROP TABLE IF EXISTS `libro`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `libro` (
  `CodigoLibro` int(11) NOT NULL,
  `Titulo` varchar(50) DEFAULT NULL,
  `Autor` varchar(50) DEFAULT NULL,
  `Editorial` varchar(50) DEFAULT NULL,
  `Anio` int(11) DEFAULT NULL,
  `ISBN` varchar(15) NOT NULL,
  `Ejemplares` int(11) DEFAULT NULL,
  `Paginas` int(11) DEFAULT NULL,
  PRIMARY KEY (`CodigoLibro`),
  UNIQUE KEY `ISBN` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `libro` WRITE;
INSERT INTO `libro` VALUES (1,'El tránsito terreno','Plasencia, Juan Luis','Larrosa Mas, S.L.',1996,'84-121-2310-1',152,2),(2,'Sistemas operativos','Bazilian Eric','GGG&G',1994,'84-7489-146-9',470,5),(3,'Poemas intrínsecos','Llorens Antonia','Deloria Editores',1997,'84-305-0473-7',173,3),(4,'Avances en Arquitectura','Richter, Helmut','TechniBooks',1991,'84-473-0120-6',422,7),(5,'Las balas del bien','Leverling, Janet','GGG&G',1995,'84-206-1704-0',181,4),(6,'La mente y el sentir','Plasencia, Juan Luis','Larrosa Mas, S.L.',1992,'84-226-2128-2',196,5),(7,'Ensayos póstumos','Bertomeu, Andrés','Deloria Editores',1995,'84-7908-349-2',290,12),(8,'La dualidad aparente','Sanabria, Carmelo','Larrosa Mas, S.L.',1994,'84-578-0214-8',157,8),(9,'Arquitectura y arte','Richter, Helmut','Grisham Publishing',1992,'84-02-08696-9',512,5),(10,'Historia de Occidente','Dulac, George','McCoy Hill',1995,'84-01-92101-5',250,7),(11,'Sentimiento popular','Llorens, Antonia','Larrosa Mas, S.L.',1994,'84-7634-421-1',199,2),(12,'Amigos o enemigos','Sanabria, Carmelo','GGG&G',1996,'84-404-8586-7',233,9),(13,'La burguesía del XIX','Dulac, George','Deloria Editores',1996,'84-205-1101-3',376,7),(14,'Procesadores cuánticos','Bazilian, Eric','Grisham Publishing',1997,'84-212-2121-2',452,4),(15,'Canto de esperanza','Davolio, Nancy','McCoy Hill',1995,'84-444-0027-3',198,5);
UNLOCK TABLES;
DROP TABLE IF EXISTS `socio`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `socio` (
  `CodigoSocio` int(11) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Apellidos` varchar(50) DEFAULT NULL,
  `FechaNac` date DEFAULT NULL,
  `Domicilio` varchar(50) DEFAULT NULL,
  `Telefono` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CodigoSocio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `socio` WRITE;
INSERT INTO `socio` VALUES (101,'Javier','Notivol','2000-12-12','C/ 5 de marzo','666-256-865'),(102,'Juan','Perez','1978-01-27','Avda Navarra','625-286-695'),(103,'Susuna','Sanz','1965-05-24','Plaza Salamero','663-594-681'),(104,'Lucia','Garcia','1977-07-13','Baltasar gracian','629-356-783'),(105,'Manuel','Serrano','1989-07-07','Avda Goya','620-056-451'),(106,'Alejandro','Bernal','2000-02-10','Mago de Oz','680-905-128'),(107,'Dolores','Lopez','1962-04-05','Volver a empezar','675-306-156'),(108,'Ruben','Bezares','1946-07-31','Tomas Breton','689-896-158');
UNLOCK TABLES;

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prestamo` (
  `CodigoLibro` int(11) NOT NULL,
  `CodigoSocio` int(11) NOT NULL,
  `FechaInicio` date DEFAULT NULL,
  `FechaFin` date DEFAULT NULL,
  PRIMARY KEY (`CodigoLibro`,`CodigoSocio`),
  KEY `SOCIO_fk` (`CodigoSocio`),
  CONSTRAINT `LIBRO_fk` FOREIGN KEY (`CodigoLibro`) REFERENCES `libro` (`codigolibro`) ON DELETE CASCADE,
  CONSTRAINT `SOCIO_fk` FOREIGN KEY (`CodigoSocio`) REFERENCES `socio` (`codigosocio`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `prestamo` WRITE;
INSERT INTO `prestamo` VALUES (1,101,'2010-09-29','2010-10-07'),(1,103,'2010-09-27','2010-10-04'),(1,108,'2010-09-25','2010-10-01'),(2,101,'2010-09-27','2010-10-04'),(2,102,'2010-09-29','2010-10-07'),(2,104,'2010-09-26','2010-10-02'),(2,107,'2010-09-25','2010-10-01'),(2,108,'2010-09-26','2010-10-02'),(3,102,'2010-09-28','2010-10-05'),(3,103,'2010-09-29','2010-10-07'),(3,105,'2010-09-26','2010-10-02'),(3,106,'2010-09-25','2010-10-01'),(4,103,'2010-09-28','2010-12-05'),(4,104,'2010-09-29','2010-10-07'),(4,105,'2010-09-25','2010-10-01'),(4,106,'2010-09-26','2010-11-02'),(5,105,'2010-09-28','2010-10-05'),(5,107,'2010-09-26','2010-10-03'),(6,103,'2010-09-25','2010-10-01'),(6,104,'2010-09-28','2010-10-05'),(6,105,'2010-09-25','2010-10-01'),(6,108,'2010-09-26','2010-10-03'),(7,101,'2010-09-28','2010-10-05'),(7,102,'2010-09-25','2010-10-01'),(7,103,'2010-09-26','2010-10-03'),(7,106,'2010-09-28','2010-10-05'),(8,101,'2010-09-25','2010-10-01'),(8,103,'2010-09-26','2010-10-03'),(9,101,'2010-09-25','2010-10-02'),(9,102,'2010-09-28','2010-10-06'),(9,104,'2010-09-26','2010-10-03'),(10,102,'2010-09-25','2010-10-02'),(10,103,'2010-09-29','2010-10-06'),(10,105,'2010-09-26','2010-10-03'),(11,103,'2010-09-25','2010-10-02'),(11,104,'2010-09-29','2010-10-06'),(11,106,'2010-09-26','2010-10-03'),(12,104,'2010-09-25','2010-11-02'),(12,105,'2010-09-29','2010-10-06'),(12,107,'2010-09-27','2010-10-04'),(13,105,'2010-09-25','2010-10-02'),(13,106,'2010-09-29','2010-10-06'),(13,108,'2010-09-27','2010-10-04'),(14,101,'2010-09-27','2010-11-04'),(14,106,'2010-09-25','2010-10-02'),(14,107,'2010-09-29','2010-10-06'),(15,102,'2010-09-27','2010-10-04'),(15,107,'2010-09-26','2010-10-02'),(15,108,'2010-09-29','2010-10-06');
UNLOCK TABLES;