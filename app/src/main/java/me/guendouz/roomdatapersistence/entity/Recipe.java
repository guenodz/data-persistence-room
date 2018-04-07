package me.guendouz.roomdatapersistence.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity class to represent the Recipe table, a recipe has a name and a description.
 */
@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;

    public Recipe() {
    }

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
