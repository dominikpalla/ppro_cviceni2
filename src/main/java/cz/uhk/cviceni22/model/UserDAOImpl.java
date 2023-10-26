package cz.uhk.cviceni22.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> q = entityManager.createQuery("FROM User", User.class);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public List<User> getAllUsersWhereName(String name) {
        TypedQuery<User> q = entityManager.createQuery("FROM User WHERE name=:name", User.class);
        q.setParameter("name", name);
        List<User> users = q.getResultList();
        return users;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User u = entityManager.find(User.class, id);
        entityManager.remove(u);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
