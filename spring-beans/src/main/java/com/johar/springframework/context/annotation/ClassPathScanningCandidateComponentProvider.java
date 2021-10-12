package com.johar.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.johar.springframework.beans.factory.config.BeanDefinition;
import com.johar.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName: ClassPathScanningCandidateComponetProvider
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/12 16:52
 * @Since: 1.0.0
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes){
            candidates.add(new BeanDefinition(clazz));
        }

        return candidates;
    }
}