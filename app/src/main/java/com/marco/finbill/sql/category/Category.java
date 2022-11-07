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
    private String name;
    private Currency currency;
    private String description;
    private int type;
    private Bitmap image;
    private Date added;
    private int isChildOf;
    private int priority;

    private enum Type {
        EXPENSE, INCOME
    }

    public Category(String name, Currency currency, String description, int type, Bitmap image, Date added, int isChildOf, int priority) {
        this.name = name;
        this.currency = currency;
        this.description = description;
        this.type = type;
        this.image = image;
        this.added = added;
        this.isChildOf = isChildOf;
        this.priority = priority;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public int getIsChildOf() {
        return isChildOf;
    }

    public void setIsChildOf(int isChildOf) {
        this.isChildOf = isChildOf;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
