package com.johar.springframework.core.convert.converter;

/**
 * @ClassName: ConverterFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 20:33
 * @Since: 1.0.0
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
