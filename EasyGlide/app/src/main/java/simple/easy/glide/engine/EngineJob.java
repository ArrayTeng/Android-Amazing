package simple.easy.glide.engine;

import simple.easy.glide.resource.Value;

public class EngineJob implements Runnable {

    public Value loadResource;

    @Override
    public void run() {
        //这里做接口请求并且由子线程直接转为主线程

    }

}
