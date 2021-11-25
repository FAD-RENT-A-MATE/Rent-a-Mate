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
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("pmDaMwkKCPG5EbzhJb3cH3iISGuJ7WkeTJ4LHVJt")
                .clientKey("C6SMKzZ2gkOea1ks9QS0A6DjaYtNvwKdmuKx2NJK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
