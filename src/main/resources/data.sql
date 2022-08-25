
create table if not exists despesas
(
    id      bigint auto_increment,
    data       date,
    descricao  varchar,
    valor      decimal,
    categorias varchar,
    constraint DESPESAS_PK
        primary key (id)
);

create table if not exists perfil
(
    id   bigint auto_increment,
    nome varchar,
    constraint PERFIL_PK
        primary key (id)
);

create table if not exists usuario
(
    id    bigint auto_increment,
    email varchar,
    nome  varchar,
    senha varchar,
    constraint USUARIO_PK
        primary key (id)
);

create table  if not exists receitas
(
    id       bigint auto_increment,
    data       date,
    descricao  varchar,
    valor      decimal,
    constraint RECEITAS_PK
        primary key (id)
);

create table if not exists usuario_perfil
(
    usuario_id bigint not null ,
    perfil_id  bigint not null,
    constraint USUARIO_PERFIL_FK
        foreign key (perfil_id) references PERFIL (ID),
    constraint USUARIO_PERFIL__FK
        foreign key (usuario_id) references USUARIO (ID)
);


INSERT INTO receitas (data, descricao, valor) VALUES('2022-08-20', 'descricaoAtu', 18.00);
INSERT INTO receitas (data, descricao, valor) VALUES( '2022-06-20', 'descricaoAtu', 18.00);
INSERT INTO receitas ( data, descricao, valor) VALUES( '2022-06-20', 'descricao', 18.00);

INSERT INTO despesas ( data, descricao, valor, categorias) VALUES('2022-06-20', 'descricao', 18.00, 'OUTRAS');
INSERT INTO despesas ( data, descricao, valor, categorias) VALUES( '2022-06-20', 'alugelD', 18.00, 'OUTRAS');
INSERT INTO despesas ( data, descricao, valor, categorias) VALUES( '2022-08-20', 'descricao', 20.00, 'OUTRAS');
INSERT INTO despesas ( data, descricao, valor, categorias) VALUES( '2022-07-20', 'aaaaaaaaaaaaaaaa', 18.00, 'OUTRAS');

INSERT INTO perfil ( nome) VALUES('USUARIO');

INSERT INTO usuario ( email, nome, senha) VALUES('teste@gmail.com', 'nome', '$2a$10$./f1RQvh4Uo3rSsiOetDMeCKHsorsHzez7Z7najauKoBUABAPLRVy');
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES(1, 1);
