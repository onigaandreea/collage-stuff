USE Biblioteca

--valori pentru tabela Autori
INSERT INTO Autor(nume_a,prenume_a) 
VALUES ('Shakespeare', 'William'), ('Tolstoi', 'Lev'), ('Austen', 'Jane'), ('Hugo', 'Victor')
INSERT INTO Autor(nume_a,prenume_a) 
VALUES ('Lewis', 'Clive Staples'), ('Andersen', 'Hans Christian'), ('Twain', 'Mark')
INSERT INTO Autor(nume_a,prenume_a) 
VALUES ('Verne', 'Jules'), ('Blaga', 'Lucian'), ('Eminescu', 'Mihai')
INSERT INTO Autor(nume_a,prenume_a) 
VALUES ('Caragiale', 'Ion Luca'), ('Slavici', 'Ioan'), ('Creanga', 'Ion')

--valori pentru tabela Sectiune
INSERT INTO Sectiune(denumire)
VALUES ('Scriitori romani'),('Copii'),('Istorie')
INSERT INTO Sectiune(denumire)
VALUES ('Drama'),('SF'),('Romantice')

--valori pentru tabela Biblioteca
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Judeteana Octavian Goga','Cluj-Napoca','Calea Dorobantilor',104)
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Centrala Universitara Lucian Blaga','Cluj-Napoca','Clinicilor',2)
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Academiei Romane','Cluj-Napoca','Mihail Kogalniceanu',13)
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Judeteana Lucian Blaga','Alba Iulia','Trandafirilor',22)
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Judeteana Astra','Sibiu','George Baritiu',7)
INSERT INTO Biblioteca(nume_bi,oras_bi,strada_bi,numar_bi)
VALUES ('Biblioteca Central Universitara','Sibiu','Lucian Blaga',2)


--valori pentru tabela Bibliotecar 
INSERT INTO Bibliotecar(nume_b,prenume_b,data_nastere_b)
VALUES ('Ardelean','Victor','1975-05-27'),('Badea','Elena','1970-02-03')
INSERT INTO Bibliotecar(nume_b,prenume_b,data_nastere_b)
VALUES ('Fernea','Ioana','1986-07-30'),('Lorin','Adrian','1983-03-17')


--valori pt Sala
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala1',30,'lectura',1),('Sala2',30,'computer',1),('Sala Lectura',50,'lectura',1)
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala1',35,'lectura',2),('Sala Matrix',40,'computer',2),('Sala Lectura',50,'lectura',2)
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala1',20,'lectura',3),('Sala2',15,'computer',3)
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala1',40,'lectura',4),('Sala2',20,'computer',4)
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala Mare',50,'lectura',6),('Sala Calc',30,'computer',6),('Sala Lectura',40,'lectura',6)
INSERT INTO Sala(denumire_s,nr_locuri,tip_s,idBi)
VALUES ('Sala011',25,'lectura',5),('Sala007',20,'computer',5)


--Valori Cititori
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Albu','Angela','1997-12-01','Botosani','Calea Nationala',1)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Vianu','Mirela','2002-07-26','Sibiu','Bulevardul Victoriei',12)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Apostol','Ilinca','1996-06-11','Mures','Trandafirilor',53)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Cojocaru','Teodor','2001-10-08','Sibiu','Moara de Scoarta',40)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Zamfir','Eduard','1998-02-10','Roman','Profesor Dumitru Martinas',4)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Vladimirescu','Toma','1999-12-12','Cluj-Napoca','Al. Peana',3)

--valori Carte
INSERT INTO Carte(titlu,idA,idSe,nr_exemplare)
VALUES ('Poezii',9,1,20),('Poezii',10,1,25),('Momente si schite',11,1,20),('Povesti si povestiri',13,1,25)
INSERT INTO Carte(titlu,idA,idSe,nr_exemplare)
VALUES ('Moara cu noroc',12,1,15),('Mara',12,1,10),('Narnia Vol 1',5,2,10),('Povesti',6,2,30)
INSERT INTO Carte(titlu,idA,idSe,nr_exemplare)
VALUES ('Romeo si Julieta',1,6,13),('Mandrie si prejudecata',3,6,8),('Calatorie spre centrul pamantului',8,5,10)


--valori Imprumut_retur
INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
VALUES (1,1,'2021-12-20','2022-01-05')
INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
VALUES (4,17,'2022-05-08','2022-05-19'),(3,16,'2022-07-16','2022-07-26')
INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
VALUES (2,8,'2017-03-06','2017-03-12'),(5,7,'2013-08-19','2013-08-31')
INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
VALUES (1,15,'2022-02-02','2022-02-09')

--valori interactiune
INSERT INTO Interactiune(idCi,idB)
VALUES (1,1),(1,2),(3,4),(5,3)

--valori legitimatie
INSERT INTO Legitimatie(idBi,idCi,data_inscriere,data_expirare)
VALUES (2,5,'2022-01-03','2023-01-03'),(4,6,'2021-01-08','2022-01-08'),(3,3,'2022-08-15','2023-08-15')
INSERT INTO Legitimatie(idBi,idCi,data_inscriere,data_expirare)
VALUES (1,1,'2022-04-07','2023-04-07'),(5,3,'2021-09-19','2022-09-19'),(6,2,'2022-12-13','2023-12-13')

--valori programare
INSERT INTO Programare(idCi,idS,zi,ora,sf_programare)
VALUES (4,14,'2021-12-18','13:00:00','14:30:00'),(3,9,'2022-11-04','11:00:00','13:00:00'),(1,2,'2022-05-17','14:15:00','16:15:00')
INSERT INTO Programare(idCi,idS,zi,ora,sf_programare)
VALUES (6,8,'2023-02-02','16:00:00','17:00:00'),(5,3,'2021-10-20','15:30:00','17:00:00'),(2,11,'2022-01-14','15:00:00','16:00:00')

SELECT * FROM Autor
SELECT * FROM Biblioteca
SELECT * FROM Bibliotecar
SELECT * FROM Carte
SELECT * FROM Cititor
SELECT * FROM Imprumut_retur
SELECT * FROM Interactiune
SELECT * FROM Legitimatie
SELECT * FROM Programare
SELECT * FROM Sala
SELECT * FROM Sectiune

INSERT INTO Interactiune(idCi,idB)
VALUES (2,1)
INSERT INTO Legitimatie(idBi,idCi,data_inscriere,data_expirare)
VALUES (2,1,'2022-05-04','2023-05-04')

INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
VALUES (2,16,'2022-10-20','2022-11-04')

--afiseaza bibliotecile si legitimatiile unui anumit cititor
SELECT c.nume_c,c.prenume_c,b.nume_bi,l.data_inscriere 
FROM Cititor c INNER JOIN Legitimatie l ON c.idCi=l.idCi
INNER JOIN Biblioteca b ON l.idBi=b.idBi
WHERE c.nume_c='Albu'

--cititorii si titlurile cartilor pe care le au imprumutat
SELECT c.nume_c,c.prenume_c,ca.titlu,i.data_imprumut
FROM Cititor c INNER JOIN Imprumut_retur i on c.idCi=i.idCi
INNER JOIN Carte ca on i.idCa=ca.idCa

--autorii care au cel putin 2 carti
SELECT a.nume_a,a.prenume_a,COUNT(a.idA) AS Cate
FROM Autor a INNER JOIN Carte c ON a.idA=c.idA
INNER JOIN Sectiune s ON c.idSe=s.idSe
GROUP BY a.nume_a,a.prenume_a
HAVING COUNT(a.idA)=2

--Cati cititorii au programare in fiecare biblioteca
SELECT b.nume_bi,b.oras_bi,COUNT(c.idCi) AS Cati
FROM Cititor c INNER JOIN Programare p ON c.idCi=p.idCi
INNER JOIN Sala s ON P.idS=s.idS
INNER JOIN Biblioteca b ON s.idBi=b.idBi
GROUP BY b.nume_bi,b.oras_bi
HAVING COUNT(c.idCi)>=1

--cu cati cititori a interactionat fiecare bibliotecar
SELECT b.idB,b.nume_b,b.prenume_b,COUNT(i.idCi) as cati
FROM Bibliotecar b INNER JOIN Interactiune i on b.idB=i.idB
INNER JOIN Cititor c on i.idCi=c.idCi
GROUP BY b.idB,b.nume_b,b.prenume_b

--cartile si autorii care apartin sectiunii cu idSe=1
SELECT c.titlu,a.nume_a,a.prenume_a,s.denumire
FROM Autor a INNER JOIN Carte c ON a.idA=c.idA
INNER JOIN Sectiune s ON c.idSe=s.idSe
WHERE s.idSe=1

--sectiunile distincte pe care le avem
SELECT DISTINCT c.idSe, s.denumire
FROM Carte c INNER JOIN Sectiune s ON c.idSe=s.idSe

--cititorii care au imprumutat cartea cu idCa=16
SELECT c.nume_c,c.prenume_c,ca.titlu,i.data_imprumut
FROM Carte ca INNER JOIN Imprumut_retur i ON ca.idCa=i.idCa
INNER JOIN Cititor c ON i.idCi=c.idCi
WHERE ca.idCa=16

--cititorii care au legitimatii la biblioteci din Cluj-Napoca
SELECT c.nume_c,c.prenume_c,b.nume_bi,l.data_inscriere
FROM Cititor c INNER JOIN Legitimatie l ON c.idCi=l.idCi
INNER JOIN Biblioteca b ON b.idBi=l.idBi
WHERE b.oras_bi='Cluj-Napoca'

--cartile cu autori ale caror nume incepe cu litera C
SELECT c.titlu,a.nume_a,a.prenume_a,s.denumire
FROM Autor a INNER JOIN Carte c ON a.idA=c.idA
INNER JOIN Sectiune s ON c.idSe=s.idSe
WHERE a.nume_a LIKE 'C%'

--cititorii care au cel putin o carte imprumutata
SELECT DISTINCT i.idCi, c.nume_c,c.prenume_c
FROM Cititor c INNER JOIN Imprumut_retur i ON c.idCi=i.idCi