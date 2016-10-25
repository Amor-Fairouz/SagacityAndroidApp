package com.esprit.sagacity.activities;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
/**
 * Created by amor on 04/01/2016.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Parse.enableLocalDatastore(this);
        // / Add your initialization code here
        //ParseObject.registerSubclass(this);
        Parse.initialize(this, "pXynpCJEwIDe0GLKpKC3rHINT8QazWFIu3YRSvIq", "5jYthdeLfTTVIn3vqlsBuqpr6iinYfoQqGAWAbJD");
        //for everyone
        ParseUser.enableAutomaticUser();
        //controle d'acees aux données
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this
        // line.(par defaut public)
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }










}