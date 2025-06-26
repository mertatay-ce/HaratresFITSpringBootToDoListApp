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