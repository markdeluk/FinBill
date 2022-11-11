package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TransferToAccountDao {
    @Query("SELECT T.transferId, A.* " +
            "FROM transfer_table T, account_table A " +
            "WHERE T.toTransfer = A.accountId " +
            "AND T.transferId = :transferId")
    TransferToAccount getTransferToAccountById(int transferId);
}
