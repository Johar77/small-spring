package com.johar.springframework.context;

import com.johar.springframework.beans.factory.HierarchicalBeanFactory;
import com.johar.springframework.beans.factory.ListableBeanFactory;
import com.johar.springframework.core.io.ResourceLoader;

/**
 * @ClassName: ApplicationContext
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 11:27
 * @Since: 1.0.0
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {


}
