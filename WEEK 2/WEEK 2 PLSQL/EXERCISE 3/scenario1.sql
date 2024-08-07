CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    FOR rec IN (SELECT AccountID, Balance FROM Accounts) LOOP
        UPDATE Accounts
        SET Balance = Balance * 1.01
        WHERE AccountID = rec.AccountID;
    END LOOP;
    COMMIT;
END;
