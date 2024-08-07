DECLARE
    CURSOR c_transactions IS
        SELECT CustomerID, TransactionDate, Amount
        FROM Transactions
        WHERE EXTRACT(MONTH FROM TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM TransactionDate) = EXTRACT(YEAR FROM SYSDATE);

BEGIN
    FOR rec IN c_transactions LOOP
        DBMS_OUTPUT.PUT_LINE('Customer ' || rec.CustomerID || ' has a transaction of ' || rec.Amount || ' on ' || rec.TransactionDate);
    END LOOP;
END;
