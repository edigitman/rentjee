CREATE TABLE `md_building_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `ORDERING` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `md_heat_source` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `ORDERING` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `md_city` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `ORDERING` int(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `md_neighborhood` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_md_neighborhood_city_id` (`city_id`),
  CONSTRAINT `FK_md_neighborhood_city_id` FOREIGN KEY (`city_id`) REFERENCES `md_city` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_address` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AP` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `BLOC` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `CP` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `ET` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `NR` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `SC` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `STREET` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `cityId` bigint(20) DEFAULT NULL,
  `nbhId` bigint(20) DEFAULT NULL,
  `lat` decimal(10,7) DEFAULT NULL,
  `lng` decimal(10,7) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rt_address_cityId` (`cityId`),
  KEY `FK_rt_address_nbhId` (`nbhId`),
  CONSTRAINT `FK_rt_address_cityId` FOREIGN KEY (`cityId`) REFERENCES `md_city` (`ID`),
  CONSTRAINT `FK_rt_address_nbhId` FOREIGN KEY (`nbhId`) REFERENCES `md_neighborhood` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FURNISHED` tinyint(1) DEFAULT '0',
  `MAXPERSONS` int(2) DEFAULT NULL,
  `NOBATHS` int(2) DEFAULT NULL,
  `NOROOMS` int(2) DEFAULT NULL,
  `SQRM` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
)  ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_interval` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FROMDATE` date DEFAULT NULL,
  `FROMNOW` tinyint(1) DEFAULT '0',
  `UNTILDATE` date DEFAULT NULL,
  `UNTILUNDEFINED` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
)  ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_value` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(4) DEFAULT NULL,
  `DEPOSIT` decimal(11,4) DEFAULT NULL,
  `NEGOTIABLE` tinyint(1) DEFAULT '0',
  `VALUE` decimal(11,4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `tracers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` bigint(20) DEFAULT NULL,
  `user` varchar(50) DEFAULT NULL,
  `url` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `nbh` int(11) DEFAULT NULL,
  `priceMin` decimal(10,4) DEFAULT NULL,
  `priceMax` decimal(10,4) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `confirmed_BL` tinyint(1) DEFAULT '0',
  `EMAIL` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `PHONE2` varchar(20) DEFAULT NULL,
  `PHONE3` varchar(20) DEFAULT NULL,
  `REGTOKEN` varchar(60) DEFAULT NULL,
  `ROLE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_advert` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `adType` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `dotari` bigint(20) DEFAULT NULL,
  `private_owned_bl` tinyint(1) DEFAULT '0',
  `STATUS` varchar(255) DEFAULT NULL,
  `STATUSUPDATE` date DEFAULT NULL,
  `buildingTypeId` bigint(20) DEFAULT NULL,
  `adusr` bigint(20) DEFAULT NULL,
  `addressId` bigint(20) DEFAULT NULL,
  `infoId` bigint(20) DEFAULT NULL,
  `intervalId` bigint(20) DEFAULT NULL,
  `valueId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rt_advert_adusr` (`adusr`),
  KEY `FK_rt_advert_valueId` (`valueId`),
  KEY `FK_rt_advert_buildingTypeId` (`buildingTypeId`),
  KEY `FK_rt_advert_addressId` (`addressId`),
  KEY `FK_rt_advert_infoId` (`infoId`),
  KEY `FK_rt_advert_intervalId` (`intervalId`),
  CONSTRAINT `FK_rt_advert_addressId` FOREIGN KEY (`addressId`) REFERENCES `rt_address` (`ID`),
  CONSTRAINT `FK_rt_advert_adusr` FOREIGN KEY (`adusr`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_rt_advert_buildingTypeId` FOREIGN KEY (`buildingTypeId`) REFERENCES `md_building_type` (`ID`),
  CONSTRAINT `FK_rt_advert_infoId` FOREIGN KEY (`infoId`) REFERENCES `rt_info` (`ID`),
  CONSTRAINT `FK_rt_advert_intervalId` FOREIGN KEY (`intervalId`) REFERENCES `rt_interval` (`ID`),
  CONSTRAINT `FK_rt_advert_valueId` FOREIGN KEY (`valueId`) REFERENCES `rt_value` (`ID`)
)  ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;

CREATE TABLE `rt_image` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BYTES` bigint(20) DEFAULT NULL,
  `CREATEDAT` date DEFAULT NULL,
  `ETAG` varchar(255) DEFAULT NULL,
  `FORMAT` varchar(50) DEFAULT NULL,
  `HEIGHT` bigint(6) DEFAULT NULL,
  `PUBLICID` varchar(255) DEFAULT NULL,
  `RESOURCETYPE` varchar(255) DEFAULT NULL,
  `SECUREURL` varchar(255) DEFAULT NULL,
  `SIGNATURE` varchar(255) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `URLSMALL` varchar(255) DEFAULT NULL,
  `VERSION` bigint(10) DEFAULT NULL,
  `WIDTH` bigint(6) DEFAULT NULL,
  `advertid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rt_image_advertid` (`advertid`),
  CONSTRAINT `FK_rt_image_advertid` FOREIGN KEY (`advertid`) REFERENCES `rt_advert` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `rt_estimated_unit` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(3) DEFAULT NULL,
  `FROMDATE` date DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `TODATE` date DEFAULT NULL,
  `VALUE` decimal(15,5) DEFAULT NULL,
  `advert_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rt_estimated_unit_advert_id` (`advert_id`),
  CONSTRAINT `FK_rt_estimated_unit_advert_id` FOREIGN KEY (`advert_id`) REFERENCES `rt_advert` (`ID`)
)  ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_romanian_ci;



CREATE TABLE `rt_favorites` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATECREATED` datetime DEFAULT NULL,
  `advertId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_rt_favorites_advertId` (`advertId`),
  KEY `FK_rt_favorites_userId` (`userId`),
  CONSTRAINT `FK_rt_favorites_advertId` FOREIGN KEY (`advertId`) REFERENCES `rt_advert` (`ID`),
  CONSTRAINT `FK_rt_favorites_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

