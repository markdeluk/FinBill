package com.marco.finbill.ui.main.dashboard;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.marco.finbill.R;
import com.marco.finbill.enums.TransactionFrequency;
import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.model.FinBillViewModel;

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
    private ImageView pictureEdit;
    private ActivityResultLauncher<Intent> cameraLauncher;

    public AddTransactionDialog() {
    }

    public AddTransactionDialog(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public static AddTransactionDialog display(FragmentManager fragmentManager) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog();
        addTransactionDialog.show(fragmentManager, TAG);
        return addTransactionDialog;
    }

    public static AddTransactionDialog display(FragmentManager fragmentManager, TransactionType transactionType) {
        AddTransactionDialog addTransactionDialog = new AddTransactionDialog(transactionType);
        addTransactionDialog.show(fragmentManager, TAG);
        return addTransactionDialog;
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
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);
        toolbar = rootView.findViewById(R.id.toolbar3);

        Spinner transactionSpinner = rootView.findViewById(R.id.transactionEdit);
        List<String> transactionTypeList = new ArrayList<>();
        transactionTypeList.add(getResources().getString(R.string.choose_transaction_type));
        transactionTypeList.addAll(Arrays.asList(getResources().getStringArray(R.array.transaction_types_option)));
        ArrayAdapter<String> transactionTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, transactionTypeList);
        transactionTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactionSpinner.setAdapter(transactionTypeAdapter);

        if (transactionType != TransactionType.DEFAULT) {
            transactionSpinner.setSelection(transactionType.ordinal());
        }
        else {
            transactionSpinner.setSelection(TransactionType.DEFAULT.ordinal());
        }

        TextView fromTitle = rootView.findViewById(R.id.fromTitle);
        Spinner fromSpinner = rootView.findViewById(R.id.fromEdit);
        TextView toTitle = rootView.findViewById(R.id.toTitle);
        Spinner toSpinner = rootView.findViewById(R.id.toEdit);

        transactionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != TransactionType.DEFAULT.ordinal()) {
                    fromTitle.setVisibility(View.VISIBLE);
                    fromSpinner.setVisibility(View.VISIBLE);
                    toTitle.setVisibility(View.VISIBLE);
                    toSpinner.setVisibility(View.VISIBLE);
                }
                else {
                    fromTitle.setVisibility(View.GONE);
                    fromSpinner.setVisibility(View.GONE);
                    toTitle.setVisibility(View.GONE);
                    toSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> fromList = new ArrayList<>();
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, fromList);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        List<String> toList = new ArrayList<>();
        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, toList);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        transactionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                    viewModel.getAllCategoriesByType(TransactionType.EXPENSE.ordinal()).observe(getViewLifecycleOwner(), categories -> {
                        toList.clear();
                        toList.add(getResources().getString(R.string.choose_category));
                        for (Category category : categories) {
                            toList.add(category.getCategoryName());
                        }
                        toAdapter.notifyDataSetChanged();
                    });

                } else if (position == TransactionType.INCOME.ordinal()) {
                    viewModel.getAllCategoriesByType(TransactionType.INCOME.ordinal()).observe(getViewLifecycleOwner(), categories -> {
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
                } else if (position == TransactionType.TRANSFER.ordinal()) {
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        EditText nameEdit = rootView.findViewById(R.id.nameEdit);
        EditText descriptionEdit = rootView.findViewById(R.id.descriptionEdit);
        EditText amountEdit = rootView.findViewById(R.id.amountEdit);

        // Currency to be done later

        EditText dateEdit = rootView.findViewById(R.id.dateEdit);
        dateEdit.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dateEdit.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime()));
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        EditText timeEdit = rootView.findViewById(R.id.timeEdit);
        timeEdit.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), (view, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                timeEdit.setText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.getTime()));
            }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

        Spinner frequencySpinner = rootView.findViewById(R.id.frequencyEdit);
        List<String> frequencyList = new ArrayList<>();
        frequencyList.add(getResources().getString(R.string.choose_frequency));
        frequencyList.addAll(Arrays.asList(getResources().getStringArray(R.array.frequency_option)));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, frequencyList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencySpinner.setAdapter(adapter);

        AtomicInteger lasting = new AtomicInteger();

        TextView infoLastingTitle = rootView.findViewById(R.id.infoLastingTitle);
        EditText infoLastingEdit = rootView.findViewById(R.id.infoLastingEdit);
        Button plusButton = rootView.findViewById(R.id.plusButton);
        plusButton.setOnClickListener(v -> {
            lasting.getAndIncrement();
            plusButton.setText(String.valueOf(lasting.get()));
        });
        Button minusButton = rootView.findViewById(R.id.minusButton);
        minusButton.setOnClickListener(v -> {
            lasting.getAndDecrement();
            minusButton.setText(String.valueOf(lasting.get()));
        });
        TextView infoRecurrentTitle = rootView.findViewById(R.id.infoRecurrentTitle);

        Spinner infoRecurrentEdit = rootView.findViewById(R.id.infoRecurrentEdit);
        List<String> infoRecurrentList = new ArrayList<>();
        infoRecurrentList.add(getResources().getString(R.string.choose_recurrency));
        infoRecurrentList.addAll(Arrays.asList(getResources().getStringArray(R.array.recurrency_option)));
        ArrayAdapter<String> infoRecurrentAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, infoRecurrentList);
        infoRecurrentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        infoRecurrentEdit.setAdapter(infoRecurrentAdapter);

        SwitchCompat notifySwitch = rootView.findViewById(R.id.notifySwitch);
        TextView notifyTitle = rootView.findViewById(R.id.notifyTitle);

        Spinner notifyEdit = rootView.findViewById(R.id.notifyEdit);
        List<String> notifyList = new ArrayList<>();
        notifyList.add(getResources().getString(R.string.choose_recurrency));
        notifyList.addAll(Arrays.asList(getResources().getStringArray(R.array.notify_option)));
        ArrayAdapter<String> notifyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, infoRecurrentList);
        notifyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notifyEdit.setAdapter(notifyAdapter);

        notifySwitch.setChecked(false);
        notifySwitch.setOnClickListener(v -> {
            if (notifySwitch.isChecked()) {
                notifyTitle.setVisibility(View.VISIBLE);
                notifyEdit.setVisibility(View.VISIBLE);
            } else {
                notifyTitle.setVisibility(View.GONE);
                notifyEdit.setVisibility(View.GONE);
            }
        });

        frequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == TransactionFrequency.ONCE.ordinal()) {
                    infoLastingTitle.setVisibility(View.GONE);
                    infoLastingEdit.setVisibility(View.GONE);
                    plusButton.setVisibility(View.GONE);
                    minusButton.setVisibility(View.GONE);
                    infoRecurrentTitle.setVisibility(View.GONE);
                    infoRecurrentEdit.setVisibility(View.GONE);
                    notifySwitch.setVisibility(View.GONE);
                }
                else if (position == TransactionFrequency.LASTING.ordinal()) {
                    infoLastingTitle.setVisibility(View.VISIBLE);
                    infoLastingEdit.setVisibility(View.VISIBLE);
                    infoLastingEdit.setText(String.valueOf(lasting.get()));
                    plusButton.setVisibility(View.VISIBLE);
                    minusButton.setVisibility(View.VISIBLE);
                    infoRecurrentTitle.setVisibility(View.GONE);
                    infoRecurrentEdit.setVisibility(View.GONE);
                    notifySwitch.setVisibility(View.GONE);
                }
                else if (position == TransactionFrequency.RECURRENT.ordinal()) {
                    infoLastingTitle.setVisibility(View.GONE);
                    infoLastingEdit.setVisibility(View.GONE);
                    plusButton.setVisibility(View.GONE);
                    minusButton.setVisibility(View.GONE);
                    infoRecurrentTitle.setVisibility(View.VISIBLE);
                    infoRecurrentEdit.setVisibility(View.VISIBLE);
                    notifySwitch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        EditText notesEdit = rootView.findViewById(R.id.notesEdit);
        ImageView pictureEdit = rootView.findViewById(R.id.pictureEdit);
        Button resetButton = rootView.findViewById(R.id.resetButton);
        Button galleryButton = rootView.findViewById(R.id.galleryButton);
        Button cameraButton = rootView.findViewById(R.id.cameraButton);

        resetButton.setOnClickListener(v -> {
            pictureEdit.setImageResource(R.drawable.picture_icon);
        });

        galleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        cameraButton.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });

        Spinner priorityEdit = rootView.findViewById(R.id.priorityEdit);
        List<String> priorityList = new ArrayList<>();
        priorityList.add(getResources().getString(R.string.choose_priority));
        priorityList.addAll(Arrays.asList(getResources().getStringArray(R.array.priority_option)));
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, infoRecurrentList);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notifyEdit.setAdapter(priorityAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(R.string.add_transaction);
        toolbar.inflateMenu(R.menu.menu_add_transaction);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_save) {
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