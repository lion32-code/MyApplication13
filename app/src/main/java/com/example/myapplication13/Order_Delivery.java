package com.example.myapplication13;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class Order_Delivery extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(ChefMenu.class);
        ParseObject.registerSubclass(CheckoutList.class);
        ParseObject.registerSubclass(CompleteOrder.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("1igYSiq7EIkI0M0fQrjwoU1qdbcqtwR2NhwNXiuY")
                .clientKey("yjjrxIe00LlVbo7DnkYFOIYoKDkwqnrja3YynidM")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}