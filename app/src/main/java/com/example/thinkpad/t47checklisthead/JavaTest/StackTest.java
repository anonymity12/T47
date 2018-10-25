package com.example.thinkpad.t47checklisthead.JavaTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by paul on 2018/10/24
 * last modified at 21:44.
 * Desc: unfinished, need more practice on String manupilation
 */

public class StackTest {
    static  Stack<String> stack = new Stack<String>();


    static String  readLine = "cd /home/pal/kk"; // = Scanner.in()???

    public static void main(String[] args) {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try {
            String line=br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (readLine.equals("pwd")) {
            System.out.println(stack.toArray() + "");
        }
        //start from cd . the . position
        for (int i = 2; i < readLine.length(); i++) {

            if (readLine.charAt(i) == '/') {
                stack.clear();
                int watcher = i;
                while (readLine.charAt(i) != '/') {
                    i++;
                }
                String ele = readLine.substring(watcher, i - 1);//no the '/'
                stack.push(ele);
            }

        }
    }


}
