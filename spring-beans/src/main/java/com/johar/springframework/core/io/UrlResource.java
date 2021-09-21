package com.johar.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName: UrlResource
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/20 00:22
 * @Since: 1.0.0
 */
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try{
            return connection.getInputStream();
        } catch (IOException e){
            if (connection instanceof HttpURLConnection){
                ((HttpURLConnection)connection).disconnect();
            }

            throw e;
        }
    }
}