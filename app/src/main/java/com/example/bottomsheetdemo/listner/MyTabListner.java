package com.example.bottomsheetdemo.listner;

import java.io.Serializable;

public interface MyTabListner extends Serializable {
    void onClick(int mainArrayPosition, int subArrayPosition, int adapterPosition, boolean isSelected);
}
