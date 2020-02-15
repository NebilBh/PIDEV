-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 15 fév. 2020 à 13:44
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `nozelites`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `idUsr` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idUsr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chasseur_talent`
--

DROP TABLE IF EXISTS `chasseur_talent`;
CREATE TABLE IF NOT EXISTS `chasseur_talent` (
  `idUsr` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `entreprise` varchar(255) NOT NULL,
  PRIMARY KEY (`idUsr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `idc` int(11) NOT NULL,
  `idE` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `description` varchar(255) NOT NULL,
  `siteWeb` varchar(255) NOT NULL,
  `NbParticipant` int(11) NOT NULL,
  `NbPlace` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`idE`),
  KEY `FK_idCreateur` (`idc`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `autorisation` int(11) NOT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupe_membre`
--

DROP TABLE IF EXISTS `groupe_membre`;
CREATE TABLE IF NOT EXISTS `groupe_membre` (
  `id_gm` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupe` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  `id_invite` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  PRIMARY KEY (`id_gm`),
  KEY `FK_idG` (`id_groupe`),
  KEY `FK_idM` (`id_membre`),
  KEY `fk_invite` (`id_invite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `listediplome`
--

DROP TABLE IF EXISTS `listediplome`;
CREATE TABLE IF NOT EXISTS `listediplome` (
  `id_diplome` int(11) NOT NULL,
  `organisation` int(11) NOT NULL,
  `domaine` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  KEY `Fk_membrePr` (`id_membre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `listparticipant`
--

DROP TABLE IF EXISTS `listparticipant`;
CREATE TABLE IF NOT EXISTS `listparticipant` (
  `idE` int(11) NOT NULL,
  `idm` int(11) NOT NULL,
  KEY `fk_ide` (`idE`),
  KEY `id_m` (`idm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

DROP TABLE IF EXISTS `membre`;
CREATE TABLE IF NOT EXISTS `membre` (
  `idUsr` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `login` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `Formation` varchar(255) NOT NULL,
  `Experience` varchar(255) NOT NULL,
  `Type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `objet` varchar(255) NOT NULL,
  `texte` varchar(255) NOT NULL,
  `id_destinataire` int(11) NOT NULL,
  `id_emeteur` int(11) NOT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `fk_destinataire` (`id_destinataire`),
  KEY `fk_emetteur` (`id_emeteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

DROP TABLE IF EXISTS `offre`;
CREATE TABLE IF NOT EXISTS `offre` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(30) NOT NULL,
  `IdEmetteur` int(11) NOT NULL,
  `IdRecepteur` int(11) NOT NULL,
  `Entreprise` varchar(30) NOT NULL,
  `Domaine` varchar(100) NOT NULL,
  `Poste` varchar(100) NOT NULL,
  `Requis` varchar(250) NOT NULL,
  `Description` varchar(250) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_idEmetteur` (`IdEmetteur`),
  KEY `Fk_idRecepteur` (`IdRecepteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `portfolio`
--

DROP TABLE IF EXISTS `portfolio`;
CREATE TABLE IF NOT EXISTS `portfolio` (
  `id_port` int(11) NOT NULL AUTO_INCREMENT,
  `images` int(11) NOT NULL,
  `projets` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  PRIMARY KEY (`id_port`),
  KEY `Fk_membre` (`id_membre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `idPub` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  `id_publicateur` int(11) NOT NULL,
  PRIMARY KEY (`idPub`),
  KEY `FK_groupe` (`id_groupe`),
  KEY `FK_idpublicateur` (`id_publicateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `idRecl` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `etat` int(11) NOT NULL,
  `id_emeteur` int(11) NOT NULL,
  `id_cible` int(11) NOT NULL,
  `selecteur` varchar(255) NOT NULL,
  PRIMARY KEY (`idRecl`),
  KEY `fk_id_emeteur` (`id_emeteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_idCreateur` FOREIGN KEY (`idc`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe_membre`
--
ALTER TABLE `groupe_membre`
  ADD CONSTRAINT `FK_idG` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idM` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_invite` FOREIGN KEY (`id_invite`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `listediplome`
--
ALTER TABLE `listediplome`
  ADD CONSTRAINT `Fk_id_membre` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_membrePr` FOREIGN KEY (`id_membre`) REFERENCES `membre_premium` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `listparticipant`
--
ALTER TABLE `listparticipant`
  ADD CONSTRAINT `fk_ide` FOREIGN KEY (`idE`) REFERENCES `evenement` (`idE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `listparticipant_ibfk_1` FOREIGN KEY (`idm`) REFERENCES `membre` (`idUsr`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_destinataire` FOREIGN KEY (`id_destinataire`) REFERENCES `membre` (`idUsr`),
  ADD CONSTRAINT `fk_emetteur` FOREIGN KEY (`id_emeteur`) REFERENCES `membre` (`idUsr`);

--
-- Contraintes pour la table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `FK_idEmetteur` FOREIGN KEY (`IdEmetteur`) REFERENCES `chasseur_talent` (`idUsr`),
  ADD CONSTRAINT `Fk_idRecepteur` FOREIGN KEY (`IdRecepteur`) REFERENCES `membre` (`idUsr`);

--
-- Contraintes pour la table `portfolio`
--
ALTER TABLE `portfolio`
  ADD CONSTRAINT `Fk_membre` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `FK_groupe` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idpublicateur` FOREIGN KEY (`id_publicateur`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_id_emeteur` FOREIGN KEY (`id_emeteur`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
