package com.johar.springframework.core.convert.support;

import com.sun.istack.internal.Nullable;

/**
 * @ClassName: ConversionService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 20:38
 * @Since: 1.0.0
 */
public interface ConversionService {

    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);
}
