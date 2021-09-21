package com.johar.springframework.beans;

/**
 * @ClassName: PropertyValue
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 21:21
 * @Since: 1.0.0
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}