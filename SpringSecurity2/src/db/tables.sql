drop table authorities;
drop table user_roles;
drop table users;
/*
create table users(
  username varchar(100) not null primary key,
  password varchar(100) not null,
  enabled boolean not null);

create table authorities (
  username varchar(100) not null,
  authority varchar(100) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

// Password Chicken22
INSERT INTO users (USERNAME,PASSWORD, ENABLED) VALUES ( 'elitepropertiesbroker@gmail.com', '56a0186ef924fabfce9976ce5a614fb1d8a995ca0cce3783aff0c5bbf538ccf35bca635292497763', TRUE);
INSERT INTO authorities(username, authority) VALUES ('elitepropertiesbroker@gmail.com', 'ROLE_USER');

commit;
*/

use tutorials;
drop table user_roles;
drop table users;

CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(90) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users (USERNAME,PASSWORD, ENABLED)

VALUES ('elitepropertiesbroker@gmail.com', '56a0186ef924fabfce9976ce5a614fb1d8a995ca0cce3783aff0c5bbf538ccf35bca635292497763', TRUE);
INSERT INTO user_roles (USER_ID,AUTHORITY) VALUES (1, 'ROLE_USER');

commit;



