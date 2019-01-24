package com.example.tengfei.client;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {

    public int id;
    public String infomation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.infomation);
    }

    public Info() {
    }

    public Info(int id, String infomation) {
        this.id = id;
        this.infomation = infomation;
    }

    protected Info(Parcel in) {
        this.id = in.readInt();
        this.infomation = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
