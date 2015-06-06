/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.service.infografia;

import com.lanacion.adminsiteln.dao.infografiasrepository.InfografiasRepository;
import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import com.lanacion.adminsiteln.model.video.VideoDocument;
import com.lanacion.adminsiteln.services.PdfIndexerService.PdfIndexerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


/**
 *
 * @author Luxo
 */
@Service
public class InfografiaServiceImpl implements InfografiaService {
    
    
    @Autowired
    private PdfIndexerService solrconn;
           
    
    @Autowired
    private ApplicationContext context = new ClassPathXmlApplicationContext("MySqlBean.xml");

    private InfografiasRepository infoRepo = 
      (InfografiasRepository)context.getBean("InfografiasRepositoryImpl");

    @Override
    public InfografiaDocument getInfografia(Integer idInfografia) {
        return infoRepo.getInfografia(idInfografia);
    }
    
    @Override
    public VideoDocument getVideo(Integer idVideo) {
        return infoRepo.getVideo(idVideo);
    }

    @Override
    public List<InfografiaDocument> listInfografias() {
        return infoRepo.listInfografias();        
    }
    
    @Override
    public List<VideoDocument> listVideos() {
        return infoRepo.listVideos();        
    }

    @Override
    public void create(String titulo, String descripcion, Integer tipo_infografia, Integer estado) {
        infoRepo.create(titulo, descripcion, tipo_infografia, estado);
    }

    @Override
    public void delete(Integer id) {
        infoRepo.delete(id);
    }

    @Override
    public void update(Integer idInfografia, String titulo, String descripcion, Integer tipo_infografia, Integer estado) {
        infoRepo.update(idInfografia, titulo, descripcion, tipo_infografia, estado);
    }
    
    @Override
    public int getCountQuery(String UserQuery){
        try {
            return solrconn.countDocuments(UserQuery);
        } catch (SolrServerException ex) {
            Logger.getLogger(InfografiaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    @Override
    public void createVistas(String titulo, String querysolr){
        infoRepo.createVistas(titulo, querysolr);
    }
    
    @Override
    public Integer getLastIdVista(){
        return infoRepo.getLastIdVista();
    }
    
    @Override
    public Integer getLastIdInfografia(){
        return infoRepo.getLastIdInfografia();
    }
    
    @Override
    public void createRelacionInfografiaVista(Integer lastIdInfografia, Integer lastIdVista, Integer id_orden){
        infoRepo.createRelacionInfografiaVista(lastIdInfografia, lastIdVista, id_orden);
    }
    
    @Override
    public void updateEstado(Integer idInfografia, Integer estado){
        infoRepo.updateEstado(idInfografia, estado);
    }
    
    @Override
    public void deleteVideo(Integer idVideo){
        infoRepo.deleteVideo(idVideo);
    }
    
    @Override
    public void updateEstadoVideo(Integer idVideo, Integer estado){
        infoRepo.updateEstadoVideo(idVideo, estado);
    }
    
    @Override
    public void updateDescripcion(Integer idInfografia, String titulo,String descripcion){
        infoRepo.updateDescripcion(idInfografia, titulo, descripcion);
    }
    
    @Override
    public void createVideo(String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code){
        infoRepo.createVideo(url, servidor, autor, titulo, descripcion, comentarios, html_code);
    }
    
    @Override
    public void editVideo(int idVideo, String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code){
        infoRepo.editVideo(idVideo, url, servidor, autor, titulo, descripcion, comentarios, html_code);
    }
}
