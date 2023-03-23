create table aluno(
    id bigint not null auto_increment primary key,
    nomealuno varchar(50),
    idcidade int not null,
    idcurso int not null
);

alter table aluno add constraint fk_aluno_curso foreign key(idcurso) references curso(id);
alter table aluno add constraint fk_aluno_cidade foreign key(idcidade) references cidade(id);

insert into aluno values(0,"raul",2,2);
insert into aluno values(0,"vinicius",1,1);
insert into aluno values(0,"sarah",3,3);
insert into aluno values(0,"giovana",2,4);
