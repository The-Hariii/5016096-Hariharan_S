DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate
        FROM Loans;

BEGIN
    FOR rec IN c_loans LOOP
        UPDATE Loans
        SET InterestRate = InterestRate * 1.05 -- assuming a 5% increase
        WHERE LoanID = rec.LoanID;
    END LOOP;
    COMMIT;
END;
