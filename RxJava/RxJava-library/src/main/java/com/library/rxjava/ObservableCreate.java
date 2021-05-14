package com.library.rxjava;

/**
 * 具体的观察者对象
 */
public class ObservableCreate<T> extends Observable<T> {

    private final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    //在具体的观察者对象里重写 subscribeActual 函数
    @Override
    public void subscribeActual(Observer<? super T> observer) {
        //创建发送器
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        //执行观察者对象的 onSubscribe 方法
        observer.onSubscribe(parent);

        try {
            source.subscribe(parent);
        } catch (Exception e) {
            parent.onError(e);
        }

    }


    //创建具体的发送器对象
    static final class CreateEmitter<T> implements ObservableEmitter<T>, Disposable {

        //发射器中持有了观察者对象，发射器执行的操作都会同步调用到观察者对应的函数方法
        private final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T value) {
            observer.onNext(value);
        }

        @Override
        public void onError(Throwable error) {
            observer.onError(error);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    }
}
