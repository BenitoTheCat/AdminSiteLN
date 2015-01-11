/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Luxo
 */
public interface InfografiasRepository {
    
    /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Student table.
    */
   public void create(String name, Integer age);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public InfografiaDocument getInfografia(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
    */
   public List<InfografiaDocument> listInfografias();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
    */
   public void delete(Integer id);
   /** 
    * This is the method to be used to update
    * a record into the Student table.
    */
   public void update(Integer id, Integer age);
    
}
