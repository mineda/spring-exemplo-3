create schema frases;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on frases.* to user@'localhost';

use frases;

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_email varchar(100) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome),
  unique key uni_usuario_email (usr_email)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key uau_usr_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key uau_aut_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table frs_frase (
  frs_id bigint unsigned not null auto_increment,
  frs_titulo varchar(50) not null,
  frs_conteudo varchar(500) not null,
  frs_data_hora datetime not null,
  usr_autor_id bigint unsigned not null,
  primary key (frs_id),
  foreign key fsr_usr_fk (usr_autor_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  unique key uni_fsr_titulo (frs_titulo)
);
