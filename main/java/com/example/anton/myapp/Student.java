package com.example.anton.myapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by foban on 10/11/14.
 */
public class Student implements Parcelable{
    String name;
    String mail;
    String phone;

    Student(String name, String mail, String phone){
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    Student(Parcel parcel){
        name = parcel.readString();
        mail = parcel.readString();
        phone = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(mail);
        parcel.writeString(phone);
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        // распаковываем объект из Parcel
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
