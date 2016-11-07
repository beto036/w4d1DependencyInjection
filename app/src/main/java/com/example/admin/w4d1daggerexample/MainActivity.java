package com.example.admin.w4d1daggerexample;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    @Inject
    @Named("beto")
    SharedPreferences sharedPreferences;
    @Inject
    @Named("will")
    SharedPreferences sharedPreferencesWill;
    @Inject
    Lazy<SharedPreferences.Editor> editor;

    @Inject
    Retrofit retrofit;

    @Inject
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMyComponent.builder().myModule(new MyModule(this)).build().inject(this);

    }

    public void save(View view) {
        Log.d(TAG, "save: " + client);
    }

    public void read(View view) {
        BatmanService batmanService = retrofit.create(BatmanService.class);
        Call<Batman> call = batmanService.retrievCharacters("batman characters");
        call.enqueue(new Callback<Batman>() {
            @Override
            public void onResponse(Call<Batman> call, Response<Batman> response) {
                Batman batman = response.body();
                for (RelatedTopic relatedTopic : batman.getRelatedTopics()) {
                    Log.d(TAG, "onResponse: " + relatedTopic);
                }
            }

            @Override
            public void onFailure(Call<Batman> call, Throwable t) {

            }
        });
    }
}
