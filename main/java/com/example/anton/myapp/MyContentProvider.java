package com.example.anton.myapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static com.example.anton.myapp.Constants.*;

/**
 * Created by foban on 10/25/14.
 */
public class MyContentProvider extends ContentProvider {
    private static final int MATCHER_CODE_PERSON = 1;

    static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(AUTHORITY, PATH_PERSON, MATCHER_CODE_PERSON);
    }

    DbReader helper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        helper = new DbReader(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        Cursor cursor = null;


        int code = matcher.match(uri);
        if (code == MATCHER_CODE_PERSON) {
            cursor = database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);
        }


        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int code = matcher.match(uri);
        if (code == MATCHER_CODE_PERSON) {
            // Person
            database.insert(TABLE_NAME, null, contentValues);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
