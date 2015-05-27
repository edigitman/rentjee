create table `rentee`.`rt_net_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `picurl` VARCHAR(100) NULL,
  `tokenexp` DATETIME NULL,
  `net_type` VARCHAR(10) NULL,
  PRIMARY KEY (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

ALTER TABLE `rentee`.`users`
ADD COLUMN `net_user_id` BIGINT NULL AFTER `ROLE`;