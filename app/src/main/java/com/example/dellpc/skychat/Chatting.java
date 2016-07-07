package com.example.dellpc.skychat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatting extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    EditText inputmsg;
    Button send;
    Message pack;
    String fromName;
    String message;
    private List<Message> packList = new ArrayList<>();
    MessagesListAdapte adapter;
    String isSelf;
    private static final String TAG = Chatting.class.getSimpleName();
    private static final String url = "http://skywalker.org.in/sendingmessage.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        final String receiver = getIntent().getStringExtra("name");

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar1);
        mActionBarToolbar.setTitle(receiver);
        setSupportActionBar(mActionBarToolbar);

        inputmsg = (EditText) findViewById(R.id.inputMsg);
        send = (Button) findViewById(R.id.btnSend);

        SharedPreferences prefs = getSharedPreferences("bio", MODE_PRIVATE);
        final String sender = prefs.getString("name", "");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final   String messageSent = inputmsg.getText().toString();

                inputmsg.setText("");
                messagesending(messageSent,receiver,sender);


            }
        });
    }

    private void messagesending( final String messagesent , final String receiver,final  String sender) {


    JsonArrayRequest movieReq = new JsonArrayRequest(url,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());


                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            pack = new Message(fromName, message, isSelf);

                            pack.setMessage(obj.getString("message"));
                            pack.setSelf(obj.getString("bool"));


                            // adding movie to movies array
                            packList.add(pack);

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


        }
    }) {


        @Override
        protected Map<String, String> getParams() {
            // Posting parameters to login url
            Map<String, String> params = new HashMap<String, String>();
            params.put("sender_name",sender );
            params.put("receiver_name", receiver);
            params.put("message", messagesent);
            return params;
        }

    };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }


}




