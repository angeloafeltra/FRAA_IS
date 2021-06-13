DROP DATABASE IF EXISTS MedQueue;
CREATE DATABASE MedQueue;
USE MedQueue;

CREATE TABLE Utente(               
codiceFiscale varchar(16) not null,
password varchar(50) not null,
nome varchar(50) not null,
cognome varchar(50) not null,
dataDiNascita date not null,
indirizzoEmail varchar(255) not null,
numeroDiTelefono varchar(14) not null,
primary key(CodiceFiscale) );

CREATE TABLE Struttura(
Id int auto_increment not null,
nome varchar(50) not null,
indirizzo varchar(100) not null,
numeroDiTelefono long not null,
primary key(id) );

CREATE TABLE Impiegato(
codiceFiscale varchar(16) not null,
password varchar(50) not null,
nome varchar(50) not null,
cognome varchar(50) not null,
dataDiNascita date not null,
indirizzoEmail varchar(255) not null,
numeroDiTelefono varchar(14) not null,
idStruttura int not null,
primary key(CodiceFiscale) ,
foreign key(idStruttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Operazione(
Id int auto_increment not null,
tipoOperazione varchar(50) not null,
descrizione varchar(255) not null,
primary key(id) );


CREATE TABLE Prenotazione(
Id int auto_increment not null,
data date not null,
ora time not null,
convalida bool not null,
codiceFiscale varchar(16) not null,
idOperazione int not null,
idStruttura int not null,
primary key(Id),
foreign key(codiceFiscale) references Utente(codiceFiscale) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(idOperazione) references Operazione(Id) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(idStruttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE );

CREATE TABLE Ambulatorio(
Id int auto_increment not null,
nome varchar(50) not null,
idStruttura int not null,
primary key(Id),
foreign key(idStruttura) references Struttura(Id) ON UPDATE CASCADE ON DELETE CASCADE );


INSERT INTO Struttura(nome, indirizzo, numeroDiTelefono) VALUES
('santobono','Via della Croce Rossa n. 8 Napoli (NA)','0812205111'),
('San Leonardo','Viale Europa n.8 Castellammare di Stabia NA','081872911'),
('Da Rimuovere Dao', 'Da rimuovere dao 8', '0812205111'),
('Da Rimuovere Controller', 'Da rimuovere Controller 8', '0812205111'),
('San Luca', 'Via Squinzi, Vallo Della Lucania', '0812205111');

INSERT INTO Impiegato VALUES
('FLTNGL99A14L845J','angelo99','angelo','afeltra','1999/01/14','a.afeltra12@studenti.unisa.it','3394487295',1),
('FCLNDR99C12B963C','andrea99','andrea','fucile','1999/03/12','andrea.fucile@studenti.unisa.it','3394487394',1),
('RPAGNN95D11A509B','givaa22','giovanni','rapa','1995/01/16','g.rapa95@gmail.com','3394487293', 1),
('ADRAMT99D13A587J','amato99','adriano','amato','1999/07/23','amatoadriano@gmail.com','3457892239',2);


INSERT INTO Operazione(tipoOperazione, Descrizione) VALUES
('Pagamento Ticket','Pagamento Ticket per visita medica'),
('Prenotazione Ambulatorio','Richiesta prenotazione ambulatorio'),
('Da Rimuovere Dao', 'Da rimuovere dao'),
('Da Rimuovere Controller', 'Da rimuovere Controller');


INSERT INTO Utente VALUES
('MNDCMN97R22A509S','carmine97!','Carmine','Amendola','1997/10/22','carmine.amendola@gmail.com','3394787295'),
('CCCNTN98H02F839V','antonio98','Antonio','Cacciapuoti','1998/06/02','antonio.cacc@gmail.com','3545253226'),
('CRLNTN92S15H703Q','antonioc','Antonio','Cirillo','1992/11/15','a.cirilli@docenti.unisa.it','3545251111'),
('DRGMRA99D09A509V','mario99','Mario','De Riggi','1999/04/09','mario.deriggi@gmail.com','3435678901'),
('SQLRFL97R10F839C','raff97','Raffaele','Squillante','1997/10/10','raffaele.sq@gmail.com','3789292020'),
('SQLRFL97R10F839D','raff98','Raffaele','Squillante','1998/10/10','raffaele.sq@gmail.com','3789292021'),
('CRLNTN92S15H703Z', 'Crill1!', 'Antonio', 'Cirillo', '2000/10/10', 'cirilloantonio@gmail.com', '3789292221'),
('MNDCMN97R22A509Y','carmine97!','Carmine','Amendola','1997/10/22','carmine.amendola@gmail.com','3394787295'),
('CNDVKM62S23B586F', 'Ciao123!', 'Carmine', 'Iaucci', '2000/02/02', 'sonoscarsoarainbow@gmail.com', '1234567890');

INSERT INTO Ambulatorio(nome, idStruttura) VALUES
('Ortopedia','1'),
('Gineconologia','2'),
('Geriatria','1'),
('Medicina Legale','1'),
('Neurologia','2'),
('Radiologia','1'),
('Pediatria','1'),
('Oncologia','2'),
('Cardiologia','1'),
('RimuoviD', '1'),
('RimuoviC', '1');


INSERT INTO Prenotazione(data, ora, convalida, codiceFiscale, idOperazione, idStruttura) VALUES
('2021-03-22','12:30:00','1','MNDCMN97R22A509S','1','1'),
('2021-03-22','12:15:00','1','CCCNTN98H02F839V','1','1'),
('2021-03-22','12:00:00','1','CRLNTN92S15H703Q','2','1'),
('2021-03-22','11:45:00','1','CRLNTN92S15H703Q','2','2'),
('2021-03-22','11:30:00','1','SQLRFL97R10F839D','1','2'),
('2021-03-22','10:30:00','0','MNDCMN97R22A509S','1','2'),
('2021-03-22','9:30:00','1','SQLRFL97R10F839D','1','1'),
('2021-03-22','12:30:00','1','CRLNTN92S15H703Q','2','1'),
('2021-03-23','11:00:00','1','CCCNTN98H02F839V','1','1'),
('2021-01-23','11:00:00','1','CCCNTN98H02F839V','1','1');
