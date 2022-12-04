package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface TransferRelationshipsDao {
    @Transaction
    @Query("SELECT * FROM transfer_table")
    LiveData<List<TransferRelationships>> getAllTransferIsTransactionWithRelationships();
}