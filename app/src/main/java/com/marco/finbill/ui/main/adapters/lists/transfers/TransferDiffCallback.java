package com.marco.finbill.ui.main.adapters.lists.transfers;

import androidx.recyclerview.widget.DiffUtil;

import com.marco.finbill.sql.transaction.transfer.TransferRelationships;

import java.util.List;

public class TransferDiffCallback extends DiffUtil.Callback {

    private final List<TransferRelationships> oldTransferList;
    private final List<TransferRelationships> newTransferList;

    public TransferDiffCallback(List<TransferRelationships> oldTransferList, List<TransferRelationships> newTransferList) {
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