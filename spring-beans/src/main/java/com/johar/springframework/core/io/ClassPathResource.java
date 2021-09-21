package com.johar.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.johar.springframework.beans.BeansException;
import com.johar.springframework.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: ClassPathResource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 23:46
 * @Since: 1.0.0
 */
public class ClassPathResource implements Resource{
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null){
            throw new BeansException(this.path + "can not be opened because it does not exist");
        }
        return inputStream;
    }

    public String getPath() {
        return path;
    }
}