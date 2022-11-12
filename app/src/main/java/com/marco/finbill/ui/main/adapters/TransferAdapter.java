package com.marco.finbill.ui.main.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.expense.ExpenseIsTransactionWithRelationships;
import com.marco.finbill.sql.transaction.transfer.TransferIsTransactionWithRelationships;

import java.util.List;

public class TransferAdapter extends ListAdapter<TransferIsTransactionWithRelationships, TransferAdapter.ViewHolder> {

    public TransferAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<TransferIsTransactionWithRelationships> DIFF_CALLBACK = new DiffUtil.ItemCallback<TransferIsTransactionWithRelationships>() {

        @Override
        public boolean areItemsTheSame(TransferIsTransactionWithRelationships oldItem, TransferIsTransactionWithRelationships newItem) {
            return oldItem.transferId == newItem.transferId;
        }

        @Override
        public boolean areContentsTheSame(TransferIsTransactionWithRelationships oldItem, TransferIsTransactionWithRelationships newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.transaction.equals(newItem.transaction) && oldItem.fromTransfer.equals(newItem.fromTransfer) && oldItem.toTransfer.equals(newItem.toTransfer);
        }
    };

    @NonNull
    @Override
    public TransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_list_item, parent, false);
        return new TransferAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.ViewHolder holder, int position) {
        TransferIsTransactionWithRelationships transferIsTransactionWithRelationships = getTransferAt(position);
        // picture
        if (transferIsTransactionWithRelationships.transaction.getImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transferIsTransactionWithRelationships.transaction.getImage());
        }
        holder.title.setText(transferIsTransactionWithRelationships.transaction.getName());
        holder.from.setText(transferIsTransactionWithRelationships.fromTransfer.getAccountName());
        holder.to.setText(transferIsTransactionWithRelationships.toTransfer.getAccountName());
        holder.date.setText(transferIsTransactionWithRelationships.transaction.getDate().toString());
        holder.time.setText(transferIsTransactionWithRelationships.transaction.getTime().toString());
        holder.sign.setText(R.string.minus);
        holder.amount.setText(String.valueOf(transferIsTransactionWithRelationships.transaction.getAmount()));
        holder.currency.setText(transferIsTransactionWithRelationships.transaction.getCurrency().getCurrencyCode());
    }

    public TransferIsTransactionWithRelationships getTransferAt(int position) {
        return getItem(position);
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