package com.example.anton.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Random;

/**
 * Created by foban on 10/28/14.
 */
public class StudentWriterService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        NPC mr = new NPC();

        return super.onStartCommand(intent, flags, startId);
    }

    class NPC implements Runnable{
        String[] names = {"Robert", "Evan","Luke", "Angel", "Hunter", "Kyle", "Luis", "Zoe", "Lily", "Mia", "Brooke"};
        String[] mails = {"mail.com", "gmail.com", "mail.ru","tut.by"};
        String[] prefixes = {"Cool", "Magic", "Funny", "Big", "Smile", ""};
        int phoneMask = 1000000000;

        @Override
        public void run() {
            Intent intent = new Intent(Constants.BROADCAST_ACTION);
            Student student;
            try {
                for(int i = 0; i < 4; i++){
                    student = createStudent();
                    intent.putExtra(Constants.STUDENT_ADD_TASK,student);
                    sendBroadcast(intent);


                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Student createStudent(){
            Random rand =new Random();
            String name = names[(rand).nextInt()%names.length];
            String mail = prefixes[(rand).nextInt()%prefixes.length]+name+"@"+mails[(rand).nextInt()%mails.length];
            String phone = "+375"+rand.nextInt()%phoneMask;
            return new Student(name,mail,phone);
        }
    }


}
