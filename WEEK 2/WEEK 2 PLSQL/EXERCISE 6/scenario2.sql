DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance
        FROM Accounts;

BEGIN
    FOR rec IN c_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - 50 -- assuming annual fee of $50
        WHERE AccountID = rec.AccountID;
    END LOOP;
    COMMIT;
END;
