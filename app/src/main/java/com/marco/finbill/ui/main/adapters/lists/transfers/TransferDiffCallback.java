package com.marco.finbill.ui.main.adapters.lists.transfers;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.util.List;

public class TransferDiffCallback extends DiffUtil.Callback {

    private final List<TransferIsTransactionWithRelationships> oldTransferList;
    private final List<TransferIsTransactionWithRelationships> newTransferList;

    public TransferDiffCallback(List<TransferIsTransactionWithRelationships> oldTransferList, List<TransferIsTransactionWithRelationships> newTransferList) {
        this.oldTransferList = oldTransferList;
        this.newTransferList = newTransferList;
    }

    @Override
    public int getOldListSize() {
        return oldTransferList.size();
    }

    @Override
    public int getNewListSize() {
        return newTransferList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldTransferList.get(oldItemPosition).getTransferId() == newTransferList.get(newItemPosition).getTransferId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldTransferList.get(oldItemPosition).equals(newTransferList.get(newItemPosition));
    }
}