package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/myData?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "admin123123";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public Connection getConnection() throws ClassNotFoundException, SQLException  {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userName, password);
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", url);
                settings.put("hibernate.connection.username",userName);
                settings.put("hibernate.connection.password",password);
                settings.put("hibernate.show_sql", "true");

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class);

                sessionFactory = sources.buildMetadata().buildSessionFactory();

            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                if(registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
