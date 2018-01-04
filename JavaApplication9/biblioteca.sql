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

INSERT INTO bibliotecaria(nome, cpf, cel, usuario, senha)
	VALUES ('Admnistrador','999.999.999-99','82999999999','admin','admin');
    
CREATE TABLE livro(
	id_livro int auto_increment not null primary key,
	titulo varchar(100) not null,
	data_livro date not null,
	cdd varchar(100) not null,
	cutter varchar(100) not null,
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

-- alter table livro change column data_livro data_livro date; ALTERADO SOMENTE PARA TESTES, LEMBRAR DE REVERTER PARA NOT NULL

CREATE TABLE alunos(
	id_aluno int auto_increment not null primary key,
    nome varchar(100) not null,
    telefone varchar(50)
);

CREATE TABLE aluguel(
	id_aluguel int auto_increment not null primary key,
    data_aluguel date not null,
    aluno_id int,
    livro_id int,
    CONSTRAINT fk_aluguel_alunos FOREIGN KEY (aluno_id) REFERENCES alunos(id_aluno),
    CONSTRAINT fk_aluguel_livro FOREIGN KEY (livro_id) REFERENCES  livro(id_livro)
);

select * from bibliotecaria;

INSERT INTO bibliotecaria(nome, cpf, cel, usuario, senha)
	VALUES ('Teste','999.999.999-99','82999999999','admin','admin');

-- insert into livro(titulo, data_livro, cdd, cutter, autor1) values ('teste','2017/12/30','test','cutter','autor');