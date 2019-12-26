/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : rm-bp188fb70hknd9gi2o.mysql.rds.aliyuncs.com:3306
 Source Schema         : qualitydb

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : 65001

 Date: 12/12/2019 22:00:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asset
-- ----------------------------
DROP TABLE IF EXISTS `asset`;
CREATE TABLE `asset`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` tinyint(4) NOT NULL COMMENT '资产类型：10-手机 20-电脑 30-配件',
  `brand` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `model` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `version` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统',
  `size` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '屏幕尺寸',
  `amount` smallint(9) NULL DEFAULT NULL COMMENT '总数量',
  `in_use` smallint(9) NULL DEFAULT NULL COMMENT '已领用数量',
  `borrower` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领用人',
  `borrow_tm` datetime(0) NULL DEFAULT NULL COMMENT '领用时间',
  `status` smallint(9) NULL DEFAULT NULL COMMENT '状态：10-闲置 20-领用 30-报修 40-作废',
  `imei` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机imei',
  `asset_number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司资产编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资产表';

-- ----------------------------
-- Table structure for asset_log
-- ----------------------------
DROP TABLE IF EXISTS `asset_log`;
CREATE TABLE `asset_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `asset_id` int(11) NOT NULL COMMENT '资产id',
  `status` smallint(9) NULL DEFAULT NULL COMMENT '资产状态',
  `borrower` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领用人',
  `borrow_tm` datetime(0) NULL DEFAULT NULL COMMENT '领用时间',
  `return_tm` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `remark` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `modifier` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资产操作记录';

-- ----------------------------
-- Table structure for auto_test
-- ----------------------------
DROP TABLE IF EXISTS `auto_test`;
CREATE TABLE `auto_test`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试类型',
  `test_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试名称',
  `suite_id` int(11) NULL DEFAULT NULL COMMENT '测试套件id',
  `execute_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行类型',
  `execute_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行状态',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `creat_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `start_tm` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_tm` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `all_num` int(20) NULL DEFAULT NULL COMMENT '总用用例数',
  `pass_num` int(20) NULL DEFAULT NULL COMMENT '成功用例数',
  `skipt_num` int(20) NULL DEFAULT NULL COMMENT '跳过用例数',
  `fail_num` int(20) NULL DEFAULT NULL COMMENT '失败用例数',
  `fail_methods` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '失败用例',
  `tester` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人',
  `report_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试报告名称',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `cc_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '抄送报告邮箱',
  `receive_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收报告邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_id`(`id`, `test_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 239 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自动化测试表';

-- ----------------------------
-- Table structure for auto_test_case_num
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_case_num`;
CREATE TABLE `auto_test_case_num`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `test_type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `num` int(11) NULL DEFAULT NULL COMMENT '用例数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '各项目用例数表';

-- ----------------------------
-- Table structure for auto_test_execution_times
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_execution_times`;
CREATE TABLE `auto_test_execution_times`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `test_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `times` int(11) NULL DEFAULT NULL COMMENT '执行次数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '各项目执行次数表';

-- ----------------------------
-- Table structure for auto_test_fail_case_num
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_fail_case_num`;
CREATE TABLE `auto_test_fail_case_num`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `fail_num` int(11) NULL DEFAULT NULL COMMENT '失败用例总数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '总失败次数表';

-- ----------------------------
-- Table structure for auto_test_fail_method_sort
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_fail_method_sort`;
CREATE TABLE `auto_test_fail_method_sort`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `no` int(5) NULL DEFAULT NULL COMMENT '排序序号',
  `test_type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `fail_method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败方法',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `tester_code` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例维护人工号',
  `tester_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例维护人名称',
  `num` int(5) NULL DEFAULT NULL COMMENT '失败次数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '各项目失败方法排行表';

-- ----------------------------
-- Table structure for auto_test_fail_reason_sort
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_fail_reason_sort`;
CREATE TABLE `auto_test_fail_reason_sort`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `test_type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `fail_reason` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  `num` int(11) NULL DEFAULT NULL COMMENT '失败次数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '失败原因排行表';

-- ----------------------------
-- Table structure for auto_test_fail_result
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_fail_result`;
CREATE TABLE `auto_test_fail_result`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auto_test_id` int(11) NULL DEFAULT NULL COMMENT '自动化测试套件id',
  `test_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试名称',
  `test_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试类型',
  `fail_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败方法',
  `fail_reason` int(255) NULL DEFAULT NULL COMMENT '失败原因',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注说明',
  `tester_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例负责人',
  `tester_name` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试用例维护人',
  `update_tm` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1010 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自动化失败用例信息表';

-- ----------------------------
-- Table structure for auto_test_new_case_num
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_new_case_num`;
CREATE TABLE `auto_test_new_case_num`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `today_num` int(11) NULL DEFAULT NULL COMMENT '今日用例数',
  `before_num` int(11) NULL DEFAULT NULL COMMENT '一周前用例数',
  `new_case_num` int(11) NULL DEFAULT NULL COMMENT '新增用例数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新增用例表';

-- ----------------------------
-- Table structure for auto_test_passing_rate
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_passing_rate`;
CREATE TABLE `auto_test_passing_rate`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `test_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `rate` int(4) NULL DEFAULT NULL COMMENT '通过率',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '各项目通过率统计表';

-- ----------------------------
-- Table structure for auto_test_statistics
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_statistics`;
CREATE TABLE `auto_test_statistics`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` date NOT NULL COMMENT '日期',
  `execution_times` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行次数',
  `case_num` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例数',
  `passing_rate` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通过率',
  `reason_sort` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因排行',
  `method_sort` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败方法排行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自动化测试执行统计';

-- ----------------------------
-- Table structure for auto_test_suite
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_suite`;
CREATE TABLE `auto_test_suite`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套件项目类型',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套件名称',
  `suite` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套件用例',
  `status` int(11) NOT NULL COMMENT '套件状态 0：无效，1：有效',
  `creat_tm` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_tm` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试套件表';

-- ----------------------------
-- Table structure for auto_test_today_fix_rate
-- ----------------------------
DROP TABLE IF EXISTS `auto_test_today_fix_rate`;
CREATE TABLE `auto_test_today_fix_rate`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `biz_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次id',
  `no` int(2) NULL DEFAULT NULL COMMENT '排序序号',
  `test_type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试项目',
  `tester_code` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败用例维护人工号',
  `tester_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败用例维护人姓名',
  `rate` int(4) NULL DEFAULT NULL COMMENT '项目失败用例维护比率',
  `fix_num` int(11) NULL DEFAULT NULL COMMENT '维护用例数',
  `fail_num` int(11) NULL DEFAULT NULL COMMENT '失败用例数',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '今日用例维护进度表';

-- ----------------------------
-- Table structure for base_case
-- ----------------------------
DROP TABLE IF EXISTS `base_case`;
CREATE TABLE `base_case`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属产品',
  `module` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属模块',
  `require` int(11) NULL DEFAULT NULL COMMENT '需求',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例标签',
  `precondition` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '前置条件',
  `pri` tinyint(3) UNSIGNED NOT NULL DEFAULT 3 COMMENT '优先级',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用例类型',
  `status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用例状态',
  `last_edited_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后编辑人',
  `last_edited_date` datetime(0) NULL DEFAULT NULL COMMENT '最后编辑日期',
  `version` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '用例版本',
  `parent_case` int(11) NULL DEFAULT NULL COMMENT '父用例',
  `family` tinyint(4) NULL DEFAULT NULL COMMENT '用例分类   0 基础用例  1  项目用例',
  `execute_status` tinyint(4) NULL DEFAULT 0 COMMENT '执行状态 0 未开始  1 通过 2失败',
  `edited` tinyint(4) NULL DEFAULT NULL COMMENT '0 未编辑  1 已编辑',
  `pushed` tinyint(4) NULL DEFAULT NULL COMMENT '0 未推送  1 已推送',
  `deleted` tinyint(4) NULL DEFAULT NULL COMMENT '0  未删除  1 已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_product`(`product`) USING BTREE,
  INDEX `idx_module`(`module`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 412 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基础用例表';

-- ----------------------------
-- Table structure for base_case_label
-- ----------------------------
DROP TABLE IF EXISTS `base_case_label`;
CREATE TABLE `base_case_label`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_id` int(11) NULL DEFAULT NULL COMMENT '用例',
  `label_id` int(11) NULL DEFAULT NULL COMMENT '标签',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creator_tm` time(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1701 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用例标签';

-- ----------------------------
-- Table structure for base_case_step
-- ----------------------------
DROP TABLE IF EXISTS `base_case_step`;
CREATE TABLE `base_case_step`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用例id',
  `step_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '步骤id',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '步骤描述',
  `expect_db` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期db',
  `expect_ui` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期ui',
  `expect_response` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期response',
  `expect_other` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期other',
  `execute_status` tinyint(4) NULL DEFAULT NULL COMMENT '执行状态 0 未开始，1 通过,  2 失败',
  `family` tinyint(4) NULL DEFAULT NULL COMMENT '用例分类   0 基础用例  1  项目用例',
  `edited` tinyint(4) NULL DEFAULT 0 COMMENT '执行状态 0 未开始  1 通过 2失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `case`(`case_id`) USING BTREE,
  INDEX `version`(`step_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1070 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用例步骤';

-- ----------------------------
-- Table structure for bbs
-- ----------------------------
DROP TABLE IF EXISTS `bbs`;
CREATE TABLE `bbs`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '类型：10-文章，20-短想法，30-打卡，40-记一笔，50-周刊',
  `title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '题目',
  `author_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者工号',
  `author_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者姓名',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `like` smallint(6) UNSIGNED NULL DEFAULT 0 COMMENT '点赞数',
  `origin` tinyint(4) NULL DEFAULT NULL COMMENT '来源：0-原创，1-转载',
  `files` varchar(20480) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `week` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打卡周',
  `note_type` tinyint(4) NULL DEFAULT NULL COMMENT '记一笔分类：0-业务经验，1-测试经验，2-工具技巧，3-其他',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'bbs';

-- ----------------------------
-- Table structure for bbs_log
-- ----------------------------
DROP TABLE IF EXISTS `bbs_log`;
CREATE TABLE `bbs_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工工号',
  `bbs_id` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  `operation` smallint(9) NULL DEFAULT NULL COMMENT '操作(1：新增,2：编辑,3：点赞,4：取消点赞,5：删除)',
  `ins_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `upd_tm` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 228 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for bbs_relate
-- ----------------------------
DROP TABLE IF EXISTS `bbs_relate`;
CREATE TABLE `bbs_relate`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bbs_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `relate_id` int(11) NULL DEFAULT NULL COMMENT '关联文章id',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for bbs_tag
-- ----------------------------
DROP TABLE IF EXISTS `bbs_tag`;
CREATE TABLE `bbs_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bbs_id` int(11) NULL DEFAULT NULL,
  `tag` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for bbs_weekly
-- ----------------------------
DROP TABLE IF EXISTS `bbs_weekly`;
CREATE TABLE `bbs_weekly`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `weekly_id` int(11) NULL DEFAULT NULL COMMENT '周刊id',
  `bbs_id` int(11) NULL DEFAULT NULL COMMENT '记一笔id',
  `note_type` tinyint(4) NULL DEFAULT NULL COMMENT '记一笔的分类',
  `week` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第几周',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for case_repair_info
-- ----------------------------
DROP TABLE IF EXISTS `case_repair_info`;
CREATE TABLE `case_repair_info`  (
  `id` int(11) NOT NULL,
  `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_owner` int(5) NULL DEFAULT NULL,
  `product_module` int(11) NULL DEFAULT NULL,
  `product_module_owner` int(5) NULL DEFAULT NULL,
  `modifier` int(5) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'base_case_repair';

-- ----------------------------
-- Table structure for core_nodes
-- ----------------------------
DROP TABLE IF EXISTS `core_nodes`;
CREATE TABLE `core_nodes`  (
  `id` int(11) NOT NULL,
  `project_node` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `demand_need` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for daily_report
-- ----------------------------
DROP TABLE IF EXISTS `daily_report`;
CREATE TABLE `daily_report`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(20) NULL DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `report_date` datetime(0) NULL DEFAULT NULL COMMENT '日报日期',
  `project_stage` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所处项目阶段',
  `task_progress` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务进度',
  `bug_summary` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'bug汇总',
  `project_risk` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目风险',
  `creator` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_project_report_date`(`project_id`, `report_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日报';

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `dict_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字所属组',
  `dict_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字code',
  `dict_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字value',
  `order` int(64) NULL DEFAULT NULL COMMENT '排序',
  `is_defult` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认关键字，N=否，Y=是',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'enable' COMMENT '字段状态，unable=失效，enable=生效',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`, `dict_group`, `dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典';

-- ----------------------------
-- Table structure for dingding_notify
-- ----------------------------
DROP TABLE IF EXISTS `dingding_notify`;
CREATE TABLE `dingding_notify`  (
  `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `chatid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` tinyint(4) NOT NULL COMMENT '消息类型：0-项目待关闭提醒 5-过期任务提醒',
  `content` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `ins_tm` datetime(0) NULL DEFAULT NULL COMMENT '生成时间',
  `msg_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送后返回的钉钉msgId',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '发送状态 0-初始状态 1-未读 2-已读',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `notify_to` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉通知的工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 385 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '钉钉提醒';

-- ----------------------------
-- Table structure for field_explain
-- ----------------------------
DROP TABLE IF EXISTS `field_explain`;
CREATE TABLE `field_explain`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_explain_id` int(10) NOT NULL COMMENT '父级id',
  `api_id` int(10) NOT NULL COMMENT '对应的接口id',
  `detail_view` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否支持详细视图, 0:否, 1:是',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段类型',
  `field_length` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字段长度',
  `mandatory` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否必须, 0:否, 1:是',
  `demo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '示例值',
  `comment` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `creator` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
  `modify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_api_id`(`api_id`) USING BTREE,
  INDEX `idx_parent_explain_id`(`parent_explain_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口字段含义表';

-- ----------------------------
-- Table structure for label_info
-- ----------------------------
DROP TABLE IF EXISTS `label_info`;
CREATE TABLE `label_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签类型',
  `product` int(255) NULL DEFAULT NULL COMMENT '引用产品',
  `citations` int(11) NULL DEFAULT NULL COMMENT '引用次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 338 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试用例标签表';

-- ----------------------------
-- Table structure for online_problem_operation_record
-- ----------------------------
DROP TABLE IF EXISTS `online_problem_operation_record`;
CREATE TABLE `online_problem_operation_record`  (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `online_problem_record_id` int(100) NOT NULL COMMENT '线上问题记录表id',
  `online_problem_operation_type` smallint(1) NOT NULL COMMENT '操作记录类型：1-新增，2-编辑，3- 删除，4-导出，5-TL确认，6-PM确认，7-归档',
  `operation_at` timestamp(0) NOT NULL COMMENT '操作时间',
  `operation_by_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人名称',
  `operation_by_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线上问题操作记录表';

-- ----------------------------
-- Table structure for online_problem_record
-- ----------------------------
DROP TABLE IF EXISTS `online_problem_record`;
CREATE TABLE `online_problem_record`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `app` smallint(1) NOT NULL COMMENT '应用：1-骑手app，2-商家app，3-鹰眼app，4-大中台',
  `problem_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题描述',
  `feedback_man_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈人名称',
  `feedback_man_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '反馈人工号',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源',
  `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  `city_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市ID',
  `feedback_date` timestamp(0) NOT NULL COMMENT '反馈时间',
  `resolve_status` smallint(1) NOT NULL COMMENT '解决状态:1-待解决，2-解决中,3-已解决',
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接产品工号',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接产品名字',
  `engineer_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接技术人工号',
  `engineer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接技术人员名字',
  `duty_man_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任人工号',
  `duty_man_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '责任人名字',
  `problem_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题原因',
  `problem_type` smallint(1) NULL DEFAULT NULL COMMENT '问题类型:1-操作问题,2-产品设计如此,3-第三方问题,4-故障,5-灰发,6-其他',
  `deal_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理方法',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` timestamp(0) NOT NULL COMMENT '创建时间',
  `create_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人工号',
  `update_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人工号',
  `is_deleted` smallint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：1-已删除，0-未删除',
  `check_status` smallint(1) NOT NULL COMMENT '工作流程状态：0-待TL审核，1-待PM审核，2-待归档，3-已归档',
  `update_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人姓名',
  `create_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线上问题记录表';

-- ----------------------------
-- Table structure for online_problem_role
-- ----------------------------
DROP TABLE IF EXISTS `online_problem_role`;
CREATE TABLE `online_problem_role`  (
  `id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_list` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '线上问题操作角色对应表';

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int(11) NOT NULL,
  `program_id` int(11) NULL DEFAULT NULL,
  `pd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `do` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for process_module
-- ----------------------------
DROP TABLE IF EXISTS `process_module`;
CREATE TABLE `process_module`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模版名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模版定义',
  `program_module` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目模版',
  `process_node` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目流程节点',
  `read_only` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否只读，N=否，Y=是',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'init' COMMENT '模板状态，unable=失效，enable=生效',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`, `read_only`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目流程模板';

-- ----------------------------
-- Table structure for process_task_relate
-- ----------------------------
DROP TABLE IF EXISTS `process_task_relate`;
CREATE TABLE `process_task_relate`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `process_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `task` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `sort` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段排序',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'enable' COMMENT '关联关系状态，unable=失效，enable=生效',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`, `process_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '阶段和任务关系表';

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品code',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'normal' COMMENT '产品类型',
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品描述',
  `createdBy` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `createdDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `defender` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维护人',
  `deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试产品表';

-- ----------------------------
-- Table structure for product_module
-- ----------------------------
DROP TABLE IF EXISTS `product_module`;
CREATE TABLE `product_module`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `root` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属产品',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模块名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '路径',
  `defender` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维护人',
  `deleted` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_root`(`root`) USING BTREE,
  INDEX `idx_path`(`path`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试产品模块';

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `program_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `program_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目类型',
  `program_module` int(20) NULL DEFAULT NULL COMMENT '项目模版',
  `process_module` int(20) NULL DEFAULT NULL COMMENT '流程模板',
  `persons` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目核心成员',
  `core_nodes` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目关键节点',
  `requires` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目关联需求',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `work_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目关联工单id',
  `pressing_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加急原因',
  `is_check` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否通过审核，Y-是，N-否',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'init' COMMENT '状态，init-待排期；processing-进行中；end-已结束；invalid-已失效',
  `create_tm` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `inner_project_type` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部项目类型',
  `each_task_work_hour` float(2, 0) NULL DEFAULT 0 COMMENT '每个任务工作量',
  `daily_task_num` int(2) NULL DEFAULT 0 COMMENT '每日任务数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表';

-- ----------------------------
-- Table structure for program_case
-- ----------------------------
DROP TABLE IF EXISTS `program_case`;
CREATE TABLE `program_case`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属产品',
  `module` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '所属模块',
  `require` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例标签',
  `precondition` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '前置条件',
  `pri` tinyint(3) UNSIGNED NOT NULL DEFAULT 3 COMMENT '优先级',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用例类型',
  `status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '用例状态',
  `opened_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用例执行人',
  `opened_date` datetime(0) NULL DEFAULT NULL COMMENT '用例执行日期',
  `last_edited_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后编辑人',
  `last_edited_date` datetime(0) NULL DEFAULT NULL COMMENT '最后编辑日期',
  `version` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '用例版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_product`(`product`) USING BTREE,
  INDEX `idx_module`(`module`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目用例表';

-- ----------------------------
-- Table structure for program_case_label
-- ----------------------------
DROP TABLE IF EXISTS `program_case_label`;
CREATE TABLE `program_case_label`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_id` int(11) NULL DEFAULT NULL COMMENT '用例',
  `label_id` int(11) NULL DEFAULT NULL COMMENT '标签',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creator_tm` time(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目用例标签';

-- ----------------------------
-- Table structure for program_case_step
-- ----------------------------
DROP TABLE IF EXISTS `program_case_step`;
CREATE TABLE `program_case_step`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用例id',
  `step_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '步骤id',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '步骤描述',
  `expect_db` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预期db',
  `expect_ui` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期ui',
  `expect_response` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期response',
  `expect_other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期other',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `case`(`case_id`) USING BTREE,
  INDEX `version`(`step_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 251 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目用例步骤';

-- ----------------------------
-- Table structure for program_document
-- ----------------------------
DROP TABLE IF EXISTS `program_document`;
CREATE TABLE `program_document`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '主键',
  `program_id` int(11) NULL DEFAULT NULL COMMENT '项目id',
  `require_id` int(11) NULL DEFAULT NULL COMMENT '需求id',
  `task_id` int(11) NULL DEFAULT NULL COMMENT '任务id',
  `document_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档名称',
  `document_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档路径',
  `status` tinyint(255) NULL DEFAULT NULL COMMENT '文档状态',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_tm` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for program_log
-- ----------------------------
DROP TABLE IF EXISTS `program_log`;
CREATE TABLE `program_log`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `program_id` int(20) NOT NULL COMMENT '项目id',
  `status` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目状态',
  `percent` int(20) NULL DEFAULT NULL COMMENT '进度',
  `program_explain` varchar(5120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目说明',
  `log_type` int(20) NULL DEFAULT NULL COMMENT '日志类型',
  `bug_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'bug信息',
  `task_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目测试任务信息',
  `is_normal` tinyint(20) NULL DEFAULT NULL COMMENT '项目是否正常',
  `reason_team` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常环节',
  `reason_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常类型',
  `reason_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常影响级别',
  `reason_detail` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常详细说明',
  `modifier` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 431 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目操作日志';

-- ----------------------------
-- Table structure for program_module
-- ----------------------------
DROP TABLE IF EXISTS `program_module`;
CREATE TABLE `program_module`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板定义',
  `parent_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父模版ID',
  `persons` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核心成员',
  `core_nodes` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核心节点',
  `requires` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求json',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `read_only` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否只读，Y-是，N-否',
  `work_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工单编号',
  `pressing_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加急原因',
  `is_check` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否通过审核，Y-是，N-否',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'enable' COMMENT '模版状态，unable=失效，enable=生效',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `daily_task_num` int(2) NULL DEFAULT 0 COMMENT '每日任务数量',
  `each_task_work_hour` float(2, 0) NULL DEFAULT 0 COMMENT '每个任务工作量',
  `inner_project_type` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部项目类型',
  `is_loop` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否循环任务，Y-是，N-否',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_code`(`parent_code`, `status`, `read_only`) USING BTREE,
  INDEX `idx_id`(`id`, `status`, `read_only`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目模板表';

-- ----------------------------
-- Table structure for program_require
-- ----------------------------
DROP TABLE IF EXISTS `program_require`;
CREATE TABLE `program_require`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `require_id` int(11) NULL DEFAULT NULL COMMENT '需求id',
  `require_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求名称',
  `program_id` int(11) NULL DEFAULT NULL COMMENT '项目id',
  `zt_project_id` int(11) NULL DEFAULT NULL COMMENT '禅道项目id',
  `zt_project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '禅道项目名称',
  `status` int(11) NULL DEFAULT NULL COMMENT '需求状态 0，无效  1有效',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 483 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for program_task
-- ----------------------------
DROP TABLE IF EXISTS `program_task`;
CREATE TABLE `program_task`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `program_id` int(64) NULL DEFAULT NULL COMMENT '项目名称',
  `program_process` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目流程名称',
  `require_id` int(64) NULL DEFAULT NULL COMMENT '任务关联需求Id',
  `require_relate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务关联需求',
  `task_id` int(20) NULL DEFAULT NULL COMMENT '任务Id',
  `task_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务code',
  `start_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务开始时间',
  `end_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务结束时间',
  `actual_start_tm` datetime(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `actual_end_tm` datetime(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `close_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务关闭时间',
  `pause_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务暂停时间',
  `tester` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试人员',
  `with_tester` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协同测试人员',
  `expect_hour` float(20, 1) NULL DEFAULT NULL COMMENT '总工时',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'init' COMMENT '任务状态，init-未开始；scheduled-已排期；processing-进行中；end-已结束；invalid-已失效；pause-暂停中',
  `create_tm` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `zt_task_id` int(20) NULL DEFAULT NULL COMMENT '禅道任务Id',
  `zt_project_id` int(20) NULL DEFAULT NULL COMMENT '禅道项目Id',
  `percent` int(20) NULL DEFAULT 0 COMMENT '进度',
  `task_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称，只用于内部项目',
  `actual_hour` float(20, 1) NULL DEFAULT 0.0 COMMENT '当前实际用时',
  `exclude_date` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '排除日期',
  `include_date` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '计算要包含的周末日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`, `status`) USING BTREE,
  INDEX `idx_task_id`(`task_id`, `program_id`, `program_process`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2575 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目任务表';

-- ----------------------------
-- Table structure for program_task_log
-- ----------------------------
DROP TABLE IF EXISTS `program_task_log`;
CREATE TABLE `program_task_log`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `program_id` int(20) NULL DEFAULT NULL COMMENT '项目id',
  `task_id` int(20) NOT NULL COMMENT '任务id',
  `require_id` int(20) NULL DEFAULT NULL COMMENT '需求id',
  `start_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务开始时间',
  `end_tm` datetime(0) NULL DEFAULT NULL COMMENT '任务结束时间',
  `task_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `tester` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试人员',
  `with_tester` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协同测试人员',
  `expect_hour` float(20, 1) NULL DEFAULT 0.0 COMMENT '预期用时',
  `actual_hour` float(20, 1) NULL DEFAULT 0.0 COMMENT '当前实际用时',
  `today_hour` float(20, 1) NULL DEFAULT 0.0 COMMENT '今日耗时',
  `percent` int(20) NULL DEFAULT 0 COMMENT '进度',
  `is_normal` tinyint(20) NULL DEFAULT NULL COMMENT '是否正常',
  `reason_team` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常环节',
  `reason_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常类型',
  `reason_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影响级别',
  `reason_detail` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细说明',
  `task_explain` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '完成情况说明',
  `auto_test_id` int(20) NULL DEFAULT NULL COMMENT '自动化测试id',
  `auto_test_result` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自动化测试报告',
  `document` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务关联文档',
  `modifier` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `zt_task_id` int(20) NULL DEFAULT NULL COMMENT '禅道任务Id',
  `abnormal_type` tinyint(4) NULL DEFAULT NULL COMMENT '异常类型：0-计划变更异常，1-任务进度异常',
  `abnormal_owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常责任人',
  `valid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'enable' COMMENT '字段状态，unable=失效，enable=生效',
  `abnormal_depart` smallint(6) NULL DEFAULT NULL COMMENT '异常所属部门',
  `duplicate_abnormal` int(11) NULL DEFAULT NULL COMMENT '与某异常重复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4508 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务操作日志';

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int(64) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `depart_id` tinyint(4) NOT NULL COMMENT '部门：0-商家，1-骑手，2-支撑，3-测开',
  `staff_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `staff_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '中文名',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：10-在职，20-离职',
  `dingding_userid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉userid',
  `dingding_chatid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉通知的chatid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组员信息表';

-- ----------------------------
-- Table structure for work_daily_report
-- ----------------------------
DROP TABLE IF EXISTS `work_daily_report`;
CREATE TABLE `work_daily_report`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `workday` date NOT NULL COMMENT '日期',
  `staff_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工工号',
  `staff_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文名',
  `task_id` int(20) NOT NULL COMMENT '任务id',
  `task_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务',
  `task_name` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内部任务名称',
  `require_id` int(64) NULL DEFAULT NULL COMMENT '任务关联需求Id',
  `require_relate` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务关联需求描述',
  `project_id` int(64) NULL DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `today_hour` float(20, 2) NULL DEFAULT NULL COMMENT '今日耗时',
  `type` int(20) NOT NULL COMMENT '任务类型：10-实际，20-预期',
  `task_log_id` int(20) NULL DEFAULT NULL COMMENT '日志id',
  `percent` int(20) NULL DEFAULT NULL COMMENT '当前进度',
  `is_normal` tinyint(5) NULL DEFAULT NULL COMMENT '是否正常：1-正常，0-异常',
  `reason_detail` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常情况说明',
  `task_explain` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '完成情况说明',
  `modify_tm` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 371 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
