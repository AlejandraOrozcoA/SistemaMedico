#Creacion de la base de datos y tablas 

drop database if exists base_medica;
CREATE DATABASE IF NOT EXISTS base_medica;
USE base_medica;

#tabla Direccion
CREATE TABLE IF NOT EXISTS direccion (
	id_dir INT auto_increment PRIMARY KEY,
    calle varchar(80) not null,
    colonia varchar(80) not null,
    municipio varchar(80) not null,
    estado varchar(80) not null,
    num_int int not null,
    num_ext int not null,
    cp int not null
); 

#tabla Persona
CREATE TABLE IF NOT EXISTS persona (
	id_persona INT NOT NULL PRIMARY KEY unique,
    id_direccion INT NOT NULL,
    nombre varchar(150) not null,
    apellido_p varchar(150) not null,
    apellido_m varchar(150) not null,
    edad int not null,
    telefono varchar(10),
    foreign key (id_direccion) references direccion(id_dir) on delete cascade on update cascade
); 

#tabla medico 
CREATE TABLE IF NOT EXISTS medico (
	id_medico INT auto_increment NOT NULL PRIMARY KEY,
    id_persona INT NOT NULL,
    cedula varchar(8) not null,
    consultorio varchar(3) not null,
    turno varchar(1) not null,
    especialidad varchar(80),
    contrasenia VARCHAR(15) not null,
    foreign key (id_persona) references persona(id_persona) on delete cascade on update cascade
); 

#tabla paciente 
CREATE TABLE IF NOT EXISTS paciente (
	id_paciente INT auto_increment NOT NULL PRIMARY KEY,
    id_persona INT NOT NULL,
    id_medico INT NOT NULL,
    fecha_n date not null,
    contacto_e varchar(150) not null,
    foreign key (id_persona) references persona(id_persona) on delete cascade on update cascade,
    foreign key (id_medico) references medico(id_medico) on delete cascade on update cascade
); 

#tabla Secretaria 
CREATE TABLE IF NOT EXISTS secretaria (
	id_secretaria INT auto_increment NOT NULL PRIMARY KEY,
    id_persona INT NOT NULL,
    turno varchar(1) not null,
    contrasenia VARCHAR(15) not null,
    foreign key (id_persona) references persona(id_persona) on delete cascade on update cascade
); 

#tabla cita 
CREATE TABLE IF NOT EXISTS cita (
	id_cita INT auto_increment NOT NULL PRIMARY KEY unique,
    id_paciente INT NOT NULL,
    fecha datetime not null,
    foreign key (id_paciente) references paciente(id_paciente) on delete cascade on update cascade
); 

#tabla historia clinica 
CREATE TABLE IF NOT EXISTS historiaClinica (
	id_historia INT NOT NULL PRIMARY KEY unique,
    id_paciente INT NOT NULL,
    ahf varchar(150),
	apnp varchar(150),
	perinatales varchar(150),
    talla double,
	apgar varchar(150),
	hospitalizado varchar(150),
	complicaciones varchar(150),
	tamiz_metabolico varchar(150),
	tamiz_auditivo varchar(150),
    foreign key (id_paciente) references paciente(id_paciente) on delete cascade on update cascade
); 

#tabla vacuna
CREATE TABLE IF NOT EXISTS vacuna (
	id_vacuna INT NOT NULL PRIMARY KEY unique,
    id_historia INT NOT NULL,
	nombre varchar(150),
    foreign key (id_historia) references historiaClinica(id_historia) on delete cascade on update cascade
); 

#tabla consulta subsecuente
CREATE TABLE IF NOT EXISTS consultaSubsecuente (
	id_consulta INT NOT NULL PRIMARY KEY unique,
    id_historia INT NOT NULL,
	pa varchar(300),
    ef varchar(300),
    dx varchar(300) not null,
    tx varchar(300) not null,
    estudios varchar(300),
    fecha date not null,
    foreign key (id_historia) references historiaClinica(id_historia) on delete cascade on update cascade
); 


