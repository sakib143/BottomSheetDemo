package com.example.bottomsheetdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomsheetdemo.MyViewPager;
import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.adapter.TotalCoinsAdapter;
import com.example.bottomsheetdemo.listner.MyTabListner;
import com.example.bottomsheetdemo.model.CoinSelectionModel;
import com.example.bottomsheetdemo.model.GiftModel;
import com.example.bottomsheetdemo.model.MainModel;
import com.example.bottomsheetdemo.model.SubModel;
import com.example.bottomsheetdemo.retrofit.RetrofitClient;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BottomSheetDialog  extends BottomSheetDialogFragment {

    private ArrayList<GiftModel.Tab> alMainTab = new ArrayList<>();
    private ArrayList<GiftModel.Tab.CategoryItem> alSubList = new ArrayList<>();

    private ArrayList<CoinSelectionModel> alTotalCoins = new ArrayList<>();

    private RecyclerView rvTotalCoins;
    private TotalCoinsAdapter totalCoinsAdapter;
    private MyTabListner callback;

    public static int MAIN_ARRAY_SELECTION = -1 ;
    public static int SUB_ARRAY_SELECTION = -1;
    public static int ADAPTER_POSITION = -1;

    private List<TabLayoutFragment> tablayoutList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        rvTotalCoins = v.findViewById(R.id.rvTotalCoins);

        makeAPIcall(v);

        return v;
    }

    private void setData(View v) {
        loadArrayList();
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);

        DemoCollectionPagerAdapter adapter = new DemoCollectionPagerAdapter(getChildFragmentManager());

        MyViewPager viewPager = v.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setAdapter(adapter);

        callback = new MyTabListner() {
            @Override
            public void onClick(int mainArrayPosition, int subArrayPosition, int adapterPosition, boolean isSelected) {
                MAIN_ARRAY_SELECTION = mainArrayPosition;
                SUB_ARRAY_SELECTION = subArrayPosition;
                ADAPTER_POSITION = adapterPosition;
//                //Log.d("TAG ==> ", "onClick: mainArrayPosition " + mainArrayPosition + "subArrayPosition "  + subArrayPosition + " adapterPosition " + adapterPosition);
                Log.d("TAG ==> ", "onClick: Tab Array Selected position  " + MAIN_ARRAY_SELECTION + " Item array Selected position  "  + SUB_ARRAY_SELECTION );
            }
        };

        tabLayout.setupWithViewPager(viewPager);
    }

    private void loadArrayList() {
//        //Loading sublist.
//        for (int i = 0; i <= 17; i++) {
//            alSubList.add(new SubModel("Position " + i));
//        }

//
        //Loading main array.
//        for (int i = 1; i <= 5; i++) {
//            alMainTab.add(new MainModel(alSubList,"Tab " + i, i ));
//            tablayoutList.add(new TabLayoutFragment());
//        }
        //Load total coins
        for (int i = 1; i < 5; i++) {
            alTotalCoins.add(new CoinSelectionModel(i + "", i, false));
        }
        totalCoinsAdapter = new TotalCoinsAdapter(alTotalCoins, new TotalCoinsAdapter.CoinSelectListner() {
            @Override
            public void onSelectedPosition(int position) {
                if (alTotalCoins.get(position).isSelected()) {
                    alTotalCoins.get(position).setSelected(true);
                } else {
                    for (int i = 0; i < alTotalCoins.size(); i++) {
                        alTotalCoins.get(i).setSelected(false);
                    }
                    alTotalCoins.get(position).setSelected(true);
                    if(MAIN_ARRAY_SELECTION != -1 && SUB_ARRAY_SELECTION != -1 && ADAPTER_POSITION != -1) {
                        tablayoutList.get(MAIN_ARRAY_SELECTION).callUpdatePrice(alTotalCoins.get(position).getName());
                    }
                }
                totalCoinsAdapter.notifyDataSetChanged();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false);
        rvTotalCoins.setLayoutManager(mLayoutManager);
        rvTotalCoins.setAdapter(totalCoinsAdapter);
    }

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            TabLayoutFragment fragment = tablayoutList.get(i);
            Bundle args = new Bundle();
//            // Our object is just an integer :-P
            args.putSerializable("main_list",alMainTab);
            args.putSerializable("main_position",i);
            args.putSerializable("listner",callback);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return alMainTab.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return alMainTab.get(position).getCategoryName();
        }
    }

    private void makeAPIcall(View v) {
        Call<GiftModel> call = RetrofitClient.getInstance().getMyApi().getGiftsData();
        call.enqueue(new Callback<GiftModel>() {
            @Override
            public void onResponse(Call<GiftModel> call, Response<GiftModel> response) {

                GiftModel model = response.body();

                //Load array for tabs
                alMainTab = new ArrayList<>();
                alMainTab.addAll(model.getTab());

//                Toast.makeText(getActivity(),"Sub array size is " + alMainTab.get(0).getCategoryItem().size(), Toast.LENGTH_LONG).show();

                alSubList.addAll(alMainTab.get(0).getCategoryItem());

//                alSubList = new ArrayList<>();
//                alSubList.addAll(alMainTab.get(0).CategoryItem);

                for (int i = 0; i < alMainTab.size(); i++) {
                    tablayoutList.add(new TabLayoutFragment());
                }

//                //Load subarray for viewpagers
//                alSubList = new ArrayList<>();
//                for (int i = 0; i < alMainTab.size(); i++) {
//                    alSubList.addAll(alMainTab.get(i).getCategoryItem());
//                }

                setData(v);
            }

            @Override
            public void onFailure(Call<GiftModel> call, Throwable t) {

            }
        });
    }

}