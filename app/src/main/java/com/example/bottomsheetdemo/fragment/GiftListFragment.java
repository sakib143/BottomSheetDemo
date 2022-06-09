package com.example.bottomsheetdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.adapter.GiftAdapter;
import com.example.bottomsheetdemo.listner.MyTabListner;
import com.example.bottomsheetdemo.listner.SetCoinCountListner;
import com.example.bottomsheetdemo.model.SubModel;

import java.util.ArrayList;
import java.util.List;

public class GiftListFragment  extends Fragment {

    public static final String ARG_OBJECT = "object";

    private RecyclerView rvGifts;
    private GiftAdapter giftAdapter;
    private List<SubModel> alGifts = new ArrayList<>();
    private int currentTabPosition = 0;
    private int currentItemPosition = 0;
    private MyTabListner callback;
    private SetCoinCountListner countListner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gift_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        int selectedTabPosition = BottomSheetDialog.MAIN_ARRAY_SELECTION;
        int selectedItemPosition = BottomSheetDialog.SUB_ARRAY_SELECTION;

        Log.d("==>"," Tab Array position  " + currentTabPosition + " Item array position " + currentItemPosition );
        Log.d("==>"," Selected Tab position  " + selectedTabPosition + " Selected Item Araay position " + selectedItemPosition );

        for (int i = 0; i < alGifts.size(); i++) {
            if (alGifts.get(i).isSelected()) {
                if(currentTabPosition != selectedTabPosition) {
                    alGifts.get(i).setSelected(false);
                } else if(selectedItemPosition != currentItemPosition) {
                    alGifts.get(i).setSelected(false);
                }
            }
        }
        giftAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        alGifts.addAll((ArrayList<SubModel>)getArguments().getSerializable("sublist"));
        currentTabPosition = getArguments().getInt("main_position");
        currentItemPosition = getArguments().getInt("sub_list_position");
        callback = (MyTabListner) getArguments().getSerializable("listner");
        countListner = (SetCoinCountListner) getArguments().getSerializable("count_listner");


        countListner = new SetCoinCountListner() {
            @Override
            public void setCount(String count, int tabPosition, int indicatorPosition, int adapterPosition) {

                Toast.makeText(getActivity(), "Gift List Fragment is calling !!!! ", Toast.LENGTH_SHORT).show();

//                alGifts.get(adapterPosition).setTotalCounts(count);
//                giftAdapter.notifyDataSetChanged();
            }
        };

//        callback.onClick(mainArrayPosition,0,0, true);

//        Log.d("==>"," main array position  " + mainArrayPosition + " sub array position " + sublistPosition );
//        Log.d("==>"," isSelected() in zero position ???? " + alGifts.get(0).isSelected() );

        rvGifts = view.findViewById(R.id.rvGifts);
        giftAdapter = new GiftAdapter(alGifts, new GiftAdapter.GiftSelectListner() {
            @Override
            public void onSelectedPosition(int position) {
                if(alGifts.get(position).isSelected()) {
                    alGifts.get(position).setSelected(false);
                    callback.onClick(-1, -1, -1, true);
                } else {
                    for (int i = 0; i < alGifts.size(); i++) {
                        alGifts.get(i).setSelected(false);
                    }
                    alGifts.get(position).setSelected(true);
                    callback.onClick(currentTabPosition,currentItemPosition,position, true);
                }
                giftAdapter.notifyDataSetChanged();
            }
        });
        rvGifts.setAdapter(giftAdapter);
    }
}