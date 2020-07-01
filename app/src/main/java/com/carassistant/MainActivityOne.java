package com.carassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.carassistant.ui.activities.DetectorActivity;

import java.util.Locale;

public class MainActivityOne extends AppCompatActivity {

    TextToSpeech t1;
    Button homebutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        homebutton1=(Button) findViewById(R.id.homebutton1);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result=t1.setLanguage(Locale.UK);

                    if(result== TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","message not supported");
                    }
                }
                else{
                    Log.e("TTS","initialization failed");
                }
            }
        });


        homebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(1);
                //  MediaPlayer hello= MediaPlayer.create(MainActivity.this,R.raw.hello);
                // hello.start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        longlistener();
                    }
                }, 8000);

            }
        });
        longlistener();


    }

    private void longlistener() {

        homebutton1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //MediaPlayer shotgun= MediaPlayer.create(MainActivity.this,R.raw.shotgun);
                //shotgun.start();
                speak(2);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent myIntent = new Intent(getBaseContext(),   MainScreen.class);
                        startActivity(myIntent);
                    }
                }, 2000);

                return true;
            }
        });

    }

    @Override
    protected void onPause() {
        if(t1 !=null){
            t1.stop();
            t1.shutdown();

        }
        super.onPause();
    }

    private void speak(int a) {
        String tospeak="";
        if(a==1){
            tospeak = "this is the home page of the application. this page consists of only one button on the whole screen. long click anywhere on the screen will take you to the next page. on each screen, the button at the bottom of the screen is. repeat auditory instructions. by pressing it, the introduction of that particular screen will be given that is how many buttons are there and what are their functionalities which will be beneficial for performing actions on that screen";

        }
        else if(a==2)
        {
            tospeak = "going to next screen";
        }
        Toast.makeText(getApplicationContext(), tospeak,Toast.LENGTH_SHORT).show();
        t1.speak(tospeak, TextToSpeech.QUEUE_FLUSH, null);
        t1.setPitch(1f);
        t1.setSpeechRate(1f);
    }

    @Override
    protected void onDestroy() {
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }

}
