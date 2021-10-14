package com.johar.springframework.core.convert.support;

import com.johar.springframework.core.convert.converter.Converter;
import com.johar.springframework.core.convert.converter.ConverterFactory;
import com.johar.springframework.utils.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * @ClassName: StringToNumberConverterFactory
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 21:21
 * @Since: 1.0.0
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}