package com.github.utils;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class SpringUtils {

    /**
     * Util method to debug Spring Contenxt - if something doesn't get autowired and loaded into the container correctly
     */
    public static List<Object> getInstantiatedSingletons(ApplicationContext ctx) {
        List<Object> singletons = new ArrayList<>();

        String[] all = ctx.getBeanDefinitionNames();

        ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
        for (String name : all) {
            Object s = clbf.getSingleton(name);
            if (s != null)
                singletons.add(s);
        }

        return singletons;

    }
}
