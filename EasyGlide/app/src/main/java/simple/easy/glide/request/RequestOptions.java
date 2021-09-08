package simple.easy.glide.request;

public class RequestOptions {
    //实现Glide应该有的方法
    //对设置项封装
    //根据需求来设置属性

    private int width;
    private int height;

    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public RequestOptions override(int width, int height){
        this.width = width;
        this.height = height;
        return this;
    }

    public RequestOptions placeholder(int resId){
        this.resId = resId;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
