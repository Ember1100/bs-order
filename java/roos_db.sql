/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : roos_db

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 20/10/2022 20:38:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '234', 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '饮料', '好喝d');
INSERT INTO `category` VALUES (3, '饭类', '好吃');
INSERT INTO `category` VALUES (4, '汤类', '好香');
INSERT INTO `category` VALUES (6, '肉类', '好吃的肉');
INSERT INTO `category` VALUES (7, '鱼类', '');
INSERT INTO `category` VALUES (8, '素菜', '');
INSERT INTO `category` VALUES (10, '其他', '未被分类的其他种类');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `f_id` int NOT NULL AUTO_INCREMENT,
  `f_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_price` int NULL DEFAULT NULL,
  `f_img` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_c_id` int NULL DEFAULT NULL,
  `f_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_amount` int UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `f_c_id`(`f_c_id` ASC) USING BTREE,
  CONSTRAINT `f_c_id` FOREIGN KEY (`f_c_id`) REFERENCES `category` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (30, '哇哈哈AD钙奶', 4, 'images/food/adgainai.jpg', 1, '好喝', 100);
INSERT INTO `food` VALUES (31, '青岛啤酒', 4, 'images/food/5DB139952068A85CE0910175AF236A2C.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (32, '红牛', 5, 'images/food/88890E607E406968FDBF03DADC324A48.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (33, '脉动', 4, 'images/food/77EC5E6CEA9AE39A0B57B4D8CC9BC85F.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (34, '果粒橙', 3, 'images/food/D9E74D43E818457305428A2C3EEEEB6C.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (35, '王老吉', 2, 'images/food/13C00455D5B58792B2DF16AA10C3E484.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (36, '冰糖雪梨', 3, 'images/food/0A3F1788F90C353E09D2B75AA89E3E8B.jpg', 1, '好喝，够劲', 200);
INSERT INTO `food` VALUES (38, '红烧茄子', 6, 'images/food/fishqie.jpg', 8, '好吃', 40);
INSERT INTO `food` VALUES (39, '梅菜扣肉', 12, 'images/food/food4.jpg', 6, '好吃', 70);
INSERT INTO `food` VALUES (40, '炒饭', 7, 'images/food/food3.jpg', 3, '好吃', 76);
INSERT INTO `food` VALUES (41, '可乐', 3, 'images/food/food1.jpg', 1, '好喝', 80);
INSERT INTO `food` VALUES (42, '水煮鱼', 20, 'images/food/waterFish.jpg', 7, '好吃', 100);
INSERT INTO `food` VALUES (43, '饺子', 6, 'images/food/jiaozi.jpg', 10, '好吃', 20);
INSERT INTO `food` VALUES (45, '鱼香肉丝', 15, 'images/food/AE077E0FA14E494C925A9D5410690C0B-123.jpg', 6, 'good', 500);
INSERT INTO `food` VALUES (48, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 100);
INSERT INTO `food` VALUES (51, '韩式酱汤', 15, 'images/food/3AC9182C4C554067914CE8D80F46B0CA-xiaxia.jpg', 4, '大虾、角瓜、土豆、元葱、冻豆腐、淘米水、韩式大酱、鸡精、味道。', 50);
INSERT INTO `food` VALUES (69, '冰红茶', 3, 'images/food/binghongcha.jpg', 1, '好喝', 100);
INSERT INTO `food` VALUES (70, '东鹏特饮', 5, 'images/food/dongpengteyin.jpg', 1, '好喝', 100);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_u_id` int NOT NULL,
  `m_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_f_id` int NOT NULL,
  `m_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`m_id`) USING BTREE,
  INDEX `m_f_id`(`m_f_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '老朱', '2019-12-01 11:01:49', 3, 21);
INSERT INTO `message` VALUES (1, '真香', '2019-12-08 22:09:51', 1, 22);
INSERT INTO `message` VALUES (7, '味道还行', '2019-12-16 09:40:19', 1, 23);
INSERT INTO `message` VALUES (1, '1楼', '2019-12-16 10:13:25', 2, 24);
INSERT INTO `message` VALUES (1, '承包空屏', '2019-12-16 10:14:05', 14, 25);

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `od_id` int NOT NULL AUTO_INCREMENT,
  `od_o_id` int NULL DEFAULT NULL,
  `od_f_id` int NULL DEFAULT NULL,
  `od_f_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `union`(`od_id` ASC, `od_o_id` ASC, `od_f_id` ASC, `od_f_amount` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `o_id` int NOT NULL AUTO_INCREMENT,
  `o_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_payment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_u_id` int NULL DEFAULT NULL,
  `o_price` decimal(10, 2) NULL DEFAULT NULL,
  `o_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`o_id`) USING BTREE,
  INDEX `o_u_id`(`o_u_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (82, '2022-10-05 17:37:33', '微信支付', 2, 87.00, '外卖小哥送餐中', '#可乐-2#韩式酱汤-3#梅菜扣肉-3', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (83, '2022-10-05 17:44:49', '微信支付', 2, 72.00, '外卖小哥送餐中', '#可乐-2#雪碧-3#可乐-2#雪碧-12', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (84, '2022-10-05 17:47:05', '微信支付', 2, 213.00, '外卖小哥送餐中', '#可乐-8#可乐-3#韩式酱汤-4#水煮鱼-6', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (85, '2022-10-05 19:02:43', '微信支付', 2, 61.00, '饭店接单中', '#青岛啤酒-1#果粒橙-1#红烧茄子-3#梅菜扣肉-3', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (86, '2022-10-19 21:58:54', '微信支付', 2, 24.00, '饭店接单中', '#果粒橙-3#红牛-3', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (87, '2022-10-19 23:51:49', '微信支付', 2, 24.00, '外卖小哥送餐中', '#果粒橙-4#青岛啤酒-3', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (88, '2022-10-20 00:20:13', '支付宝', 2, 96.00, '外卖小哥送餐中', '#饺子-3#红烧茄子-3#鱼香肉丝-4', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (89, '2022-10-20 20:04:00', '微信支付', 10, 104.00, '用户已接收', '#韩式酱汤-4#炒饭-4#哇哈哈AD钙奶-4', '广东省揭阳市*********');
INSERT INTO `orders` VALUES (90, '2022-10-20 20:21:56', '支付宝', 10, 48.00, '饭店接单中', '#青岛啤酒-4#红牛-4#哇哈哈AD钙奶-3', '广东省揭阳市*********');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'teskinfly', '12315', '13421193765', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `user` VALUES (2, 'lqk', '123', '131511512', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `user` VALUES (10, 'laowang', '123', '1321532153', '广东省揭阳市*********');

SET FOREIGN_KEY_CHECKS = 1;
