package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transfer_table")
public class Transfer {

    @PrimaryKey public int transferId;
    public int fromTransfer;
    public int toTransfer;

}
