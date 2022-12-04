package com.marco.finbill.ui.main.dialogs;

import static com.marco.finbill.ui.main.MainActivity.SHAREDPREFS;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.marco.finbill.R;
import com.marco.finbill.enums.AccountType;
import com.marco.finbill.enums.PriorityType;
import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.currency.Currency;
import com.marco.finbill.model.FinBillViewModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AddAccountDialog extends DialogFragment {

    public static final String TAG = "AddAccountDialog";
    private Toolbar toolbar;

    private FinBillViewModel viewModel;

    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;

    private Account account;

    private Spinner accountTypeSpinner;
    private TextInputEditText dateEdit;
    private ImageView pictureEdit;
    private boolean pictureSelected = false;
    private Spinner priorityEdit;
    private TextInputEditText nameEdit;
    private TextInputEditText descriptionEdit;
    private TextInputEditText balanceEdit;
    private TextInputEditText platfondEdit;
    private Spinner currencyBalanceEdit;
    private Spinner currencyPlatfondEdit;

    private SharedPreferences preferences;

    public static void display(FragmentManager fragmentManager) {
        AddAccountDialog addAccountDialog = new AddAccountDialog();
        addAccountDialog.show(fragmentManager, TAG);
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
        View rootView = inflater.inflate(R.layout.fragment_add_account, container, false);
        toolbar = rootView.findViewById(R.id.toolbarAddAccount);

        // ACCOUNT TYPE

        accountTypeSpinner = rootView.findViewById(R.id.accountTypeEdit);
        List<String> accountTypeList = new ArrayList<>();
        accountTypeList.add(0, getResources().getString(R.string.choose_account_type));
        accountTypeList.addAll(Arrays.asList(getResources().getStringArray(R.array.account_types_option)));
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, accountTypeList);
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(accountTypeAdapter);

        LinearLayout accountLayout = rootView.findViewById(R.id.accountLayout);
        accountLayout.setVisibility(View.GONE);

        accountTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    accountLayout.setVisibility(View.GONE);
                } else {
                    accountLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                accountLayout.setVisibility(View.GONE);
            }
        });

        // CURRENCY

        List<String> currencyStringList = new ArrayList<>();
        ArrayAdapter<String> currencyStringAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, currencyStringList);
        currencyStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currencyBalanceEdit = rootView.findViewById(R.id.currencyBalanceEdit);
        currencyBalanceEdit.setAdapter(currencyStringAdapter);
        currencyPlatfondEdit = rootView.findViewById(R.id.currencyPlatfondEdit);
        currencyPlatfondEdit.setAdapter(currencyStringAdapter);

        viewModel.getAllCurrencies().observe(getViewLifecycleOwner(), currencies -> {
            currencyStringList.clear();
            currencyStringList.add(0, getResources().getString(R.string.choose_currency));
            for (Currency currency : currencies) {
                currencyStringList.add(currency.getCurrencyString());
            }
            currencyStringAdapter.notifyDataSetChanged();
            String choice = preferences.getString("currency", null);
            if (choice != null) {
                int currencyPosition = currencyStringList.indexOf(choice);
                currencyPlatfondEdit.setSelection(currencyPosition);
                currencyBalanceEdit.setSelection(currencyPosition);
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

        nameEdit = rootView.findViewById(R.id.nameEdit);
        descriptionEdit = rootView.findViewById(R.id.descriptionEdit);
        balanceEdit = rootView.findViewById(R.id.balanceEdit);
        platfondEdit = rootView.findViewById(R.id.platfondEdit);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(R.string.add_account);
        toolbar.inflateMenu(R.menu.menu_add_item);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_save) {
                account = new Account();
                if (nameEdit.getText() != null && !nameEdit.getText().toString().isEmpty()) {
                    account.setAccountName(nameEdit.getText().toString());
                }
                if (descriptionEdit.getText() != null && !descriptionEdit.getText().toString().isEmpty()) {
                    account.setAccountDescription(descriptionEdit.getText().toString());
                }
                if (accountTypeSpinner.getSelectedItemPosition() != 0) {
                    account.setAccountType(AccountType.values()[accountTypeSpinner.getSelectedItemPosition()]);
                }
                if (balanceEdit.getText() != null && !balanceEdit.getText().toString().isEmpty()) {
                    account.setAccountBalance(Double.parseDouble(balanceEdit.getText().toString()));
                }
                if (currencyBalanceEdit.getSelectedItemPosition() != 0) {
                    account.setAccountBalanceCurrencyId(0); // temporary value
                }
                if (platfondEdit.getText() != null && !platfondEdit.getText().toString().isEmpty()) {
                    account.setAccountPlatfond(Double.parseDouble(platfondEdit.getText().toString()));
                }
                if (currencyPlatfondEdit.getSelectedItemPosition() != 0) {
                    account.setAccountPlatfondCurrencyId(0); // temporary value
                }
                if (dateEdit.getText() != null && !dateEdit.getText().toString().isEmpty()) {
                    account.setAccountCreated(Date.valueOf(dateEdit.getText().toString()));
                }
                if (pictureSelected) {
                    account.setAccountImage(((BitmapDrawable)pictureEdit.getDrawable()).getBitmap());
                }
                if (priorityEdit.getSelectedItemPosition() != 0) {
                    account.setAccountPriority(PriorityType.values()[priorityEdit.getSelectedItemPosition()]);
                }
                if (account.isValid()) {
                    viewModel.getCurrencyByString(currencyBalanceEdit.getSelectedItem().toString()).observe(getViewLifecycleOwner(), currency -> {
                        if (currency != null) {
                            viewModel.pushAccountFieldBalance(currency.getCurrencyId());
                        }
                    });
                    viewModel.getCurrencyByString(currencyPlatfondEdit.getSelectedItem().toString()).observe(getViewLifecycleOwner(), currency -> {
                        if (currency != null) {
                            viewModel.pushAccountFieldPlatfond(currency.getCurrencyId());
                        }
                    });
                    viewModel.getAccountByName(account.getAccountName()).observe(getViewLifecycleOwner(), query -> {
                        if (query == null) {
                            viewModel.pushAccountFieldProceed(true);
                        } else {
                            Snackbar.make(requireContext(), view, getResources().getString(R.string.account_already_exists), Snackbar.LENGTH_LONG).show();
                        }
                    });
                    viewModel.pullAccountFieldsLiveData().observe(getViewLifecycleOwner(), fields -> {
                        if (fields.isValid()) {
                            account.setAccountBalanceCurrencyId(fields.getBalanceCurrencyId());
                            account.setAccountPlatfondCurrencyId(fields.getPlatfondCurrencyId());
                            account.setAccountAdded(new Date(System.currentTimeMillis()));
                            viewModel.insertAccount(account);
                            viewModel.popAccountFields();
                        }
                    });
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
