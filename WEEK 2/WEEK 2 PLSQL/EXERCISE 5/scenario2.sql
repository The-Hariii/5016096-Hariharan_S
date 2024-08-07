CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, ActionDate, ActionType)
    VALUES (:NEW.TransactionID, SYSDATE, 'INSERT');
END;
