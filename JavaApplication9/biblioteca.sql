-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 13-Dez-2017 às 15:01
-- Versão do servidor: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `bibliotecaria`
--

CREATE TABLE `bibliotecaria` (
  `id_usuario` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `cel` varchar(15) DEFAULT NULL,
  `usuario` varchar(60) NOT NULL,
  `senha` varchar(10) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `bibliotecaria`
--

INSERT INTO `bibliotecaria` (`id_usuario`, `nome`, `cpf`, `cel`, `usuario`, `senha`, `email`) VALUES
(1, 'Administrador', '999.999.999-99', '82-99999-9999', 'admin', 'admin', 'estevaogabrielsr@outlook.com.br');

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

CREATE TABLE `livro` (
  `id_livro` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `data_livro` date NOT NULL,
  `cdd` varchar(100) NOT NULL,
  `cutter` varchar(100) NOT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `autor1` varchar(100) NOT NULL,
  `autor2` varchar(100) DEFAULT NULL,
  `autor3` varchar(100) DEFAULT NULL,
  `tradutores` varchar(100) DEFAULT NULL,
  `ilustradores` varchar(100) DEFAULT NULL,
  `assunto` varchar(100) DEFAULT NULL,
  `local_livro` varchar(100) DEFAULT NULL,
  `editora` varchar(100) DEFAULT NULL,
  `ano` varchar(100) DEFAULT NULL,
  `edicao` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`id_livro`, `titulo`, `data_livro`, `cdd`, `cutter`, `complemento`, `autor1`, `autor2`, `autor3`, `tradutores`, `ilustradores`, `assunto`, `local_livro`, `editora`, `ano`, `edicao`) VALUES
(1, 'Teste Livro', '2017-12-13', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', 'Teste Livro', '2017', 'Teste Livro');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bibliotecaria`
--
ALTER TABLE `bibliotecaria`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`id_livro`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bibliotecaria`
--
ALTER TABLE `bibliotecaria`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `livro`
--
ALTER TABLE `livro`
  MODIFY `id_livro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
