package com.stuart.testcontroller.model;


public class Dog {

    private String id;
    private String name;
    private int age;

    public boolean isSmellyIndicator() {
        return smellyIndicator;
    }

    public void setSmellyIndicator(boolean smellyIndicator) {
        this.smellyIndicator = smellyIndicator;
    }

    private boolean smellyIndicator;

    public Dog(String id, String name, int age, boolean smellyIndicator) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.smellyIndicator = smellyIndicator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
