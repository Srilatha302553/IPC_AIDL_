package com.srilatha.ClientSideExample.bean;

import android.os.Bundle;

public class CatalogueBean {
    private String name;
    private Class clazz;
    private Bundle bundle;

    public CatalogueBean() {
    }

    public CatalogueBean(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }
    public CatalogueBean(String name, Class clazz, Bundle bundle) {
        this.name = name;
        this.clazz = clazz;
        this.bundle = bundle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
