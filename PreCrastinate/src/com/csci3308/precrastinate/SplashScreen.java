package com.csci3308.precrastinate;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Loading Screen with a progress bar to indicate to user that the app is loading.
 * 
 * @author Laura Matuszewska
 * 
 * @version 1.0, 06/27/14
 * 
 */
public class SplashScreen extends Activity {
 
    long Delay = 3000;
 
    /**
     * Loading is on a timer with a no feature screen which starts MainActivity when it completes.
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
 
        // Get the view from splash_screen.xml
        setContentView(R.layout.splash_screen);
 
        // Create a Timer
        Timer RunSplash = new Timer();
 
        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreen.class
                finish();
 
                // Start MainActivity.class
                Intent myIntent = new Intent(SplashScreen.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        };
 
        RunSplash.schedule(ShowSplash, Delay);
    }
}