package com.idigita.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.idigita.rxjavaretrofit.adapter.AndroidAdapter;
import com.idigita.rxjavaretrofit.network.RetrofitApi;
import com.idigita.rxjavaretrofit.network.RetrofitService;
import com.idigita.rxjavaretrofit.network.model.Android;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AndroidAdapter androidAdapter;
    private RetrofitService retrofitService;
    private List<Android> results = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitService = RetrofitApi.getClient().create(RetrofitService.class);
        setupRecyclerView();
        loadJSON();
    }

    private void loadJSON() {
        compositeDisposable.add(
                retrofitService.getAllFeeds(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Android>>() {
                    @Override
                    public void accept(List<Android> androids) throws Exception {
                        MainActivity.this.handleSuccess(androids);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MainActivity.this.handleFailure(throwable);
                    }
                }
        ));
    }

    private void handleFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,"Internal Error!",Toast.LENGTH_LONG).show();
    }

    private void handleSuccess(List<Android> androids) {
        results = new ArrayList<>(androids);
        androidAdapter = new AndroidAdapter(androids);
        recyclerView.setAdapter(androidAdapter);
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
