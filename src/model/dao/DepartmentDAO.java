package model.dao;

import java.util.List;

import model.entitities.Department;

public interface DepartmentDAO {
    
    void Insert(Department department);
    void Update(Department department);
    void DeleteById(Department department);
    void findById(Integer id);
    List<Department> findAll();
    
}
