package dao;

import entity.NewUser;
import entity.User;
import entity.UserEntity;
import io.vavr.collection.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

public class UserDaoJPA implements  Dao<User, NewUser> {

    private EntityManager em;

    public UserDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getAll() {
        em.getTransaction().begin();
        Query query = em.createQuery("FROM UserEntity");
        List<User> result = List.ofAll(query.getResultStream().map(e -> new User((UserEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<User> get(long id) {
        return getEntity(id).map(user -> new User(user));
    }

    @Override
    public void save(NewUser obj) {
        em.getTransaction().begin();
        em.persist(new UserEntity(obj));
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void update(User obj) {

    }

    private Optional<UserEntity> getEntity(long id){
        try {
            return Optional.of(em
                    .createQuery("SELECT a from UserEntity a WHERE id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
