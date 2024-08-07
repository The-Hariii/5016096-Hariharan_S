CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF (SELECT Balance FROM Accounts WHERE AccountID = :NEW.AccountID) < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20004, 'Insufficient funds for withdrawal');
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Deposit amount must be positive');
    END IF;
END;
