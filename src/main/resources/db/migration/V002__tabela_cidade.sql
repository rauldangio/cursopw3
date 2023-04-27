create table cidade(
    id int not null auto_increment primary key,
    nomecidade varchar(40) not null,
    uf varchar(40) not null
);

insert into cidade values(0,"Ribeirao Preto", "SP"),(0,"Lencois Paulista", "SP"),(0,"Barueri", "SP");