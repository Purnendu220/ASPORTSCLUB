package com.asportsclub.utils;

import com.asportsclub.rest.Response.Item;

import java.util.ArrayList;

public class RefrenceWrapper {
    private static final RefrenceWrapper ourInstance = new RefrenceWrapper();
    ArrayList<Item> mSelectedItems = new ArrayList<>();

    public static RefrenceWrapper getInstance() {
        return ourInstance;
    }

    private RefrenceWrapper() {
    }

    public ArrayList<Item> getmSelectedItems() {
        return mSelectedItems;
    }

    public void setmSelectedItems(ArrayList<Item> mSelectedItems) {
        this.mSelectedItems = mSelectedItems;
    }
}
