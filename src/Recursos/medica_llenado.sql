# llenado de datos de prueba 

-- base de datos a utilizar 
use base_medica;

-- datos prueba direccion
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle mixtecas','Ajusco', 'Iztapalapa', 'CDMX', 9, 7,04300);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle mar','Miguel Hidalgo', 'Doctores', 'CDMX', 115, 17,56450);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle peras','Buenaventura', 'Ixtapaluca', 'Edo.Mex', 46, 97,56536);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Av. Pinos','Pradera', 'Gustavo A. Madero', 'CDMX', 5, 9,58886);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Av.Azhares','Bella', 'Chalco', 'Edo. Mex', 10, 34,67936);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle san patricio','Camelia', 'La Paz', 'Edo.Mex', 16, 8,60636);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle mango','Las peras', 'San Mateo', 'Edo. Mex', 55, 77,50436);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Rtno. 12','', 'Iztapalapa', 'CDMX', 4, 6,55798);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Volcan Camalote','Pradera', 'Gustavo A. Madero', 'CDMX', 5, 7,55360);
insert into direccion(calle, colonia, municipio, estado, num_int, num_ext, cp) values ('Calle 14','Pradera', 'Gustavo A. Madero', 'CDMX', 9, 33,55360);

-- datos prueba persona 
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0001,1,'Gustavo', 'Arreola', 'Villegas',35,'5541890302');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0201,2,'Amanda', 'Arevalo', 'Villa',40,'5567896579');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0405,3,'Claudia', 'Ordaz', 'Suarez',39,'5593750497');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0706,4,'Ivan', 'Bello', 'Lopez',55,'5568492099');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0811,5,'Karla', 'Vaca', 'Carrasco',15,'5576359048');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0901,6,'Santiago', 'Fabian', 'Mata',14,'5585038592');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (1001,7,'Mauricio', 'Ceja', 'Mota',10,'5596478800');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0801,8,'Alma', 'Torres', 'Nuñez',15,'5541415641');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0091,9,'Arturo', 'Aguilera', 'Casas',30,'5507849977');
insert into persona(id_persona, id_direccion, nombre, apellido_p, apellido_m, edad, telefono) values (0891,10,'Gerardo', 'Zuñiga', 'Galvan',35,'5593870044');

-- datos prueba medico 
insert into medico(id_persona, cedula, consultorio, turno, especialidad,contrasenia) values (0001,'18937847','2','m','pediatria','admin');
insert into medico(id_persona, cedula, consultorio, turno, especialidad,contrasenia) values (0201,'17983678','2','v','pediatria','contra');
insert into medico(id_persona, cedula, consultorio, turno, especialidad,contrasenia) values (0405,'99876630','1','m','pediatria','hola');
insert into medico(id_persona, cedula, consultorio, turno, especialidad,contrasenia) values (0706,'66836658','1','m','pediatria','ggg');

-- datos prueba paciente 
insert into paciente(id_persona, id_medico, fecha_n, contacto_e) values (0811,1,'2006-05-14','Monica Lopez 5589097709');
insert into paciente(id_persona, id_medico, fecha_n, contacto_e) values (0901,2,'2007-11-30','Amanda Garcia 5588279000');
insert into paciente(id_persona, id_medico, fecha_n, contacto_e) values (1001,3,'2011-06-22','Saul Lopez 556692768822');
insert into paciente(id_persona, id_medico, fecha_n, contacto_e) values (0801,4,'2006-02-10','Andres Eduardo 5567895678');

-- datos prueba secretaria 
insert into secretaria (id_persona, turno,contrasenia) values (0091,'m','secre');
insert into secretaria (id_persona, turno,contrasenia) values (0891,'v','hola');

-- datos prueba cita 
insert into cita (id_paciente, fecha) values (1, '2021-12-28 07:30:00');
insert into cita (id_paciente, fecha) values (2, '2022-01-10 17:00:00');
insert into cita (id_paciente, fecha) values (3, '2021-12-28 08:45:00');
insert into cita (id_paciente, fecha) values (4, '2022-01-10 19:30:00');

