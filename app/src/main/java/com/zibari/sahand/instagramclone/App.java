package com.zibari.sahand.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bapLuyNkKCuyKakVbCLHMVDcZOP06WMa4Mfc1XP7")
                // if defined
                .clientKey("5vDWgRvY7UqD16dVAlHPvCilh8VkJekW0mGa8o8C")
                .server("https://parseapi.back4app.com/")
                .build()
        );



    }
}
