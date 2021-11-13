package com.cos.validation_test.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class VisitorCounter implements ServletContextListener { // 웹어플리이션이 종료될때 작동 (가장 늦게 실행)
	@Override
	public void contextDestroyed(ServletContextEvent sc) {
		System.out.println("contextdestroyed");
	} // 웹어플리이션이 실행될때 작동 ( 가장 먼저 실행)

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("contextInitialized");
	}
}
