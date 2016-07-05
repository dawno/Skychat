package com.example.dellpc.skychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Chatting extends AppCompatActivity {
    Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        String value= getIntent().getStringExtra("name");

      mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar1);
        mActionBarToolbar.setTitle(value);
        setSupportActionBar(mActionBarToolbar);





        }


    }

