package com.example.bottomsheetdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GiftListFragment  extends Fragment {

    public static final String ARG_OBJECT = "object";

    private RecyclerView rvGifts;
    private GiftAdapter giftAdapter;
    private ArrayList<GiftModel> alGifts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gift_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        Log.d("==>  " , "onViewCreated: ??? " + Integer.toString(args.getInt(ARG_OBJECT)) );
        for (int i = 0; i < 10; i++) {
            alGifts.add(new GiftModel("Position " + i, false,-1));
        }

        rvGifts = view.findViewById(R.id.rvGifts);
        giftAdapter = new GiftAdapter(alGifts, new GiftAdapter.GiftSelectListner() {
            @Override
            public void onSelectedPosition(int position) {
                if(alGifts.get(position).isSelected()) {
                    alGifts.get(position).setSelected(false);
                } else {
                    for (int i = 0; i < alGifts.size(); i++) {
                        alGifts.get(i).setSelected(false);
                    }
                    alGifts.get(position).setSelected(true);
                }
                giftAdapter.notifyDataSetChanged();
            }
        });
        rvGifts.setAdapter(giftAdapter);
    }
}