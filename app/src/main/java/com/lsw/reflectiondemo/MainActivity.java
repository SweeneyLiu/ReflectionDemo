package com.lsw.reflectiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMethodNum();
        getClassName();
        createPerson();
    }

    private void getMethodNum() {

        Method[] methods = Person.class.getMethods();
        for (Method method : methods) {
            Log.i(TAG, "getMethodNum: " + method.getName());
        }
    }

    private void getClassName(){
        Class c1 = Person.class;
        Class c2 = new Person().getClass();
        Class c3 = null;
        try {
            c3 = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "getClassName: " + c1.getName());
        Log.i(TAG, "getClassName: " + c2.getName());
        Log.i(TAG, "getClassName: " + c3.getName());
    }

    private void createPerson(){
        Class c = null;
        Person person = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            person = (Person)c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        person.setName("lisi");
        Log.i(TAG, "createPerson: " + person.getName());
    }

}
