package com.example.bottomsheetdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.fragment.BottomSheetDialog;
import com.example.bottomsheetdemo.model.GiftModel;
import com.example.bottomsheetdemo.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button OpenBottomSheet = findViewById(R.id.open_bottom_sheet);

        OpenBottomSheet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
                        bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                    }
                });
    }
}