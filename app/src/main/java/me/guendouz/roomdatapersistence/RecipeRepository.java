package me.guendouz.roomdatapersistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.guendouz.roomdatapersistence.dao.IngredientDAO;
import me.guendouz.roomdatapersistence.dao.RecipeDAO;
import me.guendouz.roomdatapersistence.database.RecipeDatabase;
import me.guendouz.roomdatapersistence.entity.Ingredient;
import me.guendouz.roomdatapersistence.entity.Recipe;

/**
 * A Repository class to encapsulate CRUD operations in one place.
 * Other classes will use this class instead of accessing directly the data using DAO classes
 */
public class RecipeRepository {

    private RecipeDAO recipeDAO;
    private IngredientDAO ingredientDAO;

    private ExecutorService executorService;

    public RecipeRepository(Context context) {
        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(context);
        recipeDAO = recipeDatabase.recipeDAO();
        ingredientDAO = recipeDatabase.ingredientDAO();

        executorService = Executors.newSingleThreadExecutor();
    }

    public void addRecipe(Recipe recipe) {
        executorService.execute(() -> recipeDAO.addRecipe(recipe));
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeDAO.findAll();
    }

    public LiveData<List<Ingredient>> getRecipeIngredients(int recipeId) {
        return ingredientDAO.findRecipeIngredients(recipeId);
    }

    public void deleteRecipe(Recipe recipe){
        executorService.execute(() -> recipeDAO.deleteRecipe(recipe));
    }
}
