package com.example.bottomsheetdemo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class MainModel implements Serializable {

    private String tabTitle;
    private ArrayList<SubModel> alSubList;
    private int mainPosition;

    public int getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(int mainPosition) {
        this.mainPosition = mainPosition;
    }

    public MainModel(ArrayList<SubModel> alSubList,String tabTitle, int mainPosition) {
        this.alSubList = alSubList;
        this.tabTitle = tabTitle;
        this.mainPosition = mainPosition;
    }

    public MainModel(ArrayList<SubModel> alSubList) {
        this.alSubList = alSubList;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public ArrayList<SubModel> getalSubList() {
        return alSubList;
    }

    public void setalSubList(ArrayList<SubModel> alMain) {
        this.alSubList = alMain;
    }
}
