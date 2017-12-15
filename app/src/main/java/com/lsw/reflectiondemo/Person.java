package com.lsw.reflectiondemo;

import android.util.Log;

/**
 * Created by Administrator on 2017/12/14.
 */

public class Person {

    private static final String TAG = "Person";

    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayChinese(){
        Log.i(TAG, "sayChinese: ");
    }

    public String sayHello(String name, int age) {
        return name+"你好，今年"+age+"岁";
    }

}
