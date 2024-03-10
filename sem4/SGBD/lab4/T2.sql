USE Biblioteca;
GO

----------------------------------------------------------------------

---- Scenariu NEREZOLVAT DirtyReads (BAD)
--	SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
--	BEGIN TRAN
--		SELECT * FROM Cititor
--		WAITFOR DELAY '00:00:10'
--		SELECT * FROM Cititor
--		COMMIT TRAN


-- Scenariu NEREZOLVAT Non-repeatable reads (BAD)
	SET TRANSACTION ISOLATION LEVEL READ COMMITTED
	BEGIN TRAN
		SELECT * FROM Cititor
		WAITFOR DELAY '00:00:10'
		SELECT * FROM Cititor
		COMMIT TRAN


---- Scenariu NEREZOLVAT Phantom reads (BAD)
--	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
--	BEGIN TRAN
--		SELECT * FROM Cititor
--		WAITFOR DELAY '00:00:10'
--		SELECT * FROM Cititor
--		COMMIT TRAN


---- Scenariu NEREZOLVAT Deadlock (BAD)
--	BEGIN TRAN
--		UPDATE Biblioteca SET nume_bi = 'Deadlock2' WHERE idBi = 6
--		WAITFOR DELAY '00:00:10'
--		UPDATE Cititor SET nume_c = 'Deadlock2' WHERE idCi = 28
--		COMMIT TRAN
--		SELECT 'Transaction commited'

--	SELECT * FROM Cititor
--	SELECT * FROM Biblioteca


--	SELECT 'scenariu rezolvat'
---- Scenariu REZOLVAT DirtyReads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL READ COMMITTED
--	BEGIN TRAN
--		SELECT * FROM Cititor
--		WAITFOR DELAY '00:00:10'
--		SELECT * FROM Cititor
--		COMMIT TRAN

---- Scenariu REZOLVAT Non-repeatable reads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
--	BEGIN TRAN
--		SELECT * FROM Cititor
--		WAITFOR DELAY '00:00:10'
--		SELECT * FROM Cititor
--		COMMIT TRAN


---- Scenariu REZOLVAT Phantom reads (GOOD)
--	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
--	BEGIN TRAN
--		SELECT * FROM Cititor
--		WAITFOR DELAY '00:00:10'
--		SELECT * FROM Cititor
--		COMMIT TRAN


-- Scenariu REZOLVAT Deadlock (GOOD)
	set deadlock_priority high
	BEGIN TRAN
		UPDATE Biblioteca SET nume_bi = 'Deadlock2' WHERE idBi = 6
		WAITFOR DELAY '00:00:10'
		UPDATE Cititor SET nume_c = 'Deadlock2' WHERE idCi = 28
		COMMIT TRAN
		SELECT 'Transaction commited'

----------------------------------------------------------------------

--CREATE OR ALTER PROCEDURE DeadLockProc2
--AS
--BEGIN
--	BEGIN TRAN
--		UPDATE Biblioteca SET nume_bi = 'Deadlock2' WHERE idBi = 5
--		WAITFOR DELAY '00:00:05'
--		UPDATE Cititor SET nume_c = 'Deadlock2' WHERE idCi = 30
--		COMMIT TRAN
--		SELECT 'Transaction commited'

--	SELECT * FROM Cititor
--	SELECT * FROM Biblioteca
--END;
--GO

----------------------------------------------------------------------

SELECT * from Biblioteca;
Select * from Cititor;