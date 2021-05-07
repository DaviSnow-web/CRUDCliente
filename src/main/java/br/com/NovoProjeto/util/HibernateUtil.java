package br.com.NovoProjeto.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(cfg.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static Connection getConexao() {
        Session sessao = getSession();

        Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
            @Override
            public Connection execute(Connection conn) throws SQLException {
                return conn;
            }
        });

        return conexao;
    }

}
