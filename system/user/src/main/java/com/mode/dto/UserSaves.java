package com.mode.dto;

import java.util.List;

import com.mode.entity.Feed;
import com.mode.entity.Item;

/**
 * Created by Lei on 4/8/16.
 */
public class UserSaves {

    private List<Feed> savedArticles;
    private List<Item> savedItems;
    private List<Feed> savedCollections;
    private List<Feed> savedVideos;

    public List<Feed> getSavedArticles() {
        return savedArticles;
    }

    public void setSavedArticles(List<Feed> savedArticles) {
        this.savedArticles = savedArticles;
    }

    public List<Item> getSavedItems() {
        return savedItems;
    }

    public void setSavedItems(List<Item> savedItems) {
        this.savedItems = savedItems;
    }

    public List<Feed> getSavedCollections() {
        return savedCollections;
    }

    public void setSavedCollections(List<Feed> savedCollections) {
        this.savedCollections = savedCollections;
    }

    public List<Feed> getSavedVideos() {
        return savedVideos;
    }

    public void setSavedVideos(List<Feed> savedVideos) {
        this.savedVideos = savedVideos;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"savedArticles\":" + savedArticles
                + ",                         \"savedItems\":" + savedItems
                + ",                         \"savedCollections\":" + savedCollections
                + ",                         \"savedVideos\":" + savedVideos
                + "}";
    }
}
