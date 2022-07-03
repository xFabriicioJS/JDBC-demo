package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Result;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entitities.Department;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;

    }

    @Override
    public void Insert(Department department) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "INSERT INTO department "
                + "(Id, Name) "
                + "VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, department.getId());
            st.setString(2, department.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);

            }else {
                throw new DbException("Unexpected erro! No rows were affected!");
            }


        }catch(SQLException error){
            throw new DbException(error.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }

    }

    @Override
    public void Update(Department department) {
        PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                "UPDATE deparment "
                + "SET Name = ? "
                + "WHERE Id = ?"
                );

                st.setString(1, department.getName());
                st.setInt(2, department.getId());
                
                st.executeUpdate();

            }catch(SQLException error){
                throw new DbException(error.getMessage());
            }
            finally{
                DB.closeStatement(st);
            }
        
    }

    @Override
    public void DeleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "DELETE FROM department "
                + "WHERE Id = ?"
            );

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0){
                throw new DbException("Unexpected error! No rows affected!");
            }
        }catch (SQLException error){
            throw new DbException(error.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
        
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM deparment WHERE Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));

                return dep;
            }
            return null;


        }catch(SQLException error){
            throw new DbException(error.getMessage());

        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn.prepareStatement(
                "SELECT * FROM department"
            );
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){
                Department dep = map.get(rs.getInt("Id"));

                if(dep == null){
                    dep = new Department();
                    dep.setId(rs.getInt("Id"));
                    dep.setName(rs.getString("Name"));  

                    map.put(rs.getInt("Id"), dep);
                }
                    list.add(dep);

            }
            return list;

            
        }catch(SQLException error){
            throw new DbException(error.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

}
