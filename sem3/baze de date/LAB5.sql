-- folosim carte, imprumut_retur si cititor

INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Albu','Sanzaiana','1999-11-20','Cluj-Napoca','Calea Nationala',1)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Vianu','Mihai','2005-07-26','Sibiu','Bulevardul Victoriei',10)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Apostolu','Bianca','2010-06-11','Cluj-Napoca','Trandafirilor',53)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Cojocariu','Teodora','2003-10-21','Sibiu','Moara de Scoarta',45)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Morarescu','Emanuela','1996-02-10','Cluj-Napoca','Profesor Dumitru Martinas',7)
INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
VALUES ('Vlaicu','Maria','2000-10-10','Cluj-Napoca','Al. Peana',7)

CREATE OR ALTER PROCEDURE CRUD_Carte
	@titlu VARCHAR(100), @nr_exemplare INT
AS
BEGIN
	IF (dbo.ValidCarte(@nr_exemplare, @titlu) = 0)
	BEGIN
		PRINT 'Datele nu sunt valide pentru tabelul Carte!'
		RETURN
	END;
	ELSE
	BEGIN
	--insert
		DECLARE @idA INT
		DECLARE @idSe INT
		SELECT TOP 1 @idA = idA FROM Autor ORDER BY NEWID()
		SELECT TOP 1 @idSe = idSe FROM Sectiune ORDER BY NEWID()
		INSERT INTO Carte(titlu,idA,idSe,nr_exemplare) VALUES(@titlu,@idA,@idSe,@nr_exemplare)
		SELECT TOP 1 @idA = idA FROM Autor ORDER BY NEWID()
		SELECT TOP 1 @idSe = idSe FROM Sectiune ORDER BY NEWID()
		INSERT INTO Carte(titlu,idA,idSe,nr_exemplare) VALUES(@titlu,@idA,@idSe,@nr_exemplare)

	--select
		SELECT * FROM Carte;
	--update
		UPDATE Carte SET nr_exemplare = 20
		WHERE idSe = 1
	--delete
		DELETE FROM Carte
		WHERE titlu = @titlu

		PRINT 'CRUD operations for table Carte'
	END;
END;

CREATE OR ALTER PROCEDURE CRUD_Cititor
	@nume VARCHAR(20), @prenume VARCHAR(50),
	@data_n DATE, @oras VARCHAR(20),
	@str VARCHAR(50), @nr INT
AS
BEGIN
	IF (dbo.ValidCititor(@nume,@prenume) = 0)
	BEGIN
		PRINT 'Datele nu sunt valide pentru tabelul Cititor!'
		RETURN
	END;
	ELSE
	BEGIN
	--insert
		INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
		VALUES(@nume, @prenume,@data_n,@oras,@str,@nr)
		INSERT INTO Cititor(nume_c,prenume_c,data_nastere_c,oras_c,strada_c,numar_c)
		VALUES(@nume, @prenume,@data_n,@oras,@str,@nr + 5)

	--select
		SELECT * FROM Cititor;
	--update
		UPDATE Cititor SET oras_c = 'Iasi'
		WHERE nume_c LIKE 'A%'
	--delete
		DELETE FROM Cititor
		WHERE nume_c = @nume AND prenume_c = @prenume

		PRINT 'CRUD operations for table Cititor'
	END;
END;

CREATE OR ALTER PROCEDURE CRUD_Imprumut_retur
	@data_imprumut DATE, @data_retur DATE
AS
BEGIN
	IF (dbo.ValidImprumut_retur(@data_imprumut) = 0)
	BEGIN
		PRINT 'Datele nu sunt valide pentru tabela Imprumut_retur!'
		RETURN
	END;
	ELSE
	BEGIN
	--insert
		DECLARE @fk INT
		DECLARE @fk1 INT
		SELECT TOP 1 @fk = idCi FROM Cititor ORDER BY NEWID()
		SELECT TOP 1 @fk1 = idCa FROM Carte ORDER BY NEWID()
		INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
		VALUES(@fk,@fk1,@data_imprumut,@data_retur)
		SELECT TOP 1 @fk = idCi FROM Cititor ORDER BY NEWID()
		SELECT TOP 1 @fk1 = idCa FROM Carte ORDER BY NEWID()
		INSERT INTO Imprumut_retur(idCi,idCa,data_imprumut,data_retur)
		VALUES(@fk,@fk1,@data_imprumut,@data_retur)
	--select
		SELECT * FROM Imprumut_retur;
	--update
		UPDATE Imprumut_retur SET data_retur = GETDATE()
		WHERE idCi = 5
	--delete
		DELETE FROM Imprumut_retur
		WHERE data_imprumut = @data_imprumut AND data_retur = @data_retur

		PRINT 'CRUD operations for table Imprumut_retur'
	END;
END;

SELECT * FROM Imprumut_retur

CREATE FUNCTION ValidCarte(@nr_ex INT,@titlu VARCHAR(100))
RETURNS INT 
AS 
BEGIN
	DECLARE @r int;
	IF (@nr_ex < 10 OR @titlu IS NULL) SET @r = 0;
	ELSE SET @r = 1;
	RETURN @r; 
END;

CREATE FUNCTION ValidCititor(@nume VARCHAR(20),@prenume VARCHAR(50))
RETURNS INT 
AS 
BEGIN
	DECLARE @r int;
	IF (@nume IS NULL OR @prenume IS NULL) SET @r = 0;
	ELSE SET @r = 1;
	RETURN @r; 
END;

CREATE FUNCTION ValidImprumut_retur(@data_imprumut DATE)
RETURNS INT 
AS 
BEGIN
	DECLARE @r int;
	IF (@data_imprumut IS NULL) SET @r = 0;
	ELSE SET @r = 1;
	RETURN @r; 
END;

SELECT * FROM Carte;
SELECT * FROM Cititor;
SELECT * FROM Imprumut_retur;

CREATE OR ALTER VIEW View_cititor AS
	SELECT nume_c,prenume_c,oras_c FROM Cititor
	WHERE oras_c = 'Cluj-Napoca'
GO

CREATE OR ALTER VIEW View_carte AS
	SELECT c.titlu,c.idA FROM Carte c
	INNER JOIN Imprumut_retur i ON c.idCa=i.idCa
	WHERE c.idA < 40
GO

CREATE OR ALTER PROCEDURE lab5
	@titlu VARCHAR(100), @nr_exemplare INT,
	@nume VARCHAR(20), @prenume VARCHAR(50),
	@data_n DATE, @oras VARCHAR(20),
	@str VARCHAR(50), @nr INT,
	@data_imprumut DATE, @data_retur DATE
AS
BEGIN
	EXEC CRUD_Carte @titlu, @nr_exemplare;
	EXEC CRUD_Cititor @nume, @prenume, @data_n, @oras, @str,@nr;
	EXEC CRUD_Imprumut_retur @data_imprumut, @data_retur;
END;

CREATE NONCLUSTERED INDEX idx_Carte_idAutor ON Carte(idA);
GO

CREATE NONCLUSTERED INDEX idx_Cititor_prenume ON Cititor(prenume_c);
GO

IF EXISTS(SELECT name FROM sys.indexes WHERE name=N'idx_Cititor_oras')DROP INDEX idx_Cititor_oras ON Cititor;
GO

CREATE NONCLUSTERED INDEX idx_Imprumut_idCarte ON Imprumut_retur(idCa);
GO

SELECT * FROM View_carte
ORDER BY idA

SELECT * FROM View_cititor
ORDER BY prenume_c

EXEC lab5 'Mara',15,'Ionescu','Maria','2000-06-10','Cluj-Napoca','Motilor',20,'2021-12-20','2022-01-10'
EXEC lab5 NULL,5,'Ionescu','Maria','2000-06-10','Cluj-Napoca','Motilor',20,'2021-12-20','2022-01-10'
EXEC lab5 'Mara',15,NULL,NULL,'2000-06-10','Cluj-Napoca','Motilor',20,'2021-12-20','2022-01-10'
EXEC lab5 'Mara',15,'Ionescu','Maria','2000-06-10','Cluj-Napoca','Motilor',20,NULL,'2022-01-10'
EXEC lab5 NULL,5,NULL,NULL,'2000-06-10','Cluj-Napoca','Motilor',20,NULL,'2022-01-10'