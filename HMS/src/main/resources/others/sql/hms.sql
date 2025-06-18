-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hms
-- ------------------------------------------------------
-- Server version	8.0.33

create database hms;
drop database if exists hms;
create database hms charset utf8;
use hms;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `admin`
--


DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin`
(
    `adminid`  int NOT NULL AUTO_INCREMENT COMMENT '管理员id，主键',
    `username` varchar(50) DEFAULT NULL COMMENT '用户名',
    `password` varchar(50) DEFAULT NULL COMMENT '密码',
    `realname` varchar(50) DEFAULT NULL COMMENT '姓名',
    `contact`  varchar(20) DEFAULT NULL COMMENT '电话号码',
    `addtime`  date        DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`adminid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin`
    DISABLE KEYS */;
INSERT INTO `admin`
VALUES (1, 'heweiyuan', '7d77a432abb09a881e298bc0dfa98bd9', '何维远', '12345879465', '2023-12-01'),
       (2, 'liuji1', 'ea7975a812ab7887cb22d5eff8923f1f', '刘吉', '9876543210', '2023-12-01'),
       (3, 'lihangkai', 'a967d516497603ecc84fe79f456036be', '李航凯', '1112223333', '2023-12-01'),
       (4, 'laiyufan', '6c5d956fb7a808478dcb99c4cae2e750', '赖宇凡', '4445556666', '2023-12-01'),
       (5, 'caozhexuan', '96c028cc60fef9b633e7cf9744387db9', '曹哲轩', '7778889999', '2023-05-01'),
       (6, 'ailaries', '129ca3252419bfdf739569e78e04585b', '曹哲轩', '13809146261', '2023-11-30'),
       (8, 'admin1', 'adminpass1', '管理员1', '1234567890', '2023-01-01'),
       (9, 'admin2', 'adminpass2', '管理员2', '9876543210', '2023-02-01'),
       (10, 'admin3', 'adminpass3', '管理员3', '1112223333', '2023-03-01'),
       (11, 'admin4', 'adminpass4', '管理员4', '4445556666', '2023-04-01');
/*!40000 ALTER TABLE `admin`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article`
(
    `articleid` int NOT NULL AUTO_INCREMENT COMMENT '新闻id，主键',
    `title`     varchar(50)  DEFAULT NULL COMMENT '新闻标题',
    `image`     varchar(255) DEFAULT NULL COMMENT '新闻图片',
    `contents`  text COMMENT '新闻内容',
    `addtime`   date         DEFAULT NULL COMMENT '新闻添加时间',
    `hits`      int          DEFAULT NULL COMMENT '点击量',
    PRIMARY KEY (`articleid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article`
    DISABLE KEYS */;
INSERT INTO `article`
VALUES (1, '奢华雅致，尽在此处', '202312111128000.png',
        '在这个奢华雅致的角落，我们为您打造了一场别致的入住体验。从精心设计的空间到无微不至的服务，尽在此处，让您感受到身临其境的宾至如归之感。',
        '2023-01-01', 100),
       (2, '梦幻之境，尽在酒店', '202312071814012.jpg',
        '这里是一片梦幻之境，酒店如同一个独特的梦境，等待着您的到来。在这个充满惊喜和温馨的空间里，您将发现属于自己的私人天堂。',
        '2023-02-01', 120),
       (3, '享受奢华，感受温馨', '202312102015037.jpg',
        '在这里，我们用心为您提供一场奢华的享受，每一个温馨的细节都被精心雕琢。在这个宛如家的地方，您将感受到别样的宾至如归之感。',
        '2023-03-01', 80),
       (4, '宾至如归，豪华定制', '202312071814044.jpg',
        '客人如亲人般的款待，每一次的入住都是一场豪华的定制体验。我们为您精心准备，只为让您在这里度过一段难忘的时光。',
        '2023-04-01', 150),
       (5, '五星享受，心意随行', '202312071814053.jpg',
        '五星级的享受，随时随地。在这里，您不仅能感受到豪华的服务，更能体验到心意随行的贴心呵护，让您尽情享受每一刻。',
        '2023-05-01', 200),
       (6, '品味人生，留宿精选', '202312071741030.jpg',
        '这里不仅是您的落脚之地，更是品味人生的绝佳选择。留宿精选酒店，让您感受到品质生活的精致和舒适', '2023-11-29', 10);
/*!40000 ALTER TABLE `article`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking`
(
    `bookingid` int NOT NULL AUTO_INCREMENT COMMENT '收支id，主键',
    `bno`       varchar(50)    DEFAULT NULL COMMENT '收支单号',
    `cate`      varchar(50)    DEFAULT NULL COMMENT '收支类型',
    `reason`    varchar(50)    DEFAULT NULL COMMENT '收支原因',
    `total`     decimal(10, 2) DEFAULT NULL COMMENT '总计',
    `addtime`   date           DEFAULT NULL COMMENT '日期',
    PRIMARY KEY (`bookingid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking`
    DISABLE KEYS */;
INSERT INTO `booking`
VALUES (1, 'BNO001', '支出', '给员工买零食', 1213.00, '2023-11-08'),
       (2, 'BNO002', '支出', '食物采购', 150.00, '2023-11-01'),
       (3, 'BNO003', '收入', '活动预订', 500.00, '2023-11-03'),
       (4, 'BNO004', '支出', '办公用品', 50.00, '2023-11-08'),
       (5, 'BNO005', '收入', '会议室预订', 300.00, '2023-11-26'),
       (6, 'BNO006', '收入', '活动预定', 1341.00, '2023-12-03');
/*!40000 ALTER TABLE `booking`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy`
(
    `buyid`     int NOT NULL AUTO_INCREMENT COMMENT '采购id，主键',
    `bno`       varchar(50)    DEFAULT NULL COMMENT '采购单号',
    `goodsname` varchar(50)    DEFAULT NULL COMMENT '采购货物',
    `price`     decimal(10, 2) DEFAULT NULL COMMENT '采购单价',
    `num`       int            DEFAULT NULL COMMENT '采购数量',
    `total`     decimal(10, 2) DEFAULT NULL COMMENT '总计',
    `addtime`   date           DEFAULT NULL COMMENT '采购日期',
    `manager`   varchar(10)    DEFAULT NULL COMMENT '经手人',
    `memo`      varchar(50)    DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`buyid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy`
    DISABLE KEYS */;
INSERT INTO `buy`
VALUES (1, 'P001', '纸张', 5.00, 100, 500.00, '2023-11-26', '约翰', '办公用品'),
       (2, 'P002', '笔记本电脑', 800.00, 2, 1600.00, '2023-11-26', '艾丽斯', 'IT设备'),
       (3, 'P003', '椅子', 30.00, 10, 300.00, '2023-11-26', '鲍勃', '办公家具'),
       (4, 'P004', '咖啡机', 150.00, 1, 150.00, '2023-11-26', '查理', '办公设备'),
       (5, 'P005', '投影仪', 200.00, 1, 200.00, '2023-11-26', '大卫', '会议室设备'),
       (6, 'P006', '纸张', 5.00, 100, 500.00, '2023-11-26', '约翰', '办公用品'),
       (7, 'P007', '笔记本电脑', 800.00, 2, 1600.00, '2023-11-26', '艾丽斯', 'IT设备'),
       (8, 'P008', '椅子', 30.00, 10, 300.00, '2023-11-26', '鲍勃', '办公家具'),
       (9, 'P009', '咖啡机', 150.00, 1, 150.00, '2023-11-26', '查理', '办公设备'),
       (10, 'P010', '投影仪', 200.00, 1, 200.00, '2023-11-26', '大卫', '会议室设备');
/*!40000 ALTER TABLE `buy`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart`
(
    `cartid`  int NOT NULL AUTO_INCREMENT COMMENT '购买物品id',
    `usersid` int            DEFAULT NULL COMMENT '用户id',
    `foodsid` int            DEFAULT NULL COMMENT '食物id',
    `price`   decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `num`     int            DEFAULT NULL COMMENT '数量',
    `addtime` date           DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`cartid`),
    KEY `usersid` (`usersid`),
    KEY `foodsid` (`foodsid`),
    CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`),
    CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`foodsid`) REFERENCES `foods` (`foodsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart`
    DISABLE KEYS */;
INSERT INTO `cart`
VALUES (1, 1, 1, 25.00, 2, '2023-11-26'),
       (2, 2, 3, 120.00, 1, '2023-11-26'),
       (3, 3, 2, 40.00, 3, '2023-11-26'),
       (4, 4, 5, 50.00, 2, '2023-11-26'),
       (5, 5, 4, 80.00, 1, '2023-11-26'),
       (6, 1, 1, 25.00, 2, '2023-11-26'),
       (7, 2, 3, 120.00, 1, '2023-11-26'),
       (8, 3, 2, 40.00, 3, '2023-11-26'),
       (9, 4, 5, 50.00, 2, '2023-11-26'),
       (10, 5, 4, 80.00, 1, '2023-11-26');
/*!40000 ALTER TABLE `cart`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cate`
--

DROP TABLE IF EXISTS `cate`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cate`
(
    `cateid`   int NOT NULL AUTO_INCREMENT COMMENT '食品类型id，主键',
    `catename` varchar(50) DEFAULT NULL COMMENT '食品类型名称',
    `addtime`  date        DEFAULT NULL COMMENT '添加时间',
    `memo`     varchar(50) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`cateid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cate`
--

LOCK TABLES `cate` WRITE;
/*!40000 ALTER TABLE `cate`
    DISABLE KEYS */;
INSERT INTO `cate`
VALUES (1, '中式菜', '2023-01-01', '中式菜品类型'),
       (2, '西式菜', '2023-02-01', '西式菜品类型'),
       (3, '日本料理', '2023-03-01', '日本料理类型'),
       (4, '韩国料理', '2023-04-01', '韩国料理类型'),
       (5, '甜点', '2023-05-01', '甜点类型'),
       (6, '中式菜', '2023-01-01', '中式菜品类型'),
       (7, '西式菜', '2023-02-01', '西式菜品类型'),
       (8, '日本料理', '2023-03-01', '日本料理类型'),
       (9, '韩国料理', '2023-04-01', '韩国料理类型'),
       (10, '甜点', '2023-05-01', '甜点类型');
/*!40000 ALTER TABLE `cate`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge`
(
    `chargeid` int NOT NULL AUTO_INCREMENT COMMENT '消费id,主键',
    `cno`      varchar(50)    DEFAULT NULL COMMENT '消费单号',
    `ordersid` int            DEFAULT NULL COMMENT '订单id',
    `usersid`  int            DEFAULT NULL COMMENT '用户id',
    `total`    decimal(10, 2) DEFAULT NULL COMMENT '总计',
    `addtime`  date           DEFAULT NULL COMMENT '添加日期',
    PRIMARY KEY (`chargeid`),
    KEY `ordersid` (`ordersid`),
    KEY `usersid` (`usersid`),
    CONSTRAINT `charge_ibfk_1` FOREIGN KEY (`ordersid`) REFERENCES `orders` (`ordersid`),
    CONSTRAINT `charge_ibfk_2` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge`
    DISABLE KEYS */;
INSERT INTO `charge`
VALUES (1, 'CNO001', 1, 1, 50.00, '2023-11-26'),
       (2, 'CNO002', 2, 2, 120.00, '2023-11-26'),
       (3, 'CNO003', 3, 3, 80.00, '2023-11-26'),
       (4, 'CNO004', 4, 4, 25.00, '2023-11-26'),
       (5, 'CNO005', 5, 5, 150.00, '2023-11-26'),
       (6, 'CNO001', 1, 1, 50.00, '2023-11-26'),
       (7, 'CNO002', 2, 2, 120.00, '2023-11-26'),
       (8, 'CNO003', 3, 3, 80.00, '2023-11-26'),
       (9, 'CNO004', 4, 4, 25.00, '2023-11-26'),
       (10, 'CNO005', 5, 5, 150.00, '2023-11-26');
/*!40000 ALTER TABLE `charge`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complains`
--

DROP TABLE IF EXISTS `complains`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complains`
(
    `complainsid` int NOT NULL AUTO_INCREMENT COMMENT '意见反馈id,主键',
    `usersid`     int          DEFAULT NULL COMMENT '用户id',
    `title`       varchar(50)  DEFAULT NULL COMMENT '标题',
    `contents`    varchar(250) DEFAULT NULL COMMENT '反馈内容',
    `addtime`     date         DEFAULT NULL COMMENT '添加时间',
    `status`      varchar(50)  DEFAULT NULL COMMENT '状态',
    `reps`        varchar(50)  DEFAULT NULL COMMENT '管理员回复',
    PRIMARY KEY (`complainsid`),
    KEY `usersid` (`usersid`),
    CONSTRAINT `complains_ibfk_1` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complains`
--

LOCK TABLES `complains` WRITE;
/*!40000 ALTER TABLE `complains`
    DISABLE KEYS */;
INSERT INTO `complains`
VALUES (1, 1, '系统界面体验不佳', '希望能够改进系统界面，提升用户体验。', '2023-01-01', '待处理', NULL),
       (2, 2, '房间卫生问题', '发现房间卫生状况不尽如人意，请尽快处理。', '2023-01-02', '待处理', NULL),
       (3, 3, '预订系统bug', '在预订过程中遇到了一些系统bug，请尽快修复。', '2023-01-03', '待处理', NULL),
       (4, 4, '服务态度不佳', '感觉酒店服务态度有待提高，希望能够引起重视。', '2023-01-04', '待处理', NULL),
       (5, 5, 'Wi-Fi连接问题', '房间内Wi-Fi连接不稳定，希望能够改善网络环境。', '2023-01-05', '待处理', NULL),
       (6, 6, '餐厅食物质量问题', '在餐厅用餐时发现食物质量不尽如人意，希望能够改进。', '2023-01-06', '待处理', NULL),
       (7, 3, '停车场设施老化', '停车场设施比较老化，建议进行维护和更新。', '2023-01-07', '待处理', NULL),
       (8, 5, '房间设施问题', '房间设施有一些问题，希望能够得到解决。', '2023-01-08', '待处理', NULL),
       (9, 2, '会员优惠未生效', '在预订时发现会员优惠未生效，请核实原因并处理。', '2023-01-09', '待处理', NULL),
       (10, 3, '房间空调故障', '房间空调出现故障，希望能够及时修复。', '2023-01-10', '待处理', NULL);
/*!40000 ALTER TABLE `complains`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details`
(
    `detailsid` int NOT NULL AUTO_INCREMENT COMMENT '食品详细ID，主键',
    `ordersid`  int            DEFAULT NULL COMMENT '订单ID',
    `foodsid`   int            DEFAULT NULL COMMENT '食品ID',
    `price`     decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `num`       int            DEFAULT NULL COMMENT '数量',
    `memo`      varchar(50)    DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`detailsid`),
    KEY `ordersid` (`ordersid`),
    KEY `foodsid` (`foodsid`),
    CONSTRAINT `details_ibfk_1` FOREIGN KEY (`ordersid`) REFERENCES `orders` (`ordersid`),
    CONSTRAINT `details_ibfk_2` FOREIGN KEY (`foodsid`) REFERENCES `foods` (`foodsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details`
    DISABLE KEYS */;
INSERT INTO `details`
VALUES (1, 1, 1, 35.00, 2, '加辣'),
       (2, 2, 2, 45.00, 1, '少盐'),
       (3, 3, 3, 50.00, 3, '不加酱油'),
       (4, 4, 4, 40.00, 2, '加香菜'),
       (5, 5, 5, 25.00, 1, '不加奶油'),
       (6, 1, 1, 35.00, 2, '加辣'),
       (7, 2, 2, 45.00, 1, '少盐'),
       (8, 3, 3, 50.00, 3, '不加酱油'),
       (9, 4, 4, 40.00, 2, '加香菜'),
       (10, 5, 5, 25.00, 1, '不加奶油');
/*!40000 ALTER TABLE `details`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employ`
--

DROP TABLE IF EXISTS `employ`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employ`
(
    `employid`   int NOT NULL AUTO_INCREMENT COMMENT '员工ID，主键',
    `eno`        varchar(50)    DEFAULT NULL COMMENT '员工号',
    `password`   varchar(255)   DEFAULT NULL COMMENT '密码',
    `employname` varchar(50)    DEFAULT NULL COMMENT '员工名字',
    `sex`        varchar(10)    DEFAULT NULL COMMENT '性别',
    `birthday`   date           DEFAULT NULL COMMENT '生日',
    `jiguan`     varchar(50)    DEFAULT NULL COMMENT '籍贯',
    `idcard`     varchar(50)    DEFAULT NULL COMMENT '身份证',
    `salary`     decimal(10, 0) DEFAULT NULL COMMENT '薪资',
    `contact`    varchar(50)    DEFAULT NULL COMMENT '联系方式',
    `status`     varchar(50)    DEFAULT NULL COMMENT '状态',
    `addtime`    date           DEFAULT NULL COMMENT '入职时间',
    PRIMARY KEY (`employid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employ`
--

LOCK TABLES `employ` WRITE;
/*!40000 ALTER TABLE `employ`
    DISABLE KEYS */;
INSERT INTO `employ`
VALUES (1, 'E001', '7d77a432abb09a881e298bc0dfa98bd9', 'heweiyuan', '男', '2003-01-01', '咸阳', '110101199001010001',
        5000, '1234567890', '在职', '2023-01-01'),
       (2, 'E002', 'da7d3cb3253803873bd9db24aeee7091', 'lihangkai', '男', '2003-01-02', '杨凌', '310101199202020002',
        6000, '9876543210', '在职', '2023-02-01'),
       (3, 'E003', 'c575a91efbe5195143f12be05cf6b7c7', 'caozhexuan', '男', '2002-08-24', '苏州', '440101199503030003',
        5500, '1112223333', '在职', '2023-03-01'),
       (4, 'E004', '2c6286ae594bf1ba5a1799a577986862', 'liuji1', '男', '2003-06-05', '汉中', '440301198804040004', 7000,
        '4445556666', '在职', '2023-04-01'),
       (5, 'E005', '6c5d956fb7a808478dcb99c4cae2e750', 'laiyufan', '男', '2003-01-09', '苏州', '510101199105050005',
        8000, '7778889999', '在职', '2023-05-01'),
       (8, 'E001', 'emppass1', '员工1', '男', '1990-01-01', '北京', '110101199001010001', 5000, '1234567890', '在职',
        '2023-01-01'),
       (9, 'E002', 'emppass2', '员工2', '女', '1992-02-02', '上海', '310101199202020002', 6000, '9876543210', '在职',
        '2023-02-01'),
       (10, 'E003', 'emppass3', '员工3', '男', '1995-03-03', '广州', '440101199503030003', 5500, '1112223333', '在职',
        '2023-03-01'),
       (11, 'E004', 'emppass4', '员工4', '女', '1988-04-04', '深圳', '440301198804040004', 7000, '4445556666', '在职',
        '2023-04-01'),
       (12, 'E005', 'emppass5', '员工5', '男', '1991-05-05', '成都', '510101199105050005', 8000, '7778889999', '在职',
        '2023-05-01');
/*!40000 ALTER TABLE `employ`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foods`
--

DROP TABLE IF EXISTS `foods`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foods`
(
    `foodsid`   int NOT NULL AUTO_INCREMENT COMMENT '食物ID',
    `foodsname` varchar(50)    DEFAULT NULL COMMENT '食物名称',
    `image`     varchar(255)   DEFAULT NULL COMMENT '图片',
    `cateid`    int            DEFAULT NULL COMMENT '类型ID',
    `price`     decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `recommend` varchar(10)    DEFAULT NULL COMMENT '是否推荐',
    `addtime`   date           DEFAULT NULL COMMENT '上架日期',
    `hits`      int            DEFAULT NULL COMMENT '点击数',
    `sellnum`   int            DEFAULT NULL COMMENT '销量',
    `contents`  text COMMENT '食品介绍',
    PRIMARY KEY (`foodsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foods`
--

LOCK TABLES `foods` WRITE;
/*!40000 ALTER TABLE `foods`
    DISABLE KEYS */;
INSERT INTO `foods`
VALUES (1, '宫保鸡丁', '202312130017055.jpg', 1, 35.00, '是', '2023-01-01', 50, 20,
        '宫保鸡丁选用鸡肉为主料，佐以花生米、辣椒等辅料烹制而成；红而不辣、辣而不猛、香辣味浓、肉质滑脆；其入口鲜辣，鸡肉的鲜嫩可以配合花生的香脆。'),
       (2, '意大利面', '202312130019029.jpg', 2, 25.00, '是', '2023-02-01', 60, 25,
        '意大利面（Pasta），也被称为意粉，是西餐正餐中最接近中国人饮食习惯的面点。意大利面条有很多种类，每种都有自己的名称，长短有差，其空心的种类被部分汉语使用者称为通心粉。'),
       (3, '寿司', '202312130021028.jpg', 3, 50.00, '是', '2023-03-01', 40, 18,
        '寿司（sushi，zushi）是一种日本传统美食。日本古代的寿司，是用盐和米腌制的咸鱼，经演变形成如今的寿司。“寿司”在日本古代写作“鮨”（すし），本意是“咸鱼”。“寿司”这一名称，是“鮨”字的万叶假名写法。'),
       (4, '石锅拌饭', '202312130022014.jpg', 4, 40.00, '是', '2023-04-01', 30, 15,
        '“石锅拌饭”又称“石碗拌饭”，是朝鲜半岛以及中国东北地区黑龙江、吉林、辽宁特有的米饭料理。 是朝鲜半岛三大名菜（平壤冷面，开城汤饭，全州拌饭）之一。'),
       (5, '巧克力蛋糕', '202312130023007.jpg', 5, 25.00, '是', '2023-05-01', 70, 30,
        '蛋糕是一种古老的西点，一般是由烤箱制作的，蛋糕是用鸡蛋、白糖、小麦粉为主要原料。以牛奶、果汁、奶粉、香粉、色拉油、水，起酥油、泡打粉为辅料。经过搅拌、调制、烘烤后制成一种像海绵的点心。'),
       (6, '糖醋里脊', '202312130016017.jpg', 1, 35.00, '是', '2023-01-01', 50, 20,
        '糖醋里脊是中国经典传统名菜之一，该菜品以猪里脊肉为主材，配以面粉、淀粉、醋、番茄酱等佐料，酸甜可口，让人食欲大开；该菜品在湘菜、陕菜、豫菜、浙菜、鲁菜、川菜、淮扬菜、粤菜、闽菜里均有此菜。');
/*!40000 ALTER TABLE `foods`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hist`
--

DROP TABLE IF EXISTS `hist`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hist`
(
    `histid`  int NOT NULL AUTO_INCREMENT COMMENT '历史记录ID，主键',
    `usersid` int         DEFAULT NULL COMMENT '用户ID',
    `foodsid` int         DEFAULT NULL COMMENT '食物ID',
    `num`     varchar(50) DEFAULT NULL COMMENT '数量',
    PRIMARY KEY (`histid`),
    KEY `usersid` (`usersid`),
    KEY `foodsid` (`foodsid`),
    CONSTRAINT `hist_ibfk_1` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`),
    CONSTRAINT `hist_ibfk_2` FOREIGN KEY (`foodsid`) REFERENCES `foods` (`foodsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hist`
--

LOCK TABLES `hist` WRITE;
/*!40000 ALTER TABLE `hist`
    DISABLE KEYS */;
INSERT INTO `hist`
VALUES (1, 1, 1, '2'),
       (2, 2, 3, '1'),
       (3, 3, 2, '3'),
       (4, 4, 5, '2'),
       (5, 5, 4, '1'),
       (6, 1, 1, '2'),
       (7, 2, 3, '1'),
       (8, 3, 2, '3'),
       (9, 4, 5, '2'),
       (10, 5, 4, '1');
/*!40000 ALTER TABLE `hist`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `needs`
--

DROP TABLE IF EXISTS `needs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `needs`
(
    `needsid`      int NOT NULL AUTO_INCREMENT COMMENT '需求ID，主键',
    `roomordersid` int         DEFAULT NULL COMMENT '客房订单ID',
    `roomsid`      int         DEFAULT NULL COMMENT '客房ID',
    `usersid`      int         DEFAULT NULL COMMENT '用户ID',
    `goodsname`    varchar(50) DEFAULT NULL COMMENT '物品名称',
    `addtime`      date        DEFAULT NULL COMMENT '添加时间',
    `status`       varchar(50) DEFAULT NULL COMMENT '状态',
    `reps`         varchar(50) DEFAULT NULL COMMENT '管理员回复',
    PRIMARY KEY (`needsid`),
    KEY `roomordersid` (`roomordersid`),
    KEY `roomsid` (`roomsid`),
    KEY `usersid` (`usersid`),
    CONSTRAINT `needs_ibfk_1` FOREIGN KEY (`roomordersid`) REFERENCES `roomorders` (`roomordersid`),
    CONSTRAINT `needs_ibfk_2` FOREIGN KEY (`roomsid`) REFERENCES `rooms` (`roomsid`),
    CONSTRAINT `needs_ibfk_3` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `needs`
--

LOCK TABLES `needs` WRITE;
/*!40000 ALTER TABLE `needs`
    DISABLE KEYS */;
INSERT INTO `needs`
VALUES (1, 1, 1, 1, '毛巾', '2023-11-26', '已处理', '已为您准备好毛巾，祝您入住愉快'),
       (2, 2, 2, 2, '拖鞋', '2023-11-26', '待处理', NULL),
       (3, 3, 3, 3, '床单', '2023-11-26', '已处理', '床单已更换，祝您入住愉快'),
       (4, 4, 4, 4, '牙刷', '2023-11-26', '待处理', NULL),
       (5, 5, 5, 5, '电吹风', '2023-11-26', '已处理', '电吹风已为您准备好，祝您使用愉快'),
       (6, 1, 1, 1, '毛巾', '2023-11-26', '已处理', '已为您准备好毛巾，祝您入住愉快'),
       (7, 2, 2, 2, '拖鞋', '2023-11-26', '待处理', NULL),
       (8, 3, 3, 3, '床单', '2023-11-26', '已处理', '床单已更换，祝您入住愉快'),
       (9, 4, 4, 4, '牙刷', '2023-11-26', '待处理', NULL),
       (10, 5, 5, 5, '电吹风', '2023-11-26', '已处理', '电吹风已为您准备好，祝您使用愉快');
/*!40000 ALTER TABLE `needs`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `ordersid`  int NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键',
    `ordercode` varchar(50)    DEFAULT NULL COMMENT '订单编号',
    `usersid`   int            DEFAULT NULL COMMENT '用户ID',
    `employid`  int            DEFAULT NULL COMMENT '员工ID',
    `total`     decimal(10, 2) DEFAULT NULL COMMENT '总计',
    `addtime`   date           DEFAULT NULL COMMENT '添加时间',
    `status`    varchar(50)    DEFAULT NULL COMMENT '状态',
    `tabxid`    int            DEFAULT NULL COMMENT '餐桌ID',
    `orderdate` date           DEFAULT NULL COMMENT '预定日期',
    `sectionx`  date           DEFAULT NULL COMMENT '时间段',
    PRIMARY KEY (`ordersid`),
    KEY `usersid` (`usersid`),
    KEY `employid` (`employid`),
    KEY `tabxid` (`tabxid`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`),
    CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`employid`) REFERENCES `employ` (`employid`),
    CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`tabxid`) REFERENCES `tabx` (`tabxid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 60
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders`
    DISABLE KEYS */;
INSERT INTO `orders`
VALUES (1, 'ORD001', 1, 1, 235.34, '2023-12-9 00:00:00', '已确认', 1, '2023-12-11', '2023-12-11'),
       (2, 'ORD002', 2, 2, 250.00, '2023-12-10 00:00:00', '待处理', 2, '2023-12-12', '2023-12-12'),
       (3, 'ORD003', 3, 3, 330.00, '2023-12-10 15:00:00', '已确认', 3, '2023-12-13', '2023-12-13'),
       (4, 'ORD004', 2, 4, 320.00, '2023-12-11 00:00:00', '待处理', 4, '2023-12-14', '2023-12-14'),
       (5, 'ORD005', 5, 5, 260.00, '2023-11-12 20:00:00', '已确认', 5, '2023-12-15', '2023-12-15'),
       (20, 'ORD006', 2, 1, 480.40, '2023-11-12 20:00:00', '待确认', 2, '2023-12-16', '2023-12-16'),
       (21, 'ORD007', 5, 1, 520.20, '2023-11-12 20:00:00', '待确认', 5, '2023-12-17', '2023-12-17'),
       (22, 'ORD008', 3, 2, 371.36, '2023-11-01 00:00:00', '已确认', 3, '2023-11-03', '2023-11-03'),
       (23, 'ORD009', 4, 3, 466.93, '2023-11-02 00:00:00', '待处理', 4, '2023-11-04', '2023-11-04'),
       (24, 'ORD010', 5, 4, 623.59, '2023-11-03 15:00:00', '已确认', 5, '2023-11-05', '2023-11-05'),
       (25, 'ORD011', 2, 1, 267.15, '2023-11-04 00:00:00', '待处理', 2, '2023-11-06', '2023-11-06'),
       (26, 'ORD012', 1, 5, 249.00, '2023-11-05 20:00:00', '已确认', 1, '2023-11-07', '2023-11-07'),
       (27, 'ORD013', 2, 2, 284.60, '2023-11-06 20:00:00', '待处理', 2, '2023-11-08', '2023-11-08'),
       (28, 'ORD014', 3, 3, 145.68, '2023-11-07 20:00:00', '已确认', 3, '2023-11-09', '2023-11-09'),
       (29, 'ORD015', 4, 4, 112.78, '2023-11-08 00:00:00', '待处理', 4, '2023-11-10', '2023-11-10'),
       (30, 'ORD016', 5, 1, 426.87, '2023-11-09 00:00:00', '已确认', 5, '2023-11-11', '2023-11-11'),
       (31, 'ORD017', 1, 2, 496.01, '2023-11-10 00:00:00', '待处理', 1, '2023-11-12', '2023-11-12'),
       (32, 'ORD018', 2, 3, 299.43, '2023-11-11 15:00:00', '已确认', 2, '2023-11-13', '2023-11-13'),
       (33, 'ORD019', 3, 4, 209.13, '2023-11-12 00:00:00', '待处理', 3, '2023-11-14', '2023-11-14'),
       (34, 'ORD020', 4, 5, 147.38, '2023-11-13 20:00:00', '已确认', 4, '2023-11-15', '2023-11-15'),
       (35, 'ORD021', 5, 1, 109.48, '2023-11-14 20:00:00', '待处理', 5, '2023-11-16', '2023-11-16'),
       (36, 'ORD022', 1, 2, 305.27, '2023-11-15 20:00:00', '已确认', 1, '2023-11-17', '2023-11-17'),
       (37, 'ORD023', 2, 3, 597.89, '2023-11-16 00:00:00', '待处理', 2, '2023-11-18', '2023-11-18'),
       (38, 'ORD024', 3, 4, 506.65, '2023-11-17 00:00:00', '已确认', 3, '2023-11-19', '2023-11-19'),
       (39, 'ORD025', 4, 5, 324.59, '2023-11-18 00:00:00', '待处理', 4, '2023-11-20', '2023-11-20'),
       (40, 'ORD026', 5, 1, 252.00, '2023-11-19 15:00:00', '已确认', 5, '2023-11-21', '2023-11-21'),
       (41, 'ORD027', 2, 2, 136.23, '2023-11-20 20:00:00', '待处理', 2, '2023-11-22', '2023-11-22'),
       (42, 'ORD028', 3, 3, 225.13, '2023-11-21 20:00:00', '已确认', 3, '2023-11-23', '2023-11-23'),
       (43, 'ORD029', 4, 4, 216.99, '2023-11-22 00:00:00', '待处理', 4, '2023-11-24', '2023-11-24'),
       (44, 'ORD030', 5, 1, 509.54, '2023-11-23 00:00:00', '已确认', 5, '2023-11-25', '2023-11-25'),
       (45, 'ORD031', 1, 2, 596.75, '2023-11-24 00:00:00', '待处理', 1, '2023-11-26', '2023-11-26'),
       (46, 'ORD032', 2, 3, 155.13, '2023-11-25 15:00:00', '已确认', 2, '2023-11-27', '2023-11-27'),
       (47, 'ORD033', 3, 4, 235.40, '2023-11-26 00:00:00', '待处理', 3, '2023-11-28', '2023-11-28'),
       (48, 'ORD034', 4, 5, 261.61, '2023-11-27 20:00:00', '已确认', 4, '2023-11-29', '2023-11-29'),
       (49, 'ORD035', 5, 1, 151.85, '2023-11-28 20:00:00', '待处理', 5, '2023-11-30', '2023-11-30'),
       (50, 'ORD036', 1, 2, 274.40, '2023-11-29 20:00:00', '已确认', 1, '2023-12-01', '2023-12-01'),
       (51, 'ORD037', 2, 3, 526.46, '2023-11-30 00:00:00', '待处理', 2, '2023-12-02', '2023-12-02'),
       (52, 'ORD038', 3, 4, 529.11, '2023-11-30 15:00:00', '已确认', 3, '2023-12-03', '2023-12-03'),
       (53, 'ORD039', 4, 5, 246.15, '2023-12-01 00:00:00', '待处理', 4, '2023-12-04', '2023-12-04'),
       (54, 'ORD040', 5, 1, 353.42, '2023-12-02 20:00:00', '已确认', 5, '2023-12-05', '2023-12-05'),
       (55, 'ORD041', 2, 2, 228.68, '2023-12-03 20:00:00', '待处理', 2, '2023-12-06', '2023-12-06'),
       (56, 'ORD042', 3, 3, 283.12, '2023-12-04 20:00:00', '已确认', 3, '2023-12-07', '2023-12-07'),
       (57, 'ORD043', 4, 3, 265.00, '2023-11-7 20:00:00', '待确认', 3, '2023-12-08', '2023-12-08'),
       (58, 'ORD044', 4, 2, 531.00, '2023-11-8 20:00:00', '待确认', 3, '2023-12-09', '2023-12-09'),
       (59, 'ORD045', 5, 3, 496.32, '2023-11-9 20:00:00', '待确认', 5, '2023-12-10', '2023-12-10');
/*!40000 ALTER TABLE `orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomorders`
--

DROP TABLE IF EXISTS `roomorders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomorders`
(
    `roomordersid` int NOT NULL AUTO_INCREMENT COMMENT '客房订单ID，主键',
    `ordercode`    varchar(50)    DEFAULT NULL COMMENT '订单号',
    `usersid`      int            DEFAULT NULL COMMENT '用户ID',
    `roomsid`      int            DEFAULT NULL COMMENT '客房ID',
    `addtime`      date           DEFAULT NULL COMMENT '下单日期',
    `thestart`     date           DEFAULT NULL COMMENT '开始日期',
    `theend`       date           DEFAULT NULL COMMENT '结束日期',
    `status`       varchar(50)    DEFAULT NULL COMMENT '状态',
    `memo`         varchar(50)    DEFAULT NULL COMMENT '管理员回复',
    `total`        decimal(10, 2) DEFAULT NULL COMMENT '总计',
    PRIMARY KEY (`roomordersid`),
    KEY `usersid` (`usersid`),
    KEY `roomsid` (`roomsid`),
    CONSTRAINT `roomorders_ibfk_1` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`),
    CONSTRAINT `roomorders_ibfk_2` FOREIGN KEY (`roomsid`) REFERENCES `rooms` (`roomsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomorders`
--

LOCK TABLES `roomorders` WRITE;
/*!40000 ALTER TABLE `roomorders`
    DISABLE KEYS */;
INSERT INTO `roomorders`
VALUES (1, 'RO001', 1, 1, '2023-11-26', '2023-11-27', '2023-11-28', '已确认', '愉快入住', 400.00),
       (2, 'RO002', 2, 2, '2023-11-26', '2023-11-27', '2023-11-28', '待处理', '需要确认', 300.00),
       (3, 'RO003', 3, 3, '2023-11-26', '2023-11-28', '2023-11-29', '已确认', '享受假期', 600.00),
       (4, 'RO004', 4, 4, '2023-11-26', '2023-11-29', '2023-11-30', '待处理', '需进一步确认', 450.00),
       (5, 'RO005', 5, 5, '2023-11-26', '2023-11-30', '2023-12-01', '已确认', '欢迎光临', 800.00),
       (7, '202312112155000', 6, 1, '2023-12-11', '2003-04-03', '2003-05-03', '未入住', '未回复', 900.00),
       (8, '202312112158034', 6, 6, '2023-12-11', '2008-05-02', '2006-02-02', '未入住', '未回复', 1040.00),
       (9, '202312112200028', 6, 4, '2023-12-11', '2023-12-10', '2023-12-11', '未入住', '未回复', 400.00);
/*!40000 ALTER TABLE `roomorders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms`
(
    `roomsid`   int NOT NULL AUTO_INCREMENT COMMENT '客房ID，主键',
    `roomsno`   varchar(50)    DEFAULT NULL COMMENT '客房编号',
    `cate`      varchar(10)    DEFAULT NULL COMMENT '客房类型',
    `image`     varchar(255)   DEFAULT NULL COMMENT '图片',
    `price`     decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `mianji`    varchar(50)    DEFAULT NULL COMMENT '客房面积',
    `chaoxiang` varchar(50)    DEFAULT NULL COMMENT '客房朝向',
    `status`    varchar(50)    DEFAULT NULL COMMENT '客房状态',
    `hits`      int            DEFAULT NULL COMMENT '点击数',
    `contents`  text COMMENT '客房介绍',
    PRIMARY KEY (`roomsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms`
    DISABLE KEYS */;
INSERT INTO `rooms`
VALUES (1, '101', '标准间', 'img_1.jpg', 300.00, '30平米', '南向', '可预订', 100,
        '舒适的标准间，设施齐全，适合商务和旅行。'),
       (2, '102', '单人间', 'img_2.jpg', 200.00, '20平米', '北向', '可预订', 80,
        '独享私密空间的单人间，价格实惠，适合独自旅行'),
       (3, '103', '套房', 'img_3.jpg', 500.00, '50平米', '东向', '可预订', 120,
        '豪华套房，空间宽敞，设有独立客厅，享受尊贵服务。'),
       (4, '104', '豪华间', 'img_4.jpg', 400.00, '40平米', '西向', '可预订', 150,
        '豪华装修的客房，设备一流，提供奢华体验。'),
       (5, '105', '家庭房', 'img_5.jpg', 600.00, '60平米', '南向', '可预订', 200,
        '宽敞的家庭房，适合一家人入住，温馨舒适。'),
       (6, '106', '情侣间', 'img_6.jpg', 520.00, '40平米', '北向', '可预订', 200,
        '浪漫的情侣间，精心布置，营造浪漫氛围。'),
       (9, '107', '超豪华间', '202312130000031.jpg', 1000.00, '50平米', '东向', '可预订', 120,
        '极致奢华的住宿体验，享受尊贵服务。'),
       (10, '108', '亲子间', '202312130002028.jpg', 600.00, '40平米', '西向', '可预订', 150,
        '适合亲子入住的房间，设有儿童友好设施。'),
       (11, '200', '别墅', '202312130003004.jpg', 600.00, '900平米', '南向', '可预订', 200,
        '豪华别墅，私密独立空间，享受尊贵的度假体验。');
/*!40000 ALTER TABLE `rooms`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabx`
--

DROP TABLE IF EXISTS `tabx`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tabx`
(
    `tabxid`   int NOT NULL AUTO_INCREMENT COMMENT '餐桌ID，主键',
    `tabxname` varchar(50)  DEFAULT NULL COMMENT '餐桌名称',
    `image`    varchar(255) DEFAULT NULL COMMENT '餐桌图片',
    `num`      int          DEFAULT NULL COMMENT '用餐人数',
    `cate`     varchar(10)  DEFAULT NULL COMMENT '餐桌类型',
    `place`    varchar(50)  DEFAULT NULL COMMENT '餐桌位置',
    `addtime`  date         DEFAULT NULL COMMENT '创建时间',
    `contents` text COMMENT '餐桌描述',
    PRIMARY KEY (`tabxid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabx`
--

LOCK TABLES `tabx` WRITE;
/*!40000 ALTER TABLE `tabx`
    DISABLE KEYS */;
INSERT INTO `tabx`
VALUES (1, 'A1', '202312130006018.jpg', 4, '当前：普通', '当前：窗边', '2023-01-01', '这是A1餐桌的描述'),
       (2, 'A2', '202312130006028.jpg', 6, '当前：豪华', '当前：中间', '2023-02-01', '这是A2餐桌的描述'),
       (3, 'B1', '202312130006038.jpg', 2, '当前：普通', '当前：角落', '2023-03-01', '这是B1餐桌的描述'),
       (4, 'B2', '202312130006047.jpg', 8, '当前：豪华', '当前：窗边', '2023-04-01', '这是B2餐桌的描述'),
       (5, 'C1', '202312130006059.jpg', 4, '当前：普通', '当前：中间', '2023-05-01', '这是C1餐桌的描述'),
       (6, 'A1', '202312130007010.jpg', 4, '当前：普通', '当前：窗边', '2023-01-01', '这是A1餐桌的描述');
/*!40000 ALTER TABLE `tabx`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic`
(
    `topicid`  int NOT NULL AUTO_INCREMENT COMMENT '用餐评价ID，主键',
    `ordersid` int            DEFAULT NULL COMMENT '订单ID',
    `usersid`  int            DEFAULT NULL COMMENT '用户ID',
    `foodsid`  int            DEFAULT NULL COMMENT '食品ID',
    `num`      decimal(10, 2) DEFAULT NULL COMMENT '评分',
    `contents` varchar(50)    DEFAULT NULL COMMENT '内容',
    `addtime`  date           DEFAULT NULL COMMENT '日期',
    PRIMARY KEY (`topicid`),
    KEY `ordersid` (`ordersid`),
    KEY `usersid` (`usersid`),
    KEY `foodsid` (`foodsid`),
    CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`ordersid`) REFERENCES `orders` (`ordersid`),
    CONSTRAINT `topic_ibfk_2` FOREIGN KEY (`usersid`) REFERENCES `users` (`usersid`),
    CONSTRAINT `topic_ibfk_3` FOREIGN KEY (`foodsid`) REFERENCES `foods` (`foodsid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic`
    DISABLE KEYS */;
INSERT INTO `topic`
VALUES (1, 1, 6, 1, 5.50, '菜品很美味，服务也很好', '2023-01-11'),
       (2, 2, 6, 2, 3.80, '环境不错，食物一般', '2023-02-01'),
       (3, 3, 6, 3, 4.20, '寿司非常新鲜，味道好', '2023-03-01'),
       (4, 4, 6, 4, 4.00, '石锅拌饭很香，量也足', '2023-04-01'),
       (6, 1, 6, 1, 4.50, '菜品很美味，服务也很好', '2023-01-01'),
       (7, 2, 6, 2, 3.80, '环境不错，食物一般', '2023-02-01'),
       (8, 3, 6, 3, 4.20, '寿司非常新鲜，味道好', '2023-03-01'),
       (9, 4, 6, 4, 4.00, '石锅拌饭很香，量也足', '2023-04-01'),
       (10, 5, 6, 5, 4.80, '巧克力蛋糕口感极佳', '2023-05-01');
/*!40000 ALTER TABLE `topic`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `usersid`  int NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
    `username` varchar(50) DEFAULT NULL COMMENT '用户名',
    `password` varchar(50) DEFAULT NULL COMMENT '密码',
    `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
    `sex`      varchar(10) DEFAULT NULL COMMENT '性别',
    `birthday` date        DEFAULT NULL COMMENT '生日',
    `contact`  varchar(50) DEFAULT NULL COMMENT '联系方式',
    `regdate`  date        DEFAULT NULL COMMENT '注册日期',
    PRIMARY KEY (`usersid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
    DISABLE KEYS */;
INSERT INTO `users`
VALUES (1, 'lihangkai', 'a967d516497603ecc84fe79f456036be', '李航凯', '男', '1995-01-26', '1234567890', '2023-01-01'),
       (2, 'caozhexuan', '96c028cc60fef9b633e7cf9744387db9', '曹哲轩', '男', '2003-04-03', '13809146261', '2023-02-01'),
       (3, 'liuji1', 'ea7975a812ab7887cb22d5eff8923f1f', '刘吉', '男', '2003-05-28', '1112223333', '2023-03-01'),
       (4, 'heweiyuan', '7d77a432abb09a881e298bc0dfa98bd9', '何维远', '男', '2003-11-13', '4445556666', '2023-04-01'),
       (5, 'laiyufan', '5002f24e72da4603e51d9bbc4e7bcd6a', '赖宇凡', '男', '2003-05-08', '7778889999', '2023-05-01'),
       (6, 'ailaries', '129ca3252419bfdf739569e78e04585b', '曹哲轩', '男', '2003-04-03', '13809146261', '2023-11-07'),
       (8, 'user1', 'userpass1', '用户1', '男', '1995-01-01', '1234567890', '2023-01-01'),
       (9, 'user2', 'userpass2', '用户2', '女', '1990-02-02', '9876543210', '2023-02-01'),
       (10, 'user3', 'userpass3', '用户3', '男', '1992-03-03', '1112223333', '2023-03-01'),
       (11, 'user4', 'userpass4', '用户4', '女', '1988-04-04', '4445556666', '2023-04-01'),
       (12, 'user5', 'userpass5', '用户5', '男', '1991-05-05', '7778889999', '2023-05-01');
/*!40000 ALTER TABLE `users`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13  7:50:58
