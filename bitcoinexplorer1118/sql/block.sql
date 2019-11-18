SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for block
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block`
(
    `block_id`           int(11) NOT NULL auto_increment,
    `blockhash`          char(64) not null,
    `height`             int not null ,
    `time`               bigint not null ,
    `miner`              varchar(50),
    `sizeOnDisk`         int,
    `confirmations`      int(11),
    `txSize`             int(11),
    `difficulty`         double,
    `merkle_root`        char(64),
    `bits`               int,
    `version`            varchar(20),
    `weight`             int,
    `block_reward`       double,
    `fee_reward`         double,
    `transaction_volume` double,
    `nonce`              varchar(20),
    PRIMARY KEY (`block_id`),
    unique `idx_blockhash` (`blockhash`),
    unique `idx_height` (`height`),
    index `idx_time` (`time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
