package com.mycompany.lazy;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Pop on 11/21/2015 AD.
 */
public class Lazy extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "JcZt8HEnIKbpVYdVhG3rwg00vom0uhrU7eYeyO9W", "1ZxTC8PkhctH0mnsIxdjW5wcyl0IOmRHX4Y4sV5R");

    }
}
