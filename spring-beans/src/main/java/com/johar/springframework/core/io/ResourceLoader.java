package com.johar.springframework.core.io;

/**
 * @ClassName: ResourceLoader
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:27
 * @Since: 1.0.0
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
