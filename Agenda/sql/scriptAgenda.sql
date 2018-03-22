CREATE DATABASE `agenda`;
USE agenda;

CREATE TABLE `localidades`
(
	`idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
	`NombreLocalidad` varchar(45) NOT NULL,
	PRIMARY KEY (`idLocalidad`)
);

CREATE TABLE `tiposDeContactos`
(
	`idTipo` int(11) NOT NULL AUTO_INCREMENT,
	`DescripcionTipo` varchar(45) NOT NULL,
	PRIMARY KEY (`idTipo`)
);

CREATE TABLE `personas` 
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(20) NOT NULL,
  `Altura` varchar(20) NOT NULL,
  `Piso` varchar(20) NOT NULL,
  `Depto` varchar(20) NOT NULL,
  `idLocalidad` int(11) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `FechaDeCumpleaños` varchar(20) NOT NULL,
  `idTipo` int(11) NOT NULL,
  PRIMARY KEY (`idPersona`)
);

ALTER TABLE agenda.personas ADD FOREIGN KEY (idLocalidad) REFERENCES localidades(idLocalidad);
ALTER TABLE agenda.personas ADD FOREIGN KEY (idTipo) REFERENCES tiposdecontactos(idTipo);
