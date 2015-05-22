create database rentee
  character set = utf8;

use rentee;

create table `md_building_type` (
  `id`       bigint(20) not null auto_increment,
  `name`     varchar(55)
             character set utf8 default null,
  `ordering` int(2) default null,
  primary key (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `md_heat_source` (
  `id`       bigint(20) not null auto_increment,
  `name`     varchar(55)
             character set utf8 default null,
  `ordering` int(2) default null,
  primary key (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `md_city` (
  `id`       bigint(20) not null auto_increment,
  `name`     varchar(55)
             character set utf8 default null,
  `ordering` int(2) default null,
  primary key (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `md_neighborhood` (
  `id`      bigint(20) not null auto_increment,
  `name`    varchar(55)
            character set utf8 default null,
  `city_id` bigint(20) default null,
  primary key (`id`),
  key `fk_md_neighborhood_city_id` (`city_id`),
  constraint `fk_md_neighborhood_city_id` foreign key (`city_id`) references `md_city` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `rt_address` (
  `id`     bigint(20) not null auto_increment,
  `ap`     varchar(10)
           character set utf8 default null,
  `bloc`   varchar(10)
           character set utf8 default null,
  `cp`     varchar(10)
           character set utf8 default null,
  `et`     varchar(5)
           character set utf8 default null,
  `nr`     varchar(5)
           character set utf8 default null,
  `sc`     varchar(5)
           character set utf8 default null,
  `street` varchar(50)
           character set utf8 default null,
  `cityid` bigint(20) default null,
  `nbhid`  bigint(20) default null,
  `lat`    decimal(10, 7) default null,
  `lng`    decimal(10, 7) default null,
  primary key (`id`),
  key `fk_rt_address_cityid` (`cityid`),
  key `fk_rt_address_nbhid` (`nbhid`),
  constraint `fk_rt_address_cityid` foreign key (`cityid`) references `md_city` (`id`),
  constraint `fk_rt_address_nbhid` foreign key (`nbhid`) references `md_neighborhood` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `rt_info` (
  `id`         bigint(20) not null auto_increment,
  `furnished`  tinyint(1) default '0',
  `maxpersons` int(2) default null,
  `nobaths`    int(2) default null,
  `norooms`    int(2) default null,
  `sqrm`       decimal(10, 3) default null,
  primary key (`id`)
)
  engine =innodb
  auto_increment =6
  default charset =utf8
  collate =utf8_romanian_ci;

create table `rt_interval` (
  `id`             bigint(20) not null auto_increment,
  `fromdate`       date default null,
  `fromnow`        tinyint(1) default '0',
  `untildate`      date default null,
  `untilundefined` tinyint(1) default '0',
  primary key (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `tracers` (
  `id`         bigint(20) not null auto_increment,
  `ip`         bigint(20) default null,
  `user`       varchar(50) default null,
  `url`        int(11) default null,
  `city`       int(11) default null,
  `nbh`        int(11) default null,
  `pricemin`   decimal(10, 2) default null,
  `pricemax`   decimal(10, 2) default null,
  `createdate` date default null,
  primary key (`id`)
)
  engine =myisam
  auto_increment =100
  default charset =latin1;

create table `users` (
  `id`           bigint(20) not null auto_increment,
  `confirmed_bl` tinyint(1) default '0',
  `email`        varchar(50) default null,
  `name`         varchar(50)
                 character set utf8 default null,
  `password`     varchar(50) default null,
  `phone`        varchar(20) default null,
  `phone2`       varchar(20) default null,
  `phone3`       varchar(20) default null,
  `regtoken`     varchar(60) default null,
  `role`         varchar(10) default null,
  primary key (`id`),
  unique key `email` (`email`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `rt_advert` (
  `id`               bigint(20) not null auto_increment,
  `adtype`           varchar(255) default null,
  `description`      varchar(255) default null,
  `dotari`           bigint(20) default null,
  `private_owned_bl` tinyint(1) default '0',
  `status`           varchar(255) default null,
  `statusupdate`     date default null,
  `buildingtypeid`   bigint(20) default null,
  `adusr`            bigint(20) default null,
  `addressid`        bigint(20) default null,
  `infoid`           bigint(20) default null,
  `intervalid`       bigint(20) default null,
  `currency`         varchar(4) default null,
  `deposit`          decimal(11, 2) default null,
  `negotiable`       tinyint(1) default '0',
  `value`            decimal(11, 2) default null,
  `withpictures`     tinyint(1) default '0',
  primary key (`id`),
  key `fk_rt_advert_adusr` (`adusr`),
  key `fk_rt_advert_buildingtypeid` (`buildingtypeid`),
  key `fk_rt_advert_addressid` (`addressid`),
  key `fk_rt_advert_infoid` (`infoid`),
  key `fk_rt_advert_intervalid` (`intervalid`),
  constraint `fk_rt_advert_addressid` foreign key (`addressid`) references `rt_address` (`id`),
  constraint `fk_rt_advert_adusr` foreign key (`adusr`) references `users` (`id`),
  constraint `fk_rt_advert_buildingtypeid` foreign key (`buildingtypeid`) references `md_building_type` (`id`),
  constraint `fk_rt_advert_infoid` foreign key (`infoid`) references `rt_info` (`id`),
  constraint `fk_rt_advert_intervalid` foreign key (`intervalid`) references `rt_interval` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;

create table `rt_image` (
  `id`           bigint(20) not null auto_increment,
  `bytes`        bigint(20) default null,
  `createdat`    date default null,
  `etag`         varchar(255) default null,
  `format`       varchar(50) default null,
  `height`       bigint(6) default null,
  `publicid`     varchar(255) default null,
  `resourcetype` varchar(255) default null,
  `secureurl`    varchar(255) default null,
  `signature`    varchar(255) default null,
  `type`         varchar(50) default null,
  `url`          varchar(255) default null,
  `urlsmall`     varchar(255) default null,
  `version`      bigint(10) default null,
  `width`        bigint(6) default null,
  `advertid`     bigint(20) default null,
  primary key (`id`),
  key `fk_rt_image_advertid` (`advertid`),
  constraint `fk_rt_image_advertid` foreign key (`advertid`) references `rt_advert` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =latin1;

create table `rt_estimated_unit` (
  `id`        bigint(20) not null auto_increment,
  `currency`  varchar(3) default null,
  `fromdate`  date default null,
  `name`      varchar(100) default null,
  `todate`    date default null,
  `value`     decimal(15, 3) default null,
  `advert_id` bigint(20) default null,
  primary key (`id`),
  key `fk_rt_estimated_unit_advert_id` (`advert_id`),
  constraint `fk_rt_estimated_unit_advert_id` foreign key (`advert_id`) references `rt_advert` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =utf8
  collate =utf8_romanian_ci;


create table `rt_favorites` (
  `id`          bigint(20) not null auto_increment,
  `datecreated` datetime default null,
  `advertid`    bigint(20) default null,
  `userid`      bigint(20) default null,
  primary key (`id`),
  key `fk_rt_favorites_advertid` (`advertid`),
  key `fk_rt_favorites_userid` (`userid`),
  constraint `fk_rt_favorites_advertid` foreign key (`advertid`) references `rt_advert` (`id`),
  constraint `fk_rt_favorites_userid` foreign key (`userid`) references `users` (`id`)
)
  engine =innodb
  auto_increment =1000
  default charset =latin1;

