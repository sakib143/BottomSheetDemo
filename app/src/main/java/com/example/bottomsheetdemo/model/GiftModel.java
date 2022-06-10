package com.example.bottomsheetdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;



public class GiftModel {
    @SerializedName("tab")
    @Expose
    private List<Tab> tab = null;

    public List<Tab> getTab() {
        return tab;
    }

    public void setTab(List<Tab> tab) {
        this.tab = tab;
    }

    public class Tab {
        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("category_item")
        @Expose
        private List<CategoryItem> categoryItem = null;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<CategoryItem> getCategoryItem() {
            return categoryItem;
        }

        public void setCategoryItem(List<CategoryItem> categoryItem) {
            this.categoryItem = categoryItem;
        }

        public class CategoryItem {

            private boolean isSelected;
            private String totalCounts;

            public String getTotalCounts() {
                return totalCounts;
            }

            public void setTotalCounts(String totalCounts) {
                this.totalCounts = totalCounts;
            }

            @SerializedName("animation_file")
            @Expose
            private String animationFile;
            @SerializedName("icon")
            @Expose
            private String icon;
            @SerializedName("animation")
            @Expose
            private String animation;
            @SerializedName("gift_name")
            @Expose
            private String giftName;
            @SerializedName("coin")
            @Expose
            private String coin;
            @SerializedName("gift_id")
            @Expose
            private String giftId;
            @SerializedName("category")
            @Expose
            private String category;
            @SerializedName("collective")
            @Expose
            private String collective;
            @SerializedName("collective_reward")
            @Expose
            private String collectiveReward;
            @SerializedName("animation_type")
            @Expose
            private String animationType;
            @SerializedName("popular")
            @Expose
            private String popular;

            public String getAnimationFile() {
                return animationFile;
            }

            public void setAnimationFile(String animationFile) {
                this.animationFile = animationFile;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getAnimation() {
                return animation;
            }

            public void setAnimation(String animation) {
                this.animation = animation;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }

            public String getGiftId() {
                return giftId;
            }

            public void setGiftId(String giftId) {
                this.giftId = giftId;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCollective() {
                return collective;
            }

            public void setCollective(String collective) {
                this.collective = collective;
            }

            public String getCollectiveReward() {
                return collectiveReward;
            }

            public void setCollectiveReward(String collectiveReward) {
                this.collectiveReward = collectiveReward;
            }

            public String getAnimationType() {
                return animationType;
            }

            public void setAnimationType(String animationType) {
                this.animationType = animationType;
            }

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public boolean isSelected() {
                return isSelected;
            }
        }
    }
}
