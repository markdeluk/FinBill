package com.marco.finbill.ui.main.dialogs;

import static com.marco.finbill.ui.main.MainActivity.SHAREDPREFS;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.marco.finbill.R;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.PriorityType;
import com.marco.finbill.enums.TransactionFrequency;
import com.marco.finbill.enums.TransactionNotifyFrequency;
import com.marco.finbill.enums.TransactionRecurrency;
import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.model.FinBillViewModel;
import com.marco.finbill.sql.transaction.all.Transaction;
import com.marco.finbill.sql.transaction.expense.Expense;
import com.marco.finbill.sql.transaction.income.Income;
import com.marco.finbill.sql.transaction.transfer.Transfer;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class AddTransactionDialog extends DialogFragment {

    public static final String TAG = "AddTransactionDialog";

    private TransactionType transactionType = TransactionType.DEFAULT;
    private Toolbar toolbar;
    private FinBillViewModel viewModel;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private Transaction transaction;
    private TextInputEditText nameEdit;
    private TextInputEditText descriptionEdit;
    private Spinner transactionSpinner;
    private TextInputEditText amountEdit;
    private TextInputEditText dateEdit;
    private TextInputEditText timeEdit;
    private Spinner frequencyEdit;
    private EditText infoLastingEdit;
    private Spinner infoRecurrentEdit;
    private SwitchCompat notifySwitch;
    private Spinner notifyEdit;
    private EditText notesEdit;
    private ImageView pictureEdit;
    private boolean pictureSelected = false;
    private Spinner priorityEdit;
    private Spinner currencyEdit;
    private Expense expense;
    private Income income;
    private Transfer transfer;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private SharedPreferences preferences;

    public AddTransactionDialog() {
    }

    public AddTransactionDialog(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public static void display(FragmentManager fragmentManager) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog();
        addTransactionDialog.show(fragmentManager, TAG);
    }

    public static void display(FragmentManager fragmentManager, TransactionType transactionType) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog(transactionType);
        addTransactionDialog.show(fragmentManager, TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new FinBillViewModel(requireActivity().getApplication());
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        pictureEdit.setImageURI(selectedImage);
                        pictureSelected = true;
                    }
                }
            }
        });
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                assert result.getData() != null;
                Bundle extras = result.getData().getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                pictureEdit.setImageBitmap(imageBitmap);
                pictureSelected = true;
            }
        });
        preferences = requireActivity().getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);
        toolbar = rootView.findViewById(R.id.toolbarAddTransaction);

        // TRANSACTION TYPE

        transactionSpinner = rootView.findViewById(R.id.transactionTypeEdit);
        List<String> transactionTypeList = new ArrayList<>();
        transactionTypeList.add(0, getResources().getString(R.string.choose_transaction_type));
        transactionTypeList.addAll(Arrays.asList(getResources().getStringArray(R.array.transaction_types_option)));
        ArrayAdapter<String> transactionTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, transactionTypeList);
        transactionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactionSpinner.setAdapter(transactionTypeAdapter);

        if (transactionType == TransactionType.DEFAULT) {
            transactionSpinner.setSelection(TransactionType.DEFAULT.ordinal());
        }
        else {
            transactionSpinner.setSelection(transactionType.ordinal());
        }

        // TRANSACTION FIELDS

        fromSpinner = rootView.findViewById(R.id.fromEdit);
        toSpinner = rootView.findViewById(R.id.toEdit);

        LinearLayout transactionLayout = rootView.findViewById(R.id.transactionLayout);
        transactionLayout.setVisibility(View.GONE);

        List<String> fromList = new ArrayList<>();
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, fromList);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        List<String> toList = new ArrayList<>();
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, toList);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        transactionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == TransactionType.DEFAULT.ordinal()) {
                    transactionLayout.setVisibility(View.GONE);
                }
                else {
                    transactionLayout.setVisibility(View.VISIBLE);
                    if (position == TransactionType.EXPENSE.ordinal()) {
                        // Populate spinners
                        viewModel.getAllAccounts().observe(getViewLifecycleOwner(), accounts -> {
                            fromList.clear();
                            fromList.add(getResources().getString(R.string.choose_account));
                            for (Account account : accounts) {
                                fromList.add(account.getAccountName());
                            }
                            fromAdapter.notifyDataSetChanged();
                        });
                        viewModel.getAllCategoriesByType(CategoryType.EXPENSE).observe(getViewLifecycleOwner(), categories -> {
                            toList.clear();
                            toList.add(getResources().getString(R.string.choose_category));
                            for (Category category : categories) {
                                toList.add(category.getCategoryName());
                            }
                            toAdapter.notifyDataSetChanged();
                        });

                    } else if (position == TransactionType.INCOME.ordinal()) {
                        viewModel.getAllCategoriesByType(CategoryType.INCOME).observe(getViewLifecycleOwner(), categories -> {
                            fromList.clear();
                            fromList.add(getResources().getString(R.string.choose_category));
                            for (Category category : categories) {
                                fromList.add(category.getCategoryName());
                            }
                            fromAdapter.notifyDataSetChanged();
                        });
                        viewModel.getAllAccounts().observe(getViewLifecycleOwner(), accounts -> {
                            toList.clear();
                            toList.add(getResources().getString(R.string.choose_account));
                            for (Account account : accounts) {
                                toList.add(account.getAccountName());
                            }
                            toAdapter.notifyDataSetChanged();
                        });
                    } else { // Transfer
                        viewModel.getAllAccounts().observe(getViewLifecycleOwner(), accounts -> {
                            fromList.clear();
                            fromList.add(getResources().getString(R.string.choose_account));
                            for (Account account : accounts) {
                                fromList.add(account.getAccountName());
                            }
                            fromAdapter.notifyDataSetChanged();

                            toList.clear();
                            toList.add(getResources().getString(R.string.choose_account));
                            for (Account account : accounts) {
                                toList.add(account.getAccountName());
                            }
                            toAdapter.notifyDataSetChanged();
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nameEdit = rootView.findViewById(R.id.nameEdit);
        descriptionEdit = rootView.findViewById(R.id.descriptionEdit);
        amountEdit = rootView.findViewById(R.id.amountEdit);

        // CURRENCY

        currencyEdit = rootView.findViewById(R.id.currencyEdit);
        List<String> currencyStringList = new ArrayList<>();
        ArrayAdapter<String> currencyStringAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, currencyStringList);
        currencyStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencyEdit.setAdapter(currencyStringAdapter);
        viewModel.getAllCurrencies().observe(getViewLifecycleOwner(), currencies -> {
            currencyStringList.clear();
            currencyStringList.add(0, getResources().getString(R.string.choose_currency));
            for (Currency currency : currencies) {
                currencyStringList.add(currency.getCurrencyString());
            }
            currencyStringAdapter.notifyDataSetChanged();
            String choice = preferences.getString("currency", null);
            if (choice != null) {
                currencyEdit.setSelection(currencyStringList.indexOf(choice));
            }
        });

        // DATE

        dateEdit = rootView.findViewById(R.id.dateEdit);
        dateEdit.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view1, year, month, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dateEdit.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime()));
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        // TIME

        timeEdit = rootView.findViewById(R.id.timeEdit);
        timeEdit.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), (view2, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                timeEdit.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.getTime()));
            }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

        // FREQUENCY

        frequencyEdit = rootView.findViewById(R.id.frequencyEdit);
        List<String> frequencyList = new ArrayList<>();
        frequencyList.add(getResources().getString(R.string.choose_frequency));
        frequencyList.addAll(Arrays.asList(getResources().getStringArray(R.array.frequency_option)));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, frequencyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencyEdit.setAdapter(adapter);

        AtomicInteger lasting = new AtomicInteger();

        TextView infoLastingTitle = rootView.findViewById(R.id.infoLastingTitle);
        infoLastingEdit = rootView.findViewById(R.id.infoLastingEdit);

        // LASTING - coordination between buttons and text field

        infoLastingEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    lasting.set(0);
                } else {
                    lasting.set(Integer.parseInt(s.toString()));
                }
            }
        });

        Button plusButton = rootView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(v -> {
            lasting.getAndIncrement();
            infoLastingEdit.setText(String.valueOf(lasting.get()));
        });
        Button minusButton = rootView.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(v -> {
            if (lasting.get() > 0) {
                lasting.getAndDecrement();
            }
            infoLastingEdit.setText(String.valueOf(lasting.get()));
        });

        // RECURRENT

        TextView infoRecurrentTitle = rootView.findViewById(R.id.infoRecurrentTitle);
        infoRecurrentEdit = rootView.findViewById(R.id.infoRecurrentEdit);
        List<String> infoRecurrentList = new ArrayList<>();
        infoRecurrentList.add(getResources().getString(R.string.choose_recurrency));
        infoRecurrentList.addAll(Arrays.asList(getResources().getStringArray(R.array.recurrency_option)));
        ArrayAdapter<String> infoRecurrentAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, infoRecurrentList);
        infoRecurrentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        infoRecurrentEdit.setAdapter(infoRecurrentAdapter);

        // NOTIFY

        notifySwitch = rootView.findViewById(R.id.notifySwitch);
        TextView notifyTitle = rootView.findViewById(R.id.notifyTitle);
        notifyEdit = rootView.findViewById(R.id.notifyEdit);

        List<String> notifyList = new ArrayList<>();
        notifyList.add(getResources().getString(R.string.choose_recurrency));
        notifyList.addAll(Arrays.asList(getResources().getStringArray(R.array.notify_option)));
        ArrayAdapter<String> notifyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, notifyList);
        notifyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notifyEdit.setAdapter(notifyAdapter);

        notifySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                notifyTitle.setVisibility(View.VISIBLE);
                notifyEdit.setVisibility(View.VISIBLE);
            } else {
                notifyTitle.setVisibility(View.GONE);
                notifyEdit.setVisibility(View.GONE);
            }
        });

        // The first time, visibility must be managed manually, since the default value for the switch is false

        notifyTitle.setVisibility(View.GONE);
        notifyEdit.setVisibility(View.GONE);

        LinearLayout notifyLayout = rootView.findViewById(R.id.notifyLayout);

        frequencyEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == TransactionFrequency.ONCE.ordinal() || position == TransactionFrequency.DEFAULT.ordinal()) {
                    infoLastingTitle.setVisibility(View.GONE);
                    infoLastingEdit.setVisibility(View.GONE);
                    plusButton.setVisibility(View.GONE);
                    minusButton.setVisibility(View.GONE);
                    infoRecurrentTitle.setVisibility(View.GONE);
                    infoRecurrentEdit.setVisibility(View.GONE);
                    notifyLayout.setVisibility(View.GONE);
                }
                else if (position == TransactionFrequency.LASTING.ordinal()) {
                    infoLastingTitle.setVisibility(View.VISIBLE);
                    infoLastingEdit.setVisibility(View.VISIBLE);
                    infoLastingEdit.setText(String.valueOf(lasting.get()));
                    plusButton.setVisibility(View.VISIBLE);
                    minusButton.setVisibility(View.VISIBLE);
                    infoRecurrentTitle.setVisibility(View.GONE);
                    infoRecurrentEdit.setVisibility(View.GONE);
                    notifyLayout.setVisibility(View.GONE);
                }
                else if (position == TransactionFrequency.RECURRENT.ordinal()) {
                    infoLastingTitle.setVisibility(View.GONE);
                    infoLastingEdit.setVisibility(View.GONE);
                    plusButton.setVisibility(View.GONE);
                    minusButton.setVisibility(View.GONE);
                    infoRecurrentTitle.setVisibility(View.VISIBLE);
                    infoRecurrentEdit.setVisibility(View.VISIBLE);
                    notifyLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        notesEdit = rootView.findViewById(R.id.notesEdit);

        // PICTURE

        pictureEdit = rootView.findViewById(R.id.pictureEdit);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int size = displayMetrics.widthPixels / 2;
        pictureEdit.getLayoutParams().width = size;
        pictureEdit.getLayoutParams().height = size;
        pictureEdit.setAdjustViewBounds(true);
        pictureEdit.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // default image
        pictureEdit.setImageResource(R.drawable.money);

        Button resetButton = rootView.findViewById(R.id.resetButton);
        Button galleryButton = rootView.findViewById(R.id.galleryButton);
        Button cameraButton = rootView.findViewById(R.id.cameraButton);

        resetButton.setOnClickListener(v -> {
            pictureEdit.setImageResource(R.drawable.money);
            pictureSelected = false;
        });

        galleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        cameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });

        // PRIORITY

        priorityEdit = rootView.findViewById(R.id.priorityEdit);
        List<String> priorityList = new ArrayList<>();
        priorityList.add(getResources().getString(R.string.choose_priority));
        priorityList.addAll(Arrays.asList(getResources().getStringArray(R.array.priority_option)));
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, priorityList);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priorityEdit.setAdapter(priorityAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(R.string.add_transaction);
        toolbar.inflateMenu(R.menu.menu_add_item);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_save) {
                transaction = new Transaction();
                if (nameEdit.getText() != null && !nameEdit.getText().toString().isEmpty()) {
                    transaction.setTransactionName(nameEdit.getText().toString());
                }
                if (descriptionEdit.getText() != null && !descriptionEdit.getText().toString().isEmpty()) {
                    transaction.setTransactionDescription(descriptionEdit.getText().toString());
                }
                transaction.setTransactionType(TransactionType.values()[transactionSpinner.getSelectedItemPosition()]);
                if (currencyEdit.getSelectedItemPosition() != 0) {
                    transaction.setTransactionCurrencyId(0); // temporary value
                }
                if (amountEdit.getText() != null && !amountEdit.getText().toString().isEmpty()) {
                    transaction.setTransactionAmount(Double.parseDouble(amountEdit.getText().toString()));
                }
                if (dateEdit.getText() != null && !dateEdit.getText().toString().isEmpty()) {
                    transaction.setTransactionDate(Date.valueOf(dateEdit.getText().toString()));
                }
                if (timeEdit.getText() != null && !timeEdit.getText().toString().isEmpty()) {
                    transaction.setTransactionTime(Time.valueOf(timeEdit.getText().toString()));
                }
                transaction.setTransactionFrequency(TransactionFrequency.values()[frequencyEdit.getSelectedItemPosition()]);
                if (transaction.getTransactionFrequency().equals(TransactionFrequency.LASTING)) {
                    transaction.setTransactionInfoLasting(Integer.parseInt(infoLastingEdit.getText().toString()));
                }
                else if (transaction.getTransactionFrequency().equals(TransactionFrequency.RECURRENT)) {
                    transaction.setTransactionInfoRecurrent(TransactionRecurrency.values()[infoRecurrentEdit.getSelectedItemPosition()]);
                    transaction.setTransactionNotify(notifySwitch.isChecked());
                    if (transaction.getTransactionNotify()) {
                        transaction.setTransactionNotifyFrequency(TransactionNotifyFrequency.values()[notifyEdit.getSelectedItemPosition()]);
                    }
                }
                transaction.setTransactionNotes(notesEdit.getText().toString());
                if (pictureSelected) {
                    transaction.setTransactionImage(((BitmapDrawable)pictureEdit.getDrawable()).getBitmap());
                }
                else {
                    transaction.setTransactionImage(null);
                }
                if (priorityEdit.getSelectedItemPosition() != 0) {
                    transaction.setTransactionPriority(PriorityType.values()[priorityEdit.getSelectedItemPosition()]);
                }
                if (transaction.isValid() && fromSpinner.getSelectedItemPosition() != 0 && toSpinner.getSelectedItemPosition() != 0) {
                    viewModel.getCurrencyByString(currencyEdit.getSelectedItem().toString()).observe(getViewLifecycleOwner(), currency -> {
                        if (currency != null) {
                            viewModel.pushTransactionFieldCurrencyId(currency.getCurrencyId());
                        }
                    });
                    // Insertion happens with a temporary value for the currency id, which is then updated
                    viewModel.insertTransaction(transaction).observe(getViewLifecycleOwner(), transactionId -> {
                        if (transactionId != null) {
                            viewModel.pushTransactionFieldId(Math.toIntExact(transactionId));
                        }
                    });
                    String from = fromSpinner.getSelectedItem().toString();
                    String to = toSpinner.getSelectedItem().toString();
                    if (transaction.getTransactionType().equals(TransactionType.EXPENSE)) {
                        expense = new Expense();
                        viewModel.getAccountByName(from).observe(getViewLifecycleOwner(), account -> {
                            if (account != null) {
                                viewModel.pushTransactionFieldFrom(account);
                            }
                        });
                        viewModel.getCategoryByName(to).observe(getViewLifecycleOwner(), category -> {
                            if (category != null) {
                                viewModel.pushTransactionFieldTo(category);
                            }
                        });
                        viewModel.pullTransactionFieldsLiveData().observe(getViewLifecycleOwner(), fields -> {
                            if (fields.isValid()) {
                                transaction.setTransactionId(fields.getId());
                                Log.e("Transaction ID", String.valueOf(transaction.getTransactionId()));
                                transaction.setTransactionCurrencyId(fields.getCurrencyId());
                                Log.e("Transaction Currency ID", String.valueOf(transaction.getTransactionCurrencyId()));
                                viewModel.updateTransaction(transaction);
                                Log.e("Transaction", transaction.toString());
                                expense.setExpenseId(fields.getId());
                                expense.setFromExpense(((Account)fields.getFrom()).getAccountId());
                                expense.setToExpense(((Category)fields.getTo()).getCategoryId());
                                viewModel.insertExpense(expense);
                                Log.e("Expense", expense.toString());
                                viewModel.popTransactionFields();
                            }
                        });
                    }
                    else if (transaction.getTransactionType().equals(TransactionType.INCOME)) {
                        income = new Income();
                        viewModel.getCategoryByName(from).observe(getViewLifecycleOwner(), category -> {
                            if (category != null) {
                                viewModel.pushTransactionFieldFrom(category);
                            }
                        });
                        viewModel.getAccountByName(to).observe(getViewLifecycleOwner(), account -> {
                            if (account != null) {
                                viewModel.pushTransactionFieldTo(account);
                            }
                        });
                        viewModel.pullTransactionFieldsLiveData().observe(getViewLifecycleOwner(), fields -> {
                            if (fields.isValid()) {
                                transaction.setTransactionId(fields.getId());
                                transaction.setTransactionCurrencyId(fields.getCurrencyId());
                                viewModel.updateTransaction(transaction);
                                income.setIncomeId(fields.getId());
                                income.setFromIncome(((Category)fields.getFrom()).getCategoryId());
                                income.setToIncome(((Account)fields.getTo()).getAccountId());
                                viewModel.insertIncome(income);
                                viewModel.popTransactionFields();
                            }
                        });
                    }
                    else { // transfer
                        transfer = new Transfer();
                        viewModel.getAccountByName(from).observe(getViewLifecycleOwner(), account -> {
                            if (account != null) {
                                viewModel.pushTransactionFieldFrom(account);
                            }
                        });
                        viewModel.getAccountByName(to).observe(getViewLifecycleOwner(), account -> {
                            if (account != null) {
                                viewModel.pushTransactionFieldTo(account);
                            }
                        });
                        viewModel.pullTransactionFieldsLiveData().observe(getViewLifecycleOwner(), fields -> {
                            if (fields.isValid()) {
                                transaction.setTransactionId(fields.getId());
                                transaction.setTransactionCurrencyId(fields.getCurrencyId());
                                viewModel.updateTransaction(transaction);
                                transfer.setTransferId(fields.getId());
                                transfer.setFromTransfer(((Account)fields.getFrom()).getAccountId());
                                transfer.setToTransfer(((Account)fields.getTo()).getAccountId());
                                viewModel.insertTransfer(transfer);
                                viewModel.popTransactionFields();
                            }
                        });
                    }
                }
                else {
                    Snackbar.make(view, R.string.incomplete_fields, Snackbar.LENGTH_LONG).show();
                    return false;
                }
                dismiss();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}