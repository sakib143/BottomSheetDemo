package com.example.bottomsheetdemo;

public class GiftModel {

    private boolean isSelected;
    private int selectedPageNumber = -1;
    private String imageUrl;

    public GiftModel(String imageUrl, boolean isSelected, int selectedPageNumber) {
        this.imageUrl = imageUrl;
        this.isSelected = isSelected;
        this.selectedPageNumber = selectedPageNumber;
    }

    public int getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public void setSelectedPageNumber(int selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
