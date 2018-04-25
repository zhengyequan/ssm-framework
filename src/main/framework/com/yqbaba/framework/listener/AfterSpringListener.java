package com.yqbaba.framework.listener;

import com.yqbaba.test.service.KafkaConsumer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AfterSpringListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        KafkaConsumer consumer = (KafkaConsumer)springContext.getBean("kafkaConsumer");
        new Thread(consumer).start();
    }
}
