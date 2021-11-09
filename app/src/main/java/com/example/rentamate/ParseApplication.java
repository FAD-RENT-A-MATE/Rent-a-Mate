package com.example.rentamate;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class ParseApplication extends Application {

    //Initializes Parse SDK as soon as the app is created
    @Override
    public void onCreate() {
        super.onCreate();

        //Register parse models
        //ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TLiWDf6WrajtXhtdWmdvRHe8svkntZf6Vcy1WoKc")
                .clientKey("KGWcoA4kOGFGM98lo5IXrnhP4kNDMqxCos4SfLQB")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
