package com.example.thinkpad.t47checklisthead.JavaTest;

import android.annotation.TargetApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;
import java.util.function.Consumer;

/**
 * Created by paul on 2018/10/24
 * last modified at 21:44.
 * Desc: unfinished, need more practice on String manupilation
 *     这个 类要实现 cd 的这个效果，应该如何改进呢？
 * output：　2018-10-25 23:27:19
 cd /user/pal/a/
 pwd
 /
 use
 /
 pa
 /

 /

 */

public class StackTest {
    static  Stack<String> stack = new Stack<String>();


    static String  readLine1 = "cd /home/pal/kk"; // = Scanner.in()???
    @TargetApi(24)
    public static void main(String[] args) {
        String readLine = null;
        int counter = 10;
        while (counter-- > 0) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                readLine = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (readLine.equals("pwd")) {
                stack.forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.print(s);
                    }
                });
                continue;
            }

            //start from cd . , the . position
            if (readLine.charAt(3) == '/') {
                stack.clear();
            }
            for (int i = 3; i < readLine.length(); ) {

                if (readLine.charAt(i) == '/') {
                    stack.push("/");
                    i++;
                } else {
                    int watcher = i;
                    //a string, iterate till the '/' position
                    while (readLine.charAt(i) != '/') {
                        i++;
                    }
                    //now i at '/''s position, push the 就在它（/）前面的那个 small string into stack
                    String ele = readLine.substring(watcher, i - 1);
                    //if the input is .., then we need pop previous two
                    // W： user must input full: like /a/b/../ not /a/b/.. which without / at last
                    if (ele.equals("..")) {
                        stack.pop();
                        stack.pop();
                    } else {
                        //push whatever string we encounter
                        stack.push(ele);
                    }
                }
            }

        }
/*        while(true) {
            continue;
            int c = 0;
        }*/
    }


}
