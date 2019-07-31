package dao;

import entity.Developer;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class DeveloperDaoImplementation implements DeveloperDao{
    private EntityManager entityManager;

    public DeveloperDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void entityManagerIsEmpty() {
        if (entityManager != null) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public boolean insertDeveloper(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(developer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateDeveloper(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(developer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Developer getByIdDeveloper(Long id) {
        try {
            entityManager.getTransaction().begin();
            Developer developer = entityManager.find(Developer.class, id);
            entityManager.getTransaction().commit();
            return developer;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteDeveloper(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Developer.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }
}
