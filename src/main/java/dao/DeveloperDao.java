package dao;

import entity.Developer;

public interface DeveloperDao {
    boolean insertDeveloper(Developer developer);

    boolean updateDeveloper(Developer developer);

    Developer getByIdDeveloper(Long id);

    boolean deleteDeveloper(Long id);
}
