package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transfer_table")
public class Transfer {

    @PrimaryKey int transferId;
    int fromTransfer;
    int toTransfer;

}
