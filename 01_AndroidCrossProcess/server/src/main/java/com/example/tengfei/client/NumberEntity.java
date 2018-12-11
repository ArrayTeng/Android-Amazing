package com.example.tengfei.client;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author tengfei
 */
public class NumberEntity implements Parcelable {
    public int number;

    public NumberEntity(int number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
    }

    public NumberEntity() {
    }



    protected NumberEntity(Parcel in) {
        this.number = in.readInt();
    }

    public static final Creator<NumberEntity> CREATOR = new Creator<NumberEntity>() {
        @Override
        public NumberEntity createFromParcel(Parcel source) {
            return new NumberEntity(source);
        }

        @Override
        public NumberEntity[] newArray(int size) {
            return new NumberEntity[size];
        }
    };
}
