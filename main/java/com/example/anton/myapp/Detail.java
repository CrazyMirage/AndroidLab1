package com.example.anton.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.anton.myapp.R;

public class Detail extends Activity {

    String phone;
    String mail;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        mail = intent.getStringExtra("Mail");
        phone = intent.getStringExtra("Phone");

        setContentView(R.layout.activity_detail);


        TextView nameView = (TextView) findViewById(R.id.nameViewDetails);
        TextView mailView = (TextView) findViewById(R.id.mailViewDetails);
        TextView phoneView = (TextView) findViewById(R.id.phoneViewDetails);

        nameView.setText(name);
        mailView.setText(mail);
        phoneView.setText(phone);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View arg0) {


        String number = "tel:"+phone;
        Log.e("Telephone", number);

        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(number)));

    }
}
