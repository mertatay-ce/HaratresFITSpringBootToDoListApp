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

