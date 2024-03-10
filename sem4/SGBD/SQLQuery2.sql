create database S6
go
use S6
go

create table Echipe(
Eid int primary key identity,
Denumire varchar(50),
NrMembrii int)

create table TipuriPremii(
Tid int primary key identity,
Tip varchar(50),
Descriere varchar(50))

create table Premii(
Pid int primary key identity,
Denumire varchar(50),
Descriere varchar(50),
Valoare int,
Eid int foreign key references Echipe(Eid),
Tid int foreign key references TipuriPremii(Tid))

create table Concursuri(
Cid int primary key identity,
Denumire varchar(50) NOT NULL,
Descriere varchar(50),
DataIncepere date,
DataFinalizare date,
Taxa int)

create table Participari(
Eid int foreign key references Echipe(Eid),
Cid int foreign key references Concursuri(Cid),
DataInscriere date,
NrPuncteAcumulate int,
constraint pk_Participari primary key(Eid, Cid))

-- 1-n: Echipe-Premii

select * from Echipe
select * from TipuriPremii
select * from Premii
select * from Concursuri
select * from Participari

insert into Echipe values('Echipa Pingunilor', 4),
('Echipa Frunzarilor', 10)

insert into TipuriPremii values('Compensare', 'Premii de bucurii compensatorii'),
('Donatii', 'Premii in daruri')

insert into Premii values('II', 'de participare', 0, 1, 1), 
('De bucurie', 'sa aducem un zambet impreuna', 200, 1, 2 )

insert into Concursuri values('Construim vise de carton', 'descriere 1', '12/10/2022', '12/12/2022',35), 
('Alergare cu premii', 'descriere 2', '03/04/2023', '12/04/2023', 0)

insert into Participari values(1, 1, '07/05/2022', 5),
(2, 2, '11/02/2023', 30)


--Rau
INSERT INTO Echipe VALUES ('Echipa Pelicanilor', 7)
	BEGIN TRAN
		WAITFOR DELAY '00:00:10'
		UPDATE Echipe SET Denumire = 'Trans1' where NrMembrii=7
		COMMIT TRAN
		select 'transaction commited'


-- Rezolvat
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
	INSERT INTO Echipe VALUES ('Echipa Pelicanilor', 9)
	BEGIN TRAN
		WAITFOR DELAY '00:00:10'
		UPDATE Echipe SET Denumire = 'Trans1' where NrMembrii=9
		COMMIT TRAN
		select 'transaction commited'

CREATE NONCLUSTERED INDEX N_idx_Echipe_nrMembrii ON Echipe(NrMembrii);

Select * from Echipe Order By NrMembrii
