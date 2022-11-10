package com.marco.finbill.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.transfer.TransactionIsTransferWithRelationships;

import java.util.ArrayList;
import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private final List<TransactionIsTransferWithRelationships> transfers;

    public TransferAdapter(List<TransactionIsTransferWithRelationships> transfers) {
        this.transfers = transfers;
    }

    @NonNull
    @Override
    public TransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.ViewHolder holder, int position) {
        TransactionIsTransferWithRelationships transactionIsTransferWithRelationships = transfers.get(position);
        // picture
        if (transactionIsTransferWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transactionIsTransferWithRelationships.transaction.getImage());
        }
        holder.title.setText(transactionIsTransferWithRelationships.transaction.getName());
        holder.from.setText(transactionIsTransferWithRelationships.transferWithRelationships.fromTransfer.getName());
        holder.to.setText(transactionIsTransferWithRelationships.transferWithRelationships.toTransfer.getName());
        holder.date.setText(transactionIsTransferWithRelationships.transaction.getDate().toString());
        holder.time.setText(transactionIsTransferWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(transactionIsTransferWithRelationships.transaction.getAmount()));
        holder.currency.setText(transactionIsTransferWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView picture;
        private final TextView title;
        private final TextView from;
        private final TextView to;
        private final TextView date;
        private final TextView time;
        private final TextView sign;
        private final TextView amount;
        private final TextView currency;

        public ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.title);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            sign = itemView.findViewById(R.id.sign);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currency);
        }
    }

}