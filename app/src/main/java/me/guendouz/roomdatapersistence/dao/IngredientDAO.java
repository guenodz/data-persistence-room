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

/**
 * A DAO class that provides CRUD operations on the Ingredient table.
 */
@Dao
public interface IngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addIngredient(Ingredient ingredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllIngredients(List<Ingredient> ingredients);

    @Update
    void updateIngredient(Ingredient ingredient);

    @Query("SELECT * FROM Ingredient")
    LiveData<List<Ingredient>> findAll();

    @Query("SELECT * FROM Ingredient INNER JOIN RecipeIngredient ON " +
            "Ingredient.id=RecipeIngredient.ingredientId WHERE " +
            "RecipeIngredient.recipeId = :recipeId")
    LiveData<List<Ingredient>> findRecipeIngredients(int recipeId);

    @Delete
    void deleteIngredient(Ingredient ingredient);

}
