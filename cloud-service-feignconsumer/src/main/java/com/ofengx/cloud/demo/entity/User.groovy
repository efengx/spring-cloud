package com.ofengx.cloud.demo.entity

class User {
    private String name
    private Integer age

    User() {
        super()
    }
    User(String name, Integer age) {
        super()
        this.name = name
        this.age = age
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Integer getAge() {
        return age
    }

    void setAge(Integer age) {
        this.age = age
    }
}
