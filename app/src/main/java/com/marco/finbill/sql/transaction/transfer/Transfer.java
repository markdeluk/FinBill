package com.marco.finbill.sql.transaction.transfer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transfer_table")
public class Transfer {

    @PrimaryKey private int transferId;
    private int fromTransfer;
    private int toTransfer;

    public Transfer(int transferId, int fromTransfer, int toTransfer) {
        this.transferId = transferId;
        this.fromTransfer = fromTransfer;
        this.toTransfer = toTransfer;
    }

    public Transfer() {
        this.transferId = 0;
        this.fromTransfer = 0;
        this.toTransfer = 0;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getFromTransfer() {
        return fromTransfer;
    }

    public void setFromTransfer(int fromTransfer) {
        this.fromTransfer = fromTransfer;
    }

    public int getToTransfer() {
        return toTransfer;
    }

    public void setToTransfer(int toTransfer) {
        this.toTransfer = toTransfer;
    }

}
