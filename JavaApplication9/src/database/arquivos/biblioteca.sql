CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE bibliotecaria(
	id_usuario int auto_increment not null primary key,
    nome varchar(100) not null,
    cpf varchar(14),
    cel varchar(15) not null,
    usuario varchar(60) not null,
    senha varchar(10) not null,
    email varchar(100)
);
    
CREATE TABLE livro(
	id_livro int auto_increment not null primary key,
	titulo varchar(100) not null,
	data_livro date not null,
    quantidade_livro int not null,
	cdd varchar(100),
	cutter varchar(100),
	complemento varchar(100),
	autor1 varchar(100) not null,
	autor2 varchar(100),
	autor3 varchar(100),
	tradutores varchar(100),
	ilustradores varchar(100),
	assunto varchar(100),
	local_livro varchar(100),
	editora varchar(100),
	ano varchar(100),
	edicao varchar(100)
);

CREATE TABLE aluno(
	id_aluno int auto_increment not null primary key,
    nome varchar(100) not null,
    telefone varchar(50) not null,
    email varchar(100),
    complemento varchar(100),
    matricula varchar(50),
    turma varchar(50)
);

CREATE TABLE aluguel(
	id_aluguel int auto_increment not null primary key,
    data_aluguel date not null,
    aluno_id int not null,
    livro_id int,
    data_devolucao date not null,
    devolvido boolean,
    data_devolvido date default '2018/01/01',
    CONSTRAINT fk_aluguel_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id_aluno), -- ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_aluguel_livro FOREIGN KEY (livro_id) REFERENCES  livro(id_livro) -- ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE itensdealuguel(
   id_itemdealuguel int auto_increment not null primary key,
   quantidade int not null,
   livro_id int not null,
   aluguel_id int not null,
   CONSTRAINT fk_itensdealuguel_livro FOREIGN KEY(livro_id) REFERENCES livro(id_livro), -- ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_itensdealuguel_aluguel FOREIGN KEY(aluguel_id) REFERENCES aluguel(id_aluguel) -- ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO bibliotecaria(nome, cpf, cel, usuario, senha) 
VALUES ('Admnistrador','999.999.999-99','82999999999','admin','admin');

INSERT INTO livro(titulo, data_livro, quantidade_livro, cdd, cutter, autor1)
VALUES ('NomeDoLivro','01/01/10',5,'Exemplo','Exemplo','Exemplo');

INSERT INTO aluno (nome, telefone) 
VALUES ('AlunoExemploTeste123456','99999-9999');

INSERT INTO aluguel (data_aluguel, aluno_id, livro_id,data_devolucao) 
VALUES ('2018/01/10',1,1,'2018/01/12');

INSERT INTO itensdealuguel(quantidade, livro_id, aluguel_id) 
VALUES('1', '1', '1');

select * from aluguel;

-- select count(id_aluguel), extract(year from data_aluguel) as ano, extract(month from data_aluguel) as mes from aluguel group by ano, mes order by ano, mes;
-- drop database biblioteca;