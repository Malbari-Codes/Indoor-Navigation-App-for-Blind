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

public class MainScreen extends AppCompatActivity {

    TextToSpeech t1;
    Button navigation,settings,repeat,d1,d2,d3;
    int a=-5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
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
        d2=findViewById(R.id.d2);
        d1=findViewById(R.id.d1);
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak1(4);
            }
        });
        d1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a=5;
                speak1(a);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent myIntent = new Intent(getBaseContext(),   DetectorActivity.class);
                        startActivity(myIntent);
                    }
                }, 2000);
                return true;
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak1(6);
            }
        });
        d2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a=7;
                speak1(a);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent myIntent = new Intent(getBaseContext(),   DetectorActivity.class);
                        startActivity(myIntent);
                    }
                }, 2000);
                return true;
            }
        });

        navigation=findViewById(R.id.Navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=-2;
                speak1(a);


            }
        });
        navigation.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a=-1;
                speak1(a);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent myIntent = new Intent(getBaseContext(),   DetectorActivity.class);
                        startActivity(myIntent);
                    }
                }, 2000);

                return true;
            }
        });

        settings= findViewById(R.id.Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=0;
                speak1(a);
            }
        });
        settings.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a=1;
                speak1(a);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent myIntent = new Intent(getBaseContext(),   MainActivityOne.class);
                        startActivity(myIntent);
                    }
                }, 2000);

                return true;
            }
        });

        repeat= findViewById(R.id.repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=2;
                speak1(a);
            }
        });
        repeat.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a=3;
                speak1(a);
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        if(t1!=null){
            String ts="welcome";
            t1.speak(ts,TextToSpeech.QUEUE_FLUSH, null);
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        if(t1 !=null){
            t1.stop();
            t1.shutdown();

        }
        super.onPause();
    }

    private void speak1(int a) {
        String tospeak="";

        if(a==4){
            tospeak="destination name is. kitchen. long click on the button to start navigation";
        }

        else if(a==5){
            tospeak = "starting navigation to kitchen";
        }
        else if(a==6){
            tospeak = "destination name is. balcony. long click on the button to start navigation";
        }
        else if(a==7){
            tospeak = "starting navigation to balcony";
        }
        else if(a==3){
            tospeak = "this is the second page of the application. this page consists of three buttons on the whole screen. first button on the top of the screen if. NAVIGATION. by long clicking in that button you will go to the navigation page where you can select destination and proceed further. second button is . settings button. by long clicking in that button you will go to the settings page where you can select by default destination. the button at the bottom of the screen is. repeat auditory instructions. by pressing it, the introduction of that particular screen will be given that is how many buttons are there and what are their functionalities which will be beneficial for performing actions on that screen";

        }
        else if(a==2)
        {
            tospeak = "button name is. repeat auditory instruction. long click to repeat the instructions of this page";
        }
        else if(a==1){
            tospeak="going back to home page";
        }
        else if(a==0){
            tospeak="button name is. back button. long click to go back to home page";
        }
        else if(a==-1){
            tospeak="going to Navigations page";
        }
        else if(a==-2){
            tospeak="button name is. Navigation button. long click to proceed to navigations page";
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
