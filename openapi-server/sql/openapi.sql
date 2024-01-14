-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 10.13.0.128    Database: openapi
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `openapi`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `openapi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `openapi`;

--
-- Table structure for table `interface_info`
--

DROP TABLE IF EXISTS `interface_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interface_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接口名称',
  `url` varchar(510) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接口url地址',
  `description` varchar(510) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '接口描述',
  `method` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求类型',
  `request_param` text COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
  `request_header` text COLLATE utf8mb4_unicode_ci COMMENT '请求头',
  `response_header` text COLLATE utf8mb4_unicode_ci COMMENT '响应头',
  `user_id` bigint NOT NULL COMMENT '创建人',
  `status` int NOT NULL DEFAULT '0' COMMENT '接口状态（0-默认关闭，1-开启）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除（0-正常，1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='接口信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interface_info`
--

LOCK TABLES `interface_info` WRITE;
/*!40000 ALTER TABLE `interface_info` DISABLE KEYS */;
INSERT INTO `interface_info` VALUES (1,'随机获取一个可爱的女孩子！','www.kawayigirl.com','555，我就是要和可爱的女孩子贴贴嘛~','POST',NULL,'','',3,0,'2024-01-03 15:53:37','2024-01-12 22:43:58',0),(2,'随机获取头像','www.getAvatar.com','','GET',NULL,'','',3,0,'2024-01-03 16:11:38','2024-01-12 22:45:03',1),(3,'获取你的可爱的女朋友','http://localhost:8081/api/girl/getGirlFriend','世界奇奇怪怪，mio可可爱爱！','GET','name=mio','',NULL,3,1,'2024-01-03 16:13:37','2024-01-13 18:18:50',0),(4,'转生成为女孩子！','http://localhost:8081/api/girl/changeToGirl','不可以欺负女孩子！','POST','{\n  \"name\": \"Eru\",\n  \"age\": \"16\"\n}','Header','Header',1,1,'2024-01-08 15:48:54','2024-01-13 18:18:38',0),(5,'taco','getTaco.com',NULL,'get',NULL,NULL,NULL,1,0,'2024-01-12 22:48:45','2024-01-12 22:50:14',1),(6,'taco','getTaco.com',NULL,'EAT',NULL,NULL,NULL,1,0,'2024-01-12 22:51:13','2024-01-12 22:51:13',1);
/*!40000 ALTER TABLE `interface_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `api_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'api签名认证',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USER' COMMENT '用户角色',
  `status` int NOT NULL DEFAULT '0' COMMENT '用户状态(0-正常，1-禁用)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '0-正常，1-被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'weng','$2a$10$dolcc65nCVo0IvyGNoRjv.jqqhVsCLRW7ya3LYtKU7Ql9rbxTpvCG','api-ee9cb04317251601d9a15e8c35312d24','ADMIN',0,'2024-01-01 23:35:03','2024-01-01 23:35:03',0),(2,'husiqi','$2a$10$dhMUdBchATW/.KqrJKUFZuPvOfQagX/x0rK3XMHXOeRFTL1YDycgi',NULL,'USER',0,'2024-01-03 13:44:47','2024-01-03 13:44:47',0),(3,'xiaoqiqi','$2a$10$H1YH8SbGLR5hygCKnkRnau48nsE/KY6B1UxbHSvirpKggzjYoChq6',NULL,'ADMIN',0,'2024-01-03 13:48:03','2024-01-03 13:48:03',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-14 19:44:10
