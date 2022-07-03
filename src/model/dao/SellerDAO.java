package model.dao;

import java.util.List;

import model.entitities.Department;
import model.entitities.Seller;

public interface SellerDAO {
    void Insert(Seller seller);
    void Update(Seller seller);
    void DeleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
