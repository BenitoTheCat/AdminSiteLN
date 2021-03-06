/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.dao.infografiasrepository;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import com.lanacion.adminsiteln.model.video.VideoDocument;
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
     * @param ds
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Student table.
     * @param titulo
     * @param descripcion
     * @param tipo_infografia
     * @param estado
    */
   public void create(String titulo, String descripcion, Integer tipo_infografia, Integer estado);
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
     * @return 
    */
   public InfografiaDocument getInfografia(Integer id);
   /**
    * 
    * @param id
    * @return 
    */
   public VideoDocument getVideo(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
     * @return 
    */
   public List<InfografiaDocument> listInfografias();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
    */
   public void delete(Integer id);
   /** 
    * This is the method to be used to update
    * a record into the Student table.
     * @param idInfografia
     * @param titulo
     * @param descripcion
     * @param tipo_infografia
     * @param estado
    */
   public void update(Integer idInfografia, String titulo, String descripcion, Integer tipo_infografia, Integer estado);
   
    /**
     *
     * @param idInfografia
     * @param estado
     */
    public void updateEstado(Integer idInfografia, Integer estado);
    
    /**
     * 
     * @param idVideo
     * @param estado 
     */
    public void updateEstadoVideo(Integer idVideo, Integer estado);
    
    /**
     * 
     * @param idVideo 
     */
    public void deleteVideo(Integer idVideo);
    
    /**
     *
     * @param idInfografia
     * @param titulo
     * @param descripcion
     */
    public void updateDescripcion(Integer idInfografia, String titulo, String descripcion);
   
    /**
     *
     * @param titulo
     * @param querysolr
     */
    public void createVistas(String titulo, String querysolr);
    
    /**
     *
     * @return
     */
    public Integer getLastIdVista();
    
    /**
     *
     * @return
     */
    public Integer getLastIdInfografia();
    
    /**
     *
     * @param lastIdInfografia
     * @param lastIdVista
     * @param id_orden
     */
    public void createRelacionInfografiaVista(Integer lastIdInfografia, Integer lastIdVista, Integer id_orden);
    
    /**
     * 
     * @param url
     * @param servidor
     * @param autor
     * @param titulo
     * @param descripcion
     * @param comentarios
     * @param html_code 
     */
    public void createVideo(String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code);
    
    /**
     * 
     * @param idVideo
     * @param url
     * @param servidor
     * @param autor
     * @param titulo
     * @param descripcion
     * @param comentarios
     * @param html_code 
     */
    public void editVideo(int idVideo,String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code);
    
    /**
     *
     * @return
     */
    public List<VideoDocument> listVideos();
}
