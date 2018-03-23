package com.example.thinkpad.t47checklisthead;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 23/03/2018.
 * 1. show u how to get method generic return type.
 */

public class MyJavaTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MyClass.class.getMethod("getStringList", null);

        Type returnType = method.getGenericReturnType();

        if(returnType instanceof ParameterizedType){
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for(Type typeArgument : typeArguments){
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }
    }

    class MyClass {

        protected List<String> stringList = new ArrayList<>();

        public List<String> getStringList() {
            return this.stringList;
        }
    }
}
