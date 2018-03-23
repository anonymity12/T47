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
 */

public class MyJavaTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MyClass.class.getMethod("getStringList", List.class);
        //we want the generic param type contained in this method.
        Type [] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type [] parameterArgTypes = aType.getActualTypeArguments();
                for (Type parameterArgType : parameterArgTypes) {
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.print(parameterArgClass);

                }
            }
        }


    }

    class MyClass {

        protected List<String> stringList = new ArrayList<>();

        public void getStringList(List<Integer> param) {
            return;
        }
    }
}
