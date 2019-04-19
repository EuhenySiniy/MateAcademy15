package dao;

import entity.Skill;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

@Log4j
public class SkillDaoImplementation implements SkillDao {
    private EntityManager entityManager;

    public SkillDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void entityManagerIsEmpty() {
        if (entityManager != null) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public boolean insertSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(skill);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateSkill(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(skill);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Skill getByIdSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            Skill skill = entityManager.find(Skill.class, id);
            entityManager.getTransaction().commit();
            return skill;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteSkill(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Skill.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            entityManagerIsEmpty();
            log.error(e.getMessage());
            return false;
        }
    }
}
