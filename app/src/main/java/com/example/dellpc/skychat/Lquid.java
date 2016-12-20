package com.example.dellpc.skychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gospelware.liquidbutton.LiquidButton;

public class Lquid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lquid);
        final LiquidButton liquidButton = (LiquidButton) findViewById(R.id.button);
       liquidButton.setAutoPlay(true);
        liquidButton.startPour();

        liquidButton.setPourFinishListener(new LiquidButton.PourFinishListener() {
            @Override
            public void onPourFinish() {
                Toast.makeText(Lquid.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Lquid.this,Profile.class));
            }

            @Override
            public void onProgressUpdate(float progress) {
              //  textView.setText(String.format("%.2f", progress * 100) + "%");
                liquidButton.changeProgress(1f );
            }
        });

    }
}
