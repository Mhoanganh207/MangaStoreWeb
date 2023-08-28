-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: firstweb
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `preprice` double NOT NULL,
  `price` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Hitsuji Gondarai','Shonen, Action','Taiyo Asano is a super shy high school student and the only person he can talk to is his childhood friend, Mutsumi Yozakura. It turns out that Mutsumi is the daughter of the ultimate spy family! Even worse, Mutsumi is being harassed by her overprotective, nightmare of a brother, Kyoichiro. What drastic steps will Taiyo have to take to save Mutsumi?! A spy family comedy — the mission begins!','https://dw9to29mmj727.cloudfront.net/products/197473689X.jpg',8,6,'Misson Yozakura Family vol.6'),(2,'Aka Akasaka','Seinen, Romance','The proudly privileged top two students of an elite school each makes it their mission to be the first to extract a confession of love from the other.','https://dw9to29mmj727.cloudfront.net/products/1974738752.jpg',10,9,'Kaguya-sama Love is war vol.26'),(3,'Oda Eiichiro','Shonen, Action','The story follows the adventures of Monkey D. Luffy and his crew, the Straw Hat Pirates, where he explores the Grand Line in search of the mythical treasure known as the \"One Piece\" in order to become the next King of the Pirates.','https://dw9to29mmj727.cloudfront.net/products/1974738701.jpg',10,7.5,'One Piece vol.4'),(4,'Yukinobu Tatsu','Shonen, Comedy','Momo Ayase is a high school girl who believes in ghosts but not aliens, while her classmate Okarun believes in aliens but not ghosts. In a bet to determine who\'s correct, the two decide to separately visit locations associated with both the occult and the supernatural','https://dw9to29mmj727.cloudfront.net/products/1974737446.jpg',10,7.5,'Dandadan vol.4'),(5,'Hirohiko Araki','Shonen, Action','The series follows the adventures of Giorno Giovanna, the son of Dio Brando (from Phantom Blood and Stardust Crusaders) albeit conceived with Jonathan Joestar\'s body, who joins the criminal organization Passione in the hopes of becoming a gangster (or \"Gang-Star\") and taking control of the organization in the name of reform.','https://dw9to29mmj727.cloudfront.net/products/1974724174.jpg',18,16.33,'JoJo’s Bizarre Adventure: Part 5--Golden Wind, Vol. 9'),(6,'Tite Kubo','Shonen, Action, Adventure','It follows the adventures of a teenager Ichigo Kurosaki, who inherits his parents\' destiny after he obtains the powers of a Soul Reaper—a death personification similar to the Grim Reaper—from another Soul Reaper, Rukia Kuchiki.','https://dw9to29mmj727.cloudfront.net/products/1421596024.jpg',10,9,'Bleach vol.73'),(7,'Akira Toriyama','Shonen, Action','Dragon Ball Z continues the adventures of Goku in his adult life as he and his companions defend the Earth against villains including aliens (Vegeta, Frieza), androids (Cell), and magical creatures (Majin Buu). At the same time, the story parallels the life of his son, Gohan, as well as the development of his rivals, Piccolo and Vegeta.','https://dw9to29mmj727.cloudfront.net/products/142150636X.jpg',9,8,'Dragon Ball vol.26'),(8,'Masashi Kishimoto','Shonen, Action','The manga tells the story of Naruto Uzumaki, a young ninja who seeks recognition from his peers and dreams of becoming the Hokage, the leader of his village.','https://dw9to29mmj727.cloudfront.net/products/1421582848.jpg',10,6.5,'Naruto vol.72');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-25 20:21:05
