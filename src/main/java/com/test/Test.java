package com.test;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Configuration cf=new Configuration().configure();
        SessionFactory sf=cf.buildSessionFactory();
        Session se=sf.openSession();


        Criteria criteria=se.createCriteria(Car1Entity.class);
        criteria.setFirstResult(2);
        criteria.setMaxResults(1);

        List list=criteria.list();
        Iterator iterator=list.iterator();

        Car1Entity car1Entity=null;

        while (iterator.hasNext()){
            car1Entity = (Car1Entity) iterator.next();
            System.out.println(car1Entity.getId() +","+car1Entity.getName());
        }

        Transaction ts=se.beginTransaction();
        car1Entity.setName("test");
        se.update(car1Entity);

        ts.commit();
        se.flush();
        se.clear();

        se.evict(car1Entity);
        int carid=car1Entity.getId();
        Car1Entity car1Entity1= (Car1Entity) se.get(Car1Entity.class,carid);

        String string=car1Entity1.getName();
        System.out.println(carid+"~"+string);

        se.close();
        sf.close();
    }
}
