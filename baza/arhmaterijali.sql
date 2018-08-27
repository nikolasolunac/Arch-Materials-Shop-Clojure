/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.20-log : Database - arhmaterijali
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`arhmaterijali` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `arhmaterijali`;

/*Table structure for table `artikal` */

DROP TABLE IF EXISTS `artikal`;

CREATE TABLE `artikal` (
  `ArtikalID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) DEFAULT NULL,
  `Opis` varchar(45) DEFAULT NULL,
  `Cena` double DEFAULT '0',
  `JedinicaMere` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ArtikalID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `artikal` */

insert  into `artikal`(`ArtikalID`,`Naziv`,`Opis`,`Cena`,`JedinicaMere`) values (1,'Kartonska lepenka','Dimenzije 100x100',450,'kom'),(2,'Matirana folija','Dimenzije 42x30',300,'kom'),(3,'Karton pena','Dimenzije 100x100',400,'kom');

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `KupacID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Telefon` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`KupacID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `kupac` */

insert  into `kupac`(`KupacID`,`Ime`,`Prezime`,`Username`,`Telefon`,`Email`) values (1,'Stefan','Jaksic','stefanjaksic','064/333444','sjaksic@yahoo.com'),(2,'Andrija','Bogunovic','andrijabogunovic','060/555444','abogunovic@gmail.com'),(3,'Slobodan','Milosavljevic','slobamilosavljevic','065/555777','smilosavljevic@yahoo.com'),(4,'Marko','Maricic','markomaricic','062/555111','mmaricic@yahoo.com');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `BrojRacuna` int(11) NOT NULL AUTO_INCREMENT,
  `DatumKupovine` datetime DEFAULT NULL,
  `UkupanIznos` double DEFAULT NULL,
  `KupacID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BrojRacuna`),
  KEY `KupacID_fk` (`KupacID`),
  CONSTRAINT `KupacID_fk` FOREIGN KEY (`KupacID`) REFERENCES `Kupac` (`KupacID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `racun` */

insert  into `racun`(`BrojRacuna`,`DatumKupovine`,`UkupanIznos`,`KupacID`) values (1,'2018-08-23 13:00:00',1000,2),(2,'2018-08-24 16:00:00',600,1);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `RBStavke` int(11) NOT NULL AUTO_INCREMENT,
  `BrojRacuna` int(11) NOT NULL,
  `IznosStavke` double DEFAULT NULL,
  `ArtikalID` int(11) DEFAULT NULL,
  `Kolicina` int(11) DEFAULT NULL,
  PRIMARY KEY (`RBStavke`,`BrojRacuna`),
  KEY `RBStavke` (`RBStavke`),
  KEY `BrojRacuna_fk` (`BrojRacuna`),
  KEY `Artikal_fk` (`ArtikalID`),
  CONSTRAINT `BrojRacuna_fk` FOREIGN KEY (`BrojRacuna`) REFERENCES `racun` (`BrojRacuna`),
  CONSTRAINT `Artikal_fk` FOREIGN KEY (`ArtikalID`) REFERENCES `artikal` (`ArtikalID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`RBStavke`,`BrojRacuna`,`IznosStavke`,`ArtikalID`,`Kolicina`) values (1,1,300,2,2),(2,1,400,3,1),(1,2,300,2,2);

/*Table structure for table `tipartikla` */

DROP TABLE IF EXISTS `tipartikla`;

CREATE TABLE `tipartikla` (
  `TipArtiklaID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TipArtiklaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tipartikla` */

insert  into `tipartikla`(`TipArtiklaID`,`Naziv`) values (1,'Kartoni'),(2,'Drvo'),(3,'Folije'),(4,'Lepak'),(5,'Boje'),(6,'Gips');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
