package dao;

import entity.Customer;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class CustomerDaoImplementation implements CustomerDao {
    private EntityManager entityManager;

    public CustomerDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void entityManagerIsEmpty() {
        if (entityManager != null) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public boolean insertCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Customer getByIdCustomer(Long id) {
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }
}
