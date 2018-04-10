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
 */

public class MyJavaTest implements Callable<Integer>{
    public static void main(String[] args)
    {
        MyJavaTest ctt = new MyJavaTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for(int i = 0;i < 100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==20)
            {
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try
        {
            System.out.println("子线程的返回值："+ft.get());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public Integer call() throws Exception
    {
        int i = 0;
        for(;i<100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}

/*output
* "C:\Program Files\Android\Android Studio\jre\bin\java" -Didea.launcher.port=52962 "-Didea.launcher.bin.path=C:\Program Files\Android\Android Studio\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\thinkpad\AppData\Local\Android\Sdk\platforms\android-26\android.jar;C:\Users\thinkpad\AppData\Local\Android\Sdk\platforms\android-26\data\res;C:\Users\thinkpad\ASProject\T47\app\build\intermediates\classes\debug;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxbinding-appcompat-v7-2.0.0.aar\16602c88cf204bb012a6150cecbe271d\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-media-compat-26.1.0.aar\3146b5120bb92c344212f69fb334e83f\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-media-compat-26.1.0.aar\3146b5120bb92c344212f69fb334e83f\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\recyclerview-v7-26.1.0.aar\2ba683d29018b2eccb62646aa2e62c7e\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\recyclerview-v7-26.1.0.aar\2ba683d29018b2eccb62646aa2e62c7e\res;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\android.arch.lifecycle\common\1.0.0\e414a4cb28434e25c4f6aa71426eb20cf4874ae9\common-1.0.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxbinding-recyclerview-v7-2.0.0.aar\8f1d5954f40582e6fb8b095010af0057\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-fragment-26.1.0.aar\dc8a648474b6f3e0f787dad51042b279\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-vector-drawable-26.1.0.aar\34ad332e9f971fbae229831d53f91cb4\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\runtime-1.0.0.aar\3aa3d0c5cdf2bf010561c85adc2b209b\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.squareup.okio\okio\1.11.0\840897fcd7223a8143f1d9b6f69714e7be34fd50\okio-1.11.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\appcompat-v7-26.1.0.aar\311d3750efc480746f9525d640cf2104\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\appcompat-v7-26.1.0.aar\311d3750efc480746f9525d640cf2104\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxbinding-support-v4-2.0.0.aar\809c42f532516ab2895ca55daed95836\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.android.support\support-annotations\26.1.0\814258103cf26a15fcc26ecce35f5b7d24b73f8\support-annotations-26.1.0.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\io.reactivex.rxjava2\rxjava\2.0.8\47fadd5ff70f325e520fbc491227bd9283a48f95\rxjava-2.0.8.jar;C:\Users\thinkpad\AppData\Local\Android\Sdk\extras\m2repository\com\android\support\constraint\constraint-layout-solver\1.0.2\constraint-layout-solver-1.0.2.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxbinding-design-2.0.0.aar\fa59efad7cb59370570ce6bd739cc0f3\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-core-utils-26.1.0.aar\e114d77a8866ac744c73423d37721715\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\constraint-layout-1.0.2.aar\4e27abb8efd7a096ddd96fdcbf0fd122\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\constraint-layout-1.0.2.aar\4e27abb8efd7a096ddd96fdcbf0fd122\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-core-ui-26.1.0.aar\401dd4fdf06b7f545e497a845503334c\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\lib-2.4.aar\6ba18058d4bc4ab7864d8ca73136020a\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\lib-2.4.aar\6ba18058d4bc4ab7864d8ca73136020a\res;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.google.code.gson\gson\2.8.2\3edcfe49d2c6053a70a2a47e4e1c2f94998a49cf\gson-2.8.2.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\butterknife-8.8.1.aar\da7b5909801f0e1be783663b49253558\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-compat-26.1.0.aar\c389ab98cef0da7ee7e93b21b53ea706\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-compat-26.1.0.aar\c389ab98cef0da7ee7e93b21b53ea706\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.jakewharton\butterknife-annotations\8.8.1\bc373fb6bc7bca3035041b924f158fd2b946ee8d\butterknife-annotations-8.8.1.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.squareup.retrofit2\converter-gson\2.2.0\a09926806199499a20b2f5168779499abfa33342\converter-gson-2.2.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxandroid-2.0.1.aar\a28d4ddc454897377a94eeafbcf47dc2\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.squareup.okhttp3\okhttp\3.6.0\69edde9fc4b01c9fd51d25b83428837478c27254\okhttp-3.6.0.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\android.arch.core\common\1.0.0\a2d487452376193fc8c103dd2b9bd5f2b1b44563\common-1.0.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\support-v4-26.1.0.aar\6a704d48ee28671621ef8569a5a0ac6f\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.squareup.retrofit2\adapter-rxjava2\2.2.0\3ee0e527143abbe130ddba4c1fa6c4be5d66ed5c\adapter-rxjava2-2.2.0.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\org.reactivestreams\reactive-streams\1.0.0\14b8c877d98005ba3941c9257cfe09f6ed0e0d74\reactive-streams-1.0.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\animated-vector-drawable-26.1.0.aar\b21822378c9ea6c3ddcb32b777f1e49e\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\rxbinding-2.0.0.aar\0d785d0476b90001b144212a4860dd40\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\modules-2\files-2.1\com.squareup.retrofit2\retrofit\2.2.0\41e67dba73c3347e4503761642c39d0e06ca1f2\retrofit-2.2.0.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\design-26.1.0.aar\c575a094dfa38f70eff02265ce5cd9bd\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\design-26.1.0.aar\c575a094dfa38f70eff02265ce5cd9bd\jars\classes.jar;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\transition-26.1.0.aar\5c6339fa25a5a5cafd017a13b32a043d\res;C:\Users\thinkpad\.gradle\caches\transforms-1\files-1.1\transition-26.1.0.aar\5c6339fa25a5a5cafd017a13b32a043d\jars\classes.jar;C:\Program Files\Android\Android Studio\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMainV2 com.example.thinkpad.t47checklisthead.MyJavaTest
main 的循环变量i的值0
main 的循环变量i的值1
main 的循环变量i的值2
main 的循环变量i的值3
main 的循环变量i的值4
main 的循环变量i的值5
main 的循环变量i的值6
main 的循环变量i的值7
main 的循环变量i的值8
main 的循环变量i的值9
main 的循环变量i的值10
main 的循环变量i的值11
main 的循环变量i的值12
main 的循环变量i的值13
main 的循环变量i的值14
main 的循环变量i的值15
main 的循环变量i的值16
main 的循环变量i的值17
main 的循环变量i的值18
main 的循环变量i的值19
main 的循环变量i的值20
main 的循环变量i的值21
main 的循环变量i的值22
main 的循环变量i的值23
main 的循环变量i的值24
main 的循环变量i的值25
main 的循环变量i的值26
main 的循环变量i的值27
main 的循环变量i的值28
main 的循环变量i的值29
main 的循环变量i的值30
main 的循环变量i的值31
main 的循环变量i的值32
main 的循环变量i的值33
main 的循环变量i的值34
main 的循环变量i的值35
main 的循环变量i的值36
main 的循环变量i的值37
main 的循环变量i的值38
main 的循环变量i的值39
main 的循环变量i的值40
main 的循环变量i的值41
main 的循环变量i的值42
main 的循环变量i的值43
main 的循环变量i的值44
main 的循环变量i的值45
main 的循环变量i的值46
main 的循环变量i的值47
main 的循环变量i的值48
main 的循环变量i的值49
main 的循环变量i的值50
main 的循环变量i的值51
main 的循环变量i的值52
main 的循环变量i的值53
main 的循环变量i的值54
main 的循环变量i的值55
main 的循环变量i的值56
main 的循环变量i的值57
main 的循环变量i的值58
main 的循环变量i的值59
main 的循环变量i的值60
main 的循环变量i的值61
main 的循环变量i的值62
main 的循环变量i的值63
main 的循环变量i的值64
main 的循环变量i的值65
main 的循环变量i的值66
main 的循环变量i的值67
main 的循环变量i的值68
main 的循环变量i的值69
main 的循环变量i的值70
main 的循环变量i的值71
main 的循环变量i的值72
main 的循环变量i的值73
main 的循环变量i的值74
main 的循环变量i的值75
main 的循环变量i的值76
main 的循环变量i的值77
main 的循环变量i的值78
main 的循环变量i的值79
main 的循环变量i的值80
main 的循环变量i的值81
main 的循环变量i的值82
main 的循环变量i的值83
main 的循环变量i的值84
main 的循环变量i的值85
main 的循环变量i的值86
main 的循环变量i的值87
main 的循环变量i的值88
main 的循环变量i的值89
main 的循环变量i的值90
main 的循环变量i的值91
main 的循环变量i的值92
main 的循环变量i的值93
main 的循环变量i的值94
main 的循环变量i的值95
main 的循环变量i的值96
main 的循环变量i的值97
main 的循环变量i的值98
main 的循环变量i的值99
有返回值的线程 0
有返回值的线程 1
有返回值的线程 2
有返回值的线程 3
有返回值的线程 4
有返回值的线程 5
有返回值的线程 6
有返回值的线程 7
有返回值的线程 8
有返回值的线程 9
有返回值的线程 10
有返回值的线程 11
有返回值的线程 12
有返回值的线程 13
有返回值的线程 14
有返回值的线程 15
有返回值的线程 16
有返回值的线程 17
有返回值的线程 18
有返回值的线程 19
有返回值的线程 20
有返回值的线程 21
有返回值的线程 22
有返回值的线程 23
有返回值的线程 24
有返回值的线程 25
有返回值的线程 26
有返回值的线程 27
有返回值的线程 28
有返回值的线程 29
有返回值的线程 30
有返回值的线程 31
有返回值的线程 32
有返回值的线程 33
有返回值的线程 34
有返回值的线程 35
有返回值的线程 36
有返回值的线程 37
有返回值的线程 38
有返回值的线程 39
有返回值的线程 40
有返回值的线程 41
有返回值的线程 42
有返回值的线程 43
有返回值的线程 44
有返回值的线程 45
有返回值的线程 46
有返回值的线程 47
有返回值的线程 48
有返回值的线程 49
有返回值的线程 50
有返回值的线程 51
有返回值的线程 52
有返回值的线程 53
有返回值的线程 54
有返回值的线程 55
有返回值的线程 56
有返回值的线程 57
有返回值的线程 58
有返回值的线程 59
有返回值的线程 60
有返回值的线程 61
有返回值的线程 62
有返回值的线程 63
有返回值的线程 64
有返回值的线程 65
有返回值的线程 66
有返回值的线程 67
有返回值的线程 68
有返回值的线程 69
有返回值的线程 70
有返回值的线程 71
有返回值的线程 72
有返回值的线程 73
有返回值的线程 74
有返回值的线程 75
有返回值的线程 76
有返回值的线程 77
有返回值的线程 78
有返回值的线程 79
有返回值的线程 80
有返回值的线程 81
有返回值的线程 82
有返回值的线程 83
有返回值的线程 84
有返回值的线程 85
有返回值的线程 86
有返回值的线程 87
有返回值的线程 88
有返回值的线程 89
有返回值的线程 90
有返回值的线程 91
有返回值的线程 92
有返回值的线程 93
有返回值的线程 94
有返回值的线程 95
有返回值的线程 96
有返回值的线程 97
有返回值的线程 98
有返回值的线程 99
子线程的返回值：100

Process finished with exit code 0

* */
