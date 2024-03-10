CREATE DATABASE Biblioteca
GO
USE Biblioteca


CREATE TABLE Biblioteca(
idBi INT PRIMARY KEY IDENTITY,
nume_bi VARCHAR(100) NOT NULL,
oras_bi VARCHAR(20),
strada_bi VARCHAR(30),
numar_bi INT)


CREATE TABLE Bibliotecar(
idB INT PRIMARY KEY IDENTITY,
nume_b VARCHAR(20),
prenume_b VARCHAR(50),
data_nastere_b DATE)


CREATE TABLE Cititor(
idCi INT PRIMARY KEY IDENTITY,
nume_c VARCHAR(20),
prenume_c VARCHAR(50),
data_nastere_c DATE,
oras_c VARCHAR(20),
strada_c VARCHAR(50),
numar_c INT)


CREATE TABLE Sala(
idS INT PRIMARY KEY IDENTITY,
denumire_s VARCHAR(30),
tip_s VARCHAR(20),
nr_locuri INT,
idBi INT FOREIGN KEY REFERENCES Biblioteca(idBi))


CREATE TABLE Autor(
idA INT PRIMARY KEY IDENTITY,
nume_a VARCHAR(20),
prenume_a VARCHAR(30))


CREATE TABLE Sectiune(
idSe INT PRIMARY KEY IDENTITY,
denumire VARCHAR(20))


CREATE TABLE Carte(
idCa INT PRIMARY KEY IDENTITY,
titlu VARCHAR(30),
idA INT FOREIGN KEY REFERENCES Autor(idA),
idSe INT FOREIGN KEY REFERENCES Sectiune(idSe),
nr_exemplare INT)


CREATE TABLE Legitimatie(
idBi INT,
idCi INT,
data_inscriere DATE,
data_expirare DATE, 
CONSTRAINT fk_Biblioteca FOREIGN KEY (idBi) REFERENCES Biblioteca(idBi),
CONSTRAINT fk_Cititor FOREIGN KEY (idCi) REFERENCES Cititor(idCi),
CONSTRAINT pk_Legitimatie PRIMARY KEY (idBi, idCi))


CREATE TABLE Programare(
idCi INT,
idS INT,
zi DATE,
ora TIME,
sf_programare TIME,
CONSTRAINT fk_Cititor1 FOREIGN KEY (idCi) REFERENCES Cititor (idCi),
CONSTRAINT fk_Sala FOREIGN KEY (idS) REFERENCES Sala (idS),
CONSTRAINT pk_Programare PRIMARY KEY (idCi, idS))


CREATE TABLE Interactiune(
idCi INT,
idB INT,
CONSTRAINT fk_Cititor2 FOREIGN KEY (idCi) REFERENCES Cititor (idCi),
CONSTRAINT fk_Bibliotecar FOREIGN KEY (idB) REFERENCES Bibliotecar(idB),
CONSTRAINT pk_Interactiune PRIMARY KEY (idCi, idB))


CREATE TABLE Imprumut_retur(
idCi INT,
idCa INT,
data_imprumut DATE,
data_retur DATE,
CONSTRAINT fk_Cititor3 FOREIGN KEY (idCi) REFERENCES Cititor (idCi),
CONSTRAINT fk_Carte FOREIGN KEY (idCa) REFERENCES Carte (idCa),
CONSTRAINT pk_Imprumut_Retur PRIMARY KEY (idCi, idCa))
