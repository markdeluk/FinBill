package com.marco.finbill.sql.transaction.transfer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.marco.finbill.sql.transaction.Transaction;

import java.util.List;

@Dao
public interface TransferDao {
    
    @Insert
    void insertTransfer(Transfer transfer);

    @Update
    void updateTransfer(Transfer transfer);

    @Delete
    void deleteTransfer(Transfer transfer);

    @androidx.room.Transaction
    @Query("SELECT * FROM transfer_table WHERE transferId = :transferId")
    TransactionIsTransferWithRelationships getTransferById(int transferId);

    @androidx.room.Transaction
    @Query("SELECT * FROM transfer_table")
    LiveData<List<TransactionIsTransferWithRelationships>> getAllTransfers();

    @Query("DELETE FROM transfer_table")
    void deleteAllTransfers();
    
}
