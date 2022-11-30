package com.dro.ibm.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WaitingDialogFragment waitingDialogFragment = WaitingDialogFragment.newInstance("coucou");
        waitingDialogFragment.setCancelable(false);
        waitingDialogFragment.show(getSupportFragmentManager(), "coucou");

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    String random = UUID.randomUUID().toString();
                    System.out.println("Posting value: " + random);
                    waitingDialogFragment.changeText(random);
                }
                catch (Exception e) {
                    // TODO: handle exception
                }
                finally{
                    //also call the same runnable to call it at regular interval
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.post(runnable);
    }
}