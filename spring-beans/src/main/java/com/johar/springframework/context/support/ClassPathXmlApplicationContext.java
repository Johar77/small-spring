package com.johar.springframework.context.support;

/**
 * @ClassName: ClassPathXmlApplicationContext
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/9/21 17:18
 * @Since: 1.0.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocation){
        this(new String[]{ configLocation });
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}