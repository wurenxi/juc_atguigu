package com.bilibili.juc.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gxl
 * @description
 * @createDate 2022/7/9 17:44
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("z3", 22);
        User z33 = new User("z3", 22);
        User li4 = new User("li4", 28);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z33, li4) + "\t" + atomicReference.get()); // false	User{username='z3', age=22}
        System.out.println(atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get()); // true	User{username='li4', age=28}
    }
}

class User {

    String username;

    int age;

    public User() {
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
