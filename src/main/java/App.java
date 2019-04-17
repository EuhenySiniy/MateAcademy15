import dao.DeveloperDao;
import entity.Developer;
import util.HibernateUtil;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        Developer developer = new Developer();
        developer.setName("Ivan");
        developer.setAge(28);
        developer.setGender("male");
        developer.setSalary(2000.00);

        DeveloperDao developerDao = new DeveloperDao(entityManager);
        developerDao.insertDeveloper(developer);
        HibernateUtil.shutdown();
    }
}
