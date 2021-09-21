package com.johar.springframework.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: PropertyValues
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 21:21
 * @Since: 1.0.0
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        Optional<PropertyValue> propertyValue = propertyValueList.stream().filter(p -> p.getName().equals(propertyName)).findFirst();
        if (propertyValue.isPresent()){
            return propertyValue.get();
        } else {
            return null;
        }
    }
}