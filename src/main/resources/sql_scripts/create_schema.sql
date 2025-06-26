
DROP SCHEMA IF EXISTS `spring_boot_todolist_app_db`;

CREATE SCHEMA `spring_boot_todolist_app_db`;

use `spring_boot_todolist_app_db`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `todo`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(50) NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    `created_date` DATE NOT NULL,
    `image_url` VARCHAR(300) DEFAULT NULL,
	`state_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),

    CONSTRAINT `FK_STATE`
    FOREIGN KEY (`state_id`)
    REFERENCES `state` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     `first_name` VARCHAR(50) NOT NULL,
     `last_name` VARCHAR(50) NOT NULL,
     `gender` VARCHAR(10) NULL,
     `birth_date` DATE NOT NULL,
     `email` VARCHAR(100) NOT NULL,
     `telephone_number` VARCHAR(20) NOT NULL,
     `username` VARCHAR(50) NOT NULL,
     `password` VARCHAR(68) NOT NULL,
     `active` BIT NOT NULL,

     PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
 CREATE TABLE `roles`(
     `id` INT(11) NOT NULL AUTO_INCREMENT,
     `role_name` VARCHAR(50) NOT NULL,

     PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 CREATE TABLE `users_roles`(
     `user_id` INT(11) NOT NULL,
     `role_id` INT(11) NOT NULL,

     PRIMARY KEY (`user_id`,`role_id`),

     CONSTRAINT `role_fk`
     FOREIGN KEY (`role_id`)
     REFERENCES `roles` (`id`)
     ON DELETE NO ACTION ON UPDATE NO ACTION,

     CONSTRAINT `user_fk`
     FOREIGN KEY (`user_id`)
     REFERENCES `users` (`id`)
     ON DELETE NO ACTION ON UPDATE NO ACTION
 )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

 SET FOREIGN_KEY_CHECKS = 1;

 INSERT INTO `roles` (`role_name`) VALUES ('ROLE_ADMIN');

 INSERT INTO `todo`  (`title`,`description`,`created_date`,`image_url`,`state_id`)VALUES('Matematik Ders','Matematik dersiniz saat 15:00 dedir.',DATE('2025-06-25'),'null','1');


 INSERT INTO `users` (`first_name`,`last_name`,`gender`,`birth_date`,`email`,`telephone_number`,`username`,`password`,`active`) VALUES ('Mert','Atay','Erkek','2000-05-06','ce.mertatay@gmail.com','05467248927','mertatay','{noop}test123',1);


 INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (1,1);