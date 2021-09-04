package simple.easy.glide.engine;

import simple.easy.glide.resource.Value;

public interface ResponseListener {

    void responseSuccess(String key, Value value);

    void responseException(Exception e);

}
