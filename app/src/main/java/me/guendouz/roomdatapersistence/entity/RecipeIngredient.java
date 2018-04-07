package me.guendouz.roomdatapersistence.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

/**
 * Entity class to represent a joint table between Recipe and Ingredient tables.
 */
@Entity(
        primaryKeys = {"recipeId", "ingredientId"},
        foreignKeys = {
                @ForeignKey(entity = Recipe.class,
                        parentColumns = {"id"},
                        childColumns = {"recipeId"},
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Ingredient.class,
                        parentColumns = {"id"},
                        childColumns = {"ingredientId"},
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)
        }
)
public class RecipeIngredient {

    private int recipeId;
    private int ingredientId;

    public RecipeIngredient() {
    }

    public RecipeIngredient(int recipeId, int ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}
