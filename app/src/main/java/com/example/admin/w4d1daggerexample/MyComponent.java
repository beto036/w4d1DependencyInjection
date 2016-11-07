package com.example.admin.w4d1daggerexample;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 11/7/2016.
 */

@Singleton
@Component(modules = {MyModule.class})
public interface MyComponent {
    void inject(MainActivity mainActivity);
}
