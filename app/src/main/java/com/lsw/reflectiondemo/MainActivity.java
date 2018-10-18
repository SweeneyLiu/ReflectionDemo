package com.lsw.reflectiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMethodNum();
        getClassName();
        getSuperClass();
        getType();
        getAllConstructors();
        createPerson();
        createPerson2();
        getClassInterface();
        getAllMethods();
        getAllFields();
        getSpecificMethod();
        getTwoParamsMethod();
        testGetSetMethod();
        testField();
        testArray();
        modifyArray();
        getPrivateMethod();
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

    /**
     * 基本类型都有TYPE属性
     */
    private void getType(){
        Log.i(TAG, "Boolean.TYPE: " + Boolean.TYPE);
        Log.i(TAG, "Byte.TYPE: " + Byte.TYPE);
        Log.i(TAG, "Character.TYPE: " + Character.TYPE);
        Log.i(TAG, "Short.TYPE: " + Short.TYPE);
        Log.i(TAG, "Integer.TYPE: " + Integer.TYPE);
        Log.i(TAG, "Long.TYPE: " + Long.TYPE);
        Log.i(TAG, "Float.TYPE: " + Float.TYPE);
        Log.i(TAG, "Double.TYPE: " + Double.TYPE);
        Log.i(TAG, "Void.TYPE: " + Void.TYPE);
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

    private void createPerson2(){
        Class c = null;
        Person2 person = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person2");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor[] constructor = c.getConstructors();

        try {
            try {
                person = (Person2)constructor[0].newInstance("lisi",21);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "createPerson2: " + person.getName());
    }

    private void getClassInterface(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class[] cInterfaces = c.getInterfaces();

        for(int i = 0;i<cInterfaces.length;i++){
            Log.i(TAG, "getClassInterface: "+cInterfaces[i]);
        }

    }

    private void getSuperClass(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class sClass = c.getSuperclass();

        Log.i(TAG, "getSuperClass: "+sClass);

    }

    private void getAllConstructors(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取类的所有public构造方法
        Constructor[] cons = c.getConstructors();
        Log.i(TAG, "getPublicConstructors:length = "+cons.length);
        for(int i = 0;i<cons.length;i++){
            Log.i(TAG, "getPublicConstructors: "+"权限："+ Modifier.toString(cons[i].getModifiers())+";名称："+cons[i].getName());

            Class[] p = cons[i].getParameterTypes();

            for(int j = 0;j<p.length;j++){
                Log.i(TAG, "getPublicConstructors:参数 "+p[j].getName());
            }
        }

        //获取类的所有构造方法
        Constructor[] allCons = c.getDeclaredConstructors();
        Log.i(TAG, "getAllConstructors:length = "+allCons.length);
        for(int i = 0;i<allCons.length;i++){
            Log.i(TAG, "getAllConstructors: "+"权限："+ Modifier.toString(allCons[i].getModifiers())+";名称："+allCons[i].getName());

            Class[] p = allCons[i].getParameterTypes();

            for(int j = 0;j<p.length;j++){
                Log.i(TAG, "getAllConstructors:参数 "+p[j].getName());
            }
        }

        //int参数构造方法
        try {
            Class[] p1 = {int.class};
            Constructor constructor1 = c.getDeclaredConstructor(p1);
            printParam(constructor1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //int参数构造方法
        try {
            Class[] p2 = {String.class,int.class};
            Constructor constructor2 = c.getDeclaredConstructor(p2);
            printParam(constructor2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void printParam(Constructor c){

        Class[] p = c.getParameterTypes();

        for(int j = 0;j<p.length;j++){
            Log.i(TAG, "printParam:参数 "+p[j].getName());
        }
    }

    private void getAllMethods(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method[] methods = c.getMethods();

        for(int i = 0;i<methods.length;i++){

            Class r = methods[i].getReturnType();
            Log.i(TAG, "getAllMethods:返回类型 "+r.getName());

            String methodType = Modifier.toString(methods[i].getModifiers());

            Log.i(TAG, "getAllMethods:方法名称 "+methods[i].getName());
            Log.i(TAG, "getAllMethods:方法类型 "+methodType);

            Class[] p = methods[i].getParameterTypes();

            for(int j = 0;j<p.length;j++){
                Log.i(TAG, "getAllMethods:参数 "+p[j].getName());
            }

            Class[] ex = methods[i].getExceptionTypes();

            for(int k = 0;k<ex.length;k++){
                Log.i(TAG, "getAllMethods:异常 "+ex[k].getName());
            }

        }

    }

    private void getAllFields(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = c.getFields();

        for(int i = 0;i<fields.length;i++){
            Class r = fields[i].getType();
            Log.i(TAG, "getAllFields:父类公共属性名称 "+fields[i].getName());
            Log.i(TAG, "getAllFields:父类公共修饰符 "+Modifier.toString(fields[i].getModifiers()));
            Log.i(TAG, "getAllFields:父类公共类型名称 "+r.getName());
        }

        Field[] dFields = c.getDeclaredFields();

        for(int i = 0;i<dFields.length;i++){
            Class r = dFields[i].getType();
            Log.i(TAG, "getAllFields:属性名称 "+dFields[i].getName());
            Log.i(TAG, "getAllFields:修饰符 "+Modifier.toString(dFields[i].getModifiers()));
            Log.i(TAG, "getAllFields:类型名称 "+r.getName());
        }

    }

    private void getSpecificMethod(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Method method = c.getMethod("sayChinese");
            try {
                method.invoke(c.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void getTwoParamsMethod(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Method method = c.getMethod("sayHello",String.class,int.class);
            String result;
            try {
                result = (String) method.invoke(c.newInstance(),"liushuwei",20);
                Log.i(TAG, "getTwoParamsMethod: "+result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void testGetSetMethod(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Object object = c.newInstance();
            setter(object,"age",20,int.class);
            getter(object,"age");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void getter(Object obj,String att){
        try {
            Method method = obj.getClass().getMethod("get"+initStr(att));
            try {
                Log.i(TAG, "getter: "+method.invoke(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public void setter(Object obj,String att,Object value,Class type){
        try {
            Method method = obj.getClass().getMethod("set"+initStr(att),type);
            try {
                Log.i(TAG, "setter: "+method.invoke(obj,value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    private String initStr(String old){
        String str = old.substring(0,1).toUpperCase()+old.substring(1);
        return str;
    }

    private void testField(){
        Class c = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Object object = c.newInstance();
            try {
                Field nameField = c.getDeclaredField("name");
                Field ageField = c.getDeclaredField("age");
                nameField.setAccessible(true);
                ageField.setAccessible(true);
                nameField.set(object,"liushuwei");
                ageField.set(object,20);
                Log.i(TAG, "testField: name = "+nameField.get(object)+";age = "+ageField.get(object));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void testArray(){

        int[] temp = {1,2,3};

        Class c = temp.getClass().getComponentType();

        Log.i(TAG, "testArray: name = "+c.getName());
        Log.i(TAG, "testArray: "+ Array.getLength(temp));
        Log.i(TAG, "testArray: "+ Array.get(temp,0));
        Array.set(temp,0,6);
        Log.i(TAG, "testArray: "+Array.get(temp,0));
    }


    private void modifyArray(){

        int[] temp = {1,2,3};
        String[] t ={"lsw","lgh","lsy"};
        print(temp);
        String[] nt = (String[])incArray(t,8);
        print(nt);
    }


    private Object incArray(Object obj,int len){

        Class c = obj.getClass();
        Class arr = c.getComponentType();
        Object new0 = Array.newInstance(arr,len);
        int co = Array.getLength(obj);
        System.arraycopy(obj,0,new0,0,co);
        return new0;

    }

    private void print(Object obj){

        Class c = obj.getClass();
        if(!c.isArray()){
            return;
        }

        Class arr = c.getComponentType();
        for(int i = 0;i<Array.getLength(obj);i++){
            Log.i(TAG, "print: "+Array.get(obj,i));
        }

    }

    private void getPrivateMethod(){
        Class c = null;
        Constructor constructor = null;
        Person3 person3 = null;
        try {
            c = Class.forName("com.lsw.reflectiondemo.Person3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class[] p3 = {String.class};
        try {
            constructor = c.getDeclaredConstructor(p3);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        try {
            person3 = (Person3)(constructor.newInstance("wanger"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Method method;
        String result = null;

        try {
            Class[] p4 = {String.class};
            method = c.getDeclaredMethod("doSomething", p4);
            method.setAccessible(true);
            try {
                result = (String)(method.invoke(person3,"test"));
                Log.i(TAG, "getPrivateMethod: "+result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //private static 方法的调用
        try {
            Method method1 = c.getDeclaredMethod("work");
            method1.setAccessible(true);
            try {
                Log.i(TAG, "getPrivateMethod: "+(String)method1.invoke(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
