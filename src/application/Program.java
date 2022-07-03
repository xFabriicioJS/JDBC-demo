package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entitities.Department;
import model.entitities.Seller;

public class Program {
    
    public static void main(String[] args){

        SellerDAO sellerDao = new DaoFactory().createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");

        Department department = new Department(2, null);

        List<Seller> list = sellerDao.findByDepartment(department); 

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("=== TEST 3: seller findAll ===");

        List<Seller> listAll = sellerDao.findAll();

        for (Seller obj: listAll){
            System.out.println(obj);
        }
    }
}
