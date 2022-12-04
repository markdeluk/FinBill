package com.marco.finbill.ui.main.adapters.lists.transfers;

import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.marco.finbill.R;
import com.marco.finbill.sql.transaction.transfer.TransferRelationships;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.ViewHolder> {

    private final ArrayList<TransferRelationships> transfers;

    public TransferAdapter() {
        this.transfers = new ArrayList<>();
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
        TransferRelationships transferRelationships = transfers.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        if (transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage() == null) {
            holder.picture.setImageResource(R.drawable.picture_icon);
        } else {
            holder.picture.setImageBitmap(transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionImage());
        }
        holder.title.setText(transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionName());
        holder.from.setText(transferRelationships.getFromTransfer().getAccountName());
        holder.to.setText(transferRelationships.getToTransfer().getAccountName());
        holder.date.setText(simpleDateFormat.format(transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionDate()));
        simpleDateFormat.applyPattern("HH:mm");
        holder.time.setText(simpleDateFormat.format(transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionTime()));
        holder.sign.setText("");
        holder.amount.setText(new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault())).format(transferRelationships.getTransactionHasCurrency().getTransaction().getTransactionAmount()));
        holder.currency.setText(Currency.getInstance(transferRelationships.getTransactionHasCurrency().getTransactionCurrency().getCurrencyString()).getSymbol());
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public void updateTransferList(List<TransferRelationships> transfers) {
        TransferDiffCallback diffCallback = new TransferDiffCallback(this.transfers, transfers);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.transfers.clear();
        this.transfers.addAll(transfers);
        diffResult.dispatchUpdatesTo(this);
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
            currency = itemView.findViewById(R.id.currencySymbol);
        }
    }
}
