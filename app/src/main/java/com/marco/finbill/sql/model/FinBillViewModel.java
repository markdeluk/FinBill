package com.marco.finbill.sql.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.income.IncomeIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.nio.channels.AcceptPendingException;
import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;

    public FinBillViewModel(Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);


    }

    public LiveData<List<ExpenseIsTransactionWithRelationships>> getAllExpenses() {
        return repository.getAllExpenses();
    }

    public LiveData<List<IncomeIsTransactionWithRelationships>> getAllIncomes() {
        return repository.getAllIncomes();
    }

    public LiveData<List<TransferIsTransactionWithRelationships>> getAllTransfers() {
        return repository.getAllTransfers();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return repository.getAllAccounts();
    }
}
