package net.gongmingqm10.zhihu.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            onSuccess(response.body());
        } else {
            onFailed("The response is empty");
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed(t.getLocalizedMessage());
    }

    public abstract void onSuccess(T data);
    public abstract void onFailed(String message);
}
