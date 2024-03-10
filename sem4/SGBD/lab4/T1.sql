USE Biblioteca;
GO

---- Scenariu NEREZOLVAT DirtyReads (BAD)
--	BEGIN TRAN
--		Update Cititor set nume_c = 'trans1' where numar_c = 3
--		WAITFOR DELAY '00:00:10'
--		ROLLBACK TRANSACTION
--		SELECT 'transaction rollbacked'


-- Scenariu NEREZOLVAT Non-repeatable reads (BAD)
	INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c, strada_c, numar_c)
	VALUES ('Teodorescu', 'Victor', '2001-01-05', 'Botosani', 'Stejarului', 5)
	BEGIN TRAN
		WAITFOR DELAY '00:00:10'
		UPDATE Cititor SET nume_c = 'Trans1' where prenume_c = 'Victor'
		COMMIT TRAN
		select 'transaction commited'


---- Scenariu NEREZOLVAT Phantom reads (BAD)
--	BEGIN TRAN
--		WAITFOR DELAY '00:00:10'
--		INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c, strada_c, numar_c)
--		VALUES ('Ciufudean', 'Mihai', '2001-03-09', 'Botosani', 'Teiului', 25)
--		COMMIT TRAN


	
---- Scenariu NEREZOLVAT Deadlock (BAD)
--	BEGIN TRAN
--		UPDATE Cititor SET nume_c = 'Deadlock1' WHERE idCi = 28
--		WAITFOR DELAY '00:00:10'
--		UPDATE Biblioteca SET nume_bi = 'Deadlock1' WHERE idBi = 6
--		COMMIT TRAN
--		SELECT 'Transaction commited'


--	SELECT 'scenariu rezolvat'

----Scenariu REZOLVAT DirtyReads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL READ COMMITTED
--	BEGIN TRAN
--		Update Cititor set nume_c = 'trans1' where numar_c = 3
--		WAITFOR DELAY '00:00:10'
--		ROLLBACK TRANSACTION
--		SELECT 'transaction rollbacked'


----Scenariu REZOLVAT Non-repeatable reads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
--	INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c, strada_c, numar_c)
--	VALUES ('Teodorescu', 'Victor', '2001-01-05', 'Botosani', 'Stejarului', 5)
--	BEGIN TRAN
--		WAITFOR DELAY '00:00:10'
--		UPDATE Cititor SET nume_c = 'Trans1' where prenume_c = 'Victor'
--		COMMIT TRAN
--		select 'transaction commited'


----Scenariu REZOLVAT Phantom reads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
--	BEGIN TRAN
--		WAITFOR DELAY '00:00:10'
--		INSERT INTO Cititor(nume_c, prenume_c, data_nastere_c, oras_c, strada_c, numar_c)
--		VALUES ('Ciufudean', 'Mihai', '2001-03-09', 'Botosani', 'Teiului', 25)
--		COMMIT TRAN


--Scenariu REZOLVAT Deadlock (GOOD)
	BEGIN TRAN
		UPDATE Cititor SET nume_c = 'Deadlock1' WHERE idCi = 28
		WAITFOR DELAY '00:00:10'
		UPDATE Biblioteca SET nume_bi = 'Deadlock1' WHERE idBi = 6
		COMMIT TRAN
		SELECT 'Transaction commited'

----------------------------------------------------------------------

--CREATE OR ALTER PROCEDURE DeadLockProc1
--AS
--BEGIN
--	BEGIN TRAN
--		UPDATE Cititor SET nume_c = 'Deadlock1' WHERE idCi = 28
--		WAITFOR DELAY '00:00:05'
--		UPDATE Biblioteca SET nume_bi = 'Deadlock1' WHERE idBi = 6
--		COMMIT TRAN
--		SELECT 'Transaction commited'

--	SELECT * FROM Cititor
--	SELECT * FROM Biblioteca
--END;
--GO

----------------------------------------------------------------------