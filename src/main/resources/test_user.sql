CREATE TABLE `lz_test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `type` int(11) DEFAULT '0' COMMENT '0',
  `branch_id` int(11) DEFAULT NULL COMMENT '版本号',
  `real_name` varchar(256) DEFAULT NULL COMMENT '真实名称',
  `mobile` varchar(256) DEFAULT NULL COMMENT '手机号码',
  `username` varchar(256) DEFAULT NULL COMMENT '用户名',
  `task_id` int(11) DEFAULT NULL COMMENT '任务 id',
  `staff_id` int(11) DEFAULT '0' COMMENT '员工 id',
  `encrypt_flag` int(11) DEFAULT '0' COMMENT '是否加密，0 未加密，1 己加密，2其他加密算法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='项目用户';


INSERT INTO `lz_test_user` (`id`, `is_delete`, `gmt_create`, `gmt_modified`, `type`, `branch_id`, `real_name`, `mobile`, `username`, `task_id`, `staff_id`, `encrypt_flag`) VALUES
('14', '0', '2021-03-28 14:34:59', '2021-03-28 14:34:59', '0', '10', 'tORUYS+eS9+3SpaZv9uLMQ==', 'ikn14QD2T7PYj7WCm8PVhQ==', 'zhangsan', NULL, '10', '0'),
('17', '0', '2021-03-28 14:37:27', '2021-03-28 14:37:27', '0', '10', '1Cjea8sDWEw/NMzZs0ulaQ==', 'ikn14QD2T7PYj7WCm8PVhQ==', 'zhangsan', NULL, '10', '0'),
('24', '0', '2021-03-28 15:04:46', '2021-03-28 15:04:46', NULL, '10', '1Cjea8sDWEw/NMzZs0ulaQ==', 'ikn14QD2T7PYj7WCm8PVhQ==', 'zhangsan', NULL, '10', '0'),
('25', '0', '2021-03-28 15:04:46', '2021-03-28 15:04:46', NULL, '10', 'Nxcr2HvYPXoetfz/JjT8sw==', 'BNWuYGFY5szjcB0o7AyaQw==', 'zhangsan', NULL, '10', '0'),
('26', '0', '2021-03-28 16:11:36', '2021-03-28 16:11:36', NULL, '10', '1Cjea8sDWEw/NMzZs0ulaQ==', 'ikn14QD2T7PYj7WCm8PVhQ==', 'zhangsan', NULL, '10', '0'),
('27', '0', '2021-03-28 16:11:36', '2021-03-28 16:11:36', NULL, '10', '1Cjea8sDWEw/NMzZs0ulaQ==', 'BNWuYGFY5szjcB0o7AyaQw==', 'zhangsan', NULL, '10', '0'),
('28', '0', '2021-04-07 10:35:00', '2021-04-07 10:35:00', '0', '10', '/LtOXyoNdjilQ56CGnXJrw==', 'tORUYS+eS9+3SpaZv9uLMQ==', 'zhangsan', NULL, '10', '0'),
('29', '0', '2021-04-07 10:36:41', '2021-04-07 10:36:41', '0', '10', '/LtOXyoNdjilQ56CGnXJrw==', 'tORUYS+eS9+3SpaZv9uLMQ==', 'zhangsan', NULL, '10', '0'),
('30', '0', '2021-04-07 10:37:17', '2021-04-07 10:37:17', '0', '10', '/LtOXyoNdjilQ56CGnXJrw==', 'tORUYS+eS9+3SpaZv9uLMQ==', 'zhangsan', NULL, '10', '0'),
('31', '0', '2021-04-07 10:43:20', '2021-04-07 10:43:20', '0', '10', '/LtOXyoNdjilQ56CGnXJrw==', 'tORUYS+eS9+3SpaZv9uLMQ==', 'zhangsan', NULL, '10', '0'),
('32', '0', '2021-04-07 10:44:11', '2021-04-07 10:44:11', NULL, '10', '/LtOXyoNdjilQ56CGnXJrw==', 'tORUYS+eS9+3SpaZv9uLMQ==', 'zhangsan', NULL, '10', '0'),
('33', '0', '2021-04-07 10:44:11', '2021-04-07 10:44:11', NULL, '10', '/LtOXyoNdjilQ56CGnXJrw==', 'xdx9tvjotjtVXn+1/ueBuQ==', 'zhangsan', NULL, '10', '0');


