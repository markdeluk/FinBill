package com.marco.finbill.sql.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import com.marco.finbill.enums.CategoryType;
import com.marco.finbill.sql.account.Account;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insertCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Query("SELECT * FROM category_table WHERE categoryId = :id")
    LiveData<Category> getCategoryId(int id);

    @Query("SELECT * FROM category_table WHERE categoryName = :name")
    LiveData<Category> getCategoryName(String name);

    @Query("SELECT * FROM category_table ORDER BY categoryName ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM category_table WHERE categoryType = :type ORDER BY categoryName ASC")
    LiveData<List<Category>> getAllCategoriesByType(CategoryType type);

    @Query("SELECT * FROM category_table WHERE categoryName LIKE '%' || :query || '%'")
    LiveData<List<Category>> searchCategory(String query);

    @Query("DELETE FROM category_table")
    void deleteAllCategories();


}
