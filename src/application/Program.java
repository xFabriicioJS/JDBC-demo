package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entitities.Department;
import model.entitities.Seller;

public class Program {
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

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

        
        System.out.println("=== TEST 4: insert Seller ===");

        Seller newSeller = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.0, department);

        sellerDao.Insert(newSeller);

        System.out.println("Inserted! New Id = " + newSeller.getId());

        System.out.println("=== TEST 5: Update Seller ===");

        seller = sellerDao.findById(1);

        seller.setName("Martha Waine");
        sellerDao.Update(seller);

        System.out.println("Uptated completed!");

        System.out.println("=== TEST 6: Seller delete ===");
        System.out.println("Enter id for delete user:");
        int id = sc.nextInt();

        sellerDao.DeleteById(id);

        System.out.println("Delete completed!");

        sc.close();
    }
}
