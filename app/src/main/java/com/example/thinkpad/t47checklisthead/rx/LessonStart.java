package com.example.thinkpad.t47checklisthead.rx;

/**
 * Created by thinkpad on 2018/3/27.
 */

public class LessonStart {
    public static void main(String[] args) {
        Observable teacher = new Teacher();
        Observer xiaoming = new Student();
        xiaoming.setName("xiaoMing");
        Observer xiaohong = new Student();
        xiaohong.setName("xiaoHong");

        teacher.attach(xiaohong);
        teacher.attach(xiaoming);

        teacher.notifyObservers("anyone here???");
    }
}
