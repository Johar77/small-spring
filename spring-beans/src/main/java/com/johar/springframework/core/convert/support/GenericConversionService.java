package com.johar.springframework.core.convert.support;

import com.johar.springframework.core.convert.converter.Converter;
import com.johar.springframework.core.convert.converter.ConverterFactory;
import com.johar.springframework.core.convert.converter.ConverterRegistry;
import com.johar.springframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * @ClassName: GenericConversionService
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 20:41
 * @Since: 1.0.0
 */
public class GenericConversionService implements ConversionService, ConverterRegistry {

    private Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new HashMap<>();

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair typeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(typeInfo, converter);
        for (GenericConverter.ConvertiblePair convertiblePair : converterAdapter.getConvertibleTypes()){
            converters.put(convertiblePair, converterAdapter);
        }
    }

    @Override
    public void addConverter(GenericConverter genericConverter) {
        for (GenericConverter.ConvertiblePair convertiblePair : genericConverter.getConvertibleTypes()){
            converters.put(convertiblePair, genericConverter);
        }
    }


    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        GenericConverter.ConvertiblePair typeInfo = getRequiredTypeInfo(converterFactory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo, converterFactory);
        for (GenericConverter.ConvertiblePair convertiblePair : converterFactoryAdapter.getConvertibleTypes()){
            converters.put(convertiblePair, converterFactoryAdapter);
        }
    }

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        GenericConverter converter = getConverter(sourceType, targetType);
        return converter != null;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        Class<?> sourceType = source.getClass();
        GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    private GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object){
        Type[] types = object.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class sourceType = (Class) actualTypeArguments[0];
        Class targetType = (Class) actualTypeArguments[1];
        return new GenericConverter.ConvertiblePair(sourceType, targetType);
    }

    private GenericConverter getConverter(Class<?> sourceType, Class<?> targetType){
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourCandidate : sourceCandidates){
            for (Class<?> targetCandidate : targetCandidates){
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourCandidate, targetCandidate);
                GenericConverter converter = converters.get(convertiblePair);
                if (converter != null){
                    return converter;
                }
            }
        }

        return null;
    }

    private List<Class<?>> getClassHierarchy(Class<?> clazz){
        List<Class<?>> hierarchy = new ArrayList<>();
        while (clazz != null){
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }

        return hierarchy;
    }

    private final class ConverterAdapter implements GenericConverter{

        private final ConvertiblePair typeInfo;

        private final Converter<Object, Object> converter;

        public ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converter.convert(source);
        }
    }

    private final class ConverterFactoryAdapter implements GenericConverter{
        private final ConvertiblePair typeInfo;

        private final ConverterFactory<Object, Object> converterFactory;

        public ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}