package com.shukalovich.database.hibernate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HibernateFactory {
    private static final HibernateFactory INSTANCE = new HibernateFactory();
    private static SessionFactory sessionFactory;

    {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static HibernateFactory getInstance() {
        return INSTANCE;
    }
}