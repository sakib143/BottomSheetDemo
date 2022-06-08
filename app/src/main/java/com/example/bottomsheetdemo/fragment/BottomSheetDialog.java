package com.example.bottomsheetdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.bottomsheetdemo.MyViewPager;
import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.model.MainModel;
import com.example.bottomsheetdemo.model.SubModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class BottomSheetDialog  extends BottomSheetDialogFragment {

    private ArrayList<MainModel> alMainTab = new ArrayList<>();
    private ArrayList<SubModel> alSubList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        loadArrayList();

        TabLayout tabLayout = v.findViewById(R.id.tab_layout);

        DemoCollectionPagerAdapter adapter = new DemoCollectionPagerAdapter(getChildFragmentManager());

        MyViewPager viewPager = v.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

    private void loadArrayList() {
        //Loading sublist.
        for (int i = 0; i <= 50; i++) {
            alSubList.add(new SubModel("Position " + i));
        }

        //Loading main array.
        for (int i = 1; i <= 5; i++) {
            alMainTab.add(new MainModel(alSubList,"Tab " + i));
        }


    }


    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new TabLayoutFragment();
            Bundle args = new Bundle();
//            // Our object is just an integer :-P
            args.putSerializable("sublist",alSubList);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return alMainTab.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return "OBJECT " + (position + 1);
            return alMainTab.get(position).getTabTitle();
        }
    }

}