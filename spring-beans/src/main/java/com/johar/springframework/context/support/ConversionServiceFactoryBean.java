package com.johar.springframework.context.support;

import com.johar.springframework.beans.factory.FactoryBean;
import com.johar.springframework.beans.factory.InitializingBean;
import com.johar.springframework.core.convert.converter.Converter;
import com.johar.springframework.core.convert.converter.ConverterFactory;
import com.johar.springframework.core.convert.converter.ConverterRegistry;
import com.johar.springframework.core.convert.converter.GenericConverter;
import com.johar.springframework.core.convert.support.ConversionService;
import com.johar.springframework.core.convert.support.DefaultConversionService;
import com.johar.springframework.core.convert.support.GenericConversionService;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * @ClassName: ConversionServiceFactoryBean
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 21:27
 * @Since: 1.0.0
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable
    private Set<?> converters;

    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry){
        if (converters != null){
            for (Object converter : converters){
                if (converter instanceof GenericConverter){
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?,?>){
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?,?>){
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}