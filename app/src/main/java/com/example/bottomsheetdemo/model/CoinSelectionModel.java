package com.example.bottomsheetdemo.model;

public class CoinSelectionModel {

    private String name;
    private int position;
    private boolean isSelected;

    public CoinSelectionModel(String name, int position, boolean isSelected) {
        this.name = name;
        this.position = position;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
