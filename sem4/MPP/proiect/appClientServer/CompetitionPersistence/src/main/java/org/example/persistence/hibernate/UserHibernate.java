package org.example.persistence.hibernate;

import org.example.model.User;
import org.example.persistence.IUserRepository;
import org.hibernate.Session;

public class UserHibernate implements IUserRepository {

    @Override
    public void add(User elem) {
    }

    @Override
    public void delete(User elem) {
        if (elem.getId() == null)
        {
            throw new IllegalArgumentException("id must not be null");
        }
    }

    @Override
    public void update(User elem, Integer id) {

    }

    @Override
    public User findById(Integer id) {
        if (id == null)
        {
            throw new IllegalArgumentException("id must not be null");
        }

        return null;
    }

    @Override
    public Iterable<User> findAll()
    {
        try (Session session = HibernateUtils.getSessionFactory().openSession())
        {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtils.getSessionFactory().openSession())
        {
            return session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", email)
                    .uniqueResult();
        }
    }
}
