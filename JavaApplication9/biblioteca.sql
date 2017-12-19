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

select * from livro;

-- insert into livro(titulo, data_livro, cdd, cutter, autor1) values ('teste','2017/12/30','test','cutter','autor');