package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import by.it.academy.util.JPA;
import by.it.academy.util.JPAUtil;
import lombok.Cleanup;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDBRepository implements UsersRepository<User>{
    private final Logger logger = Logger.getLogger(String.valueOf(UserDBRepository.class));
    JPA jpa;

    public UserDBRepository(List<User> users) {
        this.jpa = new JPAUtil();
    }

    @Override
    /**
     * This method is used to write to the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     */
    public void createUser(User user) {
       @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("create User where login= :login and password = :password");
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        List<User> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        entityManager.persist(resultList);
        transaction.commit();

    }

    @Override
    /**
     * This method is used to read the Account from the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return optionalUserList
    it's Optional.ofNullable(resultList).
     */
    public Optional<List<User>>readUser(User user) {
        EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery
                ("select user from User as user where user.login= :login and user.password= :password");
        query.setParameter("login", user.getLogin());
       query.setParameter("password", user.getPassword());
        List<User> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        Optional<List<User>> optionalUserList = Optional.ofNullable(resultList);
        transaction.commit();

        return optionalUserList;
    }

    @Override
    /**
     * This method is used to remove users from the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return false.
     */
    public boolean deleteUser(User user) {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("delete User where id= :id");
        query.setParameter("id", user.getId());
        List resultList = query.getResultList();
        query.executeUpdate();
        transaction.commit();
        return false;
    }

    @Override
    /**
     * This method is used to update the user in the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return false.
     */
    public boolean updateUser(User user, User newUser) {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update User set login= :newLogin and password= :newPassword where " +
                "id = :id"); // TODO: 23.06.22 update по id
        query.setParameter("newLogin", newUser.getLogin());
        query.setParameter("newPassword", newUser.getPassword());

        List<User> resultList = query.getResultList();
        entityManager.persist(resultList);
        transaction.commit();
        return false;
    }

    @Override
    /**
     * This method is used to read all users from the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return Optional.ofNullable(resultList).
     */
    public Optional<List<User>> readAllUser() {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("select user from User as user");
        List<User> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        transaction.commit();
        return Optional.ofNullable(resultList);
    }
}
