package com.example.admin.w4d1daggerexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/7/2016.
 */
@Module
public class MyModule {
    private Context context;

    private static final String BASE_URL = "https://api.duckduckgo.com/";

    public MyModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named(value = "beto")
    public SharedPreferences provideSharedBeto(){
//        return PreferenceManager.getDefaultSharedPreferences(this.context);
        return context.getSharedPreferences("BETO", Context.MODE_PRIVATE);
    }

    @Provides
    @Named(value = "will")
    public SharedPreferences provideSharedWill(){
//        return PreferenceManager.getDefaultSharedPreferences(this.context);
        return context.getSharedPreferences("WILL", Context.MODE_PRIVATE);
    }

    @Provides
    public SharedPreferences.Editor provideEditor(@Named("will") SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }

    @Provides
    public Retrofit provideRetroFit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public OkHttpClient provideOkHTTP(){
        return new OkHttpClient();
    }
}
