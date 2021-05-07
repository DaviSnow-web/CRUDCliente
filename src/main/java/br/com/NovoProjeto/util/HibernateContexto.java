package br.com.NovoProjeto.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSession();
    }

    @Override
    public void contextDestroyed(ServletContextEvent vent) {
        HibernateUtil.getSession().close();
    }

}
