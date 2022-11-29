package com.marco.finbill.ui.main.dialogs;

import static com.marco.finbill.ui.main.MainActivity.SHAREDPREFS;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.marco.finbill.R;
import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.PriorityType;
import com.marco.finbill.enums.TransactionType;
import com.marco.finbill.sql.category.Category;
import com.marco.finbill.sql.model.FinBillViewModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCategoryDialog extends DialogFragment {
    public static final String TAG = "AddCategoryDialog";
    private Toolbar toolbar;

    private CategoryType categoryType = CategoryType.DEFAULT;

    private FinBillViewModel viewModel;

    private ActivityResultLauncher<Intent> galleryLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;

    private Category category;

    private Spinner categoryTypeSpinner;
    private Spinner categoryIsChildOfSpinner;
    private ImageView pictureEdit;
    private boolean pictureSelected = false;
    private Spinner priorityEdit;
    private EditText nameEdit;
    private EditText descriptionEdit;
    private Object defaultImage;

    private SharedPreferences sharedPreferences;

    public AddCategoryDialog() {
    }

    public AddCategoryDialog(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public static AddCategoryDialog display(FragmentManager fragmentManager) {
        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
        addCategoryDialog.show(fragmentManager, TAG);
        return addCategoryDialog;
    }

    public static AddCategoryDialog display(FragmentManager fragmentManager, CategoryType categoryType) {
        AddCategoryDialog addCategoryDialog = new AddCategoryDialog(categoryType);
        addCategoryDialog.show(fragmentManager, TAG);
        return addCategoryDialog;
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
        sharedPreferences = requireActivity().getSharedPreferences(SHAREDPREFS, Activity.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_add_category, container, false);
        toolbar = rootView.findViewById(R.id.toolbarAddCategory);

        // CATEGORY TYPE

        categoryTypeSpinner = rootView.findViewById(R.id.categoryTypeEdit);
        List<String> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(0, getResources().getString(R.string.choose_category_type));
        categoryTypeList.addAll(Arrays.asList(getResources().getStringArray(R.array.category_types_option)));
        ArrayAdapter<String> categoryTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, categoryTypeList);
        categoryTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTypeSpinner.setAdapter(categoryTypeAdapter);

        if (categoryType == CategoryType.DEFAULT) {
            categoryTypeSpinner.setSelection(CategoryType.DEFAULT.ordinal());
        }
        else {
            categoryTypeSpinner.setSelection(categoryType.ordinal());
        }

        LinearLayout categoryLayout = rootView.findViewById(R.id.categoryLayout);
        categoryLayout.setVisibility(View.GONE);

        categoryTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    categoryLayout.setVisibility(View.GONE);
                } else {
                    categoryLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryLayout.setVisibility(View.GONE);
            }
        });

        pictureEdit = rootView.findViewById(R.id.pictureEdit);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int size = displayMetrics.widthPixels / 2;
        pictureEdit.getLayoutParams().width = size;
        pictureEdit.getLayoutParams().height = size;
        pictureEdit.setAdjustViewBounds(true);
        pictureEdit.setScaleType(ImageView.ScaleType.CENTER_CROP);

        categoryIsChildOfSpinner = rootView.findViewById(R.id.isChildOfEdit);
        List<String> categoryIsChildOfList = new ArrayList<>();
        categoryIsChildOfList.add(getResources().getString(R.string.choose_category_is_child_of));
        categoryIsChildOfList.add(getResources().getString(R.string.no_one));
        viewModel.getAllCategoriesByType(CategoryType.values()[categoryTypeSpinner.getSelectedItemPosition()]).observe(getViewLifecycleOwner(), categories -> {
            for (Category category : categories) {
                categoryIsChildOfList.add(category.getCategoryName());
            }
        });
        ArrayAdapter<String> categoryIsChildOfAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, categoryIsChildOfList);
        categoryIsChildOfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryIsChildOfSpinner.setAdapter(categoryIsChildOfAdapter);
        categoryIsChildOfSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 1) {
                    viewModel.getCategoryByName(categoryIsChildOfSpinner.getSelectedItem().toString()).observe(getViewLifecycleOwner(), parentCategory -> {
                        defaultImage = parentCategory.getCategoryImage();
                        if (defaultImage == null) {
                            defaultImage = R.drawable.money;
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // PICTURE

        Button resetButton = rootView.findViewById(R.id.resetButton);
        Button galleryButton = rootView.findViewById(R.id.galleryButton);
        Button cameraButton = rootView.findViewById(R.id.cameraButton);

        resetButton.setOnClickListener(v -> {
            if (defaultImage instanceof Integer) {
                pictureEdit.setImageResource((Integer) defaultImage);
            } else {
                pictureEdit.setImageURI((Uri) defaultImage);
            }
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
                category = new Category();
                if (!nameEdit.getText().toString().isEmpty()) {
                    category.setCategoryName(nameEdit.getText().toString());
                }
                if (!descriptionEdit.getText().toString().isEmpty()) {
                    category.setCategoryDescription(descriptionEdit.getText().toString());
                }
                if (categoryTypeSpinner.getSelectedItemPosition() != 0) {
                    category.setCategoryType(CategoryType.values()[categoryTypeSpinner.getSelectedItemPosition()]);
                }
                if (pictureSelected) {
                    category.setCategoryImage(((BitmapDrawable)pictureEdit.getDrawable()).getBitmap());
                }
                if (priorityEdit.getSelectedItemPosition() != 0) {
                    category.setCategoryPriority(PriorityType.values()[priorityEdit.getSelectedItemPosition()]);
                }
                if (categoryIsChildOfSpinner.getSelectedItemPosition() > 1) {
                    viewModel.getCategoryByName(categoryIsChildOfSpinner.getSelectedItem().toString()).observe(getViewLifecycleOwner(), parentCategory -> {
                        category.setCategoryIsChildOf(parentCategory.getCategoryId());
                    });
                }
                if (category.isValid()) {
                    viewModel.getCategoryByName(category.getCategoryName()).observe(getViewLifecycleOwner(), query -> {
                        if (query == null) {
                            category.setCategoryAdded(new Date(System.currentTimeMillis()));
                            category.setCategoryBalanceCurrency(sharedPreferences.getString("currency", null));
                            viewModel.insertCategory(category);
                            dismiss();
                        } else {
                            Snackbar.make(requireContext(), view, getResources().getString(R.string.category_already_exists), Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Snackbar.make(view, R.string.incomplete_fields, Snackbar.LENGTH_LONG).show();
                }
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
