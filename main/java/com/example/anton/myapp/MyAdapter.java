package com.example.anton.myapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by foban on 10/11/14.
 */
public class MyAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Student> studentList;


    MyAdapter(Context context, ArrayList<Student> studentList){
        ctx = context;
        this.studentList = studentList;
        lInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }


        Student p = studentList.get(i);
        //view.setId(i);
        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        TextView textView = ((TextView) view.findViewById(R.id.textView));
        textView.setText(p.name);


        //view.setOnClickListener(clickListener);

        return view;
    }

    void showDescription(int id){

            Intent intent = new Intent(ctx, Detail.class);
            Student student = studentList.get(id);
            intent.putExtra("Name", student.name);
            intent.putExtra("Mail", student.mail);
            intent.putExtra("Phone", student.phone);


            ctx.startActivity(intent);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Log.e("Click", "Ursa");


            /*Intent intent = new Intent(ctx, Detail.class);
            Student student = studentList.get(view.getId());
            intent.putExtra("Name", student.name);
            intent.putExtra("Mail", student.mail);
            intent.putExtra("Phone", student.phone);


            ctx.startActivity(intent);*/
        }
    };

}
