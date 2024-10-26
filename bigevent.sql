/*
 Navicat Premium Data Transfer

 Source Server         : Test
 Source Server Type    : MySQL
 Source Server Version : 80400
 Source Host           : localhost:3306
 Source Schema         : bigevent

 Target Server Type    : MySQL
 Target Server Version : 80400
 File Encoding         : 65001

 Date: 26/10/2024 16:33:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章内容',
  `cover_img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章封面',
  `state` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '草稿' COMMENT '文章状态: 只能是[已发布] 或者 [草稿]',
  `category_id` int UNSIGNED NULL DEFAULT NULL COMMENT '文章分类ID',
  `create_user` int UNSIGNED NOT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_article_category`(`category_id`) USING BTREE,
  INDEX `fk_article_user`(`create_user`) USING BTREE,
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_article_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (8, '我校赴苏州市相关企业开展专业改造调研工作', '<p>	<span style=\"color: rgb(102, 102, 102);\">10月15日，我校副院长朱竹青率领由教务处、信息工程学院、电力工程学院以及化学与生物工程学院共8人组成的调研团队，前往苏州市吴江区，就专业改造事宜进行深入调研。</span></p><p>	<span style=\"color: rgb(102, 102, 102);\">当日上午，朱竹青一行抵达乐聚（苏州）机器人技术有限公司，受到了公司总经理王松的热情接待。在王松的陪同下，调研团队参观了该公司自主研发的双足仿人形机器人，随后与公司相关专家进行了座谈交流。会上，王松详细介绍了乐聚公司在机器人及人工智能领域的研究成果，并针对我校的专业改造提出了诸多宝贵建议。他表达了与我校携手合作，共同推动专业建设的良好愿景。</span></p><p><span class=\"ql-cursor\">﻿</span>	下午，东太湖度假区管理办招商局局长万能陪同朱竹青一行，先后参观了苏州中瑞智创三维科技股份有限公司和江苏亿友慧云软件股份有限公司。在中瑞科技，教育事业部总监刘康详细介绍了公司在3D打印设备研发、3D打印应用软件系统等方面的发展成就。他表示，中瑞科技已与多所高校开展产教融合合作，并期待与我校建立深入联系，为我校专业改造贡献力量。亿友慧云负责人易晶晶介绍了公司作为苏州吴江区大数据平台领军企业的优势，特别是在云计算、大数据、人工智能等领域的研究成果。她表示，亿友慧云愿意为我校在新时代创新人才培养方面提供支持。</p><p>	随后，朱竹青一行还参观了中国工业互联网研究院（江苏分院）。在调研交流活动中，朱竹青向对方详细介绍了我校的发展历程及现状，并重点就我校的专业建设和创新人才培养提出了未来展望。他表示，通过此次调研，对我校专业改造的思路和存在的困难有了更深入的了解。希望我校相关学院在专业改造过程中，能够积极探索校企协同、产教融合的新工科人才培养模式，切实做好专业改造升级工作，推动我校教育事业的发展。</p>', '', '已发布', 3, 5, '2024-10-21 14:40:13', '2024-10-21 14:40:13');
INSERT INTO `article` VALUES (9, '国足客场迎战巴林', '<p>本次比赛备受期待</p>', '', '已发布', 4, 5, '2024-10-26 13:03:01', '2024-10-26 13:03:01');
INSERT INTO `article` VALUES (10, '巡视公告', '<p>	<span style=\"color: rgb(102, 102, 102);\">根据巡视工作职责，巡视组主要受理反映南京师范大学领导班子及其成员、领导班子成员以外的其他负责同志、下一级主要负责人和相关重点岗位干部问题的来信、来电、来访，重点是关于违反政治纪律、组织纪律、廉洁纪律、群众纪律、工作纪律、生活纪律等方面的举报和反映。其他不属于巡视受理范围的信访问题，将按规定由被巡视单位和有关部门认真处理。</span></p>', '', '已发布', 3, 5, '2024-10-26 14:28:06', '2024-10-26 14:28:06');
INSERT INTO `article` VALUES (11, '国足对战马来西亚胜利', '<p>上周四，在特斯拉的“We，Robot”的新品发布会上，人形机器人「擎天柱」（Optimus）的意外亮相令许多人都深感惊艳：不仅能跳舞、调制饮料，还能与现场嘉宾击掌互动、玩“石头剪刀布”……按理来说，这本该是特斯拉展示 Optimus 机器人进展的一次绝佳机会。</p><p><br></p><p>然而，近日有知情人士指出：那天 Optimus 所展现出的令人印象深刻的表现，其实都是有人在远程操控的，它“并没有展示出来的那么智能”——此消息一出，无数网友大呼“被骗”。</p><p><br></p><p><br></p>', '', '已发布', 4, 5, '2024-10-26 14:28:13', '2024-10-26 14:28:13');
INSERT INTO `article` VALUES (12, '纪委办公室召开二级党组织纪检委员工作会议', '<p>	<span style=\"color: rgb(102, 102, 102);\">10月16日下午，纪委办公室在图书馆801会议室召开二级党组织纪检委员工作会议。纪委书记张晓红到会并讲话，纪委办公室主任万年青主持会议，各党总支纪检委员参会。</span></p>', '', '已发布', 3, 5, '2024-10-26 14:28:22', '2024-10-26 14:28:22');
INSERT INTO `article` VALUES (13, '阿里云为高校学生打造的“一站式学成”阵地', '<p>	<span style=\"color: rgb(51, 51, 51);\">中国始终是“全球南方”的一员，同广大发展中国家团结合作是中国对外关系不可动摇的根基。我们提出“全球南方”开放包容合作倡议，宣布支持“全球南方”合作八项举措，向160多个国家提供发展援助，同150多个国家携手共建“一带一路”，同100多个国家和联合国等多个国际组织推进全球发展倡议合作，投入并动员近200亿美元发展资金，开展了1100多个项目，以实际行动为“全球南方”发展振兴注入强劲动力。</span></p>', '', '已发布', 3, 5, '2024-10-26 14:29:24', '2024-10-26 14:29:24');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
  `category_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类别名',
  `create_user` int UNSIGNED NOT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_user`(`create_user`) USING BTREE,
  CONSTRAINT `fk_category_user` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '体育', '体育竞技', 4, '2024-10-17 08:57:41', '2024-10-17 08:57:41');
INSERT INTO `category` VALUES (2, '时事', '时事新闻', 4, '2024-10-17 08:57:55', '2024-10-17 08:57:55');
INSERT INTO `category` VALUES (3, '新闻', '新闻时事', 5, '2024-10-21 14:38:42', '2024-10-21 14:38:42');
INSERT INTO `category` VALUES (4, '体育', '体育竞技', 5, '2024-10-26 13:02:30', '2024-10-26 13:02:30');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '邮箱',
  `user_pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'sa', '123', '丽萨', '23232323@qq.com', '', '2024-08-24 19:25:14', '2024-08-24 19:25:20');
INSERT INTO `user` VALUES (2, 'sdsdsds1', 'bbb8aae57c104cda40c93843ad5e6db8', '', '', '', '2024-10-16 18:51:59', '2024-10-16 18:51:59');
INSERT INTO `user` VALUES (3, 'huaxing', '09230542', '星', '3993762567@qq.com', '', '2024-10-15 19:08:18', '2024-10-15 19:08:41');
INSERT INTO `user` VALUES (4, 'wwwww', 'd785c99d298a4e9e6e13fe99e602ef42', 'huahua', 'huaxingtang726@gmail.com', '', '2024-10-17 08:52:06', '2024-10-21 14:33:41');
INSERT INTO `user` VALUES (5, 'administrator', '0525ba1a5704e530756bd99d280ff2a5', '管理员', '3093652682@qq.com', '', '2024-10-21 14:37:25', '2024-10-26 13:03:19');
INSERT INTO `user` VALUES (6, 'qqqqq', '343b1c4a3ea721b2d640fc8700db0f36', '', '', '', '2024-10-26 14:10:21', '2024-10-26 14:10:21');

SET FOREIGN_KEY_CHECKS = 1;
