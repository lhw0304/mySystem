package com.mode.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoweiwei on 15/11/20.
 */
public class HeadlineStats extends Headline {

    private List<Stats> statsList = new ArrayList<Stats>();

    public List<Stats> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<Stats> statsList) {
        this.statsList = statsList;
    }

    public static class Stats {
        private String date;
        private int view;
        private int viewDistinct;
        private int share;
        private int shareDistinct;
        private int like;
        private int likeDistinct;
        private int save;
        private int saveDistinct;
        private int purchase;
        private int purchaseDistinct;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public int getViewDistinct() {
            return viewDistinct;
        }

        public void setViewDistinct(int viewDistinct) {
            this.viewDistinct = viewDistinct;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getShareDistinct() {
            return shareDistinct;
        }

        public void setShareDistinct(int shareDistinct) {
            this.shareDistinct = shareDistinct;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getLikeDistinct() {
            return likeDistinct;
        }

        public void setLikeDistinct(int likeDistinct) {
            this.likeDistinct = likeDistinct;
        }

        public int getSave() {
            return save;
        }

        public void setSave(int save) {
            this.save = save;
        }

        public int getSaveDistinct() {
            return saveDistinct;
        }

        public void setSaveDistinct(int saveDistinct) {
            this.saveDistinct = saveDistinct;
        }

        public int getPurchase() {
            return purchase;
        }

        public void setPurchase(int purchase) {
            this.purchase = purchase;
        }

        public int getPurchaseDistinct() {
            return purchaseDistinct;
        }

        public void setPurchaseDistinct(int purchaseDistinct) {
            this.purchaseDistinct = purchaseDistinct;
        }
    }


    //    private List<ActionCount> actionCountList = new ArrayList<ActionCount>();
//
//    private List<DailyAcountCount> dailyActionCountList = new ArrayList<DailyAcountCount>();
//
//    public List<ActionCount> getActionCountList() {
//        return actionCountList;
//    }
//
//    public void setActionCountList(List<ActionCount> actionCountList) {
//        this.actionCountList = actionCountList;
//    }
//
//    public List<DailyAcountCount> getDailyActionCountList() {
//        return dailyActionCountList;
//    }
//
//    public void setDailyActionCountList(List<DailyAcountCount> dailyActionCountList) {
//        this.dailyActionCountList = dailyActionCountList;
//    }
//
//    public static class ActionCount {
//        private String action;
//        private Integer total;
//        private Integer distinct;
//
//        public String getAction() {
//            return action;
//        }
//
//        public void setAction(String action) {
//            this.action = action;
//        }
//
//        public Integer getTotal() {
//            return total;
//        }
//
//        public void setTotal(Integer total) {
//            this.total = total;
//        }
//
//        public Integer getDistinct() {
//            return distinct;
//        }
//
//        public void setDistinct(Integer distinct) {
//            this.distinct = distinct;
//        }
//    }
//
//    public static class DailyAcountCount {
//        private String date;
//        private List<ActionCount> actionCounts = new ArrayList<ActionCount>();
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public List<ActionCount> getActionCounts() {
//            return actionCounts;
//        }
//
//        public void setActionCounts(List<ActionCount> actionCounts) {
//            this.actionCounts = actionCounts;
//        }
//    }

}
