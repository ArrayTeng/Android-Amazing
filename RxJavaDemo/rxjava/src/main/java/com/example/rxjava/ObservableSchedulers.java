package com.example.rxjava;

/**
 * @author tengfei
 * date 2020-03-04 23:24
 * email arrayadapter.cn@gmail.com
 * description
 */
final class ObservableSchedulers<T> extends Observable<T> {

    final Observable<T> source;
    final Schedulers schedulers;
    public ObservableSchedulers(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        schedulers.scheduleDirct(new scheduleTask(observer));
    }

    private class scheduleTask implements Runnable{
        final Observer<T> observer;
        public scheduleTask( Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            //noinspection unchecked
            source.subscribe(observer);
        }
    }
}
