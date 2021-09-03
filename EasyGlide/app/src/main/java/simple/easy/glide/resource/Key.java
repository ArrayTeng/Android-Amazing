package simple.easy.glide.resource;

import simple.easy.glide.util.Tool;

public class Key {

    private String key;

    public Key(String path){
        this.key = Tool.getSHA256StrJava(path);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
