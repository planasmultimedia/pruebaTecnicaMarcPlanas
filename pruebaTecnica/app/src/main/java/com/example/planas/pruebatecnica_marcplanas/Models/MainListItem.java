package com.example.planas.pruebatecnica_marcplanas.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MainListItem implements Parcelable{

    private String name;
    private String url;
    private String next_url;

    public MainListItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    protected MainListItem(Parcel in) {
        name = in.readString();
        url = in.readString();
        next_url = in.readString();
    }

    public static final Creator<MainListItem> CREATOR = new Creator<MainListItem>() {
        @Override
        public MainListItem createFromParcel(Parcel in) {
            return new MainListItem(in);
        }

        @Override
        public MainListItem[] newArray(int size) {
            return new MainListItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(next_url);
    }
}
