/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import com.mysql.jdbc.Driver;

/**
 *
 * @author Luxo
 */
@Repository
public class InfografiasRepositoryImpl implements InfografiasRepository{
 
 private DriverManagerDataSource dataConn2;
 private DataSource dataSource;
 private JdbcTemplate jdbcTemplateObject;
 
   
 @Override
   public void setDataSource(DataSource dataSource2) {
       /*
      BasicDataSource dataConn=new BasicDataSource();
      dataConn.setDriverClassName("com.mysql.jdbc.Driver");
      dataConn.setUrl("jdbc:mysql://localhost:8889/DiarioLaNacion");
      dataConn.setUsername("root");
      dataConn.setPassword("root");
        
       
       
       
      if(dataSource == null){
          System.out.println("Es nulo loco++++++!!!!!");
      }
      */
      this.dataSource = dataSource2;
      System.out.println("Entro a mysql*************");
      this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
   }

 @Override
   public void create(String name, Integer age) {
      String SQL = "insert into Student (name, age) values (?, ?)";
      
      jdbcTemplateObject.update( SQL, name, age);
      System.out.println("Created Record Name = " + name + " Age = " + age);
   }

 @Override
   public InfografiaDocument getInfografia(Integer idInfografia) {
     try {
         System.out.println("Conexion:````` "+dataSource.toString());
         ResultSet executeQuery = dataSource.getConnection().prepareCall("select * from Infografias where idInfografia = 1").executeQuery();
         System.out.println("Query executeQuery "+executeQuery);
     } catch (SQLException ex) {
         Logger.getLogger(InfografiasRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
     }
      String SQL = "select * from Infografias where idInfografia = ?";
      InfografiaDocument infografia = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{idInfografia}, new InfografiaMapper());
      return infografia;
   }

 @Override
   public List<InfografiaDocument> listInfografias() {
      String SQL = "select * from Student";
      List <InfografiaDocument> infografias = jdbcTemplateObject.query(SQL, 
                                new InfografiaMapper());
      return infografias;
   }

 @Override
   public void delete(Integer id){
      String SQL = "delete from Student where id = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
   }

 @Override
   public void update(Integer id, Integer age){
      String SQL = "update Student set age = ? where id = ?";
      jdbcTemplateObject.update(SQL, age, id);
      System.out.println("Updated Record with ID = " + id );
   }

}
