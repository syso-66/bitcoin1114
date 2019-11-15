SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction_detail
-- ----------------------------
DROP TABLE IF EXISTS `transaction_detail`;
CREATE TABLE `transaction_detail`
(
    `tx_detail_id`   bigint(20) NOT NULL auto_increment,
    `address`        varchar(50),
    `type`           tinyint,
    `amount`         double,
    `transaction_id` int        not null,
    PRIMARY KEY (`tx_detail_id`),
    index `idx_address` (`address`),
    index `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;