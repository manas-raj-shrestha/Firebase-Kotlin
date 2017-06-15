package com.droid.manasshrestha.firebase;

/**
 * Created by ManasShrestha on 6/13/17.
 */

public class Name {

    public Name() {

    }

    public Name(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int age;


}
