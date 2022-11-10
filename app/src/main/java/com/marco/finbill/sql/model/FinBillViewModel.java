package com.marco.finbill.sql.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.model.FinBillRepository;
import com.marco.finbill.sql.transaction.Transaction;
import com.marco.finbill.sql.transaction.expense.TransactionIsExpenseWithRelationships;
import com.marco.finbill.sql.transaction.income.TransactionIsIncomeWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransactionIsTransferWithRelationships;

import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;

    public FinBillViewModel(Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);
    }

    public LiveData<List<TransactionIsExpenseWithRelationships>> getAllExpenses() {
        return repository.getAllExpenses();
    }

    public LiveData<List<TransactionIsIncomeWithRelationships>> getAllIncomes() {
        return repository.getAllIncomes();
    }

    public LiveData<List<TransactionIsTransferWithRelationships>> getAllTransfers() {
        return repository.getAllTransfers();
    }
}
