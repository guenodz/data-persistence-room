package me.guendouz.roomdatapersistence.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity class to represent the Ingredient table.
 */
@Entity
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
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
}
