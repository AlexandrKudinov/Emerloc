package com.kudinov.db;

import java.util.List;
import org.hibernate.Session;

public class PlayerDAOImpl implements PlayerDAO{

    public void save(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(player);
        session.flush();
        session.close();
    }

    public void delete(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(player);
        session.flush();
        session.close();
    }

    public void incrWon(Player player) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int won = player.getWon();
        player.setWon(++won);
        session.saveOrUpdate(player);
        session.close();
    }


    public List<Player> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(Player.class).list();
    }

    public Player getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Player player = session.get(Player.class, id);
        return player;
    }


}
