package com.example.thinkpad.t47checklisthead;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 23/03/2018.
 * 1. show u how to get method generic return type.
 * 2. show u how to get method generic parameter type.
 * 3. show u how to get Array by reflection
 */

public class MyJavaTest {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        //tt: creating arrays
        int[] intArray = (int[]) Array.newInstance(int.class, 3);
        //tt： access arrays
        Array.set(intArray, 0, 124);
        //tt: obtaining the class object of an array
        Class stringArrayClass = String[].class;
        //tt: not so straightforward way is using Class.forName();
        Class intArray2 = Class.forName("[I");//tt: so [D is double, [F for float;
        // but when u need more than primitives
        Class stringArrayClass2 = Class.forName("[Ljava.lang.String;");//tt: so [Lcom.paul.MyClass; is ok,too

        //tt: obtaining the component type of an Array
        String[] strings = new String[3];
        Class stringArrayClass3 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass3.getComponentType();
        System.out.println(stringArrayComponentType);//output: class java.lang.String
    }
}
