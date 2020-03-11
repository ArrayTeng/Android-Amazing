package com.example.client;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author tengfei
 * date 2020-03-11 12:33
 * email arrayadapter.cn@gmail.com
 * description 因为是跨进程通信所以实体类必须序列化
 */
public class Book implements Parcelable {
    private int price;
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.price);
        dest.writeString(this.name);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.price = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
