package com.example.bottomsheetdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.adapter.GiftAdapter;
import com.example.bottomsheetdemo.listner.MyTabListner;
import com.example.bottomsheetdemo.model.SubModel;

import java.util.ArrayList;
import java.util.List;

public class GiftListFragment  extends Fragment {

    public static final String ARG_OBJECT = "object";

    private RecyclerView rvGifts;
    private GiftAdapter giftAdapter;
    private List<SubModel> alGifts = new ArrayList<>();
    private int mainArrayPosition = 0;
    private int sublistPosition = 0;
    private MyTabListner callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gift_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("==>"," main array position  " + mainArrayPosition + " sub array position " + sublistPosition );

        for (int i = 0; i < alGifts.size(); i++) {
            alGifts.get(i).setSelected(false);
        }
        giftAdapter.notifyDataSetChanged();

//        if(mainArrayPosition == BottomSheetDialog.MAIN_ARRAY_SELECTION && sublistPosition == BottomSheetDialog.SUB_ARRAY_SELECTION) {
//            if(alGifts.get(BottomSheetDialog.ADAPTER_POSITION).isSelected()) {
//                alGifts.get(BottomSheetDialog.ADAPTER_POSITION).setSelected(false);
//                giftAdapter.notifyDataSetChanged();
//            }
//        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        alGifts.addAll((ArrayList<SubModel>)getArguments().getSerializable("sublist"));
        mainArrayPosition = getArguments().getInt("main_position");
        sublistPosition = getArguments().getInt("sub_list_position");
        callback = (MyTabListner) getArguments().getSerializable("listner");


//        Log.d("==>"," main array position  " + mainArrayPosition + " sub array position " + sublistPosition );
//        Log.d("==>"," isSelected() in zero position ???? " + alGifts.get(0).isSelected() );

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
                callback.onClick(mainArrayPosition,sublistPosition,position, true);
                giftAdapter.notifyDataSetChanged();
            }
        });
        rvGifts.setAdapter(giftAdapter);
    }
}