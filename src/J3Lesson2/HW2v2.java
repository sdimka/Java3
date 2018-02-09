package J3Lesson2;


import J3Lesson2.Products;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW2v2 {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    Session session = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
                settings.put("hibernate.connection.url", "jdbc:mysql://35.198.121.82:3306/DB1?cloudSqlInstance=my0test0bd\"");
                settings.put("hibernate.connection.username", "sd30");
                settings.put("hibernate.connection.password", "pnpIpwm2b7");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
                settings.put("hibernate.hbm2ddl.auto", "update");

                registry = registryBuilder.applySettings(settings).build();

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(Products.class);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    // -create,-init,-r,-d,-getprice,-setprice, -list
    private void addProduct(String name, int cost) {
        session.beginTransaction();
        Products prd = new Products();
        prd.setTitle(name);
        prd.setCost(cost);
        session.save(prd);
        session.getTransaction().commit();
    }

    private List<Products> listProducts() {
        session.beginTransaction();
        List<Products> result = session.createQuery("from Products order by id").list();
        session.getTransaction().commit();
        return result;
    }

    private void init(){
        //session.beginTransaction();
        // List<Products> result = session.createQuery("from Products order by id").list();
        // session.getTransaction().commit();
        session.beginTransaction();
        // result.forEach(a -> session.delete(a));
        String hql = "delete from Products";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();

        for (int i = 1; i < 16; i++){
            addProduct("Товар" + i, i * 10);
        }
    }

    private void getPrice(String prd){
        session.beginTransaction();
        List<Products> result = session.createQuery("from Products where title = (:title)")
                .setParameter("title", prd).list();
        session.getTransaction().commit();
        if (result.isEmpty()){
            System.out.println(prd + " not found");
        } else result.forEach(a -> System.out.println(a.getTitle() +"\t\t"+ a.getCost()));
    }

    private void setPrice(String prd, int price){
        session.beginTransaction();
        List<Products> result = session.createQuery("from Products where title = (:title)")
                .setParameter("title", prd).list();
        if (result.isEmpty()){
            System.out.println(prd + " not found");
        } else {
            session.beginTransaction();
            result.forEach(a -> {});
        }
    }

    private void listRange(int lowPrice, int hiPrice){

    }

    public HW2v2() {

        try {
            session = HW2v2.getSessionFactory().openSession();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        if (session != null) {
            //List<Products> productsList = listProducts();
            //productsList.forEach(a -> System.out.println(a.getTitle()));
            //addProduct("Prd1", 10);
            //addProduct("Prd2", 20);
            init();
            getPrice("Товар10");

            if (session.isOpen())
                session.close();
        }
    }

    public static void main(String[] args) {
        new HW2v2();
        System.exit(0);
    }
}
