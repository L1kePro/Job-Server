DROP DATABASE IF EXISTS hiring;
CREATE DATABASE `hiring`; 
USE `hiring`;

/*Таблица навыков*/
CREATE TABLE skills (
id INT (11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
lvl INT (11) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (name)
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Таблица работодателей*/
CREATE TABLE employer (
id INT (11) NOT NULL AUTO_INCREMENT,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
patronymic VARCHAR(50) DEFAULT NULL,
address VARCHAR(50) NOT NULL,
company VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
login VARCHAR(50) NOT NULL,
pass VARCHAR(50) NOT NULL,
uuid VARCHAR (50) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (login)
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Связующая таблица работодателя и вакансий*/
CREATE TABLE vacancies (
id INT (11) NOT NULL AUTO_INCREMENT,
id_employer INT (11) NOT NULL,
name VARCHAR (50) NOT NULL,
salary INT (11) NOT NULL,
isactive BOOLEAN DEFAULT 1 NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (name, id_employer),
FOREIGN KEY (id_employer) REFERENCES employer (id) ON DELETE CASCADE
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Связующая таблица вакансии и навыков*/
CREATE TABLE vacancy_skills (
id INT (11) NOT NULL AUTO_INCREMENT,
id_vacancy INT (11) NOT NULL,
id_skill INT (11) NOT NULL,
/*lvl INT (11) NOT NULL,*/
necessary BOOLEAN DEFAULT 1 NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (id_vacancy, id_skill),
FOREIGN KEY (id_vacancy) REFERENCES vacancies (id) ON DELETE CASCADE,
FOREIGN KEY (id_skill) REFERENCES skills (id) ON DELETE CASCADE
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Таблица работников*/
CREATE TABLE employee (
id INT (11) NOT NULL AUTO_INCREMENT,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
patronymic VARCHAR(50) DEFAULT NULL,
email VARCHAR(50) NOT NULL,
login VARCHAR(50) NOT NULL,
pass VARCHAR(50) NOT NULL,
active BOOLEAN DEFAULT 1 NOT NULL,
uuid VARCHAR (50) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (login)
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Связующая таблица работника и навыков*/
CREATE TABLE employee_skills (
id INT (11) NOT NULL AUTO_INCREMENT,
id_employee INT (11) NOT NULL,
id_skill INT (11) NOT NULL,
/*lvl INT (11) NOT NULL,*/
PRIMARY KEY (id),
UNIQUE KEY (id_employee, id_skill),
FOREIGN KEY (id_employee) REFERENCES employee (id) ON DELETE CASCADE,
FOREIGN KEY (id_skill) REFERENCES skills (id) ON DELETE CASCADE
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;


/*Таблица uuid-ов записываются uuid работников и работодателей*/
CREATE TABLE uuids (
id INT (11) NOT NULL AUTO_INCREMENT,
uuid VARCHAR (50) DEFAULT NULL,
is_online BOOLEAN DEFAULT 1 NOT NULL,
PRIMARY KEY (id)
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Таблица логинов, записываются логины работников и работодателей*/
CREATE TABLE logins (
id INT (11) NOT NULL AUTO_INCREMENT,
login VARCHAR (50) NOT NULL,
UNIQUE KEY (login),
PRIMARY KEY (id)
) 
ENGINE=INNODB DEFAULT CHARSET=utf8;

