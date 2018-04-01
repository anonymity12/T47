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
 * 4. 最长子字符串的获得，by 简单的数组方式
 */

public class MyJavaTest {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.print(findGreatest(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));

    }

    private static int findGreatest(int[] scoreArr) {
        //boundary check

        int curSum = 0;
        int greateestSum = 0;
        for (int i = 0; i < scoreArr.length; i++) {
            if (curSum <= 0) {
                curSum = scoreArr[i];
            } else {
                curSum += scoreArr[i];
            }
            if (curSum > greateestSum) {
                greateestSum = curSum;
            }
        }
        return greateestSum;
    }

    /**
     * unfinish, no good thoughts
     * */
    private static void func(int startP, String s, int desire) {
        int counter = 0;
        for (int i = startP; i < s.length() - startP; i ++) {
            if (s.charAt(startP) == s.charAt(++startP)) {
                counter ++;
                if (counter == desire) {
                    if (s.charAt(startP) != s.charAt(startP + 1)) {
                        System.out.println();
                    }
                }
            }
        }
    }


}
