/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lanacion.adminsiteln.service.infografia;

import com.lanacion.adminsiteln.model.infografia.InfografiaDocument;
import com.lanacion.adminsiteln.model.video.VideoDocument;
import java.util.List;

/**
 *
 * @author Luxo
 */
public interface InfografiaService {

    public InfografiaDocument getInfografia(Integer idInfografia);
    
    public VideoDocument getVideo(Integer idVideo);
    
    public List<InfografiaDocument> listInfografias();
    
    public List<VideoDocument> listVideos();
    
    public void create(String titulo, String descripcion, Integer tipo_infografia, Integer estado);
    
    public void delete(Integer id);
    
    public void update(Integer idInfografia, String titulo, String descripcion, Integer tipo_infografia, Integer estado);
    
    public int getCountQuery(String UserQuery);
    
    public void createVistas(String titulo, String querysolr);
    
    public Integer getLastIdVista();
    
    public Integer getLastIdInfografia();
    
    public void createRelacionInfografiaVista(Integer lastIdInfografia, Integer lastIdVista, Integer id_orden);
    
    public void updateEstado(Integer idInfografia, Integer estado);
    
    public void updateEstadoVideo(Integer idVideo, Integer estado);
    
    public void deleteVideo(Integer idVideo);
    
    public void updateDescripcion(Integer idInfografia, String titulo,String descripcion);
    
    public void createVideo(String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code);
    
    public void editVideo(int idvideo, String url, String servidor, String autor, String titulo, String descripcion, String comentarios, String html_code);
}
