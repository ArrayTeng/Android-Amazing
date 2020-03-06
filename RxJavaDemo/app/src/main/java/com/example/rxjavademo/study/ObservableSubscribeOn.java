package com.example.rxjavademo.study;

/**
 * @author tengfei
 * date 2020-03-06 14:36
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObservableSubscribeOn<T> extends Observable<T> {

    private Observable<T> source;
    private Schedulers schedulers;

    public ObservableSubscribeOn(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        schedulers.scheduleDirect(new SubscribeOnTask(observer));
    }

    private class SubscribeOnTask implements Runnable {

        private Observer<T> observer;

        public SubscribeOnTask(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            source.subscribe(observer);
        }
    }


}
