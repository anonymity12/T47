package com.example.thinkpad.t47checklisthead;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by paul on 23/03/2018.
 * 1. show u how to get method generic return type.
 * 2. show u how to get method generic parameter type.
 * 3. show u how to get Array by reflection
 * 4. show u how to use Future Task/ multithreading
 * J5. test can we use digit as variable name: we can't
 * J6. try to solve qiang ge's question on AbstractList, no good solution.
 */

public class MyJavaTest {
    public static void main(String[] args) {
        ArrayList<String > stringArrayList = new ArrayList<>();
        stringArrayList.add("str1");
        stringArrayList.add("str2");
        stringArrayList.add("str3");


    }
}
