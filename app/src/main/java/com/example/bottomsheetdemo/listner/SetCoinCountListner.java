package com.example.bottomsheetdemo.listner;

import java.io.Serializable;

public interface SetCoinCountListner extends Serializable {
    void setCount(String count, int tabPosition, int indicatorPosition, int adapterPosition);
}
