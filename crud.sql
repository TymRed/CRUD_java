CREATE DATABASE  IF NOT EXISTS `crud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crud`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: crud
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
-- Table structure for table `asignaturas`
--

DROP TABLE IF EXISTS `asignaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignaturas` (
  `nombre` varchar(45) NOT NULL,
  `nombreprof` varchar(45) NOT NULL,
  `cantidadalum` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`nombre`),
  UNIQUE KEY `nombreprof_UNIQUE` (`nombreprof`),
  KEY `FK_nombreProf_profesores_idx` (`nombreprof`),
  CONSTRAINT `FK_nombreProf_profesores` FOREIGN KEY (`nombreprof`) REFERENCES `profesores` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignaturas`
--

LOCK TABLES `asignaturas` WRITE;
/*!40000 ALTER TABLE `asignaturas` DISABLE KEYS */;
INSERT INTO `asignaturas` VALUES ('BBDD','Prof2',0),('LMSG','Prof4',0),('Prog','Prof1',0),('SISI','Prof3',0);
/*!40000 ALTER TABLE `asignaturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `nombre_asignatura` varchar(45) NOT NULL,
  `nombre_estudiante` varchar(45) NOT NULL,
  `notafinal` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`nombre_asignatura`,`nombre_estudiante`),
  KEY `FK_nombreEstudiante_estudiantes_idx` (`nombre_estudiante`),
  CONSTRAINT `FK_nombreAsignatura_asignaturas` FOREIGN KEY (`nombre_asignatura`) REFERENCES `asignaturas` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_nombreEstudiante_estudiantes` FOREIGN KEY (`nombre_estudiante`) REFERENCES `estudiantes` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES ('Prog','Albert',NULL),('Prog','Claudiu',NULL),('Prog','Dani',NULL),('Prog','Diego',NULL),('Prog','Fran',NULL),('Prog','Javi',NULL),('Prog','Luru',NULL),('Prog','Tym',NULL),('SISI','Claudiu',NULL),('SISI','Diego',NULL),('SISI','Tym',NULL);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiantes`
--

DROP TABLE IF EXISTS `estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiantes` (
  `nombre` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiantes`
--

LOCK TABLES `estudiantes` WRITE;
/*!40000 ALTER TABLE `estudiantes` DISABLE KEYS */;
INSERT INTO `estudiantes` VALUES ('Albert','aa'),('Claudiu','aa'),('Dani','aa'),('Diego','aa'),('Fran','aa'),('Javi','aa'),('Luru','aa'),('Sebas','aa'),('Tym','aa');
/*!40000 ALTER TABLE `estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `nombre` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES ('Prof1','aa'),('Prof2','aa'),('Prof3','aa'),('Prof4','aa');
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `nombre` varchar(45) NOT NULL,
  `nombre_estudiante` varchar(45) NOT NULL,
  `nombre_asignatura` varchar(45) NOT NULL,
  `nota` decimal(4,2) DEFAULT NULL,
  `entregado_fecha` date DEFAULT NULL,
  PRIMARY KEY (`nombre`,`nombre_asignatura`,`nombre_estudiante`),
  KEY `FK_nombreAsignatura_cursos_idx` (`nombre_asignatura`),
  KEY `FK_nombreEstudiante_cursos_idx` (`nombre_estudiante`),
  CONSTRAINT `FK_nombreAsignatura_tareasinfo` FOREIGN KEY (`nombre_asignatura`) REFERENCES `tareasinfo` (`nombre_asignatura`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_nombreEstudiante_cursos` FOREIGN KEY (`nombre_estudiante`) REFERENCES `cursos` (`nombre_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_nombreTarea_tareaInfo` FOREIGN KEY (`nombre`) REFERENCES `tareasinfo` (`nombre`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES ('Tarea1','Albert','Prog',NULL,'2024-05-22'),('Tarea1','Claudiu','Prog',NULL,'2024-05-22'),('Tarea1','Dani','Prog',NULL,'2024-05-22'),('Tarea1','Diego','Prog',NULL,'2024-05-22'),('Tarea1','Fran','Prog',NULL,'2024-05-22'),('Tarea1','Javi','Prog',NULL,'2024-05-22'),('Tarea1','Luru','Prog',NULL,'2024-05-22'),('Tarea1','Tym','Prog',NULL,'2024-05-22'),('Tarea1','Claudiu','SISI',NULL,'2024-05-22'),('Tarea1','Diego','SISI',NULL,'2024-05-22'),('Tarea1','Tym','SISI',NULL,'2024-05-22'),('Tarea2','Javi','Prog',NULL,'2024-05-22'),('Tarea2','Tym','Prog',NULL,'2024-05-22'),('Tarea3','Fran','Prog',NULL,'2024-05-22');
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareasinfo`
--

DROP TABLE IF EXISTS `tareasinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareasinfo` (
  `nombre` varchar(45) NOT NULL,
  `nombre_asignatura` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`,`nombre_asignatura`),
  KEY `FK_nombreAsignatura2_asignatura_idx` (`nombre_asignatura`),
  CONSTRAINT `FK_nombreAsignatura2_asignatura` FOREIGN KEY (`nombre_asignatura`) REFERENCES `asignaturas` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareasinfo`
--

LOCK TABLES `tareasinfo` WRITE;
/*!40000 ALTER TABLE `tareasinfo` DISABLE KEYS */;
INSERT INTO `tareasinfo` VALUES ('Tarea1','Prog'),('Tarea2','Prog'),('Tarea3','Prog'),('Tarea1','SISI');
/*!40000 ALTER TABLE `tareasinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-27 23:19:00
