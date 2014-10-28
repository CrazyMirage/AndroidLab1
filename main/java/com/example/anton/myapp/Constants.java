package com.example.anton.myapp;

   import android.net.Uri;

public class Constants {


    public final static String BROADCAST_ACTION = "com.example.anton.myapp.RECEIVER";
    public final static String STUDENT_ADD_TASK = "Student";

    // constants for content provider
    // content://<authority>
    // content://com.examples.ContentProviderDemo
    public static final String AUTHORITY = "com.example.anton.myapp";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // specific details (PATH)
    // content://com.examples.ContentProviderDemo/Person
    public static final String PATH_PERSON = "Person";
    public static final Uri CONTENT_URI_PERSON = Uri.parse("content://" + AUTHORITY + "/" + PATH_PERSON);


    // constants for car table
    public static final String TABLE_NAME = "entry_list";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MAIL = "mail";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_NAME_NULLABLE = COLUMN_NAME;
}