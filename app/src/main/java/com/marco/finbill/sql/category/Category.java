package com.marco.finbill.sql.category;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.PriorityType;

import java.util.Date;

@Entity(tableName = "category_table", indices = {@Index(value = {"categoryName"}, unique = true)})
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryName;
    @Nullable
    private String categoryDescription;
    private CategoryType categoryType;
    @Nullable
    private Bitmap categoryImage;
    private Date categoryAdded;
    private Double categoryBalance;
    private String categoryBalanceCurrency;
    @Nullable
    private Integer categoryIsChildOf;
    private PriorityType categoryPriority;

    public Category(String categoryName, @Nullable String categoryDescription, CategoryType categoryType, @Nullable Bitmap categoryImage, Date categoryAdded, Double categoryBalance, String categoryBalanceCurrency, @Nullable Integer categoryIsChildOf, PriorityType categoryPriority) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryType = categoryType;
        this.categoryImage = categoryImage;
        this.categoryAdded = categoryAdded;
        this.categoryBalance = categoryBalance;
        this.categoryBalanceCurrency = categoryBalanceCurrency;
        this.categoryIsChildOf = categoryIsChildOf;
        this.categoryPriority = categoryPriority;
    }

    public Category() {
        this.categoryName = null;
        this.categoryDescription = null;
        this.categoryType = null;
        this.categoryImage = null;
        this.categoryAdded = null;
        this.categoryBalance = (double) 0;
        this.categoryBalanceCurrency = null;
        this.categoryIsChildOf = Integer.MIN_VALUE;
        this.categoryPriority = null;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Nullable
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(@Nullable String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Nullable
    public Bitmap getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(@Nullable Bitmap categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Date getCategoryAdded() {
        return categoryAdded;
    }

    public void setCategoryAdded(Date categoryAdded) {
        this.categoryAdded = categoryAdded;
    }

    public Double getCategoryBalance() {
        return categoryBalance;
    }

    public void setCategoryBalance(Double categoryBalance) {
        this.categoryBalance = categoryBalance;
    }

    public String getCategoryBalanceCurrency() {
        return categoryBalanceCurrency;
    }

    public void setCategoryBalanceCurrency(String categoryBalanceCurrency) {
        this.categoryBalanceCurrency = categoryBalanceCurrency;
    }

    @Nullable
    public Integer getCategoryIsChildOf() {
        return categoryIsChildOf;
    }

    public void setCategoryIsChildOf(@Nullable Integer categoryIsChildOf) {
        this.categoryIsChildOf = categoryIsChildOf;
    }

    public PriorityType getCategoryPriority() {
        return categoryPriority;
    }

    public void setCategoryPriority(PriorityType categoryPriority) {
        this.categoryPriority = categoryPriority;
    }

    public boolean equals(Category category) {
        return this.categoryId == category.getCategoryId() &&
                this.categoryName.equals(category.getCategoryName()) &&
                (this.categoryDescription == null && category.getCategoryDescription() == null || this.categoryDescription.equals(category.getCategoryDescription())) &&
                this.categoryType == category.getCategoryType() &&
                (this.categoryImage == null && category.getCategoryImage() == null || this.categoryImage.equals(category.getCategoryImage())) &&
                this.categoryAdded.equals(category.getCategoryAdded()) &&
                (this.categoryIsChildOf == null && category.getCategoryIsChildOf() == null || this.categoryIsChildOf.equals(category.getCategoryIsChildOf())) &&
                this.categoryPriority == category.getCategoryPriority();
    }

    public boolean isValid() {
        return this.categoryName != null &&
                this.categoryType != null;
    }
}
