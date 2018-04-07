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
import me.guendouz.roomdatapersistence.entity.Recipe;

/**
 * A DAO class that provides CRUD operations on the Recipe table.
 */
@Dao
public interface RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAllRecipes(List<Recipe> recipes);

    @Update
    void updateRecipe(Recipe recipe);

    @Query("SELECT * FROM Recipe")
    LiveData<List<Recipe>> findAll();

    @Delete
    void deleteRecipe(Recipe recipe);

    @Query("DELETE FROM Recipe")
    void deleteAllRecipes();

}
