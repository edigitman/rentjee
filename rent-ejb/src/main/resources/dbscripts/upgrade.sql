create table `rentee`.`rt_net_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `picurl` VARCHAR(100) NULL,
  `tokenexp` BIGINT NULL,
  `net_type` VARCHAR(10) NULL,
  PRIMARY KEY (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

ALTER TABLE `rentee`.`users`
ADD COLUMN `net_user_id` BIGINT NULL AFTER `ROLE`;

ALTER TABLE `rentee`.`rt_net_user` MODIFY COLUMN `tokenexp` BIGINT;

ALTER TABLE `rentee`.`rt_net_user` ADD COLUMN `token` VARCHAR(100);

--new tables
create table `rentee`.`rt_help_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(200) NULL,
  `help_type` VARCHAR(10) NULL,
  `hits` int 0,
  PRIMARY KEY (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;


create table `rentee`.`rt_favorite_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `favorite_id` BIGINT NOT NULL,
  `text` VARCHAR(100) NULL,
  `datecreated` datetime default null,
  PRIMARY KEY (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

-- update user to also have a locale
ALTER TABLE `rentee`.`users`
ADD COLUMN `locale` VARCHAR(10) 'ro' AFTER `ROLE`;