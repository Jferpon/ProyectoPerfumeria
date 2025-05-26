Drop database if exists BDPerfumeria;
create database if not exists BDPerfumeria;
use BDPerfumeria;
drop table if exists disenadores;
create table if not exists disenadores
( idDisenador int auto_increment,
	nombre varchar (100),
    ape1 varchar (50), 
    ape2 varchar (50), 
    fecNacimiento date,
    constraint pk_Disenador primary key (idDisenador)
);
drop table if exists perfumes;
create table if not exists perfumes
( idPerfume int auto_increment,
  idDisenador int, 
  nombrePerfume varchar (50), 
  nombreLinea varchar (50), 
  precio decimal (6,2), 
  duracion varchar (30), 
  tipoPerfume enum('eau de toilette', 'eau de parfum','parfum','elixir'),
  constraint pk_perfume primary key (idPerfume),
  constraint fk_disenador_perfume foreign key (idDisenador) references disenadores (idDisenador)
 );
 drop table if exists notasAromaticas; 
 create table if not exists notasAromaticas
 ( idNota int auto_increment,
   tipoNota enum('salida','corazon','base'),
   olor varchar (70), 
   constraint pk_notasAromaticas primary key (idNota)
   );
   
   drop table if exists detallePerfume; 
   create table if not exists detallePerfume
   ( idDetalle int auto_increment,
	idPerfume int, 
    idNota int, 
    constraint pk_detalle_perfume primary key (idDetalle), 
    constraint fk_perfume foreign key (idPerfume) references perfumes (idPerfume),
    constraint fk_nota foreign key (idNota) references notasAromaticas (idNota)
   );