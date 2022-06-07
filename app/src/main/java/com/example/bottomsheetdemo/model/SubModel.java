package com.example.bottomsheetdemo.model;

public class SubModel {

    private String name;
    private boolean isSelected;
    private int selectedPageNumber = -1;

    public SubModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }





}
