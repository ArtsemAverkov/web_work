package by.it.academy.repositories.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.util.JPA;
import by.it.academy.util.JPAUtil;
import lombok.Cleanup;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ProductAPIRepository implements ProductsRepository<ModelProduct> {
    JPA jpa;

    public ProductAPIRepository(List<ModelProduct> jpa) {
        this.jpa = new JPAUtil();
    }

    @Override
    /**
     * This method is used to write a New Product to the database.
     * @param query is a database query
     * @param queryResultList This is the result of the query in the database
     * @return false.
     */
    public boolean createProduct(ModelProduct modelProduct) {

        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("create ModelProduct where Model= :model " +
                "and Price = :price and Amount= : amount");
        query.setParameter("Model", modelProduct.getModel());
        query.setParameter("Price", modelProduct.getPrice());
        query.setParameter("Amount", modelProduct.getAmount());
        query.setParameter("product_id", modelProduct.getProduct());
        List<ModelProduct> queryResultList = query.getResultList();
        entityManager.persist(queryResultList);
        transaction.commit();
        return false;
    }

    @Override
    /**
     * This method is used to read the product from the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return resultListModelProduct returns Optional.ofNullable(resultList).
     */
    public  Optional<List<ModelProduct>> readProduct(ModelProduct modelProduct) {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery
                ("select m from ModelProduct as m where m.Model= :model and m.product= :product");
        query.setParameter("model", modelProduct.getModel());
        query.setParameter("product",modelProduct.getProduct());
        List<ModelProduct> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        Optional<List<ModelProduct>> resultListModelProduct = Optional.ofNullable(resultList);
        transaction.commit();
        return resultListModelProduct;
    }

    @Override
    /**
     * This method is used to update the product product in the DB.
     * @param query is a database query
     * @param queryResultList This is the result of the query in the database
     * @return false.
     */
    public boolean updateProduct(ModelProduct modelProduct, ModelProduct newModelProduct) {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("update ModelProduct set Model= :newModel and Price= :newPrice and Amount= :amount where " +
                "id = :id");// TODO: 23.06.22 update по id
        query.setParameter("newModel", newModelProduct.getModel());
        query.setParameter("newPrice", newModelProduct.getPrice());
        query.setParameter("newPAmount", newModelProduct.getAmount());

        List<ModelProduct> queryResultList = query.getResultList();
        entityManager.persist(queryResultList);
        transaction.commit();

        return false;
    }

    @Override
    /**
     * This method is used to delete a product's product in the DB.
     * @param query is a database query
     * @param queryResultList This is the result of the query in the database
     * @return false.
     */
    public boolean deleteProduct(ModelProduct modelProduct) {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("delete ModelProduct where id= :id");
        query.setParameter("id",modelProduct.getId()); // TODO: 25.06.22 Delete по id
        List queryResultList = query.getResultList();
        queryResultList.forEach(System.out::println);
        query.executeUpdate();
        transaction.commit();
        return false;
    }

    @Override
    /**
     * This method is used to read all products from the database.
     * @param query is a database query
     * @param resultList This is the result of the query in the database
     * @return optionalModelProductList is Optional.ofNullable(resultList).
     */
    public Optional<List<ModelProduct>> readAllProduct() {
        @Cleanup EntityManager entityManager = jpa.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<ModelProduct> query = entityManager.createQuery
                ("select m from ModelProduct as m join fetch m.product", ModelProduct.class);
        List<ModelProduct> resultList = query.getResultList();
        Optional<List<ModelProduct>> optionalModelProductList = Optional.ofNullable(resultList);
        return optionalModelProductList;
    }
}

