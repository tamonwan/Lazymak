package com.mycompany.lazy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class PDF extends AppCompatActivity {

    String message_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", null);

        final ListView listview = (ListView) findViewById(R.id.namelist);
        final ListView postlist = (ListView) findViewById(R.id.postlist);

        final ArrayList<String> everypost = new ArrayList<String>();

        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    final ArrayList<String> list = new ArrayList<String>();
                    final ArrayList<String> posts = new ArrayList<String>();

                    for (int i = 0; i < objects.size(); i++) {
                        String nameonline;
                        if(objects.get(i).getBoolean("online")){
                            nameonline = objects.get(i).getUsername()+": online";
                        }else{
                            nameonline = objects.get(i).getUsername()+": offline";
                        }
                        list.add(nameonline);
                        posts.add(objects.get(i).get("post")+"");
                        everypost.add(objects.get(i).get("post")+"");
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(PDF.this, android.R.layout.simple_list_item_1, list);
                    final ArrayAdapter postadapter = new ArrayAdapter(PDF.this, android.R.layout.simple_list_item_1, posts);
                    postlist.setAdapter(postadapter);
                    listview.setAdapter(adapter);

                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recreate();
            }
        });

        postlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                recreate();
            }
        });

    }

    public void logout(View view){
        ParseUser.getCurrentUser().put("online", false);
        ParseUser.getCurrentUser().saveInBackground();
        ParseUser.logOut();
        startActivity(new Intent(PDF.this, MainActivity.class));
    }

    public void draw(View view){
        startActivity(new Intent(PDF.this, Draw.class));
    }

    public void post(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Post");
        final EditText message = new EditText(this);
        builder.setView(message);
        AlertDialog.Builder builder1 = builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                message_user = message.getText().toString();
                ParseUser currentuser = ParseUser.getCurrentUser();
                currentuser.put("post", message_user);
                Toast.makeText(getApplicationContext(), "post: " + currentuser.get("post"), Toast.LENGTH_SHORT).show();
                currentuser.saveInBackground();
                recreate();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.show();

    }

}
