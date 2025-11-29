package com.example.recycler;

public class Item {
    private int imageResId;
    private String name;
    private int number;

    public Item(int imageResId, String name, int number) {
        this.imageResId = imageResId;
        this.name = name;
        this.number = number;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
