package com.example.thinkpad.t47checklisthead;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 23/03/2018.
 * 1. show u how to get method generic return type.
 * 2. show u how to get method generic parameter type.
 * 3. mk interview cheat
 */

public class MyJavaTest {
    public static void main(String[] args) throws NoSuchMethodException {
//        System.out.print(foo(17, 8));
        int a,b,c;
        a=b=c=5;
        a=++b-(++c);//0,6,6
        System.out.println(a+","+b+","+c);
        a=++b+(++c);//
        System.out.println(a+","+b+","+c);
        a = (b--) + (c--);
        System.out.println(a+","+b+","+c);
        a = (b--);//tt: u will got a=6, but b will be 5 at end
        System.out.println(a+"");

    }

    public static int foo(int x, int y) {
        if (x <= 0 || y <= 0) {
            return 1;
        }
        else {
            return 3 * foo(x - 6, y / 2);
        }
    }

    class MyClass {

        protected List<String> stringList = new ArrayList<>();

        public void getStringList(List<Integer> param) {
            return;
        }
    }
}
