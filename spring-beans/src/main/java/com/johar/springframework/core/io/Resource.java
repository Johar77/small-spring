package com.johar.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: Resource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/19 23:45
 * @Since: 1.0.0
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
