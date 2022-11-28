package com.marco.finbill.sql.transaction.income;

public class IncomeIsTransaction {
    private int incomeId;
    private int transactionId;

    public IncomeIsTransaction(int incomeId, int transactionId) {
        this.incomeId = incomeId;
        this.transactionId = transactionId;
    }

    public int getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(int incomeId) {
        this.incomeId = incomeId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
