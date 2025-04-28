-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbo
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gc_alerta`
--

DROP TABLE IF EXISTS `gc_alerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_alerta` (
  `N_ALERTA_ID` int NOT NULL AUTO_INCREMENT,
  `N_SERVICIOS_ID` int NOT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `S_MENSAJE` varchar(1024) NOT NULL,
  `S_TIPO` varchar(15) NOT NULL,
  `FH_NOTIFICACION` datetime DEFAULT NULL,
  `B_CORREO` smallint NOT NULL,
  `B_PANTALLA` smallint NOT NULL,
  `C_USUARIO_NOTIFICA` varchar(1024) DEFAULT NULL,
  `S_CORREO_NOTIFICA` varchar(1024) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  PRIMARY KEY (`N_ALERTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_alerta`
--

LOCK TABLES `gc_alerta` WRITE;
/*!40000 ALTER TABLE `gc_alerta` DISABLE KEYS */;
INSERT INTO `gc_alerta` VALUES (1,2,'80052','Aviso','0','2016-05-19 00:00:00',0,1,'All',NULL,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00',1),(2,2,'80048','buenas noches','0','2024-02-22 20:00:23',0,1,'All','','ss.administrador','ss.administrador','2024-02-22 19:59:00','2024-02-23 19:59:00',NULL,1),(3,2,'80048','wad','0','2024-05-02 22:57:30',0,1,'GC_R4','','ss.administrador','ss.administrador','2024-06-06 22:57:00','2024-06-05 22:57:00',NULL,1);
/*!40000 ALTER TABLE `gc_alerta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_alumno`
--

DROP TABLE IF EXISTS `gc_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_alumno` (
  `ncarnet` varchar(12) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `facultad` varchar(45) NOT NULL,
  `carrera` varchar(45) NOT NULL,
  `ciclo` varchar(45) NOT NULL,
  PRIMARY KEY (`ncarnet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_alumno`
--

LOCK TABLES `gc_alumno` WRITE;
/*!40000 ALTER TABLE `gc_alumno` DISABLE KEYS */;
INSERT INTO `gc_alumno` VALUES ('100389','Krispin','Fontel','Medicina','Biology','02/2024'),('100462','Milty','Simecek','Biologia','Psychology','02/2024'),('100464','Pryce','Scarsbrick','Economia','English','02/2024'),('100675','Tedman','Raccio','Ingenieria y arquitectura','History','01/2024'),('100758','Bentley','Tooher','Ingenieria y arquitectura','English','02/2024'),('101022','Judith','Dunseith','Biologia','Psychology','01/2024'),('101266','Cecelia','McGrorty','Economia','Chemistry','02/2024'),('101298','Haroun','Cargen','Medicina','Chemistry','01/2024'),('10145','Gayle','Kaley','Economia','Chemistry','01/2024'),('101486','Burty','Marzelle','Ingenieria y arquitectura','Computer Science','01/2024'),('101793','Paddy','Goodband','Ingenieria y arquitectura','Mathematics','01/2023'),('101875','Pauline','Sarginson','Biologia','Mathematics','02/2023'),('102016','Jerald','Dymocke','Biologia','Mathematics','01/2023'),('102241','Molly','O\'Dea','Medicina','Chemistry','02/2024'),('102519','Roanna','Mc Gee','Derecho','Biology','01/2023'),('102550','Johnna','Harsnipe','Derecho','History','01/2024'),('10278','Scotti','Smaile','Biologia','Mathematics','02/2023'),('102828','Clarette','Bosma','Economia','History','01/2023'),('103515','Thea','Chell','Biologia','Psychology','02/2023'),('104019','Bradly','Llewelyn','Derecho','English','01/2023'),('104614','Jaclin','Crump','Ingenieria y arquitectura','Biology','02/2024'),('104889','Heddie','MacGettigen','Economia','Mathematics','02/2024'),('105167','Seana','Warn','Ingenieria y arquitectura','Computer Science','02/2024'),('105222','Harriet','Cashley','Ingenieria y arquitectura','Mathematics','02/2023'),('105543','Elnora','Truce','Economia','Computer Science','01/2024'),('105590','Ingelbert','Lambin','Economia','Psychology','02/2023'),('105711','Veronica','Parton','Medicina','Chemistry','01/2024'),('105721','Rozanne','Grills','Economia','Chemistry','02/2023'),('105924','Lotta','Rois','Economia','Biology','02/2023'),('105939','Deanna','Dilnot','Economia','Psychology','02/2024'),('105985','Buffy','Cashin','Economia','Psychology','02/2024'),('106418','Yolane','Segges','Biologia','Chemistry','02/2024'),('106701','Matthaeus','Eberle','Ingenieria y arquitectura','Mathematics','02/2023'),('106711','Veronika','Tzar','Ingenieria y arquitectura','Psychology','02/2024'),('106954','Alric','Carson','Economia','Computer Science','02/2023'),('10705','Meyer','Yuille','Ingenieria y arquitectura','History','02/2023'),('107051','Hort','Pingston','Economia','Computer Science','01/2023'),('107195','Piggy','Folliss','Economia','Mathematics','01/2024'),('10724','Ave','Agnolo','Derecho','Computer Science','01/2024'),('107257','Sascha','Warby','Derecho','Computer Science','02/2024'),('107379','Tiffani','Tant','Medicina','Biology','01/2023'),('107680','Catlee','Beadle','Derecho','Computer Science','02/2023'),('107706','Candace','Musk','Medicina','Computer Science','02/2023'),('108045','Loren','Bartunek','Economia','English','01/2024'),('108057','Dorthea','Darville','Ingenieria y arquitectura','Biology','01/2023'),('108077','Chaddie','Dagon','Medicina','English','01/2024'),('108589','Arliene','Burland','Medicina','History','02/2023'),('108670','Denis','Linsey','Ingenieria y arquitectura','Biology','01/2023'),('108761','Glenna','McKeurtan','Derecho','Computer Science','02/2023'),('108897','Pamela','Petras','Biologia','History','01/2023'),('109144','Scarlett','Brimilcombe','Economia','Mathematics','02/2024'),('109256','Boycey','Kneafsey','Biologia','Psychology','01/2024'),('109550','Moreen','Davidovics','Economia','History','01/2023'),('110234','Kandy','Parkman','Derecho','Mathematics','01/2023'),('110428','Alix','Tooth','Economia','History','02/2024'),('110596','Sephira','Reape','Medicina','Psychology','01/2024'),('110746','Victoria','Draysay','Ingenieria y arquitectura','Mathematics','01/2024'),('111143','Codi','Feighry','Medicina','Chemistry','01/2024'),('111190','Ernesto','Stodit','Medicina','Computer Science','02/2024'),('111287','Chalmers','Swetman','Derecho','Computer Science','01/2024'),('11140','Gwendolyn','Slyne','Economia','Psychology','01/2024'),('111540','Philipa','Pendleberry','Ingenieria y arquitectura','Biology','01/2024'),('111689','Dorelia','Dimitru','Economia','Computer Science','02/2024'),('111933','Elenore','Eusden','Economia','English','01/2024'),('112772','Jude','Rowantree','Biologia','History','02/2024'),('112942','Archambault','Lacey','Derecho','English','02/2023'),('113138','Claudette','Preshaw','Derecho','History','02/2023'),('113271','Chaddy','Bodell','Derecho','English','01/2024'),('113318','Arley','Ellph','Economia','History','02/2023'),('113384','Tallia','Eckels','Ingenieria y arquitectura','Mathematics','02/2024'),('113545','Buddy','Epp','Economia','Computer Science','02/2023'),('113588','Chrotoem','Averall','Derecho','Biology','02/2024'),('113692','Barris','Giabuzzi','Biologia','Computer Science','02/2023'),('113804','Joscelin','Stonebridge','Ingenieria y arquitectura','English','02/2024'),('113872','Ardis','Dowty','Derecho','Psychology','01/2024'),('114364','Fee','Dust','Economia','Mathematics','01/2023'),('114447','Eldon','Maro','Economia','Computer Science','02/2023'),('114448','Flori','Marages','Biologia','Mathematics','02/2024'),('11447','Abbie','Kneale','Ingenieria y arquitectura','Computer Science','02/2024'),('114476','Kurt','Giacoboni','Derecho','Chemistry','02/2023'),('114531','Bastien','Revington','Biologia','Computer Science','02/2024'),('115065','Baudoin','Russell','Ingenieria y arquitectura','Biology','01/2023'),('115576','Bren','Keasey','Ingenieria y arquitectura','Computer Science','01/2024'),('115606','Etty','Covolini','Economia','Chemistry','02/2024'),('115647','Raychel','Napolitano','Economia','English','01/2024'),('115737','Tessa','Browning','Medicina','History','01/2023'),('115872','Nefen','Quinane','Economia','English','01/2024'),('116168','Antin','Cours','Medicina','History','01/2023'),('116290','Ollie','Canfield','Biologia','English','02/2024'),('117136','Alley','Elmhirst','Derecho','English','02/2023'),('11719','Humfrid','Craik','Economia','Biology','01/2023'),('117342','Agnola','Barcke','Medicina','Psychology','01/2024'),('117436','Clarisse','Skace','Biologia','English','02/2023'),('117605','Junette','Lynd','Derecho','History','01/2024'),('11764','Lucie','Abbs','Ingenieria y arquitectura','Mathematics','02/2024'),('117720','Laureen','Borrott','Derecho','History','02/2024'),('118089','Saudra','Clitheroe','Economia','Chemistry','01/2023'),('118141','Eddy','Smallpiece','Economia','Mathematics','02/2024'),('118148','Roxane','Casotti','Derecho','Psychology','01/2023'),('118323','Chicky','Dufour','Derecho','English','01/2024'),('118577','Holmes','Bydaway','Ingenieria y arquitectura','History','01/2023'),('118589','Sharlene','Lucien','Ingenieria y arquitectura','History','02/2023'),('118630','Corabella','Lidbetter','Biologia','Biology','01/2024'),('118720','Myra','Meachan','Economia','Biology','01/2024'),('118758','Hermione','Sprade','Ingenieria y arquitectura','Computer Science','02/2024'),('118974','Pacorro','Jerosch','Derecho','Psychology','01/2024'),('118983','Desdemona','Berthomieu','Derecho','Computer Science','01/2024'),('11900','Calhoun','Luton','Economia','Psychology','01/2024'),('119189','Cody','Cuningham','Ingenieria y arquitectura','Chemistry','01/2023'),('119379','Madeline','Kelsell','Economia','History','02/2024'),('119556','Freida','Presnell','Economia','Biology','01/2023'),('119769','Heindrick','Atwel','Medicina','Chemistry','02/2023'),('120264','Cory','Mallaby','Medicina','Computer Science','02/2023'),('120379','Violette','Raunds','Biologia','Chemistry','01/2023'),('120613','Amalee','Edgeley','Medicina','History','01/2024'),('120660','Gib','Jewel','Derecho','Psychology','01/2023'),('120668','Augustus','Castelijn','Derecho','Chemistry','01/2023'),('121002','Curtice','Hollyman','Ingenieria y arquitectura','Chemistry','01/2024'),('121068','Jada','Snelgrove','Derecho','Chemistry','01/2024'),('121629','Noam','Keigher','Economia','Psychology','01/2024'),('121733','Charita','Birney','Medicina','Computer Science','02/2024'),('12183','Archibold','Coltman','Economia','Chemistry','01/2023'),('121893','Trish','Worham','Biologia','Computer Science','02/2024'),('122118','Currie','Goulthorp','Derecho','English','01/2023'),('122570','Booth','Boustred','Ingenieria y arquitectura','Chemistry','01/2023'),('122751','Saraann','Wiffen','Economia','History','02/2024'),('122790','Stacie','O\'Gormally','Ingenieria y arquitectura','English','01/2024'),('122885','Adiana','Packer','Ingenieria y arquitectura','Mathematics','01/2023'),('123104','Allsun','Hebborn','Ingenieria y arquitectura','History','02/2023'),('123200','Bendite','Danilenko','Economia','Psychology','02/2023'),('123347','Lolly','Moyser','Derecho','Psychology','01/2023'),('123663','Arne','Bemrose','Biologia','English','01/2024'),('123788','Merna','Duncklee','Economia','English','02/2023'),('123863','Edmon','O\'Brian','Derecho','Chemistry','01/2023'),('123990','Julia','Brunstan','Biologia','Mathematics','02/2024'),('124224','Collette','Batham','Economia','Chemistry','01/2023'),('124303','Ermina','Whiston','Economia','Mathematics','02/2024'),('124434','Lurette','Pinwill','Derecho','Psychology','02/2024'),('124524','Jarrad','Medd','Medicina','English','01/2024'),('124554','Lindy','McAloren','Biologia','English','01/2024'),('12478','Luise','Romanini','Biologia','Chemistry','01/2023'),('125543','Rogers','Townby','Derecho','Chemistry','02/2024'),('125559','Cesya','Shutte','Medicina','Computer Science','01/2023'),('125644','Veda','Eilhermann','Ingenieria y arquitectura','English','01/2023'),('125671','Cher','MacLeese','Derecho','Computer Science','01/2024'),('12573','Erv','Bruford','Biologia','Chemistry','01/2023'),('125789','Ronny','Weine','Ingenieria y arquitectura','Psychology','01/2023'),('125876','Malory','Grishin','Medicina','Psychology','02/2024'),('125895','Jamil','Roft','Ingenieria y arquitectura','Psychology','01/2023'),('125915','Brose','Wastling','Economia','Chemistry','01/2023'),('125945','Honor','McGeorge','Economia','Chemistry','02/2023'),('126633','Joya','Plowes','Ingenieria y arquitectura','Mathematics','01/2023'),('127108','Richard','Idney','Derecho','Mathematics','02/2024'),('127552','Alexandro','Vaughan-Hughes','Derecho','History','01/2023'),('127790','Jeniece','Dooley','Derecho','Biology','01/2023'),('127860','Daryle','Boggon','Medicina','Chemistry','02/2023'),('128402','Dennie','Calvey','Derecho','Psychology','01/2023'),('128404','Mylo','Hatchman','Ingenieria y arquitectura','Mathematics','02/2024'),('128488','Toinette','Comizzoli','Biologia','Mathematics','01/2024'),('128647','Luce','Piletic','Economia','Chemistry','02/2023'),('12877','Herrick','Lautie','Biologia','Computer Science','02/2023'),('128806','Vlad','Hugett','Economia','Computer Science','01/2023'),('129076','La verne','Seckington','Biologia','English','01/2023'),('129360','Sammie','Sandlin','Ingenieria y arquitectura','Psychology','02/2023'),('129451','Stirling','McGlew','Biologia','Biology','02/2023'),('129786','Dillie','Fullick','Derecho','Computer Science','02/2024'),('129806','Zorana','Akroyd','Ingenieria y arquitectura','Computer Science','01/2024'),('130405','Fifi','Malser','Economia','English','02/2023'),('130433','Gordon','Twigg','Medicina','History','02/2023'),('130553','Beryl','Fasson','Economia','Mathematics','02/2024'),('130660','Les','Hynam','Economia','English','01/2024'),('131156','Jilly','Pepler','Economia','Computer Science','02/2024'),('131427','Dawn','Boys','Biologia','Biology','01/2023'),('131497','Sergio','Karadzas','Ingenieria y arquitectura','Psychology','01/2023'),('131613','Amalie','Grieswood','Medicina','Psychology','02/2024'),('132027','Bastien','Elsey','Biologia','History','02/2023'),('132147','Rowen','Hammerstone','Biologia','English','01/2023'),('132259','Aggie','Helks','Ingenieria y arquitectura','Biology','01/2023'),('132301','Ron','Canedo','Derecho','History','01/2024'),('13255','Brittany','Pepineaux','Medicina','Chemistry','01/2024'),('132736','Hally','Parkin','Economia','History','02/2023'),('132786','Dawn','Demetr','Medicina','Computer Science','02/2023'),('133767','Alissa','Tuke','Ingenieria y arquitectura','Psychology','02/2023'),('133914','Luci','Sommerfeld','Derecho','Chemistry','01/2024'),('133925','Hank','Palatini','Economia','Computer Science','02/2024'),('133975','Teddi','Menaul','Derecho','Chemistry','01/2023'),('134680','Asa','Brattan','Derecho','Psychology','02/2023'),('134966','Millie','Yannikov','Derecho','Biology','02/2024'),('135083','Evita','Scholte','Economia','Biology','02/2024'),('135143','Noble','Kertess','Economia','History','02/2023'),('13561','Malina','Kingswell','Medicina','Psychology','02/2023'),('135662','Cloe','Kiossel','Ingenieria y arquitectura','Psychology','01/2023'),('135819','Darby','Abramovicz','Derecho','Psychology','02/2023'),('136718','Daveen','Danskine','Biologia','Psychology','02/2024'),('136921','Fiorenze','Giovanni','Economia','English','02/2023'),('137028','Elwyn','Cockram','Medicina','Biology','02/2024'),('137432','Xenos','Denniston','Biologia','Psychology','01/2024'),('137488','Lovell','Streatfeild','Derecho','Psychology','01/2023'),('137579','Margarita','Lynett','Medicina','Psychology','02/2024'),('137591','Ringo','Olekhov','Derecho','Computer Science','02/2023'),('137615','Gabie','Bartul','Ingenieria y arquitectura','History','01/2023'),('138049','Norrie','Dranfield','Ingenieria y arquitectura','Chemistry','01/2024'),('138167','Sidonnie','Lowndes','Ingenieria y arquitectura','Mathematics','01/2023'),('138181','Melvyn','Dracksford','Economia','Mathematics','01/2024'),('13825','Hazel','Cormode','Medicina','Psychology','02/2024'),('138305','Emmey','Droghan','Derecho','Chemistry','02/2023'),('138390','Kelci','Stentiford','Medicina','Chemistry','01/2024'),('138509','Joshia','Hawthorne','Derecho','Chemistry','02/2023'),('138556','Aurel','Moulton','Economia','Chemistry','01/2024'),('138650','Justen','Redler','Ingenieria y arquitectura','Mathematics','01/2023'),('138673','Amata','Phelp','Biologia','English','01/2023'),('138985','Brion','Bagnell','Biologia','Computer Science','02/2023'),('139325','Kennedy','Leeke','Derecho','Psychology','02/2024'),('139449','Edd','Trent','Economia','Chemistry','01/2023'),('139475','Adella','Tash','Ingenieria y arquitectura','History','01/2023'),('139799','Gav','Cardall','Derecho','English','01/2024'),('140010','Pavla','Rolfo','Ingenieria y arquitectura','Psychology','02/2023'),('14014','Karlene','Huggens','Biologia','Biology','01/2024'),('140179','Doti','Aldie','Economia','Psychology','01/2023'),('140275','Heywood','Bedow','Biologia','Psychology','02/2023'),('140322','Celene','Macon','Economia','History','02/2023'),('14057','Carter','Ames','Medicina','Biology','01/2023'),('140982','Austin','Piers','Economia','Psychology','02/2023'),('141790','Leigha','Klarzynski','Biologia','Chemistry','02/2023'),('14193','Janenna','Euler','Biologia','Computer Science','02/2024'),('141992','Debbi','Capoun','Biologia','English','02/2024'),('142000','Derrick','Smoth','Medicina','Chemistry','01/2023'),('142245','Giraldo','Rogliero','Biologia','Chemistry','02/2024'),('14244','Lek','Ickovici','Ingenieria y arquitectura','Mathematics','02/2024'),('142475','Slade','Feuell','Ingenieria y arquitectura','Biology','02/2024'),('143226','Wright','Zorzi','Biologia','Psychology','02/2023'),('143590','Eadmund','Szabo','Derecho','Computer Science','01/2024'),('143708','Ralph','Ummfrey','Biologia','History','02/2023'),('143709','Zonnya','Cardiff','Medicina','Computer Science','02/2023'),('143796','Nial','Arckoll','Derecho','Biology','02/2023'),('144081','Berthe','Jaquest','Medicina','Chemistry','01/2024'),('14409','Oliviero','Easterbrook','Biologia','Biology','01/2023'),('144295','Wakefield','McDonell','Derecho','Mathematics','01/2024'),('144313','Emelia','Hoyle','Economia','English','02/2023'),('144391','Stephana','Inde','Derecho','History','01/2023'),('144433','Isabel','Andrei','Biologia','History','01/2024'),('14449','Jodie','Woolvin','Derecho','Chemistry','01/2023'),('144809','Saul','Marte','Biologia','English','01/2023'),('14540','Kyla','Terren','Biologia','English','02/2023'),('145634','Hyman','Graddell','Ingenieria y arquitectura','History','02/2024'),('145653','Helen','Posselwhite','Biologia','History','01/2023'),('145692','Jorge','Sprague','Ingenieria y arquitectura','History','02/2023'),('145942','Susannah','Dunseath','Ingenieria y arquitectura','English','02/2024'),('145944','Orbadiah','Hardcastle','Derecho','Computer Science','01/2024'),('145956','Evered','Arents','Economia','English','01/2024'),('14606','Evita','Utteridge','Derecho','Biology','02/2023'),('146164','Annadiana','Pawlowicz','Economia','Mathematics','01/2024'),('146184','Netta','Cutforth','Ingenieria y arquitectura','Mathematics','02/2024'),('146213','Barbi','Bloxam','Ingenieria y arquitectura','Psychology','02/2024'),('146216','Deck','Boome','Biologia','Biology','01/2023'),('146339','Marcie','Klamman','Biologia','History','01/2024'),('146515','Alexandr','Browning','Biologia','Biology','02/2023'),('14653','Christiane','Dumphries','Biologia','Psychology','02/2023'),('146603','Goldia','MacNulty','Medicina','Mathematics','01/2023'),('146761','Trudi','Heisman','Economia','Computer Science','02/2024'),('146763','Harman','Draper','Medicina','English','02/2023'),('147170','Theodoric','Wilgar','Ingenieria y arquitectura','History','02/2023'),('147346','Carine','Daniells','Biologia','Chemistry','02/2023'),('147371','Roze','Allsopp','Derecho','Mathematics','02/2024'),('147397','Tina','Persich','Biologia','Psychology','01/2024'),('147636','Amberly','Tourville','Biologia','Chemistry','01/2024'),('14767','Vernor','Tournay','Ingenieria y arquitectura','History','01/2023'),('147748','Kalvin','Stickney','Biologia','English','01/2024'),('147777','Eb','Hounihan','Biologia','History','01/2024'),('147812','Fitz','Wandrey','Ingenieria y arquitectura','Computer Science','01/2024'),('147822','Tibold','Lukash','Biologia','Chemistry','01/2023'),('147863','Madge','Carhart','Ingenieria y arquitectura','History','02/2024'),('147922','Nels','Huckerbe','Derecho','English','02/2023'),('148194','Fax','Meiklem','Derecho','Chemistry','01/2023'),('148327','Sargent','Gosby','Ingenieria y arquitectura','English','01/2024'),('148752','Melva','Cajkler','Derecho','Mathematics','02/2023'),('148875','Pet','Semeniuk','Derecho','Chemistry','01/2024'),('148969','Patty','Mowett','Economia','Chemistry','01/2023'),('149254','Darla','Jeaffreson','Derecho','Mathematics','01/2023'),('149348','Barry','Douris','Biologia','Computer Science','01/2023'),('150137','Tina','Janisson','Biologia','Computer Science','01/2024'),('150587','Tobye','Bootherstone','Ingenieria y arquitectura','Computer Science','02/2024'),('150608','Guglielmo','Ziemecki','Medicina','Mathematics','02/2023'),('151084','Gerardo','Waadenburg','Derecho','Mathematics','01/2024'),('151307','Brien','Vellden','Derecho','Biology','02/2023'),('151422','Joelie','Gittose','Ingenieria y arquitectura','English','02/2023'),('15171','Kristan','Murrigan','Ingenieria y arquitectura','English','01/2024'),('152264','Gabriel','Raoux','Derecho','History','02/2023'),('152304','Rem','Collelton','Economia','Mathematics','01/2024'),('15239','Arv','Masserel','Biologia','Psychology','02/2024'),('15240','Sibbie','Antonat','Economia','English','02/2024'),('152400','Addison','Romanetti','Medicina','Chemistry','02/2023'),('152455','Julianna','Edlyne','Derecho','English','01/2024'),('15259','Archibald','Moodey','Medicina','Biology','01/2024'),('15316','Valentino','Fillan','Biologia','Mathematics','02/2024'),('15349','Ike','Van de Vlies','Derecho','English','01/2024'),('153903','Loria','Hellwing','Biologia','History','02/2024'),('153909','Haven','Syme','Derecho','History','01/2024'),('154078','Corilla','Daout','Medicina','History','01/2024'),('154115','Renault','Atcheson','Economia','Mathematics','01/2024'),('154136','Reese','Kingstne','Derecho','Chemistry','01/2023'),('154163','Frannie','O\'Dwyer','Medicina','History','01/2023'),('154263','Levin','Trevna','Ingenieria y arquitectura','Biology','02/2024'),('154469','Nikita','Woodrow','Ingenieria y arquitectura','Biology','01/2024'),('154940','Stacee','Ambroz','Ingenieria y arquitectura','Biology','02/2023'),('155652','Lane','Drake','Medicina','Chemistry','01/2023'),('155698','Else','Fields','Medicina','Mathematics','01/2023'),('155844','Fulvia','Twigley','Derecho','History','01/2023'),('155887','Helli','Sutherley','Economia','History','02/2023'),('15595','Jammie','Bullerwell','Biologia','Chemistry','01/2023'),('156138','Ara','Boothroyd','Biologia','Biology','01/2024'),('156168','Fred','Flatley','Ingenieria y arquitectura','Mathematics','01/2023'),('156346','Susan','Dymidowski','Derecho','English','02/2024'),('156486','Linell','Grimme','Ingenieria y arquitectura','English','01/2023'),('156533','Tricia','Janoch','Economia','Psychology','01/2023'),('156625','Tova','Kahen','Ingenieria y arquitectura','Biology','01/2024'),('156743','Lauren','Brundill','Ingenieria y arquitectura','History','01/2023'),('156917','Hermia','Dickerson','Medicina','English','02/2023'),('15696','Jacqui','Nodin','Ingenieria y arquitectura','Biology','02/2023'),('157163','Wilie','Elsdon','Derecho','Computer Science','01/2023'),('15718','Barnebas','Coker','Medicina','English','01/2023'),('157242','Lizzie','Comolli','Medicina','Chemistry','01/2023'),('157467','Bernie','Castilla','Ingenieria y arquitectura','History','01/2024'),('157695','Chiarra','Dybald','Ingenieria y arquitectura','Biology','01/2024'),('157743','Kylie','Elcox','Economia','Mathematics','01/2023'),('158182','Nonie','Crumbleholme','Biologia','History','01/2024'),('158544','Currey','Tatersale','Ingenieria y arquitectura','Mathematics','01/2023'),('158645','Margaux','Blumire','Medicina','Biology','02/2024'),('158742','Nollie','Swetland','Medicina','Psychology','02/2023'),('159186','Buddie','Bosward','Derecho','Mathematics','01/2023'),('159482','Barbabas','Jiranek','Ingenieria y arquitectura','Biology','02/2024'),('159599','Aileen','Shovelin','Biologia','Psychology','01/2023'),('159686','Solomon','Noel','Medicina','English','01/2024'),('159709','Ivor','Pennino','Ingenieria y arquitectura','Computer Science','02/2024'),('159730','Kaiser','Brockhouse','Derecho','Mathematics','02/2024'),('159892','Babette','Baudino','Medicina','History','02/2023'),('159947','Abrahan','Tremblay','Derecho','Chemistry','02/2024'),('159951','Thain','Huncoot','Economia','Chemistry','02/2023'),('16003','Andria','Matushevich','Derecho','Chemistry','01/2024'),('16023','Issi','Breedy','Medicina','History','01/2024'),('160535','Kimmy','Uden','Economia','Psychology','01/2024'),('160760','Tomlin','Wilmot','Ingenieria y arquitectura','Computer Science','02/2023'),('160972','Culley','Whatson','Economia','Psychology','02/2023'),('161697','Doug','Dumphrey','Medicina','Chemistry','01/2024'),('161766','Devon','Gluyus','Economia','History','01/2023'),('161873','Roxane','Friel','Medicina','Chemistry','01/2024'),('162141','Lottie','Dominick','Medicina','Computer Science','02/2023'),('162194','Roxana','Copsey','Derecho','English','02/2023'),('162209','Rosana','Sushams','Medicina','History','02/2024'),('162453','Catina','Skim','Economia','Mathematics','02/2024'),('162511','Laraine','Belone','Medicina','Mathematics','01/2023'),('162705','Pauli','Southard','Economia','English','02/2024'),('162721','Emerson','Risbie','Economia','Biology','01/2024'),('162757','Brendon','Sikora','Derecho','History','01/2023'),('162794','Joete','Tudgay','Ingenieria y arquitectura','Mathematics','01/2024'),('163006','Sayer','Birnie','Derecho','Biology','02/2024'),('163030','Ellary','Caple','Biologia','Computer Science','01/2024'),('163066','Veronike','Crowcum','Ingenieria y arquitectura','Chemistry','01/2024'),('163225','Durand','Rowthorn','Ingenieria y arquitectura','Biology','01/2023'),('163323','Dody','Mussard','Economia','Psychology','02/2023'),('163342','Clevey','Grundwater','Ingenieria y arquitectura','History','01/2024'),('163447','Lottie','Shere','Economia','Chemistry','02/2023'),('163553','Rosemary','Daws','Economia','Biology','02/2023'),('16367','Virgil','Careless','Medicina','Biology','02/2023'),('163771','Giles','Pettwood','Derecho','Chemistry','02/2023'),('163788','Ynez','Poundsford','Biologia','Biology','02/2023'),('164222','Raimund','Armsby','Economia','Psychology','02/2023'),('164737','Billi','Everington','Derecho','Psychology','02/2024'),('164745','Orsa','McCart','Biologia','Biology','01/2024'),('164846','Arturo','Mildmott','Derecho','Mathematics','02/2023'),('165213','Dominica','Aveson','Economia','History','02/2023'),('165223','Derick','Andretti','Derecho','English','01/2024'),('165450','Cheryl','Carlett','Medicina','English','02/2023'),('165698','Agnesse','Bagley','Biologia','Computer Science','02/2023'),('166052','Elayne','Tolley','Ingenieria y arquitectura','Computer Science','02/2024'),('166178','Ludovika','Hele','Economia','Psychology','01/2023'),('166259','Fabian','Mourge','Biologia','Psychology','02/2024'),('166317','Lana','Deards','Biologia','Computer Science','01/2023'),('166465','Fax','Mirfield','Derecho','History','02/2023'),('166748','Dela','Porter','Biologia','Chemistry','02/2023'),('167038','Idalina','Lethem','Ingenieria y arquitectura','Chemistry','01/2023'),('167045','Lenard','Pitkaithly','Ingenieria y arquitectura','Mathematics','02/2023'),('167065','Callida','McGraffin','Economia','Mathematics','01/2023'),('167446','Sylas','Tassel','Derecho','Psychology','02/2023'),('167542','Wilt','Fewtrell','Medicina','Biology','02/2024'),('167594','Sayre','Zini','Medicina','History','02/2024'),('167643','Hunt','Dumberell','Derecho','Biology','01/2024'),('167692','Danica','Campsall','Derecho','Mathematics','01/2023'),('16774','Corabella','Cicconetti','Derecho','Chemistry','01/2023'),('168006','Sharon','Tarren','Economia','Mathematics','02/2023'),('168539','Patten','Joderli','Ingenieria y arquitectura','Mathematics','01/2023'),('16861','Niles','Splaven','Economia','Chemistry','01/2023'),('168627','Cloe','Elger','Ingenieria y arquitectura','Chemistry','01/2023'),('168693','Arnaldo','Worman','Ingenieria y arquitectura','Computer Science','02/2024'),('168969','Natalee','Esson','Medicina','History','01/2023'),('168983','Karil','Drinnan','Biologia','Mathematics','01/2024'),('169539','Wilhelmine','Swadlen','Biologia','English','02/2024'),('169647','Beitris','Dukesbury','Biologia','Biology','01/2023'),('169851','Flora','Stonhard','Ingenieria y arquitectura','Mathematics','02/2023'),('170030','Chan','Harnott','Biologia','English','02/2024'),('17027','Sydelle','Hindhaugh','Medicina','English','01/2024'),('170452','Ives','Gorwood','Medicina','Chemistry','01/2023'),('170738','Anthea','Scolli','Ingenieria y arquitectura','Chemistry','01/2024'),('170849','Betta','Sowerbutts','Derecho','Biology','02/2023'),('170933','Jack','McKee','Ingenieria y arquitectura','English','02/2023'),('170939','Gawain','Glitherow','Ingenieria y arquitectura','Computer Science','02/2024'),('171004','Hazel','Cluney','Ingenieria y arquitectura','Biology','01/2024'),('171016','Heloise','Futty','Biologia','Psychology','01/2023'),('17102','Binky','Tuhy','Medicina','Psychology','01/2024'),('171121','Celinka','Sackett','Biologia','Mathematics','01/2023'),('171179','Luis','Longo','Biologia','History','02/2023'),('17173','Ewan','Peacey','Derecho','Mathematics','01/2024'),('172189','Roseanna','Oluwatoyin','Economia','English','01/2024'),('172259','Lilli','Hinckes','Ingenieria y arquitectura','History','01/2023'),('17250','Gae','Wagge','Derecho','Computer Science','01/2024'),('172590','Skipper','Feckey','Biologia','Mathematics','01/2023'),('172592','Lottie','Pittock','Derecho','Mathematics','02/2024'),('17270','Philomena','Snashall','Biologia','Mathematics','02/2023'),('172846','Guillemette','Stockbridge','Medicina','Chemistry','02/2024'),('173414','Bekki','Eixenberger','Economia','Computer Science','01/2023'),('173557','Fernanda','Espasa','Biologia','Biology','02/2024'),('173573','Mariellen','Luxen','Derecho','Computer Science','01/2023'),('173686','Zane','Le Friec','Economia','Computer Science','02/2024'),('173838','Lida','Grebert','Derecho','History','01/2024'),('173899','Hurlee','Beardwell','Biologia','Biology','01/2024'),('173953','Batholomew','Bradforth','Derecho','Mathematics','01/2024'),('174057','Aurore','Van Son','Derecho','Computer Science','01/2023'),('174148','Cortie','Felderer','Economia','Biology','02/2024'),('174639','Rance','Chilley','Biologia','History','01/2023'),('17471','Flin','Clubbe','Derecho','English','02/2023'),('17485','Jannelle','Meysham','Derecho','History','02/2023'),('174886','Sarajane','Goaley','Medicina','Mathematics','01/2024'),('174939','Tatiana','Vasilechko','Biologia','Biology','02/2024'),('175520','Reagan','De Bischof','Derecho','English','02/2024'),('175780','Ashien','Cully','Medicina','Chemistry','02/2023'),('175866','Garvey','Grestie','Biologia','Biology','02/2023'),('175876','Ede','Phythian','Ingenieria y arquitectura','English','02/2023'),('176755','Mel','Streeten','Medicina','Biology','01/2023'),('176813','Kyrstin','Bury','Ingenieria y arquitectura','English','01/2023'),('176939','Cordelie','Stonebridge','Biologia','Mathematics','02/2024'),('177044','Bernita','Beardsell','Economia','Psychology','02/2024'),('177528','Leah','Ockwell','Derecho','Chemistry','01/2024'),('177586','Alie','Fabler','Economia','Biology','01/2023'),('177719','Anson','Skitt','Derecho','History','01/2023'),('177806','Dareen','Livezey','Ingenieria y arquitectura','English','01/2023'),('178188','Davy','Scahill','Medicina','Chemistry','02/2023'),('178313','Gretna','Fereday','Medicina','Biology','02/2024'),('178604','Geoffry','Hews','Economia','English','01/2024'),('178749','Jamesy','Muddle','Ingenieria y arquitectura','Biology','02/2023'),('179128','Nicolas','Eads','Ingenieria y arquitectura','Mathematics','01/2024'),('179363','Carmita','Lorens','Biologia','Chemistry','01/2024'),('179588','Clem','Backshaw','Medicina','History','01/2023'),('179844','Emyle','Cossey','Derecho','Chemistry','01/2024'),('179952','Lucille','Archbald','Biologia','Computer Science','01/2023'),('180184','Marven','Knowlden','Ingenieria y arquitectura','Computer Science','01/2024'),('180197','Tanitansy','Helstrom','Biologia','Biology','02/2023'),('180591','Morley','Lawson','Biologia','History','01/2024'),('181114','Rina','Hurdidge','Ingenieria y arquitectura','Chemistry','01/2024'),('181198','Kaleena','Trebilcock','Biologia','Biology','02/2023'),('181215','Dionis','Macari','Economia','Computer Science','01/2024'),('181313','Fredia','Aspenlon','Economia','Psychology','02/2023'),('18140','Eydie','Layus','Derecho','Mathematics','01/2023'),('181525','Fidel','Adshed','Biologia','English','02/2023'),('181612','Neile','Athy','Ingenieria y arquitectura','History','02/2023'),('181648','Kaylee','Goaks','Medicina','Chemistry','02/2024'),('181672','Jacki','Lavallie','Derecho','Psychology','02/2023'),('181690','Crosby','Farrah','Economia','Mathematics','02/2024'),('181725','Maurizio','Sainsbury-Brown','Biologia','History','01/2023'),('181937','Whitney','Epps','Derecho','Mathematics','02/2024'),('182006','Ellene','Biggam','Economia','Chemistry','02/2023'),('182077','Ode','Kinnane','Derecho','English','02/2023'),('182610','Lesya','Tolmie','Medicina','Psychology','01/2024'),('182656','Hartley','Blucher','Biologia','Biology','02/2023'),('18312','Filmer','Lewzey','Derecho','Chemistry','01/2024'),('183252','Raynard','Canero','Economia','Psychology','01/2024'),('183386','Shaina','Beeres','Medicina','Biology','02/2023'),('183487','Lanna','Hyde','Economia','Mathematics','02/2024'),('183840','Kathye','Dureden','Derecho','Chemistry','01/2024'),('184201','Thatcher','Mattson','Ingenieria y arquitectura','History','02/2024'),('184267','Donia','Osgordby','Ingenieria y arquitectura','English','02/2024'),('184397','Evita','Jerrolt','Ingenieria y arquitectura','Computer Science','02/2023'),('184561','Prudi','Stoyle','Biologia','History','01/2024'),('184624','Avigdor','Prozescky','Ingenieria y arquitectura','Biology','02/2023'),('184797','Dianemarie','Teml','Derecho','Chemistry','01/2023'),('184989','Merrie','Bonnefin','Ingenieria y arquitectura','Computer Science','02/2023'),('185162','Culver','O\'Scandall','Derecho','Mathematics','01/2024'),('185170','Waverly','Ivanishin','Medicina','Psychology','02/2024'),('185204','Judah','Temperley','Economia','Mathematics','02/2023'),('185252','Gayle','Silverston','Medicina','Psychology','01/2024'),('185329','Chad','Alty','Medicina','Biology','02/2024'),('185398','Alvera','Andreolli','Economia','Computer Science','01/2023'),('18554','Zollie','Worswick','Medicina','History','01/2024'),('185923','Gilda','Baccus','Ingenieria y arquitectura','Chemistry','02/2024'),('186060','Arden','Connow','Ingenieria y arquitectura','History','02/2023'),('186345','Camila','Lilloe','Economia','Biology','02/2024'),('186445','Darn','Gregon','Biologia','Mathematics','02/2024'),('186515','Godwin','Beverley','Biologia','Computer Science','02/2024'),('186723','Paul','Mellon','Ingenieria y arquitectura','Biology','01/2024'),('186964','Jaquenetta','Meindl','Biologia','History','01/2024'),('187013','Olenka','Owtram','Ingenieria y arquitectura','Mathematics','01/2024'),('187211','Jenifer','Swalteridge','Ingenieria y arquitectura','Mathematics','01/2024'),('187454','Fey','Duxfield','Ingenieria y arquitectura','Biology','01/2024'),('187485','Thekla','Fissenden','Ingenieria y arquitectura','Psychology','02/2024'),('187944','Bethena','Measey','Derecho','Psychology','02/2024'),('188706','Yuri','Normanville','Biologia','Mathematics','02/2024'),('189010','Corey','Ebi','Derecho','English','02/2023'),('189070','Simona','Parvin','Economia','Psychology','01/2024'),('189437','Allissa','Blain','Economia','History','02/2023'),('18944','Anne','Gidden','Biologia','Chemistry','01/2024'),('189646','Rob','Dryden','Derecho','English','01/2024'),('190020','Vannie','Apperley','Economia','History','01/2024'),('190216','Montgomery','Rostron','Biologia','Psychology','01/2023'),('190667','Pet','Brouard','Medicina','Computer Science','01/2023'),('190692','Alison','Gellett','Medicina','Biology','01/2024'),('190858','Remus','Freddi','Biologia','Psychology','02/2023'),('190867','Jeannie','Martinuzzi','Ingenieria y arquitectura','History','02/2023'),('190963','Melantha','Hupka','Derecho','History','01/2024'),('191509','Prudence','Llewellin','Ingenieria y arquitectura','English','02/2023'),('191611','Leontyne','Mussetti','Ingenieria y arquitectura','Mathematics','01/2023'),('191634','Sherilyn','Moxstead','Ingenieria y arquitectura','Psychology','01/2023'),('191866','Orv','Iskowicz','Medicina','History','02/2024'),('192773','Frederick','Kigelman','Biologia','Psychology','01/2024'),('193174','Nerti','MacMarcuis','Medicina','Biology','01/2023'),('193185','Barbi','Tourne','Derecho','Computer Science','02/2023'),('193270','Hanni','Pennell','Medicina','English','01/2024'),('193518','Murvyn','Bowmaker','Medicina','Biology','02/2024'),('193646','Marthena','Lamperti','Economia','Psychology','02/2023'),('193650','Collin','McCarlie','Biologia','English','01/2024'),('193864','Loella','Gaskin','Medicina','History','01/2024'),('193893','Elvira','Akers','Biologia','Chemistry','02/2023'),('194189','Wendye','Knivett','Ingenieria y arquitectura','History','01/2023'),('194324','Demetris','Cutriss','Economia','Biology','02/2023'),('194419','Marleah','Tynnan','Derecho','Biology','01/2023'),('194432','Morgen','Lightbown','Biologia','Mathematics','01/2023'),('194770','Cyndia','Kuhnke','Ingenieria y arquitectura','English','01/2023'),('195007','Neddie','Ros','Ingenieria y arquitectura','English','02/2024'),('195047','Sauncho','Philot','Economia','Psychology','02/2024'),('195343','Anne','Groom','Ingenieria y arquitectura','History','01/2023'),('195465','Scarface','Buller','Economia','Mathematics','02/2023'),('195554','Shalne','Green','Medicina','History','01/2024'),('195560','Corilla','Perrelle','Derecho','History','01/2024'),('196032','Liva','Railton','Medicina','English','01/2024'),('196083','Guillaume','Hartil','Economia','Chemistry','02/2023'),('196181','Tessi','Wesker','Derecho','Biology','01/2024'),('196341','Alla','Lorraine','Medicina','Chemistry','01/2024'),('19647','Peta','Sprowson','Biologia','Psychology','02/2023'),('196576','Karlotta','Tremathack','Derecho','Mathematics','01/2023'),('196631','Esmaria','Kenion','Medicina','English','01/2023'),('196640','Hardy','Sarfass','Derecho','English','01/2023'),('196921','Nikolaus','Ivery','Medicina','Mathematics','02/2023'),('197206','Drucy','Shelborne','Medicina','English','01/2024'),('197471','Phyllis','Burgill','Economia','Chemistry','02/2023'),('197561','Gussie','Cubbini','Biologia','History','01/2024'),('197737','Iolanthe','Sheekey','Medicina','History','02/2023'),('198005','Tawsha','Lyard','Medicina','Biology','01/2023'),('198064','Ollie','Cowdroy','Derecho','English','02/2024'),('198153','Maryrose','Osorio','Derecho','Psychology','01/2023'),('198242','Carroll','Yukhnevich','Derecho','Psychology','01/2023'),('198256','Liv','Luttgert','Derecho','English','02/2024'),('198336','Purcell','Mattiato','Derecho','Chemistry','01/2023'),('198448','Abbye','Waddy','Economia','English','01/2024'),('198603','Harwell','Anespie','Biologia','History','01/2023'),('198702','Michele','Hamal','Medicina','English','01/2023'),('198783','Cory','Mullett','Medicina','English','01/2023'),('19898','Vicki','Klimkov','Medicina','Psychology','02/2024'),('199092','Homerus','Doody','Derecho','Chemistry','01/2024'),('199200','Ginevra','Camis','Economia','Biology','01/2023'),('19957','Lon','Hardway','Ingenieria y arquitectura','Biology','02/2023'),('199624','Caitlin','Mathelon','Biologia','Psychology','01/2024'),('199696','Sigismund','Teare','Derecho','History','01/2024'),('199741','Wendie','Mountney','Derecho','Chemistry','01/2024'),('199771','Heath','Austing','Economia','Computer Science','02/2024'),('199807','Carry','Pirie','Biologia','Computer Science','01/2024'),('20038','Pegeen','Reolfi','Derecho','Mathematics','01/2024'),('20044','Alverta','Lyall','Derecho','Biology','01/2023'),('20204','Cherye','Pike','Biologia','Chemistry','02/2024'),('20396','Leonelle','Petche','Economia','English','01/2024'),('20492','Nettle','Gilligan','Medicina','Mathematics','02/2023'),('20644','Eziechiele','McFaell','Biologia','Psychology','01/2023'),('20861','Chrissie','Brownill','Economia','Chemistry','01/2023'),('20992','Judah','Pigne','Derecho','Computer Science','02/2024'),('21102','Misti','Brecknell','Biologia','Biology','01/2023'),('21495','Mollee','Bautiste','Derecho','History','01/2023'),('21710','Harold','Tedstone','Medicina','Mathematics','01/2023'),('21788','Hope','Sinclair','Medicina','English','02/2024'),('21865','Carlene','McTiernan','Ingenieria y arquitectura','English','02/2023'),('21924','Nels','Domengue','Medicina','Psychology','02/2023'),('22080','Suzette','Rizzillo','Ingenieria y arquitectura','Mathematics','01/2024'),('22498','Jacques','Terrington','Economia','Biology','01/2024'),('22599','Neddy','Doylend','Economia','Computer Science','02/2023'),('22644','Ericka','Weiner','Economia','History','01/2024'),('22839','Shanna','Bentall','Economia','History','01/2024'),('22891','Durante','Laherty','Economia','Psychology','02/2023'),('23246','Raynell','Leftbridge','Economia','Chemistry','01/2023'),('23309','Mohandas','Ferrandez','Ingenieria y arquitectura','Psychology','02/2024'),('24216','Benita','Tapper','Ingenieria y arquitectura','English','02/2023'),('24358','Hestia','Emanuelli','Derecho','History','01/2023'),('24923','Leoline','Bednell','Biologia','Psychology','01/2024'),('25184','Vernen','Buttle','Medicina','Computer Science','02/2023'),('25499','Drusi','Breacher','Biologia','Biology','02/2023'),('25672','Wittie','Willan','Derecho','Chemistry','02/2024'),('25760','Mohammed','Mandy','Biologia','Biology','01/2024'),('26064','Shanon','Bolus','Economia','History','02/2023'),('26206','Ashlen','Orrah','Biologia','Mathematics','02/2023'),('26425','Demetris','Gendrich','Ingenieria y arquitectura','Biology','01/2024'),('26695','Bunni','Tome','Derecho','Biology','01/2023'),('27404','Sisely','Aleavy','Derecho','Mathematics','01/2023'),('27612','Mattie','Shepland','Derecho','Computer Science','02/2024'),('27831','Stillman','Crinage','Biologia','English','02/2024'),('27840','Albina','Sharma','Derecho','English','01/2024'),('28021','Meir','Umpleby','Derecho','History','01/2024'),('28417','Delaney','Yurin','Derecho','Chemistry','02/2023'),('28458','Yul','Janson','Ingenieria y arquitectura','Computer Science','01/2023'),('28628','Steven','Beckers','Biologia','Chemistry','01/2023'),('28674','Abramo','Dodwell','Derecho','English','02/2023'),('28766','Rafaelita','Andrivot','Ingenieria y arquitectura','Mathematics','02/2024'),('28888','Nissy','Gorvin','Derecho','Psychology','02/2024'),('29034','Ritchie','Bougourd','Ingenieria y arquitectura','History','01/2024'),('29211','Charline','Hinks','Economia','English','02/2024'),('29250','Jacinda','Tidd','Ingenieria y arquitectura','Chemistry','01/2023'),('29251','Lorine','Kingdom','Ingenieria y arquitectura','Computer Science','02/2023'),('29459','Domeniga','Tumpane','Derecho','Psychology','01/2024'),('29513','Jordan','Charette','Biologia','Chemistry','01/2024'),('29559','Carissa','Gatlin','Economia','Computer Science','01/2024'),('29663','Julio','McGuane','Biologia','Mathematics','01/2023'),('29778','Daven','Gilffilland','Economia','History','01/2023'),('29796','Maurise','Kornilov','Economia','English','02/2024'),('30250','Gilly','Kemwal','Economia','Computer Science','01/2023'),('30307','Sadie','Loade','Medicina','Computer Science','02/2024'),('30407','Hardy','Reddoch','Ingenieria y arquitectura','Biology','02/2024'),('30598','Evanne','Paling','Ingenieria y arquitectura','Psychology','02/2024'),('30744','Brita','Stutard','Medicina','English','01/2024'),('31729','Pippo','Milbourn','Derecho','Chemistry','02/2023'),('31735','Dur','Hexham','Economia','Psychology','02/2024'),('31766','Willabella','Pavy','Derecho','English','02/2024'),('32299','Llewellyn','Mandres','Biologia','Biology','01/2023'),('32421','Marilyn','Drust','Derecho','Biology','02/2024'),('32432','Marijo','Gudgen','Biologia','Chemistry','01/2023'),('32552','Tasha','Coverly','Biologia','Psychology','01/2024'),('32885','Aime','Fyrth','Economia','English','01/2024'),('32912','Duffie','Blanden','Biologia','Mathematics','01/2023'),('32926','Inness','Pelman','Derecho','Psychology','01/2024'),('33035','Sammy','Rizzolo','Biologia','Chemistry','02/2024'),('33198','Dallon','Carriage','Ingenieria y arquitectura','Computer Science','02/2024'),('33426','Rosanna','Houseley','Derecho','Mathematics','02/2024'),('33520','Sanders','Allanson','Medicina','Psychology','01/2023'),('33583','Chevalier','Joice','Biologia','Psychology','02/2024'),('33696','Diane','Rand','Economia','English','01/2023'),('33708','Moyna','Sprigging','Biologia','Mathematics','01/2024'),('33824','Gearalt','Jannasch','Economia','Chemistry','01/2023'),('33949','Booth','Jurgenson','Ingenieria y arquitectura','Mathematics','01/2024'),('34310','Nicol','Blades','Ingenieria y arquitectura','English','01/2023'),('34319','Cherilynn','Dicks','Medicina','Psychology','02/2024'),('34580','Hester','Makeswell','Ingenieria y arquitectura','History','02/2024'),('34734','Antonin','Pearlman','Economia','Biology','02/2024'),('34873','Andras','Rapelli','Economia','History','02/2024'),('34893','Orville','Dedon','Biologia','History','01/2023'),('35298','Jemmy','Iozefovich','Medicina','History','02/2024'),('35645','Noell','Birchenough','Medicina','Computer Science','01/2024'),('35765','Raphael','Ryburn','Derecho','History','02/2023'),('35824','Bren','Brittian','Economia','English','02/2024'),('35874','Sigismondo','Nelsey','Ingenieria y arquitectura','English','01/2023'),('35930','Berry','Valasek','Economia','Computer Science','01/2023'),('35977','Brittaney','Klaaassen','Medicina','Biology','02/2023'),('36133','Prudy','Eagell','Derecho','Biology','02/2024'),('36184','Ardelis','Dalli','Ingenieria y arquitectura','Biology','01/2024'),('36238','Renelle','Ayto','Economia','Psychology','02/2023'),('36477','Nikaniki','Davson','Biologia','Computer Science','02/2023'),('36593','D\'arcy','Grayston','Economia','Chemistry','01/2024'),('36635','Adolpho','Nicolls','Ingenieria y arquitectura','Chemistry','01/2024'),('37969','Ramsey','Santus','Economia','Biology','02/2024'),('38085','Aryn','Tire','Ingenieria y arquitectura','Biology','02/2024'),('38748','Cordie','Gymblett','Biologia','Biology','02/2023'),('39412','Lexis','Hassen','Derecho','Computer Science','01/2023'),('39557','Lothaire','Hindrich','Medicina','History','02/2023'),('39720','Viva','Geeraert','Derecho','Psychology','02/2023'),('40142','Cordie','Pusey','Derecho','Biology','01/2023'),('40304','Ingelbert','Fieldgate','Economia','Biology','02/2023'),('40860','Joelle','Brocking','Derecho','English','02/2023'),('41027','Jobey','Castelyn','Ingenieria y arquitectura','History','01/2023'),('41063','Cherie','Carette','Derecho','English','02/2024'),('41127','Auberon','Pollicatt','Ingenieria y arquitectura','Psychology','02/2024'),('41340','Art','Jennions','Economia','Biology','01/2024'),('41617','Sosanna','Neads','Derecho','Psychology','02/2024'),('41943','Janetta','Eidelman','Economia','History','01/2024'),('41984','Waring','Beer','Derecho','Chemistry','02/2024'),('41997','Kimbell','Kinny','Ingenieria y arquitectura','English','01/2023'),('42214','Blake','Hessentaler','Economia','Chemistry','02/2023'),('42306','Eldon','Cattonnet','Economia','Psychology','02/2024'),('42401','Brunhilda','Van Niekerk','Biologia','English','02/2023'),('42982','Leontine','Craisford','Ingenieria y arquitectura','History','01/2024'),('43230','Irena','Gabites','Ingenieria y arquitectura','Psychology','01/2024'),('43379','Caril','Thies','Biologia','Mathematics','02/2024'),('43478','Rodrigo','Everly','Economia','Chemistry','02/2024'),('43659','Jody','Bartosch','Ingenieria y arquitectura','Chemistry','02/2023'),('43707','Jacquelin','Heaven','Economia','Chemistry','01/2024'),('44010','Aime','Saltern','Derecho','Computer Science','01/2024'),('44045','Rikki','Padden','Economia','English','01/2024'),('44671','Bran','Treharne','Economia','Psychology','01/2023'),('44741','Frazer','Wigelsworth','Biologia','Mathematics','02/2023'),('45181','Peggie','Verheijden','Medicina','Psychology','02/2024'),('45828','Gretta','Roset','Medicina','Psychology','02/2024'),('45852','Roland','Vice','Biologia','Chemistry','01/2023'),('45885','Anne-corinne','Scouller','Ingenieria y arquitectura','Mathematics','01/2024'),('46045','Candie','Manklow','Economia','Computer Science','01/2024'),('46633','Giavani','Klewer','Economia','Computer Science','01/2023'),('46667','Bari','Bremley','Derecho','Biology','01/2023'),('47227','Benyamin','Blann','Medicina','Computer Science','02/2024'),('47521','Faustina','Buttel','Derecho','Mathematics','01/2024'),('47750','Huntington','Punch','Medicina','History','02/2023'),('47859','Filberto','Illsley','Medicina','English','01/2024'),('47981','Chloette','Bilbee','Medicina','Biology','01/2024'),('48527','Pierre','Brickwood','Derecho','History','01/2023'),('48552','Marlee','Barney','Biologia','Psychology','02/2023'),('48730','Karia','Coombe','Derecho','Mathematics','01/2023'),('48805','Boyce','Haley','Biologia','Biology','01/2024'),('48856','Heriberto','Rupert','Economia','Biology','01/2024'),('49828','Tressa','McElrea','Economia','Psychology','02/2023'),('49837','Debi','Tommaseo','Ingenieria y arquitectura','Psychology','02/2023'),('50158','Ronalda','Rainy','Medicina','Chemistry','01/2024'),('50173','Mikol','Longmire','Derecho','Psychology','01/2023'),('50202','Anatole','Ovill','Derecho','Mathematics','01/2024'),('50236','Darill','Coop','Economia','Biology','02/2024'),('50470','Dall','Cubbin','Biologia','History','01/2024'),('50698','Aubry','Dawson','Economia','Computer Science','02/2024'),('50748','Helge','Habergham','Ingenieria y arquitectura','Chemistry','02/2024'),('50789','Keir','Raatz','Ingenieria y arquitectura','Psychology','01/2024'),('51370','Nolly','Sproat','Medicina','History','01/2024'),('51410','Tera','Bendig','Biologia','Computer Science','01/2024'),('514187','Chickie','Malafe','Medicina','Biology','02/2023'),('51626','Tull','Beaudry','Ingenieria y arquitectura','Biology','02/2024'),('51646','Maudie','Dowtry','Economia','Chemistry','01/2023'),('51715','Phillie','Llorente','Ingenieria y arquitectura','Psychology','01/2024'),('51926','Rafi','Feldheim','Derecho','Chemistry','02/2024'),('51955','Christal','Ibeson','Biologia','Mathematics','01/2023'),('52052','Enriqueta','Luppitt','Economia','English','02/2024'),('52165','Phedra','Parkhouse','Biologia','Chemistry','01/2023'),('52448','Greta','Grimsdyke','Medicina','English','01/2024'),('52583','Milzie','Vaugham','Biologia','Computer Science','01/2023'),('52622','Derron','Biss','Ingenieria y arquitectura','Computer Science','01/2023'),('52724','Jermaine','Versey','Medicina','Computer Science','02/2023'),('52825','Erin','Rainard','Economia','Chemistry','02/2024'),('52930','Ora','Zamorrano','Economia','Chemistry','02/2024'),('52997','Bernadina','Fice','Derecho','Computer Science','02/2024'),('53323','Carry','Cawdell','Medicina','Mathematics','01/2024'),('53413','Shelli','Barkway','Derecho','History','02/2024'),('53598','Pebrook','Amberson','Medicina','Psychology','01/2024'),('53640','Freddie','Kubyszek','Biologia','Biology','01/2023'),('53655','Javier','Angrick','Derecho','English','02/2023'),('53675','Langsdon','Bedingfield','Economia','Biology','02/2024'),('53880','Lesya','MacMenemy','Medicina','Psychology','01/2023'),('54093','Freida','Filyushkin','Biologia','Biology','01/2024'),('54368','Merrily','Doorey','Biologia','English','02/2023'),('54394','Donnamarie','Need','Biologia','English','02/2023'),('54447','Arlene','Warmisham','Ingenieria y arquitectura','History','02/2023'),('54834','Iorgo','Reder','Ingenieria y arquitectura','History','01/2024'),('55123','Alexa','Dinis','Derecho','Biology','01/2024'),('55228','Baldwin','Dellenbrook','Biologia','Psychology','01/2024'),('55412','Shanta','Fayers','Derecho','Psychology','01/2023'),('55647','Bond','Dreng','Economia','Chemistry','01/2024'),('55864','Rhodia','Manning','Medicina','Computer Science','02/2023'),('55937','Rupert','Tottie','Ingenieria y arquitectura','Computer Science','02/2024'),('56357','Duffy','Brunt','Biologia','Chemistry','02/2024'),('56684','Anastassia','Rasp','Economia','Chemistry','02/2024'),('56865','Lauren','Attewell','Derecho','Mathematics','01/2023'),('56884','Danna','Jeandeau','Economia','History','01/2024'),('57134','Abby','Sharpe','Economia','Psychology','02/2024'),('57494','Dimitri','Barfield','Biologia','History','02/2024'),('57496','Manuel','Riehm','Ingenieria y arquitectura','Psychology','01/2024'),('57647','Jonathon','Escala','Ingenieria y arquitectura','Computer Science','02/2023'),('57753','Jarred','Rayer','Biologia','English','01/2023'),('57870','Tucky','Taunton.','Derecho','Computer Science','02/2024'),('58306','Kiel','Byne','Biologia','Psychology','01/2024'),('58712','Erna','Roslen','Ingenieria y arquitectura','History','02/2023'),('58745','Galvan','Tissell','Medicina','Computer Science','01/2024'),('59090','Hayley','Petrak','Ingenieria y arquitectura','Biology','02/2023'),('59154','Darbie','Caizley','Derecho','Computer Science','02/2024'),('59257','Stesha','Alner','Medicina','Mathematics','02/2024'),('59513','Davidde','Humbie','Derecho','Mathematics','01/2023'),('60141','Harper','Drinan','Derecho','Biology','02/2023'),('60237','Eran','McClunaghan','Ingenieria y arquitectura','Mathematics','01/2024'),('60646','Glynn','Verring','Economia','Biology','01/2023'),('60985','Faith','Hearns','Economia','Biology','01/2023'),('61197','Hershel','Ringwood','Economia','Mathematics','01/2024'),('61737','Valenka','McKendo','Economia','Chemistry','02/2023'),('62234','Langston','Hagart','Derecho','Mathematics','01/2023'),('62358','Jerrie','Stranahan','Biologia','Chemistry','02/2024'),('62569','Harrison','Goodinson','Medicina','Biology','02/2023'),('62746','Herrick','McRorie','Medicina','English','01/2023'),('63072','Miof mela','Beesley','Ingenieria y arquitectura','English','01/2024'),('63316','Joete','Cragg','Derecho','History','01/2023'),('63418','Hillery','Meneghelli','Biologia','Biology','01/2024'),('63475','Michelina','Ainslie','Medicina','Mathematics','01/2023'),('63812','Marigold','Stodhart','Ingenieria y arquitectura','Mathematics','02/2024'),('63887','Steffie','Castille','Derecho','English','02/2024'),('64128','Urban','Emmatt','Medicina','History','02/2024'),('64345','Davidde','Routley','Medicina','Mathematics','02/2023'),('64593','Aland','Tubble','Medicina','Psychology','01/2023'),('64643','Shawn','Marqyes','Derecho','English','02/2024'),('64752','Inglis','Martino','Biologia','Biology','02/2024'),('64840','Meggi','Deason','Medicina','History','01/2023'),('65320','Chrissie','Bulgen','Economia','Biology','02/2023'),('65467','Pace','Shepland','Medicina','English','01/2023'),('65551','Tildie','Quan','Ingenieria y arquitectura','History','02/2023'),('65734','Galen','Ivakin','Derecho','Psychology','02/2024'),('65983','Gay','Waldera','Ingenieria y arquitectura','Computer Science','02/2023'),('66075','Charlean','Bondesen','Ingenieria y arquitectura','Computer Science','02/2024'),('66126','Karola','Bumfrey','Medicina','Biology','01/2024'),('66485','Ferdinand','Brothwood','Ingenieria y arquitectura','English','01/2023'),('66559','Sarajane','Wasielewski','Economia','History','02/2023'),('66567','Mari','Warrilow','Ingenieria y arquitectura','Psychology','02/2024'),('66597','Helyn','Jorn','Biologia','Psychology','01/2024'),('67077','Peggy','Janusz','Economia','Psychology','02/2024'),('67129','Colver','Barajaz','Ingenieria y arquitectura','Mathematics','01/2024'),('67249','Diego','Kopfer','Medicina','Psychology','01/2023'),('67255','Kare','Blasius','Derecho','Chemistry','02/2023'),('67351','Michelle','Masic','Ingenieria y arquitectura','Computer Science','01/2024'),('67791','Raoul','Joll','Medicina','Psychology','02/2024'),('68009','Dani','Radbond','Derecho','Computer Science','01/2024'),('68420','Gordy','Tungay','Economia','Mathematics','01/2024'),('68441','Rainer','Masselin','Biologia','Chemistry','02/2023'),('68455','Nikki','Smoughton','Medicina','English','01/2023'),('68514','Sofie','Rumming','Ingenieria y arquitectura','Psychology','01/2024'),('68521','Allyson','Mahon','Medicina','Biology','02/2024'),('68833','Marie','Milroy','Economia','English','01/2023'),('68988','Katherina','Balsillie','Biologia','Chemistry','02/2024'),('69027','Dell','Kearney','Derecho','Psychology','02/2023'),('69114','Torrey','McGirr','Economia','Computer Science','01/2024'),('69521','Paul','Osmond','Ingenieria y arquitectura','English','01/2023'),('69662','Harris','Hammerton','Biologia','Biology','01/2024'),('69789','Raffaello','Abberley','Derecho','Chemistry','01/2024'),('69927','Stefania','Toy','Biologia','History','02/2023'),('70209','Fanchette','Dangl','Biologia','History','02/2023'),('71072','Kat','Burrus','Ingenieria y arquitectura','Mathematics','01/2023'),('71088','Charles','Ryder','Economia','History','02/2023'),('71441','Jourdan','Klebes','Economia','Chemistry','02/2024'),('71547','Rolph','Stanex','Ingenieria y arquitectura','Chemistry','01/2024'),('71555','Duff','De Biasi','Derecho','History','01/2023'),('71701','Lizzie','Tropman','Derecho','Computer Science','01/2023'),('71799','Diarmid','Penning','Medicina','Mathematics','01/2024'),('71853','Idaline','Bruniges','Derecho','Computer Science','02/2024'),('71996','Garret','Pocklington','Ingenieria y arquitectura','English','01/2023'),('72061','Bride','Baillie','Medicina','Computer Science','02/2023'),('72143','Arny','Galliford','Ingenieria y arquitectura','Chemistry','02/2023'),('72283','Yank','Cowndley','Derecho','Computer Science','02/2023'),('73004','Yasmeen','Truelove','Economia','Biology','01/2024'),('73096','Tammy','Kinchlea','Derecho','Computer Science','02/2023'),('73157','Cherice','Skaif','Biologia','Chemistry','02/2023'),('73295','Barny','Tremayne','Ingenieria y arquitectura','Psychology','02/2023'),('73314','Andie','Ricciardiello','Biologia','Computer Science','01/2024'),('73332','Dall','Fiorentino','Derecho','English','01/2024'),('73391','Udale','Kirwan','Derecho','Chemistry','01/2023'),('73392','Cinda','Harvatt','Biologia','Biology','01/2024'),('73508','Gerik','Ruckledge','Biologia','History','02/2024'),('73521','Bernadine','Thow','Ingenieria y arquitectura','Chemistry','01/2023'),('73584','Andre','Barmby','Ingenieria y arquitectura','Chemistry','01/2023'),('73779','Dominique','Raraty','Medicina','Psychology','02/2024'),('74128','Tildie','Yewman','Derecho','Biology','02/2024'),('74142','Loren','Edes','Ingenieria y arquitectura','History','01/2023'),('74450','Fayina','Beveredge','Derecho','History','01/2023'),('74665','Annette','Bruhnicke','Medicina','Computer Science','01/2024'),('74690','Stormy','Alberti','Medicina','Chemistry','01/2024'),('74747','Alexine','Leftwich','Medicina','Mathematics','01/2023'),('74866','Verena','McCowan','Ingenieria y arquitectura','Biology','02/2024'),('74876','Andromache','Seczyk','Medicina','Chemistry','02/2023'),('74880','Moyna','Petts','Derecho','Biology','01/2024'),('74885','Erena','Huffy','Biologia','Biology','01/2024'),('74924','Larine','Saines','Economia','Psychology','02/2023'),('75013','Gavan','Farragher','Economia','History','02/2023'),('75147','Jacques','Scorthorne','Economia','Computer Science','01/2023'),('75714','Tildie','Cargen','Economia','Psychology','02/2023'),('75748','Tamarra','MacNish','Derecho','Computer Science','01/2024'),('75904','Joachim','Redwood','Medicina','Biology','01/2024'),('76486','Kylie','Road','Biologia','Mathematics','02/2023'),('76646','Tiffie','Stobo','Medicina','Chemistry','01/2024'),('77163','Happy','Hatliffe','Economia','History','01/2023'),('77331','Darrel','Farries','Economia','Computer Science','02/2023'),('77661','Vernor','Crippin','Ingenieria y arquitectura','Biology','01/2023'),('77773','Curran','Grigson','Biologia','History','01/2024'),('77779','Yoshi','Ebdin','Biologia','English','01/2023'),('77958','Anabelle','Prescote','Medicina','History','01/2023'),('78095','Pet','Carlow','Derecho','Computer Science','02/2024'),('78621','Desdemona','Shaddock','Biologia','Computer Science','01/2024'),('78732','Myrwyn','Ginger','Medicina','Chemistry','01/2024'),('78817','Eliza','Billborough','Biologia','Biology','02/2023'),('78832','Maire','Goldston','Economia','History','01/2023'),('78879','Chad','Hercock','Ingenieria y arquitectura','Biology','01/2023'),('78926','Wilton','Loalday','Medicina','Chemistry','01/2024'),('78947','Isobel','Vagges','Biologia','Computer Science','01/2024'),('79236','Jessie','Milne','Medicina','Computer Science','01/2024'),('79265','Eberto','Matfield','Economia','Biology','01/2023'),('79702','Allyce','Whyborn','Economia','English','02/2024'),('79762','Reggis','Fitzsymonds','Derecho','Mathematics','02/2024'),('79768','Charley','Amberger','Derecho','Mathematics','01/2023'),('80638','Evanne','Pheby','Biologia','Computer Science','02/2023'),('80747','Berte','Menguy','Biologia','History','02/2023'),('80828','Danika','Fowler','Derecho','English','01/2023'),('80902','Sheffie','Heyball','Biologia','Computer Science','01/2023'),('80939','Brandise','Beauman','Medicina','Psychology','02/2023'),('81178','Allx','Eddy','Biologia','Computer Science','02/2024'),('81325','Rufus','Silkstone','Economia','Computer Science','01/2024'),('81342','Lorena','O\'Brogane','Biologia','Computer Science','02/2024'),('81468','Chaunce','Eley','Biologia','Computer Science','02/2024'),('81601','Hilliary','Gwyneth','Derecho','Mathematics','01/2024'),('81608','Liva','Kinchley','Derecho','English','02/2024'),('81719','Delphine','Aasaf','Biologia','Chemistry','01/2023'),('82109','Sabine','Struss','Medicina','Biology','02/2023'),('82150','Carmon','Kleynen','Derecho','Chemistry','02/2023'),('82318','Gustav','Poad','Derecho','Mathematics','01/2023'),('82433','Rayner','Risbrough','Economia','History','02/2024'),('82445','Koo','MacAscaidh','Medicina','Biology','02/2023'),('82897','Trina','Harmstone','Ingenieria y arquitectura','History','02/2023'),('83219','Virginia','MacMillan','Derecho','Mathematics','01/2024'),('83274','Faun','Satyford','Economia','English','02/2024'),('83296','Philippine','Pettie','Biologia','Psychology','02/2023'),('83317','Tammara','Patinkin','Ingenieria y arquitectura','Mathematics','02/2024'),('83634','Dreddy','Frostick','Medicina','Mathematics','02/2024'),('83855','Twila','Aldrich','Biologia','Computer Science','01/2024'),('84329','Wren','Ewins','Medicina','Psychology','02/2023'),('84503','Marcus','Count','Economia','English','02/2024'),('84524','Fina','Headington','Biologia','Psychology','02/2024'),('84567','Freddie','Biggans','Ingenieria y arquitectura','Biology','02/2024'),('84767','Bevin','Maunton','Economia','Psychology','02/2024'),('85064','Francene','Bore','Derecho','Biology','01/2024'),('85114','Tiffi','Worviell','Derecho','English','01/2023'),('85145','Moselle','Jardein','Derecho','English','02/2024'),('85163','Codie','Thame','Biologia','English','01/2023'),('85252','Dyanne','Orpin','Economia','Computer Science','02/2024'),('85263','Ignatius','Greenard','Derecho','Mathematics','01/2024'),('85721','Brew','Gosforth','Derecho','Biology','02/2023'),('85797','Emmanuel','Manuelli','Economia','Mathematics','01/2024'),('86199','Norene','Sainsbury','Biologia','English','01/2024'),('86376','Jolee','Sanpher','Biologia','History','01/2024'),('86861','Charmane','Chatenet','Biologia','History','02/2024'),('86912','Anestassia','Wethey','Biologia','Biology','02/2024'),('87336','Nickey','End','Economia','Chemistry','01/2023'),('87440','Scott','Epperson','Biologia','Psychology','01/2023'),('87621','Ezequiel','Freak','Economia','Biology','02/2023'),('88017','Peggy','Cohen','Derecho','Psychology','01/2023'),('88746','Leyla','Birkenshaw','Biologia','Biology','02/2024'),('88915','Worthy','Trustrie','Medicina','Chemistry','02/2024'),('89095','Brocky','Tremberth','Biologia','English','02/2024'),('89154','Roy','De Carlo','Medicina','History','01/2023'),('89508','Yolane','Triplow','Medicina','Mathematics','01/2023'),('90077','Paulita','Tousey','Derecho','Biology','01/2023'),('90125','Eloise','Barnsley','Economia','Computer Science','01/2023'),('90299','Gusty','Cardall','Economia','Psychology','02/2023'),('90341','Christophe','Sandaver','Economia','Psychology','01/2023'),('90488','Anders','Olcot','Economia','Computer Science','02/2023'),('90528','Brear','Valeri','Ingenieria y arquitectura','History','02/2024'),('90729','Udell','Androsik','Biologia','English','01/2024'),('90742','Sianna','Calfe','Ingenieria y arquitectura','Biology','01/2024'),('90850','Athene','Farrow','Biologia','English','01/2024'),('90888','Jen','Whitley','Derecho','History','01/2023'),('91573','Florie','Toone','Derecho','History','02/2023'),('91575','Pamela','Hubball','Ingenieria y arquitectura','Psychology','02/2023'),('91755','Rudd','Swigg','Economia','Chemistry','01/2024'),('92082','Brenna','Satteford','Biologia','Psychology','02/2023'),('92253','Florie','Savary','Derecho','Biology','01/2024'),('92593','Cleve','Ellcome','Medicina','Computer Science','02/2023'),('92594','Nil','Bloys','Derecho','History','02/2024'),('92954','Paulina','Farthing','Biologia','Chemistry','02/2023'),('93293','Hercule','Dell\'Abbate','Biologia','Chemistry','02/2023'),('93513','Steve','Frankcombe','Biologia','Psychology','02/2023'),('94216','Cathlene','Pooke','Derecho','English','01/2024'),('94524','Celeste','Corey','Ingenieria y arquitectura','Biology','01/2023'),('94598','Tonnie','Burless','Derecho','Biology','02/2023'),('94821','Michaeline','Bowe','Medicina','History','01/2024'),('94842','Cash','Joderli','Biologia','Biology','02/2024'),('95086','Lauren','Henkmann','Ingenieria y arquitectura','History','01/2024'),('95283','Granville','Blodgett','Derecho','Mathematics','01/2024'),('95491','Marjorie','Kalisch','Biologia','History','01/2024'),('95562','Niels','Roby','Derecho','Mathematics','01/2023'),('95581','Ashia','Brydson','Derecho','Psychology','02/2023'),('95761','Deloria','Madre','Ingenieria y arquitectura','English','01/2024'),('95813','Irena','Trunks','Ingenieria y arquitectura','History','01/2023'),('95920','Annabel','Casarili','Biologia','English','01/2024'),('95946','Vernice','Hendrik','Derecho','History','01/2023'),('96011','Burlie','Bourgaize','Economia','History','01/2023'),('96045','Bren','Dikels','Biologia','Biology','02/2024'),('96375','Kristyn','Gairdner','Ingenieria y arquitectura','History','02/2023'),('96563','Ike','Olligan','Derecho','English','01/2023'),('96902','Connor','Vance','Medicina','Computer Science','01/2024'),('97055','Kiele','McGawn','Biologia','Chemistry','01/2023'),('97322','Hedvige','Ondracek','Medicina','Chemistry','01/2023'),('97514','Georgia','Theze','Ingenieria y arquitectura','History','01/2024'),('97860','Jory','Kittless','Medicina','English','02/2024'),('97978','Gideon','Spellar','Ingenieria y arquitectura','History','01/2023'),('97996','Helenka','Kither','Derecho','History','01/2023'),('98134','Tiena','Sifleet','Medicina','Computer Science','02/2023'),('98433','Malanie','Adamek','Derecho','Computer Science','01/2024'),('98455','Lethia','Revett','Derecho','Chemistry','01/2023'),('98542','Harbert','Wombwell','Medicina','Psychology','01/2023'),('98576','Katie','O\'Hoolahan','Biologia','Biology','01/2024'),('98776','Robers','Bichener','Derecho','History','02/2023'),('99054','Elston','Grigoletti','Biologia','Chemistry','01/2023'),('99256','Nolan','Jacmard','Derecho','History','02/2024'),('99302','Abbie','Stobbe','Medicina','English','02/2024'),('99316','Tomasine','Carnaman','Biologia','Mathematics','01/2024'),('99668','Tulley','Spykins','Ingenieria y arquitectura','Mathematics','02/2023');
/*!40000 ALTER TABLE `gc_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_conf_llamado`
--

DROP TABLE IF EXISTS `gc_conf_llamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_conf_llamado` (
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_NUM_LLAMADAS` int NOT NULL,
  `N_INTERVALO_LLAMADA` int NOT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `S_MENSAJE` varchar(1024) NOT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `S_DESCRIPCION` varchar(256) DEFAULT NULL,
  `N_CONFLLAMADO_ID` int NOT NULL,
  PRIMARY KEY (`N_CONFLLAMADO_ID`),
  UNIQUE KEY `gc_conf_llamado$GC_CONF_LLAMADO_PK` (`N_CONFLLAMADO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_conf_llamado`
--

LOCK TABLES `gc_conf_llamado` WRITE;
/*!40000 ALTER TABLE `gc_conf_llamado` DISABLE KEYS */;
INSERT INTO `gc_conf_llamado` VALUES ('80048',3,10,1,'Prueba  de mensaje','ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00',NULL,1),('80054',5,8,1,'PruebaSQLSERVER','ever.argueta','ever.argueta','2016-12-05 08:56:59','2016-12-05 08:56:59','2016-12-05 08:56:59','Prueba SQLSERVER',2),('80052',3,15,1,'Mensaje santa ana','sm.administrador','sm.administrador','2018-03-06 09:59:48','2018-03-06 09:59:48','2018-03-06 09:59:48','',3),('80053',3,15,1,'Mensaje san miguel','sm.administrador','sm.administrador','2018-03-06 10:00:37','2018-03-06 10:00:37','2018-03-06 10:00:37','',4);
/*!40000 ALTER TABLE `gc_conf_llamado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_conf_repro`
--

DROP TABLE IF EXISTS `gc_conf_repro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_conf_repro` (
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_ORDEN` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_conf_repro`
--

LOCK TABLES `gc_conf_repro` WRITE;
/*!40000 ALTER TABLE `gc_conf_repro` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_conf_repro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_conf_tiquete`
--

DROP TABLE IF EXISTS `gc_conf_tiquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_conf_tiquete` (
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `X_CONTENIDO` longtext NOT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `N_CONF_TIQ_ID` int NOT NULL,
  `N_VERSION` int DEFAULT NULL,
  `X_IMAGEN` longblob,
  PRIMARY KEY (`N_CONF_TIQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_conf_tiquete`
--

LOCK TABLES `gc_conf_tiquete` WRITE;
/*!40000 ALTER TABLE `gc_conf_tiquete` DISABLE KEYS */;
INSERT INTO `gc_conf_tiquete` VALUES ('80048',1,'<html> <head> <meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo> </head> <body> <div class=\"marco\" style=\"width:250px; margin:4px auto;\"> <div class=\"inn\"> <center><h2>[0]</h2></center> </div> <div class=\"inn\"> <center><h4>[1]</h4></center> </div> <div class=\"inn\"> <center><h4>[2]</h4></center> </div> <div class=\"inn\"> <center><h1><b>[6]</h1><b></center></div> <div class=\"inn\"> <center>Espera Aproximada: [7]</center> </div><div class=\"inn\"> <h4><center>[9] [10]</center></h4> </div> </center> </div> </body></html>','ever.argueta','ss.administrador',NULL,'2016-05-19 00:00:00','2018-02-15 14:20:23',2,1,NULL),('80054',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ever.argueta','ever.argueta',NULL,'2016-11-03 17:45:57','2016-12-01 16:46:07',3,1,NULL),('80052',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:24',NULL,4,1,NULL),('80053',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,5,1,NULL),('80055',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,6,1,NULL),('80056',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,7,1,NULL),('80057',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,8,1,NULL),('80058',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,9,1,NULL),('80059',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,10,1,NULL),('80060',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,11,1,NULL),('80062',1,'<html><head><meta charset=\"UTF-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <estilo></head><body><div class=\"marco\" style=\"width:250px; margin:4px auto;\"><center><div class=\"inn\">[0]</div><div class=\"inn\">[1]</div><div class=\"inn\">[2]</div><div class=\"inn\">[6]</div><div class=\"inn\">Espera Aproximada: [7]</div><div class=\"inn\">[9] [10]</div><div class=\"inn\">[14]</div></center></div></body></html>','ss.administrador','ss.administrador',NULL,'2018-03-06 09:58:34',NULL,12,1,NULL);
/*!40000 ALTER TABLE `gc_conf_tiquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_conf_tramite`
--

DROP TABLE IF EXISTS `gc_conf_tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_conf_tramite` (
  `N_CONF_TRA_ID` int NOT NULL AUTO_INCREMENT,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_TRAMITE_ID` int NOT NULL,
  `N_ATENCION_PROM` int NOT NULL,
  `N_TIEMPO_ESPERA` int DEFAULT NULL,
  `N_TIEMPO_HOLGURA` int DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `N_COMPORTAMIENTO` int DEFAULT NULL,
  `N_PESO` int DEFAULT NULL,
  `N_PROM_ESPERA` double DEFAULT NULL,
  `N_PROM_ATENCION` double DEFAULT NULL,
  PRIMARY KEY (`N_CONF_TRA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=520 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_conf_tramite`
--

LOCK TABLES `gc_conf_tramite` WRITE;
/*!40000 ALTER TABLE `gc_conf_tramite` DISABLE KEYS */;
INSERT INTO `gc_conf_tramite` VALUES (76,'80048',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(77,'80048',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(78,'80048',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(79,'80048',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(80,'80048',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(81,'80048',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(82,'80048',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(83,'80048',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(84,'80048',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(85,'80048',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(86,'80048',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(87,'80048',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(88,'80048',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(89,'80048',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(90,'80048',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(91,'80048',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(92,'80048',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(93,'80048',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(94,'80048',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(95,'80048',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(96,'80048',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(97,'80048',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(98,'80048',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(99,'80048',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(100,'80048',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(101,'80048',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(102,'80048',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(103,'80048',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(104,'80048',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(105,'80048',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(106,'80048',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(107,'80048',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(108,'80048',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(109,'80048',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(110,'80048',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(111,'80048',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(112,'80048',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(113,'80052',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(114,'80052',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(115,'80052',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(116,'80052',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(117,'80052',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(118,'80052',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(119,'80052',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(120,'80052',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(121,'80052',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(122,'80052',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(123,'80052',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(124,'80052',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(125,'80052',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(126,'80052',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(127,'80052',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(128,'80052',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(129,'80052',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(130,'80052',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(131,'80052',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(132,'80052',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(133,'80052',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(134,'80052',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(135,'80052',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(136,'80052',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(137,'80052',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(138,'80052',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(139,'80052',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(140,'80052',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(141,'80052',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(142,'80052',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(143,'80052',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(144,'80052',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(145,'80052',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(146,'80052',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(147,'80052',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(148,'80052',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(149,'80052',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(150,'80053',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(151,'80053',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(152,'80053',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(153,'80053',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(154,'80053',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(155,'80053',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(156,'80053',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(157,'80053',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(158,'80053',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(159,'80053',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(160,'80053',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(161,'80053',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(162,'80053',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(163,'80053',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(164,'80053',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(165,'80053',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(166,'80053',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(167,'80053',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(168,'80053',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(169,'80053',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(170,'80053',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(171,'80053',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(172,'80053',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(173,'80053',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(174,'80053',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(175,'80053',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(176,'80053',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(177,'80053',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(178,'80053',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(179,'80053',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(180,'80053',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(181,'80053',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(182,'80053',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(183,'80053',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(184,'80053',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(185,'80053',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(186,'80053',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(187,'80054',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(188,'80054',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(189,'80054',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(190,'80054',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(191,'80054',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(192,'80054',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(193,'80054',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(194,'80054',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(195,'80054',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(196,'80054',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(197,'80054',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(198,'80054',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(199,'80054',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(200,'80054',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(201,'80054',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(202,'80054',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(203,'80054',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(204,'80054',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(205,'80054',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(206,'80054',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(207,'80054',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(208,'80054',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(209,'80054',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(210,'80054',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(211,'80054',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(212,'80054',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(213,'80054',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(214,'80054',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(215,'80054',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(216,'80054',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(217,'80054',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(218,'80054',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(219,'80054',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(220,'80054',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(221,'80054',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(222,'80054',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(223,'80054',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(224,'80055',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(225,'80055',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(226,'80055',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(227,'80055',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(228,'80055',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(229,'80055',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(230,'80055',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(231,'80055',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(232,'80055',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(233,'80055',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(234,'80055',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(235,'80055',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(236,'80055',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(237,'80055',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(238,'80055',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(239,'80055',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(240,'80055',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(241,'80055',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(242,'80055',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(243,'80055',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(244,'80055',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(245,'80055',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(246,'80055',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(247,'80055',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(248,'80055',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(249,'80055',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(250,'80055',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(251,'80055',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(252,'80055',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(253,'80055',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(254,'80055',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(255,'80055',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(256,'80055',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(257,'80055',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(258,'80055',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(259,'80055',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(260,'80055',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(261,'80056',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(262,'80056',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(263,'80056',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(264,'80056',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(265,'80056',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(266,'80056',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(267,'80056',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(268,'80056',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(269,'80056',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(270,'80056',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(271,'80056',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(272,'80056',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(273,'80056',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(274,'80056',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(275,'80056',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(276,'80056',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(277,'80056',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(278,'80056',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(279,'80056',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(280,'80056',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(281,'80056',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(282,'80056',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(283,'80056',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(284,'80056',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(285,'80056',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(286,'80056',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(287,'80056',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(288,'80056',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(289,'80056',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(290,'80056',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(291,'80056',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(292,'80056',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(293,'80056',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(294,'80056',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(295,'80056',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(296,'80056',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(297,'80056',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(298,'80057',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(299,'80057',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(300,'80057',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(301,'80057',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(302,'80057',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(303,'80057',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(304,'80057',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(305,'80057',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(306,'80057',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(307,'80057',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(308,'80057',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(309,'80057',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(310,'80057',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(311,'80057',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(312,'80057',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(313,'80057',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(314,'80057',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(315,'80057',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(316,'80057',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(317,'80057',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(318,'80057',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(319,'80057',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(320,'80057',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(321,'80057',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(322,'80057',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(323,'80057',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(324,'80057',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(325,'80057',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(326,'80057',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(327,'80057',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(328,'80057',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(329,'80057',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(330,'80057',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(331,'80057',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(332,'80057',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(333,'80057',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(334,'80057',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(335,'80058',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(336,'80058',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(337,'80058',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(338,'80058',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(339,'80058',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(340,'80058',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(341,'80058',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(342,'80058',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(343,'80058',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(344,'80058',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(345,'80058',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(346,'80058',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(347,'80058',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(348,'80058',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(349,'80058',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(350,'80058',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(351,'80058',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(352,'80058',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(353,'80058',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(354,'80058',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(355,'80058',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(356,'80058',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(357,'80058',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(358,'80058',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(359,'80058',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(360,'80058',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(361,'80058',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(362,'80058',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(363,'80058',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(364,'80058',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(365,'80058',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(366,'80058',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(367,'80058',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(368,'80058',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(369,'80058',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(370,'80058',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(371,'80058',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(372,'80059',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(373,'80059',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(374,'80059',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(375,'80059',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(376,'80059',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(377,'80059',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(378,'80059',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(379,'80059',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(380,'80059',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(381,'80059',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(382,'80059',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(383,'80059',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(384,'80059',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(385,'80059',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(386,'80059',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(387,'80059',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(388,'80059',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(389,'80059',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(390,'80059',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(391,'80059',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(392,'80059',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(393,'80059',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(394,'80059',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(395,'80059',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(396,'80059',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(397,'80059',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(398,'80059',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(399,'80059',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(400,'80059',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(401,'80059',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(402,'80059',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(403,'80059',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(404,'80059',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(405,'80059',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(406,'80059',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(407,'80059',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(408,'80059',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(409,'80060',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(410,'80060',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(411,'80060',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(412,'80060',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(413,'80060',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(414,'80060',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(415,'80060',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(416,'80060',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(417,'80060',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(418,'80060',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(419,'80060',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(420,'80060',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(421,'80060',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(422,'80060',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(423,'80060',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(424,'80060',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(425,'80060',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(426,'80060',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(427,'80060',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(428,'80060',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(429,'80060',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(430,'80060',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(431,'80060',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(432,'80060',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(433,'80060',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(434,'80060',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(435,'80060',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(436,'80060',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(437,'80060',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(438,'80060',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(439,'80060',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(440,'80060',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(441,'80060',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(442,'80060',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(443,'80060',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(444,'80060',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(445,'80060',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(446,'80061',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(447,'80061',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(448,'80061',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(449,'80061',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(450,'80061',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(451,'80061',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(452,'80061',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(453,'80061',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(454,'80061',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(455,'80061',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(456,'80061',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(457,'80061',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(458,'80061',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(459,'80061',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(460,'80061',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(461,'80061',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(462,'80061',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(463,'80061',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(464,'80061',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(465,'80061',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(466,'80061',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(467,'80061',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(468,'80061',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(469,'80061',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(470,'80061',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(471,'80061',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(472,'80061',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(473,'80061',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(474,'80061',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(475,'80061',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(476,'80061',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(477,'80061',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(478,'80061',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(479,'80061',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(480,'80061',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(481,'80061',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(482,'80061',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(483,'80062',22,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(484,'80062',23,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(485,'80062',24,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(486,'80062',25,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(487,'80062',26,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(488,'80062',27,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(489,'80062',28,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(490,'80062',29,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(491,'80062',30,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(492,'80062',31,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(493,'80062',32,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(494,'80062',33,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(495,'80062',34,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(496,'80062',35,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(497,'80062',36,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(498,'80062',37,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(499,'80062',38,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(500,'80062',39,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(501,'80062',40,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(502,'80062',41,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(503,'80062',42,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(504,'80062',43,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(505,'80062',44,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(506,'80062',45,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(507,'80062',46,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(508,'80062',47,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(509,'80062',48,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(510,'80062',49,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(511,'80062',50,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(512,'80062',51,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(513,'80062',52,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(514,'80062',53,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(515,'80062',54,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(516,'80062',55,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(517,'80062',56,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(518,'80062',57,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL),(519,'80062',58,10,30,1,'ss.administrador','ss.administrador','2024-08-31 04:38:52','2024-08-31 04:38:52','2024-08-31 04:38:52',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `gc_conf_tramite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_equipos`
--

DROP TABLE IF EXISTS `gc_equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_equipos` (
  `N_EQUIPO_ID` int NOT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_TIPO_ID` int NOT NULL,
  `S_MARCA` varchar(100) NOT NULL,
  `S_MODELO` varchar(50) NOT NULL,
  `S_SERIE` varchar(50) NOT NULL,
  `S_NUMERO_INVENTARIO` varchar(50) NOT NULL,
  `D_EQUIPO` varchar(256) DEFAULT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `B_ACTIVA` smallint NOT NULL,
  PRIMARY KEY (`N_EQUIPO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_equipos`
--

LOCK TABLES `gc_equipos` WRITE;
/*!40000 ALTER TABLE `gc_equipos` DISABLE KEYS */;
INSERT INTO `gc_equipos` VALUES (1,'80048',1,'HP','AP2409','AP','XJ19234',NULL,'2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00','ever.argueta','ever.argueta',1),(3,'80048',2,'HP','D673','3000','PC-8373','Para proyecto','2016-12-21 15:49:22','2016-12-21 15:49:22','2016-12-21 15:49:22','ever.argueta','ever.argueta',1),(4,'80048',1,'Toshiba','Satelite','A4362','AX4635','Para proyecto','2016-12-21 16:09:08','2016-12-21 16:09:08','2016-12-21 16:09:08','ever.argueta','ever.argueta',1);
/*!40000 ALTER TABLE `gc_equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_escritorio`
--

DROP TABLE IF EXISTS `gc_escritorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_escritorio` (
  `N_ESCRITORIO_ID` int NOT NULL AUTO_INCREMENT,
  `N_ZONA_ID` int NOT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `D_ESCRITORIO` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `C_IDENTIFICADOR` varchar(1) DEFAULT NULL,
  `N_NUM_ESCRITORIO` int NOT NULL,
  `B_FILA_ESP` smallint NOT NULL,
  PRIMARY KEY (`N_ESCRITORIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_escritorio`
--

LOCK TABLES `gc_escritorio` WRITE;
/*!40000 ALTER TABLE `gc_escritorio` DISABLE KEYS */;
INSERT INTO `gc_escritorio` VALUES (14,1,'80048',3,'','ss.administrador','ce.admin','2018-03-08 14:58:28','2024-09-02 11:08:52',NULL,'C',4,0),(15,1,'80048',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(16,1,'80048',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(17,1,'80048',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(18,1,'80048',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(19,2,'80052',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(20,2,'80052',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(21,2,'80052',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(22,2,'80052',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(23,3,'80053',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(24,3,'80053',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(25,3,'80053',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(26,3,'80053',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(27,4,'80054',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(28,4,'80054',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(29,4,'80054',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(30,4,'80054',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(31,5,'80055',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(32,5,'80055',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(33,5,'80055',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(34,5,'80055',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(35,6,'80056',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(36,6,'80056',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(37,6,'80056',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(38,6,'80056',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(39,7,'80057',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',1,0),(40,7,'80057',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',2,0),(41,7,'80057',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',3,0),(42,7,'80057',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:52',NULL,'2024-08-31 04:33:52','A',4,0),(43,8,'80058',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',1,0),(44,8,'80058',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',2,0),(45,8,'80058',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',3,0),(46,8,'80058',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',4,0),(47,9,'80059',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',1,0),(48,9,'80059',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',2,0),(49,9,'80059',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',3,0),(50,9,'80059',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',4,0),(51,10,'80060',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',1,0),(52,10,'80060',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',2,0),(53,10,'80060',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',3,0),(54,10,'80060',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',4,0),(55,11,'80061',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',1,0),(56,11,'80061',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',2,0),(57,11,'80061',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',3,0),(58,11,'80061',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',4,0),(59,12,'80062',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',1,0),(60,12,'80062',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',2,0),(61,12,'80062',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',3,0),(62,12,'80062',1,NULL,'ss.administrador','ss.administrador','2024-08-31 04:33:53',NULL,'2024-08-31 04:33:53','A',4,0),(63,1,'80048',1,'khhkh','ce.admin','ce.admin','2024-09-02 15:57:09',NULL,'2024-09-02 15:57:09','A',5,0),(64,1,'80052',1,'testing escritorio','ce.admin','ce.admin','2024-09-02 15:57:44',NULL,'2024-09-02 15:57:44','A',5,0),(65,2,'80052',1,'testing escritorio','testing','testing','2024-09-02 16:07:57',NULL,'2024-09-02 16:07:57','B',5,0),(66,2,'80052',1,'testing escritorio','testing','testing','2024-09-02 16:08:45',NULL,'2024-09-02 16:08:45','B',6,0);
/*!40000 ALTER TABLE `gc_escritorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_eventos`
--

DROP TABLE IF EXISTS `gc_eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_eventos` (
  `N_EVENTO_ID` int NOT NULL AUTO_INCREMENT,
  `D_EVENTOS` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`N_EVENTO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_eventos`
--

LOCK TABLES `gc_eventos` WRITE;
/*!40000 ALTER TABLE `gc_eventos` DISABLE KEYS */;
INSERT INTO `gc_eventos` VALUES (1,'Ev1'),(2,'ev2');
/*!40000 ALTER TABLE `gc_eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_jerarquia`
--

DROP TABLE IF EXISTS `gc_jerarquia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_jerarquia` (
  `N_JERARQUIA_ID` bigint NOT NULL AUTO_INCREMENT,
  `S_DESCRIPCION` varchar(512) DEFAULT NULL,
  `S_JERARQUIA` varchar(100) NOT NULL,
  `B_ACTIVA` smallint NOT NULL DEFAULT '0',
  `FI_VIGENCIA` datetime DEFAULT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `C_USUARIO_MODIFICA` varchar(100) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`N_JERARQUIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_jerarquia`
--

LOCK TABLES `gc_jerarquia` WRITE;
/*!40000 ALTER TABLE `gc_jerarquia` DISABLE KEYS */;
INSERT INTO `gc_jerarquia` VALUES (1,NULL,'Jefe',1,'2016-05-19 00:00:00','2016-05-19 00:00:00','ever.argueta','ever.argueta'),(2,'Escalar Cuando se presente algún inconveniente en caja','Jefe de Cajas',1,NULL,'2018-03-08 11:23:09','ss.administrador',NULL),(3,'Jefe local de sucursal','Jefe de Sucursal',1,NULL,'2024-02-16 15:10:24','ss.administrador',NULL),(5,'Gerente de atención cliente','Gerente de Atención',1,'2024-02-16 15:10:04','2024-02-16 15:10:04','ss.administrador','ss.administrador');
/*!40000 ALTER TABLE `gc_jerarquia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_jerarquia_seccion`
--

DROP TABLE IF EXISTS `gc_jerarquia_seccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_jerarquia_seccion` (
  `N_JRQ_SEC_ID` bigint NOT NULL AUTO_INCREMENT,
  `N_JERARQUIA_ID` bigint DEFAULT NULL,
  `S_USUARIO` varchar(256) NOT NULL,
  `N_SERVICIOS_ID` int NOT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `D_UNIDAD_RECEP` varchar(512) DEFAULT NULL,
  `D_JERARQUIAS_SECCION` varchar(512) DEFAULT NULL,
  `FI_VIGENCIA` datetime DEFAULT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `C_USUARIO_MODIFICA` varchar(100) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`N_JRQ_SEC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_jerarquia_seccion`
--

LOCK TABLES `gc_jerarquia_seccion` WRITE;
/*!40000 ALTER TABLE `gc_jerarquia_seccion` DISABLE KEYS */;
INSERT INTO `gc_jerarquia_seccion` VALUES (1,1,'ss.operador4',2,'80048','SUCURSAL SAN SALVADOR','','2018-03-09 18:07:47','2018-03-09 18:07:47','ss.administrador','ss.administrador'),(2,1,'ss.operador',1,'80048','SUCURSAL SAN SALVADOR','','2018-03-09 18:07:47','2018-03-09 18:07:47','ss.administrador','ss.administrador');
/*!40000 ALTER TABLE `gc_jerarquia_seccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_lineas`
--

DROP TABLE IF EXISTS `gc_lineas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_lineas` (
  `LINEA` longtext,
  `NL` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_lineas`
--

LOCK TABLES `gc_lineas` WRITE;
/*!40000 ALTER TABLE `gc_lineas` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_lineas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_multimed_servicio`
--

DROP TABLE IF EXISTS `gc_multimed_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_multimed_servicio` (
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_MULTIMEDIA_ID` int NOT NULL,
  `N_ORDEN` int NOT NULL,
  PRIMARY KEY (`C_UNIDAD_RECEP`,`N_MULTIMEDIA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_multimed_servicio`
--

LOCK TABLES `gc_multimed_servicio` WRITE;
/*!40000 ALTER TABLE `gc_multimed_servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_multimed_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_multimedia`
--

DROP TABLE IF EXISTS `gc_multimedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_multimedia` (
  `N_MULTIMEDIA_ID` int NOT NULL AUTO_INCREMENT,
  `S_TIPO` varchar(256) NOT NULL,
  `N_DURACION` int DEFAULT NULL,
  `FI_MULTIMEDIA` datetime DEFAULT NULL,
  `FF_MULTIMEDIA` datetime DEFAULT NULL,
  `D_MULTIMEDIA` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime DEFAULT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `S_RUTA` varchar(1024) NOT NULL,
  `X_ARCHIVO` longblob,
  `S_MULTIMEDIA` varchar(256) NOT NULL,
  PRIMARY KEY (`N_MULTIMEDIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_multimedia`
--

LOCK TABLES `gc_multimedia` WRITE;
/*!40000 ALTER TABLE `gc_multimedia` DISABLE KEYS */;
INSERT INTO `gc_multimedia` VALUES (1,'video',0,'2024-05-16 17:54:48','2024-05-16 17:54:48','Naturaleza','ever.argueta','ss.administrador','2024-05-16 17:54:48',NULL,'2024-05-16 17:54:48',1,'C:\\Users\\cnolasco\\Documents\\NetBeansProjects\\colas_QM\\target\\colas\\WEB-INF\\classes\\static\\contenido','','documental.mp4'),(2,'video',0,'2024-05-16 17:55:21','2024-05-16 17:55:21','Wild Life','ever.argueta','ss.administrador','2024-05-16 17:55:21',NULL,'2024-05-16 17:55:21',1,'C:\\Users\\cnolasco\\Documents\\NetBeansProjects\\colas_QM\\target\\colas\\WEB-INF\\classes\\static\\contenido','','tv.mp4'),(17,'video',0,'2024-05-16 17:55:46','2024-05-16 17:55:46','oceans','ss.administrador','ss.administrador','2024-05-16 17:55:46',NULL,'2024-05-16 17:55:46',1,'C:\\Users\\cnolasco\\Documents\\NetBeansProjects\\colas_QM\\target\\colas\\WEB-INF\\classes\\static\\contenido',NULL,'Citi.mp4');
/*!40000 ALTER TABLE `gc_multimedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_notificacion`
--

DROP TABLE IF EXISTS `gc_notificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_notificacion` (
  `N_ALERTA_ID` int NOT NULL AUTO_INCREMENT,
  `C_USUARIO` varchar(256) NOT NULL,
  `B_CORREO` smallint NOT NULL DEFAULT '0',
  `B_PANTALLA` smallint NOT NULL DEFAULT '0',
  `FH_NOTIFICACION` datetime NOT NULL,
  `M_ESTADO` varchar(1) NOT NULL,
  PRIMARY KEY (`N_ALERTA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_notificacion`
--

LOCK TABLES `gc_notificacion` WRITE;
/*!40000 ALTER TABLE `gc_notificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_notificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_plantilla_tiq`
--

DROP TABLE IF EXISTS `gc_plantilla_tiq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_plantilla_tiq` (
  `N_PLANTILLA_TIQ` int NOT NULL AUTO_INCREMENT,
  `X_CONTENIDO` longtext,
  `S_NOMBRE` varchar(256) NOT NULL,
  PRIMARY KEY (`N_PLANTILLA_TIQ`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_plantilla_tiq`
--

LOCK TABLES `gc_plantilla_tiq` WRITE;
/*!40000 ALTER TABLE `gc_plantilla_tiq` DISABLE KEYS */;
INSERT INTO `gc_plantilla_tiq` VALUES (1,'<html><head><meta charset=\"UTF-8\"><estilo></head><body><div class=\"marco\"><div class=\"inn\"><center><h2>[0]</h2></center></div><div class=\"inn\"><center><h4>[1]</h4></center></div><div class=\"inn\"><center><h4>[2]</h4></center></div><div class=\"inn\"><center><h1><b>[6]</h1><b></center></div><div class=\"inn\"><center>Espera Aproximada: [7] : Minutos</center></div><div class=\"inn\"><h4><center>[9] [10]</center></h4></div></div></body></html>','Ticket simple');
/*!40000 ALTER TABLE `gc_plantilla_tiq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_preguntas`
--

DROP TABLE IF EXISTS `gc_preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_preguntas` (
  `N_PREGUNTA_ID` int NOT NULL AUTO_INCREMENT,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `S_PREGUNTA` varchar(256) NOT NULL,
  `N_PONDERACION` int NOT NULL,
  `D_PREGUNTA` varchar(512) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`N_PREGUNTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_preguntas`
--

LOCK TABLES `gc_preguntas` WRITE;
/*!40000 ALTER TABLE `gc_preguntas` DISABLE KEYS */;
INSERT INTO `gc_preguntas` VALUES (2,'80048','¿Está satisfecho con la información brindada?',1,NULL,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00'),(3,'80048','¿Se ha resuelto su problema?',1,NULL,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00'),(4,'80048','Volvera nuevamente a la sucursal',1,'','ever.argueta','ss.administrador','2016-05-19 00:00:00','2016-05-19 00:00:00','2024-02-16 15:12:48');
/*!40000 ALTER TABLE `gc_preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_preguntas_respuestas`
--

DROP TABLE IF EXISTS `gc_preguntas_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_preguntas_respuestas` (
  `N_PREGUNTA_RESPUESTA_ID` int NOT NULL AUTO_INCREMENT,
  `N_RESPUESTA_ID` int NOT NULL,
  `FH_RESPONDIO` datetime DEFAULT NULL,
  `C_USUARIO` varchar(256) NOT NULL,
  PRIMARY KEY (`N_PREGUNTA_RESPUESTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_preguntas_respuestas`
--

LOCK TABLES `gc_preguntas_respuestas` WRITE;
/*!40000 ALTER TABLE `gc_preguntas_respuestas` DISABLE KEYS */;
INSERT INTO `gc_preguntas_respuestas` VALUES (1,16,'2016-05-19 00:00:00','ever.argueta'),(2,8,'2016-05-19 00:00:00','ever.argueta');
/*!40000 ALTER TABLE `gc_preguntas_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_prioridad`
--

DROP TABLE IF EXISTS `gc_prioridad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_prioridad` (
  `N_PRIORIDAD_ID` int NOT NULL AUTO_INCREMENT,
  `N_PESO` int NOT NULL,
  `D_PRIORIDAD` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `B_FILA_ESP` smallint NOT NULL,
  `S_NOMBRE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`N_PRIORIDAD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_prioridad`
--

LOCK TABLES `gc_prioridad` WRITE;
/*!40000 ALTER TABLE `gc_prioridad` DISABLE KEYS */;
INSERT INTO `gc_prioridad` VALUES (1,1,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',1,0,'Normal'),(2,2,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',1,1,'Cliente Ocasional'),(3,3,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',1,1,'Cliente Frecuente'),(4,4,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',1,1,'Cliente Premium'),(5,2,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',1,1,'Discapacidad'),(6,4,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',0,1,'Menores de Edad'),(7,1,NULL,'ever.argueta','ever.argueta','2017-05-19 00:00:00','2017-12-31 00:00:00','2017-05-19 00:00:00',0,0,'Tramite Normal'),(8,1,'Usuarios contramites normales','ss.administrador','ss.administrador','2024-01-30 09:40:38','2034-01-30 09:40:38','2024-01-30 09:40:38',1,0,'Cliente Normal'),(9,2,'Mujeres embarazadas','ss.administrador','ss.administrador','2024-01-30 09:41:04','2034-01-30 09:41:04','2024-01-30 09:41:04',1,0,'Embarazada'),(10,2,'Prioridad para 3 edad','ss.administrador','ss.administrador','2024-01-30 09:41:25','2034-01-30 09:41:25','2024-01-30 09:41:25',1,0,'Tercera Edad'),(11,4,'Prioridad para clientes en silla de ruedas o con capacidades especiales','ss.administrador','ss.administrador','2024-02-16 15:11:37','2034-02-16 15:11:37','2024-02-16 15:11:37',1,1,'Con capacidades especiales');
/*!40000 ALTER TABLE `gc_prioridad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_reserva_cita`
--

DROP TABLE IF EXISTS `gc_reserva_cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_reserva_cita` (
  `N_RESERVA_CITA_ID` int NOT NULL AUTO_INCREMENT,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_TRAMITE_ID` int NOT NULL,
  `FH_RESERVACION` datetime NOT NULL,
  `S_CORREO` varchar(256) DEFAULT NULL,
  `NIT` varchar(14) DEFAULT NULL,
  `N_TELEFONO` varchar(25) DEFAULT NULL,
  `S_OBSERVACIONES` varchar(1024) DEFAULT NULL,
  `S_COD_VERIFICA` varchar(50) DEFAULT NULL,
  `N_TIPO_RESERVA` int DEFAULT NULL,
  `NIT_TERCERO` varchar(14) DEFAULT NULL,
  `B_ESTADO` smallint NOT NULL DEFAULT '0',
  PRIMARY KEY (`N_RESERVA_CITA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_reserva_cita`
--

LOCK TABLES `gc_reserva_cita` WRITE;
/*!40000 ALTER TABLE `gc_reserva_cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_reserva_cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_respuestas`
--

DROP TABLE IF EXISTS `gc_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_respuestas` (
  `N_RESPUESTA_ID` int NOT NULL AUTO_INCREMENT,
  `N_PREGUNTA_ID` int NOT NULL,
  `S_RESPUESTA` varchar(128) NOT NULL,
  `N_ESCALA` int NOT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  PRIMARY KEY (`N_RESPUESTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_respuestas`
--

LOCK TABLES `gc_respuestas` WRITE;
/*!40000 ALTER TABLE `gc_respuestas` DISABLE KEYS */;
INSERT INTO `gc_respuestas` VALUES (8,3,'Sí',1,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00'),(9,3,' No',1,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00'),(16,2,'Sí',1,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00'),(17,2,' No',1,'ever.argueta','ever.argueta','2016-05-19 00:00:00','2016-05-19 00:00:00','2016-05-19 00:00:00');
/*!40000 ALTER TABLE `gc_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_seguridad_role`
--

DROP TABLE IF EXISTS `gc_seguridad_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_seguridad_role` (
  `ID_ROLE` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) DEFAULT NULL,
  `DESCRIPCION` varchar(256) DEFAULT NULL,
  `FECHA_INGRESO` datetime NOT NULL,
  `USUARIO_INGRESO` varchar(100) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  `USUARIO_MODIFICA` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_seguridad_role`
--

LOCK TABLES `gc_seguridad_role` WRITE;
/*!40000 ALTER TABLE `gc_seguridad_role` DISABLE KEYS */;
INSERT INTO `gc_seguridad_role` VALUES (1,'ROLE_GC_R1','ROLE DE ADINISTRADOR','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL),(2,'ROLE_GC_R2','ROLE DE ADINISTRADOR LOCAL','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL),(3,'ROLE_GC_R3','ROLE DE ADINISTRADOR GENERAL','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL),(4,'ROLE_GC_R4','ROLE DE OPERADOR','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL),(5,'ROLE_GC_R5','ROLE DE OPERADOR','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL),(6,'ROLE_GC_R6','ROLE DE OPERADOR','2016-05-19 00:00:00','ever.argueta','2016-05-19 00:00:00',NULL);
/*!40000 ALTER TABLE `gc_seguridad_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_seguridad_usuario`
--

DROP TABLE IF EXISTS `gc_seguridad_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_seguridad_usuario` (
  `ID_USUARIO` int NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `CORREO` varchar(100) DEFAULT NULL,
  `CONTRASENA` varchar(256) DEFAULT NULL,
  `FECHA_INGRESO` datetime NOT NULL,
  `USUARIO_INGRESO` varchar(100) DEFAULT NULL,
  `FECHA_MODIFICA` datetime DEFAULT NULL,
  `USUARIO_MODIFICA` varchar(100) DEFAULT NULL,
  `C_UNIDAD_RECEP` varchar(20) DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL DEFAULT '0',
  `USUARIO` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_seguridad_usuario`
--

LOCK TABLES `gc_seguridad_usuario` WRITE;
/*!40000 ALTER TABLE `gc_seguridad_usuario` DISABLE KEYS */;
INSERT INTO `gc_seguridad_usuario` VALUES (1,'Administrador','administrador.sv@gmail.com','$2a$12$Jcd.7YSUzzbanmh43EXFKeCRGk29aCYy5hX6nH9aFUBkqiEFMKREa','2016-05-19 00:00:00','Administrador','2018-02-07 10:22:22','ss.administrador','80048',1,'ss.administrador'),(10,'Ciencias Economicas Operador #1','ce.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80048',1,'ce.operador1'),(11,'Ciencias Economicas Operador #2','ce.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80048',1,'ce.operador2'),(12,'Ciencias Economicas Operador #3','ce.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80048',1,'ce.operador3'),(13,'Ciencias Economicas Operador #4','ce.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80048',1,'ce.operador4'),(14,'Ciencias Economicas Administrador','ce.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80048',1,'ce.admin'),(15,'Ciencias y Humanidades Operador #1','ch.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80053',1,'ch.operador1'),(16,'Ciencias y Humanidades Operador #2','ch.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80053',1,'ch.operador2'),(17,'Ciencias y Humanidades Operador #3','ch.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80053',1,'ch.operador3'),(18,'Ciencias y Humanidades Operador #4','ch.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80053',1,'ch.operador4'),(19,'Ciencias y Humanidades Administrador','ch.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80053',1,'ch.admin'),(20,'Multidisciplinaria de Oriente Operador #1','mo.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80054',1,'mo.operador1'),(21,'Multidisciplinaria de Oriente Operador #2','mo.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80054',1,'mo.operador2'),(22,'Multidisciplinaria de Oriente Operador #3','mo.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80054',1,'mo.operador3'),(23,'Multidisciplinaria de Oriente Operador #4','mo.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80054',1,'mo.operador4'),(24,'Multidisciplinaria de Oriente Administrador','mo.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80054',1,'mo.admin'),(25,'Ingeniería y Arquitectura Operador #1','ia.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80052',1,'ia.operador1'),(26,'Ingeniería y Arquitectura Operador #2','ia.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80052',1,'ia.operador2'),(27,'Ingeniería y Arquitectura Operador #3','ia.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80052',1,'ia.operador3'),(28,'Ingeniería y Arquitectura Operador #4','ia.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80052',1,'ia.operador4'),(29,'Ingeniería y Arquitectura Administrador','ia.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80052',1,'ia.admin'),(30,'Agronomía Operador #1','ag.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80055',1,'ag.operador1'),(31,'Agronomía Operador #2','ag.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80055',1,'ag.operador2'),(32,'Agronomía Operador #3','ag.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80055',1,'ag.operador3'),(33,'Agronomía Operador #4','ag.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80055',1,'ag.operador4'),(34,'Agronomía Administrador','ag.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80055',1,'ag.admin'),(35,'Odontología Operador #1','od.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80056',1,'od.operador1'),(36,'Odontología Operador #2','od.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80056',1,'od.operador2'),(37,'Odontología Operador #3','od.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80056',1,'od.operador3'),(38,'Odontología Operador #4','od.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80056',1,'od.operador4'),(39,'Odontología Administrador','od.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80056',1,'od.admin'),(40,'Medicina Operador #1','md.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80057',1,'md.operador1'),(41,'Medicina Operador #2','md.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80057',1,'md.operador2'),(42,'Medicina Operador #3','md.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80057',1,'md.operador3'),(43,'Medicina Operador #4','md.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80057',1,'md.operador4'),(44,'Medicina Administrador','md.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:52','ss.administrador','2024-08-31 04:33:52','ss.administrador','80057',1,'md.admin'),(45,'Multidisciplinaria Paracentral Operador #1','mp.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80058',1,'mp.operador1'),(46,'Multidisciplinaria Paracentral Operador #2','mp.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80058',1,'mp.operador2'),(47,'Multidisciplinaria Paracentral Operador #3','mp.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80058',1,'mp.operador3'),(48,'Multidisciplinaria Paracentral Operador #4','mp.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80058',1,'mp.operador4'),(49,'Multidisciplinaria Paracentral Administrador','mp.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80058',1,'mp.admin'),(50,'Jurisprudencia y Ciencias Sociales Operador #1','jc.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80059',1,'jc.operador1'),(51,'Jurisprudencia y Ciencias Sociales Operador #2','jc.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80059',1,'jc.operador2'),(52,'Jurisprudencia y Ciencias Sociales Operador #3','jc.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80059',1,'jc.operador3'),(53,'Jurisprudencia y Ciencias Sociales Operador #4','jc.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80059',1,'jc.operador4'),(54,'Jurisprudencia y Ciencias Sociales Administrador','jc.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80059',1,'jc.admin'),(55,'Química y Farmacia Operador #1','qf.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80060',1,'qf.operador1'),(56,'Química y Farmacia Operador #2','qf.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80060',1,'qf.operador2'),(57,'Química y Farmacia Operador #3','qf.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80060',1,'qf.operador3'),(58,'Química y Farmacia Operador #4','qf.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80060',1,'qf.operador4'),(59,'Química y Farmacia Administrador','qf.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80060',1,'qf.admin'),(60,'Ciencias Naturales y Matemática Operador #1','cm.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80061',1,'cm.operador1'),(61,'Ciencias Naturales y Matemática Operador #2','cm.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80061',1,'cm.operador2'),(62,'Ciencias Naturales y Matemática Operador #3','cm.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80061',1,'cm.operador3'),(63,'Ciencias Naturales y Matemática Operador #4','cm.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80061',1,'cm.operador4'),(64,'Ciencias Naturales y Matemática Administrador','cm.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80061',1,'cm.admin'),(65,'Multidisciplinaria de Occidente Operador #1','moc.operador1@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80062',1,'moc.operador1'),(66,'Multidisciplinaria de Occidente Operador #2','moc.operador2@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80062',1,'moc.operador2'),(67,'Multidisciplinaria de Occidente Operador #3','moc.operador3@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80062',1,'moc.operador3'),(68,'Multidisciplinaria de Occidente Operador #4','moc.operador4@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80062',1,'moc.operador4'),(69,'Multidisciplinaria de Occidente Administrador','moc.admin@ues.edu.sv','$2a$12$sx1/wR2f5xXXYCnftFaTZ./2RaqDhZzTrXhvwvjG9bbXa7.NgG4A6','2024-08-31 04:33:53','ss.administrador','2024-08-31 04:33:53','ss.administrador','80062',1,'moc.admin'),(70,'test','test@gmail.com','$2a$10$AyIbzSm/cOOC1Nxk8c18ouErCjjmYVvqjqcNxTa9h8Qpx9gzrxQQi','2024-09-02 15:05:11','ce.admin',NULL,NULL,'80052',1,'testing'),(71,'prueba','prueba@gmail.com','$2a$10$OpdKpriCo36ozCfJHlzXn.QDlGqMghch1W48ifDNPLA3UnZBIZTiO','2024-09-02 16:10:53','testing',NULL,NULL,'80056',1,'prueba');
/*!40000 ALTER TABLE `gc_seguridad_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_seguridad_usuario_role`
--

DROP TABLE IF EXISTS `gc_seguridad_usuario_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_seguridad_usuario_role` (
  `ID_USUARIO` int NOT NULL,
  `ID_ROLE` int NOT NULL,
  PRIMARY KEY (`ID_USUARIO`,`ID_ROLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_seguridad_usuario_role`
--

LOCK TABLES `gc_seguridad_usuario_role` WRITE;
/*!40000 ALTER TABLE `gc_seguridad_usuario_role` DISABLE KEYS */;
INSERT INTO `gc_seguridad_usuario_role` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(10,4),(10,5),(10,6),(11,4),(11,5),(11,6),(12,4),(12,5),(12,6),(13,4),(13,5),(13,6),(14,1),(14,2),(14,3),(14,4),(14,5),(14,6),(15,4),(15,5),(15,6),(16,4),(16,5),(16,6),(17,4),(17,5),(17,6),(18,4),(18,5),(18,6),(19,1),(19,2),(19,3),(19,4),(19,5),(19,6),(20,4),(20,5),(20,6),(21,4),(21,5),(21,6),(22,4),(22,5),(22,6),(23,4),(23,5),(23,6),(24,1),(24,2),(24,3),(24,4),(24,5),(24,6),(25,4),(25,5),(25,6),(26,4),(26,5),(26,6),(27,4),(27,5),(27,6),(28,4),(28,5),(28,6),(29,1),(29,2),(29,3),(29,4),(29,5),(29,6),(30,4),(30,5),(30,6),(31,4),(31,5),(31,6),(32,4),(32,5),(32,6),(33,4),(33,5),(33,6),(34,1),(34,2),(34,3),(34,4),(34,5),(34,6),(35,4),(35,5),(35,6),(36,4),(36,5),(36,6),(37,4),(37,5),(37,6),(38,4),(38,5),(38,6),(39,1),(39,2),(39,3),(39,4),(39,5),(39,6),(40,4),(40,5),(40,6),(41,4),(41,5),(41,6),(42,4),(42,5),(42,6),(43,4),(43,5),(43,6),(44,1),(44,2),(44,3),(44,4),(44,5),(44,6),(45,4),(45,5),(45,6),(46,4),(46,5),(46,6),(47,4),(47,5),(47,6),(48,4),(48,5),(48,6),(49,1),(49,2),(49,3),(49,4),(49,5),(49,6),(50,4),(50,5),(50,6),(51,4),(51,5),(51,6),(52,4),(52,5),(52,6),(53,4),(53,5),(53,6),(54,1),(54,2),(54,3),(54,4),(54,5),(54,6),(55,4),(55,5),(55,6),(56,4),(56,5),(56,6),(57,4),(57,5),(57,6),(58,4),(58,5),(58,6),(59,1),(59,2),(59,3),(59,4),(59,5),(59,6),(60,4),(60,5),(60,6),(61,4),(61,5),(61,6),(62,4),(62,5),(62,6),(63,4),(63,5),(63,6),(64,1),(64,2),(64,3),(64,4),(64,5),(64,6),(65,4),(65,5),(65,6),(66,4),(66,5),(66,6),(67,4),(67,5),(67,6),(68,4),(68,5),(68,6),(69,1),(69,2),(69,3),(69,4),(69,5),(69,6),(70,1),(71,1);
/*!40000 ALTER TABLE `gc_seguridad_usuario_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_servicios`
--

DROP TABLE IF EXISTS `gc_servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_servicios` (
  `N_SERVICIOS_ID` int NOT NULL AUTO_INCREMENT,
  `D_SERVICIOS` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `N_ORDEN` int DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `S_NOMBRE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`N_SERVICIOS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_servicios`
--

LOCK TABLES `gc_servicios` WRITE;
/*!40000 ALTER TABLE `gc_servicios` DISABLE KEYS */;
INSERT INTO `gc_servicios` VALUES (1,'Nuevo ingreso','ss.administrador','ss.administrador','2024-05-03 19:52:08',NULL,'2024-05-03 19:52:08',1,1,'Nuevo ingreso'),(2,'Acciones académicas','ss.administrador','ss.administrador','2024-05-03 19:52:29',NULL,'2024-05-03 19:52:29',2,1,'Acciones académicas'),(3,'Constancias y certificaciones','ss.administrador','ss.administrador','2024-05-03 19:53:00',NULL,'2024-05-03 19:53:00',3,1,'Constancias y certificaciones '),(4,'Graduaciones ','ss.administrador','ss.administrador','2024-05-03 19:54:51',NULL,'2024-05-03 19:54:51',4,1,'Graduaciones '),(5,'Graduados ','ss.administrador','ss.administrador','2024-05-03 19:54:58',NULL,'2024-05-03 19:54:58',5,1,'Graduados'),(6,'Soporte ','ss.administrador','ss.administrador','2024-05-03 19:54:42',NULL,'2024-05-03 19:54:42',6,1,'Soporte '),(7,'Otros ','ss.administrador','ss.administrador','2024-05-03 19:55:22',NULL,'2024-05-03 19:55:22',7,1,'Otros ');
/*!40000 ALTER TABLE `gc_servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_tipo_equipo`
--

DROP TABLE IF EXISTS `gc_tipo_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_tipo_equipo` (
  `N_TIPO_ID` int NOT NULL,
  `D_TIPO_EQUIPO` varchar(512) DEFAULT NULL,
  `S_NOMBRE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`N_TIPO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_tipo_equipo`
--

LOCK TABLES `gc_tipo_equipo` WRITE;
/*!40000 ALTER TABLE `gc_tipo_equipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `gc_tipo_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_tiquete`
--

DROP TABLE IF EXISTS `gc_tiquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_tiquete` (
  `N_TIQUETE_ID` int NOT NULL AUTO_INCREMENT,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_RESERVA_CITA_ID` int DEFAULT NULL,
  `N_PRIORIDAD_ID` int NOT NULL,
  `N_TRAMITE_ID` int NOT NULL,
  `N_TIQUETE_REA` int DEFAULT NULL,
  `S_CORRELATIVO` varchar(25) NOT NULL,
  `NIT` varchar(14) DEFAULT NULL,
  `M_ESTADO` varchar(1) NOT NULL,
  `FH_LLEGADA` datetime DEFAULT NULL,
  `FH_LLAMADO` datetime DEFAULT NULL,
  `FHI_PROCESO` datetime DEFAULT NULL,
  `FHF_PROCESO` datetime DEFAULT NULL,
  `C_USUARIO_ATENDIO` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(256) DEFAULT NULL,
  `N_TIEMPO_HOLGURA` int DEFAULT NULL,
  `N_JRQ_SEC_ID` bigint DEFAULT NULL,
  `TARJETA` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`N_TIQUETE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_tiquete`
--

LOCK TABLES `gc_tiquete` WRITE;
/*!40000 ALTER TABLE `gc_tiquete` DISABLE KEYS */;
INSERT INTO `gc_tiquete` VALUES (13,'80048',NULL,8,51,0,'A1','','1','2024-08-30 22:39:21',NULL,NULL,NULL,NULL,'ce.operador2',NULL,NULL,NULL),(14,'80048',NULL,8,22,0,'A2','','1','2024-08-30 22:39:25',NULL,NULL,NULL,NULL,'ce.operador2',NULL,NULL,NULL),(15,'80052',NULL,8,39,0,'1','','1','2024-08-30 22:39:37',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(16,'80052',NULL,8,51,0,'1','','1','2024-08-31 16:00:36',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(17,'80052',NULL,8,51,0,'D1','','1','2024-08-31 16:26:23',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(18,'80052',NULL,8,51,0,'D2','','1','2024-08-31 16:26:25',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(19,'80052',NULL,8,22,0,'D3','','1','2024-08-31 16:26:29',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(20,'80053',NULL,8,51,0,'G1','','1','2024-08-31 16:29:27',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(21,'80053',NULL,8,51,0,'G2','','1','2024-08-31 16:29:30',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(22,'80053',NULL,8,22,0,'G3','','1','2024-08-31 16:29:33',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(23,'80053',NULL,8,23,0,'G4','','1','2024-08-31 16:29:36',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(24,'80053',NULL,8,25,0,'G5','','1','2024-08-31 16:29:39',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(25,'80054',NULL,8,51,0,'D1','','1','2024-08-31 16:32:13',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(26,'80054',NULL,8,51,0,'D2','','1','2024-08-31 16:32:48',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(27,'80053',NULL,8,51,0,'C1','','1','2024-08-31 16:32:59',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(28,'80053',NULL,8,39,0,'C2','','1','2024-08-31 16:33:02',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(29,'80053',NULL,8,43,0,'C3','','1','2024-08-31 16:33:05',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(30,'80054',NULL,8,22,0,'D3','','1','2024-08-31 16:35:35',NULL,NULL,NULL,NULL,'mo.operador2',NULL,NULL,NULL),(31,'80055',NULL,8,51,0,'E1','','1','2024-08-31 16:37:14',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(32,'80055',NULL,8,39,0,'E2','','1','2024-08-31 16:37:18',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(33,'80055',NULL,8,42,0,'E3','','1','2024-08-31 16:37:20',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(34,'80055',NULL,8,43,0,'E4','','1','2024-08-31 16:37:23',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(35,'80054',NULL,8,51,0,'G1','','1','2024-08-31 16:39:31',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(36,'80054',NULL,8,41,0,'G2','','1','2024-08-31 16:39:36',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(37,'80054',NULL,8,42,0,'G3','','1','2024-08-31 16:39:40',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(38,'80054',NULL,8,43,0,'G4','','1','2024-08-31 16:39:43',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(39,'80054',NULL,8,51,0,'G5','','1','2024-08-31 16:39:50',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(40,'80048',NULL,8,51,0,'A1','','1','2024-08-31 17:05:51',NULL,NULL,NULL,NULL,'ce.operador2',NULL,NULL,NULL),(41,'80048',NULL,8,45,0,'A2','','1','2024-08-31 17:05:55',NULL,NULL,NULL,NULL,'ce.operador2',NULL,NULL,NULL),(42,'80052',NULL,8,25,0,'B1','','1','2024-08-31 17:06:41',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(43,'80052',NULL,8,26,0,'B2','','1','2024-08-31 17:06:44',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(44,'80052',NULL,8,48,0,'B3','','1','2024-08-31 17:06:48',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(45,'80053',NULL,8,39,0,'C4','','1','2024-08-31 17:07:08',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(46,'80053',NULL,8,43,0,'C5','','1','2024-08-31 17:07:11',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(47,'80053',NULL,8,41,0,'C6','','1','2024-08-31 17:07:16',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(48,'80053',NULL,8,48,0,'C7','','1','2024-08-31 17:07:26',NULL,NULL,NULL,NULL,'ch.operador1',NULL,NULL,NULL),(49,'80054',NULL,8,54,0,'D4','','1','2024-08-31 17:07:49',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(50,'80054',NULL,8,55,0,'D5','','1','2024-08-31 17:07:52',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(51,'80054',NULL,8,22,0,'D6','','1','2024-08-31 17:07:55',NULL,NULL,NULL,NULL,'mo.operador1',NULL,NULL,NULL),(52,'80055',NULL,8,54,0,'E5','','1','2024-08-31 17:08:15',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(53,'80055',NULL,8,27,0,'E6','','1','2024-08-31 17:08:20',NULL,NULL,NULL,NULL,'ag.operador1',NULL,NULL,NULL),(54,'80059',NULL,8,48,0,'I1','','1','2024-08-31 17:08:37',NULL,NULL,NULL,NULL,'jc.operador1',NULL,NULL,NULL),(55,'80059',NULL,8,41,0,'I2','','1','2024-08-31 17:08:42',NULL,NULL,NULL,NULL,'jc.operador1',NULL,NULL,NULL),(56,'80048',NULL,8,51,0,'A1','','4','2024-09-02 08:20:19','2024-09-02 09:01:07','2024-09-02 09:01:12','2024-09-02 09:01:14','ce.operador2','ce.operador2',NULL,NULL,NULL),(57,'80052',NULL,8,51,0,'B1','','1','2024-09-02 09:29:56',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(58,'80052',NULL,8,49,0,'B2','','1','2024-09-02 09:30:03',NULL,NULL,NULL,NULL,'ia.operador1',NULL,NULL,NULL),(59,'80048',NULL,8,41,0,'A2','','4','2024-09-02 13:02:05','2024-09-02 13:05:48','2024-09-02 13:05:57','2024-09-02 13:06:03','ce.operador2','ce.operador2',NULL,NULL,NULL),(60,'80048',NULL,8,42,0,'A3','','4','2024-09-02 13:02:09','2024-09-02 13:06:04','2024-09-02 13:06:13','2024-09-02 13:06:13','ce.operador2','ce.operador2',NULL,NULL,NULL),(61,'80048',NULL,8,24,0,'A4','','4','2024-09-02 13:05:29','2024-09-02 13:06:15','2024-09-02 13:06:22','2024-09-02 13:06:23','ce.operador2','ce.operador2',NULL,NULL,NULL),(62,'80048',NULL,8,24,0,'A5','','4','2024-09-02 13:14:28','2024-09-02 13:14:32','2024-09-02 13:14:36','2024-09-02 13:14:38','ce.operador2','ce.operador2',NULL,NULL,NULL),(63,'80048',NULL,8,51,0,'A6','','1','2024-09-02 15:17:49',NULL,NULL,NULL,NULL,'ce.admin',NULL,NULL,NULL),(64,'80052',NULL,8,51,0,'B3','','1','2024-09-02 15:59:16',NULL,NULL,NULL,NULL,'testing',NULL,NULL,NULL),(65,'80052',NULL,8,22,0,'B4','','1','2024-09-02 15:59:25',NULL,NULL,NULL,NULL,'testing',NULL,NULL,NULL),(66,'80052',NULL,8,23,0,'B5','','1','2024-09-02 15:59:29',NULL,NULL,NULL,NULL,'testing',NULL,NULL,NULL),(67,'80056',NULL,8,24,0,'F1','','1','2024-09-02 16:11:31',NULL,NULL,NULL,NULL,'prueba',NULL,NULL,NULL);
/*!40000 ALTER TABLE `gc_tiquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_tiquete_hist`
--

DROP TABLE IF EXISTS `gc_tiquete_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_tiquete_hist` (
  `N_TIQUETE_ID` int NOT NULL,
  `N_RESERVA_CITA_ID` int DEFAULT NULL,
  `N_PRIORIDAD_ID` int NOT NULL,
  `N_TRAMITE_ID` int NOT NULL,
  `N_TIQUETE_REA` int DEFAULT NULL,
  `S_CORRELATIVO` varchar(25) NOT NULL,
  `NIT` varchar(14) DEFAULT NULL,
  `M_ESTADO` int NOT NULL,
  `FH_LLEGADA` datetime DEFAULT NULL,
  `FH_LLAMADO` datetime DEFAULT NULL,
  `FHI_PROCESO` datetime DEFAULT NULL,
  `FHF_PROCESO` datetime DEFAULT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `C_USUARIO_ATENDIO` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(256) DEFAULT NULL,
  `N_TIEMPO_HOLGURA` int DEFAULT NULL,
  `N_JRQ_SEC_ID` int DEFAULT NULL,
  `TARJETA` varbinary(25) DEFAULT NULL,
  PRIMARY KEY (`N_TIQUETE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_tiquete_hist`
--

LOCK TABLES `gc_tiquete_hist` WRITE;
/*!40000 ALTER TABLE `gc_tiquete_hist` DISABLE KEYS */;
INSERT INTO `gc_tiquete_hist` VALUES (1,NULL,6,2,0,'A1',NULL,5,'2018-03-16 14:05:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(2,NULL,2,2,0,'A2',NULL,4,'2018-03-16 14:31:00','2018-03-16 14:40:00','2018-03-16 14:41:00','2018-03-16 14:44:00','80048','usuario','0',NULL,NULL,NULL),(3,NULL,3,6,0,'B3',NULL,4,'2018-03-16 13:54:00','2018-03-16 14:01:00','2018-03-16 14:02:00','2018-03-16 14:03:00','80048','usuario','0',NULL,NULL,NULL),(4,NULL,6,2,0,'B4',NULL,4,'2018-03-16 14:03:00','2018-03-16 14:11:00','2018-03-16 14:12:00','2018-03-16 14:16:00','80048','usuario','0',NULL,NULL,NULL),(5,NULL,1,6,0,'C5',NULL,4,'2018-03-16 14:11:00','2018-03-16 14:17:00','2018-03-16 14:18:00','2018-03-16 14:24:00','80048','usuario','0',NULL,NULL,NULL),(6,NULL,2,19,0,'B6',NULL,4,'2018-03-16 13:56:00','2018-03-16 13:57:00','2018-03-16 13:58:00','2018-03-16 14:01:00','80048','usuario','0',NULL,NULL,NULL),(7,NULL,2,4,0,'B7',NULL,4,'2018-03-16 14:10:00','2018-03-16 14:18:00','2018-03-16 14:19:00','2018-03-16 14:24:00','80048','usuario','0',NULL,NULL,NULL),(8,NULL,2,19,0,'B8',NULL,4,'2018-03-16 14:34:00','2018-03-16 14:37:00','2018-03-16 14:38:00','2018-03-16 14:48:00','80048','usuario','0',NULL,NULL,NULL),(9,NULL,1,19,0,'A9',NULL,4,'2018-03-16 14:09:00','2018-03-16 14:21:00','2018-03-16 14:22:00','2018-03-16 14:27:00','80048','usuario','0',NULL,NULL,NULL),(10,NULL,3,2,0,'C10',NULL,4,'2018-03-16 14:21:00','2018-03-16 14:34:00','2018-03-16 14:35:00','2018-03-16 14:38:00','80048','usuario','0',NULL,NULL,NULL),(11,NULL,1,2,0,'B11',NULL,5,'2018-03-16 14:38:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(12,NULL,6,6,0,'B12',NULL,4,'2018-03-16 14:46:00','2018-03-16 14:52:00','2018-03-16 14:53:00','2018-03-16 14:56:00','80048','usuario','0',NULL,NULL,NULL),(13,NULL,2,19,0,'C13',NULL,5,'2018-03-16 14:39:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(14,NULL,5,6,0,'A14',NULL,5,'2018-03-16 14:23:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(15,NULL,2,20,0,'C15',NULL,4,'2018-03-16 14:15:00','2018-03-16 14:29:00','2018-03-16 14:30:00','2018-03-16 14:32:00','80048','usuario','0',NULL,NULL,NULL),(16,NULL,1,4,0,'C16',NULL,5,'2018-03-16 13:54:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(17,NULL,3,6,0,'C17',NULL,4,'2018-03-16 14:15:00','2018-03-16 14:19:00','2018-03-16 14:20:00','2018-03-16 14:27:00','80048','usuario','0',NULL,NULL,NULL),(18,NULL,5,2,0,'B18',NULL,4,'2018-03-16 14:24:00','2018-03-16 14:30:00','2018-03-16 14:31:00','2018-03-16 14:39:00','80048','usuario','0',NULL,NULL,NULL),(19,NULL,1,2,0,'B19',NULL,4,'2018-03-16 14:46:00','2018-03-16 14:53:00','2018-03-16 14:54:00','2018-03-16 14:55:00','80048','usuario','0',NULL,NULL,NULL),(20,NULL,2,20,0,'A20',NULL,5,'2018-03-16 13:51:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(21,NULL,6,16,0,'A21',NULL,4,'2018-03-16 14:06:00','2018-03-16 14:11:00','2018-03-16 14:12:00','2018-03-16 14:15:00','80048','usuario','0',NULL,NULL,NULL),(22,NULL,2,20,0,'B22',NULL,5,'2018-03-16 14:38:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(23,NULL,3,20,0,'A23',NULL,5,'2018-03-16 14:44:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(24,NULL,3,19,0,'B24',NULL,4,'2018-03-16 14:32:00','2018-03-16 14:37:00','2018-03-16 14:38:00','2018-03-16 14:40:00','80048','usuario','0',NULL,NULL,NULL),(25,NULL,2,16,0,'A25',NULL,5,'2018-03-16 13:52:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(26,NULL,1,19,0,'A26',NULL,4,'2018-03-16 14:19:00','2018-03-16 14:27:00','2018-03-16 14:28:00','2018-03-16 14:39:00','80048','usuario','0',NULL,NULL,NULL),(27,NULL,2,4,0,'A27',NULL,4,'2018-03-16 14:21:00','2018-03-16 14:28:00','2018-03-16 14:29:00','2018-03-16 14:37:00','80048','usuario','0',NULL,NULL,NULL),(28,NULL,6,16,0,'C28',NULL,4,'2018-03-16 14:17:00','2018-03-16 14:23:00','2018-03-16 14:24:00','2018-03-16 14:26:00','80048','usuario','0',NULL,NULL,NULL),(29,NULL,1,6,0,'C29',NULL,4,'2018-03-16 14:36:00','2018-03-16 14:47:00','2018-03-16 14:48:00','2018-03-16 14:55:00','80048','usuario','0',NULL,NULL,NULL),(30,NULL,2,16,0,'A30',NULL,4,'2018-03-16 13:54:00','2018-03-16 14:07:00','2018-03-16 14:08:00','2018-03-16 14:18:00','80048','usuario','0',NULL,NULL,NULL),(31,NULL,3,6,0,'B31',NULL,5,'2018-03-16 14:10:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(32,NULL,5,19,0,'B32',NULL,4,'2018-03-16 14:00:00','2018-03-16 14:08:00','2018-03-16 14:09:00','2018-03-16 14:17:00','80048','usuario','0',NULL,NULL,NULL),(33,NULL,5,19,0,'A33',NULL,5,'2018-03-16 14:38:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(34,NULL,5,2,0,'B34',NULL,4,'2018-03-16 14:41:00','2018-03-16 14:42:00','2018-03-16 14:43:00','2018-03-16 14:54:00','80048','usuario','0',NULL,NULL,NULL),(35,NULL,1,20,0,'A35',NULL,4,'2018-03-16 14:10:00','2018-03-16 14:16:00','2018-03-16 14:17:00','2018-03-16 14:18:00','80048','usuario','0',NULL,NULL,NULL),(36,NULL,1,16,0,'A36',NULL,4,'2018-03-16 14:42:00','2018-03-16 14:43:00','2018-03-16 14:44:00','2018-03-16 14:49:00','80048','usuario','0',NULL,NULL,NULL),(37,NULL,2,20,0,'B37',NULL,5,'2018-03-16 14:10:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(38,NULL,3,4,0,'C38',NULL,4,'2018-03-16 14:09:00','2018-03-16 14:12:00','2018-03-16 14:13:00','2018-03-16 14:22:00','80048','usuario','0',NULL,NULL,NULL),(39,NULL,5,19,0,'B39',NULL,5,'2018-03-16 13:55:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(40,NULL,3,19,0,'A40',NULL,4,'2018-03-16 14:47:00','2018-03-16 14:57:00','2018-03-16 14:58:00','2018-03-16 15:06:00','80048','usuario','0',NULL,NULL,NULL),(41,NULL,4,16,0,'A41',NULL,4,'2018-03-16 14:46:00','2018-03-16 14:56:00','2018-03-16 14:57:00','2018-03-16 15:01:00','80048','usuario','0',NULL,NULL,NULL),(42,NULL,5,19,0,'A42',NULL,4,'2018-03-16 13:53:00','2018-03-16 14:05:00','2018-03-16 14:06:00','2018-03-16 14:16:00','80048','usuario','0',NULL,NULL,NULL),(43,NULL,3,2,0,'B43',NULL,4,'2018-03-16 14:06:00','2018-03-16 14:17:00','2018-03-16 14:18:00','2018-03-16 14:29:00','80048','usuario','0',NULL,NULL,NULL),(44,NULL,3,20,0,'B44',NULL,4,'2018-03-16 14:25:00','2018-03-16 14:35:00','2018-03-16 14:36:00','2018-03-16 14:43:00','80048','usuario','0',NULL,NULL,NULL),(45,NULL,5,2,0,'C45',NULL,4,'2018-03-16 14:09:00','2018-03-16 14:20:00','2018-03-16 14:21:00','2018-03-16 14:24:00','80048','usuario','0',NULL,NULL,NULL),(46,NULL,6,19,0,'C46',NULL,5,'2018-03-16 13:57:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(47,NULL,1,20,0,'C47',NULL,4,'2018-03-16 14:13:00','2018-03-16 14:14:00','2018-03-16 14:15:00','2018-03-16 14:25:00','80048','usuario','0',NULL,NULL,NULL),(48,NULL,6,6,0,'C48',NULL,4,'2018-03-16 14:25:00','2018-03-16 14:35:00','2018-03-16 14:36:00','2018-03-16 14:47:00','80048','usuario','0',NULL,NULL,NULL),(49,NULL,1,16,0,'B49',NULL,4,'2018-03-16 13:57:00','2018-03-16 14:11:00','2018-03-16 14:12:00','2018-03-16 14:19:00','80048','usuario','0',NULL,NULL,NULL),(50,NULL,4,20,0,'B50',NULL,4,'2018-03-16 14:24:00','2018-03-16 14:36:00','2018-03-16 14:37:00','2018-03-16 14:43:00','80048','usuario','0',NULL,NULL,NULL),(51,NULL,4,20,0,'C51',NULL,4,'2018-03-16 14:20:00','2018-03-16 14:25:00','2018-03-16 14:26:00','2018-03-16 14:33:00','80048','usuario','0',NULL,NULL,NULL),(52,NULL,4,19,0,'B52',NULL,4,'2018-03-16 14:16:00','2018-03-16 14:22:00','2018-03-16 14:23:00','2018-03-16 14:25:00','80048','usuario','0',NULL,NULL,NULL),(53,NULL,5,2,0,'C53',NULL,4,'2018-03-16 14:33:00','2018-03-16 14:47:00','2018-03-16 14:48:00','2018-03-16 14:55:00','80048','usuario','0',NULL,NULL,NULL),(54,NULL,4,20,0,'A54',NULL,4,'2018-03-16 14:21:00','2018-03-16 14:27:00','2018-03-16 14:28:00','2018-03-16 14:36:00','80048','usuario','0',NULL,NULL,NULL),(55,NULL,4,4,0,'A55',NULL,4,'2018-03-16 14:23:00','2018-03-16 14:33:00','2018-03-16 14:34:00','2018-03-16 14:41:00','80048','usuario','0',NULL,NULL,NULL),(56,NULL,4,20,0,'C56',NULL,4,'2018-03-16 14:06:00','2018-03-16 14:09:00','2018-03-16 14:10:00','2018-03-16 14:20:00','80048','usuario','0',NULL,NULL,NULL),(57,NULL,2,2,0,'A57',NULL,4,'2018-03-16 14:42:00','2018-03-16 14:53:00','2018-03-16 14:54:00','2018-03-16 14:55:00','80048','usuario','0',NULL,NULL,NULL),(58,NULL,5,2,0,'A58',NULL,5,'2018-03-16 14:22:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(59,NULL,6,19,0,'C59',NULL,4,'2018-03-16 14:29:00','2018-03-16 14:32:00','2018-03-16 14:33:00','2018-03-16 14:43:00','80048','usuario','0',NULL,NULL,NULL),(60,NULL,2,20,0,'B60',NULL,4,'2018-03-16 14:18:00','2018-03-16 14:24:00','2018-03-16 14:25:00','2018-03-16 14:26:00','80048','usuario','0',NULL,NULL,NULL),(61,NULL,3,16,0,'A61',NULL,5,'2018-03-16 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(62,NULL,4,19,0,'C62',NULL,4,'2018-03-16 13:58:00','2018-03-16 14:10:00','2018-03-16 14:11:00','2018-03-16 14:16:00','80048','usuario','0',NULL,NULL,NULL),(63,NULL,2,6,0,'B63',NULL,4,'2018-03-16 14:28:00','2018-03-16 14:29:00','2018-03-16 14:30:00','2018-03-16 14:41:00','80048','usuario','0',NULL,NULL,NULL),(64,NULL,5,2,0,'C64',NULL,5,'2018-03-16 14:15:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(65,NULL,3,20,0,'C65',NULL,4,'2018-03-16 14:00:00','2018-03-16 14:07:00','2018-03-16 14:08:00','2018-03-16 14:15:00','80048','usuario','0',NULL,NULL,NULL),(66,NULL,6,4,0,'B66',NULL,4,'2018-03-16 14:33:00','2018-03-16 14:35:00','2018-03-16 14:36:00','2018-03-16 14:37:00','80048','usuario','0',NULL,NULL,NULL),(67,NULL,1,16,0,'B67',NULL,4,'2018-03-16 14:23:00','2018-03-16 14:24:00','2018-03-16 14:25:00','2018-03-16 14:36:00','80048','usuario','0',NULL,NULL,NULL),(68,NULL,1,4,0,'A68',NULL,4,'2018-03-16 13:53:00','2018-03-16 14:04:00','2018-03-16 14:05:00','2018-03-16 14:16:00','80048','usuario','0',NULL,NULL,NULL),(69,NULL,6,4,0,'A69',NULL,4,'2018-03-16 14:32:00','2018-03-16 14:34:00','2018-03-16 14:35:00','2018-03-16 14:38:00','80048','usuario','0',NULL,NULL,NULL),(70,NULL,5,4,0,'C70',NULL,4,'2018-03-16 14:17:00','2018-03-16 14:25:00','2018-03-16 14:26:00','2018-03-16 14:27:00','80048','usuario','0',NULL,NULL,NULL),(71,NULL,3,6,0,'A71',NULL,4,'2018-03-16 14:49:00','2018-03-16 14:55:00','2018-03-16 14:56:00','2018-03-16 15:07:00','80048','usuario','0',NULL,NULL,NULL),(72,NULL,2,6,0,'B72',NULL,5,'2018-03-16 14:48:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(73,NULL,2,6,0,'B73',NULL,4,'2018-03-16 14:26:00','2018-03-16 14:27:00','2018-03-16 14:28:00','2018-03-16 14:38:00','80048','usuario','0',NULL,NULL,NULL),(74,NULL,4,16,0,'A74',NULL,5,'2018-03-16 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(75,NULL,2,20,0,'A75',NULL,4,'2018-03-16 14:40:00','2018-03-16 14:51:00','2018-03-16 14:52:00','2018-03-16 14:59:00','80048','usuario','0',NULL,NULL,NULL),(76,NULL,2,20,0,'A76',NULL,5,'2018-03-16 14:18:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(77,NULL,3,2,0,'A77',NULL,4,'2018-03-16 14:37:00','2018-03-16 14:38:00','2018-03-16 14:39:00','2018-03-16 14:50:00','80048','usuario','0',NULL,NULL,NULL),(78,NULL,2,16,0,'A78',NULL,4,'2018-03-16 14:06:00','2018-03-16 14:12:00','2018-03-16 14:13:00','2018-03-16 14:21:00','80048','usuario','0',NULL,NULL,NULL),(79,NULL,4,19,0,'A79',NULL,5,'2018-03-16 14:05:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(80,NULL,3,6,0,'A80',NULL,5,'2018-03-16 14:33:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(81,NULL,6,16,0,'C81',NULL,5,'2018-03-17 14:22:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(82,NULL,6,19,0,'B82',NULL,4,'2018-03-17 14:06:00','2018-03-17 14:16:00','2018-03-17 14:17:00','2018-03-17 14:24:00','80048','usuario','0',NULL,NULL,NULL),(83,NULL,1,20,0,'A83',NULL,4,'2018-03-17 14:37:00','2018-03-17 14:51:00','2018-03-17 14:52:00','2018-03-17 14:53:00','80048','usuario','0',NULL,NULL,NULL),(84,NULL,2,19,0,'A84',NULL,4,'2018-03-17 14:46:00','2018-03-17 14:59:00','2018-03-17 15:00:00','2018-03-17 15:04:00','80048','usuario','0',NULL,NULL,NULL),(85,NULL,4,16,0,'B85',NULL,4,'2018-03-17 14:30:00','2018-03-17 14:40:00','2018-03-17 14:41:00','2018-03-17 14:44:00','80048','usuario','0',NULL,NULL,NULL),(86,NULL,2,16,0,'B86',NULL,4,'2018-03-17 14:39:00','2018-03-17 14:43:00','2018-03-17 14:44:00','2018-03-17 14:47:00','80048','usuario','0',NULL,NULL,NULL),(87,NULL,2,4,0,'C87',NULL,4,'2018-03-17 14:27:00','2018-03-17 14:37:00','2018-03-17 14:38:00','2018-03-17 14:39:00','80048','usuario','0',NULL,NULL,NULL),(88,NULL,5,4,0,'B88',NULL,4,'2018-03-17 14:43:00','2018-03-17 14:47:00','2018-03-17 14:48:00','2018-03-17 14:50:00','80048','usuario','0',NULL,NULL,NULL),(89,NULL,5,6,0,'B89',NULL,4,'2018-03-17 14:49:00','2018-03-17 14:50:00','2018-03-17 14:51:00','2018-03-17 14:59:00','80048','usuario','0',NULL,NULL,NULL),(90,NULL,2,16,0,'C90',NULL,4,'2018-03-17 14:23:00','2018-03-17 14:29:00','2018-03-17 14:30:00','2018-03-17 14:36:00','80048','usuario','0',NULL,NULL,NULL),(91,NULL,4,19,0,'A91',NULL,4,'2018-03-17 14:33:00','2018-03-17 14:38:00','2018-03-17 14:39:00','2018-03-17 14:47:00','80048','usuario','0',NULL,NULL,NULL),(92,NULL,4,6,0,'C92',NULL,4,'2018-03-17 14:47:00','2018-03-17 14:54:00','2018-03-17 14:55:00','2018-03-17 15:04:00','80048','usuario','0',NULL,NULL,NULL),(93,NULL,2,4,0,'A93',NULL,4,'2018-03-17 14:26:00','2018-03-17 14:27:00','2018-03-17 14:28:00','2018-03-17 14:38:00','80048','usuario','0',NULL,NULL,NULL),(94,NULL,5,6,0,'A94',NULL,4,'2018-03-17 14:34:00','2018-03-17 14:41:00','2018-03-17 14:42:00','2018-03-17 14:52:00','80048','usuario','0',NULL,NULL,NULL),(95,NULL,4,2,0,'B95',NULL,4,'2018-03-17 14:34:00','2018-03-17 14:44:00','2018-03-17 14:45:00','2018-03-17 14:49:00','80048','usuario','0',NULL,NULL,NULL),(96,NULL,4,6,0,'C96',NULL,4,'2018-03-17 14:15:00','2018-03-17 14:16:00','2018-03-17 14:17:00','2018-03-17 14:18:00','80048','usuario','0',NULL,NULL,NULL),(97,NULL,2,4,0,'B97',NULL,5,'2018-03-17 13:56:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(98,NULL,2,6,0,'A98',NULL,4,'2018-03-17 14:47:00','2018-03-17 15:01:00','2018-03-17 15:02:00','2018-03-17 15:12:00','80048','usuario','0',NULL,NULL,NULL),(99,NULL,4,20,0,'B99',NULL,4,'2018-03-17 13:54:00','2018-03-17 14:05:00','2018-03-17 14:06:00','2018-03-17 14:10:00','80048','usuario','0',NULL,NULL,NULL),(100,NULL,5,2,0,'A100',NULL,4,'2018-03-17 14:35:00','2018-03-17 14:38:00','2018-03-17 14:39:00','2018-03-17 14:43:00','80048','usuario','0',NULL,NULL,NULL),(101,NULL,1,19,0,'A101',NULL,4,'2018-03-17 14:17:00','2018-03-17 14:30:00','2018-03-17 14:31:00','2018-03-17 14:33:00','80048','usuario','0',NULL,NULL,NULL),(102,NULL,4,6,0,'A102',NULL,5,'2018-03-17 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(103,NULL,1,16,0,'A103',NULL,4,'2018-03-17 14:37:00','2018-03-17 14:50:00','2018-03-17 14:51:00','2018-03-17 14:57:00','80048','usuario','0',NULL,NULL,NULL),(104,NULL,1,20,0,'C104',NULL,5,'2018-03-17 13:56:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(105,NULL,1,19,0,'C105',NULL,5,'2018-03-17 14:48:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(106,NULL,3,20,0,'A106',NULL,4,'2018-03-17 14:07:00','2018-03-17 14:14:00','2018-03-17 14:15:00','2018-03-17 14:17:00','80048','usuario','0',NULL,NULL,NULL),(107,NULL,5,19,0,'A107',NULL,4,'2018-03-17 14:00:00','2018-03-17 14:03:00','2018-03-17 14:04:00','2018-03-17 14:15:00','80048','usuario','0',NULL,NULL,NULL),(108,NULL,6,6,0,'A108',NULL,4,'2018-03-17 14:20:00','2018-03-17 14:29:00','2018-03-17 14:30:00','2018-03-17 14:34:00','80048','usuario','0',NULL,NULL,NULL),(109,NULL,6,19,0,'B109',NULL,4,'2018-03-17 14:03:00','2018-03-17 14:11:00','2018-03-17 14:12:00','2018-03-17 14:16:00','80048','usuario','0',NULL,NULL,NULL),(110,NULL,3,2,0,'B110',NULL,4,'2018-03-17 14:02:00','2018-03-17 14:16:00','2018-03-17 14:17:00','2018-03-17 14:25:00','80048','usuario','0',NULL,NULL,NULL),(111,NULL,6,19,0,'C111',NULL,5,'2018-03-17 14:02:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(112,NULL,6,2,0,'B112',NULL,4,'2018-03-17 14:07:00','2018-03-17 14:18:00','2018-03-17 14:19:00','2018-03-17 14:27:00','80048','usuario','0',NULL,NULL,NULL),(113,NULL,3,19,0,'C113',NULL,5,'2018-03-17 14:08:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(114,NULL,3,19,0,'A114',NULL,4,'2018-03-17 14:37:00','2018-03-17 14:49:00','2018-03-17 14:50:00','2018-03-17 14:57:00','80048','usuario','0',NULL,NULL,NULL),(115,NULL,2,16,0,'C115',NULL,4,'2018-03-17 14:08:00','2018-03-17 14:20:00','2018-03-17 14:21:00','2018-03-17 14:23:00','80048','usuario','0',NULL,NULL,NULL),(116,NULL,1,19,0,'A116',NULL,4,'2018-03-17 14:07:00','2018-03-17 14:20:00','2018-03-17 14:21:00','2018-03-17 14:24:00','80048','usuario','0',NULL,NULL,NULL),(117,NULL,2,16,0,'C117',NULL,4,'2018-03-17 14:08:00','2018-03-17 14:15:00','2018-03-17 14:16:00','2018-03-17 14:25:00','80048','usuario','0',NULL,NULL,NULL),(118,NULL,2,16,0,'A118',NULL,4,'2018-03-17 14:34:00','2018-03-17 14:40:00','2018-03-17 14:41:00','2018-03-17 14:44:00','80048','usuario','0',NULL,NULL,NULL),(119,NULL,2,20,0,'B119',NULL,5,'2018-03-17 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(120,NULL,4,4,0,'B120',NULL,4,'2018-03-17 14:33:00','2018-03-17 14:34:00','2018-03-17 14:35:00','2018-03-17 14:43:00','80048','usuario','0',NULL,NULL,NULL),(121,NULL,6,20,0,'A121',NULL,5,'2018-03-17 14:18:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(122,NULL,4,20,0,'C122',NULL,4,'2018-03-17 14:24:00','2018-03-17 14:27:00','2018-03-17 14:28:00','2018-03-17 14:32:00','80048','usuario','0',NULL,NULL,NULL),(123,NULL,4,6,0,'A123',NULL,4,'2018-03-17 14:26:00','2018-03-17 14:28:00','2018-03-17 14:29:00','2018-03-17 14:37:00','80048','usuario','0',NULL,NULL,NULL),(124,NULL,5,4,0,'C124',NULL,4,'2018-03-17 14:30:00','2018-03-17 14:43:00','2018-03-17 14:44:00','2018-03-17 14:50:00','80048','usuario','0',NULL,NULL,NULL),(125,NULL,5,16,0,'C125',NULL,5,'2018-03-17 14:08:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(126,NULL,2,6,0,'A126',NULL,5,'2018-03-17 13:52:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(127,NULL,2,19,0,'A127',NULL,4,'2018-03-17 14:01:00','2018-03-17 14:14:00','2018-03-17 14:15:00','2018-03-17 14:24:00','80048','usuario','0',NULL,NULL,NULL),(128,NULL,1,2,0,'A128',NULL,5,'2018-03-17 13:59:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(129,NULL,3,2,0,'B129',NULL,5,'2018-03-17 14:15:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(130,NULL,3,6,0,'A130',NULL,4,'2018-03-17 14:30:00','2018-03-17 14:37:00','2018-03-17 14:38:00','2018-03-17 14:49:00','80048','usuario','0',NULL,NULL,NULL),(131,NULL,2,16,0,'A131',NULL,5,'2018-03-17 14:30:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(132,NULL,2,16,0,'C132',NULL,4,'2018-03-17 14:34:00','2018-03-17 14:39:00','2018-03-17 14:40:00','2018-03-17 14:42:00','80048','usuario','0',NULL,NULL,NULL),(133,NULL,1,16,0,'A133',NULL,5,'2018-03-17 14:22:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(134,NULL,6,6,0,'A134',NULL,4,'2018-03-17 13:56:00','2018-03-17 14:03:00','2018-03-17 14:04:00','2018-03-17 14:12:00','80048','usuario','0',NULL,NULL,NULL),(135,NULL,1,2,0,'B135',NULL,4,'2018-03-17 14:30:00','2018-03-17 14:38:00','2018-03-17 14:39:00','2018-03-17 14:49:00','80048','usuario','0',NULL,NULL,NULL),(136,NULL,4,2,0,'A136',NULL,5,'2018-03-17 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(137,NULL,2,16,0,'A137',NULL,4,'2018-03-17 14:18:00','2018-03-17 14:30:00','2018-03-17 14:31:00','2018-03-17 14:32:00','80048','usuario','0',NULL,NULL,NULL),(138,NULL,1,2,0,'C138',NULL,4,'2018-03-17 14:44:00','2018-03-17 14:47:00','2018-03-17 14:48:00','2018-03-17 14:56:00','80048','usuario','0',NULL,NULL,NULL),(139,NULL,3,6,0,'C139',NULL,5,'2018-03-17 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(140,NULL,4,4,0,'A140',NULL,4,'2018-03-17 14:28:00','2018-03-17 14:39:00','2018-03-17 14:40:00','2018-03-17 14:45:00','80048','usuario','0',NULL,NULL,NULL),(141,NULL,4,6,0,'A141',NULL,4,'2018-03-17 14:10:00','2018-03-17 14:21:00','2018-03-17 14:22:00','2018-03-17 14:25:00','80048','usuario','0',NULL,NULL,NULL),(142,NULL,2,6,0,'A142',NULL,4,'2018-03-17 14:46:00','2018-03-17 14:58:00','2018-03-17 14:59:00','2018-03-17 15:06:00','80048','usuario','0',NULL,NULL,NULL),(143,NULL,6,16,0,'C143',NULL,4,'2018-03-17 14:12:00','2018-03-17 14:15:00','2018-03-17 14:16:00','2018-03-17 14:19:00','80048','usuario','0',NULL,NULL,NULL),(144,NULL,1,20,0,'C144',NULL,4,'2018-03-17 14:33:00','2018-03-17 14:36:00','2018-03-17 14:37:00','2018-03-17 14:46:00','80048','usuario','0',NULL,NULL,NULL),(145,NULL,6,19,0,'B145',NULL,5,'2018-03-17 13:55:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(146,NULL,6,20,0,'B146',NULL,4,'2018-03-17 14:01:00','2018-03-17 14:05:00','2018-03-17 14:06:00','2018-03-17 14:15:00','80048','usuario','0',NULL,NULL,NULL),(147,NULL,1,6,0,'A147',NULL,4,'2018-03-17 13:53:00','2018-03-17 13:57:00','2018-03-17 13:58:00','2018-03-17 14:08:00','80048','usuario','0',NULL,NULL,NULL),(148,NULL,4,16,0,'C148',NULL,4,'2018-03-17 14:09:00','2018-03-17 14:12:00','2018-03-17 14:13:00','2018-03-17 14:16:00','80048','usuario','0',NULL,NULL,NULL),(149,NULL,5,6,0,'B149',NULL,4,'2018-03-17 14:25:00','2018-03-17 14:36:00','2018-03-17 14:37:00','2018-03-17 14:41:00','80048','usuario','0',NULL,NULL,NULL),(150,NULL,1,4,0,'A150',NULL,5,'2018-03-17 14:03:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(151,NULL,1,2,0,'C151',NULL,5,'2018-03-17 14:39:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(152,NULL,5,19,0,'A152',NULL,4,'2018-03-17 14:39:00','2018-03-17 14:46:00','2018-03-17 14:47:00','2018-03-17 14:49:00','80048','usuario','0',NULL,NULL,NULL),(153,NULL,5,20,0,'A153',NULL,5,'2018-03-17 14:32:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(154,NULL,1,19,0,'A154',NULL,5,'2018-03-17 14:11:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(155,NULL,4,20,0,'C155',NULL,4,'2018-03-17 14:44:00','2018-03-17 14:56:00','2018-03-17 14:57:00','2018-03-17 15:04:00','80048','usuario','0',NULL,NULL,NULL),(156,NULL,1,4,0,'C156',NULL,4,'2018-03-17 13:51:00','2018-03-17 14:03:00','2018-03-17 14:04:00','2018-03-17 14:06:00','80048','usuario','0',NULL,NULL,NULL),(157,NULL,3,20,0,'B157',NULL,4,'2018-03-17 14:14:00','2018-03-17 14:23:00','2018-03-17 14:24:00','2018-03-17 14:34:00','80048','usuario','0',NULL,NULL,NULL),(158,NULL,3,16,0,'A158',NULL,4,'2018-03-17 14:16:00','2018-03-17 14:20:00','2018-03-17 14:21:00','2018-03-17 14:32:00','80048','usuario','0',NULL,NULL,NULL),(159,NULL,3,2,0,'B159',NULL,4,'2018-03-17 14:32:00','2018-03-17 14:35:00','2018-03-17 14:36:00','2018-03-17 14:44:00','80048','usuario','0',NULL,NULL,NULL),(160,NULL,1,16,0,'A160',NULL,4,'2018-03-17 14:13:00','2018-03-17 14:27:00','2018-03-17 14:28:00','2018-03-17 14:30:00','80048','usuario','0',NULL,NULL,NULL),(161,NULL,2,19,0,'B161',NULL,4,'2018-03-18 14:27:00','2018-03-18 14:36:00','2018-03-18 14:37:00','2018-03-18 14:41:00','80048','usuario','0',NULL,NULL,NULL),(162,NULL,1,20,0,'A162',NULL,5,'2018-03-18 14:16:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(163,NULL,2,4,0,'A163',NULL,4,'2018-03-18 14:46:00','2018-03-18 14:56:00','2018-03-18 14:57:00','2018-03-18 15:08:00','80048','usuario','0',NULL,NULL,NULL),(164,NULL,4,19,0,'B164',NULL,5,'2018-03-18 14:39:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(165,NULL,6,2,0,'B165',NULL,5,'2018-03-18 14:29:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(166,NULL,4,2,0,'C166',NULL,4,'2018-03-18 14:10:00','2018-03-18 14:15:00','2018-03-18 14:16:00','2018-03-18 14:18:00','80048','usuario','0',NULL,NULL,NULL),(167,NULL,4,2,0,'A167',NULL,4,'2018-03-18 14:21:00','2018-03-18 14:27:00','2018-03-18 14:28:00','2018-03-18 14:33:00','80048','usuario','0',NULL,NULL,NULL),(168,NULL,5,6,0,'B168',NULL,5,'2018-03-18 14:05:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(169,NULL,3,4,0,'A169',NULL,5,'2018-03-18 14:30:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(170,NULL,5,6,0,'A170',NULL,4,'2018-03-18 14:11:00','2018-03-18 14:21:00','2018-03-18 14:22:00','2018-03-18 14:23:00','80048','usuario','0',NULL,NULL,NULL),(171,NULL,3,16,0,'B171',NULL,4,'2018-03-18 14:05:00','2018-03-18 14:14:00','2018-03-18 14:15:00','2018-03-18 14:20:00','80048','usuario','0',NULL,NULL,NULL),(172,NULL,1,19,0,'C172',NULL,5,'2018-03-18 13:54:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(173,NULL,1,6,0,'C173',NULL,4,'2018-03-18 13:57:00','2018-03-18 14:04:00','2018-03-18 14:05:00','2018-03-18 14:13:00','80048','usuario','0',NULL,NULL,NULL),(174,NULL,1,19,0,'A174',NULL,5,'2018-03-18 14:09:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(175,NULL,6,2,0,'A175',NULL,5,'2018-03-18 14:32:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(176,NULL,3,6,0,'A176',NULL,4,'2018-03-18 14:06:00','2018-03-18 14:18:00','2018-03-18 14:19:00','2018-03-18 14:24:00','80048','usuario','0',NULL,NULL,NULL),(177,NULL,1,20,0,'C177',NULL,5,'2018-03-18 14:43:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(178,NULL,4,20,0,'C178',NULL,4,'2018-03-18 14:21:00','2018-03-18 14:32:00','2018-03-18 14:33:00','2018-03-18 14:36:00','80048','usuario','0',NULL,NULL,NULL),(179,NULL,5,19,0,'B179',NULL,4,'2018-03-18 13:52:00','2018-03-18 14:05:00','2018-03-18 14:06:00','2018-03-18 14:11:00','80048','usuario','0',NULL,NULL,NULL),(180,NULL,3,6,0,'C180',NULL,4,'2018-03-18 14:32:00','2018-03-18 14:33:00','2018-03-18 14:34:00','2018-03-18 14:42:00','80048','usuario','0',NULL,NULL,NULL),(181,NULL,4,6,0,'A181',NULL,4,'2018-03-18 13:56:00','2018-03-18 14:05:00','2018-03-18 14:06:00','2018-03-18 14:08:00','80048','usuario','0',NULL,NULL,NULL),(182,NULL,2,2,0,'A182',NULL,4,'2018-03-18 14:21:00','2018-03-18 14:32:00','2018-03-18 14:33:00','2018-03-18 14:44:00','80048','usuario','0',NULL,NULL,NULL),(183,NULL,3,19,0,'C183',NULL,5,'2018-03-18 14:07:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(184,NULL,1,16,0,'B184',NULL,4,'2018-03-18 14:02:00','2018-03-18 14:16:00','2018-03-18 14:17:00','2018-03-18 14:22:00','80048','usuario','0',NULL,NULL,NULL),(185,NULL,3,6,0,'B185',NULL,4,'2018-03-18 14:09:00','2018-03-18 14:19:00','2018-03-18 14:20:00','2018-03-18 14:21:00','80048','usuario','0',NULL,NULL,NULL),(186,NULL,3,19,0,'A186',NULL,5,'2018-03-18 14:03:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(187,NULL,3,20,0,'C187',NULL,4,'2018-03-18 14:01:00','2018-03-18 14:10:00','2018-03-18 14:11:00','2018-03-18 14:14:00','80048','usuario','0',NULL,NULL,NULL),(188,NULL,2,4,0,'A188',NULL,4,'2018-03-18 14:45:00','2018-03-18 14:49:00','2018-03-18 14:50:00','2018-03-18 14:54:00','80048','usuario','0',NULL,NULL,NULL),(189,NULL,2,6,0,'A189',NULL,5,'2018-03-18 14:35:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(190,NULL,3,16,0,'B190',NULL,4,'2018-03-18 14:29:00','2018-03-18 14:35:00','2018-03-18 14:36:00','2018-03-18 14:43:00','80048','usuario','0',NULL,NULL,NULL),(191,NULL,4,16,0,'A191',NULL,5,'2018-03-18 14:07:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(192,NULL,6,16,0,'A192',NULL,5,'2018-03-18 13:56:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(193,NULL,6,6,0,'B193',NULL,4,'2018-03-18 13:56:00','2018-03-18 14:10:00','2018-03-18 14:11:00','2018-03-18 14:15:00','80048','usuario','0',NULL,NULL,NULL),(194,NULL,6,19,0,'C194',NULL,5,'2018-03-18 13:58:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(195,NULL,1,20,0,'A195',NULL,4,'2018-03-18 14:29:00','2018-03-18 14:32:00','2018-03-18 14:33:00','2018-03-18 14:37:00','80048','usuario','0',NULL,NULL,NULL),(196,NULL,3,19,0,'A196',NULL,5,'2018-03-18 14:09:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(197,NULL,2,20,0,'B197',NULL,4,'2018-03-18 14:45:00','2018-03-18 14:48:00','2018-03-18 14:49:00','2018-03-18 14:53:00','80048','usuario','0',NULL,NULL,NULL),(198,NULL,3,19,0,'A198',NULL,5,'2018-03-18 14:45:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(199,NULL,6,19,0,'B199',NULL,4,'2018-03-18 14:36:00','2018-03-18 14:45:00','2018-03-18 14:46:00','2018-03-18 14:53:00','80048','usuario','0',NULL,NULL,NULL),(200,NULL,2,20,0,'C200',NULL,4,'2018-03-18 14:31:00','2018-03-18 14:38:00','2018-03-18 14:39:00','2018-03-18 14:40:00','80048','usuario','0',NULL,NULL,NULL),(201,NULL,5,20,0,'B201',NULL,4,'2018-03-18 14:15:00','2018-03-18 14:18:00','2018-03-18 14:19:00','2018-03-18 14:24:00','80048','usuario','0',NULL,NULL,NULL),(202,NULL,3,20,0,'B202',NULL,4,'2018-03-18 14:35:00','2018-03-18 14:36:00','2018-03-18 14:37:00','2018-03-18 14:46:00','80048','usuario','0',NULL,NULL,NULL),(203,NULL,3,6,0,'A203',NULL,5,'2018-03-18 14:45:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(204,NULL,3,2,0,'B204',NULL,4,'2018-03-18 14:27:00','2018-03-18 14:28:00','2018-03-18 14:29:00','2018-03-18 14:36:00','80048','usuario','0',NULL,NULL,NULL),(205,NULL,4,20,0,'B205',NULL,5,'2018-03-18 14:05:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(206,NULL,6,4,0,'B206',NULL,5,'2018-03-18 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(207,NULL,5,2,0,'B207',NULL,4,'2018-03-18 14:20:00','2018-03-18 14:33:00','2018-03-18 14:34:00','2018-03-18 14:44:00','80048','usuario','0',NULL,NULL,NULL),(208,NULL,1,16,0,'B208',NULL,4,'2018-03-18 14:11:00','2018-03-18 14:21:00','2018-03-18 14:22:00','2018-03-18 14:25:00','80048','usuario','0',NULL,NULL,NULL),(209,NULL,3,2,0,'B209',NULL,5,'2018-03-18 14:43:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(210,NULL,2,16,0,'A210',NULL,4,'2018-03-18 14:15:00','2018-03-18 14:16:00','2018-03-18 14:17:00','2018-03-18 14:21:00','80048','usuario','0',NULL,NULL,NULL),(211,NULL,4,16,0,'C211',NULL,4,'2018-03-18 14:45:00','2018-03-18 14:59:00','2018-03-18 15:00:00','2018-03-18 15:05:00','80048','usuario','0',NULL,NULL,NULL),(212,NULL,5,16,0,'B212',NULL,5,'2018-03-18 13:57:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(213,NULL,4,20,0,'A213',NULL,5,'2018-03-18 14:15:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(214,NULL,4,6,0,'C214',NULL,4,'2018-03-18 14:11:00','2018-03-18 14:20:00','2018-03-18 14:21:00','2018-03-18 14:31:00','80048','usuario','0',NULL,NULL,NULL),(215,NULL,2,4,0,'A215',NULL,4,'2018-03-18 13:54:00','2018-03-18 13:58:00','2018-03-18 13:59:00','2018-03-18 14:05:00','80048','usuario','0',NULL,NULL,NULL),(216,NULL,5,16,0,'A216',NULL,4,'2018-03-18 14:03:00','2018-03-18 14:15:00','2018-03-18 14:16:00','2018-03-18 14:22:00','80048','usuario','0',NULL,NULL,NULL),(217,NULL,5,4,0,'C217',NULL,5,'2018-03-18 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(218,NULL,1,4,0,'C218',NULL,4,'2018-03-18 14:01:00','2018-03-18 14:13:00','2018-03-18 14:14:00','2018-03-18 14:19:00','80048','usuario','0',NULL,NULL,NULL),(219,NULL,6,6,0,'C219',NULL,5,'2018-03-18 14:47:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(220,NULL,1,20,0,'A220',NULL,4,'2018-03-18 14:17:00','2018-03-18 14:22:00','2018-03-18 14:23:00','2018-03-18 14:29:00','80048','usuario','0',NULL,NULL,NULL),(221,NULL,1,16,0,'A221',NULL,4,'2018-03-18 13:58:00','2018-03-18 14:12:00','2018-03-18 14:13:00','2018-03-18 14:15:00','80048','usuario','0',NULL,NULL,NULL),(222,NULL,4,4,0,'A222',NULL,4,'2018-03-18 13:55:00','2018-03-18 14:07:00','2018-03-18 14:08:00','2018-03-18 14:12:00','80048','usuario','0',NULL,NULL,NULL),(223,NULL,3,16,0,'B223',NULL,5,'2018-03-18 14:35:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(224,NULL,1,2,0,'C224',NULL,4,'2018-03-18 14:15:00','2018-03-18 14:27:00','2018-03-18 14:28:00','2018-03-18 14:34:00','80048','usuario','0',NULL,NULL,NULL),(225,NULL,5,4,0,'B225',NULL,4,'2018-03-18 14:25:00','2018-03-18 14:36:00','2018-03-18 14:37:00','2018-03-18 14:41:00','80048','usuario','0',NULL,NULL,NULL),(226,NULL,5,2,0,'C226',NULL,4,'2018-03-18 14:24:00','2018-03-18 14:31:00','2018-03-18 14:32:00','2018-03-18 14:33:00','80048','usuario','0',NULL,NULL,NULL),(227,NULL,1,6,0,'C227',NULL,4,'2018-03-18 14:06:00','2018-03-18 14:07:00','2018-03-18 14:08:00','2018-03-18 14:13:00','80048','usuario','0',NULL,NULL,NULL),(228,NULL,1,6,0,'A228',NULL,4,'2018-03-18 14:23:00','2018-03-18 14:27:00','2018-03-18 14:28:00','2018-03-18 14:30:00','80048','usuario','0',NULL,NULL,NULL),(229,NULL,1,4,0,'A229',NULL,5,'2018-03-18 14:14:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(230,NULL,6,4,0,'B230',NULL,4,'2018-03-18 14:41:00','2018-03-18 14:50:00','2018-03-18 14:51:00','2018-03-18 15:01:00','80048','usuario','0',NULL,NULL,NULL),(231,NULL,2,19,0,'B231',NULL,4,'2018-03-18 14:26:00','2018-03-18 14:39:00','2018-03-18 14:40:00','2018-03-18 14:51:00','80048','usuario','0',NULL,NULL,NULL),(232,NULL,5,19,0,'A232',NULL,5,'2018-03-18 14:25:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(233,NULL,3,20,0,'A233',NULL,4,'2018-03-18 14:19:00','2018-03-18 14:20:00','2018-03-18 14:21:00','2018-03-18 14:31:00','80048','usuario','0',NULL,NULL,NULL),(234,NULL,4,2,0,'B234',NULL,4,'2018-03-18 13:54:00','2018-03-18 13:55:00','2018-03-18 13:56:00','2018-03-18 14:06:00','80048','usuario','0',NULL,NULL,NULL),(235,NULL,6,19,0,'A235',NULL,4,'2018-03-18 14:32:00','2018-03-18 14:46:00','2018-03-18 14:47:00','2018-03-18 14:58:00','80048','usuario','0',NULL,NULL,NULL),(236,NULL,6,6,0,'C236',NULL,4,'2018-03-18 14:39:00','2018-03-18 14:47:00','2018-03-18 14:48:00','2018-03-18 14:55:00','80048','usuario','0',NULL,NULL,NULL),(237,NULL,5,19,0,'B237',NULL,5,'2018-03-18 14:23:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(238,NULL,4,4,0,'C238',NULL,4,'2018-03-18 14:05:00','2018-03-18 14:19:00','2018-03-18 14:20:00','2018-03-18 14:24:00','80048','usuario','0',NULL,NULL,NULL),(239,NULL,6,2,0,'B239',NULL,4,'2018-03-18 14:40:00','2018-03-18 14:48:00','2018-03-18 14:49:00','2018-03-18 14:54:00','80048','usuario','0',NULL,NULL,NULL),(240,NULL,5,6,0,'C240',NULL,4,'2018-03-18 14:06:00','2018-03-18 14:11:00','2018-03-18 14:12:00','2018-03-18 14:17:00','80048','usuario','0',NULL,NULL,NULL),(241,NULL,2,2,0,'C241',NULL,4,'2018-03-19 14:28:00','2018-03-19 14:38:00','2018-03-19 14:39:00','2018-03-19 14:43:00','80048','usuario','0',NULL,NULL,NULL),(242,NULL,6,16,0,'B242',NULL,4,'2018-03-19 14:21:00','2018-03-19 14:29:00','2018-03-19 14:30:00','2018-03-19 14:39:00','80048','usuario','0',NULL,NULL,NULL),(243,NULL,2,19,0,'C243',NULL,5,'2018-03-19 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(244,NULL,5,20,0,'A244',NULL,5,'2018-03-19 13:52:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(245,NULL,6,16,0,'A245',NULL,4,'2018-03-19 14:28:00','2018-03-19 14:31:00','2018-03-19 14:32:00','2018-03-19 14:43:00','80048','usuario','0',NULL,NULL,NULL),(246,NULL,6,2,0,'C246',NULL,4,'2018-03-19 14:02:00','2018-03-19 14:12:00','2018-03-19 14:13:00','2018-03-19 14:21:00','80048','usuario','0',NULL,NULL,NULL),(247,NULL,2,2,0,'B247',NULL,4,'2018-03-19 14:20:00','2018-03-19 14:34:00','2018-03-19 14:35:00','2018-03-19 14:43:00','80048','usuario','0',NULL,NULL,NULL),(248,NULL,1,16,0,'A248',NULL,4,'2018-03-19 13:59:00','2018-03-19 14:08:00','2018-03-19 14:09:00','2018-03-19 14:15:00','80048','usuario','0',NULL,NULL,NULL),(249,NULL,6,19,0,'A249',NULL,5,'2018-03-19 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(250,NULL,4,2,0,'A250',NULL,4,'2018-03-19 14:30:00','2018-03-19 14:33:00','2018-03-19 14:34:00','2018-03-19 14:38:00','80048','usuario','0',NULL,NULL,NULL),(251,NULL,2,4,0,'B251',NULL,4,'2018-03-19 14:38:00','2018-03-19 14:46:00','2018-03-19 14:47:00','2018-03-19 14:51:00','80048','usuario','0',NULL,NULL,NULL),(252,NULL,6,4,0,'A252',NULL,4,'2018-03-19 14:02:00','2018-03-19 14:04:00','2018-03-19 14:05:00','2018-03-19 14:16:00','80048','usuario','0',NULL,NULL,NULL),(253,NULL,2,4,0,'B253',NULL,4,'2018-03-19 14:03:00','2018-03-19 14:06:00','2018-03-19 14:07:00','2018-03-19 14:10:00','80048','usuario','0',NULL,NULL,NULL),(254,NULL,5,16,0,'C254',NULL,5,'2018-03-19 14:05:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(255,NULL,6,6,0,'C255',NULL,4,'2018-03-19 13:55:00','2018-03-19 13:59:00','2018-03-19 14:00:00','2018-03-19 14:05:00','80048','usuario','0',NULL,NULL,NULL),(256,NULL,6,20,0,'B256',NULL,5,'2018-03-19 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(257,NULL,3,19,0,'C257',NULL,5,'2018-03-19 14:10:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(258,NULL,1,2,0,'C258',NULL,5,'2018-03-19 14:26:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(259,NULL,1,20,0,'C259',NULL,4,'2018-03-19 14:28:00','2018-03-19 14:42:00','2018-03-19 14:43:00','2018-03-19 14:46:00','80048','usuario','0',NULL,NULL,NULL),(260,NULL,5,20,0,'C260',NULL,4,'2018-03-19 13:58:00','2018-03-19 14:06:00','2018-03-19 14:07:00','2018-03-19 14:09:00','80048','usuario','0',NULL,NULL,NULL),(261,NULL,6,16,0,'B261',NULL,4,'2018-03-19 14:05:00','2018-03-19 14:17:00','2018-03-19 14:18:00','2018-03-19 14:21:00','80048','usuario','0',NULL,NULL,NULL),(262,NULL,5,6,0,'B262',NULL,4,'2018-03-19 14:11:00','2018-03-19 14:20:00','2018-03-19 14:21:00','2018-03-19 14:24:00','80048','usuario','0',NULL,NULL,NULL),(263,NULL,2,19,0,'C263',NULL,4,'2018-03-19 14:15:00','2018-03-19 14:20:00','2018-03-19 14:21:00','2018-03-19 14:31:00','80048','usuario','0',NULL,NULL,NULL),(264,NULL,3,20,0,'A264',NULL,4,'2018-03-19 14:21:00','2018-03-19 14:33:00','2018-03-19 14:34:00','2018-03-19 14:44:00','80048','usuario','0',NULL,NULL,NULL),(265,NULL,4,2,0,'B265',NULL,4,'2018-03-19 14:17:00','2018-03-19 14:28:00','2018-03-19 14:29:00','2018-03-19 14:33:00','80048','usuario','0',NULL,NULL,NULL),(266,NULL,5,6,0,'C266',NULL,4,'2018-03-19 14:14:00','2018-03-19 14:26:00','2018-03-19 14:27:00','2018-03-19 14:31:00','80048','usuario','0',NULL,NULL,NULL),(267,NULL,6,19,0,'C267',NULL,4,'2018-03-19 14:05:00','2018-03-19 14:08:00','2018-03-19 14:09:00','2018-03-19 14:12:00','80048','usuario','0',NULL,NULL,NULL),(268,NULL,5,20,0,'B268',NULL,5,'2018-03-19 14:14:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(269,NULL,1,20,0,'A269',NULL,5,'2018-03-19 14:26:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(270,NULL,6,16,0,'C270',NULL,5,'2018-03-19 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(271,NULL,1,16,0,'C271',NULL,5,'2018-03-19 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(272,NULL,5,4,0,'C272',NULL,4,'2018-03-19 14:37:00','2018-03-19 14:43:00','2018-03-19 14:44:00','2018-03-19 14:50:00','80048','usuario','0',NULL,NULL,NULL),(273,NULL,6,20,0,'B273',NULL,5,'2018-03-19 13:55:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(274,NULL,3,16,0,'C274',NULL,4,'2018-03-19 13:59:00','2018-03-19 14:04:00','2018-03-19 14:05:00','2018-03-19 14:11:00','80048','usuario','0',NULL,NULL,NULL),(275,NULL,5,6,0,'A275',NULL,4,'2018-03-19 14:02:00','2018-03-19 14:16:00','2018-03-19 14:17:00','2018-03-19 14:22:00','80048','usuario','0',NULL,NULL,NULL),(276,NULL,2,6,0,'C276',NULL,5,'2018-03-19 13:59:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(277,NULL,4,16,0,'B277',NULL,4,'2018-03-19 14:04:00','2018-03-19 14:11:00','2018-03-19 14:12:00','2018-03-19 14:22:00','80048','usuario','0',NULL,NULL,NULL),(278,NULL,3,4,0,'A278',NULL,4,'2018-03-19 14:12:00','2018-03-19 14:14:00','2018-03-19 14:15:00','2018-03-19 14:16:00','80048','usuario','0',NULL,NULL,NULL),(279,NULL,6,4,0,'A279',NULL,4,'2018-03-19 14:37:00','2018-03-19 14:45:00','2018-03-19 14:46:00','2018-03-19 14:49:00','80048','usuario','0',NULL,NULL,NULL),(280,NULL,5,6,0,'A280',NULL,4,'2018-03-19 14:26:00','2018-03-19 14:38:00','2018-03-19 14:39:00','2018-03-19 14:42:00','80048','usuario','0',NULL,NULL,NULL),(281,NULL,1,2,0,'B281',NULL,4,'2018-03-19 13:54:00','2018-03-19 14:05:00','2018-03-19 14:06:00','2018-03-19 14:12:00','80048','usuario','0',NULL,NULL,NULL),(282,NULL,1,16,0,'A282',NULL,4,'2018-03-19 14:32:00','2018-03-19 14:33:00','2018-03-19 14:34:00','2018-03-19 14:39:00','80048','usuario','0',NULL,NULL,NULL),(283,NULL,4,6,0,'C283',NULL,4,'2018-03-19 14:45:00','2018-03-19 14:56:00','2018-03-19 14:57:00','2018-03-19 14:59:00','80048','usuario','0',NULL,NULL,NULL),(284,NULL,6,4,0,'B284',NULL,4,'2018-03-19 14:49:00','2018-03-19 14:53:00','2018-03-19 14:54:00','2018-03-19 15:01:00','80048','usuario','0',NULL,NULL,NULL),(285,NULL,2,2,0,'C285',NULL,4,'2018-03-19 14:21:00','2018-03-19 14:35:00','2018-03-19 14:36:00','2018-03-19 14:39:00','80048','usuario','0',NULL,NULL,NULL),(286,NULL,4,6,0,'B286',NULL,5,'2018-03-19 14:01:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(287,NULL,6,2,0,'C287',NULL,4,'2018-03-19 13:53:00','2018-03-19 13:58:00','2018-03-19 13:59:00','2018-03-19 14:10:00','80048','usuario','0',NULL,NULL,NULL),(288,NULL,6,19,0,'B288',NULL,5,'2018-03-19 14:10:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(289,NULL,2,4,0,'B289',NULL,5,'2018-03-19 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(290,NULL,6,4,0,'B290',NULL,4,'2018-03-19 13:56:00','2018-03-19 14:10:00','2018-03-19 14:11:00','2018-03-19 14:18:00','80048','usuario','0',NULL,NULL,NULL),(291,NULL,6,19,0,'A291',NULL,4,'2018-03-19 14:17:00','2018-03-19 14:24:00','2018-03-19 14:25:00','2018-03-19 14:31:00','80048','usuario','0',NULL,NULL,NULL),(292,NULL,4,6,0,'C292',NULL,5,'2018-03-19 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(293,NULL,2,2,0,'A293',NULL,4,'2018-03-19 14:33:00','2018-03-19 14:34:00','2018-03-19 14:35:00','2018-03-19 14:39:00','80048','usuario','0',NULL,NULL,NULL),(294,NULL,3,16,0,'A294',NULL,4,'2018-03-19 14:24:00','2018-03-19 14:31:00','2018-03-19 14:32:00','2018-03-19 14:40:00','80048','usuario','0',NULL,NULL,NULL),(295,NULL,1,2,0,'A295',NULL,4,'2018-03-19 14:03:00','2018-03-19 14:07:00','2018-03-19 14:08:00','2018-03-19 14:19:00','80048','usuario','0',NULL,NULL,NULL),(296,NULL,1,6,0,'B296',NULL,5,'2018-03-19 14:18:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(297,NULL,1,16,0,'C297',NULL,5,'2018-03-19 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(298,NULL,5,19,0,'C298',NULL,5,'2018-03-19 14:03:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(299,NULL,3,2,0,'A299',NULL,5,'2018-03-19 13:51:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(300,NULL,6,16,0,'B300',NULL,5,'2018-03-19 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(301,NULL,2,19,0,'C301',NULL,5,'2018-03-19 14:30:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(302,NULL,2,4,0,'C302',NULL,4,'2018-03-19 14:06:00','2018-03-19 14:08:00','2018-03-19 14:09:00','2018-03-19 14:15:00','80048','usuario','0',NULL,NULL,NULL),(303,NULL,1,16,0,'A303',NULL,5,'2018-03-19 14:44:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(304,NULL,6,2,0,'C304',NULL,4,'2018-03-19 14:38:00','2018-03-19 14:40:00','2018-03-19 14:41:00','2018-03-19 14:50:00','80048','usuario','0',NULL,NULL,NULL),(305,NULL,5,2,0,'C305',NULL,5,'2018-03-19 14:19:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(306,NULL,1,16,0,'A306',NULL,4,'2018-03-19 14:01:00','2018-03-19 14:04:00','2018-03-19 14:05:00','2018-03-19 14:12:00','80048','usuario','0',NULL,NULL,NULL),(307,NULL,6,6,0,'A307',NULL,4,'2018-03-19 14:00:00','2018-03-19 14:04:00','2018-03-19 14:05:00','2018-03-19 14:13:00','80048','usuario','0',NULL,NULL,NULL),(308,NULL,5,6,0,'B308',NULL,5,'2018-03-19 14:27:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(309,NULL,1,2,0,'A309',NULL,4,'2018-03-19 14:26:00','2018-03-19 14:28:00','2018-03-19 14:29:00','2018-03-19 14:32:00','80048','usuario','0',NULL,NULL,NULL),(310,NULL,4,6,0,'C310',NULL,4,'2018-03-19 14:48:00','2018-03-19 14:54:00','2018-03-19 14:55:00','2018-03-19 15:04:00','80048','usuario','0',NULL,NULL,NULL),(311,NULL,5,6,0,'C311',NULL,5,'2018-03-19 14:42:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(312,NULL,3,16,0,'C312',NULL,4,'2018-03-19 14:04:00','2018-03-19 14:08:00','2018-03-19 14:09:00','2018-03-19 14:14:00','80048','usuario','0',NULL,NULL,NULL),(313,NULL,5,2,0,'A313',NULL,4,'2018-03-19 14:43:00','2018-03-19 14:56:00','2018-03-19 14:57:00','2018-03-19 15:03:00','80048','usuario','0',NULL,NULL,NULL),(314,NULL,5,6,0,'C314',NULL,4,'2018-03-19 14:09:00','2018-03-19 14:17:00','2018-03-19 14:18:00','2018-03-19 14:22:00','80048','usuario','0',NULL,NULL,NULL),(315,NULL,2,6,0,'A315',NULL,4,'2018-03-19 14:38:00','2018-03-19 14:50:00','2018-03-19 14:51:00','2018-03-19 14:56:00','80048','usuario','0',NULL,NULL,NULL),(316,NULL,1,2,0,'C316',NULL,4,'2018-03-19 14:48:00','2018-03-19 14:52:00','2018-03-19 14:53:00','2018-03-19 14:54:00','80048','usuario','0',NULL,NULL,NULL),(317,NULL,4,6,0,'B317',NULL,4,'2018-03-19 14:08:00','2018-03-19 14:14:00','2018-03-19 14:15:00','2018-03-19 14:17:00','80048','usuario','0',NULL,NULL,NULL),(318,NULL,4,6,0,'C318',NULL,5,'2018-03-19 14:15:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(319,NULL,1,20,0,'C319',NULL,4,'2018-03-19 14:21:00','2018-03-19 14:30:00','2018-03-19 14:31:00','2018-03-19 14:39:00','80048','usuario','0',NULL,NULL,NULL),(320,NULL,2,6,0,'A320',NULL,5,'2018-03-19 14:44:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(321,NULL,3,16,0,'A321',NULL,5,'2018-03-20 14:44:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(322,NULL,6,6,0,'C322',NULL,4,'2018-03-20 14:35:00','2018-03-20 14:43:00','2018-03-20 14:44:00','2018-03-20 14:46:00','80048','usuario','0',NULL,NULL,NULL),(323,NULL,6,16,0,'A323',NULL,5,'2018-03-20 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(324,NULL,3,2,0,'C324',NULL,5,'2018-03-20 14:07:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(325,NULL,6,16,0,'C325',NULL,5,'2018-03-20 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(326,NULL,2,6,0,'B326',NULL,4,'2018-03-20 13:57:00','2018-03-20 14:05:00','2018-03-20 14:06:00','2018-03-20 14:09:00','80048','usuario','0',NULL,NULL,NULL),(327,NULL,6,4,0,'A327',NULL,4,'2018-03-20 13:51:00','2018-03-20 13:56:00','2018-03-20 13:57:00','2018-03-20 14:01:00','80048','usuario','0',NULL,NULL,NULL),(328,NULL,6,20,0,'C328',NULL,4,'2018-03-20 14:35:00','2018-03-20 14:45:00','2018-03-20 14:46:00','2018-03-20 14:50:00','80048','usuario','0',NULL,NULL,NULL),(329,NULL,2,19,0,'C329',NULL,4,'2018-03-20 14:37:00','2018-03-20 14:42:00','2018-03-20 14:43:00','2018-03-20 14:51:00','80048','usuario','0',NULL,NULL,NULL),(330,NULL,5,6,0,'B330',NULL,5,'2018-03-20 14:33:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(331,NULL,2,6,0,'C331',NULL,4,'2018-03-20 14:11:00','2018-03-20 14:18:00','2018-03-20 14:19:00','2018-03-20 14:29:00','80048','usuario','0',NULL,NULL,NULL),(332,NULL,5,2,0,'A332',NULL,4,'2018-03-20 13:56:00','2018-03-20 14:06:00','2018-03-20 14:07:00','2018-03-20 14:17:00','80048','usuario','0',NULL,NULL,NULL),(333,NULL,6,6,0,'B333',NULL,4,'2018-03-20 14:33:00','2018-03-20 14:42:00','2018-03-20 14:43:00','2018-03-20 14:46:00','80048','usuario','0',NULL,NULL,NULL),(334,NULL,6,2,0,'A334',NULL,5,'2018-03-20 14:02:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(335,NULL,4,20,0,'B335',NULL,4,'2018-03-20 14:05:00','2018-03-20 14:14:00','2018-03-20 14:15:00','2018-03-20 14:17:00','80048','usuario','0',NULL,NULL,NULL),(336,NULL,6,20,0,'C336',NULL,4,'2018-03-20 14:00:00','2018-03-20 14:10:00','2018-03-20 14:11:00','2018-03-20 14:17:00','80048','usuario','0',NULL,NULL,NULL),(337,NULL,5,16,0,'A337',NULL,4,'2018-03-20 13:55:00','2018-03-20 14:06:00','2018-03-20 14:07:00','2018-03-20 14:16:00','80048','usuario','0',NULL,NULL,NULL),(338,NULL,2,20,0,'A338',NULL,4,'2018-03-20 14:05:00','2018-03-20 14:16:00','2018-03-20 14:17:00','2018-03-20 14:21:00','80048','usuario','0',NULL,NULL,NULL),(339,NULL,6,4,0,'B339',NULL,5,'2018-03-20 14:32:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(340,NULL,6,19,0,'B340',NULL,4,'2018-03-20 14:12:00','2018-03-20 14:26:00','2018-03-20 14:27:00','2018-03-20 14:34:00','80048','usuario','0',NULL,NULL,NULL),(341,NULL,1,2,0,'A341',NULL,4,'2018-03-20 13:57:00','2018-03-20 14:02:00','2018-03-20 14:03:00','2018-03-20 14:06:00','80048','usuario','0',NULL,NULL,NULL),(342,NULL,6,4,0,'B342',NULL,4,'2018-03-20 14:03:00','2018-03-20 14:16:00','2018-03-20 14:17:00','2018-03-20 14:20:00','80048','usuario','0',NULL,NULL,NULL),(343,NULL,2,20,0,'B343',NULL,5,'2018-03-20 14:47:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(344,NULL,2,16,0,'B344',NULL,4,'2018-03-20 14:45:00','2018-03-20 14:54:00','2018-03-20 14:55:00','2018-03-20 14:56:00','80048','usuario','0',NULL,NULL,NULL),(345,NULL,4,19,0,'B345',NULL,5,'2018-03-20 14:16:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(346,NULL,2,4,0,'B346',NULL,4,'2018-03-20 14:08:00','2018-03-20 14:17:00','2018-03-20 14:18:00','2018-03-20 14:25:00','80048','usuario','0',NULL,NULL,NULL),(347,NULL,6,16,0,'C347',NULL,4,'2018-03-20 14:11:00','2018-03-20 14:23:00','2018-03-20 14:24:00','2018-03-20 14:29:00','80048','usuario','0',NULL,NULL,NULL),(348,NULL,4,4,0,'C348',NULL,5,'2018-03-20 14:26:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(349,NULL,4,19,0,'C349',NULL,5,'2018-03-20 13:58:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(350,NULL,3,19,0,'B350',NULL,4,'2018-03-20 14:35:00','2018-03-20 14:48:00','2018-03-20 14:49:00','2018-03-20 14:53:00','80048','usuario','0',NULL,NULL,NULL),(351,NULL,4,6,0,'B351',NULL,4,'2018-03-20 14:26:00','2018-03-20 14:39:00','2018-03-20 14:40:00','2018-03-20 14:46:00','80048','usuario','0',NULL,NULL,NULL),(352,NULL,5,19,0,'B352',NULL,4,'2018-03-20 14:37:00','2018-03-20 14:45:00','2018-03-20 14:46:00','2018-03-20 14:54:00','80048','usuario','0',NULL,NULL,NULL),(353,NULL,2,19,0,'A353',NULL,4,'2018-03-20 14:46:00','2018-03-20 14:50:00','2018-03-20 14:51:00','2018-03-20 14:53:00','80048','usuario','0',NULL,NULL,NULL),(354,NULL,6,20,0,'C354',NULL,4,'2018-03-20 14:05:00','2018-03-20 14:07:00','2018-03-20 14:08:00','2018-03-20 14:18:00','80048','usuario','0',NULL,NULL,NULL),(355,NULL,2,19,0,'A355',NULL,4,'2018-03-20 14:16:00','2018-03-20 14:26:00','2018-03-20 14:27:00','2018-03-20 14:37:00','80048','usuario','0',NULL,NULL,NULL),(356,NULL,2,4,0,'C356',NULL,4,'2018-03-20 14:12:00','2018-03-20 14:21:00','2018-03-20 14:22:00','2018-03-20 14:25:00','80048','usuario','0',NULL,NULL,NULL),(357,NULL,1,6,0,'C357',NULL,4,'2018-03-20 14:11:00','2018-03-20 14:24:00','2018-03-20 14:25:00','2018-03-20 14:32:00','80048','usuario','0',NULL,NULL,NULL),(358,NULL,1,19,0,'A358',NULL,4,'2018-03-20 13:54:00','2018-03-20 13:58:00','2018-03-20 13:59:00','2018-03-20 14:10:00','80048','usuario','0',NULL,NULL,NULL),(359,NULL,5,2,0,'B359',NULL,4,'2018-03-20 13:51:00','2018-03-20 14:05:00','2018-03-20 14:06:00','2018-03-20 14:15:00','80048','usuario','0',NULL,NULL,NULL),(360,NULL,1,20,0,'A360',NULL,4,'2018-03-20 14:06:00','2018-03-20 14:20:00','2018-03-20 14:21:00','2018-03-20 14:28:00','80048','usuario','0',NULL,NULL,NULL),(361,NULL,2,6,0,'C361',NULL,4,'2018-03-20 14:21:00','2018-03-20 14:28:00','2018-03-20 14:29:00','2018-03-20 14:36:00','80048','usuario','0',NULL,NULL,NULL),(362,NULL,1,20,0,'C362',NULL,4,'2018-03-20 14:28:00','2018-03-20 14:32:00','2018-03-20 14:33:00','2018-03-20 14:43:00','80048','usuario','0',NULL,NULL,NULL),(363,NULL,4,4,0,'B363',NULL,4,'2018-03-20 14:28:00','2018-03-20 14:29:00','2018-03-20 14:30:00','2018-03-20 14:33:00','80048','usuario','0',NULL,NULL,NULL),(364,NULL,2,19,0,'A364',NULL,4,'2018-03-20 14:08:00','2018-03-20 14:11:00','2018-03-20 14:12:00','2018-03-20 14:15:00','80048','usuario','0',NULL,NULL,NULL),(365,NULL,3,20,0,'C365',NULL,5,'2018-03-20 14:00:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(366,NULL,6,4,0,'A366',NULL,4,'2018-03-20 14:00:00','2018-03-20 14:14:00','2018-03-20 14:15:00','2018-03-20 14:22:00','80048','usuario','0',NULL,NULL,NULL),(367,NULL,1,6,0,'C367',NULL,4,'2018-03-20 14:00:00','2018-03-20 14:07:00','2018-03-20 14:08:00','2018-03-20 14:09:00','80048','usuario','0',NULL,NULL,NULL),(368,NULL,2,19,0,'C368',NULL,5,'2018-03-20 14:16:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(369,NULL,2,6,0,'C369',NULL,4,'2018-03-20 14:38:00','2018-03-20 14:43:00','2018-03-20 14:44:00','2018-03-20 14:53:00','80048','usuario','0',NULL,NULL,NULL),(370,NULL,6,2,0,'B370',NULL,4,'2018-03-20 14:42:00','2018-03-20 14:44:00','2018-03-20 14:45:00','2018-03-20 14:56:00','80048','usuario','0',NULL,NULL,NULL),(371,NULL,3,19,0,'C371',NULL,4,'2018-03-20 13:52:00','2018-03-20 14:00:00','2018-03-20 14:01:00','2018-03-20 14:06:00','80048','usuario','0',NULL,NULL,NULL),(372,NULL,3,4,0,'C372',NULL,5,'2018-03-20 13:52:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(373,NULL,6,20,0,'C373',NULL,4,'2018-03-20 14:40:00','2018-03-20 14:43:00','2018-03-20 14:44:00','2018-03-20 14:53:00','80048','usuario','0',NULL,NULL,NULL),(374,NULL,4,4,0,'B374',NULL,5,'2018-03-20 14:00:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(375,NULL,2,20,0,'C375',NULL,5,'2018-03-20 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(376,NULL,6,16,0,'A376',NULL,4,'2018-03-20 14:38:00','2018-03-20 14:46:00','2018-03-20 14:47:00','2018-03-20 14:48:00','80048','usuario','0',NULL,NULL,NULL),(377,NULL,1,16,0,'B377',NULL,4,'2018-03-20 14:39:00','2018-03-20 14:40:00','2018-03-20 14:41:00','2018-03-20 14:47:00','80048','usuario','0',NULL,NULL,NULL),(378,NULL,4,6,0,'C378',NULL,4,'2018-03-20 14:27:00','2018-03-20 14:33:00','2018-03-20 14:34:00','2018-03-20 14:41:00','80048','usuario','0',NULL,NULL,NULL),(379,NULL,4,16,0,'C379',NULL,4,'2018-03-20 14:23:00','2018-03-20 14:31:00','2018-03-20 14:32:00','2018-03-20 14:36:00','80048','usuario','0',NULL,NULL,NULL),(380,NULL,1,4,0,'A380',NULL,4,'2018-03-20 14:49:00','2018-03-20 14:56:00','2018-03-20 14:57:00','2018-03-20 15:04:00','80048','usuario','0',NULL,NULL,NULL),(381,NULL,5,4,0,'B381',NULL,4,'2018-03-20 14:40:00','2018-03-20 14:50:00','2018-03-20 14:51:00','2018-03-20 14:56:00','80048','usuario','0',NULL,NULL,NULL),(382,NULL,6,16,0,'C382',NULL,4,'2018-03-20 14:08:00','2018-03-20 14:18:00','2018-03-20 14:19:00','2018-03-20 14:21:00','80048','usuario','0',NULL,NULL,NULL),(383,NULL,6,19,0,'C383',NULL,4,'2018-03-20 14:48:00','2018-03-20 14:49:00','2018-03-20 14:50:00','2018-03-20 14:53:00','80048','usuario','0',NULL,NULL,NULL),(384,NULL,1,6,0,'B384',NULL,4,'2018-03-20 14:08:00','2018-03-20 14:22:00','2018-03-20 14:23:00','2018-03-20 14:27:00','80048','usuario','0',NULL,NULL,NULL),(385,NULL,1,19,0,'C385',NULL,4,'2018-03-20 13:56:00','2018-03-20 14:09:00','2018-03-20 14:10:00','2018-03-20 14:20:00','80048','usuario','0',NULL,NULL,NULL),(386,NULL,4,19,0,'B386',NULL,5,'2018-03-20 14:37:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(387,NULL,5,16,0,'C387',NULL,4,'2018-03-20 13:58:00','2018-03-20 14:03:00','2018-03-20 14:04:00','2018-03-20 14:13:00','80048','usuario','0',NULL,NULL,NULL),(388,NULL,5,16,0,'B388',NULL,4,'2018-03-20 14:06:00','2018-03-20 14:16:00','2018-03-20 14:17:00','2018-03-20 14:21:00','80048','usuario','0',NULL,NULL,NULL),(389,NULL,1,2,0,'B389',NULL,4,'2018-03-20 14:24:00','2018-03-20 14:35:00','2018-03-20 14:36:00','2018-03-20 14:46:00','80048','usuario','0',NULL,NULL,NULL),(390,NULL,2,20,0,'C390',NULL,5,'2018-03-20 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(391,NULL,3,19,0,'B391',NULL,5,'2018-03-20 14:29:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(392,NULL,5,2,0,'B392',NULL,4,'2018-03-20 14:47:00','2018-03-20 14:48:00','2018-03-20 14:49:00','2018-03-20 14:52:00','80048','usuario','0',NULL,NULL,NULL),(393,NULL,1,2,0,'B393',NULL,4,'2018-03-20 13:51:00','2018-03-20 13:59:00','2018-03-20 14:00:00','2018-03-20 14:05:00','80048','usuario','0',NULL,NULL,NULL),(394,NULL,4,20,0,'B394',NULL,4,'2018-03-20 14:06:00','2018-03-20 14:09:00','2018-03-20 14:10:00','2018-03-20 14:13:00','80048','usuario','0',NULL,NULL,NULL),(395,NULL,3,19,0,'B395',NULL,4,'2018-03-20 14:39:00','2018-03-20 14:49:00','2018-03-20 14:50:00','2018-03-20 15:01:00','80048','usuario','0',NULL,NULL,NULL),(396,NULL,3,20,0,'B396',NULL,4,'2018-03-20 14:17:00','2018-03-20 14:27:00','2018-03-20 14:28:00','2018-03-20 14:31:00','80048','usuario','0',NULL,NULL,NULL),(397,NULL,5,19,0,'B397',NULL,5,'2018-03-20 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(398,NULL,3,16,0,'C398',NULL,5,'2018-03-20 14:43:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(399,NULL,2,2,0,'C399',NULL,4,'2018-03-20 14:33:00','2018-03-20 14:37:00','2018-03-20 14:38:00','2018-03-20 14:48:00','80048','usuario','0',NULL,NULL,NULL),(400,NULL,1,4,0,'A400',NULL,4,'2018-03-20 13:54:00','2018-03-20 14:03:00','2018-03-20 14:04:00','2018-03-20 14:07:00','80048','usuario','0',NULL,NULL,NULL),(401,NULL,1,6,0,'A401',NULL,4,'2018-03-21 14:37:00','2018-03-21 14:39:00','2018-03-21 14:40:00','2018-03-21 14:46:00','80048','usuario','0',NULL,NULL,NULL),(402,NULL,1,16,0,'B402',NULL,4,'2018-03-21 14:24:00','2018-03-21 14:29:00','2018-03-21 14:30:00','2018-03-21 14:31:00','80048','usuario','0',NULL,NULL,NULL),(403,NULL,3,6,0,'A403',NULL,5,'2018-03-21 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(404,NULL,1,19,0,'C404',NULL,5,'2018-03-21 13:53:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(405,NULL,1,20,0,'B405',NULL,5,'2018-03-21 14:17:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(406,NULL,4,6,0,'B406',NULL,5,'2018-03-21 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(407,NULL,3,2,0,'C407',NULL,4,'2018-03-21 14:47:00','2018-03-21 14:59:00','2018-03-21 15:00:00','2018-03-21 15:06:00','80048','usuario','0',NULL,NULL,NULL),(408,NULL,2,19,0,'A408',NULL,4,'2018-03-21 14:41:00','2018-03-21 14:44:00','2018-03-21 14:45:00','2018-03-21 14:52:00','80048','usuario','0',NULL,NULL,NULL),(409,NULL,6,2,0,'B409',NULL,5,'2018-03-21 14:00:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(410,NULL,4,20,0,'C410',NULL,5,'2018-03-21 14:42:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(411,NULL,3,6,0,'C411',NULL,5,'2018-03-21 14:03:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(412,NULL,5,20,0,'A412',NULL,4,'2018-03-21 13:56:00','2018-03-21 14:09:00','2018-03-21 14:10:00','2018-03-21 14:13:00','80048','usuario','0',NULL,NULL,NULL),(413,NULL,1,6,0,'B413',NULL,5,'2018-03-21 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(414,NULL,2,20,0,'B414',NULL,4,'2018-03-21 14:07:00','2018-03-21 14:21:00','2018-03-21 14:22:00','2018-03-21 14:25:00','80048','usuario','0',NULL,NULL,NULL),(415,NULL,2,4,0,'B415',NULL,4,'2018-03-21 14:38:00','2018-03-21 14:41:00','2018-03-21 14:42:00','2018-03-21 14:44:00','80048','usuario','0',NULL,NULL,NULL),(416,NULL,2,16,0,'C416',NULL,5,'2018-03-21 14:47:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(417,NULL,5,19,0,'B417',NULL,4,'2018-03-21 14:38:00','2018-03-21 14:52:00','2018-03-21 14:53:00','2018-03-21 15:04:00','80048','usuario','0',NULL,NULL,NULL),(418,NULL,6,16,0,'B418',NULL,4,'2018-03-21 14:46:00','2018-03-21 14:47:00','2018-03-21 14:48:00','2018-03-21 14:52:00','80048','usuario','0',NULL,NULL,NULL),(419,NULL,2,20,0,'C419',NULL,4,'2018-03-21 14:00:00','2018-03-21 14:09:00','2018-03-21 14:10:00','2018-03-21 14:14:00','80048','usuario','0',NULL,NULL,NULL),(420,NULL,5,19,0,'A420',NULL,5,'2018-03-21 14:30:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(421,NULL,6,20,0,'C421',NULL,5,'2018-03-21 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(422,NULL,4,20,0,'A422',NULL,5,'2018-03-21 14:27:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(423,NULL,5,4,0,'B423',NULL,5,'2018-03-21 14:03:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(424,NULL,1,6,0,'B424',NULL,4,'2018-03-21 14:30:00','2018-03-21 14:40:00','2018-03-21 14:41:00','2018-03-21 14:50:00','80048','usuario','0',NULL,NULL,NULL),(425,NULL,6,4,0,'B425',NULL,5,'2018-03-21 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(426,NULL,2,19,0,'B426',NULL,4,'2018-03-21 14:39:00','2018-03-21 14:43:00','2018-03-21 14:44:00','2018-03-21 14:51:00','80048','usuario','0',NULL,NULL,NULL),(427,NULL,6,20,0,'B427',NULL,4,'2018-03-21 14:15:00','2018-03-21 14:24:00','2018-03-21 14:25:00','2018-03-21 14:26:00','80048','usuario','0',NULL,NULL,NULL),(428,NULL,6,4,0,'B428',NULL,4,'2018-03-21 13:51:00','2018-03-21 13:59:00','2018-03-21 14:00:00','2018-03-21 14:08:00','80048','usuario','0',NULL,NULL,NULL),(429,NULL,1,6,0,'A429',NULL,5,'2018-03-21 14:36:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(430,NULL,6,16,0,'A430',NULL,4,'2018-03-21 14:05:00','2018-03-21 14:14:00','2018-03-21 14:15:00','2018-03-21 14:25:00','80048','usuario','0',NULL,NULL,NULL),(431,NULL,4,4,0,'A431',NULL,5,'2018-03-21 14:31:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(432,NULL,6,19,0,'A432',NULL,4,'2018-03-21 14:15:00','2018-03-21 14:19:00','2018-03-21 14:20:00','2018-03-21 14:31:00','80048','usuario','0',NULL,NULL,NULL),(433,NULL,6,16,0,'B433',NULL,4,'2018-03-21 13:53:00','2018-03-21 13:55:00','2018-03-21 13:56:00','2018-03-21 13:59:00','80048','usuario','0',NULL,NULL,NULL),(434,NULL,4,19,0,'C434',NULL,4,'2018-03-21 14:05:00','2018-03-21 14:14:00','2018-03-21 14:15:00','2018-03-21 14:20:00','80048','usuario','0',NULL,NULL,NULL),(435,NULL,4,20,0,'B435',NULL,4,'2018-03-21 14:30:00','2018-03-21 14:32:00','2018-03-21 14:33:00','2018-03-21 14:44:00','80048','usuario','0',NULL,NULL,NULL),(436,NULL,1,2,0,'A436',NULL,4,'2018-03-21 14:13:00','2018-03-21 14:18:00','2018-03-21 14:19:00','2018-03-21 14:20:00','80048','usuario','0',NULL,NULL,NULL),(437,NULL,5,16,0,'A437',NULL,4,'2018-03-21 13:58:00','2018-03-21 14:07:00','2018-03-21 14:08:00','2018-03-21 14:14:00','80048','usuario','0',NULL,NULL,NULL),(438,NULL,3,6,0,'B438',NULL,4,'2018-03-21 14:02:00','2018-03-21 14:15:00','2018-03-21 14:16:00','2018-03-21 14:25:00','80048','usuario','0',NULL,NULL,NULL),(439,NULL,5,16,0,'C439',NULL,4,'2018-03-21 14:16:00','2018-03-21 14:19:00','2018-03-21 14:20:00','2018-03-21 14:28:00','80048','usuario','0',NULL,NULL,NULL),(440,NULL,4,19,0,'C440',NULL,4,'2018-03-21 14:27:00','2018-03-21 14:41:00','2018-03-21 14:42:00','2018-03-21 14:49:00','80048','usuario','0',NULL,NULL,NULL),(441,NULL,3,2,0,'B441',NULL,5,'2018-03-21 14:26:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(442,NULL,4,16,0,'A442',NULL,5,'2018-03-21 14:09:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(443,NULL,2,19,0,'C443',NULL,5,'2018-03-21 14:04:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(444,NULL,6,4,0,'B444',NULL,4,'2018-03-21 14:40:00','2018-03-21 14:49:00','2018-03-21 14:50:00','2018-03-21 14:53:00','80048','usuario','0',NULL,NULL,NULL),(445,NULL,1,6,0,'B445',NULL,5,'2018-03-21 13:57:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(446,NULL,2,19,0,'B446',NULL,5,'2018-03-21 13:57:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(447,NULL,2,16,0,'B447',NULL,5,'2018-03-21 13:56:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(448,NULL,4,6,0,'A448',NULL,5,'2018-03-21 14:18:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(449,NULL,2,6,0,'C449',NULL,4,'2018-03-21 14:41:00','2018-03-21 14:46:00','2018-03-21 14:47:00','2018-03-21 14:54:00','80048','usuario','0',NULL,NULL,NULL),(450,NULL,4,4,0,'C450',NULL,4,'2018-03-21 14:48:00','2018-03-21 14:57:00','2018-03-21 14:58:00','2018-03-21 15:02:00','80048','usuario','0',NULL,NULL,NULL),(451,NULL,2,16,0,'C451',NULL,4,'2018-03-21 14:03:00','2018-03-21 14:15:00','2018-03-21 14:16:00','2018-03-21 14:21:00','80048','usuario','0',NULL,NULL,NULL),(452,NULL,6,19,0,'A452',NULL,5,'2018-03-21 14:20:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(453,NULL,3,2,0,'C453',NULL,4,'2018-03-21 14:40:00','2018-03-21 14:49:00','2018-03-21 14:50:00','2018-03-21 14:52:00','80048','usuario','0',NULL,NULL,NULL),(454,NULL,5,2,0,'B454',NULL,4,'2018-03-21 13:59:00','2018-03-21 14:05:00','2018-03-21 14:06:00','2018-03-21 14:11:00','80048','usuario','0',NULL,NULL,NULL),(455,NULL,1,16,0,'B455',NULL,4,'2018-03-21 14:21:00','2018-03-21 14:29:00','2018-03-21 14:30:00','2018-03-21 14:31:00','80048','usuario','0',NULL,NULL,NULL),(456,NULL,3,2,0,'A456',NULL,5,'2018-03-21 14:46:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(457,NULL,5,20,0,'C457',NULL,4,'2018-03-21 14:03:00','2018-03-21 14:04:00','2018-03-21 14:05:00','2018-03-21 14:08:00','80048','usuario','0',NULL,NULL,NULL),(458,NULL,3,19,0,'B458',NULL,4,'2018-03-21 14:02:00','2018-03-21 14:04:00','2018-03-21 14:05:00','2018-03-21 14:09:00','80048','usuario','0',NULL,NULL,NULL),(459,NULL,4,20,0,'A459',NULL,5,'2018-03-21 13:51:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(460,NULL,5,2,0,'A460',NULL,4,'2018-03-21 14:32:00','2018-03-21 14:46:00','2018-03-21 14:47:00','2018-03-21 14:58:00','80048','usuario','0',NULL,NULL,NULL),(461,NULL,2,6,0,'C461',NULL,4,'2018-03-21 13:58:00','2018-03-21 14:12:00','2018-03-21 14:13:00','2018-03-21 14:16:00','80048','usuario','0',NULL,NULL,NULL),(462,NULL,6,16,0,'A462',NULL,4,'2018-03-21 14:13:00','2018-03-21 14:19:00','2018-03-21 14:20:00','2018-03-21 14:26:00','80048','usuario','0',NULL,NULL,NULL),(463,NULL,2,2,0,'B463',NULL,4,'2018-03-21 14:03:00','2018-03-21 14:16:00','2018-03-21 14:17:00','2018-03-21 14:27:00','80048','usuario','0',NULL,NULL,NULL),(464,NULL,3,16,0,'A464',NULL,4,'2018-03-21 14:20:00','2018-03-21 14:28:00','2018-03-21 14:29:00','2018-03-21 14:34:00','80048','usuario','0',NULL,NULL,NULL),(465,NULL,3,6,0,'B465',NULL,4,'2018-03-21 14:36:00','2018-03-21 14:47:00','2018-03-21 14:48:00','2018-03-21 14:49:00','80048','usuario','0',NULL,NULL,NULL),(466,NULL,5,6,0,'C466',NULL,4,'2018-03-21 14:11:00','2018-03-21 14:16:00','2018-03-21 14:17:00','2018-03-21 14:28:00','80048','usuario','0',NULL,NULL,NULL),(467,NULL,5,16,0,'A467',NULL,4,'2018-03-21 14:27:00','2018-03-21 14:28:00','2018-03-21 14:29:00','2018-03-21 14:39:00','80048','usuario','0',NULL,NULL,NULL),(468,NULL,4,20,0,'B468',NULL,4,'2018-03-21 13:59:00','2018-03-21 14:05:00','2018-03-21 14:06:00','2018-03-21 14:11:00','80048','usuario','0',NULL,NULL,NULL),(469,NULL,2,20,0,'B469',NULL,5,'2018-03-21 14:49:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(470,NULL,2,6,0,'B470',NULL,5,'2018-03-21 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(471,NULL,3,2,0,'C471',NULL,4,'2018-03-21 14:22:00','2018-03-21 14:35:00','2018-03-21 14:36:00','2018-03-21 14:43:00','80048','usuario','0',NULL,NULL,NULL),(472,NULL,6,2,0,'A472',NULL,4,'2018-03-21 14:46:00','2018-03-21 14:47:00','2018-03-21 14:48:00','2018-03-21 14:49:00','80048','usuario','0',NULL,NULL,NULL),(473,NULL,5,4,0,'B473',NULL,4,'2018-03-21 14:23:00','2018-03-21 14:37:00','2018-03-21 14:38:00','2018-03-21 14:42:00','80048','usuario','0',NULL,NULL,NULL),(474,NULL,6,16,0,'B474',NULL,4,'2018-03-21 13:57:00','2018-03-21 14:07:00','2018-03-21 14:08:00','2018-03-21 14:10:00','80048','usuario','0',NULL,NULL,NULL),(475,NULL,6,20,0,'B475',NULL,4,'2018-03-21 14:42:00','2018-03-21 14:49:00','2018-03-21 14:50:00','2018-03-21 14:56:00','80048','usuario','0',NULL,NULL,NULL),(476,NULL,2,20,0,'C476',NULL,5,'2018-03-21 14:12:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(477,NULL,2,2,0,'B477',NULL,4,'2018-03-21 14:01:00','2018-03-21 14:06:00','2018-03-21 14:07:00','2018-03-21 14:11:00','80048','usuario','0',NULL,NULL,NULL),(478,NULL,1,4,0,'A478',NULL,4,'2018-03-21 14:06:00','2018-03-21 14:20:00','2018-03-21 14:21:00','2018-03-21 14:28:00','80048','usuario','0',NULL,NULL,NULL),(479,NULL,4,6,0,'C479',NULL,4,'2018-03-21 13:52:00','2018-03-21 14:02:00','2018-03-21 14:03:00','2018-03-21 14:06:00','80048','usuario','0',NULL,NULL,NULL),(480,NULL,3,2,0,'B480',NULL,4,'2018-03-21 14:33:00','2018-03-21 14:43:00','2018-03-21 14:44:00','2018-03-21 14:48:00','80048','usuario','0',NULL,NULL,NULL),(481,NULL,4,16,0,'C481',NULL,5,'2018-03-22 13:54:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(482,NULL,2,6,0,'B482',NULL,4,'2018-03-22 13:54:00','2018-03-22 13:55:00','2018-03-22 13:56:00','2018-03-22 14:05:00','80048','usuario','0',NULL,NULL,NULL),(483,NULL,3,16,0,'C483',NULL,4,'2018-03-22 14:11:00','2018-03-22 14:14:00','2018-03-22 14:15:00','2018-03-22 14:25:00','80048','usuario','0',NULL,NULL,NULL),(484,NULL,6,19,0,'B484',NULL,4,'2018-03-22 14:32:00','2018-03-22 14:46:00','2018-03-22 14:47:00','2018-03-22 14:55:00','80048','usuario','0',NULL,NULL,NULL),(485,NULL,5,20,0,'B485',NULL,4,'2018-03-22 14:41:00','2018-03-22 14:46:00','2018-03-22 14:47:00','2018-03-22 14:48:00','80048','usuario','0',NULL,NULL,NULL),(486,NULL,5,16,0,'A486',NULL,4,'2018-03-22 14:39:00','2018-03-22 14:49:00','2018-03-22 14:50:00','2018-03-22 14:52:00','80048','usuario','0',NULL,NULL,NULL),(487,NULL,1,20,0,'B487',NULL,5,'2018-03-22 13:57:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(488,NULL,4,4,0,'C488',NULL,5,'2018-03-22 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(489,NULL,3,20,0,'B489',NULL,4,'2018-03-22 14:20:00','2018-03-22 14:34:00','2018-03-22 14:35:00','2018-03-22 14:45:00','80048','usuario','0',NULL,NULL,NULL),(490,NULL,4,4,0,'C490',NULL,4,'2018-03-22 14:24:00','2018-03-22 14:25:00','2018-03-22 14:26:00','2018-03-22 14:34:00','80048','usuario','0',NULL,NULL,NULL),(491,NULL,1,20,0,'C491',NULL,4,'2018-03-22 14:48:00','2018-03-22 14:56:00','2018-03-22 14:57:00','2018-03-22 15:06:00','80048','usuario','0',NULL,NULL,NULL),(492,NULL,2,16,0,'A492',NULL,4,'2018-03-22 14:15:00','2018-03-22 14:26:00','2018-03-22 14:27:00','2018-03-22 14:33:00','80048','usuario','0',NULL,NULL,NULL),(493,NULL,2,2,0,'B493',NULL,5,'2018-03-22 14:38:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(494,NULL,6,6,0,'B494',NULL,5,'2018-03-22 14:43:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(495,NULL,5,19,0,'A495',NULL,4,'2018-03-22 14:08:00','2018-03-22 14:11:00','2018-03-22 14:12:00','2018-03-22 14:14:00','80048','usuario','0',NULL,NULL,NULL),(496,NULL,1,20,0,'B496',NULL,4,'2018-03-22 14:31:00','2018-03-22 14:43:00','2018-03-22 14:44:00','2018-03-22 14:45:00','80048','usuario','0',NULL,NULL,NULL),(497,NULL,6,16,0,'A497',NULL,5,'2018-03-22 14:41:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(498,NULL,5,6,0,'C498',NULL,4,'2018-03-22 13:52:00','2018-03-22 14:03:00','2018-03-22 14:04:00','2018-03-22 14:12:00','80048','usuario','0',NULL,NULL,NULL),(499,NULL,4,4,0,'A499',NULL,4,'2018-03-22 14:41:00','2018-03-22 14:49:00','2018-03-22 14:50:00','2018-03-22 15:00:00','80048','usuario','0',NULL,NULL,NULL),(500,NULL,3,20,0,'A500',NULL,4,'2018-03-22 14:48:00','2018-03-22 14:49:00','2018-03-22 14:50:00','2018-03-22 14:51:00','80048','usuario','0',NULL,NULL,NULL),(501,NULL,4,19,0,'A501',NULL,4,'2018-03-22 14:43:00','2018-03-22 14:50:00','2018-03-22 14:51:00','2018-03-22 15:01:00','80048','usuario','0',NULL,NULL,NULL),(502,NULL,2,4,0,'C502',NULL,5,'2018-03-22 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(503,NULL,3,19,0,'B503',NULL,4,'2018-03-22 13:55:00','2018-03-22 14:01:00','2018-03-22 14:02:00','2018-03-22 14:12:00','80048','usuario','0',NULL,NULL,NULL),(504,NULL,6,19,0,'A504',NULL,5,'2018-03-22 14:06:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(505,NULL,3,4,0,'C505',NULL,4,'2018-03-22 14:47:00','2018-03-22 14:48:00','2018-03-22 14:49:00','2018-03-22 14:52:00','80048','usuario','0',NULL,NULL,NULL),(506,NULL,2,19,0,'A506',NULL,4,'2018-03-22 14:41:00','2018-03-22 14:46:00','2018-03-22 14:47:00','2018-03-22 14:48:00','80048','usuario','0',NULL,NULL,NULL),(507,NULL,5,4,0,'B507',NULL,5,'2018-03-22 14:32:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(508,NULL,5,20,0,'A508',NULL,4,'2018-03-22 13:59:00','2018-03-22 14:05:00','2018-03-22 14:06:00','2018-03-22 14:16:00','80048','usuario','0',NULL,NULL,NULL),(509,NULL,3,2,0,'C509',NULL,5,'2018-03-22 14:28:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(510,NULL,1,4,0,'B510',NULL,5,'2018-03-22 14:08:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(511,NULL,1,19,0,'C511',NULL,5,'2018-03-22 14:47:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(512,NULL,1,16,0,'A512',NULL,4,'2018-03-22 14:02:00','2018-03-22 14:13:00','2018-03-22 14:14:00','2018-03-22 14:21:00','80048','usuario','0',NULL,NULL,NULL),(513,NULL,4,6,0,'C513',NULL,4,'2018-03-22 14:14:00','2018-03-22 14:26:00','2018-03-22 14:27:00','2018-03-22 14:32:00','80048','usuario','0',NULL,NULL,NULL),(514,NULL,5,16,0,'C514',NULL,5,'2018-03-22 13:51:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(515,NULL,2,2,0,'A515',NULL,4,'2018-03-22 14:35:00','2018-03-22 14:41:00','2018-03-22 14:42:00','2018-03-22 14:46:00','80048','usuario','0',NULL,NULL,NULL),(516,NULL,4,2,0,'A516',NULL,4,'2018-03-22 14:35:00','2018-03-22 14:38:00','2018-03-22 14:39:00','2018-03-22 14:49:00','80048','usuario','0',NULL,NULL,NULL),(517,NULL,5,19,0,'A517',NULL,5,'2018-03-22 14:18:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(518,NULL,5,20,0,'B518',NULL,5,'2018-03-22 14:25:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(519,NULL,1,6,0,'C519',NULL,4,'2018-03-22 14:01:00','2018-03-22 14:06:00','2018-03-22 14:07:00','2018-03-22 14:12:00','80048','usuario','0',NULL,NULL,NULL),(520,NULL,1,4,0,'C520',NULL,5,'2018-03-22 14:11:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(521,NULL,2,16,0,'C521',NULL,4,'2018-03-22 13:55:00','2018-03-22 13:59:00','2018-03-22 14:00:00','2018-03-22 14:10:00','80048','usuario','0',NULL,NULL,NULL),(522,NULL,3,2,0,'B522',NULL,4,'2018-03-22 14:38:00','2018-03-22 14:41:00','2018-03-22 14:42:00','2018-03-22 14:43:00','80048','usuario','0',NULL,NULL,NULL),(523,NULL,2,4,0,'B523',NULL,4,'2018-03-22 14:39:00','2018-03-22 14:53:00','2018-03-22 14:54:00','2018-03-22 14:56:00','80048','usuario','0',NULL,NULL,NULL),(524,NULL,4,19,0,'A524',NULL,4,'2018-03-22 14:48:00','2018-03-22 15:02:00','2018-03-22 15:03:00','2018-03-22 15:13:00','80048','usuario','0',NULL,NULL,NULL),(525,NULL,5,20,0,'A525',NULL,4,'2018-03-22 14:36:00','2018-03-22 14:50:00','2018-03-22 14:51:00','2018-03-22 15:01:00','80048','usuario','0',NULL,NULL,NULL),(526,NULL,6,19,0,'C526',NULL,4,'2018-03-22 14:34:00','2018-03-22 14:43:00','2018-03-22 14:44:00','2018-03-22 14:53:00','80048','usuario','0',NULL,NULL,NULL),(527,NULL,4,20,0,'C527',NULL,4,'2018-03-22 14:30:00','2018-03-22 14:31:00','2018-03-22 14:32:00','2018-03-22 14:43:00','80048','usuario','0',NULL,NULL,NULL),(528,NULL,5,2,0,'C528',NULL,4,'2018-03-22 14:48:00','2018-03-22 14:57:00','2018-03-22 14:58:00','2018-03-22 15:06:00','80048','usuario','0',NULL,NULL,NULL),(529,NULL,2,6,0,'B529',NULL,4,'2018-03-22 14:10:00','2018-03-22 14:24:00','2018-03-22 14:25:00','2018-03-22 14:33:00','80048','usuario','0',NULL,NULL,NULL),(530,NULL,2,20,0,'A530',NULL,4,'2018-03-22 14:16:00','2018-03-22 14:19:00','2018-03-22 14:20:00','2018-03-22 14:30:00','80048','usuario','0',NULL,NULL,NULL),(531,NULL,1,16,0,'C531',NULL,4,'2018-03-22 13:51:00','2018-03-22 13:56:00','2018-03-22 13:57:00','2018-03-22 14:03:00','80048','usuario','0',NULL,NULL,NULL),(532,NULL,6,2,0,'C532',NULL,4,'2018-03-22 14:35:00','2018-03-22 14:43:00','2018-03-22 14:44:00','2018-03-22 14:49:00','80048','usuario','0',NULL,NULL,NULL),(533,NULL,3,19,0,'A533',NULL,4,'2018-03-22 14:13:00','2018-03-22 14:21:00','2018-03-22 14:22:00','2018-03-22 14:26:00','80048','usuario','0',NULL,NULL,NULL),(534,NULL,2,19,0,'A534',NULL,4,'2018-03-22 14:26:00','2018-03-22 14:27:00','2018-03-22 14:28:00','2018-03-22 14:37:00','80048','usuario','0',NULL,NULL,NULL),(535,NULL,1,19,0,'C535',NULL,4,'2018-03-22 14:48:00','2018-03-22 14:55:00','2018-03-22 14:56:00','2018-03-22 14:57:00','80048','usuario','0',NULL,NULL,NULL),(536,NULL,2,20,0,'B536',NULL,4,'2018-03-22 14:09:00','2018-03-22 14:17:00','2018-03-22 14:18:00','2018-03-22 14:26:00','80048','usuario','0',NULL,NULL,NULL),(537,NULL,5,2,0,'B537',NULL,4,'2018-03-22 14:10:00','2018-03-22 14:17:00','2018-03-22 14:18:00','2018-03-22 14:19:00','80048','usuario','0',NULL,NULL,NULL),(538,NULL,2,19,0,'C538',NULL,4,'2018-03-22 14:49:00','2018-03-22 14:59:00','2018-03-22 15:00:00','2018-03-22 15:10:00','80048','usuario','0',NULL,NULL,NULL),(539,NULL,5,6,0,'B539',NULL,4,'2018-03-22 14:16:00','2018-03-22 14:22:00','2018-03-22 14:23:00','2018-03-22 14:25:00','80048','usuario','0',NULL,NULL,NULL),(540,NULL,1,2,0,'C540',NULL,5,'2018-03-22 14:23:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(541,NULL,4,20,0,'B541',NULL,5,'2018-03-22 14:23:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(542,NULL,3,16,0,'A542',NULL,4,'2018-03-22 14:21:00','2018-03-22 14:22:00','2018-03-22 14:23:00','2018-03-22 14:33:00','80048','usuario','0',NULL,NULL,NULL),(543,NULL,2,4,0,'C543',NULL,5,'2018-03-22 14:21:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(544,NULL,6,19,0,'B544',NULL,4,'2018-03-22 13:56:00','2018-03-22 13:59:00','2018-03-22 14:00:00','2018-03-22 14:04:00','80048','usuario','0',NULL,NULL,NULL),(545,NULL,5,2,0,'C545',NULL,4,'2018-03-22 14:25:00','2018-03-22 14:31:00','2018-03-22 14:32:00','2018-03-22 14:38:00','80048','usuario','0',NULL,NULL,NULL),(546,NULL,6,19,0,'C546',NULL,4,'2018-03-22 14:26:00','2018-03-22 14:29:00','2018-03-22 14:30:00','2018-03-22 14:33:00','80048','usuario','0',NULL,NULL,NULL),(547,NULL,5,20,0,'B547',NULL,5,'2018-03-22 13:55:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(548,NULL,5,19,0,'A548',NULL,4,'2018-03-22 14:42:00','2018-03-22 14:49:00','2018-03-22 14:50:00','2018-03-22 15:01:00','80048','usuario','0',NULL,NULL,NULL),(549,NULL,5,19,0,'B549',NULL,5,'2018-03-22 14:30:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(550,NULL,2,19,0,'B550',NULL,4,'2018-03-22 14:46:00','2018-03-22 14:52:00','2018-03-22 14:53:00','2018-03-22 14:58:00','80048','usuario','0',NULL,NULL,NULL),(551,NULL,1,4,0,'B551',NULL,5,'2018-03-22 14:41:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(552,NULL,6,4,0,'B552',NULL,4,'2018-03-22 14:07:00','2018-03-22 14:18:00','2018-03-22 14:19:00','2018-03-22 14:25:00','80048','usuario','0',NULL,NULL,NULL),(553,NULL,1,4,0,'A553',NULL,4,'2018-03-22 14:46:00','2018-03-22 14:51:00','2018-03-22 14:52:00','2018-03-22 15:00:00','80048','usuario','0',NULL,NULL,NULL),(554,NULL,3,2,0,'C554',NULL,5,'2018-03-22 14:41:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(555,NULL,1,6,0,'C555',NULL,4,'2018-03-22 14:34:00','2018-03-22 14:47:00','2018-03-22 14:48:00','2018-03-22 14:50:00','80048','usuario','0',NULL,NULL,NULL),(556,NULL,2,6,0,'A556',NULL,5,'2018-03-22 14:23:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(557,NULL,2,19,0,'C557',NULL,4,'2018-03-22 13:51:00','2018-03-22 14:03:00','2018-03-22 14:04:00','2018-03-22 14:11:00','80048','usuario','0',NULL,NULL,NULL),(558,NULL,3,4,0,'B558',NULL,5,'2018-03-22 14:48:00',NULL,NULL,NULL,'80048','usuario','0',NULL,NULL,NULL),(559,NULL,5,2,0,'B559',NULL,4,'2018-03-22 13:55:00','2018-03-22 14:09:00','2018-03-22 14:10:00','2018-03-22 14:15:00','80048','usuario','0',NULL,NULL,NULL),(560,NULL,2,16,0,'A560',NULL,4,'2018-03-22 14:48:00','2018-03-22 14:51:00','2018-03-22 14:52:00','2018-03-22 15:02:00','80048','usuario','0',NULL,NULL,NULL);
/*!40000 ALTER TABLE `gc_tiquete_hist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_tramite`
--

DROP TABLE IF EXISTS `gc_tramite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_tramite` (
  `N_TRAMITE_ID` int NOT NULL AUTO_INCREMENT,
  `N_SERVICIOS_ID` int NOT NULL,
  `N_PESO` int NOT NULL,
  `B_NIT_REQUERIDO` smallint NOT NULL,
  `B_ESCALAMIENTO` smallint NOT NULL,
  `D_TRAMITE` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `S_NOMBRE` varchar(256) NOT NULL,
  `N_ORDEN` int DEFAULT NULL,
  `B_RESERVA_CITA` smallint DEFAULT NULL,
  `N_ICONO` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`N_TRAMITE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_tramite`
--

LOCK TABLES `gc_tramite` WRITE;
/*!40000 ALTER TABLE `gc_tramite` DISABLE KEYS */;
INSERT INTO `gc_tramite` VALUES (22,2,1,1,0,'Activación','ss.administrador','ss.administrador','2024-07-03 16:48:41',NULL,'2024-07-03 16:48:41',1,'Activación',3,0,0),(23,2,1,1,0,'Examen Diferido','ss.administrador','ss.administrador','2024-07-01 11:23:21',NULL,'2024-07-01 11:23:21',1,'Examen Diferido',3,0,0),(24,2,1,1,0,'Servicio Social','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Servicio Social',3,0,2),(25,2,1,1,0,'Prórroga de Calidad de Egresado','ss.administrador','ss.administrador','2024-05-17 09:38:04',NULL,'2024-05-17 09:38:04',1,'Prórroga de Egresado',3,0,0),(26,2,1,1,0,'Reingreso Graduado','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Reingreso Graduado',3,0,2),(27,2,1,1,0,'Reingreso profesorado','ss.administrador','ss.administrador','2024-07-03 16:48:30',NULL,'2024-07-03 16:48:30',1,'Reingreso profesorado',3,0,0),(28,2,1,1,0,'Cambio de Carrera','ss.administrador','ss.administrador','2024-05-27 23:46:11',NULL,'2024-05-27 23:46:11',1,'Cambio de Carrera',3,0,0),(29,2,1,1,0,'Traslado de Sede','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Traslado de Sede',3,0,2),(30,2,1,1,0,'Solicitud de equivalencia','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Solicitud de equivalencia',3,0,2),(31,2,1,1,0,'Cambio de modalidad','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Cambio de modalidad',3,0,2),(32,2,1,1,0,'Retiro de Unidades de Aprendizaje (Asignaturas)','ss.administrador','ss.administrador','2024-05-17 09:38:37',NULL,'2024-05-17 09:38:37',1,'Retiro de Asignaturas',3,0,0),(33,2,1,1,0,'Retiro Extraordinario de Unidades de Aprendizaje (Asignaturas)','ss.administrador','ss.administrador','2024-05-17 09:39:04',NULL,'2024-05-17 09:39:04',1,'Retiro Extraordinario de Asignaturas',3,0,0),(34,2,1,1,0,'Retiro Especial de Unidades de Aprendizaje (TODAS las Asignaturas)','ss.administrador','ss.administrador','2024-05-17 09:39:36',NULL,'2024-05-17 09:39:36',1,'Retiro de TODAS las Asignaturas',3,0,0),(35,2,1,1,0,'Retiro OFICIAL de ciclo','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Retiro OFICIAL de ciclo',3,0,2),(36,2,1,1,0,'Reposición de DUE','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Reposición de DUE',3,0,2),(37,2,1,1,0,'Equivalencias','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Equivalencias',3,0,2),(38,2,1,1,0,'Incorporación','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Incorporación',3,0,2),(39,3,1,1,0,'Certificación de notas','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Certificación de notas',3,0,2),(40,3,1,1,0,'Certificaciones de Planes','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Certificaciones de Planes',3,0,2),(41,3,1,1,0,'Certificaciones de Programas','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Certificaciones de Programas',3,0,2),(42,3,1,1,0,'Constancias estudiante activo','ss.administrador','ss.administrador','2024-05-17 09:40:05',NULL,'2024-05-17 09:40:05',1,'Constancias estudiante activo',3,0,0),(43,3,1,1,0,'Emisión de Carta de Egreso','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Emisión de Carta de Egreso',3,0,2),(44,3,1,1,0,'Constancia Única','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Constancia Única',3,0,2),(45,4,1,1,0,'Expediente de Graduación','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Expediente de Graduación',3,0,2),(46,4,1,1,0,'Firma de Título','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Firma de Título',3,0,2),(47,3,1,1,0,'Certificaciones de Titulo','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Certificaciones de Titulo',3,0,2),(48,4,1,1,0,'Constancias de graduación','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Constancias de graduación',3,0,2),(49,5,1,1,0,'Constancias Graduados','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Constancias Graduados',3,0,2),(50,5,1,1,0,'Reposición de Título','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Reposición de Título',3,0,2),(51,1,1,1,0,'Presentación de Documentos F2 (Confrontación de documentos para obtener la categoría de estudiante de la Universidad de El Salvador)','ss.administrador','ss.administrador','2024-07-03 16:47:44',NULL,'2024-07-03 16:47:44',1,'Presentación de Documentos F2',3,0,0),(52,6,1,1,0,'Corrección de datos','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Corrección de datos',3,0,2),(53,6,1,1,0,'Recuperación de credencial institucional','ss.administrador','ss.administrador','2024-05-17 09:41:29',NULL,'2024-05-17 09:41:29',1,'Reposición de carnet',3,0,0),(54,6,1,1,0,'Gestiones de Inscripción','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Gestiones de Inscripción',3,0,2),(55,6,1,1,0,'Corrección de notas','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Corrección de notas',3,0,2),(56,6,1,1,0,'Expediente en línea','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Expediente en línea',3,0,2),(57,6,1,1,0,'Campus virtual','ss.administrador','ss.administrador','2024-05-22 14:40:35',NULL,'2030-03-22 14:40:35',1,'Campus virtual',3,0,2),(58,7,1,1,0,'Información (Consultas sobre los procesos académicos)','ss.administrador','ss.administrador','2024-07-04 09:12:48',NULL,'2024-07-04 09:12:48',1,'Información procesos académicos',3,0,0);
/*!40000 ALTER TABLE `gc_tramite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_user_log`
--

DROP TABLE IF EXISTS `gc_user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_user_log` (
  `N_EVENTO_ID` int NOT NULL AUTO_INCREMENT,
  `N_USR_LOG_ID` int DEFAULT NULL,
  `FHI_EVENTO` datetime NOT NULL,
  `S_DETALLES` varchar(512) DEFAULT NULL,
  `C_USUARIO` varchar(256) NOT NULL,
  `FHF_EVENTO` datetime DEFAULT NULL,
  `C_UNIDAD_RECEP` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`N_EVENTO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_user_log`
--

LOCK TABLES `gc_user_log` WRITE;
/*!40000 ALTER TABLE `gc_user_log` DISABLE KEYS */;
INSERT INTO `gc_user_log` VALUES (1,2,'2016-05-19 00:00:00',NULL,'ever.argueta','2016-05-19 00:00:00','80048'),(2,1,'2016-05-19 00:00:00',NULL,'florentin.hdz','2016-05-19 00:00:00','80048');
/*!40000 ALTER TABLE `gc_user_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_user_role`
--

DROP TABLE IF EXISTS `gc_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_user_role` (
  `ROLE_ID` int NOT NULL AUTO_INCREMENT,
  `IDUSER` int NOT NULL,
  `ROLE_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_user_role`
--

LOCK TABLES `gc_user_role` WRITE;
/*!40000 ALTER TABLE `gc_user_role` DISABLE KEYS */;
INSERT INTO `gc_user_role` VALUES (2,2,'ROL_ADMIN'),(3,3,'ROL_ADMIN'),(4,4,'ROL_USER'),(5,3,'ROLE_GC_R1'),(6,3,'ROLE_GC_R2'),(7,3,'ROLE_GC_R3'),(8,2,'ROLE_GC_R2'),(9,4,'ROLE_GC_R1'),(10,5,'ROLE_GC_R3'),(11,2,'ROLE_GC_R4'),(12,2,'ROLE_GC_R5'),(13,2,'ROLE_GC_R6'),(14,4,'ROLE_GC_R6'),(15,4,'ROLE_GC_R4');
/*!40000 ALTER TABLE `gc_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_usr_tra`
--

DROP TABLE IF EXISTS `gc_usr_tra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_usr_tra` (
  `C_USUARIO` varchar(256) NOT NULL,
  `N_TRAMITE_ID` int NOT NULL,
  PRIMARY KEY (`C_USUARIO`,`N_TRAMITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_usr_tra`
--

LOCK TABLES `gc_usr_tra` WRITE;
/*!40000 ALTER TABLE `gc_usr_tra` DISABLE KEYS */;
INSERT INTO `gc_usr_tra` VALUES ('ag.operador1',22),('ag.operador1',23),('ag.operador1',24),('ag.operador1',25),('ag.operador1',26),('ag.operador1',27),('ag.operador1',28),('ag.operador1',29),('ag.operador1',30),('ag.operador1',31),('ag.operador1',32),('ag.operador1',33),('ag.operador1',34),('ag.operador1',35),('ag.operador1',36),('ag.operador1',37),('ag.operador1',38),('ag.operador1',39),('ag.operador1',40),('ag.operador1',41),('ag.operador1',42),('ag.operador1',43),('ag.operador1',44),('ag.operador1',45),('ag.operador1',46),('ag.operador1',47),('ag.operador1',48),('ag.operador1',49),('ag.operador1',50),('ag.operador1',51),('ag.operador1',52),('ag.operador1',53),('ag.operador1',54),('ag.operador1',55),('ag.operador1',56),('ag.operador1',57),('ag.operador2',22),('ag.operador2',23),('ag.operador2',24),('ag.operador2',25),('ag.operador2',26),('ag.operador2',27),('ag.operador2',28),('ag.operador2',29),('ag.operador2',30),('ag.operador2',31),('ag.operador2',32),('ag.operador2',33),('ag.operador2',34),('ag.operador2',35),('ag.operador2',36),('ag.operador2',37),('ag.operador2',38),('ag.operador2',39),('ag.operador2',40),('ag.operador2',41),('ag.operador2',42),('ag.operador2',43),('ag.operador2',44),('ag.operador2',45),('ag.operador2',46),('ag.operador2',47),('ag.operador2',48),('ag.operador2',49),('ag.operador2',50),('ag.operador2',51),('ag.operador2',52),('ag.operador2',53),('ag.operador2',54),('ag.operador2',55),('ag.operador2',56),('ag.operador2',57),('ag.operador3',22),('ag.operador3',23),('ag.operador3',24),('ag.operador3',25),('ag.operador3',26),('ag.operador3',27),('ag.operador3',28),('ag.operador3',29),('ag.operador3',30),('ag.operador3',31),('ag.operador3',32),('ag.operador3',33),('ag.operador3',34),('ag.operador3',35),('ag.operador3',36),('ag.operador3',37),('ag.operador3',38),('ag.operador3',39),('ag.operador3',40),('ag.operador3',41),('ag.operador3',42),('ag.operador3',43),('ag.operador3',44),('ag.operador3',45),('ag.operador3',46),('ag.operador3',47),('ag.operador3',48),('ag.operador3',49),('ag.operador3',50),('ag.operador3',51),('ag.operador3',52),('ag.operador3',53),('ag.operador3',54),('ag.operador3',55),('ag.operador3',56),('ag.operador3',57),('ag.operador4',22),('ag.operador4',23),('ag.operador4',24),('ag.operador4',25),('ag.operador4',26),('ag.operador4',27),('ag.operador4',28),('ag.operador4',29),('ag.operador4',30),('ag.operador4',31),('ag.operador4',32),('ag.operador4',33),('ag.operador4',34),('ag.operador4',35),('ag.operador4',36),('ag.operador4',37),('ag.operador4',38),('ag.operador4',39),('ag.operador4',40),('ag.operador4',41),('ag.operador4',42),('ag.operador4',43),('ag.operador4',44),('ag.operador4',45),('ag.operador4',46),('ag.operador4',47),('ag.operador4',48),('ag.operador4',49),('ag.operador4',50),('ag.operador4',51),('ag.operador4',52),('ag.operador4',53),('ag.operador4',54),('ag.operador4',55),('ag.operador4',56),('ag.operador4',57),('ce.operador1',22),('ce.operador1',23),('ce.operador1',24),('ce.operador1',25),('ce.operador1',26),('ce.operador1',27),('ce.operador1',28),('ce.operador1',29),('ce.operador1',30),('ce.operador1',31),('ce.operador1',32),('ce.operador1',33),('ce.operador1',34),('ce.operador1',35),('ce.operador1',36),('ce.operador1',37),('ce.operador1',38),('ce.operador1',39),('ce.operador1',40),('ce.operador1',41),('ce.operador1',42),('ce.operador1',43),('ce.operador1',44),('ce.operador1',45),('ce.operador1',46),('ce.operador1',47),('ce.operador1',48),('ce.operador1',49),('ce.operador1',50),('ce.operador1',51),('ce.operador1',52),('ce.operador1',53),('ce.operador1',54),('ce.operador1',55),('ce.operador1',56),('ce.operador1',57),('ce.operador2',22),('ce.operador2',23),('ce.operador2',24),('ce.operador2',25),('ce.operador2',26),('ce.operador2',27),('ce.operador2',28),('ce.operador2',29),('ce.operador2',30),('ce.operador2',31),('ce.operador2',32),('ce.operador2',33),('ce.operador2',34),('ce.operador2',35),('ce.operador2',36),('ce.operador2',37),('ce.operador2',38),('ce.operador2',39),('ce.operador2',40),('ce.operador2',41),('ce.operador2',42),('ce.operador2',43),('ce.operador2',44),('ce.operador2',45),('ce.operador2',46),('ce.operador2',47),('ce.operador2',48),('ce.operador2',49),('ce.operador2',50),('ce.operador2',51),('ce.operador2',52),('ce.operador2',53),('ce.operador2',54),('ce.operador2',55),('ce.operador2',56),('ce.operador2',57),('ce.operador3',22),('ce.operador3',23),('ce.operador3',24),('ce.operador3',25),('ce.operador3',26),('ce.operador3',27),('ce.operador3',28),('ce.operador3',29),('ce.operador3',30),('ce.operador3',31),('ce.operador3',32),('ce.operador3',33),('ce.operador3',34),('ce.operador3',35),('ce.operador3',36),('ce.operador3',37),('ce.operador3',38),('ce.operador3',39),('ce.operador3',40),('ce.operador3',41),('ce.operador3',42),('ce.operador3',43),('ce.operador3',44),('ce.operador3',45),('ce.operador3',46),('ce.operador3',47),('ce.operador3',48),('ce.operador3',49),('ce.operador3',50),('ce.operador3',51),('ce.operador3',52),('ce.operador3',53),('ce.operador3',54),('ce.operador3',55),('ce.operador3',56),('ce.operador3',57),('ce.operador4',22),('ce.operador4',23),('ce.operador4',24),('ce.operador4',25),('ce.operador4',26),('ce.operador4',27),('ce.operador4',28),('ce.operador4',29),('ce.operador4',30),('ce.operador4',31),('ce.operador4',32),('ce.operador4',33),('ce.operador4',34),('ce.operador4',35),('ce.operador4',36),('ce.operador4',37),('ce.operador4',38),('ce.operador4',39),('ce.operador4',40),('ce.operador4',41),('ce.operador4',42),('ce.operador4',43),('ce.operador4',44),('ce.operador4',45),('ce.operador4',46),('ce.operador4',47),('ce.operador4',48),('ce.operador4',49),('ce.operador4',50),('ce.operador4',51),('ce.operador4',52),('ce.operador4',53),('ce.operador4',54),('ce.operador4',55),('ce.operador4',56),('ce.operador4',57),('ch.operador1',22),('ch.operador1',23),('ch.operador1',24),('ch.operador1',25),('ch.operador1',26),('ch.operador1',27),('ch.operador1',28),('ch.operador1',29),('ch.operador1',30),('ch.operador1',31),('ch.operador1',32),('ch.operador1',33),('ch.operador1',34),('ch.operador1',35),('ch.operador1',36),('ch.operador1',37),('ch.operador1',38),('ch.operador1',39),('ch.operador1',40),('ch.operador1',41),('ch.operador1',42),('ch.operador1',43),('ch.operador1',44),('ch.operador1',45),('ch.operador1',46),('ch.operador1',47),('ch.operador1',48),('ch.operador1',49),('ch.operador1',50),('ch.operador1',51),('ch.operador1',52),('ch.operador1',53),('ch.operador1',54),('ch.operador1',55),('ch.operador1',56),('ch.operador1',57),('ch.operador2',22),('ch.operador2',23),('ch.operador2',24),('ch.operador2',25),('ch.operador2',26),('ch.operador2',27),('ch.operador2',28),('ch.operador2',29),('ch.operador2',30),('ch.operador2',31),('ch.operador2',32),('ch.operador2',33),('ch.operador2',34),('ch.operador2',35),('ch.operador2',36),('ch.operador2',37),('ch.operador2',38),('ch.operador2',39),('ch.operador2',40),('ch.operador2',41),('ch.operador2',42),('ch.operador2',43),('ch.operador2',44),('ch.operador2',45),('ch.operador2',46),('ch.operador2',47),('ch.operador2',48),('ch.operador2',49),('ch.operador2',50),('ch.operador2',51),('ch.operador2',52),('ch.operador2',53),('ch.operador2',54),('ch.operador2',55),('ch.operador2',56),('ch.operador2',57),('ch.operador3',22),('ch.operador3',23),('ch.operador3',24),('ch.operador3',25),('ch.operador3',26),('ch.operador3',27),('ch.operador3',28),('ch.operador3',29),('ch.operador3',30),('ch.operador3',31),('ch.operador3',32),('ch.operador3',33),('ch.operador3',34),('ch.operador3',35),('ch.operador3',36),('ch.operador3',37),('ch.operador3',38),('ch.operador3',39),('ch.operador3',40),('ch.operador3',41),('ch.operador3',42),('ch.operador3',43),('ch.operador3',44),('ch.operador3',45),('ch.operador3',46),('ch.operador3',47),('ch.operador3',48),('ch.operador3',49),('ch.operador3',50),('ch.operador3',51),('ch.operador3',52),('ch.operador3',53),('ch.operador3',54),('ch.operador3',55),('ch.operador3',56),('ch.operador3',57),('ch.operador4',22),('ch.operador4',23),('ch.operador4',24),('ch.operador4',25),('ch.operador4',26),('ch.operador4',27),('ch.operador4',28),('ch.operador4',29),('ch.operador4',30),('ch.operador4',31),('ch.operador4',32),('ch.operador4',33),('ch.operador4',34),('ch.operador4',35),('ch.operador4',36),('ch.operador4',37),('ch.operador4',38),('ch.operador4',39),('ch.operador4',40),('ch.operador4',41),('ch.operador4',42),('ch.operador4',43),('ch.operador4',44),('ch.operador4',45),('ch.operador4',46),('ch.operador4',47),('ch.operador4',48),('ch.operador4',49),('ch.operador4',50),('ch.operador4',51),('ch.operador4',52),('ch.operador4',53),('ch.operador4',54),('ch.operador4',55),('ch.operador4',56),('ch.operador4',57),('cm.operador1',22),('cm.operador1',23),('cm.operador1',24),('cm.operador1',25),('cm.operador1',26),('cm.operador1',27),('cm.operador1',28),('cm.operador1',29),('cm.operador1',30),('cm.operador1',31),('cm.operador1',32),('cm.operador1',33),('cm.operador1',34),('cm.operador1',35),('cm.operador1',36),('cm.operador1',37),('cm.operador1',38),('cm.operador1',39),('cm.operador1',40),('cm.operador1',41),('cm.operador1',42),('cm.operador1',43),('cm.operador1',44),('cm.operador1',45),('cm.operador1',46),('cm.operador1',47),('cm.operador1',48),('cm.operador1',49),('cm.operador1',50),('cm.operador1',51),('cm.operador1',52),('cm.operador1',53),('cm.operador1',54),('cm.operador1',55),('cm.operador1',56),('cm.operador1',57),('cm.operador2',22),('cm.operador2',23),('cm.operador2',24),('cm.operador2',25),('cm.operador2',26),('cm.operador2',27),('cm.operador2',28),('cm.operador2',29),('cm.operador2',30),('cm.operador2',31),('cm.operador2',32),('cm.operador2',33),('cm.operador2',34),('cm.operador2',35),('cm.operador2',36),('cm.operador2',37),('cm.operador2',38),('cm.operador2',39),('cm.operador2',40),('cm.operador2',41),('cm.operador2',42),('cm.operador2',43),('cm.operador2',44),('cm.operador2',45),('cm.operador2',46),('cm.operador2',47),('cm.operador2',48),('cm.operador2',49),('cm.operador2',50),('cm.operador2',51),('cm.operador2',52),('cm.operador2',53),('cm.operador2',54),('cm.operador2',55),('cm.operador2',56),('cm.operador2',57),('cm.operador3',22),('cm.operador3',23),('cm.operador3',24),('cm.operador3',25),('cm.operador3',26),('cm.operador3',27),('cm.operador3',28),('cm.operador3',29),('cm.operador3',30),('cm.operador3',31),('cm.operador3',32),('cm.operador3',33),('cm.operador3',34),('cm.operador3',35),('cm.operador3',36),('cm.operador3',37),('cm.operador3',38),('cm.operador3',39),('cm.operador3',40),('cm.operador3',41),('cm.operador3',42),('cm.operador3',43),('cm.operador3',44),('cm.operador3',45),('cm.operador3',46),('cm.operador3',47),('cm.operador3',48),('cm.operador3',49),('cm.operador3',50),('cm.operador3',51),('cm.operador3',52),('cm.operador3',53),('cm.operador3',54),('cm.operador3',55),('cm.operador3',56),('cm.operador3',57),('cm.operador4',22),('cm.operador4',23),('cm.operador4',24),('cm.operador4',25),('cm.operador4',26),('cm.operador4',27),('cm.operador4',28),('cm.operador4',29),('cm.operador4',30),('cm.operador4',31),('cm.operador4',32),('cm.operador4',33),('cm.operador4',34),('cm.operador4',35),('cm.operador4',36),('cm.operador4',37),('cm.operador4',38),('cm.operador4',39),('cm.operador4',40),('cm.operador4',41),('cm.operador4',42),('cm.operador4',43),('cm.operador4',44),('cm.operador4',45),('cm.operador4',46),('cm.operador4',47),('cm.operador4',48),('cm.operador4',49),('cm.operador4',50),('cm.operador4',51),('cm.operador4',52),('cm.operador4',53),('cm.operador4',54),('cm.operador4',55),('cm.operador4',56),('cm.operador4',57),('ia.operador1',22),('ia.operador1',23),('ia.operador1',24),('ia.operador1',25),('ia.operador1',26),('ia.operador1',27),('ia.operador1',28),('ia.operador1',29),('ia.operador1',30),('ia.operador1',31),('ia.operador1',32),('ia.operador1',33),('ia.operador1',34),('ia.operador1',35),('ia.operador1',36),('ia.operador1',37),('ia.operador1',38),('ia.operador1',39),('ia.operador1',40),('ia.operador1',41),('ia.operador1',42),('ia.operador1',43),('ia.operador1',44),('ia.operador1',45),('ia.operador1',46),('ia.operador1',47),('ia.operador1',48),('ia.operador1',49),('ia.operador1',50),('ia.operador1',51),('ia.operador1',52),('ia.operador1',53),('ia.operador1',54),('ia.operador1',55),('ia.operador1',56),('ia.operador1',57),('ia.operador2',22),('ia.operador2',23),('ia.operador2',24),('ia.operador2',25),('ia.operador2',26),('ia.operador2',27),('ia.operador2',28),('ia.operador2',29),('ia.operador2',30),('ia.operador2',31),('ia.operador2',32),('ia.operador2',33),('ia.operador2',34),('ia.operador2',35),('ia.operador2',36),('ia.operador2',37),('ia.operador2',38),('ia.operador2',39),('ia.operador2',40),('ia.operador2',41),('ia.operador2',42),('ia.operador2',43),('ia.operador2',44),('ia.operador2',45),('ia.operador2',46),('ia.operador2',47),('ia.operador2',48),('ia.operador2',49),('ia.operador2',50),('ia.operador2',51),('ia.operador2',52),('ia.operador2',53),('ia.operador2',54),('ia.operador2',55),('ia.operador2',56),('ia.operador2',57),('ia.operador3',22),('ia.operador3',23),('ia.operador3',24),('ia.operador3',25),('ia.operador3',26),('ia.operador3',27),('ia.operador3',28),('ia.operador3',29),('ia.operador3',30),('ia.operador3',31),('ia.operador3',32),('ia.operador3',33),('ia.operador3',34),('ia.operador3',35),('ia.operador3',36),('ia.operador3',37),('ia.operador3',38),('ia.operador3',39),('ia.operador3',40),('ia.operador3',41),('ia.operador3',42),('ia.operador3',43),('ia.operador3',44),('ia.operador3',45),('ia.operador3',46),('ia.operador3',47),('ia.operador3',48),('ia.operador3',49),('ia.operador3',50),('ia.operador3',51),('ia.operador3',52),('ia.operador3',53),('ia.operador3',54),('ia.operador3',55),('ia.operador3',56),('ia.operador3',57),('ia.operador4',22),('ia.operador4',23),('ia.operador4',24),('ia.operador4',25),('ia.operador4',26),('ia.operador4',27),('ia.operador4',28),('ia.operador4',29),('ia.operador4',30),('ia.operador4',31),('ia.operador4',32),('ia.operador4',33),('ia.operador4',34),('ia.operador4',35),('ia.operador4',36),('ia.operador4',37),('ia.operador4',38),('ia.operador4',39),('ia.operador4',40),('ia.operador4',41),('ia.operador4',42),('ia.operador4',43),('ia.operador4',44),('ia.operador4',45),('ia.operador4',46),('ia.operador4',47),('ia.operador4',48),('ia.operador4',49),('ia.operador4',50),('ia.operador4',51),('ia.operador4',52),('ia.operador4',53),('ia.operador4',54),('ia.operador4',55),('ia.operador4',56),('ia.operador4',57),('jc.operador1',22),('jc.operador1',23),('jc.operador1',24),('jc.operador1',25),('jc.operador1',26),('jc.operador1',27),('jc.operador1',28),('jc.operador1',29),('jc.operador1',30),('jc.operador1',31),('jc.operador1',32),('jc.operador1',33),('jc.operador1',34),('jc.operador1',35),('jc.operador1',36),('jc.operador1',37),('jc.operador1',38),('jc.operador1',39),('jc.operador1',40),('jc.operador1',41),('jc.operador1',42),('jc.operador1',43),('jc.operador1',44),('jc.operador1',45),('jc.operador1',46),('jc.operador1',47),('jc.operador1',48),('jc.operador1',49),('jc.operador1',50),('jc.operador1',51),('jc.operador1',52),('jc.operador1',53),('jc.operador1',54),('jc.operador1',55),('jc.operador1',56),('jc.operador1',57),('jc.operador2',22),('jc.operador2',23),('jc.operador2',24),('jc.operador2',25),('jc.operador2',26),('jc.operador2',27),('jc.operador2',28),('jc.operador2',29),('jc.operador2',30),('jc.operador2',31),('jc.operador2',32),('jc.operador2',33),('jc.operador2',34),('jc.operador2',35),('jc.operador2',36),('jc.operador2',37),('jc.operador2',38),('jc.operador2',39),('jc.operador2',40),('jc.operador2',41),('jc.operador2',42),('jc.operador2',43),('jc.operador2',44),('jc.operador2',45),('jc.operador2',46),('jc.operador2',47),('jc.operador2',48),('jc.operador2',49),('jc.operador2',50),('jc.operador2',51),('jc.operador2',52),('jc.operador2',53),('jc.operador2',54),('jc.operador2',55),('jc.operador2',56),('jc.operador2',57),('jc.operador3',22),('jc.operador3',23),('jc.operador3',24),('jc.operador3',25),('jc.operador3',26),('jc.operador3',27),('jc.operador3',28),('jc.operador3',29),('jc.operador3',30),('jc.operador3',31),('jc.operador3',32),('jc.operador3',33),('jc.operador3',34),('jc.operador3',35),('jc.operador3',36),('jc.operador3',37),('jc.operador3',38),('jc.operador3',39),('jc.operador3',40),('jc.operador3',41),('jc.operador3',42),('jc.operador3',43),('jc.operador3',44),('jc.operador3',45),('jc.operador3',46),('jc.operador3',47),('jc.operador3',48),('jc.operador3',49),('jc.operador3',50),('jc.operador3',51),('jc.operador3',52),('jc.operador3',53),('jc.operador3',54),('jc.operador3',55),('jc.operador3',56),('jc.operador3',57),('jc.operador4',22),('jc.operador4',23),('jc.operador4',24),('jc.operador4',25),('jc.operador4',26),('jc.operador4',27),('jc.operador4',28),('jc.operador4',29),('jc.operador4',30),('jc.operador4',31),('jc.operador4',32),('jc.operador4',33),('jc.operador4',34),('jc.operador4',35),('jc.operador4',36),('jc.operador4',37),('jc.operador4',38),('jc.operador4',39),('jc.operador4',40),('jc.operador4',41),('jc.operador4',42),('jc.operador4',43),('jc.operador4',44),('jc.operador4',45),('jc.operador4',46),('jc.operador4',47),('jc.operador4',48),('jc.operador4',49),('jc.operador4',50),('jc.operador4',51),('jc.operador4',52),('jc.operador4',53),('jc.operador4',54),('jc.operador4',55),('jc.operador4',56),('jc.operador4',57),('md.operador1',22),('md.operador1',23),('md.operador1',24),('md.operador1',25),('md.operador1',26),('md.operador1',27),('md.operador1',28),('md.operador1',29),('md.operador1',30),('md.operador1',31),('md.operador1',32),('md.operador1',33),('md.operador1',34),('md.operador1',35),('md.operador1',36),('md.operador1',37),('md.operador1',38),('md.operador1',39),('md.operador1',40),('md.operador1',41),('md.operador1',42),('md.operador1',43),('md.operador1',44),('md.operador1',45),('md.operador1',46),('md.operador1',47),('md.operador1',48),('md.operador1',49),('md.operador1',50),('md.operador1',51),('md.operador1',52),('md.operador1',53),('md.operador1',54),('md.operador1',55),('md.operador1',56),('md.operador1',57),('md.operador2',22),('md.operador2',23),('md.operador2',24),('md.operador2',25),('md.operador2',26),('md.operador2',27),('md.operador2',28),('md.operador2',29),('md.operador2',30),('md.operador2',31),('md.operador2',32),('md.operador2',33),('md.operador2',34),('md.operador2',35),('md.operador2',36),('md.operador2',37),('md.operador2',38),('md.operador2',39),('md.operador2',40),('md.operador2',41),('md.operador2',42),('md.operador2',43),('md.operador2',44),('md.operador2',45),('md.operador2',46),('md.operador2',47),('md.operador2',48),('md.operador2',49),('md.operador2',50),('md.operador2',51),('md.operador2',52),('md.operador2',53),('md.operador2',54),('md.operador2',55),('md.operador2',56),('md.operador2',57),('md.operador3',22),('md.operador3',23),('md.operador3',24),('md.operador3',25),('md.operador3',26),('md.operador3',27),('md.operador3',28),('md.operador3',29),('md.operador3',30),('md.operador3',31),('md.operador3',32),('md.operador3',33),('md.operador3',34),('md.operador3',35),('md.operador3',36),('md.operador3',37),('md.operador3',38),('md.operador3',39),('md.operador3',40),('md.operador3',41),('md.operador3',42),('md.operador3',43),('md.operador3',44),('md.operador3',45),('md.operador3',46),('md.operador3',47),('md.operador3',48),('md.operador3',49),('md.operador3',50),('md.operador3',51),('md.operador3',52),('md.operador3',53),('md.operador3',54),('md.operador3',55),('md.operador3',56),('md.operador3',57),('md.operador4',22),('md.operador4',23),('md.operador4',24),('md.operador4',25),('md.operador4',26),('md.operador4',27),('md.operador4',28),('md.operador4',29),('md.operador4',30),('md.operador4',31),('md.operador4',32),('md.operador4',33),('md.operador4',34),('md.operador4',35),('md.operador4',36),('md.operador4',37),('md.operador4',38),('md.operador4',39),('md.operador4',40),('md.operador4',41),('md.operador4',42),('md.operador4',43),('md.operador4',44),('md.operador4',45),('md.operador4',46),('md.operador4',47),('md.operador4',48),('md.operador4',49),('md.operador4',50),('md.operador4',51),('md.operador4',52),('md.operador4',53),('md.operador4',54),('md.operador4',55),('md.operador4',56),('md.operador4',57),('mo.operador1',22),('mo.operador1',23),('mo.operador1',24),('mo.operador1',25),('mo.operador1',26),('mo.operador1',27),('mo.operador1',28),('mo.operador1',29),('mo.operador1',30),('mo.operador1',31),('mo.operador1',32),('mo.operador1',33),('mo.operador1',34),('mo.operador1',35),('mo.operador1',36),('mo.operador1',37),('mo.operador1',38),('mo.operador1',39),('mo.operador1',40),('mo.operador1',41),('mo.operador1',42),('mo.operador1',43),('mo.operador1',44),('mo.operador1',45),('mo.operador1',46),('mo.operador1',47),('mo.operador1',48),('mo.operador1',49),('mo.operador1',50),('mo.operador1',51),('mo.operador1',52),('mo.operador1',53),('mo.operador1',54),('mo.operador1',55),('mo.operador1',56),('mo.operador1',57),('mo.operador2',22),('mo.operador2',23),('mo.operador2',24),('mo.operador2',25),('mo.operador2',26),('mo.operador2',27),('mo.operador2',28),('mo.operador2',29),('mo.operador2',30),('mo.operador2',31),('mo.operador2',32),('mo.operador2',33),('mo.operador2',34),('mo.operador2',35),('mo.operador2',36),('mo.operador2',37),('mo.operador2',38),('mo.operador2',39),('mo.operador2',40),('mo.operador2',41),('mo.operador2',42),('mo.operador2',43),('mo.operador2',44),('mo.operador2',45),('mo.operador2',46),('mo.operador2',47),('mo.operador2',48),('mo.operador2',49),('mo.operador2',50),('mo.operador2',51),('mo.operador2',52),('mo.operador2',53),('mo.operador2',54),('mo.operador2',55),('mo.operador2',56),('mo.operador2',57),('mo.operador3',22),('mo.operador3',23),('mo.operador3',24),('mo.operador3',25),('mo.operador3',26),('mo.operador3',27),('mo.operador3',28),('mo.operador3',29),('mo.operador3',30),('mo.operador3',31),('mo.operador3',32),('mo.operador3',33),('mo.operador3',34),('mo.operador3',35),('mo.operador3',36),('mo.operador3',37),('mo.operador3',38),('mo.operador3',39),('mo.operador3',40),('mo.operador3',41),('mo.operador3',42),('mo.operador3',43),('mo.operador3',44),('mo.operador3',45),('mo.operador3',46),('mo.operador3',47),('mo.operador3',48),('mo.operador3',49),('mo.operador3',50),('mo.operador3',51),('mo.operador3',52),('mo.operador3',53),('mo.operador3',54),('mo.operador3',55),('mo.operador3',56),('mo.operador3',57),('mo.operador4',22),('mo.operador4',23),('mo.operador4',24),('mo.operador4',25),('mo.operador4',26),('mo.operador4',27),('mo.operador4',28),('mo.operador4',29),('mo.operador4',30),('mo.operador4',31),('mo.operador4',32),('mo.operador4',33),('mo.operador4',34),('mo.operador4',35),('mo.operador4',36),('mo.operador4',37),('mo.operador4',38),('mo.operador4',39),('mo.operador4',40),('mo.operador4',41),('mo.operador4',42),('mo.operador4',43),('mo.operador4',44),('mo.operador4',45),('mo.operador4',46),('mo.operador4',47),('mo.operador4',48),('mo.operador4',49),('mo.operador4',50),('mo.operador4',51),('mo.operador4',52),('mo.operador4',53),('mo.operador4',54),('mo.operador4',55),('mo.operador4',56),('mo.operador4',57),('moc.operador1',22),('moc.operador1',23),('moc.operador1',24),('moc.operador1',25),('moc.operador1',26),('moc.operador1',27),('moc.operador1',28),('moc.operador1',29),('moc.operador1',30),('moc.operador1',31),('moc.operador1',32),('moc.operador1',33),('moc.operador1',34),('moc.operador1',35),('moc.operador1',36),('moc.operador1',37),('moc.operador1',38),('moc.operador1',39),('moc.operador1',40),('moc.operador1',41),('moc.operador1',42),('moc.operador1',43),('moc.operador1',44),('moc.operador1',45),('moc.operador1',46),('moc.operador1',47),('moc.operador1',48),('moc.operador1',49),('moc.operador1',50),('moc.operador1',51),('moc.operador1',52),('moc.operador1',53),('moc.operador1',54),('moc.operador1',55),('moc.operador1',56),('moc.operador1',57),('moc.operador2',22),('moc.operador2',23),('moc.operador2',24),('moc.operador2',25),('moc.operador2',26),('moc.operador2',27),('moc.operador2',28),('moc.operador2',29),('moc.operador2',30),('moc.operador2',31),('moc.operador2',32),('moc.operador2',33),('moc.operador2',34),('moc.operador2',35),('moc.operador2',36),('moc.operador2',37),('moc.operador2',38),('moc.operador2',39),('moc.operador2',40),('moc.operador2',41),('moc.operador2',42),('moc.operador2',43),('moc.operador2',44),('moc.operador2',45),('moc.operador2',46),('moc.operador2',47),('moc.operador2',48),('moc.operador2',49),('moc.operador2',50),('moc.operador2',51),('moc.operador2',52),('moc.operador2',53),('moc.operador2',54),('moc.operador2',55),('moc.operador2',56),('moc.operador2',57),('moc.operador3',22),('moc.operador3',23),('moc.operador3',24),('moc.operador3',25),('moc.operador3',26),('moc.operador3',27),('moc.operador3',28),('moc.operador3',29),('moc.operador3',30),('moc.operador3',31),('moc.operador3',32),('moc.operador3',33),('moc.operador3',34),('moc.operador3',35),('moc.operador3',36),('moc.operador3',37),('moc.operador3',38),('moc.operador3',39),('moc.operador3',40),('moc.operador3',41),('moc.operador3',42),('moc.operador3',43),('moc.operador3',44),('moc.operador3',45),('moc.operador3',46),('moc.operador3',47),('moc.operador3',48),('moc.operador3',49),('moc.operador3',50),('moc.operador3',51),('moc.operador3',52),('moc.operador3',53),('moc.operador3',54),('moc.operador3',55),('moc.operador3',56),('moc.operador3',57),('moc.operador4',22),('moc.operador4',23),('moc.operador4',24),('moc.operador4',25),('moc.operador4',26),('moc.operador4',27),('moc.operador4',28),('moc.operador4',29),('moc.operador4',30),('moc.operador4',31),('moc.operador4',32),('moc.operador4',33),('moc.operador4',34),('moc.operador4',35),('moc.operador4',36),('moc.operador4',37),('moc.operador4',38),('moc.operador4',39),('moc.operador4',40),('moc.operador4',41),('moc.operador4',42),('moc.operador4',43),('moc.operador4',44),('moc.operador4',45),('moc.operador4',46),('moc.operador4',47),('moc.operador4',48),('moc.operador4',49),('moc.operador4',50),('moc.operador4',51),('moc.operador4',52),('moc.operador4',53),('moc.operador4',54),('moc.operador4',55),('moc.operador4',56),('moc.operador4',57),('mp.operador1',22),('mp.operador1',23),('mp.operador1',24),('mp.operador1',25),('mp.operador1',26),('mp.operador1',27),('mp.operador1',28),('mp.operador1',29),('mp.operador1',30),('mp.operador1',31),('mp.operador1',32),('mp.operador1',33),('mp.operador1',34),('mp.operador1',35),('mp.operador1',36),('mp.operador1',37),('mp.operador1',38),('mp.operador1',39),('mp.operador1',40),('mp.operador1',41),('mp.operador1',42),('mp.operador1',43),('mp.operador1',44),('mp.operador1',45),('mp.operador1',46),('mp.operador1',47),('mp.operador1',48),('mp.operador1',49),('mp.operador1',50),('mp.operador1',51),('mp.operador1',52),('mp.operador1',53),('mp.operador1',54),('mp.operador1',55),('mp.operador1',56),('mp.operador1',57),('mp.operador2',22),('mp.operador2',23),('mp.operador2',24),('mp.operador2',25),('mp.operador2',26),('mp.operador2',27),('mp.operador2',28),('mp.operador2',29),('mp.operador2',30),('mp.operador2',31),('mp.operador2',32),('mp.operador2',33),('mp.operador2',34),('mp.operador2',35),('mp.operador2',36),('mp.operador2',37),('mp.operador2',38),('mp.operador2',39),('mp.operador2',40),('mp.operador2',41),('mp.operador2',42),('mp.operador2',43),('mp.operador2',44),('mp.operador2',45),('mp.operador2',46),('mp.operador2',47),('mp.operador2',48),('mp.operador2',49),('mp.operador2',50),('mp.operador2',51),('mp.operador2',52),('mp.operador2',53),('mp.operador2',54),('mp.operador2',55),('mp.operador2',56),('mp.operador2',57),('mp.operador3',22),('mp.operador3',23),('mp.operador3',24),('mp.operador3',25),('mp.operador3',26),('mp.operador3',27),('mp.operador3',28),('mp.operador3',29),('mp.operador3',30),('mp.operador3',31),('mp.operador3',32),('mp.operador3',33),('mp.operador3',34),('mp.operador3',35),('mp.operador3',36),('mp.operador3',37),('mp.operador3',38),('mp.operador3',39),('mp.operador3',40),('mp.operador3',41),('mp.operador3',42),('mp.operador3',43),('mp.operador3',44),('mp.operador3',45),('mp.operador3',46),('mp.operador3',47),('mp.operador3',48),('mp.operador3',49),('mp.operador3',50),('mp.operador3',51),('mp.operador3',52),('mp.operador3',53),('mp.operador3',54),('mp.operador3',55),('mp.operador3',56),('mp.operador3',57),('mp.operador4',22),('mp.operador4',23),('mp.operador4',24),('mp.operador4',25),('mp.operador4',26),('mp.operador4',27),('mp.operador4',28),('mp.operador4',29),('mp.operador4',30),('mp.operador4',31),('mp.operador4',32),('mp.operador4',33),('mp.operador4',34),('mp.operador4',35),('mp.operador4',36),('mp.operador4',37),('mp.operador4',38),('mp.operador4',39),('mp.operador4',40),('mp.operador4',41),('mp.operador4',42),('mp.operador4',43),('mp.operador4',44),('mp.operador4',45),('mp.operador4',46),('mp.operador4',47),('mp.operador4',48),('mp.operador4',49),('mp.operador4',50),('mp.operador4',51),('mp.operador4',52),('mp.operador4',53),('mp.operador4',54),('mp.operador4',55),('mp.operador4',56),('mp.operador4',57),('od.operador1',22),('od.operador1',23),('od.operador1',24),('od.operador1',25),('od.operador1',26),('od.operador1',27),('od.operador1',28),('od.operador1',29),('od.operador1',30),('od.operador1',31),('od.operador1',32),('od.operador1',33),('od.operador1',34),('od.operador1',35),('od.operador1',36),('od.operador1',37),('od.operador1',38),('od.operador1',39),('od.operador1',40),('od.operador1',41),('od.operador1',42),('od.operador1',43),('od.operador1',44),('od.operador1',45),('od.operador1',46),('od.operador1',47),('od.operador1',48),('od.operador1',49),('od.operador1',50),('od.operador1',51),('od.operador1',52),('od.operador1',53),('od.operador1',54),('od.operador1',55),('od.operador1',56),('od.operador1',57),('od.operador2',22),('od.operador2',23),('od.operador2',24),('od.operador2',25),('od.operador2',26),('od.operador2',27),('od.operador2',28),('od.operador2',29),('od.operador2',30),('od.operador2',31),('od.operador2',32),('od.operador2',33),('od.operador2',34),('od.operador2',35),('od.operador2',36),('od.operador2',37),('od.operador2',38),('od.operador2',39),('od.operador2',40),('od.operador2',41),('od.operador2',42),('od.operador2',43),('od.operador2',44),('od.operador2',45),('od.operador2',46),('od.operador2',47),('od.operador2',48),('od.operador2',49),('od.operador2',50),('od.operador2',51),('od.operador2',52),('od.operador2',53),('od.operador2',54),('od.operador2',55),('od.operador2',56),('od.operador2',57),('od.operador3',22),('od.operador3',23),('od.operador3',24),('od.operador3',25),('od.operador3',26),('od.operador3',27),('od.operador3',28),('od.operador3',29),('od.operador3',30),('od.operador3',31),('od.operador3',32),('od.operador3',33),('od.operador3',34),('od.operador3',35),('od.operador3',36),('od.operador3',37),('od.operador3',38),('od.operador3',39),('od.operador3',40),('od.operador3',41),('od.operador3',42),('od.operador3',43),('od.operador3',44),('od.operador3',45),('od.operador3',46),('od.operador3',47),('od.operador3',48),('od.operador3',49),('od.operador3',50),('od.operador3',51),('od.operador3',52),('od.operador3',53),('od.operador3',54),('od.operador3',55),('od.operador3',56),('od.operador3',57),('od.operador4',22),('od.operador4',23),('od.operador4',24),('od.operador4',25),('od.operador4',26),('od.operador4',27),('od.operador4',28),('od.operador4',29),('od.operador4',30),('od.operador4',31),('od.operador4',32),('od.operador4',33),('od.operador4',34),('od.operador4',35),('od.operador4',36),('od.operador4',37),('od.operador4',38),('od.operador4',39),('od.operador4',40),('od.operador4',41),('od.operador4',42),('od.operador4',43),('od.operador4',44),('od.operador4',45),('od.operador4',46),('od.operador4',47),('od.operador4',48),('od.operador4',49),('od.operador4',50),('od.operador4',51),('od.operador4',52),('od.operador4',53),('od.operador4',54),('od.operador4',55),('od.operador4',56),('od.operador4',57),('qf.operador1',22),('qf.operador1',23),('qf.operador1',24),('qf.operador1',25),('qf.operador1',26),('qf.operador1',27),('qf.operador1',28),('qf.operador1',29),('qf.operador1',30),('qf.operador1',31),('qf.operador1',32),('qf.operador1',33),('qf.operador1',34),('qf.operador1',35),('qf.operador1',36),('qf.operador1',37),('qf.operador1',38),('qf.operador1',39),('qf.operador1',40),('qf.operador1',41),('qf.operador1',42),('qf.operador1',43),('qf.operador1',44),('qf.operador1',45),('qf.operador1',46),('qf.operador1',47),('qf.operador1',48),('qf.operador1',49),('qf.operador1',50),('qf.operador1',51),('qf.operador1',52),('qf.operador1',53),('qf.operador1',54),('qf.operador1',55),('qf.operador1',56),('qf.operador1',57),('qf.operador2',22),('qf.operador2',23),('qf.operador2',24),('qf.operador2',25),('qf.operador2',26),('qf.operador2',27),('qf.operador2',28),('qf.operador2',29),('qf.operador2',30),('qf.operador2',31),('qf.operador2',32),('qf.operador2',33),('qf.operador2',34),('qf.operador2',35),('qf.operador2',36),('qf.operador2',37),('qf.operador2',38),('qf.operador2',39),('qf.operador2',40),('qf.operador2',41),('qf.operador2',42),('qf.operador2',43),('qf.operador2',44),('qf.operador2',45),('qf.operador2',46),('qf.operador2',47),('qf.operador2',48),('qf.operador2',49),('qf.operador2',50),('qf.operador2',51),('qf.operador2',52),('qf.operador2',53),('qf.operador2',54),('qf.operador2',55),('qf.operador2',56),('qf.operador2',57),('qf.operador3',22),('qf.operador3',23),('qf.operador3',24),('qf.operador3',25),('qf.operador3',26),('qf.operador3',27),('qf.operador3',28),('qf.operador3',29),('qf.operador3',30),('qf.operador3',31),('qf.operador3',32),('qf.operador3',33),('qf.operador3',34),('qf.operador3',35),('qf.operador3',36),('qf.operador3',37),('qf.operador3',38),('qf.operador3',39),('qf.operador3',40),('qf.operador3',41),('qf.operador3',42),('qf.operador3',43),('qf.operador3',44),('qf.operador3',45),('qf.operador3',46),('qf.operador3',47),('qf.operador3',48),('qf.operador3',49),('qf.operador3',50),('qf.operador3',51),('qf.operador3',52),('qf.operador3',53),('qf.operador3',54),('qf.operador3',55),('qf.operador3',56),('qf.operador3',57),('qf.operador4',22),('qf.operador4',23),('qf.operador4',24),('qf.operador4',25),('qf.operador4',26),('qf.operador4',27),('qf.operador4',28),('qf.operador4',29),('qf.operador4',30),('qf.operador4',31),('qf.operador4',32),('qf.operador4',33),('qf.operador4',34),('qf.operador4',35),('qf.operador4',36),('qf.operador4',37),('qf.operador4',38),('qf.operador4',39),('qf.operador4',40),('qf.operador4',41),('qf.operador4',42),('qf.operador4',43),('qf.operador4',44),('qf.operador4',45),('qf.operador4',46),('qf.operador4',47),('qf.operador4',48),('qf.operador4',49),('qf.operador4',50),('qf.operador4',51),('qf.operador4',52),('qf.operador4',53),('qf.operador4',54),('qf.operador4',55),('qf.operador4',56),('qf.operador4',57),('testing',22);
/*!40000 ALTER TABLE `gc_usr_tra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_usuario`
--

DROP TABLE IF EXISTS `gc_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_usuario` (
  `N_ESCRITORIO_ID` int NOT NULL,
  `C_USUARIO` varchar(256) NOT NULL,
  `URL_FOTO` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`C_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_usuario`
--

LOCK TABLES `gc_usuario` WRITE;
/*!40000 ALTER TABLE `gc_usuario` DISABLE KEYS */;
INSERT INTO `gc_usuario` VALUES (31,'ag.operador1',NULL),(32,'ag.operador2',NULL),(33,'ag.operador3',NULL),(34,'ag.operador4',NULL),(15,'ce.operador1',NULL),(16,'ce.operador2',NULL),(17,'ce.operador3',NULL),(18,'ce.operador4',NULL),(23,'ch.operador1',NULL),(24,'ch.operador2',NULL),(25,'ch.operador3',NULL),(26,'ch.operador4',NULL),(55,'cm.operador1',NULL),(56,'cm.operador2',NULL),(57,'cm.operador3',NULL),(58,'cm.operador4',NULL),(19,'ia.operador1',NULL),(20,'ia.operador2',NULL),(21,'ia.operador3',NULL),(22,'ia.operador4',NULL),(47,'jc.operador1',NULL),(48,'jc.operador2',NULL),(49,'jc.operador3',NULL),(50,'jc.operador4',NULL),(39,'md.operador1',NULL),(40,'md.operador2',NULL),(41,'md.operador3',NULL),(42,'md.operador4',NULL),(27,'mo.operador1',NULL),(28,'mo.operador2',NULL),(29,'mo.operador3',NULL),(30,'mo.operador4',NULL),(59,'moc.operador1',NULL),(60,'moc.operador2',NULL),(61,'moc.operador3',NULL),(62,'moc.operador4',NULL),(43,'mp.operador1',NULL),(44,'mp.operador2',NULL),(45,'mp.operador3',NULL),(46,'mp.operador4',NULL),(35,'od.operador1',NULL),(36,'od.operador2',NULL),(37,'od.operador3',NULL),(38,'od.operador4',NULL),(51,'qf.operador1',NULL),(52,'qf.operador2',NULL),(53,'qf.operador3',NULL),(54,'qf.operador4',NULL),(64,'testing',NULL);
/*!40000 ALTER TABLE `gc_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gc_zona`
--

DROP TABLE IF EXISTS `gc_zona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gc_zona` (
  `N_ZONA_ID` int NOT NULL,
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `N_SILLAS_ESPERA` int DEFAULT NULL,
  `B_ACTIVA` smallint NOT NULL,
  `D_ZONA` varchar(256) DEFAULT NULL,
  `C_USUARIO_CREA` varchar(100) NOT NULL,
  `C_USUARIO_MODI` varchar(100) NOT NULL,
  `FI_VIGENCIA` datetime NOT NULL,
  `FF_VIGENCIA` datetime DEFAULT NULL,
  `F_MODIFICA` datetime DEFAULT NULL,
  `S_NOMBRE` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`N_ZONA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gc_zona`
--

LOCK TABLES `gc_zona` WRITE;
/*!40000 ALTER TABLE `gc_zona` DISABLE KEYS */;
INSERT INTO `gc_zona` VALUES (1,'80048',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','A'),(2,'80052',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','B'),(3,'80053',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','C'),(4,'80054',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','D'),(5,'80055',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','E'),(6,'80056',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','F'),(7,'80057',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','G'),(8,'80058',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','H'),(9,'80059',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','I'),(10,'80060',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','J'),(11,'80061',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','K'),(12,'80062',50,1,NULL,'sa.administrador','sa.administrador','2018-03-05 17:17:24',NULL,'2018-03-05 17:17:24','L');
/*!40000 ALTER TABLE `gc_zona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `per_cliente`
--

DROP TABLE IF EXISTS `per_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `per_cliente` (
  `ID_CLIENTE` bigint NOT NULL,
  `DUI` varchar(25) DEFAULT NULL,
  `CELULAR` varchar(25) DEFAULT '0',
  `TARJETA` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_cliente`
--

LOCK TABLES `per_cliente` WRITE;
/*!40000 ALTER TABLE `per_cliente` DISABLE KEYS */;
INSERT INTO `per_cliente` VALUES (1,'000000001','72860950','4072450024348841'),(2,'000000002','0','4215580001684632'),(3,'000000003','0','4072450024183131'),(4,'000000004','0',NULL),(5,'000000005','0',NULL),(6,'000000006','0',NULL),(7,'000000007','0',NULL),(8,'000000008','0',NULL);
/*!40000 ALTER TABLE `per_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `per_cliente_perfil`
--

DROP TABLE IF EXISTS `per_cliente_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `per_cliente_perfil` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ID_CLIENTE` bigint NOT NULL,
  `ID_FACTOR` bigint NOT NULL,
  `PUNTAJE` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_cliente_perfil`
--

LOCK TABLES `per_cliente_perfil` WRITE;
/*!40000 ALTER TABLE `per_cliente_perfil` DISABLE KEYS */;
INSERT INTO `per_cliente_perfil` VALUES (3,1,1,7),(4,1,2,4),(5,1,3,8),(6,2,1,8),(7,2,2,9),(9,2,3,8),(10,3,1,10),(11,3,2,9),(12,3,3,10),(13,4,1,2),(14,4,2,2),(15,4,3,1),(16,5,1,5),(17,5,2,5),(19,5,3,5),(20,6,1,6),(21,6,2,7),(22,6,3,8),(23,7,1,4),(24,7,2,3),(25,7,3,8),(26,8,1,7),(27,8,2,5),(28,8,3,6);
/*!40000 ALTER TABLE `per_cliente_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `per_factor`
--

DROP TABLE IF EXISTS `per_factor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `per_factor` (
  `ID_FACTOR` bigint NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(200) NOT NULL,
  PRIMARY KEY (`ID_FACTOR`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_factor`
--

LOCK TABLES `per_factor` WRITE;
/*!40000 ALTER TABLE `per_factor` DISABLE KEYS */;
INSERT INTO `per_factor` VALUES (1,'NIVEL SOCIOECONOMICO'),(2,'SECTOR VIVIENDA'),(3,'NIVEL ESTUDIO');
/*!40000 ALTER TABLE `per_factor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `per_oferta`
--

DROP TABLE IF EXISTS `per_oferta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `per_oferta` (
  `ID_OFERTA` bigint NOT NULL,
  `DESCRIPCION` varchar(500) DEFAULT NULL,
  `PUNTAJE_MINIMO` int DEFAULT NULL,
  `PUNTAJE_MAXIMO` int DEFAULT NULL,
  `CONTENIDO` longtext,
  PRIMARY KEY (`ID_OFERTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `per_oferta`
--

LOCK TABLES `per_oferta` WRITE;
/*!40000 ALTER TABLE `per_oferta` DISABLE KEYS */;
INSERT INTO `per_oferta` VALUES (1,'Tarjeta de Credito NEgra',20,30,'Tarjeta de Credito con interes anual del 25%'),(2,'Prestamo Mayor a $10,000.00',22,28,'Prestamo para mediana empresas y/o usuarios que sobrepasen movimientos de $5,000.00 en sus cuentas'),(3,'Refinanciamento',5,35,'Refinanciamento sobre el monto de su tarjeta de credito por un uso adecuado de la tarjeta'),(4,'Tarjeta de Credito platino',30,34,'Tarjeta de Credito con limite de');
/*!40000 ALTER TABLE `per_oferta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_listas_valor`
--

DROP TABLE IF EXISTS `tb_listas_valor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_listas_valor` (
  `C_LISTA` varchar(250) DEFAULT NULL,
  `C_MODULO` varchar(250) DEFAULT NULL,
  `D_LISTA` varchar(1024) DEFAULT NULL,
  `B_STATUS` int DEFAULT NULL,
  `C_USUARIO` varchar(50) DEFAULT NULL,
  `F_INGRESO` int DEFAULT NULL,
  `SYSTEM_VALUE` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_listas_valor`
--

LOCK TABLES `tb_listas_valor` WRITE;
/*!40000 ALTER TABLE `tb_listas_valor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_listas_valor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_listas_valor_det`
--

DROP TABLE IF EXISTS `tb_listas_valor_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_listas_valor_det` (
  `C_LISTA` varchar(250) DEFAULT NULL,
  `C_MODULO` varchar(250) DEFAULT NULL,
  `C_LISTA_DET` varchar(250) DEFAULT NULL,
  `C_VALOR` varchar(250) DEFAULT NULL,
  `D_LISTA_DET` varchar(250) DEFAULT NULL,
  `F_INICIO` datetime DEFAULT NULL,
  `F_FIN` datetime DEFAULT NULL,
  `C_USUARIO` varchar(50) DEFAULT NULL,
  `F_INGRESO` datetime DEFAULT NULL,
  `C_VALOR_1` varchar(250) DEFAULT NULL,
  `C_VALOR_2` varchar(250) DEFAULT NULL,
  `C_VALOR_DATE` datetime DEFAULT NULL,
  `C_VALOR_NUMBER` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_listas_valor_det`
--

LOCK TABLES `tb_listas_valor_det` WRITE;
/*!40000 ALTER TABLE `tb_listas_valor_det` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_listas_valor_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_unidad_recep`
--

DROP TABLE IF EXISTS `tb_unidad_recep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_unidad_recep` (
  `C_UNIDAD_RECEP` varchar(5) NOT NULL,
  `D_UNIDAD_RECEP` varchar(250) NOT NULL,
  `C_USUARIO` varchar(250) NOT NULL,
  `FH_INGRESO` datetime DEFAULT NULL,
  `B_STATUS` int DEFAULT NULL,
  `M_TIPO_UNIDAD` varchar(250) DEFAULT NULL,
  `B_DESPLEGABLE` int DEFAULT NULL,
  `C_UNIDAD` varchar(250) DEFAULT NULL,
  `C_UNIDAD_DGT` varchar(250) DEFAULT NULL,
  `C_DEP_MUN` varchar(250) DEFAULT NULL,
  `S_UBIC_GEOGRAF` varchar(200) DEFAULT NULL,
  `C_UNIDAD_RECEP_SUP` varchar(250) DEFAULT NULL,
  `M_SERVICIO` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`C_UNIDAD_RECEP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_unidad_recep`
--

LOCK TABLES `tb_unidad_recep` WRITE;
/*!40000 ALTER TABLE `tb_unidad_recep` DISABLE KEYS */;
INSERT INTO `tb_unidad_recep` VALUES ('80048','CIENCIAS ECONOMICAS','ss.administrador',NULL,1,'CENTRO DE SERVICIO',1,'80048',NULL,NULL,NULL,NULL,'M'),('80052','INGENIERIA Y ARQUITECTURA','ss.administrador',NULL,1,'CENTRO DE SERVICIO',1,'80052',NULL,NULL,NULL,NULL,'M'),('80053','CIENCIAS Y HUMANIDADES','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80053',NULL,NULL,NULL,NULL,'M'),('80054','MULTIDISCIPLINARIA DE ORIENTE','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80054',NULL,NULL,NULL,NULL,'M'),('80055','AGRONOMIA','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80055',NULL,NULL,NULL,NULL,'M'),('80056','ODONTOLOGIA','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80056',NULL,NULL,NULL,NULL,'M'),('80057','MEDICINA','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80057',NULL,NULL,NULL,NULL,'M'),('80058','MULTIDISCIPLINARIA PARACENTRAL','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80058',NULL,NULL,NULL,NULL,'M'),('80059','JURISPRUDENCIA Y CIENCIAS SOCIALES','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80059',NULL,NULL,NULL,NULL,'M'),('80060','QUIMICA Y FARMACIA','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80060',NULL,NULL,NULL,NULL,'M'),('80061','CIENCIAS NATURALES Y MATEMATICAS','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80061',NULL,NULL,NULL,NULL,'M'),('80062','MULTIDISCIPLINARIA DE OCCIDENTE','ss.administrador',NULL,0,'CENTRO DE SERVICIO',1,'80062',NULL,NULL,NULL,NULL,'M');
/*!40000 ALTER TABLE `tb_unidad_recep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `USERNAME` varchar(250) NOT NULL,
  `PASSWORD` varchar(1024) NOT NULL,
  `ENABLED` int DEFAULT NULL,
  `ROLE` varchar(1024) NOT NULL,
  `IDUSER` int NOT NULL,
  PRIMARY KEY (`IDUSER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbo'
--

--
-- Dumping routines for database 'dbo'
--
/*!50003 DROP FUNCTION IF EXISTS `CENTRO_SERVICIO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `CENTRO_SERVICIO`(
    UNIDAD_RECEP VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE CENT_SERV VARCHAR(6);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN UNIDAD_RECEP;
  END;
    SELECT IFNULL(MIN(C_LISTA_DET),UNIDAD_RECEP)
    INTO CENT_SERV
    FROM TB_LISTAS_VALOR_DET
    WHERE C_LISTA =
      (SELECT C_LISTA
      FROM TB_LISTAS_VALOR_DET
      WHERE C_MODULO ='GC'
      AND C_LISTA_DET=UNIDAD_RECEP
LIMIT 1
      );
  END;
  RETURN(CENT_SERV);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `DATEADD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `DATEADD`(interval_name VARCHAR(10), num INT, date_value DATE) RETURNS date
    DETERMINISTIC
BEGIN
    DECLARE result_date DATE;
    case LOWER(interval_name)
        WHEN 'year' THEN SET result_date = DATE_ADD(date_value, INTERVAL num YEAR);
        WHEN 'quarter' THEN SET result_date = DATE_ADD(date_value, INTERVAL num QUARTER);
        WHEN 'month' THEN SET result_date = DATE_ADD(date_value, INTERVAL num MONTH);
        WHEN 'week' THEN SET result_date = DATE_ADD(date_value, INTERVAL num WEEK);
        WHEN 'day' THEN SET result_date = DATE_ADD(date_value, INTERVAL num DAY);
        WHEN 'hour' THEN SET result_date = DATE_ADD(date_value, INTERVAL num HOUR);
        WHEN 'minute' THEN SET result_date = DATE_ADD(date_value, INTERVAL num MINUTE);
        WHEN 'second' THEN SET result_date = DATE_ADD(date_value, INTERVAL num SECOND);
        ELSE SET result_date = NULL;
    END CASE;

    RETURN result_date;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `ESTADO_USUARIO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `ESTADO_USUARIO`(
    USUARIO VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE ESTADO2 VARCHAR(256);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 'INACTIVO';
  END;
    SELECT ESTADO
    INTO ESTADO2
    FROM
      (SELECT S_CORRELATIVO ESTADO
      FROM gc_tiquete t
      WHERE t.M_ESTADO IN(2,3)
      AND t.C_USUARIO_ATENDIO = USUARIO
      AND TRUNCATE(t.FH_LLEGADA, 0) =DATE(SYSDATE()) 
      UNION
      SELECT E.D_EVENTOS ESTADO
      FROM gc_user_log ul
      LEFT OUTER JOIN GC_EVENTOS e
      ON ul.n_evento_id    =e.n_evento_id
      WHERE UL.FHF_EVENTO IS NULL
      AND UL.N_EVENTO_ID  IN (1,2,3)
      AND TRUNCATE(FHI_EVENTO, 0)=DATE(SYSDATE())
      AND UL.C_USUARIO     = USUARIO limit 1
      ) as subq
    LIMIT 1;
  END;
  RETURN(ESTADO2);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `GENERATIQUETE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `GENERATIQUETE`( 
   p_V_NO_TIQUETE varchar(20),
   p_v_usuario varchar(20)
) RETURNS varchar(5000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN 
        DECLARE v_retorno nvarchar(4000);
        DECLARE v_htmltiquete longtext;
        DECLARE v_v0 varchar(256);
        DECLARE v_v1 varchar(256);
		DECLARE v_v2 varchar(256);
        DECLARE v_v3 int;
        DECLARE v_v4 int;
        DECLARE v_v5 varchar(256);
        DECLARE v_v6 varchar(256);
        DECLARE v_v7 varchar(256);
        DECLARE v_v8 varchar(256);
        DECLARE v_v9 varchar(256);
        DECLARE v_v10 varchar(256);
        DECLARE v_v11 varchar(256);
	    DECLARE v_ss int; 
        DECLARE v_v14 varchar(1000);
		DECLARE v_CALCULO int;
		DECLARE v_CS varchar(100);
		DECLARE v_SECCION int;
	    DECLARE v_aux varchar(256);
	
    SET v_retorno = 'ok';

	SELECT 
    C_UNIDAD_RECEP
INTO v_CS FROM
    gc_tiquete
WHERE
    N_TIQUETE_ID = p_V_NO_TIQUETE;

	SELECT 
    N_TRAMITE_ID
INTO v_SECCION FROM
    gc_tiquete
WHERE
    N_TIQUETE_ID = p_V_NO_TIQUETE;

	   SET v_CALCULO=(select COUNT(*) from gc_tiquete 
					WHERE DATE_FORMAT(fh_llegada,'%d/%m/%Y') = DATE_FORMAT(NOW(3),'%d/%m/%Y')
					AND fh_llegada IS NOT NULL
					AND M_ESTADO = 1
					AND C_UNIDAD_RECEP =v_CS
					AND n_tramite_id = v_SECCION);

      BEGIN
		SELECT X_CONTENIDO INTO v_htmltiquete
		FROM dbo.gc_plantilla_tiq  LIMIT 1;
      END;

	  IF v_htmltiquete is null THEN
		set v_retorno='-1';
  	END IF;

      IF v_retorno <> '-1'
		 THEN
SELECT Count(*) - 1 into v_ss
FROM   gc_tiquete x,
       gc_tiquete a
       INNER JOIN tb_unidad_recep b
               ON a.c_unidad_recep = b.c_unidad_recep
       INNER JOIN gc_tramite c
               ON a.n_tramite_id = c.n_tramite_id
       INNER JOIN gc_servicios d
               ON d.n_servicios_id = c.n_servicios_id
       INNER JOIN gc_prioridad e
               ON e.n_prioridad_id = a.n_prioridad_id
       INNER JOIN gc_conf_tramite f
               ON f.n_tramite_id = c.n_tramite_id
                  AND a.c_unidad_recep = f.c_unidad_recep
WHERE  a.N_TIQUETE_ID = p_V_NO_TIQUETE
       AND x.c_unidad_recep = a.c_unidad_recep
       AND x.m_estado = 1
       AND x.n_tramite_id = a.n_tramite_id
       AND Trunc(x.fh_llegada) = Trunc(Now(3)); 
		 END IF;
         BEGIN
			SELECT 
 ROUND((ifnull(f.N_PROM_ESPERA,(f.N_ATENCION_PROM*(v_ss) + (f.N_TIEMPO_HOLGURA+1)))),0),
 b.D_UNIDAD_RECEP, 
 IFNULL(d.D_SERVICIOS,''),
 c.S_nombre,
 e.B_FILA_ESP,
 a.N_RESERVA_CITA_ID,
 a.NIT,
 a.S_CORRELATIVO,
 v_aux,
 DATE_FORMAT(a.FH_LLEGADA, '%H:%i'), 
 DATE_FORMAT(a.FH_LLEGADA, '%d/%m/%Y'), 
 a.N_TIQUETE_ID,
 CONCAT('<i class="glyphicon glyphicon-user"></i><i class="glyphicon glyphicon-user"></i><i class="glyphicon glyphicon-user"></i> <b>X</b> ', CONVERT(v_CALCULO, char(10))) INTO v_aux, v_v0, v_v1, v_v2, v_v3, v_v4, v_v5, v_v6, v_v7, v_v9, v_v10, v_v11, v_v14
            FROM 
               GC_TIQUETE a 
				  inner join TB_UNIDAD_RECEP b on a.C_UNIDAD_RECEP=b.C_UNIDAD_RECEP
				  inner join gc_tramite c on a.N_TRAMITE_ID = c.N_TRAMITE_ID
				  inner join gc_servicios d on d.n_servicios_id=c.n_servicios_id
				  inner join gc_prioridad e on e.N_PRIORIDAD_ID=a.N_PRIORIDAD_ID 
				  inner join gc_conf_tramite f on f.N_TRAMITE_ID = c.N_TRAMITE_ID and a.C_UNIDAD_RECEP=f.C_UNIDAD_RECEP
			  where a.N_TIQUETE_ID=p_V_NO_TIQUETE;
            
			SET v_v8 = 'usuario.usuario';

            SET v_htmltiquete = replace(v_htmltiquete, '[12]', '<img id="theimg" style="width:65;height:45" src="#" />');

            SET v_htmltiquete = replace(v_htmltiquete, '[10]', IFNULL(v_v10, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[11]', IFNULL(v_v11, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[0]', IFNULL(v_v0, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[1]', IFNULL(v_v1, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[2]', IFNULL(v_v2, ''));

            IF v_v3 = 1 THEN
               SET v_htmltiquete = replace(v_htmltiquete, '[3]', 'Fila Atencion Especial: SI');
            ELSE 
               SET v_htmltiquete = replace(v_htmltiquete, '[3]', '');
            END IF;

            IF v_v4 IS NULL THEN
               SET v_htmltiquete = replace(v_htmltiquete, '[4]', '');
            ELSE 
               SET v_htmltiquete = replace(v_htmltiquete, '[4]', 'Con Cita Previa: SI');
            END IF;

            IF v_v5 IS NULL THEN
               SET v_htmltiquete = replace(v_htmltiquete, '[5]', ' ');
            ELSE 
               SET v_htmltiquete = replace(v_htmltiquete, '[5]', CONCAT('NIT: ' , IFNULL(v_v5, '')));
            END IF;

            SET v_htmltiquete = replace(v_htmltiquete, '[6]', IFNULL(v_v6, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[7]', IFNULL(v_aux, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[8]', IFNULL(v_v8, ''));

            SET v_htmltiquete = replace(v_htmltiquete, '[9]', IFNULL(v_v9, ''));

			SET v_htmltiquete=REPLACE(v_htmltiquete,'[14]',IFNULL(v_v14,''));

            SET v_htmltiquete = replace(v_htmltiquete, 'á', 'a');

            SET v_htmltiquete = replace(v_htmltiquete, 'é', 'e');

            SET v_htmltiquete = replace(v_htmltiquete, 'í', 'i');

            SET v_htmltiquete = replace(v_htmltiquete, 'ó', 'o');

            SET v_htmltiquete = replace(v_htmltiquete, 'ú', 'u');

            SET v_htmltiquete = replace(v_htmltiquete, 'ñ', 'n');

            SET v_htmltiquete = replace(v_htmltiquete, 'Ñ', 'N');

            SET v_htmltiquete = replace(v_htmltiquete, 'Á', 'A');

            SET v_htmltiquete = replace(v_htmltiquete, 'É', 'E');

            SET v_htmltiquete = replace(v_htmltiquete, 'Í', 'I');

            SET v_htmltiquete = replace(v_htmltiquete, 'Ó', 'O');

            SET v_htmltiquete = replace(v_htmltiquete, 'Ú', 'U');
			/*
            SET v_htmltiquete = replace(v_htmltiquete, '<estilo>', '<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> <style>h3 {font-size:22px;font-weight: bold;} .marco {padding: 1px;border: 2px solid black;margin: 1px;}.inn {padding: 1px;border: none;margin: 5px;background-color: FFFFFF;text-align:center;}</style>');
            */
            SET v_htmltiquete = replace(v_htmltiquete, '<estilo>', ' ');
            SET v_retorno = v_htmltiquete;

         END;

      RETURN TRIM(v_retorno);

   END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `getdate`() RETURNS datetime
    DETERMINISTIC
BEGIN
    DECLARE current_datetime DATETIME;
    SET current_datetime = NOW();
    RETURN current_datetime;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `regexp_replace2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `regexp_replace2`(p_original VARCHAR(1000), p_replacement VARCHAR(1000)) RETURNS varchar(1000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
		DECLARE v_temp VARCHAR(1000);
		DECLARE v_ch VARCHAR(1);
		DECLARE v_i INT;
		declare v_patron tinyint default 0;
		declare v_auxi varchar(1000);
		DECLARE v_FLAG INT;
		set v_ch='';
		SET v_i = 1;
		SET v_temp = '';
		set v_auxi='';
		set v_patron=0;
		SET v_FLAG=1;

		myloop: WHILE v_FLAG <= CHAR_LENGTH(RTRIM(p_original))
		DO	
			BEGIN
				BEGIN
					SET v_FLAG = v_FLAG + 1;
				END;
				
					IF (v_i > CHAR_LENGTH(RTRIM(p_original))) THEN
						LEAVE myloop;
					END IF;
					
			END;
			SET v_ch = SUBSTRING(p_original,v_i,1);
				if v_ch='<'
				  then
				  set v_patron=1;
				  set v_temp=concat(v_temp,v_auxi);
				  set v_auxi='';
				  end if;
				
				if v_ch = '>'
				  THEN
					  if v_patron=1
					     then
						 set v_temp=concat(v_temp,p_replacement);
						 set v_patron=0;
						 set v_auxi='';
						 end if;
				else
				  set v_auxi=concat(v_auxi,v_ch);
				end if;
			   SET v_i=v_i+1;
		END WHILE;
			if v_temp='' then 
				set v_temp=p_original;
			end if;
	RETURN(v_temp);
	END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SECCIONES_USUARIO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SECCIONES_USUARIO`(USUARIO VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN DECLARE SECCIONES VARCHAR(1000);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 'NO SECCIONES ASOCIADAS';
  END;
       SELECT group_concat(s_nombre)  INTO SECCIONES FROM (
          SELECT distinct se.s_nombre
            FROM GC_USR_TRA UT
                LEFT OUTER JOIN gc_tramite tra ON UT.N_TRAMITE_ID=TRA.N_TRAMITE_ID
                LEFT OUTER JOIN gc_servicios se ON tra.n_servicios_id=se.n_servicios_id
            WHERE UT.c_usuario   = USUARIO ) as subq;
  END;
  RETURN(SECCIONES);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SECCION_MAXIMO_ESPERA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SECCION_MAXIMO_ESPERA`(SECCION DOUBLE,CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN DECLARE MAXIMA VARCHAR(1000);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN '00:00:00';
  END;
        SELECT REPLACE(
          CONCAT(IFNULL(IFNULL(TO_CHAR(truncate(MOD(SUM(MAXIMO),86400)/3600, 0),'09'),'00'), '') ,':',
          IFNULL(IFNULL(TO_CHAR(truncate(MOD(MOD(SUM(MAXIMO),86400),3600)/60, 0),'09'),'00'), '') ,':',
          IFNULL(IFNULL(TO_CHAR(MOD(MOD(MOD(SUM(MAXIMO),86400),3600),60),'09'),'00'), '')),' ','') INTO MAXIMA FROM(
          SELECT MAX(MINIMO)*24*60*60 MAXIMO FROM (
            SELECT
            (IFNULL(fh_llamado,SYSDATE())-fh_llegada) MINIMO
            FROM gc_tiquete
            WHERE truncate(fh_llegada, 0)=date(SYSDATE())
            AND fh_llegada IS NOT NULL
            AND M_ESTADO=1
            AND C_UNIDAD_RECEP=CS
            AND n_tramite_id in (SELECT n_tramite_id FROM gc_tramite WHERE n_servicios_id=SECCION)) as subq1
          ) as subq2;
  END;
  RETURN(MAXIMA);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SECCION_MINIMO_ESPERA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SECCION_MINIMO_ESPERA`(SECCION DOUBLE,CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN DECLARE MINIMA VARCHAR(1000);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN '00:00:00';
  END;
        
    /*MINIMO*/
    SELECT REPLACE(
    CONCAT(IFNULL(IFNULL(TO_CHAR(truncate(MOD(SUM(MINIMO),86400)/3600, 0),'09'),'00'), '') ,':',
    IFNULL(IFNULL(TO_CHAR(truncate(MOD(MOD(SUM(MINIMO),86400),3600)/60, 0),'09'),'00'), '') ,':',
    IFNULL(IFNULL(TO_CHAR(MOD(MOD(MOD(SUM(MINIMO),86400),3600),60),'09'),'00'), '')),' ','') INTO MINIMA FROM(
    SELECT MIN(MINIMO)*24*60*60 MINIMO FROM (
      select 
      (IFNULL(fh_llamado,SYSDATE())-fh_llegada) MINIMO
      From gc_tiquete
      where truncate(fh_llegada, 0)=date(sysdate()) 
      AND fh_llegada IS NOT NULL
      --  SQLINES DEMO *** NOT NULL
      AND M_ESTADO=1
      AND C_UNIDAD_RECEP=CS
      AND n_tramite_id in (select n_tramite_id from gc_tramite where n_servicios_id=SECCION)) as subq1
    ) as subq2;
  END;
  RETURN(MINIMA);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SECCION_PROMEDIO_ESPERA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SECCION_PROMEDIO_ESPERA`(SECCION DOUBLE,CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN DECLARE PROMEDIO VARCHAR(1000);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN '00:00:00';
  END;
        
      /*PROMEDIO*/
      SELECT REPLACE(
        CONCAT(IFNULL(IFNULL(TO_CHAR(truncate(MOD(SUM(PROMEDIO),86400)/3600, 0),'09'),'00'), '') ,':',
        IFNULL(IFNULL(TO_CHAR(truncate(MOD(MOD(SUM(PROMEDIO),86400),3600)/60, 0),'09'),'00'), '') ,':',
        IFNULL(IFNULL(TO_CHAR(MOD(MOD(MOD(SUM(PROMEDIO),86400),3600),60),'09'),'00'), '')),' ','') INTO PROMEDIO FROM(
        SELECT AVG(PROM)*24*60*60 PROMEDIO FROM (
          SELECT 
          (IFNULL(fh_llamado,SYSDATE())-fh_llegada) PROM
          From gc_tiquete
          where truncate(fh_llegada, 0)=date(sysdate()) 
          AND fh_llegada IS NOT NULL
          --  SQLINES DEMO *** NOT NULL
          AND M_ESTADO=1
          AND C_UNIDAD_RECEP=CS
          AND n_tramite_id in (select n_tramite_id from gc_tramite where n_servicios_id=SECCION)) as subq1
        ) as subq2;
  END;
  RETURN(PROMEDIO);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SECCION_SATURACION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SECCION_SATURACION`(SECCION DOUBLE, CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE RESULTADO       DOUBLE  DEFAULT 0;
  DECLARE TOTAL_ESPERANDO DOUBLE  DEFAULT 0;
  DECLARE FRACIONES_TOTAL DOUBLE  DEFAULT 0;
  DECLARE TIEMPO_ATENCION DOUBLE  DEFAULT 0;
  DECLARE MAX_ESPERA      DOUBLE  DEFAULT 0;
  DECLARE FRACCION        DOUBLE  DEFAULT 0;
  DECLARE CUENTA_TRAMITES DOUBLE  DEFAULT 0;
  DECLARE HORA            varchar(20);
  DECLARE NTRAMITE        varchar(200);
  DECLARE RETORNO         varchar(4000);
  DECLARE SECCIONESS_TRAMITE VARCHAR(200);
  DECLARE not_found INT DEFAULT 0;
  DECLARE SECCIONES CURSOR FOR SELECT N_TRAMITE_ID TRAMITE FROM GC_TRAMITE WHERE N_SERVICIOS_ID=SECCION;
  DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET not_found = 1; 
 open SECCIONES;
 FETCH SECCIONES INTO SECCIONESS_TRAMITE;
 WHILE NOT_FOUND=0
 DO
            /* SQLINES DEMO *** R ESE TRAMITE */
            BEGIN
            DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
              SET TOTAL_ESPERANDO = 0 ;
              RETURN 0;
              RESIGNAL;
            END;
              SELECT COUNT(*)
              INTO TOTAL_ESPERANDO
              FROM gc_tiquete ti
              WHERE ti.n_tramite_id   =SECCIONESS_TRAMITE
              AND ti.c_unidad_recep   =CS
              AND TRUNCATE(ti.fh_llegada, 0)=DATE(SYSDATE())
              AND ti.m_estado         = 1;
            END;
        IF TOTAL_ESPERANDO>0 THEN
            /**** DE ATENCION Y MAXIMO DE ESPERO PARA ESE TRAMITE EN UN CENTRO DE SERVICIO*/
            BEGIN
            DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
              SET TIEMPO_ATENCION = 5 ;
              SET MAX_ESPERA      = 30 ;
            END;
              SELECT IFNULL(n_atencion_prom,5),
                IFNULL(n_tiempo_espera,30)
              INTO TIEMPO_ATENCION,
                MAX_ESPERA
              FROM GC_CONF_TRAMITE
              WHERE c_unidad_recep=CS
              AND n_tramite_id    =SECCIONESS_TRAMITE;
            END;
          
            BEGIN
            DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
              SET FRACCION=0;
              RESIGNAL;
            END;
              SELECT SUM(fraccion)
              INTO FRACCION
              FROM gc_usr_tra ut
              LEFT OUTER JOIN gc_usuario us
              ON ut.c_usuario=us.c_usuario,
                (SELECT c_usuario usr,
                  (1/COUNT(n_tramite_id)) fraccion
                FROM gc_usr_tra
                WHERE c_usuario IN
                  (SELECT c_usuario
                  FROM gc_usuario us,
                    gc_escritorio es
                  WHERE us.n_escritorio_id=es.n_escritorio_id
                  AND es.c_unidad_recep=CS
                  )
                AND EXISTS
                  (SELECT *
                  FROM gc_tiquete
                  WHERE c_unidad_recep =CS
                  AND TRUNCATE(fh_llegada, 0)=DATE(sysdate())
                  AND m_estado         =1
                  AND n_tramite_id     =gc_usr_tra.n_tramite_id
                  )
                GROUP BY c_usuario
                ) fra
              WHERE us.c_usuario  = fra.usr
              AND ut.n_tramite_id = SECCIONESS_TRAMITE;
            END;
            BEGIN DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
              SET RESULTADO =RESULTADO;
 END;
              IF (TOTAL_ESPERANDO >0 AND FRACCION >0 AND TIEMPO_ATENCION>0) THEN
                SET RESULTADO        =RESULTADO + TRUNCATE((((TOTAL_ESPERANDO *TIEMPO_ATENCION ) / FRACCION)/MAX_ESPERA)*100, 0 ) ;
              END IF;
            END;
            END IF;
            SET CUENTA_TRAMITES=CUENTA_TRAMITES+1;
  FETCH SECCIONES INTO SECCIONESS_TRAMITE;
  END WHILE;
  CLOSE SECCIONES;
  BEGIN DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
    SET RESULTADO =0;
 END;
    SET RESULTADO=RESULTADO/CUENTA_TRAMITES;
  END;
  select date_format(sysdate(),'HH:%i am') into HORA from dual;
  select s_nombre into NTRAMITE from GC_SERVICIOS where n_servicios_id=SECCION;
   SET RETORNO=CONCAT(IFNULL(HORA, ''),';',IFNULL(TOTAL_ESPERANDO, ''),';',IFNULL(RESULTADO, ''),';',IFNULL(NTRAMITE, ''));
  RETURN RETORNO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SIGUIENTE_TIQUETE` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SIGUIENTE_TIQUETE`( 
   p_TRAMITE int,
   p_CS nvarchar(4000)
) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN

      DECLARE
         v_TIQUE_GENERADO nvarchar(1000);

      DECLARE
         v_ZONA nvarchar(2);

      SET p_CS = NULLIF(p_CS, '');

      BEGIN
         SELECT TabAl.S_NOMBRE INTO v_ZONA
         FROM 
            (
               SELECT TabAl2.saturacion, TabAl2.S_NOMBRE
               FROM 
                  (
                     SELECT ZONA_SATURACION(p_CS, gc_zona.N_ZONA_ID, gc_zona.S_NOMBRE) AS saturacion, gc_zona.S_NOMBRE
                     FROM gc_zona
                     WHERE gc_zona.C_UNIDAD_RECEP = p_CS AND gc_zona.N_ZONA_ID IN 
                        (
                           SELECT gc_escritorio.N_ZONA_ID
                           FROM gc_escritorio
                           WHERE gc_escritorio.N_ESCRITORIO_ID IN 
                              (
                                 SELECT gc_usuario.N_ESCRITORIO_ID
                                 FROM gc_usuario
                                 WHERE gc_usuario.C_USUARIO IN 
                                    (
                                       SELECT gc_usr_tra.C_USUARIO
                                       FROM gc_usr_tra
                                       WHERE gc_usr_tra.N_TRAMITE_ID = p_TRAMITE
                                    )
                              ) AND gc_escritorio.C_UNIDAD_RECEP = p_CS
                        )
                  )  AS TabAl2
                  ORDER BY TabAl2.saturacion
            )  AS TabAl
         LIMIT 1;
      END;

      BEGIN
	 
		 SELECT CAST(SUBSTRING(S_CORRELATIVO,2,4) AS unsigned)+1 INTO v_TIQUE_GENERADO 
                  FROM GC_TIQUETE
                  WHERE N_TIQUETE_ID =(SELECT MAX(N_TIQUETE_ID)
                     FROM GC_TIQUETE
                     WHERE C_UNIDAD_RECEP = p_CS
                     AND S_CORRELATIVO LIKE CONCAT(IFNULL(v_ZONA,''),'%')
                     AND trunc(FH_LLEGADA) = trunc(now(3)));
         
		 SELECT CAST((NULL + 1) AS char(50)) INTO v_TIQUE_GENERADO
         FROM gc_tiquete
         WHERE gc_tiquete.N_TIQUETE_ID = 
            (
               SELECT max(gc_tiquete.N_TIQUETE_ID)
               FROM gc_tiquete
               WHERE 
                  gc_tiquete.C_UNIDAD_RECEP = p_CS AND 
                  gc_tiquete.S_CORRELATIVO LIKE concat((IFNULL(v_ZONA, '')) , ('%')) AND 
                  gc_tiquete.FH_LLEGADA = (CAST(now(3) AS date))
            );
         

     

      END;

      BEGIN
         IF v_TIQUE_GENERADO IS NULL OR CAST(v_TIQUE_GENERADO AS double) >= 999 THEN
            SET v_TIQUE_GENERADO = '1';
         END IF;
      END;

      SET v_TIQUE_GENERADO = CONCAT(IFNULL(v_ZONA, '') , IFNULL(v_TIQUE_GENERADO, ''));

      RETURN v_TIQUE_GENERADO;

   END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `SUSTITUIR_SIMBOLOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `SUSTITUIR_SIMBOLOS`(CADENA2 VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE CADENA VARCHAR(4000);
   
          BEGIN DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
              RETURN CADENA;
		  END;
             SET CADENA=CADENA2;
             SET CADENA=replace(CADENA,'á',';');
             SET CADENA=replace(CADENA,'é',';');
             SET CADENA=replace(CADENA,'í',';');
             SET CADENA=replace(CADENA,'ó',';');
             SET CADENA=replace(CADENA,'ú',';');
             SET CADENA=replace(CADENA,'ñ',';');
             SET CADENA=replace(CADENA,'Ñ',';');
             SET CADENA=replace(CADENA,'Á',';');
             SET CADENA=replace(CADENA,'É',';');
             SET CADENA=replace(CADENA,'Í',';');
             SET CADENA=replace(CADENA,'Ó',';');
             SET CADENA=replace(CADENA,'Ú',';');
          END;
            RETURN CADENA;
        END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `to_char` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `to_char`(num INT) RETURNS varchar(255) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
    DECLARE result varchar(6);
    
    IF num IS NULL THEN
        SET result = '0';
    ELSE
        SET result = CAST(num AS CHAR);
    END IF;
    
    RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `TRAMITES_USUARIO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `TRAMITES_USUARIO`(USUARIO VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN DECLARE TRAMITES VARCHAR(14000);
 
  BEGIN

  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 'SIN TRAMITES ASIGNADOS';

  END;
        SELECT IFNULL(GROUP_CONCAT(s_nombre),'SIN TRAMITES ASIGNADOS') INTO TRAMITES FROM
               (SELECT DISTINCT TRA.s_nombre
                  FROM GC_USR_TRA UT
                  LEFT OUTER JOIN gc_tramite tra
                  ON UT.N_TRAMITE_ID=TRA.N_TRAMITE_ID
                  LEFT OUTER JOIN gc_servicios se
                  ON tra.n_servicios_id=se.n_servicios_id
                  WHERE UT.c_usuario   = USUARIO) as subq;
  END;
  RETURN(TRAMITES);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `TRAMITE_SATURACION_ALERTA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `TRAMITE_SATURACION_ALERTA`(
    TRAMITE DOUBLE,
    CS      VARCHAR(4000),
    ZONA    DOUBLE) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE RESULTADO       DOUBLE  DEFAULT 0;
  DECLARE TOTAL_ESPERANDO DOUBLE  DEFAULT 0;
  DECLARE FRACIONES_TOTAL DOUBLE  DEFAULT 0;
  DECLARE TIEMPO_ATENCION DOUBLE  DEFAULT 0;
  DECLARE MAX_ESPERA      DOUBLE  DEFAULT 0;
  DECLARE FRACCION        DOUBLE  DEFAULT 0;
  DECLARE RETORNO         VARCHAR(5000);
  DECLARE HORA            varchar(20);
  DECLARE NTRAMITE        varchar(200);
  /* NA CON SUS FRACIONES DE ATENCION POR TRAMITE*/
  -- ta.test tiene dos tramites asignados y hay personas esperando por los dos tramites
  -- accion de atencion por tramite es 1/cantidad tramites asignados(segun personas esperando) = 1/2=0.5
  -- personas esperando solo por 1 tramite aunque tenga asignados 2 tramites la fraccion de este usuario es 1
  -- RACCION) * PROMEDIO_ATENCION

  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    SET TOTAL_ESPERANDO = 0 ;
    RETURN 0;
    RESIGNAL;
  END;
    SELECT COUNT(*)
    INTO TOTAL_ESPERANDO
    FROM gc_tiquete ti
    WHERE ti.n_tramite_id   =TRAMITE
    AND ti.c_unidad_recep   =CS
    AND TRUNCATE(ti.fh_llegada, 0)=DATE(SYSDATE())
    AND ti.m_estado         = 1;
  END;
  /*** DE ATENCION Y MAXIMO DE ESPERO PARA ESE TRAMITE EN UN CENTRO DE SERVICIO*/
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    SET TIEMPO_ATENCION = 5 ;
    SET MAX_ESPERA      = 30 ;
    RESIGNAL;
  END;
    SELECT IFNULL(n_atencion_prom,5),
      IFNULL(n_tiempo_espera,30)
    INTO TIEMPO_ATENCION,
      MAX_ESPERA
    FROM GC_CONF_TRAMITE
    WHERE c_unidad_recep=CS
    AND n_tramite_id    =TRAMITE;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    SET FRACCION=0;
    RESIGNAL;
  END;
    SELECT SUM(fraccion)
    INTO FRACCION
    FROM gc_usr_tra ut
    LEFT OUTER JOIN gc_usuario us
    ON ut.c_usuario=us.c_usuario,
      (SELECT c_usuario usr,
        (1/COUNT(n_tramite_id)) fraccion
      FROM gc_usr_tra
      WHERE c_usuario IN
        (SELECT c_usuario
        FROM gc_usuario us,
          gc_escritorio es
        WHERE us.n_escritorio_id=es.n_escritorio_id
        AND n_zona_id           =ZONA
        )
      AND EXISTS
        (SELECT *
        FROM gc_tiquete
        WHERE c_unidad_recep =CS
        AND TRUNCATE(fh_llegada, 0)=DATE(sysdate())
        AND m_estado         =1
        AND n_tramite_id     =gc_usr_tra.n_tramite_id
        )
      GROUP BY c_usuario
      ) fra
    WHERE us.c_usuario  = fra.usr
    AND ut.n_tramite_id = TRAMITE;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
    SET RESULTADO =0;
    RESIGNAL;
  END;
     select date_format(sysdate(),'HH:%i am') into HORA from dual;
    IF (TOTAL_ESPERANDO >0 AND FRACCION >0 AND TIEMPO_ATENCION>0) THEN
      SET RESULTADO        = TRUNCATE((((TOTAL_ESPERANDO *TIEMPO_ATENCION ) / FRACCION)/MAX_ESPERA)*100, 0 ) ;      
    END IF;
  END;
  select s_nombre into NTRAMITE from GC_TRAMITE where n_tramite_id=TRAMITE;
  SET RETORNO=CONCAT(IFNULL(HORA, ''),';',IFNULL(TOTAL_ESPERANDO, ''),';',IFNULL(RESULTADO, ''),';',IFNULL(NTRAMITE, ''));
  RETURN RETORNO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `TRUNC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `TRUNC`(input_datetime DATETIME) RETURNS date
    DETERMINISTIC
BEGIN
    DECLARE fecha_string VARCHAR(10);
    DECLARE result_date DATE;

    SET fecha_string = DATE_FORMAT(input_datetime, '%d/%m/%Y');
    SET result_date = STR_TO_DATE(fecha_string, '%d/%m/%Y');

    RETURN result_date;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `UNIDAD_SATURACION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `UNIDAD_SATURACION`(
    CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE RESULTADO      DOUBLE  DEFAULT 0;
  DECLARE N_SILLAS       DOUBLE  DEFAULT 0;
  DECLARE N_TIQUE_ESPERA DOUBLE  DEFAULT 0;
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 0;
  END;
    SELECT COUNT(*)
    INTO N_TIQUE_ESPERA
    FROM gc_tiquete
    WHERE c_unidad_recep =CENTRO_SERVICIO(CS)
    AND TRUNCATE(fh_llegada, 0)=DATE(sysdate())
    AND m_estado         =1;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 0;
  END;
    SELECT SUM(n_sillas_espera)
    INTO N_SILLAS
    FROM gc_zona
    WHERE c_unidad_recep=CENTRO_SERVICIO(CS)
    AND b_activa        =1
    AND ff_vigencia    IS NULL;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
    RETURN 0;
  END;
    SET RESULTADO=TRUNCATE((N_TIQUE_ESPERA/N_SILLAS)*100, 0);
  END;
  RETURN RESULTADO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `UNIDAD_SATURACION_ALERTA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `UNIDAD_SATURACION_ALERTA`(
    CS VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE RESULTADO      DOUBLE  DEFAULT 0;
  DECLARE N_SILLAS       DOUBLE  DEFAULT 0;
  DECLARE N_TIQUE_ESPERA DOUBLE  DEFAULT 0;
  DECLARE HORA        VARCHAR(20);
  DECLARE RETORNO     VARCHAR(2000);
  DECLARE NUNIDAD       VARCHAR(256);
 
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 0;
  END;
    SELECT COUNT(*)
    INTO N_TIQUE_ESPERA
    FROM gc_tiquete
    WHERE c_unidad_recep =CENTRO_SERVICIO(CS)
    AND DATE(fh_llegada)=DATE(sysdate())
    AND m_estado         =1;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    RETURN 0;
  END;
    SELECT SUM(n_sillas_espera)
    INTO N_SILLAS
    FROM gc_zona
    WHERE c_unidad_recep=CENTRO_SERVICIO(CS)
    AND b_activa        =1
    AND ff_vigencia    IS NULL;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
    RETURN 0;
  END;
    SET RESULTADO=TRUNCATE((N_TIQUE_ESPERA/N_SILLAS)*100, 0);
  END;
  begin
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
   select d_unidad_recep into NUNIDAD from tb_unidad_recep where c_unidad_recep=CS;
  END;
      select D_LISTA into NUNIDAD from TB_LISTAS_VALOR where c_lista =(select c_lista from TB_LISTAS_VALOR_DET where c_modulo='GC' and c_lista_det=CS
limit 1);
  END;    
   select date_format(sysdate(),'HH:%i am') into HORA from dual;  
  SET RETORNO=CONCAT(IFNULL(HORA, ''),';',IFNULL(N_TIQUE_ESPERA, ''),';',IFNULL(RESULTADO, ''),';',IFNULL(NUNIDAD, ''),';',IFNULL(N_SILLAS, ''));
  RETURN RETORNO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `ZONA_SATURACION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `ZONA_SATURACION`( 
   p_CS nvarchar(4000),
   p_ZONA_ID int,
   p_ZONA nvarchar(4000)
) RETURNS int
    DETERMINISTIC
BEGIN

      DECLARE
         v_RESULTADO int DEFAULT 0;

      DECLARE
         v_T_ESPERANDO int DEFAULT 0;

      DECLARE
         v_NSILLAS int DEFAULT 0;

      SET p_CS = NULLIF(p_CS, '');

      SET p_ZONA = NULLIF(p_ZONA, '');

      BEGIN
         SELECT count(*) INTO v_T_ESPERANDO
         FROM gc_tiquete
         WHERE 
            gc_tiquete.FH_LLEGADA = CAST(now(3) AS DATE) AND 
            substring(IFNULL(gc_tiquete.S_CORRELATIVO, '0'), 1, 1) = p_ZONA AND 
            CAST(gc_tiquete.M_ESTADO AS double) = 1 AND 
            gc_tiquete.C_UNIDAD_RECEP = p_CS;
      END;

      BEGIN
         SELECT gc_zona.N_SILLAS_ESPERA INTO v_NSILLAS
         FROM gc_zona
         WHERE gc_zona.N_ZONA_ID = p_ZONA_ID;
      END;

      BEGIN


         DECLARE
            v_db_null_statement int;

      END;

      RETURN v_RESULTADO;

   END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `ZONA_SATURACION_ALERTA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `ZONA_SATURACION_ALERTA`(
    CS      VARCHAR(4000),
    ZONA_ID DOUBLE,
    ZONA    VARCHAR(4000)) RETURNS varchar(4000) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE RESULTADO   DOUBLE  DEFAULT 0;
  DECLARE T_ESPERANDO DOUBLE  DEFAULT 0;
  DECLARE NSILLAS     DOUBLE  DEFAULT 0;
  DECLARE HORA        VARCHAR(20);
  DECLARE RETORNO     VARCHAR(2000);
  DECLARE NZONA       VARCHAR(256);
 
  /**** DE ATENCION Y MAXIMO DE ESPERO PARA ESE TRAMITE EN UN CENTRO DE SERVICIO*/
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    SET T_ESPERANDO = 5 ;
    RETURN 0;
  END;
    SELECT COUNT(*)
    INTO T_ESPERANDO
    FROM gc_tiquete
    WHERE date(fh_llegada)=DATE(SYSDATE())
    AND SUBSTR(IFNULL(s_correlativo,'0'),0,1)=ZONA
    AND m_estado                          =1
    AND c_unidad_recep                    =CS;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR NOT FOUND BEGIN
    SET NSILLAS = 0 ;
    RETURN 0;
  END;
    SELECT n_sillas_espera,D_ZONA INTO NSILLAS,NZONA FROM gc_zona WHERE n_zona_id=ZONA_ID;
  END;
  BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
    RETURN 0;
  END;
    SET RESULTADO=TRUNCATE((T_ESPERANDO/NSILLAS)*100, 0);
  END;
  select date_format(sysdate(),'HH:%i am') into HORA from dual;  
  SET RETORNO=CONCAT(IFNULL(HORA, ''),';',IFNULL(T_ESPERANDO, ''),';',IFNULL(RESULTADO, ''),';',IFNULL(NZONA, ''),';',IFNULL(NSILLAS, ''));
  RETURN RETORNO;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `NextId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `NextId`( IN unit_id INT,IN operator_name VARCHAR(255))
BEGIN
    SELECT n_tiquete_id
FROM (
    SELECT ti.n_tiquete_id,
           ti.FH_LLEGADA,
           orden
    FROM (
        SELECT tiq.n_tiquete_id,
               tiq.fh_llegada,
               1 AS orden,
               n_prioridad_id
        FROM gc_tiquete tiq
        JOIN gc_usr_tra utra ON tiq.n_tramite_id = utra.n_tramite_id
        WHERE tiq.c_unidad_recep = unit_id
              AND tiq.m_estado = 1
              AND tiq.n_jrq_sec_id IS NULL
              AND CAST(tiq.FH_LLEGADA AS DATE) = CAST(NOW(3) AS DATE)
              AND tiq.n_tramite_id IN (
                  SELECT utra.n_tramite_id
                  FROM gc_usr_tra utra
                  WHERE utra.C_usuario = operator_name
              )
              AND NOW(3) >= TIMESTAMPADD(SECOND, IFNULL(tiq.n_tiempo_holgura, 0) / 1440, tiq.FH_LLEGADA)
        UNION
        SELECT tique.n_tiquete_id,
               tique.fh_llegada,
               2 AS orden,
               tique.n_prioridad_id
        FROM gc_tiquete tique
        JOIN gc_usr_tra ustra ON tique.n_tramite_id = ustra.n_tramite_id
        JOIN gc_zona zon ON zon.n_zona_id = (
                SELECT esc.n_zona_id
                FROM gc_escritorio esc
                WHERE esc.n_escritorio_id = (
                        SELECT usu.n_escritorio_id
                        FROM gc_usuario usu
                        WHERE usu.C_usuario = operator_name
                    )
            )
        WHERE tique.c_unidad_recep = unit_id
              AND tique.m_estado = 1
              AND CAST(tique.FH_LLEGADA AS DATE) = CAST(NOW(3) AS DATE)
              AND tique.n_tramite_id IN (
                  SELECT ustra.n_tramite_id
                  FROM gc_usr_tra ustra
                  WHERE ustra.C_usuario = operator_name
              )
              AND tique.s_correlativo NOT LIKE CONCAT(zon.s_nombre, '%')
              AND NOW(3) >= TIMESTAMPADD(SECOND, IFNULL(tique.n_tiempo_holgura, 0) / 1440, tique.FH_LLEGADA)
    ) AS ti
    JOIN gc_prioridad pr ON ti.n_prioridad_id = pr.n_prioridad_id
) AS sfinal
ORDER BY orden, TIMESTAMPDIFF(SECOND, fh_llegada, NOW(3)) DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PRC_SECCION_ESPERA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRC_SECCION_ESPERA`(  p_SECCION int,
	  p_CS nvarchar(4000),
	  OUT p_MODA nvarchar(1000) /* = NULL */ )
BEGIN
	
	DECLARE v_VAR INT;
	SET p_CS = NULLIF(p_CS, '');
	DROP TABLE IF EXISTS tmp_tabagg;
	
	-- SQLINES DEMO *** temporal
	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	CREATE TEMPORARY TABLE tmp_tabagg (
		campo1 INT
	);
	   -- SQLINES LICENSE FOR EVALUATION USE ONLY
   	INSERT INTO tmp_tabagg (campo1)
       SELECT MODAN FROM(SELECT MINIMO*24*60*60	AS MODAN FROM(SELECT(TIMESTAMPDIFF(day,fh_llegada,IFNULL(fh_llamado,NOW(3)))) MINIMO
            FROM gc_tiquete
            WHERE DATE_FORMAT(fh_llegada,'%d/%m/%Y') = DATE_FORMAT(NOW(3),'%d/%m/%Y')
            AND fh_llegada IS NOT NULL
            AND M_ESTADO = 1
            AND C_UNIDAD_RECEP = p_CS
            AND n_tramite_id in(select n_tramite_id from gc_tramite where n_servicios_id = p_SECCION)) AS TabAl2) AS TabAl;

       SELECT  REPLACE(CONCAT(IFNULL(IFNULL(m2ss.lpad(ROUND((SUM(MINIMO.MODA)%86400)/3600,0),2,'0'),'00'),
      ''),':',IFNULL(IFNULL(m2ss.lpad(ROUND(((SUM(MINIMO.MODA)%86400)%3600)/60,0),2,'0'),
      '00'),''),':',IFNULL(IFNULL(m2ss.lpad((((SUM(MINIMO.MODA)%86400)%3600)%60),2,'0'),'00'),
      '')),' ','') INTO p_MODA
	  FROM(SELECT campo1*24*60*60 AS MODA FROM tmp_tabagg group by campo1 order by 1
LIMIT 1) MINIMO;


		SELECT SECCION_MAXIMO_ESPERA(p_SECCION,p_CS) MAXIMO,
		SECCION_MINIMO_ESPERA(p_SECCION,p_CS) MINIMO,
		SECCION_PROMEDIO_ESPERA(p_SECCION,p_CS) PROMEDIO,
		CONVERT(p_MODA, char(1000)) MODA,
		(SELECT COUNT(*) FROM GC_TIQUETE WHERE M_ESTADO=1 AND
		C_UNIDAD_RECEP=p_CS AND N_TRAMITE_ID IN (SELECT N_TRAMITE_ID
		FROM GC_TRAMITE WHERE N_SERVICIOS_ID=p_SECCION) AND DATE_FORMAT(fh_llegada,'%Y-%m-%d')=DATE_FORMAT(NOW(3),'%Y-%m-%d')) ESPERANDO,
		(SELECT COUNT(*) FROM GC_TIQUETE WHERE M_ESTADO=4 AND C_UNIDAD_RECEP=p_CS
		AND N_TRAMITE_ID IN (SELECT N_TRAMITE_ID FROM GC_TRAMITE WHERE N_SERVICIOS_ID=p_SECCION)
		AND DATE_FORMAT(fh_llegada,'%Y-%m-%d')=DATE_FORMAT(NOW(3),'%Y-%m-%d')) PROCESADOS;

	  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-03  9:14:31
