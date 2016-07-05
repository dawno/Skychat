package com.example.dellpc.skychat;

import android.app.ActionBar;
import android.app.ProgressDialog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.app.Activity;



import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
public class Profile extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://skywalker.org.in/profileimage.php";
    private ProgressDialog pDialog;
    private List<Bio> movieList = new ArrayList<Bio>();
    private ListView listView;
    private CustomListAdapter adapter;
  String name[] ;
    Bio movie;
    Toolbar mActionBarToolbar;
    private String title, thumbnailUrl,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Users");
        setSupportActionBar(mActionBarToolbar);
name = new String[10000];
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                movie = new Bio(title, thumbnailUrl, status);

                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setStatus(obj.getString("status"));

                                 name[i] = obj.getString("title");
                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Profile.this,Chatting.class);

String value =  name[position];

              in.putExtra("name",value);
                startActivity(in);


            }
        });

    }
        @Override
        public void onDestroy () {
            super.onDestroy();
            hidePDialog();
        }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}





