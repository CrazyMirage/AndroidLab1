package com.example.anton.myapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends Activity {

    private static final Pattern namePattern = Pattern.compile("^[a-zA-ZА-Яа-я]{3,15}$");
    private static final Pattern mailPattern = Pattern.compile(".+@.+");
    private static final Pattern phonePattern = Pattern.compile("^\\+[0-9]*$");
    private DbReader mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DbReader(this.getApplicationContext());
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    public void addToList(View view){
        String name = ((EditText)findViewById(R.id.nameEdit)).getText().toString();
        String mail = ((EditText)findViewById(R.id.mailEdit)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phoneEdit)).getText().toString();



        if(checkName(name) && checkMail(mail) && checkPhone(phone)){

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DbReader.COLUMN_NAME, name);
            values.put(DbReader.COLUMN_MAIL, mail);
            values.put(DbReader.COLUMN_PHONE, phone);

            long newRowId;
            newRowId = db.insert(
                    DbReader.TABLE_NAME,
                    DbReader.COLUMN_NAME_NULLABLE,
                    values);

            Log.i("Insert to DB" , String.valueOf(newRowId));

            Intent intent = new Intent(this, MyListActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Mail", mail);
            intent.putExtra("Phone", phone);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, R.string.regular_expression_error,
                    Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkName(String name){
        Matcher m = namePattern.matcher(name);
        Log.i("name",name + " " + m.matches());
        return m.matches();
    }

    public boolean checkMail(String mail){
        Matcher m = mailPattern.matcher(mail);
        Log.i("mail",mail + " " + m.matches());
        return m.matches();
    }

    public boolean checkPhone(String phone){
        Matcher m = phonePattern.matcher(phone);
        Log.i("phone",phone + " " + m.matches());
        return m.matches();
    }

    public void openList(View view) {
        Intent intent = new Intent(this, MyListActivity.class);
        startActivity(intent);
    }


}
