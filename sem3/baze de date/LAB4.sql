INSERT INTO Tables(Name)
VALUES ('Autor'), ('Carte'), ('Imprumut_retur');

SELECT * FROM Tables;

CREATE OR ALTER VIEW View_1 AS
	SELECT idA,nume_a,prenume_a 
	FROM Autor
GO

CREATE OR ALTER VIEW View_2 AS
	SELECT c.titlu FROM Carte c
	INNER JOIN Imprumut_retur i ON c.idCa=i.idCa
GO

CREATE OR ALTER VIEW View_3 AS
	SELECT a.nume_a,a.prenume_a,COUNT(a.idA) AS Cate
	FROM Autor a INNER JOIN Carte c ON a.idA=c.idA
	GROUP BY a.nume_a,a.prenume_a
	HAVING COUNT(a.idA) > 1
GO

INSERT INTO Views(Name)
VALUES ('View_1'), ('View_2'), ('View_3');

INSERT INTO Tests(Name)
VALUES ('delete'), ('insert'), ('select');

INSERT INTO TestViews(TestID,ViewID)
VALUES (3,1), (3,2), (3,3);

SELECT * FROM Tests;

INSERT INTO TestTables(TestID,TableID,NoOfRows,Position)
VALUES (1,1,100,3),(1,2,100,2),(1,3,100,1),(2,1,100,1),(2,2,100,2),(2,3,100,3);

CREATE OR ALTER PROCEDURE insert_1
AS
BEGIN
	DECLARE @NoOFRows int
	DECLARE @n int
	DECLARE @na VARCHAR(20)
	DECLARE @pr VARCHAR(50)
	SELECT TOP 1 @NoOfRows =NoOfRows FROM dbo.TestTables 
	WHERE TableID = 1 AND TestID = 2
	SET @n = 1 
	SET identity_insert Autor ON
	WHILE @n <= @NoOfRows
	BEGIN
		SET @na ='Nume'+CONVERT(VARCHAR(5),@n)
		SET @pr ='Prenume'+CONVERT(VARCHAR(5),@n)
		
		INSERT INTO Autor(idA,nume_a,prenume_a)VALUES (@n,@na,@pr)
		SET @n=@n+1
	END
	SET identity_insert Autor OFF
END;

CREATE OR ALTER PROCEDURE insert_2
AS
BEGIN
	DECLARE @NoOFRows int
	DECLARE @n int
	DECLARE @t VARCHAR(100)
	DECLARE @fk int
	DECLARE @fk1 int
	SELECT TOP 1 @NoOfRows =NoOfRows FROM dbo.TestTables 
	WHERE TableID = 2 AND TestID = 2
	SET identity_insert Carte ON
	SET @n = 1 
	WHILE @n <= @NoOfRows
	BEGIN
		SET @t = 'Titlu' + CONVERT(VARCHAR(5),@n)
		SELECT TOP 1 @fk = idA FROM Autor ORDER BY NEWID()
		SELECT TOP 1 @fk1 = idSe FROM Sectiune ORDER BY NEWID()
		INSERT INTO Carte(idCa,titlu,idA,idSe,nr_exemplare) VALUES (@n,@t,@fk,@fk1,10)
		SET @n=@n+1
	END
	SET identity_insert Carte OFF
END;

CREATE OR ALTER PROCEDURE insert_3
AS
BEGIN
	DECLARE @NoOfRows int
	DECLARE @n int
	DECLARE @fk int
	DECLARE @fk1 int
	DECLARE @c INT
	SELECT TOP 1 @NoOfRows = NoOfRows FROM dbo.TestTables 
	WHERE TableID=3 AND TestID=2
	SET @n = 1 
	WHILE @n <= @NoOfRows
	BEGIN
		SELECT TOP 1 @fk = idCa FROM Carte ORDER BY NEWID()
		SELECT TOP 1 @fk1 = idCi FROM Cititor ORDER BY NEWID()
		SELECT @c=COUNT(idCa) FROM Imprumut_retur p WHERE p.idCa = @fk AND p.idCi = @fk1
		IF @c=0 BEGIN
			INSERT INTO Imprumut_retur(idCi,idCa) VALUES (@fk1,@fk)
		END;
		SET @n=@n+1
	END;
END;

CREATE OR ALTER PROCEDURE OneTest @t INT, @v INT
AS
BEGIN
	DECLARE @ds DATETIME --start timetest
	DECLARE @di DATETIME --intermediate timetest
	DECLARE @de DATETIME --end timetest

	DECLARE @nt NVARCHAR(50)
	DECLARE @nv NVARCHAR(50)
	DECLARE @p INT 
	DECLARE @te INT
	SELECT @nt = Name FROM Tables WHERE TableID = @t
	SELECT @nv = Name FROM Views WHERE ViewID = @v
	SET @ds =GETDATE()
	--SELECT @te = TestID FROM Tests WHERE Name = 'delete'
	--SELECT @p = Position FROM TestTables WHERE TestID = @te AND TableID = @t
	--IF (@p = 3)
	--BEGIN 
		--DELETE FROM Imprumut_retur;
		--DELETE FROM Carte;
		--DELETE FROM Autor;
		--PRINT('delete3')
	--END;
	--IF (@p = 2)
	--BEGIN
		--DELETE FROM Imprumut_retur;
		--DELETE FROM Carte;
		--PRINT('delete2')
	--END;
	--IF (@p = 1)
	--BEGIN
		--DELETE FROM Imprumut_retur;
		--PRINT('delete1')
	--END;
	DELETE FROM Imprumut_retur;
	DELETE FROM Carte;
	DELETE FROM Autor;
	SELECT @te = TestID FROM Tests WHERE Name = 'insert'
	SELECT @p = Position FROM TestTables WHERE TestID = @te AND TableID = @t
	IF (@p = 3)
	BEGIN 
		EXEC insert_1;
		EXEC insert_2;
		EXEC insert_3;
	END;
	IF (@p = 2)
	BEGIN
		EXEC insert_1;
		EXEC insert_2;
	END;
	IF (@p = 1)
	BEGIN
		EXEC insert_1;
	END;
	SET @di=GETDATE()

	IF (@nv = 'View_1')
	BEGIN
		SELECT *FROM View_1
	END;
	IF (@nv = 'View_2')
	BEGIN
		SELECT *FROM View_2
	END;
	IF (@nv = 'View_3')
	BEGIN
		SELECT *FROM View_3
	END;
	SET @de = GETDATE()
	DECLARE @des NVARCHAR(30)
	SET @des = @nt + ' and ' + @nv
	Insert into TestRuns(Description,StartAt,EndAt) VALUES (@des,@ds,@de)
	
	DECLARE @tr INT
	SELECT @tr = TestRunID FROM TestRuns WHERE Description = @des
	Insert into TestRunTables(TestRunID,TableID,StartAt,EndAt) VALUES(@tr,@t,@ds,@di)
	Insert into TestRunViews(TestRunID,ViewID,StartAt,EndAt) VALUES(@tr,@v,@di,@de)
END;

--DELETE FROM Imprumut_retur;
--DELETE FROM Carte;
--DELETE FROM Autor;

--exec insert_1;
--SELECT * FROM Autor;
--exec insert_2;
--SELECT * FROM Carte;
--exec insert_3;
--SELECT * FROM Imprumut_retur;
--SELECT * FROM View_1;
--SELECT * FROM View_2;
--SELECT * FROM View_3;

EXEC OneTest 3,2
SELECT * FROM TestRuns
SELECT * FROM TestRunViews;
SELECT * FROM TestRunTables;
