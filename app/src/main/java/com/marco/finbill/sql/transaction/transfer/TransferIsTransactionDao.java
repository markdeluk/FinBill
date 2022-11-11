package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransferIsTransactionDao {
    @Query("SELECT T.transactionId AS transactionId, TR.transferId AS transferId " +
            "FROM transaction_table T, transfer_table TR " +
            "WHERE T.transactionId = TR.transferId")
    public LiveData<List<TransferIsTransaction>> getAllTransferIsTransaction();
}
