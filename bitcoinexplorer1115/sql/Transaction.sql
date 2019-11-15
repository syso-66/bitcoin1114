SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`
(
    `transaction_id`      int(11)  NOT NULL auto_increment,
    `txid`                char(64) not null,
    `txhash`              char(64) not null,
    `time`                bigint   not null,
    `amount`              double   not null,
    `fees`                double,
    `confirmations`       int(11),
    `status`              tinyint,
    `sizeOnDisk`          int,
    `weight`              int,
    `total_input`         double,
    `total_output`        double,
    `fee_per_byte`        double,
    `fee_per_weight_unit` double,
    `block_id`            int,
    PRIMARY KEY (`transaction_id`),
    unique `idx_txid` (`txid`),
    unique `idx_txhash` (`txhash`),
    index `idx_time` (`time`),
    index `idx_block_id` (`block_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
