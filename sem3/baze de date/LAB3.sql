CREATE TABLE Version(
ID INT PRIMARY KEY IDENTITY,
crt_version INT);

INSERT INTO Version(crt_version)
VALUES (0);

SELECT * FROM Version;

--procedura care modifica tipul unei coloane
CREATE PROCEDURE ModifyTypeOfColumn
AS
BEGIN
ALTER TABLE Bibliotecar
ALTER COLUMN prenume_b VARCHAR(30);
END;

--am modificat procedura pentru a adauga un mesaj:)
ALTER PROCEDURE ModifyTypeOfColumn
AS
BEGIN
ALTER TABLE Bibliotecar
ALTER COLUMN prenume_b VARCHAR(30);
PRINT N'S-a modificat coloana prenume_b a tabelului Bibliotecar din VARCHAR(50) in VARCHAR(30)!';
END;

--procedura care va face UNDO la procedura anterioara si atribuie tipul anterior coloanei pe care am modificat-o cu prima procedura
CREATE PROCEDURE UndoModifyTypeOfColumn
AS
BEGIN
ALTER TABLE Bibliotecar
ALTER COLUMN prenume_b VARCHAR(50);
PRINT N'S-a facut UNDO la modificarea tipului de coloana!';
END;

--procedura prin care se adauga o constrangere default pe una din coloanele unui tabel
CREATE PROCEDURE AddDefaultConstraint
AS
BEGIN
ALTER TABLE Carte
ADD CONSTRAINT df_nr_exemplare_min DEFAULT 5 FOR nr_exemplare;
PRINT N'S-a adaugat o constrangere default in tabelul Carte, in coloana nr_exemplare!';
END;

--procedura care face UNDO anterioarei si sterge constrangerea default
CREATE PROCEDURE RemoveDefaultConstraint
AS
BEGIN
ALTER TABLE Carte
DROP CONSTRAINT df_nr_exemplare_min;
PRINT N'S-a sters constrangerea default!';
END;

--procedura prin care se creaza un nou tabel
CREATE PROCEDURE CreateTable
AS
BEGIN
CREATE TABLE Editura(
idE INT PRIMARY KEY IDENTITY,
denumire_e VARCHAR(20));
PRINT N'S-a creat un nou tabel, Editura!';
END;

--procedura care face UNDO anterioarei si sterge tabelul nou creat
CREATE PROCEDURE RemoveTable
AS
BEGIN
DROP TABLE Editura;
PRINT N'S-a sters tabelul Editura!';
END;

--procedura prin care se adauga o noua coloana intr-un tabel
CREATE PROCEDURE AddColumn
AS
BEGIN
ALTER TABLE Carte
ADD idE INT;
PRINT N'S-a adaugat coloana idE in tabelul Carte!';
END;

--procedura prin care se face UNDO anterioarei si se sterge coloana adaugata
CREATE PROCEDURE RemoveColumn
AS
BEGIN
ALTER TABLE Carte
DROP COLUMN idE;
PRINT N'S-a sters coloana idE din tabelul Carte!';
END;

--procedura prin care se adauga o chiei straina intr-un tabel
CREATE PROCEDURE CreateForeignKey
AS
BEGIN
ALTER TABLE Carte
ADD CONSTRAINT fk_editura FOREIGN KEY(idE) REFERENCES Editura(idE);
PRINT N'S-a adaugat o cheie straina in tabelul Carte ce refera tabelul Editura!';
END;

--procedura prin care se face UNDO anterioarei si se sterge cheiea straina adaugata prin apelarea procedurii precedente
CREATE PROCEDURE RemoveForeignKey
AS
BEGIN
ALTER TABLE Carte
DROP CONSTRAINT fk_editura;
PRINT N'S-a sters constrangerea de cheie straina pentru coloana idE!';
END;

--procedura prin care actualizam versiunea
CREATE PROCEDURE UpdateVersion @nr_versiune_noua INT
AS
BEGIN
UPDATE Version SET crt_version=@nr_versiune_noua WHERE ID=1; 
PRINT N'S-a actializat versiunea!';
END;

--procedura main care va face trecerea de la versiunea actuala la versiunea data ca si parametru
CREATE PROCEDURE Main @version_nr INT
AS
BEGIN
DECLARE @crt_vers AS INT;
SELECT @crt_vers=crt_version FROM Version WHERE ID=1;
IF ((@version_nr >= 0) AND (@version_nr <= 5))
BEGIN
	IF (@crt_vers < @version_nr)
	BEGIN
		IF ((@crt_vers = 0) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC ModifyTypeOfColumn;
			EXEC UpdateVersion 1;
			SET @crt_vers = 1;
		END;
		IF ((@crt_vers = 1) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC AddDefaultConstraint;
			EXEC UpdateVersion 2;
			SET @crt_vers = 2;
		END;
		IF ((@crt_vers = 2) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC CreateTable;
			EXEC UpdateVersion 3;
			SET @crt_vers = 3;
		END;
		IF ((@crt_vers = 3) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC AddColumn;
			EXEC UpdateVersion 4;
			SET @crt_vers = 4;
		END;
		IF ((@crt_vers = 4) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC CreateForeignKey;
			EXEC UpdateVersion 5;
			SET @crt_vers = 5;
		END;
	END;
	ELSE
	BEGIN
		IF ((@crt_vers = 5) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC RemoveForeignKey;
			EXEC UpdateVersion 4;
			SET @crt_vers = 4;
		END;
		IF ((@crt_vers = 4) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC RemoveColumn;
			EXEC UpdateVersion 3;
			SET @crt_vers = 3;
		END;
		IF ((@crt_vers = 3) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC RemoveTable;
			EXEC UpdateVersion 2;
			SET @crt_vers = 2;
		END;
		IF ((@crt_vers = 2) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC RemoveDefaultConstraint;
			EXEC UpdateVersion 1;
			SET @crt_vers = 1;
		END;
		IF ((@crt_vers = 1) AND (@crt_vers != @version_nr))
		BEGIN
			EXEC UndoModifyTypeOfColumn;
			EXEC UpdateVersion 0;
			SET @crt_vers = 0;
		END;
	END;
END;
ELSE
PRINT N'Numarul de versiune nu este corect, versiunea trebuie sa fie un numar intreg intre 0 si 5, inclusiv!';
END;
