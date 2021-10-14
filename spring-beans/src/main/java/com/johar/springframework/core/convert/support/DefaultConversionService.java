package com.johar.springframework.core.convert.support;

import com.johar.springframework.core.convert.converter.ConverterRegistry;

/**
 * @ClassName: DefaultConversionService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 21:20
 * @Since: 1.0.0
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry){
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}