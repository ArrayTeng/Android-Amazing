package simple.easy.glide.resource;

import android.graphics.Bitmap;

public class Value {
    private String key;
    private Bitmap mBitmap;
    private ValueCallBack valueCallBack;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public ValueCallBack getValueCallBack() {
        return valueCallBack;
    }

    public void setValueCallBack(ValueCallBack valueCallBack) {
        this.valueCallBack = valueCallBack;
    }

    public void recycle(){
        if (valueCallBack!=null){
            valueCallBack.valueNonUseListener(key,this);
        }
    }
}
