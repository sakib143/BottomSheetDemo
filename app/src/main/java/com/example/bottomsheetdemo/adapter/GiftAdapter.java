package com.example.bottomsheetdemo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomsheetdemo.R;
import com.example.bottomsheetdemo.model.GiftModel;
import com.example.bottomsheetdemo.model.SubModel;

import java.util.List;


public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.ViewHolder> {

    private List<GiftModel.Tab.CategoryItem> alGifts;
    private GiftSelectListner listner;

    public GiftAdapter(List<GiftModel.Tab.CategoryItem> alGifts, GiftSelectListner listner) {
        this.alGifts = alGifts;
        this.listner = listner;
        Log.d("TAG ==>"  , "GiftAdapter: alGifts size is ???  " + alGifts.size() );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_gift, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.tvSelectedCoins.setText(alGifts.get(position).getTotalCounts());

        if(alGifts.get(position).isSelected()) {
            viewHolder.rootLayout.setBackground(ContextCompat.getDrawable(viewHolder.ivImages.getContext(), R.drawable.bg_gift_selected));
        } else {
            viewHolder.rootLayout.setBackground(null);
        }

        viewHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onSelectedPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alGifts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImages;
        private RelativeLayout rootLayout;
        private TextView tvSelectedCoins;
        public ViewHolder(View view) {
            super(view);
            ivImages = view.findViewById(R.id.ivImages);
            rootLayout = view.findViewById(R.id.rootLayout);
            tvSelectedCoins = view.findViewById(R.id.tvSelectedCoins);
        }
    }

    public interface GiftSelectListner {
        void onSelectedPosition(int position);
    }
}
