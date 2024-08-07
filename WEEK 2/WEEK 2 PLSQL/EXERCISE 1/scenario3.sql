BEGIN
    FOR rec IN (SELECT CustomerID, LoanID FROM Loans WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || rec.LoanID || ' is due soon for customer ' || rec.CustomerID);
    END LOOP;
END;
