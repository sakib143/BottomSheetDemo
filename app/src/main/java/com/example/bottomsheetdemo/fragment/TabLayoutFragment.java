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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.adapter.GiftAdapter;
import com.example.bottomsheetdemo.model.GiftModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class TabLayoutFragment  extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        DemoCollectionAdapter demoCollectionAdapter = new DemoCollectionAdapter(this);
        ViewPager2  viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionAdapter);

        TabLayout into_tab_layout = view.findViewById(R.id.into_tab_layout);

        new TabLayoutMediator(into_tab_layout,viewPager, (tab, position) -> tab.setText("")).attach();
    }

    public class DemoCollectionAdapter extends FragmentStateAdapter {

        public DemoCollectionAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
            Fragment fragment = new GiftListFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(GiftListFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

}
