package com.johar.springframework.core.io;

import java.io.*;

/**
 * @ClassName: FileSystemResource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:19
 * @Since: 1.0.0
 */
public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getPath() {
        return path;
    }
}