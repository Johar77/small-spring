package com.johar.springframework.test.reference;

/**
 * @ClassName: Husband
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:44
 * @Since: 1.0.0
 */
public class Husband {

    private Wife wife;

    public String queryWife(){
        return "Husband.wife";
    }

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }
}