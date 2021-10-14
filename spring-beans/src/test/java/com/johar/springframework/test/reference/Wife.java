package com.johar.springframework.test.reference;

/**
 * @ClassName: Wife
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/10/14 16:45
 * @Since: 1.0.0
 */
public class Wife {
    private Husband husband;

    private IMother mother;

    public String queryHusband(){
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }
}