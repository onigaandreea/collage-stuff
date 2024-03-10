CREATE OR ALTER FUNCTION ValidareNumar
(
    @Numar int
)
RETURNS bit
AS
BEGIN
    DECLARE @Result bit

    IF @Numar IS NULL
        set @Result = 1
    ELSE IF @Numar < 0 OR @Numar > 300
        set @Result =1
    ELSE
        SET @Result = 0

    RETURN @Result
END
GO

CREATE OR ALTER FUNCTION ValidareData
(
    @Data date
)
RETURNS bit
AS
BEGIN
    DECLARE @Result bit

    IF @Data IS NULL
        set @Result = 1
    ELSE IF @Data < '1920-01-01' OR @Data > '2011-12-31'
        set @Result = 1
    ELSE
        SET @Result = 0

    RETURN @Result
END
GO


CREATE OR ALTER PROCEDURE InteractiuneCititorBibliotecar
(
   @nume_cititor VARCHAR(20),
   @prenume_cititor  VARCHAR(50),
   @data_nastere_cititor DATE,
   @oras VARCHAR(20),
   @strada VARCHAR(50),
   @numar INT,
   @nume_bibliotecar VARCHAR(20),
   @prenume_bibliotecar  VARCHAR(30),
   @data_nastere_bibliotecar DATE
)
AS
BEGIN
   SET NOCOUNT ON;
   DECLARE @Error int = 0;

   BEGIN TRANSACTION
	BEGIN TRY
		IF dbo.ValidareNumar(@numar) = 1 
		BEGIN
			RAISERROR('Numarul trebuie sa fie pozitiv si mai mic decat 300',14,1)
		END
		IF dbo.ValidareData(@data_nastere_cititor) = 1 
		BEGIN
			RAISERROR('Data trebuie sa fie intre 1920-01-01 si 2011-12-31',14,1)
			END
		IF dbo.ValidareData(@data_nastere_bibliotecar) = 1 
		BEGIN
			RAISERROR('Data trebuie sa fie intre 1920-01-01 si 2011-12-31',14,1)
			END
		

		-- Inserăm un cititor
		INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c,strada_c, numar_c) 
		VALUES (@nume_cititor, @prenume_cititor, @data_nastere_cititor, @oras, @strada, @numar)
		
		-- Inserăm un bibliotecar
		INSERT INTO Bibliotecar (nume_b, prenume_b, data_nastere_b) 
		VALUES (@nume_bibliotecar, @prenume_bibliotecar,@data_nastere_bibliotecar)

		DECLARE @id_cititor INT;
		SET @id_cititor = (SELECT MAX(idCi) FROM Cititor);
		DECLARE @id_bibliotecar INT;
		SET @id_bibliotecar = (SELECT MAX(idB) FROM Bibliotecar);

		-- Inserăm înregistrarea în tabelul de legătură Interactiune
		INSERT INTO Interactiune(idCi, idB) 
		VALUES (@id_cititor, @id_bibliotecar)

		COMMIT TRANSACTION
		SELECT 'Transaction committed'
		
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
		SELECT 'Transaction rollback'

		DECLARE @ErrorMessage nvarchar(4000) = ERROR_MESSAGE(),
		@ErrorSeverity int = ERROR_SEVERITY(),
		@ErrorState int = ERROR_STATE();

		RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
	END CATCH
	END
GO

CREATE OR ALTER PROCEDURE InteractiuneCititorBibliotecar2
(
   @nume_cititor VARCHAR(20),
   @prenume_cititor  VARCHAR(50),
   @data_nastere_cititor DATE,
   @oras VARCHAR(20),
   @strada VARCHAR(50),
   @numar INT,
   @nume_bibliotecar VARCHAR(20),
   @prenume_bibliotecar  VARCHAR(30),
   @data_nastere_bibliotecar DATE
)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION

   -- Inserăm un cititor
	BEGIN TRY
		IF dbo.ValidareNumar(@numar) = 1 
		BEGIN
			RAISERROR('Numarul trebuie sa fie pozitiv si mai mic decat 300',14,1)
			END
		IF dbo.ValidareData(@data_nastere_cititor) = 1 
		BEGIN
			RAISERROR('Data trebuie sa fie intre 1920-01-01 si 2011-12-31',14,1)
			END


		INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c,strada_c, numar_c) 
		VALUES (@nume_cititor, @prenume_cititor, @data_nastere_cititor, @oras, @strada, @numar)
		
		COMMIT TRANSACTION
		SELECT 'Transaction (cititor) committed'
		
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
		
		DECLARE @ErrorMessage nvarchar(4000) = ERROR_MESSAGE(),
		@ErrorSeverity int = ERROR_SEVERITY(),
		@ErrorState int = ERROR_STATE();

		RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
		SELECT 'Transaction (cititor) rollback'
	END CATCH

	BEGIN TRANSACTION

   -- Inserăm un bibliotecar
	BEGIN TRY
		IF dbo.ValidareData(@data_nastere_bibliotecar) = 1 
		BEGIN
			RAISERROR('Data trebuie sa fie intre 1920-01-01 si 2011-12-31',14,1)
			END

		INSERT INTO Bibliotecar (nume_b, prenume_b, data_nastere_b) 
		VALUES (@nume_bibliotecar, @prenume_bibliotecar,@data_nastere_bibliotecar)

		COMMIT TRANSACTION
		SELECT 'Transaction (bibliotecar) committed'
		
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION
		
		DECLARE @ErrorMessage1 nvarchar(4000) = ERROR_MESSAGE(),
		@ErrorSeverity1 int = ERROR_SEVERITY(),
		@ErrorState1 int = ERROR_STATE();

		RAISERROR (@ErrorMessage1, @ErrorSeverity1, @ErrorState1);
		SELECT 'Transaction (bibliotecar) rollback'

	END CATCH

	BEGIN TRANSACTION

   -- Inserăm in tabelul de legatura
	BEGIN TRY

		DECLARE @id_cititor INT;
		SET @id_cititor = (SELECT MAX(idCi) FROM Cititor);
		DECLARE @id_bibliotecar INT;
		SET @id_bibliotecar = (SELECT MAX(idB) FROM Bibliotecar);

		-- Inserăm înregistrarea în tabelul de legătură Interactiune
		INSERT INTO Interactiune(idCi, idB) 
		VALUES (@id_cititor, @id_bibliotecar)

		COMMIT TRANSACTION
		SELECT 'Transaction (tabel legatura) committed'
		
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION

		DECLARE @ErrorMessage2 nvarchar(4000) = ERROR_MESSAGE(),
		@ErrorSeverity2 int = ERROR_SEVERITY(),
		@ErrorState2 int = ERROR_STATE();

		RAISERROR (@ErrorMessage2, @ErrorSeverity2, @ErrorState2);
		SELECT 'Transaction (tabel legatura) rollback'
	END CATCH
	END 


SELECT * FROM Cititor;
SELECT * FROM Bibliotecar;
SELECT * FROM Interactiune;

EXEC dbo.InteractiuneCititorBibliotecar 'Oniga', 'Maria', '2002-07-08', 'Blaj', 'Eroilor', 72, 'Andreescu', 'Marius', '1980-05-23';
SELECT * FROM Cititor;
SELECT * FROM Bibliotecar;
SELECT * FROM Interactiune;

EXEC dbo.InteractiuneCititorBibliotecar 'Oniga', 'Maria', '2102-07-08', 'Blaj', 'Eroilor', 72, 'Andreescu', 'Marius', '1980-05-23';
SELECT * FROM Cititor;
SELECT * FROM Bibliotecar;
SELECT * FROM Interactiune;

EXEC dbo.InteractiuneCititorBibliotecar2 'Oniga', 'Maria', '2002-07-08', 'Blaj', 'Eroilor', 72, 'Andreescu', 'Marius', '1980-05-23';
SELECT * FROM Cititor;
SELECT * FROM Bibliotecar;
SELECT * FROM Interactiune;

EXEC dbo.InteractiuneCititorBibliotecar2 'Oniga', 'Maria', '2102-07-08', 'Blaj', 'Unirii', 73, 'Andreescu', 'Marius', '1980-05-23';
SELECT * FROM Cititor;
SELECT * FROM Bibliotecar;
SELECT * FROM Interactiune;