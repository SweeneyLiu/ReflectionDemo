package com.lsw.reflectiondemo;

import android.util.Log;

/**
 * Created by liushuwei on 2017/12/15.
 */

public class Person3 implements China {

    private String name;
    private int age;
    private static final String TAG = "person3";


    public Person3(String name) {
        this.name = name;
    }

    public Person3(int age) {
        this.age = age;
    }

    private Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void sayChinese() {
        Log.i(TAG, "sayChinese: "+"作者："+AUTHOR+",国籍："+NATIONAL);
    }

    @Override
    public String sayHello(String name, int age) {
        return name+"你好，今年"+age+"岁";
    }

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

    private String doSomething(String d) {
        return "name = " + name + ";age = " + age + ";d = " + d;
    }

    private static String work(){
        return "static";
    }

}
