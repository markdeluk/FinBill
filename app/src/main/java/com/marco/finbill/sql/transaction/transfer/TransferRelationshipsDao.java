package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransferRelationshipsDao {
    @Query("SELECT * " +
            "FROM transfer_table TF, transaction_table TC, currency_table C, account_table A1, account_table A2 " +
            "WHERE TF.transferId = TC.transactionId AND TC.transactionCurrencyId = C.currencyId AND TF.fromTransfer = A1.accountId AND TF.toTransfer = A2.accountId")
    LiveData<List<TransferRelationships>> getAllTransferIsTransactionWithRelationships();
}
