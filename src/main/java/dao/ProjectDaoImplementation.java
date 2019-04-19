package dao;

import entity.Project;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class ProjectDaoImplementation implements ProjectDao {
    private EntityManager entityManager;

    public ProjectDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void entityManagerIsEmpty() {
        if (entityManager != null) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public boolean insertProject(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateProject(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Project getByIdProject(Long id) {
        try {
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, id);
            entityManager.getTransaction().commit();
            return project;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteProject(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Project.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }
}
