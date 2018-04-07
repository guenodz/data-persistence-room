package me.guendouz.roomdatapersistence.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import me.guendouz.roomdatapersistence.entity.Ingredient;
import me.guendouz.roomdatapersistence.entity.RecipeIngredient;

/**
 * A DAO class that provides CRUD operations on the RecipeIngredient joint table.
 */
@Dao
public interface RecipeIngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addRecipeIngredient(RecipeIngredient recipeIngredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllRecipeIngredients(List<RecipeIngredient> recipeIngredients);

    @Update
    void updateRecipeIngredient(RecipeIngredient recipeIngredient);

    @Query("SELECT * FROM RecipeIngredient")
    LiveData<List<RecipeIngredient>> findAll();

    @Delete
    void deleteIngredient(RecipeIngredient recipeIngredient);

}
