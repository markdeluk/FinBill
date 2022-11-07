package com.marco.finbill.sql;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.marco.finbill.sql.transaction.Transaction;

import java.util.List;

public class FinBillViewModel extends AndroidViewModel {

    private final FinBillRepository repository;

    public FinBillViewModel(@NonNull Application application) {
        super(application);
        repository = FinBillRepository.getInstance(application);
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return repository.getAllNotes();
    }

    public void insert(final Note note) {
        repository.insert(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public void upperCase() {
        repository.upperCase();
    }

    public LiveData<List<Note>> search(String query) {
        return repository.search(query);
    }
}
