package com.example.bottomsheetdemo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.listner.MyTabListner;
import com.example.bottomsheetdemo.model.SubModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutFragment  extends Fragment  {

    private ArrayList<SubModel> alSub = new ArrayList<>();
    private List<List<SubModel>> alSplitList = new ArrayList<>();
    private int partitionSize = 8;
    private int mainArrayPosition = 0;
    private MyTabListner callback;
    private List<GiftListFragment> alGiftsFragment = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        alSub.addAll((ArrayList<SubModel>)getArguments().getSerializable("sublist"));
        mainArrayPosition = getArguments().getInt("main_position");
        callback = (MyTabListner) getArguments().getSerializable("listner");

        alSplitList = splitArrayList(alSub,partitionSize);

        for (int i = 0; i < alSplitList.size(); i++) {
//            Log.d("TAG ==> ", "createFragment: position is in loop ???" + i );
            alGiftsFragment.add(new GiftListFragment());
            DemoCollectionAdapter demoCollectionAdapter = new DemoCollectionAdapter(TabLayoutFragment.this);

            ViewPager2  viewPager = view.findViewById(R.id.pager);
            viewPager.setOffscreenPageLimit(1);
            viewPager.setAdapter(demoCollectionAdapter);

            TabLayout into_tab_layout = view.findViewById(R.id.into_tab_layout);
            new TabLayoutMediator(into_tab_layout,viewPager, (tab, position) -> tab.setText("")).attach();
        }
    }

    public class DemoCollectionAdapter extends FragmentStateAdapter {

        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            GiftListFragment fragment = alGiftsFragment.get(position);
            Bundle args = new Bundle();
            args.putSerializable("sublist", new ArrayList<SubModel>(alSplitList.get(position)));
            args.putSerializable("main_position",mainArrayPosition);
            args.putSerializable("sub_list_position", position);
            args.putSerializable("listner",callback);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return alSplitList.size();
        }
    }

    //Get sublist from main list
    public  <T> List<List<T>> splitArrayList(List<T> list, int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("Invalid chunk size: " + chunkSize);
        }
        List<List<T>> chunkList = new ArrayList<>(list.size() / chunkSize);
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunkList.add(list.subList(i, i + chunkSize >= list.size() ? list.size()-1 : i + chunkSize));
        }
        return chunkList;
    }

    public void callUpdatePrice(String count) {
        alGiftsFragment.get(0).callUpdatePrice(count);
    }

}
