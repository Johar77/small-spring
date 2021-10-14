package com.johar.springframework.core.convert.converter;

/**
 * @ClassName: Converter
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 20:32
 * @Since: 1.0.0
 */
public interface Converter<S, T> {

    T convert(S source);
}
