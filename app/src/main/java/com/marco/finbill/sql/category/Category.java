package com.marco.finbill.sql.category;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.enums.PriorityType;

import java.sql.Date;

@Entity(tableName = "category_table", indices = {@Index(value = {"categoryName"}, unique = true)})
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private CategoryType categoryType;
    private Bitmap categoryImage;
    private Date categoryAdded;
    private int categoryIsChildOf;
    private PriorityType categoryPriority;

    public Category(String categoryName, String categoryDescription, CategoryType categoryType, Bitmap categoryImage, Date categoryAdded, int categoryIsChildOf, PriorityType categoryPriority) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryType = categoryType;
        this.categoryImage = categoryImage;
        this.categoryAdded = categoryAdded;
        this.categoryIsChildOf = categoryIsChildOf;
        this.categoryPriority = categoryPriority;
    }

    public Category() {
        this.categoryName = null;
        this.categoryDescription = null;
        this.categoryType = null;
        this.categoryImage = null;
        this.categoryAdded = null;
        this.categoryIsChildOf = 0;
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

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public Bitmap getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Bitmap categoryImage) {
        this.categoryImage = categoryImage;
    }

    public Date getCategoryAdded() {
        return categoryAdded;
    }

    public void setCategoryAdded(Date categoryAdded) {
        this.categoryAdded = categoryAdded;
    }

    public int getCategoryIsChildOf() {
        return categoryIsChildOf;
    }

    public void setCategoryIsChildOf(int categoryIsChildOf) {
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
                this.categoryDescription.equals(category.getCategoryDescription()) &&
                this.categoryType == category.getCategoryType() &&
                this.categoryImage.equals(category.getCategoryImage()) &&
                this.categoryAdded.equals(category.getCategoryAdded()) &&
                this.categoryIsChildOf == category.getCategoryIsChildOf() &&
                this.categoryPriority == category.getCategoryPriority();
    }

    public boolean isValid() {
        return this.categoryName != null &&
                this.categoryType != null;
    }
}
