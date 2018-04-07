package me.guendouz.roomdatapersistence.database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import me.guendouz.roomdatapersistence.dao.IngredientDAO;
import me.guendouz.roomdatapersistence.dao.RecipeDAO;
import me.guendouz.roomdatapersistence.dao.RecipeIngredientDAO;
import me.guendouz.roomdatapersistence.entity.Ingredient;
import me.guendouz.roomdatapersistence.entity.Recipe;
import me.guendouz.roomdatapersistence.entity.RecipeIngredient;

/**
 * This class is the main entry point for all other classes in the application,
 * it extends the RoomDatabase class to provide database access methods.
 * Note that we are using a Room Callback to populate the database with data after the database is created.
 */
@Database(entities = {Recipe.class, Ingredient.class, RecipeIngredient.class}, exportSchema = false, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    private final static List<Recipe> RECIPES = Arrays.asList(
            new Recipe("Cookies", "A chocolate chip cookie is a drop cookie that originated in the United States and features chocolate chips (small morsels of sweetened chocolate) as its distinguishing ingredient."),
            new Recipe("Brownies", "A brownie is a square, baked, chocolate dessert. Brownies come in a variety of forms and may be either fudgy or cakey, depending on their density. They may include nuts, frosting, cream cheese, chocolate chips, or other ingredients."),
            new Recipe("Cupcakes", "A cupcake is a small cake designed to serve one person, which may be baked in a small thin paper or aluminum cup.")
    );

    private final static List<Ingredient> INGREDIENTS = Arrays.asList(
            new Ingredient("Flour"),
            new Ingredient("Butter"),
            new Ingredient("White Sugar"),
            new Ingredient("Egg"),
            new Ingredient("Baking Soda"),
            new Ingredient("Milk")
    );

    private final static List<RecipeIngredient> RECIPE_INGREDIENTS = Arrays.asList(
            new RecipeIngredient(1, 1),
            new RecipeIngredient(1, 2),
            new RecipeIngredient(1, 3),
            new RecipeIngredient(2, 3),
            new RecipeIngredient(2, 6),
            new RecipeIngredient(2, 4),
            new RecipeIngredient(3, 1),
            new RecipeIngredient(3, 2),
            new RecipeIngredient(3, 5)
    );

    private static RecipeDatabase INSTANCE;

    public abstract RecipeDAO recipeDAO();

    public abstract IngredientDAO ingredientDAO();

    public abstract RecipeIngredientDAO recipeIngredientDAO();

    public static RecipeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, "recipes_db")
                            // Populate the database after its creation
                            .addCallback(populateDatabase)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback populateDatabase = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Executors.newSingleThreadExecutor().execute(() -> {
                INSTANCE.recipeDAO().addAllRecipes(RECIPES);
                INSTANCE.ingredientDAO().addAllIngredients(INGREDIENTS);
                INSTANCE.recipeIngredientDAO().addAllRecipeIngredients(RECIPE_INGREDIENTS);
            });
        }
    };
}
