package com.example.demo.retrofit;

/**
 * @author tengfei
 * date 2019-12-01 23:04
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface IParameterHandler<T> {

    void apply(T value);

    class Query<T> implements IParameterHandler<T> {

        String key;

        public Query(String key) {
            this.key = key;
        }

        @Override
        public void apply(T value) {

        }
    }
}
