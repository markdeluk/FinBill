package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface TransferFromAccountDao {
    @Query("SELECT T.transferId, A.* " +
            "FROM transfer_table T, account_table A " +
            "WHERE T.fromTransfer = A.accountId " +
            "AND T.transferId = :transferId")
    TransferFromAccount getTransferFromAccountById(int transferId);
}
