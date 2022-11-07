package com.marco.finbill.sql.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.model.FinBillRepository;
import com.marco.finbill.sql.transaction.Transaction;

import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;

    public FinBillViewModel(Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return repository.getAllTransactions();
    }
}
