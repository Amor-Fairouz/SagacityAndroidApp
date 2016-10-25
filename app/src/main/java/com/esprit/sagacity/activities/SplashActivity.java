package com.esprit.sagacity.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.esprit.sagacity.R;
import com.esprit.sagacity.utils.InternetChecker;
import com.esprit.sagacity.utils.Result_Updater;


public class SplashActivity extends Activity  {
//implemts Runnable.???
// Splash screen timer
private static int SPLASH_TIME_OUT = 3000;
ImageView imageLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
// This method will be executed once the timer is over
        //findViewById(android.R.id.content).postDelayed(SplashActivity.this, 3000);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //Intent i = new Intent(SplashActivity.this, .class);
                //startActivity(i);
                check_internet(SplashActivity.this, 3000);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
    private void check_internet(final Context context, final int time_interval) {
        InternetChecker asyncTask = new InternetChecker(new Result_Updater() {
            @Override
            public void processFinish(boolean output) {
                if (output) {
                    Toast_it(context, "INTERNET IS AVAILABLE");
                    Intent checkUpdates = new Intent(SplashActivity.this, AcceuilParseActivity.class);
                    startActivity(checkUpdates);
                   // WidgetProvider hg=new WidgetProvider();
                   // hg.onUpdate();
                } else {
                    Toast_it(context, "INTERNET IS NOT-AVAILABLE");
                    Intent checkUpdates = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(checkUpdates);
                }
            }

        }, context, time_interval);
        asyncTask.execute();


    }

    private void Toast_it(final Context context, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, "" + msg, Toast.LENGTH_LONG).show();
            }
        });

    }



   /* @Override
    public void run() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }*/




    private void startAnim() {

        animate(imageLoad, R.anim.zoom_in);
        animate(imageLoad,R.anim.zoom_out);
    }



    private void animate(ImageView img , int id) {
        Animation a = AnimationUtils.loadAnimation(this, id);
        a.reset();
        img.clearAnimation();
        img.startAnimation(a);
    }

    public void passe(View v) {
        startAnim();
    }

}
