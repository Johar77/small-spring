package com.johar.springframework.core.convert.converter;

/**
 * @ClassName: ConverterRegistry
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 20:34
 * @Since: 1.0.0
 */
public interface ConverterRegistry {

    void addConverter(Converter<?,?> converter);

    void addConverter(GenericConverter genericConverter);

    void addConverterFactory(ConverterFactory<?,?> converterFactory);
}
