package com.example.dellpc.skychat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

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
    private ListView messageView;
<<<<<<< HEAD
    private List<Message> packList = new ArrayList<>();
    public static final String MyPREFERENCES = "" ;
    MessagesListAdapte adapter;
=======
    RecyclerView recyclerView;
     ArrayList<Message> messageArrayList ;
    public static final String MyPREFERENCES = "" ;
  ChatRoomThreadAdapter adapter;
>>>>>>> origin/master
    String isSelf;
    private static final String TAG = Chatting.class.getSimpleName();
    private static final String url = "http://skywalker.org.in/sendingmessage.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        messageArrayList = new ArrayList<>();
        final String receiver = getIntent().getStringExtra("name");
<<<<<<< HEAD
        messageView = (ListView)findViewById(R.id.list_view_messages);
        adapter = new MessagesListAdapte(this, packList);
        messageView.setAdapter(adapter);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar1);
=======
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       // messageView = (ListView)findViewById(R.id.list_view_messages);
        adapter = new ChatRoomThreadAdapter(this,messageArrayList );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //messageView.setAdapter(adapter);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
>>>>>>> origin/master
        mActionBarToolbar.setTitle(receiver);
        setSupportActionBar(mActionBarToolbar);

        inputmsg = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.btn_send);

        SharedPreferences prefs = getSharedPreferences("Details", MODE_PRIVATE);
        final String sender = prefs.getString("myName", "");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String messageSent = inputmsg.getText().toString();

             inputmsg.setText("");

                messagesending(messageSent, receiver, sender);


            }
        });
    }

    private void messagesending( final String messageSent , final String receiver,final  String sender) {


   StringRequest movieRe = new StringRequest(Request.Method.POST,url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response.toString());


                    try {
                    JSONArray Json = new JSONArray(response);
                        Log.d("message mila ki ni"," mil gaya");
                    // Parsing json
                    for (int i = 0; i < Json.length(); i++) {
                        try {

                            JSONObject obj = Json.getJSONObject(i);
                            pack = new Message(fromName, message, isSelf);
                            Log.d("message mila ki ni"," mil gaya");
                            pack.setFromName(sender);
                            pack.setMessage(obj.getString("message"));
                            pack.setSelf(obj.getString("bool"));


                            // adding movie to movies array
                            messageArrayList.add(pack);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data
                    adapter.notifyDataSetChanged();
                }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            Log.e(TAG, "message loading  " + error.getMessage());
            Toast.makeText(getApplicationContext(),
                    error.getMessage(), Toast.LENGTH_LONG).show();

        }
    }) {


        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("sender_name", sender);

            params.put("receiver_name", receiver);

            params.put("message", messageSent);

            return params;
        }

    };


        AppController.getInstance().addToRequestQueue(movieRe);
    }


}




