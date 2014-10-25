package com.example.anton.myapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.anton.myapp.Constants.*;

import java.util.ArrayList;

public class MyListActivity extends Activity {

    // элементы списка которые будут в него внесены

    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);


        ArrayList<Student> studentArrayList = CreateStudentList();

        // Связываемся с ListView
        ListView list = (ListView) findViewById(R.id.listView);
        // создаем адаптер

        final MyAdapter adapter = new MyAdapter(this,studentArrayList);

        // устанавливаем адаптер списку


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.showDescription(position);
            }
        });

        list.setAdapter(adapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String mail = intent.getStringExtra("Mail");
        String phone = intent.getStringExtra("Phone");

        Log.i("intent message", name +" "+ mail + " "+ phone);

    }

    private ArrayList<Student> CreateStudentList(){
        ArrayList<Student> studentList = new ArrayList<Student>();
        /*DbReader mDbHelper = new DbReader(this.getApplicationContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                DbReader.COLUMN_NAME,
                DbReader.COLUMN_MAIL,
                DbReader.COLUMN_PHONE
        };

        cursor = db.query(
                DbReader.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );*/
        cursor = getStudentsFromDB();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                studentList.add(new Student(cursor.getString(cursor.getColumnIndex(DbReader.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DbReader.COLUMN_MAIL)),
                        cursor.getString(cursor.getColumnIndex(DbReader.COLUMN_PHONE))
                        ));
                cursor.moveToNext();
            }
        }
        return  studentList;
    }

    private Cursor getStudentsFromDB(){
        ContentResolver resolver = getContentResolver();

        String projection[] = new String[] {COLUMN_NAME, COLUMN_MAIL, COLUMN_PHONE};
        Cursor cursor1= resolver.query(CONTENT_URI_PERSON, projection, null, null, null);
        return cursor1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_list, menu);
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
}
