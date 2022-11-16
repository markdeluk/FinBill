package com.marco.finbill.sql.category;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.icu.util.Currency;
import android.media.Image;
import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "category_table")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private int categoryType;
    private Bitmap categoryImage;
    private Date categoryAdded;
    private int categoryIsChildOf;
    private int categoryPriority;

    public Category(String categoryName, String categoryDescription, int categoryType, Bitmap categoryImage, Date categoryAdded, int categoryIsChildOf, int categoryPriority) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryType = categoryType;
        this.categoryImage = categoryImage;
        this.categoryAdded = categoryAdded;
        this.categoryIsChildOf = categoryIsChildOf;
        this.categoryPriority = categoryPriority;
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

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
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

    public int getCategoryPriority() {
        return categoryPriority;
    }

    public void setCategoryPriority(int categoryPriority) {
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
}
