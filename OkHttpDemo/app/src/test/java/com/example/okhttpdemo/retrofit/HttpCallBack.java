package com.example.okhttpdemo.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author tengfei
 * date 2019-11-29 12:20
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class HttpCallBack<T> implements Callback<Result<T>> {

    private Gson gson;

    public HttpCallBack() {
        this.gson = buildGson();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        Result<T> result = response.body();
        if (result == null || !result.isOk()) {
            onFail();
            return;
        }

        String data = result.data;

        if (TextUtils.isEmpty(data)) {
            onFail();
            return;
        }

        @SuppressWarnings("unchecked")
        Class<T> dataClass = (Class<T>) ((ParameterizedType) Objects.requireNonNull(this.getClass().
                getGenericSuperclass())).getActualTypeArguments()[0];

        T resultEntity = gson.fromJson(data, dataClass);

        onSuccess(resultEntity);

    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {

    }

    abstract void onSuccess(T result);

    abstract void onFail();

    private Gson buildGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
