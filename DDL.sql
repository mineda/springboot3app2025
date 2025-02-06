create table usr_usuario (
  usr_id bigint generated always as identity,
  usr_nome varchar(20) not null,
  usr_senha varchar(150) not null,
  primary key (usr_id),
  unique (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint generated always as identity,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint generated always as identity,
  ant_texto varchar(256) not null,
  ant_data_hora timestamp not null,
  ant_usr_id bigint not null,
  primary key (ant_id),
  foreign key (ant_usr_id) references usr_usuario(usr_id)
);

insert into usr_usuario (usr_nome, usr_senha)
  values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
  values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
  values (1, 1);
insert into ant_anotacao(ant_texto, ant_data_hora, ant_usr_id)
  values('Meu novo projeto', '2023-08-01 19:10', 1);

create user spring with password 'pass123';

grant update, delete, insert, select on all tables in schema public to spring;
