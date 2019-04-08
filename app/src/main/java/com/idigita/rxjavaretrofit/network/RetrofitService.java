package com.idigita.rxjavaretrofit.network;

import com.idigita.rxjavaretrofit.network.model.Android;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/api/json/get/cfbdGAjmNu")
    Observable<List<Android>> getAllFeeds(@Query("indent")int id);
}
