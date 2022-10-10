

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
