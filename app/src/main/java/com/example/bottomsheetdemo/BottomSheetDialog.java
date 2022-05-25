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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BottomSheetDialog  extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        TabLayout into_tab_layout = v.findViewById(R.id.into_tab_layout);

        DemoCollectionAdapter demoCollectionAdapter = new DemoCollectionAdapter(this);

        ViewPager2  viewPager = v.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("OBJECT " + (position + 1))).attach();

        new TabLayoutMediator(into_tab_layout,viewPager, (tab, position) -> tab.setText("")).attach();

        return v;
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
            return 4;
        }
    }
}