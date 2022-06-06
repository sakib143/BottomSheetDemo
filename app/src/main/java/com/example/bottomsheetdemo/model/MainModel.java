package com.example.bottomsheetdemo.model;

import java.util.ArrayList;

public class MainModel {
    private ArrayList<SubModel> alSubList;

    public MainModel(ArrayList<SubModel> alSubList) {
        this.alSubList = alSubList;
    }

    public ArrayList<SubModel> getalSubList() {
        return alSubList;
    }

    public void setalSubList(ArrayList<SubModel> alMain) {
        this.alSubList = alMain;
    }
}
