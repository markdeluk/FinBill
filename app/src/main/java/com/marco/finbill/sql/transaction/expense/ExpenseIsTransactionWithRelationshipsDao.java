package com.marco.finbill.sql.transaction.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseIsTransactionWithRelationshipsDao {

    @Query("SELECT E.*, T.*, CU.*, A.*, CA.* " +
            "FROM transaction_table T, expense_table E, account_table A, category_table CA, currency_table CU " +
            "WHERE T.transactionId = E.expenseId AND E.fromExpense = A.accountId AND E.toExpense = CA.categoryId AND T.transactionCurrencyId = CU.currencyId")
    LiveData<List<ExpenseIsTransactionWithRelationships>> getAllExpenseIsTransactionWithRelationships();


    @Query("SELECT E.*, T.*, A.*, C.*" +
            "FROM transaction_table T, expense_table E, account_table A, category_table C " +
            "WHERE T.transactionId = E.expenseId AND E.fromExpense = A.accountId AND E.toExpense = C.categoryId " +
            "AND E.expenseId = :expenseId")
    ExpenseIsTransactionWithRelationships getExpenseIsTransactionWithRelationshipsById(int expenseId);


}
