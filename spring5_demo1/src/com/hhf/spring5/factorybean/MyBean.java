package com.hhf.spring5.factorybean;

import com.hhf.spring5.bean.Dept;
import com.hhf.spring5.bean.Emp;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Dept> {
    @Override
    public Dept getObject() throws Exception {
        Dept dept = new Dept();
        dept.setDname("技术部");
        return dept ;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
