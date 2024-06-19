CREATE TABLE IF NOT EXISTS `Departamentos` (
	`idDpto` int NOT NULL UNIQUE,
	`Nombre` char(200) NOT NULL,
	`Telefono` int NOT NULL,
	`Fax` int NOT NULL,
	PRIMARY KEY (`idDpto`)
);

CREATE TABLE IF NOT EXISTS `Ingenieros` (
	`idIngeniero` int NOT NULL UNIQUE,
	`Especialidad` char(100) NOT NULL,
	`Cargo` char(200) NOT NULL,
	`IdProyecto` int NOT NULL,
	PRIMARY KEY (`idIngeniero`)
);

CREATE TABLE IF NOT EXISTS `Proyecto` (
	`idProyecto` int NOT NULL UNIQUE,
	`Nombre` char(100) NOT NULL,
	`Fec_Inicio` date NOT NULL,
	`Fec_Final` date NOT NULL,
	`idDpto` int NOT NULL,
	PRIMARY KEY (`idProyecto`)
);


ALTER TABLE `Ingenieros` ADD CONSTRAINT `Ingenieros_fk3` FOREIGN KEY (`IdProyecto`) REFERENCES `Proyecto`(`idProyecto`);
ALTER TABLE `Proyecto` ADD CONSTRAINT `Proyecto_fk4` FOREIGN KEY (`idDpto`) REFERENCES `Departamentos`(`idDpto`);  