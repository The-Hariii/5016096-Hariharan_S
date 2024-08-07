BEGIN
    FOR rec IN (SELECT LoanID, InterestRate FROM Loans WHERE CustomerID IN (SELECT CustomerID FROM Customers WHERE (SYSDATE - DOB) / 365 > 60)) LOOP
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE LoanID = rec.LoanID;
    END LOOP;
    COMMIT;
END;
