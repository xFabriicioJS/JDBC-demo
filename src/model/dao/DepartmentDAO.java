package model.dao;

import java.util.List;

import model.entitities.Department;

public interface DepartmentDAO {
    
    void Insert(Department department);
    void Update(Department department);
    void DeleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
    
}
