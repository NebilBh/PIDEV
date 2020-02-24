-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 24 fév. 2020 à 21:22
-- Version du serveur :  10.1.38-MariaDB
-- Version de PHP :  5.6.40

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

CREATE TABLE `admin` (
  `idUsr` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chasseur_talent`
--

CREATE TABLE `chasseur_talent` (
  `idUsr` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `entreprise` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chasseur_talent`
--

INSERT INTO `chasseur_talent` (`idUsr`, `nom`, `prenom`, `tel`, `mail`, `login`, `mdp`, `age`, `entreprise`, `image`, `date`) VALUES
(1, 'Recrutement', 'Imed', '98753165', 'ImedR@outlook.com', 'Imed68', '123', 45, 'Microsoft', 'C:/Users/Nebil/Desktop/PIDEV/nozelites/src/GUI/images/trash.png', '2020-02-23');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_commentaire` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  `id_publication` int(11) NOT NULL,
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `idc` int(11) NOT NULL,
  `idE` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `description` varchar(255) NOT NULL,
  `siteWeb` varchar(255) NOT NULL,
  `NbParticipant` int(11) NOT NULL,
  `NbPlace` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `etat` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE `favoris` (
  `id_fav` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id_formation` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `id_membre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `id_groupe` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `autorisation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`id_groupe`, `titre`, `description`, `autorisation`) VALUES
(1, 'java', 'java forum , projets', 1),
(2, 'c+++', 'c++ forum', 0),
(3, 'python', 'formations projets', 1),
(5, 'marketing', 'workshop', 1),
(6, 'symphony+', 'php , workshop', 0),
(7, 'c#', 'tuto', 1),
(8, 'JAVA+', 'dzzdsfdf', 1);

-- --------------------------------------------------------

--
-- Structure de la table `groupe_membre`
--

CREATE TABLE `groupe_membre` (
  `id_gm` int(11) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  `id_invite` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `listediplome`
--

CREATE TABLE `listediplome` (
  `id_diplome` int(11) NOT NULL,
  `organisation` varchar(11) NOT NULL,
  `domaine` varchar(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `listediplome`
--

INSERT INTO `listediplome` (`id_diplome`, `organisation`, `domaine`, `id_membre`, `date`) VALUES
(0, 'harvard', 'BI', 7, '2020-02-24');

-- --------------------------------------------------------

--
-- Structure de la table `listparticipant`
--

CREATE TABLE `listparticipant` (
  `idE` int(11) NOT NULL,
  `idm` int(11) NOT NULL,
  `etatp` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE `membre` (
  `idUsr` int(11) NOT NULL,
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
  `image` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `membre`
--

INSERT INTO `membre` (`idUsr`, `nom`, `prenom`, `tel`, `mail`, `login`, `mdp`, `age`, `Formation`, `Experience`, `Type`, `image`, `date`) VALUES
(7, 'Ben Hamouda', 'Nebil', '22145654', 'nebilbh@esprit.tn', 'nebil98', '123654', 21, '0', '10 ans', 0, 'C:/Users/Nebil/Desktop/PIDEV/nozelites/src/GUI/images/Annotation 2020-02-02 202038.png', '2020-02-24'),
(8, 'lasaad', 'Mornag', '98123456', 'lasaad@mornag.com', 'lasaad63', '123', 20, '0', '40 ans', 0, 'C:/Users/Nebil/Desktop/PIDEV/nozelites/src/GUI/images/default.png', '2020-02-24'),
(9, 'torkhani', 'raif', '22123589', 'bentelma3mel@gmail.com', 'torkhani69', '123', 60, '0', '30 ans', 0, 'C:/Users/Nebil/Desktop/PIDEV/nozelites/src/GUI/images/default.png', '2020-02-24');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `idMessage` int(11) NOT NULL,
  `objet` varchar(255) NOT NULL,
  `texte` varchar(255) NOT NULL,
  `id_destinataire` int(11) NOT NULL,
  `id_emeteur` int(11) NOT NULL,
  `date` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `Id` int(11) NOT NULL,
  `Type` varchar(30) NOT NULL,
  `IdEmetteur` int(11) NOT NULL,
  `IdRecepteur` int(11) NOT NULL,
  `Entreprise` varchar(30) NOT NULL,
  `Domaine` varchar(100) NOT NULL,
  `Poste` varchar(100) NOT NULL,
  `Requis` varchar(250) NOT NULL,
  `Description` varchar(250) NOT NULL,
  `Date` varchar(30) NOT NULL,
  `Etat` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`Id`, `Type`, `IdEmetteur`, `IdRecepteur`, `Entreprise`, `Domaine`, `Poste`, `Requis`, `Description`, `Date`, `Etat`) VALUES
(4, 'emploi', 1, 7, 'Microsoft', 'azea', 'aze', 'zae', 'aze', 'aze', 'Acceptée'),
(5, 'azeza', 1, 8, 'aze', 'aze', 'aze', 'aze', 'aze', 'aza', 'Acceptée'),
(6, 'azeaz', 1, 9, 'zae', 'aze', 'aze', 'aze', 'azea', 'azeaz', 'Acceptée');

-- --------------------------------------------------------

--
-- Structure de la table `portfolio`
--

CREATE TABLE `portfolio` (
  `id_port` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `lien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_groupe` int(11) NOT NULL,
  `id_publicateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `idRecl` int(11) NOT NULL,
  `id_emeteur` int(11) NOT NULL,
  `id_cible` int(11) NOT NULL,
  `description` varchar(256) NOT NULL,
  `etat` int(20) NOT NULL,
  `selecteur` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `nb_jaime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idUsr`),
  ADD UNIQUE KEY `CK_UNIQUE` (`login`);

--
-- Index pour la table `chasseur_talent`
--
ALTER TABLE `chasseur_talent`
  ADD PRIMARY KEY (`idUsr`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`),
  ADD KEY `FK_comm_membre` (`id_membre`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`idE`),
  ADD UNIQUE KEY `nom` (`nom`),
  ADD KEY `FK_idCreateur` (`idc`);

--
-- Index pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD PRIMARY KEY (`id_fav`),
  ADD KEY `FK_FAV_MEMBRE` (`id_membre`),
  ADD KEY `FK_FAV_PUBLC` (`id_pub`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id_formation`),
  ADD KEY `fk_formation_id_membre` (`id_membre`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`id_groupe`);

--
-- Index pour la table `groupe_membre`
--
ALTER TABLE `groupe_membre`
  ADD PRIMARY KEY (`id_gm`),
  ADD KEY `FK_idG` (`id_groupe`),
  ADD KEY `FK_idM` (`id_membre`);

--
-- Index pour la table `listediplome`
--
ALTER TABLE `listediplome`
  ADD PRIMARY KEY (`id_diplome`),
  ADD KEY `FK_id_membre` (`id_membre`);

--
-- Index pour la table `listparticipant`
--
ALTER TABLE `listparticipant`
  ADD KEY `fk_ide` (`idE`),
  ADD KEY `id_m` (`idm`);

--
-- Index pour la table `membre`
--
ALTER TABLE `membre`
  ADD PRIMARY KEY (`idUsr`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`idMessage`),
  ADD KEY `fk_destinataire` (`id_destinataire`),
  ADD KEY `fk_emetteur` (`id_emeteur`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FK_idEmetteur` (`IdEmetteur`),
  ADD KEY `Fk_idRecepteur` (`IdRecepteur`);

--
-- Index pour la table `portfolio`
--
ALTER TABLE `portfolio`
  ADD PRIMARY KEY (`id_port`),
  ADD KEY `Fk_membre` (`id_membre`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_groupe` (`id_groupe`),
  ADD KEY `FK_idpublicateur` (`id_publicateur`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`idRecl`),
  ADD KEY `fk_id_emeteur` (`id_emeteur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `chasseur_talent`
--
ALTER TABLE `chasseur_talent`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `idE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `favoris`
--
ALTER TABLE `favoris`
  MODIFY `id_fav` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id_formation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `id_groupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `groupe_membre`
--
ALTER TABLE `groupe_membre`
  MODIFY `id_gm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `membre`
--
ALTER TABLE `membre`
  MODIFY `idUsr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `idMessage` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `portfolio`
--
ALTER TABLE `portfolio`
  MODIFY `id_port` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `idRecl` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_comm_membre` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_idCreateur` FOREIGN KEY (`idc`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `FK_FAV_MEMBRE` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_FAV_PUBLC` FOREIGN KEY (`id_pub`) REFERENCES `publication` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
  ADD CONSTRAINT `fk_formation_id_membre` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `groupe_membre`
--
ALTER TABLE `groupe_membre`
  ADD CONSTRAINT `FK_idG` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_idM` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `listediplome`
--
ALTER TABLE `listediplome`
  ADD CONSTRAINT `FK_id_membre` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `FK_idEmetteur` FOREIGN KEY (`IdEmetteur`) REFERENCES `chasseur_talent` (`idUsr`) ON DELETE CASCADE,
  ADD CONSTRAINT `Fk_idRecepteur` FOREIGN KEY (`IdRecepteur`) REFERENCES `membre` (`idUsr`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
