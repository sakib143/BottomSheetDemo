package com.example.bottomsheetdemo.model;

import java.util.ArrayList;

public class MainModel {

    private String tabTitle;
    private ArrayList<SubModel> alSubList;

    public MainModel(ArrayList<SubModel> alSubList,String tabTitle) {
        this.alSubList = alSubList;
        this.tabTitle = tabTitle;
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
